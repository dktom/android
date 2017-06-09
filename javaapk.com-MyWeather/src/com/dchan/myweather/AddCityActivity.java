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
	MarginLayoutParams provinceLayoutParams;//ʡ��layout�Ĳ���
	MarginLayoutParams cityLayoutParams;//��layout�Ĳ���
	 int screenWidth;//��Ļ���
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
		
		String[] s=new String[]{"�㶫","����","�Ϻ�","����","����","����","������","����","���ɹ�","�½�","����","����","����","̨��","����","ɽ��",
				                "����","ɽ��","����","����","����","�ӱ�","����","�Ĵ�","����","�ຣ","����","����","����"};
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
					String[] c=new String[]{"�Ϻ�"};
					
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
				}else if(arg2==1){
					String[] c=new String[]{"����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}else if(arg2==2){
					String[] c=new String[]{"���ͺ���","��ͷ"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}else if(arg2==3){
					String[] c=new String[]{"����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}else if(arg2==4){
					String[] c=new String[]{"̨��","����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==5){
					String[] c=new String[]{"����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==6){
					String[] c=new String[]{"�ɶ�"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==7){
					String[] c=new String[]{"����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==8){
					String[] c=new String[]{"�Ϸ�"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==9){
					String[] c=new String[]{"�ຣ","����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==10){
					String[] c=new String[]{"̫ԭ"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==11){
					String[] c=new String[]{"����","����","��ݸ","����","տ��","ï��","��ͷ"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==12){
					String[] c=new String[]{"����","����","����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==13){
					String[] c=new String[]{"��³ľ��"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==14){
					String[] c=new String[]{"�Ͼ�"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==15){
					String[] c=new String[]{"�ϲ�"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==16){
					String[] c=new String[]{"ʯ��ׯ"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==17){
					String[] c=new String[]{"֣��"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==18){
					String[] c=new String[]{"����","����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==19){
					String[] c=new String[]{"�人"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==20){
					String[] c=new String[]{"��ɳ"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==21){
					String[] c=new String[]{"����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==22){
					String[] c=new String[]{"����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==23){
					String[] c=new String[]{"����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==24){
					String[] c=new String[]{"����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==25){
					String[] c=new String[]{"����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==26){
					String[] c=new String[]{"����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==27){
					String[] c=new String[]{"����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==25){
					String[] c=new String[]{"����"};
					adapter2=new ArrayAdapter<String>(AddCityActivity.this, android.R.layout.simple_expandable_list_item_1, c);
					city.setAdapter(adapter2);
					adapter2.notifyDataSetChanged();
				}
				else if(arg2==25){
					String[] c=new String[]{"������"};
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
