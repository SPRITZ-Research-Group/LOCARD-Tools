package android.support.v4.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

abstract class h<K, V> {
    b b;
    c c;
    e d;

    final class a<T> implements Iterator<T> {
        final int a;
        int b;
        int c;
        boolean d = false;
        final /* synthetic */ h e;

        a(h this$0, int offset) {
            this.e = this$0;
            this.a = offset;
            this.b = this$0.a();
        }

        public final boolean hasNext() {
            return this.c < this.b;
        }

        public final T next() {
            if (hasNext()) {
                Object res = this.e.a(this.c, this.a);
                this.c++;
                this.d = true;
                return res;
            }
            throw new NoSuchElementException();
        }

        public final void remove() {
            if (this.d) {
                this.c--;
                this.b--;
                this.d = false;
                this.e.a(this.c);
                return;
            }
            throw new IllegalStateException();
        }
    }

    final class b implements Set<Entry<K, V>> {
        final /* synthetic */ h a;

        b(h this$0) {
            this.a = this$0;
        }

        public final boolean addAll(Collection<? extends Entry<K, V>> collection) {
            int oldSize = this.a.a();
            for (Entry<K, V> entry : collection) {
                this.a.a(entry.getKey(), entry.getValue());
            }
            return oldSize != this.a.a();
        }

        public final void clear() {
            this.a.c();
        }

        public final boolean contains(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry<?, ?> e = (Entry) o;
            int index = this.a.a(e.getKey());
            if (index >= 0) {
                return c.a(this.a.a(index, 1), e.getValue());
            }
            return false;
        }

        public final boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public final boolean isEmpty() {
            return this.a.a() == 0;
        }

        public final Iterator<Entry<K, V>> iterator() {
            return new d(this.a);
        }

        public final boolean remove(Object object) {
            throw new UnsupportedOperationException();
        }

        public final boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public final boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public final int size() {
            return this.a.a();
        }

        public final Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        public final <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }

        public final boolean equals(Object object) {
            return h.a((Set) this, object);
        }

        public final int hashCode() {
            int result = 0;
            for (int i = this.a.a() - 1; i >= 0; i--) {
                int i2;
                Object key = this.a.a(i, 0);
                Object value = this.a.a(i, 1);
                int hashCode = key == null ? 0 : key.hashCode();
                if (value == null) {
                    i2 = 0;
                } else {
                    i2 = value.hashCode();
                }
                result += i2 ^ hashCode;
            }
            return result;
        }

