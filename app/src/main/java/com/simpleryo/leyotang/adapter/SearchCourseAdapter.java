package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.BusinessHomeActivty;
import com.simpleryo.leyotang.activity.CourseDetailActivity;
import com.simpleryo.leyotang.activity.CourseListActivity;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CourseListBean;
import com.simpleryo.leyotang.bean.MultipleItem;
import com.simpleryo.leyotang.bean.RecommendStoresBean;
import com.simpleryo.leyotang.viewholder.EmptyViewHolder;
import com.simpleryo.leyotang.viewholder.ExcellentCourseViewHolder;
import com.simpleryo.leyotang.viewholder.HotCourseViewHolder;
import com.simpleryo.leyotang.viewholder.RecommendViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanglei
 * @ClassNname：HomeAdapter.java
 * @Describe 首页适配器
 * @time 2018/3/27 10:54
 */

public class SearchCourseAdapter extends BaseMultiAdapter<MultipleItem> {
    Context context;
    List<CourseListBean.DataBeanX> orderListBeans=new ArrayList<>();
    List<CourseListBean.DataBeanX> excellentListBeans=new ArrayList<>();
    ArrayList<RecommendStoresBean.DataBean> recommendList=new ArrayList<>();
    View view = null;
    public SearchCourseAdapter(Context context) {
        super(context);
        this.context = context;
        EventBus.getDefault().register(this);
    }

    public ArrayList<RecommendStoresBean.DataBean> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(ArrayList<RecommendStoresBean.DataBean> recommendList) {
        this.recommendList = recommendList;
    }

    public List<CourseListBean.DataBeanX> getOrderListBeans() {
        return orderListBeans;
    }

    public void setOrderListBeans(List<CourseListBean.DataBeanX> orderListBeans) {
        this.orderListBeans = orderListBeans;
    }

    public List<CourseListBean.DataBeanX> getExcellentListBeans() {
        return excellentListBeans;
    }

    public void setExcellentListBeans(List<CourseListBean.DataBeanX> excellentListBeans) {
        this.excellentListBeans = excellentListBeans;
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==MultipleItem.InstitutionalRecommen){
            return new RecommendViewHolder(mInflater.inflate(R.layout.layout_recommend, parent, false));
        }else if (viewType == MultipleItem.HOMEHOTCOURSE) {
            return new HotCourseViewHolder(mInflater.inflate(R.layout.layout_hot_course, parent, false));
        } else if (viewType == MultipleItem.HOMEEXCELLENT) {
            return new ExcellentCourseViewHolder(mInflater.inflate(R.layout.layout_excellent_course, parent, false));
        } else {//所有都没有的情况
            return new EmptyViewHolder(mInflater.inflate(R.layout.layout_empty_item, parent, false));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        String courseId=bus.getContent();
        if (bus.getType()==33){
         EventBus.getDefault().post(new BusEntity(44,courseId));
        }
        if (bus.getType()==34){
            EventBus.getDefault().post(new BusEntity(45,courseId));
        }
    }
    @Override
    public void onBindItemHolder(final SuperViewHolder holder, int position) {
        DividerDecoration divider = new DividerDecoration.Builder(mContext)
                .setHeight(30f)
                .setColorResource(R.color.color_transparent)
                .build();
        if(holder instanceof RecommendViewHolder){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
            ((RecommendViewHolder) holder).lrecyclerview.setLayoutManager(gridLayoutManager);
            InstitutionalRecommendAdapter institutionalRecommendAdapter= new InstitutionalRecommendAdapter(mContext);
            LRecyclerViewAdapter   mLRecyclerViewAdapter = new LRecyclerViewAdapter(institutionalRecommendAdapter);
            ((RecommendViewHolder) holder).lrecyclerview.setAdapter(mLRecyclerViewAdapter);
            if (recommendList!=null&&recommendList.size()>0){
                institutionalRecommendAdapter.setDataList(recommendList);
                mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        mContext.startActivity(new Intent(mContext, BusinessHomeActivty.class).putExtra("storeId",recommendList.get(position).getStoreInfo().getId()));
                    }
                });
            }else{
                ((RecommendViewHolder) holder).lrecyclerview.setEmptyView(((RecommendViewHolder) holder).empty_view);
            }
            //是否允许嵌套滑动
            ((RecommendViewHolder) holder).lrecyclerview.setLoadMoreEnabled(false);
            ((RecommendViewHolder) holder).lrecyclerview.setPullRefreshEnabled(false);
            ((RecommendViewHolder) holder).lrecyclerview.setHasFixedSize(false);

        } else if (holder instanceof HotCourseViewHolder) {//热门课程
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            ((HotCourseViewHolder) holder).lrecyclerview.setLayoutManager(layoutManager);
            SearchHotCourseAdapter jingxuanAdapter = new SearchHotCourseAdapter(mContext);
            LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(jingxuanAdapter);
            ((HotCourseViewHolder) holder).lrecyclerview.setAdapter(lRecyclerViewAdapter);
            ((HotCourseViewHolder) holder).lrecyclerview.setPullRefreshEnabled(false);
            ((HotCourseViewHolder) holder).lrecyclerview.setLoadMoreEnabled(false);
            ((HotCourseViewHolder) holder).lrecyclerview.removeItemDecoration(divider);
            ((HotCourseViewHolder) holder).lrecyclerview.addItemDecoration(divider);
            ((HotCourseViewHolder) holder).lrecyclerview.setHasFixedSize(false);
            if (orderListBeans!=null&&orderListBeans.size()>0){
                jingxuanAdapter.setDataList(orderListBeans);
            }else{
                ((HotCourseViewHolder) holder).lrecyclerview.setEmptyView(((HotCourseViewHolder) holder).empty_view);
            }
            jingxuanAdapter.setDataList(orderListBeans);
            lRecyclerViewAdapter.notifyDataSetChanged();
            ((HotCourseViewHolder) holder).lrecyclerview.setFocusable(false);
            lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    context.startActivity(new Intent(mContext, CourseDetailActivity.class));
                }
            });
            ((HotCourseViewHolder) holder).item_more_rihgt_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(mContext, CourseListActivity.class).putExtra("type","hot"));
                }
            });

        }  else if (holder instanceof ExcellentCourseViewHolder) {//精选课程
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            ((ExcellentCourseViewHolder) holder).lrecyclerview.setLayoutManager(layoutManager);
            SearchExcellentCourseAdapter excellentCourseAdapter = new SearchExcellentCourseAdapter(mContext);
            LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(excellentCourseAdapter);
            ((ExcellentCourseViewHolder) holder).lrecyclerview.setAdapter(lRecyclerViewAdapter);
            ((ExcellentCourseViewHolder) holder).lrecyclerview.setPullRefreshEnabled(false);
            ((ExcellentCourseViewHolder) holder).lrecyclerview.setLoadMoreEnabled(false);
            ((ExcellentCourseViewHolder) holder).lrecyclerview.removeItemDecoration(divider);
            ((ExcellentCourseViewHolder) holder).lrecyclerview.addItemDecoration(divider);
            ((ExcellentCourseViewHolder) holder).lrecyclerview.setHasFixedSize(false);
            excellentCourseAdapter.setDataList(excellentListBeans);
            lRecyclerViewAdapter.notifyDataSetChanged();
            ((ExcellentCourseViewHolder) holder).lrecyclerview.setFocusable(false);
            ((ExcellentCourseViewHolder) holder).item_more_rihgt_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(mContext, CourseListActivity.class).putExtra("type","excellent"));
                }
            });
            lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    context.startActivity(new Intent(mContext, CourseDetailActivity.class));
                }
            });

        }

    }


}
