package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CourseListBean;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.viewholder.HotCourseItemViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.NumberFormat;


/**
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
 * @date 2017/11/10 18:55
 */

public class SearchHotCourseAdapter extends BaseAdapter<CourseListBean.DataBeanX> {
    public SearchHotCourseAdapter(Context context) {
        super(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new HotCourseItemViewHolder(layoutInflater.inflate(R.layout.layout_hot_course_item, parent, false));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final CourseListBean.DataBeanX bean = listData.get(position);
        if (bean.getCoverUrl() != null) {
            Picasso.with(mContext).load(bean.getCoverUrl()).transform(raduisTransformation).into(((HotCourseItemViewHolder) holder).iv_collection_img);
        } else {
            Picasso.with(mContext).load("http://p0.so.qhmsg.com/bdr/_240_/t01eb2a6c6319b04655.jpg").into(((HotCourseItemViewHolder) holder).iv_collection_img);
        }
        ((HotCourseItemViewHolder) holder).tv_collection_name.setText(bean.getName());
        if(bean.getPrice()!=0){
            ((HotCourseItemViewHolder) holder).tv_price.setText(XStringPars.foramtPrice(Integer.valueOf(bean.getPrice())) + "$/"+course_price);
        }else{
            if (bean.getType().equalsIgnoreCase("series")){
                ((HotCourseItemViewHolder) holder).tv_price.setText("免费购买");
            }else if(bean.getType().equalsIgnoreCase("single")){
                ((HotCourseItemViewHolder) holder).tv_price.setText("免费预约");
            }
        }

        int collectCount=bean.getCollectCount();

        ((HotCourseItemViewHolder) holder).tv_popular.setText(collectCount+ " people");
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        int totalCount=bean.getUpperLimit();
        float percent= (float)bean.getClassCount() / (float)totalCount*100;
        ((HotCourseItemViewHolder) holder).horizontal_progressbar.setProgress((int) percent);
        ((HotCourseItemViewHolder) holder).tv_percent.setText(bean.getClassCount()+"/"+totalCount);
        if (bean.isHasCollect()) {
            ((HotCourseItemViewHolder) holder).iv_collection_star.setImageResource(R.mipmap.iv_collection_star);
        } else {
            ((HotCourseItemViewHolder) holder).iv_collection_star.setImageResource(R.mipmap.iv_collection_white_star);
        }
        if (bean.getRecommends().get(0).getValue().equalsIgnoreCase("HOT")){
            ((HotCourseItemViewHolder) holder).rl_collect.setBackgroundResource(R.mipmap.iv_collection_bg);
        }
       else if (bean.getRecommends().get(0).getValue().equalsIgnoreCase("EXCELLENT")){
            ((HotCourseItemViewHolder) holder).rl_collect.setBackgroundResource(R.mipmap.iv_collection_blue);
        }
        else if (bean.getRecommends().get(0).getValue().equalsIgnoreCase("OFFCIAL")){
            ((HotCourseItemViewHolder) holder).rl_collect.setBackgroundResource(R.mipmap.iv_collection_ping);
        }
        ((HotCourseItemViewHolder) holder).rl_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new BusEntity(33, bean.getId(), bean.isHasCollect()));//取消收藏
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
