package com.skype.snapshot;

public class MathUtils {

    public static class Rect {
        public final int a;
        public final int b;
        public final float c;

        public Rect(int width, int height, float scaleFactor) {
            this.a = width;
            this.b = height;
            this.c = scaleFactor;
        }
    }

    private static int a(int a, int b) {
        int gcd = Math.min(a, b);
        while (true) {
            if (a % gcd == 0 && b % gcd == 0) {
                return gcd;
            }
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
            gcd = Math.min(a, b);
        }
    }

    public static Rect a(int width, int height, long availableMemory) {
        float sqrt;
        if (a(width, height) > 50) {
            int a = a(width, height);
            sqrt = ((float) ((int) (Math.sqrt(((double) availableMemory) / ((double) ((width * 4) * height))) * ((double) a)))) / ((float) a);
            return new Rect((int) (((float) width) * sqrt), (int) (((float) height) * sqrt), sqrt);
        }
        sqrt = (float) Math.sqrt(((double) availableMemory) / ((double) ((width * height) * 4)));
        return new Rect((int) (((float) width) * sqrt), (int) (((float) height) * sqrt), sqrt);
    }
}
