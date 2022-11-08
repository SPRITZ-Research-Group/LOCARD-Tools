package jp.naver.line.android.activity.pushdialog;

import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.rxeventbus.a;
import java.util.concurrent.atomic.AtomicInteger;
import jp.naver.line.android.util.at;

public final class e {
    private final d a;
    private final Handler b;
    private final AtomicInteger c = new AtomicInteger(0);
    private final AtomicInteger d = new AtomicInteger(0);
    private Cursor e = null;
    private g f = null;
    private f g = null;

    protected e(d dVar, Handler handler) {
        this.a = dVar;
        this.b = handler;
        a e = dVar.e();
        if (e != null) {
            e.b(this);
        }
    }

    protected final void a() {
        if (this.f != null) {
            try {
                if (!this.f.isCancelled()) {
                    this.f.cancel(true);
                }
                this.f = null;
            } catch (Throwable th) {
                this.f = null;
            }
        }
        c();
        this.f = new g(this, this.c.incrementAndGet());
        this.f.executeOnExecutor(at.b(), new Void[0]);
    }

    private void c() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.pushdialog.e.c():void. bs: []
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
        r0 = r2.e;
        if (r0 == 0) goto L_0x0015;
    L_0x0004:
        monitor-enter(r2);
        r0 = r2.e;	 Catch:{ all -> 0x0012 }
        r1 = 0;	 Catch:{ all -> 0x0012 }
        r2.e = r1;	 Catch:{ all -> 0x0012 }
        monitor-exit(r2);	 Catch:{ all -> 0x0012 }
        if (r0 != 0) goto L_0x000e;
    L_0x000d:
        return;
    L_0x000e:
        r0.close();	 Catch:{ Throwable -> 0x0015 }
        return;
    L_0x0012:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0012 }
        throw r0;
    L_0x0015:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.pushdialog.e.c():void");
    }

    final void a(int i) {
        if (this.g != null) {
            if (!this.g.isCancelled()) {
                this.g.cancel(true);
            }
            this.g = null;
        }
        if (this.c.get() == this.d.get()) {
            boolean z = !j.d() || j.e();
            this.g = new f(this, z);
            this.g.executeOnExecutor(at.b(), new Integer[]{Integer.valueOf(i)});
        }
    }

    private jp.naver.line.android.activity.pushdialog.r b(int r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.pushdialog.e.b(int):jp.naver.line.android.activity.pushdialog.r. bs: []
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
        r3 = this;
        if (r4 < 0) goto L_0x001c;
    L_0x0002:
        r0 = r3.e;	 Catch:{ Exception -> 0x001c }
        if (r0 == 0) goto L_0x001c;	 Catch:{ Exception -> 0x001c }
    L_0x0006:
        r0 = r3.e;	 Catch:{ Exception -> 0x001c }
        r0 = r0.moveToPosition(r4);	 Catch:{ Exception -> 0x001c }
        if (r0 == 0) goto L_0x001c;	 Catch:{ Exception -> 0x001c }
    L_0x000e:
        r0 = new jp.naver.line.android.activity.pushdialog.r;	 Catch:{ Exception -> 0x001c }
        r1 = r3.a;	 Catch:{ Exception -> 0x001c }
        r1 = r1.c();	 Catch:{ Exception -> 0x001c }
        r2 = r3.e;	 Catch:{ Exception -> 0x001c }
        r0.<init>(r1, r2, r4);	 Catch:{ Exception -> 0x001c }
        return r0;
    L_0x001c:
        r4 = 0;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.pushdialog.e.b(int):jp.naver.line.android.activity.pushdialog.r");
    }

    final void b() {
        c();
        this.e = null;
        if (this.f != null) {
            if (!this.f.isCancelled()) {
                this.f.cancel(true);
            }
            this.f = null;
        }
        if (this.g != null) {
            if (!this.g.isCancelled()) {
                this.g.cancel(true);
            }
            this.g = null;
        }
        a e = this.a.e();
        if (e != null) {
            e.c(this);
        }
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onPushStatusChanged(t tVar) {
        if (tVar.a == 11) {
            a();
        }
    }

    static /* synthetic */ void a(e eVar, Cursor cursor) throws IllegalArgumentException {
        if (cursor == null || cursor.isClosed() || !cursor.moveToFirst()) {
            throw new IllegalArgumentException("There's a problem with requested Cursor");
        }
        int count = cursor.getCount();
        synchronized (eVar) {
            eVar.e = cursor;
            j.f = count;
        }
    }

    static /* synthetic */ void a(e eVar, int i, int i2) {
        if (eVar.b != null) {
            eVar.b.sendMessage(Message.obtain(eVar.b, i, i2, 0));
        }
    }
}
