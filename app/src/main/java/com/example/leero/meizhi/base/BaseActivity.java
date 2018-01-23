package com.example.leero.meizhi.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * author : Leero
 * e-mail : 925230519@qq.com
 * time  : 2018-01-23
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView{

    protected T mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initPresenter();
        initVariable();
        initView(savedInstanceState);
        loadData();

        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
//        App.getInstance().addActivity(this);
        // 设置竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    protected void initVariable() {}

    protected abstract void initView(Bundle state);

    protected void loadData(){}
}
