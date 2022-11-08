package android.support.v4.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class b<E> implements Collection<E>, Set<E> {
    static Object[] a;
    static int b;
    static Object[] c;
    static int d;
    private static final int[] j = new int[0];
    private static final Object[] k = new Object[0];
    final boolean e;
    int[] f;
    Object[] g;
    int h;
    h<E, E> i;

    private int a(Object key, int hash) {
        int N = this.h;
        if (N == 0) {
            return -1;
        }
        int index = c.a(this.f, N, hash);
        if (index < 0 || key.equals(this.g[index])) {
            return index;
        }
        int end = index + 1;
        while (end < N && this.f[end] == hash) {
            if (key.equals(this.g[end])) {
                return end;
            }
            end++;
        }
        int i = index - 1;
        while (i >= 0 && this.f[i] == hash) {
            if (key.equals(this.g[i])) {
                return i;
            }
            i--;
        }
        return end ^ -1;
    }

    private int a() {
        int N = this.h;
        if (N == 0) {
            return -1;
        }
        int index = c.a(this.f, N, 0);
        if (index < 0 || this.g[index] == null) {
            return index;
        }
        int end = index + 1;
        while (end < N && this.f[end] == 0) {
            if (this.g[end] == null) {
                return end;
            }
            end++;
        }
        int i = index - 1;
        while (i >= 0 && this.f[i] == 0) {
            if (this.g[i] == null) {
                return i;
            }
            i--;
        }
        return end ^ -1;
    }

    private void c(int size) {
        Object[] array;
        if (size == 8) {
            synchronized (b.class) {
                if (c != null) {
                    array = c;
                    this.g = array;
                    c = (Object[]) array[0];
                    this.f = (int[]) array[1];
                    array[1] = null;
                    array[0] = null;
                    d--;
                    return;
                }
            }
        } else if (size == 4) {
            synchronized (b.class) {
                if (a != null) {
                    array = a;
                    this.g = array;
                    a = (Object[]) array[0];
                    this.f = (int[]) array[1];
                    array[1] = null;
                    array[0] = null;
                    b--;
                    return;
                }
            }
        }
        this.f = new int[size];
        this.g = new Object[size];
    }

    private static void a(int[] hashes, Object[] array, int size) {
        int i;
        if (hashes.length == 8) {
            synchronized (b.class) {
                if (d < 10) {
                    array[0] = c;
                    array[1] = hashes;
                    for (i = size - 1; i >= 2; i--) {
                        array[i] = null;
                    }
                    c = array;
                    d++;
                }
            }
        } else if (hashes.length == 4) {
            synchronized (b.class) {
                if (b < 10) {
                    array[0] = a;
                    array[1] = hashes;
                    for (i = size - 1; i >= 2; i--) {
                        array[i] = null;
                    }
                    a = array;
                    b++;
                }
            }
        }
    }

    public b() {
        this((byte) 0);
    }

    private b(byte b) {
        this.e = false;
        this.f = j;
        this.g = k;
        this.h = 0;
    }

    public final void clear() {
        if (this.h != 0) {
            a(this.f, this.g, this.h);
            this.f = j;
            this.g = k;
            this.h = 0;
        }
    }

    public final boolean contains(Object key) {
        return a(key) >= 0;
    }

    public final int a(Object key) {
        if (key == null) {
            return a();
        }
        return a(key, this.e ? System.identityHashCode(key) : key.hashCode());
    }

    public final E a(int index) {
        return this.g[index];
    }

    public final boolean isEmpty() {
        return this.h <= 0;
    }

    public final boolean add(E value) {
        int hash;
        int index;
        int n = 8;
        if (value == null) {
            hash = 0;
            index = a();
        } else {
            hash = this.e ? System.identityHashCode(value) : value.hashCode();
            index = a(value, hash);
        }
        if (index >= 0) {
            return false;
        }
        index ^= -1;
        if (this.h >= this.f.length) {
            if (this.h >= 8) {
                n = this.h + (this.h >> 1);
            } else if (this.h < 4) {
                n = 4;
            }
            int[] ohashes = this.f;
            Object[] oarray = this.g;
            c(n);
            if (this.f.length > 0) {
                System.arraycopy(ohashes, 0, this.f, 0, ohashes.length);
                System.arraycopy(oarray, 0, this.g, 0, oarray.length);
            }
            a(ohashes, oarray, this.h);
        }
        if (index < this.h) {
            System.arraycopy(this.f, index, this.f, index + 1, this.h - index);
            System.arraycopy(this.g, index, this.g, index + 1, this.h - index);
        }
        this.f[index] = hash;
        this.g[index] = value;
        this.h++;
        return true;
    }

    public final boolean remove(Object object) {
        int index = a(object);
        if (index < 0) {
            return false;
        }
        b(index);
        return true;
    }

    public final E b(int index) {
        int n = 8;
        Object old = this.g[index];
        if (this.h <= 1) {
            a(this.f, this.g, this.h);
            this.f = j;
            this.g = k;
            this.h = 0;
        } else if (this.f.length <= 8 || this.h >= this.f.length / 3) {
            this.h--;
            if (index < this.h) {
                System.arraycopy(this.f, index + 1, this.f, index, this.h - index);
                System.arraycopy(this.g, index + 1, this.g, index, this.h - index);
            }
            this.g[this.h] = null;
        } else {
            if (this.h > 8) {
                n = this.h + (this.h >> 1);
            }
            int[] ohashes = this.f;
            Object[] oarray = this.g;
            c(n);
            this.h--;
            if (index > 0) {
                System.arraycopy(ohashes, 0, this.f, 0, index);
                System.arraycopy(oarray, 0, this.g, 0, index);
            }
            if (index < this.h) {
                System.arraycopy(ohashes, index + 1, this.f, index, this.h - index);
                System.arraycopy(oarray, index + 1, this.g, index, this.h - index);
            }
        }
        return old;
    }

    public final int size() {
        return this.h;
    }

    public final Object[] toArray() {
        Object[] result = new Object[this.h];
        System.arraycopy(this.g, 0, result, 0, this.h);
        return result;
    }

    public final <T> T[] toArray(T[] array) {
        if (array.length < this.h) {
            array = (Object[]) Array.newInstance(array.getClass().getComponentType(), this.h);
        }
        System.arraycopy(this.g, 0, array, 0, this.h);
        if (array.length > this.h) {
            array[this.h] = null;
        }
        return array;
    }

    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Set)) {
            return false;
        }
        Set<?> set = (Set) object;
        if (size() != set.size()) {
            return false;
        }
        int i = 0;
        while (i < this.h) {
            try {
                if (!set.contains(this.g[i])) {
                    return false;
                }
                i++;
            } catch (NullPointerException e) {
                return false;
            } catch (ClassCastException e2) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int[] hashes = this.f;
        int result = 0;
        for (int i = 0; i < this.h; i++) {
            result += hashes[i];
        }
        return result;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder buffer = new StringBuilder(this.h * 14);
        buffer.append('{');
        for (int i = 0; i < this.h; i++) {
            if (i > 0) {
                buffer.append(", ");
            }
            b value = this.g[i];
            if (value != this) {
                buffer.append(value);
            } else {
                buffer.append("(this Set)");
            }
        }
        buffer.append('}');
        return buffer.toString();
    }

    public final boolean containsAll(Collection<?> collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public final boolean addAll(Collection<? extends E> collection) {
        int size = this.h + collection.size();
        if (this.f.length < size) {
            Object obj = this.f;
            Object obj2 = this.g;
            c(size);
            if (this.h > 0) {
                System.arraycopy(obj, 0, this.f, 0, this.h);
                System.arraycopy(obj2, 0, this.g, 0, this.h);
            }
            a(obj, obj2, this.h);
        }
        boolean added = false;
        for (E value : collection) {
            added |= add(value);
        }
        return added;
    }

    public final boolean removeAll(Collection<?> collection) {
        boolean removed = false;
        for (Object value : collection) {
            removed |= remove(value);
        }
        return removed;
    }

    public final boolean retainAll(Collection<?> collection) {
        boolean removed = false;
        for (int i = this.h - 1; i >= 0; i--) {
            if (!collection.contains(this.g[i])) {
                b(i);
                removed = true;
            }
        }
        return removed;
    }

    public final Iterator<E> iterator() {
        if (this.i == null) {
            this.i = new h<E, E>(this) {
                final /* synthetic */ b a;

                {
                    this.a = this$0;
                }

                protected final int a() {
                    return this.a.h;
                }

                protected final Object a(int index, int offset) {
                    return this.a.g[index];
                }

                protected final int a(Object key) {
                    return this.a.a(key);
                }

                protected final int b(Object value) {
                    return this.a.a(value);
                }

                protected final Map<E, E> b() {
                    throw new UnsupportedOperationException("not a map");
                }

                protected final void a(E key, E e) {
                    this.a.add(key);
                }

                protected final E a(int index, E e) {
                    throw new UnsupportedOperationException("not a map");
                }

                protected final void a(int index) {
                    this.a.b(index);
                }

                protected final void c() {
                    this.a.clear();
                }
            };
        }
        return this.i.d().iterator();
    }
}
