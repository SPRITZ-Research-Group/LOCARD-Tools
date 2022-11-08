package defpackage;

/* renamed from: acso */
public final class acso {
    private static final acsp a;
    private static final acua[] b = new acua[0];

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:acso.<clinit>():void. bs: []
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
        r0 = 0;
        r1 = "acxh";	 Catch:{ ClassCastException -> 0x000f, ClassCastException -> 0x000f, ClassCastException -> 0x000f, ClassCastException -> 0x000f }
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassCastException -> 0x000f, ClassCastException -> 0x000f, ClassCastException -> 0x000f, ClassCastException -> 0x000f }
        r1 = r1.newInstance();	 Catch:{ ClassCastException -> 0x000f, ClassCastException -> 0x000f, ClassCastException -> 0x000f, ClassCastException -> 0x000f }
        r1 = (defpackage.acsp) r1;	 Catch:{ ClassCastException -> 0x000f, ClassCastException -> 0x000f, ClassCastException -> 0x000f, ClassCastException -> 0x000f }
        r0 = r1;
        goto L_0x0010;
    L_0x0010:
        if (r0 == 0) goto L_0x0013;
    L_0x0012:
        goto L_0x0018;
    L_0x0013:
        r0 = new acsp;
        r0.<init>();
    L_0x0018:
        a = r0;
        r0 = 0;
        r0 = new defpackage.acua[r0];
        b = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: acso.<clinit>():void");
    }

    public static acuc a(Class cls, String str) {
        return a.a(cls, str);
    }

    public static acua a(Class cls) {
        return a.a(cls);
    }

    public static String a(acrz acrz) {
        return a.a(acrz);
    }

    public static String a(acrw acrw) {
        return a.a(acrw);
    }

    public static acud a(acrx acrx) {
        return a.a(acrx);
    }

    public static acuq a(acsf acsf) {
        return a.a(acsf);
    }

    public static acus a(acsh acsh) {
        return a.a(acsh);
    }

    public static acui a(acsb acsb) {
        return a.a(acsb);
    }
}
