package com.example.aksy.myapplication.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import com.example.aksy.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_blank, null);

        return view;
    }

    @Override
    public void initData() {

    }

}
