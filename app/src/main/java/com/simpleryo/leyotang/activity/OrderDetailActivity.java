package com.simpleryo.leyotang.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.OrderDetailBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.simpleryo.leyotang.utils.XStringPars;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * @ClassNname：MyCourse.java
 * @Describe 订单详情页面
 * @author huanglei
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_order_detail)
public class OrderDetailActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_order_img)
    ImageView iv_order_img;
    @ViewInject(R.id.tv_address)
    TextView tv_address;
    @ViewInject(R.id.tv_coach_name)
    TextView tv_coach_name;
    @ViewInject(R.id.tv_store_name)
    TextView tv_store_name;
    Transformation transformation = new RoundedTransformationBuilder()
            .cornerRadius(30)
            .borderWidth(5)
            .borderColor(Color.WHITE)
            .oval(true)
            .build();
    @ViewInject(R.id.iv_coach_img)
    ImageView iv_coach_img;
    @ViewInject(R.id.tv_order_number)
    TextView tv_order_number;
    @ViewInject(R.id.tv_total_price)
    TextView tv_total_price;
    @ViewInject(R.id.tv_shop_price)
    TextView tv_shop_price;
    @ViewInject(R.id.tv_count)
    TextView tv_count;
    @ViewInject(R.id.tv_course_name)
    TextView tv_course_name;
    @ViewInject(R.id.tv_order_course_name)
    TextView tv_order_course_name;
    @ViewInject(R.id.tv_time)
    TextView tv_time;
    String orderId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("订单详情");

        orderId=getIntent().getStringExtra("orderId");
        SimpleryoNetwork.getOrderDetail(OrderDetailActivity.this,new MyBaseProgressCallbackImpl(OrderDetailActivity.this){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                loadingDialog.dismiss();
                OrderDetailBean orderDetailBean=info.getRetDetail(OrderDetailBean.class);
                if (orderDetailBean.getData().getImageUrl()!=null){
                    Picasso.with(OrderDetailActivity.this).load(orderDetailBean.getData().getImageUrl()).into(iv_order_img);
                }else{
                    Picasso.with(OrderDetailActivity.this).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").into(iv_order_img);
                }
                tv_course_name.setText("课程名称："+orderDetailBean.getData().getCourseName());
                tv_order_course_name.setText(orderDetailBean.getData().getCourseName());
                tv_total_price.setText(XStringPars.foramtPrice(orderDetailBean.getData().getTotalAmt())+"$");
                tv_shop_price.setText(XStringPars.foramtPrice(orderDetailBean.getData().getUnitPrice())+"$");
                tv_count.setText("X"+orderDetailBean.getData().getQuantity());
                tv_order_number.setText("订单号："+orderDetailBean.getData().getNo());
                if (orderDetailBean.getData().getCoach()!=null){
                    tv_coach_name.setText("授课教练："+orderDetailBean.getData().getCoach().getName());
                    Picasso.with(OrderDetailActivity.this).load(orderDetailBean.getData().getCoach().getAvatarUrl()).transform(transformation).into(iv_coach_img);
                }else{
                    tv_coach_name.setText("授课教练：无");
                    Picasso.with(OrderDetailActivity.this).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").transform(transformation).into(iv_coach_img);
                }
                tv_time.setText("上课时间：" + orderDetailBean.getData().getCourse().getDurations().getStartDate() + "至" + orderDetailBean.getData().getCourse().getDurations().getEndDate());
                tv_store_name.setText("所在机构："+orderDetailBean.getData().getStore().getName());
                tv_address.setText("授课地点："+orderDetailBean.getData().getStore().getAddress().getDetail());
            }
        },orderId);
    }
    @Event(value = {R.id.iv_back}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(OrderDetailActivity.this);
                break;
        }
    }
}
