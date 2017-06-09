package com.dchan.adpater;

import java.util.ArrayList;
import java.util.zip.Inflater;

import com.dchan.model.WeatherModel;
import com.edchan.myweather.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherAdapter extends BaseAdapter{
	ArrayList<WeatherModel> list;
	Context context;
	public WeatherAdapter(Context context,ArrayList<WeatherModel> list){
		this.list=list;
		this.context=context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Model model=new Model();
		if(convertView==null){			
			convertView=LayoutInflater.from(context).inflate(R.layout.city_weather_list, null);
			model.day=(TextView)convertView.findViewById(R.id.day);
			model.weatherImage=(ImageView)convertView.findViewById(R.id.image_day);
			model.highImage=(ImageView)convertView.findViewById(R.id.image_high_tem);
			model.lowImage=(ImageView)convertView.findViewById(R.id.image_low_tem);
			model.lowTemp=(TextView)convertView.findViewById(R.id.low_tem);
			model.highTemp=(TextView)convertView.findViewById(R.id.high_tem);
			convertView.setTag(model);
		}else{
			model=(Model) convertView.getTag();
		}
		model.day.setText(list.get(position).getDay());
		model.highTemp.setText(list.get(position).getHighTemp());
		model.lowTemp.setText(list.get(position).getLowTemp());
		model.weatherImage.setImageResource(list.get(position).findImageResouce(list.get(position).getWeatherImage()));
		model.highImage.setImageResource(R.drawable.h);
		model.lowImage.setImageResource(R.drawable.l);
		return convertView;
	}
	class Model{
		public TextView day;
		public ImageView weatherImage;
		public ImageView highImage;
		public ImageView lowImage;
		public TextView highTemp;
		public TextView lowTemp;
	}
}
