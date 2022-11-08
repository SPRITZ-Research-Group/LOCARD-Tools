package kotlin.reflect.jvm.internal.impl.utils;

public class WrappedValues {
    private static final Object NULL_VALUE = new Object() {
        public final String toString() {
            return "NULL_VALUE";
        }
    };
    public static volatile boolean throwWrappedProcessCanceledException = false;

    final class ThrowableWrapper {
        private final Throwable throwable;

        /* synthetic */ ThrowableWrapper(Throwable th, AnonymousClass1 anonymousClass1) {
            this(th);
        }

        private ThrowableWrapper(Throwable th) {
            this.throwable = th;
        }

        public final Throwable getThrowable() {
            return this.throwable;
        }

        public final String toString() {
            return this.throwable.toString();
        }
    }

    public class WrappedProcessCanceledException extends RuntimeException {
        public WrappedProcessCanceledException(Throwable th) {
            super("Rethrow stored exception", th);
        }
    }

    public static <V> V unescapeNull(Object obj) {
        return obj == NULL_VALUE ? null : obj;
    }

    public static <V> Object escapeNull(V v) {
        return v == null ? NULL_VALUE : v;
    }

    public static Object escapeThrowable(Throwable th) {
        return new ThrowableWrapper(th, null);
    }

    public static <V> V unescapeExceptionOrNull(Object obj) {
        return unescapeNull(unescapeThrowable(obj));
    }

    public static <V> V unescapeThrowable(Object obj) {
        if (!(obj instanceof ThrowableWrapper)) {
            return obj;
        }
        Throwable throwable = ((ThrowableWrapper) obj).getThrowable();
        if (throwWrappedProcessCanceledException && ExceptionUtilsKt.isProcessCanceledException(throwable)) {
            throw new WrappedProcessCanceledException(throwable);
        }
        throw ExceptionUtilsKt.rethrow(throwable);
    }
}
