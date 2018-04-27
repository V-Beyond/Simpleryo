package com.simpleryo.leyotang.bean;

/**
 * Created by 77429 on 2018/3/25.
 */

public class MultipleItem   implements MultiItemEntity{
    public static  final int HOMEBANNER=1;
    public static  final int HOMEHOTCOURSE=2;
    public static  final int HOMEEXCELLENT=3;
    public static  final int HOMECOURSETYPE=4;
    public static final int ORDER = 5;
    public static final int MYATTENTION =6;
    public static final int MYCOLLECTION = 7;
    public static final int InstitutionalRecommen = 8;
    public static  final int HOMEINTRODUCTORYCOURSE=9;
    private int itemType;

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

   OrderListBean.DataBean orderListBean;

    public OrderListBean.DataBean getOrderListBean() {
        return orderListBean;
    }

    public void setOrderListBean(OrderListBean.DataBean orderListBean) {
        this.orderListBean = orderListBean;
    }
    CourseListBean.DataBeanX courseListBean;

    CollectionListBean.DataBean collectDataBean;
    StoreFollowBean.DataBean storeDataBean;
    RecommendStoresBean.DataBean recommendDataBean;

    public RecommendStoresBean.DataBean getRecommendDataBean() {
        return recommendDataBean;
    }

    public void setRecommendDataBean(RecommendStoresBean.DataBean recommendDataBean) {
        this.recommendDataBean = recommendDataBean;
    }

    public StoreFollowBean.DataBean getStoreDataBean() {
        return storeDataBean;
    }

    public void setStoreDataBean(StoreFollowBean.DataBean storeDataBean) {
        this.storeDataBean = storeDataBean;
    }

    public CollectionListBean.DataBean getCollectDataBean() {
        return collectDataBean;
    }

    public void setCollectDataBean(CollectionListBean.DataBean collectDataBean) {
        this.collectDataBean = collectDataBean;
    }

    public CourseListBean.DataBeanX getCourseListBean() {
        return courseListBean;
    }

    public void setCourseListBean(CourseListBean.DataBeanX courseListBean) {
        this.courseListBean = courseListBean;
    }

    CommonBean commonBean;

    public CommonBean getCommonBean() {
        return commonBean;
    }

    public void setCommonBean(CommonBean commonBean) {
        this.commonBean = commonBean;
    }
}
