package defpackage;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.v;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u000bR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lkotlin/reflect/jvm/internal/structure/Java8ParameterNamesLoader;", "", "()V", "cache", "Lkotlin/reflect/jvm/internal/structure/Java8ParameterNamesLoader$Cache;", "getCache", "()Lkotlin/reflect/jvm/internal/structure/Java8ParameterNamesLoader$Cache;", "setCache", "(Lkotlin/reflect/jvm/internal/structure/Java8ParameterNamesLoader$Cache;)V", "buildCache", "member", "Ljava/lang/reflect/Member;", "loadParameterNames", "", "", "Cache", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* renamed from: aczz */
final class aczz {
    public static final aczz a = new aczz();
    private static adaa b;

    private aczz() {
    }

    private static defpackage.adaa b(java.lang.reflect.Member r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:aczz.b(java.lang.reflect.Member):adaa. bs: []
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
        r4 = r4.getClass();
        r0 = "getParameters";	 Catch:{ NoSuchMethodException -> 0x0025 }
        r1 = 0;	 Catch:{ NoSuchMethodException -> 0x0025 }
        r2 = new java.lang.Class[r1];	 Catch:{ NoSuchMethodException -> 0x0025 }
        r0 = r4.getMethod(r0, r2);	 Catch:{ NoSuchMethodException -> 0x0025 }
        r4 = defpackage.adab.a(r4);
        r2 = "java.lang.reflect.Parameter";
        r4 = r4.loadClass(r2);
        r2 = new adaa;
        r3 = "getName";
        r1 = new java.lang.Class[r1];
        r4 = r4.getMethod(r3, r1);
        r2.<init>(r0, r4);
        return r2;
    L_0x0025:
        r4 = new adaa;
        r0 = 0;
        r4.<init>(r0, r0);
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: aczz.b(java.lang.reflect.Member):adaa");
    }

    public static List<String> a(Member member) {
        adaa adaa = b;
        if (adaa == null) {
            adaa = aczz.b(member);
            b = adaa;
        }
        Method a = adaa.a();
        if (a == null) {
            return null;
        }
        Method b = adaa.b();
        if (b == null) {
            return null;
        }
        Object invoke = a.invoke(member, new Object[0]);
        if (invoke != null) {
            Object[] objArr = (Object[]) invoke;
            Collection arrayList = new ArrayList(objArr.length);
            int length = objArr.length;
            int i = 0;
            while (i < length) {
                Object invoke2 = b.invoke(objArr[i], new Object[0]);
                if (invoke2 != null) {
                    arrayList.add((String) invoke2);
                    i++;
                } else {
                    throw new v("null cannot be cast to non-null type kotlin.String");
                }
            }
            return (List) arrayList;
        }
        throw new v("null cannot be cast to non-null type kotlin.Array<*>");
    }
}
