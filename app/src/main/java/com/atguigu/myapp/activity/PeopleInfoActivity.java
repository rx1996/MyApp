package com.atguigu.myapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.myapp.R;
import com.atguigu.myapp.adapter.daren.LikeAdapter;
import com.atguigu.myapp.adapter.shop.FenleiAdapter;
import com.atguigu.myapp.bean.TuijianBean;
import com.atguigu.myapp.bean.daren.ItemBean;
import com.atguigu.myapp.bean.daren.LikeBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static android.R.attr.id;

public class PeopleInfoActivity extends AppCompatActivity {

    @Bind(R.id.iv_people_image)
    ImageView ivPeopleImage;
    @Bind(R.id.tv_people_name)
    TextView tvPeopleName;
    @Bind(R.id.tv_people_work)
    TextView tvPeopleWork;
    @Bind(R.id.ll_jianjie)
    LinearLayout llJianjie;
    @Bind(R.id.bt_letter)
    Button btLetter;
    @Bind(R.id.bt_follow)
    Button btFollow;
    @Bind(R.id.rl_xinxi)
    RelativeLayout rlXinxi;
    @Bind(R.id.tv_like_num)
    TextView tvLikeNum;
    @Bind(R.id.ll_like)
    LinearLayout llLike;
    @Bind(R.id.tv_recommend_num)
    TextView tvRecommendNum;
    @Bind(R.id.ll_recommend)
    LinearLayout llRecommend;
    @Bind(R.id.tv_guanzhu_num)
    TextView tvGuanzhuNum;
    @Bind(R.id.ll_guanzhu)
    LinearLayout llGuanzhu;
    @Bind(R.id.tv_fans_num)
    TextView tvFansNum;
    @Bind(R.id.ll_fans)
    LinearLayout llFans;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;
    @Bind(R.id.activity_people_info)
    RelativeLayout activityPeopleInfo;
    @Bind(R.id.gv)
    GridView gv;
    private String id;

    private LikeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_info);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("uid");
        String url = "http://mobile.iliangcang.com/user/masterFollow?app_key=Android&count=12&owner_id="+id+"&page=1&sig=5715DFAE35D85EA29846D090DBBF8753%7C557744010558468&v=1.0";
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
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
        ItemBean bean = new Gson().fromJson(json, ItemBean.class);
        tvPeopleName.setText(bean.getData().getItems().getUser_name());
        tvPeopleWork.setText(bean.getData().getItems().getUser_desc());
        Glide.with(this)
                .load(bean.getData().getItems().getUser_image().getOrig())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivPeopleImage);
        tvLikeNum.setText(bean.getData().getItems().getLike_count());
        tvRecommendNum.setText(bean.getData().getItems().getRecommendation_count());
        tvGuanzhuNum.setText(bean.getData().getItems().getFollowing_count());
        tvFansNum.setText(bean.getData().getItems().getFollowed_count());
    }

    @OnClick({R.id.ll_like, R.id.ll_recommend, R.id.ll_guanzhu, R.id.ll_fans})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_like:
//                Toast.makeText(PeopleInfoActivity.this, "喜欢", Toast.LENGTH_SHORT).show();
                String likeUrl = "http://mobile.iliangcang.com/user/masterLike?app_key=Android&count=10&owner_id=85&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
                OkHttpUtils.get().url(likeUrl).build().execute(new LikeCallback());
                break;
            case R.id.ll_recommend:
//                Toast.makeText(PeopleInfoActivity.this, "推荐", Toast.LENGTH_SHORT).show();
                String TuijianUrl = "http://mobile.iliangcang.com/user/masterListInfo?app_key=Android&count=10&owner_id=85&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
                OkHttpUtils.get().url(TuijianUrl).build().execute(new LikeCallback());
                break;
            case R.id.ll_guanzhu:
                Toast.makeText(PeopleInfoActivity.this, "关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_fans:
                Toast.makeText(PeopleInfoActivity.this, "粉丝", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private
    class LikeCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("TAG", "请求失败==" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e("TAG", "请求成功==");
            processData1(response);

        }
    }

    private void processData1(String json) {
        LikeBean bean = new Gson().fromJson(json, LikeBean.class);
        adapter = new LikeAdapter(this, bean.getData().getItems().getGoods());
        gv.setAdapter(adapter);
    }
}
