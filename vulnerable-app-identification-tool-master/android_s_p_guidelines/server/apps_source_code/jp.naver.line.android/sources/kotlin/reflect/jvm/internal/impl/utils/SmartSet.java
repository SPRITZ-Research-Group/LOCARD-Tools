package kotlin.reflect.jvm.internal.impl.utils;

import defpackage.acno;
import defpackage.acot;
import defpackage.acro;
import defpackage.acru;
import defpackage.acry;
import defpackage.acss;
import defpackage.acsw;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.v;

public final class SmartSet<T> extends AbstractSet<T> {
    private static final int ARRAY_THRESHOLD = 5;
    public static final Companion Companion = new Companion();
    private Object data;
    private int size;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }

        public final <T> SmartSet<T> create() {
            return new SmartSet();
        }

        public final <T> SmartSet<T> create(Collection<? extends T> collection) {
            SmartSet<T> smartSet = new SmartSet();
            smartSet.addAll(collection);
            return smartSet;
        }
    }

    final class ArrayIterator<T> implements acsw, Iterator<T> {
        private final Iterator<T> arrayIterator;

        public ArrayIterator(T[] tArr) {
            this.arrayIterator = acro.a(tArr);
        }

        public final boolean hasNext() {
            return this.arrayIterator.hasNext();
        }

        public final T next() {
            return this.arrayIterator.next();
        }

        public final Void remove() {
            throw new UnsupportedOperationException();
        }
    }

    final class SingletonIterator<T> implements acsw, Iterator<T> {
        private final T element;
        private boolean hasNext = true;

        public SingletonIterator(T t) {
            this.element = t;
        }

        public final T next() {
            if (this.hasNext) {
                this.hasNext = false;
                return this.element;
            }
            throw new NoSuchElementException();
        }

        public final boolean hasNext() {
            return this.hasNext;
        }

        public final Void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static final <T> SmartSet<T> create() {
        return Companion.create();
    }

    private SmartSet() {
    }

    public /* synthetic */ SmartSet(acru acru) {
        this();
    }

    public final int size() {
        return getSize();
    }

    public final int getSize() {
        return this.size;
    }

    public final void setSize(int i) {
        this.size = i;
    }

    public final Iterator<T> iterator() {
        if (size() == 0) {
            return Collections.emptySet().iterator();
        }
        if (size() == 1) {
            return new SingletonIterator(this.data);
        }
        if (size() < ARRAY_THRESHOLD) {
            Object obj = this.data;
            if (obj != null) {
                return new ArrayIterator((Object[]) obj);
            }
            throw new v("null cannot be cast to non-null type kotlin.Array<T>");
        }
        Object obj2 = this.data;
        if (obj2 != null) {
            return acss.e(obj2).iterator();
        }
        throw new v("null cannot be cast to non-null type kotlin.collections.MutableSet<T>");
    }

    public final boolean add(T t) {
        Object obj;
        if (size() == 0) {
            this.data = t;
        } else if (size() == 1) {
            if (acry.a(this.data, (Object) t)) {
                return false;
            }
            this.data = new Object[]{this.data, t};
        } else if (size() < ARRAY_THRESHOLD) {
            obj = this.data;
            if (obj != null) {
                Object[] objArr = (Object[]) obj;
                if (acno.b(objArr, (Object) t)) {
                    return false;
                }
                if (size() == ARRAY_THRESHOLD - 1) {
                    obj = acot.b(Arrays.copyOf(objArr, objArr.length));
                    obj.add(t);
                } else {
                    obj = Arrays.copyOf(objArr, size() + 1);
                    obj[obj.length - 1] = t;
                }
                this.data = obj;
            } else {
                throw new v("null cannot be cast to non-null type kotlin.Array<T>");
            }
        } else {
            obj = this.data;
            if (obj == null) {
                throw new v("null cannot be cast to non-null type kotlin.collections.MutableSet<T>");
            } else if (!acss.e(obj).add(t)) {
                return false;
            }
        }
        setSize(size() + 1);
        return true;
    }

    public final void clear() {
        this.data = null;
        setSize(0);
    }

    public final boolean contains(Object obj) {
        if (size() == 0) {
            return false;
        }
        if (size() == 1) {
            return acry.a(this.data, obj);
        }
        Object obj2;
        if (size() < ARRAY_THRESHOLD) {
            obj2 = this.data;
            if (obj2 != null) {
                return acno.b((Object[]) obj2, obj);
            }
            throw new v("null cannot be cast to non-null type kotlin.Array<T>");
        }
        obj2 = this.data;
        if (obj2 != null) {
            return ((Set) obj2).contains(obj);
        }
        throw new v("null cannot be cast to non-null type kotlin.collections.Set<T>");
    }
}
