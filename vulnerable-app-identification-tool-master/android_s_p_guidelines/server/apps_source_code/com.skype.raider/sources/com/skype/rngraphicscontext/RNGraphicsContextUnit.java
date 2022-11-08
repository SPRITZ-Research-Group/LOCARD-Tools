package com.skype.rngraphicscontext;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class RNGraphicsContextUnit {
    final Canvas a;
    final Bitmap b;
    final Paint c;
    final Path d;

    public RNGraphicsContextUnit(Canvas canvas, Bitmap bmp, Paint paint, Path path) {
        this.a = canvas;
        this.b = bmp;
        this.c = paint;
        this.d = path;
    }
}
