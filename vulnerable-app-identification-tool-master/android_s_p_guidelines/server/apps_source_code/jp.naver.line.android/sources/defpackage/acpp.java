package defpackage;

import kotlin.Metadata;
import kotlin.v;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0001\u001a\"\u0010\b\u001a\u0002H\t\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\b¢\u0006\u0002\u0010\f\u001a\b\u0010\r\u001a\u00020\u0005H\u0002\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "apiVersionIsAtLeast", "", "major", "", "minor", "patch", "castToBaseType", "T", "", "instance", "(Ljava/lang/Object;)Ljava/lang/Object;", "getJavaVersion", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
/* renamed from: acpp */
public final class acpp {
    public static final acpo a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        acpo acpo;
        int a = acpp.a();
        if (a >= 65544) {
            Object newInstance = Class.forName("kotlin.internal.jdk8.JDK8PlatformImplementations").newInstance();
            if (newInstance != null) {
                try {
                    acpo = (acpo) newInstance;
                } catch (ClassCastException e) {
                    ClassLoader classLoader = newInstance.getClass().getClassLoader();
                    ClassLoader classLoader2 = acpo.class.getClassLoader();
                    StringBuilder stringBuilder = new StringBuilder("Instance classloader: ");
                    stringBuilder.append(classLoader);
                    stringBuilder.append(", base type classloader: ");
                    stringBuilder.append(classLoader2);
                    throw new ClassCastException(stringBuilder.toString()).initCause(e);
                }
                a = acpo;
            }
            throw new v("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
        }
        if (a >= 65543) {
            Object newInstance2 = Class.forName("acpr").newInstance();
            if (newInstance2 != null) {
                try {
                    acpo = (acpo) newInstance2;
                } catch (ClassCastException e2) {
                    ClassLoader classLoader3 = newInstance2.getClass().getClassLoader();
                    ClassLoader classLoader4 = acpo.class.getClassLoader();
                    StringBuilder stringBuilder2 = new StringBuilder("Instance classloader: ");
                    stringBuilder2.append(classLoader3);
                    stringBuilder2.append(", base type classloader: ");
                    stringBuilder2.append(classLoader4);
                    throw new ClassCastException(stringBuilder2.toString()).initCause(e2);
                }
                a = acpo;
            }
            throw new v("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
        }
        acpo = new acpo();
        a = acpo;
    }

    private static final int a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:acpp.a():int. bs: []
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
        r0 = "java.specification.version";
        r0 = java.lang.System.getProperty(r0);
        r1 = 65542; // 0x10006 float:9.1844E-41 double:3.2382E-319;
        if (r0 != 0) goto L_0x000c;
    L_0x000b:
        return r1;
    L_0x000c:
        r2 = r0;
        r2 = (java.lang.CharSequence) r2;
        r3 = 6;
        r4 = 0;
        r5 = 46;
        r3 = defpackage.addc.a(r2, r5, r4, r3);
        r6 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        if (r3 >= 0) goto L_0x0022;
    L_0x001b:
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0021 }
        r1 = r0 * r6;
    L_0x0021:
        return r1;
    L_0x0022:
        r7 = r3 + 1;
        r8 = 4;
        r2 = defpackage.addc.a(r2, r5, r7, r8);
        if (r2 >= 0) goto L_0x002f;
    L_0x002b:
        r2 = r0.length();
    L_0x002f:
        if (r0 == 0) goto L_0x0050;
    L_0x0031:
        r3 = r0.substring(r4, r3);
        if (r0 == 0) goto L_0x0048;
    L_0x0037:
        r0 = r0.substring(r7, r2);
        r2 = java.lang.Integer.parseInt(r3);	 Catch:{ NumberFormatException -> 0x0047 }
        r2 = r2 * r6;	 Catch:{ NumberFormatException -> 0x0047 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0047 }
        r1 = r2 + r0;
    L_0x0047:
        return r1;
    L_0x0048:
        r0 = new kotlin.v;
        r1 = "null cannot be cast to non-null type java.lang.String";
        r0.<init>(r1);
        throw r0;
    L_0x0050:
        r0 = new kotlin.v;
        r1 = "null cannot be cast to non-null type java.lang.String";
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: acpp.a():int");
    }
}
