package com.example.aksy.myapplication.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.aksy.myapplication.R;
import com.example.aksy.myapplication.adapter.NetworkImageHolderView;
import com.facebook.drawee.backends.pipeline.Fresco;
import java.util.ArrayList;
import java.util.List;


/**
 * 商品详情完成
 */
public class DetailsActivity extends Activity {
    private ConvenientBanner vp_item_goods_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Fresco.initialize(DetailsActivity.this);
        setContentView(R.layout.activity_details);
        initView();
    }

    private void initView() {
        vp_item_goods_img = findViewById(R.id.vp_item_goods_img);
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        vp_item_goods_img.setPageIndicator(new int[]{R.mipmap.index_white, R.mipmap.index_red});
        vp_item_goods_img.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        setLoopView();
    }


    /**
     * 给商品轮播图设置图片路径
     */
    public void setLoopView() {
        List<String> imgUrls = new ArrayList<>();
        imgUrls.add("http://img4.hqbcdn.com/product/79/f3/79f3ef1b0b2283def1f01e12f21606d4.jpg");
        imgUrls.add("http://img14.hqbcdn.com/product/77/6c/776c63e6098f05fdc5639adc96d8d6ea.jpg");
        imgUrls.add("http://img13.hqbcdn.com/product/41/ca/41cad5139371e4eb1ce095e5f6224f4d.jpg");
        imgUrls.add("http://img10.hqbcdn.com/product/fa/ab/faab98caca326949b87b770c8080e6cf.jpg");
        imgUrls.add("http://img2.hqbcdn.com/product/6b/b8/6bb86086397a8cd0525c449f29abfaff.jpg");
        //初始化商品图片轮播
        vp_item_goods_img.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView();
            }
        }, imgUrls);
    }
}
