package com.example.leero.meizhi.model;

import android.util.Log;

import com.example.leero.meizhi.bean.MeiZhi;
import com.example.leero.meizhi.http.Apis;
import com.example.leero.meizhi.http.OKHttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * author : Leero
 * e-mail : 925230519@qq.com
 * time  : 2018-01-24
 */
public class MeiZhiModelImpl implements MeiZhiModel {
    @Override
    public void getMeiZhi(int per, int page, final OnRequestListener listener) {
        OKHttpUtils.create(Apis.class).getMMs(per, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<MeiZhi>() {
                    @Override
                    public void onNext(@NonNull MeiZhi meiZhi) {
                        listener.onSuccess(meiZhi);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("onResponse", "Error --- " + e.getMessage());
                        listener.onError("网络异常，请稍后尝试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
