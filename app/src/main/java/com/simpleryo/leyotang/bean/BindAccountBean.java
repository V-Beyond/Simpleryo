package com.simpleryo.leyotang.bean;

/**
 * @ClassNname：BaseResult.java
 * @Describe 绑定账号实体类
 * @author huanglei
 * @time 2018/3/12 14:52
 */
public class BindAccountBean extends BaseResult {
    ThirdNoBean data;

    public ThirdNoBean getData() {
        return data;
    }

    public void setData(ThirdNoBean data) {
        this.data = data;
    }

    public class ThirdNoBean{
        String typeCode;
        String thirdNo;

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getThirdNo() {
            return thirdNo;
        }

        public void setThirdNo(String thirdNo) {
            this.thirdNo = thirdNo;
        }
    }
}
