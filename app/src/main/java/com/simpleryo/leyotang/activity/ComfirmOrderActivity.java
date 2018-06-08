package com.simpleryo.leyotang.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CourdeDetailBean;
import com.simpleryo.leyotang.bean.CreateOrderBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.push.NotificationBroadcast;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.simpleryo.leyotang.utils.XStringPars;
import com.squareup.picasso.Picasso;

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

import java.util.ArrayList;
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
    @ViewInject(R.id.edittext_name)
    EditText edittext_name;
    @ViewInject(R.id.edittext_phone)
    EditText edittext_phone;
    @ViewInject(R.id.edittext_remark)
    EditText edittext_remark;
    String name;
    String phone;
    String remark;
    int price;
    int total_price;
    String courseName;
    String storeId;
    String coachId;
    private ProgressDialog dialog;
    String payType = "WECHATPAY";//支付方式，默认为微信支付
    @ViewInject(R.id.tv_course_time)
    TextView tv_course_time;
    @ViewInject(R.id.tv_course_address)
    TextView tv_course_address;
    @ViewInject(R.id.radigroup_time)
    TextView radigroup_time;
    @ViewInject(R.id.ll_course_time)
    LinearLayout ll_course_time;

    public final static String CSS_STYLE = "<style>* {font-size:14px;line-height:20px;}p {color:##373737;font-size:12px}</style>";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText(getResources().getString(R.string.confirmation_of_order));
        EventBus.getDefault().register(this);
        count = Integer.parseInt(et_count.getText().toString().trim());
        courseId = getIntent().getStringExtra("courseId");
        getCourseDetail();
    }

    String aboutArrangeId;//预约单节课程id

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    ArrayList<CourdeDetailBean.DataBeanX.Arrange> arrangeArrayList = new ArrayList<>();
    ArrayMap<String, CourdeDetailBean.DataBeanX.Arrange> arrayMap = new ArrayMap();
    ArrayList<String> arrangeTimes = new ArrayList<>();
    String arrangeTime;
    boolean isSingle = false;

    /**
     * 获取课程详情
     */
    public void getCourseDetail() {
        SimpleryoNetwork.getCourseDetail(ComfirmOrderActivity.this, new MyBaseProgressCallbackImpl(ComfirmOrderActivity.this) {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                loadingDialog.dismiss();
                CourdeDetailBean courdeDetailBean = info.getRetDetail(CourdeDetailBean.class);
                if (courdeDetailBean.getCode().equalsIgnoreCase("0")) {
                    Picasso.with(ComfirmOrderActivity.this).load(courdeDetailBean.getData().getCoverUrl()).into(iv_course_detail_img);
                    courseName = courdeDetailBean.getData().getName();
                    if (courdeDetailBean.getData().getIntro() != null) {
                        tv_course_detail.loadDataWithBaseURL(null, CSS_STYLE + courdeDetailBean.getData().getIntro(), "text/html", "utf-8", null);
                    } else {
                        tv_course_detail.loadDataWithBaseURL(null, CSS_STYLE + "暂无详情", "text/html", "utf-8", null);
                    }
                    //上课时间
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
                        tv_course_address.setText(getResources().getString(R.string.offline_training_training_address) +address);
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
                        tv_coach_name.setText(getResources().getString(R.string.Tutor_name) +"：" + courdeDetailBean.getData().getCoach().getNickName() + "   " + courdeDetailBean.getData().getCoach().getWorkLife() + getResources().getString(R.string.experience) );

                    }
                    price = courdeDetailBean.getData().getPrice();
                    total_price = count * price;
                    tv_price.setText(getResources().getString(R.string.course_of_price) + XStringPars.foramtPrice(total_price) + "$");
                    tv_total_price.setText(getResources().getString(R.string.total_course) + XStringPars.foramtPrice(total_price) + "$");
                    if (courdeDetailBean.getData().getStoreName() != null) {
                        tv_store_name.setText(courdeDetailBean.getData().getStoreName());
                    } else {
                        tv_store_name.setText("暂无机构名称");
                    }
                    if (courdeDetailBean.getData().getType().equalsIgnoreCase("single")) {
                        isSingle = true;
                        ll_course_time.setVisibility(View.VISIBLE);
                        if (courdeDetailBean.getData().getArranges() != null && courdeDetailBean.getData().getArranges().size() > 0) {
                            arrangeArrayList.addAll(courdeDetailBean.getData().getArranges());
                            for (int i = 0; i < arrangeArrayList.size(); i++) {
                                arrangeTimes.add(arrangeArrayList.get(i).getDateDetail());
                                arrayMap.put(arrangeArrayList.get(i).getDateDetail(), arrangeArrayList.get(i));
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
            }
        }, courseId);
    }

    private OptionsPickerView pvCustomOptions;
    String time;

    private void initCustomOptionPicker() {//条件选择器初始化，自定义布局

        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvCustomOptions = new OptionsPickerBuilder(ComfirmOrderActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                arrangeTime = arrangeTimes.get(options1);
                aboutArrangeId = arrayMap.get(arrangeTime).getId();
            }
        })
                .setLayoutRes(R.layout.dialog_course_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView tvSubmit = (TextView) v.findViewById(R.id.tv_sure);
                        TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                                radigroup_time.setText(arrangeTime);
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });
                    }
                })
                .isDialog(true)
                .build();
        pvCustomOptions.setPicker(arrangeTimes);//添加数据
        pvCustomOptions.show();
    }

    @Event(value = {R.id.iv_back, R.id.iv_msg, R.id.iv_count_reduce, R.id.iv_count_increase, R.id.rl_pay, R.id.ll_course_time}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(ComfirmOrderActivity.this);
                break;
            case R.id.ll_course_time:
                initCustomOptionPicker();
                break;
            case R.id.iv_msg:
                startActivity(new Intent(ComfirmOrderActivity.this, MyMsgActivity.class));
                break;
            case R.id.iv_count_reduce://减少
                if (count > 1) {
                    count -= 1;
                    et_count.setText(count + "");
                } else {
                    Toast.makeText(ComfirmOrderActivity.this, "订单数量不能小于1", Toast.LENGTH_SHORT).show();
                }
                total_price = count * price;
                tv_total_price.setText(getResources().getString(R.string.total_course) + XStringPars.foramtPrice(total_price * 1) + "$");
                break;
            case R.id.iv_count_increase://增加
                count += 1;
                et_count.setText(count + "");
                total_price = count * price;
                tv_total_price.setText(getResources().getString(R.string.total_course)  + XStringPars.foramtPrice(total_price * 1) + "$");
                break;
            case R.id.rl_pay://支付
                name = edittext_name.getText().toString().trim();
                phone = edittext_phone.getText().toString().trim();
                remark = edittext_remark.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(ComfirmOrderActivity.this, "预订人不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.isEmpty()) {
                    Toast.makeText(ComfirmOrderActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isSingle == true) {
                    if (TextUtils.isEmpty(aboutArrangeId)) {
                        Toast.makeText(ComfirmOrderActivity.this, "预约时间不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                SimpleryoNetwork.createOrder(ComfirmOrderActivity.this, new MyBaseProgressCallbackImpl(ComfirmOrderActivity.this) {
                    @Override
                    public void onSuccess(HttpInfo info) {
                        super.onSuccess(info);
                        loadingDialog.dismiss();
                        CreateOrderBean createOrderBean = info.getRetDetail(CreateOrderBean.class);
                        if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                            if (isSingle == true) {
                                if (price == 0) {
                                    startActivity(new Intent(ComfirmOrderActivity.this, MyOrderActivity.class).putExtra("status", "PAYED"));
                                } else {
                                    recordOrder(createOrderBean.getData().getId(), createOrderBean.getData().getPayAmt() + "", createOrderBean.getData().getNo(), createOrderBean.getData().getCourseName(), payType);
                                    if (createOrderBean.getData().getPayType().equalsIgnoreCase("ALIPAY")) {//支付宝支付
                                        clickAlipay(ComfirmOrderActivity.this, XStringPars.foramtPrice(createOrderBean.getData().getPayAmt()) + "", createOrderBean.getData().getNo(), courseName);
                                    } else {//微信支付
                                        clickWechat(ComfirmOrderActivity.this, XStringPars.foramtPrice(createOrderBean.getData().getPayAmt()) + "", createOrderBean.getData().getNo(), courseName);
                                    }
                                }
                            } else if (isSingle == false) {
                                recordOrder(createOrderBean.getData().getId(), createOrderBean.getData().getPayAmt() + "", createOrderBean.getData().getNo(), createOrderBean.getData().getCourseName(), payType);
                                if (createOrderBean.getData().getPayType().equalsIgnoreCase("ALIPAY")) {//支付宝支付
                                    clickAlipay(ComfirmOrderActivity.this, XStringPars.foramtPrice(createOrderBean.getData().getPayAmt()) + "", createOrderBean.getData().getNo(), courseName);
                                } else {//微信支付
                                    clickWechat(ComfirmOrderActivity.this, XStringPars.foramtPrice(createOrderBean.getData().getPayAmt()) + "", createOrderBean.getData().getNo(), courseName);
                                }
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
                }, coachId, storeId, courseId, courseName, payType, count, price, total_price, total_price, name, phone, remark, aboutArrangeId);
                break;
        }
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
        req.amount = amount;//支付金额
        req.merchantReference = merchantReference;//订单号
        req.productName = productName;//商品名称
        req.callbackUrl = "https://api.simpleryo.com/o/orders/callback/url";//支付回调地址
        req.setListener(new LatipayListener() {

            @Override
            public void onTransactionCompleted(HashMap<String, String> latipayOrder, Error error) {
                Log.w("cc", "onTransactionCompleted " + String.valueOf(latipayOrder) + (error != null ? error.getMessage() : ""));
                dialog.dismiss();

                if (error != null) {
                    Toast.makeText(ComfirmOrderActivity.this, getResources().getString(R.string.Payment_error), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ComfirmOrderActivity.this, MyOrderActivity.class).putExtra("status", "NEW"));
                    XActivityUtils.getInstance().popActivity(ComfirmOrderActivity.this);
                    return;
                }
            }

            @Override
            public void onPaymentCompleted(int result) {
                if (result == PaymentStatus.PAID) {
                    EventBus.getDefault().post(new BusEntity(0222, "PAID"));
                } else if (result == PaymentStatus.UNPAID) {
                    EventBus.getDefault().post(new BusEntity(0222, "UNPAID"));
                } else { //PaymentStatus.UNKNOWN
                    //search payment status from your own server
                    EventBus.getDefault().post(new BusEntity(0222, "UNKNOWN"));
                }
            }
        });

        LatipayAPI.sendRequest(req);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType() == 0222) {
            String state = bus.getContent();
            if (state.equalsIgnoreCase("PAID")) {
                Toast.makeText(ComfirmOrderActivity.this,  getResources().getString(R.string.Payment_successful), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ComfirmOrderActivity.this, MyOrderActivity.class).putExtra("status", "PAYED"));
                XActivityUtils.getInstance().popActivity(ComfirmOrderActivity.this);
            }
            if (state.equalsIgnoreCase("UNPAID")) {
                Toast.makeText(ComfirmOrderActivity.this,  getResources().getString(R.string.Payment_cancle), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ComfirmOrderActivity.this, MyOrderActivity.class).putExtra("status", "NEW"));
                XActivityUtils.getInstance().popActivity(ComfirmOrderActivity.this);
            }
            if (state.equalsIgnoreCase("UNKNOWN")) {
                Toast.makeText(ComfirmOrderActivity.this, getResources().getString(R.string.Payment_error), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ComfirmOrderActivity.this, MyOrderActivity.class).putExtra("status", "NEW"));
                XActivityUtils.getInstance().popActivity(ComfirmOrderActivity.this);
            }
        }
    }

    /**
     * 支付宝支付
     *
     * @param activity
     * @param amount
     * @param merchantReference
     * @param productName
     */
    public void clickAlipay(final Activity activity, String amount, String merchantReference, String productName) {
        dialog = ProgressDialog.show(activity, null, "Loading", false, true);
        AlipayRequest req = new AlipayRequest(activity);
        req.amount = amount;//支付金额
        req.merchantReference = merchantReference;//订单号
        req.productName = productName;//商品名称
        req.callbackUrl = "https://api.simpleryo.com/o/orders/callback/url";//支付回调地址


        req.setListener(new LatipayListener() {

            @Override
            public void onTransactionCompleted(HashMap<String, String> latipayOrder, Error error) {
                Log.w("cc", "onTransactionCompleted " + String.valueOf(latipayOrder) + (error != null ? error.getMessage() : ""));
                dialog.dismiss();
                if (error != null) {
                    Toast.makeText(ComfirmOrderActivity.this, getResources().getString(R.string.Payment_error), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ComfirmOrderActivity.this, MyOrderActivity.class).putExtra("status", "NEW"));
                    XActivityUtils.getInstance().popActivity(ComfirmOrderActivity.this);
                    return;
                }
            }

            @Override
            public void onPaymentCompleted(int result) {
                if (result == PaymentStatus.PAID) {
                    Toast.makeText(ComfirmOrderActivity.this,  getResources().getString(R.string.Payment_successful), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ComfirmOrderActivity.this, MyOrderActivity.class).putExtra("status", "PAYED"));
                    XActivityUtils.getInstance().popActivity(ComfirmOrderActivity.this);
                } else if (result == PaymentStatus.UNPAID) {
                    Toast.makeText(ComfirmOrderActivity.this,  getResources().getString(R.string.Payment_cancle), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ComfirmOrderActivity.this, MyOrderActivity.class).putExtra("status", "NEW"));
                    XActivityUtils.getInstance().popActivity(ComfirmOrderActivity.this);
                } else { //PaymentStatus.UNKNOWN
                    //search payment status from your own server
                    Toast.makeText(ComfirmOrderActivity.this, getResources().getString(R.string.Payment_error), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ComfirmOrderActivity.this, MyOrderActivity.class).putExtra("status", "NEW"));
                    XActivityUtils.getInstance().popActivity(ComfirmOrderActivity.this);
                }
            }
        });

        LatipayAPI.sendRequest(req);
    }
}
