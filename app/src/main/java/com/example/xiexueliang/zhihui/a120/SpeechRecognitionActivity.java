package com.example.xiexueliang.zhihui.a120;
/*
* 语音识别
*/

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.idst.nls.NlsClient;
import com.alibaba.idst.nls.NlsListener;
import com.alibaba.idst.nls.StageListener;
import com.alibaba.idst.nls.internal.protocol.NlsRequest;
import com.alibaba.idst.nls.internal.protocol.NlsRequestASR;

/**
 * Created by xiexueliang on 2018/3/22.
 */

/**
 * github上传测试注释*/
public class SpeechRecognitionActivity extends Activity {
    private Button startbutton;
    private Button stopbutton;
    private Button textsave;
    private TextView resqulttext;
    private Context context;
    private NlsClient mNlsClient;
    private NlsRequest mNlsRequest;
    private String id ;
    private String secret;
    private String appKey;
    private boolean isRecognizing = false;
    JSONObject jsonObject2;        TCPClient tcpClient = new TCPClient();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_recognition);
        context = getApplicationContext();
        startbutton = findViewById(R.id.startbutton);
        stopbutton = findViewById(R.id.stopbutton);
        resqulttext = findViewById(R.id.requesttext);
        textsave = findViewById(R.id.textsave);
        mNlsRequest = new NlsRequest();
        appKey = "nls-service"; //参考文档
        id = "LTAIqQyS2iIGjNIg";
        secret = "fkxdbIhPi41Z0Qo2Kl1DeYQSpUwSCe";//请替换为用户申请到的数加认证Access Key和Access Srcret，见上方文档
        mNlsRequest.setApp_key(appKey);
        //mNlsRequest.setAsr_sc("opu");//设置语音格式
        mNlsRequest.setAsrResposeMode(NlsRequestASR.mode.STREAMING);
        NlsClient.openLog(true);
        NlsClient.configure(getApplicationContext());
        mNlsClient = NlsClient.newInstance(this,mRecognizeListener,mStageListener,mNlsRequest);
        mNlsClient.setMaxRecordTime(60000);  //设置最长语音
        mNlsClient.setMaxStallTime(1000);    //设置最短语音
        mNlsClient.setMinRecordTime(500);    //设置最大录音中断时间
        mNlsClient.setRecordAutoStop(false);  //设置VAD
        mNlsClient.setMinVoiceValueInterval(100); //设置音量回调时长
        initStartRecognizing();
        initStopRecognizing();
        textsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputdialog();
            }
        });
    }
    private NlsListener mRecognizeListener = new NlsListener(){
        @Override
        public void onRecognizingResult(int status, RecognizedResult result) {//语音识别文本回调
            switch (status) {
                case NlsClient.ErrorCode.SUCCESS:
                    if (result != null) {
                        jsonObject2 = JSON.parseObject(JSON.parseObject(result.results).get("asr_out").toString());
                        if (mNlsClient.isStarted()==false)
                        {
                            tcpClient.setString(resqulttext.getText().toString());
                            tcpClient.sss();
                            mNlsClient.start();

                        }
                        resqulttext.setText(jsonObject2.get("result").toString());
                    }
                    break;
                case NlsClient.ErrorCode.RECOGNIZE_ERROR:
                    Toast.makeText(SpeechRecognitionActivity.this, "recognizer error", Toast.LENGTH_LONG).show();
                    break;
                case NlsClient.ErrorCode.RECORDING_ERROR:
                    Toast.makeText(SpeechRecognitionActivity.this,"recording error", Toast.LENGTH_LONG).show();
                    break;
                case NlsClient.ErrorCode.NOTHING:
                    Toast.makeText(SpeechRecognitionActivity.this,"nothing", Toast.LENGTH_LONG).show();
                    break;
            }
            isRecognizing = false;
        }
    };


    private void initStartRecognizing(){
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRecognizing = true;
                mNlsRequest.authorize(id,secret);
                mNlsClient.start();
                resqulttext.setEnabled(false);
                startbutton.setText("录音中...");
            }
        });

    }

    private void initStopRecognizing(){
        stopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRecognizing = false;
                mNlsClient.stop();
                resqulttext.setEnabled(true);
//                tcpClient.setString(resqulttext.getText().toString());
//                tcpClient.sss();
                startbutton.setText("开始 录音");
            }
        });
    }

    private StageListener mStageListener = new StageListener() {
        @Override
        public void onStartRecognizing(NlsClient recognizer){//识别开始的回调
            super.onStartRecognizing(recognizer);   //To change body of overridden methods use File | Settings | File Templates.
        }
        @Override
        public void onStopRecognizing(NlsClient recognizer) {//识别结束的回调
            super.onStopRecognizing(recognizer);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void onStartRecording(NlsClient recognizer) {//录音开始的回调
            super.onStartRecording(recognizer);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void onStopRecording(NlsClient recognizer) {//录音结束的回调
            //super.onStopRecording(recognizer);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void onVoiceVolume(int volume) {//音量大小回调
            super.onVoiceVolume(volume);
        }
    };

    private void inputdialog(){
        InputDialog dialog = new InputDialog(SpeechRecognitionActivity.this, new InputDialog.OnEditInputFinishedListener() {
            @Override
            public void editInputFinished(String Name,String Data,String Remark) {
                Toast.makeText(SpeechRecognitionActivity.this, Data, Toast.LENGTH_LONG).show();

            }
        });
        dialog.setView(new EditText(SpeechRecognitionActivity.this));
        dialog.show();

    }
}


















