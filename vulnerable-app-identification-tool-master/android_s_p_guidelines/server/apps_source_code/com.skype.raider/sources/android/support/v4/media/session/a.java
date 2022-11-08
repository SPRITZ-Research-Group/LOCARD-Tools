package android.support.v4.media.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat.QueueItem;
import android.text.TextUtils;
import java.util.List;

public interface a extends IInterface {

    public static abstract class a extends Binder implements a {

        private static class a implements a {
            private IBinder a;

            a(IBinder remote) {
                this.a = remote;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final void a(String event, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    _data.writeString(event);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public final void a() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    this.a.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public final void a(PlaybackStateCompat state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (state != null) {
                        _data.writeInt(1);
                        state.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(3, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public final void a(MediaMetadataCompat metadata) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (metadata != null) {
                        _data.writeInt(1);
                        metadata.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(4, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public final void a(List<QueueItem> queue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    _data.writeTypedList(queue);
                    this.a.transact(5, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public final void a(CharSequence title) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (title != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(title, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(6, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public final void a(Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(7, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public final void a(ParcelableVolumeInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(8, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public final void a(int repeatMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    _data.writeInt(repeatMode);
                    this.a.transact(9, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public final void a(boolean enabled) throws RemoteException {
                int i = 1;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (!enabled) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.a.transact(10, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public final void b(boolean enabled) throws RemoteException {
                int i = 1;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (!enabled) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.a.transact(11, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public final void b(int shuffleMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    _data.writeInt(shuffleMode);
                    this.a.transact(12, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
        }

        public static a a(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
            if (iin == null || !(iin instanceof a)) {
                return new a(obj);
            }
            return (a) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean _arg0 = false;
            switch (code) {
                case 1:
                    Bundle _arg1;
                    data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    String _arg02 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    a(_arg02, _arg1);
                    return true;
                case 2:
                    data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    a();
                    return true;
                case 3:
                    PlaybackStateCompat _arg03;
                    data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (data.readInt() != 0) {
                        _arg03 = (PlaybackStateCompat) PlaybackStateCompat.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    a(_arg03);
                    return true;
                case 4:
                    MediaMetadataCompat _arg04;
                    data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (data.readInt() != 0) {
                        _arg04 = (MediaMetadataCompat) MediaMetadataCompat.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    a(_arg04);
                    return true;
                case 5:
                    data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    a((List) data.createTypedArrayList(QueueItem.CREATOR));
                    return true;
                case 6:
                    CharSequence _arg05;
                    data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (data.readInt() != 0) {
                        _arg05 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                    } else {
                        _arg05 = null;
                    }
                    a(_arg05);
                    return true;
                case 7:
                    Bundle _arg06;
                    data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (data.readInt() != 0) {
                        _arg06 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg06 = null;
                    }
                    a(_arg06);
                    return true;
                case 8:
                    ParcelableVolumeInfo _arg07;
                    data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (data.readInt() != 0) {
                        _arg07 = (ParcelableVolumeInfo) ParcelableVolumeInfo.CREATOR.createFromParcel(data);
                    } else {
                        _arg07 = null;
                    }
                    a(_arg07);
                    return true;
                case 9:
                    data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    a(data.readInt());
                    return true;
                case 10:
                    data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (data.readInt() != 0) {
                        _arg0 = true;
                    }
                    a(_arg0);
                    return true;
                case 11:
                    data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (data.readInt() != 0) {
                        _arg0 = true;
                    }
                    b(_arg0);
                    return true;
                case 12:
                    data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    b(data.readInt());
                    return true;
                case 1598968902:
                    reply.writeString("android.support.v4.media.session.IMediaControllerCallback");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a() throws RemoteException;

    void a(int i) throws RemoteException;

    void a(Bundle bundle) throws RemoteException;

    void a(MediaMetadataCompat mediaMetadataCompat) throws RemoteException;

    void a(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException;

    void a(PlaybackStateCompat playbackStateCompat) throws RemoteException;

    void a(CharSequence charSequence) throws RemoteException;

    void a(String str, Bundle bundle) throws RemoteException;

    void a(List<QueueItem> list) throws RemoteException;

    void a(boolean z) throws RemoteException;

    void b(int i) throws RemoteException;

    void b(boolean z) throws RemoteException;
}
