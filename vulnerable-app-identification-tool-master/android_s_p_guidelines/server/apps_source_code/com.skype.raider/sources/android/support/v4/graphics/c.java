package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat.b;
import android.support.v4.content.res.FontResourcesParserCompat.d;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.util.g;
import android.widget.TextView;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class c {
    private static final a a;
    private static final g<String, Typeface> b = new g(16);

    interface a {
        Typeface a(Context context, Resources resources, int i, String str, int i2);

        Typeface a(Context context, b bVar, Resources resources, int i);

        Typeface a(Context context, @NonNull android.support.v4.provider.FontsContractCompat.a[] aVarArr, int i);
    }

    static {
        if (VERSION.SDK_INT >= 26) {
            a = new f();
        } else if (VERSION.SDK_INT >= 24 && e.a()) {
            a = new e();
        } else if (VERSION.SDK_INT >= 21) {
            a = new d();
        } else {
            a = new g();
        }
    }

    public static Typeface a(Resources resources, int id, int style) {
        return (Typeface) b.a(b(resources, id, style));
    }

    private static String b(Resources resources, int id, int style) {
        return resources.getResourcePackageName(id) + "-" + id + "-" + style;
    }

    public static Typeface a(Context context, android.support.v4.content.res.FontResourcesParserCompat.a entry, Resources resources, int id, int style, @Nullable TextView targetView) {
        Typeface typeface;
        if (entry instanceof d) {
            d providerEntry = (d) entry;
            typeface = FontsContractCompat.a(context, providerEntry.a(), targetView, providerEntry.b(), providerEntry.c(), style);
        } else {
            typeface = a.a(context, (b) entry, resources, style);
        }
        if (typeface != null) {
            b.a(b(resources, id, style), typeface);
        }
        return typeface;
    }

    @Nullable
    public static Typeface a(Context context, Resources resources, int id, String path, int style) {
        Typeface typeface = a.a(context, resources, id, path, style);
        if (typeface != null) {
            b.a(b(resources, id, style), typeface);
        }
        return typeface;
    }

    public static Typeface a(Context context, @NonNull android.support.v4.provider.FontsContractCompat.a[] fonts, int style) {
        return a.a(context, fonts, style);
    }
}
