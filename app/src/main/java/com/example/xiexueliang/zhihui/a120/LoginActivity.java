package com.example.xiexueliang.zhihui.a120;
/*
* 登陆
*/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by xiexueliang on 2018/3/22.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText Accounts,Password;
    private Button logoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();//隐藏标题
        logoin = findViewById(R.id.login);
        Accounts = findViewById(R.id.accounts);
        Password = findViewById(R.id.password);
        logoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (Accounts.getText().toString() == "123456"&&Password.getText().toString() == "0000"){
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
               // }
            }
        });

    }
}
