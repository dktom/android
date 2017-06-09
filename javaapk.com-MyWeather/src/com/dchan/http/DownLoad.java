package com.dchan.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpConnection;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public class DownLoad {
	Context context;
	public DownLoad(Context context){
		this.context=context;
	}
	/*
	 * 下载json文件
	 */
	public String downLoadFromNet(String path){
		try {
			URL url=new URL(path);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			InputStream inputStream=conn.getInputStream();
			BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
			String len=null;
			StringBuilder builder=new StringBuilder();
			while((len=reader.readLine())!=null){
				builder.append(len);
			}
			System.out.println(builder.toString());
			return builder.toString();
		} catch (Exception e) {			
			e.printStackTrace();
			Looper.prepare();
			Toast.makeText(context, "请检查你的网络", 1000).show();
			Looper.loop();
			return null;
		}
	}

}
