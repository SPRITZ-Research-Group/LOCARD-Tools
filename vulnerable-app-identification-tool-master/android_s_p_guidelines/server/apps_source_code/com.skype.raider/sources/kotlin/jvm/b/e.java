package kotlin.jvm.b;

import kotlin.SinceKotlin;
import kotlin.f.a;

public final class e {
    private static final f a;
    private static final a[] b = new a[0];

    static {
        f impl;
        try {
            impl = (f) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException e) {
            impl = null;
        } catch (ClassNotFoundException e2) {
            impl = null;
        } catch (InstantiationException e3) {
            impl = null;
        } catch (IllegalAccessException e4) {
            impl = null;
        }
        if (impl == null) {
            impl = new f();
        }
        a = impl;
    }

    @SinceKotlin(version = "1.1")
    public static String a(d lambda) {
        String obj = lambda.getClass().getGenericInterfaces()[0].toString();
        return obj.startsWith("kotlin.jvm.functions.") ? obj.substring(21) : obj;
    }
}
