package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
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

    @Event(value = {R.id.tv_register, R.id.tv_login}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.tv_login:
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
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            SharedPreferencesUtils.saveKeyBoolean("isLogin", true);
                            SharedPreferencesUtils.saveKeyString("token",loginBean.getData().getToken());
                            SharedPreferencesUtils.saveKeyString("userId", loginBean.getData().getUserId());
                            XActivityUtils.getInstance().popActivity(LoginActivity.this);
//                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
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
