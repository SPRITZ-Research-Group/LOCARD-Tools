package a.a;

import android.app.Activity;
import com.appboy.c.b;
import com.appboy.f.c;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

public class fy implements b {
    private static final String a = c.a(fy.class);
    private final ConcurrentMap<Activity, ConcurrentMap<Class, CopyOnWriteArraySet<b>>> b = new ConcurrentHashMap();
    private final ConcurrentMap<Class, CopyOnWriteArraySet<b>> c = new ConcurrentHashMap();
    private final ConcurrentMap<Class, CopyOnWriteArraySet<b>> d = new ConcurrentHashMap();
    private final Executor e;
    private final cj f;
    private final Object g = new Object();
    private final Object h = new Object();
    private final Object i = new Object();

    public fy(Executor executor, cj cjVar) {
        this.e = executor;
        this.f = cjVar;
    }

    public final <T> boolean a(b<T> bVar, Class<T> cls) {
        boolean z;
        synchronized (this.h) {
            ConcurrentMap concurrentMap = this.c;
            if (bVar == null) {
                c.g(a, "Error: Attempted to add a null subscriber for eventClass " + cls.getName() + ". This subscriber is being ignored. Please check your calling code to ensure that all potential subscriptions are valid.");
                z = false;
            } else {
                CopyOnWriteArraySet copyOnWriteArraySet = (CopyOnWriteArraySet) concurrentMap.get(cls);
                if (copyOnWriteArraySet == null) {
                    CopyOnWriteArraySet copyOnWriteArraySet2 = new CopyOnWriteArraySet();
                    copyOnWriteArraySet = (CopyOnWriteArraySet) concurrentMap.putIfAbsent(cls, copyOnWriteArraySet2);
                    if (copyOnWriteArraySet == null) {
                        copyOnWriteArraySet = copyOnWriteArraySet2;
                    }
                }
                z = copyOnWriteArraySet.add(bVar);
            }
        }
        return z;
    }

    public final <T> boolean b(b<T> bVar, Class<T> cls) {
        boolean z;
        synchronized (this.h) {
            CopyOnWriteArraySet copyOnWriteArraySet = (CopyOnWriteArraySet) this.c.get(cls);
            if (copyOnWriteArraySet == null || bVar == null || !copyOnWriteArraySet.remove(bVar)) {
                z = false;
            } else {
                z = true;
            }
        }
        return z;
    }

    public final <T> void a(final T t, final Class<T> cls) {
        if (this.f.a()) {
            c.b(a, "SDK is disabled. Not publishing event class: " + cls.getName() + " and message: " + t.toString());
            return;
        }
        Object obj;
        c.c(a, cls.getName() + " fired: " + t.toString());
        Object obj2 = null;
        Iterator it = this.b.entrySet().iterator();
        while (true) {
            obj = obj2;
            if (!it.hasNext()) {
                break;
            }
            Entry entry = (Entry) it.next();
            final CopyOnWriteArraySet copyOnWriteArraySet = (CopyOnWriteArraySet) ((ConcurrentMap) entry.getValue()).get(cls);
            if (copyOnWriteArraySet == null || copyOnWriteArraySet.isEmpty()) {
                obj2 = obj;
            } else {
                ((Activity) entry.getKey()).runOnUiThread(new Runnable(this) {
                    final /* synthetic */ fy d;

                    public final void run() {
                        Iterator it = fy.b(cls, copyOnWriteArraySet).iterator();
                        while (it.hasNext()) {
                            ((b) it.next()).trigger(t);
                        }
                    }
                });
                obj2 = 1;
            }
        }
        CopyOnWriteArraySet copyOnWriteArraySet2 = (CopyOnWriteArraySet) this.c.get(cls);
        if (copyOnWriteArraySet2 != null) {
            it = b((Class) cls, copyOnWriteArraySet2).iterator();
            while (it.hasNext()) {
                final b bVar = (b) it.next();
                this.e.execute(new Runnable(this) {
                    final /* synthetic */ fy c;

                    public final void run() {
                        bVar.trigger(t);
                    }
                });
            }
            if (!copyOnWriteArraySet2.isEmpty()) {
                obj = 1;
            }
        }
        copyOnWriteArraySet2 = (CopyOnWriteArraySet) this.d.get(cls);
        if (copyOnWriteArraySet2 != null) {
            it = b((Class) cls, copyOnWriteArraySet2).iterator();
            while (it.hasNext()) {
                ((b) it.next()).trigger(t);
            }
            if (!copyOnWriteArraySet2.isEmpty()) {
                obj = 1;
            }
        }
        if (obj == null && cls.equals(com.appboy.c.c.class)) {
            c.d(a, "***********************************************************************************************");
            c.d(a, "**                                       !! WARNING !!                                       **");
            c.d(a, "**             InAppMessageEvent was published, but no subscribers were found.               **");
            c.d(a, "**  This is likely an integration error. Please ensure that the AppboyInAppMessageManager is **");
            c.d(a, "**               registered as early as possible. Additionally, be sure to call              **");
            c.d(a, "**       AppboyInAppMessageManager.ensureSubscribedToInAppMessageEvents(Context) in your     **");
            c.d(a, "**          Application onCreate() to avoid losing any in-app messages in the future.        **");
            c.d(a, "***********************************************************************************************");
        }
    }

    public final void a() {
        synchronized (this.h) {
            this.c.clear();
        }
        synchronized (this.i) {
            this.d.clear();
        }
        synchronized (this.g) {
            this.b.clear();
        }
    }

    private static <T> CopyOnWriteArraySet<b<T>> b(Class<T> cls, CopyOnWriteArraySet<b> copyOnWriteArraySet) {
        CopyOnWriteArraySet<b<T>> copyOnWriteArraySet2 = copyOnWriteArraySet;
        c.c(a, "Triggering " + cls.getName() + " on " + copyOnWriteArraySet.size() + " subscribers.");
        return copyOnWriteArraySet2;
    }
}
