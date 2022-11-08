package com.facebook.soloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.text.TextUtils;
import android.util.Log;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SoLoader {
    static final boolean DEBUG = false;
    public static final int SOLOADER_ALLOW_ASYNC_INIT = 2;
    public static final int SOLOADER_ENABLE_EXOPACKAGE = 1;
    public static final int SOLOADER_LOOK_IN_ZIP = 4;
    private static final String SO_STORE_NAME_MAIN = "lib-main";
    static final boolean SYSTRACE_LIBRARY_LOADING;
    static final String TAG = "SoLoader";
    private static ApplicationSoSource sApplicationSoSource;
    private static UnpackingSoSource sBackupSoSource;
    private static int sFlags;
    private static final Set<String> sLoadedAndMergedLibraries = Collections.newSetFromMap(new ConcurrentHashMap());
    private static final HashSet<String> sLoadedLibraries = new HashSet();
    private static final Map<String, Object> sLoadingLibraries = new HashMap();
    static SoFileLoader sSoFileLoader;
    private static SoSource[] sSoSources = null;
    private static final ReentrantReadWriteLock sSoSourcesLock = new ReentrantReadWriteLock();
    private static int sSoSourcesVersion = 0;
    private static SystemLoadLibraryWrapper sSystemLoadLibraryWrapper = null;

    @DoNotOptimize
    @TargetApi(14)
    class Api14Utils {
        private Api14Utils() {
        }

        public static String getClassLoaderLdLoadLibrary() {
            ClassLoader classLoader = SoLoader.class.getClassLoader();
            if (classLoader instanceof BaseDexClassLoader) {
                try {
                    return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke((BaseDexClassLoader) classLoader, new Object[0]);
                } catch (Throwable e) {
                    throw new RuntimeException("Cannot call getLdLibraryPath", e);
                }
            }
            StringBuilder stringBuilder = new StringBuilder("ClassLoader ");
            stringBuilder.append(classLoader.getClass().getName());
            stringBuilder.append(" should be of type BaseDexClassLoader");
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    public final class WrongAbiError extends UnsatisfiedLinkError {
        WrongAbiError(Throwable th) {
            super("APK was built for a different platform");
            initCause(th);
        }
    }

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.soloader.SoLoader.<clinit>():void. bs: []
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
        r0 = new java.util.concurrent.locks.ReentrantReadWriteLock;
        r0.<init>();
        sSoSourcesLock = r0;
        r0 = 0;
        sSoSources = r0;
        r1 = 0;
        sSoSourcesVersion = r1;
        r2 = new java.util.HashSet;
        r2.<init>();
        sLoadedLibraries = r2;
        r2 = new java.util.HashMap;
        r2.<init>();
        sLoadingLibraries = r2;
        r2 = new java.util.concurrent.ConcurrentHashMap;
        r2.<init>();
        r2 = java.util.Collections.newSetFromMap(r2);
        sLoadedAndMergedLibraries = r2;
        sSystemLoadLibraryWrapper = r0;
        r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ NoClassDefFoundError -> 0x0030, NoClassDefFoundError -> 0x0030 }
        r2 = 18;
        if (r0 < r2) goto L_0x0030;
    L_0x002e:
        r0 = 1;
        r1 = 1;
    L_0x0030:
        SYSTRACE_LIBRARY_LOADING = r1;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.<clinit>():void");
    }

    public static void init(Context context, int i) throws IOException {
        init(context, i, null);
    }

    private static void init(Context context, int i, SoFileLoader soFileLoader) throws IOException {
        ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            initSoLoader(soFileLoader);
            initSoSources(context, i, soFileLoader);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        }
    }

    public static void init(Context context, boolean z) {
        try {
            init(context, (int) z);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static void initSoSources(Context context, int i, SoFileLoader soFileLoader) throws IOException {
        sSoSourcesLock.writeLock().lock();
        try {
            if (sSoSources == null) {
                sFlags = i;
                ArrayList arrayList = new ArrayList();
                String str = System.getenv("LD_LIBRARY_PATH");
                if (str == null) {
                    str = "/vendor/lib:/system/lib";
                }
                String[] split = str.split(":");
                for (int i2 = 0; i2 < split.length; i2++) {
                    new StringBuilder("adding system library source: ").append(split[i2]);
                    arrayList.add(new DirectorySoSource(new File(split[i2]), 2));
                }
                if (context != null) {
                    if ((i & 1) != 0) {
                        sBackupSoSource = null;
                        arrayList.add(0, new ExoSoSource(context, SO_STORE_NAME_MAIN));
                    } else {
                        ApplicationInfo applicationInfo = context.getApplicationInfo();
                        Object obj = ((applicationInfo.flags & 1) == 0 || (applicationInfo.flags & 128) != 0) ? null : 1;
                        if (obj != null) {
                            i = 0;
                        } else {
                            sApplicationSoSource = new ApplicationSoSource(context, VERSION.SDK_INT <= 17 ? 1 : 0);
                            new StringBuilder("adding application source: ").append(sApplicationSoSource.toString());
                            arrayList.add(0, sApplicationSoSource);
                            i = 1;
                        }
                        sBackupSoSource = new ApkSoSource(context, SO_STORE_NAME_MAIN, i);
                        new StringBuilder("adding backup  source: ").append(sBackupSoSource.toString());
                        arrayList.add(0, sBackupSoSource);
                    }
                }
                SoSource[] soSourceArr = (SoSource[]) arrayList.toArray(new SoSource[arrayList.size()]);
                i = makePrepareFlags();
                int length = soSourceArr.length;
                while (true) {
                    int i3 = length - 1;
                    if (length <= 0) {
                        break;
                    }
                    new StringBuilder("Preparing SO source: ").append(soSourceArr[i3]);
                    soSourceArr[i3].prepare(i);
                    length = i3;
                }
                sSoSources = soSourceArr;
                sSoSourcesVersion++;
                StringBuilder stringBuilder = new StringBuilder("init finish: ");
                stringBuilder.append(sSoSources.length);
                stringBuilder.append(" SO sources prepared");
            }
            sSoSourcesLock.writeLock().unlock();
        } catch (Throwable th) {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    private static int makePrepareFlags() {
        sSoSourcesLock.writeLock().lock();
        try {
            int i = (sFlags & 2) != 0 ? 1 : 0;
            sSoSourcesLock.writeLock().unlock();
            return i;
        } catch (Throwable th) {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    private static synchronized void initSoLoader(SoFileLoader soFileLoader) {
        synchronized (SoLoader.class) {
            if (soFileLoader != null) {
                sSoFileLoader = soFileLoader;
                return;
            }
            final Runtime runtime = Runtime.getRuntime();
            final Method nativeLoadRuntimeMethod = getNativeLoadRuntimeMethod();
            final boolean z = nativeLoadRuntimeMethod != null;
            final String classLoaderLdLoadLibrary = z ? Api14Utils.getClassLoaderLdLoadLibrary() : null;
            final String makeNonZipPath = makeNonZipPath(classLoaderLdLoadLibrary);
            sSoFileLoader = new SoFileLoader() {
                /* JADX WARNING: inconsistent code. */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final void load(String str, int i) {
                    Throwable th;
                    String concat;
                    StringBuilder stringBuilder;
                    if (z) {
                        String str2 = ((i & 4) == 4 ? 1 : null) != null ? classLoaderLdLoadLibrary : makeNonZipPath;
                        String str3 = null;
                        try {
                            synchronized (runtime) {
                                try {
                                    String str4;
                                    if (VERSION.SDK_INT <= 27) {
                                        str4 = (String) nativeLoadRuntimeMethod.invoke(runtime, new Object[]{str, SoLoader.class.getClassLoader(), str2});
                                    } else {
                                        str4 = (String) nativeLoadRuntimeMethod.invoke(runtime, new Object[]{str, SoLoader.class.getClassLoader()});
                                    }
                                    if (str4 == null) {
                                        try {
                                        } catch (Throwable th2) {
                                            String str5 = str4;
                                            th = th2;
                                            str3 = str5;
                                            throw th;
                                        }
                                    } else {
                                        throw new UnsatisfiedLinkError(str4);
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    throw th;
                                }
                            }
                        } catch (Throwable th4) {
                            concat = "Error: Cannot load ".concat(String.valueOf(str));
                            throw new RuntimeException(concat, th4);
                        } catch (Throwable th5) {
                            th4 = th5;
                            str3 = concat;
                            if (str3 != null) {
                                stringBuilder = new StringBuilder("Error when loading lib: ");
                                stringBuilder.append(str3);
                                stringBuilder.append(" lib hash: ");
                                stringBuilder.append(getLibHash(str));
                                stringBuilder.append(" search path is ");
                                stringBuilder.append(str2);
                                Log.e(SoLoader.TAG, stringBuilder.toString());
                            }
                            throw th4;
                        }
                    }
                    System.load(str);
                }

                private String getLibHash(String str) {
                    Throwable th;
                    InputStream fileInputStream;
                    try {
                        File file = new File(str);
                        MessageDigest instance = MessageDigest.getInstance("MD5");
                        fileInputStream = new FileInputStream(file);
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read > 0) {
                                instance.update(bArr, 0, read);
                            } else {
                                str = String.format("%32x", new Object[]{new BigInteger(1, instance.digest())});
                                fileInputStream.close();
                                return str;
                            }
                        }
                    } catch (IOException e) {
                        return e.toString();
                    } catch (NoSuchAlgorithmException e2) {
                        return e2.toString();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            };
        }
    }

    private static Method getNativeLoadRuntimeMethod() {
        if (VERSION.SDK_INT < 23) {
            return null;
        }
        try {
            Method declaredMethod;
            if (VERSION.SDK_INT <= 27) {
                declaredMethod = Runtime.class.getDeclaredMethod("nativeLoad", new Class[]{String.class, ClassLoader.class, String.class});
            } else {
                declaredMethod = Runtime.class.getDeclaredMethod("nativeLoad", new Class[]{String.class, ClassLoader.class});
            }
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable e) {
            Log.w(TAG, "Cannot get nativeLoad method", e);
            return null;
        }
    }

    public static void setInTestMode() {
        setSoSources(new SoSource[]{new NoopSoSource()});
    }

    public static void deinitForTest() {
        setSoSources(null);
    }

    static void setSoSources(SoSource[] soSourceArr) {
        sSoSourcesLock.writeLock().lock();
        try {
            sSoSources = soSourceArr;
            sSoSourcesVersion++;
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    static void setSoFileLoader(SoFileLoader soFileLoader) {
        sSoFileLoader = soFileLoader;
    }

    static void resetStatus() {
        synchronized (SoLoader.class) {
            sLoadedLibraries.clear();
            sLoadingLibraries.clear();
            sSoFileLoader = null;
        }
        setSoSources(null);
    }

    public static void setSystemLoadLibraryWrapper(SystemLoadLibraryWrapper systemLoadLibraryWrapper) {
        sSystemLoadLibraryWrapper = systemLoadLibraryWrapper;
    }

    public static boolean loadLibrary(String str) {
        return loadLibrary(str, 0);
    }

    public static boolean loadLibrary(String str, int i) throws UnsatisfiedLinkError {
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources == null) {
                if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    assertInitialized();
                } else {
                    boolean contains;
                    synchronized (SoLoader.class) {
                        contains = sLoadedLibraries.contains(str) ^ 1;
                        if (contains) {
                            if (sSystemLoadLibraryWrapper != null) {
                                sSystemLoadLibraryWrapper.loadLibrary(str);
                            } else {
                                System.loadLibrary(str);
                            }
                        }
                    }
                    sSoSourcesLock.readLock().unlock();
                    return contains;
                }
            }
            sSoSourcesLock.readLock().unlock();
            String mapLibName = MergedSoMapping.mapLibName(str);
            return loadLibraryBySoName(System.mapLibraryName(mapLibName != null ? mapLibName : str), str, mapLibName, i, null);
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
        }
    }

    static void loadLibraryBySoName(String str, int i, ThreadPolicy threadPolicy) {
        loadLibraryBySoName(str, null, null, i, threadPolicy);
    }

    public static Set<String> getLoadedLibraries() {
        HashSet hashSet;
        synchronized (SoLoader.class) {
            hashSet = (HashSet) sLoadedLibraries.clone();
        }
        return hashSet;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean loadLibraryBySoName(String str, String str2, String str3, int i, ThreadPolicy threadPolicy) {
        if (!TextUtils.isEmpty(str2) && sLoadedAndMergedLibraries.contains(str2)) {
            return false;
        }
        synchronized (SoLoader.class) {
            Object obj;
            if (!sLoadedLibraries.contains(str)) {
                obj = null;
            } else if (str3 == null) {
                return false;
            } else {
                obj = 1;
            }
            Object obj2;
            if (sLoadingLibraries.containsKey(str)) {
                obj2 = sLoadingLibraries.get(str);
            } else {
                obj2 = new Object();
                sLoadingLibraries.put(str, obj2);
            }
        }
    }

    public static File unpackLibraryAndDependencies(String str) throws UnsatisfiedLinkError {
        assertInitialized();
        try {
            return unpackLibraryBySoName(System.mapLibraryName(str));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static void doLoadLibraryBySoName(String str, int i, ThreadPolicy threadPolicy) throws IOException {
        Throwable th;
        UnsatisfiedLinkError unsatisfiedLinkError;
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources != null) {
                Object obj;
                if (threadPolicy == null) {
                    threadPolicy = StrictMode.allowThreadDiskReads();
                    obj = 1;
                } else {
                    obj = null;
                }
                if (SYSTRACE_LIBRARY_LOADING) {
                    StringBuilder stringBuilder = new StringBuilder("SoLoader.loadLibrary[");
                    stringBuilder.append(str);
                    stringBuilder.append("]");
                    Api18TraceUtils.beginTraceSection(stringBuilder.toString());
                }
                int i2 = 0;
                while (true) {
                    try {
                        Object obj2;
                        sSoSourcesLock.readLock().lock();
                        int i3 = sSoSourcesVersion;
                        int i4 = 0;
                        while (i2 == 0) {
                            try {
                                if (i4 >= sSoSources.length) {
                                    break;
                                }
                                int loadLibrary = sSoSources[i4].loadLibrary(str, i, threadPolicy);
                                if (loadLibrary == 3) {
                                    try {
                                        if (sBackupSoSource != null) {
                                            sBackupSoSource.prepare(str);
                                            i2 = sBackupSoSource.loadLibrary(str, i, threadPolicy);
                                            break;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        i2 = loadLibrary;
                                    }
                                }
                                i4++;
                                i2 = loadLibrary;
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        }
                        sSoSourcesLock.readLock().unlock();
                        if (i2 == 0) {
                            sSoSourcesLock.writeLock().lock();
                            if (sApplicationSoSource != null && sApplicationSoSource.checkAndMaybeUpdate()) {
                                sSoSourcesVersion++;
                            }
                            obj2 = sSoSourcesVersion != i3 ? 1 : null;
                            sSoSourcesLock.writeLock().unlock();
                            continue;
                        } else {
                            obj2 = null;
                            continue;
                        }
                        if (obj2 == null) {
                            if (SYSTRACE_LIBRARY_LOADING) {
                                Api18TraceUtils.endSection();
                            }
                            if (obj != null) {
                                StrictMode.setThreadPolicy(threadPolicy);
                            }
                            if (i2 == 0 || i2 == 3) {
                                str = "couldn't find DSO to load: ".concat(String.valueOf(str));
                                Log.e(TAG, str);
                                throw new UnsatisfiedLinkError(str);
                            }
                            return;
                        }
                    } catch (Throwable th4) {
                        if (SYSTRACE_LIBRARY_LOADING) {
                            Api18TraceUtils.endSection();
                        }
                        if (obj != null) {
                            StrictMode.setThreadPolicy(threadPolicy);
                        }
                        if (i2 == 0 || i2 == 3) {
                            str = "couldn't find DSO to load: ".concat(String.valueOf(str));
                            Log.e(TAG, str);
                            unsatisfiedLinkError = new UnsatisfiedLinkError(str);
                        }
                    }
                }
            } else {
                String str2 = TAG;
                StringBuilder stringBuilder2 = new StringBuilder("Could not load: ");
                stringBuilder2.append(str);
                stringBuilder2.append(" because no SO source exists");
                Log.e(str2, stringBuilder2.toString());
                throw new UnsatisfiedLinkError("couldn't find DSO to load: ".concat(String.valueOf(str)));
            }
            sSoSourcesLock.readLock().unlock();
            throw th;
        } finally {
            i = sSoSourcesLock.readLock();
            i.unlock();
        }
    }

    public static String makeNonZipPath(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(":");
        Iterable arrayList = new ArrayList(split.length);
        for (String str2 : split) {
            if (!str2.contains("!")) {
                arrayList.add(str2);
            }
        }
        return TextUtils.join(":", arrayList);
    }

    static File unpackLibraryBySoName(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        int i = 0;
        while (i < sSoSources.length) {
            try {
                File unpackLibrary = sSoSources[i].unpackLibrary(str);
                if (unpackLibrary != null) {
                    return unpackLibrary;
                }
                i++;
            } finally {
                sSoSourcesLock.readLock().unlock();
            }
        }
        sSoSourcesLock.readLock().unlock();
        throw new FileNotFoundException(str);
    }

    private static void assertInitialized() {
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources == null) {
                throw new RuntimeException("SoLoader.init() not yet called");
            }
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static void prependSoSource(SoSource soSource) throws IOException {
        sSoSourcesLock.writeLock().lock();
        try {
            new StringBuilder("Prepending to SO sources: ").append(soSource);
            assertInitialized();
            soSource.prepare(makePrepareFlags());
            Object obj = new SoSource[(sSoSources.length + 1)];
            obj[0] = soSource;
            System.arraycopy(sSoSources, 0, obj, 1, sSoSources.length);
            sSoSources = obj;
            sSoSourcesVersion++;
            new StringBuilder("Prepended to SO sources: ").append(soSource);
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    public static String makeLdLibraryPath() {
        sSoSourcesLock.readLock().lock();
        try {
            assertInitialized();
            Iterable arrayList = new ArrayList();
            SoSource[] soSourceArr = sSoSources;
            for (SoSource addToLdLibraryPath : soSourceArr) {
                addToLdLibraryPath.addToLdLibraryPath(arrayList);
            }
            String join = TextUtils.join(":", arrayList);
            return join;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static boolean areSoSourcesAbisSupported() {
        sSoSourcesLock.readLock().lock();
        boolean z;
        try {
            z = false;
            if (sSoSources == null) {
                return z;
            }
            String[] supportedAbis = SysUtil.getSupportedAbis();
            for (SoSource soSourceAbis : sSoSources) {
                String[] soSourceAbis2 = soSourceAbis.getSoSourceAbis();
                int i = 0;
                while (i < soSourceAbis2.length) {
                    boolean z2 = false;
                    for (int i2 = 0; i2 < supportedAbis.length && !z2; i2++) {
                        z2 = soSourceAbis2[i].equals(supportedAbis[i2]);
                    }
                    if (z2) {
                        i++;
                    } else {
                        sSoSourcesLock.readLock().unlock();
                        return false;
                    }
                }
            }
            sSoSourcesLock.readLock().unlock();
            return true;
        } finally {
            z = sSoSourcesLock.readLock();
            z.unlock();
        }
    }
}
