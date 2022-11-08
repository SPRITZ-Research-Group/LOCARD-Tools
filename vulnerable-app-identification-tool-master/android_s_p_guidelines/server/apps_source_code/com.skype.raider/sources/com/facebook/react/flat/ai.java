package com.facebook.react.flat;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import java.util.HashMap;
import javax.annotation.Nullable;

final class ai {
    private static final HashMap<String, Typeface[]> a = new HashMap();
    private static final HashMap<Typeface, Typeface[]> b = new HashMap();
    private static final String[] c = new String[]{"", "_bold", "_italic", "_bold_italic"};
    private static final String[] d = new String[]{".ttf", ".otf"};
    @Nullable
    private static AssetManager e = null;

    public static Typeface a(String fontFamily, int style) {
        Typeface[] cache = (Typeface[]) a.get(fontFamily);
        if (cache == null) {
            cache = new Typeface[4];
            a.put(fontFamily, cache);
        } else if (cache[style] != null) {
            return cache[style];
        }
        Typeface typeface = b(fontFamily, style);
        cache[style] = typeface;
        b.put(typeface, cache);
        return typeface;
    }

    private static Typeface b(String fontFamilyName, int style) {
        StringBuilder fileNameBuffer = new StringBuilder(32).append("fonts/").append(fontFamilyName).append(c[style]);
        int length = fileNameBuffer.length();
        String[] strArr = d;
        int length2 = strArr.length;
        int i = 0;
        while (i < length2) {
            try {
                return Typeface.createFromAsset(e, fileNameBuffer.append(strArr[i]).toString());
            } catch (RuntimeException e) {
                fileNameBuffer.setLength(length);
                i++;
            }
        }
        return Typeface.create(fontFamilyName, style);
    }

    public static Typeface a(Typeface typeface, int style) {
        if (typeface == null) {
            return Typeface.defaultFromStyle(style);
        }
        Typeface[] cache = (Typeface[]) b.get(typeface);
        if (cache == null) {
            cache = new Typeface[4];
            cache[typeface.getStyle()] = typeface;
        } else if (cache[style] != null) {
            return cache[style];
        }
        typeface = Typeface.create(typeface, style);
        cache[style] = typeface;
        b.put(typeface, cache);
        return typeface;
    }
}
