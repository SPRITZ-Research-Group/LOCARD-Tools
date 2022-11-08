package com.facebook.ads.internal.view.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.ads.internal.q.a.j;
import com.facebook.ads.internal.q.a.u;

public class b extends FrameLayout {
    private final ImageView a;
    private int b;
    private int c;

    public b(Context context) {
        super(context);
        this.a = new ImageView(context);
        b();
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new ImageView(context, attributeSet);
        b();
    }

    public b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new ImageView(context, attributeSet, i);
        b();
    }

    private void b() {
        this.a.setScaleType(ScaleType.FIT_XY);
        addView(this.a, new LayoutParams(-2, -2));
        j.a(this.a, j.INTERNAL_AD_MEDIA);
    }

    public final ImageView a() {
        return this.a;
    }

    public final void a(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap2 != null) {
            u.a((View) this, new BitmapDrawable(getContext().getResources(), bitmap2));
        } else {
            u.a((View) this, 0);
        }
        if (bitmap != null) {
            this.b = bitmap.getWidth();
            this.c = bitmap.getHeight();
            this.a.setImageBitmap(Bitmap.createBitmap(bitmap));
            return;
        }
        this.a.setImageDrawable(null);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        if (this.b <= 0 || this.c <= 0) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        float min = Math.min(((float) i5) / ((float) this.b), ((float) i6) / ((float) this.c));
        int i7 = (int) (((float) this.b) * min);
        int i8 = (int) (min * ((float) this.c));
        i5 = (i5 / 2) + i;
        i6 = (i6 / 2) + i2;
        this.a.layout(i5 - (i7 / 2), i6 - (i8 / 2), i5 + (i7 / 2), i6 + (i8 / 2));
    }
}
