package com.microsoft.tokenshare;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface g extends IInterface {

    public static abstract class a extends Binder implements g {

        private static class a implements g {
            private IBinder a;

            a(IBinder remote) {
                this.a = remote;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final List<AccountInfo> a() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.microsoft.tokenshare.ITokenProvider");
                    this.a.transact(1, _data, _reply, 0);
                    _reply.readException();
                    List<AccountInfo> _result = _reply.createTypedArrayList(AccountInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final RefreshToken a(AccountInfo accountInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    RefreshToken _result;
                    _data.writeInterfaceToken("com.microsoft.tokenshare.ITokenProvider");
                    if (accountInfo != null) {
                        _data.writeInt(1);
                        accountInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(2, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (RefreshToken) RefreshToken.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final String b() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.microsoft.tokenshare.ITokenProvider");
                    this.a.transact(3, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.microsoft.tokenshare.ITokenProvider");
        }

        public static g a(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.microsoft.tokenshare.ITokenProvider");
            if (iin == null || !(iin instanceof g)) {
                return new a(obj);
            }
            return (g) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("com.microsoft.tokenshare.ITokenProvider");
                    List<AccountInfo> _result = a();
                    reply.writeNoException();
                    reply.writeTypedList(_result);
                    return true;
                case 2:
                    AccountInfo _arg0;
                    data.enforceInterface("com.microsoft.tokenshare.ITokenProvider");
                    if (data.readInt() != 0) {
                        _arg0 = (AccountInfo) AccountInfo.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    RefreshToken _result2 = a(_arg0);
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(1);
                        _result2.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 3:
                    data.enforceInterface("com.microsoft.tokenshare.ITokenProvider");
                    String _result3 = b();
                    reply.writeNoException();
                    reply.writeString(_result3);
                    return true;
                case 1598968902:
                    reply.writeString("com.microsoft.tokenshare.ITokenProvider");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    RefreshToken a(AccountInfo accountInfo) throws RemoteException;

    List<AccountInfo> a() throws RemoteException;

    String b() throws RemoteException;
}
