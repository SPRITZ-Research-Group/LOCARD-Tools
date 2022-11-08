package addon.greenrobot.eventbus;

import defpackage.b;
import defpackage.c;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

final class s {
    private static final Map<Class<?>, List<r>> a = new ConcurrentHashMap();
    private static final t[] e = new t[4];
    private List<c> b;
    private final boolean c;
    private final boolean d;

    s(List<c> list, boolean z, boolean z2) {
        this.b = list;
        this.c = z;
        this.d = z2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final List<r> a(Class<?> cls) {
        List<r> list = (List) a.get(cls);
        if (list != null) {
            return list;
        }
        t a;
        if (this.d) {
            a = a();
            a.a(cls);
            while (a.f != null) {
                b(a);
                a.a();
            }
            list = a(a);
        } else {
            a = a();
            a.a(cls);
            while (a.f != null) {
                b c;
                if (!(a.h == null || a.h.c() == null)) {
                    c = a.h.c();
                }
                if (this.b != null) {
                    for (c a2 : this.b) {
                        b a3 = a2.a();
                        if (a3 != null) {
                            c = a3;
                            break;
                        }
                    }
                }
                c = null;
                a.h = c;
                if (a.h != null) {
                    for (r rVar : a.h.b()) {
                        if (a.a(rVar.a, rVar.c)) {
                            a.a.add(rVar);
                        }
                    }
                } else {
                    b(a);
                }
                a.a();
            }
            list = a(a);
        }
        if (list.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("Subscriber ");
            stringBuilder.append(cls);
            stringBuilder.append(" and its super classes have no public methods with the @Subscribe annotation");
            throw new f(stringBuilder.toString());
        }
        a.put(cls, list);
        return list;
    }

    private static List<r> a(t tVar) {
        List arrayList = new ArrayList(tVar.a);
        tVar.a.clear();
        tVar.b.clear();
        tVar.c.clear();
        int i = 0;
        tVar.d.setLength(0);
        tVar.e = null;
        tVar.f = null;
        tVar.g = false;
        tVar.h = null;
        synchronized (e) {
            while (i < 4) {
                if (e[i] == null) {
                    e[i] = tVar;
                    break;
                }
                i++;
            }
        }
        return arrayList;
    }

    private static t a() {
        synchronized (e) {
            for (int i = 0; i < 4; i++) {
                t tVar = e[i];
                if (tVar != null) {
                    e[i] = null;
                    return tVar;
                }
            }
            return new t();
        }
    }

    private void b(addon.greenrobot.eventbus.t r15) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:addon.greenrobot.eventbus.s.b(addon.greenrobot.eventbus.t):void. bs: []
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
        r14 = this;
        r0 = 1;
        r1 = r15.f;	 Catch:{ Throwable -> 0x0008 }
        r1 = r1.getDeclaredMethods();	 Catch:{ Throwable -> 0x0008 }
        goto L_0x0010;
    L_0x0008:
        r1 = r15.f;
        r1 = r1.getMethods();
        r15.g = r0;
    L_0x0010:
        r2 = r1.length;
        r3 = 0;
        r4 = 0;
    L_0x0013:
        if (r4 >= r2) goto L_0x00ec;
    L_0x0015:
        r6 = r1[r4];
        r5 = r6.getModifiers();
        r7 = r5 & 1;
        if (r7 == 0) goto L_0x00a4;
    L_0x001f:
        r5 = r5 & 5192;
        if (r5 != 0) goto L_0x00a4;
    L_0x0023:
        r5 = r6.getParameterTypes();
        r7 = r5.length;
        if (r7 != r0) goto L_0x0059;
    L_0x002a:
        r7 = addon.greenrobot.eventbus.p.class;
        r7 = r6.getAnnotation(r7);
        r7 = (addon.greenrobot.eventbus.p) r7;
        if (r7 == 0) goto L_0x00e8;
    L_0x0034:
        r8 = r5[r3];
        r5 = r15.a(r6, r8);
        if (r5 == 0) goto L_0x00e8;
    L_0x003c:
        r9 = r7.a();
        r11 = r15.a;
        r12 = new addon.greenrobot.eventbus.r;
        r10 = r7.c();
        r13 = r7.b();
        r5 = r12;
        r7 = r8;
        r8 = r9;
        r9 = r10;
        r10 = r13;
        r5.<init>(r6, r7, r8, r9, r10);
        r11.add(r12);
        goto L_0x00e8;
    L_0x0059:
        r7 = r14.c;
        if (r7 == 0) goto L_0x00e8;
    L_0x005d:
        r7 = addon.greenrobot.eventbus.p.class;
        r7 = r6.isAnnotationPresent(r7);
        if (r7 != 0) goto L_0x0067;
    L_0x0065:
        goto L_0x00e8;
    L_0x0067:
        r15 = new java.lang.StringBuilder;
        r15.<init>();
        r0 = r6.getDeclaringClass();
        r0 = r0.getName();
        r15.append(r0);
        r0 = ".";
        r15.append(r0);
        r0 = r6.getName();
        r15.append(r0);
        r15 = r15.toString();
        r0 = new addon.greenrobot.eventbus.f;
        r1 = new java.lang.StringBuilder;
        r2 = "@Subscribe method ";
        r1.<init>(r2);
        r1.append(r15);
        r15 = "must have exactly 1 parameter but has ";
        r1.append(r15);
        r15 = r5.length;
        r1.append(r15);
        r15 = r1.toString();
        r0.<init>(r15);
        throw r0;
    L_0x00a4:
        r5 = r14.c;
        if (r5 == 0) goto L_0x00e8;
    L_0x00a8:
        r5 = addon.greenrobot.eventbus.p.class;
        r5 = r6.isAnnotationPresent(r5);
        if (r5 != 0) goto L_0x00b1;
    L_0x00b0:
        goto L_0x00e8;
    L_0x00b1:
        r15 = new java.lang.StringBuilder;
        r15.<init>();
        r0 = r6.getDeclaringClass();
        r0 = r0.getName();
        r15.append(r0);
        r0 = ".";
        r15.append(r0);
        r0 = r6.getName();
        r15.append(r0);
        r15 = r15.toString();
        r0 = new addon.greenrobot.eventbus.f;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r1.append(r15);
        r15 = " is a illegal @Subscribe method: must be public, non-static, and non-abstract";
        r1.append(r15);
        r15 = r1.toString();
        r0.<init>(r15);
        throw r0;
    L_0x00e8:
        r4 = r4 + 1;
        goto L_0x0013;
    L_0x00ec:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: addon.greenrobot.eventbus.s.b(addon.greenrobot.eventbus.t):void");
    }
}
