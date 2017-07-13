package com.atguigu.myapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.adapter.PinpaiChanpinAdapter;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.bean.PinpaiShangpinBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/13.
 */

public class PinpaiChanpinFragment extends BaseFragment {

    @Bind(R.id.gv)
    GridView gv;
    private String url;
    private Bundle bundle;
    private String id;
    private PinpaiChanpinAdapter adapter;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_chanpin, null);
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
        adapter = new PinpaiChanpinAdapter(context,bean.getData().getItems());
        gv.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
