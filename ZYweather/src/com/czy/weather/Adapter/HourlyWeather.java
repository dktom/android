package com.czy.weather.Adapter;

public class HourlyWeather {
	// Ԥ��ʱ��
	private String clock;
	// �¶�
	private String tmp;
	// ��ˮ����
	private String pop;
	// ����
	private String wind;

	public HourlyWeather(String clock, String tmp, String pop, String wind) {
		this.clock = clock;
		this.tmp = tmp;
		this.pop = pop;
		this.wind = wind;
	}

	public String getClock() {
		return clock;
	}

	public String getTmp() {
		return tmp;
	}

	public String getPop() {
		return pop;
	}

	public String getWind() {
		return wind;
	}
}
