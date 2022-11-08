package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.stats.b;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

final class aj implements ServiceConnection {
    @GuardedBy("this")
    int a;
    final Messenger b;
    ao c;
    @GuardedBy("this")
    final Queue<c<?>> d;
    @GuardedBy("this")
    final SparseArray<c<?>> e;
    final /* synthetic */ ai f;

    private aj(ai aiVar) {
        this.f = aiVar;
        this.a = 0;
        this.b = new Messenger(new Handler(Looper.getMainLooper(), new ak(this)));
        this.d = new ArrayDeque();
        this.e = new SparseArray();
    }

    /* synthetic */ aj(ai aiVar, byte b) {
        this(aiVar);
    }

    private final void c() {
        this.f.c.execute(new am(this));
    }

    final synchronized void a() {
        if (this.a == 2 && this.d.isEmpty() && this.e.size() == 0) {
            this.a = 3;
            b.a();
            b.a(this.f.b, this);
        }
    }

    final synchronized void a(int i) {
        c cVar = (c) this.e.get(i);
        if (cVar != null) {
            new StringBuilder(31).append("Timing out request: ").append(i);
            this.e.remove(i);
            cVar.a(new d(3, "Timed out waiting for response"));
            a();
        }
    }

    final synchronized void a(int i, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String str2 = "Disconnected: ";
            String valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
                str2.concat(valueOf);
            } else {
                valueOf = new String(str2);
            }
        }
        switch (this.a) {
            case 0:
                throw new IllegalStateException();
            case 1:
            case 2:
                this.a = 4;
                b.a();
                b.a(this.f.b, this);
                d dVar = new d(i, str);
                for (c a : this.d) {
                    a.a(dVar);
                }
                this.d.clear();
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    if (i3 >= this.e.size()) {
                        this.e.clear();
                        break;
                    } else {
                        ((c) this.e.valueAt(i3)).a(dVar);
                        i2 = i3 + 1;
                    }
                }
            case 3:
                this.a = 4;
                break;
            case 4:
                break;
            default:
                throw new IllegalStateException("Unknown state: " + this.a);
        }
    }

    final boolean a(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            new StringBuilder(41).append("Received response to request: ").append(i);
        }
        synchronized (this) {
            c cVar = (c) this.e.get(i);
            if (cVar == null) {
                new StringBuilder(50).append("Received response for unknown request: ").append(i);
            } else {
                this.e.remove(i);
                a();
                Bundle data = message.getData();
                if (data.getBoolean("unsupported", false)) {
                    cVar.a(new d(4, "Not supported by GmsCore"));
                } else {
                    cVar.a(data);
                }
            }
        }
        return true;
    }

    final synchronized boolean a(c cVar) {
        boolean z = false;
        boolean z2 = true;
        synchronized (this) {
            switch (this.a) {
                case 0:
                    this.d.add(cVar);
                    if (this.a == 0) {
                        z = true;
                    }
                    ab.a(z);
                    this.a = 1;
                    Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                    intent.setPackage("com.google.android.gms");
                    b.a();
                    if (!b.b(this.f.b, intent, this, 1)) {
                        a(0, "Unable to bind to service");
                        break;
                    }
                    this.f.c.schedule(new al(this), 30, TimeUnit.SECONDS);
                    break;
                case 1:
                    this.d.add(cVar);
                    break;
                case 2:
                    this.d.add(cVar);
                    c();
                    break;
                case 3:
                case 4:
                    z2 = false;
                    break;
                default:
                    throw new IllegalStateException("Unknown state: " + this.a);
            }
        }
        return z2;
    }

    final synchronized void b() {
        if (this.a == 1) {
            a(1, "Timed out while binding");
        }
    }

    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            a(0, "Null service connection");
        } else {
            try {
                this.c = new ao(iBinder);
                this.a = 2;
                c();
            } catch (RemoteException e) {
                a(0, e.getMessage());
            }
        }
        return;
    }

    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        a(2, "Service disconnected");
    }
}
