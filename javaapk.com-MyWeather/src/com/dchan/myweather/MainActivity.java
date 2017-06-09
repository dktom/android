package com.dchan.myweather;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.dchan.SharedPreferences.MySharedPreferences;
import com.dchan.adpater.WeatherAdapter;
import com.dchan.http.DownLoad;
import com.dchan.jsonreader.JsonForReader;
import com.dchan.model.TodayWeatherModel;
import com.dchan.model.WeatherModel;
import com.dchan.mylayout.MyLayout;
import com.dchan.slidinglayout.SlidingLayout;
import com.edchan.myweather.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.JsonReader;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	/*
	 * ��������
	 */
	SlidingLayout slidingLayout;
	/*
	 * ������
	 */
	View weatherContent;
	/*
	 * �����б�
	 */
	ListView cityList;
	/*
	 * һ�����е����Լ�δ�����������
	 */
	ListView cityWeatherList;
	/*
	 * ��߰�ť
	 */
	ImageButton button1;
	/*
	 * �ұ߰�ť
	 * 
	 */
	ImageButton button2;
	SwipeRefreshLayout swipeRefreshLayout;
	
	
	/*
	 * ���������
	 */
	TextView todayText;
	/*
	 * ����
	 */
	TextView city;
	/*
	 * ����ͼƬ
	 */
	ImageView imageView;
	/*
	 * ��������
	 */
	TextView weatherText;
	/*
	 * ƽ���¶�
	 */
	TextView evrTemp;
	/*
	 * ����¶�
	 */
	TextView highTemp;
	/*
	 * ����¶�
	 */
	TextView lowTemp;
	
	/*
	 * 
	 * ������ӳ���
	 */
	ImageButton addCityButton;
	
	TodayWeatherModel todaymodel;
	MyHandler myHandler;
	public static final int setWeatherList=1;//֪ͨhandler����listview
	public static final int REFLASHCITY=2;//֪ͨhandler���³����б�
	public static long time=0;//���ڴ���˫���˳�
	
	MyBroadcast broadcast=new MyBroadcast();
	
	String theCity;//��ǰ��ʾ�ĳ���
	ArrayAdapter<String> cityAdapter;
	
	//MySharedPreferences myCities=new MySharedPreferences(this);
	ArrayList<String> nameList;//���еĳ���
	
	SharedPreferences sharedPreferences;
	private String theFirst="0";//�Ƿ��һ������
	
	LinearLayout todayLinearLayout;//���������Ĳ��֣����ڵ������������Ϣ
	TextView cold;//��ð����
	TextView windStrength;//����
	TextView windDirection;//����	
	LayoutInflater inflater;
	View view;//moreInformation��layout
	AlertDialog dialog;//moreInformation�ĶԻ���
	
	ProgressDialog progressDialog;//���ڼ���ʱ��ʾ
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		inflater=getLayoutInflater();
		view=inflater.inflate(R.layout.more_information, null);
		
		slidingLayout=(SlidingLayout)findViewById(R.id.sliding);
		weatherContent=(LinearLayout)findViewById(R.id.mycontent);		
		button1=(ImageButton)findViewById(R.id.add_city_button);
		button2=(ImageButton)findViewById(R.id.add_more_button);		
		cityList=(ListView)findViewById(R.id.city_list);
		cityWeatherList=(ListView)findViewById(R.id.city_weadther_list);
		slidingLayout.setScrollEvent(cityWeatherList);
		
		todayText=(TextView)findViewById(R.id.thedate);
		city=(TextView)findViewById(R.id.city);
		imageView=(ImageView)findViewById(R.id.todayweather);
		weatherText=(TextView)findViewById(R.id.todayweathertext);
		evrTemp=(TextView)findViewById(R.id.todayvertemp);
		highTemp=(TextView)findViewById(R.id.todayhightemp);
		lowTemp=(TextView)findViewById(R.id.todaylowtemp);
		
		addCityButton=(ImageButton)findViewById(R.id.add_city_button);
		
		swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_container);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_light, android.R.color.holo_red_dark, android.R.color.holo_purple, android.R.color.holo_green_light);
		
		todayLinearLayout=(LinearLayout)findViewById(R.id.todayweatherlayout);
		cold=(TextView)view.findViewById(R.id.cold);
		windStrength=(TextView)view.findViewById(R.id.wind_strength);
		windDirection=(TextView)view.findViewById(R.id.wind_direction);
		dialog=new AlertDialog.Builder(MainActivity.this).setView(view).setTitle("                ������Ϣ").create();
		
		progressDialog=new ProgressDialog(this);
		
		myHandler=new MyHandler();
		
		sharedPreferences=getSharedPreferences("city", MODE_PRIVATE);
		/*SharedPreferences.Editor editor1=sharedPreferences.edit();
		editor1.clear();
		editor1.commit();*/
		theCity=sharedPreferences.getString("theCity", "����");
		theFirst=sharedPreferences.getString("theFirst", "0");
		if(theFirst.equals("0")){
			theFirst="1";
			SharedPreferences.Editor editor=sharedPreferences.edit();
			editor.putString("theFirst", theFirst);
			editor.commit();
			
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, BeginActivity.class);
			startActivity(intent);
		}
		/*SharedPreferences.Editor editor=sharedPreferences.edit();
		editor.clear();
		editor.commit();*/
		
		
		
		nameList=getCityList();
		
		if(nameList!=null){
			System.out.println("nameList is not null");
			ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, nameList);			
			cityList.setAdapter(adapter);
		}
		
		todayLinearLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.show();
			}
		});
				
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				startReflashUI();
				new Thread(){
					public void run(){
						try {
							sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						swipeRefreshLayout.setRefreshing(false);
					}
				}.start();
			}
		});
		
		
		startReflashUI();		
		
		addCityButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("!!!!!!!!!!!");
				Intent intent=new Intent(MainActivity.this, AddCityActivity.class);
				startActivity(intent);
			}
		});
		
		/*
		 * city�б�����ɾ������
		 */
		cityList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				String key=nameList.get(arg2);
				nameList.remove(arg2);
				deleteCity(key);
				Message msg=new Message();
				msg.what=REFLASHCITY;
				myHandler.sendMessage(msg);
				return true;
			}
		});
		/*
		 * ���Ҫ�鿴city������
		 */
		cityList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				theCity=nameList.get(arg2);
				SharedPreferences.Editor editor=sharedPreferences.edit();
				editor.putString("theCity", theCity);
				editor.commit();
				startReflashUI();
				slidingLayout.scrollToRightLayout();
			}
		});
		
		registerReceiver(broadcast, getIntentFilter());
	}
	/*
	 * handler����UI�¼�
	 */
	class MyHandler extends Handler{

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			System.out.println("inide");
			switch (msg.what) {
			case setWeatherList:
				System.out.println("setWeatherList");
				ArrayList<WeatherModel> weatherlist=(ArrayList<WeatherModel>) msg.obj;
				WeatherAdapter adapter=new WeatherAdapter(MainActivity.this, weatherlist);
				cityWeatherList.setAdapter(adapter);
				
				todayText.setText(weatherlist.get(0).getDay());
				city.setText(weatherlist.get(0).getCity());
				imageView.setBackgroundResource(weatherlist.get(0).findImageResouce(weatherlist.get(0).getWeatherImage()));
				weatherText.setText(weatherlist.get(0).getWeatherImage());
				evrTemp.setText(weatherlist.get(0).getVerTemp());
				highTemp.setText(weatherlist.get(0).getHighTemp());	
				lowTemp.setText(weatherlist.get(0).getLowTemp());
				
				/*
				 * �Ի��������
				 */
				windStrength.setText(weatherlist.get(0).getWindStrength());
				windDirection.setText(weatherlist.get(0).getWindDirection());
				cold.setText(weatherlist.get(0).getCold());
				
				//progressDialog.dismiss();
				break;
			case REFLASHCITY:
				System.out.println("REFLASHCITY");
				cityAdapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, nameList);
				cityList.setAdapter(cityAdapter);
				cityAdapter.notifyDataSetChanged();
				startReflashUI(); 
			break;
			default:
				break;
			}
		}		
	}
	
	
	public IntentFilter getIntentFilter(){
		IntentFilter filter=new IntentFilter();
		filter.addAction("CITYSELECT");
		return filter;
	}
	class MyBroadcast extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String action=intent.getAction();
			SharedPreferences.Editor editor=sharedPreferences.edit();
			if(action.equals("CITYSELECT")){
				String city=intent.getStringExtra("city");
				theCity=city;
				if(theCity!=null){
					System.out.println(theCity);
					editor.putString(city, city);
					editor.putString("theCity", theCity);
					editor.commit();
				}
				System.out.println("isSet");
				nameList.add(city);
				for(int i=0;i<nameList.size();i++){
					System.out.println("!!!!!"+nameList.get(i));
				}
				Message msg=myHandler.obtainMessage();
				msg.what=REFLASHCITY;
				myHandler.sendMessage(msg);
				
			}
			
		}		
	}
	
	public void startReflashUI(){
		new Thread(){

			@Override
			public void run() {
				super.run();
				/*Looper.prepare();
				progressDialog.show(MainActivity.this, "loading", "loading...");
				Looper.loop();*/
				
				DownLoad downLoad=new DownLoad(MainActivity.this);
				String b="http://wthrcdn.etouch.cn/weather_mini?city=";
				try {
					if(theCity!=null){
						String c=URLEncoder.encode(theCity, "UTF-8");
						b=b+c;
					}else{
						String c=URLEncoder.encode("����", "UTF-8");
						b=b+c;
					}
					
					String jsonMessage=downLoad.downLoadFromNet(b);
					JsonForReader reader=new JsonForReader(jsonMessage);
					reader.read();
					ArrayList<WeatherModel> weatherlist=reader.getListWeather();
					todaymodel=reader.getTodayWeatherModel();
					Message msg=myHandler.obtainMessage();
					msg.what=setWeatherList;
					msg.obj=weatherlist;
					myHandler.sendMessage(msg);
					
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}.start();	
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		unregisterReceiver(broadcast);
	}
	
	/*
	 * ���ҳ���
	 */
	public ArrayList<String> getCityList(){
		ArrayList<String> cityList1=new ArrayList<String>();
		Map<String,String> map=(Map<String, String>) sharedPreferences.getAll();
		Collection<String> collection=map.values();
		Iterator<String> iterator=collection.iterator();
		
		while(iterator.hasNext()){
			String city=iterator.next();
			System.out.println("����----"+city);	
			if(!(city.equals("0")||city.equals("1"))){
				cityList1.add(city);
			}			
		}
		//��һ��HashSet����cityList1������ظ�Ԫ��ȥ��
		HashSet<String> h=new HashSet<String>(cityList1);
		cityList1.clear();		
		cityList1.addAll(h);
		return cityList1;	
	}
	
	/*
	 * ���ӳ���
	 */
	public boolean setCityList(String key,String value){
		SharedPreferences.Editor editor=sharedPreferences.edit();
		editor.putString(key, value);
		boolean isSet=editor.commit();
		return isSet;
	}
	/*
	 * ɾ������
	 */
	public boolean deleteCity(String key){
		SharedPreferences.Editor editor=sharedPreferences.edit();
		editor.remove(key);
		boolean isDelete=editor.commit();
		return isDelete;
	}
	
	/*
	 * ˫���˳�
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if(System.currentTimeMillis()-time>2000){
				Toast.makeText(this, "�˳���", 1000).show();
				time=System.currentTimeMillis();
			}else{
				finish();
			}
			
			return false;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
