package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.AllCourseActivity;
import com.simpleryo.leyotang.activity.BusinessHomeActivty;
import com.simpleryo.leyotang.activity.CouponsActivity;
import com.simpleryo.leyotang.activity.CourseDetailActivity;
import com.simpleryo.leyotang.activity.CourseFilterActivity;
import com.simpleryo.leyotang.activity.CourseListActivity;
import com.simpleryo.leyotang.activity.CourseSearchActivity;
import com.simpleryo.leyotang.activity.MyNoticeActivity;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.HomeDataBean;
import com.simpleryo.leyotang.bean.MultipleItem;
import com.simpleryo.leyotang.viewholder.BannerViewHolder;
import com.simpleryo.leyotang.viewholder.CourseTypeViewHolder;
import com.simpleryo.leyotang.viewholder.EmptyViewHolder;
import com.simpleryo.leyotang.viewholder.ExcellentCourseViewHolder;
import com.simpleryo.leyotang.viewholder.HotCourseViewHolder;
import com.simpleryo.leyotang.viewholder.IntroductoryCourseViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

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

public class HomeAdapter extends BaseMultiAdapter<MultipleItem> {
    Context context;
    List<HomeDataBean.DataBeanX.BannersBean> images = new ArrayList<>();
    List<HomeDataBean.DataBeanX.CourseTypesBean> courseTypetBeans = new ArrayList<>();
    HomeDataBean.DataBeanX.CoursesBeanX introductoryListBeans = new HomeDataBean.DataBeanX.CoursesBeanX();
    HomeDataBean.DataBeanX.CoursesBeanX orderListBeans = new HomeDataBean.DataBeanX.CoursesBeanX();
    HomeDataBean.DataBeanX.CoursesBeanX excellentListBeans = new HomeDataBean.DataBeanX.CoursesBeanX();
    View view = null;
    DividerDecoration divider;
    public HomeAdapter(Context context) {
        super(context);
        this.context = context;
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        divider= new DividerDecoration.Builder(mContext)
                .setHeight(30f)
                .setColorResource(R.color.color_transparent)
                .build();
    }

    public List<HomeDataBean.DataBeanX.CourseTypesBean> getCourseTypetBeans() {
        return courseTypetBeans;
    }

    public void setCourseTypetBeans(List<HomeDataBean.DataBeanX.CourseTypesBean> courseTypetBeans) {
        this.courseTypetBeans = courseTypetBeans;
    }

    public List<HomeDataBean.DataBeanX.BannersBean> getImages() {
        return images;
    }

    public void setImages(List<HomeDataBean.DataBeanX.BannersBean> images) {
        if (this.images != null && this.images.size() > 0) {
            this.images.clear();
        }
        this.images.addAll(images);
    }

    public HomeDataBean.DataBeanX.CoursesBeanX getIntroductoryListBeans() {
        return introductoryListBeans;
    }

    public void setIntroductoryListBeans(HomeDataBean.DataBeanX.CoursesBeanX introductoryListBeans) {
        this.introductoryListBeans = introductoryListBeans;
    }

    public HomeDataBean.DataBeanX.CoursesBeanX getOrderListBeans() {
        return orderListBeans;
    }

    public void setOrderListBeans(HomeDataBean.DataBeanX.CoursesBeanX orderListBeans) {
        this.orderListBeans = orderListBeans;
    }

    public HomeDataBean.DataBeanX.CoursesBeanX getExcellentListBeans() {
        return excellentListBeans;
    }

