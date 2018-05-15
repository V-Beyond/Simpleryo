package com.simpleryo.leyotang.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.OrderDetailBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.simpleryo.leyotang.utils.XStringPars;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import net.latipay.mobile.AlipayRequest;
import net.latipay.mobile.LatipayAPI;
import net.latipay.mobile.LatipayListener;
import net.latipay.mobile.PaymentStatus;
import net.latipay.mobile.WechatpayRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

/**
 * @author huanglei
 * @ClassNname：MyCourse.java
 * @Describe 订单详情页面
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
    @ViewInject(R.id.tv_buyer_name)
    TextView  tv_buyer_name;
    @ViewInject(R.id.tv_buyer_phone)
    TextView  tv_buyer_phone;
    @ViewInject(R.id.tv_buyer_remark)
    TextView  tv_buyer_remark;
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
    @ViewInject(R.id.rl_bottom)
    RelativeLayout rl_bottom;
    @ViewInject(R.id.tv_detail)
    TextView tv_detail;
    @ViewInject(R.id.tv_order_remark)
    TextView tv_order_remark;
    String orderId;
    String payType;
    String amount;
    String merchantReference;
    String productName;
    String status;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        tv_name.setText("订单详情");
        orderId = getIntent().getStringExtra("orderId");
        getOrderDetail();
    }

    /**
     * 获取订单详情
     */
    public void getOrderDetail(){
        SimpleryoNetwork.getOrderDetail(OrderDetailActivity.this, new MyBaseProgressCallbackImpl(OrderDetailActivity.this) {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                loadingDialog.dismiss();
                OrderDetailBean orderDetailBean = info.getRetDetail(OrderDetailBean.class);
                if (orderDetailBean.getData().getImageUrl() != null) {
                    Picasso.with(OrderDetailActivity.this).load(orderDetailBean.getData().getImageUrl()).into(iv_order_img);
                } else {
                    Picasso.with(OrderDetailActivity.this).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").into(iv_order_img);
                }
                tv_course_name.setText("课程名称：" + orderDetailBean.getData().getCourseName());
                tv_order_course_name.setText(orderDetailBean.getData().getCourseName());
                tv_total_price.setText(XStringPars.foramtPrice(orderDetailBean.getData().getTotalAmt()) + "$");
                tv_shop_price.setText(XStringPars.foramtPrice(orderDetailBean.getData().getUnitPrice()) + "$");
                tv_count.setText("X" + orderDetailBean.getData().getQuantity());
                tv_order_number.setText("订单号：" + orderDetailBean.getData().getId());
                tv_buyer_name.setText(orderDetailBean.getData().getUserName());
                tv_buyer_phone.setText(orderDetailBean.getData().getUserPhone());
                if (orderDetailBean.getData().getUserRemark()!=null){
                    tv_buyer_remark.setText(orderDetailBean.getData().getUserRemark());
                }else{
                    tv_buyer_remark.setText("暂无备注");
                }

                payType = orderDetailBean.getData().getPayType();
                amount= XStringPars.foramtPrice(orderDetailBean.getData().getPayAmt());
                merchantReference=orderDetailBean.getData().getNo();
                productName=orderDetailBean.getData().getCourseName();
                 status = orderDetailBean.getData().getStatus();
                if (status.equalsIgnoreCase("NEW")) {
                    rl_bottom.setVisibility(View.VISIBLE);
                }
                if (status.equalsIgnoreCase("PAYED")) {
                    rl_bottom.setVisibility(View.GONE);
                }
                if (status.equalsIgnoreCase("RECEIVED")) {
                    rl_bottom.setVisibility(View.VISIBLE);
                    tv_detail.setVisibility(View.GONE);
                    tv_order_remark.setText("评价");
                }
                if (status.equalsIgnoreCase("COMPLETED")) {
                    rl_bottom.setVisibility(View.GONE);
                }
                if (status.equalsIgnoreCase("CANCALLED")) {
                    rl_bottom.setVisibility(View.GONE);
                }
                if (orderDetailBean.getData().getCoach() != null) {
                    tv_coach_name.setText("授课教练：" + orderDetailBean.getData().getCoach().getName());
                    Picasso.with(OrderDetailActivity.this).load(orderDetailBean.getData().getCoach().getAvatarUrl()).transform(transformation).into(iv_coach_img);
                } else {
                    tv_coach_name.setText("授课教练：无");
                    Picasso.with(OrderDetailActivity.this).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").transform(transformation).into(iv_coach_img);
                }
                tv_time.setText("上课时间：" + orderDetailBean.getData().getCourse().getDurations().getStartDate() + "至" + orderDetailBean.getData().getCourse().getDurations().getEndDate());
                tv_store_name.setText("所在机构：" + orderDetailBean.getData().getStore().getName());
                tv_address.setText("授课地点：" + orderDetailBean.getData().getStore().getAddress().getDetail());
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                loadingDialog.dismiss();
            }
        }, orderId);
    }
    @Event(value = {R.id.iv_back, R.id.tv_detail, R.id.tv_order_remark,R.id.iv_msg}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_msg:
                XActivityUtils.getInstance().popActivity(OrderDetailActivity.this);
                break;
            case R.id.iv_back:
                startActivity(new Intent(OrderDetailActivity.this,MyMsgActivity.class));
                break;
            case R.id.tv_detail:
                if (payType.equalsIgnoreCase("ALIPAY")) {
                    orderAlipay(OrderDetailActivity.this,amount,merchantReference,productName);
                } else if (payType.equalsIgnoreCase("WECHATPAY")) {
                    orderWechat(OrderDetailActivity.this,amount,merchantReference,productName);
                }
                break;
            case R.id.tv_order_remark:
                if (status.equalsIgnoreCase("NEW")) {
                    SimpleryoNetwork.cancelOrder(OrderDetailActivity.this, new MyBaseProgressCallbackImpl(OrderDetailActivity.this) {
                        @Override
                        public void onSuccess(HttpInfo info) {
                            super.onSuccess(info);
                            loadingDialog.dismiss();
                            EventBus.getDefault().post(new BusEntity(1002));
                        }
                        @Override
                        public void onFailure(HttpInfo info) {
                            super.onFailure(info);
                            loadingDialog.dismiss();
                        }
                    }, orderId);
                }
                if (status.equalsIgnoreCase("RECEIVED")) {
                    startActivity(new Intent(OrderDetailActivity.this, RemarkCourseActivity.class).putExtra("orderId", orderId));
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType() == 1002) {
        getOrderDetail();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private ProgressDialog dialog;

    /**
     * 支付
     *
     * @param activity
     * @param amount
     * @param merchantReference
     * @param productName
     */
    public void orderAlipay(final Activity activity, String amount, String merchantReference, String productName) {
        dialog = ProgressDialog.show(activity, null, "Loading", false, true);
        AlipayRequest req = new AlipayRequest(activity);
        req.amount = amount;//支付金额
        req.merchantReference = merchantReference;//订单号
        req.productName = productName;//商品名称
//        req.productName = "test produce of Simpleryo";
        req.callbackUrl = "https://api.simpleryo.com/o/orders/callback/url";//回调地址


        req.setListener(new LatipayListener() {

            @Override
            public void onTransactionCompleted(HashMap<String, String> latipayOrder, Error error) {
                Log.w("cc", "onTransactionCompleted " + String.valueOf(latipayOrder) + (error != null ? error.getMessage() : ""));
                dialog.dismiss();

                if (error != null) {
                    Log.w("cc", "onOrderCompleted:" + error.getMessage());
                    Toast.makeText(activity, "Latipay: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onPaymentCompleted(int result) {
                if (result == PaymentStatus.PAID) {
                    Toast.makeText(activity, "支付成功", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new BusEntity(1002));
                } else if (result == PaymentStatus.UNPAID) {
                    Toast.makeText(activity, "支付取消", Toast.LENGTH_SHORT).show();
                } else { //PaymentStatus.UNKNOWN
                    //search payment status from your own server
                    Toast.makeText(activity, "支付异常", Toast.LENGTH_SHORT).show();
                }
            }

        });
        LatipayAPI.sendRequest(req);
    }

    /**
     * 微信支付
     *
     * @param activity
     * @param amount
     * @param merchantReference
     * @param productName
     */
    public void orderWechat(final Activity activity, String amount, String merchantReference, String productName) {
        dialog = ProgressDialog.show(activity, null, "Loading", false, true);
        WechatpayRequest req = new WechatpayRequest(activity);
        req.amount = amount;//支付金额
        req.merchantReference = merchantReference;//订单号
        req.productName = productName;//商品名称
//        req.productName = "test produce of Simpleryo";
        req.callbackUrl = "https://api.simpleryo.com/o/orders/callback/url";//回调地址


        req.setListener(new LatipayListener() {

            @Override
            public void onTransactionCompleted(HashMap<String, String> latipayOrder, Error error) {
                Log.w("cc", "onTransactionCompleted " + String.valueOf(latipayOrder) + (error != null ? error.getMessage() : ""));
                dialog.dismiss();

                if (error != null) {
                    Log.w("cc", "onOrderCompleted:" + error.getMessage());
                    Toast.makeText(activity, "Latipay: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onPaymentCompleted(int result) {
                if (result == PaymentStatus.PAID) {
                    EventBus.getDefault().post(new BusEntity(1002));
                    Toast.makeText(activity, "支付成功", Toast.LENGTH_SHORT).show();
                } else if (result == PaymentStatus.UNPAID) {
                    Toast.makeText(activity, "支付取消", Toast.LENGTH_SHORT).show();
                } else { //PaymentStatus.UNKNOWN
                    //search payment status from your own server
                    Toast.makeText(activity, "支付异常", Toast.LENGTH_SHORT).show();
                }
            }

        });
        LatipayAPI.sendRequest(req);
    }
}
