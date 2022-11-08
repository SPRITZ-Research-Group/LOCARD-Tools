package jp.naver.line.android.common;

import android.app.Application;
import jp.naver.line.android.t;

@Deprecated
public final class e {
    public static String a = null;
    private static boolean b = true;
    private static Application c = null;
    private static boolean d = false;
    private static t e;

    public static synchronized void a(Application application, boolean z, t tVar) {
        synchronized (e.class) {
            b = false;
            c = application;
            d = z;
            e = tVar;
        }
    }

    public static boolean a() {
        return d;
    }

    public static t b() {
        return e;
    }

    public static synchronized Application c() {
        Application application;
        synchronized (e.class) {
            if (c != null) {
                application = c;
            } else if (b) {
                throw new RuntimeException("Application was detached.");
            } else {
                throw new RuntimeException("Application is not loaded.");
            }
        }
        return application;
    }
}
