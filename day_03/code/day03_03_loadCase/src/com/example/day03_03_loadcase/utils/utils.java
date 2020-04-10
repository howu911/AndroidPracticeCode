package com.example.day03_03_loadcase.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.os.Environment;

public class utils {
	
	//存储信息，通过绝对路径
	public static boolean saveInfo(String username, String pwd) {
		String Info = username + "##" + pwd;
		File file = new File("data/data/com.example.day03_03_loadcase/info.txt");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(Info.getBytes());
			fos.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	//通过上下文进行存储
	public static boolean saveInfoByContext(Context context, String username, String pwd) {
		String info = username + "##" + pwd;
		//使用上下文获取私有文件路径
		File file = new File(context.getFilesDir().getAbsolutePath() + "/info.txt");
		try {
			FileOutputStream fos = context.openFileOutput("info.txt", context.MODE_PRIVATE);
			//FileOutputStream fos = context.openFileOutput("info2.txt", Context.MODE_PRIVATE);也可以通过这种方式
			fos.write(info.getBytes());
			fos.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	//通过SdCard进行存储
	public static boolean saveInfo2sdcard(String username, String pwd) {
		String info = username+"##"+pwd;
		//File file = new File("mnt/sdcard/info.txt");
		File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/info.txt");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(info.getBytes());
			fos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//读取文件
	public static String[] readInfo() {
		File file = new File("data/data/com.example.day03_03_loadcase/info.txt");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			String temp = reader.readLine();
			String[] result = temp.split("##");
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//通过上下文读取
	public static String[] readInfoByContext(Context context) {
		//File file = new File("data/data/com.example.day03_03_loadcase/info.txt");
		FileInputStream fis;
		try {
			fis = context.openFileInput("info.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			String temp = reader.readLine();
			String[] result = temp.split("##");
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[] readInfoBySdcard() {
		File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/info.txt");
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			String temp = reader.readLine();
			String[] result = temp.split("##");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
