package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

/**
 * @ClassNname：BaseResult.java
 * @Describe 实体类基类
 * @author huanglei
 * @time 2018/3/12 14:52
 */
@HttpResponse(parser = JsonResponseParser.class)
public class BaseResult {
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
