package com.simpleryo.leyotang.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.simpleryo.leyotang.bean.BusEntity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @ClassNname：WXEntryActivity.java
 * @Describe 微信分享回调类
 * @author huanglei
 * @time 2018/5/11 10:26
 */

public class WXEntryActivity extends Activity  implements IWXAPIEventHandler {
    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        api.registerApp(Constants.APP_ID);
        try {
            api.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.errCode==BaseResp.ErrCode.ERR_OK){
            if (baseResp instanceof SendAuth.Resp){
                SendAuth.Resp sendResp = (SendAuth.Resp) baseResp;
                if (sendResp != null) {
                    String code = sendResp.code;
                    EventBus.getDefault().post(new BusEntity(301,code));
                    Toast.makeText(WXEntryActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(WXEntryActivity.this,"分享成功",Toast.LENGTH_SHORT).show();
            }
        }if (baseResp.errCode==BaseResp.ErrCode.ERR_SENT_FAILED){
            Toast.makeText(WXEntryActivity.this,"分享失败",Toast.LENGTH_SHORT).show();
        }
        if (baseResp.errCode==BaseResp.ErrCode.ERR_USER_CANCEL){
            Toast.makeText(WXEntryActivity.this,"分享取消",Toast.LENGTH_SHORT).show();
        }
        finish();
    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
}
