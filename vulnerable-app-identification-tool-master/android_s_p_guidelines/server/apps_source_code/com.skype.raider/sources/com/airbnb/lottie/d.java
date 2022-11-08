package com.airbnb.lottie;

public final class d {
    private static boolean a = false;
    private static String[] b;
    private static long[] c;
    private static int d = 0;
    private static int e = 0;

    public static void a(String section) {
        if (!a) {
            return;
        }
        if (d == 20) {
            e++;
            return;
        }
        b[d] = section;
        c[d] = System.nanoTime();
        android.support.v4.os.d.a(section);
        d++;
    }

    public static float b(String section) {
        if (e > 0) {
            e--;
            return 0.0f;
        } else if (!a) {
            return 0.0f;
        } else {
            int i = d - 1;
            d = i;
            if (i == -1) {
                throw new IllegalStateException("Can't end trace section. There are none.");
            } else if (section.equals(b[d])) {
                android.support.v4.os.d.a();
                return ((float) (System.nanoTime() - c[d])) / 1000000.0f;
            } else {
                throw new IllegalStateException("Unbalanced trace call " + section + ". Expected " + b[d] + ".");
            }
        }
    }
}
