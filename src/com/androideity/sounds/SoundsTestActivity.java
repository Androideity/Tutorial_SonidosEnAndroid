package com.androideity.sounds;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SoundsTestActivity extends Activity {
	
	SoundManager snd;
	int laser, explode, pickup, meow, bark, moo;
	OnSeekBarChangeListener barChange;
	OnClickListener buttonClick;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
     // Create an instance of our sound manger
        snd = new SoundManager(getApplicationContext());
        
        // Set volume rocker mode to media volume
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        // Load the samples from res/raw
        laser = snd.load(R.raw.laser);
        explode = snd.load(R.raw.explosion);
        pickup = snd.load(R.raw.pickup);
        meow = snd.load(R.raw.cat);
        bark = snd.load(R.raw.barkloud);
        moo = snd.load(R.raw.cow);
        
        // Create a seek bar handler
        barChange = new OnSeekBarChangeListener() 
        {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {	}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {  }
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
			{
				switch (seekBar.getId())
				{
				 case R.id.VolBar1:
					 snd.setVolume((float)progress/100.0f);
					 break;
					 
				 case R.id.BalBar:
					 snd.setBalance((float)progress/100.0f);
					 break;
					 
				 case R.id.SpeedBar:
					 snd.setSpeed((float)progress/100.0f);
					 break;
				}
			}
		};
        
		// Set our handler as the ChangeListener for the seekbar controls  
        SeekBar sb;
        sb = (SeekBar)findViewById(R.id.SpeedBar);
        sb.setOnSeekBarChangeListener(barChange);
        
        sb = (SeekBar)findViewById(R.id.BalBar);
        sb.setOnSeekBarChangeListener(barChange);  
        
        sb = (SeekBar)findViewById(R.id.VolBar1);
        sb.setOnSeekBarChangeListener(barChange);
    }
    
 // Button listener assigned in XML layout
    public void clickHandler(View v)
    {
    	int id = v.getId(); // Use the button id to determine which sample should be played
    	
    	switch (id)
    	{
    	 case R.id.button1:
    	  snd.play(laser);
    	  Log.i("---","Button1");
    	  break;
    	  
    	 case R.id.button2:
    	  snd.play(explode);
    	  Log.i("---","Button2");
    	  break;
    	   
    	 case R.id.button3:
       	  snd.play(pickup);
       	Log.i("---","Button3");
       	  break;    	  
       	  
    	 case R.id.button4:
          snd.play(meow);
          Log.i("---","Button4");
          break;    	  
          	  
    	 case R.id.button5:
          snd.play(bark);
          Log.i("---","Button5");
          break;    	  
          	  
    	 case R.id.button6:
          snd.play(moo);
          Log.i("---","Button6");
          break;    	  
    	}
    }
}