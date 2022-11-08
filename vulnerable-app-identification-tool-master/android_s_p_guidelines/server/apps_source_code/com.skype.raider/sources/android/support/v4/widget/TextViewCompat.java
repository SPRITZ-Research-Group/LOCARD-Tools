package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

public final class TextViewCompat {
    static final f a;

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AutoSizeTextType {
    }

    static class f {
        private static Field a;
        private static boolean b;
        private static Field c;
        private static boolean d;

        f() {
        }

        public void a(@NonNull TextView textView, @Nullable Drawable start) {
            textView.setCompoundDrawables(start, null, null, null);
        }

        private static Field a(String fieldName) {
            Field field = null;
            try {
                field = TextView.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                new StringBuilder("Could not retrieve ").append(fieldName).append(" field.");
                return field;
            }
        }

        private static int a(Field field, TextView textView) {
            try {
                return field.getInt(textView);
            } catch (IllegalAccessException e) {
                new StringBuilder("Could not retrieve value of ").append(field.getName()).append(" field.");
                return -1;
            }
        }

        public int a(TextView textView) {
            if (!d) {
                c = a("mMaxMode");
                d = true;
            }
            if (c != null && a(c, textView) == 1) {
                if (!b) {
                    a = a("mMaximum");
                    b = true;
                }
                if (a != null) {
                    return a(a, textView);
                }
            }
            return -1;
        }

        public void a(TextView textView, @StyleRes int resId) {
            textView.setTextAppearance(textView.getContext(), resId);
        }
    }

    @RequiresApi(16)
    static class a extends f {
        a() {
        }

        public final int a(TextView textView) {
            return textView.getMaxLines();
        }
    }

    @RequiresApi(17)
    static class b extends a {
        b() {
        }

        public void a(@NonNull TextView textView, @Nullable Drawable start) {
            Drawable drawable;
            boolean rtl = true;
            if (textView.getLayoutDirection() != 1) {
                rtl = false;
            }
            if (rtl) {
                drawable = null;
            } else {
                drawable = start;
            }
            if (!rtl) {
                start = null;
            }
            textView.setCompoundDrawables(drawable, null, start, null);
        }
    }

    @RequiresApi(18)
    static class c extends b {
        c() {
        }

        public final void a(@NonNull TextView textView, @Nullable Drawable start) {
            textView.setCompoundDrawablesRelative(start, null, null, null);
        }
    }

    @RequiresApi(23)
    static class d extends c {
        d() {
        }

        public final void a(@NonNull TextView textView, @StyleRes int resId) {
            textView.setTextAppearance(resId);
        }
    }

    @RequiresApi(26)
    static class e extends d {
        e() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 26) {
            a = new e();
        } else if (VERSION.SDK_INT >= 23) {
            a = new d();
        } else if (VERSION.SDK_INT >= 18) {
            a = new c();
        } else if (VERSION.SDK_INT >= 17) {
            a = new b();
        } else if (VERSION.SDK_INT >= 16) {
            a = new a();
        } else {
            a = new f();
        }
    }

    public static void a(@NonNull TextView textView, @Nullable Drawable start) {
        a.a(textView, start);
    }

    public static int a(@NonNull TextView textView) {
        return a.a(textView);
    }

    public static void a(@NonNull TextView textView, @StyleRes int resId) {
        a.a(textView, resId);
    }
}
