package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.d.b;
import com.google.android.gms.internal.d.c;

public interface w extends IInterface {

    public static abstract class a extends b implements w {

        public static class a extends com.google.android.gms.internal.d.a implements w {
            a(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.IResolveAccountCallbacks");
            }

            public final void a(ResolveAccountResponse resolveAccountResponse) throws RemoteException {
                Parcel d = d();
                c.a(d, (Parcelable) resolveAccountResponse);
                b(2, d);
            }
        }

        public static w a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
            return queryLocalInterface instanceof w ? (w) queryLocalInterface : new a(iBinder);
        }

        protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
            if (i != 2) {
                return false;
            }
            a((ResolveAccountResponse) c.a(parcel, ResolveAccountResponse.CREATOR));
            parcel2.writeNoException();
            return true;
        }
    }

    void a(ResolveAccountResponse resolveAccountResponse) throws RemoteException;
}
