package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @ClassNname：SplashActivity.java
 * @Describe 闪屏页
 * @author huanglei
 * @time 2018/4/24 15:20
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends AutoLayoutActivity {
    @ViewInject(R.id.iv_splash)
    ImageView iv_splash;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        XActivityUtils.getInstance().pushActivity(this);
        ImmersionBar.with(this)
                .statusBarColor(R.color.color_transparent)
                .navigationBarWithKitkatEnable(false)
                .fullScreen(false)
                .init();
//        Picasso.with(SplashActivity.this).load("http://p0.so.qhimgs1.com/bdr/_240_/t01a43386419b27c414.jpg").into(iv_splash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    startActivity(new Intent(SplashActivity.this,MainActivity.class).putExtra("type","splash"));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
