package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public final class b {
    public static int a(Parcel parcel) {
        return b(parcel, 20293);
    }

    public static void a(Parcel parcel, int i) {
        c(parcel, i);
    }

    public static void a(Parcel parcel, int i, byte b) {
        b(parcel, i, 4);
        parcel.writeInt(b);
    }

    public static void a(Parcel parcel, int i, double d) {
        b(parcel, i, 8);
        parcel.writeDouble(d);
    }

    public static void a(Parcel parcel, int i, float f) {
        b(parcel, i, 4);
        parcel.writeFloat(f);
    }

    public static void a(Parcel parcel, int i, int i2) {
        b(parcel, i, 4);
        parcel.writeInt(i2);
    }

    public static void a(Parcel parcel, int i, long j) {
        b(parcel, i, 8);
        parcel.writeLong(j);
    }

    public static void a(Parcel parcel, int i, Bundle bundle) {
        if (bundle != null) {
            int b = b(parcel, i);
            parcel.writeBundle(bundle);
            c(parcel, b);
        }
    }

    public static void a(Parcel parcel, int i, IBinder iBinder) {
        if (iBinder != null) {
            int b = b(parcel, i);
            parcel.writeStrongBinder(iBinder);
            c(parcel, b);
        }
    }

    public static void a(Parcel parcel, int i, Parcelable parcelable, int i2) {
        if (parcelable != null) {
            int b = b(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            c(parcel, b);
        }
    }

    public static void a(Parcel parcel, int i, Float f) {
        if (f != null) {
            b(parcel, i, 4);
            parcel.writeFloat(f.floatValue());
        }
    }

    public static void a(Parcel parcel, int i, Integer num) {
        if (num != null) {
            b(parcel, i, 4);
            parcel.writeInt(num.intValue());
        }
    }

    public static void a(Parcel parcel, int i, String str) {
        if (str != null) {
            int b = b(parcel, i);
            parcel.writeString(str);
            c(parcel, b);
        }
    }

    public static void a(Parcel parcel, int i, List<String> list) {
        if (list != null) {
            int b = b(parcel, i);
            parcel.writeStringList(list);
            c(parcel, b);
        }
    }

    public static void a(Parcel parcel, int i, boolean z) {
        b(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    public static void a(Parcel parcel, int i, byte[] bArr) {
        if (bArr != null) {
            int b = b(parcel, i);
            parcel.writeByteArray(bArr);
            c(parcel, b);
        }
    }

    public static void a(Parcel parcel, int i, int[] iArr) {
        if (iArr != null) {
            int b = b(parcel, i);
            parcel.writeIntArray(iArr);
            c(parcel, b);
        }
    }

    public static <T extends Parcelable> void a(Parcel parcel, int i, T[] tArr, int i2) {
        if (tArr != null) {
            int b = b(parcel, i);
            parcel.writeInt(r3);
            for (Parcelable parcelable : tArr) {
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    a(parcel, parcelable, i2);
                }
            }
            c(parcel, b);
        }
    }

    public static void a(Parcel parcel, int i, String[] strArr) {
        if (strArr != null) {
            int b = b(parcel, i);
            parcel.writeStringArray(strArr);
            c(parcel, b);
        }
    }

    public static void a(Parcel parcel, int i, byte[][] bArr) {
        if (bArr != null) {
            int b = b(parcel, i);
            parcel.writeInt(r2);
            for (byte[] writeByteArray : bArr) {
                parcel.writeByteArray(writeByteArray);
            }
            c(parcel, b);
        }
    }

    public static void a(Parcel parcel, Parcel parcel2) {
        if (parcel2 != null) {
            int b = b(parcel, 2);
            parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            c(parcel, b);
        }
    }

    private static <T extends Parcelable> void a(Parcel parcel, T t, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        t.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    public static void a(Parcel parcel, Double d) {
        if (d != null) {
            b(parcel, 8, 8);
            parcel.writeDouble(d.doubleValue());
        }
    }

    public static void a(Parcel parcel, Long l) {
        if (l != null) {
            b(parcel, 4, 8);
            parcel.writeLong(l.longValue());
        }
    }

    public static void a(Parcel parcel, List list) {
        if (list != null) {
            int b = b(parcel, 3);
            parcel.writeList(list);
            c(parcel, b);
        }
    }

    public static void a(Parcel parcel, short s) {
        b(parcel, 3, 4);
        parcel.writeInt(s);
    }

    private static int b(Parcel parcel, int i) {
        parcel.writeInt(-65536 | i);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void b(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(-65536 | i);
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt((i2 << 16) | i);
    }

    public static <T extends Parcelable> void b(Parcel parcel, int i, List<T> list) {
        if (list != null) {
            int b = b(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                Parcelable parcelable = (Parcelable) list.get(i2);
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    a(parcel, parcelable, 0);
                }
            }
            c(parcel, b);
        }
    }

    private static void c(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        int i2 = dataPosition - i;
        parcel.setDataPosition(i - 4);
        parcel.writeInt(i2);
        parcel.setDataPosition(dataPosition);
    }
}
