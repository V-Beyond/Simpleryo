package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.FragMentAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.fragment.OrderCancelFragment;
import com.simpleryo.leyotang.fragment.OrderCompleteFragment;
import com.simpleryo.leyotang.fragment.OrderNewFragment;
import com.simpleryo.leyotang.fragment.OrderPayedFragment;
import com.simpleryo.leyotang.fragment.OrderReceiveFragment;
import com.simpleryo.leyotang.utils.XActivityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanglei
 * @ClassNname：MyCourse.java
 * @Describe 我的订单分类页面
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_order_layout)
public class MyOrderActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.view_pager_main)
    ViewPager view_pager_main;
    @ViewInject(R.id.radio_btn_my_wait_pay)
    RadioButton radio_btn_my_wait_pay;
    @ViewInject(R.id.radio_btn_payed)
    RadioButton radio_btn_payed;
    @ViewInject(R.id.radio_btn_wait_remark)
    RadioButton radio_btn_wait_remark;
    @ViewInject(R.id.radio_btn_complete)
    RadioButton radio_btn_complete;
    @ViewInject(R.id.radio_btn_cancel)
    RadioButton radio_btn_cancel;
    FragMentAdapter<XLibraryLazyFragment> mAdapter;
    List<XLibraryLazyFragment> fragments = new ArrayList<XLibraryLazyFragment>();
    String status = "";//订单状态

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status = getIntent().getStringExtra("status");
        tv_name.setText(getResources().getString(R.string.my_order));
        fragments.add(new OrderNewFragment());
        fragments.add(new OrderPayedFragment());
        fragments.add(new OrderReceiveFragment());
        fragments.add(new OrderCompleteFragment());
        fragments.add(new OrderCancelFragment());
        mAdapter = new FragMentAdapter<XLibraryLazyFragment>(
                getSupportFragmentManager(), fragments);
        view_pager_main.setAdapter(mAdapter);
        if (status.equalsIgnoreCase("NEW")) {//待支付
            radio_btn_my_wait_pay.setChecked(true);
            radio_btn_payed.setChecked(false);
            radio_btn_wait_remark.setChecked(false);
            radio_btn_complete.setChecked(false);
            radio_btn_cancel.setChecked(false);
            view_pager_main.setCurrentItem(0);
        } else if (status.equalsIgnoreCase("PAYED")) {//已支付
            radio_btn_my_wait_pay.setChecked(false);
            radio_btn_payed.setChecked(true);
            radio_btn_wait_remark.setChecked(false);
            radio_btn_complete.setChecked(false);
            radio_btn_cancel.setChecked(false);
            view_pager_main.setCurrentItem(1);
        } else if (status.equalsIgnoreCase("RECEIVED")) {//待评价
            radio_btn_my_wait_pay.setChecked(false);
            radio_btn_payed.setChecked(false);
            radio_btn_wait_remark.setChecked(true);
            radio_btn_complete.setChecked(false);
            radio_btn_cancel.setChecked(false);
            view_pager_main.setCurrentItem(2);
        } else if (status.equalsIgnoreCase("COMPLETED")) {//已完成
            radio_btn_my_wait_pay.setChecked(false);
            radio_btn_payed.setChecked(false);
            radio_btn_wait_remark.setChecked(false);
            radio_btn_complete.setChecked(true);
            radio_btn_cancel.setChecked(false);
            view_pager_main.setCurrentItem(3);
        } else if (status.equalsIgnoreCase("CANCALLED")) {//已取消
            radio_btn_my_wait_pay.setChecked(false);
            radio_btn_payed.setChecked(false);
            radio_btn_wait_remark.setChecked(false);
            radio_btn_complete.setChecked(false);
            radio_btn_cancel.setChecked(true);
            view_pager_main.setCurrentItem(4);
        }
    }

    @Event(value = {R.id.radio_group_main}, type = RadioGroup.OnCheckedChangeListener.class)
    private void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.radio_btn_my_wait_pay:
                view_pager_main.setCurrentItem(0);
                break;
            case R.id.radio_btn_payed:
                view_pager_main.setCurrentItem(1);
                break;
            case R.id.radio_btn_wait_remark:
                view_pager_main.setCurrentItem(2);
                break;
            case R.id.radio_btn_complete:
                view_pager_main.setCurrentItem(3);
                break;
            case R.id.radio_btn_cancel:
                view_pager_main.setCurrentItem(4);
                break;

            default:
                break;
        }
    }

    @Event(value = {R.id.iv_back,R.id.iv_msg}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(MyOrderActivity.this);
                break;
            case R.id.iv_msg:
                startActivity(new Intent(MyOrderActivity.this,MyMsgActivity.class));
                break;
        }
    }
}
