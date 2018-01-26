package com.example.leero.meizhi.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.leero.meizhi.R;
import com.example.leero.meizhi.bean.MeiZhi;

/**
 * author : Leero
 * e-mail : 925230519@qq.com
 * time  : 2018-01-24
 */
public class MeiZhiAdapter extends BaseQuickAdapter<MeiZhi.ResultsBean, BaseViewHolder> {

    private Context mContext;

    public MeiZhiAdapter(Context context, int layoutResId) {
        super(layoutResId);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MeiZhi.ResultsBean item) {
        ImageView image = helper.getView(R.id.image_view);

        // 设置图片的随机高度
        ViewGroup.LayoutParams params = image.getLayoutParams();
        params.height = (int) (400 + Math.random() * 650);
        image.setLayoutParams(params);

        Glide.with(mContext).load(item.getUrl()).into(image);
    }
}
