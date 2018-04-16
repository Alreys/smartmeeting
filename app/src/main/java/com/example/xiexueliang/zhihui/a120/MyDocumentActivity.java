package com.example.xiexueliang.zhihui.a120;
/*
* 我的文档
*/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by xiexueliang on 2018/3/22.
 */

public class MyDocumentActivity extends AppCompatActivity {
    private String[] names = new String[]{"语音文件1","语音文件2","文本文件1","文本文件2"};
    private String[] dates = new String[]{"2018.3.22","2018.3.23","2018.3.24","2018.3.24"};
    private String[] remart = new String[]{"11111","22222","33333","44444"};
    private boolean[] checkbox = new boolean[]{false,false,false,true};
    private int[] image = new int[]{R.id.name,R.id.data,R.id.remark,R.id.checkbok};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_document);
        getSupportActionBar().hide();//隐藏标题
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for (int i = 0;i < names.length; i++){
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("names",names[i]);
            listItem.put("dates",dates[i]);
            listItem.put("remark",remart[i]);
            listItem.put("checkbox",checkbox[i]);
            listItems.add(listItem);
        }

        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.simple_item,
                new String[]{"names","dates","remark","checkbox"},
                new int[]{R.id.name,R.id.data,R.id.remark,R.id.checkbok});
        ListView listView = (ListView)findViewById(R.id.mylist);

        //为ListView设置Adapter
        listView.setAdapter(simpleAdapter);
    }

}
