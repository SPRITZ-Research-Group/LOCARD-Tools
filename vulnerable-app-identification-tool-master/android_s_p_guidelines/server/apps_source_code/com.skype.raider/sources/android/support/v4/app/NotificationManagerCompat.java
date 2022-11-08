package android.support.v4.app;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import android.support.annotation.GuardedBy;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class NotificationManagerCompat {
    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
    private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
    private static final String TAG = "NotifManCompat";
    @GuardedBy("sEnabledNotificationListenersLock")
    private static Set<String> sEnabledNotificationListenerPackages = new HashSet();
    @GuardedBy("sEnabledNotificationListenersLock")
    private static String sEnabledNotificationListeners;
    private static final Object sEnabledNotificationListenersLock = new Object();
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    private static d sSideChannelManager;
    private final Context mContext;
    private final NotificationManager mNotificationManager = ((NotificationManager) this.mContext.getSystemService("notification"));

    private interface e {
        void a(n nVar) throws RemoteException;
    }

    private static class a implements e {
        final String a;
        final int b;
        final String c;
        final boolean d;

        a(String packageName) {
            this.a = packageName;
            this.b = 0;
            this.c = null;
            this.d = true;
        }

        a(String packageName, int id, String tag) {
            this.a = packageName;
            this.b = id;
            this.c = tag;
            this.d = false;
        }

        public final void a(n service) throws RemoteException {
            if (this.d) {
                service.a(this.a);
            } else {
                service.a(this.a, this.b, this.c);
            }
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("CancelTask[");
            sb.append("packageName:").append(this.a);
            sb.append(", id:").append(this.b);
            sb.append(", tag:").append(this.c);
            sb.append(", all:").append(this.d);
            sb.append("]");
            return sb.toString();
        }
    }

    private static class b implements e {
        final String a;
        final int b;
        final String c;
        final Notification d;

        b(String packageName, int id, String tag, Notification notif) {
            this.a = packageName;
            this.b = id;
            this.c = tag;
            this.d = notif;
        }

        public final void a(n service) throws RemoteException {
            service.a(this.a, this.b, this.c, this.d);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("NotifyTask[");
            sb.append("packageName:").append(this.a);
            sb.append(", id:").append(this.b);
            sb.append(", tag:").append(this.c);
            sb.append("]");
            return sb.toString();
        }
    }

    private static class c {
        final ComponentName a;
        final IBinder b;

        c(ComponentName componentName, IBinder iBinder) {
            this.a = componentName;
            this.b = iBinder;
        }
    }

    private static class d implements ServiceConnection, Callback {
        private final Context a;
        private final HandlerThread b;
        private final Handler c;
        private final Map<ComponentName, a> d = new HashMap();
        private Set<String> e = new HashSet();

        private static class a {
            public final ComponentName a;
            public boolean b = false;
            public n c;
            public LinkedList<e> d = new LinkedList();
            public int e = 0;

            public a(ComponentName componentName) {
                this.a = componentName;
            }
        }

        public d(Context context) {
            this.a = context;
            this.b = new HandlerThread("NotificationManagerCompat");
            this.b.start();
            this.c = new Handler(this.b.getLooper(), this);
        }

        public final void a(e task) {
            this.c.obtainMessage(0, task).sendToTarget();
        }

        public final boolean handleMessage(Message msg) {
            a aVar;
            switch (msg.what) {
                case 0:
                    Iterator it;
                    e eVar = (e) msg.obj;
                    Set enabledListenerPackages = NotificationManagerCompat.getEnabledListenerPackages(this.a);
                    if (!enabledListenerPackages.equals(this.e)) {
                        this.e = enabledListenerPackages;
                        List<ResolveInfo> queryIntentServices = this.a.getPackageManager().queryIntentServices(new Intent().setAction(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL), 0);
                        Set<ComponentName> hashSet = new HashSet();
                        for (ResolveInfo resolveInfo : queryIntentServices) {
                            if (enabledListenerPackages.contains(resolveInfo.serviceInfo.packageName)) {
                                ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
                                if (resolveInfo.serviceInfo.permission != null) {
                                    new StringBuilder("Permission present on component ").append(componentName).append(", not adding listener record.");
                                } else {
                                    hashSet.add(componentName);
                                }
                            }
                        }
                        for (ComponentName componentName2 : hashSet) {
                            if (!this.d.containsKey(componentName2)) {
                                if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                                    new StringBuilder("Adding listener record for ").append(componentName2);
                                }
                                this.d.put(componentName2, new a(componentName2));
                            }
                        }
                        it = this.d.entrySet().iterator();
                        while (it.hasNext()) {
                            Entry entry = (Entry) it.next();
                            if (!hashSet.contains(entry.getKey())) {
                                if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                                    new StringBuilder("Removing listener record for ").append(entry.getKey());
                                }
                                a((a) entry.getValue());
                                it.remove();
                            }
                        }
                    }
                    for (a aVar2 : this.d.values()) {
                        aVar2.d.add(eVar);
                        c(aVar2);
                    }
                    return true;
                case 1:
                    c event = msg.obj;
                    ComponentName componentName3 = event.a;
                    IBinder iBinder = event.b;
                    aVar = (a) this.d.get(componentName3);
                    if (aVar != null) {
                        aVar.c = android.support.v4.app.n.a.a(iBinder);
                        aVar.e = 0;
                        c(aVar);
                    }
                    return true;
                case 2:
                    aVar = (a) this.d.get((ComponentName) msg.obj);
                    if (aVar != null) {
                        a(aVar);
                    }
                    return true;
                case 3:
                    aVar = (a) this.d.get((ComponentName) msg.obj);
                    if (aVar != null) {
                        c(aVar);
                    }
                    return true;
                default:
                    return false;
            }
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                new StringBuilder("Connected to service ").append(componentName);
            }
            this.c.obtainMessage(1, new c(componentName, iBinder)).sendToTarget();
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                new StringBuilder("Disconnected from service ").append(componentName);
            }
            this.c.obtainMessage(2, componentName).sendToTarget();
        }

        private void a(a record) {
            if (record.b) {
                this.a.unbindService(this);
                record.b = false;
            }
            record.c = null;
        }

        private void b(a record) {
            if (!this.c.hasMessages(3, record.a)) {
                record.e++;
                if (record.e > 6) {
                    new StringBuilder("Giving up on delivering ").append(record.d.size()).append(" tasks to ").append(record.a).append(" after ").append(record.e).append(" retries");
                    record.d.clear();
                    return;
                }
                int delayMs = (1 << (record.e - 1)) * 1000;
                if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                    new StringBuilder("Scheduling retry for ").append(delayMs).append(" ms");
                }
                this.c.sendMessageDelayed(this.c.obtainMessage(3, record.a), (long) delayMs);
            }
        }

        private void c(a record) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                new StringBuilder("Processing component ").append(record.a).append(", ").append(record.d.size()).append(" queued tasks");
            }
            if (!record.d.isEmpty()) {
                boolean z;
                if (record.b) {
                    z = true;
                } else {
                    record.b = this.a.bindService(new Intent(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL).setComponent(record.a), this, 33);
                    if (record.b) {
                        record.e = 0;
                    } else {
                        new StringBuilder("Unable to bind to listener ").append(record.a);
                        this.a.unbindService(this);
                    }
                    z = record.b;
                }
                if (!z || record.c == null) {
                    b(record);
                    return;
                }
                while (true) {
                    e task = (e) record.d.peek();
                    if (task == null) {
                        break;
                    }
                    try {
                        if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                            new StringBuilder("Sending task ").append(task);
                        }
                        task.a(record.c);
                        record.d.remove();
                    } catch (DeadObjectException e) {
                        if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                            new StringBuilder("Remote service has died: ").append(record.a);
                        }
                    } catch (RemoteException e2) {
                        new StringBuilder("RemoteException communicating with ").append(record.a);
                    }
                }
                if (!record.d.isEmpty()) {
                    b(record);
                }
            }
        }
    }

    public static NotificationManagerCompat from(Context context) {
        return new NotificationManagerCompat(context);
    }

    private NotificationManagerCompat(Context context) {
        this.mContext = context;
    }

    public final void cancel(int id) {
        cancel(null, id);
    }

    public final void cancel(String tag, int id) {
        this.mNotificationManager.cancel(tag, id);
        if (VERSION.SDK_INT <= 19) {
            pushSideChannelQueue(new a(this.mContext.getPackageName(), id, tag));
        }
    }

    public final void cancelAll() {
        this.mNotificationManager.cancelAll();
        if (VERSION.SDK_INT <= 19) {
            pushSideChannelQueue(new a(this.mContext.getPackageName()));
        }
    }

    public final void notify(int id, Notification notification) {
        notify(null, id, notification);
    }

    public final void notify(String tag, int id, Notification notification) {
        if (useSideChannelForNotification(notification)) {
            pushSideChannelQueue(new b(this.mContext.getPackageName(), id, tag, notification));
            this.mNotificationManager.cancel(tag, id);
            return;
        }
        this.mNotificationManager.notify(tag, id, notification);
    }

    public final boolean areNotificationsEnabled() {
        if (VERSION.SDK_INT >= 24) {
            return this.mNotificationManager.areNotificationsEnabled();
        }
        if (VERSION.SDK_INT < 19) {
            return true;
        }
        AppOpsManager appOps = (AppOpsManager) this.mContext.getSystemService("appops");
        ApplicationInfo appInfo = this.mContext.getApplicationInfo();
        String pkg = this.mContext.getApplicationContext().getPackageName();
        int uid = appInfo.uid;
        try {
            Class<?> appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, new Class[]{Integer.TYPE, Integer.TYPE, String.class});
            int value = ((Integer) appOpsClass.getDeclaredField(OP_POST_NOTIFICATION).get(Integer.class)).intValue();
            return ((Integer) checkOpNoThrowMethod.invoke(appOps, new Object[]{Integer.valueOf(value), Integer.valueOf(uid), pkg})).intValue() == 0;
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e2) {
        } catch (NoSuchFieldException e3) {
        } catch (InvocationTargetException e4) {
        } catch (IllegalAccessException e5) {
        } catch (RuntimeException e6) {
        }
        return true;
    }

    public final int getImportance() {
        if (VERSION.SDK_INT >= 24) {
            return this.mNotificationManager.getImportance();
        }
        return IMPORTANCE_UNSPECIFIED;
    }

    public static Set<String> getEnabledListenerPackages(Context context) {
        String enabledNotificationListeners = Secure.getString(context.getContentResolver(), SETTING_ENABLED_NOTIFICATION_LISTENERS);
        synchronized (sEnabledNotificationListenersLock) {
            if (enabledNotificationListeners != null) {
                if (!enabledNotificationListeners.equals(sEnabledNotificationListeners)) {
                    String[] components = enabledNotificationListeners.split(":");
                    Set<String> packageNames = new HashSet(components.length);
                    for (String unflattenFromString : components) {
                        ComponentName componentName = ComponentName.unflattenFromString(unflattenFromString);
                        if (componentName != null) {
                            packageNames.add(componentName.getPackageName());
                        }
                    }
                    sEnabledNotificationListenerPackages = packageNames;
                    sEnabledNotificationListeners = enabledNotificationListeners;
                }
            }
        }
        return sEnabledNotificationListenerPackages;
    }

    private static boolean useSideChannelForNotification(Notification notification) {
        Bundle extras = NotificationCompat.a(notification);
        return extras != null && extras.getBoolean(EXTRA_USE_SIDE_CHANNEL);
    }

    private void pushSideChannelQueue(e task) {
        synchronized (sLock) {
            if (sSideChannelManager == null) {
                sSideChannelManager = new d(this.mContext.getApplicationContext());
            }
            sSideChannelManager.a(task);
        }
    }
}
