package com.androideity.sounds;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager {
	
	private Context pContext;
	private SoundPool sndPool;
	private float rate = 1.0f;
	private float masterVolume = 1.0f;
	private float leftVolume = 1.0f;
	private float rightVolume = 1.0f;
    private float balance = 0.5f;
	
	// Constructor, setup the audio manager and store the app context
	public SoundManager(Context appContext)
	{
	  sndPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 100);
 	  pContext = appContext;
	}
	
	// Load up a sound and return the id
	public int load(int sound_id)
	{
		return sndPool.load(pContext, sound_id, 1);
	}
	
	// Play a sound
	public void play(int sound_id)
	{
		sndPool.play(sound_id, leftVolume, rightVolume, 1, 0, rate); 	
	}	
	
	// Set volume values based on existing balance value
	public void setVolume(float vol)
	{
		masterVolume = vol;
		
		if(balance < 1.0f)
		{
			leftVolume = masterVolume;
			rightVolume = masterVolume * balance;
		}
		else
		{
			rightVolume = masterVolume;
			leftVolume = masterVolume * ( 2.0f - balance );
		}

	}
	
	public void setSpeed(float speed)
	{
		rate = speed;
		
		// Speed of zero is invalid 
		if(rate < 0.01f)
			rate = 0.01f;
		
		// Speed has a maximum of 2.0
		if(rate > 2.0f)
			rate = 2.0f;
	}
	
	public void setBalance(float balVal)
	{
		balance = balVal;
		
		// Recalculate volume levels
		setVolume(masterVolume);
	}
	

	
	// Free ALL the things!
	public void unloadAll()
	{
		sndPool.release();		
	}

}
