<%@ page import="yunding.shop.web.alipay.AlipayController" %>
<%@ page import="yunding.shop.dto.RequestResult" %>
<%@ page import="yunding.shop.service.AlipayService" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="yunding.shop.service.impl.AlipayServiceImpl" %>
<%@ page import="yunding.shop.entity.Order" %>
<%@ page import="yunding.shop.dto.ServiceResult" %>
<%@ page import="yunding.shop.utils.AlipayUtil" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="org.springframework.context.support.FileSystemXmlApplicationContext" %><%--
  Created by IntelliJ IDEA.
  User: huguobin
  Date: 2018/8/7
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    ApplicationContext context=new FileSystemXmlApplicationContext("E:\\IDEAEJ\\yunding_shop\\api\\src\\main\\resources\\spring\\spring-dao.xml");
    AlipayUtil alipayUtil= (AlipayUtil) context.getBean("alipay");
    String string=alipayUtil.alipayClient("4","1","123","50");
    out.println(string);
%>
</body>
</html>
