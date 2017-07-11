package com.atguigu.myapp.pager;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.myapp.R;
import com.atguigu.myapp.adapter.DuanziAdapter;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.bean.DuanziBean;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/6.
 */

public class DuanziPager extends BaseFragment {


//    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.tv_nomedia)
    TextView tvNomedia;
    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView pullRefreshList;

    private String url = "http://s.budejie.com/topic/tag-topic/64/hot/budejie-android-6.6.3/0-20.json";
    private DuanziAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.pager_duanzi, null);
        ButterKnife.bind(this, view);
        listview = pullRefreshList.getRefreshableView();
        //设置下拉和上拉刷新的监听
        pullRefreshList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "下拉刷新==");
                OkHttpUtils.get().url(url).build().execute(new MyStringCallback());

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

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
            //结束下来刷新
            pullRefreshList.onRefreshComplete();

        }
    }

    private void processData(String json) {
        //解析数据
        DuanziBean netAudioBean = new Gson().fromJson(json, DuanziBean.class);
        List<DuanziBean.ListBean> datas = netAudioBean.getList();
        String text = datas.get(0).getText();
//        Toast.makeText(context, "text=="+text, Toast.LENGTH_SHORT).show();
        if (datas != null && datas.size() > 0) {
            //有数据
            tvNomedia.setVisibility(View.GONE);
            //设置适配器
            adapter = new DuanziAdapter(context, datas);
            listview.setAdapter(adapter);
        } else {
            //没有数据
            tvNomedia.setVisibility(View.VISIBLE);
        }

        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
