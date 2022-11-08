package kotlin.reflect.jvm.internal.impl.name;

import defpackage.adcn;

public final class NameUtils {
    public static final NameUtils INSTANCE = new NameUtils();
    private static final adcn SANITIZE_AS_JAVA_INVALID_CHARACTERS = new adcn("[^\\p{L}\\p{Digit}]");

    private NameUtils() {
    }

    public static final String sanitizeAsJavaIdentifier(String str) {
        return SANITIZE_AS_JAVA_INVALID_CHARACTERS.a((CharSequence) str, "_");
    }
}
