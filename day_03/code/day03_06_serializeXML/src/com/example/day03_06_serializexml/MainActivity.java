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
    	//获取xml解析器
    	XmlPullParser pullParser = Xml.newPullParser();
    	//设置一个输入
    	try {
			pullParser.setInput(openFileInput("sms.xml"), "utf-8");
			//获取事件类型
			int eventType = pullParser.getEventType();
			//只要没解析到文档结束就一直解析
			while(eventType!= XmlPullParser.END_DOCUMENT){
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if("SMSList".equals(pullParser.getName())){
						//创建集合
						SMSs = new ArrayList<SMS>();
					}else if("SMS".equals(pullParser.getName())){
						//创建对象
						sms = new SMS();
					}else if("from".equals(pullParser.getName())){
						//保存from属性
						sms.from = pullParser.nextText();
					}else if("content".equals(pullParser.getName())){
						//保存content属性
						sms.content = pullParser.nextText();
					}else if("time".equals(pullParser.getName())){
						//保存time属性
						sms.time = pullParser.nextText();
					}
					
					break;

				case XmlPullParser.END_TAG:
					if("SMS".equals(pullParser.getName())){
						//把对象添加到集合
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
    	//获取xml序列化器
    	XmlSerializer serializer = Xml.newSerializer();
    	//给序列化器设置一个输出
    	try {
			serializer.setOutput(openFileOutput("smslist.xml", MODE_PRIVATE), "utf-8");
			//写文档开始的声明<?xml version="1.0"?>
			serializer.startDocument("utf-8", true);
			//第一个参数 名称空间 如果当前文档受某份schema约束就传入一个名称空间 没有约束的情况传null
			//<SMSList>
			serializer.startTag(null, "SMSList");
			for(SMS sms:smsList){
				//写<SMS>
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
    	//写xml的文档声明
    	sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    	sb.append("<SMSList>");
    	for(SMS sms:smsList){
    		//写每一个sms开始的标签
    		sb.append("<SMS>");
    		
    		//一个属性一个属性写对应的节点
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
