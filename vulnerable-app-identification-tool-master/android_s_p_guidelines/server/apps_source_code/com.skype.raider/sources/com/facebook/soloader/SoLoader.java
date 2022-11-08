package com.facebook.soloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
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
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SoLoader {
    static final boolean a;
    @Nullable
    static g b;
    private static final ReentrantReadWriteLock c = new ReentrantReadWriteLock();
    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static h[] d = null;
    private static int e = 0;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static j f;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static b g;
    @GuardedBy("SoLoader.class")
    private static final HashSet<String> h = new HashSet();
    @GuardedBy("SoLoader.class")
    private static final Map<String, Object> i = new HashMap();
    private static final Set<String> j = Collections.newSetFromMap(new ConcurrentHashMap());
    @Nullable
    private static i k = null;
    @GuardedBy("sSoSourcesLock")
    private static int l;

    @DoNotOptimize
    @TargetApi(14)
    private static class Api14Utils {
        private Api14Utils() {
        }

        public static String a() {
            ClassLoader classLoader = SoLoader.class.getClassLoader();
            if (classLoader instanceof BaseDexClassLoader) {
                try {
                    return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke((BaseDexClassLoader) classLoader, new Object[0]);
                } catch (Exception e) {
                    throw new RuntimeException("Cannot call getLdLibraryPath", e);
                }
            }
            throw new IllegalStateException("ClassLoader " + classLoader.getClass().getName() + " should be of type BaseDexClassLoader");
        }
    }

    public static final class a extends UnsatisfiedLinkError {
        a(Throwable cause) {
            super("APK was built for a different platform");
            initCause(cause);
        }
    }

    static {
        boolean shouldSystrace = false;
        try {
            shouldSystrace = VERSION.SDK_INT >= 18;
        } catch (NoClassDefFoundError e) {
        } catch (UnsatisfiedLinkError e2) {
        }
        a = shouldSystrace;
    }

    public static void a(Context context) throws IOException {
        int i = 0;
        ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            b();
            c.writeLock().lock();
            if (d == null) {
                int i2;
                l = 0;
                ArrayList arrayList = new ArrayList();
                String str = System.getenv("LD_LIBRARY_PATH");
                if (str == null) {
                    str = "/vendor/lib:/system/lib";
                }
                String[] split = str.split(":");
                for (i2 = 0; i2 < split.length; i2++) {
                    new StringBuilder("adding system library source: ").append(split[i2]);
                    arrayList.add(new c(new File(split[i2]), 2));
                }
                if (context != null) {
                    ApplicationInfo applicationInfo = context.getApplicationInfo();
                    i2 = ((applicationInfo.flags & 1) == 0 || (applicationInfo.flags & 128) != 0) ? 0 : 1;
                    if (i2 == 0) {
                        if (VERSION.SDK_INT <= 17) {
                            i = 1;
                        }
                        g = new b(context, i);
                        new StringBuilder("adding application source: ").append(g.toString());
                        arrayList.add(0, g);
                        i = 1;
                    }
                    f = new a(context, "lib-main", i);
                    new StringBuilder("adding backup  source: ").append(f.toString());
                    arrayList.add(0, f);
                }
                h[] hVarArr = (h[]) arrayList.toArray(new h[arrayList.size()]);
                int a = a();
                int length = hVarArr.length;
                while (true) {
                    i2 = length - 1;
                    if (length <= 0) {
                        break;
                    }
                    new StringBuilder("Preparing SO source: ").append(hVarArr[i2]);
                    hVarArr[i2].a(a);
                    length = i2;
                }
                d = hVarArr;
                e++;
                new StringBuilder("init finish: ").append(d.length).append(" SO sources prepared");
            }
            c.writeLock().unlock();
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        }
    }

    public static void b(Context context) {
        try {
            a(context);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static int a() {
        int prepareFlags = 0;
        c.writeLock().lock();
        try {
            if ((l & 2) != 0) {
                prepareFlags = 1;
            }
            c.writeLock().unlock();
            return prepareFlags;
        } catch (Throwable th) {
            c.writeLock().unlock();
        }
    }

    private static synchronized void b() {
        String localLdLibraryPathNoZips = null;
        int i = 0;
        synchronized (SoLoader.class) {
            boolean hasNativeLoadMethod;
            String localLdLibraryPath;
            final Runtime runtime = Runtime.getRuntime();
            final Method nativeLoadRuntimeMethod = c();
            if (nativeLoadRuntimeMethod != null) {
                hasNativeLoadMethod = true;
            } else {
                hasNativeLoadMethod = false;
            }
            if (hasNativeLoadMethod) {
                localLdLibraryPath = Api14Utils.a();
            } else {
                localLdLibraryPath = null;
            }
            if (localLdLibraryPath != null) {
                String[] split = localLdLibraryPath.split(":");
                Iterable arrayList = new ArrayList(split.length);
                int length = split.length;
                while (i < length) {
                    String str = split[i];
                    if (!str.contains("!")) {
                        arrayList.add(str);
                    }
                    i++;
                }
                localLdLibraryPathNoZips = TextUtils.join(":", arrayList);
            }
            b = new g() {
                public final void a(String pathToSoFile, int loadFlags) {
                    String path;
                    Exception e;
                    Object obj = 1;
                    if (hasNativeLoadMethod) {
                        if ((loadFlags & 4) != 4) {
                            obj = null;
                        }
                        path = obj != null ? localLdLibraryPath : localLdLibraryPathNoZips;
                        try {
                            String error;
                            synchronized (runtime) {
                                if (VERSION.SDK_INT <= 27) {
                                    error = (String) nativeLoadRuntimeMethod.invoke(runtime, new Object[]{pathToSoFile, SoLoader.class.getClassLoader(), path});
                                } else {
                                    error = (String) nativeLoadRuntimeMethod.invoke(runtime, new Object[]{pathToSoFile, SoLoader.class.getClassLoader()});
                                }
                                if (error != null) {
                                    throw new UnsatisfiedLinkError(error);
                                }
                            }
                            if (error != null) {
                                new StringBuilder("Error when loading lib: ").append(error).append(" lib hash: ").append(AnonymousClass1.a(pathToSoFile)).append(" search path is ").append(path);
                                return;
                            }
                            return;
                        } catch (Exception e2) {
                            e = e2;
                        } catch (Exception e22) {
                            e = e22;
                        } catch (Exception e222) {
                            e = e222;
                        }
                    } else {
                        System.load(pathToSoFile);
                        return;
                    }
                    try {
                        throw new RuntimeException("Error: Cannot load " + pathToSoFile, e);
                    } catch (Throwable th) {
                        if (null != null) {
                            new StringBuilder("Error when loading lib: ").append(null).append(" lib hash: ").append(AnonymousClass1.a(pathToSoFile)).append(" search path is ").append(path);
                        }
                    }
                }

                private static String a(String libPath) {
                    Throwable th;
                    try {
                        File libFile = new File(libPath);
                        MessageDigest digest = MessageDigest.getInstance(Constants.MD5);
                        InputStream libInStream = new FileInputStream(libFile);
                        Throwable th2 = null;
                        try {
                            byte[] buffer = new byte[4096];
                            while (true) {
                                int bytesRead = libInStream.read(buffer);
                                if (bytesRead > 0) {
                                    digest.update(buffer, 0, bytesRead);
                                } else {
                                    String digestStr = String.format("%32x", new Object[]{new BigInteger(1, digest.digest())});
                                    libInStream.close();
                                    return digestStr;
                                }
                            }
                        } catch (Throwable th22) {
                            Throwable th3 = th22;
                            th22 = th;
                            th = th3;
                        }
                        if (th22 != null) {
                            try {
                                libInStream.close();
                            } catch (Throwable th4) {
                                th22.addSuppressed(th4);
                            }
                        } else {
                            libInStream.close();
                        }
                        throw th;
                        throw th;
                    } catch (IOException e) {
                        return e.toString();
                    } catch (NoSuchAlgorithmException e2) {
                        return e2.toString();
                    }
                }
            };
        }
    }

    @Nullable
    private static Method c() {
        if (VERSION.SDK_INT < 23) {
            return null;
        }
        try {
            Method method;
            if (VERSION.SDK_INT <= 27) {
                method = Runtime.class.getDeclaredMethod("nativeLoad", new Class[]{String.class, ClassLoader.class, String.class});
            } else {
                method = Runtime.class.getDeclaredMethod("nativeLoad", new Class[]{String.class, ClassLoader.class});
            }
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (SecurityException e2) {
            return null;
        }
    }

    public static boolean a(String shortName) {
        return c(shortName);
    }

    private static boolean c(String shortName) throws UnsatisfiedLinkError {
        boolean needsLoad = false;
        c.readLock().lock();
        try {
            if (d == null) {
                if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    d();
                } else {
                    synchronized (SoLoader.class) {
                        if (!h.contains(shortName)) {
                            needsLoad = true;
                        }
                        if (needsLoad && k == null) {
                            System.loadLibrary(shortName);
                        }
                    }
                    c.readLock().unlock();
                    return needsLoad;
                }
            }
            c.readLock().unlock();
            return a(System.mapLibraryName(shortName), shortName, 0, null);
        } catch (Throwable th) {
            c.readLock().unlock();
        }
    }

    static void a(String soName, int loadFlags, ThreadPolicy oldPolicy) {
        a(soName, null, loadFlags, oldPolicy);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(String soName, @Nullable String shortName, int loadFlags, @Nullable ThreadPolicy oldPolicy) {
        if (!TextUtils.isEmpty(shortName) && j.contains(shortName)) {
            return false;
        }
        synchronized (SoLoader.class) {
            Object loadingLibLock;
            if (h.contains(soName)) {
                return false;
            } else if (i.containsKey(soName)) {
                loadingLibLock = i.get(soName);
            } else {
                loadingLibLock = new Object();
                i.put(soName, loadingLibLock);
            }
        }
    }

    public static File b(String shortName) throws UnsatisfiedLinkError {
        d();
        try {
            return d(System.mapLibraryName(shortName));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void b(String soName, int loadFlags, ThreadPolicy oldPolicy) throws IOException {
        UnsatisfiedLinkError unsatisfiedLinkError;
        int result = 0;
        c.readLock().lock();
        try {
            if (d == null) {
                new StringBuilder("Could not load: ").append(soName).append(" because no SO source exists");
                throw new UnsatisfiedLinkError("couldn't find DSO to load: " + soName);
            }
            boolean restoreOldPolicy = false;
            if (oldPolicy == null) {
                oldPolicy = StrictMode.allowThreadDiskReads();
                restoreOldPolicy = true;
            }
            if (a) {
                Api18TraceUtils.a("SoLoader.loadLibrary[" + soName + "]");
            }
            while (true) {
                boolean retry = false;
                try {
                    c.readLock().lock();
                    int currentSoSourcesVersion = e;
                    int i = 0;
                    while (result == 0) {
                        if (i < d.length) {
                            result = d[i].a(soName, loadFlags, oldPolicy);
                            if (result == 3 && f != null) {
                                f.b(soName);
                                result = f.a(soName, loadFlags, oldPolicy);
                                break;
                            }
                            i++;
                        }
                    }
                    c.readLock().unlock();
                    if (result == 0) {
                        c.writeLock().lock();
                        if (g != null && g.a()) {
                            e++;
                        }
                        if (e != currentSoSourcesVersion) {
                            retry = true;
                        }
                        c.writeLock().unlock();
                    }
                    if (!retry) {
                        if (a) {
                            Api18TraceUtils.a();
                        }
                        if (restoreOldPolicy) {
                            StrictMode.setThreadPolicy(oldPolicy);
                        }
                        if (result == 0 || result == 3) {
                            throw new UnsatisfiedLinkError("couldn't find DSO to load: " + soName);
                        }
                        return;
                    }
                } catch (Throwable th) {
                    if (a) {
                        Api18TraceUtils.a();
                    }
                    if (restoreOldPolicy) {
                        StrictMode.setThreadPolicy(oldPolicy);
                    }
                    if (result == 0 || result == 3) {
                        unsatisfiedLinkError = new UnsatisfiedLinkError("couldn't find DSO to load: " + soName);
                    }
                }
            }
        } finally {
            c.readLock().unlock();
        }
    }

    private static File d(String soName) throws IOException {
        c.readLock().lock();
        int i = 0;
        while (i < d.length) {
            try {
                File unpacked = d[i].a(soName);
                if (unpacked != null) {
                    return unpacked;
                }
                i++;
            } finally {
                c.readLock().unlock();
            }
        }
        c.readLock().unlock();
        throw new FileNotFoundException(soName);
    }

    private static void d() {
        c.readLock().lock();
        try {
            if (d == null) {
                throw new RuntimeException("SoLoader.init() not yet called");
            }
        } finally {
            c.readLock().unlock();
        }
    }
}
