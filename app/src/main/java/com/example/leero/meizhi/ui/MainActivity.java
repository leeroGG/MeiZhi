package com.example.leero.meizhi.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.leero.meizhi.R;
import com.example.leero.meizhi.adapter.MeiZhiAdapter;
import com.example.leero.meizhi.base.BaseActivity;
import com.example.leero.meizhi.bean.MeiZhi;
import com.example.leero.meizhi.presenter.MeiZhiPresenterImpl;
import com.example.leero.meizhi.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MeiZhiPresenterImpl>
        implements MainView, NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private MeiZhiAdapter mAdapter;
    private List<MeiZhi.ResultsBean> dataList;
    private int page; // 页数
    private int per; // 每页数据量

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MeiZhiPresenterImpl();
    }

    @Override
    protected void initVariable() {
        page = 1;
        per = 30;
        dataList = new ArrayList<>();
        mAdapter = new MeiZhiAdapter(this, R.layout.item_meizhi, dataList);
    }

    @Override
    protected void initView(Bundle state) {
        // 标题设置
        addToolbar("Girls", false);

        // recyclerView 设置
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);

        // 上下拉设置
        refreshLayout.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, recyclerView);

        // 侧滑菜单打开后主内容区域设置没有阴影覆盖
//        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(drawerListener);
        navigationView.setNavigationItemSelectedListener(this);

        // 浮动按钮点击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "刷新可以下拉，这里的功能先放着", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void loadData() {
        Log.d("111", "page---" + page);
        mPresenter.getMeiZhiData(per, page);
    }

    @Override
    public void getGirlsFinished(MeiZhi meiZhi) {
        if (!meiZhi.isError()) {
            dataList.addAll(meiZhi.getResults());
            mAdapter.loadMoreComplete();

            if (meiZhi.getResults().size() < per) {
                mAdapter.loadMoreEnd();
            }
            mAdapter.notifyDataSetChanged();
        }

        refreshLayout.setRefreshing(false);
        Snackbar.make(fab, "数据加载成功", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    public void onRefresh() {
        page = 1;
        dataList.clear();
        refreshLayout.setRefreshing(true);
        loadData();

        Log.d("111", "11111111111111111");
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        loadData();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
            default:
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showError(String msg) {
        Snackbar.make(fab, "数据加载错误，请稍后尝试", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    // 设置监听让主页面跟着滑动
    private DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            // 得到contentView
            View content = drawerLayout.getChildAt(0);
            int offset = (int) (drawerView.getWidth() * slideOffset);
            content.setTranslationX(offset);
        }

        @Override
        public void onDrawerOpened(View drawerView) {

        }

        @Override
        public void onDrawerClosed(View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };
}
