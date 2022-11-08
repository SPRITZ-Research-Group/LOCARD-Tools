package com.skype.rt;

import java.util.concurrent.ConcurrentHashMap;

public final class LogFactory {
    static LogFactory instance;
    private static final Object lck = new Object();
    private static ConcurrentHashMap<String, LogComponent> registeredComponents = new ConcurrentHashMap();
    private long nativeCtxPtr = nativeInitFactory();

    private static native void nativeDispose(long j);

    private final native long nativeInitFactory();

    private static native void nativeStaticInit();

    public final native void logFlush();

    public static LogFactory getInstance() {
        Throwable th;
        if (Auf.inited.get() <= 0) {
            return null;
        }
        LogFactory ret = instance;
        if (ret != null) {
            return ret;
        }
        synchronized (lck) {
            try {
                ret = instance;
                if (ret != null) {
                    return ret;
                }
                LogFactory ret2 = new LogFactory();
                try {
                    instance = ret2;
                    return ret2;
                } catch (Throwable th2) {
                    th = th2;
                    ret = ret2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    public final void declareComponentSafe(String name, boolean safe) {
        getComponentCore(name).setSafe(safe);
    }

    public final LogComponent getComponent(String name) {
        return getComponentCore(name);
    }

    final void dispose() {
        synchronized (lck) {
            synchronized (registeredComponents) {
                registeredComponents.clear();
            }
            instance = null;
            nativeDispose(this.nativeCtxPtr);
        }
    }

    private LogFactory() {
    }

    private LogComponent getComponentCore(String name) {
        Throwable th;
        if (Auf.inited.get() <= 0) {
            return null;
        }
        LogComponent ret = (LogComponent) registeredComponents.get(name);
        if (ret != null) {
            return ret;
        }
        synchronized (registeredComponents) {
            try {
                ret = (LogComponent) registeredComponents.get(name);
                if (ret != null) {
                    return ret;
                }
                LogComponent ret2 = new LogComponent(name);
                try {
                    registeredComponents.put(name, ret2);
                    return ret2;
                } catch (Throwable th2) {
                    th = th2;
                    ret = ret2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    private void LLChangeFromNative(String compName, int newLevel) {
        LogComponent lc = (LogComponent) registeredComponents.get(compName);
        if (lc != null) {
            lc.setOnlyJavaLL(newLevel);
        }
    }

    static {
        nativeStaticInit();
    }
}
