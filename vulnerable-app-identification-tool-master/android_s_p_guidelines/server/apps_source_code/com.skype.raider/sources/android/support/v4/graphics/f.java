package android.support.v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.Typeface.Builder;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.content.res.FontResourcesParserCompat.b;
import android.support.v4.content.res.FontResourcesParserCompat.c;
import android.support.v4.provider.FontsContractCompat;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

@RequiresApi(26)
@RestrictTo({a.LIBRARY_GROUP})
public final class f extends d {
    private static final Class a;
    private static final Constructor b;
    private static final Method c;
    private static final Method d;
    private static final Method e;
    private static final Method f;
    private static final Method g;

    static {
        Class fontFamilyClass;
        Constructor fontFamilyCtor;
        Method addFontMethod;
        Method addFromBufferMethod;
        Method freezeMethod;
        Method abortCreationMethod;
        Method createFromFamiliesWithDefaultMethod;
        ReflectiveOperationException e;
        try {
            fontFamilyClass = Class.forName("android.graphics.FontFamily");
            fontFamilyCtor = fontFamilyClass.getConstructor(new Class[0]);
            addFontMethod = fontFamilyClass.getMethod("addFontFromAssetManager", new Class[]{AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class});
            addFromBufferMethod = fontFamilyClass.getMethod("addFontFromBuffer", new Class[]{ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE});
            freezeMethod = fontFamilyClass.getMethod("freeze", new Class[0]);
            abortCreationMethod = fontFamilyClass.getMethod("abortCreation", new Class[0]);
            createFromFamiliesWithDefaultMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[]{Array.newInstance(fontFamilyClass, 1).getClass(), Integer.TYPE, Integer.TYPE});
            createFromFamiliesWithDefaultMethod.setAccessible(true);
        } catch (ClassNotFoundException e2) {
            e = e2;
            new StringBuilder("Unable to collect necessary methods for class ").append(e.getClass().getName());
            fontFamilyClass = null;
            fontFamilyCtor = null;
            addFontMethod = null;
            addFromBufferMethod = null;
            freezeMethod = null;
            abortCreationMethod = null;
            createFromFamiliesWithDefaultMethod = null;
            b = fontFamilyCtor;
            a = fontFamilyClass;
            c = addFontMethod;
            d = addFromBufferMethod;
            e = freezeMethod;
            f = abortCreationMethod;
            g = createFromFamiliesWithDefaultMethod;
        } catch (NoSuchMethodException e3) {
            e = e3;
            new StringBuilder("Unable to collect necessary methods for class ").append(e.getClass().getName());
            fontFamilyClass = null;
            fontFamilyCtor = null;
            addFontMethod = null;
            addFromBufferMethod = null;
            freezeMethod = null;
            abortCreationMethod = null;
            createFromFamiliesWithDefaultMethod = null;
            b = fontFamilyCtor;
            a = fontFamilyClass;
            c = addFontMethod;
            d = addFromBufferMethod;
            e = freezeMethod;
            f = abortCreationMethod;
            g = createFromFamiliesWithDefaultMethod;
        }
        b = fontFamilyCtor;
        a = fontFamilyClass;
        c = addFontMethod;
        d = addFromBufferMethod;
        e = freezeMethod;
        f = abortCreationMethod;
        g = createFromFamiliesWithDefaultMethod;
    }

    private static boolean a() {
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

    private static boolean a(Context context, Object family, String fileName, int weight, int style) {
        ReflectiveOperationException e;
        try {
            return ((Boolean) c.invoke(family, new Object[]{context.getAssets(), fileName, Integer.valueOf(0), Boolean.valueOf(false), Integer.valueOf(0), Integer.valueOf(weight), Integer.valueOf(style), null})).booleanValue();
        } catch (IllegalAccessException e2) {
            e = e2;
        } catch (InvocationTargetException e3) {
            e = e3;
        }
        throw new RuntimeException(e);
    }

    private static boolean a(Object family, ByteBuffer buffer, int ttcIndex, int weight, int style) {
        ReflectiveOperationException e;
        try {
            return ((Boolean) d.invoke(family, new Object[]{buffer, Integer.valueOf(ttcIndex), null, Integer.valueOf(weight), Integer.valueOf(style)})).booleanValue();
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
            return (Typeface) g.invoke(null, new Object[]{familyArray, Integer.valueOf(-1), Integer.valueOf(-1)});
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw new RuntimeException(e);
        }
    }

    private static boolean b(Object family) {
        ReflectiveOperationException e;
        try {
            return ((Boolean) e.invoke(family, new Object[0])).booleanValue();
        } catch (IllegalAccessException e2) {
            e = e2;
        } catch (InvocationTargetException e3) {
            e = e3;
        }
        throw new RuntimeException(e);
    }

    private static boolean c(Object family) {
        ReflectiveOperationException e;
        try {
            return ((Boolean) f.invoke(family, new Object[0])).booleanValue();
        } catch (IllegalAccessException e2) {
            e = e2;
        } catch (InvocationTargetException e3) {
            e = e3;
        }
        throw new RuntimeException(e);
    }

    public final Typeface a(Context context, b entry, Resources resources, int style) {
        if (!a()) {
            return super.a(context, entry, resources, style);
        }
        Object fontFamily = b();
        c[] a = entry.a();
        int length = a.length;
        int i = 0;
        while (i < length) {
            int i2;
            c fontFile = a[i];
            String a2 = fontFile.a();
            int b = fontFile.b();
            if (fontFile.c()) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (a(context, fontFamily, a2, b, i2)) {
                i++;
            } else {
                c(fontFamily);
                return null;
            }
        }
        if (b(fontFamily)) {
            return a(fontFamily);
        }
        return null;
    }

    public final Typeface a(Context context, @NonNull FontsContractCompat.a[] fonts, int style) {
        ParcelFileDescriptor pfd;
        Throwable th;
        Throwable th2;
        if (fonts.length <= 0) {
            return null;
        }
        if (a()) {
            Map<Uri, ByteBuffer> uriBuffer = FontsContractCompat.a(context, fonts);
            Object fontFamily = b();
            boolean atLeastOneFont = false;
            for (FontsContractCompat.a font : fonts) {
                ByteBuffer fontBuffer = (ByteBuffer) uriBuffer.get(font.a());
                if (fontBuffer != null) {
                    if (a(fontFamily, fontBuffer, font.b(), font.c(), font.d() ? 1 : 0)) {
                        atLeastOneFont = true;
                    } else {
                        c(fontFamily);
                        return null;
                    }
                }
            }
            if (!atLeastOneFont) {
                c(fontFamily);
                return null;
            } else if (b(fontFamily)) {
                return a(fontFamily);
            } else {
                return null;
            }
        }
        FontsContractCompat.a bestFont = a(fonts, style);
        try {
            pfd = context.getContentResolver().openFileDescriptor(bestFont.a(), "r", null);
            th = null;
            try {
                Typeface build = new Builder(pfd.getFileDescriptor()).setWeight(bestFont.c()).setItalic(bestFont.d()).build();
                if (pfd == null) {
                    return build;
                }
                pfd.close();
                return build;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                th3 = th2;
                th2 = th4;
            }
        } catch (IOException e) {
            return null;
        }
        if (pfd != null) {
            if (th3 != null) {
                try {
                    pfd.close();
                } catch (Throwable th5) {
                    th3.addSuppressed(th5);
                }
            } else {
                pfd.close();
            }
        }
        throw th2;
        throw th2;
    }

    @Nullable
    public final Typeface a(Context context, Resources resources, int id, String path, int style) {
        if (!a()) {
            return super.a(context, resources, id, path, style);
        }
        Object fontFamily = b();
        if (!a(context, fontFamily, path, -1, -1)) {
            c(fontFamily);
            return null;
        } else if (b(fontFamily)) {
            return a(fontFamily);
        } else {
            return null;
        }
    }
}
