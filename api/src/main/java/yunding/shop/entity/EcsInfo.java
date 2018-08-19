package yunding.shop.entity;

import java.util.Date;

/**
 * 服务器信息
 * @author guo
 */
public class EcsInfo {

    /**
     * 服务器IP地址
     */
    private String ecsIP;
    /**
     * 服务器端口
     */
    private Integer ecsPort;
    /**
     * 服务器当前时间
     */
    private Date date;
    /**
     * 当前Session数量
     */
    private Integer sessionNum;

    public String getEcsIP() {
        return ecsIP;
    }

    public void setEcsIP(String ecsIP) {
        this.ecsIP = ecsIP;
    }

    public Integer getEcsPort() {
        return ecsPort;
    }

    public void setEcsPort(Integer ecsPort) {
        this.ecsPort = ecsPort;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSessionNum() {
        return sessionNum;
    }

    public void setSessionNum(Integer sessionNum) {
        this.sessionNum = sessionNum;
    }
}
