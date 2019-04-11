package com.example.qihui.hello;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;

/**
 * 使用异步任务实现图片加载
 * Created by qihui on 2019/4/11.
 */

public class ImageTest extends Activity {

    private ImageView imageView;
    private ProgressBar progressBar;
    private static String URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554974045329&di=fb92f21aa22239af88f07b7048f22515&imgtype=0&src=http%3A%2F%2Fimg.daimg.com%2Fuploads%2Fallimg%2F120405%2F3-120405153433M5.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        imageView = (ImageView) findViewById(R.id.image);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        new MyAsyncTask().execute(URL); //在主线程中asynctask实例调用excute方法开启异步线程的操作，在execute中传递一个或者多个参数

    }

    //内部类实现                       URL类型，进度值类型，返回值类型
    class MyAsyncTask extends AsyncTask<String,Void,Bitmap>{


        @Override
        //此方法用于界面的初始化操作，如显示进度条等
        protected void onPreExecute() {
            super.onPreExecute();
            //显示进度条
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        //此方法运行在主线程
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.GONE);//加载完成后隐藏进度条
            //操作ui,设置图像
            imageView.setImageBitmap(bitmap);
        }

        @Override                        // 可变长度的数组
        protected Bitmap doInBackground(String... params) {
            String url = params[0];  //获取传递过来的参数，取出对应url
            Bitmap bitmap = null;
            URLConnection connection;  //定义网络连接对象
            InputStream is; //用于获取数据的输入流
            try {
                connection = new URL(url).openConnection();
                is = connection.getInputStream(); //获取输入流    输入流: 程序可以从中读取数据的流。  输出流: 程序能向其中写入数据的流
                BufferedInputStream bis = new BufferedInputStream(is); //包装
                Thread.sleep(3000);
                //通过decodeStream解析输入流
                bitmap = BitmapFactory.decodeStream(bis);//将url输入流解析成bitmap
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return bitmap;  //bitmap/作为参数返回
        }
    }
}
