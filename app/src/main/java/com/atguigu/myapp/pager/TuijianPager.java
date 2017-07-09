package com.atguigu.myapp.pager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.activity.ShowImageAndGifActivity;
import com.atguigu.myapp.adapter.TuijianAdapter;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.bean.TuijianBean;
import com.atguigu.myapp.fragment.ShopFenleiFragment;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.ref.PhantomReference;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/6.
 */

public class TuijianPager extends BaseFragment {


    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.tv_nomedia)
    TextView tvNomedia;

    private String url = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.6.3/0-20.json";

    private TuijianAdapter adapter;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_tuijian, null);
        ButterKnife.inject(this, view);
        //设置点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                TuijianBean.ListBean listEntity = adapter.getItem(position);
                if(listEntity !=null ){
                    //3.传递视频列表
                    Intent intent = new Intent(context,ShowImageAndGifActivity.class);
                    if(listEntity.getType().equals("gif")){
                        String url = listEntity.getGif().getImages().get(0);
                        intent.putExtra("url",url);
                        context.startActivity(intent);
                    }else if(listEntity.getType().equals("image")){
                        String url = listEntity.getImage().getBig().get(0);
                        intent.putExtra("url",url);
                        context.startActivity(intent);
                    }
                }


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
            Log.e("TAG", "请求失败==" + e.getMessage() );
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e("TAG", "请求成功==");
            processData(response);

        }
    }

    private void processData(String json) {
        //解析数据
        TuijianBean netAudioBean = new Gson().fromJson(json, TuijianBean.class);
        List<TuijianBean.ListBean> datas = netAudioBean.getList();
        String text = datas.get(0).getText();
//        Toast.makeText(context, "text=="+text, Toast.LENGTH_SHORT).show();
        if(datas != null && datas.size() >0){
            //有数据
            tvNomedia.setVisibility(View.GONE);
            //设置适配器
            adapter = new TuijianAdapter(context,datas);
            listview.setAdapter(adapter);
        }else{
            //没有数据
            tvNomedia.setVisibility(View.VISIBLE);
        }

        progressbar.setVisibility(View.GONE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
