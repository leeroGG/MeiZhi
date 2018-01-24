package com.example.leero.meizhi.presenter;

import com.example.leero.meizhi.bean.MeiZhi;
import com.example.leero.meizhi.model.MeiZhiModel;
import com.example.leero.meizhi.model.MeiZhiModelImpl;
import com.example.leero.meizhi.ui.MainView;

/**
 * author : Leero
 * e-mail : 925230519@qq.com
 * time  : 2018-01-24
 */
public class MeiZhiPresenterImpl implements MeiZhiPresenter, MeiZhiModel.OnRequestListener {

    private MeiZhiModel mModel;
    private MainView mView;

    public MeiZhiPresenterImpl() {
        mModel = new MeiZhiModelImpl();
    }

    @Override
    public void attachView(MainView view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onSuccess(MeiZhi data) {
        if (mView != null) {
            mView.getGirlsFinished(data);
        }
    }

    @Override
    public void onError(String msg) {
        if (mView != null) {
            mView.showError(msg);
        }
    }

    @Override
    public void getMeiZhiData(int per, int page) {
        if (mView != null) {
            mModel.getMeiZhi(per, page, this);
        }
    }
}
