package com.facebook.ads.internal.j;

import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public final class e<T extends f, E extends d> {
    private final Map<Class<E>, List<WeakReference<T>>> a = new HashMap();
    private final Queue<E> b = new ArrayDeque();

    private static void a(List<WeakReference<T>> list) {
        int i = 0;
        if (list != null) {
            int i2;
            int i3 = 0;
            while (true) {
                i2 = i;
                if (i3 >= list.size()) {
                    break;
                }
                WeakReference weakReference = (WeakReference) list.get(i3);
                if (weakReference.get() != null) {
                    int i4 = i2 + 1;
                    list.set(i2, weakReference);
                    i2 = i4;
                }
                i = i3 + 1;
            }
            for (i = list.size() - 1; i >= i2; i--) {
                list.remove(i);
            }
        }
    }

    public final synchronized void a(E e) {
        if (this.b.isEmpty()) {
            this.b.add(e);
            while (!this.b.isEmpty()) {
                d dVar = (d) this.b.peek();
                if (this.a != null) {
                    List list = (List) this.a.get(dVar.getClass());
                    if (list != null) {
                        a(list);
                        if (!list.isEmpty()) {
                            for (WeakReference weakReference : new ArrayList(list)) {
                                f fVar = (f) weakReference.get();
                                if (fVar != null) {
                                    fVar.a(dVar);
                                }
                            }
                        }
                    }
                }
                this.b.remove();
            }
        } else {
            this.b.add(e);
        }
    }

    public final synchronized void a(T... tArr) {
        for (f a : tArr) {
            a(a);
        }
    }

    public final synchronized boolean a(T t) {
        boolean z;
        if (t == null) {
            z = false;
        } else {
            Class a = t.a();
            if (this.a.get(a) == null) {
                this.a.put(a, new ArrayList());
            }
            List list = (List) this.a.get(a);
            a(list);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (((WeakReference) list.get(i)).get() == t) {
                    z = false;
                    break;
                }
            }
            z = list.add(new WeakReference(t));
        }
        return z;
    }

    public final synchronized void b(T... tArr) {
        for (f b : tArr) {
            b(b);
        }
    }

    public final synchronized boolean b(@Nullable T t) {
        boolean z;
        if (t == null) {
            z = false;
        } else {
            List list = (List) this.a.get(t.a());
            if (list == null) {
                z = false;
            } else {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (((WeakReference) list.get(i)).get() == t) {
                        ((WeakReference) list.get(i)).clear();
                        z = true;
                        break;
                    }
                }
                z = false;
            }
        }
        return z;
    }
}
