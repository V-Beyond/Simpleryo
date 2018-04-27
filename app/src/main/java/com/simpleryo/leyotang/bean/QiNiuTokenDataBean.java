package com.simpleryo.leyotang.bean;

/**
 * @ClassNname：QiNiuTokenDataBean.java
 * @Describe 获取七牛token
 * @author huanglei
 * @time 2018/4/23 13:27
 */
public class QiNiuTokenDataBean {


    private String token;
    private String domainName;
    private String upUrl;
    private String upUrlHttps;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getUpUrl() {
        return upUrl;
    }

    public void setUpUrl(String upUrl) {
        this.upUrl = upUrl;
    }

    public String getUpUrlHttps() {
        return upUrlHttps;
    }

    public void setUpUrlHttps(String upUrlHttps) {
        this.upUrlHttps = upUrlHttps;
    }
}
