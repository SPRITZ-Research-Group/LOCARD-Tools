package android.support.v4.util;

public final class m<E> implements Cloneable {
    private static final Object a = new Object();
    private boolean b;
    private int[] c;
    private Object[] d;
    private int e;

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return c();
    }

    public m() {
        this(10);
    }

    public m(int initialCapacity) {
        this.b = false;
        if (initialCapacity == 0) {
            this.c = c.a;
            this.d = c.c;
        } else {
            initialCapacity = c.a(initialCapacity);
            this.c = new int[initialCapacity];
            this.d = new Object[initialCapacity];
        }
        this.e = 0;
    }

    private m<E> c() {
        m<E> clone = null;
        try {
            clone = (m) super.clone();
            clone.c = (int[]) this.c.clone();
            clone.d = (Object[]) this.d.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            return clone;
        }
    }

    private void d() {
        int n = this.e;
        int o = 0;
        int[] keys = this.c;
        Object[] values = this.d;
        for (int i = 0; i < n; i++) {
            Object val = values[i];
            if (val != a) {
                if (i != o) {
                    keys[o] = keys[i];
                    values[o] = val;
                    values[i] = null;
                }
                o++;
            }
        }
        this.b = false;
        this.e = o;
    }

    public final void a(int key, E value) {
        int i = c.a(this.c, this.e, key);
        if (i >= 0) {
            this.d[i] = value;
            return;
        }
        i ^= -1;
        if (i >= this.e || this.d[i] != a) {
            if (this.b && this.e >= this.c.length) {
                d();
                i = c.a(this.c, this.e, key) ^ -1;
            }
            if (this.e >= this.c.length) {
                int n = c.a(this.e + 1);
                int[] nkeys = new int[n];
                Object[] nvalues = new Object[n];
                System.arraycopy(this.c, 0, nkeys, 0, this.c.length);
                System.arraycopy(this.d, 0, nvalues, 0, this.d.length);
                this.c = nkeys;
                this.d = nvalues;
            }
            if (this.e - i != 0) {
                System.arraycopy(this.c, i, this.c, i + 1, this.e - i);
                System.arraycopy(this.d, i, this.d, i + 1, this.e - i);
            }
            this.c[i] = key;
            this.d[i] = value;
            this.e++;
            return;
        }
        this.c[i] = key;
        this.d[i] = value;
    }

    public final int a() {
        if (this.b) {
            d();
        }
        return this.e;
    }

    public final int c(int index) {
        if (this.b) {
            d();
        }
        return this.c[index];
    }

    public final E d(int index) {
        if (this.b) {
            d();
        }
        return this.d[index];
    }

    public final void b() {
        int n = this.e;
        Object[] values = this.d;
        for (int i = 0; i < n; i++) {
            values[i] = null;
        }
        this.e = 0;
        this.b = false;
    }

    public final void b(int key, E value) {
        if (this.e == 0 || key > this.c[this.e - 1]) {
            if (this.b && this.e >= this.c.length) {
                d();
            }
            int pos = this.e;
            if (pos >= this.c.length) {
                int n = c.a(pos + 1);
                int[] nkeys = new int[n];
                Object[] nvalues = new Object[n];
                System.arraycopy(this.c, 0, nkeys, 0, this.c.length);
                System.arraycopy(this.d, 0, nvalues, 0, this.d.length);
                this.c = nkeys;
                this.d = nvalues;
            }
            this.c[pos] = key;
            this.d[pos] = value;
            this.e = pos + 1;
            return;
        }
        a(key, value);
    }

    public final String toString() {
        if (a() <= 0) {
            return "{}";
        }
        StringBuilder buffer = new StringBuilder(this.e * 28);
        buffer.append('{');
        for (int i = 0; i < this.e; i++) {
            if (i > 0) {
                buffer.append(", ");
            }
            buffer.append(c(i));
            buffer.append('=');
            m value = d(i);
            if (value != this) {
                buffer.append(value);
            } else {
                buffer.append("(this Map)");
            }
        }
        buffer.append('}');
        return buffer.toString();
    }

    public final E a(int key) {
        int a = c.a(this.c, this.e, key);
        if (a < 0 || this.d[a] == a) {
            return null;
        }
        return this.d[a];
    }

    public final void b(int key) {
        int a = c.a(this.c, this.e, key);
        if (a >= 0 && this.d[a] != a) {
            this.d[a] = a;
            this.b = true;
        }
    }
}
