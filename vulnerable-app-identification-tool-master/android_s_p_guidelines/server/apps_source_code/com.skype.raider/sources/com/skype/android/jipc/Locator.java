package com.skype.android.jipc;

import android.os.IBinder;
import java.lang.reflect.Method;

public class Locator {
    private static volatile Locator c;
    final Class<?> a;
    final Method b;

    private Locator() {
        try {
            this.a = Class.forName("android.os.ServiceManager");
            this.b = this.a.getDeclaredMethod("getService", new Class[]{String.class});
            this.b.setAccessible(true);
        } catch (Exception e) {
            throw new LoopholeClosedException(e);
        }
    }

    public static Locator a() {
        if (c == null) {
            c = new Locator();
        }
        return c;
    }

    public final IBinder a(String serviceName) {
        try {
            return (IBinder) this.b.invoke(null, new Object[]{serviceName});
        } catch (Exception e) {
            throw new LoopholeClosedException(serviceName, e);
        }
    }
}
