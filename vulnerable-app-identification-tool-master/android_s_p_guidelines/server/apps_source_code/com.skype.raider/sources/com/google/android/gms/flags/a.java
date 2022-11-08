package com.google.android.gms.flags;

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
                super(iBinder, "com.google.android.gms.flags.IFlagProvider");
            }

            public final boolean getBooleanFlagValue(String str, boolean z, int i) throws RemoteException {
                Parcel d = d();
                d.writeString(str);
                c.a(d, z);
                d.writeInt(i);
                d = a(2, d);
                boolean a = c.a(d);
                d.recycle();
                return a;
            }

            public final int getIntFlagValue(String str, int i, int i2) throws RemoteException {
                Parcel d = d();
                d.writeString(str);
                d.writeInt(i);
                d.writeInt(i2);
                d = a(3, d);
                int readInt = d.readInt();
                d.recycle();
                return readInt;
            }

            public final long getLongFlagValue(String str, long j, int i) throws RemoteException {
                Parcel d = d();
                d.writeString(str);
                d.writeLong(j);
                d.writeInt(i);
                d = a(4, d);
                long readLong = d.readLong();
                d.recycle();
                return readLong;
            }

            public final String getStringFlagValue(String str, String str2, int i) throws RemoteException {
                Parcel d = d();
                d.writeString(str);
                d.writeString(str2);
                d.writeInt(i);
                d = a(5, d);
                String readString = d.readString();
                d.recycle();
                return readString;
            }

            public final void init(com.google.android.gms.b.b bVar) throws RemoteException {
                Parcel d = d();
                c.a(d, (IInterface) bVar);
                b(1, d);
            }
        }

        public a() {
            super("com.google.android.gms.flags.IFlagProvider");
        }

        public static a asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.flags.IFlagProvider");
            return queryLocalInterface instanceof a ? (a) queryLocalInterface : new a(iBinder);
        }

        protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
            switch (i) {
                case 1:
                    init(com.google.android.gms.b.b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                case 2:
                    boolean booleanFlagValue = getBooleanFlagValue(parcel.readString(), c.a(parcel), parcel.readInt());
                    parcel2.writeNoException();
                    c.a(parcel2, booleanFlagValue);
                    break;
                case 3:
                    int intFlagValue = getIntFlagValue(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(intFlagValue);
                    break;
                case 4:
                    long longFlagValue = getLongFlagValue(parcel.readString(), parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(longFlagValue);
                    break;
                case 5:
                    String stringFlagValue = getStringFlagValue(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(stringFlagValue);
                    break;
                default:
                    return false;
            }
            return true;
        }
    }

    boolean getBooleanFlagValue(String str, boolean z, int i) throws RemoteException;

    int getIntFlagValue(String str, int i, int i2) throws RemoteException;

    long getLongFlagValue(String str, long j, int i) throws RemoteException;

    String getStringFlagValue(String str, String str2, int i) throws RemoteException;

    void init(com.google.android.gms.b.b bVar) throws RemoteException;
}
