package defpackage;

import java.util.concurrent.Executor;

/* renamed from: cmp */
public abstract class cmp<P, R> {
    public static final Void a = cpp.a;

    protected abstract cmq<P, R> a(cmp<R, ?> cmp, cmq<R, ?> cmq);

    protected abstract Executor b(P p);

    public final <S> cmp<P, S> a(cmp<R, S> cmp) {
        return new cng(this, cmp);
    }

    public final void a(P p) {
        Runnable a = a(null, null);
        a.a(p);
        a((Object) p, a);
    }

    public final void a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:cmp.a():void. bs: []
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
        r1 = this;
        r0 = a;	 Catch:{ ClassCastException -> 0x0006 }
        r1.a(r0);	 Catch:{ ClassCastException -> 0x0006 }
        return;
    L_0x0006:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cmp.a():void");
    }

    final void a(P p, Runnable runnable) {
        b(p).execute(runnable);
    }
}
