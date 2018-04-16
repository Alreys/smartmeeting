package com.example.xiexueliang.zhihui.a120;
/*
* 个人中心
*/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by xiexueliang on 2018/3/22.
 */
public class PersonCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);
        getSupportActionBar().hide();//隐藏标题

    }
}
