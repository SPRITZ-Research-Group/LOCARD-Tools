package com.facebook.react.bridge;

import android.util.Pair;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.WeakHashMap;

public final class ao<T> {
    private WeakHashMap<T, Void> a = new WeakHashMap();
    private Queue<Pair<T, a>> b = new ArrayDeque();
    private boolean c;

    public interface b<T> {
        void b(T t);
    }

    private enum a {
        ADD,
        REMOVE
    }

    public final void a(T item) {
        synchronized (this.a) {
            if (this.c) {
                this.b.add(new Pair(item, a.ADD));
            } else {
                this.a.put(item, null);
            }
        }
    }

    public final void b(T item) {
        synchronized (this.a) {
            if (this.c) {
                this.b.add(new Pair(item, a.REMOVE));
            } else {
                this.a.remove(item);
            }
        }
    }

    public final void a(b<T> iterated) {
        synchronized (this.a) {
            this.c = true;
            for (T listener : this.a.keySet()) {
                iterated.b(listener);
            }
            this.c = false;
            while (!this.b.isEmpty()) {
                Pair<T, a> pair = (Pair) this.b.poll();
                switch ((a) pair.second) {
                    case ADD:
                        this.a.put(pair.first, null);
                        break;
                    case REMOVE:
                        this.a.remove(pair.first);
                        break;
                    default:
                        throw new c("Unsupported command" + pair.second);
                }
            }
        }
    }
}
