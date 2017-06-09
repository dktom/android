package com.dchan.SharedPreferences;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.AvoidXfermode.Mode;

public class MySharedPreferences {
	SharedPreferences sharedPreferences;
	public MySharedPreferences(Context context){
		sharedPreferences=context.getSharedPreferences("cities", context.MODE_PRIVATE);
	}
	/*
	 * 查找城市
	 */
	public ArrayList<String> getCityList(){
		ArrayList<String> cityList1=new ArrayList<String>();
		Map<String,String> map=(Map<String, String>) sharedPreferences.getAll();
		Collection<String> collection=map.values();
		String[] cityList=(String[]) collection.toArray();
		if(cityList!=null){
			for(int i=0;i<cityList.length;i++){
				cityList1.add(cityList[i]);
			}
			return cityList1;
		}
		return null;
	}
	/*
	 * 增加城市
	 */
	public boolean setCityList(String key,String value){
		SharedPreferences.Editor editor=sharedPreferences.edit();
		editor.putString(key, value);
		boolean isSet=editor.commit();
		return isSet;
	}
	/*
	 * 删除城市
	 */
	public boolean deleteCity(String key){
		SharedPreferences.Editor editor=sharedPreferences.edit();
		editor.remove(key);
		boolean isDelete=editor.commit();
		return isDelete;
	}
}
