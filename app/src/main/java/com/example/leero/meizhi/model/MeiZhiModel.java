package com.example.leero.meizhi.model;

import com.example.leero.meizhi.bean.MeiZhi;

/**
 * author : Leero
 * e-mail : 925230519@qq.com
 * time  : 2018-01-23
 */
public interface MeiZhiModel {

    interface OnRequestListener {
        void onSuccess(MeiZhi data);
        void onError(String msg);
    }

    void getMeiZhi(int per, int page, OnRequestListener listener);
}
