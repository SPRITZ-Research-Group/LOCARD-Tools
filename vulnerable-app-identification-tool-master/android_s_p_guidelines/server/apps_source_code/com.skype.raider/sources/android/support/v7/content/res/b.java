package android.support.v7.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.h;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.WeakHashMap;

public final class b {
    private static final ThreadLocal<TypedValue> a = new ThreadLocal();
    private static final WeakHashMap<Context, SparseArray<a>> b = new WeakHashMap(0);
    private static final Object c = new Object();

    private static class a {
        final ColorStateList a;
        final Configuration b;

        a(@NonNull ColorStateList value, @NonNull Configuration configuration) {
            this.a = value;
            this.b = configuration;
        }
    }

    public static ColorStateList a(@NonNull Context context, @ColorRes int resId) {
        if (VERSION.SDK_INT >= 23) {
            return context.getColorStateList(resId);
        }
        ColorStateList csl = d(context, resId);
        if (csl != null) {
            return csl;
        }
        csl = c(context, resId);
        if (csl == null) {
            return android.support.v4.content.a.b(context, resId);
        }
        synchronized (c) {
            SparseArray sparseArray = (SparseArray) b.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray();
                b.put(context, sparseArray);
            }
            sparseArray.append(resId, new a(csl, context.getResources().getConfiguration()));
        }
        return csl;
    }

    @Nullable
    public static Drawable b(@NonNull Context context, @DrawableRes int resId) {
        return h.a().a(context, resId);
    }

    @Nullable
    private static ColorStateList c(Context context, int resId) {
        boolean z;
        Resources resources = context.getResources();
        TypedValue typedValue = (TypedValue) a.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            a.set(typedValue);
        }
        resources.getValue(resId, typedValue, true);
        if (typedValue.type < 28 || typedValue.type > 31) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            return null;
        }
        Resources r = context.getResources();
        try {
            return a.a(r, r.getXml(resId), context.getTheme());
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @Nullable
    private static ColorStateList d(@NonNull Context context, @ColorRes int resId) {
        synchronized (c) {
            SparseArray<a> entries = (SparseArray) b.get(context);
            if (entries != null && entries.size() > 0) {
                a entry = (a) entries.get(resId);
                if (entry != null) {
                    if (entry.b.equals(context.getResources().getConfiguration())) {
                        ColorStateList colorStateList = entry.a;
                        return colorStateList;
                    }
                    entries.remove(resId);
                }
            }
        }
    }
}
