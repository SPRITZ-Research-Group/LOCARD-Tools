package android.support.v4.util;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class a<K, V> extends l<K, V> implements Map<K, V> {
    h<K, V> a;

    public a(int capacity) {
        super(capacity);
    }

    private h<K, V> a() {
        if (this.a == null) {
            this.a = new h<K, V>(this) {
                final /* synthetic */ a a;

                {
                    this.a = this$0;
                }

                protected final int a() {
                    return this.a.h;
                }

                protected final Object a(int index, int offset) {
                    return this.a.g[(index << 1) + offset];
                }

                protected final int a(Object key) {
                    return this.a.a(key);
                }

                protected final int b(Object value) {
                    return this.a.b(value);
                }

                protected final Map<K, V> b() {
                    return this.a;
                }

                protected final void a(K key, V value) {
                    this.a.put(key, value);
                }

                protected final V a(int index, V value) {
                    l lVar = this.a;
                    int i = (index << 1) + 1;
                    V v = lVar.g[i];
                    lVar.g[i] = value;
                    return v;
                }

                protected final void a(int index) {
                    this.a.d(index);
                }

                protected final void c() {
                    this.a.clear();
                }
            };
        }
        return this.a;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        a(this.h + map.size());
        for (Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public final boolean a(Collection<?> collection) {
        return h.a((Map) this, (Collection) collection);
    }

    public Set<Entry<K, V>> entrySet() {
        h a = a();
        if (a.b == null) {
            a.b = new b(a);
        }
        return a.b;
    }

    public Set<K> keySet() {
        return a().d();
    }

    public Collection<V> values() {
        h a = a();
        if (a.d == null) {
            a.d = new e(a);
        }
        return a.d;
    }
}
