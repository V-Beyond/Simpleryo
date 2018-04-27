package com.simpleryo.leyotang.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.simpleryo.leyotang.R;
import com.zhouwei.mzbanner.MZBannerView;

/**
 * Created by 77429 on 2018/4/3.
 */

public class ArcViewPager extends RelativeLayout {

    public  MZBannerView  mViewPager;

    public ArcViewPager(Context context) {
        this(context, null);
    }

    public ArcViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ArcCoverView mArcCoverView = new ArcCoverView(getContext());
        mArcCoverView.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 30));
        mViewPager= (MZBannerView) LayoutInflater.from(context).inflate(R.layout.layout_mz_bannerview,null,false);
        addView(mViewPager);    //添加 ViewPager
        addView(mArcCoverView); //添加弧形遮挡 View
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //遍历子 View,将弧形遮挡 View 放在底部
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof ArcCoverView) {
                ArcCoverView arcCoverView = (ArcCoverView) getChildAt(i);
                arcCoverView.layout(0, getMeasuredHeight() - arcCoverView.getMeasuredHeight(), arcCoverView.getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }


    /**
     * Description:弧形遮挡 View
     * Date:2018/1/22
     */
    public class ArcCoverView extends View {

        private Paint mPaint;
        private Path mPath;
        private int width;
        private int height;

        public ArcCoverView(Context context) {
            this(context, null);
        }

        public ArcCoverView(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public ArcCoverView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            width = context.getResources().getDisplayMetrics().widthPixels;
            height = 30;
            mPaint = new Paint();
            mPaint.setColor(Color.parseColor("#C7E9FF"));
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setAntiAlias(true);
            mPath = new Path();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mPath.moveTo(0, 0);
            mPath.quadTo(width / 2, height * 2, width, 0);
            mPath.lineTo(width, height);
            mPath.lineTo(0, height);
            canvas.drawPath(mPath, mPaint);
        }
    }
}
