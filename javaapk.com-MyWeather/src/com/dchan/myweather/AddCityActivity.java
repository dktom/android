package com.dchan.myweather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import com.edchan.myweather.R;

import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AddCityActivity extends Activity{
	ListView province;
	ListView city;
	MarginLayoutParams provinceLayoutParams;//省份layout的参数
	MarginLayoutParams cityLayoutParams;//市layout的参数
	 int screenWidth;//屏幕宽度
	 ArrayAdapter<String> adapter2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_city_activity);
		province=(ListView)findViewById(R.id.add_province);
		city=(ListView)findViewById(R.id.add_city);
		
		WindowManager wm=(WindowManager) getSystemService(WINDOW_SERVICE);
		screenWidth=wm.getDefaultDisplay().getWidth();
		
		provinceLayoutParams=(MarginLayoutParams) province.getLayoutParams();
		cityLayoutParams=(MarginLayoutParams) city.getLayoutParams();
		
		String[] s=new String[]{"广东","福建","上海","北京","广西","西藏","黑龙江","云南","内蒙古","新疆","海南","重庆","宁夏","台湾","吉林","山东",
				                "江西","山西","湖南","湖北","河南","河北","甘肃","四川","安徽","青海","陕西","贵州","江苏"};
		Set<String> set=new TreeSet<String>();
		ArrayList<String> list=new ArrayList<String>();
		for(int i=0;i<s.length;i++){
			set.add(s[i]);
		}		
		list.addAll(set);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
		province.setAdapter(adapter);
		
		
		province.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(arg2==0){
					String[] c=new String[]{"上海"};
					
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
				}else if(arg2==1){
					String[] c=new String[]{"昆明"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}else if(arg2==2){
					String[] c=new String[]{"呼和浩特","包头"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}else if(arg2==3){
					String[] c=new String[]{"北京"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}else if(arg2==4){
					String[] c=new String[]{"台北","高雄"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==5){
					String[] c=new String[]{"长春"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==6){
					String[] c=new String[]{"成都"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==7){
					String[] c=new String[]{"银川"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==8){
					String[] c=new String[]{"合肥"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==9){
					String[] c=new String[]{"青海","济南"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==10){
					String[] c=new String[]{"太原"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==11){
					String[] c=new String[]{"广州","深圳","东莞","惠州","湛江","茂名","汕头"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==12){
					String[] c=new String[]{"南宁","桂林","玉林"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==13){
					String[] c=new String[]{"乌鲁木齐"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==14){
					String[] c=new String[]{"南京"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==15){
					String[] c=new String[]{"南昌"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==16){
					String[] c=new String[]{"石家庄"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==17){
					String[] c=new String[]{"郑州"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==18){
					String[] c=new String[]{"海口","三亚"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==19){
					String[] c=new String[]{"武汉"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==20){
					String[] c=new String[]{"长沙"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==21){
					String[] c=new String[]{"兰州"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==22){
					String[] c=new String[]{"厦门"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==23){
					String[] c=new String[]{"拉萨"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==24){
					String[] c=new String[]{"贵阳"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==25){
					String[] c=new String[]{"重庆"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==26){
					String[] c=new String[]{"西安"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==27){
					String[] c=new String[]{"重庆"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==25){
					String[] c=new String[]{"西宁"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==25){
					String[] c=new String[]{"哈尔滨"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				
				provinceLayoutParams.width=screenWidth/2;
				cityLayoutParams.width=screenWidth/2;
				province.setLayoutParams(provinceLayoutParams);
				city.setLayoutParams(cityLayoutParams);
				city.setVisibility(View.VISIBLE);
			}
		});
		
		city.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String city=adapter2.getItem(arg2);
				Intent intent=new Intent();
				intent.setAction("CITYSELECT");
				intent.putExtra("city", city);
				sendBroadcast(intent);
				finish();
			}
		});
	}
	
}
