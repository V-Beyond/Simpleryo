package com.simpleryo.leyotang.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.Map;

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

    @Event(value = {R.id.iv_back,R.id.rl_facebook}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(this);
                break;
            case R.id.rl_facebook:
                    UMShareAPI.get(BindAccontActivity.this).doOauthVerify(BindAccontActivity.this, SHARE_MEDIA.FACEBOOK, authListener);
                break;
        }
    }



    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Log.w("cc","first_name:"+data.get("data"));
            Toast.makeText(BindAccontActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(BindAccontActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(BindAccontActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSex(BusEntity bus) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
