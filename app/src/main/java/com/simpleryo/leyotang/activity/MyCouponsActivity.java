package com.simpleryo.leyotang.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.FragMentAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.fragment.CouponFragment;
import com.simpleryo.leyotang.utils.XActivityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanglei
 * @ClassNname：MyCourse.java
 * @Describe 我的优惠券
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_coupons_layout)
public class MyCouponsActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_msg)
    ImageView iv_msg;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iv_msg.setVisibility(View.GONE);
        tv_name.setText(getResources().getString(R.string.my_coupon));
        fragments.add(new CouponFragment());
        fragments.add(new CouponFragment());
        fragments.add(new CouponFragment());
        mAdapter = new FragMentAdapter<XLibraryLazyFragment>(
                getSupportFragmentManager(), fragments);
        view_pager_main.setAdapter(mAdapter);
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
            default:
                break;
        }
    }

    @Event(value = {R.id.iv_back}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(MyCouponsActivity.this);
                break;
        }
    }
}
