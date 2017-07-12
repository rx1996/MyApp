package com.atguigu.myapp.adapter.shop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.bean.ShopShouyeBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/11.
 */

public class ShouyeAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final List<ShopShouyeBean.DataBean.ItemsBean.ListBeanX> datas;


    public ShouyeAdapter(Context context, List<ShopShouyeBean.DataBean.ItemsBean.ListBeanX> list) {
        this.context = context;
        this.datas = list;
    }

    @Override
    public int getItemViewType(int position) {
        int home_type = datas.get(position).getHome_type();

        if (1 == home_type) {
            return 1;
        } else if (2 == home_type) {
            return 2;
        } else if (4 == home_type) {
            return 4;
        } else if (6 == home_type) {
            return 6;
        }
        return home_type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return initViewHolder(parent, viewType);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 1) {
            OneHolder oneHolder = (OneHolder) holder;
            oneHolder.setData(datas.get(position));
        } else if (getItemViewType(position) == 2) {
            TwoHolder twoHolder = (TwoHolder) holder;
            twoHolder.setData(datas.get(position));
        } else if (getItemViewType(position) == 4) {
            ThirdHolder thirdHolder = (ThirdHolder) holder;
            thirdHolder.setData(datas.get(position));
        } else if (getItemViewType(position) == 6) {
            SixHolder sixHolder = (SixHolder) holder;
            sixHolder.setData(datas.get(position));
        }
    }

    private RecyclerView.ViewHolder initViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View convertView = null;
        switch (viewType) {
            case 1:
                convertView = LayoutInflater.from(context).inflate(R.layout.item_one, parent, false);
                viewHolder = new OneHolder(convertView);
                break;
            case 2:
                convertView = LayoutInflater.from(context).inflate(R.layout.item_two, parent, false);
                viewHolder = new TwoHolder(convertView);
                break;
            case 4:
                convertView = LayoutInflater.from(context).inflate(R.layout.item_third, parent, false);
                viewHolder = new ThirdHolder(convertView);
                break;
            case 6:
                convertView = LayoutInflater.from(context).inflate(R.layout.item_six, parent, false);
                viewHolder = new SixHolder(convertView);
                break;
        }
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class OneHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image1)
        ImageView oneImage;

        public OneHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(ShopShouyeBean.DataBean.ItemsBean.ListBeanX listBeanX) {
            if (listBeanX.getOne().getPic_url() != null) {
                Glide.with(context).load(listBeanX.getOne().getPic_url())
                        .placeholder(R.drawable.ic_launcher).
                        error(R.drawable.ic_launcher).
                        diskCacheStrategy(DiskCacheStrategy.ALL).into(oneImage);
            }

        }
    }

    class TwoHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image1)
        ImageView image1;
        @Bind(R.id.image2)
        ImageView image2;

        public TwoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(ShopShouyeBean.DataBean.ItemsBean.ListBeanX listBeanX) {
            if (listBeanX.getOne().getPic_url() != null) {
                Glide.with(context).load(listBeanX.getOne().getPic_url())
                        .placeholder(R.drawable.ic_launcher).
                        error(R.drawable.ic_launcher).
                        diskCacheStrategy(DiskCacheStrategy.ALL).into(image1);
                Glide.with(context).load(listBeanX.getTwo().getPic_url())
                        .placeholder(R.drawable.ic_launcher).
                        error(R.drawable.ic_launcher).
                        diskCacheStrategy(DiskCacheStrategy.ALL).into(image2);

            }

        }
    }

    class ThirdHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image1)
        ImageView image1;
        @Bind(R.id.image2)
        ImageView image2;
        @Bind(R.id.image3)
        ImageView image3;
        @Bind(R.id.image4)
        ImageView image4;

        public ThirdHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(ShopShouyeBean.DataBean.ItemsBean.ListBeanX listBeanX) {
            if (listBeanX.getOne().getPic_url() != null) {
                Glide.with(context).load(listBeanX.getOne().getPic_url())
                        .placeholder(R.drawable.ic_launcher).
                        error(R.drawable.ic_launcher).
                        diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(image1);
                Glide.with(context).load(listBeanX.getTwo().getPic_url())
                        .placeholder(R.drawable.ic_launcher).
                        error(R.drawable.ic_launcher).
                        diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(image2);
                Glide.with(context).load(listBeanX.getThree().getPic_url())
                        .placeholder(R.drawable.ic_launcher).
                        error(R.drawable.ic_launcher).
                        diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(image3);
                Glide.with(context).load(listBeanX.getFour().getPic_url())
                        .placeholder(R.drawable.ic_launcher).
                        error(R.drawable.ic_launcher).
                        diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(image4);
            }
        }
    }

    class SixHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image1)
        ImageView image1;

        public SixHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(ShopShouyeBean.DataBean.ItemsBean.ListBeanX listBeanX) {
            Glide.with(context).load(listBeanX.getPic_url())
                    .placeholder(R.drawable.ic_launcher).
                    error(R.drawable.ic_launcher).
                    diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image1);
        }
    }
}
