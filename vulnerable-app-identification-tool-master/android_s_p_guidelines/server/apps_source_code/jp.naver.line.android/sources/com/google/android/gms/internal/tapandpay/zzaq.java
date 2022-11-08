package com.google.android.gms.internal.tapandpay;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class zzaq {
    private static String zza(@NullableDecl Object obj) {
        try {
            return String.valueOf(obj);
        } catch (Throwable e) {
            String name = obj.getClass().getName();
            String toHexString = Integer.toHexString(System.identityHashCode(obj));
            StringBuilder stringBuilder = new StringBuilder((String.valueOf(name).length() + 1) + String.valueOf(toHexString).length());
            stringBuilder.append(name);
            stringBuilder.append('@');
            stringBuilder.append(toHexString);
            toHexString = stringBuilder.toString();
            Logger logger = Logger.getLogger("com.google.common.base.Strings");
            Level level = Level.WARNING;
            String str = "com.google.common.base.Strings";
            String str2 = "lenientToString";
            String str3 = "Exception during lenientFormat for ";
            String valueOf = String.valueOf(toHexString);
            logger.logp(level, str, str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), e);
            String name2 = e.getClass().getName();
            StringBuilder stringBuilder2 = new StringBuilder((String.valueOf(toHexString).length() + 9) + String.valueOf(name2).length());
            stringBuilder2.append("<");
            stringBuilder2.append(toHexString);
            stringBuilder2.append(" threw ");
            stringBuilder2.append(name2);
            stringBuilder2.append(">");
            return stringBuilder2.toString();
        }
    }

    public static String zza(@NullableDecl String str, @NullableDecl Object... objArr) {
        CharSequence valueOf = String.valueOf(str);
        int i = 0;
        for (int i2 = 0; i2 < objArr.length; i2++) {
            objArr[i2] = zza(objArr[i2]);
        }
        StringBuilder stringBuilder = new StringBuilder(valueOf.length() + (objArr.length * 16));
        int i3 = 0;
        while (i < objArr.length) {
            int indexOf = valueOf.indexOf("%s", i3);
            if (indexOf == -1) {
                break;
            }
            stringBuilder.append(valueOf, i3, indexOf);
            i3 = i + 1;
            stringBuilder.append(objArr[i]);
            int i4 = i3;
            i3 = indexOf + 2;
            i = i4;
        }
        stringBuilder.append(valueOf, i3, valueOf.length());
        if (i < objArr.length) {
            stringBuilder.append(" [");
            int i5 = i + 1;
            stringBuilder.append(objArr[i]);
            while (i5 < objArr.length) {
                stringBuilder.append(", ");
                i = i5 + 1;
                stringBuilder.append(objArr[i5]);
                i5 = i;
            }
            stringBuilder.append(']');
        }
        return stringBuilder.toString();
    }
}
