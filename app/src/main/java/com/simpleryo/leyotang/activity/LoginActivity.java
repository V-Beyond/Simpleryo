package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.LoginBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * @author huanglei
 * @ClassNname：LoginActivity.java
 * @Describe 登录页面
 * @time 2018/3/21 14:07
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.edittext_account)
    EditText edittext_account;
    @ViewInject(R.id.edittext_password)
    EditText edittext_password;
    String account;
    String passWord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Event(value = {R.id.tv_register, R.id.tv_login,R.id.forget_the_password}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register://注册
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class).putExtra("type","register"));
                break;
            case R.id.forget_the_password://忘记密码
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class).putExtra("type","forget"));
                break;
            case R.id.tv_login://登录
                account = edittext_account.getText().toString().trim();
                passWord = edittext_password.getText().toString().trim();
                if (account.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passWord.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleryoNetwork.userLogin(LoginActivity.this, new MyBaseProgressCallbackImpl(LoginActivity.this) {
                    @Override
                    public void onSuccess(HttpInfo info) {
                        LoginBean loginBean = info.getRetDetail(LoginBean.class);
                        loadingDialog.dismiss();
                        if (loginBean.getCode().equalsIgnoreCase("0")) {
                            //设置推送Alias
                            PushAgent mPushAgent = PushAgent.getInstance(LoginActivity.this);
                            mPushAgent.setPushCheck(true);
                            mPushAgent.setAlias(loginBean.getData().getUserId(), "leyotang", new UTrack.ICallBack() {
                                @Override
                                public void onMessage(boolean isSuccess, String message) {
                                    Log.w("cc","isSuccess:"+isSuccess);
                                    Log.w("cc","message:"+message);
                                }
                            });
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            SharedPreferencesUtils.saveKeyString("refreshToken",loginBean.getData().getRefreshToken());
                            SharedPreferencesUtils.saveKeyBoolean("isLogin", true);
                            SharedPreferencesUtils.saveKeyString("token",loginBean.getData().getToken());
                            SharedPreferencesUtils.saveKeyString("userId", loginBean.getData().getUserId());
                            XActivityUtils.getInstance().popActivity(LoginActivity.this);
                        } else {
                            Toast.makeText(LoginActivity.this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(HttpInfo info) {

                    }
                }, account, passWord);
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            XActivityUtils.getInstance().popActivity(this);
        }
        return super.onKeyDown(keyCode, event);
    }
}
