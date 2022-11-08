package jp.naver.line.android.activity.helper;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.IntentFilter;
import defpackage.srf;
import defpackage.tfq;
import defpackage.uac;
import jp.naver.line.android.R;
import jp.naver.line.android.model.cl;
import jp.naver.line.android.util.at;
import jp.naver.line.android.util.au;

public class c {
    private static e a;
    private static Activity b;
    private static d c;
    private static boolean d;
    private static boolean e;

    private c() {
    }

    public static synchronized void a() {
        synchronized (c.class) {
            e = true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void b() {
        synchronized (c.class) {
            if (b == null || c == null) {
            } else if (a == null && e && !d) {
                a = new e();
                at.a(au.BASEACTIVITY, a);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(Activity activity) {
        synchronized (c.class) {
            if (activity != null) {
                if (c == null && b == null) {
                    c = new d();
                    b = activity;
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("LINE.Application.Low.Storage");
                    srf.a(activity, c, intentFilter);
                }
            }
        }
    }

    public static synchronized void b(Activity activity) {
        synchronized (c.class) {
            if (b != activity) {
                return;
            }
            srf.a(b, c);
            c = null;
            b = null;
            d = false;
        }
    }

    private static synchronized boolean h() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.helper.c.h():boolean. bs: []
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
        r0 = jp.naver.line.android.activity.helper.c.class;
        monitor-enter(r0);
        r1 = 1;
        r2 = defpackage.uac.a();	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        r3 = b;	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        r4 = 0;	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        if (r3 == 0) goto L_0x0037;	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
    L_0x000d:
        r3 = c;	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        if (r3 != 0) goto L_0x0012;	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
    L_0x0011:
        goto L_0x0037;	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
    L_0x0012:
        r3 = d;	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        if (r3 == 0) goto L_0x0018;
    L_0x0016:
        monitor-exit(r0);
        return r4;
    L_0x0018:
        r3 = e;	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        if (r3 != 0) goto L_0x001e;
    L_0x001c:
        monitor-exit(r0);
        return r4;
    L_0x001e:
        r5 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        r3 = jp.naver.line.android.model.cl.STORAGE_NOT_AVAILABLE_LAST_CHECK_TIME;	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        r7 = 0;	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        r2 = r2.b(r3, r7);	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        r7 = 0;
        r5 = r5 - r2;
        r2 = 259200000; // 0xf731400 float:1.1984677E-29 double:1.280618154E-315;
        r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1));
        if (r7 > 0) goto L_0x0035;
    L_0x0033:
        monitor-exit(r0);
        return r4;
    L_0x0035:
        monitor-exit(r0);
        return r1;
    L_0x0037:
        monitor-exit(r0);
        return r4;
    L_0x0039:
        r1 = move-exception;
        monitor-exit(r0);
        throw r1;
    L_0x003c:
        monitor-exit(r0);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.helper.c.h():boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void e() {
        if (h()) {
            tfq tfq = new tfq(b);
            tfq.a(true);
            tfq.a((int) R.string.storage_warn_dialog_title);
            tfq.b((int) R.string.storage_warn_dialog_content);
            tfq.a((int) R.string.confirm, new OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    long currentTimeMillis = System.currentTimeMillis();
                    synchronized (c.class) {
                        try {
                            uac.a().a(cl.STORAGE_NOT_AVAILABLE_LAST_CHECK_TIME, currentTimeMillis);
                            c.e = false;
                        } catch (Throwable th) {
                        }
                    }
                    dialogInterface.dismiss();
                }
            });
            tfq.a(new OnDismissListener() {
                public final void onDismiss(DialogInterface dialogInterface) {
                    synchronized (c.class) {
                        c.d = false;
                    }
                }
            });
            synchronized (c.class) {
                try {
                    d = true;
                } catch (Throwable th) {
                    while (true) {
                    }
                }
            }
            tfq.f();
        }
    }
}
