package com.simpleryo.leyotang.base;

import android.os.Bundle;

import com.simpleryo.leyotang.utils.XActivityUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.x;

/**
 * @ClassNname：BaseActivity.java
 * @Describe activity基类
 * @author huanglei
 * @time 2018/3/19 11:03
 */
public class BaseActivity extends AutoLayoutActivity {
    private final String mPageName = "BaseActivity";
    public  boolean isLogin;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        XActivityUtils.getInstance().pushActivity(this);
        PushAgent.getInstance(this).onAppStart();
        // SDK在统计Fragment时，需要关闭Activity自带的页面统计，
        // 然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
        MobclickAgent.openActivityDurationTrack(false);

        // 设置为U-APP场景
        MobclickAgent.setScenarioType(BaseActivity.this, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this); // 基础指标统计，不能遗漏
        MobclickAgent.onPageStart(mPageName);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this); // 基础指标统计，不能遗漏
        MobclickAgent.onPageEnd(mPageName);
    }
}
