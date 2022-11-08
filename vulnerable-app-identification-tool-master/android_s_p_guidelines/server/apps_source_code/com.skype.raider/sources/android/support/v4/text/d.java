package android.support.v4.text;

import android.support.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

@RequiresApi(14)
final class d {
    private static Method a;
    private static Method b;

    static {
        try {
            Class<?> clazz = Class.forName("libcore.icu.ICU");
            if (clazz != null) {
                a = clazz.getMethod("getScript", new Class[]{String.class});
                b = clazz.getMethod("addLikelySubtags", new Class[]{String.class});
            }
        } catch (Exception e) {
            a = null;
            b = null;
        }
    }

    public static String a(Locale locale) {
        String localeWithSubtags = b(locale);
        if (localeWithSubtags != null) {
            return a(localeWithSubtags);
        }
        return null;
    }

    private static String a(String localeStr) {
        try {
            if (a != null) {
                return (String) a.invoke(null, new Object[]{localeStr});
            }
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e2) {
        }
        return null;
    }

    private static String b(Locale locale) {
        String localeStr = locale.toString();
        try {
            if (b != null) {
                return (String) b.invoke(null, new Object[]{localeStr});
            }
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e2) {
        }
        return localeStr;
    }
}
