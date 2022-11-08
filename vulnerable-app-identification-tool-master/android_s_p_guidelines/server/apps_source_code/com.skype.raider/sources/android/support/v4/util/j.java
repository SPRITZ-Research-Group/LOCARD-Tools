package android.support.v4.util;

public final class j {

    public interface a<T> {
        T a();

        boolean a(T t);
    }

    public static class b<T> implements a<T> {
        private final Object[] a;
        private int b;

        public b(int maxPoolSize) {
            if (maxPoolSize <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.a = new Object[maxPoolSize];
        }

        public T a() {
            if (this.b <= 0) {
                return null;
            }
            int lastPooledIndex = this.b - 1;
            T instance = this.a[lastPooledIndex];
            this.a[lastPooledIndex] = null;
            this.b--;
            return instance;
        }

        public boolean a(T instance) {
            boolean z;
            for (int i = 0; i < this.b; i++) {
                if (this.a[i] == instance) {
                    z = true;
                    break;
                }
            }
            z = false;
            if (z) {
                throw new IllegalStateException("Already in the pool!");
            } else if (this.b >= this.a.length) {
                return false;
            } else {
                this.a[this.b] = instance;
                this.b++;
                return true;
            }
        }
    }

    public static class c<T> extends b<T> {
        private final Object a = new Object();

        public c(int maxPoolSize) {
            super(maxPoolSize);
        }

        public final T a() {
            T a;
            synchronized (this.a) {
                a = super.a();
            }
            return a;
        }

        public final boolean a(T element) {
            boolean a;
            synchronized (this.a) {
                a = super.a(element);
            }
            return a;
        }
    }
}
