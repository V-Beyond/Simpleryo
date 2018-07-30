package com.simpleryo.leyotang.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.annotation.Encoding;
import com.okhttplib.annotation.RequestType;
import com.okhttplib.callback.Callback;
import com.simpleryo.leyotang.app.SimpleryoApplication;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @author huanglei
 * @ClassNname：SimpleryoNetwork.java
 * @Describe 网络请求类
 * @time 2018/3/21 14:11
 */

public class SimpleryoNetwork {
    public static final String httpUrl = "https://api.simpleryo.com/";//接口域名
    public static final String h5Url = "http://wx.simpleryo.com/";//H5接口域名
    public static final String imgUrl = "http://p.simpleryo.com/";//阿里云图片自定义域名
//    public static final String imgUrl="https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/";//阿里云Bucket自带图片域名
//    public static final String imgUrl = "https://wongleoi.oss-cn-shanghai.aliyuncs.com/";//阿里云Bucket自带图片域名（黄磊自己的阿里云账号）

    public static final SimpleryoNetwork simpleryoNetwork = new SimpleryoNetwork();

    public SimpleryoNetwork() {
    }

    public SimpleryoNetwork getInstance() {
        return simpleryoNetwork;
    }

    /**
     * 获取token
     */
    public static String getToken() {
        String token = SharedPreferencesUtils.getKeyString("token");
        if (token.equalsIgnoreCase("")) {
            token = "simpleryo";
        }
        Log.w("cc", "token值：" + token);
        return token;
    }

