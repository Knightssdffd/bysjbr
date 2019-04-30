package com.example.aksy.myapplication.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.aksy.myapplication.R;
import com.example.aksy.myapplication.adapter.HomeAdapter;
import com.example.aksy.myapplication.base.CommodityBase;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class HomeFragment extends BaseFragment {
    private List<CommodityBase> commodityList = new ArrayList<>();
    private boolean isErr = true;
    private RecyclerView recyclerList;
    private HomeAdapter homeAdapter;
    private int mCurrentCounter;

    @Override
    public View initView() {
        initCommodity();
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        recyclerList = view.findViewById(R.id.home_recycler);
        return view;
    }

    @Override
    public void initData() {
        recyclerList.setLayoutManager(new GridLayoutManager(mActivity, 2));
        homeAdapter = new HomeAdapter(R.layout.home_view_item, commodityList);
        recyclerList.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mActivity, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });

        homeAdapter.openLoadAnimation();
        homeAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        homeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mCurrentCounter >= 200) {
                            //数据全部加载完毕
                            homeAdapter.loadMoreEnd();
                        } else {
                            if (isErr) {
                                initCommodity();
                                //成功获取更多数据
                                homeAdapter.addData(commodityList);
                                mCurrentCounter = homeAdapter.getData().size();
                                homeAdapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = true;
                                Toast.makeText(mActivity, "更多数据失败", Toast.LENGTH_LONG).show();
                                homeAdapter.loadMoreFail();
                            }
                        }
                    }
                }, 10);
            }
        }, recyclerList);
    }


    private void initCommodity() {
//        commodityList.clear();
        for (int i = 0; i < 20; i++) {
            commodityList.add(new CommodityBase("aaaaaaaaaa", R.drawable.menu1));
        }
    }
}
