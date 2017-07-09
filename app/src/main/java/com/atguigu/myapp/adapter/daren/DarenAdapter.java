package com.atguigu.myapp.adapter.daren;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.adapter.shop.FenleiAdapter;
import com.atguigu.myapp.bean.daren.ShouyeBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/7.
 */

public class DarenAdapter extends BaseAdapter {
    private final Context context;
    private final List<ShouyeBean.DataBean.ItemsBean> items;

    public DarenAdapter(Context context, List<ShouyeBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_daren, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShouyeBean.DataBean.ItemsBean bean = items.get(position);
        viewHolder.tvDarenName.setText(bean.getUsername());
        viewHolder.tvDarenContent.setText(bean.getDuty());
        Glide.with(context)
                .load(bean.getUser_images().getOrig())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivDarenImage);
        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.iv_daren_image)
        ImageView ivDarenImage;
        @InjectView(R.id.tv_daren_name)
        TextView tvDarenName;
        @InjectView(R.id.tv_daren_content)
        TextView tvDarenContent;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
