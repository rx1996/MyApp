package com.atguigu.myapp.fragment;

import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.bean.PinpaiShangpinBean;
import com.atguigu.myapp.bean.daren.ShouyeBean;
import com.atguigu.myapp.pager.DarenPager;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFileBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/13.
 */

public class PinpaiGushiFragment extends BaseFragment {

    @Bind(R.id.tv)
    TextView tv;

    private String url;
    private Bundle bundle;
    private String id;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_gushi, null);
        ButterKnife.bind(this, view);
        bundle = this.getArguments();
        id = bundle.getString("id");
        Log.e("TAG", "bundle=="+id);
        url = "http://mobile.iliangcang.com/brand/brandShopList?app_key=Android&brand_id="+id+"&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
        Log.e("TAG", "url=="+url);
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
            Log.e("TAG", "请求失败==" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e("TAG", "请求成功==");
            processData(response);

        }
    }

    private void processData(String json) {
        PinpaiShangpinBean bean = new Gson().fromJson(json, PinpaiShangpinBean.class);
        tv.setText(bean.getData().getItems().get(0).getBrand_info().getBrand_desc());
        Log.e("TAG", "id=="+bean.getData().getItems().get(0).getBrand_info().getBrand_desc());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
