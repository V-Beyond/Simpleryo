package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

/**
 * @ClassNname：CodeBean.java
 * @Describe 短信验证码实体类
 * @author huanglei
 * @time 2018/4/11 9:15
 */
@HttpResponse(parser = JsonResponseParser.class)
public class CodeBean extends BaseResult {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public  class DataBean {

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
