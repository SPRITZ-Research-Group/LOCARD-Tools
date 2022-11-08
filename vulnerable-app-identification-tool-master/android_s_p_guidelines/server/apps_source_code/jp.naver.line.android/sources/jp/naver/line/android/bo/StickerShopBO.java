package jp.naver.line.android.bo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.linecorp.rxeventbus.a;
import defpackage.addc;
import defpackage.adfz;
import defpackage.bpl;
import defpackage.bpq;
import defpackage.bpr;
import defpackage.bps;
import defpackage.bpw;
import defpackage.cmp;
import defpackage.lyj;
import defpackage.lzd;
import defpackage.shh;
import defpackage.smy;
import defpackage.tjm;
import defpackage.twj;
import defpackage.twm;
import defpackage.tyu;
import defpackage.tzd;
import defpackage.uad;
import defpackage.ubm;
import defpackage.ubo;
import defpackage.ubp;
import defpackage.udx;
import defpackage.uet;
import defpackage.ufh;
import defpackage.utk;
import defpackage.uuk;
import defpackage.uwb;
import defpackage.uwc;
import defpackage.uwe;
import defpackage.uwj;
import defpackage.uwl;
import defpackage.uwu;
import defpackage.uwx;
import defpackage.uwy;
import defpackage.vkd;
import defpackage.vkx;
import defpackage.vvn;
import defpackage.zrl;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import jp.naver.line.android.BuildConfig;
import jp.naver.line.android.LineApplication;
import jp.naver.line.android.common.util.io.d;
import jp.naver.line.android.common.util.io.i;
import jp.naver.line.android.db.main.model.ao;
import jp.naver.line.android.db.main.model.aq;
import jp.naver.line.android.l;
import jp.naver.line.android.stickershop.model.StickerInfo;
import jp.naver.line.android.stickershop.model.c;
import jp.naver.line.android.stickershop.model.f;
import jp.naver.line.android.util.aw;
import jp.naver.line.android.util.text.e;
import jp.naver.line.shop.protocol.thrift.ProductSearchSummary;
import jp.naver.line.shop.protocol.thrift.gh;
import jp.naver.line.shop.protocol.thrift.kp;
import org.json.JSONException;

public final class StickerShopBO {
    private static final StickerShopBO a = new StickerShopBO();
    private final LineApplication b = l.a();
    private final a c = this.b.a();
    private String d;
    private final tzd e = new tzd();
    private final lyj f = new lyj((byte) 0);

    final class ShowcaseProductsAdapter implements bpr<List<ProductSearchSummary>> {
        private ShowcaseProductsAdapter() {
        }

        /* synthetic */ ShowcaseProductsAdapter(byte b) {
            this();
        }

        public final /* synthetic */ Object deserialize(bps bps, Type type, bpq bpq) throws bpw {
            return a(bps, type, bpq);
        }

        private static List<ProductSearchSummary> a(bps bps, Type type, bpq bpq) throws bpw {
            try {
                List<ProductSearchSummary> list = (List) new bpl().a(bps, type);
                int i = 0;
                for (ProductSearchSummary productSearchSummary : list) {
                    if (productSearchSummary.getProperty().isSetStickerProperty()) {
                        int i2 = i + 1;
                        kp kpVar = (kp) bpq.a(bps.h().a(i).g().b("property").g().b("value_"), kp.class);
                        if (kpVar != null) {
                            productSearchSummary.getProperty().setStickerProperty(kpVar);
                        }
                        i = i2;
                    }
                }
                return list;
            } catch (Throwable e) {
                throw new bpw(e);
            }
        }
    }

