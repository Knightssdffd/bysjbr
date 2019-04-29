package com.example.aksy.myapplication.base;

import com.example.aksy.myapplication.utils.JsonUitl;

public class UserBase {

    private String username;
    private String password;

    public UserBase(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getJson() {
        return JsonUitl.objectToString(this);
    }



    @Override
    public String toString() {
        return "UserBase{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
