package com.example.day03_05_saveinfobysp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private EditText et_username;
	private EditText et_pwd;
	private CheckBox cb_isSave;
	private Button btn_login;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_password);
        cb_isSave = (CheckBox) findViewById(R.id.cb_isSave);
        btn_login = (Button) findViewById(R.id.btn_login);
		
        
        btn_login.setOnClickListener(this);
        
        sp = getSharedPreferences("info", MODE_PRIVATE);
        boolean  isSave = sp.getBoolean("ischecked", false);
        if(isSave) {
        	et_username.setText(sp.getString("username", ""));
        	et_pwd.setText(sp.getString("pwd", ""));
        	cb_isSave.setChecked(true);
        }
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

	@Override
	public void onClick(View v) {
		String username = et_username.getText().toString().trim();
		String pwd = et_pwd.getText().toString().trim();
		
		if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
			Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT);
		}else {
			Editor editor = sp.edit();
			boolean cheaked = cb_isSave.isChecked();
			if(cheaked) {
				editor.putString("username", username);
				editor.putString("pwd", pwd);
			}
			editor.putBoolean("ischecked", cheaked);
			editor.commit();
		}
	}
}
