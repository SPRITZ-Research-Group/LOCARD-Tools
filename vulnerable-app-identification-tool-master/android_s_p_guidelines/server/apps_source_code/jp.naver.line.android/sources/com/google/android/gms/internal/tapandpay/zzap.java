package com.google.android.gms.internal.tapandpay;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class zzap {
    public static int checkElementIndex(int i, int i2) {
        String str = "index";
        if (i >= 0 && i < i2) {
            return i;
        }
        String zza;
        if (i < 0) {
            zza = zzaq.zza("%s (%s) must not be negative", str, Integer.valueOf(i));
        } else if (i2 < 0) {
            StringBuilder stringBuilder = new StringBuilder(26);
            stringBuilder.append("negative size: ");
            stringBuilder.append(i2);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else {
            zza = zzaq.zza("%s (%s) must be less than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        }
        throw new IndexOutOfBoundsException(zza);
    }

    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static int checkPositionIndex(int i, int i2) {
        if (i >= 0 && i <= i2) {
            return i;
        }
        throw new IndexOutOfBoundsException(zza(i, i2, "index"));
    }

    public static void checkPositionIndexes(int i, int i2, int i3) {
        if (i < 0 || i2 < i || i2 > i3) {
            String zza;
            if (i < 0 || i > i3) {
                zza = zza(i, i3, "start index");
            } else if (i2 < 0 || i2 > i3) {
                zza = zza(i2, i3, "end index");
            } else {
                zza = zzaq.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
            }
            throw new IndexOutOfBoundsException(zza);
        }
    }

    private static String zza(int i, int i2, @NullableDecl String str) {
        if (i < 0) {
            return zzaq.zza("%s (%s) must not be negative", str, Integer.valueOf(i));
        } else if (i2 >= 0) {
            return zzaq.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        } else {
            StringBuilder stringBuilder = new StringBuilder(26);
            stringBuilder.append("negative size: ");
            stringBuilder.append(i2);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
