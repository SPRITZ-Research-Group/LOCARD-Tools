package kotlin.reflect.jvm.internal.impl.utils;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class SmartList<E> extends AbstractList<E> implements RandomAccess {
    private Object myElem;
    private int mySize;

    class EmptyIterator<T> implements Iterator<T> {
        private static final EmptyIterator INSTANCE = new EmptyIterator();

        public boolean hasNext() {
            return false;
        }

        private EmptyIterator() {
        }

        public static <T> EmptyIterator<T> getInstance() {
            return INSTANCE;
        }

        public T next() {
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new IllegalStateException();
        }
    }

    abstract class SingletonIteratorBase<T> implements Iterator<T> {
        private boolean myVisited;

        protected abstract void checkCoModification();

        protected abstract T getElement();

        private SingletonIteratorBase() {
        }

        public final boolean hasNext() {
            return !this.myVisited;
        }

        public final T next() {
            if (this.myVisited) {
                throw new NoSuchElementException();
            }
            this.myVisited = true;
            checkCoModification();
            return getElement();
        }
    }

    class SingletonIterator extends SingletonIteratorBase<E> {
        private final int myInitialModCount;

        public SingletonIterator() {
            super();
            this.myInitialModCount = SmartList.this.modCount;
        }

        protected E getElement() {
            return SmartList.this.myElem;
        }

        protected void checkCoModification() {
            if (SmartList.this.modCount != this.myInitialModCount) {
                StringBuilder stringBuilder = new StringBuilder("ModCount: ");
                stringBuilder.append(SmartList.this.modCount);
                stringBuilder.append("; expected: ");
                stringBuilder.append(this.myInitialModCount);
                throw new ConcurrentModificationException(stringBuilder.toString());
            }
        }

        public void remove() {
            checkCoModification();
            SmartList.this.clear();
        }
    }

    public E get(int i) {
        if (i < 0 || i >= this.mySize) {
            StringBuilder stringBuilder = new StringBuilder("Index: ");
            stringBuilder.append(i);
            stringBuilder.append(", Size: ");
            stringBuilder.append(this.mySize);
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        } else if (this.mySize == 1) {
            return this.myElem;
        } else {
            return ((Object[]) this.myElem)[i];
        }
    }

    public boolean add(E e) {
        if (this.mySize == 0) {
            this.myElem = e;
        } else if (this.mySize == 1) {
            this.myElem = new Object[]{this.myElem, e};
        } else {
            Object obj = (Object[]) this.myElem;
            int length = obj.length;
            if (this.mySize >= length) {
                int i = ((length * 3) / 2) + 1;
                int i2 = this.mySize + 1;
                if (i >= i2) {
                    i2 = i;
                }
                Object obj2 = new Object[i2];
                this.myElem = obj2;
                System.arraycopy(obj, 0, obj2, 0, length);
                obj = obj2;
            }
            obj[this.mySize] = e;
        }
        this.mySize++;
        this.modCount++;
        return true;
    }

    public void add(int i, E e) {
        if (i < 0 || i > this.mySize) {
            StringBuilder stringBuilder = new StringBuilder("Index: ");
            stringBuilder.append(i);
            stringBuilder.append(", Size: ");
            stringBuilder.append(this.mySize);
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        }
        if (this.mySize == 0) {
            this.myElem = e;
        } else if (this.mySize == 1 && i == 0) {
            this.myElem = new Object[]{e, this.myElem};
        } else {
            Object obj = new Object[(this.mySize + 1)];
            if (this.mySize == 1) {
                obj[0] = this.myElem;
            } else {
                Object[] objArr = (Object[]) this.myElem;
                System.arraycopy(objArr, 0, obj, 0, i);
                System.arraycopy(objArr, i, obj, i + 1, this.mySize - i);
            }
            obj[i] = e;
            this.myElem = obj;
        }
        this.mySize++;
        this.modCount++;
    }

    public int size() {
        return this.mySize;
    }

    public void clear() {
        this.myElem = null;
        this.mySize = 0;
        this.modCount++;
    }

    public E set(int i, E e) {
        if (i < 0 || i >= this.mySize) {
            StringBuilder stringBuilder = new StringBuilder("Index: ");
            stringBuilder.append(i);
            stringBuilder.append(", Size: ");
            stringBuilder.append(this.mySize);
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        } else if (this.mySize == 1) {
            E e2 = this.myElem;
            this.myElem = e;
            return e2;
        } else {
            Object[] objArr = (Object[]) this.myElem;
            E e3 = objArr[i];
            objArr[i] = e;
            return e3;
        }
    }

    public E remove(int i) {
        if (i < 0 || i >= this.mySize) {
            StringBuilder stringBuilder = new StringBuilder("Index: ");
            stringBuilder.append(i);
            stringBuilder.append(", Size: ");
            stringBuilder.append(this.mySize);
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        }
        E e;
        if (this.mySize == 1) {
            e = this.myElem;
            this.myElem = null;
        } else {
            Object[] objArr = (Object[]) this.myElem;
            E e2 = objArr[i];
            if (this.mySize == 2) {
                this.myElem = objArr[1 - i];
            } else {
                int i2 = (this.mySize - i) - 1;
                if (i2 > 0) {
                    System.arraycopy(objArr, i + 1, objArr, i, i2);
                }
                objArr[this.mySize - 1] = null;
            }
            e = e2;
        }
        this.mySize--;
        this.modCount++;
        return e;
    }

    public Iterator<E> iterator() {
        if (this.mySize == 0) {
            return EmptyIterator.getInstance();
        }
        if (this.mySize == 1) {
            return new SingletonIterator();
        }
        return super.iterator();
    }

    public <T> T[] toArray(T[] tArr) {
        int length = tArr.length;
        if (this.mySize == 1) {
            if (length != 0) {
                tArr[0] = this.myElem;
            } else {
                Object[] objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), 1);
                objArr[0] = this.myElem;
                return objArr;
            }
        } else if (length < this.mySize) {
            return Arrays.copyOf((Object[]) this.myElem, this.mySize, tArr.getClass());
        } else {
            if (this.mySize != 0) {
                System.arraycopy(this.myElem, 0, tArr, 0, this.mySize);
            }
        }
        if (length > this.mySize) {
            tArr[this.mySize] = null;
        }
        return tArr;
    }
}
