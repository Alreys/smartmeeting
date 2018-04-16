package com.example.xiexueliang.zhihui.a120;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiexueliang on 2018/3/22.
 */

public class InputDialog extends AlertDialog implements View.OnClickListener {
    private OnEditInputFinishedListener mListener; //接口
    private EditText name,data,remark;  //编辑框
    private Button btnConfrim, btnCancel;  //确定取消按钮


    public interface OnEditInputFinishedListener{
        void editInputFinished(String Name,String Data,String Remark);
    }

    protected InputDialog(Context context, OnEditInputFinishedListener mListener) {
        super(context);
        this.mListener = mListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filename);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());


        //控件
        name = (EditText)findViewById(R.id.nameedit);
        btnConfrim = (Button)findViewById(R.id.dailogyes);
        btnCancel = (Button)findViewById(R.id.dailogno);
        data = findViewById(R.id.dateedit);
        remark = findViewById(R.id.remarkedit);
        btnConfrim.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        data.setText(simpleDateFormat.format(curDate));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dailogyes) {
            //确定
            if (mListener != null) {
                String Name = name.getText().toString();
                String Data = data.getText().toString();
                String Remark = remark.getText().toString();
                mListener.editInputFinished(Name,Data,Remark);
                //data.setText(Name + Data + Remark);
            }
            dismiss();
        }else {
            //取消
            dismiss();
        }
    }

}