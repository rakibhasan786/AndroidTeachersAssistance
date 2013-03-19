package com.comboyz.abc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		
		Bundle bundle = arg1.getExtras();
		String smsText = bundle.getString("KeyToAccessData");
		Toast.makeText(arg0, smsText, Toast.LENGTH_LONG).show();
		
		
		Intent i = new Intent();
        i.setClassName(arg0, "com.comboyz.abc.Showalarm");
        i.putExtra("KeyToAccessData", smsText);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        arg0.startActivity(i);

	}

}
