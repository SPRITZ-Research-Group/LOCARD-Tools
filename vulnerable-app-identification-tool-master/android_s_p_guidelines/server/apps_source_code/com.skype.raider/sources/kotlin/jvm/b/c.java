package kotlin.jvm.b;

import java.util.Arrays;
import java.util.List;
import kotlin.b;

public class c {
    private c() {
    }

    public static void a() {
        throw ((b) a(new b()));
    }

    public static void a(Object value, String expression) {
        if (value == null) {
            throw ((IllegalStateException) a(new IllegalStateException(expression + " must not be null")));
        }
    }

    public static void b(Object value, String paramName) {
        if (value == null) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String className = stackTraceElement.getClassName();
            throw ((IllegalArgumentException) a(new IllegalArgumentException("Parameter specified as non-null is null: method " + className + "." + stackTraceElement.getMethodName() + ", parameter " + paramName)));
        }
    }

    public static int a(int thisVal) {
        if (thisVal <= 0) {
            return -1;
        }
        return thisVal == 1 ? 0 : 1;
    }

    public static boolean a(Object first, Object second) {
        if (first == null) {
            return second == null;
        } else {
            return first.equals(second);
        }
    }

    private static <T extends Throwable> T a(T throwable) {
        String name = c.class.getName();
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (name.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        List subList = Arrays.asList(stackTrace).subList(i + 1, length);
        throwable.setStackTrace((StackTraceElement[]) subList.toArray(new StackTraceElement[subList.size()]));
        return throwable;
    }
}
