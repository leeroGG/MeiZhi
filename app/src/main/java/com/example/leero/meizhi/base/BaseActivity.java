package com.example.leero.meizhi.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.leero.meizhi.utils.ToolbarUtils;

import butterknife.ButterKnife;

/**
 * author : Leero
 * e-mail : 925230519@qq.com
 * time  : 2018-01-23
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected T mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        initVariable();
        initView(savedInstanceState);
        loadData();

        // 设置竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 添加布局
     */
    protected abstract int getLayoutId();

    /***
     * 初始化Presenter
     */
    protected abstract void initPresenter();

    /**
     * 初始化页面参数
     */
    protected void initVariable() {}

    /**
     * 页面初始化
     */
    protected abstract void initView(Bundle state);

    /**
     * 获取数据
     */
    protected void loadData(){}

    @Override
    public void showLoading(String msg) {}

    @Override
    public void showError(String msg) {}

    @Override
    public void hideLoading() {}

    /**
     * 添加标题栏
     * @param title 标题
     * @param back 是否可以返回
     */
    protected void addToolbar(String title, boolean back) {
        ToolbarUtils.getInstance().setToolbar(this, title, back);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }
}
