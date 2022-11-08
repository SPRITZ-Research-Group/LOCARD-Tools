package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.d.b;
import com.google.android.gms.internal.d.c;

public interface q extends IInterface {

    public static abstract class a extends b implements q {

        public static class a extends com.google.android.gms.internal.d.a implements q {
            a(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.IAccountAccessor");
            }

            public final Account a() throws RemoteException {
                Parcel a = a(2, d());
                Account account = (Account) c.a(a, Account.CREATOR);
                a.recycle();
                return account;
            }
        }

        public static q a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
            return queryLocalInterface instanceof q ? (q) queryLocalInterface : new a(iBinder);
        }

        protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
            if (i != 2) {
                return false;
            }
            Parcelable a = a();
            parcel2.writeNoException();
            c.b(parcel2, a);
            return true;
        }
    }

    Account a() throws RemoteException;
}
