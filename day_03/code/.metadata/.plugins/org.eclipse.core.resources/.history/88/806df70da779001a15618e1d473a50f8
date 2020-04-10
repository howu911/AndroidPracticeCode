package com.example.day03_04_getsdcardsize;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button bt_free = (Button) findViewById(R.id.freeSize);
		Button bt_total = (Button) findViewById(R.id.totalSize);
		
		File storageDirectory = Environment.getExternalStorageDirectory();
        long totalSpace = storageDirectory.getTotalSpace();
        long freeSpace = storageDirectory.getFreeSpace();
        
        //通过formatter把用byte字节表示的大小 转换成用 kb mb gb 这种单位表示的字符串
        String total = Formatter.formatFileSize(this, totalSpace);
        String free = Formatter.formatFileSize(this, freeSpace);
        
        bt_free.setText(free);
        bt_total.setText(total);
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
