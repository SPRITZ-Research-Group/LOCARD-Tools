package androidx.core.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.INotificationSideChannel.Stub;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class ak implements ServiceConnection, Callback {
    private final Context a;
    private final HandlerThread b;
    private final Handler c;
    private final Map<ComponentName, al> d = new HashMap();
    private Set<String> e = new HashSet();

    ak(Context context) {
        this.a = context;
        this.b = new HandlerThread("NotificationManagerCompat");
        this.b.start();
        this.c = new Handler(this.b.getLooper(), this);
    }

    public final void a(am amVar) {
        this.c.obtainMessage(0, amVar).sendToTarget();
    }

    public final boolean handleMessage(Message message) {
        al alVar;
        switch (message.what) {
            case 0:
                Iterator it;
                am amVar = (am) message.obj;
                Set b = ag.b(this.a);
                if (!b.equals(this.e)) {
                    this.e = b;
                    List<ResolveInfo> queryIntentServices = this.a.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
                    Set<ComponentName> hashSet = new HashSet();
                    for (ResolveInfo resolveInfo : queryIntentServices) {
                        if (b.contains(resolveInfo.serviceInfo.packageName)) {
                            ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
                            if (resolveInfo.serviceInfo.permission != null) {
                                StringBuilder stringBuilder = new StringBuilder("Permission present on component ");
                                stringBuilder.append(componentName);
                                stringBuilder.append(", not adding listener record.");
                                Log.w("NotifManCompat", stringBuilder.toString());
                            } else {
                                hashSet.add(componentName);
                            }
                        }
                    }
                    for (ComponentName componentName2 : hashSet) {
                        if (!this.d.containsKey(componentName2)) {
                            if (Log.isLoggable("NotifManCompat", 3)) {
                                new StringBuilder("Adding listener record for ").append(componentName2);
                            }
                            this.d.put(componentName2, new al(componentName2));
                        }
                    }
                    it = this.d.entrySet().iterator();
                    while (it.hasNext()) {
                        Entry entry = (Entry) it.next();
                        if (!hashSet.contains(entry.getKey())) {
                            if (Log.isLoggable("NotifManCompat", 3)) {
                                new StringBuilder("Removing listener record for ").append(entry.getKey());
                            }
                            a((al) entry.getValue());
                            it.remove();
                        }
                    }
                }
                for (al alVar2 : this.d.values()) {
                    alVar2.d.add(amVar);
                    c(alVar2);
                }
                return true;
            case 1:
                aj ajVar = (aj) message.obj;
                ComponentName componentName3 = ajVar.a;
                IBinder iBinder = ajVar.b;
                al alVar3 = (al) this.d.get(componentName3);
                if (alVar3 != null) {
                    alVar3.c = Stub.asInterface(iBinder);
                    alVar3.e = 0;
                    c(alVar3);
                }
                return true;
            case 2:
                alVar = (al) this.d.get((ComponentName) message.obj);
                if (alVar != null) {
                    a(alVar);
                }
                return true;
            case 3:
                alVar = (al) this.d.get((ComponentName) message.obj);
                if (alVar != null) {
                    c(alVar);
                }
                return true;
            default:
                return false;
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            new StringBuilder("Connected to service ").append(componentName);
        }
        this.c.obtainMessage(1, new aj(componentName, iBinder)).sendToTarget();
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            new StringBuilder("Disconnected from service ").append(componentName);
        }
        this.c.obtainMessage(2, componentName).sendToTarget();
    }

    private void a(al alVar) {
        if (alVar.b) {
            this.a.unbindService(this);
            alVar.b = false;
        }
        alVar.c = null;
    }

    private void b(al alVar) {
        if (!this.c.hasMessages(3, alVar.a)) {
            alVar.e++;
            StringBuilder stringBuilder;
            if (alVar.e > 6) {
                stringBuilder = new StringBuilder("Giving up on delivering ");
                stringBuilder.append(alVar.d.size());
                stringBuilder.append(" tasks to ");
                stringBuilder.append(alVar.a);
                stringBuilder.append(" after ");
                stringBuilder.append(alVar.e);
                stringBuilder.append(" retries");
                Log.w("NotifManCompat", stringBuilder.toString());
                alVar.d.clear();
                return;
            }
            int i = (1 << (alVar.e - 1)) * 1000;
            if (Log.isLoggable("NotifManCompat", 3)) {
                stringBuilder = new StringBuilder("Scheduling retry for ");
                stringBuilder.append(i);
                stringBuilder.append(" ms");
            }
            this.c.sendMessageDelayed(this.c.obtainMessage(3, alVar.a), (long) i);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(al alVar) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            StringBuilder stringBuilder = new StringBuilder("Processing component ");
            stringBuilder.append(alVar.a);
            stringBuilder.append(", ");
            stringBuilder.append(alVar.d.size());
            stringBuilder.append(" queued tasks");
        }
        if (!alVar.d.isEmpty()) {
            boolean z;
            StringBuilder stringBuilder2;
            if (alVar.b) {
                z = true;
            } else {
                alVar.b = this.a.bindService(new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(alVar.a), this, 33);
                if (alVar.b) {
                    alVar.e = 0;
                } else {
                    stringBuilder2 = new StringBuilder("Unable to bind to listener ");
                    stringBuilder2.append(alVar.a);
                    Log.w("NotifManCompat", stringBuilder2.toString());
                    this.a.unbindService(this);
                }
                z = alVar.b;
            }
            if (!z || alVar.c == null) {
                b(alVar);
                return;
            }
            while (true) {
                am amVar = (am) alVar.d.peek();
                if (amVar == null) {
                    break;
                }
                try {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        new StringBuilder("Sending task ").append(amVar);
                    }
                    amVar.a(alVar.c);
                    alVar.d.remove();
                } catch (Throwable e) {
                    stringBuilder2 = new StringBuilder("RemoteException communicating with ");
                    stringBuilder2.append(alVar.a);
                    Log.w("NotifManCompat", stringBuilder2.toString(), e);
                }
            }
            if (!alVar.d.isEmpty()) {
                b(alVar);
            }
        }
    }
}
