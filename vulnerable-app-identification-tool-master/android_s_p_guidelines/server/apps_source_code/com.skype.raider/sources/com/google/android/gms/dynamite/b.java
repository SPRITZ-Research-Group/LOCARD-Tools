package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.d.c;

public interface b extends IInterface {

    public static abstract class a extends com.google.android.gms.internal.d.b implements b {

        public static class a extends com.google.android.gms.internal.d.a implements b {
            a(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
            }

            public final com.google.android.gms.b.b a(com.google.android.gms.b.b bVar, String str, int i, com.google.android.gms.b.b bVar2) throws RemoteException {
                Parcel d = d();
                c.a(d, (IInterface) bVar);
                d.writeString(str);
                d.writeInt(i);
                c.a(d, (IInterface) bVar2);
                d = a(2, d);
                com.google.android.gms.b.b a = com.google.android.gms.b.b.a.a(d.readStrongBinder());
                d.recycle();
                return a;
            }

            public final com.google.android.gms.b.b a(com.google.android.gms.b.b bVar, String str, byte[] bArr) throws RemoteException {
                Parcel d = d();
                c.a(d, (IInterface) bVar);
                d.writeString(str);
                d.writeByteArray(bArr);
                d = a(1, d);
                com.google.android.gms.b.b a = com.google.android.gms.b.b.a.a(d.readStrongBinder());
                d.recycle();
                return a;
            }
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
            return queryLocalInterface instanceof b ? (b) queryLocalInterface : new a(iBinder);
        }

        protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
            IInterface a;
            switch (i) {
                case 1:
                    a = a(com.google.android.gms.b.b.a.a(parcel.readStrongBinder()), parcel.readString(), parcel.createByteArray());
                    parcel2.writeNoException();
                    c.a(parcel2, a);
                    break;
                case 2:
                    a = a(com.google.android.gms.b.b.a.a(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), com.google.android.gms.b.b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    c.a(parcel2, a);
                    break;
                default:
                    return false;
            }
            return true;
        }
    }

    com.google.android.gms.b.b a(com.google.android.gms.b.b bVar, String str, int i, com.google.android.gms.b.b bVar2) throws RemoteException;

    com.google.android.gms.b.b a(com.google.android.gms.b.b bVar, String str, byte[] bArr) throws RemoteException;
}
