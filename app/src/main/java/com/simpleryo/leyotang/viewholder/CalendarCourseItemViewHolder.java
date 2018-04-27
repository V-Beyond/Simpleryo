package com.simpleryo.leyotang.viewholder;

import android.view.View;
import android.widget.TextView;

import com.simpleryo.leyotang.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 77429 on 2017/11/10.
 */

public class CalendarCourseItemViewHolder extends SuperViewHolder {
    @ViewInject(R.id.tv_course_name)
    public TextView tv_course_name;
    @ViewInject(R.id.tv_course_more)
    public TextView tv_course_more;
    public CalendarCourseItemViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this, itemView);
    }
}
