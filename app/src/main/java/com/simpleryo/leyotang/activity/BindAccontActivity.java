package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BindAccountBean;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;
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
    UMShareAPI umShareAPI;
    @ViewInject(R.id.tv_nickname)
    TextView tv_nickname;
    @ViewInject(R.id.rl_wechat)
    RelativeLayout rl_wechat;
    public String userId;
    boolean isBindWechat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        tv_name.setText("绑定账号");
        umShareAPI = UMShareAPI.get(BindAccontActivity.this);
        userId = SharedPreferencesUtils.getKeyString("userId");
        isBindWechat = SharedPreferencesUtils.getKeyBoolean("isBindWechat");
        if (isBindWechat==true) {
            rl_wechat.setClickable(false);
            tv_nickname.setText("已绑定");
        }
    }

    String typeCode;

    @Event(value = {R.id.iv_back, R.id.iv_msg, R.id.rl_facebook, R.id.rl_wechat, R.id.rl_alipay}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(this);
                break;
            case R.id.iv_msg:
                startActivity(new Intent(BindAccontActivity.this, MyMsgActivity.class));
                break;
            case R.id.rl_wechat:
                typeCode = "WECHAT";
                if (umShareAPI.isInstall(BindAccontActivity.this, SHARE_MEDIA.WEIXIN)) {
                    doOauthVerify(SHARE_MEDIA.WEIXIN);
                } else {
                    Toast.makeText(BindAccontActivity.this, "请安装微信客户端", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_alipay:
                doOauthVerify(SHARE_MEDIA.ALIPAY);
                break;
            case R.id.rl_facebook:
                typeCode = "Facebook";
                doOauthVerify(SHARE_MEDIA.FACEBOOK);
                break;
        }
    }

    public void doOauthVerify(SHARE_MEDIA share_media) {
        umShareAPI.deleteOauth(BindAccontActivity.this, share_media, deleteAuthListener);
    }

    public void getPlatformInfo(SHARE_MEDIA share_media) {
        umShareAPI.getPlatformInfo(BindAccontActivity.this, share_media, getInfoListener);
    }

    UMAuthListener deleteAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            umShareAPI.doOauthVerify(BindAccontActivity.this, platform, authListener);
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
    UMAuthListener getInfoListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Log.w("cc", "first_name:" + data.get("name"));
            Log.w("cc", "iconurl:" + data.get("iconurl"));
            Log.w("cc", "uid:" + data.get("uid"));
//            tv_nickname.setText(data.get("name"));
            EventBus.getDefault().post(new BusEntity(303, data.get("uid")));
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

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            getPlatformInfo(platform);
//            Toast.makeText(BindAccontActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//            Toast.makeText(BindAccontActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
//            Toast.makeText(BindAccontActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSex(BusEntity bus) {
        if (bus.getType() == 303) {
            String thirdNo = bus.getContent();
            bindAccount(thirdNo, typeCode);
        }
    }

    /**
     * 绑定第三方账号
     * @param thirdNo
     * @param typeCode
     */
    public void bindAccount(String thirdNo, String typeCode) {
        SimpleryoNetwork.bindAccount(BindAccontActivity.this, new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                BindAccountBean bindAccountBean = info.getRetDetail(BindAccountBean.class);
                if (bindAccountBean.getCode().equalsIgnoreCase("0")) {
                    tv_nickname.setText("已绑定");
                    rl_wechat.setClickable(false);
                }
            }
            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
            }
        }, userId, thirdNo, typeCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
