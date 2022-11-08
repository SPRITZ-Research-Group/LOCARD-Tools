package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import com.linecorp.yuki.sensetime.model.SegmentationData;

public abstract class b extends Drawable {
    final Bitmap a;
    final Rect b = new Rect();
    private int c = SegmentationData.MAX_SEGMENTATION_WIDTH;
    private int d = 119;
    private final Paint e = new Paint(3);
    private final BitmapShader f;
    private final Matrix g = new Matrix();
    private float h;
    private final RectF i = new RectF();
    private boolean j = true;
    private boolean k;
    private int l;
    private int m;

    private static boolean b(float f) {
        return f > 0.05f;
    }

    public void setFilterBitmap(boolean z) {
        this.e.setFilterBitmap(z);
        invalidateSelf();
    }

    public void setDither(boolean z) {
        this.e.setDither(z);
        invalidateSelf();
    }

    void a(int i, int i2, int i3, Rect rect, Rect rect2) {
        throw new UnsupportedOperationException();
    }

    final void a() {
        if (this.j) {
            if (this.k) {
                int min = Math.min(this.l, this.m);
                a(this.d, min, min, getBounds(), this.b);
                int min2 = Math.min(this.b.width(), this.b.height());
                this.b.inset(Math.max(0, (this.b.width() - min2) / 2), Math.max(0, (this.b.height() - min2) / 2));
                this.h = ((float) min2) * 0.5f;
            } else {
                a(this.d, this.l, this.m, getBounds(), this.b);
            }
            this.i.set(this.b);
            if (this.f != null) {
                this.g.setTranslate(this.i.left, this.i.top);
                this.g.preScale(this.i.width() / ((float) this.a.getWidth()), this.i.height() / ((float) this.a.getHeight()));
                this.f.setLocalMatrix(this.g);
                this.e.setShader(this.f);
            }
            this.j = false;
        }
    }

    public void draw(Canvas canvas) {
        Bitmap bitmap = this.a;
        if (bitmap != null) {
            a();
            if (this.e.getShader() == null) {
                canvas.drawBitmap(bitmap, null, this.b, this.e);
            } else {
                canvas.drawRoundRect(this.i, this.h, this.h, this.e);
            }
        }
    }

    public void setAlpha(int i) {
        if (i != this.e.getAlpha()) {
            this.e.setAlpha(i);
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.e.getAlpha();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.e.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public ColorFilter getColorFilter() {
        return this.e.getColorFilter();
    }

    public final void a(float f) {
        if (this.h != f) {
            this.k = false;
            if (b(f)) {
                this.e.setShader(this.f);
            } else {
                this.e.setShader(null);
            }
            this.h = f;
            invalidateSelf();
        }
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (this.k) {
            this.h = (float) (Math.min(this.m, this.l) / 2);
        }
        this.j = true;
    }

    public final float b() {
        return this.h;
    }

    public int getIntrinsicWidth() {
        return this.l;
    }

    public int getIntrinsicHeight() {
        return this.m;
    }

    public int getOpacity() {
        if (this.d != 119 || this.k) {
            return -3;
        }
        Bitmap bitmap = this.a;
        if (bitmap == null || bitmap.hasAlpha() || this.e.getAlpha() < 255 || b(this.h)) {
            return -3;
        }
        return -1;
    }

    b(Resources resources, Bitmap bitmap) {
        if (resources != null) {
            this.c = resources.getDisplayMetrics().densityDpi;
        }
        this.a = bitmap;
        if (this.a != null) {
            this.l = this.a.getScaledWidth(this.c);
            this.m = this.a.getScaledHeight(this.c);
            bitmap = this.a;
            TileMode tileMode = TileMode.CLAMP;
            this.f = new BitmapShader(bitmap, tileMode, tileMode);
            return;
        }
        this.m = -1;
        this.l = -1;
        this.f = null;
    }
}
