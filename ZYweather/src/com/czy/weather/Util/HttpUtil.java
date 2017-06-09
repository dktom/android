package com.czy.weather.Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	public static void sendHttpRequest(final String address,
			final HttpCallbackListener listener) {
		new Thread(new Runnable() {
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					connection
							.setRequestProperty(
									"apikey",
									"此处填写自己的apikey值，否则程序无法可运行但无法获取详细的天气信息"
											+ "具体步骤可参考http://blog.csdn.net/new_one_object/article/details/50807528");
					connection.connect();
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(in, "UTF-8"));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					if (listener != null) {
						listener.onFinish(response.toString());
					}
				} catch (Exception e) {
					if (listener != null) {
						listener.onError(e);
					}
				} finally {
					if (connection != null) {
						connection.disconnect();
					}
				}
			}
		}).start();
	}
}
