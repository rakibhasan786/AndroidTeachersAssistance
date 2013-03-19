package com.comboyz.abc;



import android.app.Activity;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Addclass extends Activity {
	SQLiteDatabase db;
	Button insertclass;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addclass);
       // EditText jtitle=(EditText) findViewById(R.id.text_field_course_title);
       // String ssql="Create Table if not exists"+jtitle.getText().toString()+"(title text,number text)";
 
        insertclass=(Button) findViewById(R.id.buttoninsertclass);
        try{
            db=openOrCreateDatabase("Teachersassistance",SQLiteDatabase.CREATE_IF_NECESSARY,null);
           db.execSQL("Create Table if not exists allclass(title text,number text)");
           // db.execSQL(ssql);
            }catch(SQLException e)
            {
            }
            insertclass.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					EditText jtitle=(EditText) findViewById(R.id.text_field_course_title);
			        EditText jnumber=(EditText)findViewById(R.id.text_field_course_number);
			        db.execSQL("Create Table if not exists '"+jtitle.getText().toString()+"'(serial INTEGER PRIMARY KEY AUTOINCREMENT,id text,name text,attendence integer,ct1 real,ct2 real,ct3 real,ct4 real,ct5 real,avg real,assignment real,exam real,total real,grad text)");
			        
			           ContentValues values=new ContentValues();
			           values.put("title", jtitle.getText().toString());
			           values.put("number", jnumber.getText().toString());
			           if((db.insert("allclass", null, values))!=-1)
			           {
			        	   Toast.makeText(Addclass.this, "Successfully "+jtitle.getText().toString()+" Inserted", 2000).show();
			           }
			           else
			           {
			           Toast.makeText(Addclass.this, "Insert Error", 2000).show();
			           }
			           jtitle.setText("");
			           jnumber.setText("");
					
					
					
					
					
				}
			});
            
        
    }
    
    
    protected void onStop() {
        // TODO Auto-generated method stub
        db.close();
        super.onStop();
      }
    
   
}