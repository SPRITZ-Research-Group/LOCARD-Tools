package com.google.android.gms.internal.measurement;

import android.os.Binder;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.k;
import com.google.android.gms.common.l;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.q;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class bz extends ao {
    private final eo a;
    private Boolean b;
    @Nullable
    private String c;

    public bz(eo eoVar) {
        this(eoVar, (byte) 0);
    }

    private bz(eo eoVar, byte b) {
        ab.a((Object) eoVar);
        this.a = eoVar;
        this.c = null;
    }

    @VisibleForTesting
    private final void a(Runnable runnable) {
        ab.a((Object) runnable);
        if (((Boolean) al.T.b()).booleanValue() && this.a.p().w()) {
            runnable.run();
        } else {
            this.a.p().a(runnable);
        }
    }

    @BinderThread
    private final void a(String str, boolean z) {
        boolean z2 = false;
        if (TextUtils.isEmpty(str)) {
            this.a.q().v().a("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        if (z) {
            try {
                if (this.b == null) {
                    if ("com.google.android.gms".equals(this.c) || q.a(this.a.k(), Binder.getCallingUid()) || l.a(this.a.k()).a(Binder.getCallingUid())) {
                        z2 = true;
                    }
                    this.b = Boolean.valueOf(z2);
                }
                if (this.b.booleanValue()) {
                    return;
                }
            } catch (SecurityException e) {
                this.a.q().v().a("Measurement Service called with invalid calling package. appId", av.a(str));
                throw e;
            }
        }
        if (this.c == null && k.a(this.a.k(), Binder.getCallingUid(), str)) {
            this.c = str;
        }
        if (!str.equals(this.c)) {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
        }
    }

    @BinderThread
    private final void e(zzdz zzdz) {
        ab.a((Object) zzdz);
        a(zzdz.a, false);
        this.a.n().d(zzdz.b);
    }

    @BinderThread
    public final List<zzjx> a(zzdz zzdz, boolean z) {
        Object e;
        e(zzdz);
        try {
            List<ev> list = (List) this.a.p().a(new cp(this, zzdz)).get();
            List<zzjx> arrayList = new ArrayList(list.size());
            for (ev evVar : list) {
                if (z || !ew.g(evVar.c)) {
                    arrayList.add(new zzjx(evVar));
                }
            }
            return arrayList;
        } catch (InterruptedException e2) {
            e = e2;
            this.a.q().v().a("Failed to get user attributes. appId", av.a(zzdz.a), e);
            return null;
        } catch (ExecutionException e3) {
            e = e3;
            this.a.q().v().a("Failed to get user attributes. appId", av.a(zzdz.a), e);
            return null;
        }
    }

    @BinderThread
    public final List<zzed> a(String str, String str2, zzdz zzdz) {
        Object e;
        e(zzdz);
        try {
            return (List) this.a.p().a(new ch(this, zzdz, str, str2)).get();
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        }
        this.a.q().v().a("Failed to get conditional user properties", e);
        return Collections.emptyList();
    }

    @BinderThread
    public final List<zzed> a(String str, String str2, String str3) {
        Object e;
        a(str, true);
        try {
            return (List) this.a.p().a(new ci(this, str, str2, str3)).get();
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        }
        this.a.q().v().a("Failed to get conditional user properties", e);
        return Collections.emptyList();
    }

    @BinderThread
    public final List<zzjx> a(String str, String str2, String str3, boolean z) {
        Object e;
        a(str, true);
        try {
            List<ev> list = (List) this.a.p().a(new cg(this, str, str2, str3)).get();
            List<zzjx> arrayList = new ArrayList(list.size());
            for (ev evVar : list) {
                if (z || !ew.g(evVar.c)) {
                    arrayList.add(new zzjx(evVar));
                }
            }
            return arrayList;
        } catch (InterruptedException e2) {
            e = e2;
            this.a.q().v().a("Failed to get user attributes. appId", av.a(str), e);
            return Collections.emptyList();
        } catch (ExecutionException e3) {
            e = e3;
            this.a.q().v().a("Failed to get user attributes. appId", av.a(str), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzjx> a(String str, String str2, boolean z, zzdz zzdz) {
        Object e;
        e(zzdz);
        try {
            List<ev> list = (List) this.a.p().a(new cf(this, zzdz, str, str2)).get();
            List<zzjx> arrayList = new ArrayList(list.size());
            for (ev evVar : list) {
                if (z || !ew.g(evVar.c)) {
                    arrayList.add(new zzjx(evVar));
                }
            }
            return arrayList;
        } catch (InterruptedException e2) {
            e = e2;
            this.a.q().v().a("Failed to get user attributes. appId", av.a(zzdz.a), e);
            return Collections.emptyList();
        } catch (ExecutionException e3) {
            e = e3;
            this.a.q().v().a("Failed to get user attributes. appId", av.a(zzdz.a), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final void a(long j, String str, String str2, String str3) {
        a(new cr(this, str2, str3, str, j));
    }

    @BinderThread
    public final void a(zzdz zzdz) {
        e(zzdz);
        a(new cq(this, zzdz));
    }

    @BinderThread
    public final void a(zzed zzed) {
        ab.a((Object) zzed);
        ab.a(zzed.c);
        a(zzed.a, true);
        zzed zzed2 = new zzed(zzed);
        if (zzed.c.a() == null) {
            a(new cd(this, zzed2));
        } else {
            a(new ce(this, zzed2));
        }
    }

    @BinderThread
    public final void a(zzed zzed, zzdz zzdz) {
        ab.a((Object) zzed);
        ab.a(zzed.c);
        e(zzdz);
        zzed zzed2 = new zzed(zzed);
        zzed2.a = zzdz.a;
        if (zzed.c.a() == null) {
            a(new cb(this, zzed2, zzdz));
        } else {
            a(new cc(this, zzed2, zzdz));
        }
    }

    @BinderThread
    public final void a(zzeu zzeu, zzdz zzdz) {
        ab.a((Object) zzeu);
        e(zzdz);
        a(new ck(this, zzeu, zzdz));
    }

    @BinderThread
    public final void a(zzeu zzeu, String str, String str2) {
        ab.a((Object) zzeu);
        ab.a(str);
        a(str, true);
        a(new cl(this, zzeu, str));
    }

    @BinderThread
    public final void a(zzjx zzjx, zzdz zzdz) {
        ab.a((Object) zzjx);
        e(zzdz);
        if (zzjx.a() == null) {
            a(new cn(this, zzjx, zzdz));
        } else {
            a(new co(this, zzjx, zzdz));
        }
    }

    @BinderThread
    public final byte[] a(zzeu zzeu, String str) {
        Object e;
        ab.a(str);
        ab.a((Object) zzeu);
        a(str, true);
        this.a.q().B().a("Log and bundle. event", this.a.o().a(zzeu.a));
        long c = this.a.j().c() / 1000000;
        try {
            byte[] bArr = (byte[]) this.a.p().b(new cm(this, zzeu, str)).get();
            if (bArr == null) {
                this.a.q().v().a("Log and bundle returned null. appId", av.a(str));
                bArr = new byte[0];
            }
            this.a.q().B().a("Log and bundle processed. event, size, time_ms", this.a.o().a(zzeu.a), Integer.valueOf(bArr.length), Long.valueOf((this.a.j().c() / 1000000) - c));
            return bArr;
        } catch (InterruptedException e2) {
            e = e2;
            this.a.q().v().a("Failed to log and bundle. appId, event, error", av.a(str), this.a.o().a(zzeu.a), e);
            return null;
        } catch (ExecutionException e3) {
            e = e3;
            this.a.q().v().a("Failed to log and bundle. appId, event, error", av.a(str), this.a.o().a(zzeu.a), e);
            return null;
        }
    }

    @BinderThread
    public final void b(zzdz zzdz) {
        e(zzdz);
        a(new ca(this, zzdz));
    }

    @BinderThread
    public final String c(zzdz zzdz) {
        e(zzdz);
        return this.a.d(zzdz);
    }

    @BinderThread
    public final void d(zzdz zzdz) {
        a(zzdz.a, false);
        a(new cj(this, zzdz));
    }
}
