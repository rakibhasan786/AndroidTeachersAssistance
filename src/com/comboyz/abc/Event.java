package com.comboyz.abc;
import java.util.ArrayList;
import java.util.StringTokenizer;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class Event extends Activity 
{
ArrayList<String> all = new ArrayList<String>();

private ListView lvCheckBox;
    
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_listview);
       
        all.add("Class Routine");
        all.add("Lab Schedule");
        all.add("Meeting Schedule");
        all.add("Examination Schedue");
        all.add("Class Test Schedule");
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
    	                // TODO Auto-generated method stub
    	                Object o = lvCheckBox.getItemAtPosition(position);
    	                String cheese = o.toString();
    	                if(cheese.equals("Others"))cheese="Others"; 
    	                else if(cheese.equals("Class Test Schedule")) cheese="ClassTestSchedule";
    	                else{
    	                StringTokenizer st = new StringTokenizer(cheese," ");
    	                String c1=(String) st.nextElement();
    	                String c2=(String) st.nextElement();
    	                cheese=c1+c2;}
    	                try{
    	            	    @SuppressWarnings("rawtypes")
							Class ourClass=Class.forName("com.comboyz.abc."+cheese);
    	            		Intent ourIntent=new Intent(Event.this,ourClass);
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
	    	inflater.inflate(R.menu.mainmenu, menu);
	    	return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	     if(item.getItemId()==R.id.item1){
	    	 try{
	    		    Class<?> ourClass=Class.forName("com.comboyz.abc.Startschedule");
	    			Intent ourIntent=new Intent(Event.this,ourClass);
	    			startActivity(ourIntent);
	    			}catch(ClassNotFoundException e){
	    				e.printStackTrace();
	    			}
	    	 
	     }
	    	return super.onOptionsItemSelected(item);
	    }


	
	
	
	

}