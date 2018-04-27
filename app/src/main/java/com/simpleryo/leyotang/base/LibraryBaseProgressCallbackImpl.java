package com.simpleryo.leyotang.base;


public abstract class LibraryBaseProgressCallbackImpl<T> extends
		BaseProgressCallbackImpl<T>  {

	@Override
	public void onSuccess(T resul) {
		super.onSuccess(resul);
	}

	@Override
	public void onError(Throwable ex, boolean isOnCallback) {
		super.onError(ex, isOnCallback);
	}

	@Override
	public void onCancelled(CancelledException cex) {
		super.onCancelled(cex);
	}

	@Override
	public void onFinished() {
		super.onFinished();
	}

	@Override
	public void onWaiting() {
		super.onWaiting();
	}

	@Override
	public void onStarted() {
		super.onStarted();

	}

	@Override
	public void onLoading(long total, long current, boolean isDownloading) {
		super.onLoading(total, current, isDownloading);
	}

}
