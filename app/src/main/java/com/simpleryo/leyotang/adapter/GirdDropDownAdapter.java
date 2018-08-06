package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.TagsListBean;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;


public class GirdDropDownAdapter extends BaseAdapter {

    private Context context;
    private List<TagsListBean.DataBean> list;
    private int checkItemPosition = 0;
    private int selectedPosition;
    int type;
    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }

    public GirdDropDownAdapter(Context context, List<TagsListBean.DataBean> list, int type) {
        this.context = context;
        this.list = list;
        this.type=type;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_drop_down, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        if(position==selectedPosition) {
            convertView.setActivated(true);
        }else{
            convertView.setActivated(false);
        }
        fillValue(position, viewHolder);
        return convertView;
    }

    private void fillValue(int position, ViewHolder viewHolder) {
        viewHolder.mText.setText(list.get(position).getName());
        if (type==1){
            if (checkItemPosition != -1) {
                if (checkItemPosition == position) {
                    viewHolder.mText.setTextColor(context.getResources().getColor(R.color.drop_down_selected));
                    viewHolder.mText.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.mipmap.drop_down_checked), null);
                } else {
                    viewHolder.mText.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));
                    viewHolder.mText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                }
            }
        }else{
          if (checkItemPosition==position){
              viewHolder.root_layout.setBackgroundColor(Color.parseColor("#ffffff"));
          }else {
              viewHolder.root_layout.setBackgroundColor(Color.parseColor("#F0F0F0"));
          }
        }
    }
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        this.notifyDataSetChanged();
    }
    static class ViewHolder {
        @ViewInject(R.id.text)
        TextView mText;
        @ViewInject(R.id.root_layout)
        LinearLayout root_layout;

        ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
