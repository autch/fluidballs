package net.autch.android.bouncetest;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class BouncingSurfaceView extends SurfaceView implements Callback {
	private final SurfaceHolder holder;
	private FluidBallsThread thread;

	public BouncingSurfaceView(Context context) {
		super(context);
		holder = getHolder();
		holder.addCallback(this);
		holder.setFixedSize(getWidth(), getHeight());
	}

	public void surfaceChanged(SurfaceHolder arg0, int format, int width, int height) {
		thread = new FluidBallsThread(holder, width, height);
		new Thread(thread).start();
	}

	public void surfaceCreated(SurfaceHolder arg0) {
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		thread.terminate();
		thread = null;
	}

	public FluidBallsThread getThread() {
		return thread;
	}

}
