package android.support.v4.media;

import android.media.AudioAttributes;
import android.support.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
final class a {
    private static Method a;

    static final class a {
        private AudioAttributes a;

        public final AudioAttributes a() {
            return this.a;
        }
    }

    public static int a(a aaWrap) {
        AudioAttributes aaObject = aaWrap.a();
        try {
            if (a == null) {
                a = AudioAttributes.class.getMethod("toLegacyStreamType", new Class[]{AudioAttributes.class});
            }
            return ((Integer) a.invoke(null, new Object[]{aaObject})).intValue();
        } catch (NoSuchMethodException e) {
            return -1;
        } catch (InvocationTargetException e2) {
            return -1;
        } catch (IllegalAccessException e3) {
            return -1;
        } catch (ClassCastException e4) {
            return -1;
        }
    }
}
