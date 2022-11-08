package androidx.databinding;

import java.util.ArrayList;
import java.util.List;

public class c<C, T, A> implements Cloneable {
    private List<C> a = new ArrayList();
    private long b = 0;
    private long[] c;
    private int d;
    private final d<C, T, A> e;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }

    public c(d<C, T, A> dVar) {
        this.e = dVar;
    }

    public synchronized void a(T t, int i, A a) {
        this.d++;
        int size = this.a.size();
        int length = this.c == null ? -1 : this.c.length - 1;
        a(t, i, a, length);
        a(t, i, a, (length + 2) * 64, size, 0);
        this.d--;
        if (this.d == 0) {
            if (this.c != null) {
                for (int length2 = this.c.length - 1; length2 >= 0; length2--) {
                    long j = this.c[length2];
                    if (j != 0) {
                        a((length2 + 1) * 64, j);
                        this.c[length2] = 0;
                    }
                }
            }
            if (this.b != 0) {
                a(0, this.b);
                this.b = 0;
            }
        }
    }

    private void b(T t, int i, A a) {
        a(t, i, a, 0, Math.min(64, this.a.size()), this.b);
    }

    private void a(T t, int i, A a, int i2) {
        if (i2 < 0) {
            b(t, i, a);
            return;
        }
        long j = this.c[i2];
        int i3 = (i2 + 1) * 64;
        int min = Math.min(this.a.size(), i3 + 64);
        a(t, i, a, i2 - 1);
        a(t, i, a, i3, min, j);
    }

    private void a(T t, int i, A a, int i2, int i3, long j) {
        long j2 = 1;
        while (i2 < i3) {
            if ((j & j2) == 0) {
                this.e.a(this.a.get(i2), t, i, a);
            }
            j2 <<= 1;
            i2++;
        }
    }

    public final synchronized void a(C c) {
        if (c != null) {
            int lastIndexOf = this.a.lastIndexOf(c);
            if (lastIndexOf < 0 || a(lastIndexOf)) {
                this.a.add(c);
            }
        } else {
            throw new IllegalArgumentException("callback cannot be null");
        }
    }

    private boolean a(int i) {
        if (i < 64) {
            return ((1 << i) & this.b) != 0;
        } else {
            if (this.c == null) {
                return false;
            }
            int i2 = (i / 64) - 1;
            if (i2 >= this.c.length) {
                return false;
            }
            return ((1 << (i % 64)) & this.c[i2]) != 0;
        }
    }

    private void a(int i, long j) {
        long j2 = Long.MIN_VALUE;
        for (int i2 = (i + 64) - 1; i2 >= i; i2--) {
            if ((j & j2) != 0) {
                this.a.remove(i2);
            }
            j2 >>>= 1;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void b(C c) {
        if (this.d == 0) {
            this.a.remove(c);
            return;
        }
        int lastIndexOf = this.a.lastIndexOf(c);
        if (lastIndexOf >= 0) {
            if (lastIndexOf < 64) {
                this.b = (1 << lastIndexOf) | this.b;
                return;
            }
            int i = (lastIndexOf / 64) - 1;
            if (this.c == null) {
                this.c = new long[(this.a.size() / 64)];
            } else if (this.c.length <= i) {
                Object obj = new long[(this.a.size() / 64)];
                System.arraycopy(this.c, 0, obj, 0, this.c.length);
                this.c = obj;
            }
            long j = 1 << (lastIndexOf % 64);
            long[] jArr = this.c;
            jArr[i] = j | jArr[i];
        }
    }

    private synchronized c<C, T, A> a() {
        c<C, T, A> cVar;
        CloneNotSupportedException e;
        try {
            cVar = (c) super.clone();
            try {
                cVar.b = 0;
                cVar.c = null;
                int i = 0;
                cVar.d = 0;
                cVar.a = new ArrayList();
                int size = this.a.size();
                while (i < size) {
                    if (!a(i)) {
                        cVar.a.add(this.a.get(i));
                    }
                    i++;
                }
            } catch (CloneNotSupportedException e2) {
                e = e2;
                e.printStackTrace();
                return cVar;
            }
        } catch (CloneNotSupportedException e3) {
            CloneNotSupportedException cloneNotSupportedException = e3;
            cVar = null;
            e = cloneNotSupportedException;
            e.printStackTrace();
            return cVar;
        }
        return cVar;
    }
}
