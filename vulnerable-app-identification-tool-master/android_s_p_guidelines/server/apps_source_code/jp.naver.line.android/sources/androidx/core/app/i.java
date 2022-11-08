package androidx.core.app;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.Method;

final class i {
    private static Method a;
    private static boolean b;
    private static Method c;
    private static boolean d;

    public static IBinder a(Bundle bundle, String str) {
        if (!b) {
            try {
                Method method = Bundle.class.getMethod("getIBinder", new Class[]{String.class});
                a = method;
                method.setAccessible(true);
            } catch (Throwable e) {
                Log.i("BundleCompatBaseImpl", "Failed to retrieve getIBinder method", e);
            }
            b = true;
        }
        if (a != null) {
            try {
                return (IBinder) a.invoke(bundle, new Object[]{str});
            } catch (Throwable e2) {
                Log.i("BundleCompatBaseImpl", "Failed to invoke getIBinder via reflection", e2);
                a = null;
            }
        }
        return null;
    }

    public static void a(Bundle bundle, String str, IBinder iBinder) {
        if (!d) {
            try {
                Method method = Bundle.class.getMethod("putIBinder", new Class[]{String.class, IBinder.class});
                c = method;
                method.setAccessible(true);
            } catch (Throwable e) {
                Log.i("BundleCompatBaseImpl", "Failed to retrieve putIBinder method", e);
            }
            d = true;
        }
        if (c != null) {
            try {
                c.invoke(bundle, new Object[]{str, iBinder});
            } catch (Throwable e2) {
                Log.i("BundleCompatBaseImpl", "Failed to invoke putIBinder via reflection", e2);
                c = null;
            }
        }
    }
}
