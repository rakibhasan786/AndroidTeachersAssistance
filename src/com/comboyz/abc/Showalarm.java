package com.comboyz.abc;


import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Showalarm extends Activity implements OnInitListener{

      TextToSpeech textTalker;
      Button b;
      TextView t;
      String yy,th="Thank You!";
      SQLiteDatabase db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showalarm);

        
        
        Intent intent = getIntent();
        yy=intent.getStringExtra("KeyToAccessData");
        
        t=(TextView) findViewById(R.id.showmessage);
        b=(Button) findViewById(R.id.ok);
        textTalker = new TextToSpeech(this, this);
 
        b.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View v)
    		{
    			
    			//dialog after clicking the save button
    			
    			
    			AlertDialog.Builder builder=new AlertDialog.Builder(Showalarm.this);
    			builder.setTitle("conformation");
    			builder.setMessage("Are you sure you want to Attend this class?");
    			builder.setCancelable(false);
    			
    			// specify the yes dialog with its listener
    			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    				
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
			
		        try{
				      db=openOrCreateDatabase("Teachersassistance",SQLiteDatabase.CREATE_IF_NECESSARY,null);
				      db.execSQL("Create Table if not exists tblcrw(cr_serial INTEGER PRIMARY KEY AUTOINCREMENT,cr_type text, cr_course_no text,cr_course_name text,room text, cr_batch text,cr_hour integer,cr_minute integer,cr_day integer,mss text)");
				      Cursor c=db.rawQuery("SELECT * FROM tblcr where mss='"+yy+"'",null);
				      
				      ContentValues values=new ContentValues();
				      c.moveToFirst();
				      while(!c.isAfterLast())
				        {
				      values.put("cr_type","Class");
					   values.put("cr_course_no",c.getString(c.getColumnIndex("cr_course_no")));
			           values.put("cr_course_name",c.getString(c.getColumnIndex("cr_course_name")) );
			           values.put("room",c.getString(c.getColumnIndex("room")));
			           values.put("cr_batch",c.getString(c.getColumnIndex("cr_batch"))); 
			           values.put("cr_hour",Integer.parseInt(c.getString(c.getColumnIndex("cr_hour"))));
			           values.put("cr_minute",Integer.parseInt(c.getString(c.getColumnIndex("cr_minute"))));
			           values.put("cr_day",Integer.parseInt(c.getString(c.getColumnIndex("cr_day"))));
			           values.put("mss",c.getString(c.getColumnIndex("mss")));
			           db.insert("tblcrw", null, values);
			           c.moveToNext();
				        }
				        c.close();
				      db.execSQL("delete from tblcr where mss='"+yy+"'") ;
		        
		        
		        }catch(SQLException e){
				    	  
				      }		
		        textTalker.speak(th, TextToSpeech.QUEUE_FLUSH, null);

				
    				}
    			}); 
    			
    			
    			builder.setNegativeButton("Suspended", new DialogInterface.OnClickListener() {
    				
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
    					///send sms
    					
    					
    					
    					
    					
    					
    					 textTalker.speak("Class is suspended, And A Message will be sent as soon as possible to the Class Representive or Class Captain", TextToSpeech.QUEUE_FLUSH, null);
    					
    					
    					dialog.cancel();
    					
    				}
    			});
    			
    			
    			AlertDialog alert =builder.create();
    			alert.show();
    			
    	           
    	           
    	           
    			
    		}
    	});
       
        
    }
    
    
    public void onDestroy() {
        if (textTalker != null) {
            textTalker.stop();
            textTalker.shutdown();
        }

        super.onDestroy();
    }
    
	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub

		if (status == TextToSpeech.SUCCESS) {

			int result = textTalker.setLanguage(Locale.US);
			
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Toast.makeText(this, "Language not supported", Toast.LENGTH_LONG).show();
			
			} else {
				

			}
			

		} else {
			Log.e("TTS", "Initilization Failed");
		}
		 textTalker.speak(yy, TextToSpeech.QUEUE_FLUSH, null);
		 t.setText(yy);
	}
	
	
	
	protected void onStop() {
        // TODO Auto-generated method stub
        db.close();
        super.onStop();
      }

}