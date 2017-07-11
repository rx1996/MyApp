package com.atguigu.myapp.adapter.shop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.bean.ShopFenleiBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/7.
 */

public class FenleiAdapter extends BaseAdapter {
    private final Context context;
    private final List<ShopFenleiBean.DataBean.ItemsBean> items;

    public FenleiAdapter(Context context, List<ShopFenleiBean.DataBean.ItemsBean> items) {
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
            convertView = View.inflate(context, R.layout.item_fenlei, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShopFenleiBean.DataBean.ItemsBean bean = items.get(position);
        Glide.with(context)
                .load(bean.getCover_img())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivFenleiImage);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_fenlei_image)
        ImageView ivFenleiImage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
