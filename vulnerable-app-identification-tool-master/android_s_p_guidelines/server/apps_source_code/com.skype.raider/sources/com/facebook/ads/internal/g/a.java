package com.facebook.ads.internal.g;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class a {
    private static SensorManager a = null;
    private static Sensor b = null;
    private static Sensor c = null;
    private static volatile float[] d;
    private static volatile float[] e;
    private static Map<String, String> f = new ConcurrentHashMap();
    private static String[] g = new String[]{"x", "y", "z"};
    private static SensorEventListener h;
    private static SensorEventListener i;

    private static class a implements SensorEventListener {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final void onAccuracyChanged(Sensor sensor, int i) {
        }

        public final void onSensorChanged(SensorEvent sensorEvent) {
            a.d = sensorEvent.values;
            a.d();
        }
    }

    private static class b implements SensorEventListener {
        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public final void onAccuracyChanged(Sensor sensor, int i) {
        }

        public final void onSensorChanged(SensorEvent sensorEvent) {
            a.e = sensorEvent.values;
            a.e();
        }
    }

    public static Map<String, String> a() {
        Map hashMap = new HashMap();
        hashMap.putAll(f);
        a(hashMap);
        return hashMap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(Context context) {
        Object obj = null;
        synchronized (a.class) {
            MemoryInfo memoryInfo = new MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            f.put("available_memory", String.valueOf(memoryInfo.availMem));
            if (VERSION.SDK_INT >= 16) {
                f.put("total_memory", String.valueOf(memoryInfo.totalMem));
            }
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            f.put("free_space", String.valueOf(((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())));
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver != null) {
                int intExtra = registerReceiver.getIntExtra("level", -1);
                int intExtra2 = registerReceiver.getIntExtra("scale", -1);
                int intExtra3 = registerReceiver.getIntExtra("status", -1);
                if (intExtra3 == 2 || intExtra3 == 5) {
                    obj = 1;
                }
                float f = 0.0f;
                if (intExtra2 > 0) {
                    f = (((float) intExtra) / ((float) intExtra2)) * 100.0f;
                }
                f.put("battery", String.valueOf(f));
                f.put("charging", obj != null ? "1" : "0");
            }
            if (a == null) {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                a = sensorManager;
            }
            if (b == null) {
                b = a.getDefaultSensor(1);
            }
            if (c == null) {
                c = a.getDefaultSensor(4);
            }
            if (h == null) {
                h = new a();
                if (b != null) {
                    a.registerListener(h, b, 3);
                }
            }
            if (i == null) {
                i = new b();
                if (c != null) {
                    a.registerListener(i, c, 3);
                }
            }
        }
    }

    private static void a(Map<String, String> map) {
        int i;
        int i2 = 0;
        float[] fArr = d;
        float[] fArr2 = e;
        if (fArr != null) {
            int min = Math.min(g.length, fArr.length);
            for (i = 0; i < min; i++) {
                map.put("accelerometer_" + g[i], String.valueOf(fArr[i]));
            }
        }
        if (fArr2 != null) {
            i = Math.min(g.length, fArr2.length);
            while (i2 < i) {
                map.put("rotation_" + g[i2], String.valueOf(fArr2[i2]));
                i2++;
            }
        }
    }

    private static synchronized void d() {
        synchronized (a.class) {
            if (a != null) {
                a.unregisterListener(h);
            }
            h = null;
        }
    }

    private static synchronized void e() {
        synchronized (a.class) {
            if (a != null) {
                a.unregisterListener(i);
            }
            i = null;
        }
    }
}