        public final /* synthetic */ boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }
    }

    final class c implements Set<K> {
        final /* synthetic */ h a;

        c(h this$0) {
            this.a = this$0;
        }

        public final boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        public final boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        public final void clear() {
            this.a.c();
        }

        public final boolean contains(Object object) {
            return this.a.a(object) >= 0;
        }

        public final boolean containsAll(Collection<?> collection) {
            Map b = this.a.b();
            for (Object containsKey : collection) {
                if (!b.containsKey(containsKey)) {
                    return false;
                }
            }
            return true;
        }

        public final boolean isEmpty() {
            return this.a.a() == 0;
        }

        public final Iterator<K> iterator() {
            return new a(this.a, 0);
        }

        public final boolean remove(Object object) {
            int index = this.a.a(object);
            if (index < 0) {
                return false;
            }
            this.a.a(index);
            return true;
        }

        public final boolean removeAll(Collection<?> collection) {
            Map b = this.a.b();
            int size = b.size();
            for (Object remove : collection) {
                b.remove(remove);
            }
            return size != b.size();
        }

        public final boolean retainAll(Collection<?> collection) {
            return h.a(this.a.b(), (Collection) collection);
        }

        public final int size() {
            return this.a.a();
        }

        public final Object[] toArray() {
            return this.a.b(0);
        }

        public final <T> T[] toArray(T[] array) {
            return this.a.a((Object[]) array, 0);
        }

        public final boolean equals(Object object) {
            return h.a((Set) this, object);
        }

        public final int hashCode() {
            int result = 0;
            for (int i = this.a.a() - 1; i >= 0; i--) {
                Object obj = this.a.a(i, 0);
                result += obj == null ? 0 : obj.hashCode();
            }
            return result;
        }
    }

    final class d implements Iterator<Entry<K, V>>, Entry<K, V> {
        int a;
        int b;
        boolean c = false;
        final /* synthetic */ h d;

        d(h this$0) {
            this.d = this$0;
            this.a = this$0.a() - 1;
            this.b = -1;
        }

        public final boolean hasNext() {
            return this.b < this.a;
        }

        public final void remove() {
            if (this.c) {
                this.d.a(this.b);
                this.b--;
                this.a--;
                this.c = false;
                return;
            }
            throw new IllegalStateException();
        }

        public final K getKey() {
            if (this.c) {
                return this.d.a(this.b, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final V getValue() {
            if (this.c) {
                return this.d.a(this.b, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final V setValue(V object) {
            if (this.c) {
                return this.d.a(this.b, (Object) object);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final boolean equals(Object o) {
            if (!this.c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            } else if (!(o instanceof Entry)) {
                return false;
            } else {
                Entry<?, ?> e = (Entry) o;
                if (c.a(e.getKey(), this.d.a(this.b, 0)) && c.a(e.getValue(), this.d.a(this.b, 1))) {
                    return true;
                }
                return false;
            }
        }

        public final int hashCode() {
            int i = 0;
            if (this.c) {
                Object key = this.d.a(this.b, 0);
                Object value = this.d.a(this.b, 1);
                int hashCode = key == null ? 0 : key.hashCode();
                if (value != null) {
                    i = value.hashCode();
                }
                return i ^ hashCode;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }

        public final /* synthetic */ Object next() {
            if (hasNext()) {
                this.b++;
                this.c = true;
                return this;
            }
            throw new NoSuchElementException();
        }
    }

    final class e implements Collection<V> {
        final /* synthetic */ h a;

        e(h this$0) {
            this.a = this$0;
        }

        public final boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        public final boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        public final void clear() {
            this.a.c();
        }

        public final boolean contains(Object object) {
            return this.a.b(object) >= 0;
        }

        public final boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public final boolean isEmpty() {
            return this.a.a() == 0;
        }

        public final Iterator<V> iterator() {
            return new a(this.a, 1);
        }

        public final boolean remove(Object object) {
            int index = this.a.b(object);
            if (index < 0) {
                return false;
            }
            this.a.a(index);
            return true;
        }

        public final boolean removeAll(Collection<?> collection) {
            int N = this.a.a();
            boolean changed = false;
            int i = 0;
            while (i < N) {
                if (collection.contains(this.a.a(i, 1))) {
                    this.a.a(i);
                    i--;
                    N--;
                    changed = true;
                }
                i++;
            }
            return changed;
        }

        public final boolean retainAll(Collection<?> collection) {
            int N = this.a.a();
            boolean changed = false;
            int i = 0;
            while (i < N) {
                if (!collection.contains(this.a.a(i, 1))) {
                    this.a.a(i);
                    i--;
                    N--;
                    changed = true;
                }
                i++;
            }
            return changed;
        }

        public final int size() {
            return this.a.a();
        }

        public final Object[] toArray() {
            return this.a.b(1);
        }

        public final <T> T[] toArray(T[] array) {
            return this.a.a((Object[]) array, 1);
        }
    }

    protected abstract int a();

    protected abstract int a(Object obj);

    protected abstract Object a(int i, int i2);

    protected abstract V a(int i, V v);

    protected abstract void a(int i);

    protected abstract void a(K k, V v);

    protected abstract int b(Object obj);

    protected abstract Map<K, V> b();

    protected abstract void c();

    h() {
    }

    public static <K, V> boolean a(Map<K, V> map, Collection<?> collection) {
        int oldSize = map.size();
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return oldSize != map.size();
    }

    public final Object[] b(int offset) {
        int N = a();
        Object[] result = new Object[N];
        for (int i = 0; i < N; i++) {
            result[i] = a(i, offset);
        }
        return result;
    }

    public final <T> T[] a(T[] array, int offset) {
        int N = a();
        if (array.length < N) {
            array = (Object[]) Array.newInstance(array.getClass().getComponentType(), N);
        }
        for (int i = 0; i < N; i++) {
            array[i] = a(i, offset);
        }
        if (array.length > N) {
            array[N] = null;
        }
        return array;
    }

    public static <T> boolean a(Set<T> set, Object object) {
        if (set == object) {
            return true;
        }
        if (!(object instanceof Set)) {
            return false;
        }
        Set<?> s = (Set) object;
        try {
            if (set.size() == s.size() && set.containsAll(s)) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        } catch (ClassCastException e2) {
            return false;
        }
    }

    public final Set<K> d() {
        if (this.c == null) {
            this.c = new c(this);
        }
        return this.c;
    }
}
