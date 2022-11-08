package kotlin.reflect.jvm.internal.impl.utils;

import defpackage.acry;

public final class ExceptionUtilsKt {
    public static final RuntimeException rethrow(Throwable th) {
        throw th;
    }

    public static final boolean isProcessCanceledException(Throwable th) {
        Class cls = th.getClass();
        while (!acry.a(cls.getCanonicalName(), (Object) "com.intellij.openapi.progress.ProcessCanceledException")) {
            cls = cls.getSuperclass();
            if (cls == null) {
                return false;
            }
        }
        return true;
    }
}
