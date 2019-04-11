package com.example.qihui.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 *  asyncTask注意事项
 必须在UI线程中创建asyncTask的实例
 必须在UI现场中调用asyncTask的execute（）方法
 重写的四个方法是系统自动调用的，不用手动调用
 每个asyncTask只能被执行一次，多次调用将会引发异常
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyAsyncTask task = new MyAsyncTask();
        task.execute(); //启动

    }

    public void loadImage(View view){
        startActivity(new Intent(this, ImageTest.class));
    }

    public void loadProgress(View view){
        startActivity(new Intent(this, ProgressBarTest.class));
    }
}
