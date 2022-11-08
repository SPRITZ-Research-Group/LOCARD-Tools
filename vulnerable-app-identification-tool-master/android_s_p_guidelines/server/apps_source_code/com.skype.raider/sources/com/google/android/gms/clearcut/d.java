package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.clearcut.zzr;
import com.google.android.gms.phenotype.ExperimentTokens;

public final class d implements Creator<zze> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        ExperimentTokens[] experimentTokensArr = null;
        int a = a.a(parcel);
        boolean z = true;
        byte[][] bArr = null;
        int[] iArr = null;
        String[] strArr = null;
        int[] iArr2 = null;
        byte[] bArr2 = null;
        zzr zzr = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    zzr = (zzr) a.a(parcel, readInt, zzr.CREATOR);
                    break;
                case 3:
                    bArr2 = a.s(parcel, readInt);
                    break;
                case 4:
                    iArr2 = a.u(parcel, readInt);
                    break;
                case 5:
                    strArr = a.x(parcel, readInt);
                    break;
                case 6:
                    iArr = a.u(parcel, readInt);
                    break;
                case 7:
                    bArr = a.t(parcel, readInt);
                    break;
                case 8:
                    z = a.c(parcel, readInt);
                    break;
                case 9:
                    experimentTokensArr = (ExperimentTokens[]) a.b(parcel, readInt, ExperimentTokens.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zze(zzr, bArr2, iArr2, strArr, iArr, bArr, z, experimentTokensArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zze[i];
    }
}
