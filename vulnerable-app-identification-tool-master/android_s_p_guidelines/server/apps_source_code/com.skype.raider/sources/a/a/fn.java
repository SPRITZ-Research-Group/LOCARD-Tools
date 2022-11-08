package a.a;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.adjust.sdk.Constants;
import com.appboy.a.a;
import com.appboy.f.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public final class fn implements fp {
    private static final String c = c.a(fn.class);
    @VisibleForTesting
    final ConcurrentHashMap<String, av> a;
    @VisibleForTesting
    final ConcurrentHashMap<String, av> b;
    private final an d;
    private final cm e;
    private final LinkedBlockingQueue<bq> f = new LinkedBlockingQueue(Constants.ONE_SECOND);
    private final a g;

    public fn(cm cmVar, an anVar, a aVar) {
        this.e = cmVar;
        this.d = anVar;
        this.g = aVar;
        this.a = new ConcurrentHashMap();
        this.b = new ConcurrentHashMap();
    }

    public final synchronized void b(av avVar) {
        if (avVar == null) {
            c.f(c, "Tried to add null AppboyEvent to pending dispatch.");
        } else {
            this.b.putIfAbsent(avVar.d(), avVar);
        }
    }

    public final synchronized void a(@NonNull bc bcVar) {
        if (!this.b.isEmpty()) {
            c.b(c, "Flushing pending events to dispatcher map");
            for (av a : this.b.values()) {
                a.a(bcVar);
            }
            this.a.putAll(this.b);
            this.b.clear();
        }
    }

    public final void a(@NonNull av avVar) {
        if (avVar == null) {
            c.f(c, "Tried to add null AppboyEvent to dispatch.");
        } else {
            this.a.putIfAbsent(avVar.d(), avVar);
        }
    }

    public final void a(bq bqVar) {
        if (bqVar == null) {
            throw new NullPointerException();
        } else if (com.appboy.a.h()) {
            c.d(c, "Network requests are offline, not adding request to queue.");
        } else {
            c.e(c, "Adding request to dispatcher with parameters: " + cv.a(bqVar.g()));
            this.f.add(bqVar);
        }
    }

    public final boolean a() {
        return !this.f.isEmpty();
    }

    public final bq b() {
        return b((bq) this.f.take());
    }

    public final bq c() {
        bq bqVar = (bq) this.f.poll();
        if (bqVar != null) {
            b(bqVar);
        }
        return bqVar;
    }

    @VisibleForTesting
    private synchronized bq b(bq bqVar) {
        if (bqVar == null) {
            bqVar = null;
        } else {
            if (this.d.c() != null) {
                bqVar.a(this.d.c());
            }
            if (this.g.b() != null) {
                bqVar.b(this.g.b().toString());
            }
            bqVar.c("2.5.1");
            bqVar.a(co.a());
            if (!((bqVar instanceof bw) || (bqVar instanceof bo) || (bqVar instanceof bp))) {
                bqVar.d(this.d.d());
                bqVar.a(this.g.w());
                bqVar.a(this.d.b());
                bqVar.a((bj) this.e.b());
                bqVar.a(d());
            }
        }
        return bqVar;
    }

    private synchronized au d() {
        Collection arrayList;
        Collection<av> values = this.a.values();
        arrayList = new ArrayList();
        for (av avVar : values) {
            arrayList.add(avVar);
            values.remove(avVar);
            c.b(c, "Event dispatched: " + avVar.h() + " with uid: " + avVar.d());
        }
        return new au(new HashSet(arrayList));
    }
}
