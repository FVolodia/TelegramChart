package com.alexvasilkov.telegram.chart.utils;

import android.os.SystemClock;
import android.util.Log;

class Fps {

    private static final String TAG = "GestureFps";

    private static final long WARNING_TIME = 20L; // Dropping less than 60 fps in average
    private static final long ERROR_TIME = 40L; // Dropping less than 30 fps in average

    private long frameStart;
    private long animationStart;
    private int framesCount;

    void start() {
        animationStart = frameStart = SystemClock.elapsedRealtime();
        framesCount = 0;
    }

    void stop() {
        if (framesCount > 0) {
            int time = (int) (SystemClock.elapsedRealtime() - animationStart);
            Log.d(TAG, "Average FPS: " + Math.round(1000f * framesCount / time));
        }
    }

    void step() {
        long frameTime = SystemClock.elapsedRealtime() - frameStart;
        if (frameTime > ERROR_TIME) {
            Log.e(TAG, "Frame time: " + frameTime);
        } else if (frameTime > WARNING_TIME) {
            Log.w(TAG, "Frame time: " + frameTime);
        }

        framesCount++;
        frameStart = SystemClock.elapsedRealtime();
    }

}
