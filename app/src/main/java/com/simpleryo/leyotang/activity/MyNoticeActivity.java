package com.simpleryo.leyotang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.utils.XActivityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * @ClassNname：MyCourse.java
 * @Describe 我的订单页面
 * @author huanglei
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_my_notice)
public class MyNoticeActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_msg)
    ImageView iv_msg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("公告");
        iv_msg.setVisibility(View.GONE);

    }
    @Event(value = {R.id.iv_back}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(MyNoticeActivity.this);
                break;
        }
    }
}
