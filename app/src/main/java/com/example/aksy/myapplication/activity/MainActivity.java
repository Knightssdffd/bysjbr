package com.example.aksy.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.aksy.myapplication.R;
import com.example.aksy.myapplication.fragment.HomeFragment;
import com.example.aksy.myapplication.fragment.UserDetailsFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, homeFragment).commit();
                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:
                    userDetailsFragment = new UserDetailsFragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, userDetailsFragment).commit();
                    return true;
            }
            return false;
        }
    };
    private FragmentTransaction transaction;
    private UserDetailsFragment userDetailsFragment;
    private HomeFragment homeFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();
        //开始事物
        transaction = fragmentManager.beginTransaction();
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        //将Fragment填充到布局里面
        transaction.replace(R.id.frameLayout, homeFragment).commit();
    }
}
