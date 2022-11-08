package android.support.customtabs;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface f extends IInterface {

    public static abstract class a extends Binder implements f {
        public a() {
            attachInterface(this, "android.support.customtabs.IPostMessageService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            d _arg0;
            switch (code) {
                case 2:
                    Bundle _arg1;
                    data.enforceInterface("android.support.customtabs.IPostMessageService");
                    _arg0 = android.support.customtabs.d.a.a(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    a(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 3:
                    Bundle _arg2;
                    data.enforceInterface("android.support.customtabs.IPostMessageService");
                    _arg0 = android.support.customtabs.d.a.a(data.readStrongBinder());
                    String _arg12 = data.readString();
                    if (data.readInt() != 0) {
                        _arg2 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    a(_arg0, _arg12, _arg2);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("android.support.customtabs.IPostMessageService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(d dVar, Bundle bundle) throws RemoteException;

    void a(d dVar, String str, Bundle bundle) throws RemoteException;
}
