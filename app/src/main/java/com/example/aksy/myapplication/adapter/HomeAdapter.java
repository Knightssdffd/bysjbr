package com.example.aksy.myapplication.adapter;


import android.support.annotation.Nullable;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.aksy.myapplication.R;
import com.example.aksy.myapplication.base.CommodityBase;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<CommodityBase, BaseViewHolder> {
    public HomeAdapter(int layoutResId, @Nullable List<CommodityBase> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityBase item) {
        helper.setImageResource(R.id.material_item_img, item.getImageId());
        Log.e("aaaaa", item.toString());
//        Glide.with(mContext).load(item.getImageId()).into(helper.getView(R.id.material_item_img));
    }
}
