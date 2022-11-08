package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.em;
import defpackage.en;
import defpackage.w;

public final class bu {
    private final Context a;
    private final TypedArray b;
    private TypedValue c;

    public static bu a(Context context, AttributeSet attributeSet, int[] iArr) {
        return new bu(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static bu a(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new bu(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public static bu a(Context context, int i, int[] iArr) {
        return new bu(context, context.obtainStyledAttributes(i, iArr));
    }

    private bu(Context context, TypedArray typedArray) {
        this.a = context;
        this.b = typedArray;
    }

    public final Drawable a(int i) {
        if (this.b.hasValue(i)) {
            int resourceId = this.b.getResourceId(i, 0);
            if (resourceId != 0) {
                return w.b(this.a, resourceId);
            }
        }
        return this.b.getDrawable(i);
    }

    public final Drawable b(int i) {
        if (this.b.hasValue(i)) {
            i = this.b.getResourceId(i, 0);
            if (i != 0) {
                return y.a().a(this.a, i, true);
            }
        }
        return null;
    }

    public final Typeface a(int i, int i2, en enVar) {
        i = this.b.getResourceId(i, 0);
        if (i == 0) {
            return null;
        }
        if (this.c == null) {
            this.c = new TypedValue();
        }
        return em.a(this.a, i, this.c, i2, enVar);
    }

    public final CharSequence c(int i) {
        return this.b.getText(i);
    }

    public final String d(int i) {
        return this.b.getString(i);
    }

    public final boolean a(int i, boolean z) {
        return this.b.getBoolean(i, z);
    }

    public final int a(int i, int i2) {
        return this.b.getInt(i, i2);
    }

    public final float a(int i, float f) {
        return this.b.getFloat(i, f);
    }

    public final int b(int i, int i2) {
        return this.b.getColor(i, i2);
    }

    public final ColorStateList e(int i) {
        if (this.b.hasValue(i)) {
            int resourceId = this.b.getResourceId(i, 0);
            if (resourceId != 0) {
                ColorStateList a = w.a(this.a, resourceId);
                if (a != null) {
                    return a;
                }
            }
        }
        return this.b.getColorStateList(i);
    }

    public final int c(int i, int i2) {
        return this.b.getInteger(i, i2);
    }

    public final float f(int i) {
        return this.b.getDimension(i, BitmapDescriptorFactory.HUE_RED);
    }

    public final int d(int i, int i2) {
        return this.b.getDimensionPixelOffset(i, i2);
    }

    public final int e(int i, int i2) {
        return this.b.getDimensionPixelSize(i, i2);
    }

    public final int f(int i, int i2) {
        return this.b.getLayoutDimension(i, i2);
    }

    public final int g(int i, int i2) {
        return this.b.getResourceId(i, i2);
    }

    public final CharSequence[] g(int i) {
        return this.b.getTextArray(i);
    }

    public final boolean h(int i) {
        return this.b.hasValue(i);
    }

    public final void a() {
        this.b.recycle();
    }
}
