package android.support.v4.util;

import java.util.ConcurrentModificationException;
import java.util.Map;

public class l<K, V> {
    static Object[] b;
    static int c;
    static Object[] d;
    static int e;
    int[] f;
    Object[] g;
    int h;

    private static int a(int[] hashes, int N, int hash) {
        try {
            return c.a(hashes, N, hash);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }

    private int a(Object key, int hash) {
        int N = this.h;
        if (N == 0) {
            return -1;
        }
        int index = a(this.f, N, hash);
        if (index < 0 || key.equals(this.g[index << 1])) {
            return index;
        }
        int end = index + 1;
        while (end < N && this.f[end] == hash) {
            if (key.equals(this.g[end << 1])) {
                return end;
            }
            end++;
        }
        int i = index - 1;
        while (i >= 0 && this.f[i] == hash) {
            if (key.equals(this.g[i << 1])) {
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
        int index = a(this.f, N, 0);
        if (index < 0 || this.g[index << 1] == null) {
            return index;
        }
        int end = index + 1;
        while (end < N && this.f[end] == 0) {
            if (this.g[end << 1] == null) {
                return end;
            }
            end++;
        }
        int i = index - 1;
        while (i >= 0 && this.f[i] == 0) {
            if (this.g[i << 1] == null) {
                return i;
            }
            i--;
        }
        return end ^ -1;
    }

    private void e(int size) {
        Object[] array;
        if (size == 8) {
            synchronized (a.class) {
                if (d != null) {
                    array = d;
                    this.g = array;
                    d = (Object[]) array[0];
                    this.f = (int[]) array[1];
                    array[1] = null;
                    array[0] = null;
                    e--;
                    return;
                }
            }
        } else if (size == 4) {
            synchronized (a.class) {
                if (b != null) {
                    array = b;
                    this.g = array;
                    b = (Object[]) array[0];
                    this.f = (int[]) array[1];
                    array[1] = null;
                    array[0] = null;
                    c--;
                    return;
                }
            }
        }
        this.f = new int[size];
        this.g = new Object[(size << 1)];
    }

    private static void a(int[] hashes, Object[] array, int size) {
        int i;
        if (hashes.length == 8) {
            synchronized (a.class) {
                if (e < 10) {
                    array[0] = d;
                    array[1] = hashes;
                    for (i = (size << 1) - 1; i >= 2; i--) {
                        array[i] = null;
                    }
                    d = array;
                    e++;
                }
            }
        } else if (hashes.length == 4) {
            synchronized (a.class) {
                if (c < 10) {
                    array[0] = b;
                    array[1] = hashes;
                    for (i = (size << 1) - 1; i >= 2; i--) {
                        array[i] = null;
                    }
                    b = array;
                    c++;
                }
            }
        }
    }

    public l() {
        this.f = c.a;
        this.g = c.c;
        this.h = 0;
    }

    public l(int capacity) {
        if (capacity == 0) {
            this.f = c.a;
            this.g = c.c;
        } else {
            e(capacity);
        }
        this.h = 0;
    }

    public void clear() {
        if (this.h > 0) {
            int[] ohashes = this.f;
            Object[] oarray = this.g;
            int osize = this.h;
            this.f = c.a;
            this.g = c.c;
            this.h = 0;
            a(ohashes, oarray, osize);
        }
        if (this.h > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public final void a(int minimumCapacity) {
        int osize = this.h;
        if (this.f.length < minimumCapacity) {
            int[] ohashes = this.f;
            Object[] oarray = this.g;
            e(minimumCapacity);
            if (this.h > 0) {
                System.arraycopy(ohashes, 0, this.f, 0, osize);
                System.arraycopy(oarray, 0, this.g, 0, osize << 1);
            }
            a(ohashes, oarray, osize);
        }
        if (this.h != osize) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object key) {
        return a(key) >= 0;
    }

    public final int a(Object key) {
        return key == null ? a() : a(key, key.hashCode());
    }

    final int b(Object value) {
        int N = this.h * 2;
        Object[] array = this.g;
        int i;
        if (value == null) {
            for (i = 1; i < N; i += 2) {
                if (array[i] == null) {
                    return i >> 1;
                }
            }
        } else {
            for (i = 1; i < N; i += 2) {
                if (value.equals(array[i])) {
                    return i >> 1;
                }
            }
        }
        return -1;
    }

    public boolean containsValue(Object value) {
        return b(value) >= 0;
    }

    public V get(Object key) {
        int index = a(key);
        return index >= 0 ? this.g[(index << 1) + 1] : null;
    }

    public final K b(int index) {
        return this.g[index << 1];
    }

    public final V c(int index) {
        return this.g[(index << 1) + 1];
    }

    public boolean isEmpty() {
        return this.h <= 0;
    }

    public V put(K key, V value) {
        int hash;
        int index;
        int n = 8;
        int osize = this.h;
        if (key == null) {
            hash = 0;
            index = a();
        } else {
            hash = key.hashCode();
            index = a(key, hash);
        }
        if (index >= 0) {
            index = (index << 1) + 1;
            V old = this.g[index];
            this.g[index] = value;
            return old;
        }
        index ^= -1;
        if (osize >= this.f.length) {
            if (osize >= 8) {
                n = osize + (osize >> 1);
            } else if (osize < 4) {
                n = 4;
            }
            int[] ohashes = this.f;
            Object[] oarray = this.g;
            e(n);
            if (osize != this.h) {
                throw new ConcurrentModificationException();
            }
            if (this.f.length > 0) {
                System.arraycopy(ohashes, 0, this.f, 0, ohashes.length);
                System.arraycopy(oarray, 0, this.g, 0, oarray.length);
            }
            a(ohashes, oarray, osize);
        }
        if (index < osize) {
            System.arraycopy(this.f, index, this.f, index + 1, osize - index);
            System.arraycopy(this.g, index << 1, this.g, (index + 1) << 1, (this.h - index) << 1);
        }
        if (osize != this.h || index >= this.f.length) {
            throw new ConcurrentModificationException();
        }
        this.f[index] = hash;
        this.g[index << 1] = key;
        this.g[(index << 1) + 1] = value;
        this.h++;
        return null;
    }

    public V remove(Object key) {
        int index = a(key);
        if (index >= 0) {
            return d(index);
        }
        return null;
    }

    public final V d(int index) {
        int nsize;
        int n = 8;
        Object old = this.g[(index << 1) + 1];
        int osize = this.h;
        if (osize <= 1) {
            a(this.f, this.g, osize);
            this.f = c.a;
            this.g = c.c;
            nsize = 0;
        } else {
            nsize = osize - 1;
            if (this.f.length <= 8 || this.h >= this.f.length / 3) {
                if (index < nsize) {
                    System.arraycopy(this.f, index + 1, this.f, index, nsize - index);
                    System.arraycopy(this.g, (index + 1) << 1, this.g, index << 1, (nsize - index) << 1);
                }
                this.g[nsize << 1] = null;
                this.g[(nsize << 1) + 1] = null;
            } else {
                if (osize > 8) {
                    n = osize + (osize >> 1);
                }
                int[] ohashes = this.f;
                Object[] oarray = this.g;
                e(n);
                if (osize != this.h) {
                    throw new ConcurrentModificationException();
                }
                if (index > 0) {
                    System.arraycopy(ohashes, 0, this.f, 0, index);
                    System.arraycopy(oarray, 0, this.g, 0, index << 1);
                }
                if (index < nsize) {
                    System.arraycopy(ohashes, index + 1, this.f, index, nsize - index);
                    System.arraycopy(oarray, (index + 1) << 1, this.g, index << 1, (nsize - index) << 1);
                }
            }
        }
        if (osize != this.h) {
            throw new ConcurrentModificationException();
        }
        this.h = nsize;
        return old;
    }

    public int size() {
        return this.h;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        int i;
        K key;
        V mine;
        Object theirs;
        if (object instanceof l) {
            l<?, ?> map = (l) object;
            if (size() != map.size()) {
                return false;
            }
            i = 0;
            while (i < this.h) {
                try {
                    key = b(i);
                    mine = c(i);
                    theirs = map.get(key);
                    if (mine == null) {
                        if (theirs != null || !map.containsKey(key)) {
                            return false;
                        }
                    } else if (!mine.equals(theirs)) {
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
        } else if (!(object instanceof Map)) {
            return false;
        } else {
            Map<?, ?> map2 = (Map) object;
            if (size() != map2.size()) {
                return false;
            }
            i = 0;
            while (i < this.h) {
                try {
                    key = b(i);
                    mine = c(i);
                    theirs = map2.get(key);
                    if (mine == null) {
                        if (theirs != null || !map2.containsKey(key)) {
                            return false;
                        }
                    } else if (!mine.equals(theirs)) {
                        return false;
                    }
                    i++;
                } catch (NullPointerException e3) {
                    return false;
                } catch (ClassCastException e4) {
                    return false;
                }
            }
            return true;
        }
    }

    public int hashCode() {
        int[] hashes = this.f;
        Object[] array = this.g;
        int result = 0;
        int i = 0;
        int v = 1;
        int s = this.h;
        while (i < s) {
            Object value = array[v];
            result += (value == null ? 0 : value.hashCode()) ^ hashes[i];
            i++;
            v += 2;
        }
        return result;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder buffer = new StringBuilder(this.h * 28);
        buffer.append('{');
        for (int i = 0; i < this.h; i++) {
            if (i > 0) {
                buffer.append(", ");
            }
            l key = b(i);
            if (key != this) {
                buffer.append(key);
            } else {
                buffer.append("(this Map)");
            }
            buffer.append('=');
            l value = c(i);
            if (value != this) {
                buffer.append(value);
            } else {
                buffer.append("(this Map)");
            }
        }
        buffer.append('}');
        return buffer.toString();
    }
}
