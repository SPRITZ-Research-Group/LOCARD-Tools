package com.BV.LinearGradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.view.View;
import com.facebook.react.bridge.al;
import com.facebook.react.uimanager.p;

public class LinearGradientView extends View {
    private final Paint a = new Paint(1);
    private Path b;
    private RectF c;
    private LinearGradient d;
    private float[] e;
    private float[] f = new float[]{0.0f, 0.0f};
    private float[] g = new float[]{0.0f, 1.0f};
    private int[] h;
    private int[] i = new int[]{0, 0};
    private float[] j = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};

    public LinearGradientView(Context context) {
        super(context);
    }

    public void setStartPosition(al startPos) {
        this.f = new float[]{(float) startPos.getDouble(0), (float) startPos.getDouble(1)};
        a();
    }

    public void setEndPosition(al endPos) {
        this.g = new float[]{(float) endPos.getDouble(0), (float) endPos.getDouble(1)};
        a();
    }

    public void setColors(al colors) {
        int[] _colors = new int[colors.size()];
        for (int i = 0; i < _colors.length; i++) {
            _colors[i] = colors.getInt(i);
        }
        this.h = _colors;
        a();
    }

    public void setLocations(al locations) {
        float[] _locations = new float[locations.size()];
        for (int i = 0; i < _locations.length; i++) {
            _locations[i] = (float) locations.getDouble(i);
        }
        this.e = _locations;
        a();
    }

    public void setBorderRadii(al borderRadii) {
        float[] _radii = new float[borderRadii.size()];
        for (int i = 0; i < _radii.length; i++) {
            _radii[i] = p.a((float) borderRadii.getDouble(i));
        }
        this.j = _radii;
        b();
        a();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.i = new int[]{w, h};
        b();
        a();
    }

    private void a() {
        if (this.h == null) {
            return;
        }
        if (this.e == null || this.h.length == this.e.length) {
            this.d = new LinearGradient(this.f[0] * ((float) this.i[0]), this.f[1] * ((float) this.i[1]), this.g[0] * ((float) this.i[0]), this.g[1] * ((float) this.i[1]), this.h, this.e, TileMode.CLAMP);
            this.a.setShader(this.d);
            invalidate();
        }
    }

    private void b() {
        if (this.b == null) {
            this.b = new Path();
            this.c = new RectF();
        }
        this.b.reset();
        this.c.set(0.0f, 0.0f, (float) this.i[0], (float) this.i[1]);
        this.b.addRoundRect(this.c, this.j, Direction.CW);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b == null) {
            canvas.drawPaint(this.a);
        } else {
            canvas.drawPath(this.b, this.a);
        }
    }
}
