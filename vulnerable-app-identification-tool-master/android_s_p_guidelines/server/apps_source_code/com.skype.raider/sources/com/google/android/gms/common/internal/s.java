package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.d.b;
import com.google.android.gms.internal.d.c;

public interface s extends IInterface {

    public static abstract class a extends b implements s {

        public static class a extends com.google.android.gms.internal.d.a implements s {
            a(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.ICertData");
            }

            public final com.google.android.gms.b.b b() throws RemoteException {
                Parcel a = a(1, d());
                com.google.android.gms.b.b a2 = com.google.android.gms.b.b.a.a(a.readStrongBinder());
                a.recycle();
                return a2;
            }

            public final int c() throws RemoteException {
                Parcel a = a(2, d());
                int readInt = a.readInt();
                a.recycle();
                return readInt;
            }
        }

        public a() {
            super("com.google.android.gms.common.internal.ICertData");
        }

        public static s a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
            return queryLocalInterface instanceof s ? (s) queryLocalInterface : new a(iBinder);
        }

        protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
            switch (i) {
                case 1:
                    IInterface b = b();
                    parcel2.writeNoException();
                    c.a(parcel2, b);
                    break;
                case 2:
                    int c = c();
                    parcel2.writeNoException();
                    parcel2.writeInt(c);
                    break;
                default:
                    return false;
            }
            return true;
        }
    }

    com.google.android.gms.b.b b() throws RemoteException;

    int c() throws RemoteException;
}
