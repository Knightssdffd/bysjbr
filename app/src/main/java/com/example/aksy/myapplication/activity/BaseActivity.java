package com.example.aksy.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.aksy.myapplication.utils.ActivityController;
import com.example.aksy.myapplication.utils.LogUtil;

public class BaseActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("BaseActivity", getClass().getSimpleName());
        ActivityController.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    void autoStartActivity(Class T) {
        Intent intent = new Intent(this, T);
        startActivity(intent);
    }
}
