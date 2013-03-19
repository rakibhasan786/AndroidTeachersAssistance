package com.comboyz.abc;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class Democlass extends Activity 
{
ArrayList<String> all = new ArrayList<String>();
String yy;
private ListView lvCheckBox;
    
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_listview);
        Intent intent = getIntent();
      all.add("DemoAttendence");
      all.add("Class Test");
      all.add("Assignment");
      all.add("Exam");
      yy=intent.getStringExtra("KeyToAccessData");
      
  
		        
		      
lvCheckBox = (ListView)findViewById(R.id.lvCheckBox);
        lvCheckBox.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvCheckBox.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, all));
       lvCheckBox.setOnItemClickListener(
    	        new OnItemClickListener()
    	        {

    	            public void onItemClick(AdapterView<?> arg0, View view,
    	                    int position, long id) {

    	                Object o = lvCheckBox.getItemAtPosition(position);
    	                String cheese = o.toString();
    	               if(cheese.equals("Class Test"))cheese="ClassTest";
    	               
    	                try{
    	            	    @SuppressWarnings("rawtypes")
    	            	    Class ourClass=Class.forName("com.comboyz.abc."+cheese);
    	            		Intent ourIntent=new Intent(Democlass.this,ourClass);
    	            		ourIntent.putExtra("KeyToAccessData", yy);
    	            		startActivity(ourIntent);
    	            		}catch(ClassNotFoundException e){
    	            			e.printStackTrace();
    	            		}    
    	               
    	               
    	            }

					   
    	        }       
    	);
        
        
    }
	
	
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater=getMenuInflater();
    	inflater.inflate(R.menu.attendence, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     if(item.getItemId()==R.id.item1){
    	 try{
 		    Class<?> ourClass=Class.forName("com.comboyz.abc.Addstudent");
 			Intent ourIntent=new Intent(Democlass.this,ourClass);
 			ourIntent.putExtra("KeyToAccessData", yy);
 			startActivity(ourIntent);
 			}catch(ClassNotFoundException e){
 				e.printStackTrace();
 			}
     }
     
     if(item.getItemId()==R.id.item2){
    	 try{
 		    Class<?> ourClass=Class.forName("com.comboyz.abc.Editstudent");
 			Intent ourIntent=new Intent(Democlass.this,ourClass);
 			ourIntent.putExtra("KeyToAccessData", yy);
 			startActivity(ourIntent);
 			}catch(ClassNotFoundException e){
 				e.printStackTrace();
 			}
     }
     
     
     
    	return super.onOptionsItemSelected(item);
    }	



	
	
	
	

}