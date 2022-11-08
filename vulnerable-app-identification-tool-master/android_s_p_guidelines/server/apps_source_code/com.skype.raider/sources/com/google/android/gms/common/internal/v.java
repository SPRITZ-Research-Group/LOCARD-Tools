package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.GoogleCertificatesQuery;
import com.google.android.gms.internal.d.b;
import com.google.android.gms.internal.d.c;

public interface v extends IInterface {

    public static abstract class a extends b implements v {

        public static class a extends com.google.android.gms.internal.d.a implements v {
            a(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
            }

            public final com.google.android.gms.b.b a() throws RemoteException {
                Parcel a = a(1, d());
                com.google.android.gms.b.b a2 = com.google.android.gms.b.b.a.a(a.readStrongBinder());
                a.recycle();
                return a2;
            }

            public final boolean a(GoogleCertificatesQuery googleCertificatesQuery, com.google.android.gms.b.b bVar) throws RemoteException {
                Parcel d = d();
                c.a(d, (Parcelable) googleCertificatesQuery);
                c.a(d, (IInterface) bVar);
                d = a(5, d);
                boolean a = c.a(d);
                d.recycle();
                return a;
            }

            public final boolean a(String str, com.google.android.gms.b.b bVar) throws RemoteException {
                Parcel d = d();
                d.writeString(str);
                c.a(d, (IInterface) bVar);
                d = a(3, d);
                boolean a = c.a(d);
                d.recycle();
                return a;
            }

            public final com.google.android.gms.b.b b() throws RemoteException {
                Parcel a = a(2, d());
                com.google.android.gms.b.b a2 = com.google.android.gms.b.b.a.a(a.readStrongBinder());
                a.recycle();
                return a2;
            }

            public final boolean b(String str, com.google.android.gms.b.b bVar) throws RemoteException {
                Parcel d = d();
                d.writeString(str);
                c.a(d, (IInterface) bVar);
                d = a(4, d);
                boolean a = c.a(d);
                d.recycle();
                return a;
            }
        }

        public static v a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
            return queryLocalInterface instanceof v ? (v) queryLocalInterface : new a(iBinder);
        }

        protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
            IInterface a;
            boolean a2;
            switch (i) {
                case 1:
                    a = a();
                    parcel2.writeNoException();
                    c.a(parcel2, a);
                    break;
                case 2:
                    a = b();
                    parcel2.writeNoException();
                    c.a(parcel2, a);
                    break;
                case 3:
                    a2 = a(parcel.readString(), com.google.android.gms.b.b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    c.a(parcel2, a2);
                    break;
                case 4:
                    a2 = b(parcel.readString(), com.google.android.gms.b.b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    c.a(parcel2, a2);
                    break;
                case 5:
                    a2 = a((GoogleCertificatesQuery) c.a(parcel, GoogleCertificatesQuery.CREATOR), com.google.android.gms.b.b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    c.a(parcel2, a2);
                    break;
                default:
                    return false;
            }
            return true;
        }
    }

    com.google.android.gms.b.b a() throws RemoteException;

    boolean a(GoogleCertificatesQuery googleCertificatesQuery, com.google.android.gms.b.b bVar) throws RemoteException;

    boolean a(String str, com.google.android.gms.b.b bVar) throws RemoteException;

    com.google.android.gms.b.b b() throws RemoteException;

    boolean b(String str, com.google.android.gms.b.b bVar) throws RemoteException;
}
