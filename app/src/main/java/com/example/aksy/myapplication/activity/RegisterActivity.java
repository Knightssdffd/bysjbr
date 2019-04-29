package com.example.aksy.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.aksy.myapplication.R;
import com.example.aksy.myapplication.base.UserBase;
import com.example.aksy.myapplication.networks.Consts;
import com.example.aksy.myapplication.networks.HttpUtil;
import com.example.aksy.myapplication.networks.MyCallback;
import com.example.aksy.myapplication.utils.LogUtil;
import com.example.aksy.myapplication.utils.ToastUtil;
import com.example.aksy.myapplication.utils.Util;
import com.solidfire.gson.Gson;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private Button registerBtn, cancelBtn;
    private EditText accountText, pwdText, confirmPwdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        initView();
    }

    void initView() {
        back = findViewById(R.id.back);
        registerBtn = findViewById(R.id.register_confirm);
        cancelBtn = findViewById(R.id.register_cancel);

        accountText = findViewById(R.id.register_account);
        pwdText = findViewById(R.id.register_pwd);
        confirmPwdText = findViewById(R.id.register_pwd_confirm);

        back.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
            case R.id.register_cancel:
                finish();
                break;
            case R.id.register_confirm:
                register();
                break;
        }
    }

    private void register() {
        // 前端参数校验，防SQL注入
        String account = Util.StringHandle(accountText.getText().toString());
        String pwd = Util.StringHandle(pwdText.getText().toString());
        String pwd_confirm = Util.StringHandle(confirmPwdText.getText().toString());
        // 检查数据格式是否正确
        String resMsg = checkDataValid(account, pwd, pwd_confirm);

        if (!resMsg.equals("")) {
            ToastUtil.showShort(RegisterActivity.this, resMsg);
            return;
        }

        String strUser = new UserBase(account, pwd).getJson();
        LogUtil.d("strUser", strUser);

        HttpUtil.sendPost(Consts.URL_Register, strUser, new MyCallback() {
            @Override
            public void congong(String data) {
//                ToastUtil.showToast1(RegisterActivity.this, data);
                autoStartActivity(MainActivity.class);
                finish();
            }

            @Override
            public void shibai(String data) {
                ToastUtil.showToast1(RegisterActivity.this, data);
            }
        });
    }

    private String checkDataValid(String account, String pwd, String pwd_confirm) {
        if (TextUtils.isEmpty(account) | TextUtils.isEmpty(pwd) | TextUtils.isEmpty(pwd_confirm))
            return getResources().getString(R.string.null_hint);
        if (!pwd.equals(pwd_confirm))
            return getResources().getString(R.string.not_equal_hint);
        return "";
    }


}
