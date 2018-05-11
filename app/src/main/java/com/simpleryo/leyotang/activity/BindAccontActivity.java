package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.WxUserInfo;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.simpleryo.leyotang.wxapi.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
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
    private IWXAPI api;

    public String userId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        tv_name.setText("绑定账号");
        umShareAPI = UMShareAPI.get(BindAccontActivity.this);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        api.registerApp(Constants.APP_ID);
        userId = SharedPreferencesUtils.getKeyString("userId");
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
                typeCode="APP";
                if (api.isWXAppInstalled()) {
//                    doOauthVerify(SHARE_MEDIA.WEIXIN);
                    final SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "leyotang_weixin";
                    api.sendReq(req);
                } else {
                    Toast.makeText(BindAccontActivity.this, "请安装微信客户端", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_alipay:
                doOauthVerify(SHARE_MEDIA.ALIPAY);
                break;
            case R.id.rl_facebook:
                typeCode="Facebook";
                doOauthVerify(SHARE_MEDIA.FACEBOOK);
                break;
        }
    }

    public void doOauthVerify(SHARE_MEDIA share_media) {
//        boolean isauth = umShareAPI.isAuthorize(BindAccontActivity.this, share_media);
//        if (isauth) {
        umShareAPI.deleteOauth(BindAccontActivity.this, share_media, deleteAuthListener);
//        } else {
//            umShareAPI.doOauthVerify(BindAccontActivity.this, share_media, authListener);
//        }
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
//            Toast.makeText(BindAccontActivity.this, "成功了", Toast.LENGTH_LONG).show();
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
            tv_nickname.setText(data.get("name"));
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
            Toast.makeText(BindAccontActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(BindAccontActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSex(BusEntity bus) {
        if (bus.getType() == 301) {
            String code = bus.getContent();
            getAccess_token(code);
        }
        if (bus.getType() == 302) {
            getUserMsg(access_token, openid);
        }
        if (bus.getType() == 303) {
            String thirdNo=bus.getContent();
            bindAccount(thirdNo,typeCode);
        }
    }

    String openid;
    String access_token;

    /**
     * 获取openid  accessToken值用于后期操作
     *
     * @param code 请求码
     */
    private void getAccess_token(String code) {
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + Constants.APP_ID
                + "&secret="
                + Constants.APP_SECRET
                + "&code="
                + code
                + "&grant_type=authorization_code";
        SimpleryoNetwork.getAccess_token(this, new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                String json = info.getRetDetail();
                try {
                    JSONObject object = new JSONObject(json);
                    //获取用户的openid
                    openid = object.getString("openid");
                    access_token = object.getString("access_token");
                    EventBus.getDefault().post(new BusEntity(302));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, path);
    }


    public void bindAccount(String thirdNo,String typeCode){
        SimpleryoNetwork.bindAccount(BindAccontActivity.this,new MyBaseProgressCallbackImpl(){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);

            }
            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
            }
        },userId,thirdNo,typeCode);
    }
    /**
     * 获取微信个人信息
     *
     * @param access_token
     * @param openid
     */
    private void getUserMsg(final String access_token, final String openid) {
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid;
        SimpleryoNetwork.getUserMsg(this, new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                WxUserInfo wxUserInfo = info.getRetDetail(WxUserInfo.class);
                tv_nickname.setText(wxUserInfo.getNickname());
                Log.w("cc","getUnionid:"+wxUserInfo.getUnionid());
                EventBus.getDefault().post(new BusEntity(303,wxUserInfo.getUnionid()));
            }
        }, path);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
