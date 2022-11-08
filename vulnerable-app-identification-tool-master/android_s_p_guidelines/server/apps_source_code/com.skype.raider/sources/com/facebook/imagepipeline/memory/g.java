package com.facebook.imagepipeline.memory;

import android.util.SparseArray;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.infer.annotation.ThreadSafe;
import java.util.LinkedList;
import javax.annotation.Nullable;

@ThreadSafe
public final class g<T> {
    protected final SparseArray<a<T>> a = new SparseArray();
    @VisibleForTesting
    @Nullable
    a<T> b;
    @VisibleForTesting
    @Nullable
    a<T> c;

    @VisibleForTesting
    static class a<I> {
        @Nullable
        a<I> a;
        int b;
        LinkedList<I> c;
        @Nullable
        a<I> d;

        /* synthetic */ a(int x1, LinkedList x2, byte b) {
            this(x1, x2);
        }

        private a(int key, LinkedList<I> value) {
            this.a = null;
            this.b = key;
            this.c = value;
            this.d = null;
        }

        public final String toString() {
            return "LinkedEntry(key: " + this.b + ")";
        }
    }

    @Nullable
    public final synchronized T a(int key) {
        T result;
        a<T> bucket = (a) this.a.get(key);
        if (bucket == null) {
            result = null;
        } else {
            result = bucket.c.pollFirst();
            b(bucket);
        }
        return result;
    }

    public final synchronized void a(int key, T value) {
        a<T> bucket = (a) this.a.get(key);
        if (bucket == null) {
            bucket = new a(key, new LinkedList(), (byte) 0);
            this.a.put(key, bucket);
        }
        bucket.c.addLast(value);
        b(bucket);
    }

    private synchronized void a(a<T> bucket) {
        a<T> prev = bucket.a;
        a<T> next = bucket.d;
        if (prev != null) {
            prev.d = next;
        }
        if (next != null) {
            next.a = prev;
        }
        bucket.a = null;
        bucket.d = null;
        if (bucket == this.b) {
            this.b = next;
        }
        if (bucket == this.c) {
            this.c = prev;
        }
    }

    private void b(a<T> bucket) {
        if (this.b != bucket) {
            a((a) bucket);
            if (this.b == null) {
                this.b = bucket;
                this.c = bucket;
                return;
            }
            bucket.d = this.b;
            this.b.a = bucket;
            this.b = bucket;
        }
    }

    @Nullable
    public final synchronized T a() {
        T value;
        a last = this.c;
        if (last == null) {
            value = null;
        } else {
            value = last.c.pollLast();
            if (last != null && last.c.isEmpty()) {
                a(last);
                this.a.remove(last.b);
            }
        }
        return value;
    }
}
