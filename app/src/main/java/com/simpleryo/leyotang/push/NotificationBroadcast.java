package com.simpleryo.leyotang.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.activity.MyNoticeActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.LoginBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;
import com.umeng.message.UTrack;
import com.umeng.message.entity.UMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationBroadcast extends BroadcastReceiver {
    public static final String EXTRA_KEY_ACTION = "ACTION";
    public static final String EXTRA_KEY_MSG = "MSG";
    public static final int ACTION_CLICK = 10;
    public static final int ACTION_DISMISS = 11;
    public static final String ACTION_REFRESHTOKEN = "com.simpleryo.token";
    public static final int EXTRA_ACTION_NOT_EXIST = -1;
    private static final String TAG = NotificationBroadcast.class.getName();
    @Override
    public void onReceive(Context context, Intent intent) {
        EventBus.getDefault().register(this);
        String message = intent.getStringExtra(EXTRA_KEY_MSG);
        int action = intent.getIntExtra(EXTRA_KEY_ACTION,
                EXTRA_ACTION_NOT_EXIST);
        try {
            Log.w("cc","onReceive："+message);
            if(message!=null){
                UMessage msg = new UMessage(new JSONObject(message));
                switch (action) {
                    case ACTION_DISMISS:
                        Log.i(TAG, "dismiss notification");
                        UTrack.getInstance(context).setClearPrevMessage(true);
                        UTrack.getInstance(context).trackMsgDismissed(msg);
                        break;
                    case ACTION_CLICK:
                        Log.i(TAG, "click notification");
                        Intent myMsgIntent = new Intent(context, MyNoticeActivity.class);
                        myMsgIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(myMsgIntent);
                        UTrack.getInstance(context).setClearPrevMessage(true);
                        MyNotificationService.oldMessage = null;
                        UTrack.getInstance(context).trackMsgClick(msg);
                        break;
                }
            }
            //
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String tokenAction=intent.getAction();
        if (tokenAction.equalsIgnoreCase(ACTION_REFRESHTOKEN)){
            String refreshToken= SharedPreferencesUtils.getKeyString("refreshToken");
            Log.w("cc","refreshToken:"+refreshToken);
            SimpleryoNetwork.refreshToken(context,new MyBaseProgressCallbackImpl(){
                @Override
                public void onSuccess(HttpInfo info) {
                    super.onSuccess(info);
                    LoginBean loginBean = info.getRetDetail(LoginBean.class);
                    if (loginBean.getCode().equalsIgnoreCase("0")) {
                        SharedPreferencesUtils.saveKeyString("refreshToken",loginBean.getData().getRefreshToken());
                        SharedPreferencesUtils.saveKeyString("token",loginBean.getData().getToken());
                        EventBus.getDefault().post(new BusEntity(021));
                    }else if (loginBean.getCode().equalsIgnoreCase("101")){
                        SharedPreferencesUtils.saveKeyBoolean("isLogin", false);
                        SharedPreferencesUtils.saveKeyString("token","simpleryo");
                        EventBus.getDefault().post(new BusEntity(021));
                    }
                }
                @Override
                public void onFailure(HttpInfo info) {
                    super.onFailure(info);
                }
            },refreshToken);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void BusMain(BusEntity bus) {

    }
}
