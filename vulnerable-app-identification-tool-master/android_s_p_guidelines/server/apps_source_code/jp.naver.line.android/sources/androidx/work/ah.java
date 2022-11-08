package androidx.work;

public abstract class ah {
    private static final String a = p.a("WorkerFactory");

    public static androidx.work.ListenableWorker a(android.content.Context r7, java.lang.String r8, androidx.work.WorkerParameters r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.work.ah.a(android.content.Context, java.lang.String, androidx.work.WorkerParameters):androidx.work.ListenableWorker. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = 0;
        r1 = 0;
        r2 = java.lang.Class.forName(r8);	 Catch:{ ClassNotFoundException -> 0x0042 }
        r3 = androidx.work.ListenableWorker.class;	 Catch:{ ClassNotFoundException -> 0x0042 }
        r2 = r2.asSubclass(r3);	 Catch:{ ClassNotFoundException -> 0x0042 }
        r3 = 2;
        r4 = 1;
        r5 = new java.lang.Class[r3];	 Catch:{ Exception -> 0x0029 }
        r6 = android.content.Context.class;	 Catch:{ Exception -> 0x0029 }
        r5[r1] = r6;	 Catch:{ Exception -> 0x0029 }
        r6 = androidx.work.WorkerParameters.class;	 Catch:{ Exception -> 0x0029 }
        r5[r4] = r6;	 Catch:{ Exception -> 0x0029 }
        r2 = r2.getDeclaredConstructor(r5);	 Catch:{ Exception -> 0x0029 }
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0029 }
        r3[r1] = r7;	 Catch:{ Exception -> 0x0029 }
        r3[r4] = r9;	 Catch:{ Exception -> 0x0029 }
        r7 = r2.newInstance(r3);	 Catch:{ Exception -> 0x0029 }
        r7 = (androidx.work.ListenableWorker) r7;	 Catch:{ Exception -> 0x0029 }
        return r7;
    L_0x0029:
        r7 = move-exception;
        r9 = androidx.work.p.a();
        r2 = a;
        r3 = "Could not instantiate ";
        r8 = java.lang.String.valueOf(r8);
        r8 = r3.concat(r8);
        r3 = new java.lang.Throwable[r4];
        r3[r1] = r7;
        r9.b(r2, r8, r3);
        return r0;
    L_0x0042:
        r7 = androidx.work.p.a();
        r9 = a;
        r2 = "Class not found: ";
        r8 = java.lang.String.valueOf(r8);
        r8 = r2.concat(r8);
        r1 = new java.lang.Throwable[r1];
        r7.b(r9, r8, r1);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.ah.a(android.content.Context, java.lang.String, androidx.work.WorkerParameters):androidx.work.ListenableWorker");
    }

    public static ah a() {
        return new ah() {
        };
    }
}
