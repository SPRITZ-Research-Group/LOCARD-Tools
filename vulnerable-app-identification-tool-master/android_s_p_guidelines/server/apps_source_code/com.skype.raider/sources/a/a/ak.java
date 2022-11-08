package a.a;

import android.content.Context;
import android.support.annotation.NonNull;
import com.appboy.a;
import com.appboy.f.c;
import com.appboy.f.i;
import java.lang.reflect.Method;

public class ak {
    private static final String a = c.a(ak.class);
    private static final String[] b = new String[]{"com.google.firebase.iid.FirebaseInstanceId"};
    private final Context c;

    public ak(Context context) {
        this.c = context;
    }

    public final void a(@NonNull String str) {
        String b = b(str);
        if (i.b(b)) {
            c.f(a, "Obtained an empty or null Firebase Cloud Messaging registration token. Not registering token.");
        } else {
            a.a(this.c).d(b);
        }
    }

    public static boolean a(Context context, com.appboy.a.a aVar) {
        if (i.b(aVar.B())) {
            c.f(a, "Firebase Cloud Messaging requires a non-null and non-empty sender ID.");
            return false;
        } else if (cs.b(context)) {
            try {
                ClassLoader classLoader = ak.class.getClassLoader();
                for (String str : b) {
                    if (Class.forName(str, false, classLoader) == null) {
                        c.f(a, "Automatic registration for Firebase Cloud Messaging requires the following class to be present: " + str);
                        return false;
                    }
                }
                return true;
            } catch (Throwable e) {
                c.d(a, "Caught error while checking for required classes for Firebase Cloud Messaging.", e);
                return false;
            }
        } else {
            c.f(a, "Firebase Cloud Messaging requires the Google Play Store to be installed.");
            return false;
        }
    }

    private static String b(@NonNull String str) {
        try {
            Method a = cx.a("com.google.firebase.iid.FirebaseInstanceId", "getInstance", new Class[0]);
            if (a == null) {
                c.b(a, "Firebase Cloud Messaging 'getInstance' method could not obtained. Not registering for Firebase Cloud Messaging.");
                return null;
            }
            Object a2 = cx.a(null, a, new Object[0]);
            if (a2 == null) {
                c.b(a, "Firebase Cloud Messaging 'InstanceId' object could not invoked. Not registering for Firebase Cloud Messaging.");
                return null;
            }
            Method a3 = cx.a(a2.getClass(), "getToken", String.class, String.class);
            if (a3 == null) {
                c.b(a, "Firebase Cloud Messaging 'FirebaseInstanceId.getInstance().getToken()' method could not obtained. Not registering for Firebase Cloud Messaging.");
                return null;
            }
            a2 = cx.a(a2, a3, str, "FCM");
            return (a2 == null || !(a2 instanceof String)) ? null : (String) a2;
        } catch (Throwable e) {
            c.d(a, "Failed to register for Firebase Cloud Messaging", e);
            return null;
        }
    }
}
