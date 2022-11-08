package com.google.gson.a;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class g<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean f = (!g.class.desiredAssertionStatus());
    private static final Comparator<Comparable> g = new Comparator<Comparable>() {
        public final /* synthetic */ int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo((Comparable) obj2);
        }
    };
    Comparator<? super K> a;
    d<K, V> b;
    int c;
    int d;
    final d<K, V> e;
    private a h;
    private b i;

    private abstract class c<T> implements Iterator<T> {
        d<K, V> b = this.e.e.d;
        d<K, V> c = null;
        int d = this.e.d;
        final /* synthetic */ g e;

        c(g gVar) {
            this.e = gVar;
        }

        public final boolean hasNext() {
            return this.b != this.e.e;
        }

        final d<K, V> a() {
            d<K, V> e = this.b;
            if (e == this.e.e) {
                throw new NoSuchElementException();
            } else if (this.e.d != this.d) {
                throw new ConcurrentModificationException();
            } else {
                this.b = e.d;
                this.c = e;
                return e;
            }
        }

        public final void remove() {
            if (this.c == null) {
                throw new IllegalStateException();
            }
            this.e.a(this.c, true);
            this.c = null;
            this.d = this.e.d;
        }
    }

    class a extends AbstractSet<Entry<K, V>> {
        final /* synthetic */ g a;

        a(g this$0) {
            this.a = this$0;
        }

        public final int size() {
            return this.a.c;
        }

        public final Iterator<Entry<K, V>> iterator() {
            return new c<Entry<K, V>>(this) {
                final /* synthetic */ a a;

                {
                    this.a = this$1;
                    g gVar = this$1.a;
                }

                public final /* synthetic */ Object next() {
                    return a();
                }
            };
        }

        public final boolean contains(Object o) {
            return (o instanceof Entry) && this.a.a((Entry) o) != null;
        }

        public final boolean remove(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            d node = this.a.a((Entry) o);
            if (node == null) {
                return false;
            }
            this.a.a(node, true);
            return true;
        }

        public final void clear() {
            this.a.clear();
        }
    }

    final class b extends AbstractSet<K> {
        final /* synthetic */ g a;

        b(g this$0) {
            this.a = this$0;
        }

        public final int size() {
            return this.a.c;
        }

        public final Iterator<K> iterator() {
            return new c<K>(this) {
                final /* synthetic */ b a;

                {
                    this.a = this$1;
                    g gVar = this$1.a;
                }

                public final K next() {
                    return a().f;
                }
            };
        }

        public final boolean contains(Object o) {
            return this.a.containsKey(o);
        }

        public final boolean remove(Object key) {
            return this.a.a(key) != null;
        }

        public final void clear() {
            this.a.clear();
        }
    }

    static final class d<K, V> implements Entry<K, V> {
        d<K, V> a;
        d<K, V> b;
        d<K, V> c;
        d<K, V> d;
        d<K, V> e;
        final K f;
        V g;
        int h;

        d() {
            this.f = null;
            this.e = this;
            this.d = this;
        }

        d(d<K, V> parent, K key, d<K, V> next, d<K, V> prev) {
            this.a = parent;
            this.f = key;
            this.h = 1;
            this.d = next;
            this.e = prev;
            prev.d = this;
            next.e = this;
        }

        public final K getKey() {
            return this.f;
        }

        public final V getValue() {
            return this.g;
        }

        public final V setValue(V value) {
            V oldValue = this.g;
            this.g = value;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry other = (Entry) o;
            if (this.f == null) {
                if (other.getKey() != null) {
                    return false;
                }
            } else if (!this.f.equals(other.getKey())) {
                return false;
            }
            if (this.g == null) {
                if (other.getValue() != null) {
                    return false;
                }
            } else if (!this.g.equals(other.getValue())) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            int i = 0;
            int hashCode = this.f == null ? 0 : this.f.hashCode();
            if (this.g != null) {
                i = this.g.hashCode();
            }
            return hashCode ^ i;
        }

        public final String toString() {
            return this.f + "=" + this.g;
        }
    }

    public g() {
        this(g);
    }

    private g(Comparator<? super K> comparator) {
        this.c = 0;
        this.d = 0;
        this.e = new d();
        if (comparator == null) {
            comparator = g;
        }
        this.a = comparator;
    }

    public final int size() {
        return this.c;
    }

    public final V get(Object key) {
        d<K, V> node = b(key);
        return node != null ? node.g : null;
    }

    public final boolean containsKey(Object key) {
        return b(key) != null;
    }

    public final V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        d<K, V> created = a((Object) key, true);
        V result = created.g;
        created.g = value;
        return result;
    }

    public final void clear() {
        this.b = null;
        this.c = 0;
        this.d++;
        d dVar = this.e;
        dVar.e = dVar;
        dVar.d = dVar;
    }

    public final V remove(Object key) {
        d<K, V> node = a(key);
        return node != null ? node.g : null;
    }

    private d<K, V> a(K key, boolean create) {
        Comparator<? super K> comparator = this.a;
        d<K, V> nearest = this.b;
        int comparison = 0;
        if (nearest != null) {
            Comparable<Object> comparableKey = comparator == g ? (Comparable) key : null;
            while (true) {
                if (comparableKey != null) {
                    comparison = comparableKey.compareTo(nearest.f);
                } else {
                    comparison = comparator.compare(key, nearest.f);
                }
                if (comparison != 0) {
                    d<K, V> child = comparison < 0 ? nearest.b : nearest.c;
                    if (child == null) {
                        break;
                    }
                    nearest = child;
                } else {
                    return nearest;
                }
            }
        }
        if (!create) {
            return null;
        }
        d<K, V> created;
        d<K, V> header = this.e;
        if (nearest != null) {
            created = new d(nearest, key, header, header.e);
            if (comparison < 0) {
                nearest.b = created;
            } else {
                nearest.c = created;
            }
            b(nearest, true);
        } else if (comparator != g || (key instanceof Comparable)) {
            created = new d(nearest, key, header, header.e);
            this.b = created;
        } else {
            throw new ClassCastException(key.getClass().getName() + " is not Comparable");
        }
        this.c++;
        this.d++;
        return created;
    }

    private d<K, V> b(Object key) {
        d<K, V> dVar = null;
        if (key == null) {
            return dVar;
        }
        try {
            return a(key, false);
        } catch (ClassCastException e) {
            return dVar;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final d<K, V> a(Entry<?, ?> entry) {
        Object obj = 1;
        d<K, V> mine = b(entry.getKey());
        if (mine != null) {
            Object obj2 = mine.g;
            Object value = entry.getValue();
            if (obj2 == value || (obj2 != null && obj2.equals(value))) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
        }
        obj = null;
        return obj != null ? mine : null;
    }

    final void a(d<K, V> node, boolean unlink) {
        if (unlink) {
            node.e.d = node.d;
            node.d.e = node.e;
        }
        d left = node.b;
        d right = node.c;
        d<K, V> originalParent = node.a;
        if (left == null || right == null) {
            if (left != null) {
                a((d) node, left);
                node.b = null;
            } else if (right != null) {
                a((d) node, right);
                node.c = null;
            } else {
                a((d) node, null);
            }
            b(originalParent, false);
            this.c--;
            this.d++;
            return;
        }
        d adjacent;
        d<K, V> dVar;
        d<K, V> adjacent2;
        if (left.h > right.h) {
            adjacent2 = left;
            for (dVar = left.c; dVar != null; dVar = dVar.c) {
                adjacent2 = dVar;
            }
        } else {
            adjacent2 = right;
            for (dVar = right.b; dVar != null; dVar = dVar.b) {
                adjacent2 = dVar;
            }
        }
        a(adjacent2, false);
        int leftHeight = 0;
        d<K, V> left2 = node.b;
        if (left2 != null) {
            leftHeight = left2.h;
            adjacent2.b = left2;
            left2.a = adjacent2;
            node.b = null;
        }
        int rightHeight = 0;
        d<K, V> right2 = node.c;
        if (right2 != null) {
            rightHeight = right2.h;
            adjacent2.c = right2;
            right2.a = adjacent2;
            node.c = null;
        }
        adjacent2.h = Math.max(leftHeight, rightHeight) + 1;
        a((d) node, adjacent2);
    }

    final d<K, V> a(Object key) {
        d node = b(key);
        if (node != null) {
            a(node, true);
        }
        return node;
    }

    private void a(d<K, V> node, d<K, V> replacement) {
        d<K, V> parent = node.a;
        node.a = null;
        if (replacement != null) {
            replacement.a = parent;
        }
        if (parent == null) {
            this.b = replacement;
        } else if (parent.b == node) {
            parent.b = replacement;
        } else if (f || parent.c == node) {
            parent.c = replacement;
        } else {
            throw new AssertionError();
        }
    }

    private void b(d<K, V> unbalanced, boolean insert) {
        for (d node = unbalanced; node != null; node = node.a) {
            d left = node.b;
            d right = node.c;
            int leftHeight = left != null ? left.h : 0;
            int rightHeight = right != null ? right.h : 0;
            int delta = leftHeight - rightHeight;
            if (delta == -2) {
                d<K, V> rightLeft = right.b;
                d<K, V> rightRight = right.c;
                int rightDelta = (rightLeft != null ? rightLeft.h : 0) - (rightRight != null ? rightRight.h : 0);
                if (rightDelta == -1 || (rightDelta == 0 && !insert)) {
                    a(node);
                } else if (f || rightDelta == 1) {
                    b(right);
                    a(node);
                } else {
                    throw new AssertionError();
                }
                if (insert) {
                    return;
                }
            } else if (delta == 2) {
                d<K, V> leftLeft = left.b;
                d<K, V> leftRight = left.c;
                int leftDelta = (leftLeft != null ? leftLeft.h : 0) - (leftRight != null ? leftRight.h : 0);
                if (leftDelta == 1 || (leftDelta == 0 && !insert)) {
                    b(node);
                } else if (f || leftDelta == -1) {
                    a(left);
                    b(node);
                } else {
                    throw new AssertionError();
                }
                if (insert) {
                    return;
                }
            } else if (delta == 0) {
                node.h = leftHeight + 1;
                if (insert) {
                    return;
                }
            } else if (f || delta == -1 || delta == 1) {
                node.h = Math.max(leftHeight, rightHeight) + 1;
                if (!insert) {
                    return;
                }
            } else {
                throw new AssertionError();
            }
        }
    }

    private void a(d<K, V> root) {
        int i;
        int i2 = 0;
        d<K, V> left = root.b;
        d pivot = root.c;
        d<K, V> pivotLeft = pivot.b;
        d<K, V> pivotRight = pivot.c;
        root.c = pivotLeft;
        if (pivotLeft != null) {
            pivotLeft.a = root;
        }
        a((d) root, pivot);
        pivot.b = root;
        root.a = pivot;
        if (left != null) {
            i = left.h;
        } else {
            i = 0;
        }
        root.h = Math.max(i, pivotLeft != null ? pivotLeft.h : 0) + 1;
        int i3 = root.h;
        if (pivotRight != null) {
            i2 = pivotRight.h;
        }
        pivot.h = Math.max(i3, i2) + 1;
    }

    private void b(d<K, V> root) {
        int i;
        int i2 = 0;
        d pivot = root.b;
        d<K, V> right = root.c;
        d<K, V> pivotLeft = pivot.b;
        d<K, V> pivotRight = pivot.c;
        root.b = pivotRight;
        if (pivotRight != null) {
            pivotRight.a = root;
        }
        a((d) root, pivot);
        pivot.c = root;
        root.a = pivot;
        if (right != null) {
            i = right.h;
        } else {
            i = 0;
        }
        root.h = Math.max(i, pivotRight != null ? pivotRight.h : 0) + 1;
        int i3 = root.h;
        if (pivotLeft != null) {
            i2 = pivotLeft.h;
        }
        pivot.h = Math.max(i3, i2) + 1;
    }

    public final Set<Entry<K, V>> entrySet() {
        a result = this.h;
        if (result != null) {
            return result;
        }
        result = new a(this);
        this.h = result;
        return result;
    }

    public final Set<K> keySet() {
        b result = this.i;
        if (result != null) {
            return result;
        }
        result = new b(this);
        this.i = result;
        return result;
    }
}
