package yunding.shop.utils;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yunding.shop.dto.JwtResult;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author Meteor
 */
public class JwtUtil {

    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static String secretKey = "yundingshop2018";

    /**
     * 生成token
     * @param claims
     * @param ttl 超时时间
     */
    public static JwtResult createJwt(Claims claims, long ttl) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setClaims(claims)
                .setIssuedAt(now);

        builder.signWith(signatureAlgorithm, signingKey);

        long expMillis = 0L;
        if (ttl >= 0) {
            expMillis = nowMillis + ttl * 1000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        String token = builder.compact();
        return new JwtResult(token, expMillis);
    }

    public JwtResult updateToken(String token, long ttl) {
        try {
            Claims claims = verifyToken(token);
            return createJwt(claims, ttl);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 验证token
     */
    public static Claims verifyToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token).getBody();
    }

}