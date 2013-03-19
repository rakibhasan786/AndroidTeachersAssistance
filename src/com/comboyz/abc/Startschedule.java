package com.comboyz.abc;

import java.util.Calendar;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Startschedule extends Activity {

	String yy;
	SQLiteDatabase db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startschedule);
        try{
		      db=openOrCreateDatabase("Teachersassistance",SQLiteDatabase.CREATE_IF_NECESSARY,null);
		      }catch(SQLException e){
		    	  
		      }       
        
        Button startschedule=(Button) findViewById(R.id.startschedule);
        startschedule.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View v)
    		{
    			
    			//dialog after clicking the save button
    			
    			
    			AlertDialog.Builder builder=new AlertDialog.Builder(Startschedule.this);
    			builder.setTitle("Save");
    			builder.setMessage("Are you sure you want to Start Schedule Service");
    			builder.setCancelable(false);
    			
    			// specify the yes dialog with its listener
    			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    				
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
    					
    					int RQS_1 = 0;			

    					Calendar calSet = Calendar.getInstance();
    					
    					Cursor c=db.rawQuery("SELECT * FROM tblcr",null);
    			        c.moveToFirst();
    			        while(!c.isAfterLast())
    			        {
    			        	calSet.set(Calendar.HOUR_OF_DAY,Integer.parseInt(c.getString(c.getColumnIndex("cr_hour"))));
        					calSet.set(Calendar.MINUTE, Integer.parseInt(c.getString(c.getColumnIndex("cr_minute"))));
        					calSet.set(Calendar.SECOND, 0);
        					calSet.set(Calendar.MILLISECOND, 0);
        					calSet.set(Calendar.DAY_OF_WEEK, Integer.parseInt(c.getString(c.getColumnIndex("cr_day"))));	
        					Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        					yy=c.getString(c.getColumnIndex("mss"));
        					RQS_1=Integer.parseInt(c.getString(c.getColumnIndex("cr_serial")));
        					intent.putExtra("KeyToAccessData", yy);
        					PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        					AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        					alarmManager.set(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), pendingIntent);
        					
        					
        					
    			        c.moveToNext();
    			        }
    			        c.close();	
    				}
    			}); 
    			
    			
    			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
    				
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
    					dialog.cancel();
    					
    				}
    			});
    			
    			
    			AlertDialog alert =builder.create();
    			alert.show();
    			
    	           
    	           
    	           
    			
    		}
    	});
    			
    }
    
	   
    
    
    
}