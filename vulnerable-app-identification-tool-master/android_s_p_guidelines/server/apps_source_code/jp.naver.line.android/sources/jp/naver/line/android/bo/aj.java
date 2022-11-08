package jp.naver.line.android.bo;

import android.text.TextUtils;
import defpackage.adfz;
import defpackage.srp;
import defpackage.twj;
import defpackage.twm;
import defpackage.tyu;
import defpackage.vkx;
import defpackage.ysh;
import defpackage.zrl;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import jp.naver.line.android.db.main.model.ag;
import jp.naver.line.android.model.z;

public final class aj {
    public static final Pattern a = Pattern.compile(String.format(Locale.ENGLISH, "^[@]?[a-zA-Z0-9-_.]{%d,%d}", new Object[]{Integer.valueOf(4), Integer.valueOf(20)}));
    public static final Pattern b = Pattern.compile(String.format(Locale.ENGLISH, "^@[a-zA-Z0-9-_.]{%d,%d}", new Object[]{Integer.valueOf(4), Integer.valueOf(20)}));
    public static final Pattern c = Pattern.compile("[a-zA-Z0-9-_.]+@[a-zA-Z0-9-_.]+");
    public static final Pattern d = Pattern.compile("^@[a-zA-Z0-9-_.].*");
    public static final Pattern e = Pattern.compile("[a-zA-Z0-9-_.]+@[a-zA-Z0-9-_.]+.*");
    private final tyu f = new tyu();
    private final srp g = new srp();

    public static boolean a(String str) {
        return str.contains("@") || str.startsWith("#");
    }

    public static boolean b(String str) {
        return str.startsWith("@") || str.startsWith("#");
    }

    private static boolean i(String str) {
        int length = str.length();
        return length >= 4 && length <= 120;
    }

    public static boolean c(String str) {
        return str != null && (a.matcher(str).matches() || (c.matcher(str).matches() && i(str)));
    }

    public static boolean d(String str) {
        return b.matcher(str).matches() || (c.matcher(str).matches() && i(str));
    }

    public static boolean e(String str) {
        return d.matcher(str).matches() || e.matcher(str).matches();
    }

    public static z f(String str) throws zrl, adfz {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Set hashSet = new HashSet();
        hashSet.add(str);
        Map a = vkx.a().a(hashSet);
        if (a != null) {
            return a((ysh) a.get(str), null);
        }
        return null;
    }

    public static jp.naver.line.android.model.z g(java.lang.String r6) throws defpackage.zrl, defpackage.adfz {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.bo.aj.g(java.lang.String):jp.naver.line.android.model.z. bs: []
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
        r0 = defpackage.vkx.a();
        r6 = r0.c(r6);
        r0 = 0;
        if (r6 == 0) goto L_0x0067;
    L_0x000b:
        r1 = r6.p;
        if (r1 == 0) goto L_0x0067;
    L_0x000f:
        r1 = defpackage.twm.MAIN;
        r1 = defpackage.twj.b(r1);
        r2 = r6.a;
        r1 = defpackage.tyu.f(r1, r2);
        r2 = 0;
        r3 = 1;
        if (r1 == 0) goto L_0x003d;
    L_0x001f:
        r4 = r1.f();
        if (r4 != 0) goto L_0x003e;
    L_0x0025:
        r4 = r1.H();
        if (r4 == 0) goto L_0x003d;
    L_0x002b:
        r5 = jp.naver.line.android.db.main.model.z.OFFICIAL;
        if (r4 == r5) goto L_0x003d;
    L_0x002f:
        r5 = jp.naver.line.android.db.main.model.z.LINE_AT_OLD;
        if (r4 != r5) goto L_0x0034;
    L_0x0033:
        goto L_0x003d;
    L_0x0034:
        r3 = jp.naver.line.android.db.main.model.z.LINE_AT;
        if (r4 != r3) goto L_0x003e;
    L_0x0038:
        r0 = r1.H();
        goto L_0x003e;
    L_0x003d:
        r2 = 1;
    L_0x003e:
        if (r2 == 0) goto L_0x0067;
    L_0x0040:
        r2 = defpackage.vkx.h();
        r3 = r6.a;
        r2 = r2.b(r3);
        if (r2 == 0) goto L_0x0067;
    L_0x004c:
        r0 = r2.n;
        r0 = jp.naver.line.android.db.main.model.z.a(r0);
        r3 = jp.naver.line.android.db.main.model.z.LINE_AT;
        if (r0 != r3) goto L_0x0067;
    L_0x0056:
        r3 = defpackage.twm.MAIN;	 Catch:{ Exception -> 0x0067 }
        r3 = defpackage.twj.a(r3);	 Catch:{ Exception -> 0x0067 }
        if (r1 != 0) goto L_0x0061;	 Catch:{ Exception -> 0x0067 }
    L_0x005e:
        defpackage.tvc.c(r6, r3);	 Catch:{ Exception -> 0x0067 }
    L_0x0061:
        defpackage.tyu.a(r3, r2);	 Catch:{ Exception -> 0x0067 }
        defpackage.srp.a(r2);	 Catch:{ Exception -> 0x0067 }
    L_0x0067:
        r6 = a(r6, r0);
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.bo.aj.g(java.lang.String):jp.naver.line.android.model.z");
    }

    private static z a(ysh ysh, jp.naver.line.android.db.main.model.z zVar) {
        if (ysh == null) {
            return null;
        }
        z zVar2 = new z();
        zVar2.e(ysh.f);
        zVar2.f(ysh.f);
        zVar2.b(ysh.b);
        zVar2.a(ysh.a);
        zVar2.j(ysh.h);
        zVar2.k(ysh.s);
        zVar2.a(ysh.e);
        zVar2.a(ysh.c);
        zVar2.d(ysh.j);
        zVar2.a(ag.a(ysh));
        zVar2.a(zVar);
        return zVar2;
    }

    public static void h(String str) {
        tyu.o(twj.a(twm.MAIN), str);
    }
}
