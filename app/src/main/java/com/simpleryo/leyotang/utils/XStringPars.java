package com.simpleryo.leyotang.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassNname：XStringPars.java
 * @Describe 字符串工具类
 * @author huanglei
 * @time 2018/3/19 11:04
 */
@SuppressLint("NewApi")
public class XStringPars {


    /**
     * 手机唯一标示
     *
     * @param activity
     * @return
     */
    public static String phonewy(Context activity) {
        return Secure.getString(activity.getContentResolver(),
                Secure.ANDROID_ID);
    }
    //判断email格式是否正确
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }
    /*-正则表达式判断手机号*/
    public static boolean isMobileNO(String mobiles) {// 正确 返回 true
        Pattern p = Pattern
                .compile("^(13[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9]|14[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 判断密码是否是6-12为数字加字母
     * @param pwd
     * @return
     */
//    public static boolean isCorrectPwd(String pwd) {// 正确 返回 true
//        Pattern p = Pattern
//                .compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$");
//        Matcher m = p.matcher(pwd);
//        return m.matches();
//    }

    /**
     * 价格保留两位小数
     * @param price
     * @return
     */
    public static  String foramtPrice(int price){
        float distanceValue = Math.round((price))/100f;
        DecimalFormat decimalFormat =new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String formatPrice = decimalFormat.format(distanceValue);//format 返回的是字符串
        return formatPrice;
    }

    public static boolean check(String str, String regex) {
        boolean flag = false;
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }



    /**
     * @param phone
     * @param fgzf  分隔的字符
     * @return
     */
    public static String phoneformat(String phone, String fgzf) {
        StringBuffer newstr = new StringBuffer();
        String[] v = new String[3];
        v[0] = phone.substring(0, 3);
        v[1] = phone.substring(3, 7);
        v[2] = phone.substring(7, phone.length());
        for (int i = 0; i < v.length; i++) {
            newstr.append(v[i] + (i == v.length - 1 ? "" : fgzf));
        }
        return newstr.toString();
    }


    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int code = info.versionCode;
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 时间戳转为时间(年月日，时分秒)
     *
     * @param cc_time 时间戳
     * @return
     */
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time));
        return re_StrTime;
    }
    /**
     * 时间戳转为时间(年月日，时分秒)
     *
     * @param cc_time 时间戳
     * @return
     */
    public static String getCouponTime(String cc_time) {
        String re_StrTime = null;
        //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time));
        return re_StrTime;
    }
    /**
     * md5加密
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

}
