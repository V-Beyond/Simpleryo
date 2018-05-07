package com.simpleryo.leyotang.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.FragMentAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.bean.StoreDetailBean;
import com.simpleryo.leyotang.fragment.BusinessCoachFragment;
import com.simpleryo.leyotang.fragment.BusinessCourseFragment;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.squareup.picasso.Picasso;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNname：BusinessHomeActivty.java
 * @Describe 商家主页
 * @author huanglei
 * @time 2018/3/27 14:24
 */
@ContentView(R.layout.activity_business_home)
public class BusinessHomeActivty extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.view_pager_main)
    ViewPager view_pager_main;
    FragMentAdapter<XLibraryLazyFragment> mAdapter;
    List<XLibraryLazyFragment> fragments = new ArrayList<XLibraryLazyFragment>();
    String storeId;
    @ViewInject(R.id.tv_store_name)
    TextView tv_store_name;
    @ViewInject(R.id.tv_store_status)
    TextView tv_store_status;
    @ViewInject(R.id.iv_licence)
    ImageView iv_licence;
    @ViewInject(R.id.tv_followCount)
    TextView tv_followCount;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("商家主页");
        storeId=getIntent().getStringExtra("storeId");
        Bundle bundle=new Bundle();
        bundle.putString("storeId",storeId);
        BusinessCourseFragment businessCourseFragment=new BusinessCourseFragment();
        businessCourseFragment.setArguments(bundle);
        BusinessCoachFragment businessCoachFragment=new BusinessCoachFragment();
        businessCoachFragment.setArguments(bundle);
        fragments.add(businessCourseFragment);
        fragments.add(businessCoachFragment);
        mAdapter = new FragMentAdapter<XLibraryLazyFragment>(
                getSupportFragmentManager(), fragments);
        view_pager_main.setAdapter(mAdapter);
        view_pager_main.setCurrentItem(0);
        getStoreDetail();
    }

    /**
     * 获取商家详情
     */
    public void getStoreDetail(){
        SimpleryoNetwork.getStoreDetail(BusinessHomeActivty.this,new MyBaseProgressCallbackImpl(BusinessHomeActivty.this){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                loadingDialog.dismiss();
                StoreDetailBean storeDetailBean=info.getRetDetail(StoreDetailBean.class);
                if (storeDetailBean.getCode().equalsIgnoreCase("0")){
                    tv_store_name.setText("商家名称"+storeDetailBean.getData().getStoreInfo().getName());
                    if (storeDetailBean.getData().getStoreInfo().getStatus().equalsIgnoreCase("AUDIT_OK")){
                        tv_store_status.setText("已认证");
                    }else if(storeDetailBean.getData().getStoreInfo().getStatus().equalsIgnoreCase("AUDITING")){
                        tv_store_status.setText("待审核");
                    }
                    else if(storeDetailBean.getData().getStoreInfo().getStatus().equalsIgnoreCase("AUDIT_FAIL")){
                        tv_store_status.setText("未通过");
                    }
                    if (storeDetailBean.getData().getStoreInfo().getLicenceUrl()!=null){
                        Picasso.with(BusinessHomeActivty.this).load(storeDetailBean.getData().getStoreInfo().getLicenceUrl()).into(iv_licence);
                    }
                    tv_followCount.setText(storeDetailBean.getData().getFollowCount()+"");
                }
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                loadingDialog.dismiss();
                Toast.makeText(BusinessHomeActivty.this,"数据一不小心走丢了，请稍后回来",Toast.LENGTH_SHORT).show();
            }
        },storeId);
    }
    @Event(value = {R.id.radio_group_main}, type = RadioGroup.OnCheckedChangeListener.class)
    private void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.radio_btn_course:
                view_pager_main.setCurrentItem(0);
                break;
            case R.id.radio_btn_coach:
                view_pager_main.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    @Event(value = {R.id.iv_back}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(this);
                break;
        }
    }
}
