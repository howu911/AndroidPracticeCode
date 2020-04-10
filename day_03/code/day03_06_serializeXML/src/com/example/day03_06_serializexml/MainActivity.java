package com.example.day03_06_serializexml;

import java.io.FileOutputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	ArrayList<SMS> smsList = new ArrayList<SMS>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		for(int i = 0;i<30;i++){
        	SMS sms = new SMS();
        	sms.from= "10000"+i;
        	sms.content="content"+i;
        	sms.time="2016-9-4 16:25:"+i;
        	smsList.add(sms);
        }
        
        for(SMS sms: smsList){
        	System.out.println(sms);
        }
	}
	
	public void parseSMS(View v){
    	ArrayList<SMS> SMSs = null;
    	SMS sms = null;
    	//��ȡxml������
    	XmlPullParser pullParser = Xml.newPullParser();
    	//����һ������
    	try {
			pullParser.setInput(openFileInput("sms.xml"), "utf-8");
			//��ȡ�¼�����
			int eventType = pullParser.getEventType();
			//ֻҪû�������ĵ�������һֱ����
			while(eventType!= XmlPullParser.END_DOCUMENT){
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if("SMSList".equals(pullParser.getName())){
						//��������
						SMSs = new ArrayList<SMS>();
					}else if("SMS".equals(pullParser.getName())){
						//��������
						sms = new SMS();
					}else if("from".equals(pullParser.getName())){
						//����from����
						sms.from = pullParser.nextText();
					}else if("content".equals(pullParser.getName())){
						//����content����
						sms.content = pullParser.nextText();
					}else if("time".equals(pullParser.getName())){
						//����time����
						sms.time = pullParser.nextText();
					}
					
					break;

				case XmlPullParser.END_TAG:
					if("SMS".equals(pullParser.getName())){
						//�Ѷ�����ӵ�����
						SMSs.add(sms);
					}
					break;
				}
				
				eventType = pullParser.next();
			}
			for(SMS sms1:SMSs){
				System.out.println(sms1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	public void saveSMS2(View v){
    	//��ȡxml���л���
    	XmlSerializer serializer = Xml.newSerializer();
    	//�����л�������һ�����
    	try {
			serializer.setOutput(openFileOutput("smslist.xml", MODE_PRIVATE), "utf-8");
			//д�ĵ���ʼ������<?xml version="1.0"?>
			serializer.startDocument("utf-8", true);
			//��һ������ ���ƿռ� �����ǰ�ĵ���ĳ��schemaԼ���ʹ���һ�����ƿռ� û��Լ���������null
			//<SMSList>
			serializer.startTag(null, "SMSList");
			for(SMS sms:smsList){
				//д<SMS>
				serializer.startTag(null, "SMS");
				
				serializer.startTag(null, "from");
				serializer.text(sms.from);
				serializer.endTag(null, "from");
				
				serializer.startTag(null, "content");
				serializer.text(sms.content);
				serializer.endTag(null, "content");
				
				serializer.startTag(null, "time");
				serializer.text(sms.time);
				serializer.endTag(null, "time");
				
				//</SMS>
				serializer.endTag(null, "SMS");
			}
			
			//</SMSList>
			serializer.endTag(null, "SMSList");
			serializer.endDocument();
		} catch (Exception e) {
			e.printStackTrace();
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
	public void saveSMS(View v){
    	StringBuilder sb = new StringBuilder();
    	//дxml���ĵ�����
    	sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    	sb.append("<SMSList>");
    	for(SMS sms:smsList){
    		//дÿһ��sms��ʼ�ı�ǩ
    		sb.append("<SMS>");
    		
    		//һ������һ������д��Ӧ�Ľڵ�
    		sb.append("<from>");
    		sb.append(sms.from);
    		sb.append("</from>");
    		
    		sb.append("<content>");
    		sb.append(sms.content);
    		sb.append("</content>");
    		
    		sb.append("<time>");
    		sb.append(sms.time);
    		sb.append("</time>");
    		
    		sb.append("</SMS>");
    	}
    	sb.append("</SMSList>");
    	
    	String xml = sb.toString();
    	try {
			FileOutputStream fos = openFileOutput("sms.xml", MODE_PRIVATE);
			fos.write(xml.getBytes());
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
