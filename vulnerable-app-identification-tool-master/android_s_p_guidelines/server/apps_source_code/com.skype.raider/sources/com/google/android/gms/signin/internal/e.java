package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.q;
import com.google.android.gms.common.internal.w;
import com.google.android.gms.internal.d.b;
import com.google.android.gms.internal.d.c;

public interface e extends IInterface {

    public static abstract class a extends b implements e {

        public static class a extends com.google.android.gms.internal.d.a implements e {
            a(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
            }

            public final void a(int i) throws RemoteException {
                Parcel d = d();
                d.writeInt(i);
                b(7, d);
            }

            public final void a(int i, Account account, d dVar) throws RemoteException {
                Parcel d = d();
                d.writeInt(i);
                c.a(d, (Parcelable) account);
                c.a(d, (IInterface) dVar);
                b(8, d);
            }

            public final void a(AuthAccountRequest authAccountRequest, d dVar) throws RemoteException {
                Parcel d = d();
                c.a(d, (Parcelable) authAccountRequest);
                c.a(d, (IInterface) dVar);
                b(2, d);
            }

            public final void a(ResolveAccountRequest resolveAccountRequest, w wVar) throws RemoteException {
                Parcel d = d();
                c.a(d, (Parcelable) resolveAccountRequest);
                c.a(d, (IInterface) wVar);
                b(5, d);
            }

            public final void a(q qVar, int i, boolean z) throws RemoteException {
                Parcel d = d();
                c.a(d, (IInterface) qVar);
                d.writeInt(i);
                c.a(d, z);
                b(9, d);
            }

            public final void a(CheckServerAuthResult checkServerAuthResult) throws RemoteException {
                Parcel d = d();
                c.a(d, (Parcelable) checkServerAuthResult);
                b(3, d);
            }

            public final void a(RecordConsentRequest recordConsentRequest, d dVar) throws RemoteException {
                Parcel d = d();
                c.a(d, (Parcelable) recordConsentRequest);
                c.a(d, (IInterface) dVar);
                b(10, d);
            }

            public final void a(SignInRequest signInRequest, d dVar) throws RemoteException {
                Parcel d = d();
                c.a(d, (Parcelable) signInRequest);
                c.a(d, (IInterface) dVar);
                b(12, d);
            }

            public final void a(d dVar) throws RemoteException {
                Parcel d = d();
                c.a(d, (IInterface) dVar);
                b(11, d);
            }

            public final void a(boolean z) throws RemoteException {
                Parcel d = d();
                c.a(d, z);
                b(4, d);
            }

            public final void b(boolean z) throws RemoteException {
                Parcel d = d();
                c.a(d, z);
                b(13, d);
            }
        }

        public static e a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
            return queryLocalInterface instanceof e ? (e) queryLocalInterface : new a(iBinder);
        }

        protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
            switch (i) {
                case 2:
                    a((AuthAccountRequest) c.a(parcel, AuthAccountRequest.CREATOR), com.google.android.gms.signin.internal.d.a.a(parcel.readStrongBinder()));
                    break;
                case 3:
                    a((CheckServerAuthResult) c.a(parcel, CheckServerAuthResult.CREATOR));
                    break;
                case 4:
                    a(c.a(parcel));
                    break;
                case 5:
                    a((ResolveAccountRequest) c.a(parcel, ResolveAccountRequest.CREATOR), com.google.android.gms.common.internal.w.a.a(parcel.readStrongBinder()));
                    break;
                case 7:
                    a(parcel.readInt());
                    break;
                case 8:
                    a(parcel.readInt(), (Account) c.a(parcel, Account.CREATOR), com.google.android.gms.signin.internal.d.a.a(parcel.readStrongBinder()));
                    break;
                case 9:
                    a(com.google.android.gms.common.internal.q.a.a(parcel.readStrongBinder()), parcel.readInt(), c.a(parcel));
                    break;
                case 10:
                    a((RecordConsentRequest) c.a(parcel, RecordConsentRequest.CREATOR), com.google.android.gms.signin.internal.d.a.a(parcel.readStrongBinder()));
                    break;
                case 11:
                    a(com.google.android.gms.signin.internal.d.a.a(parcel.readStrongBinder()));
                    break;
                case 12:
                    a((SignInRequest) c.a(parcel, SignInRequest.CREATOR), com.google.android.gms.signin.internal.d.a.a(parcel.readStrongBinder()));
                    break;
                case 13:
                    b(c.a(parcel));
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void a(int i) throws RemoteException;

    void a(int i, Account account, d dVar) throws RemoteException;

    void a(AuthAccountRequest authAccountRequest, d dVar) throws RemoteException;

    void a(ResolveAccountRequest resolveAccountRequest, w wVar) throws RemoteException;

    void a(q qVar, int i, boolean z) throws RemoteException;

    void a(CheckServerAuthResult checkServerAuthResult) throws RemoteException;

    void a(RecordConsentRequest recordConsentRequest, d dVar) throws RemoteException;

    void a(SignInRequest signInRequest, d dVar) throws RemoteException;

    void a(d dVar) throws RemoteException;

    void a(boolean z) throws RemoteException;

    void b(boolean z) throws RemoteException;
}
