package com.simpleryo.leyotang.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.CodeBean;
import com.simpleryo.leyotang.bean.RegisterBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.simpleryo.leyotang.utils.XStringPars;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * @author huanglei
 * @ClassNname：RegisterActivity.java
 * @Describe 注册页面
 * @time 2018/3/21 14:08
 */
@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {
    @ViewInject(R.id.edittext_email)
    EditText edittext_email;
    @ViewInject(R.id.edittext_phone)
    EditText edittext_phone;
    @ViewInject(R.id.edittext_code)
    EditText edittext_code;
    @ViewInject(R.id.edittext_password)
    EditText edittext_password;
    @ViewInject(R.id.edittext_comfirm_password)
    EditText edittext_comfirm_password;
    @ViewInject(R.id.tv_get_code)
    TextView tv_get_code;
    String email;//邮箱
    String phone;//手机号
    String code;//验证码
    String password;//密码
    String comfirmPassWord;//确认密码
    public TimeCount mTime;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTime = new TimeCount(60000, 1000);
    }

    @Event(value = {R.id.tv_register, R.id.tv_get_code}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register://注册
                email = edittext_email.getText().toString().trim();
                phone = edittext_phone.getText().toString().trim();
                code = edittext_code.getText().toString().trim();
                password = edittext_password.getText().toString().trim();
                comfirmPassWord = edittext_comfirm_password.getText().toString().trim();
                if (email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!XStringPars.isEmail(email)) {
                    Toast.makeText(RegisterActivity.this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "手机不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!XStringPars.isMobileNO(phone)) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (comfirmPassWord.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equalsIgnoreCase(comfirmPassWord)) {
                    Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!XStringPars.isCorrectPwd(password)) {
                    Toast.makeText(RegisterActivity.this, "请输入6-12位的数字和字母", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!XStringPars.isCorrectPwd(comfirmPassWord)) {
                    Toast.makeText(RegisterActivity.this, "请输入6-12位的数字和字母", Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleryoNetwork.userRegister(RegisterActivity.this, new MyBaseProgressCallbackImpl(RegisterActivity.this) {
                    @Override
                    public void onSuccess(HttpInfo info)   {
                        loadingDialog.dismiss();
                        RegisterBean registerBean = info.getRetDetail(RegisterBean.class);
                        if (registerBean.getCode().equalsIgnoreCase("0")){
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            XActivityUtils.getInstance().popActivity(RegisterActivity.this);
                        }else{
                            Toast.makeText(RegisterActivity.this, registerBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(HttpInfo info)   {
                        loadingDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                }, email, phone, code, password);
                break;
            case R.id.tv_get_code://获取验证码
                phone = edittext_phone.getText().toString().trim();
                if (phone.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "手机不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!XStringPars.isMobileNO(phone)) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                mTime.start();
                SimpleryoNetwork.userGetCode(RegisterActivity.this, new MyBaseProgressCallbackImpl(RegisterActivity.this) {
                    @Override
                    public void onSuccess(HttpInfo info)  {
                        loadingDialog.dismiss();
                        CodeBean codeBean = info.getRetDetail(CodeBean.class);
                        if (codeBean.getCode().equalsIgnoreCase("0")) {
                            Toast.makeText(RegisterActivity.this, "验证码发送成功，请注意查收", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterActivity.this, codeBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(HttpInfo info){

                    }
                }, phone);

                break;
        }
    }
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_get_code.setClickable(false);
            tv_get_code.setBackgroundResource(R.drawable.bg_getcode_gray);
            tv_get_code.setText(millisUntilFinished / 1000 + "S重新获取");
        }

        @Override
        public void onFinish() {
            tv_get_code.setBackgroundResource(R.drawable.shape_get_code);
            tv_get_code.setText("重新获取验证码");
            tv_get_code.setClickable(true);

        }
    }
}
