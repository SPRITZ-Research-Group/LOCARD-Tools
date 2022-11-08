package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.d.b;
import com.google.android.gms.internal.d.c;

public interface t extends IInterface {

    public static abstract class a extends b implements t {

        public static class a extends com.google.android.gms.internal.d.a implements t {
            a(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.IGmsCallbacks");
            }

            public final void a(int i, Bundle bundle) throws RemoteException {
                Parcel d = d();
                d.writeInt(i);
                c.a(d, (Parcelable) bundle);
                b(2, d);
            }

            public final void a(int i, IBinder iBinder, Bundle bundle) throws RemoteException {
                Parcel d = d();
                d.writeInt(i);
                d.writeStrongBinder(iBinder);
                c.a(d, (Parcelable) bundle);
                b(1, d);
            }

            public final void a(int i, IBinder iBinder, ConnectionInfo connectionInfo) throws RemoteException {
                Parcel d = d();
                d.writeInt(i);
                d.writeStrongBinder(iBinder);
                c.a(d, (Parcelable) connectionInfo);
                b(3, d);
            }
        }

        public a() {
            super("com.google.android.gms.common.internal.IGmsCallbacks");
        }

        public static t a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
            return queryLocalInterface instanceof t ? (t) queryLocalInterface : new a(iBinder);
        }

        protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
            switch (i) {
                case 1:
                    a(parcel.readInt(), parcel.readStrongBinder(), (Bundle) c.a(parcel, Bundle.CREATOR));
                    break;
                case 2:
                    a(parcel.readInt(), (Bundle) c.a(parcel, Bundle.CREATOR));
                    break;
                case 3:
                    a(parcel.readInt(), parcel.readStrongBinder(), (ConnectionInfo) c.a(parcel, ConnectionInfo.CREATOR));
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void a(int i, Bundle bundle) throws RemoteException;

    void a(int i, IBinder iBinder, Bundle bundle) throws RemoteException;

    void a(int i, IBinder iBinder, ConnectionInfo connectionInfo) throws RemoteException;
}
