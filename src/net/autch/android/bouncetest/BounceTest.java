package net.autch.android.bouncetest;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;

public class BounceTest extends Activity implements SensorEventListener{
	private SensorManager	sensorManager;
	private Sensor			acceleroMeter;
	private BouncingSurfaceView surfaceView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if(list.size() > 0) acceleroMeter = list.get(0);

		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		surfaceView = new BouncingSurfaceView(this);
		setContentView(surfaceView);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// ignore
	}

	public void onSensorChanged(SensorEvent event) {
		if(event.sensor == acceleroMeter) {
			if(surfaceView.getThread() != null)
				surfaceView.getThread().setAccel(event.values);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		if(acceleroMeter != null)
			sensorManager.registerListener(this, acceleroMeter, SensorManager.SENSOR_DELAY_FASTEST);
	}

	@Override
	protected void onStop() {
		super.onStop();

		sensorManager.unregisterListener(this);
	}
}