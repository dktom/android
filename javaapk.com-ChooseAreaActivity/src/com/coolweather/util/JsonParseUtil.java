package com.coolweather.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class JsonParseUtil {

	/**
	 * 解析天气的返回结果
	 * 
	 * @param context
	 * @param response
	 */
	public static void handleWeatherResponse(Context context, String response) {
		try {
			JSONObject jsonObject = new JSONObject(response);
			JSONObject weatherinfo = jsonObject.getJSONObject("weatherinfo");
			String cityName = weatherinfo.getString("city");
			String weatherCode = weatherinfo.getString("cityid");
			String temp1 = weatherinfo.getString("temp1");
			String temp2 = weatherinfo.getString("temp2");
			String weatherDesp = weatherinfo.getString("weather");
			String publishTime = weatherinfo.getString("ptime");
			saveWeatherInfo(context, cityName, weatherCode, temp1, temp2,
					weatherDesp, publishTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将服务器返回的所有信息存储到sharedPreferences文件中
	 * 
	 * @param context
	 * @param cityName
	 * @param weatherCode
	 * @param temp1
	 * @param temp2
	 * @param weatherDesp
	 * @param publishTime
	 */
	public static void saveWeatherInfo(Context context, String cityName,
			String weatherCode, String temp1, String temp2, String weatherDesp,
			String publishTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
		SharedPreferences.Editor editor = PreferenceManager
				.getDefaultSharedPreferences(context).edit();
		editor.putBoolean("city_selected", true);
		editor.putString("city_name", cityName);
		editor.putString("weather_code", weatherCode);
		editor.putString("temp1", temp1);
		editor.putString("temp2", temp2);
		editor.putString("weather_desp", weatherDesp);
		editor.putString("publish_time", publishTime);
		editor.putString("current_date", sdf.format(new Date()));
		editor.commit();
	}

}
