package com.coolweather.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.coolweather.model.City;
import com.coolweather.model.County;
import com.coolweather.model.Province;
import com.coolweather.service.api.HttpCallbackListener;
import com.coolweather.util.HttpUtil;
import com.coolweather.util.Utility;
import com.nsz.coolweather.R;

public class ChooseAreaActivity extends BaseActivity {

	public static final int LEVEL_PROVINCE = 0;
	public static final int LEVEL_CITY = 1;
	public static final int LEVEL_COUNTY = 2;

	private TextView titleText;// 标题view
	private ListView listView;// 列表view
	private ArrayAdapter<String> adapter;
	private List<String> dataList = new ArrayList<String>();

	// 省列表
	private List<Province> provinceList;
	// 市列表
	private List<City> cityList;
	// 县列表
	private List<County> countyList;

	// 选中的省份
	private Province selectedProvince;
	// 选中的城市
	private City selectedCity;
	// 当前选中的级别
	private int currentLevel;

	// 是否从WeatherActivity跳转过来的
	private boolean isFromWeatherActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isFromWeatherActivity = getIntent().getBooleanExtra(
				"from_weather_activity", false);
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		//已经选择了城市，且不是从WeatherActivity跳转过来，才会直接跳转到WeatherActivity
		if ( prefs.getBoolean("city_selected", false) && !isFromWeatherActivity ) {
			Intent intent = new Intent(this, WeatherActivity.class);
			startActivity(intent);
			finish();
			return;
		}

		setContentView(R.layout.activity_choose_area);
		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		listView = (ListView) this.findViewById(R.id.list_view);
		titleText = (TextView) this.findViewById(R.id.title_text);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dataList);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (currentLevel == LEVEL_PROVINCE) {
					selectedProvince = provinceList.get(position);
					queryCities();
				} else if (currentLevel == LEVEL_CITY) {
					selectedCity = cityList.get(position);
					queryCounties();
				} else if (currentLevel == LEVEL_COUNTY) {
					String countyCode = countyList.get(position)
							.getCountyCode();
					Intent intent = new Intent(ChooseAreaActivity.this,
							WeatherActivity.class);
					intent.putExtra("county_code", countyCode);
					startActivity(intent);
					finish();
				}
			}
		});

		queryProvinces();// 加载省级数据
	}

	/**
	 * 查询全国所有的省，优先从数据库查询，如果没有查询到再去服务器上查询
	 */
	private void queryProvinces() {
		provinceList = mDbController.loadProvinces();
		if (provinceList != null && provinceList.size() > 0) {
			dataList.clear();
			for (Province province : provinceList) {
				dataList.add(province.getProvinceName());
			}

			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText("中国");
			currentLevel = LEVEL_PROVINCE;
		} else {
			queryFromServer(null, "province");
		}

	}

	/**
	 * 查询选中省内所有的市，优先从数据库查询，如果没有查询到再去服务器上查询
	 */
	private void queryCities() {
		cityList = mDbController.loadCities(selectedProvince.getId());
		if (cityList != null && cityList.size() > 0) {
			dataList.clear();
			for (City city : cityList) {
				dataList.add(city.getCityName());
			}

			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedProvince.getProvinceName());
			currentLevel = LEVEL_CITY;
		} else {
			queryFromServer(selectedProvince.getProvinceCode(), "city");
		}

	}

	/**
	 * 查询选中市内所有的县，优先从数据库查询，如果没有查询到再去服务器上查询
	 */
	private void queryCounties() {
		countyList = mDbController.loadCounties(selectedCity.getId());
		if (countyList != null && countyList.size() > 0) {
			dataList.clear();
			for (County county : countyList) {
				dataList.add(county.getCountyName());
			}

			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedCity.getCityName());
			currentLevel = LEVEL_COUNTY;
		} else {
			queryFromServer(selectedCity.getCityCode(), "county");
		}

	}

	/**
	 * 根据传入的代号和类型从服务器上查询省市县数据
	 * 
	 * @param object
	 * @param string
	 */
	private void queryFromServer(final String code, final String type) {
		String address;
		if (!TextUtils.isEmpty(code)) {
			address = "http://www.weather.com.cn/data/list3/city" + code
					+ ".xml";
		} else {
			address = "http://www.weather.com.cn/data/list3/city.xml";
		}

		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {

			@Override
			public void onFinish(String response) {
				boolean result = false;
				if ("province".equals(type)) {
					result = Utility.handleProvincesResponse(mDbController,
							response);
				} else if ("city".equals(type)) {
					result = Utility.handleCitiesResponse(mDbController,
							response, selectedProvince.getId());
				} else if ("county".equals(type)) {
					result = Utility.handleCountiesResponse(mDbController,
							response, selectedCity.getId());
				}

				if (result) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							if ("province".equals(type)) {
								queryProvinces();
							} else if ("city".equals(type)) {
								queryCities();
							} else if ("county".equals(type)) {
								queryCounties();
							}
						}
					});
				}

			}

			@Override
			public void onError(Exception e) {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "加载失败",
								Toast.LENGTH_SHORT).show();
					}
				});
			}
		});

	}

	/**
	 * 捕获Back键，根据当前的级别来判断，此时应该返回市列表、省列表，还是直接退出
	 */
	@Override
	public void onBackPressed() {
		if (currentLevel == LEVEL_COUNTY) {
			queryCities();
		} else if (currentLevel == LEVEL_CITY) {
			queryProvinces();
		} else {
			if ( isFromWeatherActivity ){
				Intent intent = new Intent(this, WeatherActivity.class);
				startActivity(intent);
			}
			finish();
		}
	}

}
