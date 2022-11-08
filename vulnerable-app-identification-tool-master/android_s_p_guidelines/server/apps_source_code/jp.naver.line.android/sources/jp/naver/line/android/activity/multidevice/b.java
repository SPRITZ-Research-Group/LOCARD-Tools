package jp.naver.line.android.activity.multidevice;

public class b {
    private static b a;

    private b() {
    }

    public static b a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    public static void b() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.multidevice.b.b():void. bs: []
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
        r0 = jp.naver.line.android.common.e.a();
        if (r0 == 0) goto L_0x006d;
    L_0x0006:
        jp.naver.line.android.activity.multidevice.c.a();
        r0 = jp.naver.line.android.activity.multidevice.c.b();
        if (r0 == 0) goto L_0x006c;
    L_0x000f:
        jp.naver.line.android.activity.multidevice.c.a();
        r0 = jp.naver.line.android.activity.multidevice.c.c();
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x006c;
    L_0x001c:
        r1 = r0.length();
        r2 = 18;
        if (r1 <= r2) goto L_0x006c;
    L_0x0024:
        r1 = new jp.naver.line.android.e2ee.k;
        r2 = "line://au/desktop/";
        r1.<init>(r0, r2);
        r0 = jp.naver.line.android.l.a();	 Catch:{ ueo -> 0x005b, uem -> 0x0043 }
        r0 = r0.getApplicationContext();	 Catch:{ ueo -> 0x005b, uem -> 0x0043 }
        r2 = r1.a();	 Catch:{ ueo -> 0x005b, uem -> 0x0043 }
        r3 = r1.b();	 Catch:{ ueo -> 0x005b, uem -> 0x0043 }
        r4 = r1.c();	 Catch:{ ueo -> 0x005b, uem -> 0x0043 }
        jp.naver.line.android.activity.multidevice.DesktopLoginActivity.a(r0, r2, r3, r4);	 Catch:{ ueo -> 0x005b, uem -> 0x0043 }
        return;
    L_0x0043:
        jp.naver.line.android.activity.multidevice.c.a();
        jp.naver.line.android.activity.multidevice.c.d();
        jp.naver.line.android.e2ee.f.a();
        r0 = r1.a();
        jp.naver.line.android.e2ee.f.h(r0);
        r0 = jp.naver.line.android.e2ee.f.a();
        r0.e();
        goto L_0x006c;
    L_0x005b:
        jp.naver.line.android.activity.multidevice.c.a();
        jp.naver.line.android.activity.multidevice.c.d();
        jp.naver.line.android.e2ee.f.a();
        r0 = r1.a();
        jp.naver.line.android.e2ee.f.g(r0);
        return;
    L_0x006c:
        return;
    L_0x006d:
        r0 = jp.naver.line.android.common.access.remote.e.a();
        r0.f();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.multidevice.b.b():void");
    }
}
