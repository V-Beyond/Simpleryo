package com.simpleryo.leyotang.utils;

import android.content.SharedPreferences;
import android.widget.Toast;

import org.xutils.x;

import static android.content.Context.MODE_MULTI_PROCESS;

/**
 * @ClassNname：SharedPreferencesUtils.java
 * @Describe 数据保存类
 * @author huanglei
 * @time 2018/3/19 10:59
 */

public class SharedPreferencesUtils {
    protected static  String  FileName="app";
    //    --------------数据保存-------------------
    public static void saveKeyString(String key, String value) {
        SharedPreferences.Editor editor = x.app().getApplicationContext().getSharedPreferences(FileName, MODE_MULTI_PROCESS).edit();
        //步骤2-2：将获取过来的值放入文件
        editor.putString(key, value);
        //步骤3：提交
        editor.commit();
    }


    public static String getKeyString(String key) {
        SharedPreferences read = x.app().getApplicationContext().getSharedPreferences(FileName, MODE_MULTI_PROCESS);
        //步骤2：获取文件中的值
        String value = read.getString(key, "");
        return value;
    }

    //    --------------数据保存-------------------
    public static void saveKeyint(String key, int value) {
        SharedPreferences.Editor editor = x.app().getApplicationContext().getSharedPreferences(FileName, MODE_MULTI_PROCESS).edit();
        //步骤2-2：将获取过来的值放入文件
        editor.putInt(key, value);
        //步骤3：提交
        editor.commit();
    }

    public static int getKeyint(String key) {
        SharedPreferences read = x.app().getApplicationContext().getSharedPreferences(FileName, MODE_MULTI_PROCESS);
        //步骤2：获取文件中的值
        int value = read.getInt(key, 0);
        return value;
    }
    //    --------------数据保存-------------------

    public static void saveKeyBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = x.app().getApplicationContext().getSharedPreferences(FileName, MODE_MULTI_PROCESS).edit();
        //步骤2-2：将获取过来的值放入文件
        editor.putBoolean(key, value);
        //步骤3：提交
        editor.commit();
    }

    public static boolean getKeyBoolean(String key) {
        SharedPreferences read = x.app().getApplicationContext().getSharedPreferences(FileName, MODE_MULTI_PROCESS);
        //步骤2：获取文件中的值
        boolean value = read.getBoolean(key, false);
        return value;
    }

    public static void customeToast(String msg) {
        Toast.makeText(x.app().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
