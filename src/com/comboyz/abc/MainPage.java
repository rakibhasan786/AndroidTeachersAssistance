package com.comboyz.abc;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainPage extends Activity{
SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle abcd) {
		super.onCreate(abcd);
		setContentView(R.layout.mainpage);

		 db=openOrCreateDatabase("Teachersassistance",SQLiteDatabase.CREATE_IF_NECESSARY,null);
	    db.execSQL("Create table if not exists allclass(title text,number text)");
	    db.close();
		Thread timer=new Thread(){
			public void run(){
				try{
					sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openStartingPoint=new Intent("com.comboyz.abc.MENU");
					startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	

}
