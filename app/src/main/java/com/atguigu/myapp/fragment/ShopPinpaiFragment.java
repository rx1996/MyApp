package com.atguigu.myapp.fragment;

import android.drm.ProcessedData;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.adapter.shop.PinpaiAdapter;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.bean.ShopPinpaiBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/7.
 */

public class ShopPinpaiFragment extends BaseFragment {

    @Bind(R.id.lv_pinpai)
    ListView lvPinpai;
    private String url = "http://mobile.iliangcang.com/brand/brandList?app_key=Android&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    private PinpaiAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_pinpai, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        OkHttpUtils.get().url(url).build().execute(new MyStringCallback());
    }
    class MyStringCallback extends StringCallback{

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
    private void processData(String json){
        ShopPinpaiBean bean = new Gson().fromJson(json,ShopPinpaiBean.class);
        adapter = new PinpaiAdapter(context,bean.getData().getItems());
        lvPinpai.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
