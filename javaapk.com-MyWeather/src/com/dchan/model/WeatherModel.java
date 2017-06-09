package com.dchan.model;

import com.dchan.appcontent.Content;
import com.edchan.myweather.R;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class WeatherModel {
	private String day;
	private String weatherImage;
	private String highImage;
	private String lowImage;
	private String highTemp;
	private String lowTemp;
	private String verTemp;//平均温度
	private String city;//城市
	private boolean isDay;//判断是白天还是黑夜（true：白天）
	private String windDirection;//风向
	private String windStrength;//风力
	private String cold;//关于感冒的描述
	public String getCold() {
		return cold;
	}
	public void setCold(String cold) {
		this.cold = cold;
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public String getWindStrength() {
		return windStrength;
	}
	public void setWindStrength(String windStrength) {
		this.windStrength = windStrength;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getVerTemp() {
		return verTemp;
	}
	public void setVerTemp(String verTemp) {
		this.verTemp = verTemp;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getWeatherImage() {
		return weatherImage;
	}
	public void setWeatherImage(String weatherImage) {
		this.weatherImage = weatherImage;
	}
	public String getHighImage() {
		return highImage;
	}
	public void setHighImage(String highImage) {
		this.highImage = highImage;
	}
	public String getLowImage() {
		return lowImage;
	}
	public void setLowImage(String lowImage) {
		this.lowImage = lowImage;
	}
	public String getHighTemp() {
		return highTemp;
	}
	public void setHighTemp(String highTemp) {
		this.highTemp = highTemp;
	}
	public String getLowTemp() {
		return lowTemp;
	}
	public void setLowTemp(String lowTemp) {
		this.lowTemp = lowTemp;
	}
	
	public boolean isDay() {
		return isDay;
	}
	public void setDay(boolean isDay) {
		this.isDay = isDay;
	}
	public int findImageResouce(String imageResouce){
		if(isDay()){
			if(imageResouce.equals("晴")){
				return Content.DAY_SUN;
			}else if(imageResouce.equals("多云")){
				return Content.DAY_CLOUD;
			}else if(imageResouce.equals("阵雨")){
				return Content.RAIN1;
			}else if(imageResouce.equals("雷阵雨")){
				return Content.THUNDER_RAIN1;
			}else if(imageResouce.equals("小雨")){
				return Content.RAIN1;
			}else if(imageResouce.equals("大雨")){
				return Content.RAIN2;
			}
		}else{
			if(imageResouce.equals("晴")){
				return Content.MOON_SUN;
			}else if(imageResouce.equals("多云")){
				return Content.MOON_CLOUD;
			}else if(imageResouce.equals("阵雨")){
				return Content.RAIN1;
			}else if(imageResouce.equals("雷阵雨")){
				return Content.THUNDER_RAIN1;
			}else if(imageResouce.equals("小雨")){
				return Content.RAIN1;
			}else if(imageResouce.equals("大雨")){
				return Content.RAIN2;
			}			
		}
		return Content.DAY_SUN;
		
	}
	
	@Override
	public String toString() {
		return "WeatherModel [day=" + day + ", weatherImage=" + weatherImage
				+ ", highImage=" + highImage + ", lowImage=" + lowImage
				+ ", highTemp=" + highTemp + ", lowTemp=" + lowTemp + "]";
	}
	
}
