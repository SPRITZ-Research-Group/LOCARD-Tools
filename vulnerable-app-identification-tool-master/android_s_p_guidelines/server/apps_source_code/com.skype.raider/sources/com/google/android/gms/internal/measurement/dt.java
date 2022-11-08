package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.e.a;
import com.google.android.gms.common.internal.e.b;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class dt implements ServiceConnection, a, b {
    final /* synthetic */ di a;
    private volatile boolean b;
    private volatile au c;

    protected dt(di diVar) {
        this.a = diVar;
    }

    @MainThread
    public final void a() {
        ab.b("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                an anVar = (an) this.c.q();
                this.c = null;
                this.a.p().a(new dw(this, anVar));
            } catch (DeadObjectException e) {
                this.c = null;
                this.b = false;
            } catch (IllegalStateException e2) {
                this.c = null;
                this.b = false;
            }
        }
    }

    @WorkerThread
    public final void a(Intent intent) {
        this.a.c();
        Context k = this.a.k();
        com.google.android.gms.common.stats.b.a();
        synchronized (this) {
            if (this.b) {
                this.a.q().C().a("Connection attempt already in progress");
                return;
            }
            this.a.q().C().a("Using local app measurement service");
            this.b = true;
            com.google.android.gms.common.stats.b.b(k, intent, this.a.a, 129);
        }
    }

    @MainThread
    public final void a(@NonNull ConnectionResult connectionResult) {
        ab.b("MeasurementServiceConnection.onConnectionFailed");
        av e = this.a.q.e();
        if (e != null) {
            e.y().a("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.b = false;
            this.c = null;
        }
        this.a.p().a(new dy(this));
    }

    @MainThread
    public final void b() {
        ab.b("MeasurementServiceConnection.onConnectionSuspended");
        this.a.q().B().a("Service connection suspended");
        this.a.p().a(new dx(this));
    }

    @WorkerThread
    public final void c() {
        this.a.c();
        Context k = this.a.k();
        synchronized (this) {
            if (this.b) {
                this.a.q().C().a("Connection attempt already in progress");
            } else if (this.c != null) {
                this.a.q().C().a("Already awaiting connection attempt");
            } else {
                this.c = new au(k, Looper.getMainLooper(), this, this);
                this.a.q().C().a("Connecting to remote service");
                this.b = true;
                this.c.j();
            }
        }
    }

    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        an anVar;
        ab.b("MeasurementServiceConnection.onServiceConnected");
        synchronized (this) {
            if (iBinder == null) {
                this.b = false;
                this.a.q().v().a("Service connected with null binder");
                return;
            }
            try {
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                    if (iBinder == null) {
                        anVar = null;
                    } else {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                        anVar = queryLocalInterface instanceof an ? (an) queryLocalInterface : new ap(iBinder);
                    }
                    try {
                        this.a.q().C().a("Bound to IMeasurementService interface");
                    } catch (RemoteException e) {
                        this.a.q().v().a("Service connect failed to get IMeasurementService");
                        if (anVar == null) {
                            this.b = false;
                            try {
                                com.google.android.gms.common.stats.b.a();
                                com.google.android.gms.common.stats.b.a(this.a.k(), this.a.a);
                            } catch (IllegalArgumentException e2) {
                            }
                        } else {
                            this.a.p().a(new du(this, anVar));
                        }
                    }
                    if (anVar == null) {
                        this.b = false;
                        com.google.android.gms.common.stats.b.a();
                        com.google.android.gms.common.stats.b.a(this.a.k(), this.a.a);
                    } else {
                        this.a.p().a(new du(this, anVar));
                    }
                }
                this.a.q().v().a("Got binder with a wrong descriptor", interfaceDescriptor);
                anVar = null;
                if (anVar == null) {
                    this.a.p().a(new du(this, anVar));
                } else {
                    this.b = false;
                    com.google.android.gms.common.stats.b.a();
                    com.google.android.gms.common.stats.b.a(this.a.k(), this.a.a);
                }
            } catch (RemoteException e3) {
                anVar = null;
            }
        }
    }

    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        ab.b("MeasurementServiceConnection.onServiceDisconnected");
        this.a.q().B().a("Service disconnected");
        this.a.p().a(new dv(this, componentName));
    }
}
