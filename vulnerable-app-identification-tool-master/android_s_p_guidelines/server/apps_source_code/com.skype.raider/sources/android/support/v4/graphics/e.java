package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.content.res.FontResourcesParserCompat.b;
import android.support.v4.content.res.FontResourcesParserCompat.c;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.util.l;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

@RequiresApi(24)
@RestrictTo({a.LIBRARY_GROUP})
final class e extends g {
    private static final Class a;
    private static final Constructor b;
    private static final Method c;
    private static final Method d;

    e() {
    }

    static {
        Class fontFamilyClass;
        Constructor fontFamilyCtor;
        Method addFontMethod;
        Method createFromFamiliesWithDefaultMethod;
        Object e;
        try {
            fontFamilyClass = Class.forName("android.graphics.FontFamily");
            fontFamilyCtor = fontFamilyClass.getConstructor(new Class[0]);
            addFontMethod = fontFamilyClass.getMethod("addFontWeightStyle", new Class[]{ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE});
            createFromFamiliesWithDefaultMethod = Typeface.class.getMethod("createFromFamiliesWithDefault", new Class[]{Array.newInstance(fontFamilyClass, 1).getClass()});
        } catch (ClassNotFoundException e2) {
            e = e2;
        } catch (NoSuchMethodException e3) {
            e = e3;
        }
        b = fontFamilyCtor;
        a = fontFamilyClass;
        c = addFontMethod;
        d = createFromFamiliesWithDefaultMethod;
        e.getClass().getName();
        fontFamilyClass = null;
        fontFamilyCtor = null;
        addFontMethod = null;
        createFromFamiliesWithDefaultMethod = null;
        b = fontFamilyCtor;
        a = fontFamilyClass;
        c = addFontMethod;
        d = createFromFamiliesWithDefaultMethod;
    }

    public static boolean a() {
        return c != null;
    }

    private static Object b() {
        ReflectiveOperationException e;
        try {
            return b.newInstance(new Object[0]);
        } catch (IllegalAccessException e2) {
            e = e2;
        } catch (InstantiationException e3) {
            e = e3;
        } catch (InvocationTargetException e4) {
            e = e4;
        }
        throw new RuntimeException(e);
    }

    private static boolean a(Object family, ByteBuffer buffer, int ttcIndex, int weight, boolean style) {
        ReflectiveOperationException e;
        try {
            return ((Boolean) c.invoke(family, new Object[]{buffer, Integer.valueOf(ttcIndex), null, Integer.valueOf(weight), Boolean.valueOf(style)})).booleanValue();
        } catch (IllegalAccessException e2) {
            e = e2;
        } catch (InvocationTargetException e3) {
            e = e3;
        }
        throw new RuntimeException(e);
    }

    private static Typeface a(Object family) {
        ReflectiveOperationException e;
        try {
            Array.set(Array.newInstance(a, 1), 0, family);
            return (Typeface) d.invoke(null, new Object[]{familyArray});
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw new RuntimeException(e);
        }
    }

    public final Typeface a(Context context, @NonNull FontsContractCompat.a[] fonts, int style) {
        Object family = b();
        l<Uri, ByteBuffer> bufferCache = new l();
        for (FontsContractCompat.a font : fonts) {
            Uri uri = font.a();
            ByteBuffer buffer = (ByteBuffer) bufferCache.get(uri);
            if (buffer == null) {
                buffer = h.a(context, uri);
                bufferCache.put(uri, buffer);
            }
            if (!a(family, buffer, font.b(), font.c(), font.d())) {
                return null;
            }
        }
        return a(family);
    }

    public final Typeface a(Context context, b entry, Resources resources, int style) {
        Object family = b();
        for (c e : entry.a()) {
            if (!a(family, h.a(context, resources, e.d()), 0, e.b(), e.c())) {
                return null;
            }
        }
        return a(family);
    }
}
