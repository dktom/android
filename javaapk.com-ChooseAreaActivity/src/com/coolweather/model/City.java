package com.coolweather.model;

/**
 * 城市的实体
 * 
 * @author zhou.ni
 *
 */
@SuppressWarnings("serial")
public class City extends SuperBean {

	private int id;
	private String cityName;
	private String cityCode;
	private int provinceId; // 省份的id

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", cityName=" + cityName + ", cityCode="
				+ cityCode + ", provinceId=" + provinceId + "]";
	}

}
