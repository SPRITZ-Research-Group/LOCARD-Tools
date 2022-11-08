package defpackage;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

/* renamed from: ozs */
public final class ozs extends RuntimeException {
    private static final long serialVersionUID = 3026362227162912146L;
    private final List<Throwable> a;
    private final String b;
    private Throwable c;

    public ozs(Throwable... thArr) {
        this(Arrays.asList(thArr));
    }

    public ozs(Iterable<? extends Throwable> iterable) {
        Collection linkedHashSet = new LinkedHashSet();
        List arrayList = new ArrayList();
        if (iterable != null) {
            for (Throwable th : iterable) {
                if (th instanceof ozs) {
                    linkedHashSet.addAll(((ozs) th).a);
                } else if (th != null) {
                    linkedHashSet.add(th);
                } else {
                    linkedHashSet.add(new NullPointerException("Throwable was null!"));
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException("errors was null"));
        }
        if (linkedHashSet.isEmpty()) {
            throw new IllegalArgumentException("errors is empty");
        }
        arrayList.addAll(linkedHashSet);
        this.a = Collections.unmodifiableList(arrayList);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a.size());
        stringBuilder.append(" exceptions occurred. ");
        this.b = stringBuilder.toString();
    }

    public final String getMessage() {
        return this.b;
    }

    public final synchronized java.lang.Throwable getCause() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:ozs.getCause():java.lang.Throwable. bs: []
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
        r8 = this;
        monitor-enter(r8);
        r0 = r8.c;	 Catch:{ all -> 0x005f }
        if (r0 != 0) goto L_0x005b;	 Catch:{ all -> 0x005f }
    L_0x0005:
        r0 = new ozt;	 Catch:{ all -> 0x005f }
        r0.<init>();	 Catch:{ all -> 0x005f }
        r1 = new java.util.HashSet;	 Catch:{ all -> 0x005f }
        r1.<init>();	 Catch:{ all -> 0x005f }
        r2 = r8.a;	 Catch:{ all -> 0x005f }
        r2 = r2.iterator();	 Catch:{ all -> 0x005f }
        r3 = r0;	 Catch:{ all -> 0x005f }
    L_0x0016:
        r4 = r2.hasNext();	 Catch:{ all -> 0x005f }
        if (r4 == 0) goto L_0x0059;	 Catch:{ all -> 0x005f }
    L_0x001c:
        r4 = r2.next();	 Catch:{ all -> 0x005f }
        r4 = (java.lang.Throwable) r4;	 Catch:{ all -> 0x005f }
        r5 = r1.contains(r4);	 Catch:{ all -> 0x005f }
        if (r5 != 0) goto L_0x0016;	 Catch:{ all -> 0x005f }
    L_0x0028:
        r1.add(r4);	 Catch:{ all -> 0x005f }
        r5 = defpackage.ozs.a(r4);	 Catch:{ all -> 0x005f }
        r5 = r5.iterator();	 Catch:{ all -> 0x005f }
    L_0x0033:
        r6 = r5.hasNext();	 Catch:{ all -> 0x005f }
        if (r6 == 0) goto L_0x0051;	 Catch:{ all -> 0x005f }
    L_0x0039:
        r6 = r5.next();	 Catch:{ all -> 0x005f }
        r6 = (java.lang.Throwable) r6;	 Catch:{ all -> 0x005f }
        r7 = r1.contains(r6);	 Catch:{ all -> 0x005f }
        if (r7 == 0) goto L_0x004d;	 Catch:{ all -> 0x005f }
    L_0x0045:
        r4 = new java.lang.RuntimeException;	 Catch:{ all -> 0x005f }
        r6 = "Duplicate found in causal chain so cropping to prevent loop ...";	 Catch:{ all -> 0x005f }
        r4.<init>(r6);	 Catch:{ all -> 0x005f }
        goto L_0x0033;	 Catch:{ all -> 0x005f }
    L_0x004d:
        r1.add(r6);	 Catch:{ all -> 0x005f }
        goto L_0x0033;
    L_0x0051:
        r3.initCause(r4);	 Catch:{ Throwable -> 0x0054 }
    L_0x0054:
        r3 = defpackage.ozs.b(r3);	 Catch:{ all -> 0x005f }
        goto L_0x0016;	 Catch:{ all -> 0x005f }
    L_0x0059:
        r8.c = r0;	 Catch:{ all -> 0x005f }
    L_0x005b:
        r0 = r8.c;	 Catch:{ all -> 0x005f }
        monitor-exit(r8);
        return r0;
    L_0x005f:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: ozs.getCause():java.lang.Throwable");
    }

    public final void printStackTrace() {
        printStackTrace(System.err);
    }

    public final void printStackTrace(PrintStream printStream) {
        a(new ozv(printStream));
    }

    public final void printStackTrace(PrintWriter printWriter) {
        a(new ozw(printWriter));
    }

    private void a(ozu ozu) {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append(this);
        stringBuilder.append(10);
        for (Object obj : getStackTrace()) {
            stringBuilder.append("\tat ");
            stringBuilder.append(obj);
            stringBuilder.append(10);
        }
        int i = 1;
        for (Throwable th : this.a) {
            stringBuilder.append("  ComposedException ");
            stringBuilder.append(i);
            stringBuilder.append(" :\n");
            ozs.a(stringBuilder, th, "\t");
            i++;
        }
        ozu.a(stringBuilder.toString());
    }

    private static void a(StringBuilder stringBuilder, Throwable th, String str) {
        while (true) {
            stringBuilder.append(str);
            stringBuilder.append(th);
            stringBuilder.append(10);
            for (Object obj : th.getStackTrace()) {
                stringBuilder.append("\t\tat ");
                stringBuilder.append(obj);
                stringBuilder.append(10);
            }
            if (th.getCause() != null) {
                stringBuilder.append("\tCaused by: ");
                th = th.getCause();
                str = "";
            } else {
                return;
            }
        }
    }

    private static List<Throwable> a(Throwable th) {
        List<Throwable> arrayList = new ArrayList();
        Throwable cause = th.getCause();
        if (cause == null || cause == th) {
            return arrayList;
        }
        while (true) {
            arrayList.add(cause);
            th = cause.getCause();
            if (th == null || th == cause) {
                return arrayList;
            }
            cause = th;
        }
        return arrayList;
    }

    private static Throwable b(Throwable th) {
        Throwable cause = th.getCause();
        if (cause == null || th == cause) {
            return th;
        }
        while (true) {
            th = cause.getCause();
            if (th == null || th == cause) {
                return cause;
            }
            cause = th;
        }
        return cause;
    }
}
