package com.example.quickaccess;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {
GestureLibrary lib;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lib=GestureLibraries.fromRawResource(this,R.raw.gestures);
        GestureOverlayView gest=(GestureOverlayView)findViewById(R.id.gest);
    if(!lib.load())
    {
    	Log.d("Message", "could not load gesture lib");
    }
    
    gest.addOnGesturePerformedListener(new OnGesturePerformedListener() {
		
		//private Gesture gestures;

		@Override
		public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
			// TODO Auto-generated method stub
		ArrayList<Prediction> predections= lib.recognize(gesture);
		if(predections.size()>0)
		{
			Prediction prediction=predections.get(0);
			if(prediction.score>1.0)
			{
				//Toast.makeText(MainActivity.this, prediction.name+"gesture"+prediction.score, Toast.LENGTH_SHORT).show();
				String name=prediction.name;
				if(name.equals("dailer"))
				{
					intent=new Intent(intent.ACTION_DIAL,Uri.parse("tel:"));
					
					Intent chooser=Intent.createChooser(intent, "DAILER");
					
					startActivity(chooser);
				
				}
				
				if(name.equals("message"))
				{
					intent = new Intent(Intent.ACTION_MAIN);
					 intent.setType("vnd.android-dir/mms-sms");	
					 startActivity(intent);
				}
				if(name.equals("email"))
				{
					intent=new Intent(Intent.ACTION_SEND);
					intent.setData(Uri.parse("mailto:"));
					intent.setType("message/rfc822");
					Intent chooser=Intent.createChooser(intent,"SEND MESSAGE");
					startActivity(chooser);
				}
				if(name.equals("google map"))
				{
					intent =new Intent (android.content.Intent.ACTION_VIEW);
					intent.setData(Uri.parse("geo:"));
					Intent chooser=Intent.createChooser(intent, "MAP LAUNCH");
					startActivity(chooser);
				}
				if(name.equals("playstore"))
				{
					intent=new Intent(android.content.Intent.ACTION_VIEW);
					intent.setData(Uri.parse("market:"));
					Intent chooser=Intent.createChooser(intent, "LAUNCH MARKET");
					startActivity(chooser);
				}
				if(name.equals("camera"))
				{
					intent=new Intent("android.media.action.IMAGE_CAPTURE");
				    startActivity(intent);
				}
				if(name.equals("facebook"))
				{
					intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"));
				     startActivity(intent);
				}
				
			}
					
}
		}	
		});
    }


}
