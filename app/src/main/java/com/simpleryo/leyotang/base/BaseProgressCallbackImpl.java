package com.simpleryo.leyotang.base;

import org.xutils.common.Callback.ProgressCallback;

public abstract class BaseProgressCallbackImpl<T> implements
        ProgressCallback<T> {

	@Override
	public void onSuccess(T resul) {

	}

	@Override
	public void onError(Throwable ex, boolean isOnCallback) {
	}

	@Override
	public void onCancelled(CancelledException cex) {

	}

	@Override
	public void onFinished() {

	}

	@Override
	public void onWaiting() {

	}

	@Override
	public void onStarted() {

	}

	@Override
	public void onLoading(long total, long current, boolean isDownloading) {

	}

}
