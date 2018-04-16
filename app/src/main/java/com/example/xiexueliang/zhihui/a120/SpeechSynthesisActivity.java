package com.example.xiexueliang.zhihui.a120;
/*
* 语音合成
*/
import android.app.Activity;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.idst.nls.NlsClient;
import com.alibaba.idst.nls.NlsListener;
import com.alibaba.idst.nls.internal.protocol.NlsRequest;
import com.alibaba.idst.nls.internal.protocol.NlsRequestProto;
/**
 * Created by xiexueliang on 2018/3/22.
 */

public class SpeechSynthesisActivity extends Activity {
    private static final String TAG = "SpeechSynthesisActivity";
    private TextView UserContent;
    private Button Send_User_Content;
    private NlsClient mNlsClient;
    private NlsRequest mNlsRequest;
    private Context context;
    int iMinBufSize = AudioTrack.getMinBufferSize(16000,
            AudioFormat.CHANNEL_CONFIGURATION_MONO,
            AudioFormat.ENCODING_PCM_16BIT);
    AudioTrack audioTrack=new AudioTrack(AudioManager.STREAM_MUSIC, 16000,
            AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT,
            iMinBufSize, AudioTrack.MODE_STREAM) ; //使用audioTrack播放返回的pcm数据


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_speech_synthesis);

        UserContent = (TextView) findViewById(R.id.editText);
        Send_User_Content = (Button) findViewById(R.id.Ttsstart);
        context = getApplicationContext();
        mNlsRequest = initNlsRequest();
        String appkey = "nls-service";     //请设置简介页面的Appkey
        mNlsRequest.setApp_key(appkey);    //appkey请从 简介页面的appkey列表中获取
        mNlsRequest.initTts();               //初始化tts请求
        NlsClient.openLog(true);
        NlsClient.configure(getApplicationContext()); //全局配置
        mNlsClient = NlsClient.newInstance(context, mRecognizeListener, null ,mNlsRequest);                          //实例化NlsClient
        initTtsContentButton();


    }
    private NlsRequest initNlsRequest(){
        NlsRequestProto proto = new NlsRequestProto(context);
        //proto.setApp_user_id("xxx"); //设置用户名
        return new NlsRequest(proto);
    }

    private void initTtsContentButton(){
        Send_User_Content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_input = UserContent.getText().toString();
                if (user_input.equals("")){
                    Toast.makeText(SpeechSynthesisActivity.this, "输入不能为空！", Toast.LENGTH_LONG).show();
                }else {
                    mNlsRequest.setTtsEncodeType("pcm"); //返回语音数据格式，支持pcm,wav.alaw
                    mNlsRequest.setTtsVolume(50);   //音量大小默认50，阈值0-100
                    mNlsRequest.setTtsSpeechRate(0);//语速，阈值-500~500
                    mNlsClient.PostTtsRequest(user_input); //用户输入文本
                    mNlsRequest.authorize("LTAIqQyS2iIGjNIg", "fkxdbIhPi41Z0Qo2Kl1DeYQSpUwSCe");       //请替换为用户申请到的数加认证key和密钥
                    audioTrack.play();
                }
            }
        });
    }

    private NlsListener mRecognizeListener = new NlsListener() {
        @Override
        public void onTtsResult(int status, byte[] ttsResult){
            switch (status) {
                case NlsClient.ErrorCode.TTS_BEGIN :
                    audioTrack.play();
                    Log.e(TAG, "tts begin");
                    audioTrack.write(ttsResult, 0, ttsResult.length);
                    break;
                case NlsClient.ErrorCode.TTS_TRANSFERRING :
                    Log.e(TAG,"tts transferring"+ttsResult.length);
                    audioTrack.write(ttsResult, 0, ttsResult.length);
                    break;
                case NlsClient.ErrorCode.TTS_OVER :
                    audioTrack.stop();
                    Log.e(TAG,"tts over");
                    break;
                case NlsClient.ErrorCode.CONNECT_ERROR :
                    Toast.makeText(SpeechSynthesisActivity.this, "CONNECT ERROR", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    } ;
}



