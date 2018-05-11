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
            Picasso.with(mContext).load(bean.getCoverUrl()).into(((HotCourseItemViewHolder) holder).iv_collection_img);
        } else {
            Picasso.with(mContext).load("http://p0.so.qhmsg.com/bdr/_240_/t01eb2a6c6319b04655.jpg").into(((HotCourseItemViewHolder) holder).iv_collection_img);
        }
        ((HotCourseItemViewHolder) holder).tv_collection_name.setText(bean.getName());
        ((HotCourseItemViewHolder) holder).tv_price.setText(XStringPars.foramtPrice(Integer.valueOf(bean.getPrice())) + "$/hour");
        int collectCount=bean.getCollectCount();

        ((HotCourseItemViewHolder) holder).tv_popular.setText(collectCount+ " people");
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        int totalCount=100;
        if (collectCount>=1&&collectCount<=99){
            totalCount=100;
        }
        if (collectCount>=100&&collectCount<=999){
            totalCount=1000;
        }
        if (collectCount>=1000&&collectCount<=9999){
            totalCount=1000;
        }
        float percent= (float)collectCount / (float) totalCount * 100;
        ((HotCourseItemViewHolder) holder).horizontal_progressbar.setProgress((int) percent);
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
