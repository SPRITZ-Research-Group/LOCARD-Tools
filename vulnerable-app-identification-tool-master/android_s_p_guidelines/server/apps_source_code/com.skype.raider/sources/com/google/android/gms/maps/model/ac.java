package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.c.g;

final class ac implements i {
    private final g a = this.c.a;
    private final /* synthetic */ TileOverlayOptions c;

    ac(TileOverlayOptions tileOverlayOptions) {
        this.c = tileOverlayOptions;
    }

    public final Tile b(int i, int i2, int i3) {
        try {
            return this.a.a(i, i2, i3);
        } catch (RemoteException e) {
            return null;
        }
    }
}
