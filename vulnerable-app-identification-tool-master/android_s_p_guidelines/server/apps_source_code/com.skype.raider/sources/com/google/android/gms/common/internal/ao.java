package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.google.android.gms.common.stats.b;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;

final class ao extends m implements Callback {
    @GuardedBy("mConnectionStatus")
    private final HashMap<a, ap> a = new HashMap();
    private final Context b;
    private final Handler c;
    private final b d;
    private final long e;
    private final long f;

    ao(Context context) {
        this.b = context.getApplicationContext();
        this.c = new Handler(context.getMainLooper(), this);
        this.d = b.a();
        this.e = 5000;
        this.f = 300000;
    }

    protected final boolean a(a aVar, ServiceConnection serviceConnection) {
        boolean c;
        ab.a((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.a) {
            ap apVar = (ap) this.a.get(aVar);
            if (apVar != null) {
                this.c.removeMessages(0, aVar);
                if (!apVar.c(serviceConnection)) {
                    apVar.a(serviceConnection);
                    switch (apVar.d()) {
                        case 1:
                            serviceConnection.onServiceConnected(apVar.g(), apVar.f());
                            break;
                        case 2:
                            apVar.a();
                            break;
                    }
                }
                String valueOf = String.valueOf(aVar);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 81).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(valueOf).toString());
            }
            apVar = new ap(this, aVar);
            apVar.a(serviceConnection);
            apVar.a();
            this.a.put(aVar, apVar);
            c = apVar.c();
        }
        return c;
    }

    protected final void b(a aVar, ServiceConnection serviceConnection) {
        ab.a((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.a) {
            ap apVar = (ap) this.a.get(aVar);
            String valueOf;
            if (apVar == null) {
                valueOf = String.valueOf(aVar);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 50).append("Nonexistent connection status for service config: ").append(valueOf).toString());
            } else if (apVar.c(serviceConnection)) {
                apVar.b(serviceConnection);
                if (apVar.e()) {
                    this.c.sendMessageDelayed(this.c.obtainMessage(0, aVar), this.e);
                }
            } else {
                valueOf = String.valueOf(aVar);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 76).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(valueOf).toString());
            }
        }
    }

    public final boolean handleMessage(Message message) {
        a aVar;
        ap apVar;
        switch (message.what) {
            case 0:
                synchronized (this.a) {
                    aVar = (a) message.obj;
                    apVar = (ap) this.a.get(aVar);
                    if (apVar != null && apVar.e()) {
                        if (apVar.c()) {
                            apVar.b();
                        }
                        this.a.remove(aVar);
                    }
                }
                return true;
            case 1:
                synchronized (this.a) {
                    aVar = (a) message.obj;
                    apVar = (ap) this.a.get(aVar);
                    if (apVar != null && apVar.d() == 3) {
                        String valueOf = String.valueOf(aVar);
                        new StringBuilder(String.valueOf(valueOf).length() + 47).append("Timeout waiting for ServiceConnection callback ").append(valueOf);
                        Exception exception = new Exception();
                        ComponentName g = apVar.g();
                        if (g == null) {
                            g = aVar.b();
                        }
                        apVar.onServiceDisconnected(g == null ? new ComponentName(aVar.a(), "unknown") : g);
                    }
                }
                return true;
            default:
                return false;
        }
    }
}
