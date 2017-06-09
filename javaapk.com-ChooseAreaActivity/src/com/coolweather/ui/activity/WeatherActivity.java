package com.coolweather.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coolweather.service.AutoUpdateService;
import com.coolweather.service.api.HttpCallbackListener;
import com.coolweather.util.HttpUtil;
import com.coolweather.util.JsonParseUtil;
import com.nsz.coolweather.R;

public class WeatherActivity extends BaseActivity implements OnClickListener {

	private LinearLayout weatherInfoLayout;
	private TextView cityNameText; // 用于显示城市名
	private TextView publishText;// 用于显示发布时间
	private TextView weatherDespText;// 用于显示天气的描述信息
	private TextView temp1Text;// 用于显示气温1
	private TextView temp2Text;// 用于显示气温2
	private TextView currentDateText;// 用于显示当前日期
	private ImageView switchCity;// 切换城市按钮
	private ImageView refreshWeather;// 更新天气按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_weather);
		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		weatherInfoLayout = (LinearLayout) this
				.findViewById(R.id.weather_info_layout);
		cityNameText = (TextView) this.findViewById(R.id.city_name);
		publishText = (TextView) this.findViewById(R.id.publish_text);
		weatherDespText = (TextView) this.findViewById(R.id.weather_desp);
		temp1Text = (TextView) this.findViewById(R.id.temp1);
		temp2Text = (TextView) this.findViewById(R.id.temp2);
		currentDateText = (TextView) this.findViewById(R.id.current_date);
		switchCity = (ImageView) this.findViewById(R.id.switch_city);
		switchCity.setOnClickListener(this);
		refreshWeather = (ImageView) this.findViewById(R.id.refresh_weather);
		refreshWeather.setOnClickListener(this);
		
		getCountyCode();
	}

	/**
	 * 得到县级代号
	 */
	private void getCountyCode() {
		String countyCode = getIntent().getStringExtra("county_code");
		if (!TextUtils.isEmpty(countyCode)) {
			// 有县级代号时就去查询天气
			publishText.setText("同步中...");
			weatherInfoLayout.setVisibility(View.INVISIBLE);
			cityNameText.setVisibility(View.INVISIBLE);
			queryWeatherCode(countyCode);
		} else {
			// 没有县级代号时就直接显示本地天气
			showWeather();
		}
	}

	/**
	 * 从SharedPreferences文件中读取存储的天气信息，并显示到界面上
	 */
	private void showWeather() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		cityNameText.setText(prefs.getString("city_name", ""));
		temp1Text.setText(prefs.getString("temp1", ""));
		temp2Text.setText(prefs.getString("temp2", ""));
		weatherDespText.setText(prefs.getString("weather_desp", ""));
		publishText.setText(prefs.getString("今天" + "publish_time", "") + "发布");
		currentDateText.setText(prefs.getString("current_date", ""));
		weatherInfoLayout.setVisibility(View.VISIBLE);
		cityNameText.setVisibility(View.VISIBLE);
		
		Intent i = new Intent(this, AutoUpdateService.class);
		this.startService(i);
	}

	/**
	 * 查询县级代号所对应的天气代号
	 * 
	 * @param countyCode
	 */
	private void queryWeatherCode(String countyCode) {
		String address = "http://www.weather.com.cn/data/list3/city"
				+ countyCode + ".xml";
		queryFromServer(address, "countyCode");
	}

	/**
	 * 查询天气代号所对应的天气
	 * 
	 * @param weatherCode
	 */
	private void queryWeatherInfo(String weatherCode) {
		String address = "http://www.weather.com.cn/data/cityinfo/"
				+ weatherCode + ".html";
		queryFromServer(address, "weatherCode");
	}

	/**
	 * 根据传入的地址和类型去向服务器查询天气代号或者天气信息
	 * 
	 * @param address
	 * @param string
	 */
	private void queryFromServer(String address, final String type) {
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
			
			@Override
			public void onFinish(String response) {
				if ( "countyCode".equals(type) ){
					if ( !TextUtils.isEmpty(response) ){
						//从服务器返回的数据中解析出天气代号
						String[] array = response.split("\\|");
						if ( array!=null && array.length==2 ){
							String weatherCode = array[1];
							queryWeatherInfo(weatherCode);
						}
					}
				} else if ( "weatherCode".equals(type) ){
					//处理服务器返回的天气信息
					JsonParseUtil.handleWeatherResponse(WeatherActivity.this, response);
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							showWeather();
						}
					});
				}
			}
			
			@Override
			public void onError(Exception e) {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						publishText.setText("同步失败");
					}
				});
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.switch_city://切换城市
			Intent intent = new Intent(this, ChooseAreaActivity.class);
			intent.putExtra("from_weather_activity", true);
			startActivity(intent);
			finish();
			break;
		case R.id.refresh_weather://刷新天气
			publishText.setText("同步中...");
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
			String weatherCode = prefs.getString("weather_code", "");
			if ( !TextUtils.isEmpty(weatherCode) ){
				queryWeatherInfo(weatherCode);
			}
			break;

		default:
			break;
		}
	}

}
