package com.atguigu.myapp.adapter.shop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.bean.ShopZhuantiBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/7.
 */

public class ZhuantiAdapter extends BaseAdapter {
    private final Context context;
    private final List<ShopZhuantiBean.DataBean.ItemsBean> items;


    public ZhuantiAdapter(Context context, List<ShopZhuantiBean.DataBean.ItemsBean> items) {
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
            convertView = View.inflate(context, R.layout.item_zhuanti, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShopZhuantiBean.DataBean.ItemsBean bean = items.get(position);
        viewHolder.tvName.setText(bean.getTopic_name());
        Glide.with(context)
                .load(bean.getCover_img())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivZhuantiImage);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_zhuanti_image)
        ImageView ivZhuantiImage;
        @Bind(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
