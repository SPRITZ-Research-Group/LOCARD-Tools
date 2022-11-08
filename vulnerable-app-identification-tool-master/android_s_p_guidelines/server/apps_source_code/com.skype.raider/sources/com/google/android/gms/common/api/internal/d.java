package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.content.Context;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.c.h;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.f;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.n;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.ag;
import com.google.android.gms.common.internal.p;
import com.google.android.gms.common.internal.q;
import com.google.android.gms.common.internal.z;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public final class d implements Callback {
    public static final Status a = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status b = new Status(4, "The user must be signed in to make this API call.");
    private static final Object f = new Object();
    @GuardedBy("lock")
    private static d g;
    private long c = 5000;
    private long d = 120000;
    private long e = 10000;
    private final Context h;
    private final GoogleApiAvailability i;
    private final p j;
    private final AtomicInteger k = new AtomicInteger(1);
    private final AtomicInteger l = new AtomicInteger(0);
    private final Map<ap<?>, a<?>> m = new ConcurrentHashMap(5, 0.75f, 1);
    @GuardedBy("lock")
    private o n = null;
    @GuardedBy("lock")
    private final Set<ap<?>> o = new android.support.v4.util.b();
    private final Set<ap<?>> p = new android.support.v4.util.b();
    private final Handler q;

    public class a<O extends com.google.android.gms.common.api.a.d> implements com.google.android.gms.common.api.e.a, com.google.android.gms.common.api.e.b, aw {
        final /* synthetic */ d a;
        private final Queue<r> b = new LinkedList();
        private final f c;
        private final com.google.android.gms.common.api.a.b d;
        private final ap<O> e;
        private final l f;
        private final Set<aq> g = new HashSet();
        private final Map<com.google.android.gms.common.api.internal.h.a<?>, aa> h = new HashMap();
        private final int i;
        private final ad j;
        private boolean k;
        private final List<b> l = new ArrayList();
        private ConnectionResult m = null;

        @WorkerThread
        public a(d dVar, com.google.android.gms.common.api.d<O> dVar2) {
            this.a = dVar;
            this.c = dVar2.a(dVar.q.getLooper(), this);
            if (this.c instanceof ag) {
                this.d = ((ag) this.c).s();
            } else {
                this.d = this.c;
            }
            this.e = dVar2.a();
            this.f = new l();
            this.i = dVar2.b();
            if (this.c.d()) {
                this.j = dVar2.a(dVar.h, dVar.q);
            } else {
                this.j = null;
            }
        }

        @WorkerThread
        private final boolean a(boolean z) {
            ab.a(this.a.q);
            if (!this.c.b() || this.h.size() != 0) {
                return false;
            }
            if (!this.f.a()) {
                this.c.a();
                return true;
            } else if (!z) {
                return false;
            } else {
                s();
                return false;
            }
        }

        static /* synthetic */ void b(a aVar, b bVar) {
            if (aVar.l.remove(bVar)) {
                Object obj;
                aVar.a.q.removeMessages(15, bVar);
                aVar.a.q.removeMessages(16, bVar);
                Object b = bVar.b;
                ArrayList arrayList = new ArrayList(aVar.b.size());
                for (r rVar : aVar.b) {
                    if (rVar instanceof an) {
                        Object[] a = ((an) rVar).a();
                        if (a != null) {
                            if (com.google.android.gms.common.util.b.a(a, b) >= 0) {
                                obj = 1;
                            } else {
                                obj = null;
                            }
                            if (obj != null) {
                                arrayList.add(rVar);
                            }
                        }
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    obj = arrayList2.get(i);
                    i++;
                    r rVar2 = (r) obj;
                    aVar.b.remove(rVar2);
                    rVar2.a(new n(b));
                }
            }
        }

        @WorkerThread
        private final boolean b(r rVar) {
            if (rVar instanceof an) {
                an anVar = (an) rVar;
                Feature[] a = anVar.a();
                if (a == null || a.length == 0) {
                    c(rVar);
                    return true;
                }
                Feature[] g = this.c.g();
                if (g == null) {
                    g = new Feature[0];
                }
                Map aVar = new android.support.v4.util.a(g.length);
                for (Feature feature : g) {
                    aVar.put(feature.a(), Long.valueOf(feature.b()));
                }
                for (Feature feature2 : a) {
                    if (!aVar.containsKey(feature2.a()) || ((Long) aVar.get(feature2.a())).longValue() < feature2.b()) {
                        if (anVar.b()) {
                            b bVar = new b(this.e, feature2, (byte) 0);
                            int indexOf = this.l.indexOf(bVar);
                            if (indexOf >= 0) {
                                bVar = (b) this.l.get(indexOf);
                                this.a.q.removeMessages(15, bVar);
                                this.a.q.sendMessageDelayed(Message.obtain(this.a.q, 15, bVar), this.a.c);
                            } else {
                                this.l.add(bVar);
                                this.a.q.sendMessageDelayed(Message.obtain(this.a.q, 15, bVar), this.a.c);
                                this.a.q.sendMessageDelayed(Message.obtain(this.a.q, 16, bVar), this.a.d);
                                ConnectionResult connectionResult = new ConnectionResult(2, null);
                                if (!c(connectionResult)) {
                                    this.a.a(connectionResult, this.i);
                                }
                            }
                        } else {
                            anVar.a(new n(feature2));
                        }
                        return false;
                    }
                    this.l.remove(new b(this.e, feature2, (byte) 0));
                }
                c(rVar);
                return true;
            }
            c(rVar);
            return true;
        }

        @WorkerThread
        private final void c(r rVar) {
            rVar.a(this.f, m());
            try {
                rVar.a(this);
            } catch (DeadObjectException e) {
                b();
                this.c.a();
            }
        }

        @WorkerThread
        private final boolean c(@NonNull ConnectionResult connectionResult) {
            boolean z;
            synchronized (d.f) {
                if (this.a.n == null || !this.a.o.contains(this.e)) {
                    z = false;
                } else {
                    this.a.n.b(connectionResult, this.i);
                    z = true;
                }
            }
            return z;
        }

        @WorkerThread
        private final void d(ConnectionResult connectionResult) {
            for (aq aqVar : this.g) {
                String str = null;
                if (z.a(connectionResult, ConnectionResult.a)) {
                    str = this.c.e();
                }
                aqVar.a(this.e, connectionResult, str);
            }
            this.g.clear();
        }

        @WorkerThread
        private final void o() {
            f();
            d(ConnectionResult.a);
            r();
            Iterator it = this.h.values().iterator();
            while (it.hasNext()) {
                it.next();
                try {
                    h hVar = new h();
                } catch (DeadObjectException e) {
                    b();
                    this.c.a();
                } catch (RemoteException e2) {
                }
            }
            q();
            s();
        }

        @WorkerThread
        private final void p() {
            f();
            this.k = true;
            this.f.c();
            this.a.q.sendMessageDelayed(Message.obtain(this.a.q, 9, this.e), this.a.c);
            this.a.q.sendMessageDelayed(Message.obtain(this.a.q, 11, this.e), this.a.d);
            this.a.j.a();
        }

        @WorkerThread
        private final void q() {
            ArrayList arrayList = new ArrayList(this.b);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                r rVar = (r) obj;
                if (!this.c.b()) {
                    return;
                }
                if (b(rVar)) {
                    this.b.remove(rVar);
                }
            }
        }

        @WorkerThread
        private final void r() {
            if (this.k) {
                this.a.q.removeMessages(11, this.e);
                this.a.q.removeMessages(9, this.e);
                this.k = false;
            }
        }

        private final void s() {
            this.a.q.removeMessages(12, this.e);
            this.a.q.sendMessageDelayed(this.a.q.obtainMessage(12, this.e), this.a.e);
        }

        public final void a() {
            if (Looper.myLooper() == this.a.q.getLooper()) {
                o();
            } else {
                this.a.q.post(new t(this));
            }
        }

        @WorkerThread
        public final void a(@NonNull ConnectionResult connectionResult) {
            ab.a(this.a.q);
            if (this.j != null) {
                this.j.c();
            }
            f();
            this.a.j.a();
            d(connectionResult);
            if (connectionResult.c() == 4) {
                a(d.b);
            } else if (this.b.isEmpty()) {
                this.m = connectionResult;
            } else if (!c(connectionResult) && !this.a.a(connectionResult, this.i)) {
                if (connectionResult.c() == 18) {
                    this.k = true;
                }
                if (this.k) {
                    this.a.q.sendMessageDelayed(Message.obtain(this.a.q, 9, this.e), this.a.c);
                    return;
                }
                String a = this.e.a();
                a(new Status(17, new StringBuilder(String.valueOf(a).length() + 38).append("API: ").append(a).append(" is not available on this device.").toString()));
            }
        }

        @WorkerThread
        public final void a(Status status) {
            ab.a(this.a.q);
            for (r a : this.b) {
                a.a(status);
            }
            this.b.clear();
        }

        @WorkerThread
        public final void a(aq aqVar) {
            ab.a(this.a.q);
            this.g.add(aqVar);
        }

        @WorkerThread
        public final void a(r rVar) {
            ab.a(this.a.q);
            if (!this.c.b()) {
                this.b.add(rVar);
                if (this.m == null || !this.m.a()) {
                    k();
                } else {
                    a(this.m);
                }
            } else if (b(rVar)) {
                s();
            } else {
                this.b.add(rVar);
            }
        }

        public final void b() {
            if (Looper.myLooper() == this.a.q.getLooper()) {
                p();
            } else {
                this.a.q.post(new u(this));
            }
        }

        @WorkerThread
        public final void b(@NonNull ConnectionResult connectionResult) {
            ab.a(this.a.q);
            this.c.a();
            a(connectionResult);
        }

        @WorkerThread
        public final void c() {
            ab.a(this.a.q);
            a(d.a);
            this.f.b();
            for (com.google.android.gms.common.api.internal.h.a aoVar : (com.google.android.gms.common.api.internal.h.a[]) this.h.keySet().toArray(new com.google.android.gms.common.api.internal.h.a[this.h.size()])) {
                a(new ao(aoVar, new h()));
            }
            d(new ConnectionResult(4));
            if (this.c.b()) {
                this.c.a(new v(this));
            }
        }

        public final f d() {
            return this.c;
        }

        public final Map<com.google.android.gms.common.api.internal.h.a<?>, aa> e() {
            return this.h;
        }

        @WorkerThread
        public final void f() {
            ab.a(this.a.q);
            this.m = null;
        }

        @WorkerThread
        public final ConnectionResult g() {
            ab.a(this.a.q);
            return this.m;
        }

        @WorkerThread
        public final void h() {
            ab.a(this.a.q);
            if (this.k) {
                k();
            }
        }

        @WorkerThread
        public final void i() {
            ab.a(this.a.q);
            if (this.k) {
                r();
                a(this.a.i.a(this.a.h) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
                this.c.a();
            }
        }

        @WorkerThread
        public final boolean j() {
            return a(true);
        }

        @WorkerThread
        public final void k() {
            ab.a(this.a.q);
            if (!this.c.b() && !this.c.c()) {
                int a = this.a.j.a(this.a.h, this.c);
                if (a != 0) {
                    a(new ConnectionResult(a, null));
                    return;
                }
                com.google.android.gms.common.internal.e.d cVar = new c(this.a, this.c, this.e);
                if (this.c.d()) {
                    this.j.a((ah) cVar);
                }
                this.c.a(cVar);
            }
        }

        final boolean l() {
            return this.c.b();
        }

        public final boolean m() {
            return this.c.d();
        }

        public final int n() {
            return this.i;
        }

        static /* synthetic */ void a(a aVar, b bVar) {
            if (aVar.l.contains(bVar) && !aVar.k) {
                if (aVar.c.b()) {
                    aVar.q();
                } else {
                    aVar.k();
                }
            }
        }
    }

    private static class b {
        private final ap<?> a;
        private final Feature b;

        private b(ap<?> apVar, Feature feature) {
            this.a = apVar;
            this.b = feature;
        }

        /* synthetic */ b(ap apVar, Feature feature, byte b) {
            this(apVar, feature);
        }

        public final boolean equals(Object obj) {
            if (obj == null || !(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return z.a(this.a, bVar.a) && z.a(this.b, bVar.b);
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.a, this.b});
        }

        public final String toString() {
            return z.a(this).a(PropertiesEntry.COLUMN_NAME_KEY, this.a).a("feature", this.b).toString();
        }
    }

    private class c implements ah, com.google.android.gms.common.internal.e.d {
        final /* synthetic */ d a;
        private final f b;
        private final ap<?> c;
        private q d = null;
        private Set<Scope> e = null;
        private boolean f = false;

        public c(d dVar, f fVar, ap<?> apVar) {
            this.a = dVar;
            this.b = fVar;
            this.c = apVar;
        }

        @WorkerThread
        private final void a() {
            if (this.f && this.d != null) {
                this.b.a(this.d, this.e);
            }
        }

        public final void a(@NonNull ConnectionResult connectionResult) {
            this.a.q.post(new x(this, connectionResult));
        }

        @WorkerThread
        public final void a(q qVar, Set<Scope> set) {
            if (qVar == null || set == null) {
                Exception exception = new Exception();
                b(new ConnectionResult(4));
                return;
            }
            this.d = qVar;
            this.e = set;
            a();
        }

        @WorkerThread
        public final void b(ConnectionResult connectionResult) {
            ((a) this.a.m.get(this.c)).b(connectionResult);
        }
    }

    @KeepForSdk
    private d(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.h = context;
        this.q = new Handler(looper, this);
        this.i = googleApiAvailability;
        this.j = new p(googleApiAvailability);
        this.q.sendMessage(this.q.obtainMessage(6));
    }

    public static d a(Context context) {
        d dVar;
        synchronized (f) {
            if (g == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                g = new d(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.a());
            }
            dVar = g;
        }
        return dVar;
    }

    @WorkerThread
    private final void b(com.google.android.gms.common.api.d<?> dVar) {
        ap a = dVar.a();
        a aVar = (a) this.m.get(a);
        if (aVar == null) {
            aVar = new a(this, dVar);
            this.m.put(a, aVar);
        }
        if (aVar.m()) {
            this.p.add(a);
        }
        aVar.k();
    }

    public final int a() {
        return this.k.getAndIncrement();
    }

    public final void a(com.google.android.gms.common.api.d<?> dVar) {
        this.q.sendMessage(this.q.obtainMessage(7, dVar));
    }

    public final <O extends com.google.android.gms.common.api.a.d> void a(com.google.android.gms.common.api.d<O> dVar, com.google.android.gms.common.api.internal.c.a<? extends i, com.google.android.gms.common.api.a.b> aVar) {
        this.q.sendMessage(this.q.obtainMessage(4, new z(new am(aVar), this.l.get(), dVar)));
    }

    final boolean a(ConnectionResult connectionResult, int i) {
        return this.i.a(this.h, connectionResult, i);
    }

    public final void b() {
        this.q.sendMessage(this.q.obtainMessage(3));
    }

    public final void b(ConnectionResult connectionResult, int i) {
        if (!a(connectionResult, i)) {
            this.q.sendMessage(this.q.obtainMessage(5, i, 0, connectionResult));
        }
    }

    @WorkerThread
    public final boolean handleMessage(Message message) {
        a aVar;
        b bVar;
        switch (message.what) {
            case 1:
                this.e = ((Boolean) message.obj).booleanValue() ? 10000 : 300000;
                this.q.removeMessages(12);
                for (ap obtainMessage : this.m.keySet()) {
                    this.q.sendMessageDelayed(this.q.obtainMessage(12, obtainMessage), this.e);
                }
                break;
            case 2:
                aq aqVar = (aq) message.obj;
                for (ap apVar : aqVar.a()) {
                    a aVar2 = (a) this.m.get(apVar);
                    if (aVar2 == null) {
                        aqVar.a(apVar, new ConnectionResult(13), null);
                        break;
                    } else if (aVar2.l()) {
                        aqVar.a(apVar, ConnectionResult.a, aVar2.d().e());
                    } else if (aVar2.g() != null) {
                        aqVar.a(apVar, aVar2.g(), null);
                    } else {
                        aVar2.a(aqVar);
                    }
                }
                break;
            case 3:
                for (a aVar3 : this.m.values()) {
                    aVar3.f();
                    aVar3.k();
                }
                break;
            case 4:
            case 8:
            case 13:
                z zVar = (z) message.obj;
                aVar = (a) this.m.get(zVar.c.a());
                if (aVar == null) {
                    b(zVar.c);
                    aVar = (a) this.m.get(zVar.c.a());
                }
                if (aVar.m() && this.l.get() != zVar.b) {
                    zVar.a.a(a);
                    aVar.c();
                    break;
                }
                aVar.a(zVar.a);
                break;
                break;
            case 5:
                Exception exception;
                String b;
                String e;
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                for (a aVar4 : this.m.values()) {
                    if (aVar4.n() == i) {
                        if (aVar4 != null) {
                            new StringBuilder(76).append("Could not find API instance ").append(i).append(" while trying to fail enqueued calls.");
                            exception = new Exception();
                            break;
                        }
                        b = this.i.b(connectionResult.c());
                        e = connectionResult.e();
                        aVar4.a(new Status(17, new StringBuilder((String.valueOf(b).length() + 69) + String.valueOf(e).length()).append("Error resolution was canceled by the user, original error message: ").append(b).append(": ").append(e).toString()));
                        break;
                    }
                }
                aVar4 = null;
                if (aVar4 != null) {
                    new StringBuilder(76).append("Could not find API instance ").append(i).append(" while trying to fail enqueued calls.");
                    exception = new Exception();
                } else {
                    b = this.i.b(connectionResult.c());
                    e = connectionResult.e();
                    aVar4.a(new Status(17, new StringBuilder((String.valueOf(b).length() + 69) + String.valueOf(e).length()).append("Error resolution was canceled by the user, original error message: ").append(b).append(": ").append(e).toString()));
                }
            case 6:
                if (this.h.getApplicationContext() instanceof Application) {
                    b.a((Application) this.h.getApplicationContext());
                    b.a().a(new s(this));
                    if (!b.a().b()) {
                        this.e = 300000;
                        break;
                    }
                }
                break;
            case 7:
                b((com.google.android.gms.common.api.d) message.obj);
                break;
            case 9:
                if (this.m.containsKey(message.obj)) {
                    ((a) this.m.get(message.obj)).h();
                    break;
                }
                break;
            case 10:
                for (ap obtainMessage2 : this.p) {
                    ((a) this.m.remove(obtainMessage2)).c();
                }
                this.p.clear();
                break;
            case 11:
                if (this.m.containsKey(message.obj)) {
                    ((a) this.m.get(message.obj)).i();
                    break;
                }
                break;
            case 12:
                if (this.m.containsKey(message.obj)) {
                    ((a) this.m.get(message.obj)).j();
                    break;
                }
                break;
            case 14:
                p pVar = (p) message.obj;
                ap a = pVar.a();
                if (!this.m.containsKey(a)) {
                    pVar.b().a(Boolean.valueOf(false));
                    break;
                }
                pVar.b().a(Boolean.valueOf(((a) this.m.get(a)).a(false)));
                break;
            case 15:
                bVar = (b) message.obj;
                if (this.m.containsKey(bVar.a)) {
                    a.a((a) this.m.get(bVar.a), bVar);
                    break;
                }
                break;
            case 16:
                bVar = (b) message.obj;
                if (this.m.containsKey(bVar.a)) {
                    a.b((a) this.m.get(bVar.a), bVar);
                    break;
                }
                break;
            default:
                new StringBuilder(31).append("Unknown message id: ").append(message.what);
                return false;
        }
        return true;
    }
}
