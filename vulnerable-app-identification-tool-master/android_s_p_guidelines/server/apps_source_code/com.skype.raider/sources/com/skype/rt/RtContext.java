package com.skype.rt;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import java.lang.reflect.InvocationTargetException;

public class RtContext {
    private static final String TAG = "rt::";
    private static Context m_context;

    protected static synchronized Context getContext() {
        Context ctx;
        synchronized (RtContext.class) {
            if (m_context != null) {
                ctx = m_context;
            } else {
                ctx = null;
                if (VERSION.SDK_INT >= 19) {
                    ctx = obtainContext();
                    if (ctx != null) {
                        m_context = ctx;
                    }
                }
            }
        }
        return ctx;
    }

    public static synchronized void setContext(Context ctx) {
        synchronized (RtContext.class) {
            if (m_context == null && ctx != null) {
                m_context = ctx;
            }
        }
    }

    protected static WifiManager getWifiManager() {
        return (WifiManager) getContext().getSystemService("wifi");
    }

    protected static ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) getContext().getSystemService("connectivity");
    }

    private static Context obtainContext() {
        Context ctx = null;
        try {
            return (Context) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException e) {
            return ctx;
        } catch (NoSuchMethodException e2) {
            return ctx;
        } catch (IllegalAccessException e3) {
            return ctx;
        } catch (InvocationTargetException e4) {
            return ctx;
        }
    }
}
