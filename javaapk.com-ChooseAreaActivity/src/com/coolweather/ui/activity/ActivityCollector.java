package com.coolweather.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActivityCollector {
	
	//存储返回栈中的Activity
	public static List<Activity> activities = new ArrayList<Activity>();
	
	/**
	 * 添加一个Activity到列表中
	 * @param activity
	 */
	public static void addActivity(Activity activity){
		activities.add(activity);
	}
	
	/**
	 * 移除列表中的一个Activity
	 * @param activity
	 */
	public static void removeActivity(Activity activity){
		activities.remove(activity);
	}
	
	/**
	 * 销毁现存的所以Activity
	 */
	public static void finishAll(){
		for (Activity activity : activities) {
			if ( !activity.isFinishing() ){
				activity.finish();
			}
		}
	}
	
}
