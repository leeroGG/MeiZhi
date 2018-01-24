package com.example.leero.meizhi.presenter;

import com.example.leero.meizhi.base.BasePresenter;
import com.example.leero.meizhi.ui.MainView;

/**
 * author : Leero
 * e-mail : 925230519@qq.com
 * time  : 2018-01-24
 */
public interface MeiZhiPresenter extends BasePresenter<MainView> {

    void getMeiZhiData(int per, int page);
}
