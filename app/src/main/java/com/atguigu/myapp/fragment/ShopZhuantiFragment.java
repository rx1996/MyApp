package com.atguigu.myapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.adapter.shop.ZhuantiAdapter;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.bean.ShopZhuantiBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/7.
 */

public class ShopZhuantiFragment extends BaseFragment {

    @Bind(R.id.lv_zhuanti)
    ListView lvZhuanti;
    private String url = "http://mobile.iliangcang.com/goods/shopSpecial?app_key=Android&count=10&page=1&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";
    private ZhuantiAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_zhuanti, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        OkHttpUtils.get().url(url).build().execute(new MyStringCallback());
    }
    class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("TAG", "请求失败==" + e.getMessage() );
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e("TAG", "请求成功==");
            processData(response);

        }
    }
    private void processData(String json) {
        ShopZhuantiBean bean = new Gson().fromJson(json,ShopZhuantiBean.class);
        adapter = new ZhuantiAdapter(context,bean.getData().getItems());
        lvZhuanti.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
