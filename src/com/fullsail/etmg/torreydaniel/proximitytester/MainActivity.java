package com.fullsail.etmg.torreydaniel.proximitytester;

import com.fullsail.etmg.torreydaniel.proximitytester.MainActivity;
import com.fullsail.etmg.torreydaniel.proximitytester.R;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends Activity implements SensorEventListener
{
	private SensorManager mSensorManager;
	private Sensor mSensorListener;

	private MediaPlayer mp;
	private AudioManager audioManager;
	
	Sensor mProximity;
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorListener = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		
		mSensorManager.registerListener(this, mSensorListener, SensorManager.SENSOR_DELAY_NORMAL);
		
		mp = MediaPlayer.create(this, R.raw.hello);
		audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE); 
		audioManager.setMode(AudioManager.MODE_IN_CALL);
		audioManager.setSpeakerphoneOn(true);
	}

	@Override
	protected void onResume() 
	{
		super.onResume();
		audioManager.setMode(AudioManager.MODE_IN_CALL);
		audioManager.setSpeakerphoneOn(true);
		
		//mSensorManager.registerListener(this, mSensorListener, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() 
	{
		//mSensorManager.unregisterListener(mSensorListener);
				
		audioManager.setMode(AudioManager.MODE_NORMAL);
		audioManager.setSpeakerphoneOn(false);
		super.onPause();
	}
	

	@Override
	public void onSensorChanged(SensorEvent event) 
	{
		// TODO Auto-generated method stub
		if (event.values[0] > 0)
		{
			mp.start();
			Toast.makeText(MainActivity.this, "HELLO!",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) 
	{
		//proText.setText(String.valueOf(event.values[0]));
		
	}

}
