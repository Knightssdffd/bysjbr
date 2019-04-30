package com.example.aksy.myapplication.networks;


import com.example.aksy.myapplication.base.ResultBase;
import com.example.aksy.myapplication.utils.JsonUitl;
import com.example.aksy.myapplication.utils.LogUtil;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class MyCallback implements Callback {
    @Override
    public void onFailure(Call call, IOException e) {
        LogUtil.e("网络请求错误", e.getMessage());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        ResultBase result = (ResultBase) JsonUitl.stringToObject(response.body().toString(), ResultBase.class);
        if (result.getTriumph() == Consts.REQUEST_SUCCEED) {
            congong(result.getData());
        } else {
            shibai(result.getData());
        }
    }


    public abstract void congong(String data);

    public abstract void shibai(String data);

}
