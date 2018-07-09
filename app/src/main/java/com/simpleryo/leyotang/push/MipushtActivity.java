package com.simpleryo.leyotang.push;

import android.content.Intent;
import android.os.Bundle;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.MainActivity;
import com.simpleryo.leyotang.activity.MyMsgActivity;
import com.umeng.message.UmengNotifyClickActivity;
import com.umeng.message.entity.UMessage;

import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
/**
 * @ClassNname：MipushtActivity.java
 * @Describe 华为系统推送
 * @author huanglei
 * @time 2018/7/9 10:12
 */
public class MipushtActivity extends UmengNotifyClickActivity {
    private static String TAG = MipushtActivity.class.getName();
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
    }
    @Override
    public void onMessage(Intent intent) {
        super.onMessage(intent);  //此方法必须调用，否则无法统计打开数
        String body = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
        UMessage msg = null;
        try {
            msg = new UMessage(new JSONObject(body));
            Map<String, String> extrs = msg.extra;
//                courseClass 课程开课
//                payComplete 支付完成
//                sysMessage 系统消息
            if (extrs.get("type").equalsIgnoreCase("courseClass")) {
                Intent courseClass = new Intent(MipushtActivity.this, MainActivity.class);
                courseClass.putExtra("type", "push");
                courseClass.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(courseClass);
                finish();
            }
            if (extrs.get("type").equalsIgnoreCase("payComplete")) {
                Intent payComplete = new Intent(MipushtActivity.this, MainActivity.class);
                payComplete.putExtra("type", "push");
                payComplete.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(payComplete);
                finish();
            }
            if (extrs.get("type").equalsIgnoreCase("sysMessage")) {
                Intent sysMessage = new Intent(MipushtActivity.this, MyMsgActivity.class);
                sysMessage.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(sysMessage);
                finish();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
