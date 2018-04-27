package com.simpleryo.leyotang.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @ClassNname：NoScrollViewPager.java
 * @Describe ViewPage 的左右滑动禁止 操作自定义类
 * @author huanglei
 * @time 2018/3/19 10:56
 */
public class ScrollAutoHeightViewPager extends ViewPager {
	private boolean noScroll = true;//false 可左右滑动，true 不可左右滑动

	public ScrollAutoHeightViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ScrollAutoHeightViewPager(Context context) {
		super(context);
	}

	public void setNoScroll(boolean noScroll) {
		this.noScroll = noScroll;
	}

	@Override
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (noScroll)
			return false;
		else
			return super.onTouchEvent(arg0);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int height = 0;
		for(int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			int h = child.getMeasuredHeight();
			if(h > height) height = h;
		}

		heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (noScroll)
			return false;
		else
			return super.onInterceptTouchEvent(arg0);
	}

	@Override
	public void setCurrentItem(int item, boolean smoothScroll) {
		super.setCurrentItem(item, smoothScroll);
	}

	@Override
	public void setCurrentItem(int item) {
		super.setCurrentItem(item);
	}

}