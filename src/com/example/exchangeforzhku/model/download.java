package com.example.exchangeforzhku.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.exchangeforzhku.model.NetWorkUtil;

//这是一个下载并宣示图片的类，需要参数是图片在数据库的id，并要宣示的imageview的实例
//试着下载图片，下面是调用方法
//new download().downLoad(1, imageView);
public class download {
	// 这是下载图片的接口地址
	private static String url2 = "http://1.exchangeforzhku.sinaapp.com/getpic.php";
	// 创建json实例
	static JSONParser jsonParser = new JSONParser();

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

	private static Drawable decode(String value) {
		// TODO 自动生成的方法存根
		final String s = value;
		System.out.println("进入showpic中，value=" + s);
		byte[] b2 = new Base64Coder().decodeLines(s);
		Bitmap dBitmap4 = BitmapFactory.decodeByteArray(b2, 0, b2.length);
		Drawable drawable4 = new BitmapDrawable(dBitmap4);
		System.out.println("开始绘图");
		return drawable4;
		// imageView.setBackgroundDrawable(drawable4);
	}

	public static void downLoad(int id, ImageView imageView2) {
		// 透过id来获取图片
		final String ID = Integer.toString(id);
		final ImageView imageView = imageView2;
		Drawable dra;
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("进入获取图片");
				List<NameValuePair> params2 = new ArrayList<NameValuePair>();
				params2.add(new BasicNameValuePair("id", ID));
				System.out.println("进入post2");
				JSONObject json2 = jsonParser.makeHttpRequest(url2, "POST",
						params2);
				try {
					int success = json2.getInt("success");
					final String value = json2.getString("message");
					final Drawable dra2 = decode(value);
					System.out.println("开始画图");
					imageView.post(new Runnable() {
						public void run() {
							System.out.println("进入run，开始画图");
							imageView.setBackgroundDrawable(dra2);
						}
					});
					
				} catch (JSONException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}

		}).start();
	}
}