    public void setExcellentListBeans(HomeDataBean.DataBeanX.CoursesBeanX excellentListBeans) {
        this.excellentListBeans = excellentListBeans;
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MultipleItem.HOMEBANNER) {
            return new BannerViewHolder(mInflater.inflate(R.layout.layout_banner, parent, false));
        } else if (viewType == MultipleItem.HOMECOURSETYPE) {
            return new CourseTypeViewHolder(mInflater.inflate(R.layout.layout_course_type, parent, false));
        } else if (viewType == MultipleItem.HOMEINTRODUCTORYCOURSE) {
            return new IntroductoryCourseViewHolder(mInflater.inflate(R.layout.layout_introductory_course, parent, false));
        } else if (viewType == MultipleItem.HOMEHOTCOURSE) {
            return new HotCourseViewHolder(mInflater.inflate(R.layout.layout_hot_course, parent, false));
        } else if (viewType == MultipleItem.HOMEEXCELLENT) {
            return new ExcellentCourseViewHolder(mInflater.inflate(R.layout.layout_excellent_course, parent, false));
        } else {//所有都没有的情况
            return new EmptyViewHolder(mInflater.inflate(R.layout.layout_empty_item, parent, false));
        }
    }


    @Override
    public void onBindItemHolder(final SuperViewHolder holder, int position) {
        final MultipleItem bean;
        bean = mDataList.get(position);
        if (holder instanceof IntroductoryCourseViewHolder) {//入门课程
            ((IntroductoryCourseViewHolder) holder).textView.setText(introductoryListBeans.getTag().getName());
            ((IntroductoryCourseViewHolder) holder).lrecyclerview.removeItemDecoration(divider);
            ((IntroductoryCourseViewHolder) holder).lrecyclerview.addItemDecoration(divider);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            ((IntroductoryCourseViewHolder) holder).lrecyclerview.setLayoutManager(layoutManager);
            IntroductoryCourseAdapter jingxuanAdapter = new IntroductoryCourseAdapter(mContext);
            LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(jingxuanAdapter);
            ((IntroductoryCourseViewHolder) holder).lrecyclerview.setAdapter(lRecyclerViewAdapter);
            ((IntroductoryCourseViewHolder) holder).lrecyclerview.setPullRefreshEnabled(false);
            ((IntroductoryCourseViewHolder) holder).lrecyclerview.setLoadMoreEnabled(false);
            ((IntroductoryCourseViewHolder) holder).lrecyclerview.setHasFixedSize(false);
            if (introductoryListBeans != null && introductoryListBeans.getCourses().size() > 0) {
                jingxuanAdapter.setDataList(introductoryListBeans.getCourses());
            } else {
                ((IntroductoryCourseViewHolder) holder).lrecyclerview.setEmptyView(((IntroductoryCourseViewHolder) holder).empty_view);
            }
            lRecyclerViewAdapter.notifyDataSetChanged();
            ((IntroductoryCourseViewHolder) holder).lrecyclerview.setFocusable(false);
            lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    context.startActivity(new Intent(mContext, CourseDetailActivity.class).putExtra("courseId", introductoryListBeans.getCourses().get(position).getId()));
                }
            });
            ((IntroductoryCourseViewHolder) holder).item_more_rihgt_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(mContext, CourseFilterActivity.class).putExtra("type", "introductory").putExtra("tagId3",introductoryListBeans.getTag().getId()));
                }
            });

        } else if (holder instanceof HotCourseViewHolder) {//热门课程
            ((HotCourseViewHolder) holder).textView.setText(orderListBeans.getTag().getName());
            ((HotCourseViewHolder) holder).lrecyclerview.removeItemDecoration(divider);
            ((HotCourseViewHolder) holder).lrecyclerview.addItemDecoration(divider);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            ((HotCourseViewHolder) holder).lrecyclerview.setLayoutManager(layoutManager);
            HotCourseAdapter jingxuanAdapter = new HotCourseAdapter(mContext);
            LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(jingxuanAdapter);
            ((HotCourseViewHolder) holder).lrecyclerview.setAdapter(lRecyclerViewAdapter);
            ((HotCourseViewHolder) holder).lrecyclerview.setPullRefreshEnabled(false);
            ((HotCourseViewHolder) holder).lrecyclerview.setLoadMoreEnabled(false);
            ((HotCourseViewHolder) holder).lrecyclerview.setHasFixedSize(false);
            if (orderListBeans != null && orderListBeans.getCourses().size() > 0) {
                jingxuanAdapter.setDataList(orderListBeans.getCourses());
            } else {
                ((HotCourseViewHolder) holder).lrecyclerview.setEmptyView(((HotCourseViewHolder) holder).empty_view);
            }

            lRecyclerViewAdapter.notifyDataSetChanged();
            ((HotCourseViewHolder) holder).lrecyclerview.setFocusable(false);
            lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    context.startActivity(new Intent(mContext, CourseDetailActivity.class).putExtra("courseId", orderListBeans.getCourses().get(position).getId()));
                }
            });
            ((HotCourseViewHolder) holder).item_more_rihgt_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(mContext, CourseFilterActivity.class).putExtra("type", "hot").putExtra("tagId3",orderListBeans.getTag().getId()));
                }
            });
        } else if (holder instanceof ExcellentCourseViewHolder) {//精选课程
            ((ExcellentCourseViewHolder) holder).textView.setText(excellentListBeans.getTag().getName());
            ((ExcellentCourseViewHolder) holder).lrecyclerview.removeItemDecoration(divider);
            ((ExcellentCourseViewHolder) holder).lrecyclerview.addItemDecoration(divider);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            ((ExcellentCourseViewHolder) holder).lrecyclerview.setLayoutManager(layoutManager);
            ExcellentCourseAdapter excellentCourseAdapter = new ExcellentCourseAdapter(mContext);
            LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(excellentCourseAdapter);
            ((ExcellentCourseViewHolder) holder).lrecyclerview.setAdapter(lRecyclerViewAdapter);
            ((ExcellentCourseViewHolder) holder).lrecyclerview.setPullRefreshEnabled(false);
            ((ExcellentCourseViewHolder) holder).lrecyclerview.setLoadMoreEnabled(false);
            ((ExcellentCourseViewHolder) holder).lrecyclerview.setHasFixedSize(false);
            if (excellentListBeans != null && excellentListBeans.getCourses().size() > 0) {
                excellentCourseAdapter.setDataList(excellentListBeans.getCourses());
            } else {
                ((ExcellentCourseViewHolder) holder).lrecyclerview.setEmptyView(((ExcellentCourseViewHolder) holder).empty_view);
            }

            lRecyclerViewAdapter.notifyDataSetChanged();
            ((ExcellentCourseViewHolder) holder).lrecyclerview.setFocusable(false);
            ((ExcellentCourseViewHolder) holder).item_more_rihgt_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(mContext, CourseFilterActivity.class).putExtra("type", "excellent").putExtra("tagId3",excellentListBeans.getTag().getId()));
                }
            });
            lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    context.startActivity(new Intent(mContext, CourseDetailActivity.class).putExtra("courseId", excellentListBeans.getCourses().get(position).getId()));
                }
            });

        } else if (holder instanceof CourseTypeViewHolder) {
            ((CourseTypeViewHolder) holder).lrecyclerview.setNestedScrollingEnabled(false);
            ((CourseTypeViewHolder) holder).lrecyclerview.removeItemDecoration(divider);
            ((CourseTypeViewHolder) holder).lrecyclerview.addItemDecoration(divider);
            GridLayoutManager layoutManager = new GridLayoutManager(mContext, 4);
            ((CourseTypeViewHolder) holder).lrecyclerview.setLayoutManager(layoutManager);
            CourseTypeAdapter courseTypeAdapter = new CourseTypeAdapter(mContext);
            LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(courseTypeAdapter);
            ((CourseTypeViewHolder) holder).lrecyclerview.setAdapter(lRecyclerViewAdapter);
            ((CourseTypeViewHolder) holder).lrecyclerview.setPullRefreshEnabled(false);
            ((CourseTypeViewHolder) holder).lrecyclerview.setLoadMoreEnabled(false);
            courseTypeAdapter.setDataList(courseTypetBeans);
            lRecyclerViewAdapter.notifyDataSetChanged();
            ((CourseTypeViewHolder) holder).lrecyclerview.setHasFixedSize(false);
            ((CourseTypeViewHolder) holder).lrecyclerview.setFocusable(false);
            lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if (position == 7) {
                        Intent intent = new Intent(mContext, AllCourseActivity.class);
                        context.startActivity(intent);

                    } else {
                        Intent intent = new Intent(mContext, CourseListActivity.class);
                        intent.putExtra("type", courseTypetBeans.get(position).getName());
                        intent.putExtra("tagId1", courseTypetBeans.get(position).getId());
                        context.startActivity(intent);
                    }

                }
            });
            ((CourseTypeViewHolder) holder).iv_coupons_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, CouponsActivity.class));
                }
            });
            Picasso.with(mContext).load("https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/2f229263ec0d1914dac90b73ff0abfc8").into(((CourseTypeViewHolder) holder).iv_coupons_img);
        } else if (holder instanceof BannerViewHolder) {
            ((BannerViewHolder) holder).arcViewPager.mViewPager.setPages(images, new MZHolderCreator<BannerItemViewHolder>() {
                @Override
                public BannerItemViewHolder createViewHolder() {
                    return new BannerItemViewHolder();
                }
            });
            ((BannerViewHolder) holder).arcViewPager.mViewPager.start();
            ((BannerViewHolder) holder).arcViewPager.mViewPager.setIndicatorRes(R.drawable.indicator_normal, R.drawable.indicator_selected);
            ((BannerViewHolder) holder).rl_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, CourseSearchActivity.class));
                }
            });
        }

    }

    public class BannerItemViewHolder implements MZViewHolder<HomeDataBean.DataBeanX.BannersBean> {
        private ImageView iv_viewpager_item;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.viewpager_home_item, null);
            iv_viewpager_item = view.findViewById(R.id.iv_viewpager_item);
            return view;
        }

        @Override
        public void onBind(Context context, int position, final HomeDataBean.DataBeanX.BannersBean data) {
            // 数据绑定
            Picasso.with(mContext).load(data.getImageUrl()).into(iv_viewpager_item);
            iv_viewpager_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (data.getCod().equalsIgnoreCase("STORE")) {
                        mContext.startActivity(new Intent(mContext, BusinessHomeActivty.class).putExtra("storeId", data.getRefId()));
                    } else if (data.getCod().equalsIgnoreCase("COURSE")) {
                        mContext.startActivity(new Intent(mContext, CourseDetailActivity.class).putExtra("courseId", data.getRefId()));
                    } else if (data.getCod().equalsIgnoreCase("NOTICE")) {
                        mContext.startActivity(new Intent(mContext, MyNoticeActivity.class).putExtra("msg_id", data.getRefId()));
                    }
                }
            });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void BusMain(BusEntity bus) {
        if (bus.getType() == 121) {
            HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean coursesBean = bus.getCoursesBean();
            EventBus.getDefault().post(new BusEntity(122, coursesBean));
        }
    }
}
