package com.skype;

import com.skype.GI.CONNSTATUS;
import com.skype.GI.FILEERROR;
import com.skype.GI.GIIListener;
import com.skype.GI.GetLastFileError_Result;
import com.skype.GI.LIBSTATUS;
import com.skype.GI.PROXYTYPE;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

public class GIImpl implements GI, a {
    private final Set<GIIListener> m_listeners;
    protected int m_nativeObject;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroyGI(this.nativeObject);
        }
    }

    private native byte[] getActiveLogFileNameNativeString();

    private native byte[] getDebugInfoNativeString();

    private native byte[] getFatalErrorMessageNativeString();

    private native Setup getSetup(byte[] bArr);

    private static native void initPlatform(byte[] bArr, boolean z, boolean z2, boolean z3);

    private static native void initPlatform(byte[] bArr, byte[] bArr2);

    private static native void initPlatform(byte[] bArr, byte[] bArr2, boolean z);

    private native void log(byte[] bArr, byte[] bArr2);

    public static native void uninitPlatform();

    public native GetLastFileError_Result getLastFileError();

    public native LIBSTATUS getLibStatus();

    public native Setup getSetup();

    public native void pollEvents(int i);

    public native void updateLogName();

    protected GIImpl(int nativeObject) {
        this(SkypeFactory.getInstance(), nativeObject);
    }

    protected GIImpl(ObjectInterfaceFactory factory, int nativeObject) {
        this.m_listeners = new HashSet();
        this.m_nativeObject = nativeObject;
        b.getInstance().addDestructibleObject(factory, this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public void addListener(GIIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(GIIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }

    public void onConnStatusChange(CONNSTATUS connStatus) {
        synchronized (this.m_listeners) {
            for (GIIListener onConnStatusChange : this.m_listeners) {
                onConnStatusChange.onConnStatusChange(this, connStatus);
            }
        }
    }

    public void onFileError(FILEERROR error) {
        synchronized (this.m_listeners) {
            for (GIIListener onFileError : this.m_listeners) {
                onFileError.onFileError(this, error);
            }
        }
    }

    public void onLibStatusChange(LIBSTATUS newStatus) {
        synchronized (this.m_listeners) {
            for (GIIListener onLibStatusChange : this.m_listeners) {
                onLibStatusChange.onLibStatusChange(this, newStatus);
            }
        }
    }

    public void onNodeinfoChange(byte[] nodeinfo) {
        synchronized (this.m_listeners) {
            for (GIIListener onNodeinfoChange : this.m_listeners) {
                onNodeinfoChange.onNodeinfoChange(this, nodeinfo);
            }
        }
    }

    public void onProxyAuthenticationFailure(PROXYTYPE proxyType) {
        synchronized (this.m_listeners) {
            for (GIIListener onProxyAuthenticationFailure : this.m_listeners) {
                onProxyAuthenticationFailure.onProxyAuthenticationFailure(this, proxyType);
            }
        }
    }

    public String getActiveLogFileName() {
        return NativeStringConvert.ConvertFromNativeBytes(getActiveLogFileNameNativeString());
    }

    public String getDebugInfo() {
        return NativeStringConvert.ConvertFromNativeBytes(getDebugInfoNativeString());
    }

    public String getFatalErrorMessage() {
        return NativeStringConvert.ConvertFromNativeBytes(getFatalErrorMessageNativeString());
    }

    public Setup getSetup(String profile) {
        return getSetup(NativeStringConvert.ConvertToNativeBytes(profile));
    }

    public static void initPlatform(String appDataPath, String logFileNameUTF8, boolean encryptLog) {
        initPlatform(NativeStringConvert.ConvertToNativeBytes(appDataPath), NativeStringConvert.ConvertToNativeBytes(logFileNameUTF8), encryptLog);
    }

    public static void initPlatform(String logFileNameUTF8, boolean encryptLog, boolean logThreadID, boolean mainProcess) {
        initPlatform(NativeStringConvert.ConvertToNativeBytes(logFileNameUTF8), encryptLog, logThreadID, mainProcess);
    }

    public static void initPlatform() {
        initPlatform("", true, true, true);
    }

    public static void initPlatform(String logFileNameUTF8) {
        initPlatform(logFileNameUTF8, true, true, true);
    }

    public static void initPlatform(String logFileNameUTF8, boolean encryptLog) {
        initPlatform(logFileNameUTF8, encryptLog, true, true);
    }

    public static void initPlatform(String logFileNameUTF8, boolean encryptLog, boolean logThreadID) {
        initPlatform(logFileNameUTF8, encryptLog, logThreadID, true);
    }

    public static void initPlatform(String logFileNameUTF8, String buildVersion) {
        initPlatform(NativeStringConvert.ConvertToNativeBytes(logFileNameUTF8), NativeStringConvert.ConvertToNativeBytes(buildVersion));
    }

    public void log(String subsystem, String format) {
        log(NativeStringConvert.ConvertToNativeBytes(subsystem), NativeStringConvert.ConvertToNativeBytes(format));
    }

    public void pollEvents() {
        pollEvents(0);
    }
}