    private StickerShopBO() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.bo.StickerShopBO.<init>():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r2 = this;
        r2.<init>();
        r0 = jp.naver.line.android.l.a();
        r2.b = r0;
        r0 = r2.b;
        r0 = r0.a();
        r2.c = r0;
        r0 = new tzd;
        r0.<init>();
        r2.e = r0;
        r0 = new lyj;
        r1 = 0;
        r0.<init>(r1);
        r2.f = r0;
        r0 = r2.d;
        if (r0 != 0) goto L_0x0032;
    L_0x0024:
        r0 = defpackage.uoa.h();	 Catch:{ Exception -> 0x002f }
        r0 = r0.g();	 Catch:{ Exception -> 0x002f }
        r2.d = r0;	 Catch:{ Exception -> 0x002f }
        return;
    L_0x002f:
        r0 = 0;
        r2.d = r0;
    L_0x0032:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.bo.StickerShopBO.<init>():void");
    }

    public static StickerShopBO a() {
        return a;
    }

    public static cmp<Void, List<StickerInfo>> b() {
        return new bp();
    }

    public static String c() {
        String str = uuk.a().settings.J;
        return TextUtils.isEmpty(str) ? BuildConfig.URL_DEFAULT_SHOP : str;
    }

    public static int d() {
        return uuk.a().settings.K;
    }

    public static smy a(long j) throws adfz {
        String stringBuilder;
        vkd l = vkx.l();
        gh ghVar = gh.STICKER;
        String valueOf = String.valueOf(j);
        shh b = a.a().b();
        if (b.c()) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(utk.h());
            stringBuilder2.append('_');
            stringBuilder2.append(b.d());
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder = utk.h();
        }
        return smy.a(l.a(ghVar, valueOf, stringBuilder, false).a);
    }

    public static boolean a(smy smy) {
        switch (smy.X()) {
            case BUDDY:
                return tyu.i(twj.b(twm.MAIN), smy.Y());
            case INSTALL:
                return a(smy.Z());
            case MISSION:
                return smy.aa();
            default:
                return false;
        }
    }

    public static boolean a(String str) {
        String[] split = e.b(str).split(",");
        if (split.length <= 0) {
            return false;
        }
        for (CharSequence b : split) {
            if (aw.b(l.a(), addc.b(b).toString())) {
                return true;
            }
        }
        return false;
    }

    public static void a(smy smy, uwx uwx) {
        uwu a = uwu.a();
        c ab = smy.ab();
        jp.naver.line.android.stickershop.model.a R = smy.R();
        if (ab == null) {
            ab = c.STATIC;
        }
        a.a(R, ab, uwx, false, false);
    }

    public static void a(smy smy, boolean z, boolean z2) {
        uwu a = uwu.a();
        c ab = smy.ab();
        jp.naver.line.android.stickershop.model.a R = smy.R();
        if (ab == null) {
            ab = c.STATIC;
        }
        a.a(R, ab, null, z, z2);
    }

    public static boolean a(long j, uwx uwx) {
        return uwu.a().a(j, uwx);
    }

    public static boolean b(long j) {
        return uwu.a().b(j);
    }

    public static void c(long j) {
        if (uwu.a().a(j)) {
            tzd.a(twj.a(twm.MAIN), j, ubp.NEED_DOWNLOAD, null);
        }
    }

    public static int d(long j) {
        return uwu.a().c(j);
    }

    public static void e(long j) {
        tzd.a(twj.a(twm.MAIN), j, ubp.NEED_DOWNLOAD, null);
    }

    public static void f() {
        SQLiteDatabase a = twj.a(twm.MAIN);
        List<ao> a2 = tzd.a(a, ubp.DOWNLOADING);
        long currentTimeMillis = System.currentTimeMillis();
        for (ao aoVar : a2) {
            if (aoVar.l() == ubp.DOWNLOADING && currentTimeMillis - aoVar.t() > 600000) {
                tzd.a(a, aoVar.f(), ubp.NEED_DOWNLOAD, Long.valueOf(0));
            }
        }
    }

    public static ubp f(long j) {
        ao f = tzd.f(twj.b(twm.MAIN), j);
        if (f == null) {
            return ubp.NEED_DOWNLOAD;
        }
        return f.l();
    }

    public static List<StickerInfo> a(long j, c cVar) {
        Throwable th;
        List<StickerInfo> arrayList = new ArrayList();
        SQLiteDatabase b = twj.b(twm.MAIN);
        Cursor a;
        try {
            long b2 = bj.a().b(j, -1);
            a = uad.a(b, null, Long.valueOf(j), ubm.c.a);
            try {
                if (a.moveToFirst()) {
                    do {
                        StickerInfo stickerInfo = new StickerInfo();
                        stickerInfo.a(ubm.a.a(a, -1));
                        stickerInfo.c(b2);
                        stickerInfo.a(ubm.d.a(a, 0));
                        stickerInfo.b(ubm.e.a(a, 0));
                        stickerInfo.b(ubm.b.a(a, -1));
                        stickerInfo.a(cVar);
                        ao f = tzd.f(b, j);
                        stickerInfo.a(f == null ? null : f.j());
                        arrayList.add(stickerInfo);
                    } while (a.moveToNext());
                }
                if (a != null) {
                    a.close();
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                if (a != null) {
                    a.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            a = null;
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    public final boolean g(long j) throws zrl, adfz, uet {
        long j2;
        Throwable e;
        StickerShopBO stickerShopBO;
        zrl e2;
        adfz e3;
        if (j >= 0) {
            try {
                smy a = a(j);
                long a2 = tjm.a(a.b(), 0);
                long G = a.G();
                SQLiteDatabase a3 = twj.a(twm.MAIN);
                ao f = tzd.f(a3, a2);
                boolean z = true;
                if (f == null) {
                    if (a.L()) {
                        c ab = a.ab();
                        jp.naver.line.android.stickershop.model.a aVar = new jp.naver.line.android.stickershop.model.a(a2, G, -1, null);
                        uwb a4 = uwb.a(uwl.a(aVar));
                        k().n().b(aVar);
                        try {
                            c cVar = ab;
                            j2 = a2;
                            try {
                                tzd.a(twj.a(twm.MAIN), a2, a4.a(), cVar, ubp.NEED_DOWNLOAD, false, -1, G);
                            } catch (SQLiteException e4) {
                                e = e4;
                            }
                        } catch (SQLiteException e5) {
                            e = e5;
                            j2 = a2;
                            udx.c(e, "StickerShopBO", "Failed insert default sticker. pacakgeId=".concat(String.valueOf(j2)), "jp.naver.line.android.bo.StickerShopBO#insertDefaultPackageAsNotSendable()");
                            stickerShopBO = this;
                            return z;
                        }
                    }
                    a(a.R(), a.F(), false);
                    stickerShopBO = this;
                } else {
                    long j3 = a2;
                    if (a.F()) {
                        k().o().a(j3);
                        if (f.o()) {
                            try {
                                this.c.a(new uwj(j3));
                            } catch (zrl e6) {
                                e2 = e6;
                                throw e2;
                            } catch (adfz e7) {
                                e3 = e7;
                                throw e3;
                            } catch (Exception e8) {
                                e = e8;
                                throw new uet(e);
                            }
                        }
                        stickerShopBO = this;
                        uwe.d(j3);
                    } else {
                        boolean z2;
                        stickerShopBO = this;
                        if (f.b()) {
                            tzd.d(a3, j3);
                            if (f.p()) {
                                a(a.R(), true, false);
                            }
                            f = tzd.f(a3, j3);
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (f == null) {
                            return false;
                        }
                        if (f.q() != a.C()) {
                            tzd.a(a3, j3, a.C());
                            z2 = true;
                        }
                        if (f.r() != a.B()) {
                            tzd.a(a3, j3, a.B());
                            z2 = true;
                        }
                        if (f.s() != a.E()) {
                            tzd.b(a3, j3, a.E());
                            z2 = true;
                        }
                        CharSequence a5 = a(f.j());
                        String a6 = a(a.S());
                        if (!TextUtils.equals(a5, a6)) {
                            tzd.a(a3, j3, a6);
                            z2 = true;
                        }
                        if (TextUtils.equals(f.h(), a.p())) {
                            z = z2;
                        } else {
                            String p = a.p();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(ubo.b.a, p);
                            p = ubo.x.a;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(ubo.a.a);
                            stringBuilder.append("=?");
                            a3.update(p, contentValues, stringBuilder.toString(), new String[]{String.valueOf(j3)});
                        }
                        tzd.d(a3, j3);
                    }
                }
                return z;
            } catch (zrl e9) {
                e2 = e9;
                stickerShopBO = this;
                throw e2;
            } catch (adfz e10) {
                e3 = e10;
                stickerShopBO = this;
                throw e3;
            } catch (Exception e11) {
                e = e11;
                stickerShopBO = this;
                throw new uet(e);
            }
        }
        stickerShopBO = this;
        return false;
    }

    public final void g() {
        if (j() < System.currentTimeMillis()) {
            k().i();
        }
    }

    private static String a(f fVar) {
        return fVar == null ? null : fVar.a();
    }

    public static void a(jp.naver.line.android.stickershop.model.a aVar, boolean z, boolean z2) throws JSONException, IOException {
        a(aVar.b(), aVar.c(), aVar.e(), z, z2);
    }

    public static boolean a(long j, long j2, f fVar, boolean z, boolean z2) throws vvn, IOException, JSONException {
        long j3;
        Object obj;
        Cursor e;
        Throwable th;
        long a;
        Object obj2;
        SQLiteDatabase sQLiteDatabase;
        long j4;
        long j5 = j;
        long j6 = j2;
        SQLiteDatabase a2 = twj.a(twm.MAIN);
        boolean z3 = false;
        if (z2) {
            j3 = -1;
            obj = null;
        } else {
            try {
                e = tzd.e(a2, j5);
                try {
                    long e2;
                    Object obj3;
                    if (e.moveToNext()) {
                        e2 = ubo.g.e(e);
                        obj3 = 1;
                    } else {
                        e2 = -1;
                        obj3 = null;
                    }
                    if (e != null) {
                        e.close();
                    }
                    if (obj3 != null && e2 >= j6) {
                        return false;
                    }
                    obj = obj3;
                    j3 = e2;
                } catch (Throwable th2) {
                    th = th2;
                    if (e != null) {
                        e.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                e = null;
                if (e != null) {
                    e.close();
                }
                throw th;
            }
        }
        j6 = -1;
        String a3 = uwl.a(new jp.naver.line.android.stickershop.model.a(j, j2, -1, fVar));
        uwb a4 = a3 != null ? uwb.a(a3) : null;
        if (a4 != null) {
            twj.a(a2);
            try {
                e = tzd.e(a2, j5);
                try {
                    if (e.moveToNext()) {
                        a = ubo.g.a(e, j6);
                        obj2 = 1;
                    } else {
                        a = j3;
                        obj2 = null;
                    }
                    if (e != null) {
                        try {
                            e.close();
                        } catch (Throwable th4) {
                            th = th4;
                            sQLiteDatabase = a2;
                            sQLiteDatabase.endTransaction();
                            throw th;
                        }
                    }
                    String a5 = a4.a();
                    if (obj2 != null) {
                        tzd.a(a2, j, a5, j2, a4.c, a4.d);
                    } else if (z) {
                        tzd.a(a2, Long.valueOf(j), a5, Integer.valueOf(a4.c), Boolean.TRUE, aq.AVAILABLE, ubp.NEED_DOWNLOAD, Boolean.FALSE, Integer.valueOf(0), Long.valueOf(j2), a4.d, fVar, Long.valueOf(0));
                    } else {
                        tzd.a(a2, Long.valueOf(j), a5, Integer.valueOf(a4.c), Boolean.FALSE, aq.AVAILABLE, ubp.NEED_DOWNLOAD, Boolean.FALSE, null, Long.valueOf(j2), a4.d, fVar, Long.valueOf(0));
                    }
                    uad.a(a2, j5);
                    List<uwc> list = a4.e;
                    if (list != null) {
                        int i = 1;
                        for (uwc uwc : list) {
                            int i2 = i + 1;
                            sQLiteDatabase = a2;
                            try {
                                uad.b(a2, uwc.a, j, i, uwc.b, uwc.c, uwc.d, uwc.e);
                                i = i2;
                                a2 = sQLiteDatabase;
                            } catch (Throwable th5) {
                                th = th5;
                            }
                        }
                    }
                    sQLiteDatabase = a2;
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th6) {
                    th = th6;
                    sQLiteDatabase = a2;
                    if (e != null) {
                        e.close();
                    }
                    throw th;
                }
            } catch (Throwable th7) {
                th = th7;
                sQLiteDatabase = a2;
                e = null;
                if (e != null) {
                    e.close();
                }
                throw th;
            }
        }
        obj2 = obj;
        a = j3;
        if (obj2 == null || a <= 0) {
            j4 = j2;
        } else {
            j4 = j2;
            if (a < j4) {
                z3 = true;
            }
        }
        if (z3) {
            bj.a().c(j5, j4);
            h();
            uwe.d(j);
        }
        return z3;
    }

    public static void h() {
        new ufh().a(new bq());
    }

    private static long j() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.bo.StickerShopBO.j():long. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = defpackage.uac.a();
        r1 = jp.naver.line.android.model.cl.STICKER_SHOP_NEXT_SYNC_ALL_TIME;
        r2 = 0;
        r0 = r0.b(r2, r1, r2);
        if (r0 == 0) goto L_0x0012;
    L_0x000d:
        r0 = java.lang.Long.parseLong(r0);	 Catch:{ NumberFormatException -> 0x0012 }
        goto L_0x0014;
    L_0x0012:
        r0 = 0;
    L_0x0014:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.bo.StickerShopBO.j():long");
    }

    public final void a(jp.naver.line.android.stickershop.model.a aVar, c cVar) throws Exception {
        uwy uwy = new uwy(aVar, cVar, this.c, this.f);
        uwy.run();
        Exception a = uwy.a();
        if (a != null) {
            throw a;
        }
    }

    public final java.util.List<jp.naver.line.shop.protocol.thrift.ProductSearchSummary> i() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.bo.StickerShopBO.i():java.util.List<jp.naver.line.shop.protocol.thrift.ProductSearchSummary>. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r9 = this;
        r0 = jp.naver.line.android.bo.a.a();
        r0 = r0.b();
        r1 = defpackage.shi.PAY_SERVICE;
        r0 = r0.a(r1);
        if (r0 != 0) goto L_0x0015;
    L_0x0010:
        r0 = java.util.Collections.emptyList();
        return r0;
    L_0x0015:
        r0 = defpackage.uuk.a();
        r0 = r0.settings;
        r0 = r0.aT;
        if (r0 != 0) goto L_0x0024;
    L_0x001f:
        r0 = java.util.Collections.emptyList();
        return r0;
    L_0x0024:
        r0 = java.lang.System.currentTimeMillis();
        r2 = defpackage.tyg.POPULAR_OFFICIAL_STICKER_LIST_LAST_UPDATE_TIME;
        r3 = 0;
        r5 = defpackage.tyh.a(r2, r3);
        r2 = defpackage.uuk.a();
        r2 = r2.settings;
        r7 = r2.aU;
        r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1));
        if (r2 == 0) goto L_0x0072;
    L_0x003c:
        r5 = r5 + r7;
        r2 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1));
        if (r2 <= 0) goto L_0x0072;
    L_0x0041:
        r2 = defpackage.tyg.POPULAR_OFFICIAL_STICKER_LIST_CACHE;
        r5 = "";
        r2 = defpackage.tyh.a(r2, r5);
        r5 = android.text.TextUtils.isEmpty(r2);
        if (r5 != 0) goto L_0x0072;
    L_0x004f:
        r5 = new jp.naver.line.android.bo.StickerShopBO$1;	 Catch:{ bpw -> 0x0072 }
        r5.<init>(r9);	 Catch:{ bpw -> 0x0072 }
        r5 = r5.b();	 Catch:{ bpw -> 0x0072 }
        r6 = new bpn;	 Catch:{ bpw -> 0x0072 }
        r6.<init>();	 Catch:{ bpw -> 0x0072 }
        r7 = new jp.naver.line.android.bo.StickerShopBO$ShowcaseProductsAdapter;	 Catch:{ bpw -> 0x0072 }
        r8 = 0;	 Catch:{ bpw -> 0x0072 }
        r7.<init>(r8);	 Catch:{ bpw -> 0x0072 }
        r6 = r6.a(r5, r7);	 Catch:{ bpw -> 0x0072 }
        r6 = r6.c();	 Catch:{ bpw -> 0x0072 }
        r2 = r6.a(r2, r5);	 Catch:{ bpw -> 0x0072 }
        r2 = (java.util.List) r2;	 Catch:{ bpw -> 0x0072 }
        return r2;
    L_0x0072:
        r2 = defpackage.vkx.l();	 Catch:{ adfz -> 0x00a7 }
        r5 = jp.naver.line.shop.protocol.thrift.gh.STICKER;	 Catch:{ adfz -> 0x00a7 }
        r6 = jp.naver.line.shop.protocol.thrift.kc.POPULAR;	 Catch:{ adfz -> 0x00a7 }
        r7 = jp.naver.line.shop.protocol.thrift.lu.GENERAL;	 Catch:{ adfz -> 0x00a7 }
        r2 = r2.a(r5, r6, r7);	 Catch:{ adfz -> 0x00a7 }
        if (r2 == 0) goto L_0x009d;	 Catch:{ adfz -> 0x00a7 }
    L_0x0082:
        r5 = r2.isEmpty();	 Catch:{ adfz -> 0x00a7 }
        if (r5 == 0) goto L_0x0089;	 Catch:{ adfz -> 0x00a7 }
    L_0x0088:
        goto L_0x009d;	 Catch:{ adfz -> 0x00a7 }
    L_0x0089:
        r5 = defpackage.tyg.POPULAR_OFFICIAL_STICKER_LIST_LAST_UPDATE_TIME;	 Catch:{ adfz -> 0x00a7 }
        defpackage.tyh.b(r5, r0);	 Catch:{ adfz -> 0x00a7 }
        r0 = defpackage.tyg.POPULAR_OFFICIAL_STICKER_LIST_CACHE;	 Catch:{ adfz -> 0x00a7 }
        r1 = new bpl;	 Catch:{ adfz -> 0x00a7 }
        r1.<init>();	 Catch:{ adfz -> 0x00a7 }
        r1 = r1.b(r2);	 Catch:{ adfz -> 0x00a7 }
        defpackage.tyh.b(r0, r1);	 Catch:{ adfz -> 0x00a7 }
        return r2;	 Catch:{ adfz -> 0x00a7 }
    L_0x009d:
        r0 = defpackage.tyg.POPULAR_OFFICIAL_STICKER_LIST_LAST_UPDATE_TIME;	 Catch:{ adfz -> 0x00a7 }
        defpackage.tyh.b(r0, r3);	 Catch:{ adfz -> 0x00a7 }
        r0 = java.util.Collections.emptyList();	 Catch:{ adfz -> 0x00a7 }
        return r0;
    L_0x00a7:
        r0 = defpackage.tyg.POPULAR_OFFICIAL_STICKER_LIST_LAST_UPDATE_TIME;
        defpackage.tyh.b(r0, r3);
        r0 = java.util.Collections.emptyList();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.bo.StickerShopBO.i():java.util.List<jp.naver.line.shop.protocol.thrift.ProductSearchSummary>");
    }

    private lzd k() {
        return this.b.f().o();
    }

    public static void e() throws d {
        File file = new File(uwe.a(), "etc");
        if (!file.exists()) {
            i.a(file);
        }
        jp.naver.line.android.common.util.io.e.a(file, null, false);
    }
}
