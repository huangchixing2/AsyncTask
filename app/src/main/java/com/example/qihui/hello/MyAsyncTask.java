package com.example.qihui.hello;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by qihui on 2019/4/10.
 */

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

    public final String TAG = "MyAsyncTask";

    @Override
    //必须实现的方法,做耗时操作
    protected Void doInBackground(Void... params) {
        Log.d(TAG, "doInBackground");
        publishProgress();//手动执行此方法才会调用onProgressUpdate（）方法
        return null;
    }

    @Override
    //通常用户完成一些初始化操作
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute");


    }

    @Override
    //在doInBackground之后调用，用于展示处理的结果
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d(TAG, "onPostExecute");

    }

    @Override
    //在doinbackground（）方法中调用publishprogress()方法更新任务的执行进度后，就会触发该方法
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate");
    }
}

