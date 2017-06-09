package com.dchan.jsonreader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dchan.model.TodayWeatherModel;
import com.dchan.model.WeatherModel;

public class JsonForReader {
	String josnMessage;
	TodayWeatherModel todaymodel=new TodayWeatherModel();
	//WeatherModel weatherModel=new WeatherModel();
	ArrayList<WeatherModel> weatherModelList;
	public JsonForReader(String josnMessage){
		this.josnMessage=josnMessage;
		weatherModelList=new ArrayList<WeatherModel>();
	}
	
	/*
	 * 解析从网络处下载的JSON
	 * 必须先read方法后才能调用getTodayWeatherModel或者getListWeather
	 */
	public void read(){
		
		weatherModelList.clear();
		try {			
			Calendar calendar=Calendar.getInstance();
			int hour=calendar.get(Calendar.HOUR_OF_DAY);
			
			JSONObject weatherInfo=new JSONObject(josnMessage);			
			JSONObject allData=weatherInfo.getJSONObject("data");
			
			
			JSONArray forecastInfo=allData.getJSONArray("forecast");			
			for(int i=0;i<forecastInfo.length();i++){
				WeatherModel weatherModel=new WeatherModel();
				
				
				if(hour>=18){
					weatherModel.setDay(false);
				}else{
					weatherModel.setDay(true);
				}
				
				JSONObject weather=forecastInfo.getJSONObject(i);

				weatherModel.setDay(weather.getString("date"));
				weatherModel.setHighTemp(weather.getString("high"));
				weatherModel.setLowTemp(weather.getString("low"));
				weatherModel.setWeatherImage(weather.getString("type"));
				weatherModel.setCity(allData.getString("city"));				
				weatherModel.setVerTemp(allData.getString("wendu")+"°");
				
				weatherModel.setCold(allData.getString("ganmao"));
				weatherModel.setWindDirection(weather.getString("fengxiang"));
				weatherModel.setWindStrength(weather.getString("fengli"));
				weatherModelList.add(weatherModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 返回当天的天气model
	 */
	public TodayWeatherModel getTodayWeatherModel(){
		if(todaymodel!=null){
			return todaymodel;
		}
		return null;
	}
	
	public ArrayList<WeatherModel> getListWeather(){
		return weatherModelList;
	}
}
