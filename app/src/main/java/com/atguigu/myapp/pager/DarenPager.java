package com.atguigu.myapp.pager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.myapp.R;
import com.atguigu.myapp.adapter.daren.DarenAdapter;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.bean.daren.ShouyeBean;
import com.atguigu.myapp.fragment.ShopFenleiFragment;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/6.
 */

public class DarenPager extends BaseFragment {

    @InjectView(R.id.gv)
    GridView gv;
    @InjectView(R.id.ib_title_search)
    ImageButton ibTitleSearch;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_daren_fenlei)
    ImageButton ibDarenFenlei;

    private DarenAdapter adapter;

    private String url = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&page=1&sig=BF287AF953103F390674E73DDA18CFD8|639843030233268&v=1.0";
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.pager_daren, null);
        ButterKnife.inject(this, view);
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
        ShouyeBean bean = new Gson().fromJson(json,ShouyeBean.class);
        adapter = new DarenAdapter(context,bean.getData().getItems());
        gv.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.ib_title_search, R.id.ib_daren_fenlei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_title_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_daren_fenlei:
                Toast.makeText(context, "分类", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
