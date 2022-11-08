package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.d.b;
import com.google.android.gms.internal.d.c;

public interface d extends IInterface {

    public static abstract class a extends b implements d {

        public static class a extends com.google.android.gms.internal.d.a implements d {
            a(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.signin.internal.ISignInCallbacks");
            }

            public final void a(ConnectionResult connectionResult, AuthAccountResult authAccountResult) throws RemoteException {
                Parcel d = d();
                c.a(d, (Parcelable) connectionResult);
                c.a(d, (Parcelable) authAccountResult);
                b(3, d);
            }

            public final void a(Status status) throws RemoteException {
                Parcel d = d();
                c.a(d, (Parcelable) status);
                b(4, d);
            }

            public final void a(Status status, GoogleSignInAccount googleSignInAccount) throws RemoteException {
                Parcel d = d();
                c.a(d, (Parcelable) status);
                c.a(d, (Parcelable) googleSignInAccount);
                b(7, d);
            }

            public final void a(SignInResponse signInResponse) throws RemoteException {
                Parcel d = d();
                c.a(d, (Parcelable) signInResponse);
                b(8, d);
            }

            public final void b(Status status) throws RemoteException {
                Parcel d = d();
                c.a(d, (Parcelable) status);
                b(6, d);
            }
        }

        public a() {
            super("com.google.android.gms.signin.internal.ISignInCallbacks");
        }

        public static d a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
            return queryLocalInterface instanceof d ? (d) queryLocalInterface : new a(iBinder);
        }

        protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
            switch (i) {
                case 3:
                    a((ConnectionResult) c.a(parcel, ConnectionResult.CREATOR), (AuthAccountResult) c.a(parcel, AuthAccountResult.CREATOR));
                    break;
                case 4:
                    a((Status) c.a(parcel, Status.CREATOR));
                    break;
                case 6:
                    b((Status) c.a(parcel, Status.CREATOR));
                    break;
                case 7:
                    a((Status) c.a(parcel, Status.CREATOR), (GoogleSignInAccount) c.a(parcel, GoogleSignInAccount.CREATOR));
                    break;
                case 8:
                    a((SignInResponse) c.a(parcel, SignInResponse.CREATOR));
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void a(ConnectionResult connectionResult, AuthAccountResult authAccountResult) throws RemoteException;

    void a(Status status) throws RemoteException;

    void a(Status status, GoogleSignInAccount googleSignInAccount) throws RemoteException;

    void a(SignInResponse signInResponse) throws RemoteException;

    void b(Status status) throws RemoteException;
}
