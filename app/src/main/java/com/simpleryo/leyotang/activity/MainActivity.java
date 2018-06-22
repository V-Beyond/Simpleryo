package com.simpleryo.leyotang.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.FragMentAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.fragment.CourseFragment;
import com.simpleryo.leyotang.fragment.HomeFragment;
import com.simpleryo.leyotang.fragment.MyFragment;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.umeng.analytics.MobclickAgent;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * @author huanglei
 * @ClassNname：MainActivity.java
 * @Describe 主页面
 * @time 2018/3/19 10:47
 */
@RuntimePermissions
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private final String mPageName = "MainActivity";
    @ViewInject(R.id.view_pager_main)
    ViewPager view_pager_main;
    @ViewInject(R.id.radio_btn_home)
    RadioButton radio_btn_home;
    @ViewInject(R.id.radio_btn_course)
    RadioButton radio_btn_course;
    @ViewInject(R.id.radio_btn_my)
    RadioButton radio_btn_my;
    FragMentAdapter<XLibraryLazyFragment> mAdapter;
    List<XLibraryLazyFragment> fragments = new ArrayList<XLibraryLazyFragment>();
    MyFragment myFragment;
    String type;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobclickAgent.openActivityDurationTrack(false);
        //设置 U-Dplus场景
        MobclickAgent.setScenarioType(MainActivity.this, MobclickAgent.EScenarioType.E_DUM_NORMAL);
        type=getIntent().getStringExtra("type");

        myFragment = new MyFragment();
        fragments.add(new HomeFragment());
        fragments.add(new CourseFragment());
        fragments.add(myFragment);
        mAdapter = new FragMentAdapter<XLibraryLazyFragment>(
                getSupportFragmentManager(), fragments);
        view_pager_main.setAdapter(mAdapter);
        if (type.equalsIgnoreCase("splash")){//启动页跳转过来
            view_pager_main.setCurrentItem(0);
        }
        if (type.equalsIgnoreCase("push")){//启动页跳转过来
            view_pager_main.setCurrentItem(1);
            radio_btn_course.setChecked(true);
            radio_btn_home.setChecked(false);
            radio_btn_my.setChecked(false);
        }
        MainActivityPermissionsDispatcher.getMultiPermissionWithCheck(this);
    }

    @Event(value = {R.id.radio_group_main}, type = RadioGroup.OnCheckedChangeListener.class)
    private void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.radio_btn_home:
                MobclickAgent.onEvent(MainActivity.this, "click");
                view_pager_main.setCurrentItem(0);
                break;
            case R.id.radio_btn_course:
                MobclickAgent.onEvent(MainActivity.this, "click");
                view_pager_main.setCurrentItem(1);
                break;
            case R.id.radio_btn_my:
                MobclickAgent.onEvent(MainActivity.this, "click");
                view_pager_main.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        myFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (view_pager_main.getCurrentItem() != 0) {
                radio_btn_home.setChecked(true);
                view_pager_main.setCurrentItem(0);
                return false;
            } else {
                XActivityUtils.getInstance().exit();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 动态获取权限
     */
    @NeedsPermission({ Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE,Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR})
    void getMultiPermission() {
    }

    /**
     * 权限获取结果
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(mPageName);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
    }
}
