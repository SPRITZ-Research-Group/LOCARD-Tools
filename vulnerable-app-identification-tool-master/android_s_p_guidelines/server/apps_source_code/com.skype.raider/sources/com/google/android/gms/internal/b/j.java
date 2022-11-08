package com.google.android.gms.internal.b;

import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class j {
    private final ConcurrentHashMap<k, List<Throwable>> a = new ConcurrentHashMap(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> b = new ReferenceQueue();

    j() {
    }

    public final List<Throwable> a(Throwable th) {
        Object poll = this.b.poll();
        while (poll != null) {
            this.a.remove(poll);
            poll = this.b.poll();
        }
        List<Throwable> list = (List) this.a.get(new k(th, null));
        if (list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        list = (List) this.a.putIfAbsent(new k(th, this.b), vector);
        return list == null ? vector : list;
    }
}
