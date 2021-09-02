package com.ahmety.nbeczzonguldak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import com.ahmety.nbeczzonguldak.R;


public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	/*	ActionBar actionBar = getActionBar();
		actionBar.hide();*/


	Thread timer= new Thread(){
			public void run(){
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Intent callList = new Intent(MainActivity.this, List.class);
			startActivity(callList);
			MainActivity.this.finish();
		}
	}
	};
		timer.start();
}
	
}	