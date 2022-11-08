package com.skype.ink;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import com.facebook.react.bridge.al;
import javax.annotation.Nullable;

public class AdditiveSurfaceView extends View {
    @Nullable
    private Bitmap a;
    private DrawCommandStack b;

    public AdditiveSurfaceView(Context context) {
        super(context);
        this.b = new DrawCommandStack(context.getResources().getDisplayMetrics().density);
    }

    public void setBitmap(Bitmap bitmap) {
        this.a = bitmap;
        invalidate();
    }

    public final void a(al args) {
        if (this.a != null) {
            this.b.a(args);
            invalidate();
        }
    }

    public final void a() {
        if (!(this.b.a() || this.a == null)) {
            this.a.eraseColor(0);
        }
        invalidate();
    }

    public void setUndoLimit(int undoLimit) {
        this.b.a(undoLimit);
    }

    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.a, 0.0f, 0.0f, null);
        this.b.a(canvas);
    }
}
