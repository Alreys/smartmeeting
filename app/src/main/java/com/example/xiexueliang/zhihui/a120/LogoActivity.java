package com.example.xiexueliang.zhihui.a120;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by xiexueliang on 2018/3/22.
 */

public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        getSupportActionBar().hide();//隐藏标题
        enterHome();

    }
    private void enterHome(){
        Timer time = new Timer();
        TimerTask tk = new TimerTask() {
            Intent intent = new Intent(LogoActivity.this,SpeechRecognitionActivity.class);
            @Override
            public void run() {
                // TODO Auto-generated method stub

                startActivity(intent);
                finish();
            }
        };
        time.schedule(tk, 1500);

    }
}
