package com.example.aksy.myapplication.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aksy.myapplication.R;
import com.example.aksy.myapplication.view.RoundImageView;

/**
 * 用户
 * 详细资料
 */
public class UserDetailsFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_user_details, null);
        View viewInfo = view.findViewById(R.id.details_info);
        //头像
        RoundImageView userTitlePic = viewInfo.findViewById(R.id.user_title_pic);
        //nickname
        TextView nickName = viewInfo.findViewById(R.id.nick_name);
        //晋趣号
        TextView yyuserId = viewInfo.findViewById(R.id.yyuser_id);
        //个性签名
        TextView userAutograph = viewInfo.findViewById(R.id.user_autograph);
        //编辑
        LinearLayout compileLL = viewInfo.findViewById(R.id.compile);
        return view;
    }

    @Override
    public void initData() {


    }


}
