package com.atguigu.myapp.fragment.shopfenlei;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.myapp.R;
import com.atguigu.myapp.adapter.shopfenlei.JajuAdapter;
import com.atguigu.myapp.bean.shopfenlei.JiaJuBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

public class FenleiContentActivity extends AppCompatActivity {

    @Bind(R.id.ib_title_back)
    ImageButton ibTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_shopping_cart)
    ImageButton ibShoppingCart;
    @Bind(R.id.tv_screen)
    TextView tvScreen;
    @Bind(R.id.iv_screen)
    ImageView ivScreen;
    @Bind(R.id.ll_screen)
    LinearLayout llScreen;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    private String url = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0045&count=10&coverId=1&page=1&sig=3D3968703BE211CC6D931C9D4F737FB4%7C290216330933368&v=1.0";
    private JajuAdapter adapter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenlei_content);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
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
        JiaJuBean bean = new Gson().fromJson(json,JiaJuBean.class);
        adapter = new JajuAdapter(this,bean.getData().getItems());
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new GridLayoutManager(FenleiContentActivity.this,2));
    }

    @OnClick({R.id.ib_title_back, R.id.ib_shopping_cart, R.id.iv_screen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_title_back:
                finish();
                break;
            case R.id.ib_shopping_cart:
                Toast.makeText(FenleiContentActivity.this, "购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_screen:
                Toast.makeText(FenleiContentActivity.this, "筛选", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
