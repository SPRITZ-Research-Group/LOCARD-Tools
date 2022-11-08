package defpackage;

import java.util.Arrays;
import java.util.List;
import kotlin.d;
import kotlin.x;

/* renamed from: acry */
public class acry {
    public static int a(int i, int i2) {
        return i < i2 ? -1 : i == i2 ? 0 : 1;
    }

    private acry() {
    }

    public static String a(String str, Object obj) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(obj);
        return stringBuilder.toString();
    }

    public static void a() {
        throw ((d) acry.a(new d()));
    }

    public static void a(String str) {
        StringBuilder stringBuilder = new StringBuilder("lateinit property ");
        stringBuilder.append(str);
        stringBuilder.append(" has not been initialized");
        throw ((x) acry.a(new x(stringBuilder.toString())));
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    private static <T extends Throwable> T a(T t) {
        return acry.a((Throwable) t, acry.class.getName());
    }

    static <T extends Throwable> T a(T t, String str) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        List subList = Arrays.asList(stackTrace).subList(i + 1, length);
        t.setStackTrace((StackTraceElement[]) subList.toArray(new StackTraceElement[subList.size()]));
        return t;
    }
}
