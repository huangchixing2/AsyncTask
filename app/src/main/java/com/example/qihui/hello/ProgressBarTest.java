package com.example.qihui.hello;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * 模拟进度条更新
 * Created by qihui on 2019/4/11.
 */

public class ProgressBarTest extends Activity {
    private ProgressBar mProgressBar;
    private MyAsyncTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        mProgressBar = (ProgressBar) findViewById(R.id.pg);
        mTask = new MyAsyncTask();
        mTask.execute(); //启动异步任务

    }

    //让异步任务的生命周期和activity保持一致，否则会出现 每次进入会等待上一次执行完了才会更新进度条
    @Override
    protected void onPause() {
        super.onPause();
        //不为空且running
        if (mTask != null && mTask.getStatus() == AsyncTask.Status.RUNNING) {
           //cancel方法只是将对应的AsyncTask标记为cancel状态，并不是真正的取消线程的执行
            mTask.cancel(true); //取消AsyncTask
        }
    }

    //进度情况
    class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        //此方法运行在子线程，不能更新ui
        protected Void doInBackground(Void... params) {
            //模拟进度条更新
            for (int i = 0; i < 100; i++) {
                //将进度传递给ProgressBar
                if(isCancelled()){  //判断isCancelled是否为true，如果是就退出
                    break;
                }
                publishProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()){
                return;
            }
            //获取进度更新值
            mProgressBar.setProgress(values[0]);  //只传递了一个参数，所以是values[0]
        }
    }
}
