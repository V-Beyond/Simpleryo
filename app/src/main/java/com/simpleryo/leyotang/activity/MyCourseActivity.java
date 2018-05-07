package com.simpleryo.leyotang.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.FragMentAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.fragment.MyCourseFinishFragment;
import com.simpleryo.leyotang.fragment.MyCourseFragment;
import com.simpleryo.leyotang.fragment.MyCourseStartedFragment;
import com.simpleryo.leyotang.utils.XActivityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNname：MyCourse.java
 * @Describe 我的订单课程分类页面
 * @author huanglei
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_my_course)
public class MyCourseActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.view_pager_main)
    ViewPager view_pager_main;
    FragMentAdapter<XLibraryLazyFragment> mAdapter;
    List<XLibraryLazyFragment> fragments = new ArrayList<XLibraryLazyFragment>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("我的课程");
        Bundle bundle=new Bundle();
        bundle.putString("status","ALL");
        MyCourseFragment myCourseFragment=new MyCourseFragment();//所有课程
        myCourseFragment.setArguments(bundle);
        Bundle bundle1=new Bundle();
        bundle1.putString("status","PENDING");
        MyCourseStartedFragment myCourseStartedFragment=new MyCourseStartedFragment();//已开始课程
        myCourseStartedFragment.setArguments(bundle1);
        Bundle bundle2=new Bundle();
        bundle2.putString("status","COMPLETED");
        MyCourseFinishFragment myCourseFinishFragment=new MyCourseFinishFragment();//已结束课程
        myCourseFinishFragment.setArguments(bundle2);
        fragments.add(myCourseFragment);
        fragments.add(myCourseStartedFragment);
        fragments.add(myCourseFinishFragment);
        mAdapter = new FragMentAdapter<XLibraryLazyFragment>(
                getSupportFragmentManager(), fragments);
        view_pager_main.setAdapter(mAdapter);
        view_pager_main.setCurrentItem(0);
    }
    @Event(value = {R.id.radio_group_main}, type = RadioGroup.OnCheckedChangeListener.class)
    private void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.radio_btn_my_course:
                view_pager_main.setCurrentItem(0);
                break;
            case R.id.radio_btn_course:
                view_pager_main.setCurrentItem(1);
                break;
            case R.id.radio_btn_coach:
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
                XActivityUtils.getInstance().popActivity(MyCourseActivity.this);
                break;
        }
    }
}
