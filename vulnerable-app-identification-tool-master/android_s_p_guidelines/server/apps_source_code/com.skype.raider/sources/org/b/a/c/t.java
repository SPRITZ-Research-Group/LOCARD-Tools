package org.b.a.c;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class t<V> implements Map<Class<?>, V> {
    static final /* synthetic */ boolean a = (!t.class.desiredAssertionStatus());
    private final Map<Class<?>, V> b = new HashMap();
    private final Map<Class<?>, Class<?>> c = new HashMap();

    public /* synthetic */ Object put(Object obj, Object obj2) {
        return a((Class) obj, obj2);
    }

    public int size() {
        return this.b.size();
    }

    public boolean isEmpty() {
        return this.b.isEmpty();
    }

    public boolean containsKey(Object key) {
        if (this.c.containsKey(key)) {
            return true;
        }
        if (!(key instanceof Class)) {
            return false;
        }
        if (get((Class) key) == null) {
            return false;
        }
        return true;
    }

    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    public V get(Object key) {
        V value = this.b.get(key);
        if (value != null) {
            return value;
        }
        Class<?> redirect = (Class) this.c.get(key);
        if (redirect != null) {
            if (redirect == Void.TYPE) {
                return null;
            }
            return this.b.get(redirect);
        } else if (!(key instanceof Class)) {
            return null;
        } else {
            Class<?> keyClass = (Class) key;
            List<Class<?>> candidates = new ArrayList();
            for (Class<?> clazz : this.b.keySet()) {
                if (clazz.isAssignableFrom(keyClass)) {
                    candidates.add(clazz);
                }
            }
            if (candidates.isEmpty()) {
                this.c.put(keyClass, Void.TYPE);
                return null;
            } else if (candidates.size() == 1) {
                this.c.put(keyClass, candidates.get(0));
                return this.b.get(candidates.get(0));
            } else {
                int i;
                int j;
                for (i = 0; i < candidates.size() - 1; i++) {
                    if (candidates.get(i) != null) {
                        for (j = i + 1; j < candidates.size(); j++) {
                            if (((Class) candidates.get(i)).isAssignableFrom((Class) candidates.get(j))) {
                                candidates.set(i, null);
                                break;
                            }
                            if (((Class) candidates.get(j)).isAssignableFrom((Class) candidates.get(i))) {
                                candidates.set(j, null);
                            }
                        }
                    }
                }
                j = 0;
                for (i = 0; i < candidates.size(); i++) {
                    Class<?> current = (Class) candidates.get(i);
                    if (current != null) {
                        if (i != j) {
                            candidates.set(j, current);
                        }
                        j++;
                    }
                }
                if (!a && j <= 0) {
                    throw new AssertionError();
                } else if (j != 1) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(String.format("The class '%s' does not match a single item in the registry. The %d ambiguous matches are:", new Object[]{keyClass.getName(), Integer.valueOf(j)}));
                    for (i = 0; i < j; i++) {
                        builder.append(String.format("%n    %s", new Object[]{((Class) candidates.get(j)).getName()}));
                    }
                    throw new c(builder.toString());
                } else {
                    this.c.put(keyClass, candidates.get(0));
                    return this.b.get(candidates.get(0));
                }
            }
        }
    }

    public final V a(Class<?> key, V value) {
        V result = get(key);
        this.b.put(key, value);
        a(key);
        return result;
    }

    public V remove(Object key) {
        if (!(key instanceof Class)) {
            return null;
        }
        Class<?> clazz = (Class) key;
        V previous = get(clazz);
        if (this.b.remove(clazz) == null) {
            return previous;
        }
        a(clazz);
        return previous;
    }

    public void putAll(Map<? extends Class<?>, ? extends V> m) {
        for (Entry<? extends Class<?>, ? extends V> entry : m.entrySet()) {
            a((Class) entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        this.b.clear();
        this.c.clear();
    }

    public Set<Class<?>> keySet() {
        return Collections.unmodifiableSet(this.b.keySet());
    }

    public Collection<V> values() {
        return Collections.unmodifiableCollection(this.b.values());
    }

    public Set<Entry<Class<?>, V>> entrySet() {
        return Collections.unmodifiableSet(this.b.entrySet());
    }

    private void a(Class<?> clazz) {
        for (Entry<Class<?>, ?> entry : this.c.entrySet()) {
            if (clazz.isAssignableFrom((Class) entry.getKey())) {
                entry.setValue(null);
            }
        }
    }
}
