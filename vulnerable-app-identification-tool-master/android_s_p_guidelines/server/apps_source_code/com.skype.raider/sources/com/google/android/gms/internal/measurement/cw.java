package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.e;
import com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty;
import com.google.android.gms.measurement.AppMeasurement.a;
import com.google.android.gms.measurement.AppMeasurement.b;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class cw extends ct {
    @VisibleForTesting
    protected dd a;
    @VisibleForTesting
    protected boolean b = true;
    private b c;
    private final Set<Object> d = new CopyOnWriteArraySet();
    private boolean e;
    private final AtomicReference<String> f = new AtomicReference();

    protected cw(bx bxVar) {
        super(bxVar);
    }

    @WorkerThread
    private final void a(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        ab.a(str);
        ab.a(str2);
        ab.a((Object) bundle);
        c();
        G();
        if (this.q.y()) {
            int i;
            if (!this.e) {
                this.e = true;
                try {
                    try {
                        Class.forName("com.google.android.gms.tagmanager.TagManagerService").getDeclaredMethod("initialize", new Class[]{Context.class}).invoke(null, new Object[]{k()});
                    } catch (Exception e) {
                        q().y().a("Failed to invoke Tag Manager's initialize() method", e);
                    }
                } catch (ClassNotFoundException e2) {
                    q().A().a("Tag Manager is not found and thus will not be used");
                }
            }
            if (z3 && !"_iap".equals(str2)) {
                ew n = this.q.n();
                i = !n.a(EventsEntry.COLUMN_NAME_EVENT, str2) ? 2 : !n.a(EventsEntry.COLUMN_NAME_EVENT, a.a, str2) ? 13 : !n.a(EventsEntry.COLUMN_NAME_EVENT, 40, str2) ? 2 : 0;
                if (i != 0) {
                    q().x().a("Invalid public event name. Event will not be logged (FE)", m().a(str2));
                    this.q.n();
                    this.q.n().a(i, "_ev", ew.a(str2, 40, true), str2 != null ? str2.length() : 0);
                    return;
                }
            }
            de v = i().v();
            if (v != null) {
                if (!bundle.containsKey("_sc")) {
                    v.d = true;
                }
            }
            boolean z4 = z && z3;
            df.a(v, bundle, z4);
            boolean equals = "am".equals(str);
            z4 = ew.g(str2);
            if (z && this.c != null && !z4 && !equals) {
                q().B().a("Passing event to registered event handler (FE)", m().a(str2), m().a(bundle));
                return;
            } else if (this.q.D()) {
                i = n().b(str2);
                if (i != 0) {
                    q().x().a("Invalid event name. Event will not be logged (FE)", m().a(str2));
                    n();
                    this.q.n().b(i, "_ev", ew.a(str2, 40, true), str2 != null ? str2.length() : 0);
                    return;
                }
                Bundle a;
                List unmodifiableList = Collections.unmodifiableList(Arrays.asList(new String[]{"_o", "_sn", "_sc", "_si"}));
                Bundle a2 = n().a(str2, bundle, unmodifiableList, z3, true);
                de deVar = (a2 != null && a2.containsKey("_sc") && a2.containsKey("_si")) ? new de(a2.getString("_sn"), a2.getString("_sc"), Long.valueOf(a2.getLong("_si")).longValue()) : null;
                de deVar2 = deVar == null ? v : deVar;
                List arrayList = new ArrayList();
                arrayList.add(a2);
                long nextLong = n().w().nextLong();
                String[] strArr = (String[]) a2.keySet().toArray(new String[bundle.size()]);
                Arrays.sort(strArr);
                int length = strArr.length;
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    if (i3 >= length) {
                        break;
                    }
                    String str4 = strArr[i3];
                    Object obj = a2.get(str4);
                    n();
                    Bundle[] a3 = ew.a(obj);
                    if (a3 != null) {
                        a2.putInt(str4, a3.length);
                        i2 = 0;
                        while (true) {
                            int i4 = i2;
                            if (i4 >= a3.length) {
                                break;
                            }
                            Bundle bundle2 = a3[i4];
                            df.a(deVar2, bundle2, true);
                            a = n().a("_ep", bundle2, unmodifiableList, z3, false);
                            a.putString("_en", str2);
                            a.putLong("_eid", nextLong);
                            a.putString("_gn", str4);
                            a.putInt("_ll", a3.length);
                            a.putInt("_i", i4);
                            arrayList.add(a);
                            i2 = i4 + 1;
                        }
                        i2 = a3.length + 0;
                    } else {
                        i2 = 0;
                    }
                    i3++;
                }
                if (null != null) {
                    a2.putLong("_eid", nextLong);
                    a2.putInt("_epc", 0);
                }
                i2 = 0;
                while (true) {
                    int i5 = i2;
                    if (i5 >= arrayList.size()) {
                        break;
                    }
                    a = (Bundle) arrayList.get(i5);
                    String str5 = (i5 != 0 ? 1 : null) != null ? "_ep" : str2;
                    a.putString("_o", str);
                    Bundle a4 = z2 ? n().a(a) : a;
                    q().B().a("Logging event (FE)", m().a(str2), m().a(a4));
                    h().a(new zzeu(str5, new zzer(a4), str, j), str3);
                    if (!equals) {
                        Iterator it = this.d.iterator();
                        while (it.hasNext()) {
                            it.next();
                            Bundle bundle3 = new Bundle(a4);
                        }
                    }
                    i2 = i5 + 1;
                }
                if (i().v() != null && "_ae".equals(str2)) {
                    o().a(true);
                    return;
                }
                return;
            } else {
                return;
            }
        }
        q().B().a("Event not sent since app measurement is disabled");
    }

    private final void a(String str, String str2, long j, Object obj) {
        p().a(new cx(this, str, str2, obj, j));
    }

    @VisibleForTesting
    private final List<ConditionalUserProperty> b(String str, String str2, String str3) {
        if (p().w()) {
            q().v().a("Cannot get conditional user properties from analytics worker thread");
            return Collections.emptyList();
        }
        p();
        if (bs.v()) {
            q().v().a("Cannot get conditional user properties from main thread");
            return Collections.emptyList();
        }
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.q.p().a(new da(this, atomicReference, str, str2, str3));
            try {
                atomicReference.wait(5000);
            } catch (InterruptedException e) {
                q().y().a("Interrupted waiting for get conditional user properties", str, e);
            }
        }
        List<zzed> list = (List) atomicReference.get();
        if (list == null) {
            q().y().a("Timed out waiting for get conditional user properties", str);
            return Collections.emptyList();
        }
        List<ConditionalUserProperty> arrayList = new ArrayList(list.size());
        for (zzed zzed : list) {
            ConditionalUserProperty conditionalUserProperty = new ConditionalUserProperty();
            conditionalUserProperty.mAppId = str;
            conditionalUserProperty.mOrigin = str2;
            conditionalUserProperty.mCreationTimestamp = zzed.d;
            conditionalUserProperty.mName = zzed.c.a;
            conditionalUserProperty.mValue = zzed.c.a();
            conditionalUserProperty.mActive = zzed.e;
            conditionalUserProperty.mTriggerEventName = zzed.f;
            if (zzed.g != null) {
                conditionalUserProperty.mTimedOutEventName = zzed.g.a;
                if (zzed.g.b != null) {
                    conditionalUserProperty.mTimedOutEventParams = zzed.g.b.b();
                }
            }
            conditionalUserProperty.mTriggerTimeout = zzed.h;
            if (zzed.i != null) {
                conditionalUserProperty.mTriggeredEventName = zzed.i.a;
                if (zzed.i.b != null) {
                    conditionalUserProperty.mTriggeredEventParams = zzed.i.b.b();
                }
            }
            conditionalUserProperty.mTriggeredTimestamp = zzed.c.b;
            conditionalUserProperty.mTimeToLive = zzed.j;
            if (zzed.k != null) {
                conditionalUserProperty.mExpiredEventName = zzed.k.a;
                if (zzed.k.b != null) {
                    conditionalUserProperty.mExpiredEventParams = zzed.k.b.b();
                }
            }
            arrayList.add(conditionalUserProperty);
        }
        return arrayList;
    }

    @VisibleForTesting
    private final Map<String, Object> b(String str, String str2, String str3, boolean z) {
        if (p().w()) {
            q().v().a("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        }
        p();
        if (bs.v()) {
            q().v().a("Cannot get user properties from main thread");
            return Collections.emptyMap();
        }
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.q.p().a(new db(this, atomicReference, str, str2, str3, z));
            try {
                atomicReference.wait(5000);
            } catch (InterruptedException e) {
                q().y().a("Interrupted waiting for get user properties", e);
            }
        }
        List<zzjx> list = (List) atomicReference.get();
        if (list == null) {
            q().y().a("Timed out waiting for get user properties");
            return Collections.emptyMap();
        }
        Map<String, Object> aVar = new android.support.v4.util.a(list.size());
        for (zzjx zzjx : list) {
            aVar.put(zzjx.a, zzjx.a());
        }
        return aVar;
    }

    private final void b(String str, String str2, String str3, Bundle bundle) {
        long a = j().a();
        ab.a(str2);
        ConditionalUserProperty conditionalUserProperty = new ConditionalUserProperty();
        conditionalUserProperty.mAppId = str;
        conditionalUserProperty.mName = str2;
        conditionalUserProperty.mCreationTimestamp = a;
        if (str3 != null) {
            conditionalUserProperty.mExpiredEventName = str3;
            conditionalUserProperty.mExpiredEventParams = bundle;
        }
        p().a(new cz(this, conditionalUserProperty));
    }

    private final void c(ConditionalUserProperty conditionalUserProperty) {
        long a = j().a();
        ab.a((Object) conditionalUserProperty);
        ab.a(conditionalUserProperty.mName);
        ab.a(conditionalUserProperty.mOrigin);
        ab.a(conditionalUserProperty.mValue);
        conditionalUserProperty.mCreationTimestamp = a;
        String str = conditionalUserProperty.mName;
        Object obj = conditionalUserProperty.mValue;
        if (n().c(str) != 0) {
            q().v().a("Invalid conditional user property name", m().c(str));
        } else if (n().b(str, obj) != 0) {
            q().v().a("Invalid conditional user property value", m().c(str), obj);
        } else {
            n();
            Object c = ew.c(str, obj);
            if (c == null) {
                q().v().a("Unable to normalize conditional user property value", m().c(str), obj);
                return;
            }
            conditionalUserProperty.mValue = c;
            long j = conditionalUserProperty.mTriggerTimeout;
            if (TextUtils.isEmpty(conditionalUserProperty.mTriggerEventName) || (j <= 15552000000L && j >= 1)) {
                j = conditionalUserProperty.mTimeToLive;
                if (j > 15552000000L || j < 1) {
                    q().v().a("Invalid conditional user property time to live", m().c(str), Long.valueOf(j));
                    return;
                } else {
                    p().a(new cy(this, conditionalUserProperty));
                    return;
                }
            }
            q().v().a("Invalid conditional user property timeout", m().c(str), Long.valueOf(j));
        }
    }

    public final List<ConditionalUserProperty> a(String str, String str2) {
        return b(null, str, str2);
    }

    public final List<ConditionalUserProperty> a(String str, String str2, String str3) {
        ab.a(str);
        a();
        return b(str, str2, str3);
    }

    public final Map<String, Object> a(String str, String str2, String str3, boolean z) {
        ab.a(str);
        a();
        return b(str, str2, str3, z);
    }

    public final Map<String, Object> a(String str, String str2, boolean z) {
        return b(null, str, str2, z);
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    public final void a(ConditionalUserProperty conditionalUserProperty) {
        ab.a((Object) conditionalUserProperty);
        ConditionalUserProperty conditionalUserProperty2 = new ConditionalUserProperty(conditionalUserProperty);
        if (!TextUtils.isEmpty(conditionalUserProperty2.mAppId)) {
            q().y().a("Package name should be null when calling setConditionalUserProperty");
        }
        conditionalUserProperty2.mAppId = null;
        c(conditionalUserProperty2);
    }

    final void a(@Nullable String str) {
        this.f.set(str);
    }

    public final void a(String str, String str2, Bundle bundle) {
        boolean z;
        Bundle bundle2;
        if (this.c == null || ew.g(str2)) {
            z = true;
        } else {
            z = false;
        }
        long a = j().a();
        if (bundle == null) {
            bundle2 = new Bundle();
        } else {
            bundle2 = new Bundle(bundle);
            for (String str3 : bundle2.keySet()) {
                Object obj = bundle2.get(str3);
                int i;
                if (obj instanceof Bundle) {
                    bundle2.putBundle(str3, new Bundle((Bundle) obj));
                } else if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    for (i = 0; i < parcelableArr.length; i++) {
                        if (parcelableArr[i] instanceof Bundle) {
                            parcelableArr[i] = new Bundle((Bundle) parcelableArr[i]);
                        }
                    }
                } else if (obj instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) obj;
                    for (i = 0; i < arrayList.size(); i++) {
                        Object obj2 = arrayList.get(i);
                        if (obj2 instanceof Bundle) {
                            arrayList.set(i, new Bundle((Bundle) obj2));
                        }
                    }
                }
            }
        }
        p().a(new dc(this, str, str2, a, bundle2, z));
    }

    public final void a(String str, String str2, Object obj) {
        int i = 0;
        ab.a(str);
        long a = j().a();
        int c = n().c(str2);
        String a2;
        if (c != 0) {
            n();
            a2 = ew.a(str2, 24, true);
            if (str2 != null) {
                i = str2.length();
            }
            this.q.n().a(c, "_ev", a2, i);
        } else if (obj != null) {
            c = n().b(str2, obj);
            if (c != 0) {
                n();
                a2 = ew.a(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i = String.valueOf(obj).length();
                }
                this.q.n().a(c, "_ev", a2, i);
                return;
            }
            n();
            Object c2 = ew.c(str2, obj);
            if (c2 != null) {
                a(str, str2, a, c2);
            }
        } else {
            a(str, str2, a, null);
        }
    }

    public final void a(String str, String str2, String str3, Bundle bundle) {
        ab.a(str);
        a();
        b(str, str2, str3, bundle);
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final void b(ConditionalUserProperty conditionalUserProperty) {
        ab.a((Object) conditionalUserProperty);
        ab.a(conditionalUserProperty.mAppId);
        a();
        c(new ConditionalUserProperty(conditionalUserProperty));
    }

    @WorkerThread
    final void b(String str, String str2, Bundle bundle) {
        c();
        boolean z = this.c == null || ew.g(str2);
        a(str, str2, j().a(), bundle, true, z, false, null);
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final void c(String str, String str2, Bundle bundle) {
        b(null, str, str2, bundle);
    }

    public final /* bridge */ /* synthetic */ n d() {
        return super.d();
    }

    public final /* bridge */ /* synthetic */ cw e() {
        return super.e();
    }

    public final /* bridge */ /* synthetic */ aq f() {
        return super.f();
    }

    public final /* bridge */ /* synthetic */ af g() {
        return super.g();
    }

    public final /* bridge */ /* synthetic */ di h() {
        return super.h();
    }

    public final /* bridge */ /* synthetic */ df i() {
        return super.i();
    }

    public final /* bridge */ /* synthetic */ e j() {
        return super.j();
    }

    public final /* bridge */ /* synthetic */ Context k() {
        return super.k();
    }

    public final /* bridge */ /* synthetic */ ar l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ at m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ ew n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ee o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ bs p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ av q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ bf r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ w s() {
        return super.s();
    }

    protected final boolean t() {
        return false;
    }

    @Nullable
    public final String v() {
        return (String) this.f.get();
    }

    @WorkerThread
    public final void w() {
        c();
        G();
        if (this.q.D()) {
            h().w();
            this.b = false;
            String y = r().y();
            if (!TextUtils.isEmpty(y)) {
                g().G();
                if (!y.equals(VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", y);
                    a("auto", "_ou", bundle);
                }
            }
        }
    }

    static /* synthetic */ void a(cw cwVar, String str, String str2, Object obj, long j) {
        ab.a(str);
        ab.a(str2);
        cwVar.c();
        cwVar.G();
        if (!cwVar.q.y()) {
            cwVar.q().B().a("User property not set since app measurement is disabled");
        } else if (cwVar.q.D()) {
            cwVar.q().B().a("Setting user property (FE)", cwVar.m().a(str2), obj);
            cwVar.h().a(new zzjx(str2, j, obj, str));
        }
    }

    static /* synthetic */ void a(cw cwVar, ConditionalUserProperty conditionalUserProperty) {
        cwVar.c();
        cwVar.G();
        ab.a((Object) conditionalUserProperty);
        ab.a(conditionalUserProperty.mName);
        ab.a(conditionalUserProperty.mOrigin);
        ab.a(conditionalUserProperty.mValue);
        if (cwVar.q.y()) {
            zzjx zzjx = new zzjx(conditionalUserProperty.mName, conditionalUserProperty.mTriggeredTimestamp, conditionalUserProperty.mValue, conditionalUserProperty.mOrigin);
            try {
                zzeu a = cwVar.n().a(conditionalUserProperty.mTriggeredEventName, conditionalUserProperty.mTriggeredEventParams, conditionalUserProperty.mOrigin, 0);
                cwVar.h().a(new zzed(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, zzjx, conditionalUserProperty.mCreationTimestamp, false, conditionalUserProperty.mTriggerEventName, cwVar.n().a(conditionalUserProperty.mTimedOutEventName, conditionalUserProperty.mTimedOutEventParams, conditionalUserProperty.mOrigin, 0), conditionalUserProperty.mTriggerTimeout, a, conditionalUserProperty.mTimeToLive, cwVar.n().a(conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, 0)));
                return;
            } catch (IllegalArgumentException e) {
                return;
            }
        }
        cwVar.q().B().a("Conditional property not sent since Firebase Analytics is disabled");
    }

    static /* synthetic */ void b(cw cwVar, ConditionalUserProperty conditionalUserProperty) {
        cwVar.c();
        cwVar.G();
        ab.a((Object) conditionalUserProperty);
        ab.a(conditionalUserProperty.mName);
        if (cwVar.q.y()) {
            zzjx zzjx = new zzjx(conditionalUserProperty.mName, 0, null, null);
            try {
                cwVar.h().a(new zzed(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, zzjx, conditionalUserProperty.mCreationTimestamp, conditionalUserProperty.mActive, conditionalUserProperty.mTriggerEventName, null, conditionalUserProperty.mTriggerTimeout, null, conditionalUserProperty.mTimeToLive, cwVar.n().a(conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, conditionalUserProperty.mCreationTimestamp)));
                return;
            } catch (IllegalArgumentException e) {
                return;
            }
        }
        cwVar.q().B().a("Conditional property not cleared since Firebase Analytics is disabled");
    }
}
