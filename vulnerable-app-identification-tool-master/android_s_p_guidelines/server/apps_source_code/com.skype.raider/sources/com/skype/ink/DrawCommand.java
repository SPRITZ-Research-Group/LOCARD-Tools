package com.skype.ink;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class DrawCommand {
    Paint a = new Paint();
    final float b;
    private Path c;
    private float[] d;
    private float e;
    private float[] f;

    public DrawCommand(Path path, float[] strokeColor, float strokeWidth, float[] fillColor, float scale) {
        this.c = path;
        this.d = strokeColor;
        this.e = strokeWidth;
        this.f = fillColor;
        this.b = scale;
        this.a.setAntiAlias(true);
    }

    public final void a(Path path) {
        this.c.addPath(path);
    }

    public final void a(Canvas canvas) {
        if (PathUtils.a(this.a, this.f)) {
            canvas.drawPath(this.c, this.a);
        }
        if (PathUtils.a(this.a, this.e, this.d, this.b)) {
            canvas.drawPath(this.c, this.a);
        }
    }
}
