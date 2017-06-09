package com.czy.weather.Activity;

import java.util.ArrayList;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.czy.weather.R;
import com.czy.weather.DB.WeatherDB;
import com.czy.weather.Model.City;
import com.czy.weather.Model.County;
import com.czy.weather.Model.Province;
import com.czy.weather.Util.HttpCallbackListener;
import com.czy.weather.Util.HttpUtil;
import com.czy.weather.Util.Utility;

//�˳���Ŀ���˼·�ɲο�http://blog.csdn.net/new_one_object/article/details/50807528
//���⣬��HttpUtil������Ҫ�����Լ���apikeyֵ���ɵ�����Ӳ鿴��ȡ����

public class ChooseAreaActivity extends Activity {
	// ��ǵ�ǰ�б�Ϊʡ��
	public static final int LEVEL_PROVINCE = 0;
	// ��ǵ�ǰ�б�Ϊ����
	public static final int LEVEL_CITY = 1;
	// ��ǵ�ǰ�б�Ϊ��
	public static final int LEVEL_COUNTY = 2;
	// ���ȶԻ���
	private ProgressDialog progressDialog;
	// ������
	private TextView titleText;
	// �����б�
	private ListView listView;
	// �б�����
	private ArrayAdapter<String> adapter;
	// ���ݿ�
	private WeatherDB weatherDB;

	private List<String> dataList = new ArrayList<String>();

	private List<Province> provinceList;

	private List<City> cityList;

	private List<County> countyList;

	private Province selectedProvince;

	private City selectedCity;

	private int currentLevel;

	private boolean isFromWeatherActivity;

	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isFromWeatherActivity = getIntent().getBooleanExtra(
				"from_weather_activity", false);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		// ���city��ѡ���ұ�activity���Ǵ�������������������
		if (prefs.getBoolean("city_selected", false) && !isFromWeatherActivity) {
			Intent intent = new Intent(this, WeatherActivity.class);
			startActivity(intent);
			finish();
			return;
		}
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_area);

		listView = (ListView) findViewById(R.id.list_view);
		titleText = (TextView) findViewById(R.id.title_text);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dataList);
		listView.setAdapter(adapter);
		weatherDB = WeatherDB.getInstance(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				if (currentLevel == LEVEL_PROVINCE) {
					selectedProvince = provinceList.get(index);
					queryCities();
				} else if (currentLevel == LEVEL_CITY) {
					selectedCity = cityList.get(index);
					queryCounties();
				} else if (currentLevel == LEVEL_COUNTY) {
					// ����������б�ʱ��������Intent��ת��������Ϣ����
					Editor editor = prefs.edit();
					editor.putBoolean("city_selected", true);
					editor.commit();
					String county_name = countyList.get(index).getCounty_name();
					Intent intent = new Intent(ChooseAreaActivity.this,
							WeatherActivity.class);
					intent.putExtra("county_name", county_name);
					startActivity(intent);
					finish();
				}
			}
		});
		queryProvinces();
	}

	private void queryProvinces() {
		provinceList = weatherDB.loadProvinces();
		if (provinceList.size() > 0) {
			dataList.clear();
			for (Province province : provinceList) {
				dataList.add(province.getProvince_name());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText("�й�");
			currentLevel = LEVEL_PROVINCE;
		} else {
			queryFromServer(null, "province");
		}
	}

	private void queryCities() {
		cityList = weatherDB.loadCities(selectedProvince.getProvince_id());
		if (cityList.size() > 0) {
			dataList.clear();
			for (City city : cityList) {
				dataList.add(city.getCity_name());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedProvince.getProvince_name());
			currentLevel = LEVEL_CITY;
		} else {
			queryFromServer(selectedProvince.getProvince_id(), "city");
		}
	}

	private void queryCounties() {
		countyList = weatherDB.loadCounties(selectedCity.getCity_id());
		if (countyList.size() > 0) {
			dataList.clear();
			for (County county : countyList) {
				dataList.add(county.getCounty_name());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedCity.getCity_name());
			currentLevel = LEVEL_COUNTY;
		} else {
			queryFromServer(selectedCity.getCity_id(), "county");
		}
	}

	private void queryFromServer(final String code, final String type) {
		String address;
		// code��Ϊ��
		if (!TextUtils.isEmpty(code)) {
			address = "http://www.weather.com.cn/data/list3/city" + code
					+ ".xml";
		} else {
			address = "http://www.weather.com.cn/data/list3/city.xml";
		}
		showProgerssDialog();
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
			@Override
			public void onFinish(String response) {
				boolean result = false;
				if ("province".equals(type)) {
					result = Utility.handleProvincesResponse(weatherDB,
							response);
				} else if ("city".equals(type)) {
					result = Utility.handleCitiesResponse(weatherDB, response,
							selectedProvince.getProvince_id());
				} else if ("county".equals(type)) {
					result = Utility.handleCountiesResponse(weatherDB,
							response, selectedCity.getCity_id());
				}
				if (result) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							closeProgressDialog();
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
						closeProgressDialog();
						Toast.makeText(ChooseAreaActivity.this, "����ʧ��",
								Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
	}

	private void showProgerssDialog() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("���ڼ��ء���");
			progressDialog.setCanceledOnTouchOutside(false);
		}
		progressDialog.show();
	}

	private void closeProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
	}

	@Override
	public void onBackPressed() {
		if (currentLevel == LEVEL_COUNTY) {
			queryCities();
		} else if (currentLevel == LEVEL_CITY) {
			queryProvinces();
		} else {
			if (isFromWeatherActivity) {
				Intent intent = new Intent(this, WeatherActivity.class);
				startActivity(intent);
			}
			finish();
		}
	}

}
