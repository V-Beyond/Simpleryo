package com.simpleryo.leyotang.activity;

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
 * @ClassNname：AboutUsActivity.java
 * @Describe 联系我们页面
 * @author huanglei
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_about_us)
public class UseHelpActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("使用帮助");
    }
    @Event(value = {R.id.iv_back}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(UseHelpActivity.this);
                break;
        }
    }
}
