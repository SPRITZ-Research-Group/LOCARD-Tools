package com.facebook.react.common;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public final class f implements SensorEventListener {
    private static final long a = TimeUnit.NANOSECONDS.convert(20, TimeUnit.MILLISECONDS);
    private static final long b = TimeUnit.NANOSECONDS.convert(250, TimeUnit.MILLISECONDS);
    private static final float c = ((float) TimeUnit.NANOSECONDS.convert(3, TimeUnit.SECONDS));
    private final a d;
    @Nullable
    private SensorManager e;
    private long f;
    private int g;
    private int h;
    private long i;
    @Nullable
    private double[] j;
    @Nullable
    private long[] k;
    private int l;

    public interface a {
        void a();
    }

    public f(a listener) {
        this(listener, (byte) 0);
    }

    private f(a listener, byte b) {
        this.d = listener;
        this.l = 1;
    }

    public final void a(SensorManager manager) {
        com.facebook.infer.annotation.a.a((Object) manager);
        Sensor accelerometer = manager.getDefaultSensor(1);
        if (accelerometer != null) {
            this.e = manager;
            this.f = -1;
            this.g = 0;
            this.j = new double[40];
            this.k = new long[40];
            this.e.registerListener(this, accelerometer, 2);
            this.h = 0;
            this.i = 0;
        }
    }

    public final void a() {
        if (this.e != null) {
            this.e.unregisterListener(this);
            this.e = null;
        }
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.timestamp - this.f >= a) {
            com.facebook.infer.annotation.a.a(this.k);
            com.facebook.infer.annotation.a.a(this.j);
            float ax = sensorEvent.values[0];
            float ay = sensorEvent.values[1];
            float az = sensorEvent.values[2];
            this.f = sensorEvent.timestamp;
            this.k[this.g] = sensorEvent.timestamp;
            this.j[this.g] = Math.sqrt((double) (((ax * ax) + (ay * ay)) + (az * az)));
            long j = sensorEvent.timestamp;
            com.facebook.infer.annotation.a.a(this.k);
            com.facebook.infer.annotation.a.a(this.j);
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < 40; i3++) {
                int i4 = ((this.g - i3) + 40) % 40;
                if (j - this.k[i4] < b) {
                    i++;
                    if (this.j[i4] >= 25.0d) {
                        i2++;
                    }
                }
            }
            if (((double) i2) / ((double) i) > 0.6d) {
                if (j - this.i >= b) {
                    this.h++;
                }
                this.i = j;
                if (this.h >= this.l) {
                    this.h = 0;
                    this.i = 0;
                    this.d.a();
                }
            }
            if (((float) (j - this.i)) > c) {
                this.h = 0;
                this.i = 0;
            }
            this.g = (this.g + 1) % 40;
        }
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }
}
