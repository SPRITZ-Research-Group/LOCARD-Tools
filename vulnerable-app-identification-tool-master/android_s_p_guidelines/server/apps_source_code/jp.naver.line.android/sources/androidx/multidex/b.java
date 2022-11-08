package androidx.multidex;

import android.util.Log;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

final class b {
    private static final int a = 4;
    private final c b;

    static void a(ClassLoader classLoader, List<? extends File> list) throws IOException, SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Object obj = a.b(classLoader, "pathList").get(classLoader);
        b bVar = new b();
        Object[] objArr = new Object[list.size()];
        for (int i = 0; i < objArr.length; i++) {
            File file = (File) list.get(i);
            c cVar = bVar.b;
            String path = file.getPath();
            File parentFile = file.getParentFile();
            String name = file.getName();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(name.substring(0, name.length() - a));
            stringBuilder.append(".dex");
            objArr[i] = cVar.a(file, DexFile.loadDex(path, new File(parentFile, stringBuilder.toString()).getPath(), 0));
        }
        try {
            a.a(obj, "dexElements", objArr);
        } catch (Throwable e) {
            Log.w("MultiDex", "Failed find field 'dexElements' attempting 'pathElements'", e);
            a.a(obj, "pathElements", objArr);
        }
    }

    private b() throws java.lang.ClassNotFoundException, java.lang.SecurityException, java.lang.NoSuchMethodException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.multidex.b.<init>():void. bs: []
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
        r2.<init>();
        r0 = "dalvik.system.DexPathList$Element";
        r0 = java.lang.Class.forName(r0);
        r1 = new androidx.multidex.d;	 Catch:{ NoSuchMethodException -> 0x000f }
        r1.<init>(r0);	 Catch:{ NoSuchMethodException -> 0x000f }
        goto L_0x001a;
    L_0x000f:
        r1 = new androidx.multidex.e;	 Catch:{ NoSuchMethodException -> 0x0015 }
        r1.<init>(r0);	 Catch:{ NoSuchMethodException -> 0x0015 }
        goto L_0x001a;
    L_0x0015:
        r1 = new androidx.multidex.f;
        r1.<init>(r0);
    L_0x001a:
        r2.b = r1;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.multidex.b.<init>():void");
    }
}
