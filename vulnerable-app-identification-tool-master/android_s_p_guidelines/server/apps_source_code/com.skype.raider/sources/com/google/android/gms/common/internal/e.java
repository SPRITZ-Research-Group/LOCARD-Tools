package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

public abstract class e<T extends IInterface> {
    public static final String[] d = new String[]{"service_esmobile", "service_googleme"};
    private static final Feature[] e = new Feature[0];
    private ConnectionResult A;
    private boolean B;
    private volatile ConnectionInfo C;
    final Handler a;
    @VisibleForTesting
    protected d b;
    @VisibleForTesting
    protected AtomicInteger c;
    private int f;
    private long g;
    private long h;
    private int i;
    private long j;
    @VisibleForTesting
    private o k;
    private final Context l;
    private final Looper m;
    private final m n;
    private final com.google.android.gms.common.e o;
    private final Object p;
    private final Object q;
    @GuardedBy("mServiceBrokerLock")
    private u r;
    @GuardedBy("mLock")
    private T s;
    private final ArrayList<c<?>> t;
    @GuardedBy("mLock")
    private f u;
    @GuardedBy("mLock")
    private int v;
    private final a w;
    private final b x;
    private final int y;
    private final String z;

    public interface d {
        void a(@NonNull ConnectionResult connectionResult);
    }

    public interface j {
        void a();
    }

    public interface a {
        void a();

        void b();
    }

    public interface b {
        void a(@NonNull ConnectionResult connectionResult);
    }

    protected abstract class c<TListener> {
        private TListener a;
        private boolean b = false;
        private final /* synthetic */ e c;

        public c(e eVar, TListener tListener) {
            this.c = eVar;
            this.a = tListener;
        }

        public final void a() {
            Object obj;
            synchronized (this) {
                obj = this.a;
                if (this.b) {
                    String valueOf = String.valueOf(this);
                    new StringBuilder(String.valueOf(valueOf).length() + 47).append("Callback proxy ").append(valueOf).append(" being reused. This is not safe.");
                }
            }
            if (obj != null) {
                try {
                    a(obj);
                } catch (RuntimeException e) {
                    throw e;
                }
            }
            synchronized (this) {
                this.b = true;
            }
            b();
        }

        protected abstract void a(TListener tListener);

        public final void b() {
            c();
            synchronized (this.c.t) {
                this.c.t.remove(this);
            }
        }

        public final void c() {
            synchronized (this) {
                this.a = null;
            }
        }
    }

    @VisibleForTesting
    public static final class e extends com.google.android.gms.common.internal.t.a {
        private e a;
        private final int b;

        public e(@NonNull e eVar, int i) {
            this.a = eVar;
            this.b = i;
        }

        @BinderThread
        public final void a(int i, @Nullable Bundle bundle) {
            Exception exception = new Exception();
        }

        @BinderThread
        public final void a(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
            ab.a(this.a, (Object) "onPostInitComplete can be called only once per call to getRemoteService");
            this.a.a(i, iBinder, bundle, this.b);
            this.a = null;
        }

        @BinderThread
        public final void a(int i, @NonNull IBinder iBinder, @NonNull ConnectionInfo connectionInfo) {
            ab.a(this.a, (Object) "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            ab.a((Object) connectionInfo);
            this.a.C = connectionInfo;
            a(i, iBinder, connectionInfo.a());
        }
    }

    @VisibleForTesting
    public final class f implements ServiceConnection {
        private final int a;
        private final /* synthetic */ e b;

        public f(e eVar, int i) {
            this.b = eVar;
            this.a = i;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null) {
                e.a(this.b);
                return;
            }
            synchronized (this.b.q) {
                this.b.r = com.google.android.gms.common.internal.u.a.a(iBinder);
            }
            this.b.a(0, this.a);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (this.b.q) {
                this.b.r = null;
            }
            this.b.a.sendMessage(this.b.a.obtainMessage(6, this.a, 1));
        }
    }

    protected class g implements d {
        private final /* synthetic */ e a;

        public g(e eVar) {
            this.a = eVar;
        }

