package com.skpcamera.antediluvian;

import java.nio.FloatBuffer;

public final class l {
    private static final float[] a = new float[]{0.0f, 0.57735026f, -0.5f, -0.28867513f, 0.5f, -0.28867513f};
    private static final float[] b = new float[]{0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
    private static final FloatBuffer c = p.a(a);
    private static final FloatBuffer d = p.a(b);
    private static final float[] e = new float[]{-0.5f, -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f};
    private static final float[] f = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    private static final FloatBuffer g = p.a(e);
    private static final FloatBuffer h = p.a(f);
    private static final float[] i = new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    private static final float[] j = new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
    private static final FloatBuffer k = p.a(i);
    private static final FloatBuffer l = p.a(j);
    private FloatBuffer m;
    private FloatBuffer n;
    private int o;
    private int p;
    private int q;
    private int r;
    private a s;

    public enum a {
        TRIANGLE,
        RECTANGLE,
        FULL_RECTANGLE
    }

    public l(a shape) {
        switch (shape) {
            case TRIANGLE:
                this.m = c;
                this.n = d;
                this.p = 2;
                this.q = this.p * 4;
                this.o = a.length / this.p;
                break;
            case RECTANGLE:
                this.m = g;
                this.n = h;
                this.p = 2;
                this.q = this.p * 4;
                this.o = e.length / this.p;
                break;
            case FULL_RECTANGLE:
                this.m = k;
                this.n = l;
                this.p = 2;
                this.q = this.p * 4;
                this.o = i.length / this.p;
                break;
            default:
                throw new RuntimeException("Unknown shape " + shape);
        }
        this.r = 8;
        this.s = shape;
    }

    public final FloatBuffer a() {
        return this.m;
    }

    public final FloatBuffer b() {
        return this.n;
    }

    public final int c() {
        return this.o;
    }

    public final int d() {
        return this.q;
    }

    public final int e() {
        return this.r;
    }

    public final int f() {
        return this.p;
    }

    public final String toString() {
        if (this.s != null) {
            return "[Drawable2d: " + this.s + "]";
        }
        return "[Drawable2d: ...]";
    }
}
