package com.example.leero.meizhi.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.leero.meizhi.R;
import com.example.leero.meizhi.bean.MeiZhi;

import java.util.List;

/**
 * author : Leero
 * e-mail : 925230519@qq.com
 * time  : 2018-01-24
 */
public class MeiZhiAdapter extends BaseQuickAdapter<MeiZhi.ResultsBean, BaseViewHolder> {

    private Context mContext;

    public MeiZhiAdapter(Context context, int layoutResId, List<MeiZhi.ResultsBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MeiZhi.ResultsBean item) {
        ImageView image = helper.getView(R.id.image_view);
        Glide.with(mContext).load(item.getUrl()).into(image);
    }
}
