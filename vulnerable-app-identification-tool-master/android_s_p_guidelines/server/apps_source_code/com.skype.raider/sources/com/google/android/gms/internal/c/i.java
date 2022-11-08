package com.google.android.gms.internal.c;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;

public final class i extends a implements g {
    i(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    public final Tile a(int i, int i2, int i3) throws RemoteException {
        Parcel u_ = u_();
        u_.writeInt(i);
        u_.writeInt(i2);
        u_.writeInt(i3);
        Parcel a = a(1, u_);
        Tile tile = (Tile) k.a(a, Tile.CREATOR);
        a.recycle();
        return tile;
    }
}
