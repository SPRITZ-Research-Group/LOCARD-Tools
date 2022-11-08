package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.z;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.n;
import com.google.firebase.components.j;
import com.google.firebase.components.m;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.concurrent.GuardedBy;

public class a {
    @GuardedBy("sLock")
    static final Map<String, a> a = new android.support.v4.util.a();
    private static final List<String> b = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
    private static final List<String> c = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    private static final List<String> d = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
    private static final List<String> e = Arrays.asList(new String[0]);
    private static final Set<String> f = Collections.emptySet();
    private static final Object g = new Object();
    private final Context h;
    private final String i;
    private final b j;
    private final m k;
    private final AtomicBoolean l = new AtomicBoolean(false);
    private final AtomicBoolean m = new AtomicBoolean();
    private final List<Object> n = new CopyOnWriteArrayList();
    private final List<Object> o = new CopyOnWriteArrayList();
    private final List<Object> p = new CopyOnWriteArrayList();
    private a q;

    @KeepForSdk
    public interface a {
    }

    @TargetApi(24)
    private static class b extends BroadcastReceiver {
        private static AtomicReference<b> a = new AtomicReference();
        private final Context b;

        private b(Context context) {
            this.b = context;
        }

        public final void onReceive(Context context, Intent intent) {
            synchronized (a.g) {
                for (a a : a.a.values()) {
                    a.j();
                }
            }
            this.b.unregisterReceiver(this);
        }

        static /* synthetic */ void a(Context context) {
            if (a.get() == null) {
                BroadcastReceiver bVar = new b(context);
                if (a.compareAndSet(null, bVar)) {
                    context.registerReceiver(bVar, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }
    }

    private a(Context context, String str, b bVar) {
        this.h = (Context) ab.a((Object) context);
        this.i = ab.a(str);
        this.j = (b) ab.a((Object) bVar);
        this.q = new com.google.firebase.b.a();
        this.k = new m(new j(this.h).a(), com.google.firebase.components.a.a(Context.class, this.h), com.google.firebase.components.a.a(a.class, this), com.google.firebase.components.a.a(b.class, this.j));
    }

    @Nullable
    public static a a(Context context) {
        a c;
        synchronized (g) {
            if (a.containsKey("[DEFAULT]")) {
                c = c();
            } else {
                b a = b.a(context);
                if (a == null) {
                    c = null;
                } else {
                    c = a(context, a, "[DEFAULT]");
                }
            }
        }
        return c;
    }

    private static a a(Context context, b bVar, String str) {
        Object context2;
        a aVar;
        com.google.firebase.b.b.a();
        if (context2.getApplicationContext() instanceof Application) {
            com.google.android.gms.common.api.internal.b.a((Application) context2.getApplicationContext());
            com.google.android.gms.common.api.internal.b.a().a(new c());
        }
        String trim = str.trim();
        if (context2.getApplicationContext() != null) {
            context2 = context2.getApplicationContext();
        }
        synchronized (g) {
            ab.a(!a.containsKey(trim), new StringBuilder(String.valueOf(trim).length() + 33).append("FirebaseApp name ").append(trim).append(" already exists!").toString());
            ab.a(context2, (Object) "Application context cannot be null.");
            aVar = new a(context2, trim, bVar);
            a.put(trim, aVar);
        }
        com.google.firebase.b.b.b();
        aVar.j();
        return aVar;
    }

    private static <T> void a(Class<T> cls, T t, Iterable<String> iterable, boolean z) {
        for (String str : iterable) {
            String str2;
            if (z) {
                try {
                    if (!e.contains(str2)) {
                    }
                } catch (ClassNotFoundException e) {
                    if (f.contains(str2)) {
                        throw new IllegalStateException(String.valueOf(str2).concat(" is missing, but is required. Check if it has been removed by Proguard."));
                    }
                    String.valueOf(str2).concat(" is not linked. Skipping initialization.");
                } catch (NoSuchMethodException e2) {
                    throw new IllegalStateException(String.valueOf(str2).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
                } catch (InvocationTargetException e3) {
                } catch (IllegalAccessException e4) {
                    String str3 = "Failed to initialize ";
                    str2 = String.valueOf(str2);
                    if (str2.length() != 0) {
                        str3.concat(str2);
                    } else {
                        str2 = new String(str3);
                    }
                }
            }
            Method method = Class.forName(str2).getMethod("getInstance", new Class[]{cls});
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                method.invoke(null, new Object[]{t});
            }
        }
    }

    @Nullable
    public static a c() {
        a aVar;
        synchronized (g) {
            aVar = (a) a.get("[DEFAULT]");
            if (aVar == null) {
                String a = n.a();
                throw new IllegalStateException(new StringBuilder(String.valueOf(a).length() + 116).append("Default FirebaseApp is not initialized in this process ").append(a).append(". Make sure to call FirebaseApp.initializeApp(Context) first.").toString());
            }
        }
        return aVar;
    }

    @KeepForSdk
    public static void d() {
        synchronized (g) {
            ArrayList arrayList = new ArrayList(a.values());
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                a aVar = (a) obj;
                if (aVar.l.get()) {
                    aVar.i();
                }
            }
        }
    }

    @NonNull
    private String f() {
        g();
        return this.i;
    }

    private final void g() {
        ab.a(!this.m.get(), (Object) "FirebaseApp was deleted");
    }

    @KeepForSdk
    @VisibleForTesting
    private boolean h() {
        return "[DEFAULT]".equals(f());
    }

    private final void i() {
        Iterator it = this.o.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    private final void j() {
        boolean d = android.support.v4.content.a.d(this.h);
        if (d) {
            b.a(this.h);
        } else {
            this.k.a(h());
        }
        a(a.class, this, b, d);
        if (h()) {
            a(a.class, this, c, d);
            a(Context.class, this.h, d, d);
        }
    }

    @NonNull
    public final Context a() {
        g();
        return this.h;
    }

    @KeepForSdk
    public final <T> T a(Class<T> cls) {
        g();
        return this.k.a((Class) cls);
    }

    @NonNull
    public final b b() {
        g();
        return this.j;
    }

    public boolean equals(Object obj) {
        return !(obj instanceof a) ? false : this.i.equals(((a) obj).f());
    }

    public int hashCode() {
        return this.i.hashCode();
    }

    public String toString() {
        return z.a(this).a("name", this.i).a("options", this.j).toString();
    }
}
