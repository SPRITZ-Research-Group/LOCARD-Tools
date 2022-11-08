package android.support.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

public final class a {
    private static final String a = ("code_cache" + File.separator + "secondary-dexes");
    private static final Set<String> b = new HashSet();
    private static final boolean c = a(System.getProperty("java.vm.version"));

    private static final class a {
        static /* synthetic */ void a(ClassLoader x0, List x1) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
            int size = x1.size();
            Field a = a.b(x0, "path");
            StringBuilder stringBuilder = new StringBuilder((String) a.get(x0));
            Object[] objArr = new String[size];
            Object[] objArr2 = new File[size];
            Object[] objArr3 = new ZipFile[size];
            Object[] objArr4 = new DexFile[size];
            ListIterator listIterator = x1.listIterator();
            while (listIterator.hasNext()) {
                File file = (File) listIterator.next();
                String absolutePath = file.getAbsolutePath();
                stringBuilder.append(':').append(absolutePath);
                int previousIndex = listIterator.previousIndex();
                objArr[previousIndex] = absolutePath;
                objArr2[previousIndex] = file;
                objArr3[previousIndex] = new ZipFile(file);
                objArr4[previousIndex] = DexFile.loadDex(absolutePath, absolutePath + ".dex", 0);
            }
            a.set(x0, stringBuilder.toString());
            a.a((Object) x0, "mPaths", objArr);
            a.a((Object) x0, "mFiles", objArr2);
            a.a((Object) x0, "mZips", objArr3);
            a.a((Object) x0, "mDexs", objArr4);
        }
    }

    static /* synthetic */ void a(Object x0, String x1, Object[] x2) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field b = b(x0, x1);
        Object[] objArr = (Object[]) b.get(x0);
        Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), objArr.length + x2.length);
        System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        System.arraycopy(x2, 0, objArr2, objArr.length, x2.length);
        b.set(x0, objArr2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(Context context) {
        if (!c) {
            if (VERSION.SDK_INT < 4) {
                throw new RuntimeException("Multi dex installation failed. SDK " + VERSION.SDK_INT + " is unsupported. Min SDK version is 4.");
            }
            try {
                ApplicationInfo applicationInfo = b(context);
                if (applicationInfo != null) {
                    synchronized (b) {
                        String apkPath = applicationInfo.sourceDir;
                        if (b.contains(apkPath)) {
                            return;
                        }
                        b.add(apkPath);
                        if (VERSION.SDK_INT > 20) {
                            new StringBuilder("MultiDex is not guaranteed to work in SDK version ").append(VERSION.SDK_INT).append(": SDK version higher than 20 should be backed by runtime with built-in multidex capabilty but it's not the case here: java.vm.version=\"").append(System.getProperty("java.vm.version")).append("\"");
                        }
                        try {
                            ClassLoader loader = context.getClassLoader();
                            if (loader == null) {
                                return;
                            }
                            try {
                                c(context);
                            } catch (Throwable th) {
                            }
                            File dexDir = new File(applicationInfo.dataDir, a);
                            List files = b.a(context, applicationInfo, dexDir, false);
                            if (a(files)) {
                                a(loader, dexDir, files);
                            } else {
                                files = b.a(context, applicationInfo, dexDir, true);
                                if (a(files)) {
                                    a(loader, dexDir, files);
                                } else {
                                    throw new RuntimeException("Zip files were not valid.");
                                }
                            }
                        } catch (RuntimeException e) {
                        }
                    }
                }
            } catch (Exception e2) {
                throw new RuntimeException("Multi dex installation failed (" + e2.getMessage() + ").");
            }
        }
    }

    private static ApplicationInfo b(Context context) throws NameNotFoundException {
        try {
            PackageManager pm = context.getPackageManager();
            String packageName = context.getPackageName();
            if (pm == null || packageName == null) {
                return null;
            }
            return pm.getApplicationInfo(packageName, 128);
        } catch (RuntimeException e) {
            return null;
        }
    }

    private static boolean a(String versionString) {
        boolean isMultidexCapable = false;
        if (versionString != null) {
            Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(versionString);
            if (matcher.matches()) {
                try {
                    int major = Integer.parseInt(matcher.group(1));
                    isMultidexCapable = major > 2 || (major == 2 && Integer.parseInt(matcher.group(2)) > 0);
                } catch (NumberFormatException e) {
                }
            }
        }
        new StringBuilder("VM with version ").append(versionString).append(isMultidexCapable ? " has multidex support" : " does not have multidex support");
        return isMultidexCapable;
    }

    private static void a(ClassLoader loader, File dexDir, List<File> files) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
        if (!files.isEmpty()) {
            Object obj;
            ArrayList arrayList;
            if (VERSION.SDK_INT >= 19) {
                obj = b(loader, "pathList").get(loader);
                ArrayList arrayList2 = new ArrayList();
                arrayList = new ArrayList(files);
                a(obj, "dexElements", (Object[]) a(obj, "makeDexElements", ArrayList.class, File.class, ArrayList.class).invoke(obj, new Object[]{arrayList, dexDir, arrayList2}));
                if (arrayList2.size() > 0) {
                    Object obj2;
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        it.next();
                    }
                    Field b = b(loader, "dexElementsSuppressedExceptions");
                    IOException[] iOExceptionArr = (IOException[]) b.get(loader);
                    if (iOExceptionArr == null) {
                        obj2 = (IOException[]) arrayList2.toArray(new IOException[arrayList2.size()]);
                    } else {
                        obj = new IOException[(arrayList2.size() + iOExceptionArr.length)];
                        arrayList2.toArray(obj);
                        System.arraycopy(iOExceptionArr, 0, obj, arrayList2.size(), iOExceptionArr.length);
                        obj2 = obj;
                    }
                    b.set(loader, obj2);
                }
            } else if (VERSION.SDK_INT >= 14) {
                obj = b(loader, "pathList").get(loader);
                arrayList = new ArrayList(files);
                a(obj, "dexElements", (Object[]) a(obj, "makeDexElements", ArrayList.class, File.class).invoke(obj, new Object[]{arrayList, dexDir}));
            } else {
                a.a(loader, files);
            }
        }
    }

    private static boolean a(List<File> files) {
        for (File a : files) {
            if (!b.a(a)) {
                return false;
            }
        }
        return true;
    }

    private static Field b(Object instance, String name) throws NoSuchFieldException {
        Class<?> clazz = instance.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(name);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field " + name + " not found in " + instance.getClass());
    }

    private static Method a(Object instance, String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        Class<?> clazz = instance.getClass();
        while (clazz != null) {
            try {
                Method method = clazz.getDeclaredMethod(name, parameterTypes);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                return method;
            } catch (NoSuchMethodException e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchMethodException("Method " + name + " with parameters " + Arrays.asList(parameterTypes) + " not found in " + instance.getClass());
    }

    private static void c(Context context) throws Exception {
        File dexDir = new File(context.getFilesDir(), "secondary-dexes");
        if (dexDir.isDirectory()) {
            new StringBuilder("Clearing old secondary dex dir (").append(dexDir.getPath()).append(").");
            File[] files = dexDir.listFiles();
            if (files == null) {
                new StringBuilder("Failed to list secondary dex dir content (").append(dexDir.getPath()).append(").");
                return;
            }
            File[] arr$ = files;
            int len$ = files.length;
            for (int i$ = 0; i$ < len$; i$++) {
                File oldFile = arr$[i$];
                new StringBuilder("Trying to delete old file ").append(oldFile.getPath()).append(" of size ").append(oldFile.length());
                if (oldFile.delete()) {
                    new StringBuilder("Deleted old file ").append(oldFile.getPath());
                } else {
                    new StringBuilder("Failed to delete old file ").append(oldFile.getPath());
                }
            }
            if (dexDir.delete()) {
                new StringBuilder("Deleted old secondary dex dir ").append(dexDir.getPath());
            } else {
                new StringBuilder("Failed to delete secondary dex dir ").append(dexDir.getPath());
            }
        }
    }
}
