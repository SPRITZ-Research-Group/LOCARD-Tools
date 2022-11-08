package addon.greenrobot.eventbus;

import android.os.Looper;
import defpackage.a;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;

public class c {
    public static String a = "EventBus";
    private static final e b = new e();
    private static final Map<Class<?>, List<Class<?>>> c = new HashMap();
    private final Map<Class<?>, CopyOnWriteArrayList<u>> d;
    private final Map<Object, List<Class<?>>> e;
    private final Map<Class<?>, Object> f;
    private final ThreadLocal<d> g;
    private final j h;
    private final o i;
    private final b j;
    private final a k;
    private final s l;
    private final ExecutorService m;
    private final boolean n;
    private final boolean o;
    private final boolean p;
    private final boolean q;
    private final boolean r;
    private final boolean s;
    private final int t;
    private final h u;

    public c() {
        this(b);
    }

    private c(e eVar) {
        j jVar;
        this.g = new ThreadLocal<d>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            protected final /* synthetic */ Object initialValue() {
                return new d();
            }
        };
        h iVar = eVar.k != null ? eVar.k : (!a.a() || e.a() == null) ? new i() : new a("EventBus");
        this.u = iVar;
        this.d = new HashMap();
        this.e = new HashMap();
        this.f = new ConcurrentHashMap();
        o oVar = null;
        if (eVar.l != null) {
            jVar = eVar.l;
        } else {
            if (a.a()) {
                Object a = e.a();
                if (a != null) {
                    jVar = new k((Looper) a);
                }
            }
            jVar = null;
        }
        this.h = jVar;
        if (this.h != null) {
            oVar = this.h.a(this);
        }
        this.i = oVar;
        this.j = new b(this);
        this.k = new a(this);
        this.t = eVar.j != null ? eVar.j.size() : 0;
        this.l = new s(eVar.j, eVar.h, eVar.g);
        this.o = eVar.a;
        this.p = eVar.b;
        this.q = eVar.c;
        this.r = eVar.d;
        this.n = eVar.e;
        this.s = eVar.f;
        this.m = eVar.i;
    }

    public void a(Object obj) {
        List<r> a = this.l.a(obj.getClass());
        synchronized (this) {
            for (r rVar : a) {
                Class cls = rVar.c;
                u uVar = new u(obj, rVar);
                CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.d.get(cls);
                if (copyOnWriteArrayList == null) {
                    copyOnWriteArrayList = new CopyOnWriteArrayList();
                    this.d.put(cls, copyOnWriteArrayList);
                } else if (copyOnWriteArrayList.contains(uVar)) {
                    StringBuilder stringBuilder = new StringBuilder("Subscriber ");
                    stringBuilder.append(obj.getClass());
                    stringBuilder.append(" already registered to event ");
                    stringBuilder.append(cls);
                    throw new f(stringBuilder.toString());
                }
                int size = copyOnWriteArrayList.size();
                int i = 0;
                while (i <= size) {
                    if (i == size || rVar.d > ((u) copyOnWriteArrayList.get(i)).b.d) {
                        copyOnWriteArrayList.add(i, uVar);
                        break;
                    }
                    i++;
                }
                List list = (List) this.e.get(obj);
                if (list == null) {
                    list = new ArrayList();
                    this.e.put(obj, list);
                }
                list.add(cls);
                if (rVar.e) {
                    if (this.s) {
                        for (Entry entry : this.f.entrySet()) {
                            if (cls.isAssignableFrom((Class) entry.getKey())) {
                                a(uVar, entry.getValue());
                            }
                        }
                    } else {
                        a(uVar, this.f.get(cls));
                    }
                }
            }
        }
    }

    private void a(u uVar, Object obj) {
        if (obj != null) {
            a(uVar, obj, c());
        }
    }

    private boolean c() {
        return this.h != null ? this.h.a() : true;
    }

    public final synchronized void b(Object obj) {
        List<Class> list = (List) this.e.get(obj);
        if (list != null) {
            for (Class cls : list) {
                List list2 = (List) this.d.get(cls);
                if (list2 != null) {
                    int size = list2.size();
                    int i = 0;
                    while (i < size) {
                        u uVar = (u) list2.get(i);
                        if (uVar.a == obj) {
                            uVar.c = false;
                            list2.remove(i);
                            i--;
                            size--;
                        }
                        i++;
                    }
                }
            }
            this.e.remove(obj);
            return;
        }
        h hVar = this.u;
        Level level = Level.WARNING;
        StringBuilder stringBuilder = new StringBuilder("Subscriber to unregister was not registered before: ");
        stringBuilder.append(obj.getClass());
        hVar.a(level, stringBuilder.toString());
    }

    public final void c(Object obj) {
        d dVar = (d) this.g.get();
        List list = dVar.a;
        list.add(obj);
        if (!dVar.b) {
            dVar.c = c();
            dVar.b = true;
            if (dVar.f) {
                throw new f("Internal error. Abort state was not reset");
            }
            while (!list.isEmpty()) {
                try {
                    int i;
                    Object remove = list.remove(0);
                    Class cls = remove.getClass();
                    if (this.s) {
                        List a = a(cls);
                        i = 0;
                        for (int i2 = 0; i2 < a.size(); i2++) {
                            i |= a(remove, dVar, (Class) a.get(i2));
                        }
                    } else {
                        i = a(remove, dVar, cls);
                    }
                    if (i == 0) {
                        if (this.p) {
                            this.u.a(Level.FINE, "No subscribers registered for event ".concat(String.valueOf(cls)));
                        }
                        if (!(!this.r || cls == l.class || cls == q.class)) {
                            c(new l(this, remove));
                        }
                    }
                } finally {
                    dVar.b = false;
                    dVar.c = false;
                }
            }
        }
    }

    private boolean a(Object obj, d dVar, Class<?> cls) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = (CopyOnWriteArrayList) this.d.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator it = copyOnWriteArrayList.iterator();
        loop0:
        while (it.hasNext()) {
            boolean z = (u) it.next();
            dVar.e = obj;
            dVar.d = z;
            try {
                a((u) z, obj, dVar.c);
                z = dVar.f;
                continue;
            } finally {
                dVar.e = null;
                dVar.d = null;
                dVar.f = false;
                continue;
                if (z) {
                    break loop0;
                }
            }
            if (z) {
                break loop0;
            }
        }
        return true;
    }

    private void a(u uVar, Object obj, boolean z) {
        switch (uVar.b.b) {
            case POSTING:
                b(uVar, obj);
                return;
            case MAIN:
                if (z) {
                    b(uVar, obj);
                    return;
                } else {
                    this.i.a(uVar, obj);
                    return;
                }
            case MAIN_ORDERED:
                if (this.i != null) {
                    this.i.a(uVar, obj);
                    return;
                } else {
                    b(uVar, obj);
                    return;
                }
            case BACKGROUND:
                if (z) {
                    this.j.a(uVar, obj);
                    return;
                } else {
                    b(uVar, obj);
                    return;
                }
            case ASYNC:
                this.k.a(uVar, obj);
                return;
            default:
                StringBuilder stringBuilder = new StringBuilder("Unknown thread mode: ");
                stringBuilder.append(uVar.b.b);
                throw new IllegalStateException(stringBuilder.toString());
        }
    }

    private static List<Class<?>> a(Class<?> cls) {
        List<Class<?>> list;
        synchronized (c) {
            list = (List) c.get(cls);
            if (list == null) {
                list = new ArrayList();
                for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    a((List) list, cls2.getInterfaces());
                }
                c.put(cls, list);
            }
        }
        return list;
    }

    private static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a((List) list, cls.getInterfaces());
            }
        }
    }

    final void a(m mVar) {
        Object obj = mVar.a;
        u uVar = mVar.b;
        m.a(mVar);
        if (uVar.c) {
            b(uVar, obj);
        }
    }

    private void b(u uVar, Object obj) {
        try {
            uVar.b.a.invoke(uVar.a, new Object[]{obj});
        } catch (InvocationTargetException e) {
            a(uVar, obj, e.getCause());
        } catch (Throwable e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        }
    }

    private void a(u uVar, Object obj, Throwable th) {
        h hVar;
        Level level;
        StringBuilder stringBuilder;
        if (obj instanceof q) {
            if (this.o) {
                hVar = this.u;
                level = Level.SEVERE;
                stringBuilder = new StringBuilder("SubscriberExceptionEvent subscriber ");
                stringBuilder.append(uVar.a.getClass());
                stringBuilder.append(" threw an exception");
                hVar.a(level, stringBuilder.toString(), th);
                q qVar = (q) obj;
                h hVar2 = this.u;
                Level level2 = Level.SEVERE;
                StringBuilder stringBuilder2 = new StringBuilder("Initial event ");
                stringBuilder2.append(qVar.c);
                stringBuilder2.append(" caused exception in ");
                stringBuilder2.append(qVar.d);
                hVar2.a(level2, stringBuilder2.toString(), qVar.b);
            }
        } else if (this.n) {
            throw new f("Invoking subscriber failed", th);
        } else {
            if (this.o) {
                hVar = this.u;
                level = Level.SEVERE;
                stringBuilder = new StringBuilder("Could not dispatch event: ");
                stringBuilder.append(obj.getClass());
                stringBuilder.append(" to subscribing class ");
                stringBuilder.append(uVar.a.getClass());
                hVar.a(level, stringBuilder.toString(), th);
            }
            if (this.q) {
                c(new q(this, th, obj, uVar.a));
            }
        }
    }

    final ExecutorService a() {
        return this.m;
    }

    public final h b() {
        return this.u;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("EventBus[indexCount=");
        stringBuilder.append(this.t);
        stringBuilder.append(", eventInheritance=");
        stringBuilder.append(this.s);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
