package com.example.leero.meizhi.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.example.leero.meizhi.R;
import com.example.leero.meizhi.base.BaseActivity;
import com.example.leero.meizhi.widget.PinchImageView;

import butterknife.BindView;
import butterknife.OnClick;

public class PhotoActivity extends BaseActivity {

    @BindView(R.id.photo)
    PinchImageView photo;

    private String url;

    public static Intent newInstance(Context context, String url) {
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra("url", url);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initVariable() {
        url = getIntent().getStringExtra("url");
    }

    @Override
    protected void initView(Bundle state) {
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (!TextUtils.isEmpty(url)) {
            Glide.with(this).load(url).into(photo);
        }
    }

    @OnClick(R.id.photo)
    public void onViewClicked() {
        finish();
    }
}
