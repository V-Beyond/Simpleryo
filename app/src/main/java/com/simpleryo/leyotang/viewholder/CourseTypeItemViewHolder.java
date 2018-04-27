package com.simpleryo.leyotang.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simpleryo.leyotang.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 77429 on 2018/3/25.
 */

public class CourseTypeItemViewHolder extends SuperViewHolder {
    @ViewInject(R.id.iv_course_type)
  public  ImageView iv_course_type;
    @ViewInject(R.id.tv_course_type)
    public  TextView tv_course_type;
    public CourseTypeItemViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this,itemView);
    }
}
