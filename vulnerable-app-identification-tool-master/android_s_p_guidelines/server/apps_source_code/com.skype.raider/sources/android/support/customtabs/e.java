package android.support.customtabs;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface e extends IInterface {

    public static abstract class a extends Binder implements e {
        public a() {
            attachInterface(this, "android.support.customtabs.ICustomTabsService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            boolean _result;
            d _arg0;
            switch (code) {
                case 2:
                    data.enforceInterface("android.support.customtabs.ICustomTabsService");
                    data.readLong();
                    _result = a();
                    reply.writeNoException();
                    if (_result) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 3:
                    data.enforceInterface("android.support.customtabs.ICustomTabsService");
                    _result = a(android.support.customtabs.d.a.a(data.readStrongBinder()));
                    reply.writeNoException();
                    if (_result) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 4:
                    data.enforceInterface("android.support.customtabs.ICustomTabsService");
                    _arg0 = android.support.customtabs.d.a.a(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        Uri.CREATOR.createFromParcel(data);
                    }
                    if (data.readInt() != 0) {
                        Bundle.CREATOR.createFromParcel(data);
                    }
                    data.createTypedArrayList(Bundle.CREATOR);
                    _result = b(_arg0);
                    reply.writeNoException();
                    if (_result) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 5:
                    data.enforceInterface("android.support.customtabs.ICustomTabsService");
                    data.readString();
                    if (data.readInt() != 0) {
                        Bundle.CREATOR.createFromParcel(data);
                    }
                    Bundle _result2 = b();
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(1);
                        _result2.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 6:
                    data.enforceInterface("android.support.customtabs.ICustomTabsService");
                    _arg0 = android.support.customtabs.d.a.a(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        Bundle.CREATOR.createFromParcel(data);
                    }
                    _result = c(_arg0);
                    reply.writeNoException();
                    if (_result) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 7:
                    data.enforceInterface("android.support.customtabs.ICustomTabsService");
                    _arg0 = android.support.customtabs.d.a.a(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        Uri.CREATOR.createFromParcel(data);
                    }
                    _result = d(_arg0);
                    reply.writeNoException();
                    if (_result) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 8:
                    data.enforceInterface("android.support.customtabs.ICustomTabsService");
                    _arg0 = android.support.customtabs.d.a.a(data.readStrongBinder());
                    data.readString();
                    if (data.readInt() != 0) {
                        Bundle.CREATOR.createFromParcel(data);
                    }
                    int _result3 = e(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 1598968902:
                    reply.writeString("android.support.customtabs.ICustomTabsService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    boolean a() throws RemoteException;

    boolean a(d dVar) throws RemoteException;

    Bundle b() throws RemoteException;

    boolean b(d dVar) throws RemoteException;

    boolean c(d dVar) throws RemoteException;

    boolean d(d dVar) throws RemoteException;

    int e(d dVar) throws RemoteException;
}
