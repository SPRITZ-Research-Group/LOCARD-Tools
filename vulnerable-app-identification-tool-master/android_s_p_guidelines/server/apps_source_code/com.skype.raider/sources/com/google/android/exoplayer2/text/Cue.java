package com.google.android.exoplayer2.text;

import android.graphics.Bitmap;
import android.text.Layout.Alignment;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Cue {
    public final CharSequence a;
    public final Alignment b;
    public final Bitmap c;
    public final float d;
    public final int e;
    public final int f;
    public final float g;
    public final int h;
    public final float i;
    public final float j;
    public final boolean k;
    public final int l;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AnchorType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LineType {
    }

    public Cue(Bitmap bitmap, float horizontalPosition, float verticalPosition, float width, float height) {
        this(null, null, bitmap, verticalPosition, 0, 0, horizontalPosition, 0, width, height, false, -16777216);
    }

    public Cue(CharSequence text) {
        this(text, null, Float.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public Cue(CharSequence text, Alignment textAlignment, float line, int lineType, int lineAnchor, float position, int positionAnchor, float size) {
        this(text, textAlignment, line, lineType, lineAnchor, position, positionAnchor, size, false, -16777216);
    }

    public Cue(CharSequence text, Alignment textAlignment, float line, int lineType, int lineAnchor, float position, int positionAnchor, float size, boolean windowColorSet, int windowColor) {
        this(text, textAlignment, null, line, lineType, lineAnchor, position, positionAnchor, size, Float.MIN_VALUE, windowColorSet, windowColor);
    }

    private Cue(CharSequence text, Alignment textAlignment, Bitmap bitmap, float line, int lineType, int lineAnchor, float position, int positionAnchor, float size, float bitmapHeight, boolean windowColorSet, int windowColor) {
        this.a = text;
        this.b = textAlignment;
        this.c = bitmap;
        this.d = line;
        this.e = lineType;
        this.f = lineAnchor;
        this.g = position;
        this.h = positionAnchor;
        this.i = size;
        this.j = bitmapHeight;
        this.k = windowColorSet;
        this.l = windowColor;
    }
}
