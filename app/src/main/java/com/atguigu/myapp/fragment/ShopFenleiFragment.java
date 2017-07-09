package com.atguigu.myapp.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.adapter.shop.FenleiAdapter;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.bean.ShopFenleiBean;
import com.atguigu.myapp.fragment.shopfenlei.FenleiContentActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/7.
 */

public class ShopFenleiFragment extends BaseFragment {

    private String url = "http://mobile.iliangcang.com/goods/goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    @InjectView(R.id.gv)
    GridView gv;

    private FenleiAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_fenlei, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        OkHttpUtils.get().url(url).build().execute(new MyStringCallback());
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(context,FenleiContentActivity.class));
            }
        });
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
        ShopFenleiBean bean = new Gson().fromJson(json,ShopFenleiBean.class);
        adapter = new FenleiAdapter(context,bean.getData().getItems());
        gv.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
