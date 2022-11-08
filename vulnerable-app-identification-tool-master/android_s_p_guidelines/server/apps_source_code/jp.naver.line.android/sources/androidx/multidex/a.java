package androidx.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.util.Log;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class a {
    private static final Set<File> a = new HashSet();
    private static final boolean b = a(System.getProperty("java.vm.version"));

    public static void a(Context context) {
        Log.i("MultiDex", "Installing application");
        if (b) {
            Log.i("MultiDex", "VM has multidex support, MultiDex support library is disabled.");
        } else if (VERSION.SDK_INT >= 4) {
            try {
                ApplicationInfo c = c(context);
                if (c == null) {
                    Log.i("MultiDex", "No ApplicationInfo available, i.e. running on a test Context: MultiDex support library is disabled.");
                    return;
                }
                a(context, new File(c.sourceDir), new File(c.dataDir), "secondary-dexes", "");
                Log.i("MultiDex", "install done");
            } catch (Throwable e) {
                Log.e("MultiDex", "MultiDex installation failure", e);
                StringBuilder stringBuilder = new StringBuilder("MultiDex installation failed (");
                stringBuilder.append(e.getMessage());
                stringBuilder.append(").");
                throw new RuntimeException(stringBuilder.toString());
            }
        } else {
            StringBuilder stringBuilder2 = new StringBuilder("MultiDex installation failed. SDK ");
            stringBuilder2.append(VERSION.SDK_INT);
            stringBuilder2.append(" is unsupported. Min SDK version is 4.");
            throw new RuntimeException(stringBuilder2.toString());
        }
    }

    private static void a(android.content.Context r5, java.io.File r6, java.io.File r7, java.lang.String r8, java.lang.String r9) throws java.io.IOException, java.lang.IllegalArgumentException, java.lang.IllegalAccessException, java.lang.NoSuchFieldException, java.lang.reflect.InvocationTargetException, java.lang.NoSuchMethodException, java.lang.SecurityException, java.lang.ClassNotFoundException, java.lang.InstantiationException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.multidex.a.a(android.content.Context, java.io.File, java.io.File, java.lang.String, java.lang.String):void. bs: []
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
        r0 = a;
        monitor-enter(r0);
        r1 = a;	 Catch:{ all -> 0x0086 }
        r1 = r1.contains(r6);	 Catch:{ all -> 0x0086 }
        if (r1 == 0) goto L_0x000d;	 Catch:{ all -> 0x0086 }
    L_0x000b:
        monitor-exit(r0);	 Catch:{ all -> 0x0086 }
        return;	 Catch:{ all -> 0x0086 }
    L_0x000d:
        r1 = a;	 Catch:{ all -> 0x0086 }
        r1.add(r6);	 Catch:{ all -> 0x0086 }
        r1 = android.os.Build.VERSION.SDK_INT;	 Catch:{ all -> 0x0086 }
        r2 = 20;	 Catch:{ all -> 0x0086 }
        if (r1 <= r2) goto L_0x0040;	 Catch:{ all -> 0x0086 }
    L_0x0018:
        r1 = "MultiDex";	 Catch:{ all -> 0x0086 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0086 }
        r3 = "MultiDex is not guaranteed to work in SDK version ";	 Catch:{ all -> 0x0086 }
        r2.<init>(r3);	 Catch:{ all -> 0x0086 }
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ all -> 0x0086 }
        r2.append(r3);	 Catch:{ all -> 0x0086 }
        r3 = ": SDK version higher than 20 should be backed by runtime with built-in multidex capabilty but it's not the case here: java.vm.version=\"";	 Catch:{ all -> 0x0086 }
        r2.append(r3);	 Catch:{ all -> 0x0086 }
        r3 = "java.vm.version";	 Catch:{ all -> 0x0086 }
        r3 = java.lang.System.getProperty(r3);	 Catch:{ all -> 0x0086 }
        r2.append(r3);	 Catch:{ all -> 0x0086 }
        r3 = "\"";	 Catch:{ all -> 0x0086 }
        r2.append(r3);	 Catch:{ all -> 0x0086 }
        r2 = r2.toString();	 Catch:{ all -> 0x0086 }
        android.util.Log.w(r1, r2);	 Catch:{ all -> 0x0086 }
    L_0x0040:
        r1 = b(r5);	 Catch:{ all -> 0x0086 }
        if (r1 != 0) goto L_0x0048;	 Catch:{ all -> 0x0086 }
    L_0x0046:
        monitor-exit(r0);	 Catch:{ all -> 0x0086 }
        return;
    L_0x0048:
        d(r5);	 Catch:{ Throwable -> 0x004c }
        goto L_0x0054;
    L_0x004c:
        r2 = move-exception;
        r3 = "MultiDex";	 Catch:{ all -> 0x0086 }
        r4 = "Something went wrong when trying to clear old MultiDex extraction, continuing without cleaning.";	 Catch:{ all -> 0x0086 }
        android.util.Log.w(r3, r4, r2);	 Catch:{ all -> 0x0086 }
    L_0x0054:
        r7 = a(r5, r7, r8);	 Catch:{ all -> 0x0086 }
        r8 = new androidx.multidex.i;	 Catch:{ all -> 0x0086 }
        r8.<init>(r6, r7);	 Catch:{ all -> 0x0086 }
        r6 = 0;
        r2 = 0;
        r2 = r8.a(r5, r9, r2);	 Catch:{ all -> 0x0081 }
        a(r1, r7, r2);	 Catch:{ IOException -> 0x0067 }
        goto L_0x0077;
    L_0x0067:
        r2 = move-exception;
        r3 = "MultiDex";	 Catch:{ all -> 0x0081 }
        r4 = "Failed to install extracted secondary dex files, retrying with forced extraction";	 Catch:{ all -> 0x0081 }
        android.util.Log.w(r3, r4, r2);	 Catch:{ all -> 0x0081 }
        r2 = 1;	 Catch:{ all -> 0x0081 }
        r5 = r8.a(r5, r9, r2);	 Catch:{ all -> 0x0081 }
        a(r1, r7, r5);	 Catch:{ all -> 0x0081 }
    L_0x0077:
        r8.close();	 Catch:{ IOException -> 0x007b }
        goto L_0x007c;
    L_0x007b:
        r6 = move-exception;
    L_0x007c:
        if (r6 != 0) goto L_0x0080;
    L_0x007e:
        monitor-exit(r0);	 Catch:{ all -> 0x0086 }
        return;	 Catch:{ all -> 0x0086 }
    L_0x0080:
        throw r6;	 Catch:{ all -> 0x0086 }
    L_0x0081:
        r5 = move-exception;
        r8.close();	 Catch:{ IOException -> 0x0085 }
    L_0x0085:
        throw r5;	 Catch:{ all -> 0x0086 }
    L_0x0086:
        r5 = move-exception;	 Catch:{ all -> 0x0086 }
        monitor-exit(r0);	 Catch:{ all -> 0x0086 }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.multidex.a.a(android.content.Context, java.io.File, java.io.File, java.lang.String, java.lang.String):void");
    }

    private static ClassLoader b(Context context) {
        try {
            ClassLoader classLoader = context.getClassLoader();
            if (VERSION.SDK_INT >= 14) {
                if (classLoader instanceof BaseDexClassLoader) {
                    return classLoader;
                }
            } else if ((classLoader instanceof DexClassLoader) || (classLoader instanceof PathClassLoader)) {
                return classLoader;
            }
            Log.e("MultiDex", "Context class loader is null or not dex-capable. Must be running in test mode. Skip patching.");
            return null;
        } catch (Throwable e) {
            Log.w("MultiDex", "Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching.", e);
            return null;
        }
    }

    private static ApplicationInfo c(Context context) {
        try {
            return context.getApplicationInfo();
        } catch (Throwable e) {
            Log.w("MultiDex", "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", e);
            return null;
        }
    }

    private static boolean a(java.lang.String r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.multidex.a.a(java.lang.String):boolean. bs: []
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
        if (r5 == 0) goto L_0x0035;
    L_0x0003:
        r1 = new java.util.StringTokenizer;
        r2 = ".";
        r1.<init>(r5, r2);
        r2 = r1.hasMoreTokens();
        r3 = 0;
        if (r2 == 0) goto L_0x0016;
    L_0x0011:
        r2 = r1.nextToken();
        goto L_0x0017;
    L_0x0016:
        r2 = r3;
    L_0x0017:
        r4 = r1.hasMoreTokens();
        if (r4 == 0) goto L_0x0021;
    L_0x001d:
        r3 = r1.nextToken();
    L_0x0021:
        if (r2 == 0) goto L_0x0035;
    L_0x0023:
        if (r3 == 0) goto L_0x0035;
    L_0x0025:
        r1 = java.lang.Integer.parseInt(r2);	 Catch:{ NumberFormatException -> 0x0035 }
        r2 = java.lang.Integer.parseInt(r3);	 Catch:{ NumberFormatException -> 0x0035 }
        r3 = 2;
        if (r1 > r3) goto L_0x0034;
    L_0x0030:
        if (r1 != r3) goto L_0x0035;
    L_0x0032:
        if (r2 <= 0) goto L_0x0035;
    L_0x0034:
        r0 = 1;
    L_0x0035:
        r1 = "MultiDex";
        r2 = new java.lang.StringBuilder;
        r3 = "VM with version ";
        r2.<init>(r3);
        r2.append(r5);
        if (r0 == 0) goto L_0x0046;
    L_0x0043:
        r5 = " has multidex support";
        goto L_0x0048;
    L_0x0046:
        r5 = " does not have multidex support";
    L_0x0048:
        r2.append(r5);
        r5 = r2.toString();
        android.util.Log.i(r1, r5);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.multidex.a.a(java.lang.String):boolean");
    }

    private static void a(ClassLoader classLoader, File file, List<? extends File> list) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException, SecurityException, ClassNotFoundException, InstantiationException {
        if (!list.isEmpty()) {
            if (VERSION.SDK_INT >= 19) {
                g.a(classLoader, list, file);
            } else if (VERSION.SDK_INT >= 14) {
                b.a(classLoader, list);
            } else {
                h.a(classLoader, list);
            }
        }
    }

    private static java.lang.reflect.Field b(java.lang.Object r3, java.lang.String r4) throws java.lang.NoSuchFieldException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.multidex.a.b(java.lang.Object, java.lang.String):java.lang.reflect.Field. bs: []
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
        r0 = r3.getClass();
    L_0x0004:
        if (r0 == 0) goto L_0x001a;
    L_0x0006:
        r1 = r0.getDeclaredField(r4);	 Catch:{ NoSuchFieldException -> 0x0015 }
        r2 = r1.isAccessible();	 Catch:{ NoSuchFieldException -> 0x0015 }
        if (r2 != 0) goto L_0x0014;	 Catch:{ NoSuchFieldException -> 0x0015 }
    L_0x0010:
        r2 = 1;	 Catch:{ NoSuchFieldException -> 0x0015 }
        r1.setAccessible(r2);	 Catch:{ NoSuchFieldException -> 0x0015 }
    L_0x0014:
        return r1;
    L_0x0015:
        r0 = r0.getSuperclass();
        goto L_0x0004;
    L_0x001a:
        r0 = new java.lang.NoSuchFieldException;
        r1 = new java.lang.StringBuilder;
        r2 = "Field ";
        r1.<init>(r2);
        r1.append(r4);
        r4 = " not found in ";
        r1.append(r4);
        r3 = r3.getClass();
        r1.append(r3);
        r3 = r1.toString();
        r0.<init>(r3);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.multidex.a.b(java.lang.Object, java.lang.String):java.lang.reflect.Field");
    }

    private static java.lang.reflect.Method b(java.lang.Object r3, java.lang.String r4, java.lang.Class<?>... r5) throws java.lang.NoSuchMethodException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.multidex.a.b(java.lang.Object, java.lang.String, java.lang.Class[]):java.lang.reflect.Method. bs: []
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
        r0 = r3.getClass();
    L_0x0004:
        if (r0 == 0) goto L_0x001a;
    L_0x0006:
        r1 = r0.getDeclaredMethod(r4, r5);	 Catch:{ NoSuchMethodException -> 0x0015 }
        r2 = r1.isAccessible();	 Catch:{ NoSuchMethodException -> 0x0015 }
        if (r2 != 0) goto L_0x0014;	 Catch:{ NoSuchMethodException -> 0x0015 }
    L_0x0010:
        r2 = 1;	 Catch:{ NoSuchMethodException -> 0x0015 }
        r1.setAccessible(r2);	 Catch:{ NoSuchMethodException -> 0x0015 }
    L_0x0014:
        return r1;
    L_0x0015:
        r0 = r0.getSuperclass();
        goto L_0x0004;
    L_0x001a:
        r0 = new java.lang.NoSuchMethodException;
        r1 = new java.lang.StringBuilder;
        r2 = "Method ";
        r1.<init>(r2);
        r1.append(r4);
        r4 = " with parameters ";
        r1.append(r4);
        r4 = java.util.Arrays.asList(r5);
        r1.append(r4);
        r4 = " not found in ";
        r1.append(r4);
        r3 = r3.getClass();
        r1.append(r3);
        r3 = r1.toString();
        r0.<init>(r3);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.multidex.a.b(java.lang.Object, java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    private static void d(Context context) throws Exception {
        File file = new File(context.getFilesDir(), "secondary-dexes");
        if (file.isDirectory()) {
            StringBuilder stringBuilder = new StringBuilder("Clearing old secondary dex dir (");
            stringBuilder.append(file.getPath());
            stringBuilder.append(").");
            Log.i("MultiDex", stringBuilder.toString());
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                stringBuilder = new StringBuilder("Failed to list secondary dex dir content (");
                stringBuilder.append(file.getPath());
                stringBuilder.append(").");
                Log.w("MultiDex", stringBuilder.toString());
                return;
            }
            for (File file2 : listFiles) {
                StringBuilder stringBuilder2 = new StringBuilder("Trying to delete old file ");
                stringBuilder2.append(file2.getPath());
                stringBuilder2.append(" of size ");
                stringBuilder2.append(file2.length());
                Log.i("MultiDex", stringBuilder2.toString());
                if (file2.delete()) {
                    stringBuilder2 = new StringBuilder("Deleted old file ");
                    stringBuilder2.append(file2.getPath());
                    Log.i("MultiDex", stringBuilder2.toString());
                } else {
                    stringBuilder2 = new StringBuilder("Failed to delete old file ");
                    stringBuilder2.append(file2.getPath());
                    Log.w("MultiDex", stringBuilder2.toString());
                }
            }
            if (file.delete()) {
                stringBuilder = new StringBuilder("Deleted old secondary dex dir ");
                stringBuilder.append(file.getPath());
                Log.i("MultiDex", stringBuilder.toString());
            } else {
                stringBuilder = new StringBuilder("Failed to delete secondary dex dir ");
                stringBuilder.append(file.getPath());
                Log.w("MultiDex", stringBuilder.toString());
            }
        }
    }

    private static java.io.File a(android.content.Context r2, java.io.File r3, java.lang.String r4) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.multidex.a.a(android.content.Context, java.io.File, java.lang.String):java.io.File. bs: []
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
        r0 = new java.io.File;
        r1 = "code_cache";
        r0.<init>(r3, r1);
        a(r0);	 Catch:{ IOException -> 0x000b }
        goto L_0x0019;
    L_0x000b:
        r0 = new java.io.File;
        r2 = r2.getFilesDir();
        r3 = "code_cache";
        r0.<init>(r2, r3);
        a(r0);
    L_0x0019:
        r2 = new java.io.File;
        r2.<init>(r0, r4);
        a(r2);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.multidex.a.a(android.content.Context, java.io.File, java.lang.String):java.io.File");
    }

    private static void a(File file) throws IOException {
        file.mkdir();
        if (!file.isDirectory()) {
            StringBuilder stringBuilder;
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                StringBuilder stringBuilder2 = new StringBuilder("Failed to create dir ");
                stringBuilder2.append(file.getPath());
                stringBuilder2.append(". Parent file is null.");
                Log.e("MultiDex", stringBuilder2.toString());
            } else {
                stringBuilder = new StringBuilder("Failed to create dir ");
                stringBuilder.append(file.getPath());
                stringBuilder.append(". parent file is a dir ");
                stringBuilder.append(parentFile.isDirectory());
                stringBuilder.append(", a file ");
                stringBuilder.append(parentFile.isFile());
                stringBuilder.append(", exists ");
                stringBuilder.append(parentFile.exists());
                stringBuilder.append(", readable ");
                stringBuilder.append(parentFile.canRead());
                stringBuilder.append(", writable ");
                stringBuilder.append(parentFile.canWrite());
                Log.e("MultiDex", stringBuilder.toString());
            }
            stringBuilder = new StringBuilder("Failed to create directory ");
            stringBuilder.append(file.getPath());
            throw new IOException(stringBuilder.toString());
        }
    }

    static /* synthetic */ void a(Object obj, String str, Object[] objArr) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field b = b(obj, str);
        Object[] objArr2 = (Object[]) b.get(obj);
        Object[] objArr3 = (Object[]) Array.newInstance(objArr2.getClass().getComponentType(), objArr2.length + objArr.length);
        System.arraycopy(objArr2, 0, objArr3, 0, objArr2.length);
        System.arraycopy(objArr, 0, objArr3, objArr2.length, objArr.length);
        b.set(obj, objArr3);
    }
}
