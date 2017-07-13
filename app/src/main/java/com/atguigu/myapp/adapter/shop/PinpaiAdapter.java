package com.atguigu.myapp.adapter.shop;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.activity.PinpaiActivity;
import com.atguigu.myapp.bean.ShopPinpaiBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/7.
 */

public class PinpaiAdapter extends BaseAdapter {

    private final Context context;
    private final List<ShopPinpaiBean.DataBean.ItemsBean> items;


    public PinpaiAdapter(Context context, List<ShopPinpaiBean.DataBean.ItemsBean> items) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_pinpai, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShopPinpaiBean.DataBean.ItemsBean bean = items.get(position);
        viewHolder.tvName.setText(bean.getBrand_name());
        Glide.with(context)
                .load(bean.getBrand_logo())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivIcon);

        viewHolder.llPinpai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PinpaiActivity.class);
                intent.putExtra("image",items.get(position).getBrand_logo());
                intent.putExtra("name",items.get(position).getBrand_name());
                intent.putExtra("id",items.get(position).getBrand_id());
                context.startActivity(intent);
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.iv_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.ll_pinpai)
        LinearLayout llPinpai;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
