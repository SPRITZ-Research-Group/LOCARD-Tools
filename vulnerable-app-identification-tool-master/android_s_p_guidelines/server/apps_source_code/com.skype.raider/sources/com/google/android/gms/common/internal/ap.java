package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.stats.b;
import java.util.HashSet;
import java.util.Set;

final class ap implements ServiceConnection {
    private final Set<ServiceConnection> a = new HashSet();
    private int b = 2;
    private boolean c;
    private IBinder d;
    private final a e;
    private ComponentName f;
    private final /* synthetic */ ao g;

    public ap(ao aoVar, a aVar) {
        this.g = aoVar;
        this.e = aVar;
    }

    public final void a() {
        this.b = 3;
        this.g.d;
        Context c = this.g.b;
        a aVar = this.e;
        this.g.b;
        this.c = b.a(c, aVar.d(), this, this.e.c());
        if (this.c) {
            this.g.c.sendMessageDelayed(this.g.c.obtainMessage(1, this.e), this.g.f);
            return;
        }
        this.b = 2;
        try {
            this.g.d;
            b.a(this.g.b, this);
        } catch (IllegalArgumentException e) {
        }
    }

    public final void a(ServiceConnection serviceConnection) {
        this.g.d;
        this.g.b;
        a aVar = this.e;
        this.g.b;
        aVar.d();
        b.b();
        this.a.add(serviceConnection);
    }

    public final void b() {
        this.g.c.removeMessages(1, this.e);
        this.g.d;
        b.a(this.g.b, this);
        this.c = false;
        this.b = 2;
    }

    public final void b(ServiceConnection serviceConnection) {
        this.g.d;
        this.g.b;
        b.c();
        this.a.remove(serviceConnection);
    }

    public final boolean c() {
        return this.c;
    }

    public final boolean c(ServiceConnection serviceConnection) {
        return this.a.contains(serviceConnection);
    }

    public final int d() {
        return this.b;
    }

    public final boolean e() {
        return this.a.isEmpty();
    }

    public final IBinder f() {
        return this.d;
    }

    public final ComponentName g() {
        return this.f;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.g.a) {
            this.g.c.removeMessages(1, this.e);
            this.d = iBinder;
            this.f = componentName;
            for (ServiceConnection onServiceConnected : this.a) {
                onServiceConnected.onServiceConnected(componentName, iBinder);
            }
            this.b = 1;
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.g.a) {
            this.g.c.removeMessages(1, this.e);
            this.d = null;
            this.f = componentName;
            for (ServiceConnection onServiceDisconnected : this.a) {
                onServiceDisconnected.onServiceDisconnected(componentName);
            }
            this.b = 2;
        }
    }
}
