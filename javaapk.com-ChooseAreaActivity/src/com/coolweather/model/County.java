package com.coolweather.model;

/**
 * 某一个县的实体
 * 
 * @author zhou.ni
 * 
 */
@SuppressWarnings("serial")
public class County extends SuperBean {

	private int id;
	private String countyName;
	private String countyCode;
	private int cityId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "County [id=" + id + ", countyName=" + countyName
				+ ", countyCode=" + countyCode + ", cityId=" + cityId + "]";
	}

}
