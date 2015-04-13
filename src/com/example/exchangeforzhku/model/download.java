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

//����һ�����ز���ʾͼƬ���࣬��Ҫ������ͼƬ�����ݿ��id����Ҫ��ʾ��imageview��ʵ��
//��������ͼƬ�������ǵ��÷���
//new download().downLoad(1, imageView);
public class download {
	// ��������ͼƬ�Ľӿڵ�ַ
	private static String url2 = "http://1.exchangeforzhku.sinaapp.com/getpic.php";
	// ����jsonʵ��
	static JSONParser jsonParser = new JSONParser();

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

	}

	private static Drawable decode(String value) {
		// TODO �Զ����ɵķ������
		final String s = value;
		System.out.println("����showpic�У�value=" + s);
		byte[] b2 = new Base64Coder().decodeLines(s);
		Bitmap dBitmap4 = BitmapFactory.decodeByteArray(b2, 0, b2.length);
		Drawable drawable4 = new BitmapDrawable(dBitmap4);
		System.out.println("��ʼ��ͼ");
		return drawable4;
		// imageView.setBackgroundDrawable(drawable4);
	}

	public static void downLoad(int id, ImageView imageView2) {
		// ͸��id����ȡͼƬ
		final String ID = Integer.toString(id);
		final ImageView imageView = imageView2;
		Drawable dra;
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("�����ȡͼƬ");
				List<NameValuePair> params2 = new ArrayList<NameValuePair>();
				params2.add(new BasicNameValuePair("id", ID));
				System.out.println("����post2");
				JSONObject json2 = jsonParser.makeHttpRequest(url2, "POST",
						params2);
				try {
					int success = json2.getInt("success");
					final String value = json2.getString("message");
					final Drawable dra2 = decode(value);
					System.out.println("��ʼ��ͼ");
					imageView.post(new Runnable() {
						public void run() {
							System.out.println("����run����ʼ��ͼ");
							imageView.setBackgroundDrawable(dra2);
						}
					});
					
				} catch (JSONException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}

		}).start();
	}
}
