package android.support.v4.util;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map.Entry;

public class g<K, V> {
    private final LinkedHashMap<K, V> a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;

    public g(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.c = maxSize;
        this.a = new LinkedHashMap(0, 0.75f, true);
    }

    public final V a(K key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V mapValue = this.a.get(key);
            if (mapValue != null) {
                this.f++;
                return mapValue;
            }
            this.g++;
            return null;
        }
    }

    public final V a(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("key == null || value == null");
        }
        V previous;
        synchronized (this) {
            this.d++;
            this.b += b(key, value);
            previous = this.a.put(key, value);
            if (previous != null) {
                this.b -= b(key, previous);
            }
        }
        a(this.c);
        return previous;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int maxSize) {
        while (true) {
            synchronized (this) {
                if (this.b >= 0 && (!this.a.isEmpty() || this.b == 0)) {
                    if (this.b <= maxSize || this.a.isEmpty()) {
                    } else {
                        Entry<K, V> toEvict = (Entry) this.a.entrySet().iterator().next();
                        K key = toEvict.getKey();
                        V value = toEvict.getValue();
                        this.a.remove(key);
                        this.b -= b(key, value);
                        this.e++;
                    }
                }
            }
        }
    }

    private int b(K key, V value) {
        int result = b(value);
        if (result >= 0) {
            return result;
        }
        throw new IllegalStateException("Negative size: " + key + "=" + value);
    }

    protected int b(V v) {
        return 1;
    }

    public final void a() {
        a(-1);
    }

    public final synchronized String toString() {
        String format;
        int hitPercent = 0;
        synchronized (this) {
            int accesses = this.f + this.g;
            if (accesses != 0) {
                hitPercent = (this.f * 100) / accesses;
            }
            format = String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.f), Integer.valueOf(this.g), Integer.valueOf(hitPercent)});
        }
        return format;
    }
}
