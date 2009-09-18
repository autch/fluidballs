package net.autch.android.bouncetest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public final class FluidBallsThread extends Thread {
	private Canvas canvas;
	private final SurfaceHolder holder;
	private boolean terminate;
	private final Paint paint;
	private final int count;

	private static final int FPS = 30;

	private native void fluidballs_init(int count, int width, int height);
	private native void fluidballs_exit();
	private native void fluidballs_setaccel(float ax, float ay);
	private native void fluidballs_drawballs();
	private native void fluidballs_update();

	static {
		System.loadLibrary("fluidballs");
	}

	public FluidBallsThread(SurfaceHolder holder, int width, int height) {
		this.holder = holder;

		count = 100;
		fluidballs_init(count, width, height);

		canvas = null;
		terminate = false;

		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(1);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.argb(255, 255, 255, 0));
	}

	public void fluidballs_draw() {
		fluidballs_drawballs();
		fluidballs_update();
	}

	public void drawBall(float x, float y, float r) {
		canvas.drawCircle(x, y, r, paint);
	}

	@Override
	public void run() {
		int width, height;
		Paint backFill = new Paint();

		backFill.setAntiAlias(false);
		backFill.setStyle(Paint.Style.FILL);
		backFill.setColor(Color.BLACK);

		try {
			while(!terminate) {
				long start = System.currentTimeMillis();

				canvas = holder.lockCanvas();
				width = canvas.getWidth();
				height = canvas.getHeight();
				try {
					canvas.drawRect(0, 0, width, height, backFill);
					fluidballs_draw();
				} finally {
					holder.unlockCanvasAndPost(canvas);
					canvas = null;
				}

				try {
					long now = System.currentTimeMillis();
					int msToWait = 1000 / FPS;

					msToWait -= (now - start);
					if(msToWait <= 0) msToWait = 10;

					Thread.sleep(msToWait);
				} catch(InterruptedException e) {

				}
			}
		} finally {
			fluidballs_exit();
		}
	}

	public void setAccel(float[] values) {
		fluidballs_setaccel((float)(-values[0] / 10.0), (float)(values[1] / 10.0));
	}

	public void terminate() {
		terminate = true;
	}

}
