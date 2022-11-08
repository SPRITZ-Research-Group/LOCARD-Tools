package com.airbnb.lottie;

import android.util.Log;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.obf.ly;
import defpackage.fk;
import java.util.HashSet;
import java.util.Set;

public final class c {
    public static boolean a = false;
    private static final Set<String> b = new HashSet();
    private static boolean c = false;
    private static String[] d;
    private static long[] e;
    private static int f = 0;
    private static int g = 0;

    public static void a() {
    }

    public static void a(String str) {
        if (!b.contains(str)) {
            Log.w("LOTTIE", str);
            b.add(str);
        }
    }

    public static void b(String str) {
        if (!c) {
            return;
        }
        if (f == 20) {
            g++;
            return;
        }
        d[f] = str;
        e[f] = System.nanoTime();
        fk.a(str);
        f++;
    }

    public static float c(String str) {
        if (g > 0) {
            g--;
            return BitmapDescriptorFactory.HUE_RED;
        } else if (!c) {
            return BitmapDescriptorFactory.HUE_RED;
        } else {
            int i = f - 1;
            f = i;
            if (i == -1) {
                throw new IllegalStateException("Can't end trace section. There are none.");
            } else if (str.equals(d[f])) {
                fk.a();
                return ((float) (System.nanoTime() - e[f])) / 1000000.0f;
            } else {
                StringBuilder stringBuilder = new StringBuilder("Unbalanced trace call ");
                stringBuilder.append(str);
                stringBuilder.append(". Expected ");
                stringBuilder.append(d[f]);
                stringBuilder.append(ly.a);
                throw new IllegalStateException(stringBuilder.toString());
            }
        }
    }
}
