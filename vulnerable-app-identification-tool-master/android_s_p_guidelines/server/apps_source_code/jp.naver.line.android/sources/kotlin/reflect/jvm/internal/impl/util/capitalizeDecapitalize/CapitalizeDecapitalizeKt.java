package kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize;

import defpackage.addc;
import kotlin.v;

public final class CapitalizeDecapitalizeKt {
    public static final String decapitalizeSmart(String str, boolean z) {
        CapitalizeDecapitalizeKt$decapitalizeSmart$1 capitalizeDecapitalizeKt$decapitalizeSmart$1 = new CapitalizeDecapitalizeKt$decapitalizeSmart$1(str, z);
        CharSequence charSequence = str;
        if ((charSequence.length() == 0 ? 1 : null) != null || !capitalizeDecapitalizeKt$decapitalizeSmart$1.invoke(0)) {
            return str;
        }
        if (str.length() != 1 && capitalizeDecapitalizeKt$decapitalizeSmart$1.invoke(1)) {
            CapitalizeDecapitalizeKt$decapitalizeSmart$2 capitalizeDecapitalizeKt$decapitalizeSmart$2 = new CapitalizeDecapitalizeKt$decapitalizeSmart$2(z);
            for (Object next : addc.c(charSequence)) {
                if ((capitalizeDecapitalizeKt$decapitalizeSmart$1.invoke(((Number) next).intValue()) ^ 1) != 0) {
                    break;
                }
            }
            Object next2 = null;
            Integer num = (Integer) next2;
            if (num == null) {
                return capitalizeDecapitalizeKt$decapitalizeSmart$2.invoke(str);
            }
            int intValue = num.intValue() - 1;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(capitalizeDecapitalizeKt$decapitalizeSmart$2.invoke(str.substring(0, intValue)));
            stringBuilder.append(str.substring(intValue));
            return stringBuilder.toString();
        } else if (z) {
            return decapitalizeAsciiOnly(str);
        } else {
            if ((charSequence.length() > 0 ? 1 : null) == null || !Character.isUpperCase(str.charAt(0))) {
                return str;
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            String substring = str.substring(0, 1);
            if (substring != null) {
                stringBuilder2.append(substring.toLowerCase());
                stringBuilder2.append(str.substring(1));
                return stringBuilder2.toString();
            }
            throw new v("null cannot be cast to non-null type java.lang.String");
        }
    }

    public static final String capitalizeAsciiOnly(String str) {
        if ((((CharSequence) str).length() == 0 ? 1 : null) != null) {
            return str;
        }
        char charAt = str.charAt(0);
        if ('a' > charAt || 'z' < charAt) {
            return str;
        }
        charAt = Character.toUpperCase(charAt);
        str = str.substring(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(charAt));
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public static final String decapitalizeAsciiOnly(String str) {
        if ((((CharSequence) str).length() == 0 ? 1 : null) != null) {
            return str;
        }
        char charAt = str.charAt(0);
        if ('A' > charAt || 'Z' < charAt) {
            return str;
        }
        charAt = Character.toLowerCase(charAt);
        str = str.substring(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(charAt));
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public static final String toLowerCaseAsciiOnly(String str) {
        StringBuilder stringBuilder = new StringBuilder(str.length());
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ('A' <= charAt && 'Z' >= charAt) {
                charAt = Character.toLowerCase(charAt);
            }
            stringBuilder.append(charAt);
        }
        return stringBuilder.toString();
    }
}
