package com.simpleryo.leyotang.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CurrentAddressInfo;
import com.simpleryo.leyotang.bean.OrderDetailBean;
import com.simpleryo.leyotang.bean.UpdateOrderBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.push.NotificationBroadcast;
import com.simpleryo.leyotang.utils.LocationUtils;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author huanglei
 * @ClassNname：MyCourse.java
 * @Describe 我的订单课程详情页面
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_my_course_detail)
public class MyCourseDetailActivity extends BaseActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_my_course_detail_img)
    ImageView iv_my_course_detail_img;
    @ViewInject(R.id.iv_coach_img)
    ImageView iv_coach_img;
    @ViewInject(R.id.tv_order_number)
    TextView tv_order_number;
    @ViewInject(R.id.tv_order_course_name)
    TextView tv_order_course_name;
    @ViewInject(R.id.tv_coach_name)
    TextView tv_coach_name;
    @ViewInject(R.id.tv_course_duration)
    TextView tv_course_duration;
    @ViewInject(R.id.tv_store_name)
    TextView tv_store_name;
    @ViewInject(R.id.tv_course_address)
    TextView tv_course_address;
    @ViewInject(R.id.tv_update_course_time)
    TextView tv_update_course_time;
    public Transformation transformation = new RoundedTransformationBuilder()
            .cornerRadiusDp(30)
            .oval(true)
            .build();
    String id;
    //谷歌地图
    private GoogleMap mMap;
    //经纬度
    double lat;
    double lng;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        tv_name.setText(getResources().getString(R.string.course_detail));
        id = getIntent().getStringExtra("id");
        EventBus.getDefault().post(new BusEntity(6001));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    ArrayList<OrderDetailBean.OrderCourseBean.Arrange> arrangeArrayList = new ArrayList<>();
    ArrayList<String> arrangeTimes = new ArrayList<>();
    String arrangeTime;
    ArrayMap<String, OrderDetailBean.OrderCourseBean.Arrange> arrayMap = new ArrayMap();
    String aboutArrangeId;//预约单节课程id
    String coachId;
    String storeId;
    String courseId;
    String courseName;
    String payType;
    String remark;
    int count;
    int price;
    int total_price;
    String name;
    String phone;

    /**
     * 获取订单详情
     */
    public void initData() {
        SimpleryoNetwork.getOrderDetail(MyCourseDetailActivity.this, new MyBaseProgressCallbackImpl(MyCourseDetailActivity.this) {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                loadingDialog.dismiss();
                OrderDetailBean orderDetailBean = info.getRetDetail(OrderDetailBean.class);
                if (orderDetailBean.getCode().equalsIgnoreCase("0")) {
                    coachId = orderDetailBean.getData().getCoach().getId();
                    storeId = orderDetailBean.getData().getStoreId();
                    courseId = orderDetailBean.getData().getCourseId();
                    courseName = orderDetailBean.getData().getCourseName();
                    payType = orderDetailBean.getData().getPayType();
                    remark = orderDetailBean.getData().getUserRemark();
                    count = orderDetailBean.getData().getQuantity();
                    price = orderDetailBean.getData().getUnitPrice();
                    total_price = orderDetailBean.getData().getTotalAmt();
                    name = orderDetailBean.getData().getUserName();
                    phone = orderDetailBean.getData().getUserPhone();
                    tv_order_number.setText(getResources().getString(R.string.Order_number) + "：" + orderDetailBean.getData().getId());
                    if (orderDetailBean.getData().getCourse().getType().equalsIgnoreCase("single")) {
                        tv_update_course_time.setVisibility(View.VISIBLE);
                        if (orderDetailBean.getData().isArrangeFlag() == false) {
                            tv_update_course_time.setClickable(false);
                            tv_update_course_time.setTextColor(Color.parseColor("#dcdcdc"));
                        }
                        if (orderDetailBean.getData().getCourse().getArranges() != null && orderDetailBean.getData().getCourse().getArranges().size() > 0) {
                            arrangeArrayList.addAll(orderDetailBean.getData().getCourse().getArranges());
                            for (int i = 0; i < arrangeArrayList.size(); i++) {
                                arrangeTimes.add(arrangeArrayList.get(i).getDateDetail());
                                arrayMap.put(arrangeArrayList.get(i).getDateDetail(), arrangeArrayList.get(i));
                            }
                            tv_course_duration.setText(orderDetailBean.getData().getClassTime());
                        }
                    } else {
                        //上课时间
                        if (orderDetailBean.getData().getCourse().getDurations() != null && orderDetailBean.getData().getCourse().getDurations().getData().size() > 0) {
                            StringBuilder durations = new StringBuilder();
                            durations.append((orderDetailBean.getData().getCourse().getDurations().getStartDate() + "至" + orderDetailBean.getData().getCourse().getDurations().getEndDate()));
                            durations.append("\n");
                            for (int i = 0; i < orderDetailBean.getData().getCourse().getDurations().getData().size(); i++) {
                                OrderDetailBean.OrderCourseBean.DurationsBean.DataBean dataBean = orderDetailBean.getData().getCourse().getDurations().getData().get(i);
                                durations.append(dataBean.getWeek() + "   " + dataBean.getStartTime() + "-" + dataBean.getEndTime());
                                if (i < orderDetailBean.getData().getCourse().getDurations().getData().size() - 1) {
                                    durations.append("\n");
                                }
                            }
                            tv_course_duration.setText(durations);
                        }
                    }
                    if (orderDetailBean.getData().getImageUrl() != null) {
                        Picasso.with(MyCourseDetailActivity.this).load(orderDetailBean.getData().getImageUrl()).into(iv_my_course_detail_img);
                    } else {
                        Picasso.with(MyCourseDetailActivity.this).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").into(iv_my_course_detail_img);
                    }
                    if (orderDetailBean.getData().getCoach() != null) {
                        if (orderDetailBean.getData().getCoach().getAvatarUrl() != null) {
                            Picasso.with(MyCourseDetailActivity.this).load(orderDetailBean.getData().getCoach().getAvatarUrl()).transform(transformation).into(iv_coach_img);
                        } else {
                            Picasso.with(MyCourseDetailActivity.this).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").transform(transformation).into(iv_coach_img);
                        }
                        tv_coach_name.setText(getResources().getString(R.string.tutor) + "：" + orderDetailBean.getData().getCoach().getName());
                    } else {
                        tv_coach_name.setText(getResources().getString(R.string.tutor) + "：无");
                        Picasso.with(MyCourseDetailActivity.this).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").transform(transformation).into(iv_coach_img);
                    }
                    if (orderDetailBean.getData().getCourseName() != null) {
                        tv_order_course_name.setText(getResources().getString(R.string.Course_name) + "：" + orderDetailBean.getData().getCourseName());
                    } else {
                        tv_order_course_name.setText(getResources().getString(R.string.Course_name) + "：无");
                    }
                    tv_store_name.setText(getResources().getString(R.string.Organisation) + "：" + orderDetailBean.getData().getStore().getName());
                    tv_course_address.setText(getResources().getString(R.string.offline_training_training_address) + orderDetailBean.getData().getCourse().getAddress().getDetail());
                    lat = orderDetailBean.getData().getStore().getAddress().getLat();
                    lng = orderDetailBean.getData().getStore().getAddress().getLng();
                }
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                loadingDialog.dismiss();
                Toast.makeText(MyCourseDetailActivity.this, "数据一不小心走丢了，请稍后回来", Toast.LENGTH_SHORT).show();
            }
        }, id);
    }


    @Event(value = {R.id.iv_back, R.id.iv_msg, R.id.tv_update_course_time}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(MyCourseDetailActivity.this);
                break;
            case R.id.tv_update_course_time:
                initCustomOptionPicker();
                break;
            case R.id.iv_msg:
                startActivity(new Intent(MyCourseDetailActivity.this, MyMsgActivity.class));
                break;
        }
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
        pvCustomOptions = new OptionsPickerBuilder(MyCourseDetailActivity.this, new OnOptionsSelectListener() {
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
                                EventBus.getDefault().post(new BusEntity(6002));
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateOrder(BusEntity bus) {
        if (bus.getType() == 6001) {
            initData();
        }

        if (bus.getType() == 6002) {
            SimpleryoNetwork.updateOrder(MyCourseDetailActivity.this, new MyBaseProgressCallbackImpl(MyCourseDetailActivity.this) {
                @Override
                public void onSuccess(HttpInfo info) {
                    super.onSuccess(info);
                    loadingDialog.dismiss();
                    UpdateOrderBean createOrderBean = info.getRetDetail(UpdateOrderBean.class);
                    if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                        Toast.makeText(MyCourseDetailActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new BusEntity(6001));
                    } else if (createOrderBean.getCode().equalsIgnoreCase("401")) {
                        Intent intent = new Intent();
                        intent.setClass(context, NotificationBroadcast.class);
                        intent.putExtra(NotificationBroadcast.EXTRA_KEY_ACTION,
                                NotificationBroadcast.ACTION_REFRESHTOKEN);
                        sendBroadcast(intent);
                    } else {
                        Toast.makeText(MyCourseDetailActivity.this, createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(HttpInfo info) {
                    super.onFailure(info);
                    loadingDialog.dismiss();
                }
            }, id, coachId, storeId, courseId, courseName, payType, count, price, total_price, total_price, name, phone, remark, aboutArrangeId, arrangeTime);
        }

    }
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setTrafficEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.setIndoorEnabled(true);
        UiSettings mUiSettings = mMap.getUiSettings();
        // Keep the UI Settings state in sync with the checkboxes.
        mUiSettings.setZoomControlsEnabled(true);
        mMap.setMyLocationEnabled(true);
//        // Add a marker in Sydney, Australia, and move the camera.
        final LatLng sydney = new LatLng(-41.095977, 175.093335);
        final Location location = LocationUtils.getInstance(MyCourseDetailActivity.this).showLocation();
        if (location != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(location.getLatitude(),
                            location.getLongitude()), 15));
            //获取当前位置信息
            SimpleryoNetwork.getAddressInfo(MyCourseDetailActivity.this, new MyBaseProgressCallbackImpl() {
                @Override
                public void onSuccess(HttpInfo info) {
                    super.onSuccess(info);
                    CurrentAddressInfo currentAddressInfo = info.getRetDetail(CurrentAddressInfo.class);
                    if (currentAddressInfo.getStatus().equalsIgnoreCase("OK")) {
                        StringBuilder address = new StringBuilder();
                        List<CurrentAddressInfo.ResultsBean.AddressComponentsBean> addressComponentsBeans = currentAddressInfo.getResults().get(0).getAddress_components();
                        Collections.reverse(addressComponentsBeans);
                        for (int i = 0; i < addressComponentsBeans.size(); i++) {
                            if (i == 3 || i == 4) {
                                address.append(addressComponentsBeans.get(i).getLong_name()+" ");
                            }
                        }
                        mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(),
                                location.getLongitude())).title(address.toString()));
                    }
                }
            }, location.getLatitude(), location.getLongitude());
        } else {
            mMap.moveCamera(CameraUpdateFactory
                    .newLatLngZoom(sydney, 15));
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Inflate the layouts for the info window, title and snippet.
                View infoWindow = getLayoutInflater().inflate(R.layout.custom_info_contents,
                        (FrameLayout) findViewById(R.id.map), false);

                TextView title = ((TextView) infoWindow.findViewById(R.id.title));
                title.setText(marker.getTitle());

                TextView snippet = ((TextView) infoWindow.findViewById(R.id.snippet));
                snippet.setText(marker.getSnippet());

                return infoWindow;
            }
        });
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(Bundle bundle) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }


}
