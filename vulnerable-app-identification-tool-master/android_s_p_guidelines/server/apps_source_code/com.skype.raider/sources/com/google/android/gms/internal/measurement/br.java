package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.util.a;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.e;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurement.c;
import com.google.android.gms.measurement.AppMeasurement.d;
import java.io.IOException;
import java.util.Map;

public final class br extends en implements y {
    @VisibleForTesting
    private static int b = 65535;
    @VisibleForTesting
    private static int c = 2;
    private final Map<String, Map<String, String>> d = new a();
    private final Map<String, Map<String, Boolean>> e = new a();
    private final Map<String, Map<String, Boolean>> f = new a();
    private final Map<String, fg> g = new a();
    private final Map<String, Map<String, Integer>> h = new a();
    private final Map<String, String> i = new a();

    br(eo eoVar) {
        super(eoVar);
    }

    @WorkerThread
    private final fg a(String str, byte[] bArr) {
        if (bArr == null) {
            return new fg();
        }
        a a = a.a(bArr, bArr.length);
        j fgVar = new fg();
        try {
            fgVar.a(a);
            q().C().a("Parsed config. version, gmp_app_id", fgVar.c, fgVar.d);
            return fgVar;
        } catch (IOException e) {
            q().y().a("Unable to merge remote config. appId", av.a(str), e);
            return new fg();
        }
    }

    private static Map<String, String> a(fg fgVar) {
        Map<String, String> aVar = new a();
        if (!(fgVar == null || fgVar.e == null)) {
            for (fh fhVar : fgVar.e) {
                if (fhVar != null) {
                    aVar.put(fhVar.c, fhVar.d);
                }
            }
        }
        return aVar;
    }

    private final void a(String str, fg fgVar) {
        Map aVar = new a();
        Map aVar2 = new a();
        Map aVar3 = new a();
        if (!(fgVar == null || fgVar.f == null)) {
            for (ff ffVar : fgVar.f) {
                if (TextUtils.isEmpty(ffVar.c)) {
                    q().y().a("EventConfig contained null event name");
                } else {
                    Object a = AppMeasurement.a.a(ffVar.c);
                    if (!TextUtils.isEmpty(a)) {
                        ffVar.c = a;
                    }
                    aVar.put(ffVar.c, ffVar.d);
                    aVar2.put(ffVar.c, ffVar.e);
                    if (ffVar.f != null) {
                        if (ffVar.f.intValue() < c || ffVar.f.intValue() > b) {
                            q().y().a("Invalid sampling rate. Event name, sample rate", ffVar.c, ffVar.f);
                        } else {
                            aVar3.put(ffVar.c, ffVar.f);
                        }
                    }
                }
            }
        }
        this.e.put(str, aVar);
        this.f.put(str, aVar2);
        this.h.put(str, aVar3);
    }

    @WorkerThread
    private final void g(String str) {
        N();
        c();
        ab.a(str);
        if (this.g.get(str) == null) {
            byte[] d = r_().d(str);
            if (d == null) {
                this.d.put(str, null);
                this.e.put(str, null);
                this.f.put(str, null);
                this.g.put(str, null);
                this.i.put(str, null);
                this.h.put(str, null);
                return;
            }
            fg a = a(str, d);
            this.d.put(str, a(a));
            a(str, a);
            this.g.put(str, a);
            this.i.put(str, null);
        }
    }

    @WorkerThread
    protected final fg a(String str) {
        N();
        c();
        ab.a(str);
        g(str);
        return (fg) this.g.get(str);
    }

    @WorkerThread
    public final String a(String str, String str2) {
        c();
        g(str);
        Map map = (Map) this.d.get(str);
        return map != null ? (String) map.get(str2) : null;
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    @WorkerThread
    protected final boolean a(String str, byte[] bArr, String str2) {
        N();
        c();
        ab.a(str);
        fg a = a(str, bArr);
        a(str, a);
        this.g.put(str, a);
        this.i.put(str, str2);
        this.d.put(str, a(a));
        em s_ = s_();
        ez[] ezVarArr = a.g;
        ab.a((Object) ezVarArr);
        for (ez ezVar : ezVarArr) {
            for (fa faVar : ezVar.e) {
                String a2 = AppMeasurement.a.a(faVar.d);
                if (a2 != null) {
                    faVar.d = a2;
                }
                for (fb fbVar : faVar.e) {
                    String a3 = c.a(fbVar.f);
                    if (a3 != null) {
                        fbVar.f = a3;
                    }
                }
            }
            for (fd fdVar : ezVar.d) {
                String a4 = d.a(fdVar.d);
                if (a4 != null) {
                    fdVar.d = a4;
                }
            }
        }
        s_.r_().a(str, ezVarArr);
        try {
            a.g = null;
            byte[] bArr2 = new byte[a.d()];
            a.a(b.a(bArr2, bArr2.length));
            bArr = bArr2;
        } catch (IOException e) {
            q().y().a("Unable to serialize reduced-size config. Storing full config instead. appId", av.a(str), e);
        }
        cs r_ = r_();
        ab.a(str);
        r_.c();
        r_.N();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        try {
            if (((long) r_.x().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                r_.q().v().a("Failed to update remote config (got 0). appId", av.a(str));
            }
        } catch (SQLiteException e2) {
            r_.q().v().a("Error storing remote config. appId", av.a(str), e2);
        }
        return true;
    }

    @WorkerThread
    protected final String b(String str) {
        c();
        return (String) this.i.get(str);
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    @WorkerThread
    final boolean b(String str, String str2) {
        c();
        g(str);
        if (e(str) && ew.g(str2)) {
            return true;
        }
        if (f(str) && ew.a(str2)) {
            return true;
        }
        Map map = (Map) this.e.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        return bool == null ? false : bool.booleanValue();
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    @WorkerThread
    protected final void c(String str) {
        c();
        this.i.put(str, null);
    }

    @WorkerThread
    final boolean c(String str, String str2) {
        c();
        g(str);
        if ("ecommerce_purchase".equals(str2)) {
            return true;
        }
        Map map = (Map) this.f.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        return bool == null ? false : bool.booleanValue();
    }

    @WorkerThread
    final int d(String str, String str2) {
        c();
        g(str);
        Map map = (Map) this.h.get(str);
        if (map == null) {
            return 1;
        }
        Integer num = (Integer) map.get(str2);
        return num == null ? 1 : num.intValue();
    }

    public final /* bridge */ /* synthetic */ n d() {
        return super.d();
    }

    @WorkerThread
    final void d(String str) {
        c();
        this.g.remove(str);
    }

    public final /* bridge */ /* synthetic */ cw e() {
        return super.e();
    }

    final boolean e(String str) {
        return "1".equals(a(str, "measurement.upload.blacklist_internal"));
    }

    public final /* bridge */ /* synthetic */ aq f() {
        return super.f();
    }

    final boolean f(String str) {
        return "1".equals(a(str, "measurement.upload.blacklist_public"));
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

    public final /* bridge */ /* synthetic */ z r_() {
        return super.r_();
    }

    public final /* bridge */ /* synthetic */ w s() {
        return super.s();
    }

    public final /* bridge */ /* synthetic */ t s_() {
        return super.s_();
    }

    protected final boolean t() {
        return false;
    }
}
