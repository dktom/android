package com.dchan.model;

public class TodayWeatherModel {
	private String today;//星期几
	private String myDate;//日期
	private String city;//城市
	private String weather;//天气
	private String verTemp;//平均温度
	private String highTemp;//最高温度
	private String lowTemp;//最低温度
	
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getMyDate() {
		return myDate;
	}
	public void setMyDate(String myDate) {
		this.myDate = myDate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getVerTemp() {
		return verTemp;
	}
	public void setVerTemp(String verTemp) {
		this.verTemp = verTemp;
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
	@Override
	public String toString() {
		return "TodayWeatherModel [today=" + today + ", myDate=" + myDate
				+ ", city=" + city + ", weather=" + weather + ", verTemp="
				+ verTemp + ", highTemp=" + highTemp + ", lowTemp=" + lowTemp
				+ "]";
	}
	
}
