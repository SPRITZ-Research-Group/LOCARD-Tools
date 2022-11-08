package android.arch.a.a;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public class b<K, V> implements Iterable<Entry<K, V>> {
    private c<K, V> a;
    private c<K, V> b;
    private WeakHashMap<Object<K, V>, Boolean> c = new WeakHashMap();
    private int d = 0;

    private static abstract class e<K, V> implements Iterator<Entry<K, V>> {
        c<K, V> a;
        c<K, V> b;

        abstract c<K, V> a(c<K, V> cVar);

        e(c<K, V> start, c<K, V> expectedEnd) {
            this.a = expectedEnd;
            this.b = start;
        }

        public boolean hasNext() {
            return this.b != null;
        }

        public /* synthetic */ Object next() {
            c cVar;
            c cVar2 = this.b;
            if (this.b == this.a || this.a == null) {
                cVar = null;
            } else {
                cVar = a(this.b);
            }
            this.b = cVar;
            return cVar2;
        }
    }

    static class a<K, V> extends e<K, V> {
        a(c<K, V> start, c<K, V> expectedEnd) {
            super(start, expectedEnd);
        }

        final c<K, V> a(c<K, V> entry) {
            return entry.c;
        }
    }

    private static class b<K, V> extends e<K, V> {
        b(c<K, V> start, c<K, V> expectedEnd) {
            super(start, expectedEnd);
        }

        final c<K, V> a(c<K, V> entry) {
            return entry.d;
        }
    }

    static class c<K, V> implements Entry<K, V> {
        @NonNull
        final K a;
        @NonNull
        final V b;
        c<K, V> c;
        c<K, V> d;

        @NonNull
        public final K getKey() {
            return this.a;
        }

        @NonNull
        public final V getValue() {
            return this.b;
        }

        public final V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public final String toString() {
            return this.a + "=" + this.b;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c entry = (c) obj;
            if (this.a.equals(entry.a) && this.b.equals(entry.b)) {
                return true;
            }
            return false;
        }
    }

    private class d implements Iterator<Entry<K, V>> {
        final /* synthetic */ b a;
        private c<K, V> b;
        private boolean c;

        private d(b bVar) {
            this.a = bVar;
            this.c = true;
        }

        /* synthetic */ d(b x0, byte b) {
            this(x0);
        }

        public final boolean hasNext() {
            if (this.c) {
                if (this.a.a != null) {
                    return true;
                }
                return false;
            } else if (this.b == null || this.b.c == null) {
                return false;
            } else {
                return true;
            }
        }

        public final /* synthetic */ Object next() {
            c a;
            d this;
            d this2;
            if (this.c) {
                this.c = false;
                a = this.a.a;
                this2 = this;
            } else if (this.b != null) {
                a = this.b.c;
                this2 = this;
            } else {
                a = null;
                this2 = this;
            }
            this2.b = a;
            return this.b;
        }
    }

    public final int a() {
        return this.d;
    }

    @NonNull
    public Iterator<Entry<K, V>> iterator() {
        e<K, V> iterator = new a(this.a, this.b);
        this.c.put(iterator, Boolean.valueOf(false));
        return iterator;
    }

    public final Iterator<Entry<K, V>> b() {
        b<K, V> iterator = new b(this.b, this.a);
        this.c.put(iterator, Boolean.valueOf(false));
        return iterator;
    }

    public final d c() {
        d iterator = new d();
        this.c.put(iterator, Boolean.valueOf(false));
        return iterator;
    }

    public final Entry<K, V> d() {
        return this.a;
    }

    public final Entry<K, V> e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b map = (b) obj;
        if (this.d != map.d) {
            return false;
        }
        Iterator<Entry<K, V>> iterator1 = iterator();
        Iterator iterator2 = map.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            Entry<K, V> next1 = (Entry) iterator1.next();
            Object next2 = iterator2.next();
            if ((next1 == null && next2 != null) || (next1 != null && !next1.equals(next2))) {
                return false;
            }
        }
        if (iterator1.hasNext() || iterator2.hasNext()) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Iterator<Entry<K, V>> iterator = iterator();
        while (iterator.hasNext()) {
            builder.append(((Entry) iterator.next()).toString());
            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
