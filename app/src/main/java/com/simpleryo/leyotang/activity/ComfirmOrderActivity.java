package com.simpleryo.leyotang.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.CourdeDetailBean;
import com.simpleryo.leyotang.bean.CreateOrderBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.push.NotificationBroadcast;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.squareup.picasso.Picasso;

import net.latipay.mobile.AlipayRequest;
import net.latipay.mobile.LatipayAPI;
import net.latipay.mobile.LatipayListener;
import net.latipay.mobile.PaymentStatus;
import net.latipay.mobile.WechatpayRequest;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

/**
 * @author huanglei
 * @ClassNname：AboutUsActivity.java
 * @Describe 联系我们页面
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_comfirm_order)
public class ComfirmOrderActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.et_count)
    EditText et_count;
    @ViewInject(R.id.iv_course_detail_img)
    ImageView iv_course_detail_img;
    @ViewInject(R.id.tv_course_category)
    TextView tv_course_category;
    @ViewInject(R.id.tv_course_detail)
    WebView tv_course_detail;
    int count;
    String courseId;
    @ViewInject(R.id.tv_price)
    TextView tv_price;
    @ViewInject(R.id.tv_coach_name)
    TextView tv_coach_name;
    @ViewInject(R.id.tv_total_price)
    TextView tv_total_price;
    @ViewInject(R.id.tv_store_name)
    TextView tv_store_name;
    int price;
    int total_price;
    String courseName;
    String storeId;
    String coachId;
    private ProgressDialog dialog;
    String payType = "WECHATPAY";
    @ViewInject(R.id.tv_course_time)
    TextView tv_course_time;
    @ViewInject(R.id.tv_course_address)
    TextView tv_course_address;
    public final static String CSS_STYLE = "<style>* {font-size:14px;line-height:20px;}p {color:##373737;font-size:12px}</style>";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("确认订单");
        count = Integer.parseInt(et_count.getText().toString().trim());
        courseId = getIntent().getStringExtra("courseId");
        SimpleryoNetwork.getCourseDetail(ComfirmOrderActivity.this, new MyBaseProgressCallbackImpl(ComfirmOrderActivity.this) {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                loadingDialog.dismiss();
                CourdeDetailBean courdeDetailBean = info.getRetDetail(CourdeDetailBean.class);
                Picasso.with(ComfirmOrderActivity.this).load(courdeDetailBean.getData().getCoverUrl()).into(iv_course_detail_img);
                courseName = courdeDetailBean.getData().getName();
                if (courdeDetailBean.getData().getIntro() != null) {
                    tv_course_detail.loadDataWithBaseURL(null, CSS_STYLE + courdeDetailBean.getData().getIntro(), "text/html", "utf-8", null);
                } else {
                    tv_course_detail.loadDataWithBaseURL(null, CSS_STYLE + "暂无详情", "text/html", "utf-8", null);
                }
                if (courdeDetailBean.getData().getDurations().getData() != null && courdeDetailBean.getData().getDurations().getData().size() > 0) {
                    StringBuilder durations = new StringBuilder();
                    durations.append(courdeDetailBean.getData().getDurations().getStartDate() + "至" + courdeDetailBean.getData().getDurations().getEndDate());
                    durations.append("\n");
                    for (int i = 0; i < courdeDetailBean.getData().getDurations().getData().size(); i++) {
                        CourdeDetailBean.DataBeanX.DurationsBean.DataBean dataBean = courdeDetailBean.getData().getDurations().getData().get(i);
                        durations.append(dataBean.getWeek() + "   " + dataBean.getStartTime() + "-" + dataBean.getEndTime());
                        if (i < courdeDetailBean.getData().getDurations().getData().size() - 1) {
                            durations.append("\n");
                        }
                    }
                    tv_course_time.setText(durations);
                }
                CourdeDetailBean.DataBeanX.AddressBean addressBean = courdeDetailBean.getData().getAddress();
                if (addressBean != null) {
//                    String address=addressBean.getProvice()+addressBean.getCity()+addressBean.getDistrict()+addressBean.getDetail();
                    String address = addressBean.getDetail();
                    tv_course_address.setText("线下授课，授课地点：" + address);
                }
                if (courdeDetailBean.getData().getCoach() != null) {
                    coachId = courdeDetailBean.getData().getCoach().getId();
                }
                if (courdeDetailBean.getData().getStoreId() != null) {
                    storeId = courdeDetailBean.getData().getStoreId();
                }
                if (courseName != null) {
                    tv_course_category.setText(courseName);
                }
                if (courdeDetailBean.getData().getCoach() != null) {
                    tv_coach_name.setText("教练姓名：" + courdeDetailBean.getData().getCoach().getNickName() + "   " + courdeDetailBean.getData().getCoach().getWorkLife() + "年教龄");

                }
                price = courdeDetailBean.getData().getPrice();
                if (price == 0) {
                    price = 2;
                }
                total_price = count * price;
                tv_price.setText("课程价格：" + price/100 + "$");
                tv_total_price.setText("课程总额：" + total_price/100 + "$");
                if (courdeDetailBean.getData().getStoreName() != null) {
                    tv_store_name.setText(courdeDetailBean.getData().getStoreName());
                } else {
                    tv_store_name.setText("暂无机构名称");
                }

            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
            }
        }, courseId);
    }

    @Event(value = {R.id.iv_back, R.id.iv_count_reduce, R.id.iv_count_increase, R.id.rl_pay}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(ComfirmOrderActivity.this);
                break;
            case R.id.iv_count_reduce:
                if (count > 1) {
                    count -= 1;
                    et_count.setText(count + "");
                } else {
                    Toast.makeText(ComfirmOrderActivity.this, "订单数量不能小于1", Toast.LENGTH_SHORT).show();
                }
                total_price = count * price;
                tv_total_price.setText("课程总额：" + (total_price * 1)/100 + "$");
                break;
            case R.id.iv_count_increase:
                count += 1;
                et_count.setText(count + "");
                total_price = count * price;
                tv_total_price.setText("课程总额：" + (total_price * 1)/100+ "$");
                break;
            case R.id.rl_pay:
                SimpleryoNetwork.createOrder(ComfirmOrderActivity.this, new MyBaseProgressCallbackImpl(ComfirmOrderActivity.this) {
                    @Override
                    public void onSuccess(HttpInfo info) {
                        super.onSuccess(info);
                        loadingDialog.dismiss();
                        CreateOrderBean createOrderBean = info.getRetDetail(CreateOrderBean.class);
                        if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                            recordOrder(createOrderBean.getData().getId(), createOrderBean.getData().getPayAmt() + "", createOrderBean.getData().getNo(), createOrderBean.getData().getCourseName(), payType);
                            if (createOrderBean.getData().getPayType().equalsIgnoreCase("ALIPAY")) {
                                clickAlipay(ComfirmOrderActivity.this, total_price + "", createOrderBean.getData().getNo(), courseName);
                            } else {
                                clickWechat(ComfirmOrderActivity.this, total_price + "", createOrderBean.getData().getNo(), courseName);
//                                alertDialog();
                            }
                        } else if (createOrderBean.getCode().equalsIgnoreCase("401")) {
                            Intent intent = new Intent();
                            intent.setClass(context, NotificationBroadcast.class);
                            intent.putExtra(NotificationBroadcast.EXTRA_KEY_ACTION,
                                    NotificationBroadcast.ACTION_REFRESHTOKEN);
                            sendBroadcast(intent);
                        } else {
                            Toast.makeText(ComfirmOrderActivity.this, createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(HttpInfo info) {
                        super.onFailure(info);
                        loadingDialog.dismiss();
                    }
                }, coachId, storeId, courseId, courseName, payType, count, price, total_price, total_price);

                break;
        }
    }


    public void alertDialog() {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(ComfirmOrderActivity.this);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("该订单无法支付，请重新下单");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        normalDialog.setCancelable(false);
        // 显示
        normalDialog.show();
    }

    @Event(value = {R.id.radio_group_pay}, type = RadioGroup.OnCheckedChangeListener.class)
    private void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.radio_button_wechat:
                payType = "WECHATPAY";
                break;
            case R.id.radio_button_alipay:
                payType = "ALIPAY";
                break;
            default:
                break;
        }
    }

    /**
     * 记录订单
     *
     * @param orderId
     * @param amount
     * @param merchantReference
     * @param productName
     * @param paymentMethod
     */
    public void recordOrder(String orderId, String amount, String merchantReference, String productName, String paymentMethod) {
        SimpleryoNetwork.recordOrder(ComfirmOrderActivity.this, new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
            }
        }, orderId, amount, merchantReference, productName, paymentMethod);
    }

    /**
     * 微信支付
     *
     * @param activity
     * @param amount
     * @param merchantReference
     * @param productName
     */
    private void clickWechat(final Activity activity, String amount, String merchantReference, String productName) {
        dialog = ProgressDialog.show(activity, null, "Loading", false, true);
        WechatpayRequest req = new WechatpayRequest(activity);
        req.amount = "0.1";
        req.merchantReference = merchantReference;

        req.productName = productName;
        req.callbackUrl = "https://api.simpleryo.com/o/orders/callback/url";

        req.setListener(new LatipayListener() {

            @Override
            public void onTransactionCompleted(HashMap<String, String> latipayOrder, Error error) {
                Log.w("cc", "onTransactionCompleted " + String.valueOf(latipayOrder) + (error != null ? error.getMessage() : ""));
                dialog.dismiss();

                if (error != null) {
                    Toast.makeText(activity, "Latipay: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(activity, "Go to Wechat", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPaymentCompleted(int result) {
                if (result == PaymentStatus.PAID) {
                    Toast.makeText(activity, "WechatPay: paid", Toast.LENGTH_LONG).show();
                } else if (result == PaymentStatus.UNPAID) {
                    Toast.makeText(activity, "WechatPay: unpaid", Toast.LENGTH_LONG).show();
                } else { //PaymentStatus.UNKNOWN

                    //search payment status from your own server
                }
            }
        });

        LatipayAPI.sendRequest(req);
    }

    /**
     * 支付
     *
     * @param activity
     * @param amount
     * @param merchantReference
     * @param productName
     */
    public void clickAlipay(final Activity activity, String amount, String merchantReference, String productName) {
        dialog = ProgressDialog.show(activity, null, "Loading", false, true);
        AlipayRequest req = new AlipayRequest(activity);
        req.amount = "0.1";//支付金额
        req.merchantReference = merchantReference;//订单号
        req.productName = productName;
        req.callbackUrl = "https://api.simpleryo.com/o/orders/callback/url";


        req.setListener(new LatipayListener() {

            @Override
            public void onTransactionCompleted(HashMap<String, String> latipayOrder, Error error) {
                Log.w("cc", "onTransactionCompleted " + String.valueOf(latipayOrder) + (error != null ? error.getMessage() : ""));
                dialog.dismiss();
                if (error != null) {
                    Toast.makeText(activity, "支付失败", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ComfirmOrderActivity.this, MyOrderActivity.class).putExtra("status", "NEW"));
                    return;
                }
            }

            @Override
            public void onPaymentCompleted(int result) {
                if (result == PaymentStatus.PAID) {
                    Toast.makeText(activity, "支付成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ComfirmOrderActivity.this, MyOrderActivity.class).putExtra("status", "PAYED"));
                } else if (result == PaymentStatus.UNPAID) {
                    Toast.makeText(activity, "支付取消", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ComfirmOrderActivity.this, MyOrderActivity.class).putExtra("status", "NEW"));
                } else { //PaymentStatus.UNKNOWN
                    //search payment status from your own server
                    Toast.makeText(activity, "支付异常", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ComfirmOrderActivity.this, MyOrderActivity.class).putExtra("status", "NEW"));
                }
            }
        });

        LatipayAPI.sendRequest(req);
    }
}
