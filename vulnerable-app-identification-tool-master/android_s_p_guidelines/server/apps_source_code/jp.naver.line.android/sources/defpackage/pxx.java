package defpackage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: pxx */
public final class pxx {
    public static final boolean a;
    public static final int b;
    static final AtomicReference<ScheduledExecutorService> c = new AtomicReference();
    static final Map<ScheduledThreadPoolExecutor, Object> d = new ConcurrentHashMap();

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:pxx.<clinit>():void. bs: []
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
        r0 = new java.util.concurrent.atomic.AtomicReference;
        r0.<init>();
        c = r0;
        r0 = new java.util.concurrent.ConcurrentHashMap;
        r0.<init>();
        d = r0;
        r0 = java.lang.System.getProperties();
        r1 = new pxy;
        r1.<init>();
        r2 = "rx2.purge-enabled";
        r2 = r0.containsKey(r2);
        r3 = 1;
        if (r2 == 0) goto L_0x002d;
    L_0x0020:
        r2 = "rx2.purge-enabled";
        r2 = r0.getProperty(r2);
        r2 = java.lang.Boolean.parseBoolean(r2);
        r1.a = r2;
        goto L_0x002f;
    L_0x002d:
        r1.a = r3;
    L_0x002f:
        r2 = r1.a;
        if (r2 == 0) goto L_0x0048;
    L_0x0033:
        r2 = "rx2.purge-period-seconds";
        r2 = r0.containsKey(r2);
        if (r2 == 0) goto L_0x0048;
    L_0x003b:
        r2 = "rx2.purge-period-seconds";	 Catch:{ NumberFormatException -> 0x0048 }
        r0 = r0.getProperty(r2);	 Catch:{ NumberFormatException -> 0x0048 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0048 }
        r1.b = r0;	 Catch:{ NumberFormatException -> 0x0048 }
        goto L_0x004a;
    L_0x0048:
        r1.b = r3;
    L_0x004a:
        r0 = r1.a;
        a = r0;
        r0 = r1.b;
        b = r0;
        defpackage.pxx.a();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: pxx.<clinit>():void");
    }

    private static void a() {
        if (a) {
            while (true) {
                ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) c.get();
                if (scheduledExecutorService != null) {
                    break;
                }
                ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new pxs("RxSchedulerPurge"));
                if (c.compareAndSet(scheduledExecutorService, newScheduledThreadPool)) {
                    newScheduledThreadPool.scheduleAtFixedRate(new pxz(), (long) b, (long) b, TimeUnit.SECONDS);
                    return;
                }
                newScheduledThreadPool.shutdownNow();
            }
        }
    }

    public static ScheduledExecutorService a(ThreadFactory threadFactory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (a && (newScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            d.put((ScheduledThreadPoolExecutor) newScheduledThreadPool, newScheduledThreadPool);
        }
        return newScheduledThreadPool;
    }
}
