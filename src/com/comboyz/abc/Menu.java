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



public class Menu extends Activity 
{
ArrayList<String> all = new ArrayList<String>();

private ListView lvCheckBox;
    
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_listview);
       
      all.add("All class");
      all.add("Event");
      all.add("Lab");
      all.add("Meeting");
      all.add("Examination");
      all.add("Class Test");
      all.add("Others");
      
  
		        
		      
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
    	               if(cheese.equals("All class"))cheese="Allclass";
    	               if(cheese.equals("Class Test"))cheese="ClassTest";
    	               
    	                try{
    	            	    @SuppressWarnings("rawtypes")
							Class ourClass=Class.forName("com.comboyz.abc."+cheese);
    	            		Intent ourIntent=new Intent(Menu.this,ourClass);
    	            		startActivity(ourIntent);
    	            		}catch(ClassNotFoundException e){
    	            			e.printStackTrace();
    	            		}    
    	               
    	               
    	            }

					   
    	        }       
    	);
        
        
    }
	
	
	



	
	
	
	

}