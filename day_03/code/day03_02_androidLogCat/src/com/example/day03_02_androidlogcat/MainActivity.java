package com.example.day03_02_androidlogcat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("hello");
//		Log.v("MainActivity", "verbose");//黑色的日志
//        Log.d("MainActivity", "debug"); //蓝色的日志
//        Log.i("MainActivity", "info"); // 绿色的日志
//        Log.w("MainActivity", "warning"); //黄色的日志
//        Log.e("MainActivity", "error"); //红色的日志
		LogUtils.LOGD("MainActivity", "debug");
		LogUtils.LOGE("MainActivity", "error");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
