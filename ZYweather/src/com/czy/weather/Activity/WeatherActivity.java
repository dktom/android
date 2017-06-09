package com.czy.weather.Activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.czy.weather.R;
import com.czy.weather.Adapter.HourlyWeather;
import com.czy.weather.Adapter.WeatherAdapter;
import com.czy.weather.Util.HttpCallbackListener;
import com.czy.weather.Util.HttpUtil;
import com.czy.weather.Util.Utility;

public class WeatherActivity extends Activity implements OnClickListener {

	private LinearLayout weatherInfoLayout;
	// �����л���ť
	private Button switch_city_button;
	// ���°�ť
	private Button refresh_weather_button;
	// ������
	private TextView city_name_text;
	// ��������
	private TextView weather_desp_text;
	// �¶�
	private TextView weather_tmp_text;
	// �ճ�ʱ��
	private TextView sr_text;
	// ����ʱ��
	private TextView ss_text;
	// ����
	private TextView wind_text;
	// ��ˮ����
	private TextView pop_text;
	// ����ʱ��
	private TextView publish_time_text;
	// ��������Ԥ���б�
	private ListView listview;

	public static List<HourlyWeather> weatherList = new ArrayList<HourlyWeather>();

	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weather_layout);

		switch_city_button = (Button) findViewById(R.id.switch_city);
		refresh_weather_button = (Button) findViewById(R.id.refresh_weather);
		weatherInfoLayout = (LinearLayout) findViewById(R.id.weather_info_layout);
		city_name_text = (TextView) findViewById(R.id.city_name);
		weather_desp_text = (TextView) findViewById(R.id.weather_desp);
		weather_tmp_text = (TextView) findViewById(R.id.weather_tmp);
		sr_text = (TextView) findViewById(R.id.sr);
		ss_text = (TextView) findViewById(R.id.ss);
		wind_text = (TextView) findViewById(R.id.windText);
		pop_text = (TextView) findViewById(R.id.pop);
		publish_time_text = (TextView) findViewById(R.id.publish_time_text);
		listview = (ListView) findViewById(R.id.hourly_forecast);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);

		String county_name = getIntent().getStringExtra("county_name");
		// ��county_name��Ϊ��
		if (!TextUtils.isEmpty(county_name)) {
			Editor editor = prefs.edit();
			editor.putString("county_name", county_name);
			editor.commit();
			publish_time_text.setText("ͬ���С���");
			weatherInfoLayout.setVisibility(View.INVISIBLE);
			city_name_text.setVisibility(View.INVISIBLE);
			queryFromServer(county_name);
		} else {
			county_name = prefs.getString("city_name", "δ֪");
			publish_time_text.setText("ͬ���С���");
			weatherInfoLayout.setVisibility(View.INVISIBLE);
			city_name_text.setVisibility(View.INVISIBLE);
			queryFromServer(county_name);
		}
		switch_city_button.setOnClickListener(this);
		refresh_weather_button.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.switch_city:
			Intent intent = new Intent(this, ChooseAreaActivity.class);
			intent.putExtra("from_weather_activity", true);
			startActivity(intent);
			finish();
			break;
		case R.id.refresh_weather:
			publish_time_text.setText("ͬ���С���");
			String county_nmae = prefs.getString("county_name", "");
			if (!TextUtils.isEmpty(county_nmae)) {
				queryFromServer(county_nmae);
				Toast.makeText(WeatherActivity.this, "���³ɹ�", Toast.LENGTH_SHORT)
						.show();
			}
			break;
		default:
			break;
		}
	}

	private void queryFromServer(final String county_name) {
		try {
			String hh = "http://apis.baidu.com/heweather/weather/free?city=";
			String hhh = new String(county_name.getBytes("UTF-8"), "iso-8859-1");
			HttpUtil.sendHttpRequest(hh + hhh, new HttpCallbackListener() {
				@Override
				public void onFinish(String response) {
					Utility.handleWeatherResponse(WeatherActivity.this,
							response);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							showWeather();
						}
					});
				}

				@Override
				public void onError(Exception e) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							publish_time_text.setText("ͬ��ʧ��");
						}
					});
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void showWeather() {
		city_name_text.setText(prefs.getString("city_name", "δ֪"));
		weather_desp_text.setText(prefs.getString("weather", "δ֪"));
		weather_tmp_text.setText("�¶ȣ�" + prefs.getString("minTmp", "0") + " ~ "
				+ prefs.getString("maxTmp", "0"));
		sr_text.setText("�ճ���" + prefs.getString("sr", ""));
		ss_text.setText("���䣺 " + prefs.getString("ss", ""));
		wind_text.setText("������ " + prefs.getString("windText", ""));
		pop_text.setText("��ˮ���ʣ� " + prefs.getString("pop", ""));
		publish_time_text.setText(prefs.getString("publish_time", "0"));

		WeatherAdapter adapter = new WeatherAdapter(this,
				R.layout.hourly_forecast, weatherList);
		listview.setAdapter(adapter);

		city_name_text.setVisibility(View.VISIBLE);
		weatherInfoLayout.setVisibility(View.VISIBLE);
		listview.setVisibility(View.VISIBLE);
	}

}
