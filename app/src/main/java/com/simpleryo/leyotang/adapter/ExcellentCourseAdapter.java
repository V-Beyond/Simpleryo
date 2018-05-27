package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.HomeDataBean;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.viewholder.ExcellentCourseItemViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.NumberFormat;


/**
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
 * @date 2017/11/10 18:55
 */

public class ExcellentCourseAdapter extends BaseAdapter<HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean> {

    public ExcellentCourseAdapter(Context context) {
        super(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new ExcellentCourseItemViewHolder(layoutInflater.inflate(R.layout.layout_excellent_course_item, parent, false));
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean bean = listData.get(position);
        int collectCount=bean.getCollectCount();
        Picasso.with(mContext).load(bean.getCoverUrl()).transform(raduisTransformation).into(((ExcellentCourseItemViewHolder) holder).iv_collection_img);
        ((ExcellentCourseItemViewHolder) holder).tv_collection_name.setText(bean.getName());
        if(bean.getPrice()!=0){
            ((ExcellentCourseItemViewHolder) holder).tv_price.setText(XStringPars.foramtPrice(Integer.valueOf(bean.getPrice()))+"$/"+course_price);
        }else{
            if (bean.getType().equalsIgnoreCase("series")){
                ((ExcellentCourseItemViewHolder) holder).tv_price.setText("免费购买");
            }else if(bean.getType().equalsIgnoreCase("single")){
                ((ExcellentCourseItemViewHolder) holder).tv_price.setText("免费预约");
            }
        }
        ((ExcellentCourseItemViewHolder) holder).tv_popular.setText(collectCount+" people");
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        int totalCount=bean.getUpperLimit();
        float percent= (float)bean.getClassCount() / (float)totalCount*100;
        ((ExcellentCourseItemViewHolder) holder).horizontal_progressbar.setProgress((int) percent);
        ((ExcellentCourseItemViewHolder) holder).tv_percent.setText(bean.getClassCount()+"/"+totalCount);
        if (bean.isHasCollect()){
            ((ExcellentCourseItemViewHolder) holder).iv_collection_star.setImageResource(R.mipmap.iv_collection_star);
        }else{
            ((ExcellentCourseItemViewHolder) holder).iv_collection_star.setImageResource(R.mipmap.iv_collection_white_star);
        }
        ((ExcellentCourseItemViewHolder) holder).rl_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new BusEntity(121,bean));//收藏or取消收藏
            }
        });
    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
