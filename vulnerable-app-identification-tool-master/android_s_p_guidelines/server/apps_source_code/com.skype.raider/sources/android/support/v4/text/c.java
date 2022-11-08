package android.support.v4.text;

import android.support.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

@RequiresApi(21)
final class c {
    private static Method a;

    static {
        try {
            a = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", new Class[]{Locale.class});
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static String a(Locale locale) {
        try {
            return ((Locale) a.invoke(null, new Object[]{locale})).getScript();
        } catch (InvocationTargetException e) {
        } catch (IllegalAccessException e2) {
        }
        return locale.getScript();
    }
}
