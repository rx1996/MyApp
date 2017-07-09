package com.atguigu.myapp;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.x;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/6/12.
 */

public class MyApplication extends Application {
    public static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        x.Ext.init(this);
        initOkhttpUtils();

    }
    //得到全局的上下文
    public static MyApplication getContext(){
        return instance;
    }

    private void initOkhttpUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(5000L, TimeUnit.MILLISECONDS)
                .readTimeout(5000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
