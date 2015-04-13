package com.example.exchangeforzhku.model;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

//上传图片的类
//这只是测试
//需要参数Bitmap photo
public class Upload {
	//json用于建立与服务器的通讯
	static JSONParser jsonParser = new JSONParser();
	JSONParser jsonParser2 = new JSONParser();
	JSONObject json2 = new JSONObject();
	//上传的接口地址
	final static String url = "http://1.exchangeforzhku.sinaapp.com/test.php";

	//这是一个转码函数，把图片数据转换成string流
	public static String encode(Bitmap photo) {
		Drawable drawable = new BitmapDrawable(photo);
		/**
		 * 下面注释的方法是将裁剪之后的图片以Base64Coder的字符方式上 传到服务器，QQ头像上传采用的方法跟这个类似
		 */
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		photo.compress(Bitmap.CompressFormat.JPEG, 60, stream);
		byte[] bytes = stream.toByteArray();
		// 将图片流以字符串形式存储下来
		final String picStr = new String(Base64Coder.encodeLines(bytes));
		return picStr;
	}

	//实现上传图片的主要函数
	public static boolean upload(Bitmap photo) {

		int success2 = 1;
		//对图片进行转码加密
		final String picStr = encode(photo);
		new Thread(new Runnable() {
			public void run(int success2) {
				System.out.println("进入run");
				System.out.println("把数据放进pair中");
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("picStr", picStr));
				params.add(new BasicNameValuePair("picName", "dengzi"));
				// final String result = NetWorkUtil.httpPost(url, params);
				System.out.println("开始post！！！");
				JSONObject json2 = jsonParser.makeHttpRequest(url, "POST",
						params);
				System.out.println(params);
				try {
					System.out.println("进入try，获取success值");
					int success=json2.getInt("success");
					success2=success;
					System.out.println("success="+success2);
								//return false;
					
				} catch (JSONException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}

			@Override
			public void run() {
				// TODO 自动生成的方法存根
				
			}

		}).start();
		// 如果下载到的服务器的数据还是以Base64Coder的形式的话，可以用以下方式转换
		// 为我们可以用的图片类型就OK啦...吼吼
		// Bitmap dBitmap = BitmapFactory.decodeFile(tp);
		// Drawable drawable = new BitmapDrawable(dBitmap);
		System.out.println("开始判断是否上传成功");
		if(success2==1){
			return true;
		}else{
			return false;
		}

	}

}
