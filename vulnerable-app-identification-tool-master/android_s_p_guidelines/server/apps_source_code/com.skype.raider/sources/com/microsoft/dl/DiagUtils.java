package com.microsoft.dl;

public final class DiagUtils {
    private static final String a = DiagUtils.class.getPackage().getName();

    private DiagUtils() {
    }

    public static String getObjName(Object obj) {
        return "[0x" + Integer.toHexString(System.identityHashCode(obj)) + "] " + getClassName(obj.getClass());
    }

    public static String getClassName(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        String className = clazz.getCanonicalName();
        return className.startsWith(a) ? className.substring(a.length() + 1) : className;
    }
}
