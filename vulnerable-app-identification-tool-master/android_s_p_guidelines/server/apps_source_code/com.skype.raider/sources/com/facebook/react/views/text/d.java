package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.SparseArray;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public final class d {
    private static final String[] a = new String[]{"", "_bold", "_italic", "_bold_italic"};
    private static final String[] b = new String[]{".ttf", ".otf"};
    private static d c;
    private Map<String, a> d = new HashMap();

    private static class a {
        private SparseArray<Typeface> a;

        /* synthetic */ a(byte b) {
            this();
        }

        private a() {
            this.a = new SparseArray(4);
        }

        public final Typeface a(int style) {
            return (Typeface) this.a.get(style);
        }

        public final void a(int style, Typeface typeface) {
            this.a.put(style, typeface);
        }
    }

    private d() {
    }

    public static d a() {
        if (c == null) {
            c = new d();
        }
        return c;
    }

    @Nullable
    public final Typeface a(String fontFamilyName, int style, AssetManager assetManager) {
        a fontFamily = (a) this.d.get(fontFamilyName);
        if (fontFamily == null) {
            fontFamily = new a();
            this.d.put(fontFamilyName, fontFamily);
        }
        Typeface typeface = fontFamily.a(style);
        if (typeface == null) {
            typeface = b(fontFamilyName, style, assetManager);
            if (typeface != null) {
                fontFamily.a(style, typeface);
            }
        }
        return typeface;
    }

    @Nullable
    private static Typeface b(String fontFamilyName, int style, AssetManager assetManager) {
        String extension = a[style];
        String[] strArr = b;
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            try {
                return Typeface.createFromAsset(assetManager, "fonts/" + fontFamilyName + extension + strArr[i]);
            } catch (RuntimeException e) {
                i++;
            }
        }
        return Typeface.create(fontFamilyName, style);
    }
}
