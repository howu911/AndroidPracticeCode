package com.example.day03_03_loadcase;

import com.example.day03_03_loadcase.utils.utils;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_username;
	private EditText et_pwd;
	private CheckBox cb_isSave;
	private Button btn_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//�ҵ��ؼ�
		et_username = (EditText) findViewById(R.id.et_username);
		et_pwd = (EditText) findViewById(R.id.et_password);
        cb_isSave = (CheckBox) findViewById(R.id.cb_isSave);
        btn_login = (Button) findViewById(R.id.btn_login);
        
        //���õ���¼�
        btn_login.setOnClickListener(new myLister());
        
        //���б����ļ�����ȡ��Ϣ
        //String[] info = utils.readInfo();
        //String[] info = utils.readInfoByContext(this);
        String[] info = utils.readInfoBySdcard();
        if(info != null) {
        	Log.d("MainActivity", "��ȡ��Ϣ");
        	et_username.setText(info[0]);
        	et_pwd.setText(info[1]);
        }
	}
	
	private class myLister implements OnClickListener{

		@Override
		public void onClick(View v) {
			//�� ��ȡ�û�����
			String username = et_username.getText().toString().trim();
			String pwd = et_pwd.getText().toString().trim();
			//���ж������Ƿ�Ϊ��
			if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
				Toast.makeText(MainActivity.this, "�û���������Ϊ��", Toast.LENGTH_SHORT).show();
			}else {
				boolean cheaced = cb_isSave.isChecked();
				if(cheaced) {
					//boolean saveInfo = utils.saveInfo(username, pwd);
					//boolean saveInfo = utils.saveInfoByContext(MainActivity.this, username, pwd);
					boolean saveInfo = utils.saveInfo2sdcard(username, pwd);
					if(saveInfo) {
						Toast.makeText(MainActivity.this, "����ɹ�", Toast.LENGTH_SHORT).show();
					}else {
						Toast.makeText(MainActivity.this, "����ʧ��", Toast.LENGTH_SHORT).show();
					}
				}
				Log.d("MainActivity", "��ʼ��¼");
			}
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
}