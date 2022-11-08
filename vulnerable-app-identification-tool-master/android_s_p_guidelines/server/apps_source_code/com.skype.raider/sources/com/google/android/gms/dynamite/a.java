package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.d.b;
import com.google.android.gms.internal.d.c;

public interface a extends IInterface {

    public static abstract class a extends b implements a {

        public static class a extends com.google.android.gms.internal.d.a implements a {
            a(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
            }

            public final int a(com.google.android.gms.b.b bVar, String str) throws RemoteException {
                Parcel d = d();
                c.a(d, (IInterface) bVar);
                d.writeString(str);
                d = a(1, d);
                int readInt = d.readInt();
                d.recycle();
                return readInt;
            }

            public final int a(com.google.android.gms.b.b bVar, String str, boolean z) throws RemoteException {
                Parcel d = d();
                c.a(d, (IInterface) bVar);
                d.writeString(str);
                c.a(d, z);
                d = a(3, d);
                int readInt = d.readInt();
                d.recycle();
                return readInt;
            }

            public final com.google.android.gms.b.b a(com.google.android.gms.b.b bVar, String str, int i) throws RemoteException {
                Parcel d = d();
                c.a(d, (IInterface) bVar);
                d.writeString(str);
                d.writeInt(i);
                d = a(2, d);
                com.google.android.gms.b.b a = com.google.android.gms.b.b.a.a(d.readStrongBinder());
                d.recycle();
                return a;
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
            return queryLocalInterface instanceof a ? (a) queryLocalInterface : new a(iBinder);
        }

        protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
            int a;
            switch (i) {
                case 1:
                    a = a(com.google.android.gms.b.b.a.a(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    break;
                case 2:
                    IInterface a2 = a(com.google.android.gms.b.b.a.a(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    c.a(parcel2, a2);
                    break;
                case 3:
                    a = a(com.google.android.gms.b.b.a.a(parcel.readStrongBinder()), parcel.readString(), c.a(parcel));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    break;
                default:
                    return false;
            }
            return true;
        }
    }

    int a(com.google.android.gms.b.b bVar, String str) throws RemoteException;

    int a(com.google.android.gms.b.b bVar, String str, boolean z) throws RemoteException;

    com.google.android.gms.b.b a(com.google.android.gms.b.b bVar, String str, int i) throws RemoteException;
}
