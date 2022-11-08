package jp.naver.line.android.bo;

import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;
import android.util.Pair;
import defpackage.adfz;
import defpackage.suh;
import defpackage.tvc;
import defpackage.twj;
import defpackage.twm;
import defpackage.tyu;
import defpackage.vkx;
import defpackage.ysh;
import defpackage.zrl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import jp.naver.line.android.common.e;
import jp.naver.line.android.db.main.model.ContactDto;
import jp.naver.line.android.db.main.model.ac;
import jp.naver.line.android.db.main.model.ag;
import jp.naver.line.android.model.z;

public final class x {
    private static final tyu a = new tyu();

    public static boolean a(int i) {
        return i < 111;
    }

    private x() {
    }

    public static final z a(String str) {
        return tyu.n(twj.b(twm.MAIN), str);
    }

    public static List<z> a(List<String> list) {
        return tyu.c(twj.b(twm.MAIN), (List) list);
    }

    public static HashSet<String> b(List<String> list) {
        return tyu.b(twj.b(twm.MAIN), (List) list);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final ContactDto b(String str) throws adfz {
        try {
            return c(str);
        } catch (adfz e) {
            throw e;
        }
    }

    public static final ContactDto c(String str) throws adfz, zrl {
        ContactDto f = tyu.f(twj.b(twm.MAIN), str);
        if (f != null) {
            return f;
        }
        ysh h = vkx.a().h(str);
        if (h == null) {
            return f;
        }
        tvc.b(h, twj.a(twm.MAIN));
        return ag.b(h);
    }

    public static Cursor d(String str) {
        return tyu.a(twj.b(twm.MAIN), str, null, Boolean.FALSE, new ac[]{ac.NORMAL});
    }

    public static Cursor a(Collection<String> collection) {
        return tyu.d(twj.b(twm.MAIN), (Collection) collection);
    }

    public static Cursor e(String str) {
        return tyu.a(str);
    }

    public static Cursor b(Collection<String> collection) {
        return tyu.a((Collection) collection);
    }

    public static List<suh> f(String str) {
        List<suh> arrayList = new ArrayList();
        Cursor b = tyu.b(str);
        if (b == null) {
            return arrayList;
        }
        CharSequence charSequence = null;
        while (b.moveToNext()) {
            CharSequence string = b.getString(b.getColumnIndex("_id"));
            if (!TextUtils.equals(string, charSequence)) {
                arrayList.add(new suh(string, b.getString(b.getColumnIndex("display_name"))));
                charSequence = string;
            }
        }
        b.close();
        return arrayList;
    }

    public static List<Pair<String, String>> g(String str) {
        List<Pair<String, String>> arrayList = new ArrayList();
        Cursor a = tyu.a(Long.valueOf(str).longValue());
        if (a == null) {
            return arrayList;
        }
        while (a.moveToNext()) {
            arrayList.add(new Pair(a.getString(a.getColumnIndex("data1")), Phone.getTypeLabel(e.c().getResources(), a.getInt(a.getColumnIndex("data2")), a.getString(a.getColumnIndex("data3"))).toString()));
        }
        a.close();
        return arrayList;
    }

    public static boolean h(String str) {
        return tyu.k(twj.a(twm.MAIN), str) > 0;
    }

    public static void a(jp.naver.line.android.db.main.model.ContactDto r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.bo.x.a(jp.naver.line.android.db.main.model.ContactDto):void. bs: []
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
        if (r5 == 0) goto L_0x0048;
    L_0x0002:
        r0 = r5.a();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x0048;
    L_0x000c:
        r0 = r5.i();
        if (r0 != 0) goto L_0x0048;
    L_0x0012:
        r0 = r5.S();
        r2 = r5.K();
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 >= 0) goto L_0x001f;
    L_0x001e:
        goto L_0x0048;
    L_0x001f:
        r0 = defpackage.uoa.h();
        if (r0 == 0) goto L_0x0034;
    L_0x0025:
        r0 = r0.m();
        r1 = r5.a();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0034;
    L_0x0033:
        return;
    L_0x0034:
        r0 = defpackage.tvr.a();	 Catch:{ Exception -> 0x0047 }
        r1 = 0;	 Catch:{ Exception -> 0x0047 }
        r2 = 1;	 Catch:{ Exception -> 0x0047 }
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x0047 }
        r3 = 0;	 Catch:{ Exception -> 0x0047 }
        r5 = r5.a();	 Catch:{ Exception -> 0x0047 }
        r2[r3] = r5;	 Catch:{ Exception -> 0x0047 }
        r0.a(r1, r2);	 Catch:{ Exception -> 0x0047 }
        return;
    L_0x0047:
        return;
    L_0x0048:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.bo.x.a(jp.naver.line.android.db.main.model.ContactDto):void");
    }

    public static List<suh> a() {
        List<suh> arrayList = new ArrayList();
        Cursor a = tyu.a();
        if (a == null) {
            return arrayList;
        }
        while (a.moveToNext()) {
            arrayList.add(new suh(a.getString(a.getColumnIndex("_id")), a.getString(a.getColumnIndex("display_name"))));
        }
        a.close();
        return arrayList;
    }
}
