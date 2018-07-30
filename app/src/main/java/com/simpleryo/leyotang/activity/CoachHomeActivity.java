package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.utils.XActivityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * @ClassNname：MyCourse.java
 * @Describe 课程分类页面
 * @author huanglei
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_coach_home)
public class CoachHomeActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("个人主页");
    }
    @Event(value = {R.id.iv_back,R.id.iv_msg}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(CoachHomeActivity.this);
                break;
            case R.id.iv_msg:
                startActivity(new Intent(CoachHomeActivity.this,MyMsgActivity.class));
                break;
        }
    }
}
