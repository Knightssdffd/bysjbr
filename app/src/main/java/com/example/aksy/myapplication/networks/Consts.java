package com.example.aksy.myapplication.networks;


/**
 * 常量
 */
public class Consts {
    private static final String URL = "http://192.168.0.115:8080/";
    //登录
    public static String URL_Login = URL + "DouyinDemo/user/login.action";
    // 注册
    public static String URL_Register = URL + "DouyinDemo/user/register.action";

    //好友关注
    public static final String URL_Attent = URL + "selectFansList";


    //请求成功
    public static final int REQUEST_SUCCEED = 200;
    //请求失败
    public static final int REQUEST_ERROR = 100;


}
