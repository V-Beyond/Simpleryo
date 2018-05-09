package com.simpleryo.leyotang.bean;

/**
 * @ClassNname：BusEntity.java
 * @Describe EventBus 消息通知使用
 * @author huanglei
 * @time 2018/3/22 16:04
 */

public class BusEntity {
    private int type;//什么类型
    private String content;//使用到的参数
    CollectionListBean.DataBean dataBean;
    OrderListBean.DataBean orderDataBean;
    HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean coursesBean;
    CourseListBean.DataBeanX courseListBean;
    boolean isCollect;
    public BusEntity() {
    }

    public BusEntity(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public BusEntity(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public BusEntity(int type, String content, boolean isCollect) {
        this.type = type;
        this.content = content;
        this.isCollect = isCollect;
    }

    public BusEntity(int type, String content, CollectionListBean.DataBean dataBean) {
        this.type = type;
        this.content = content;
        this.dataBean = dataBean;
    }

    public BusEntity(int type, OrderListBean.DataBean orderDataBean) {
        this.type = type;
        this.orderDataBean = orderDataBean;
    }
    public BusEntity(int type, HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean coursesBean) {
        this.type = type;
        this.coursesBean = coursesBean;
    }

    public BusEntity(int type, CourseListBean.DataBeanX courseListBean) {
        this.type = type;
        this.courseListBean = courseListBean;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CollectionListBean.DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(CollectionListBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public OrderListBean.DataBean getOrderDataBean() {
        return orderDataBean;
    }

    public void setOrderDataBean(OrderListBean.DataBean orderDataBean) {
        this.orderDataBean = orderDataBean;
    }

    public HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean getCoursesBean() {
        return coursesBean;
    }

    public void setCoursesBean(HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean coursesBean) {
        this.coursesBean = coursesBean;
    }

    public CourseListBean.DataBeanX getCourseListBean() {
        return courseListBean;
    }

    public void setCourseListBean(CourseListBean.DataBeanX courseListBean) {
        this.courseListBean = courseListBean;
    }
}
