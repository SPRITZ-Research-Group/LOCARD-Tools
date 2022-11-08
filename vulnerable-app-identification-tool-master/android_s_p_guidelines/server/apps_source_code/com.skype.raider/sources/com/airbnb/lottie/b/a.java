package com.airbnb.lottie.b;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.Nullable;
import android.view.View;
import com.airbnb.lottie.b;
import com.airbnb.lottie.c.g;
import java.util.HashMap;
import java.util.Map;

public final class a {
    private final g<String> a = new g();
    private final Map<g<String>, Typeface> b = new HashMap();
    private final Map<String, Typeface> c = new HashMap();
    private final AssetManager d;
    @Nullable
    private b e;
    private String f = ".ttf";

    public a(Callback callback, @Nullable b delegate) {
        this.e = delegate;
        if (callback instanceof View) {
            this.d = ((View) callback).getContext().getAssets();
        } else {
            this.d = null;
        }
    }

    public final void a(@Nullable b assetDelegate) {
        this.e = assetDelegate;
    }

    public final Typeface a(String fontFamily, String style) {
        this.a.a(fontFamily, style);
        Typeface typeface = (Typeface) this.b.get(this.a);
        if (typeface != null) {
            return typeface;
        }
        Typeface typeface2 = (Typeface) this.c.get(fontFamily);
        if (typeface2 == null) {
            typeface2 = Typeface.createFromAsset(this.d, "fonts/" + fontFamily + this.f);
            this.c.put(fontFamily, typeface2);
        }
        int i = 0;
        boolean contains = style.contains("Italic");
        boolean contains2 = style.contains("Bold");
        if (contains && contains2) {
            i = 3;
        } else if (contains) {
            i = 2;
        } else if (contains2) {
            i = 1;
        }
        if (typeface2.getStyle() == i) {
            typeface = typeface2;
        } else {
            typeface = Typeface.create(typeface2, i);
        }
        this.b.put(this.a, typeface);
        return typeface;
    }
}
