package com.example.leero.meizhi.base;

/**
 * author : Leero
 * e-mail : 925230519@qq.com
 * time  : 2018-01-23
 */
public interface BaseView {

    // 显示加载窗口
    void showLoading(String msg);

    // 隐藏加载窗口
    void hideLoading();

    // 显示出错信息
    void showError(String msg);

}
