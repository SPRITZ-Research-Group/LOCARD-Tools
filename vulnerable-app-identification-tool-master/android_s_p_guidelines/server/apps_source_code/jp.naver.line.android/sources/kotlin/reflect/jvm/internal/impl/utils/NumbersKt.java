package kotlin.reflect.jvm.internal.impl.utils;

public final class NumbersKt {
    public static final NumberWithRadix extractRadix(String str) {
        if (str.startsWith("0x") || str.startsWith("0X")) {
            return new NumberWithRadix(str.substring(2), 16);
        }
        if (str.startsWith("0b") || str.startsWith("0B")) {
            return new NumberWithRadix(str.substring(2), 2);
        }
        return new NumberWithRadix(str, 10);
    }
}
