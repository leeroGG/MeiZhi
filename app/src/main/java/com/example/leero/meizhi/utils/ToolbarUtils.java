package com.example.leero.meizhi.utils;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.leero.meizhi.R;

/**
 * Created by 94002_000 on 2017/3/2.
 */

public class ToolbarUtils {
  private static ToolbarUtils helper = new ToolbarUtils();

  public static ToolbarUtils getInstance() {
      return helper;
  }

  public void setToolbar(Fragment fragment, String title) {
    setToolbar(fragment, title, false);
  }

  public void setToolbar(AppCompatActivity activity, String title) {
    setToolbar(activity, title, false);
  }

  public void setToolbar(AppCompatActivity activity, String title, boolean back) {
    Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
    setToolbar(activity, toolbar, title, back);
  }

  public void setToolbar(Fragment fragment, String title, boolean back) {
    Toolbar toolbar = (Toolbar) fragment.getView().findViewById(R.id.toolbar);
    AppCompatActivity activity = (AppCompatActivity) fragment.getActivity();
    fragment.setHasOptionsMenu(true);
    setToolbar(activity, toolbar, title, back);
  }

  private void setToolbar(final AppCompatActivity activity, Toolbar toolbar, String title, boolean back) {
    TextView mTvTitle = (TextView) toolbar.findViewById(R.id.tv_title);
    mTvTitle.setText(title);

    activity.setSupportActionBar(toolbar);
    ActionBar actionBar= activity.getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayShowTitleEnabled(false);
    }

    if (back) {
      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          activity.onBackPressed();
        }
      });
    }else {
      toolbar.setNavigationIcon(null);
    }
  }

}
