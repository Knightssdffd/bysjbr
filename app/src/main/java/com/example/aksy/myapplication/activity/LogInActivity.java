package com.example.aksy.myapplication.activity;

import android.content.SharedPreferences;
import android.os.UserManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.aksy.myapplication.R;
import com.example.aksy.myapplication.base.UserBase;
import com.example.aksy.myapplication.networks.Consts;
import com.example.aksy.myapplication.networks.HttpUtil;
import com.example.aksy.myapplication.networks.MyCallback;
import com.example.aksy.myapplication.utils.LogUtil;
import com.example.aksy.myapplication.utils.SharedPreferencesUtil;
import com.example.aksy.myapplication.utils.ToastUtil;
import com.example.aksy.myapplication.utils.Util;
import com.solidfire.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LogInActivity extends BaseActivity implements View.OnClickListener {
    private ProgressBar progressBar;
    private Button loginBtn, registerBtn;
    private EditText accountText, passwordText;
    private CheckBox isRememberPwd, isAutoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_log_in);

        initView();
        // 自动填充
        SharedPreferencesUtil spu = new SharedPreferencesUtil(this);
        Boolean isRemember = (Boolean) spu.getParam("isRememberPwd", false);
        Boolean isAutoLogin = (Boolean) spu.getParam("isAutoLogin", false);
        // SharedPreference获取用户账号密码，存在则填充
        String account = (String) spu.getParam("account", "");
        String pwd = (String) spu.getParam("pwd", "");
        if (!account.equals("") && !pwd.equals("")) {
            if (isRemember) {
                accountText.setText(account);
                passwordText.setText(pwd);
                isRememberPwd.setChecked(true);
            }
            if (isAutoLogin)
                Login();
        }

    }


    private void initView() {
        loginBtn = findViewById(R.id.login);
        registerBtn = findViewById(R.id.register);
        accountText = findViewById(R.id.account);
        passwordText = findViewById(R.id.password);
        isRememberPwd = findViewById(R.id.login_remember);
        isAutoLogin = findViewById(R.id.login_auto);
        progressBar = findViewById(R.id.progressbar);

        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                autoStartActivity(RegisterActivity.class);
                break;
            case R.id.login:
                Login();
                break;
        }
    }


    /**
     * POST方式Login
     */
    private void Login() {
        // 前端参数校验，防SQL注入
        String account = Util.StringHandle(accountText.getText().toString());
        String password = Util.StringHandle(passwordText.getText().toString());
        // 检查数据格式是否正确
        String resMsg = checkDataValid(account, password);
        if (!resMsg.equals("")) {
            ToastUtil.showShort(LogInActivity.this, resMsg);
            return;
        }
        OptionHandle(account, password);// 处理自动登录及记住密码

        progressBar.setVisibility(View.VISIBLE);// 显示进度条

        String strUser = new UserBase(account, password).getJson();
        LogUtil.d("strUser", strUser);


        HttpUtil.sendPost(Consts.URL_Login, strUser, new MyCallback() {
            @Override
            public void congong(String data) {
                autoStartActivity(MainActivity.class);
            }

            @Override
            public void shibai(String data) {
                ToastUtil.showToast1(LogInActivity.this, data);
            }
        });


    }


    @NonNull
    private String checkDataValid(String account, String pwd) {
        if (TextUtils.isEmpty(account) | TextUtils.isEmpty(pwd))
            return getResources().getString(R.string.null_hint);
        return "";
    }


    private void OptionHandle(String account, String pwd) {
        SharedPreferences.Editor editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();
        SharedPreferencesUtil spu = new SharedPreferencesUtil(this);
        if (isRememberPwd.isChecked()) {
            editor.putBoolean("isRememberPwd", true);
            // 保存账号密码
            spu.setParam("account", account);
            spu.setParam("pwd", pwd);
        } else {
            editor.putBoolean("isRememberPwd", false);
        }
        if (isAutoLogin.isChecked()) {
            editor.putBoolean("isAutoLogin", true);
        } else {
            editor.putBoolean("isAutoLogin", false);
        }
        editor.apply();
    }


}
