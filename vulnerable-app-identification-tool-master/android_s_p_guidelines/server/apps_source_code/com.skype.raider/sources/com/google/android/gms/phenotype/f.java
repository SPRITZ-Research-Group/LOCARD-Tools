package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class f implements Creator<ExperimentTokens> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        byte[][] bArr = null;
        int a = a.a(parcel);
        int[] iArr = null;
        byte[][] bArr2 = null;
        byte[][] bArr3 = null;
        byte[][] bArr4 = null;
        byte[][] bArr5 = null;
        byte[] bArr6 = null;
        String str = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = a.p(parcel, readInt);
                    break;
                case 3:
                    bArr6 = a.s(parcel, readInt);
                    break;
                case 4:
                    bArr5 = a.t(parcel, readInt);
                    break;
                case 5:
                    bArr4 = a.t(parcel, readInt);
                    break;
                case 6:
                    bArr3 = a.t(parcel, readInt);
                    break;
                case 7:
                    bArr2 = a.t(parcel, readInt);
                    break;
                case 8:
                    iArr = a.u(parcel, readInt);
                    break;
                case 9:
                    bArr = a.t(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new ExperimentTokens(str, bArr6, bArr5, bArr4, bArr3, bArr2, iArr, bArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ExperimentTokens[i];
    }
}
