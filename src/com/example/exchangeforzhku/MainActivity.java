package com.example.exchangeforzhku;

import com.example.exchangeforzhku.view.ExchangeforZhku;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ExchangeforZhku mLeftMenu;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mLeftMenu = (ExchangeforZhku) findViewById(R.id.id_menu);
		Button homepage = (Button) findViewById(R.id.id_homepage);
		Button mymessage = (Button) findViewById(R.id.id_mymessage);
		Button setting = (Button) findViewById(R.id.id_setting);
		Button out = (Button) findViewById(R.id.id_out);
		 homepage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				Toast.makeText(getApplicationContext(), "��ҳ", Toast.LENGTH_LONG).show();
			}
		});		
		 mymessage.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO �Զ����ɵķ������
					Toast.makeText(getApplicationContext(), "�ҵ���Ϣ", Toast.LENGTH_LONG).show();
				}
			});
		 setting.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO �Զ����ɵķ������
					Intent intent = new Intent(MainActivity.this,login.class);
					startActivity(intent);

				}
			});
		 out.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO �Զ����ɵķ������
					Toast.makeText(getApplicationContext(), "�˳�", Toast.LENGTH_LONG).show();
				}
			});
	}
	public void toggleMenu(View View){
    	mLeftMenu.toggle();
    }

	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
}
