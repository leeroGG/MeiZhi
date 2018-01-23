package com.example.leero.meizhi.base;

/**
 * author : Leero
 * e-mail : 925230519@qq.com
 * time  : 2018-01-23
 */
public interface BasePresenter<T extends BaseView> {

    // 添加View
    void attachView(T view);

    // 移除View
    void detachView();
}
