package com.google.android.gms.internal.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.b.d;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.a;
import com.google.android.gms.vision.barcode.Barcode;
import java.nio.ByteBuffer;

public final class f extends k<g> {
    private final zzc a;

    public f(Context context, zzc zzc) {
        super(context, "BarcodeNativeHandle");
        this.a = zzc;
        b();
    }

    protected final /* synthetic */ Object a(DynamiteModule dynamiteModule, Context context) throws RemoteException, a {
        i iVar;
        IBinder a = dynamiteModule.a("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator");
        if (a == null) {
            iVar = null;
        } else {
            IInterface queryLocalInterface = a.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
            iVar = queryLocalInterface instanceof i ? (i) queryLocalInterface : new j(a);
        }
        return iVar == null ? null : iVar.a(d.a((Object) context), this.a);
    }

    public final Barcode[] a(Bitmap bitmap, zzk zzk) {
        if (!a()) {
            return new Barcode[0];
        }
        try {
            return ((g) b()).b(d.a((Object) bitmap), zzk);
        } catch (RemoteException e) {
            return new Barcode[0];
        }
    }

    public final Barcode[] a(ByteBuffer byteBuffer, zzk zzk) {
        if (!a()) {
            return new Barcode[0];
        }
        try {
            return ((g) b()).a(d.a((Object) byteBuffer), zzk);
        } catch (RemoteException e) {
            return new Barcode[0];
        }
    }
}
