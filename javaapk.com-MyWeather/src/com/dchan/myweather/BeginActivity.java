package com.dchan.myweather;


import com.edchan.myweather.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BeginActivity extends Activity{
	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.begin_activity);
		button=(Button)findViewById(R.id.startbutton);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(BeginActivity.this, AddCityActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			
			return false;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
