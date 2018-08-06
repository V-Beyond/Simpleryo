package com.simpleryo.leyotang.utils;

import java.util.List;

/**
 * 文件名：Data.java
 * 作用：初始化菜单栏中的数据
 * Created by Chitose on 2018/5/3.
 */

public class Data {
    public static void initFirstData(List<PopItem> itemList){
        //一级目录List
        itemList.add(new PopItem(1, 0, "语文"));
        itemList.add(new PopItem(2, 0, "数学"));
        itemList.add(new PopItem(3, 0, "英语"));
        itemList.add(new PopItem(4, 0, "物理"));
        itemList.add(new PopItem(5, 0, "化学"));
        itemList.add(new PopItem(6, 0, "生物"));
    }

    public static void initSecondData(List<PopItem> itemList){
        //二级目录List
        itemList.add(new PopItem(1, 0, "全部分类"));
        itemList.add(new PopItem(2, 1, "全部分类"));
        itemList.add(new PopItem(3, 0, "家装"));
        itemList.add(new PopItem(4, 3, "全部课程"));
        itemList.add(new PopItem(5, 3, "维修"));
        itemList.add(new PopItem(6, 3, "装修"));
        itemList.add(new PopItem(7, 3, "电器知识"));
    }

    public static void initThirdData(List<PopItem> itemList){
        //三级目录List
        itemList.add(new PopItem(1, 0, "语言"));
        itemList.add(new PopItem(2, 1, "说话的"));
        itemList.add(new PopItem(3, 1, "编程的"));
        itemList.add(new PopItem(4, 1, "凑数的"));
        itemList.add(new PopItem(5, 1, "还是凑数的"));
        itemList.add(new PopItem(6, 2, "中文"));
        itemList.add(new PopItem(7, 2, "英文"));
        itemList.add(new PopItem(8, 2, "法语"));
        itemList.add(new PopItem(9, 2, "日语"));
        itemList.add(new PopItem(10, 3, "C语言"));
        itemList.add(new PopItem(11, 3, "Java"));
        itemList.add(new PopItem(12, 3, "Python"));
        itemList.add(new PopItem(13, 0, "性别"));
        itemList.add(new PopItem(14, 13, "男性"));
        itemList.add(new PopItem(15, 13, "女性"));
        itemList.add(new PopItem(16, 0, "课程"));
        itemList.add(new PopItem(17, 16, "公选课"));
        itemList.add(new PopItem(18, 16, "专业课"));
        itemList.add(new PopItem(19, 17, "大英"));
        itemList.add(new PopItem(20, 17, "数学"));
        itemList.add(new PopItem(21, 18, "数据库"));
        itemList.add(new PopItem(22, 18, "数据结构"));
        itemList.add(new PopItem(23, 18, "操作系统"));
        itemList.add(new PopItem(24, 0, "凑数"));
    }

}
