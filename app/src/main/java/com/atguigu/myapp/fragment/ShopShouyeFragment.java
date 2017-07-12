package com.atguigu.myapp.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.atguigu.myapp.R;
import com.atguigu.myapp.adapter.shop.ShouyeAdapter;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.bean.ShopShouyeBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/7.
 */

public class ShopShouyeFragment extends BaseFragment {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    String url="http://mobile.iliangcang.com/goods/newShopHome?app_key=Android&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";
    private List<ShopShouyeBean.DataBean.ItemsBean.ListBeanX> datas;
    private ShouyeAdapter adapater;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_shouye, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet(url);
    }

    private void getDataFromNet(final String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "图组请求失败==" + e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "图组请求成功==");
                        processData(response);

                    }


                });
    }

    private void processData(String json) {
        ShopShouyeBean bean = new Gson().fromJson(json, ShopShouyeBean.class);
        adapater = new ShouyeAdapter(context,bean.getData().getItems().getList());
        recyclerview.setAdapter(adapater);
        recyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
