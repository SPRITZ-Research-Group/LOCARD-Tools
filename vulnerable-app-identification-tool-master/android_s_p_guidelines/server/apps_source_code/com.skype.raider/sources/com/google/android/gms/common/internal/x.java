package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.d.b;
import com.google.android.gms.internal.d.c;

public interface x extends IInterface {

    public static abstract class a extends b implements x {

        public static class a extends com.google.android.gms.internal.d.a implements x {
            a(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
            }

            public final com.google.android.gms.b.b a(com.google.android.gms.b.b bVar, int i, int i2) throws RemoteException {
                Parcel d = d();
                c.a(d, (IInterface) bVar);
                d.writeInt(i);
                d.writeInt(i2);
                d = a(1, d);
                com.google.android.gms.b.b a = com.google.android.gms.b.b.a.a(d.readStrongBinder());
                d.recycle();
                return a;
            }

            public final com.google.android.gms.b.b a(com.google.android.gms.b.b bVar, SignInButtonConfig signInButtonConfig) throws RemoteException {
                Parcel d = d();
                c.a(d, (IInterface) bVar);
                c.a(d, (Parcelable) signInButtonConfig);
                d = a(2, d);
                com.google.android.gms.b.b a = com.google.android.gms.b.b.a.a(d.readStrongBinder());
                d.recycle();
                return a;
            }
        }

        public static x a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
            return queryLocalInterface instanceof x ? (x) queryLocalInterface : new a(iBinder);
        }

        protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
            IInterface a;
            switch (i) {
                case 1:
                    a = a(com.google.android.gms.b.b.a.a(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    c.a(parcel2, a);
                    break;
                case 2:
                    a = a(com.google.android.gms.b.b.a.a(parcel.readStrongBinder()), (SignInButtonConfig) c.a(parcel, SignInButtonConfig.CREATOR));
                    parcel2.writeNoException();
                    c.a(parcel2, a);
                    break;
                default:
                    return false;
            }
            return true;
        }
    }

    com.google.android.gms.b.b a(com.google.android.gms.b.b bVar, int i, int i2) throws RemoteException;

    com.google.android.gms.b.b a(com.google.android.gms.b.b bVar, SignInButtonConfig signInButtonConfig) throws RemoteException;
}
