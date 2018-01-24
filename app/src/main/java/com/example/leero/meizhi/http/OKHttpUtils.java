package com.example.leero.meizhi.http;

import android.util.Log;

import com.example.leero.meizhi.base.App;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : Leero
 * e-mail : 925230519@qq.com
 * time  : 2018-01-23
 */
public class OKHttpUtils {

    private static OkHttpClient mOkHttpClient;
    private static Retrofit mRetrofit;

    public static Retrofit getInstance() {
        if (mRetrofit == null) {
            synchronized (OKHttpUtils.class) {
                if (mRetrofit == null) {
                    initOkHttpClient();
                    initRetrofit();
                }
            }
        }
        return mRetrofit;
    }

    private static void initOkHttpClient() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 缓存文件夹
        File cacheFile = new File(App.getInstance().getExternalCacheDir().toString(), "cache");
        // 缓存大小为10M
        int cacheSize = 10 * 1024 * 1024;
        // 创建缓存对象
        Cache cache = new Cache(cacheFile, cacheSize);

        mOkHttpClient = new OkHttpClient.Builder()
                // 设置请求读写的超时时间
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                // 添加拦截器
                .addInterceptor(logInterceptor)
                .cache(cache)
                .build();
    }

    private static void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                //设置服务器路径
                .baseUrl(Urls.API_SERVER)
                //添加转化库，默认是Gson
                .addConverterFactory(GsonConverterFactory.create())
                //添加回调库，采用RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //设置使用OkHttp网络请求
                .client(mOkHttpClient)
                .build();
    }

    public static <T> T create(Class<T> service) {
        return getInstance().create(service);
    }

    /**
     * 日志拦截器
     */
    private static class HttpLogger implements HttpLoggingInterceptor.Logger {

        @Override
        public void log(String message) {
            Log.d("onResponse", message);
        }
    }
}
