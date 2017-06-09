package com.czy.weather.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.czy.weather.Activity.WeatherActivity;
import com.czy.weather.Adapter.HourlyWeather;
import com.czy.weather.DB.WeatherDB;
import com.czy.weather.Model.City;
import com.czy.weather.Model.County;
import com.czy.weather.Model.Province;

public class Utility {
	// ������������ص�ʡ������
	public static boolean handleProvincesResponse(WeatherDB weatherDB,
			String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvince_id(array[0]);
					province.setProvince_name(array[1]);
					weatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}

	// ������������ص��м�����
	public static boolean handleCitiesResponse(WeatherDB weatherDB,
			String response, String provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String c : allCities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCity_id(array[0]);
					city.setCity_name(array[1]);
					city.setProvince_id(provinceId);
					weatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}

	// ������������ص��ؼ�����
	public static boolean handleCountiesResponse(WeatherDB weatherDB,
			String response, String cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCounty_id(array[0]);
					county.setCounty_name(array[1]);
					county.setCity_id(cityId);
					weatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}

	// ������������ص�json����
	public static void handleWeatherResponse(Context context, String response) {
		try {
			JSONObject jsonobject = new JSONObject(response);
			JSONArray title = jsonobject
					.getJSONArray("HeWeather data service 3.0");
			JSONObject first_object = (JSONObject) title.get(0);
			JSONObject basic = (JSONObject) first_object.get("basic");
			JSONObject update = (JSONObject) basic.get("update");
			JSONArray daily_forecast = (JSONArray) first_object
					.get("daily_forecast");
			JSONObject daily_forecast_first = (JSONObject) daily_forecast
					.get(0);
			JSONObject cond = (JSONObject) daily_forecast_first.get("cond");
			JSONObject tmp = (JSONObject) daily_forecast_first.get("tmp");
			JSONObject astro = (JSONObject) daily_forecast_first.get("astro");
			JSONObject wind = (JSONObject) daily_forecast_first.get("wind");
			JSONArray hourly_forecast = (JSONArray) first_object
					.get("hourly_forecast");
			WeatherActivity.weatherList.clear();
			for (int i = 0; i < hourly_forecast.length(); i++) {
				JSONObject json = hourly_forecast.getJSONObject(i);
				JSONObject json_wind = (JSONObject) json.get("wind");
				String date = json.getString("date");
				String[] array = date.split(" ");
				String dir = json_wind.getString("dir");
				String sc = json_wind.getString("sc");
				String hourly_clock = array[1];
				String hourly_tmp = "�¶ȣ�" + json.getString("tmp") + "��";
				String hourly_pop = "��ˮ���ʣ�" + json.getString("pop");
				String hourly_wind = "������" + dir + " " + sc + "��";
				HourlyWeather wea = new HourlyWeather(hourly_clock, hourly_tmp,
						hourly_pop, hourly_wind);
				WeatherActivity.weatherList.add(wea);
			}
			String sr = astro.getString("sr");
			String ss = astro.getString("ss");
			String pop = daily_forecast_first.getString("pop");
			String windText = wind.getString("dir") + " "
					+ wind.getString("sc") + "��";
			String city_name = basic.getString("city");
			String publish_time = "����ʱ�䣺" + update.getString("loc");
			String weather = "�գ�" + cond.getString("txt_d") + "   ҹ��"
					+ cond.getString("txt_n");
			String min = tmp.getString("min");
			String max = tmp.getString("max");
			saveWeatherInfo(context, city_name, publish_time, weather, min,
					max, sr, ss, windText, pop);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	private static void saveWeatherInfo(Context context, String city_name,
			String publish_time, String weather, String min, String max,
			String sr, String ss, String windText, String pop) {
		SharedPreferences.Editor editor = PreferenceManager
				.getDefaultSharedPreferences(context).edit();
		editor.putString("city_name", city_name);
		editor.putString("publish_time", publish_time);
		editor.putString("weather", weather);
		editor.putString("minTmp", min + "��");
		editor.putString("maxTmp", max + "��");
		editor.putString("sr", sr);
		editor.putString("ss", ss);
		editor.putString("windText", windText);
		editor.putString("pop", pop);
		editor.commit();
	}
}
