package android.support.v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface n extends IInterface {

    public static abstract class a extends Binder implements n {

        private static class a implements n {
            private IBinder a;

            a(IBinder remote) {
                this.a = remote;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final void a(String packageName, int id, String tag, Notification notification) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    _data.writeString(packageName);
                    _data.writeInt(id);
                    _data.writeString(tag);
                    if (notification != null) {
                        _data.writeInt(1);
                        notification.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public final void a(String packageName, int id, String tag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    _data.writeString(packageName);
                    _data.writeInt(id);
                    _data.writeString(tag);
                    this.a.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public final void a(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    _data.writeString(packageName);
                    this.a.transact(3, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }

        public static n a(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("android.support.v4.app.INotificationSideChannel");
            if (iin == null || !(iin instanceof n)) {
                return new a(obj);
            }
            return (n) iin;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    Notification _arg3;
                    data.enforceInterface("android.support.v4.app.INotificationSideChannel");
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    String _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg3 = (Notification) Notification.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    a(_arg0, _arg1, _arg2, _arg3);
                    return true;
                case 2:
                    data.enforceInterface("android.support.v4.app.INotificationSideChannel");
                    a(data.readString(), data.readInt(), data.readString());
                    return true;
                case 3:
                    data.enforceInterface("android.support.v4.app.INotificationSideChannel");
                    a(data.readString());
                    return true;
                case 1598968902:
                    reply.writeString("android.support.v4.app.INotificationSideChannel");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(String str) throws RemoteException;

    void a(String str, int i, String str2) throws RemoteException;

    void a(String str, int i, String str2, Notification notification) throws RemoteException;
}
