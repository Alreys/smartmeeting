package com.example.xiexueliang.zhihui.a120;
/*
*主界面
*/

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * Created by xiexueliang on 2018/3/22.
 */

public class MainActivity extends AppCompatActivity {
    private Button speechRecognition;
    private Button speechSynthesis;
    private Button myDocument;
    private Button personCenter;
    private TextView ceshi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//隐藏标题
        speechRecognition = findViewById(R.id.speech_recognition);
        speechSynthesis = findViewById(R.id.speech_synthesis);
        myDocument = findViewById(R.id.my_document);
        personCenter = findViewById(R.id.person_center);
        ceshi = findViewById(R.id.ceshi);
        speechRecognition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SpeechRecognitionActivity.class);
                startActivity(intent);

            }

        });
        speechSynthesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SpeechSynthesisActivity.class);
                startActivity(intent);

            }
        });
        myDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyDocumentActivity.class);
                startActivity(intent);
            }
        });
        personCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PersonCenterActivity.class);
                startActivity(intent);
            }
        });
        Build build = new Build();
    }

//    private void inputdialog(final String type){
//        InputDialog dialog = new InputDialog(MainActivity.this, new InputDialog.OnEditInputFinishedListener() {
//            @Override
//            public void editInputFinished(String Name) {
//                ceshi.setText(Name);
//                if (type == "SpeechRecognitionActivity") {
//                    Intent intent = new Intent(MainActivity.this, SpeechRecognitionActivity.class);
//                    startActivity(intent);
//                }else {
//                    Intent intent = new Intent(MainActivity.this,SpeechSynthesisActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });
//        dialog.setView(new EditText(MainActivity.this));
//        dialog.show();
//
//    }

}
