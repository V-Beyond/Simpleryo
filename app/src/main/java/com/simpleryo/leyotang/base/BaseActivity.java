package com.simpleryo.leyotang.base;

import android.os.Bundle;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.squareup.picasso.Transformation;
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
    public  boolean isLogin;//是否登录
    public Transformation transformation = new RoundedTransformationBuilder()
            .cornerRadiusDp(30)
            .oval(true)
            .build();
    public Transformation raduisTransformation = new RoundedTransformationBuilder()
            .cornerRadius(20)
            .oval(false)
            .build();
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
        MobclickAgent.onPageStart(mPageName);
        MobclickAgent.onResume(this); // 基础指标统计，不能遗漏
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
        MobclickAgent.onPause(this); // 基础指标统计，不能遗漏
    }
}
