package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.a;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.a.c;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.b;
import com.google.android.gms.common.util.e;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class eo implements u {
    br a;
    az b;
    long c;
    private z d;
    private bd e;
    private ek f;
    private t g;
    private bx h;
    private boolean i = false;
    private boolean j;
    @VisibleForTesting
    private long k;
    private List<Runnable> l;
    private int m;
    private int n;
    private boolean o;
    private boolean p;
    private boolean q;
    private FileLock r;
    private FileChannel s;
    private List<Long> t;
    private List<Long> u;

    @WorkerThread
    @VisibleForTesting
    private final int a(FileChannel fileChannel) {
        int i = 0;
        x();
        if (fileChannel == null || !fileChannel.isOpen()) {
            q().v().a("Bad channel to read from");
            return i;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read == 4) {
                allocate.flip();
                return allocate.getInt();
            } else if (read == -1) {
                return i;
            } else {
                q().y().a("Unexpected data length. Bytes read", Integer.valueOf(read));
                return i;
            }
        } catch (IOException e) {
            q().v().a("Failed to read from channel", e);
            return i;
        }
    }

    private final br a() {
        a(this.a);
        return this.a;
    }

    private final zzdz a(Context context, String str, String str2, boolean z, boolean z2, boolean z3, long j) {
        Object charSequence;
        String str3 = "Unknown";
        String str4 = "Unknown";
        int i = Integer.MIN_VALUE;
        String str5 = "Unknown";
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            q().v().a("PackageManager is null, can not log app install information");
            return null;
        }
        try {
            str3 = packageManager.getInstallerPackageName(str);
        } catch (IllegalArgumentException e) {
            q().v().a("Error retrieving installer package name. appId", av.a(str));
        }
        if (str3 == null) {
            str3 = "manual_install";
        } else if ("com.android.vending".equals(str3)) {
            str3 = "";
        }
        try {
            PackageInfo b = c.a(context).b(str, 0);
            if (b != null) {
                CharSequence b2 = c.a(context).b(str);
                if (TextUtils.isEmpty(b2)) {
                    String charSequence2 = str5;
                } else {
                    charSequence2 = b2.toString();
                }
                try {
                    str4 = b.versionName;
                    i = b.versionCode;
                } catch (NameNotFoundException e2) {
                    q().v().a("Error retrieving newly installed package info. appId, appName", av.a(str), charSequence2);
                    return null;
                }
            }
            long j2 = 0;
            if (c().j(str)) {
                j2 = j;
            }
            return new zzdz(str, str2, str4, (long) i, str3, 12451, n().b(context, str), null, z, false, "", 0, j2, 0, z2, z3, false);
        } catch (NameNotFoundException e3) {
            charSequence2 = str5;
            q().v().a("Error retrieving newly installed package info. appId, appName", av.a(str), charSequence2);
            return null;
        }
    }

    private static void a(en enVar) {
        if (enVar == null) {
            throw new IllegalStateException("Upload component not created");
        } else if (!enVar.M()) {
            String valueOf = String.valueOf(enVar.getClass());
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 27).append("Component not initialized: ").append(valueOf).toString());
        }
    }

    @WorkerThread
    private final void a(r rVar) {
        x();
        if (TextUtils.isEmpty(rVar.d())) {
            a(rVar.b(), 204, null, null, null);
            return;
        }
        String d = rVar.d();
        String c = rVar.c();
        Builder builder = new Builder();
        Builder encodedAuthority = builder.scheme((String) al.f.b()).encodedAuthority((String) al.g.b());
        String str = "config/app/";
        String valueOf = String.valueOf(d);
        encodedAuthority.path(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).appendQueryParameter("app_instance_id", c).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", "12451");
        String uri = builder.build().toString();
        try {
            Map map;
            URL url = new URL(uri);
            q().C().a("Fetching remote configuration", rVar.b());
            fg a = a().a(rVar.b());
            CharSequence b = a().b(rVar.b());
            if (a == null || TextUtils.isEmpty(b)) {
                map = null;
            } else {
                Map aVar = new a();
                aVar.put("If-Modified-Since", b);
                map = aVar;
            }
            this.o = true;
            cs F = F();
            c = rVar.b();
            Object eqVar = new eq(this);
            F.c();
            F.N();
            ab.a((Object) url);
            ab.a(eqVar);
            F.p().b(new bc(F, c, url, null, map, eqVar));
        } catch (MalformedURLException e) {
            q().v().a("Failed to parse config URL. Not fetching. appId", av.a(rVar.b()), uri);
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean a(int i, FileChannel fileChannel) {
        x();
        if (fileChannel == null || !fileChannel.isOpen()) {
            q().v().a("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() == 4) {
                return true;
            }
            q().v().a("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            return true;
        } catch (IOException e) {
            q().v().a("Failed to write to channel", e);
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @WorkerThread
    private final boolean a(long j) {
        Throwable th;
        G().u();
        eo eoVar = this;
        es esVar = new es();
        cs G = G();
        String str = null;
        long j2 = this.c;
        ab.a((Object) esVar);
        G.c();
        G.N();
        Cursor cursor = null;
        Object obj;
        boolean z;
        fm fmVar;
        int i;
        long j3;
        boolean e;
        int i2;
        fj fjVar;
        Object obj2;
        long j4;
        int i3;
        boolean z2;
        boolean c;
        Object obj3;
        Object obj4;
        fk[] fkVarArr;
        int length;
        int i4;
        fk fkVar;
        Object obj5;
        fk[] fkVarArr2;
        fk fkVar2;
        fk fkVar3;
        int i5;
        fk fkVar4;
        boolean z3;
        long j5;
        long j6;
        ev c2;
        ev evVar;
        fo foVar;
        Map hashMap;
        fj[] fjVarArr;
        SecureRandom w;
        fj[] fjVarArr2;
        int length2;
        int i6;
        int i7;
        fj fjVar2;
        String str2;
        ah ahVar;
        Long valueOf;
        int d;
        ah ahVar2;
        Long l;
        Boolean valueOf2;
        String str3;
        r b;
        fg a;
        cs G2;
        StringBuilder stringBuilder;
        cs G3;
        try {
            String[] strArr;
            String str4;
            String str5;
            Cursor cursor2;
            String str6;
            SQLiteDatabase x = G.x();
            if (TextUtils.isEmpty(null)) {
                strArr = j2 != -1 ? new String[]{String.valueOf(j2), String.valueOf(j)} : new String[]{String.valueOf(j)};
                str4 = j2 != -1 ? "rowid <= ? and " : "";
                cursor = x.rawQuery(new StringBuilder(String.valueOf(str4).length() + 148).append("select app_id, metadata_fingerprint from raw_events where ").append(str4).append("app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;").toString(), strArr);
                if (cursor.moveToFirst()) {
                    str = cursor.getString(0);
                    str4 = cursor.getString(1);
                    cursor.close();
                    str5 = str4;
                    cursor2 = cursor;
                    str6 = str;
                } else {
                    if (cursor != null) {
                        cursor.close();
                    }
                    obj = (esVar.c != null || esVar.c.isEmpty()) ? 1 : null;
                    if (obj == null) {
                        z = false;
                        fmVar = esVar.a;
                        fmVar.d = new fj[esVar.c.size()];
                        i = 0;
                        j3 = 0;
                        e = c().e(fmVar.q);
                        i2 = 0;
                        while (i2 < esVar.c.size()) {
                            fjVar = (fj) esVar.c.get(i2);
                            if (a().b(esVar.a.q, fjVar.d)) {
                                q().y().a("Dropping blacklisted raw event. appId", av.a(esVar.a.q), o().a(fjVar.d));
                                obj2 = (a().e(esVar.a.q) || a().f(esVar.a.q)) ? 1 : null;
                                if (obj2 == null || "_err".equals(fjVar.d)) {
                                    j4 = j3;
                                    i3 = i;
                                    z2 = z;
                                } else {
                                    n().b(11, "_ev", fjVar.d, 0);
                                    j4 = j3;
                                    i3 = i;
                                    z2 = z;
                                }
                            } else {
                                c = a().c(esVar.a.q, fjVar.d);
                                if (!c) {
                                    n();
                                }
                                obj3 = null;
                                obj4 = null;
                                if (fjVar.c == null) {
                                    fjVar.c = new fk[0];
                                }
                                fkVarArr = fjVar.c;
                                length = fkVarArr.length;
                                i4 = 0;
                                while (i4 < length) {
                                    fkVar = fkVarArr[i4];
                                    if (!"_c".equals(fkVar.c)) {
                                        fkVar.e = Long.valueOf(1);
                                        obj5 = obj4;
                                        obj4 = 1;
                                        obj2 = obj5;
                                    } else if ("_r".equals(fkVar.c)) {
                                        fkVar.e = Long.valueOf(1);
                                        obj2 = 1;
                                        obj4 = obj3;
                                    } else {
                                        obj2 = obj4;
                                        obj4 = obj3;
                                    }
                                    i4++;
                                    obj3 = obj4;
                                    obj4 = obj2;
                                }
                                if (obj3 == null && c) {
                                    q().C().a("Marking event as conversion", o().a(fjVar.d));
                                    fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                                    fkVar2 = new fk();
                                    fkVar2.c = "_c";
                                    fkVar2.e = Long.valueOf(1);
                                    fkVarArr2[fkVarArr2.length - 1] = fkVar2;
                                    fjVar.c = fkVarArr2;
                                }
                                if (obj4 == null) {
                                    q().C().a("Marking event as real-time", o().a(fjVar.d));
                                    fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                                    fkVar3 = new fk();
                                    fkVar3.c = "_r";
                                    fkVar3.e = Long.valueOf(1);
                                    fkVarArr2[fkVarArr2.length - 1] = fkVar3;
                                    fjVar.c = fkVarArr2;
                                }
                                if (G().a(g(), esVar.a.q, false, false, false, false, true).e > ((long) c().a(esVar.a.q))) {
                                    i5 = 0;
                                    while (i5 < fjVar.c.length) {
                                        if ("_r".equals(fjVar.c[i5].c)) {
                                            obj4 = new fk[(fjVar.c.length - 1)];
                                            if (i5 > 0) {
                                                System.arraycopy(fjVar.c, 0, obj4, 0, i5);
                                            }
                                            if (i5 < obj4.length) {
                                                System.arraycopy(fjVar.c, i5 + 1, obj4, i5, obj4.length - i5);
                                            }
                                            fjVar.c = obj4;
                                        } else {
                                            i5++;
                                        }
                                    }
                                } else {
                                    z = true;
                                }
                                if (ew.a(fjVar.d) && c && G().a(g(), esVar.a.q, false, false, true, false, false).c > ((long) c().b(esVar.a.q, al.o))) {
                                    q().y().a("Too many conversions. Not logging as conversion. appId", av.a(esVar.a.q));
                                    obj3 = null;
                                    fkVar3 = null;
                                    fkVarArr = fjVar.c;
                                    length = fkVarArr.length;
                                    i4 = 0;
                                    while (i4 < length) {
                                        fkVar = fkVarArr[i4];
                                        if (!"_c".equals(fkVar.c)) {
                                            obj4 = obj3;
                                        } else if ("_err".equals(fkVar.c)) {
                                            fkVar4 = fkVar3;
                                            i3 = 1;
                                            fkVar = fkVar4;
                                        } else {
                                            fkVar = fkVar3;
                                            obj4 = obj3;
                                        }
                                        i4++;
                                        obj3 = obj4;
                                        fkVar3 = fkVar;
                                    }
                                    if (obj3 == null && fkVar3 != null) {
                                        fjVar.c = (fk[]) b.a(fjVar.c, fkVar3);
                                        z3 = z;
                                        if (e) {
                                        }
                                        j5 = j3;
                                        i5 = i + 1;
                                        fmVar.d[i] = fjVar;
                                        j6 = j5;
                                        i3 = i5;
                                        z2 = z3;
                                        j4 = j6;
                                    } else if (fkVar3 != null) {
                                        fkVar3.c = "_err";
                                        fkVar3.e = Long.valueOf(10);
                                        z3 = z;
                                        if (e || !"_e".equals(fjVar.d)) {
                                            j5 = j3;
                                        } else if (fjVar.c == null || fjVar.c.length == 0) {
                                            q().y().a("Engagement event does not contain any parameters. appId", av.a(esVar.a.q));
                                            j5 = j3;
                                        } else {
                                            n();
                                            Long l2 = (Long) ew.b(fjVar, "_et");
                                            if (l2 == null) {
                                                q().y().a("Engagement event does not include duration. appId", av.a(esVar.a.q));
                                                j5 = j3;
                                            } else {
                                                j5 = j3 + l2.longValue();
                                            }
                                        }
                                        i5 = i + 1;
                                        fmVar.d[i] = fjVar;
                                        j6 = j5;
                                        i3 = i5;
                                        z2 = z3;
                                        j4 = j6;
                                    } else {
                                        q().v().a("Did not find conversion parameter. appId", av.a(esVar.a.q));
                                    }
                                }
                                z3 = z;
                                if (e) {
                                }
                                j5 = j3;
                                i5 = i + 1;
                                fmVar.d[i] = fjVar;
                                j6 = j5;
                                i3 = i5;
                                z2 = z3;
                                j4 = j6;
                            }
                            i2++;
                            j3 = j4;
                            i = i3;
                            z = z2;
                        }
                        if (i < esVar.c.size()) {
                            fmVar.d = (fj[]) Arrays.copyOf(fmVar.d, i);
                        }
                        if (e) {
                            c2 = G().c(fmVar.q, "_lte");
                            evVar = (c2 != null || c2.e == null) ? new ev(fmVar.q, "auto", "_lte", j().a(), Long.valueOf(j3)) : new ev(fmVar.q, "auto", "_lte", j().a(), Long.valueOf(((Long) c2.e).longValue() + j3));
                            foVar = new fo();
                            foVar.d = "_lte";
                            foVar.c = Long.valueOf(j().a());
                            foVar.f = (Long) evVar.e;
                            obj = null;
                            for (i5 = 0; i5 < fmVar.e.length; i5++) {
                                if (!"_lte".equals(fmVar.e[i5].d)) {
                                    fmVar.e[i5] = foVar;
                                    obj = 1;
                                    break;
                                }
                            }
                            if (obj == null) {
                                fmVar.e = (fo[]) Arrays.copyOf(fmVar.e, fmVar.e.length + 1);
                                fmVar.e[esVar.a.e.length - 1] = foVar;
                            }
                            if (j3 > 0) {
                                G().a(evVar);
                                q().B().a("Updated lifetime engagement user property with value. Value", evVar.e);
                            }
                        }
                        fmVar.C = a(fmVar.q, fmVar.e, fmVar.d);
                        if (c().d(esVar.a.q)) {
                            hashMap = new HashMap();
                            fjVarArr = new fj[fmVar.d.length];
                            w = n().w();
                            fjVarArr2 = fmVar.d;
                            length2 = fjVarArr2.length;
                            i6 = 0;
                            while (true) {
                                i7 = i6;
                                if (i7 < length2) {
                                    break;
                                }
                                fjVar2 = fjVarArr2[i7];
                                if (fjVar2.d.equals("_ep")) {
                                    n();
                                    str2 = (String) ew.b(fjVar2, "_en");
                                    ahVar = (ah) hashMap.get(str2);
                                    if (ahVar == null) {
                                        ahVar = G().a(esVar.a.q, str2);
                                        hashMap.put(str2, ahVar);
                                    }
                                    if (ahVar.g == null) {
                                        if (ahVar.h.longValue() > 1) {
                                            n();
                                            fjVar2.c = ew.a(fjVar2.c, "_sr", ahVar.h);
                                        }
                                        if (ahVar.i != null && ahVar.i.booleanValue()) {
                                            n();
                                            fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                        }
                                        i6 = 0 + 1;
                                        fjVarArr[0] = fjVar2;
                                    }
                                    i6 = 0;
                                } else {
                                    obj4 = "_dbg";
                                    valueOf = Long.valueOf(1);
                                    if (!TextUtils.isEmpty(obj4) || valueOf == null) {
                                        obj2 = null;
                                    } else {
                                        fk[] fkVarArr3 = fjVar2.c;
                                        int length3 = fkVarArr3.length;
                                        i5 = 0;
                                        while (i5 < length3) {
                                            fk fkVar5 = fkVarArr3[i5];
                                            if (obj4.equals(fkVar5.c)) {
                                                obj2 = (((valueOf instanceof Long) && valueOf.equals(fkVar5.e)) || (((valueOf instanceof String) && valueOf.equals(fkVar5.d)) || ((valueOf instanceof Double) && valueOf.equals(fkVar5.f)))) ? 1 : null;
                                            } else {
                                                i5++;
                                            }
                                        }
                                        obj2 = null;
                                    }
                                    d = obj2 == null ? a().d(esVar.a.q, fjVar2.d) : 1;
                                    if (d <= 0) {
                                        q().y().a("Sample rate must be positive. event, rate", fjVar2.d, Integer.valueOf(d));
                                        i6 = 0 + 1;
                                        fjVarArr[0] = fjVar2;
                                    } else {
                                        ahVar2 = (ah) hashMap.get(fjVar2.d);
                                        if (ahVar2 == null) {
                                            ahVar = G().a(esVar.a.q, fjVar2.d);
                                            if (ahVar == null) {
                                                q().y().a("Event being bundled has no eventAggregate. appId, eventName", esVar.a.q, fjVar2.d);
                                                ahVar = new ah(esVar.a.q, fjVar2.d, 1, 1, fjVar2.e.longValue(), 0, null, null, null);
                                            }
                                        } else {
                                            ahVar = ahVar2;
                                        }
                                        n();
                                        l = (Long) ew.b(fjVar2, "_eid");
                                        valueOf2 = Boolean.valueOf(l != null);
                                        if (d != 1) {
                                            i6 = 0 + 1;
                                            fjVarArr[0] = fjVar2;
                                            if (valueOf2.booleanValue() && !(ahVar.g == null && ahVar.h == null && ahVar.i == null)) {
                                                hashMap.put(fjVar2.d, ahVar.a(null, null, null));
                                            }
                                        } else if (w.nextInt(d) == 0) {
                                            n();
                                            fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                            i6 = 0 + 1;
                                            fjVarArr[0] = fjVar2;
                                            if (valueOf2.booleanValue()) {
                                                ahVar = ahVar.a(null, Long.valueOf((long) d), null);
                                            }
                                            hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                        } else {
                                            if (Math.abs(fjVar2.e.longValue() - ahVar.f) >= 86400000) {
                                                n();
                                                fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                                n();
                                                fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                                i6 = 0 + 1;
                                                fjVarArr[0] = fjVar2;
                                                if (valueOf2.booleanValue()) {
                                                    ahVar = ahVar.a(null, Long.valueOf((long) d), Boolean.valueOf(true));
                                                }
                                                hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                            } else {
                                                if (valueOf2.booleanValue()) {
                                                    hashMap.put(fjVar2.d, ahVar.a(l, null, null));
                                                }
                                                i6 = 0;
                                            }
                                        }
                                    }
                                }
                                i7++;
                            }
                            if (0 < fmVar.d.length) {
                                fmVar.d = (fj[]) Arrays.copyOf(fjVarArr, 0);
                            }
                            for (Entry value : hashMap.entrySet()) {
                                G().a((ah) value.getValue());
                            }
                        }
                        fmVar.g = Long.valueOf(Long.MAX_VALUE);
                        fmVar.h = Long.valueOf(Long.MIN_VALUE);
                        for (fj fjVar3 : fmVar.d) {
                            if (fjVar3.e.longValue() < fmVar.g.longValue()) {
                                fmVar.g = fjVar3.e;
                            }
                            if (fjVar3.e.longValue() <= fmVar.h.longValue()) {
                                fmVar.h = fjVar3.e;
                            }
                        }
                        str3 = esVar.a.q;
                        b = G().b(str3);
                        if (b != null) {
                            q().v().a("Bundling raw events w/o app info. appId", av.a(esVar.a.q));
                        } else if (fmVar.d.length > 0) {
                            j4 = b.h();
                            fmVar.j = j4 != 0 ? Long.valueOf(j4) : null;
                            j5 = b.g();
                            if (j5 != 0) {
                                j4 = j5;
                            }
                            fmVar.i = j4 != 0 ? Long.valueOf(j4) : null;
                            b.r();
                            fmVar.y = Integer.valueOf((int) b.o());
                            b.a(fmVar.g.longValue());
                            b.b(fmVar.h.longValue());
                            fmVar.z = b.z();
                            G().a(b);
                        }
                        if (fmVar.d.length > 0) {
                            a = a().a(esVar.a.q);
                            if (a == null && a.c != null) {
                                fmVar.G = a.c;
                            } else if (TextUtils.isEmpty(esVar.a.A)) {
                                fmVar.G = Long.valueOf(-1);
                            } else {
                                q().y().a("Did not find measurement config or missing version info. appId", av.a(esVar.a.q));
                            }
                            G().a(fmVar, z);
                        }
                        G2 = G();
                        obj3 = esVar.b;
                        ab.a(obj3);
                        G2.c();
                        G2.N();
                        stringBuilder = new StringBuilder("rowid in (");
                        i6 = 0;
                        while (true) {
                            i5 = i6;
                            if (i5 < obj3.size()) {
                                break;
                            }
                            if (i5 == 0) {
                                stringBuilder.append(",");
                            }
                            stringBuilder.append(((Long) obj3.get(i5)).longValue());
                            i6 = i5 + 1;
                        }
                        stringBuilder.append(")");
                        i6 = G2.x().delete("raw_events", stringBuilder.toString(), null);
                        if (i6 != obj3.size()) {
                            G2.q().v().a("Deleted fewer rows from raw events table than expected", Integer.valueOf(i6), Integer.valueOf(obj3.size()));
                        }
                        G3 = G();
                        try {
                            G3.x().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                        } catch (SQLiteException e2) {
                            G3.q().v().a("Failed to remove unused event metadata. appId", av.a(str3), e2);
                        }
                        G().v();
                        G().w();
                        return true;
                    }
                    G().v();
                    G().w();
                    return false;
                }
            }
            strArr = j2 != -1 ? new String[]{null, String.valueOf(j2)} : new String[]{null};
            str4 = j2 != -1 ? " and rowid <= ?" : "";
            cursor = x.rawQuery(new StringBuilder(String.valueOf(str4).length() + 84).append("select metadata_fingerprint from raw_events where app_id = ?").append(str4).append(" order by rowid limit 1;").toString(), strArr);
            if (cursor.moveToFirst()) {
                str4 = cursor.getString(0);
                cursor.close();
                str5 = str4;
                cursor2 = cursor;
                str6 = null;
            } else {
                if (cursor != null) {
                    cursor.close();
                }
                if (esVar.c != null) {
                }
                if (obj == null) {
                    G().v();
                    G().w();
                    return false;
                }
                z = false;
                fmVar = esVar.a;
                fmVar.d = new fj[esVar.c.size()];
                i = 0;
                j3 = 0;
                e = c().e(fmVar.q);
                i2 = 0;
                while (i2 < esVar.c.size()) {
                    fjVar = (fj) esVar.c.get(i2);
                    if (a().b(esVar.a.q, fjVar.d)) {
                        c = a().c(esVar.a.q, fjVar.d);
                        if (c) {
                            n();
                        }
                        obj3 = null;
                        obj4 = null;
                        if (fjVar.c == null) {
                            fjVar.c = new fk[0];
                        }
                        fkVarArr = fjVar.c;
                        length = fkVarArr.length;
                        i4 = 0;
                        while (i4 < length) {
                            fkVar = fkVarArr[i4];
                            if (!"_c".equals(fkVar.c)) {
                                fkVar.e = Long.valueOf(1);
                                obj5 = obj4;
                                obj4 = 1;
                                obj2 = obj5;
                            } else if ("_r".equals(fkVar.c)) {
                                obj2 = obj4;
                                obj4 = obj3;
                            } else {
                                fkVar.e = Long.valueOf(1);
                                obj2 = 1;
                                obj4 = obj3;
                            }
                            i4++;
                            obj3 = obj4;
                            obj4 = obj2;
                        }
                        q().C().a("Marking event as conversion", o().a(fjVar.d));
                        fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                        fkVar2 = new fk();
                        fkVar2.c = "_c";
                        fkVar2.e = Long.valueOf(1);
                        fkVarArr2[fkVarArr2.length - 1] = fkVar2;
                        fjVar.c = fkVarArr2;
                        if (obj4 == null) {
                            q().C().a("Marking event as real-time", o().a(fjVar.d));
                            fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                            fkVar3 = new fk();
                            fkVar3.c = "_r";
                            fkVar3.e = Long.valueOf(1);
                            fkVarArr2[fkVarArr2.length - 1] = fkVar3;
                            fjVar.c = fkVarArr2;
                        }
                        if (G().a(g(), esVar.a.q, false, false, false, false, true).e > ((long) c().a(esVar.a.q))) {
                            z = true;
                        } else {
                            i5 = 0;
                            while (i5 < fjVar.c.length) {
                                if ("_r".equals(fjVar.c[i5].c)) {
                                    i5++;
                                } else {
                                    obj4 = new fk[(fjVar.c.length - 1)];
                                    if (i5 > 0) {
                                        System.arraycopy(fjVar.c, 0, obj4, 0, i5);
                                    }
                                    if (i5 < obj4.length) {
                                        System.arraycopy(fjVar.c, i5 + 1, obj4, i5, obj4.length - i5);
                                    }
                                    fjVar.c = obj4;
                                }
                            }
                        }
                        q().y().a("Too many conversions. Not logging as conversion. appId", av.a(esVar.a.q));
                        obj3 = null;
                        fkVar3 = null;
                        fkVarArr = fjVar.c;
                        length = fkVarArr.length;
                        i4 = 0;
                        while (i4 < length) {
                            fkVar = fkVarArr[i4];
                            if (!"_c".equals(fkVar.c)) {
                                obj4 = obj3;
                            } else if ("_err".equals(fkVar.c)) {
                                fkVar = fkVar3;
                                obj4 = obj3;
                            } else {
                                fkVar4 = fkVar3;
                                i3 = 1;
                                fkVar = fkVar4;
                            }
                            i4++;
                            obj3 = obj4;
                            fkVar3 = fkVar;
                        }
                        if (obj3 == null) {
                        }
                        if (fkVar3 != null) {
                            q().v().a("Did not find conversion parameter. appId", av.a(esVar.a.q));
                            z3 = z;
                            if (e) {
                            }
                            j5 = j3;
                            i5 = i + 1;
                            fmVar.d[i] = fjVar;
                            j6 = j5;
                            i3 = i5;
                            z2 = z3;
                            j4 = j6;
                        } else {
                            fkVar3.c = "_err";
                            fkVar3.e = Long.valueOf(10);
                            z3 = z;
                            if (e) {
                            }
                            j5 = j3;
                            i5 = i + 1;
                            fmVar.d[i] = fjVar;
                            j6 = j5;
                            i3 = i5;
                            z2 = z3;
                            j4 = j6;
                        }
                    } else {
                        q().y().a("Dropping blacklisted raw event. appId", av.a(esVar.a.q), o().a(fjVar.d));
                        if (!a().e(esVar.a.q)) {
                        }
                        if (obj2 == null) {
                        }
                        j4 = j3;
                        i3 = i;
                        z2 = z;
                    }
                    i2++;
                    j3 = j4;
                    i = i3;
                    z = z2;
                }
                if (i < esVar.c.size()) {
                    fmVar.d = (fj[]) Arrays.copyOf(fmVar.d, i);
                }
                if (e) {
                    c2 = G().c(fmVar.q, "_lte");
                    if (c2 != null) {
                    }
                    foVar = new fo();
                    foVar.d = "_lte";
                    foVar.c = Long.valueOf(j().a());
                    foVar.f = (Long) evVar.e;
                    obj = null;
                    for (i5 = 0; i5 < fmVar.e.length; i5++) {
                        if (!"_lte".equals(fmVar.e[i5].d)) {
                            fmVar.e[i5] = foVar;
                            obj = 1;
                            break;
                        }
                    }
                    if (obj == null) {
                        fmVar.e = (fo[]) Arrays.copyOf(fmVar.e, fmVar.e.length + 1);
                        fmVar.e[esVar.a.e.length - 1] = foVar;
                    }
                    if (j3 > 0) {
                        G().a(evVar);
                        q().B().a("Updated lifetime engagement user property with value. Value", evVar.e);
                    }
                }
                fmVar.C = a(fmVar.q, fmVar.e, fmVar.d);
                if (c().d(esVar.a.q)) {
                    hashMap = new HashMap();
                    fjVarArr = new fj[fmVar.d.length];
                    w = n().w();
                    fjVarArr2 = fmVar.d;
                    length2 = fjVarArr2.length;
                    i6 = 0;
                    while (true) {
                        i7 = i6;
                        if (i7 < length2) {
                            break;
                        }
                        fjVar2 = fjVarArr2[i7];
                        if (fjVar2.d.equals("_ep")) {
                            obj4 = "_dbg";
                            valueOf = Long.valueOf(1);
                            if (TextUtils.isEmpty(obj4)) {
                            }
                            obj2 = null;
                            if (obj2 == null) {
                            }
                            if (d <= 0) {
                                ahVar2 = (ah) hashMap.get(fjVar2.d);
                                if (ahVar2 == null) {
                                    ahVar = ahVar2;
                                } else {
                                    ahVar = G().a(esVar.a.q, fjVar2.d);
                                    if (ahVar == null) {
                                        q().y().a("Event being bundled has no eventAggregate. appId, eventName", esVar.a.q, fjVar2.d);
                                        ahVar = new ah(esVar.a.q, fjVar2.d, 1, 1, fjVar2.e.longValue(), 0, null, null, null);
                                    }
                                }
                                n();
                                l = (Long) ew.b(fjVar2, "_eid");
                                if (l != null) {
                                }
                                valueOf2 = Boolean.valueOf(l != null);
                                if (d != 1) {
                                    i6 = 0 + 1;
                                    fjVarArr[0] = fjVar2;
                                    hashMap.put(fjVar2.d, ahVar.a(null, null, null));
                                } else if (w.nextInt(d) == 0) {
                                    if (Math.abs(fjVar2.e.longValue() - ahVar.f) >= 86400000) {
                                        if (valueOf2.booleanValue()) {
                                            hashMap.put(fjVar2.d, ahVar.a(l, null, null));
                                        }
                                        i6 = 0;
                                    } else {
                                        n();
                                        fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                        n();
                                        fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                        i6 = 0 + 1;
                                        fjVarArr[0] = fjVar2;
                                        if (valueOf2.booleanValue()) {
                                            ahVar = ahVar.a(null, Long.valueOf((long) d), Boolean.valueOf(true));
                                        }
                                        hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                    }
                                } else {
                                    n();
                                    fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                    i6 = 0 + 1;
                                    fjVarArr[0] = fjVar2;
                                    if (valueOf2.booleanValue()) {
                                        ahVar = ahVar.a(null, Long.valueOf((long) d), null);
                                    }
                                    hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                }
                            } else {
                                q().y().a("Sample rate must be positive. event, rate", fjVar2.d, Integer.valueOf(d));
                                i6 = 0 + 1;
                                fjVarArr[0] = fjVar2;
                            }
                        } else {
                            n();
                            str2 = (String) ew.b(fjVar2, "_en");
                            ahVar = (ah) hashMap.get(str2);
                            if (ahVar == null) {
                                ahVar = G().a(esVar.a.q, str2);
                                hashMap.put(str2, ahVar);
                            }
                            if (ahVar.g == null) {
                                if (ahVar.h.longValue() > 1) {
                                    n();
                                    fjVar2.c = ew.a(fjVar2.c, "_sr", ahVar.h);
                                }
                                n();
                                fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                i6 = 0 + 1;
                                fjVarArr[0] = fjVar2;
                            }
                            i6 = 0;
                        }
                        i7++;
                    }
                    if (0 < fmVar.d.length) {
                        fmVar.d = (fj[]) Arrays.copyOf(fjVarArr, 0);
                    }
                    while (r3.hasNext()) {
                        G().a((ah) value.getValue());
                    }
                }
                fmVar.g = Long.valueOf(Long.MAX_VALUE);
                fmVar.h = Long.valueOf(Long.MIN_VALUE);
                for (fj fjVar32 : fmVar.d) {
                    if (fjVar32.e.longValue() < fmVar.g.longValue()) {
                        fmVar.g = fjVar32.e;
                    }
                    if (fjVar32.e.longValue() <= fmVar.h.longValue()) {
                        fmVar.h = fjVar32.e;
                    }
                }
                str3 = esVar.a.q;
                b = G().b(str3);
                if (b != null) {
                    q().v().a("Bundling raw events w/o app info. appId", av.a(esVar.a.q));
                } else if (fmVar.d.length > 0) {
                    j4 = b.h();
                    if (j4 != 0) {
                    }
                    fmVar.j = j4 != 0 ? Long.valueOf(j4) : null;
                    j5 = b.g();
                    if (j5 != 0) {
                        j4 = j5;
                    }
                    if (j4 != 0) {
                    }
                    fmVar.i = j4 != 0 ? Long.valueOf(j4) : null;
                    b.r();
                    fmVar.y = Integer.valueOf((int) b.o());
                    b.a(fmVar.g.longValue());
                    b.b(fmVar.h.longValue());
                    fmVar.z = b.z();
                    G().a(b);
                }
                if (fmVar.d.length > 0) {
                    a = a().a(esVar.a.q);
                    if (a == null) {
                    }
                    if (TextUtils.isEmpty(esVar.a.A)) {
                        q().y().a("Did not find measurement config or missing version info. appId", av.a(esVar.a.q));
                    } else {
                        fmVar.G = Long.valueOf(-1);
                    }
                    G().a(fmVar, z);
                }
                G2 = G();
                obj3 = esVar.b;
                ab.a(obj3);
                G2.c();
                G2.N();
                stringBuilder = new StringBuilder("rowid in (");
                i6 = 0;
                while (true) {
                    i5 = i6;
                    if (i5 < obj3.size()) {
                        break;
                    }
                    if (i5 == 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(((Long) obj3.get(i5)).longValue());
                    i6 = i5 + 1;
                }
                stringBuilder.append(")");
                i6 = G2.x().delete("raw_events", stringBuilder.toString(), null);
                if (i6 != obj3.size()) {
                    G2.q().v().a("Deleted fewer rows from raw events table than expected", Integer.valueOf(i6), Integer.valueOf(obj3.size()));
                }
                G3 = G();
                G3.x().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                G().v();
                G().w();
                return true;
            }
            try {
                cursor2 = x.query("raw_events_metadata", new String[]{"metadata"}, "app_id = ? and metadata_fingerprint = ?", new String[]{str6, str5}, null, null, "rowid", "2");
                if (cursor2.moveToFirst()) {
                    byte[] blob = cursor2.getBlob(0);
                    a a2 = a.a(blob, blob.length);
                    j fmVar2 = new fm();
                    try {
                        fmVar2.a(a2);
                        if (cursor2.moveToNext()) {
                            G.q().y().a("Get multiple raw event metadata records, expected one. appId", av.a(str6));
                        }
                        cursor2.close();
                        esVar.a(fmVar2);
                        if (j2 != -1) {
                            str4 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
                            strArr = new String[]{str6, str5, String.valueOf(j2)};
                        } else {
                            str4 = "app_id = ? and metadata_fingerprint = ?";
                            strArr = new String[]{str6, str5};
                        }
                        cursor = x.query("raw_events", new String[]{"rowid", "name", "timestamp", "data"}, str4, strArr, null, null, "rowid", null);
                        if (cursor.moveToFirst()) {
                            do {
                                j5 = cursor.getLong(0);
                                byte[] blob2 = cursor.getBlob(3);
                                a a3 = a.a(blob2, blob2.length);
                                j fjVar4 = new fj();
                                try {
                                    fjVar4.a(a3);
                                    try {
                                        fjVar4.d = cursor.getString(1);
                                        fjVar4.e = Long.valueOf(cursor.getLong(2));
                                        if (!esVar.a(j5, fjVar4)) {
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                            if (esVar.c != null) {
                                            }
                                            if (obj == null) {
                                                z = false;
                                                fmVar = esVar.a;
                                                fmVar.d = new fj[esVar.c.size()];
                                                i = 0;
                                                j3 = 0;
                                                e = c().e(fmVar.q);
                                                i2 = 0;
                                                while (i2 < esVar.c.size()) {
                                                    fjVar = (fj) esVar.c.get(i2);
                                                    if (a().b(esVar.a.q, fjVar.d)) {
                                                        q().y().a("Dropping blacklisted raw event. appId", av.a(esVar.a.q), o().a(fjVar.d));
                                                        if (a().e(esVar.a.q)) {
                                                        }
                                                        if (obj2 == null) {
                                                        }
                                                        j4 = j3;
                                                        i3 = i;
                                                        z2 = z;
                                                    } else {
                                                        c = a().c(esVar.a.q, fjVar.d);
                                                        if (c) {
                                                            n();
                                                        }
                                                        obj3 = null;
                                                        obj4 = null;
                                                        if (fjVar.c == null) {
                                                            fjVar.c = new fk[0];
                                                        }
                                                        fkVarArr = fjVar.c;
                                                        length = fkVarArr.length;
                                                        i4 = 0;
                                                        while (i4 < length) {
                                                            fkVar = fkVarArr[i4];
                                                            if (!"_c".equals(fkVar.c)) {
                                                                fkVar.e = Long.valueOf(1);
                                                                obj5 = obj4;
                                                                obj4 = 1;
                                                                obj2 = obj5;
                                                            } else if ("_r".equals(fkVar.c)) {
                                                                fkVar.e = Long.valueOf(1);
                                                                obj2 = 1;
                                                                obj4 = obj3;
                                                            } else {
                                                                obj2 = obj4;
                                                                obj4 = obj3;
                                                            }
                                                            i4++;
                                                            obj3 = obj4;
                                                            obj4 = obj2;
                                                        }
                                                        q().C().a("Marking event as conversion", o().a(fjVar.d));
                                                        fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                                                        fkVar2 = new fk();
                                                        fkVar2.c = "_c";
                                                        fkVar2.e = Long.valueOf(1);
                                                        fkVarArr2[fkVarArr2.length - 1] = fkVar2;
                                                        fjVar.c = fkVarArr2;
                                                        if (obj4 == null) {
                                                            q().C().a("Marking event as real-time", o().a(fjVar.d));
                                                            fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                                                            fkVar3 = new fk();
                                                            fkVar3.c = "_r";
                                                            fkVar3.e = Long.valueOf(1);
                                                            fkVarArr2[fkVarArr2.length - 1] = fkVar3;
                                                            fjVar.c = fkVarArr2;
                                                        }
                                                        if (G().a(g(), esVar.a.q, false, false, false, false, true).e > ((long) c().a(esVar.a.q))) {
                                                            i5 = 0;
                                                            while (i5 < fjVar.c.length) {
                                                                if ("_r".equals(fjVar.c[i5].c)) {
                                                                    obj4 = new fk[(fjVar.c.length - 1)];
                                                                    if (i5 > 0) {
                                                                        System.arraycopy(fjVar.c, 0, obj4, 0, i5);
                                                                    }
                                                                    if (i5 < obj4.length) {
                                                                        System.arraycopy(fjVar.c, i5 + 1, obj4, i5, obj4.length - i5);
                                                                    }
                                                                    fjVar.c = obj4;
                                                                } else {
                                                                    i5++;
                                                                }
                                                            }
                                                        } else {
                                                            z = true;
                                                        }
                                                        q().y().a("Too many conversions. Not logging as conversion. appId", av.a(esVar.a.q));
                                                        obj3 = null;
                                                        fkVar3 = null;
                                                        fkVarArr = fjVar.c;
                                                        length = fkVarArr.length;
                                                        i4 = 0;
                                                        while (i4 < length) {
                                                            fkVar = fkVarArr[i4];
                                                            if (!"_c".equals(fkVar.c)) {
                                                                obj4 = obj3;
                                                            } else if ("_err".equals(fkVar.c)) {
                                                                fkVar4 = fkVar3;
                                                                i3 = 1;
                                                                fkVar = fkVar4;
                                                            } else {
                                                                fkVar = fkVar3;
                                                                obj4 = obj3;
                                                            }
                                                            i4++;
                                                            obj3 = obj4;
                                                            fkVar3 = fkVar;
                                                        }
                                                        if (obj3 == null) {
                                                        }
                                                        if (fkVar3 != null) {
                                                            fkVar3.c = "_err";
                                                            fkVar3.e = Long.valueOf(10);
                                                            z3 = z;
                                                            if (e) {
                                                            }
                                                            j5 = j3;
                                                            i5 = i + 1;
                                                            fmVar.d[i] = fjVar;
                                                            j6 = j5;
                                                            i3 = i5;
                                                            z2 = z3;
                                                            j4 = j6;
                                                        } else {
                                                            q().v().a("Did not find conversion parameter. appId", av.a(esVar.a.q));
                                                            z3 = z;
                                                            if (e) {
                                                            }
                                                            j5 = j3;
                                                            i5 = i + 1;
                                                            fmVar.d[i] = fjVar;
                                                            j6 = j5;
                                                            i3 = i5;
                                                            z2 = z3;
                                                            j4 = j6;
                                                        }
                                                    }
                                                    i2++;
                                                    j3 = j4;
                                                    i = i3;
                                                    z = z2;
                                                }
                                                if (i < esVar.c.size()) {
                                                    fmVar.d = (fj[]) Arrays.copyOf(fmVar.d, i);
                                                }
                                                if (e) {
                                                    c2 = G().c(fmVar.q, "_lte");
                                                    if (c2 != null) {
                                                    }
                                                    foVar = new fo();
                                                    foVar.d = "_lte";
                                                    foVar.c = Long.valueOf(j().a());
                                                    foVar.f = (Long) evVar.e;
                                                    obj = null;
                                                    for (i5 = 0; i5 < fmVar.e.length; i5++) {
                                                        if (!"_lte".equals(fmVar.e[i5].d)) {
                                                            fmVar.e[i5] = foVar;
                                                            obj = 1;
                                                            break;
                                                        }
                                                    }
                                                    if (obj == null) {
                                                        fmVar.e = (fo[]) Arrays.copyOf(fmVar.e, fmVar.e.length + 1);
                                                        fmVar.e[esVar.a.e.length - 1] = foVar;
                                                    }
                                                    if (j3 > 0) {
                                                        G().a(evVar);
                                                        q().B().a("Updated lifetime engagement user property with value. Value", evVar.e);
                                                    }
                                                }
                                                fmVar.C = a(fmVar.q, fmVar.e, fmVar.d);
                                                if (c().d(esVar.a.q)) {
                                                    hashMap = new HashMap();
                                                    fjVarArr = new fj[fmVar.d.length];
                                                    w = n().w();
                                                    fjVarArr2 = fmVar.d;
                                                    length2 = fjVarArr2.length;
                                                    i6 = 0;
                                                    while (true) {
                                                        i7 = i6;
                                                        if (i7 < length2) {
                                                            break;
                                                        }
                                                        fjVar2 = fjVarArr2[i7];
                                                        if (fjVar2.d.equals("_ep")) {
                                                            n();
                                                            str2 = (String) ew.b(fjVar2, "_en");
                                                            ahVar = (ah) hashMap.get(str2);
                                                            if (ahVar == null) {
                                                                ahVar = G().a(esVar.a.q, str2);
                                                                hashMap.put(str2, ahVar);
                                                            }
                                                            if (ahVar.g == null) {
                                                                if (ahVar.h.longValue() > 1) {
                                                                    n();
                                                                    fjVar2.c = ew.a(fjVar2.c, "_sr", ahVar.h);
                                                                }
                                                                n();
                                                                fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                                                i6 = 0 + 1;
                                                                fjVarArr[0] = fjVar2;
                                                            }
                                                            i6 = 0;
                                                        } else {
                                                            obj4 = "_dbg";
                                                            valueOf = Long.valueOf(1);
                                                            if (TextUtils.isEmpty(obj4)) {
                                                            }
                                                            obj2 = null;
                                                            if (obj2 == null) {
                                                            }
                                                            if (d <= 0) {
                                                                q().y().a("Sample rate must be positive. event, rate", fjVar2.d, Integer.valueOf(d));
                                                                i6 = 0 + 1;
                                                                fjVarArr[0] = fjVar2;
                                                            } else {
                                                                ahVar2 = (ah) hashMap.get(fjVar2.d);
                                                                if (ahVar2 == null) {
                                                                    ahVar = G().a(esVar.a.q, fjVar2.d);
                                                                    if (ahVar == null) {
                                                                        q().y().a("Event being bundled has no eventAggregate. appId, eventName", esVar.a.q, fjVar2.d);
                                                                        ahVar = new ah(esVar.a.q, fjVar2.d, 1, 1, fjVar2.e.longValue(), 0, null, null, null);
                                                                    }
                                                                } else {
                                                                    ahVar = ahVar2;
                                                                }
                                                                n();
                                                                l = (Long) ew.b(fjVar2, "_eid");
                                                                if (l != null) {
                                                                }
                                                                valueOf2 = Boolean.valueOf(l != null);
                                                                if (d != 1) {
                                                                    i6 = 0 + 1;
                                                                    fjVarArr[0] = fjVar2;
                                                                    hashMap.put(fjVar2.d, ahVar.a(null, null, null));
                                                                } else if (w.nextInt(d) == 0) {
                                                                    n();
                                                                    fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                                                    i6 = 0 + 1;
                                                                    fjVarArr[0] = fjVar2;
                                                                    if (valueOf2.booleanValue()) {
                                                                        ahVar = ahVar.a(null, Long.valueOf((long) d), null);
                                                                    }
                                                                    hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                                                } else {
                                                                    if (Math.abs(fjVar2.e.longValue() - ahVar.f) >= 86400000) {
                                                                        n();
                                                                        fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                                                        n();
                                                                        fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                                                        i6 = 0 + 1;
                                                                        fjVarArr[0] = fjVar2;
                                                                        if (valueOf2.booleanValue()) {
                                                                            ahVar = ahVar.a(null, Long.valueOf((long) d), Boolean.valueOf(true));
                                                                        }
                                                                        hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                                                    } else {
                                                                        if (valueOf2.booleanValue()) {
                                                                            hashMap.put(fjVar2.d, ahVar.a(l, null, null));
                                                                        }
                                                                        i6 = 0;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        i7++;
                                                    }
                                                    if (0 < fmVar.d.length) {
                                                        fmVar.d = (fj[]) Arrays.copyOf(fjVarArr, 0);
                                                    }
                                                    while (r3.hasNext()) {
                                                        G().a((ah) value.getValue());
                                                    }
                                                }
                                                fmVar.g = Long.valueOf(Long.MAX_VALUE);
                                                fmVar.h = Long.valueOf(Long.MIN_VALUE);
                                                for (fj fjVar322 : fmVar.d) {
                                                    if (fjVar322.e.longValue() < fmVar.g.longValue()) {
                                                        fmVar.g = fjVar322.e;
                                                    }
                                                    if (fjVar322.e.longValue() <= fmVar.h.longValue()) {
                                                        fmVar.h = fjVar322.e;
                                                    }
                                                }
                                                str3 = esVar.a.q;
                                                b = G().b(str3);
                                                if (b != null) {
                                                    q().v().a("Bundling raw events w/o app info. appId", av.a(esVar.a.q));
                                                } else if (fmVar.d.length > 0) {
                                                    j4 = b.h();
                                                    if (j4 != 0) {
                                                    }
                                                    fmVar.j = j4 != 0 ? Long.valueOf(j4) : null;
                                                    j5 = b.g();
                                                    if (j5 != 0) {
                                                        j4 = j5;
                                                    }
                                                    if (j4 != 0) {
                                                    }
                                                    fmVar.i = j4 != 0 ? Long.valueOf(j4) : null;
                                                    b.r();
                                                    fmVar.y = Integer.valueOf((int) b.o());
                                                    b.a(fmVar.g.longValue());
                                                    b.b(fmVar.h.longValue());
                                                    fmVar.z = b.z();
                                                    G().a(b);
                                                }
                                                if (fmVar.d.length > 0) {
                                                    a = a().a(esVar.a.q);
                                                    if (a == null) {
                                                    }
                                                    if (TextUtils.isEmpty(esVar.a.A)) {
                                                        fmVar.G = Long.valueOf(-1);
                                                    } else {
                                                        q().y().a("Did not find measurement config or missing version info. appId", av.a(esVar.a.q));
                                                    }
                                                    G().a(fmVar, z);
                                                }
                                                G2 = G();
                                                obj3 = esVar.b;
                                                ab.a(obj3);
                                                G2.c();
                                                G2.N();
                                                stringBuilder = new StringBuilder("rowid in (");
                                                i6 = 0;
                                                while (true) {
                                                    i5 = i6;
                                                    if (i5 < obj3.size()) {
                                                        break;
                                                    }
                                                    if (i5 == 0) {
                                                        stringBuilder.append(",");
                                                    }
                                                    stringBuilder.append(((Long) obj3.get(i5)).longValue());
                                                    i6 = i5 + 1;
                                                }
                                                stringBuilder.append(")");
                                                i6 = G2.x().delete("raw_events", stringBuilder.toString(), null);
                                                if (i6 != obj3.size()) {
                                                    G2.q().v().a("Deleted fewer rows from raw events table than expected", Integer.valueOf(i6), Integer.valueOf(obj3.size()));
                                                }
                                                G3 = G();
                                                G3.x().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                                                G().v();
                                                G().w();
                                                return true;
                                            }
                                            G().v();
                                            G().w();
                                            return false;
                                        }
                                    } catch (SQLiteException e3) {
                                        obj = e3;
                                        str = str6;
                                    }
                                } catch (IOException e4) {
                                    G.q().v().a("Data loss. Failed to merge raw event. appId", av.a(str6), e4);
                                }
                            } while (cursor.moveToNext());
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (esVar.c != null) {
                            }
                            if (obj == null) {
                                G().v();
                                G().w();
                                return false;
                            }
                            z = false;
                            fmVar = esVar.a;
                            fmVar.d = new fj[esVar.c.size()];
                            i = 0;
                            j3 = 0;
                            e = c().e(fmVar.q);
                            i2 = 0;
                            while (i2 < esVar.c.size()) {
                                fjVar = (fj) esVar.c.get(i2);
                                if (a().b(esVar.a.q, fjVar.d)) {
                                    c = a().c(esVar.a.q, fjVar.d);
                                    if (c) {
                                        n();
                                    }
                                    obj3 = null;
                                    obj4 = null;
                                    if (fjVar.c == null) {
                                        fjVar.c = new fk[0];
                                    }
                                    fkVarArr = fjVar.c;
                                    length = fkVarArr.length;
                                    i4 = 0;
                                    while (i4 < length) {
                                        fkVar = fkVarArr[i4];
                                        if (!"_c".equals(fkVar.c)) {
                                            fkVar.e = Long.valueOf(1);
                                            obj5 = obj4;
                                            obj4 = 1;
                                            obj2 = obj5;
                                        } else if ("_r".equals(fkVar.c)) {
                                            obj2 = obj4;
                                            obj4 = obj3;
                                        } else {
                                            fkVar.e = Long.valueOf(1);
                                            obj2 = 1;
                                            obj4 = obj3;
                                        }
                                        i4++;
                                        obj3 = obj4;
                                        obj4 = obj2;
                                    }
                                    q().C().a("Marking event as conversion", o().a(fjVar.d));
                                    fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                                    fkVar2 = new fk();
                                    fkVar2.c = "_c";
                                    fkVar2.e = Long.valueOf(1);
                                    fkVarArr2[fkVarArr2.length - 1] = fkVar2;
                                    fjVar.c = fkVarArr2;
                                    if (obj4 == null) {
                                        q().C().a("Marking event as real-time", o().a(fjVar.d));
                                        fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                                        fkVar3 = new fk();
                                        fkVar3.c = "_r";
                                        fkVar3.e = Long.valueOf(1);
                                        fkVarArr2[fkVarArr2.length - 1] = fkVar3;
                                        fjVar.c = fkVarArr2;
                                    }
                                    if (G().a(g(), esVar.a.q, false, false, false, false, true).e > ((long) c().a(esVar.a.q))) {
                                        z = true;
                                    } else {
                                        i5 = 0;
                                        while (i5 < fjVar.c.length) {
                                            if ("_r".equals(fjVar.c[i5].c)) {
                                                i5++;
                                            } else {
                                                obj4 = new fk[(fjVar.c.length - 1)];
                                                if (i5 > 0) {
                                                    System.arraycopy(fjVar.c, 0, obj4, 0, i5);
                                                }
                                                if (i5 < obj4.length) {
                                                    System.arraycopy(fjVar.c, i5 + 1, obj4, i5, obj4.length - i5);
                                                }
                                                fjVar.c = obj4;
                                            }
                                        }
                                    }
                                    q().y().a("Too many conversions. Not logging as conversion. appId", av.a(esVar.a.q));
                                    obj3 = null;
                                    fkVar3 = null;
                                    fkVarArr = fjVar.c;
                                    length = fkVarArr.length;
                                    i4 = 0;
                                    while (i4 < length) {
                                        fkVar = fkVarArr[i4];
                                        if (!"_c".equals(fkVar.c)) {
                                            obj4 = obj3;
                                        } else if ("_err".equals(fkVar.c)) {
                                            fkVar = fkVar3;
                                            obj4 = obj3;
                                        } else {
                                            fkVar4 = fkVar3;
                                            i3 = 1;
                                            fkVar = fkVar4;
                                        }
                                        i4++;
                                        obj3 = obj4;
                                        fkVar3 = fkVar;
                                    }
                                    if (obj3 == null) {
                                    }
                                    if (fkVar3 != null) {
                                        q().v().a("Did not find conversion parameter. appId", av.a(esVar.a.q));
                                        z3 = z;
                                        if (e) {
                                        }
                                        j5 = j3;
                                        i5 = i + 1;
                                        fmVar.d[i] = fjVar;
                                        j6 = j5;
                                        i3 = i5;
                                        z2 = z3;
                                        j4 = j6;
                                    } else {
                                        fkVar3.c = "_err";
                                        fkVar3.e = Long.valueOf(10);
                                        z3 = z;
                                        if (e) {
                                        }
                                        j5 = j3;
                                        i5 = i + 1;
                                        fmVar.d[i] = fjVar;
                                        j6 = j5;
                                        i3 = i5;
                                        z2 = z3;
                                        j4 = j6;
                                    }
                                } else {
                                    q().y().a("Dropping blacklisted raw event. appId", av.a(esVar.a.q), o().a(fjVar.d));
                                    if (a().e(esVar.a.q)) {
                                    }
                                    if (obj2 == null) {
                                    }
                                    j4 = j3;
                                    i3 = i;
                                    z2 = z;
                                }
                                i2++;
                                j3 = j4;
                                i = i3;
                                z = z2;
                            }
                            if (i < esVar.c.size()) {
                                fmVar.d = (fj[]) Arrays.copyOf(fmVar.d, i);
                            }
                            if (e) {
                                c2 = G().c(fmVar.q, "_lte");
                                if (c2 != null) {
                                }
                                foVar = new fo();
                                foVar.d = "_lte";
                                foVar.c = Long.valueOf(j().a());
                                foVar.f = (Long) evVar.e;
                                obj = null;
                                for (i5 = 0; i5 < fmVar.e.length; i5++) {
                                    if (!"_lte".equals(fmVar.e[i5].d)) {
                                        fmVar.e[i5] = foVar;
                                        obj = 1;
                                        break;
                                    }
                                }
                                if (obj == null) {
                                    fmVar.e = (fo[]) Arrays.copyOf(fmVar.e, fmVar.e.length + 1);
                                    fmVar.e[esVar.a.e.length - 1] = foVar;
                                }
                                if (j3 > 0) {
                                    G().a(evVar);
                                    q().B().a("Updated lifetime engagement user property with value. Value", evVar.e);
                                }
                            }
                            fmVar.C = a(fmVar.q, fmVar.e, fmVar.d);
                            if (c().d(esVar.a.q)) {
                                hashMap = new HashMap();
                                fjVarArr = new fj[fmVar.d.length];
                                w = n().w();
                                fjVarArr2 = fmVar.d;
                                length2 = fjVarArr2.length;
                                i6 = 0;
                                while (true) {
                                    i7 = i6;
                                    if (i7 < length2) {
                                        break;
                                    }
                                    fjVar2 = fjVarArr2[i7];
                                    if (fjVar2.d.equals("_ep")) {
                                        obj4 = "_dbg";
                                        valueOf = Long.valueOf(1);
                                        if (TextUtils.isEmpty(obj4)) {
                                        }
                                        obj2 = null;
                                        if (obj2 == null) {
                                        }
                                        if (d <= 0) {
                                            ahVar2 = (ah) hashMap.get(fjVar2.d);
                                            if (ahVar2 == null) {
                                                ahVar = ahVar2;
                                            } else {
                                                ahVar = G().a(esVar.a.q, fjVar2.d);
                                                if (ahVar == null) {
                                                    q().y().a("Event being bundled has no eventAggregate. appId, eventName", esVar.a.q, fjVar2.d);
                                                    ahVar = new ah(esVar.a.q, fjVar2.d, 1, 1, fjVar2.e.longValue(), 0, null, null, null);
                                                }
                                            }
                                            n();
                                            l = (Long) ew.b(fjVar2, "_eid");
                                            if (l != null) {
                                            }
                                            valueOf2 = Boolean.valueOf(l != null);
                                            if (d != 1) {
                                                i6 = 0 + 1;
                                                fjVarArr[0] = fjVar2;
                                                hashMap.put(fjVar2.d, ahVar.a(null, null, null));
                                            } else if (w.nextInt(d) == 0) {
                                                if (Math.abs(fjVar2.e.longValue() - ahVar.f) >= 86400000) {
                                                    if (valueOf2.booleanValue()) {
                                                        hashMap.put(fjVar2.d, ahVar.a(l, null, null));
                                                    }
                                                    i6 = 0;
                                                } else {
                                                    n();
                                                    fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                                    n();
                                                    fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                                    i6 = 0 + 1;
                                                    fjVarArr[0] = fjVar2;
                                                    if (valueOf2.booleanValue()) {
                                                        ahVar = ahVar.a(null, Long.valueOf((long) d), Boolean.valueOf(true));
                                                    }
                                                    hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                                }
                                            } else {
                                                n();
                                                fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                                i6 = 0 + 1;
                                                fjVarArr[0] = fjVar2;
                                                if (valueOf2.booleanValue()) {
                                                    ahVar = ahVar.a(null, Long.valueOf((long) d), null);
                                                }
                                                hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                            }
                                        } else {
                                            q().y().a("Sample rate must be positive. event, rate", fjVar2.d, Integer.valueOf(d));
                                            i6 = 0 + 1;
                                            fjVarArr[0] = fjVar2;
                                        }
                                    } else {
                                        n();
                                        str2 = (String) ew.b(fjVar2, "_en");
                                        ahVar = (ah) hashMap.get(str2);
                                        if (ahVar == null) {
                                            ahVar = G().a(esVar.a.q, str2);
                                            hashMap.put(str2, ahVar);
                                        }
                                        if (ahVar.g == null) {
                                            if (ahVar.h.longValue() > 1) {
                                                n();
                                                fjVar2.c = ew.a(fjVar2.c, "_sr", ahVar.h);
                                            }
                                            n();
                                            fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                            i6 = 0 + 1;
                                            fjVarArr[0] = fjVar2;
                                        }
                                        i6 = 0;
                                    }
                                    i7++;
                                }
                                if (0 < fmVar.d.length) {
                                    fmVar.d = (fj[]) Arrays.copyOf(fjVarArr, 0);
                                }
                                while (r3.hasNext()) {
                                    G().a((ah) value.getValue());
                                }
                            }
                            fmVar.g = Long.valueOf(Long.MAX_VALUE);
                            fmVar.h = Long.valueOf(Long.MIN_VALUE);
                            for (fj fjVar3222 : fmVar.d) {
                                if (fjVar3222.e.longValue() < fmVar.g.longValue()) {
                                    fmVar.g = fjVar3222.e;
                                }
                                if (fjVar3222.e.longValue() <= fmVar.h.longValue()) {
                                    fmVar.h = fjVar3222.e;
                                }
                            }
                            str3 = esVar.a.q;
                            b = G().b(str3);
                            if (b != null) {
                                q().v().a("Bundling raw events w/o app info. appId", av.a(esVar.a.q));
                            } else if (fmVar.d.length > 0) {
                                j4 = b.h();
                                if (j4 != 0) {
                                }
                                fmVar.j = j4 != 0 ? Long.valueOf(j4) : null;
                                j5 = b.g();
                                if (j5 != 0) {
                                    j4 = j5;
                                }
                                if (j4 != 0) {
                                }
                                fmVar.i = j4 != 0 ? Long.valueOf(j4) : null;
                                b.r();
                                fmVar.y = Integer.valueOf((int) b.o());
                                b.a(fmVar.g.longValue());
                                b.b(fmVar.h.longValue());
                                fmVar.z = b.z();
                                G().a(b);
                            }
                            if (fmVar.d.length > 0) {
                                a = a().a(esVar.a.q);
                                if (a == null) {
                                }
                                if (TextUtils.isEmpty(esVar.a.A)) {
                                    q().y().a("Did not find measurement config or missing version info. appId", av.a(esVar.a.q));
                                } else {
                                    fmVar.G = Long.valueOf(-1);
                                }
                                G().a(fmVar, z);
                            }
                            G2 = G();
                            obj3 = esVar.b;
                            ab.a(obj3);
                            G2.c();
                            G2.N();
                            stringBuilder = new StringBuilder("rowid in (");
                            i6 = 0;
                            while (true) {
                                i5 = i6;
                                if (i5 < obj3.size()) {
                                    break;
                                }
                                if (i5 == 0) {
                                    stringBuilder.append(",");
                                }
                                stringBuilder.append(((Long) obj3.get(i5)).longValue());
                                i6 = i5 + 1;
                            }
                            stringBuilder.append(")");
                            i6 = G2.x().delete("raw_events", stringBuilder.toString(), null);
                            if (i6 != obj3.size()) {
                                G2.q().v().a("Deleted fewer rows from raw events table than expected", Integer.valueOf(i6), Integer.valueOf(obj3.size()));
                            }
                            G3 = G();
                            G3.x().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                            G().v();
                            G().w();
                            return true;
                        }
                        G.q().y().a("Raw event data disappeared while in transaction. appId", av.a(str6));
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (esVar.c != null) {
                        }
                        if (obj == null) {
                            G().v();
                            G().w();
                            return false;
                        }
                        z = false;
                        fmVar = esVar.a;
                        fmVar.d = new fj[esVar.c.size()];
                        i = 0;
                        j3 = 0;
                        e = c().e(fmVar.q);
                        i2 = 0;
                        while (i2 < esVar.c.size()) {
                            fjVar = (fj) esVar.c.get(i2);
                            if (a().b(esVar.a.q, fjVar.d)) {
                                c = a().c(esVar.a.q, fjVar.d);
                                if (c) {
                                    n();
                                }
                                obj3 = null;
                                obj4 = null;
                                if (fjVar.c == null) {
                                    fjVar.c = new fk[0];
                                }
                                fkVarArr = fjVar.c;
                                length = fkVarArr.length;
                                i4 = 0;
                                while (i4 < length) {
                                    fkVar = fkVarArr[i4];
                                    if (!"_c".equals(fkVar.c)) {
                                        fkVar.e = Long.valueOf(1);
                                        obj5 = obj4;
                                        obj4 = 1;
                                        obj2 = obj5;
                                    } else if ("_r".equals(fkVar.c)) {
                                        obj2 = obj4;
                                        obj4 = obj3;
                                    } else {
                                        fkVar.e = Long.valueOf(1);
                                        obj2 = 1;
                                        obj4 = obj3;
                                    }
                                    i4++;
                                    obj3 = obj4;
                                    obj4 = obj2;
                                }
                                q().C().a("Marking event as conversion", o().a(fjVar.d));
                                fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                                fkVar2 = new fk();
                                fkVar2.c = "_c";
                                fkVar2.e = Long.valueOf(1);
                                fkVarArr2[fkVarArr2.length - 1] = fkVar2;
                                fjVar.c = fkVarArr2;
                                if (obj4 == null) {
                                    q().C().a("Marking event as real-time", o().a(fjVar.d));
                                    fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                                    fkVar3 = new fk();
                                    fkVar3.c = "_r";
                                    fkVar3.e = Long.valueOf(1);
                                    fkVarArr2[fkVarArr2.length - 1] = fkVar3;
                                    fjVar.c = fkVarArr2;
                                }
                                if (G().a(g(), esVar.a.q, false, false, false, false, true).e > ((long) c().a(esVar.a.q))) {
                                    z = true;
                                } else {
                                    i5 = 0;
                                    while (i5 < fjVar.c.length) {
                                        if ("_r".equals(fjVar.c[i5].c)) {
                                            i5++;
                                        } else {
                                            obj4 = new fk[(fjVar.c.length - 1)];
                                            if (i5 > 0) {
                                                System.arraycopy(fjVar.c, 0, obj4, 0, i5);
                                            }
                                            if (i5 < obj4.length) {
                                                System.arraycopy(fjVar.c, i5 + 1, obj4, i5, obj4.length - i5);
                                            }
                                            fjVar.c = obj4;
                                        }
                                    }
                                }
                                q().y().a("Too many conversions. Not logging as conversion. appId", av.a(esVar.a.q));
                                obj3 = null;
                                fkVar3 = null;
                                fkVarArr = fjVar.c;
                                length = fkVarArr.length;
                                i4 = 0;
                                while (i4 < length) {
                                    fkVar = fkVarArr[i4];
                                    if (!"_c".equals(fkVar.c)) {
                                        obj4 = obj3;
                                    } else if ("_err".equals(fkVar.c)) {
                                        fkVar = fkVar3;
                                        obj4 = obj3;
                                    } else {
                                        fkVar4 = fkVar3;
                                        i3 = 1;
                                        fkVar = fkVar4;
                                    }
                                    i4++;
                                    obj3 = obj4;
                                    fkVar3 = fkVar;
                                }
                                if (obj3 == null) {
                                }
                                if (fkVar3 != null) {
                                    q().v().a("Did not find conversion parameter. appId", av.a(esVar.a.q));
                                    z3 = z;
                                    if (e) {
                                    }
                                    j5 = j3;
                                    i5 = i + 1;
                                    fmVar.d[i] = fjVar;
                                    j6 = j5;
                                    i3 = i5;
                                    z2 = z3;
                                    j4 = j6;
                                } else {
                                    fkVar3.c = "_err";
                                    fkVar3.e = Long.valueOf(10);
                                    z3 = z;
                                    if (e) {
                                    }
                                    j5 = j3;
                                    i5 = i + 1;
                                    fmVar.d[i] = fjVar;
                                    j6 = j5;
                                    i3 = i5;
                                    z2 = z3;
                                    j4 = j6;
                                }
                            } else {
                                q().y().a("Dropping blacklisted raw event. appId", av.a(esVar.a.q), o().a(fjVar.d));
                                if (a().e(esVar.a.q)) {
                                }
                                if (obj2 == null) {
                                }
                                j4 = j3;
                                i3 = i;
                                z2 = z;
                            }
                            i2++;
                            j3 = j4;
                            i = i3;
                            z = z2;
                        }
                        if (i < esVar.c.size()) {
                            fmVar.d = (fj[]) Arrays.copyOf(fmVar.d, i);
                        }
                        if (e) {
                            c2 = G().c(fmVar.q, "_lte");
                            if (c2 != null) {
                            }
                            foVar = new fo();
                            foVar.d = "_lte";
                            foVar.c = Long.valueOf(j().a());
                            foVar.f = (Long) evVar.e;
                            obj = null;
                            for (i5 = 0; i5 < fmVar.e.length; i5++) {
                                if (!"_lte".equals(fmVar.e[i5].d)) {
                                    fmVar.e[i5] = foVar;
                                    obj = 1;
                                    break;
                                }
                            }
                            if (obj == null) {
                                fmVar.e = (fo[]) Arrays.copyOf(fmVar.e, fmVar.e.length + 1);
                                fmVar.e[esVar.a.e.length - 1] = foVar;
                            }
                            if (j3 > 0) {
                                G().a(evVar);
                                q().B().a("Updated lifetime engagement user property with value. Value", evVar.e);
                            }
                        }
                        fmVar.C = a(fmVar.q, fmVar.e, fmVar.d);
                        if (c().d(esVar.a.q)) {
                            hashMap = new HashMap();
                            fjVarArr = new fj[fmVar.d.length];
                            w = n().w();
                            fjVarArr2 = fmVar.d;
                            length2 = fjVarArr2.length;
                            i6 = 0;
                            while (true) {
                                i7 = i6;
                                if (i7 < length2) {
                                    break;
                                }
                                fjVar2 = fjVarArr2[i7];
                                if (fjVar2.d.equals("_ep")) {
                                    obj4 = "_dbg";
                                    valueOf = Long.valueOf(1);
                                    if (TextUtils.isEmpty(obj4)) {
                                    }
                                    obj2 = null;
                                    if (obj2 == null) {
                                    }
                                    if (d <= 0) {
                                        ahVar2 = (ah) hashMap.get(fjVar2.d);
                                        if (ahVar2 == null) {
                                            ahVar = ahVar2;
                                        } else {
                                            ahVar = G().a(esVar.a.q, fjVar2.d);
                                            if (ahVar == null) {
                                                q().y().a("Event being bundled has no eventAggregate. appId, eventName", esVar.a.q, fjVar2.d);
                                                ahVar = new ah(esVar.a.q, fjVar2.d, 1, 1, fjVar2.e.longValue(), 0, null, null, null);
                                            }
                                        }
                                        n();
                                        l = (Long) ew.b(fjVar2, "_eid");
                                        if (l != null) {
                                        }
                                        valueOf2 = Boolean.valueOf(l != null);
                                        if (d != 1) {
                                            i6 = 0 + 1;
                                            fjVarArr[0] = fjVar2;
                                            hashMap.put(fjVar2.d, ahVar.a(null, null, null));
                                        } else if (w.nextInt(d) == 0) {
                                            if (Math.abs(fjVar2.e.longValue() - ahVar.f) >= 86400000) {
                                                if (valueOf2.booleanValue()) {
                                                    hashMap.put(fjVar2.d, ahVar.a(l, null, null));
                                                }
                                                i6 = 0;
                                            } else {
                                                n();
                                                fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                                n();
                                                fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                                i6 = 0 + 1;
                                                fjVarArr[0] = fjVar2;
                                                if (valueOf2.booleanValue()) {
                                                    ahVar = ahVar.a(null, Long.valueOf((long) d), Boolean.valueOf(true));
                                                }
                                                hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                            }
                                        } else {
                                            n();
                                            fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                            i6 = 0 + 1;
                                            fjVarArr[0] = fjVar2;
                                            if (valueOf2.booleanValue()) {
                                                ahVar = ahVar.a(null, Long.valueOf((long) d), null);
                                            }
                                            hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                        }
                                    } else {
                                        q().y().a("Sample rate must be positive. event, rate", fjVar2.d, Integer.valueOf(d));
                                        i6 = 0 + 1;
                                        fjVarArr[0] = fjVar2;
                                    }
                                } else {
                                    n();
                                    str2 = (String) ew.b(fjVar2, "_en");
                                    ahVar = (ah) hashMap.get(str2);
                                    if (ahVar == null) {
                                        ahVar = G().a(esVar.a.q, str2);
                                        hashMap.put(str2, ahVar);
                                    }
                                    if (ahVar.g == null) {
                                        if (ahVar.h.longValue() > 1) {
                                            n();
                                            fjVar2.c = ew.a(fjVar2.c, "_sr", ahVar.h);
                                        }
                                        n();
                                        fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                        i6 = 0 + 1;
                                        fjVarArr[0] = fjVar2;
                                    }
                                    i6 = 0;
                                }
                                i7++;
                            }
                            if (0 < fmVar.d.length) {
                                fmVar.d = (fj[]) Arrays.copyOf(fjVarArr, 0);
                            }
                            while (r3.hasNext()) {
                                G().a((ah) value.getValue());
                            }
                        }
                        fmVar.g = Long.valueOf(Long.MAX_VALUE);
                        fmVar.h = Long.valueOf(Long.MIN_VALUE);
                        for (fj fjVar32222 : fmVar.d) {
                            if (fjVar32222.e.longValue() < fmVar.g.longValue()) {
                                fmVar.g = fjVar32222.e;
                            }
                            if (fjVar32222.e.longValue() <= fmVar.h.longValue()) {
                                fmVar.h = fjVar32222.e;
                            }
                        }
                        str3 = esVar.a.q;
                        b = G().b(str3);
                        if (b != null) {
                            q().v().a("Bundling raw events w/o app info. appId", av.a(esVar.a.q));
                        } else if (fmVar.d.length > 0) {
                            j4 = b.h();
                            if (j4 != 0) {
                            }
                            fmVar.j = j4 != 0 ? Long.valueOf(j4) : null;
                            j5 = b.g();
                            if (j5 != 0) {
                                j4 = j5;
                            }
                            if (j4 != 0) {
                            }
                            fmVar.i = j4 != 0 ? Long.valueOf(j4) : null;
                            b.r();
                            fmVar.y = Integer.valueOf((int) b.o());
                            b.a(fmVar.g.longValue());
                            b.b(fmVar.h.longValue());
                            fmVar.z = b.z();
                            G().a(b);
                        }
                        if (fmVar.d.length > 0) {
                            a = a().a(esVar.a.q);
                            if (a == null) {
                            }
                            if (TextUtils.isEmpty(esVar.a.A)) {
                                q().y().a("Did not find measurement config or missing version info. appId", av.a(esVar.a.q));
                            } else {
                                fmVar.G = Long.valueOf(-1);
                            }
                            G().a(fmVar, z);
                        }
                        G2 = G();
                        obj3 = esVar.b;
                        ab.a(obj3);
                        G2.c();
                        G2.N();
                        stringBuilder = new StringBuilder("rowid in (");
                        i6 = 0;
                        while (true) {
                            i5 = i6;
                            if (i5 < obj3.size()) {
                                break;
                            }
                            if (i5 == 0) {
                                stringBuilder.append(",");
                            }
                            stringBuilder.append(((Long) obj3.get(i5)).longValue());
                            i6 = i5 + 1;
                        }
                        stringBuilder.append(")");
                        i6 = G2.x().delete("raw_events", stringBuilder.toString(), null);
                        if (i6 != obj3.size()) {
                            G2.q().v().a("Deleted fewer rows from raw events table than expected", Integer.valueOf(i6), Integer.valueOf(obj3.size()));
                        }
                        G3 = G();
                        G3.x().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                        G().v();
                        G().w();
                        return true;
                    } catch (IOException e42) {
                        G.q().v().a("Data loss. Failed to merge raw event metadata. appId", av.a(str6), e42);
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                    } catch (Throwable th2) {
                        G().w();
                    }
                } else {
                    G.q().v().a("Raw event metadata record is missing. appId", av.a(str6));
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (esVar.c != null) {
                    }
                    if (obj == null) {
                        z = false;
                        fmVar = esVar.a;
                        fmVar.d = new fj[esVar.c.size()];
                        i = 0;
                        j3 = 0;
                        e = c().e(fmVar.q);
                        i2 = 0;
                        while (i2 < esVar.c.size()) {
                            fjVar = (fj) esVar.c.get(i2);
                            if (a().b(esVar.a.q, fjVar.d)) {
                                q().y().a("Dropping blacklisted raw event. appId", av.a(esVar.a.q), o().a(fjVar.d));
                                if (a().e(esVar.a.q)) {
                                }
                                if (obj2 == null) {
                                }
                                j4 = j3;
                                i3 = i;
                                z2 = z;
                            } else {
                                c = a().c(esVar.a.q, fjVar.d);
                                if (c) {
                                    n();
                                }
                                obj3 = null;
                                obj4 = null;
                                if (fjVar.c == null) {
                                    fjVar.c = new fk[0];
                                }
                                fkVarArr = fjVar.c;
                                length = fkVarArr.length;
                                i4 = 0;
                                while (i4 < length) {
                                    fkVar = fkVarArr[i4];
                                    if (!"_c".equals(fkVar.c)) {
                                        fkVar.e = Long.valueOf(1);
                                        obj5 = obj4;
                                        obj4 = 1;
                                        obj2 = obj5;
                                    } else if ("_r".equals(fkVar.c)) {
                                        fkVar.e = Long.valueOf(1);
                                        obj2 = 1;
                                        obj4 = obj3;
                                    } else {
                                        obj2 = obj4;
                                        obj4 = obj3;
                                    }
                                    i4++;
                                    obj3 = obj4;
                                    obj4 = obj2;
                                }
                                q().C().a("Marking event as conversion", o().a(fjVar.d));
                                fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                                fkVar2 = new fk();
                                fkVar2.c = "_c";
                                fkVar2.e = Long.valueOf(1);
                                fkVarArr2[fkVarArr2.length - 1] = fkVar2;
                                fjVar.c = fkVarArr2;
                                if (obj4 == null) {
                                    q().C().a("Marking event as real-time", o().a(fjVar.d));
                                    fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                                    fkVar3 = new fk();
                                    fkVar3.c = "_r";
                                    fkVar3.e = Long.valueOf(1);
                                    fkVarArr2[fkVarArr2.length - 1] = fkVar3;
                                    fjVar.c = fkVarArr2;
                                }
                                if (G().a(g(), esVar.a.q, false, false, false, false, true).e > ((long) c().a(esVar.a.q))) {
                                    i5 = 0;
                                    while (i5 < fjVar.c.length) {
                                        if ("_r".equals(fjVar.c[i5].c)) {
                                            obj4 = new fk[(fjVar.c.length - 1)];
                                            if (i5 > 0) {
                                                System.arraycopy(fjVar.c, 0, obj4, 0, i5);
                                            }
                                            if (i5 < obj4.length) {
                                                System.arraycopy(fjVar.c, i5 + 1, obj4, i5, obj4.length - i5);
                                            }
                                            fjVar.c = obj4;
                                        } else {
                                            i5++;
                                        }
                                    }
                                } else {
                                    z = true;
                                }
                                q().y().a("Too many conversions. Not logging as conversion. appId", av.a(esVar.a.q));
                                obj3 = null;
                                fkVar3 = null;
                                fkVarArr = fjVar.c;
                                length = fkVarArr.length;
                                i4 = 0;
                                while (i4 < length) {
                                    fkVar = fkVarArr[i4];
                                    if (!"_c".equals(fkVar.c)) {
                                        obj4 = obj3;
                                    } else if ("_err".equals(fkVar.c)) {
                                        fkVar4 = fkVar3;
                                        i3 = 1;
                                        fkVar = fkVar4;
                                    } else {
                                        fkVar = fkVar3;
                                        obj4 = obj3;
                                    }
                                    i4++;
                                    obj3 = obj4;
                                    fkVar3 = fkVar;
                                }
                                if (obj3 == null) {
                                }
                                if (fkVar3 != null) {
                                    fkVar3.c = "_err";
                                    fkVar3.e = Long.valueOf(10);
                                    z3 = z;
                                    if (e) {
                                    }
                                    j5 = j3;
                                    i5 = i + 1;
                                    fmVar.d[i] = fjVar;
                                    j6 = j5;
                                    i3 = i5;
                                    z2 = z3;
                                    j4 = j6;
                                } else {
                                    q().v().a("Did not find conversion parameter. appId", av.a(esVar.a.q));
                                    z3 = z;
                                    if (e) {
                                    }
                                    j5 = j3;
                                    i5 = i + 1;
                                    fmVar.d[i] = fjVar;
                                    j6 = j5;
                                    i3 = i5;
                                    z2 = z3;
                                    j4 = j6;
                                }
                            }
                            i2++;
                            j3 = j4;
                            i = i3;
                            z = z2;
                        }
                        if (i < esVar.c.size()) {
                            fmVar.d = (fj[]) Arrays.copyOf(fmVar.d, i);
                        }
                        if (e) {
                            c2 = G().c(fmVar.q, "_lte");
                            if (c2 != null) {
                            }
                            foVar = new fo();
                            foVar.d = "_lte";
                            foVar.c = Long.valueOf(j().a());
                            foVar.f = (Long) evVar.e;
                            obj = null;
                            for (i5 = 0; i5 < fmVar.e.length; i5++) {
                                if (!"_lte".equals(fmVar.e[i5].d)) {
                                    fmVar.e[i5] = foVar;
                                    obj = 1;
                                    break;
                                }
                            }
                            if (obj == null) {
                                fmVar.e = (fo[]) Arrays.copyOf(fmVar.e, fmVar.e.length + 1);
                                fmVar.e[esVar.a.e.length - 1] = foVar;
                            }
                            if (j3 > 0) {
                                G().a(evVar);
                                q().B().a("Updated lifetime engagement user property with value. Value", evVar.e);
                            }
                        }
                        fmVar.C = a(fmVar.q, fmVar.e, fmVar.d);
                        if (c().d(esVar.a.q)) {
                            hashMap = new HashMap();
                            fjVarArr = new fj[fmVar.d.length];
                            w = n().w();
                            fjVarArr2 = fmVar.d;
                            length2 = fjVarArr2.length;
                            i6 = 0;
                            while (true) {
                                i7 = i6;
                                if (i7 < length2) {
                                    break;
                                }
                                fjVar2 = fjVarArr2[i7];
                                if (fjVar2.d.equals("_ep")) {
                                    n();
                                    str2 = (String) ew.b(fjVar2, "_en");
                                    ahVar = (ah) hashMap.get(str2);
                                    if (ahVar == null) {
                                        ahVar = G().a(esVar.a.q, str2);
                                        hashMap.put(str2, ahVar);
                                    }
                                    if (ahVar.g == null) {
                                        if (ahVar.h.longValue() > 1) {
                                            n();
                                            fjVar2.c = ew.a(fjVar2.c, "_sr", ahVar.h);
                                        }
                                        n();
                                        fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                        i6 = 0 + 1;
                                        fjVarArr[0] = fjVar2;
                                    }
                                    i6 = 0;
                                } else {
                                    obj4 = "_dbg";
                                    valueOf = Long.valueOf(1);
                                    if (TextUtils.isEmpty(obj4)) {
                                    }
                                    obj2 = null;
                                    if (obj2 == null) {
                                    }
                                    if (d <= 0) {
                                        q().y().a("Sample rate must be positive. event, rate", fjVar2.d, Integer.valueOf(d));
                                        i6 = 0 + 1;
                                        fjVarArr[0] = fjVar2;
                                    } else {
                                        ahVar2 = (ah) hashMap.get(fjVar2.d);
                                        if (ahVar2 == null) {
                                            ahVar = G().a(esVar.a.q, fjVar2.d);
                                            if (ahVar == null) {
                                                q().y().a("Event being bundled has no eventAggregate. appId, eventName", esVar.a.q, fjVar2.d);
                                                ahVar = new ah(esVar.a.q, fjVar2.d, 1, 1, fjVar2.e.longValue(), 0, null, null, null);
                                            }
                                        } else {
                                            ahVar = ahVar2;
                                        }
                                        n();
                                        l = (Long) ew.b(fjVar2, "_eid");
                                        if (l != null) {
                                        }
                                        valueOf2 = Boolean.valueOf(l != null);
                                        if (d != 1) {
                                            i6 = 0 + 1;
                                            fjVarArr[0] = fjVar2;
                                            hashMap.put(fjVar2.d, ahVar.a(null, null, null));
                                        } else if (w.nextInt(d) == 0) {
                                            n();
                                            fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                            i6 = 0 + 1;
                                            fjVarArr[0] = fjVar2;
                                            if (valueOf2.booleanValue()) {
                                                ahVar = ahVar.a(null, Long.valueOf((long) d), null);
                                            }
                                            hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                        } else {
                                            if (Math.abs(fjVar2.e.longValue() - ahVar.f) >= 86400000) {
                                                n();
                                                fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                                n();
                                                fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                                i6 = 0 + 1;
                                                fjVarArr[0] = fjVar2;
                                                if (valueOf2.booleanValue()) {
                                                    ahVar = ahVar.a(null, Long.valueOf((long) d), Boolean.valueOf(true));
                                                }
                                                hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                            } else {
                                                if (valueOf2.booleanValue()) {
                                                    hashMap.put(fjVar2.d, ahVar.a(l, null, null));
                                                }
                                                i6 = 0;
                                            }
                                        }
                                    }
                                }
                                i7++;
                            }
                            if (0 < fmVar.d.length) {
                                fmVar.d = (fj[]) Arrays.copyOf(fjVarArr, 0);
                            }
                            while (r3.hasNext()) {
                                G().a((ah) value.getValue());
                            }
                        }
                        fmVar.g = Long.valueOf(Long.MAX_VALUE);
                        fmVar.h = Long.valueOf(Long.MIN_VALUE);
                        for (fj fjVar322222 : fmVar.d) {
                            if (fjVar322222.e.longValue() < fmVar.g.longValue()) {
                                fmVar.g = fjVar322222.e;
                            }
                            if (fjVar322222.e.longValue() <= fmVar.h.longValue()) {
                                fmVar.h = fjVar322222.e;
                            }
                        }
                        str3 = esVar.a.q;
                        b = G().b(str3);
                        if (b != null) {
                            q().v().a("Bundling raw events w/o app info. appId", av.a(esVar.a.q));
                        } else if (fmVar.d.length > 0) {
                            j4 = b.h();
                            if (j4 != 0) {
                            }
                            fmVar.j = j4 != 0 ? Long.valueOf(j4) : null;
                            j5 = b.g();
                            if (j5 != 0) {
                                j4 = j5;
                            }
                            if (j4 != 0) {
                            }
                            fmVar.i = j4 != 0 ? Long.valueOf(j4) : null;
                            b.r();
                            fmVar.y = Integer.valueOf((int) b.o());
                            b.a(fmVar.g.longValue());
                            b.b(fmVar.h.longValue());
                            fmVar.z = b.z();
                            G().a(b);
                        }
                        if (fmVar.d.length > 0) {
                            a = a().a(esVar.a.q);
                            if (a == null) {
                            }
                            if (TextUtils.isEmpty(esVar.a.A)) {
                                fmVar.G = Long.valueOf(-1);
                            } else {
                                q().y().a("Did not find measurement config or missing version info. appId", av.a(esVar.a.q));
                            }
                            G().a(fmVar, z);
                        }
                        G2 = G();
                        obj3 = esVar.b;
                        ab.a(obj3);
                        G2.c();
                        G2.N();
                        stringBuilder = new StringBuilder("rowid in (");
                        i6 = 0;
                        while (true) {
                            i5 = i6;
                            if (i5 < obj3.size()) {
                                break;
                            }
                            if (i5 == 0) {
                                stringBuilder.append(",");
                            }
                            stringBuilder.append(((Long) obj3.get(i5)).longValue());
                            i6 = i5 + 1;
                        }
                        stringBuilder.append(")");
                        i6 = G2.x().delete("raw_events", stringBuilder.toString(), null);
                        if (i6 != obj3.size()) {
                            G2.q().v().a("Deleted fewer rows from raw events table than expected", Integer.valueOf(i6), Integer.valueOf(obj3.size()));
                        }
                        G3 = G();
                        G3.x().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                        G().v();
                        G().w();
                        return true;
                    }
                    G().v();
                    G().w();
                    return false;
                }
            } catch (SQLiteException e5) {
                obj = e5;
                cursor = cursor2;
                str = str6;
            } catch (Throwable th3) {
                th = th3;
                cursor = cursor2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e6) {
            obj = e6;
            try {
                G.q().v().a("Data loss. Error selecting raw event. appId", av.a(str), obj);
                if (cursor != null) {
                    cursor.close();
                }
                if (esVar.c != null) {
                }
                if (obj == null) {
                    z = false;
                    fmVar = esVar.a;
                    fmVar.d = new fj[esVar.c.size()];
                    i = 0;
                    j3 = 0;
                    e = c().e(fmVar.q);
                    i2 = 0;
                    while (i2 < esVar.c.size()) {
                        fjVar = (fj) esVar.c.get(i2);
                        if (a().b(esVar.a.q, fjVar.d)) {
                            q().y().a("Dropping blacklisted raw event. appId", av.a(esVar.a.q), o().a(fjVar.d));
                            if (a().e(esVar.a.q)) {
                            }
                            if (obj2 == null) {
                            }
                            j4 = j3;
                            i3 = i;
                            z2 = z;
                        } else {
                            c = a().c(esVar.a.q, fjVar.d);
                            if (c) {
                                n();
                            }
                            obj3 = null;
                            obj4 = null;
                            if (fjVar.c == null) {
                                fjVar.c = new fk[0];
                            }
                            fkVarArr = fjVar.c;
                            length = fkVarArr.length;
                            i4 = 0;
                            while (i4 < length) {
                                fkVar = fkVarArr[i4];
                                if (!"_c".equals(fkVar.c)) {
                                    fkVar.e = Long.valueOf(1);
                                    obj5 = obj4;
                                    obj4 = 1;
                                    obj2 = obj5;
                                } else if ("_r".equals(fkVar.c)) {
                                    fkVar.e = Long.valueOf(1);
                                    obj2 = 1;
                                    obj4 = obj3;
                                } else {
                                    obj2 = obj4;
                                    obj4 = obj3;
                                }
                                i4++;
                                obj3 = obj4;
                                obj4 = obj2;
                            }
                            q().C().a("Marking event as conversion", o().a(fjVar.d));
                            fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                            fkVar2 = new fk();
                            fkVar2.c = "_c";
                            fkVar2.e = Long.valueOf(1);
                            fkVarArr2[fkVarArr2.length - 1] = fkVar2;
                            fjVar.c = fkVarArr2;
                            if (obj4 == null) {
                                q().C().a("Marking event as real-time", o().a(fjVar.d));
                                fkVarArr2 = (fk[]) Arrays.copyOf(fjVar.c, fjVar.c.length + 1);
                                fkVar3 = new fk();
                                fkVar3.c = "_r";
                                fkVar3.e = Long.valueOf(1);
                                fkVarArr2[fkVarArr2.length - 1] = fkVar3;
                                fjVar.c = fkVarArr2;
                            }
                            if (G().a(g(), esVar.a.q, false, false, false, false, true).e > ((long) c().a(esVar.a.q))) {
                                i5 = 0;
                                while (i5 < fjVar.c.length) {
                                    if ("_r".equals(fjVar.c[i5].c)) {
                                        obj4 = new fk[(fjVar.c.length - 1)];
                                        if (i5 > 0) {
                                            System.arraycopy(fjVar.c, 0, obj4, 0, i5);
                                        }
                                        if (i5 < obj4.length) {
                                            System.arraycopy(fjVar.c, i5 + 1, obj4, i5, obj4.length - i5);
                                        }
                                        fjVar.c = obj4;
                                    } else {
                                        i5++;
                                    }
                                }
                            } else {
                                z = true;
                            }
                            q().y().a("Too many conversions. Not logging as conversion. appId", av.a(esVar.a.q));
                            obj3 = null;
                            fkVar3 = null;
                            fkVarArr = fjVar.c;
                            length = fkVarArr.length;
                            i4 = 0;
                            while (i4 < length) {
                                fkVar = fkVarArr[i4];
                                if (!"_c".equals(fkVar.c)) {
                                    obj4 = obj3;
                                } else if ("_err".equals(fkVar.c)) {
                                    fkVar4 = fkVar3;
                                    i3 = 1;
                                    fkVar = fkVar4;
                                } else {
                                    fkVar = fkVar3;
                                    obj4 = obj3;
                                }
                                i4++;
                                obj3 = obj4;
                                fkVar3 = fkVar;
                            }
                            if (obj3 == null) {
                            }
                            if (fkVar3 != null) {
                                fkVar3.c = "_err";
                                fkVar3.e = Long.valueOf(10);
                                z3 = z;
                                if (e) {
                                }
                                j5 = j3;
                                i5 = i + 1;
                                fmVar.d[i] = fjVar;
                                j6 = j5;
                                i3 = i5;
                                z2 = z3;
                                j4 = j6;
                            } else {
                                q().v().a("Did not find conversion parameter. appId", av.a(esVar.a.q));
                                z3 = z;
                                if (e) {
                                }
                                j5 = j3;
                                i5 = i + 1;
                                fmVar.d[i] = fjVar;
                                j6 = j5;
                                i3 = i5;
                                z2 = z3;
                                j4 = j6;
                            }
                        }
                        i2++;
                        j3 = j4;
                        i = i3;
                        z = z2;
                    }
                    if (i < esVar.c.size()) {
                        fmVar.d = (fj[]) Arrays.copyOf(fmVar.d, i);
                    }
                    if (e) {
                        c2 = G().c(fmVar.q, "_lte");
                        if (c2 != null) {
                        }
                        foVar = new fo();
                        foVar.d = "_lte";
                        foVar.c = Long.valueOf(j().a());
                        foVar.f = (Long) evVar.e;
                        obj = null;
                        for (i5 = 0; i5 < fmVar.e.length; i5++) {
                            if (!"_lte".equals(fmVar.e[i5].d)) {
                                fmVar.e[i5] = foVar;
                                obj = 1;
                                break;
                            }
                        }
                        if (obj == null) {
                            fmVar.e = (fo[]) Arrays.copyOf(fmVar.e, fmVar.e.length + 1);
                            fmVar.e[esVar.a.e.length - 1] = foVar;
                        }
                        if (j3 > 0) {
                            G().a(evVar);
                            q().B().a("Updated lifetime engagement user property with value. Value", evVar.e);
                        }
                    }
                    fmVar.C = a(fmVar.q, fmVar.e, fmVar.d);
                    if (c().d(esVar.a.q)) {
                        hashMap = new HashMap();
                        fjVarArr = new fj[fmVar.d.length];
                        w = n().w();
                        fjVarArr2 = fmVar.d;
                        length2 = fjVarArr2.length;
                        i6 = 0;
                        while (true) {
                            i7 = i6;
                            if (i7 < length2) {
                                break;
                            }
                            fjVar2 = fjVarArr2[i7];
                            if (fjVar2.d.equals("_ep")) {
                                n();
                                str2 = (String) ew.b(fjVar2, "_en");
                                ahVar = (ah) hashMap.get(str2);
                                if (ahVar == null) {
                                    ahVar = G().a(esVar.a.q, str2);
                                    hashMap.put(str2, ahVar);
                                }
                                if (ahVar.g == null) {
                                    if (ahVar.h.longValue() > 1) {
                                        n();
                                        fjVar2.c = ew.a(fjVar2.c, "_sr", ahVar.h);
                                    }
                                    n();
                                    fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                    i6 = 0 + 1;
                                    fjVarArr[0] = fjVar2;
                                }
                                i6 = 0;
                            } else {
                                obj4 = "_dbg";
                                valueOf = Long.valueOf(1);
                                if (TextUtils.isEmpty(obj4)) {
                                }
                                obj2 = null;
                                if (obj2 == null) {
                                }
                                if (d <= 0) {
                                    q().y().a("Sample rate must be positive. event, rate", fjVar2.d, Integer.valueOf(d));
                                    i6 = 0 + 1;
                                    fjVarArr[0] = fjVar2;
                                } else {
                                    ahVar2 = (ah) hashMap.get(fjVar2.d);
                                    if (ahVar2 == null) {
                                        ahVar = G().a(esVar.a.q, fjVar2.d);
                                        if (ahVar == null) {
                                            q().y().a("Event being bundled has no eventAggregate. appId, eventName", esVar.a.q, fjVar2.d);
                                            ahVar = new ah(esVar.a.q, fjVar2.d, 1, 1, fjVar2.e.longValue(), 0, null, null, null);
                                        }
                                    } else {
                                        ahVar = ahVar2;
                                    }
                                    n();
                                    l = (Long) ew.b(fjVar2, "_eid");
                                    if (l != null) {
                                    }
                                    valueOf2 = Boolean.valueOf(l != null);
                                    if (d != 1) {
                                        i6 = 0 + 1;
                                        fjVarArr[0] = fjVar2;
                                        hashMap.put(fjVar2.d, ahVar.a(null, null, null));
                                    } else if (w.nextInt(d) == 0) {
                                        n();
                                        fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                        i6 = 0 + 1;
                                        fjVarArr[0] = fjVar2;
                                        if (valueOf2.booleanValue()) {
                                            ahVar = ahVar.a(null, Long.valueOf((long) d), null);
                                        }
                                        hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                    } else {
                                        if (Math.abs(fjVar2.e.longValue() - ahVar.f) >= 86400000) {
                                            n();
                                            fjVar2.c = ew.a(fjVar2.c, "_efs", Long.valueOf(1));
                                            n();
                                            fjVar2.c = ew.a(fjVar2.c, "_sr", Long.valueOf((long) d));
                                            i6 = 0 + 1;
                                            fjVarArr[0] = fjVar2;
                                            if (valueOf2.booleanValue()) {
                                                ahVar = ahVar.a(null, Long.valueOf((long) d), Boolean.valueOf(true));
                                            }
                                            hashMap.put(fjVar2.d, ahVar.b(fjVar2.e.longValue()));
                                        } else {
                                            if (valueOf2.booleanValue()) {
                                                hashMap.put(fjVar2.d, ahVar.a(l, null, null));
                                            }
                                            i6 = 0;
                                        }
                                    }
                                }
                            }
                            i7++;
                        }
                        if (0 < fmVar.d.length) {
                            fmVar.d = (fj[]) Arrays.copyOf(fjVarArr, 0);
                        }
                        while (r3.hasNext()) {
                            G().a((ah) value.getValue());
                        }
                    }
                    fmVar.g = Long.valueOf(Long.MAX_VALUE);
                    fmVar.h = Long.valueOf(Long.MIN_VALUE);
                    for (fj fjVar3222222 : fmVar.d) {
                        if (fjVar3222222.e.longValue() < fmVar.g.longValue()) {
                            fmVar.g = fjVar3222222.e;
                        }
                        if (fjVar3222222.e.longValue() <= fmVar.h.longValue()) {
                            fmVar.h = fjVar3222222.e;
                        }
                    }
                    str3 = esVar.a.q;
                    b = G().b(str3);
                    if (b != null) {
                        q().v().a("Bundling raw events w/o app info. appId", av.a(esVar.a.q));
                    } else if (fmVar.d.length > 0) {
                        j4 = b.h();
                        if (j4 != 0) {
                        }
                        fmVar.j = j4 != 0 ? Long.valueOf(j4) : null;
                        j5 = b.g();
                        if (j5 != 0) {
                            j4 = j5;
                        }
                        if (j4 != 0) {
                        }
                        fmVar.i = j4 != 0 ? Long.valueOf(j4) : null;
                        b.r();
                        fmVar.y = Integer.valueOf((int) b.o());
                        b.a(fmVar.g.longValue());
                        b.b(fmVar.h.longValue());
                        fmVar.z = b.z();
                        G().a(b);
                    }
                    if (fmVar.d.length > 0) {
                        a = a().a(esVar.a.q);
                        if (a == null) {
                        }
                        if (TextUtils.isEmpty(esVar.a.A)) {
                            fmVar.G = Long.valueOf(-1);
                        } else {
                            q().y().a("Did not find measurement config or missing version info. appId", av.a(esVar.a.q));
                        }
                        G().a(fmVar, z);
                    }
                    G2 = G();
                    obj3 = esVar.b;
                    ab.a(obj3);
                    G2.c();
                    G2.N();
                    stringBuilder = new StringBuilder("rowid in (");
                    i6 = 0;
                    while (true) {
                        i5 = i6;
                        if (i5 < obj3.size()) {
                            break;
                        }
                        if (i5 == 0) {
                            stringBuilder.append(",");
                        }
                        stringBuilder.append(((Long) obj3.get(i5)).longValue());
                        i6 = i5 + 1;
                    }
                    stringBuilder.append(")");
                    i6 = G2.x().delete("raw_events", stringBuilder.toString(), null);
                    if (i6 != obj3.size()) {
                        G2.q().v().a("Deleted fewer rows from raw events table than expected", Integer.valueOf(i6), Integer.valueOf(obj3.size()));
                    }
                    G3 = G();
                    G3.x().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str3, str3});
                    G().v();
                    G().w();
                    return true;
                }
                G().v();
                G().w();
                return false;
            } catch (Throwable th4) {
                th = th4;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
    }

    private final boolean a(String str, zzeu zzeu) {
        long round;
        Object d = zzeu.b.d("currency");
        if ("ecommerce_purchase".equals(zzeu.a)) {
            double doubleValue = zzeu.b.c(PropertiesEntry.COLUMN_NAME_VALUE).doubleValue() * 1000000.0d;
            if (doubleValue == 0.0d) {
                doubleValue = ((double) zzeu.b.b(PropertiesEntry.COLUMN_NAME_VALUE).longValue()) * 1000000.0d;
            }
            if (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d) {
                q().y().a("Data lost. Currency value is too big. appId", av.a(str), Double.valueOf(doubleValue));
                return false;
            }
            round = Math.round(doubleValue);
        } else {
            round = zzeu.b.b(PropertiesEntry.COLUMN_NAME_VALUE).longValue();
        }
        if (!TextUtils.isEmpty(d)) {
            String toUpperCase = d.toUpperCase(Locale.US);
            if (toUpperCase.matches("[A-Z]{3}")) {
                String valueOf = String.valueOf("_ltv_");
                toUpperCase = String.valueOf(toUpperCase);
                String concat = toUpperCase.length() != 0 ? valueOf.concat(toUpperCase) : new String(valueOf);
                ev c = G().c(str, concat);
                if (c == null || !(c.e instanceof Long)) {
                    cs G = G();
                    int b = c().b(str, al.F) - 1;
                    ab.a(str);
                    G.c();
                    G.N();
                    try {
                        G.x().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(b)});
                    } catch (SQLiteException e) {
                        G.q().v().a("Error pruning currencies. appId", av.a(str), e);
                    }
                    c = new ev(str, zzeu.c, concat, j().a(), Long.valueOf(round));
                } else {
                    c = new ev(str, zzeu.c, concat, j().a(), Long.valueOf(round + ((Long) c.e).longValue()));
                }
                if (!G().a(c)) {
                    q().v().a("Too many unique user properties are set. Ignoring user property. appId", av.a(str), o().c(c.c), c.e);
                    n().b(9, null, null, 0);
                }
            }
        }
        return true;
    }

    private final fi[] a(String str, fo[] foVarArr, fj[] fjVarArr) {
        ab.a(str);
        return H().a(str, fjVarArr, foVarArr);
    }

    @WorkerThread
    private final Boolean b(r rVar) {
        try {
            if (rVar.j() != -2147483648L) {
                if (rVar.j() == ((long) c.a(k()).b(rVar.b(), 0).versionCode)) {
                    return Boolean.valueOf(true);
                }
            }
            String str = c.a(k()).b(rVar.b(), 0).versionName;
            if (rVar.i() != null && rVar.i().equals(str)) {
                return Boolean.valueOf(true);
            }
            return Boolean.valueOf(false);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    @WorkerThread
    private final void b(zzeu zzeu, zzdz zzdz) {
        ab.a((Object) zzdz);
        ab.a(zzdz.a);
        long nanoTime = System.nanoTime();
        x();
        I();
        String str = zzdz.a;
        n();
        if (!ew.a(zzeu, zzdz)) {
            return;
        }
        if (!zzdz.h) {
            e(zzdz);
        } else if (a().b(str, zzeu.a)) {
            q().y().a("Dropping blacklisted event. appId", av.a(str), o().a(zzeu.a));
            Object obj = (a().e(str) || a().f(str)) ? 1 : null;
            if (obj == null && !"_err".equals(zzeu.a)) {
                n().b(11, "_ev", zzeu.a, 0);
            }
            if (obj != null) {
                r b = G().b(str);
                if (b != null) {
                    if (Math.abs(j().a() - Math.max(b.q(), b.p())) > ((Long) al.A.b()).longValue()) {
                        q().B().a("Fetching config for blacklisted app");
                        a(b);
                    }
                }
            }
        } else {
            if (q().a(2)) {
                q().C().a("Logging event", o().a(zzeu));
            }
            G().u();
            e(zzdz);
            if (("_iap".equals(zzeu.a) || "ecommerce_purchase".equals(zzeu.a)) && !a(str, zzeu)) {
                G().v();
                G().w();
                return;
            }
            fm fmVar;
            try {
                boolean a = ew.a(zzeu.a);
                boolean equals = "_err".equals(zzeu.a);
                aa a2 = G().a(g(), str, true, a, false, equals, false);
                long intValue = a2.b - ((long) ((Integer) al.l.b()).intValue());
                if (intValue > 0) {
                    if (intValue % 1000 == 1) {
                        q().v().a("Data loss. Too many events logged. appId, count", av.a(str), Long.valueOf(a2.b));
                    }
                    G().v();
                    G().w();
                    return;
                }
                ah a3;
                ag agVar;
                boolean z;
                if (a) {
                    intValue = a2.a - ((long) ((Integer) al.n.b()).intValue());
                    if (intValue > 0) {
                        if (intValue % 1000 == 1) {
                            q().v().a("Data loss. Too many public events logged. appId, count", av.a(str), Long.valueOf(a2.a));
                        }
                        n().b(16, "_ev", zzeu.a, 0);
                        G().v();
                        G().w();
                        return;
                    }
                }
                if (equals) {
                    intValue = a2.d - ((long) Math.max(0, Math.min(1000000, c().b(zzdz.a, al.m))));
                    if (intValue > 0) {
                        if (intValue == 1) {
                            q().v().a("Too many error events logged. appId, count", av.a(str), Long.valueOf(a2.d));
                        }
                        G().v();
                        G().w();
                        return;
                    }
                }
                Bundle b2 = zzeu.b.b();
                n().a(b2, "_o", zzeu.c);
                if (n().h(str)) {
                    n().a(b2, "_dbg", Long.valueOf(1));
                    n().a(b2, "_r", Long.valueOf(1));
                }
                long c = G().c(str);
                if (c > 0) {
                    q().y().a("Data lost. Too many events stored on disk, deleted. appId", av.a(str), Long.valueOf(c));
                }
                ag agVar2 = new ag(this.h, zzeu.c, str, zzeu.a, zzeu.d, 0, b2);
                ah a4 = G().a(str, agVar2.b);
                if (a4 != null) {
                    agVar2 = agVar2.a(this.h, a4.e);
                    a3 = a4.a(agVar2.c);
                    agVar = agVar2;
                } else if (G().f(str) < 500 || !a) {
                    a3 = new ah(str, agVar2.b, 0, 0, agVar2.c, 0, null, null, null);
                    agVar = agVar2;
                } else {
                    q().v().a("Too many event names used, ignoring event. appId, name, supported count", av.a(str), o().a(agVar2.b), Integer.valueOf(500));
                    n().b(8, null, null, 0);
                    G().w();
                    return;
                }
                G().a(a3);
                x();
                I();
                ab.a((Object) agVar);
                ab.a((Object) zzdz);
                ab.a(agVar.a);
                ab.b(agVar.a.equals(zzdz.a));
                fmVar = new fm();
                fmVar.c = Integer.valueOf(1);
                fmVar.k = "android";
                fmVar.q = zzdz.a;
                fmVar.p = zzdz.d;
                fmVar.r = zzdz.c;
                fmVar.E = zzdz.j == -2147483648L ? null : Integer.valueOf((int) zzdz.j);
                fmVar.s = Long.valueOf(zzdz.e);
                fmVar.A = zzdz.b;
                fmVar.x = zzdz.f == 0 ? null : Long.valueOf(zzdz.f);
                Pair a5 = d().a(zzdz.a);
                if (TextUtils.isEmpty((CharSequence) a5.first)) {
                    if (!u().a(k()) && zzdz.p) {
                        String string = Secure.getString(k().getContentResolver(), "android_id");
                        if (string == null) {
                            q().y().a("null secure ID. appId", av.a(fmVar.q));
                            string = "null";
                        } else if (string.isEmpty()) {
                            q().y().a("empty secure ID. appId", av.a(fmVar.q));
                        }
                        fmVar.F = string;
                    }
                } else if (zzdz.o) {
                    fmVar.u = (String) a5.first;
                    fmVar.v = (Boolean) a5.second;
                }
                u().G();
                fmVar.m = Build.MODEL;
                u().G();
                fmVar.l = VERSION.RELEASE;
                fmVar.o = Integer.valueOf((int) u().u());
                fmVar.n = u().v();
                fmVar.t = null;
                fmVar.f = null;
                fmVar.g = null;
                fmVar.h = null;
                fmVar.H = Long.valueOf(zzdz.l);
                if (this.h.y() && w.y()) {
                    fmVar.I = null;
                }
                r b3 = G().b(zzdz.a);
                if (b3 == null) {
                    b3 = new r(this.h, zzdz.a);
                    b3.a(this.h.v().v());
                    b3.d(zzdz.k);
                    b3.b(zzdz.b);
                    b3.c(d().b(zzdz.a));
                    b3.f(0);
                    b3.a(0);
                    b3.b(0);
                    b3.e(zzdz.c);
                    b3.c(zzdz.j);
                    b3.f(zzdz.d);
                    b3.d(zzdz.e);
                    b3.e(zzdz.f);
                    b3.a(zzdz.h);
                    b3.o(zzdz.l);
                    G().a(b3);
                }
                fmVar.w = b3.c();
                fmVar.D = b3.f();
                List a6 = G().a(zzdz.a);
                fmVar.e = new fo[a6.size()];
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= a6.size()) {
                        break;
                    }
                    fo foVar = new fo();
                    fmVar.e[i2] = foVar;
                    foVar.d = ((ev) a6.get(i2)).c;
                    foVar.c = Long.valueOf(((ev) a6.get(i2)).d);
                    n().a(foVar, ((ev) a6.get(i2)).e);
                    i = i2 + 1;
                }
                long a7 = G().a(fmVar);
                z G = G();
                if (agVar.e != null) {
                    Iterator it = agVar.e.iterator();
                    while (it.hasNext()) {
                        if ("_r".equals((String) it.next())) {
                            z = true;
                            break;
                        }
                    }
                    z = a().c(agVar.a, agVar.b);
                    a2 = G().a(g(), agVar.a, false, false, false, false, false);
                    if (z && a2.e < ((long) c().a(agVar.a))) {
                        z = true;
                        if (G.a(agVar, a7, z)) {
                            this.k = 0;
                        }
                        G().v();
                        if (q().a(2)) {
                            q().C().a("Event recorded", o().a(agVar));
                        }
                        G().w();
                        i();
                        q().C().a("Background event processing time, ms", Long.valueOf(((System.nanoTime() - nanoTime) + 500000) / 1000000));
                    }
                }
                z = false;
                if (G.a(agVar, a7, z)) {
                    this.k = 0;
                }
                G().v();
                if (q().a(2)) {
                    q().C().a("Event recorded", o().a(agVar));
                }
                G().w();
                i();
                q().C().a("Background event processing time, ms", Long.valueOf(((System.nanoTime() - nanoTime) + 500000) / 1000000));
            } catch (IOException e) {
                q().v().a("Data loss. Failed to insert raw event metadata. appId", av.a(fmVar.q), e);
            } catch (Throwable th) {
                G().w();
            }
        }
    }

    private final bd e() {
        if (this.e != null) {
            return this.e;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    @WorkerThread
    private final r e(zzdz zzdz) {
        Object obj = 1;
        x();
        I();
        ab.a((Object) zzdz);
        ab.a(zzdz.a);
        r b = G().b(zzdz.a);
        String b2 = d().b(zzdz.a);
        Object obj2 = null;
        if (b == null) {
            r rVar = new r(this.h, zzdz.a);
            rVar.a(this.h.v().v());
            rVar.c(b2);
            b = rVar;
            obj2 = 1;
        } else if (!b2.equals(b.e())) {
            b.c(b2);
            b.a(this.h.v().v());
            int obj22 = 1;
        }
        if (!(TextUtils.isEmpty(zzdz.b) || zzdz.b.equals(b.d()))) {
            b.b(zzdz.b);
            obj22 = 1;
        }
        if (!(TextUtils.isEmpty(zzdz.k) || zzdz.k.equals(b.f()))) {
            b.d(zzdz.k);
            obj22 = 1;
        }
        if (!(zzdz.e == 0 || zzdz.e == b.l())) {
            b.d(zzdz.e);
            obj22 = 1;
        }
        if (!(TextUtils.isEmpty(zzdz.c) || zzdz.c.equals(b.i()))) {
            b.e(zzdz.c);
            obj22 = 1;
        }
        if (zzdz.j != b.j()) {
            b.c(zzdz.j);
            obj22 = 1;
        }
        if (!(zzdz.d == null || zzdz.d.equals(b.k()))) {
            b.f(zzdz.d);
            obj22 = 1;
        }
        if (zzdz.f != b.m()) {
            b.e(zzdz.f);
            obj22 = 1;
        }
        if (zzdz.h != b.n()) {
            b.a(zzdz.h);
            obj22 = 1;
        }
        if (!(TextUtils.isEmpty(zzdz.g) || zzdz.g.equals(b.y()))) {
            b.g(zzdz.g);
            obj22 = 1;
        }
        if (zzdz.l != b.A()) {
            b.o(zzdz.l);
            obj22 = 1;
        }
        if (zzdz.o != b.B()) {
            b.b(zzdz.o);
            obj22 = 1;
        }
        if (zzdz.p != b.C()) {
            b.c(zzdz.p);
        } else {
            obj = obj22;
        }
        if (obj != null) {
            G().a(b);
        }
        return b;
    }

    private final ek f() {
        a(this.f);
        return this.f;
    }

    private final long g() {
        long a = j().a();
        cs d = d();
        d.G();
        d.c();
        long a2 = d.g.a();
        if (a2 == 0) {
            a2 = 1 + ((long) d.n().w().nextInt(86400000));
            d.g.a(a2);
        }
        return ((((a2 + a) / 1000) / 60) / 60) / 24;
    }

    private final boolean h() {
        x();
        I();
        return G().D() || !TextUtils.isEmpty(G().y());
    }

    @WorkerThread
    private final void i() {
        x();
        I();
        if (r()) {
            long abs;
            if (this.k > 0) {
                abs = 3600000 - Math.abs(j().b() - this.k);
                if (abs > 0) {
                    q().C().a("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                    e().b();
                    f().u();
                    return;
                }
                this.k = 0;
            }
            if (this.h.D() && h()) {
                long a = j().a();
                long max = Math.max(0, ((Long) al.B.b()).longValue());
                Object obj = (G().E() || G().z()) ? 1 : null;
                if (obj != null) {
                    CharSequence x = c().x();
                    abs = (TextUtils.isEmpty(x) || ".none.".equals(x)) ? Math.max(0, ((Long) al.v.b()).longValue()) : Math.max(0, ((Long) al.w.b()).longValue());
                } else {
                    abs = Math.max(0, ((Long) al.u.b()).longValue());
                }
                long a2 = d().c.a();
                long a3 = d().d.a();
                long max2 = Math.max(G().B(), G().C());
                if (max2 == 0) {
                    a = 0;
                } else {
                    max2 = a - Math.abs(max2 - a);
                    a3 = a - Math.abs(a3 - a);
                    a2 = Math.max(a - Math.abs(a2 - a), a3);
                    a = max2 + max;
                    if (obj != null && a2 > 0) {
                        a = Math.min(max2, a2) + abs;
                    }
                    if (!n().a(a2, abs)) {
                        a = a2 + abs;
                    }
                    if (a3 != 0 && a3 >= max2) {
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= Math.min(20, Math.max(0, ((Integer) al.D.b()).intValue()))) {
                                a = 0;
                                break;
                            }
                            a += (1 << i2) * Math.max(0, ((Long) al.C.b()).longValue());
                            if (a > a3) {
                                break;
                            }
                            i = i2 + 1;
                        }
                    }
                }
                if (a == 0) {
                    q().C().a("Next upload time is 0");
                    e().b();
                    f().u();
                    return;
                } else if (F().u()) {
                    long a4 = d().e.a();
                    abs = Math.max(0, ((Long) al.s.b()).longValue());
                    abs = !n().a(a4, abs) ? Math.max(a, abs + a4) : a;
                    e().b();
                    abs -= j().a();
                    if (abs <= 0) {
                        abs = Math.max(0, ((Long) al.x.b()).longValue());
                        d().c.a(j().a());
                    }
                    q().C().a("Upload scheduled in approximately ms", Long.valueOf(abs));
                    f().a(abs);
                    return;
                } else {
                    q().C().a("No network");
                    e().a();
                    f().u();
                    return;
                }
            }
            q().C().a("Nothing to upload or uploading impossible");
            e().b();
            f().u();
        }
    }

    @WorkerThread
    private final void l() {
        x();
        if (this.o || this.p || this.q) {
            q().C().a("Not stopping services. fetch, network, upload", Boolean.valueOf(this.o), Boolean.valueOf(this.p), Boolean.valueOf(this.q));
            return;
        }
        q().C().a("Stopping uploading service(s)");
        if (this.l != null) {
            for (Runnable run : this.l) {
                run.run();
            }
            this.l.clear();
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean m() {
        x();
        try {
            this.s = new RandomAccessFile(new File(k().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.r = this.s.tryLock();
            if (this.r != null) {
                q().C().a("Storage concurrent access okay");
                return true;
            }
            q().v().a("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            q().v().a("Failed to acquire storage lock", e);
        } catch (IOException e2) {
            q().v().a("Failed to access storage lock file", e2);
        }
    }

    @WorkerThread
    private final boolean r() {
        x();
        I();
        return this.j;
    }

    @WorkerThread
    final void E() {
        x();
        en zVar = new z(this.h);
        zVar.O();
        this.d = zVar;
        c().a(this.a);
        zVar = new t(this.h);
        zVar.O();
        this.g = zVar;
        zVar = new ek(this.h);
        zVar.O();
        this.f = zVar;
        this.e = new bd(this.h);
        if (this.m != this.n) {
            q().v().a("Not all upload components initialized", Integer.valueOf(this.m), Integer.valueOf(this.n));
        }
        this.i = true;
    }

    public final az F() {
        a(this.b);
        return this.b;
    }

    public final z G() {
        a(this.d);
        return this.d;
    }

    public final t H() {
        a(this.g);
        return this.g;
    }

    final void I() {
        if (!this.i) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    @WorkerThread
    public final void J() {
        x();
        I();
        this.q = true;
        String y;
        String str;
        try {
            Boolean y2 = this.h.t().y();
            if (y2 == null) {
                q().y().a("Upload data called on the client side before use of service was decided");
                this.q = false;
                l();
            } else if (y2.booleanValue()) {
                q().v().a("Upload called in the client side when service should be used");
                this.q = false;
                l();
            } else if (this.k > 0) {
                i();
                this.q = false;
                l();
            } else {
                x();
                if ((this.t != null ? 1 : null) != null) {
                    q().C().a("Uploading requested multiple times");
                    this.q = false;
                    l();
                } else if (F().u()) {
                    long a = j().a();
                    a(a - w.w());
                    long a2 = d().c.a();
                    if (a2 != 0) {
                        q().B().a("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(a - a2)));
                    }
                    y = G().y();
                    Object a3;
                    if (TextUtils.isEmpty(y)) {
                        this.c = -1;
                        a3 = G().a(a - w.w());
                        if (!TextUtils.isEmpty(a3)) {
                            r b = G().b(a3);
                            if (b != null) {
                                a(b);
                            }
                        }
                    } else {
                        if (this.c == -1) {
                            this.c = G().F();
                        }
                        List<Pair> a4 = G().a(y, c().b(y, al.h), Math.max(0, c().b(y, al.i)));
                        if (!a4.isEmpty()) {
                            fm fmVar;
                            Object obj;
                            int i;
                            int i2;
                            List subList;
                            for (Pair pair : a4) {
                                fmVar = (fm) pair.first;
                                if (!TextUtils.isEmpty(fmVar.u)) {
                                    obj = fmVar.u;
                                    break;
                                }
                            }
                            obj = null;
                            if (obj != null) {
                                i = 0;
                                while (true) {
                                    i2 = i;
                                    if (i2 >= a4.size()) {
                                        break;
                                    }
                                    fmVar = (fm) ((Pair) a4.get(i2)).first;
                                    if (!TextUtils.isEmpty(fmVar.u) && !fmVar.u.equals(obj)) {
                                        subList = a4.subList(0, i2);
                                        break;
                                    }
                                    i = i2 + 1;
                                }
                            }
                            subList = a4;
                            fl flVar = new fl();
                            flVar.c = new fm[subList.size()];
                            Collection arrayList = new ArrayList(subList.size());
                            Object obj2 = (w.y() && c().c(y)) ? 1 : null;
                            i = 0;
                            while (true) {
                                i2 = i;
                                if (i2 >= flVar.c.length) {
                                    break;
                                }
                                flVar.c[i2] = (fm) ((Pair) subList.get(i2)).first;
                                arrayList.add((Long) ((Pair) subList.get(i2)).second);
                                flVar.c[i2].t = Long.valueOf(12451);
                                flVar.c[i2].f = Long.valueOf(a);
                                flVar.c[i2].B = Boolean.valueOf(false);
                                if (obj2 == null) {
                                    flVar.c[i2].I = null;
                                }
                                i = i2 + 1;
                            }
                            obj2 = q().a(2) ? o().a(flVar) : null;
                            obj = n().a(flVar);
                            str = (String) al.r.b();
                            Object url = new URL(str);
                            ab.b(!arrayList.isEmpty());
                            if (this.t != null) {
                                q().v().a("Set uploading progress before finishing the previous upload");
                            } else {
                                this.t = new ArrayList(arrayList);
                            }
                            d().d.a(a);
                            a3 = "?";
                            if (flVar.c.length > 0) {
                                a3 = flVar.c[0].q;
                            }
                            q().C().a("Uploading data. app, uncompressed size, data", a3, Integer.valueOf(obj.length), obj2);
                            this.p = true;
                            cs F = F();
                            Object epVar = new ep(this, y);
                            F.c();
                            F.N();
                            ab.a(url);
                            ab.a(obj);
                            ab.a(epVar);
                            F.p().b(new bc(F, y, url, obj, null, epVar));
                        }
                    }
                    this.q = false;
                    l();
                } else {
                    q().C().a("Network not connected, ignoring upload request");
                    i();
                    this.q = false;
                    l();
                }
            }
        } catch (MalformedURLException e) {
            q().v().a("Failed to parse upload URL. Not uploading. appId", av.a(y), str);
        } catch (Throwable th) {
            this.q = false;
            l();
        }
    }

    @WorkerThread
    final void K() {
        x();
        I();
        if (!this.j) {
            q().A().a("This instance being marked as an uploader");
            x();
            I();
            if (r() && m()) {
                int a = a(this.s);
                int y = this.h.v().y();
                x();
                if (a > y) {
                    q().v().a("Panic: can't downgrade version. Previous, current version", Integer.valueOf(a), Integer.valueOf(y));
                } else if (a < y) {
                    if (a(y, this.s)) {
                        q().C().a("Storage version upgraded. Previous, current version", Integer.valueOf(a), Integer.valueOf(y));
                    } else {
                        q().v().a("Storage version upgrade failed. Previous, current version", Integer.valueOf(a), Integer.valueOf(y));
                    }
                }
            }
            this.j = true;
            i();
        }
    }

    final void L() {
        this.m++;
    }

    final void M() {
        this.n++;
    }

    final bx N() {
        return this.h;
    }

    public final void O() {
        i();
    }

    @WorkerThread
    final zzdz a(String str) {
        r b = G().b(str);
        if (b == null || TextUtils.isEmpty(b.i())) {
            q().B().a("No app data available; dropping", str);
            return null;
        }
        Boolean b2 = b(b);
        if (b2 == null || b2.booleanValue()) {
            return new zzdz(str, b.d(), b.i(), b.j(), b.k(), b.l(), b.m(), null, b.n(), false, b.f(), b.A(), 0, 0, b.B(), b.C(), false);
        }
        q().v().a("App version does not match; dropping. appId", av.a(str));
        return null;
    }

    @WorkerThread
    @VisibleForTesting
    protected final void a(int i, Throwable th, byte[] bArr, String str) {
        x();
        I();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.p = false;
                l();
            }
        }
        List<Long> list = this.t;
        this.t = null;
        if ((i == 200 || i == 204) && th == null) {
            try {
                d().c.a(j().a());
                d().d.a(0);
                i();
                q().C().a("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                G().u();
                try {
                    for (Long l : list) {
                        cs G;
                        try {
                            G = G();
                            long longValue = l.longValue();
                            G.c();
                            G.N();
                            if (G.x().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                                throw new SQLiteException("Deleted fewer rows from queue than expected");
                            }
                        } catch (SQLiteException e) {
                            G.q().v().a("Failed to delete a bundle in a queue table", e);
                            throw e;
                        } catch (SQLiteException e2) {
                            if (this.u == null || !this.u.contains(l)) {
                                throw e2;
                            }
                        }
                    }
                    G().v();
                    this.u = null;
                    if (F().u() && h()) {
                        J();
                    } else {
                        this.c = -1;
                        i();
                    }
                    this.k = 0;
                } finally {
                    G().w();
                }
            } catch (SQLiteException e3) {
                q().v().a("Database error while trying to delete uploaded bundles", e3);
                this.k = j().b();
                q().C().a("Disable upload, time", Long.valueOf(this.k));
            }
        } else {
            q().C().a("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            d().d.a(j().a());
            boolean z = i == 503 || i == 429;
            if (z) {
                d().e.a(j().a());
            }
            if (c().g(str)) {
                G().a((List) list);
            }
            i();
        }
        this.p = false;
        l();
    }

    final void a(bx bxVar) {
        this.h = bxVar;
    }

    final void a(zzdz zzdz) {
        x();
        I();
        ab.a(zzdz.a);
        e(zzdz);
    }

    @WorkerThread
    final void a(zzed zzed, zzdz zzdz) {
        boolean z = true;
        ab.a((Object) zzed);
        ab.a(zzed.a);
        ab.a(zzed.b);
        ab.a(zzed.c);
        ab.a(zzed.c.a);
        x();
        I();
        if (!TextUtils.isEmpty(zzdz.b)) {
            if (zzdz.h) {
                zzed zzed2 = new zzed(zzed);
                zzed2.e = false;
                G().u();
                try {
                    zzed d = G().d(zzed2.a, zzed2.c.a);
                    if (!(d == null || d.b.equals(zzed2.b))) {
                        q().y().a("Updating a conditional user property with different origin. name, origin, origin (from DB)", o().c(zzed2.c.a), zzed2.b, d.b);
                    }
                    if (d != null && d.e) {
                        zzed2.b = d.b;
                        zzed2.d = d.d;
                        zzed2.h = d.h;
                        zzed2.f = d.f;
                        zzed2.i = d.i;
                        zzed2.e = d.e;
                        zzed2.c = new zzjx(zzed2.c.a, d.c.b, zzed2.c.a(), d.c.c);
                        z = false;
                    } else if (TextUtils.isEmpty(zzed2.f)) {
                        zzed2.c = new zzjx(zzed2.c.a, zzed2.d, zzed2.c.a(), zzed2.c.c);
                        zzed2.e = true;
                    } else {
                        z = false;
                    }
                    if (zzed2.e) {
                        zzjx zzjx = zzed2.c;
                        ev evVar = new ev(zzed2.a, zzed2.b, zzjx.a, zzjx.b, zzjx.a());
                        if (G().a(evVar)) {
                            q().B().a("User property updated immediately", zzed2.a, o().c(evVar.c), evVar.e);
                        } else {
                            q().v().a("(2)Too many active user properties, ignoring", av.a(zzed2.a), o().c(evVar.c), evVar.e);
                        }
                        if (z && zzed2.i != null) {
                            b(new zzeu(zzed2.i, zzed2.d), zzdz);
                        }
                    }
                    if (G().a(zzed2)) {
                        q().B().a("Conditional property added", zzed2.a, o().c(zzed2.c.a), zzed2.c.a());
                    } else {
                        q().v().a("Too many conditional properties, ignoring", av.a(zzed2.a), o().c(zzed2.c.a), zzed2.c.a());
                    }
                    G().v();
                } finally {
                    G().w();
                }
            } else {
                e(zzdz);
            }
        }
    }

    @WorkerThread
    final void a(zzeu zzeu, zzdz zzdz) {
        ab.a((Object) zzdz);
        ab.a(zzdz.a);
        x();
        I();
        String str = zzdz.a;
        long j = zzeu.d;
        n();
        if (!ew.a(zzeu, zzdz)) {
            return;
        }
        if (zzdz.h) {
            G().u();
            try {
                List emptyList;
                Object obj;
                cs G = G();
                ab.a(str);
                G.c();
                G.N();
                if (j < 0) {
                    G.q().y().a("Invalid time querying timed out conditional properties", av.a(str), Long.valueOf(j));
                    emptyList = Collections.emptyList();
                } else {
                    emptyList = G.a("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
                }
                for (zzed zzed : emptyList) {
                    if (zzed != null) {
                        q().B().a("User property timed out", zzed.a, o().c(zzed.c.a), zzed.c.a());
                        if (zzed.g != null) {
                            b(new zzeu(zzed.g, j), zzdz);
                        }
                        G().e(str, zzed.c.a);
                    }
                }
                G = G();
                ab.a(str);
                G.c();
                G.N();
                if (j < 0) {
                    G.q().y().a("Invalid time querying expired conditional properties", av.a(str), Long.valueOf(j));
                    emptyList = Collections.emptyList();
                } else {
                    emptyList = G.a("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
                }
                List arrayList = new ArrayList(r2.size());
                for (zzed zzed2 : r2) {
                    if (zzed2 != null) {
                        q().B().a("User property expired", zzed2.a, o().c(zzed2.c.a), zzed2.c.a());
                        G().b(str, zzed2.c.a);
                        if (zzed2.k != null) {
                            arrayList.add(zzed2.k);
                        }
                        G().e(str, zzed2.c.a);
                    }
                }
                ArrayList arrayList2 = (ArrayList) arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    obj = arrayList2.get(i);
                    i++;
                    b(new zzeu((zzeu) obj, j), zzdz);
                }
                G = G();
                String str2 = zzeu.a;
                ab.a(str);
                ab.a(str2);
                G.c();
                G.N();
                if (j < 0) {
                    G.q().y().a("Invalid time querying triggered conditional properties", av.a(str), G.m().a(str2), Long.valueOf(j));
                    emptyList = Collections.emptyList();
                } else {
                    emptyList = G.a("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
                }
                List arrayList3 = new ArrayList(r2.size());
                for (zzed zzed3 : r2) {
                    if (zzed3 != null) {
                        zzjx zzjx = zzed3.c;
                        ev evVar = new ev(zzed3.a, zzed3.b, zzjx.a, j, zzjx.a());
                        if (G().a(evVar)) {
                            q().B().a("User property triggered", zzed3.a, o().c(evVar.c), evVar.e);
                        } else {
                            q().v().a("Too many active user properties, ignoring", av.a(zzed3.a), o().c(evVar.c), evVar.e);
                        }
                        if (zzed3.i != null) {
                            arrayList3.add(zzed3.i);
                        }
                        zzed3.c = new zzjx(evVar);
                        zzed3.e = true;
                        G().a(zzed3);
                    }
                }
                b(zzeu, zzdz);
                arrayList2 = (ArrayList) arrayList3;
                int size2 = arrayList2.size();
                i = 0;
                while (i < size2) {
                    obj = arrayList2.get(i);
                    i++;
                    b(new zzeu((zzeu) obj, j), zzdz);
                }
                G().v();
            } finally {
                G().w();
            }
        } else {
            e(zzdz);
        }
    }

    @WorkerThread
    final void a(zzeu zzeu, String str) {
        r b = G().b(str);
        if (b == null || TextUtils.isEmpty(b.i())) {
            q().B().a("No app data available; dropping event", str);
            return;
        }
        Boolean b2 = b(b);
        if (b2 == null) {
            if (!"_ui".equals(zzeu.a)) {
                q().y().a("Could not find package. appId", av.a(str));
            }
        } else if (!b2.booleanValue()) {
            q().v().a("App version does not match; dropping event. appId", av.a(str));
            return;
        }
        zzeu zzeu2 = zzeu;
        a(zzeu2, new zzdz(str, b.d(), b.i(), b.j(), b.k(), b.l(), b.m(), null, b.n(), false, b.f(), b.A(), 0, 0, b.B(), b.C(), false));
    }

    @WorkerThread
    final void a(zzjx zzjx, zzdz zzdz) {
        int i = 0;
        x();
        I();
        if (!TextUtils.isEmpty(zzdz.b)) {
            if (zzdz.h) {
                int c = n().c(zzjx.a);
                String a;
                ew n;
                String str;
                if (c != 0) {
                    n();
                    a = ew.a(zzjx.a, 24, true);
                    if (zzjx.a != null) {
                        i = zzjx.a.length();
                    }
                    n = n();
                    str = zzdz.a;
                    n.b(c, "_ev", a, i);
                    return;
                }
                c = n().b(zzjx.a, zzjx.a());
                if (c != 0) {
                    n();
                    a = ew.a(zzjx.a, 24, true);
                    Object a2 = zzjx.a();
                    if (a2 != null && ((a2 instanceof String) || (a2 instanceof CharSequence))) {
                        i = String.valueOf(a2).length();
                    }
                    n = n();
                    str = zzdz.a;
                    n.b(c, "_ev", a, i);
                    return;
                }
                n();
                Object c2 = ew.c(zzjx.a, zzjx.a());
                if (c2 != null) {
                    ev evVar = new ev(zzdz.a, zzjx.c, zzjx.a, zzjx.b, c2);
                    q().B().a("Setting user property", o().c(evVar.c), c2);
                    G().u();
                    try {
                        e(zzdz);
                        boolean a3 = G().a(evVar);
                        G().v();
                        if (a3) {
                            q().B().a("User property set", o().c(evVar.c), evVar.e);
                        } else {
                            q().v().a("Too many unique user properties are set. Ignoring user property", o().c(evVar.c), evVar.e);
                            ew n2 = n();
                            String str2 = zzdz.a;
                            n2.b(9, null, null, 0);
                        }
                        G().w();
                        return;
                    } catch (Throwable th) {
                        G().w();
                    }
                } else {
                    return;
                }
            }
            e(zzdz);
        }
    }

    @WorkerThread
    final void a(Runnable runnable) {
        x();
        if (this.l == null) {
            this.l = new ArrayList();
        }
        this.l.add(runnable);
    }

    @WorkerThread
    @VisibleForTesting
    final void a(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        boolean z = true;
        x();
        I();
        ab.a(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.o = false;
                l();
            }
        }
        q().C().a("onConfigFetched. Response size", Integer.valueOf(bArr.length));
        G().u();
        r b = G().b(str);
        boolean z2 = (i == 200 || i == 204 || i == 304) && th == null;
        if (b == null) {
            q().y().a("App does not exist in onConfigFetched. appId", av.a(str));
        } else if (z2 || i == 404) {
            List list = map != null ? (List) map.get("Last-Modified") : null;
            String str2 = (list == null || list.size() <= 0) ? null : (String) list.get(0);
            if (i != 404 && i != 304) {
                a().a(str, bArr, str2);
            } else if (a().a(str) == null) {
                a().a(str, null, null);
            }
            b.g(j().a());
            G().a(b);
            if (i == 404) {
                q().z().a("Config not found. Using empty config. appId", str);
            } else {
                q().C().a("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
            }
            if (F().u() && h()) {
                J();
            } else {
                i();
            }
        } else {
            b.h(j().a());
            G().a(b);
            q().C().a("Fetching config failed. code, error", Integer.valueOf(i), th);
            a().c(str);
            d().d.a(j().a());
            if (!(i == 503 || i == 429)) {
                z = false;
            }
            if (z) {
                d().e.a(j().a());
            }
            i();
        }
        G().v();
        G().w();
        this.o = false;
        l();
    }

    @WorkerThread
    protected void b() {
        x();
        G().A();
        if (d().c.a() == 0) {
            d().c.a(j().a());
        }
        i();
    }

    @WorkerThread
    @VisibleForTesting
    final void b(zzdz zzdz) {
        if (this.t != null) {
            this.u = new ArrayList();
            this.u.addAll(this.t);
        }
        cs G = G();
        String str = zzdz.a;
        ab.a(str);
        G.c();
        G.N();
        try {
            SQLiteDatabase x = G.x();
            String[] strArr = new String[]{str};
            int delete = x.delete("main_event_params", "app_id=?", strArr) + ((((((((x.delete("apps", "app_id=?", strArr) + 0) + x.delete(EventsEntry.TABLE_NAME, "app_id=?", strArr)) + x.delete("user_attributes", "app_id=?", strArr)) + x.delete("conditional_properties", "app_id=?", strArr)) + x.delete("raw_events", "app_id=?", strArr)) + x.delete("raw_events_metadata", "app_id=?", strArr)) + x.delete("queue", "app_id=?", strArr)) + x.delete("audience_filter_values", "app_id=?", strArr));
            if (delete > 0) {
                G.q().C().a("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            G.q().v().a("Error resetting analytics data. appId, error", av.a(str), e);
        }
        zzdz a = a(k(), zzdz.a, zzdz.b, zzdz.h, zzdz.o, zzdz.p, zzdz.m);
        if (!c().i(zzdz.a) || zzdz.h) {
            c(a);
        }
    }

    @WorkerThread
    final void b(zzed zzed, zzdz zzdz) {
        ab.a((Object) zzed);
        ab.a(zzed.a);
        ab.a(zzed.c);
        ab.a(zzed.c.a);
        x();
        I();
        if (!TextUtils.isEmpty(zzdz.b)) {
            if (zzdz.h) {
                G().u();
                try {
                    e(zzdz);
                    zzed d = G().d(zzed.a, zzed.c.a);
                    if (d != null) {
                        q().B().a("Removing conditional user property", zzed.a, o().c(zzed.c.a));
                        G().e(zzed.a, zzed.c.a);
                        if (d.e) {
                            G().b(zzed.a, zzed.c.a);
                        }
                        if (zzed.k != null) {
                            Bundle bundle = null;
                            if (zzed.k.b != null) {
                                bundle = zzed.k.b.b();
                            }
                            b(n().a(zzed.k.a, bundle, d.b, zzed.k.d), zzdz);
                        }
                    } else {
                        q().y().a("Conditional user property doesn't exist", av.a(zzed.a), o().c(zzed.c.a));
                    }
                    G().v();
                } finally {
                    G().w();
                }
            } else {
                e(zzdz);
            }
        }
    }

    @WorkerThread
    final void b(zzjx zzjx, zzdz zzdz) {
        x();
        I();
        if (!TextUtils.isEmpty(zzdz.b)) {
            if (zzdz.h) {
                q().B().a("Removing user property", o().c(zzjx.a));
                G().u();
                try {
                    e(zzdz);
                    G().b(zzdz.a, zzjx.a);
                    G().v();
                    q().B().a("User property removed", o().c(zzjx.a));
                } finally {
                    G().w();
                }
            } else {
                e(zzdz);
            }
        }
    }

    @WorkerThread
    public final byte[] b(@NonNull zzeu zzeu, @Size(min = 1) String str) {
        I();
        x();
        bx.A();
        ab.a((Object) zzeu);
        ab.a(str);
        j flVar = new fl();
        G().u();
        try {
            r b = G().b(str);
            byte[] bArr;
            if (b == null) {
                q().B().a("Log and bundle not available. package_name", str);
                bArr = new byte[0];
                return bArr;
            } else if (b.n()) {
                fo foVar;
                long j;
                if (("_iap".equals(zzeu.a) || "ecommerce_purchase".equals(zzeu.a)) && !a(str, zzeu)) {
                    q().y().a("Failed to handle purchase event at single event bundle creation. appId", av.a(str));
                }
                boolean e = c().e(str);
                Long valueOf = Long.valueOf(0);
                if (e && "_e".equals(zzeu.a)) {
                    if (zzeu.b == null || zzeu.b.a() == 0) {
                        q().y().a("The engagement event does not contain any parameters. appId", av.a(str));
                    } else if (zzeu.b.b("_et") == null) {
                        q().y().a("The engagement event does not include duration. appId", av.a(str));
                    } else {
                        valueOf = zzeu.b.b("_et");
                    }
                }
                fm fmVar = new fm();
                flVar.c = new fm[]{fmVar};
                fmVar.c = Integer.valueOf(1);
                fmVar.k = "android";
                fmVar.q = b.b();
                fmVar.p = b.k();
                fmVar.r = b.i();
                long j2 = b.j();
                fmVar.E = j2 == -2147483648L ? null : Integer.valueOf((int) j2);
                fmVar.s = Long.valueOf(b.l());
                fmVar.A = b.d();
                fmVar.x = Long.valueOf(b.m());
                if (this.h.y() && w.y() && c().c(fmVar.q)) {
                    fmVar.I = null;
                }
                Pair a = d().a(b.b());
                if (b.B() && !TextUtils.isEmpty((CharSequence) a.first)) {
                    fmVar.u = (String) a.first;
                    fmVar.v = (Boolean) a.second;
                }
                u().G();
                fmVar.m = Build.MODEL;
                u().G();
                fmVar.l = VERSION.RELEASE;
                fmVar.o = Integer.valueOf((int) u().u());
                fmVar.n = u().v();
                fmVar.w = b.c();
                fmVar.D = b.f();
                List a2 = G().a(b.b());
                fmVar.e = new fo[a2.size()];
                ev evVar = null;
                if (e) {
                    ev c = G().c(fmVar.q, "_lte");
                    evVar = (c == null || c.e == null) ? new ev(fmVar.q, "auto", "_lte", j().a(), valueOf) : valueOf.longValue() > 0 ? new ev(fmVar.q, "auto", "_lte", j().a(), Long.valueOf(((Long) c.e).longValue() + valueOf.longValue())) : c;
                }
                fo foVar2 = null;
                int i = 0;
                while (i < a2.size()) {
                    fo foVar3;
                    foVar = new fo();
                    fmVar.e[i] = foVar;
                    foVar.d = ((ev) a2.get(i)).c;
                    foVar.c = Long.valueOf(((ev) a2.get(i)).d);
                    n().a(foVar, ((ev) a2.get(i)).e);
                    if (e && "_lte".equals(foVar.d)) {
                        foVar.f = (Long) evVar.e;
                        foVar.c = Long.valueOf(j().a());
                        foVar3 = foVar;
                    } else {
                        foVar3 = foVar2;
                    }
                    i++;
                    foVar2 = foVar3;
                }
                if (e && foVar2 == null) {
                    foVar = new fo();
                    foVar.d = "_lte";
                    foVar.c = Long.valueOf(j().a());
                    foVar.f = (Long) evVar.e;
                    fmVar.e = (fo[]) Arrays.copyOf(fmVar.e, fmVar.e.length + 1);
                    fmVar.e[fmVar.e.length - 1] = foVar;
                }
                if (valueOf.longValue() > 0) {
                    G().a(evVar);
                }
                Bundle b2 = zzeu.b.b();
                if ("_iap".equals(zzeu.a)) {
                    b2.putLong("_c", 1);
                    q().B().a("Marking in-app purchase as real-time");
                    b2.putLong("_r", 1);
                }
                b2.putString("_o", zzeu.c);
                if (n().h(fmVar.q)) {
                    n().a(b2, "_dbg", Long.valueOf(1));
                    n().a(b2, "_r", Long.valueOf(1));
                }
                ah a3 = G().a(str, zzeu.a);
                if (a3 == null) {
                    G().a(new ah(str, zzeu.a, 1, 0, zzeu.d, 0, null, null, null));
                    j = 0;
                } else {
                    j = a3.e;
                    G().a(a3.a(zzeu.d).a());
                }
                ag agVar = new ag(this.h, zzeu.c, str, zzeu.a, zzeu.d, j, b2);
                fj fjVar = new fj();
                fmVar.d = new fj[]{fjVar};
                fjVar.e = Long.valueOf(agVar.c);
                fjVar.d = agVar.b;
                fjVar.f = Long.valueOf(agVar.d);
                fjVar.c = new fk[agVar.e.a()];
                Iterator it = agVar.e.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String str2 = (String) it.next();
                    fk fkVar = new fk();
                    i = i2 + 1;
                    fjVar.c[i2] = fkVar;
                    fkVar.c = str2;
                    n().a(fkVar, agVar.e.a(str2));
                    i2 = i;
                }
                fmVar.C = a(b.b(), fmVar.e, fmVar.d);
                fmVar.g = fjVar.e;
                fmVar.h = fjVar.e;
                j2 = b.h();
                fmVar.j = j2 != 0 ? Long.valueOf(j2) : null;
                long g = b.g();
                if (g != 0) {
                    j2 = g;
                }
                fmVar.i = j2 != 0 ? Long.valueOf(j2) : null;
                b.r();
                fmVar.y = Integer.valueOf((int) b.o());
                fmVar.t = Long.valueOf(12451);
                fmVar.f = Long.valueOf(j().a());
                fmVar.B = Boolean.TRUE;
                b.a(fmVar.g.longValue());
                b.b(fmVar.h.longValue());
                G().a(b);
                G().v();
                G().w();
                try {
                    bArr = new byte[flVar.d()];
                    b a4 = b.a(bArr, bArr.length);
                    flVar.a(a4);
                    a4.a();
                    return n().a(bArr);
                } catch (IOException e2) {
                    q().v().a("Data loss. Failed to bundle and serialize. appId", av.a(str), e2);
                    return null;
                }
            } else {
                q().B().a("Log and bundle disabled. package_name", str);
                bArr = new byte[0];
                G().w();
                return bArr;
            }
        } finally {
            G().w();
        }
    }

    public w c() {
        return this.h.c();
    }

    @WorkerThread
    public final void c(zzdz zzdz) {
        x();
        I();
        ab.a((Object) zzdz);
        ab.a(zzdz.a);
        if (!TextUtils.isEmpty(zzdz.b)) {
            r b = G().b(zzdz.a);
            if (!(b == null || !TextUtils.isEmpty(b.d()) || TextUtils.isEmpty(zzdz.b))) {
                b.g(0);
                G().a(b);
                a().d(zzdz.a);
            }
            if (zzdz.h) {
                int i;
                Bundle bundle;
                long j = zzdz.m;
                if (j == 0) {
                    j = j().a();
                }
                int i2 = zzdz.n;
                if (i2 == 0 || i2 == 1) {
                    i = i2;
                } else {
                    q().y().a("Incorrect app type, assuming installed app. appId, appType", av.a(zzdz.a), Integer.valueOf(i2));
                    i = 0;
                }
                G().u();
                cs G;
                String b2;
                try {
                    b = G().b(zzdz.a);
                    if (!(b == null || b.d() == null || b.d().equals(zzdz.b))) {
                        q().y().a("New GMP App Id passed in. Removing cached database data. appId", av.a(b.b()));
                        G = G();
                        b2 = b.b();
                        G.N();
                        G.c();
                        ab.a(b2);
                        SQLiteDatabase x = G.x();
                        String[] strArr = new String[]{b2};
                        i2 = x.delete("audience_filter_values", "app_id=?", strArr) + ((((((((x.delete(EventsEntry.TABLE_NAME, "app_id=?", strArr) + 0) + x.delete("user_attributes", "app_id=?", strArr)) + x.delete("conditional_properties", "app_id=?", strArr)) + x.delete("apps", "app_id=?", strArr)) + x.delete("raw_events", "app_id=?", strArr)) + x.delete("raw_events_metadata", "app_id=?", strArr)) + x.delete("event_filters", "app_id=?", strArr)) + x.delete("property_filters", "app_id=?", strArr));
                        if (i2 > 0) {
                            G.q().C().a("Deleted application data. app, records", b2, Integer.valueOf(i2));
                        }
                        b = null;
                    }
                } catch (SQLiteException e) {
                    G.q().v().a("Error deleting application data. appId, error", av.a(b2), e);
                } catch (Throwable th) {
                    G().w();
                }
                if (b != null) {
                    if (b.j() != -2147483648L) {
                        if (b.j() != zzdz.j) {
                            bundle = new Bundle();
                            bundle.putString("_pv", b.i());
                            a(new zzeu("_au", new zzer(bundle), "auto", j), zzdz);
                        }
                    } else if (!(b.i() == null || b.i().equals(zzdz.c))) {
                        bundle = new Bundle();
                        bundle.putString("_pv", b.i());
                        a(new zzeu("_au", new zzer(bundle), "auto", j), zzdz);
                    }
                }
                e(zzdz);
                ah ahVar = null;
                if (i == 0) {
                    ahVar = G().a(zzdz.a, "_f");
                } else if (i == 1) {
                    ahVar = G().a(zzdz.a, "_v");
                }
                if (ahVar == null) {
                    long j2 = (1 + (j / 3600000)) * 3600000;
                    if (i == 0) {
                        a(new zzjx("_fot", j, Long.valueOf(j2), "auto"), zzdz);
                        x();
                        I();
                        Bundle bundle2 = new Bundle();
                        bundle2.putLong("_c", 1);
                        bundle2.putLong("_r", 1);
                        bundle2.putLong("_uwa", 0);
                        bundle2.putLong("_pfo", 0);
                        bundle2.putLong("_sys", 0);
                        bundle2.putLong("_sysu", 0);
                        if (c().i(zzdz.a) && zzdz.q) {
                            bundle2.putLong("_dac", 1);
                        }
                        if (k().getPackageManager() == null) {
                            q().v().a("PackageManager is null, first open report might be inaccurate. appId", av.a(zzdz.a));
                        } else {
                            ApplicationInfo a;
                            PackageInfo packageInfo = null;
                            try {
                                packageInfo = c.a(k()).b(zzdz.a, 0);
                            } catch (NameNotFoundException e2) {
                                q().v().a("Package info is null, first open report might be inaccurate. appId", av.a(zzdz.a), e2);
                            }
                            if (packageInfo != null) {
                                if (packageInfo.firstInstallTime != 0) {
                                    Object obj = null;
                                    if (packageInfo.firstInstallTime != packageInfo.lastUpdateTime) {
                                        bundle2.putLong("_uwa", 1);
                                    } else {
                                        obj = 1;
                                    }
                                    a(new zzjx("_fi", j, Long.valueOf(obj != null ? 1 : 0), "auto"), zzdz);
                                }
                            }
                            try {
                                a = c.a(k()).a(zzdz.a, 0);
                            } catch (NameNotFoundException e22) {
                                q().v().a("Application info is null, first open report might be inaccurate. appId", av.a(zzdz.a), e22);
                                a = null;
                            }
                            if (a != null) {
                                if ((a.flags & 1) != 0) {
                                    bundle2.putLong("_sys", 1);
                                }
                                if ((a.flags & 128) != 0) {
                                    bundle2.putLong("_sysu", 1);
                                }
                            }
                        }
                        cs G2 = G();
                        String str = zzdz.a;
                        ab.a(str);
                        G2.c();
                        G2.N();
                        j2 = G2.h(str, "first_open_count");
                        if (j2 >= 0) {
                            bundle2.putLong("_pfo", j2);
                        }
                        a(new zzeu("_f", new zzer(bundle2), "auto", j), zzdz);
                    } else if (i == 1) {
                        a(new zzjx("_fvt", j, Long.valueOf(j2), "auto"), zzdz);
                        x();
                        I();
                        bundle = new Bundle();
                        bundle.putLong("_c", 1);
                        bundle.putLong("_r", 1);
                        if (c().i(zzdz.a) && zzdz.q) {
                            bundle.putLong("_dac", 1);
                        }
                        a(new zzeu("_v", new zzer(bundle), "auto", j), zzdz);
                    }
                    bundle = new Bundle();
                    bundle.putLong("_et", 1);
                    a(new zzeu("_e", new zzer(bundle), "auto", j), zzdz);
                } else if (zzdz.i) {
                    a(new zzeu("_cd", new zzer(new Bundle()), "auto", j), zzdz);
                }
                G().v();
                G().w();
                return;
            }
            e(zzdz);
        }
    }

    public bf d() {
        return this.h.d();
    }

    public final String d(zzdz zzdz) {
        Object e;
        try {
            return (String) p().a(new er(this, zzdz)).get(30000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e2) {
            e = e2;
        } catch (InterruptedException e3) {
            e = e3;
        } catch (ExecutionException e4) {
            e = e4;
        }
        q().v().a("Failed to get app instance id. appId", av.a(zzdz.a), e);
        return null;
    }

    public e j() {
        return this.h.j();
    }

    public Context k() {
        return this.h.k();
    }

    public ew n() {
        return this.h.n();
    }

    public at o() {
        return this.h.o();
    }

    public bs p() {
        return this.h.p();
    }

    public av q() {
        return this.h.q();
    }

    public af u() {
        return this.h.u();
    }

    @WorkerThread
    public void x() {
        p().c();
    }
}
