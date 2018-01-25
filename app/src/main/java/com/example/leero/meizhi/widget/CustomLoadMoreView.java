package com.example.leero.meizhi.widget;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.example.leero.meizhi.R;

/**
 * author : Leero
 * e-mail : 925230519@qq.com
 * time  : 2018-01-25
 */
public final class CustomLoadMoreView extends LoadMoreView {

    @Override public int getLayoutId() {
        return R.layout.view_load_more;
    }


    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return 0;
    }

}