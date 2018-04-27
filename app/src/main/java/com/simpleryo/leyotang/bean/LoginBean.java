package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

/**
 * @ClassNname：LoginBean.java
 * @Describe 用户登录实体类
 * @author huanglei
 * @time 2018/4/11 9:47
 */
@HttpResponse(parser = JsonResponseParser.class)
public class LoginBean extends BaseResult {

    /**
     * data : {"token":"b7e3c1e69fc44ca18d4d31cf64584012","userId":"5acd688b51183135c4a19e6e","name":null,"role":null}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : b7e3c1e69fc44ca18d4d31cf64584012
         * userId : 5acd688b51183135c4a19e6e
         * name : null
         * role : null
         */

        private String token;
        private String userId;
        private String name;
        private String role;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