    /**
     * 使用refreshToken
     * @param context
     * @param callback
     * @param refreshToken
     */
    public static void refreshToken(Context context, Callback callback, String refreshToken) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/token/refresh")
                .setRequestType(RequestType.PUT)//设置请求方式
                .addParam("refreshToken",refreshToken)//添加接口参数
                .build(), callback);
    }

    /**
     * 获取微信getAccess_token
     * @param context
     * @param callback
     * @param httpUrl
     */
    public static void getAccess_token(Context context, Callback callback,String httpUrl) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl)
                .setRequestType(RequestType.GET)//设置请求方式
                .build(), callback);
    }
    /**
     * 获取微信用户信息
     * @param context
     * @param callback
     * @param httpUrl
     */
    public static void getUserMsg(Context context, Callback callback,String httpUrl) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl)
                .setRequestType(RequestType.GET)//设置请求方式
                .build(), callback);
    }

    /**
     * 绑定第三方账号
     * @param context
     * @param callback
     * @param id
     * @param thirdNo
     * @param typeCode
     */
    public static void bindAccount(Context context, Callback callback,String id,String thirdNo,String typeCode) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("thirdNo", thirdNo);//第三方用户唯一标识
            jsonObject.put("typeCode", typeCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("cc", "注册json：" + jsonObject.toString()+"用户id："+id);
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/users/"+id+"/bind/account?token=" + getToken())
                .setRequestType(RequestType.POST)//设置请求方式
                .addParamJson(jsonObject.toString())
                .build(), callback);
    }
    /**
     * 用户注册
     *
     * @param callback
     * @param email
     * @param phone
     * @param code
     * @param password
     */
    public static void userRegister(Context context, Callback callback, String email, String phone, String code, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);//邮箱
            jsonObject.put("phone", phone);//手机号
            jsonObject.put("msgAuthCode", code);//验证码
            jsonObject.put("password", password);//密码
            jsonObject.put("loginName", phone);//登录名
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("cc", "注册json：" + jsonObject.toString());
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/users?token="+getToken())
                .setRequestType(RequestType.POST)//设置请求方式
                .addParamJson(jsonObject.toString())//添加接口参数
                .build(), callback);
    }

    /**
     * 获取验证码
     *
     * @param callback
     * @param phone
     */
    public static void userGetCode(Context context, Callback callback, String phone) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/phones/" + phone)
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .build(), callback);
    }

    /**
     * 用户登录
     *
     * @param callback
     * @param phone
     * @param pwd
     */
    public static void userLogin(Context context, Callback callback, String phone, String pwd) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("loginName", phone);//登录名
            jsonObject.put("password", pwd);//密码
        } catch (JSONException e) {
            e.printStackTrace();
        }
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/account/login")
                .setRequestType(RequestType.POST)//设置请求方式
                .addParamJson(jsonObject.toString())//添加接口参数
                .build(), callback);
    }

    /**
     * 获取首页数据
     *
     * @param context
     * @param callback
     */
    public static void getHomeCourse(Context context, Callback callback) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "home")
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .addParam("platform", "ANDROID")//平台
                .build(), callback);
    }

    /**
     * 获取日历订单
     *
     * @param context
     * @param callback
     * @param year
     * @param month
     */
    public static void getCalendarOrderList(Context context, Callback callback, int year, int month) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "o/orders/calendar")
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .addParam("year", year + "")//年
                .addParam("month", month + "")//月
                .build(), callback);
    }

    /**
     * 创建订单
     *
     * @param context
     * @param callback
     * @param courseId
     * @param payType
     * @param quantity
     * @param totalAmt
     */
    public static void createOrder(Context context, Callback callback, String coachId, String storeId, String courseId, String courseName, String payType, int quantity, int unitPrice, int totalAmt, int payAmt,String name,String phone,String remark,String aboutArrangeId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("coachId", coachId);
            jsonObject.put("storeId", storeId);
            jsonObject.put("courseId", courseId);
            jsonObject.put("courseName", courseName);
            jsonObject.put("payType", payType);
            jsonObject.put("quantity", quantity);
            jsonObject.put("routeCode", "ANDROID");
            jsonObject.put("totalAmt", totalAmt);
            jsonObject.put("unitPrice", unitPrice);
            jsonObject.put("payAmt", payAmt);
            jsonObject.put("userName", name);
            jsonObject.put("userPhone", phone);
            jsonObject.put("userRemark", remark);
            jsonObject.put("aboutArrangeId",aboutArrangeId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("cc", "订单json：" + jsonObject.toString());
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "o/orders?token=" + getToken())
                .setRequestType(RequestType.POST)//设置请求方式
                .addParamJson(jsonObject.toString())
                .build(), callback);
    }
    /**
     * 修改订单
     *
     * @param context
     * @param callback
     * @param courseId
     * @param payType
     * @param quantity
     * @param totalAmt
     */
    public static void updateOrder(Context context, Callback callback, String orderId,String coachId, String storeId, String courseId, String courseName, String payType, int quantity, int unitPrice, int totalAmt, int payAmt,String name,String phone,String remark,String aboutArrangeId,String creationDate) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("coachId", coachId);
            jsonObject.put("storeId", storeId);
            jsonObject.put("courseId", courseId);
            jsonObject.put("courseName", courseName);
            jsonObject.put("payType", payType);
            jsonObject.put("quantity", quantity);
            jsonObject.put("routeCode", "ANDROID");
            jsonObject.put("totalAmt", totalAmt);
            jsonObject.put("unitPrice", unitPrice);
            jsonObject.put("payAmt", payAmt);
            jsonObject.put("userName", name);
            jsonObject.put("userPhone", phone);
            jsonObject.put("userRemark", remark);
            jsonObject.put("aboutArrangeId",aboutArrangeId);
            jsonObject.put("creationDate",creationDate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("cc", "订单json：" + jsonObject.toString());
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "o/orders/"+orderId+"?token=" + getToken())
                .setRequestType(RequestType.PUT)//设置请求方式
                .addParamJson(jsonObject.toString())
                .build(), callback);
    }
    /**
     * 记录订单
     *
     * @param context
     * @param callback
     */
    public static void recordOrder(Context context, Callback callback, String ordeId, String amount, String merchantReference, String productName, String paymentMethod) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("amount", amount);//支付金额
            jsonObject.put("merchantReference", merchantReference);//订单号
            jsonObject.put("productName", productName);//订单名称
            jsonObject.put("paymentMethod", paymentMethod);//支付方式（暂时默认是支付宝）
            jsonObject.put("callbackUrl", "https://api.simpleryo.com/o/orders/callback/url");//支付异步通知地址
            jsonObject.put("userId", "U000000348");//Latipay平台的userId
            jsonObject.put("walletId ", "W000000384");//Latipay平台的walletId
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("cc", "json：" + jsonObject.toString());
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "o/orders/" + ordeId + "/record?token=" + getToken())
                .setRequestType(RequestType.POST)//设置请求方式
                .addParamJson(jsonObject.toString())
                .build(), callback);
    }

    /**
     * 查询课程列表
     *
     * @param context
     * @param callback
     */
    public static void getCourse(Context context, Callback callback, String storeId, String name, String tagId1, String tagId3) {
        HttpInfo.Builder builder = new HttpInfo.Builder();
        builder.setUrl(httpUrl + "p/courses");
        builder.setRequestType(RequestType.GET);
        builder.addParam("token", getToken());
        if (!storeId.equalsIgnoreCase("")) {
            builder.addParam("storeId", storeId);//商家id
        }
        if (!name.equalsIgnoreCase("")) {
            builder.addParam("name", name);//课程名
        }
        if (!tagId3.equalsIgnoreCase("")) {
            builder.addParam("tagId3", tagId3);
        }
        if (!tagId1.equalsIgnoreCase("")) {
            builder.addParam("tagId1", tagId1);
        }
        doHttpAsync(context, builder.build(), callback);
    }
    /**
     * 查询课程列表
     *
     * @param context
     * @param callback
     */
    public static void getSearchCourse(Context context, Callback callback,  String name, int offset, int limit) {
        HttpInfo.Builder builder = new HttpInfo.Builder();
        builder.setUrl(httpUrl + "p/courses");
        builder.setRequestType(RequestType.GET);
        builder.addParam("token", getToken());
        if (!name.equalsIgnoreCase("")) {
            builder.addParam("name", name);//课程名
        }
        builder.addParam("offset", offset + "");
        builder.addParam("limit", limit + "");
        doHttpAsync(context, builder.build(), callback);
    }
    /**
     * 修改用户信息
     *
     * @param context
     * @param callback
     * @param userId
     * @param name
     */
    public static void updateInfo(Context context, MyBaseProgressCallbackImpl callback, String userId, String email, String name, String loginName, String gender, String starSign, String des, String avatarUrl) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", userId);
            jsonObject.put("email", email);
            jsonObject.put("loginName", loginName);
            jsonObject.put("name", name);
            jsonObject.put("nickName", name);
            jsonObject.put("gender", gender);
            jsonObject.put("avatarUrl", avatarUrl);
            jsonObject.put("intro", des);
            jsonObject.put("starSign", starSign);
            jsonObject.put("role", "NORMAL");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("cc", "json:" + jsonObject.toString());
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/users/" + userId + "?token=" + getToken())
                .setRequestEncoding(Encoding.UTF_8)
                .setRequestType(RequestType.PUT)//设置请求方式
                .addParamJson(jsonObject.toString())//添加接口参数
                .build(), callback);
    }

    /**
     * 根据课程状态查询购买课程列表
     *
     * @param context
     * @param callback
     */
    public static void getBuyAllCourse(Context context, MyBaseProgressCallbackImpl callback, String userId, String status, int offset, int limit) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "p/courses/buy")
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .addParam("userId", userId)
                .addParam("status", status)
                .addParam("offset", offset + "")
                .addParam("limit", limit + "")
                .build(), callback);
    }


    /**
     * 查询课程详情
     *
     * @param context
     * @param callback
     * @param courseId
     */
    public static void getCourseDetail(Context context, MyBaseProgressCallbackImpl callback, String courseId) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "p/courses/" + courseId)
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .build(), callback);
    }

    /**
     * 查询推荐机构
     *
     * @param context
     * @param callback
     */
    public static void getRecommendStores(Context context, MyBaseProgressCallbackImpl callback) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/stores")
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .addParam("isRecommend", "true")
                .build(), callback);
    }

    /**
     * 根据机构id查询教练
     *
     * @param context
     * @param callback
     * @param storeId
     */
    public static void getCoachesByStoreId(Context context, MyBaseProgressCallbackImpl callback, String storeId) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/coaches")
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .addParam("storeId", storeId)
                .build(), callback);
    }

    /**
     * 查询关注门店列表
     *
     * @param context
     * @param callback
     */
    public static void getStoresList(Context context, MyBaseProgressCallbackImpl callback, int offset, int limit) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/follow/stores")
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .addParam("offset", offset + "")
                .addParam("limit", limit + "")
                .build(), callback);
    }

    /**
     * 查询收藏列表
     *
     * @param context
     * @param callback
     */
    public static void geCollectList(Context context, MyBaseProgressCallbackImpl callback, int offset, int limit) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/collect/courses")
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .addParam("offset", offset + "")
                .addParam("limit", limit + "")
                .build(), callback);
    }
    /**
     * 关注门店
     *
     * @param context
     * @param callback
     * @param storeId
     */
    public static void followStore(Context context, MyBaseProgressCallbackImpl callback, String storeId) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/link/resources/" + storeId)
                .setRequestType(RequestType.POST)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .addParam("typeCode", "FOLLOW")
                .build(), callback);
    }

    /**
     * 取消关注门店
     *
     * @param context
     * @param callback
     * @param storeId
     */
    public static void disfollowStore(Context context, MyBaseProgressCallbackImpl callback, String storeId) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/link/resources/" + storeId + "?token=" + getToken() + "&typeCode=FOLLOW")
                .setRequestType(RequestType.DELETE)//设置请求方式
                .build(), callback);
    }

    /**
     * 收藏课程
     *
     * @param context
     * @param callback
     * @param courseId
     */
    public static void collectCourse(Context context, MyBaseProgressCallbackImpl callback, String courseId) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/link/resources/" + courseId)
                .setRequestType(RequestType.POST)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .addParam("typeCode", "COLLECT")
                .build(), callback);
    }

    /**
     * 取消收藏课程
     *
     * @param context
     * @param callback
     * @param courseId
     */
    public static void disCollectCourse(Context context, MyBaseProgressCallbackImpl callback, String courseId) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/link/resources/" + courseId + "?token=" + getToken() + "&typeCode=COLLECT")
                .setRequestType(RequestType.DELETE)//设置请求方式
                .build(), callback);
    }

    /**
     * 查询用户信息
     *
     * @param context
     * @param callback
     * @param userId
     */
    public static void getUserInfo(Context context, MyBaseProgressCallbackImpl callback, String userId) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/users/" + userId)
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .build(), callback);
    }

    /**
     * 添加用户地址信息
     *
     * @param context
     * @param callback
     * @param userId
     * @param linkman
     * @param phone
     * @param provice
     * @param city
     * @param district
     * @param detail
     */
    public static void addUserAddress(Context context, MyBaseProgressCallbackImpl callback, String userId, String linkman, String phone, String provice, String city, String district, String detail) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("linkman", linkman);
            jsonObject.put("phone", phone);
            jsonObject.put("provice", provice);
            jsonObject.put("city", city);
            jsonObject.put("id", userId);
            jsonObject.put("district", district);
            jsonObject.put("lat", "31.22");
            jsonObject.put("lng", "121.48");
            jsonObject.put("detail", detail);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("cc", "json:" + jsonObject.toString());
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "/u/users/" + userId + "/addresses?token=" + getToken())
                .setRequestType(RequestType.POST)//设置请求方式
                .addParamJson(jsonObject.toString())
                .build(), callback);
    }

    /**
     * 查询订单列表
     *
     * @param context
     * @param callback
     */
    public static void getOrders(Context context, MyBaseProgressCallbackImpl callback, String status, int offset, int limit) {
        HttpInfo.Builder builder = new HttpInfo.Builder();
        builder.setUrl(httpUrl + "o/orders");
        builder.setRequestType(RequestType.GET);
        builder.addParam("token", getToken());
        if (!status.equalsIgnoreCase("")) {
            builder.addParam("status", status);
        }
        builder.addParam("offset", offset + "");
        builder.addParam("limit", limit + "");
        doHttpAsync(context, builder.build(), callback);
    }

    /**
     * 取消订单
     *
     * @param context
     * @param callback
     */
    public static void cancelOrder(Context context, MyBaseProgressCallbackImpl callback, String orderId) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "o/orders/" + orderId + "/cancalled")
                .setRequestType(RequestType.PUT)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .build(), callback);
    }

    /**
     * 查询订单详情
     *
     * @param context
     * @param callback
     */
    public static void getOrderDetail(Context context, MyBaseProgressCallbackImpl callback, String orderId) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "o/orders/" + orderId)
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .build(), callback);
    }

    /**
     * 根据门店名称查询门店列表
     *
     * @param context
     * @param callback
     */
    public static void getStoreListByName(Context context, MyBaseProgressCallbackImpl callback, String name) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/stores")
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .addParam("name", name)
                .build(), callback);
    }

    /**
     * 查询门店详情
     *
     * @param context
     * @param callback
     */
    public static void getStoreDetail(Context context, MyBaseProgressCallbackImpl callback, String storeId) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "u/stores/" + storeId)
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .build(), callback);
    }

    /**
     * 查询消息
     *
     * @param context
     * @param callback
     */
    public static void getMessage(Context context, MyBaseProgressCallbackImpl callback) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "s/messages")
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .build(), callback);
    }

    /**
     * 查询消息列表
     *
     * @param context
     * @param callback
     */
    public static void getMessageList(Context context, MyBaseProgressCallbackImpl callback,int offset, int limit) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "s/contents")
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .addParam("typeCode", "MESSAGE")
                .addParam("offset", offset + "")
                .addParam("limit", limit + "")
                .build(), callback);
    }

    /**
     * 获取七牛的TOKEN
     *
     * @param context
     * @param callback
     */
    public static void getQiNiuToken(Context context, MyBaseProgressCallbackImpl callback) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "qiniu/uptoken")
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .build(), callback);
    }

    /**
     * 上传文件到七牛服务器
     *
     * @param context
     * @param callback
     */
    public static void uploadPicToQiNiu(Context context, MyBaseProgressCallbackImpl callback, String key, String localFile) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("key", key);
            jsonObject.put("localFile", localFile);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("cc", "json:" + jsonObject.toString());
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "qiniu/upload")
                .setRequestType(RequestType.POST)//设置请求方式
                .addParamJson(jsonObject.toString())
                .build(), callback);
    }

    /**
     * 获取当前具体位置
     *
     * @param context
     * @param callback
     * @param lat
     * @param lng
     */
    public static void getAddressInfo(Context context, MyBaseProgressCallbackImpl callback, double lat, double lng) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&result_type=street_address&key=" + SimpleryoApplication.GOOGLEAPIKEY + "&language=zh-CN")
                .setRequestType(RequestType.GET)//设置请求方式
                .build(), callback);
    }

    /**
     * 订单评论
     *
     * @param context
     * @param callback
     * @param resourceId
     * @param comment
     * @param creationTime
     * @param point
     */
    public static void reviewCourse(Context context, MyBaseProgressCallbackImpl callback, String resourceId, String comment, String creationTime, String point, JsonArray imageUrls) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("comment", comment);
        jsonObject.addProperty("creationTime", creationTime);
        jsonObject.addProperty("point", point);
        jsonObject.addProperty("resourceId", resourceId);
        jsonObject.addProperty("typeCode", "ORDER");
        jsonObject.add("imageUrls", imageUrls);
        Log.w("cc", "json:" + jsonObject.toString());
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "r/" + resourceId + "/comments?token=" + getToken())
                .setRequestType(RequestType.POST)//设置请求方式
                .addParamJson(jsonObject.toString())
                .build(), callback);
    }

    /**
     * 投诉建议
     *
     * @param context
     * @param callback
     * @param body
     * @param imageUrls
     */
    public static void addComplaint(Context context, MyBaseProgressCallbackImpl callback, String body, JsonArray imageUrls) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", "新普乐android端投诉建议");
        jsonObject.addProperty("body", body);
        jsonObject.add("imageUrls", imageUrls);
        jsonObject.addProperty("typeCode", "COMPLAINT");
        Log.w("cc", "json:" + jsonObject.toString());
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "s/contents?token=" + getToken())
                .setRequestType(RequestType.POST)//设置请求方式
                .addParamJson(jsonObject.toString())
                .build(), callback);
    }

    /**
     * 查询优惠券类型列表
     * @param context
     * @param callback
     */
    public static void cardcoupontypes(Context context, MyBaseProgressCallbackImpl callback,int offset,int limit) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "coupon/cardcoupontypes")
                .addParam("token", getToken())//添加接口参数
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("offset", offset + "")
                .addParam("limit", limit + "")
                .build(), callback);
    }

    /**
     * 查询优惠券列表
     * @param context
     * @param callback
     */
    public static void tickets(Context context, MyBaseProgressCallbackImpl callback,String status,String couponId,int offset,int limit) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "coupon/tickets")
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .addParam("status", status)
                .addParam("couponId", couponId)
                .addParam("offset", offset + "")
                .addParam("limit", limit + "")
                .build(), callback);
    }
    /**
     * 领取券
     * @param context
     * @param callback
     */
    public static void cardcoupontypes(Context context, MyBaseProgressCallbackImpl callback,String id) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "/coupon/cardcoupontypes/"+id+"/tickets")
                .setRequestType(RequestType.PUT)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .build(), callback);
    }

    /**
     * GET /coupon/tickets/{id} 用户查询领取券详情
     * @param context
     * @param callback
     */
    public static void tickets(Context context, MyBaseProgressCallbackImpl callback,String id) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "coupon/tickets?id="+id)
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .build(), callback);
    }
    /**
     * /coupon/available/tickets
     查询可用优惠券
     * @param context
     * @param callback
     */
    public static void availableTickets(Context context, MyBaseProgressCallbackImpl callback,String storeId,String courseId,int offset,int limit) {
        doHttpAsync(context, HttpInfo.Builder()
                .setUrl(httpUrl + "coupon/available/tickets")
                .setRequestType(RequestType.GET)//设置请求方式
                .addParam("token", getToken())//添加接口参数
                .addParam("storeId", storeId)
                .addParam("courseId", courseId)
                .addParam("offset", offset + "")
                .addParam("limit", limit + "")
                .build(), callback);
    }
    /**
     * 异步请求
     *
     * @param info     请求信息体
     * @param callback 结果回调接口
     */
    public static void doHttpAsync(Context context, HttpInfo info, final com.okhttplib.callback.Callback callback) {
        OkHttpUtil.getDefault(context).doAsync(info, new com.okhttplib.callback.Callback() {
            @Override
            public void onSuccess(HttpInfo info) throws IOException {
                callback.onSuccess(info);
            }

            @Override
            public void onFailure(HttpInfo info) throws IOException {
                callback.onFailure(info);
            }
        });
    }
}
