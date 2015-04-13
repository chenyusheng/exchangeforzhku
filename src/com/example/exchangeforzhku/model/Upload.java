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

//�ϴ�ͼƬ����
//��ֻ�ǲ���
//��Ҫ����Bitmap photo
public class Upload {
	//json���ڽ������������ͨѶ
	static JSONParser jsonParser = new JSONParser();
	JSONParser jsonParser2 = new JSONParser();
	JSONObject json2 = new JSONObject();
	//�ϴ��Ľӿڵ�ַ
	final static String url = "http://1.exchangeforzhku.sinaapp.com/test.php";

	//����һ��ת�뺯������ͼƬ����ת����string��
	public static String encode(Bitmap photo) {
		Drawable drawable = new BitmapDrawable(photo);
		/**
		 * ����ע�͵ķ����ǽ��ü�֮���ͼƬ��Base64Coder���ַ���ʽ�� ������������QQͷ���ϴ����õķ������������
		 */
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		photo.compress(Bitmap.CompressFormat.JPEG, 60, stream);
		byte[] bytes = stream.toByteArray();
		// ��ͼƬ�����ַ�����ʽ�洢����
		final String picStr = new String(Base64Coder.encodeLines(bytes));
		return picStr;
	}

	//ʵ���ϴ�ͼƬ����Ҫ����
	public static boolean upload(Bitmap photo) {

		int success2 = 1;
		//��ͼƬ����ת�����
		final String picStr = encode(photo);
		new Thread(new Runnable() {
			public void run(int success2) {
				System.out.println("����run");
				System.out.println("�����ݷŽ�pair��");
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("picStr", picStr));
				params.add(new BasicNameValuePair("picName", "dengzi"));
				// final String result = NetWorkUtil.httpPost(url, params);
				System.out.println("��ʼpost������");
				JSONObject json2 = jsonParser.makeHttpRequest(url, "POST",
						params);
				System.out.println(params);
				try {
					System.out.println("����try����ȡsuccessֵ");
					int success=json2.getInt("success");
					success2=success;
					System.out.println("success="+success2);
								//return false;
					
				} catch (JSONException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				
			}

			@Override
			public void run() {
				// TODO �Զ����ɵķ������
				
			}

		}).start();
		// ������ص��ķ����������ݻ�����Base64Coder����ʽ�Ļ������������·�ʽת��
		// Ϊ���ǿ����õ�ͼƬ���;�OK��...���
		// Bitmap dBitmap = BitmapFactory.decodeFile(tp);
		// Drawable drawable = new BitmapDrawable(dBitmap);
		System.out.println("��ʼ�ж��Ƿ��ϴ��ɹ�");
		if(success2==1){
			return true;
		}else{
			return false;
		}

	}

}
