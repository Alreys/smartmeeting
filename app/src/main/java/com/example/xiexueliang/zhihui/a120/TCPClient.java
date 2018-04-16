package com.example.xiexueliang.zhihui.a120;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by xiexueliang on 2018/4/12.
 */

public class TCPClient {
    String string;
    public void sss(){
        new Thread(){
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("120.79.189.55",1314);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("text",string);
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(jsonObject.toString().getBytes("utf-8"));
                    outputStream.close();
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
