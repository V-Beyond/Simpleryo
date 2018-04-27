package com.simpleryo.leyotang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.utils.XActivityUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * @author huanglei
 * @ClassNname：MyInfoActivity.java
 * @Describe 绑定账号信息
 * @time 2018/3/22 15:30
 */
@ContentView(R.layout.activity_bind_account)
public class BindAccontActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        tv_name.setText("绑定账号");
    }

    @Event(value = {R.id.iv_back}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(this);
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSex(BusEntity bus) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
