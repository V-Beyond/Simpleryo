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
import com.simpleryo.leyotang.fragment.ExcellentCourseFragment;
import com.simpleryo.leyotang.fragment.HotCourseFragment;
import com.simpleryo.leyotang.fragment.OfficialCourseFragment;
import com.simpleryo.leyotang.utils.XActivityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNname：MyCourse.java
 * @Describe 课程分类页面
 * @author huanglei
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_course_type)
public class CourseTypeActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.view_pager_main)
    ViewPager view_pager_main;
    @ViewInject(R.id.radio_btn_my_course)
    RadioButton radio_btn_my_course;
    @ViewInject(R.id.radio_btn_course)
    RadioButton radio_btn_course;
    @ViewInject(R.id.radio_btn_coach)
    RadioButton radio_btn_coach;
    FragMentAdapter<XLibraryLazyFragment> mAdapter;
    List<XLibraryLazyFragment> fragments = new ArrayList<XLibraryLazyFragment>();
    String type;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("课程列表");
        type=getIntent().getStringExtra("type");
        fragments.add(new HotCourseFragment());
        fragments.add(new ExcellentCourseFragment());
        fragments.add(new OfficialCourseFragment());
        mAdapter = new FragMentAdapter<XLibraryLazyFragment>(
                getSupportFragmentManager(), fragments);
        view_pager_main.setAdapter(mAdapter);
        if (type.equalsIgnoreCase("hot")){//热门
            radio_btn_my_course.setChecked(true);
            radio_btn_course.setChecked(false);
            radio_btn_coach.setChecked(false);
            view_pager_main.setCurrentItem(0);
        }else if(type.equalsIgnoreCase("excellent")){//精选
            radio_btn_my_course.setChecked(false);
            radio_btn_course.setChecked(true);
            radio_btn_coach.setChecked(false);
            view_pager_main.setCurrentItem(1);
        }else if(type.equalsIgnoreCase("introductory")){//推荐
            radio_btn_my_course.setChecked(false);
            radio_btn_course.setChecked(false);
            radio_btn_coach.setChecked(true);
            view_pager_main.setCurrentItem(2);
        }
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
    @Event(value = {R.id.iv_back,R.id.iv_msg}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(CourseTypeActivity.this);
                break;
            case R.id.iv_msg:
                startActivity(new Intent(CourseTypeActivity.this,MyMsgActivity.class));
                break;
        }
    }
}
