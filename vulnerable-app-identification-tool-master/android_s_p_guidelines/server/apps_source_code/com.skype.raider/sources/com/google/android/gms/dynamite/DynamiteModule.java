package com.google.android.gms.dynamite;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.e;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.DynamiteApi;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.concurrent.GuardedBy;

public final class DynamiteModule {
    public static final b a = new d();
    public static final b b = new e();
    public static final b c = new f();
    public static final b d = new g();
    public static final b e = new h();
    public static final b f = new i();
    @GuardedBy("DynamiteModule.class")
    private static Boolean g;
    @GuardedBy("DynamiteModule.class")
    private static a h;
    @GuardedBy("DynamiteModule.class")
    private static b i;
    @GuardedBy("DynamiteModule.class")
    private static String j;
    private static final ThreadLocal<c> k = new ThreadLocal();
    private static final a l = new c();
    private final Context m;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        @GuardedBy("DynamiteLoaderClassLoader.class")
        public static ClassLoader sClassLoader;
    }

    public static class a extends Exception {
        private a(String str) {
            super(str);
        }

        /* synthetic */ a(String str, byte b) {
            this(str);
        }

        private a(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ a(String str, Throwable th, byte b) {
            this(str, th);
        }
    }

    public interface b {

        public interface a {
            int a(Context context, String str);

            int a(Context context, String str, boolean z) throws a;
        }

        public static class b {
            public int a = 0;
            public int b = 0;
            public int c = 0;
        }

        b a(Context context, String str, a aVar) throws a;
    }

    private static class c {
        public Cursor a;

        private c() {
        }

        /* synthetic */ c(byte b) {
            this();
        }
    }

    private static class d implements a {
        private final int a;
        private final int b = 0;

        public d(int i) {
            this.a = i;
        }

        public final int a(Context context, String str) {
            return this.a;
        }

        public final int a(Context context, String str, boolean z) {
            return 0;
        }
    }

    private DynamiteModule(Context context) {
        this.m = (Context) ab.a((Object) context);
    }

    public static int a(Context context, String str) {
        String valueOf;
        try {
            Class loadClass = context.getApplicationContext().getClassLoader().loadClass(new StringBuilder(String.valueOf(str).length() + 61).append("com.google.android.gms.dynamite.descriptors.").append(str).append(".ModuleDescriptor").toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            valueOf = String.valueOf(declaredField.get(null));
            new StringBuilder((String.valueOf(valueOf).length() + 51) + String.valueOf(str).length()).append("Module descriptor id '").append(valueOf).append("' didn't match expected id '").append(str).append("'");
            return 0;
        } catch (ClassNotFoundException e) {
            new StringBuilder(String.valueOf(str).length() + 45).append("Local module descriptor class for ").append(str).append(" not found.");
            return 0;
        } catch (Exception e2) {
            String str2 = "Failed to load module descriptor class: ";
            valueOf = String.valueOf(e2.getMessage());
            if (valueOf.length() != 0) {
                str2.concat(valueOf);
                return 0;
            }
            valueOf = new String(str2);
            return 0;
        }
    }

    public static int a(Context context, String str, boolean z) {
        Boolean bool;
        String valueOf;
        Object e;
        synchronized (DynamiteModule.class) {
            bool = g;
            if (bool == null) {
                try {
                    Class loadClass = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName());
                    Field declaredField = loadClass.getDeclaredField("sClassLoader");
                    synchronized (loadClass) {
                        ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                        if (classLoader != null) {
                            if (classLoader == ClassLoader.getSystemClassLoader()) {
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    a(classLoader);
                                } catch (a e2) {
                                }
                                bool = Boolean.TRUE;
                            }
                        } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                            bool = Boolean.FALSE;
                        } else {
                            try {
                                int c = c(context, str, z);
                                if (j == null || j.isEmpty()) {
                                    return c;
                                }
                                ClassLoader jVar = new j(j, ClassLoader.getSystemClassLoader());
                                a(jVar);
                                declaredField.set(null, jVar);
                                g = Boolean.TRUE;
                                return c;
                            } catch (a e3) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                                g = bool;
                                if (!bool.booleanValue()) {
                                    try {
                                    } catch (a e4) {
                                        String str2 = "Failed to retrieve remote module version: ";
                                        valueOf = String.valueOf(e4.getMessage());
                                        if (valueOf.length() != 0) {
                                            str2.concat(valueOf);
                                        } else {
                                            valueOf = new String(str2);
                                        }
                                        return 0;
                                    }
                                }
                            }
                        }
                    }
                } catch (ClassNotFoundException e5) {
                    e = e5;
                } catch (IllegalAccessException e6) {
                    e = e6;
                } catch (NoSuchFieldException e7) {
                    e = e7;
                }
            }
        }
        valueOf = String.valueOf(e);
        new StringBuilder(String.valueOf(valueOf).length() + 30).append("Failed to load module via V2: ").append(valueOf);
        bool = Boolean.FALSE;
        g = bool;
        return !bool.booleanValue() ? b(context, str, z) : c(context, str, z);
    }

    private static Context a(Context context, String str, int i, Cursor cursor, b bVar) {
        try {
            return (Context) com.google.android.gms.b.d.a(bVar.a(com.google.android.gms.b.d.a((Object) context), str, i, com.google.android.gms.b.d.a((Object) cursor)));
        } catch (Exception e) {
            String str2 = "Failed to load DynamiteLoader: ";
            String valueOf = String.valueOf(e.toString());
            if (valueOf.length() != 0) {
                str2.concat(valueOf);
            } else {
                valueOf = new String(str2);
            }
            return null;
        }
    }

    public static DynamiteModule a(Context context, b bVar, String str) throws a {
        c cVar = (c) k.get();
        c cVar2 = new c();
        k.set(cVar2);
        b a;
        DynamiteModule b;
        try {
            a = bVar.a(context, str, l);
            int i = a.a;
            new StringBuilder((String.valueOf(str).length() + 68) + String.valueOf(str).length()).append("Considering local module ").append(str).append(":").append(i).append(" and remote module ").append(str).append(":").append(a.b);
            if (a.c == 0 || ((a.c == -1 && a.a == 0) || (a.c == 1 && a.b == 0))) {
                int i2 = a.a;
                throw new a("No acceptable module found. Local version is " + i2 + " and remote version is " + a.b + ".", (byte) 0);
            } else if (a.c == -1) {
                b = b(context, str);
                if (cVar2.a != null) {
                    cVar2.a.close();
                }
                k.set(cVar);
                return b;
            } else if (a.c == 1) {
                b = a(context, str, a.b);
                if (cVar2.a != null) {
                    cVar2.a.close();
                }
                k.set(cVar);
                return b;
            } else {
                throw new a("VersionPolicy returned invalid code:" + a.c, (byte) 0);
            }
        } catch (Throwable e) {
            String str2 = "Failed to load remote module: ";
            String valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() != 0) {
                str2.concat(valueOf);
            } else {
                valueOf = new String(str2);
            }
            if (a.a == 0 || bVar.a(context, str, new d(a.a)).c != -1) {
                throw new a("Remote load failed. No local fallback found.", e, (byte) 0);
            }
            b = b(context, str);
            if (cVar2.a != null) {
                cVar2.a.close();
            }
            k.set(cVar);
            return b;
        } catch (Throwable th) {
            if (cVar2.a != null) {
                cVar2.a.close();
            }
            k.set(cVar);
        }
    }

    private static DynamiteModule a(Context context, String str, int i) throws a {
        Boolean bool;
        synchronized (DynamiteModule.class) {
            bool = g;
        }
        if (bool != null) {
            return bool.booleanValue() ? c(context, str, i) : b(context, str, i);
        } else {
            throw new a("Failed to determine which loading route to use.", (byte) 0);
        }
    }

    private static a a(Context context) {
        synchronized (DynamiteModule.class) {
            a aVar;
            if (h != null) {
                aVar = h;
                return aVar;
            } else if (e.b().a(context) != 0) {
                return null;
            } else {
                try {
                    aVar = com.google.android.gms.dynamite.a.a.a((IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance());
                    if (aVar != null) {
                        h = aVar;
                        return aVar;
                    }
                } catch (Exception e) {
                    String str = "Failed to load IDynamiteLoader from GmsCore: ";
                    String valueOf = String.valueOf(e.getMessage());
                    if (valueOf.length() != 0) {
                        str.concat(valueOf);
                    } else {
                        valueOf = new String(str);
                    }
                    return null;
                }
            }
        }
    }

    @GuardedBy("DynamiteModule.class")
    private static void a(ClassLoader classLoader) throws a {
        Throwable e;
        try {
            i = com.google.android.gms.dynamite.b.a.a((IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (ClassNotFoundException e2) {
            e = e2;
            throw new a("Failed to instantiate dynamite loader", e, (byte) 0);
        } catch (IllegalAccessException e3) {
            e = e3;
            throw new a("Failed to instantiate dynamite loader", e, (byte) 0);
        } catch (InstantiationException e4) {
            e = e4;
            throw new a("Failed to instantiate dynamite loader", e, (byte) 0);
        } catch (InvocationTargetException e5) {
            e = e5;
            throw new a("Failed to instantiate dynamite loader", e, (byte) 0);
        } catch (NoSuchMethodException e6) {
            e = e6;
            throw new a("Failed to instantiate dynamite loader", e, (byte) 0);
        }
    }

    private static int b(Context context, String str, boolean z) {
        int i = 0;
        a a = a(context);
        if (a == null) {
            return i;
        }
        try {
            return a.a(com.google.android.gms.b.d.a((Object) context), str, z);
        } catch (RemoteException e) {
            String str2 = "Failed to retrieve remote module version: ";
            String valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() != 0) {
                str2.concat(valueOf);
                return i;
            }
            valueOf = new String(str2);
            return i;
        }
    }

    private static DynamiteModule b(Context context, String str) {
        String str2 = "Selected local version of ";
        String valueOf = String.valueOf(str);
        if (valueOf.length() != 0) {
            str2.concat(valueOf);
        } else {
            valueOf = new String(str2);
        }
        return new DynamiteModule(context.getApplicationContext());
    }

    private static DynamiteModule b(Context context, String str, int i) throws a {
        new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i);
        a a = a(context);
        if (a == null) {
            throw new a("Failed to create IDynamiteLoader.", (byte) 0);
        }
        try {
            com.google.android.gms.b.b a2 = a.a(com.google.android.gms.b.d.a((Object) context), str, i);
            if (com.google.android.gms.b.d.a(a2) != null) {
                return new DynamiteModule((Context) com.google.android.gms.b.d.a(a2));
            }
            throw new a("Failed to load remote module.", (byte) 0);
        } catch (Throwable e) {
            throw new a("Failed to load remote module.", e, (byte) 0);
        }
    }

    private static int c(Context context, String str, boolean z) throws a {
        Cursor query;
        Throwable e;
        try {
            String str2;
            ContentResolver contentResolver = context.getContentResolver();
            if (z) {
                str2 = "api_force_staging";
            } else {
                str2 = "api";
            }
            query = contentResolver.query(Uri.parse(new StringBuilder((String.valueOf(str2).length() + 42) + String.valueOf(str).length()).append("content://com.google.android.gms.chimera/").append(str2).append("/").append(str).toString()), null, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        int i = query.getInt(0);
                        if (i > 0) {
                            synchronized (DynamiteModule.class) {
                                j = query.getString(2);
                            }
                            c cVar = (c) k.get();
                            if (cVar != null && cVar.a == null) {
                                cVar.a = query;
                                query = null;
                            }
                        }
                        if (query != null) {
                            query.close();
                        }
                        return i;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
            throw new a("Failed to connect to dynamite module ContentResolver.", (byte) 0);
        } catch (Exception e3) {
            e = e3;
            query = null;
        } catch (Throwable th) {
            e = th;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e;
        }
        try {
            if (e instanceof a) {
                throw e;
            }
            throw new a("V2 version check failed", e, (byte) 0);
        } catch (Throwable th2) {
            e = th2;
            if (query != null) {
                query.close();
            }
            throw e;
        }
    }

    private static DynamiteModule c(Context context, String str, int i) throws a {
        b bVar;
        new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i);
        synchronized (DynamiteModule.class) {
            bVar = i;
        }
        if (bVar == null) {
            throw new a("DynamiteLoaderV2 was not cached.", (byte) 0);
        }
        c cVar = (c) k.get();
        if (cVar == null || cVar.a == null) {
            throw new a("No result cursor", (byte) 0);
        }
        Context a = a(context.getApplicationContext(), str, i, cVar.a, bVar);
        if (a != null) {
            return new DynamiteModule(a);
        }
        throw new a("Failed to get module context", (byte) 0);
    }

    public final Context a() {
        return this.m;
    }

    public final IBinder a(String str) throws a {
        Throwable e;
        String str2;
        String valueOf;
        try {
            return (IBinder) this.m.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException e2) {
            e = e2;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            throw new a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e, (byte) 0);
        } catch (InstantiationException e3) {
            e = e3;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
            }
            throw new a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e, (byte) 0);
        } catch (IllegalAccessException e4) {
            e = e4;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
            }
            throw new a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e, (byte) 0);
        }
    }
}
