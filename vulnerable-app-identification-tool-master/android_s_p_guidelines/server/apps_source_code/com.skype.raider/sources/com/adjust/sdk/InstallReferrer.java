package com.adjust.sdk;

import android.content.Context;
import com.microsoft.applications.telemetry.LogConfiguration;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InstallReferrer implements InvocationHandler {
    private static final String PACKAGE_BASE_NAME = "com.android.installreferrer.";
    private static final int STATUS_DEVELOPER_ERROR = 3;
    private static final int STATUS_FEATURE_NOT_SUPPORTED = 2;
    private static final int STATUS_OK = 0;
    private static final int STATUS_SERVICE_DISCONNECTED = -1;
    private static final int STATUS_SERVICE_UNAVAILABLE = 1;
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private Context context;
    private final Object flagLock;
    private boolean hasInstallReferrerBeenRead;
    private ILogger logger = AdjustFactory.getLogger();
    private Object referrerClient;
    private int retries;
    private TimerOnce retryTimer;
    private int retryWaitTime = LogConfiguration.BASE_BACKOFF_FOR_SENDING_RETRIES_MILLIS;

    public InstallReferrer(Context context, IActivityHandler activityHandler) {
        this.context = context;
        this.flagLock = new Object();
        this.hasInstallReferrerBeenRead = false;
        this.retries = 0;
        this.retryTimer = new TimerOnce(new Runnable(this) {
            final /* synthetic */ InstallReferrer a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.startConnection();
            }
        }, "InstallReferrer");
        this.activityHandlerWeakRef = new WeakReference(activityHandler);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startConnection() {
        if (AdjustFactory.getTryInstallReferrer()) {
            closeReferrerClient();
            synchronized (this.flagLock) {
                if (this.hasInstallReferrerBeenRead) {
                    this.logger.debug("Install referrer has already been read", new Object[0]);
                }
            }
        }
    }

    private Object createInstallReferrerClient(Context context) {
        Object obj = null;
        try {
            return Reflection.invokeInstanceMethod(Reflection.invokeStaticMethod("com.android.installreferrer.api.InstallReferrerClient", "newBuilder", new Class[]{Context.class}, context), "build", null, new Object[0]);
        } catch (ClassNotFoundException ex) {
            this.logger.warn("InstallReferrer not integrated in project (%s) thrown by (%s)", ex.getMessage(), ex.getClass().getCanonicalName());
            return obj;
        } catch (Exception ex2) {
            this.logger.error("createInstallReferrerClient error (%s) from (%s)", ex2.getMessage(), ex2.getClass().getCanonicalName());
            return obj;
        }
    }

    private Class getInstallReferrerStateListenerClass() {
        try {
            return Class.forName("com.android.installreferrer.api.InstallReferrerStateListener");
        } catch (Exception ex) {
            this.logger.error("getInstallReferrerStateListenerClass error (%s) from (%s)", ex.getMessage(), ex.getClass().getCanonicalName());
            return null;
        }
    }

    private Object createProxyInstallReferrerStateListener(Class installReferrerStateListenerClass) {
        Object proxyInstance = null;
        try {
            return Proxy.newProxyInstance(installReferrerStateListenerClass.getClassLoader(), new Class[]{installReferrerStateListenerClass}, this);
        } catch (IllegalArgumentException e) {
            this.logger.error("InstallReferrer proxy violating parameter restrictions", new Object[0]);
            return proxyInstance;
        } catch (NullPointerException e2) {
            this.logger.error("Null argument passed to InstallReferrer proxy", new Object[0]);
            return proxyInstance;
        }
    }

    private void startConnection(Class listenerClass, Object listenerProxy) {
        try {
            Reflection.invokeInstanceMethod(this.referrerClient, "startConnection", new Class[]{listenerClass}, listenerProxy);
        } catch (InvocationTargetException ex) {
            if (Util.hasRootCause(ex)) {
                this.logger.error("InstallReferrer encountered an InvocationTargetException %s", Util.getRootCause(ex));
            }
        } catch (Exception ex2) {
            this.logger.error("startConnection error (%s) thrown by (%s)", ex2.getMessage(), ex2.getClass().getCanonicalName());
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        this.logger.debug("InstallReferrer invoke method name: %s", methodName);
        for (Object arg : args) {
            this.logger.debug("InstallReferrer invoke arg: %s", arg);
        }
        if (methodName.equals("onInstallReferrerSetupFinished")) {
            onInstallReferrerSetupFinishedInt(((Integer) args[0]).intValue());
        } else if (methodName.equals("onInstallReferrerServiceDisconnected")) {
            this.logger.debug("InstallReferrer onInstallReferrerServiceDisconnected", new Object[0]);
        }
        return null;
    }

    private void onInstallReferrerSetupFinishedInt(int responseCode) {
        switch (responseCode) {
            case -1:
                this.logger.debug("Play Store service is not connected now. Retrying ...", new Object[0]);
                retry();
                return;
            case 0:
                try {
                    Object referrerDetails = getInstallReferrer();
                    this.logger.debug("installReferrer: %s, clickTime: %d, installBeginTime: %d", getStringInstallReferrer(referrerDetails), Long.valueOf(getReferrerClickTimestampSeconds(referrerDetails)), Long.valueOf(getInstallBeginTimestampSeconds(referrerDetails)));
                    IActivityHandler activityHandler = (IActivityHandler) this.activityHandlerWeakRef.get();
                    if (activityHandler != null) {
                        activityHandler.sendInstallReferrer(clickTime, installBegin, installReferrer);
                    }
                    synchronized (this.flagLock) {
                        this.hasInstallReferrerBeenRead = true;
                    }
                    closeReferrerClient();
                    return;
                } catch (Exception e) {
                    this.logger.warn("Couldn't get install referrer from client (%s). Retrying ...", e.getMessage());
                    retry();
                    return;
                }
            case 1:
                this.logger.debug("Could not initiate connection to the Install Referrer service. Retrying ...", new Object[0]);
                retry();
                return;
            case 2:
                this.logger.debug("Install referrer not available on the current Play Store app.", new Object[0]);
                return;
            case 3:
                this.logger.debug("Install referrer general errors caused by incorrect usage. Retrying ...", new Object[0]);
                retry();
                return;
            default:
                this.logger.debug("Unexpected response code of install referrer response: %d", Integer.valueOf(responseCode));
                closeReferrerClient();
                return;
        }
    }

    private Object getInstallReferrer() {
        Object obj = null;
        if (this.referrerClient == null) {
            return obj;
        }
        try {
            return Reflection.invokeInstanceMethod(this.referrerClient, "getInstallReferrer", null, new Object[0]);
        } catch (Exception e) {
            this.logger.error("getInstallReferrer error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return obj;
        }
    }

    private String getStringInstallReferrer(Object referrerDetails) {
        if (referrerDetails == null) {
            return null;
        }
        try {
            return (String) Reflection.invokeInstanceMethod(referrerDetails, "getInstallReferrer", null, new Object[0]);
        } catch (Exception e) {
            this.logger.error("getStringInstallReferrer error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return null;
        }
    }

    private long getReferrerClickTimestampSeconds(Object referrerDetails) {
        long j = -1;
        if (referrerDetails == null) {
            return j;
        }
        try {
            return ((Long) Reflection.invokeInstanceMethod(referrerDetails, "getReferrerClickTimestampSeconds", null, new Object[0])).longValue();
        } catch (Exception e) {
            this.logger.error("getReferrerClickTimestampSeconds error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return j;
        }
    }

    private long getInstallBeginTimestampSeconds(Object referrerDetails) {
        long j = -1;
        if (referrerDetails == null) {
            return j;
        }
        try {
            return ((Long) Reflection.invokeInstanceMethod(referrerDetails, "getInstallBeginTimestampSeconds", null, new Object[0])).longValue();
        } catch (Exception e) {
            this.logger.error("getInstallBeginTimestampSeconds error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return j;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void retry() {
        synchronized (this.flagLock) {
            if (this.hasInstallReferrerBeenRead) {
                this.logger.debug("Install referrer has already been read", new Object[0]);
            }
        }
    }

    private void closeReferrerClient() {
        if (this.referrerClient != null) {
            try {
                Reflection.invokeInstanceMethod(this.referrerClient, "endConnection", null, new Object[0]);
            } catch (Exception e) {
                this.logger.error("closeReferrerClient error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            }
            this.referrerClient = null;
        }
    }
}
