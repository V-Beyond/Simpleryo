package com.simpleryo.leyotang.base;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.view.LoadingDialog;


/**
 * @author huanglei
 * @version V1.0
 * @Title: MyBaseProgressCallbackImpl
 * @Package com.hpkj.nrw.base
 * @Description:自定义请求回调类
 * @date 2017/2/15 14:46
 */

public class MyBaseProgressCallbackImpl implements com.okhttplib.callback.Callback {
    public Context context;
    public LoadingDialog loadingDialog;//加载提示框

    Handler mHandler = new Handler();

    public MyBaseProgressCallbackImpl() {
        super();
    }

    public MyBaseProgressCallbackImpl(final Context content) {
        this.context = content;
        if (content != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (loadingDialog == null) {
                        loadingDialog = new LoadingDialog(content);
                        //点击空白处Dialog不消失
                        loadingDialog.setCanceledOnTouchOutside(false);
                        loadingDialog.showDialog();
                }
                }
            });
        }
    }

    @Override
    public void onSuccess(HttpInfo info)  {
        Log.w("cc", "onSuccess：" + info.getRetDetail());
    }

    @Override
    public void onFailure(HttpInfo info) {
        Log.w("cc", "onFailure：" + info.getRetDetail());
    }
}