        public final void a(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.b()) {
                this.a.a(null, this.a.r());
            } else if (this.a.x != null) {
                this.a.x.a(connectionResult);
            }
        }
    }

    private abstract class k extends c<Boolean> {
        private final /* synthetic */ e a;
        public final int b;
        public final Bundle c;

        @BinderThread
        protected k(e eVar, int i, Bundle bundle) {
            this.a = eVar;
            super(eVar, Boolean.valueOf(true));
            this.b = i;
            this.c = bundle;
        }

        protected abstract void a(ConnectionResult connectionResult);

        protected final /* synthetic */ void a(Object obj) {
            PendingIntent pendingIntent = null;
            if (((Boolean) obj) == null) {
                this.a.a(1, null);
                return;
            }
            switch (this.b) {
                case 0:
                    if (!d()) {
                        this.a.a(1, null);
                        a(new ConnectionResult(8, null));
                        return;
                    }
                    return;
                case 10:
                    this.a.a(1, null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    this.a.a(1, null);
                    if (this.c != null) {
                        pendingIntent = (PendingIntent) this.c.getParcelable("pendingIntent");
                    }
                    a(new ConnectionResult(this.b, pendingIntent));
                    return;
            }
        }

        protected abstract boolean d();
    }

    protected final class h extends k {
        public final IBinder a;
        private final /* synthetic */ e d;

        @BinderThread
        public h(e eVar, int i, IBinder iBinder, Bundle bundle) {
            this.d = eVar;
            super(eVar, i, bundle);
            this.a = iBinder;
        }

        protected final void a(ConnectionResult connectionResult) {
            if (this.d.x != null) {
                this.d.x.a(connectionResult);
            }
            this.d.a(connectionResult);
        }

        protected final boolean d() {
            try {
                String interfaceDescriptor = this.a.getInterfaceDescriptor();
                if (this.d.i().equals(interfaceDescriptor)) {
                    IInterface a = this.d.a(this.a);
                    if (a == null) {
                        return false;
                    }
                    if (!this.d.a(2, 4, a) && !this.d.a(3, 4, a)) {
                        return false;
                    }
                    this.d.A = null;
                    e.p();
                    if (this.d.w != null) {
                        this.d.w.a();
                    }
                    return true;
                }
                String i = this.d.i();
                new StringBuilder((String.valueOf(i).length() + 34) + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(i).append(" vs. ").append(interfaceDescriptor);
                return false;
            } catch (RemoteException e) {
                return false;
            }
        }
    }

    protected final class i extends k {
        private final /* synthetic */ e a;

        @BinderThread
        public i(e eVar, int i) {
            this.a = eVar;
            super(eVar, i, null);
        }

        protected final void a(ConnectionResult connectionResult) {
            this.a.b.a(connectionResult);
            this.a.a(connectionResult);
        }

        protected final boolean d() {
            this.a.b.a(ConnectionResult.a);
            return true;
        }
    }

    final class l extends Handler {
        private final /* synthetic */ e a;

        public l(e eVar, Looper looper) {
            this.a = eVar;
            super(looper);
        }

        private static void a(Message message) {
            ((c) message.obj).b();
        }

        private static boolean b(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 7;
        }

        public final void handleMessage(Message message) {
            ConnectionResult e;
            if (this.a.c.get() != message.arg1) {
                if (b(message)) {
                    a(message);
                }
            } else if ((message.what == 1 || message.what == 7 || message.what == 4 || message.what == 5) && !this.a.c()) {
                a(message);
            } else if (message.what == 4) {
                this.a.A = new ConnectionResult(message.arg2);
                if (!this.a.u() || this.a.B) {
                    e = this.a.A != null ? this.a.A : new ConnectionResult(8);
                    this.a.b.a(e);
                    this.a.a(e);
                    return;
                }
                this.a.a(3, null);
            } else if (message.what == 5) {
                e = this.a.A != null ? this.a.A : new ConnectionResult(8);
                this.a.b.a(e);
                this.a.a(e);
            } else if (message.what == 3) {
                ConnectionResult connectionResult = new ConnectionResult(message.arg2, message.obj instanceof PendingIntent ? (PendingIntent) message.obj : null);
                this.a.b.a(connectionResult);
                this.a.a(connectionResult);
            } else if (message.what == 6) {
                this.a.a(5, null);
                if (this.a.w != null) {
                    a f = this.a.w;
                    int i = message.arg2;
                    f.b();
                }
                this.a.a(message.arg2);
                this.a.a(5, 1, null);
            } else if (message.what == 2 && !this.a.b()) {
                a(message);
            } else if (b(message)) {
                ((c) message.obj).a();
            } else {
                new StringBuilder(45).append("Don't know how to handle message: ").append(message.what);
                Exception exception = new Exception();
            }
        }
    }

    protected e(Context context, Looper looper, a aVar, b bVar) {
        this(context, looper, m.a(context), com.google.android.gms.common.e.b(), 93, (a) ab.a((Object) aVar), (b) ab.a((Object) bVar), null);
    }

    @VisibleForTesting
    protected e(Context context, Looper looper, m mVar, com.google.android.gms.common.e eVar, int i, a aVar, b bVar, String str) {
        this.p = new Object();
        this.q = new Object();
        this.t = new ArrayList();
        this.v = 1;
        this.A = null;
        this.B = false;
        this.C = null;
        this.c = new AtomicInteger(0);
        this.l = (Context) ab.a((Object) context, (Object) "Context must not be null");
        this.m = (Looper) ab.a((Object) looper, (Object) "Looper must not be null");
        this.n = (m) ab.a((Object) mVar, (Object) "Supervisor must not be null");
        this.o = (com.google.android.gms.common.e) ab.a((Object) eVar, (Object) "API availability must not be null");
        this.a = new l(this, looper);
        this.y = i;
        this.w = aVar;
        this.x = bVar;
        this.z = str;
    }

    private final void a(int i, T t) {
        boolean z = true;
        if ((i == 4) != (t != null)) {
            z = false;
        }
        ab.b(z);
        synchronized (this.p) {
            this.v = i;
            this.s = t;
            ServiceConnection serviceConnection;
            switch (i) {
                case 1:
                    if (this.u != null) {
                        serviceConnection = this.u;
                        s();
                        this.n.b(h(), "com.google.android.gms", 129, serviceConnection);
                        this.u = null;
                        break;
                    }
                    break;
                case 2:
                case 3:
                    String a;
                    String b;
                    m mVar;
                    String b2;
                    int c;
                    if (!(this.u == null || this.k == null)) {
                        a = this.k.a();
                        b = this.k.b();
                        new StringBuilder((String.valueOf(a).length() + 70) + String.valueOf(b).length()).append("Calling connect() while still connected, missing disconnect() for ").append(a).append(" on ").append(b);
                        mVar = this.n;
                        b = this.k.a();
                        b2 = this.k.b();
                        c = this.k.c();
                        serviceConnection = this.u;
                        s();
                        mVar.b(b, b2, c, serviceConnection);
                        this.c.incrementAndGet();
                    }
                    this.u = new f(this, this.c.get());
                    this.k = new o("com.google.android.gms", h());
                    mVar = this.n;
                    b = this.k.a();
                    b2 = this.k.b();
                    c = this.k.c();
                    serviceConnection = this.u;
                    s();
                    if (!mVar.a(b, b2, c, serviceConnection)) {
                        a = this.k.a();
                        b = this.k.b();
                        new StringBuilder((String.valueOf(a).length() + 34) + String.valueOf(b).length()).append("unable to connect to service: ").append(a).append(" on ").append(b);
                        a(16, this.c.get());
                        break;
                    }
                    break;
                case 4:
                    this.h = System.currentTimeMillis();
                    break;
            }
        }
    }

    private final boolean a(int i, int i2, T t) {
        boolean z;
        synchronized (this.p) {
            if (this.v != i) {
                z = false;
            } else {
                a(i2, (IInterface) t);
                z = true;
            }
        }
        return z;
    }

    public static Bundle p() {
        return null;
    }

    @Nullable
    private String s() {
        return this.z == null ? this.l.getClass().getName() : this.z;
    }

    private final boolean t() {
        boolean z;
        synchronized (this.p) {
            z = this.v == 3;
        }
        return z;
    }

    private final boolean u() {
        if (this.B || TextUtils.isEmpty(i()) || TextUtils.isEmpty(null)) {
            return false;
        }
        try {
            Class.forName(i());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Nullable
    protected abstract T a(IBinder iBinder);

    public void a() {
        this.c.incrementAndGet();
        synchronized (this.t) {
            int size = this.t.size();
            for (int i = 0; i < size; i++) {
                ((c) this.t.get(i)).c();
            }
            this.t.clear();
        }
        synchronized (this.q) {
            this.r = null;
        }
        a(1, null);
    }

    @CallSuper
    protected final void a(int i) {
        this.f = i;
        this.g = System.currentTimeMillis();
    }

    protected final void a(int i, int i2) {
        this.a.sendMessage(this.a.obtainMessage(7, i2, -1, new i(this, i)));
    }

    protected final void a(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.a.sendMessage(this.a.obtainMessage(1, i2, -1, new h(this, i, iBinder, bundle)));
    }

    @CallSuper
    protected final void a(ConnectionResult connectionResult) {
        this.i = connectionResult.c();
        this.j = System.currentTimeMillis();
    }

    public final void a(@NonNull d dVar) {
        this.b = (d) ab.a((Object) dVar, (Object) "Connection progress callbacks cannot be null.");
        a(2, null);
    }

    public final void a(@NonNull j jVar) {
        jVar.a();
    }

    @WorkerThread
    public final void a(q qVar, Set<Scope> set) {
        GetServiceRequest a = new GetServiceRequest(this.y).a(this.l.getPackageName()).a(n());
        if (set != null) {
            a.a((Collection) set);
        }
        if (d()) {
            a.a(l() != null ? l() : new Account("<<default account>>", "com.google")).a(qVar);
        }
        a.a(m());
        a.b(e);
        try {
            synchronized (this.q) {
                if (this.r != null) {
                    this.r.a(new e(this, this.c.get()), a);
                }
            }
        } catch (DeadObjectException e) {
            this.a.sendMessage(this.a.obtainMessage(6, this.c.get(), 1));
        } catch (SecurityException e2) {
            throw e2;
        } catch (RemoteException e3) {
            a(8, null, null, this.c.get());
        } catch (RuntimeException e4) {
            a(8, null, null, this.c.get());
        }
    }

    public final boolean b() {
        boolean z;
        synchronized (this.p) {
            z = this.v == 4;
        }
        return z;
    }

    public final boolean c() {
        boolean z;
        synchronized (this.p) {
            z = this.v == 2 || this.v == 3;
        }
        return z;
    }

    public boolean d() {
        return false;
    }

    public final String e() {
        if (b() && this.k != null) {
            return this.k.b();
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    public int f() {
        return com.google.android.gms.common.e.b;
    }

    @Nullable
    public final Feature[] g() {
        ConnectionInfo connectionInfo = this.C;
        return connectionInfo == null ? null : connectionInfo.b();
    }

    @NonNull
    protected abstract String h();

    @NonNull
    protected abstract String i();

    public final void j() {
        int b = this.o.b(this.l, f());
        if (b != 0) {
            a(1, null);
            this.b = (d) ab.a(new g(this), (Object) "Connection progress callbacks cannot be null.");
            this.a.sendMessage(this.a.obtainMessage(3, this.c.get(), b, null));
            return;
        }
        a(new g(this));
    }

    public final Context k() {
        return this.l;
    }

    public Account l() {
        return null;
    }

    public Feature[] m() {
        return e;
    }

    protected Bundle n() {
        return new Bundle();
    }

    protected final void o() {
        if (!b()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T q() throws DeadObjectException {
        T t;
        synchronized (this.p) {
            if (this.v == 5) {
                throw new DeadObjectException();
            }
            o();
            ab.a(this.s != null, (Object) "Client is connected but service is null");
            t = this.s;
        }
        return t;
    }

    protected Set<Scope> r() {
        return Collections.EMPTY_SET;
    }

    static /* synthetic */ void a(e eVar) {
        int i;
        if (eVar.t()) {
            i = 5;
            eVar.B = true;
        } else {
            i = 4;
        }
        eVar.a.sendMessage(eVar.a.obtainMessage(i, eVar.c.get(), 16));
    }
}
