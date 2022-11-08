package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class a {
    static final e a;

    static class e {
        e() {
        }

        public void a(Drawable drawable, boolean mirrored) {
        }

        public boolean b(Drawable drawable) {
            return false;
        }

        public void a(Drawable drawable, float x, float y) {
        }

        public void a(Drawable drawable, int left, int top, int right, int bottom) {
        }

        public void b(Drawable drawable, int tint) {
            if (drawable instanceof f) {
                ((f) drawable).setTint(tint);
            }
        }

        public void a(Drawable drawable, ColorStateList tint) {
            if (drawable instanceof f) {
                ((f) drawable).setTintList(tint);
            }
        }

        public void a(Drawable drawable, Mode tintMode) {
            if (drawable instanceof f) {
                ((f) drawable).setTintMode(tintMode);
            }
        }

        public Drawable c(Drawable drawable) {
            if (drawable instanceof f) {
                return drawable;
            }
            return new c(drawable);
        }

        public boolean a(Drawable drawable, int layoutDirection) {
            return false;
        }

        public int a(Drawable drawable) {
            return 0;
        }

        public int d(Drawable drawable) {
            return 0;
        }

        public void a(Drawable drawable, Theme t) {
        }

        public boolean e(Drawable drawable) {
            return false;
        }

        public ColorFilter f(Drawable drawable) {
            return null;
        }

        public void a(Drawable drawable, Resources res, XmlPullParser parser, AttributeSet attrs, Theme t) throws IOException, XmlPullParserException {
            drawable.inflate(res, parser, attrs);
        }
    }

    @RequiresApi(17)
    static class a extends e {
        private static Method a;
        private static boolean b;
        private static Method c;
        private static boolean d;

        a() {
        }

        public boolean a(Drawable drawable, int layoutDirection) {
            if (!b) {
                try {
                    Method declaredMethod = Drawable.class.getDeclaredMethod("setLayoutDirection", new Class[]{Integer.TYPE});
                    a = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                }
                b = true;
            }
            if (a != null) {
                try {
                    a.invoke(drawable, new Object[]{Integer.valueOf(layoutDirection)});
                    return true;
                } catch (Exception e2) {
                    a = null;
                }
            }
            return false;
        }

        public int a(Drawable drawable) {
            if (!d) {
                try {
                    Method declaredMethod = Drawable.class.getDeclaredMethod("getLayoutDirection", new Class[0]);
                    c = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                }
                d = true;
            }
            if (c != null) {
                try {
                    return ((Integer) c.invoke(drawable, new Object[0])).intValue();
                } catch (Exception e2) {
                    c = null;
                }
            }
            return 0;
        }
    }

    @RequiresApi(19)
    static class b extends a {
        b() {
        }

        public final void a(Drawable drawable, boolean mirrored) {
            drawable.setAutoMirrored(mirrored);
        }

        public final boolean b(Drawable drawable) {
            return drawable.isAutoMirrored();
        }

        public Drawable c(Drawable drawable) {
            if (drawable instanceof f) {
                return drawable;
            }
            return new d(drawable);
        }

        public final int d(Drawable drawable) {
            return drawable.getAlpha();
        }
    }

    @RequiresApi(21)
    static class c extends b {
        c() {
        }

        public final void a(Drawable drawable, float x, float y) {
            drawable.setHotspot(x, y);
        }

        public final void a(Drawable drawable, int left, int top, int right, int bottom) {
            drawable.setHotspotBounds(left, top, right, bottom);
        }

        public final void b(Drawable drawable, int tint) {
            drawable.setTint(tint);
        }

        public final void a(Drawable drawable, ColorStateList tint) {
            drawable.setTintList(tint);
        }

        public final void a(Drawable drawable, Mode tintMode) {
            drawable.setTintMode(tintMode);
        }

        public Drawable c(Drawable drawable) {
            if (drawable instanceof f) {
                return drawable;
            }
            return new e(drawable);
        }

        public final void a(Drawable drawable, Theme t) {
            drawable.applyTheme(t);
        }

        public final boolean e(Drawable drawable) {
            return drawable.canApplyTheme();
        }

        public final ColorFilter f(Drawable drawable) {
            return drawable.getColorFilter();
        }

        public final void a(Drawable drawable, Resources res, XmlPullParser parser, AttributeSet attrs, Theme t) throws IOException, XmlPullParserException {
            drawable.inflate(res, parser, attrs, t);
        }
    }

    @RequiresApi(23)
    static class d extends c {
        d() {
        }

        public final boolean a(Drawable drawable, int layoutDirection) {
            return drawable.setLayoutDirection(layoutDirection);
        }

        public final int a(Drawable drawable) {
            return drawable.getLayoutDirection();
        }

        public final Drawable c(Drawable drawable) {
            return drawable;
        }
    }

    static {
        if (VERSION.SDK_INT >= 23) {
            a = new d();
        } else if (VERSION.SDK_INT >= 21) {
            a = new c();
        } else if (VERSION.SDK_INT >= 19) {
            a = new b();
        } else if (VERSION.SDK_INT >= 17) {
            a = new a();
        } else {
            a = new e();
        }
    }

    public static void a(@NonNull Drawable drawable, boolean mirrored) {
        a.a(drawable, mirrored);
    }

    public static boolean b(@NonNull Drawable drawable) {
        return a.b(drawable);
    }

    public static void a(@NonNull Drawable drawable, float x, float y) {
        a.a(drawable, x, y);
    }

    public static void a(@NonNull Drawable drawable, int left, int top, int right, int bottom) {
        a.a(drawable, left, top, right, bottom);
    }

    public static void a(@NonNull Drawable drawable, @ColorInt int tint) {
        a.b(drawable, tint);
    }

    public static void a(@NonNull Drawable drawable, @Nullable ColorStateList tint) {
        a.a(drawable, tint);
    }

    public static void a(@NonNull Drawable drawable, @Nullable Mode tintMode) {
        a.a(drawable, tintMode);
    }

    public static int c(@NonNull Drawable drawable) {
        return a.d(drawable);
    }

    public static void a(@NonNull Drawable drawable, @NonNull Theme t) {
        a.a(drawable, t);
    }

    public static boolean d(@NonNull Drawable drawable) {
        return a.e(drawable);
    }

    public static ColorFilter e(@NonNull Drawable drawable) {
        return a.f(drawable);
    }

    public static void a(@NonNull Drawable drawable, @NonNull Resources res, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Theme theme) throws XmlPullParserException, IOException {
        a.a(drawable, res, parser, attrs, theme);
    }

    public static Drawable f(@NonNull Drawable drawable) {
        return a.c(drawable);
    }

    public static <T extends Drawable> T g(@NonNull Drawable drawable) {
        if (drawable instanceof b) {
            return ((b) drawable).a();
        }
        return drawable;
    }

    public static boolean b(@NonNull Drawable drawable, int layoutDirection) {
        return a.a(drawable, layoutDirection);
    }

    public static int h(@NonNull Drawable drawable) {
        return a.a(drawable);
    }

    public static void a(@NonNull Drawable drawable) {
        drawable.jumpToCurrentState();
    }
}
