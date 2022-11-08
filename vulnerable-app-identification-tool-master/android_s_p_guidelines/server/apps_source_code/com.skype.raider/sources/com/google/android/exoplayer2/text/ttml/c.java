package com.google.android.exoplayer2.text.ttml;

final class c {
    public final String a;
    public final float b;
    public final float c;
    public final int d;
    public final int e;
    public final float f;

    public c() {
        this(null, Float.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public c(String id, float position, float line, int lineType, int lineAnchor, float width) {
        this.a = id;
        this.b = position;
        this.c = line;
        this.d = lineType;
        this.e = lineAnchor;
        this.f = width;
    }
}
