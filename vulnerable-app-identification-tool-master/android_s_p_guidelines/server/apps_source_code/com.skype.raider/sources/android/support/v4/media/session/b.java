package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaSessionCompat.QueueItem;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.List;

public interface b extends IInterface {

    public static abstract class a extends Binder implements b {

        private static class a implements b {
            private IBinder a;

            a(IBinder remote) {
                this.a = remote;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final void a(String command, Bundle args, ResultReceiverWrapper cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeString(command);
                    if (args != null) {
                        _data.writeInt(1);
                        args.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (cb != null) {
                        _data.writeInt(1);
                        cb.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final boolean a(KeyEvent mediaButton) throws RemoteException {
                boolean _result = true;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (mediaButton != null) {
                        _data.writeInt(1);
                        mediaButton.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(2, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void a(a cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    this.a.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void b(a cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    this.a.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final boolean a() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(5, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
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
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(6, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final String c() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(7, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final PendingIntent d() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    PendingIntent _result;
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(8, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (PendingIntent) PendingIntent.CREATOR.createFromParcel(_reply);
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

            public final long e() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(9, _data, _reply, 0);
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final ParcelableVolumeInfo f() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    ParcelableVolumeInfo _result;
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(10, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (ParcelableVolumeInfo) ParcelableVolumeInfo.CREATOR.createFromParcel(_reply);
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

            public final void a(int direction, int flags, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeInt(direction);
                    _data.writeInt(flags);
                    _data.writeString(packageName);
                    this.a.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void b(int value, int flags, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeInt(value);
                    _data.writeInt(flags);
                    _data.writeString(packageName);
                    this.a.transact(12, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final MediaMetadataCompat g() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    MediaMetadataCompat _result;
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(27, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (MediaMetadataCompat) MediaMetadataCompat.CREATOR.createFromParcel(_reply);
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

            public final PlaybackStateCompat h() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    PlaybackStateCompat _result;
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(28, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (PlaybackStateCompat) PlaybackStateCompat.CREATOR.createFromParcel(_reply);
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

            public final List<QueueItem> i() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(29, _data, _reply, 0);
                    _reply.readException();
                    List<QueueItem> _result = _reply.createTypedArrayList(QueueItem.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final CharSequence j() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    CharSequence _result;
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(30, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(_reply);
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

            public final Bundle k() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(31, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
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

            public final int l() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(32, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final boolean m() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(45, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final int n() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(37, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final boolean o() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(38, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final int p() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(47, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void a(MediaDescriptionCompat description) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (description != null) {
                        _data.writeInt(1);
                        description.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(41, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void a(MediaDescriptionCompat description, int index) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (description != null) {
                        _data.writeInt(1);
                        description.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(index);
                    this.a.transact(42, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void b(MediaDescriptionCompat description) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (description != null) {
                        _data.writeInt(1);
                        description.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(43, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void a(int index) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeInt(index);
                    this.a.transact(44, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void q() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(33, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void a(String uri, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeString(uri);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(34, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void b(String string, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeString(string);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(35, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void a(Uri uri, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (uri != null) {
                        _data.writeInt(1);
                        uri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(36, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void r() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(13, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void c(String uri, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeString(uri);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(14, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void d(String string, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeString(string);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(15, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void b(Uri uri, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (uri != null) {
                        _data.writeInt(1);
                        uri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(16, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void a(long id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeLong(id);
                    this.a.transact(17, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void s() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(18, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void t() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(19, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void u() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(20, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void v() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(21, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void w() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(22, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void x() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.a.transact(23, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void b(long pos) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeLong(pos);
                    this.a.transact(24, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void a(RatingCompat rating) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (rating != null) {
                        _data.writeInt(1);
                        rating.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(25, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void a(RatingCompat rating, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (rating != null) {
                        _data.writeInt(1);
                        rating.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(51, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void a(boolean enabled) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (enabled) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.a.transact(46, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void b(int repeatMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeInt(repeatMode);
                    this.a.transact(39, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void b(boolean shuffleMode) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (shuffleMode) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.a.transact(40, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void c(int shuffleMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeInt(shuffleMode);
                    this.a.transact(48, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public final void e(String action, Bundle args) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    _data.writeString(action);
                    if (args != null) {
                        _data.writeInt(1);
                        args.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.a.transact(26, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "android.support.v4.media.session.IMediaSession");
        }

        public static b a(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            if (iin == null || !(iin instanceof b)) {
                return new a(obj);
            }
            return (b) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            String _arg0;
            Bundle _arg1;
            boolean _result;
            String _result2;
            Uri _arg02;
            RatingCompat _arg03;
            int _result3;
            boolean _arg04;
            MediaDescriptionCompat _arg05;
            switch (code) {
                case 1:
                    ResultReceiverWrapper _arg2;
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _arg0 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg2 = (ResultReceiverWrapper) ResultReceiverWrapper.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    a(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                case 2:
                    KeyEvent _arg06;
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (data.readInt() != 0) {
                        _arg06 = (KeyEvent) KeyEvent.CREATOR.createFromParcel(data);
                    } else {
                        _arg06 = null;
                    }
                    _result = a(_arg06);
                    reply.writeNoException();
                    if (_result) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 3:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    a(android.support.v4.media.session.a.a.a(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    b(android.support.v4.media.session.a.a.a(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _result = a();
                    reply.writeNoException();
                    if (_result) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 6:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _result2 = b();
                    reply.writeNoException();
                    reply.writeString(_result2);
                    return true;
                case 7:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _result2 = c();
                    reply.writeNoException();
                    reply.writeString(_result2);
                    return true;
                case 8:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    PendingIntent _result4 = d();
                    reply.writeNoException();
                    if (_result4 != null) {
                        reply.writeInt(1);
                        _result4.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 9:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    long _result5 = e();
                    reply.writeNoException();
                    reply.writeLong(_result5);
                    return true;
                case 10:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    ParcelableVolumeInfo _result6 = f();
                    reply.writeNoException();
                    if (_result6 != null) {
                        reply.writeInt(1);
                        _result6.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 11:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    a(data.readInt(), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    b(data.readInt(), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    r();
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _arg0 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    c(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _arg0 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    d(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (data.readInt() != 0) {
                        _arg02 = (Uri) Uri.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    b(_arg02, _arg1);
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    a(data.readLong());
                    reply.writeNoException();
                    return true;
                case 18:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    s();
                    reply.writeNoException();
                    return true;
                case 19:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    t();
                    reply.writeNoException();
                    return true;
                case 20:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    u();
                    reply.writeNoException();
                    return true;
                case 21:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    v();
                    reply.writeNoException();
                    return true;
                case 22:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    w();
                    reply.writeNoException();
                    return true;
                case 23:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    x();
                    reply.writeNoException();
                    return true;
                case 24:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    b(data.readLong());
                    reply.writeNoException();
                    return true;
                case 25:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (data.readInt() != 0) {
                        _arg03 = (RatingCompat) RatingCompat.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    a(_arg03);
                    reply.writeNoException();
                    return true;
                case 26:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _arg0 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    e(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 27:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    MediaMetadataCompat _result7 = g();
                    reply.writeNoException();
                    if (_result7 != null) {
                        reply.writeInt(1);
                        _result7.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 28:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    PlaybackStateCompat _result8 = h();
                    reply.writeNoException();
                    if (_result8 != null) {
                        reply.writeInt(1);
                        _result8.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 29:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    List<QueueItem> _result9 = i();
                    reply.writeNoException();
                    reply.writeTypedList(_result9);
                    return true;
                case 30:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    CharSequence _result10 = j();
                    reply.writeNoException();
                    if (_result10 != null) {
                        reply.writeInt(1);
                        TextUtils.writeToParcel(_result10, reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 31:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Bundle _result11 = k();
                    reply.writeNoException();
                    if (_result11 != null) {
                        reply.writeInt(1);
                        _result11.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 32:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _result3 = l();
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 33:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    q();
                    reply.writeNoException();
                    return true;
                case 34:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _arg0 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    a(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 35:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _arg0 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    b(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 36:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (data.readInt() != 0) {
                        _arg02 = (Uri) Uri.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    a(_arg02, _arg1);
                    reply.writeNoException();
                    return true;
                case 37:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _result3 = n();
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 38:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _result = o();
                    reply.writeNoException();
                    if (_result) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 39:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    b(data.readInt());
                    reply.writeNoException();
                    return true;
                case 40:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (data.readInt() != 0) {
                        _arg04 = true;
                    } else {
                        _arg04 = false;
                    }
                    b(_arg04);
                    reply.writeNoException();
                    return true;
                case 41:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (data.readInt() != 0) {
                        _arg05 = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(data);
                    } else {
                        _arg05 = null;
                    }
                    a(_arg05);
                    reply.writeNoException();
                    return true;
                case 42:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (data.readInt() != 0) {
                        _arg05 = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(data);
                    } else {
                        _arg05 = null;
                    }
                    a(_arg05, data.readInt());
                    reply.writeNoException();
                    return true;
                case 43:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (data.readInt() != 0) {
                        _arg05 = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(data);
                    } else {
                        _arg05 = null;
                    }
                    b(_arg05);
                    reply.writeNoException();
                    return true;
                case 44:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    a(data.readInt());
                    reply.writeNoException();
                    return true;
                case 45:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _result = m();
                    reply.writeNoException();
                    if (_result) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 46:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (data.readInt() != 0) {
                        _arg04 = true;
                    } else {
                        _arg04 = false;
                    }
                    a(_arg04);
                    reply.writeNoException();
                    return true;
                case 47:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    _result3 = p();
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 48:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    c(data.readInt());
                    reply.writeNoException();
                    return true;
                case 51:
                    data.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (data.readInt() != 0) {
                        _arg03 = (RatingCompat) RatingCompat.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    a(_arg03, _arg1);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("android.support.v4.media.session.IMediaSession");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(int i) throws RemoteException;

    void a(int i, int i2, String str) throws RemoteException;

    void a(long j) throws RemoteException;

    void a(Uri uri, Bundle bundle) throws RemoteException;

    void a(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException;

    void a(MediaDescriptionCompat mediaDescriptionCompat, int i) throws RemoteException;

    void a(RatingCompat ratingCompat) throws RemoteException;

    void a(RatingCompat ratingCompat, Bundle bundle) throws RemoteException;

    void a(a aVar) throws RemoteException;

    void a(String str, Bundle bundle) throws RemoteException;

    void a(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) throws RemoteException;

    void a(boolean z) throws RemoteException;

    boolean a() throws RemoteException;

    boolean a(KeyEvent keyEvent) throws RemoteException;

    String b() throws RemoteException;

    void b(int i) throws RemoteException;

    void b(int i, int i2, String str) throws RemoteException;

    void b(long j) throws RemoteException;

    void b(Uri uri, Bundle bundle) throws RemoteException;

    void b(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException;

    void b(a aVar) throws RemoteException;

    void b(String str, Bundle bundle) throws RemoteException;

    void b(boolean z) throws RemoteException;

    String c() throws RemoteException;

    void c(int i) throws RemoteException;

    void c(String str, Bundle bundle) throws RemoteException;

    PendingIntent d() throws RemoteException;

    void d(String str, Bundle bundle) throws RemoteException;

    long e() throws RemoteException;

    void e(String str, Bundle bundle) throws RemoteException;

    ParcelableVolumeInfo f() throws RemoteException;

    MediaMetadataCompat g() throws RemoteException;

    PlaybackStateCompat h() throws RemoteException;

    List<QueueItem> i() throws RemoteException;

    CharSequence j() throws RemoteException;

    Bundle k() throws RemoteException;

    int l() throws RemoteException;

    boolean m() throws RemoteException;

    int n() throws RemoteException;

    boolean o() throws RemoteException;

    int p() throws RemoteException;

    void q() throws RemoteException;

    void r() throws RemoteException;

    void s() throws RemoteException;

    void t() throws RemoteException;

    void u() throws RemoteException;

    void v() throws RemoteException;

    void w() throws RemoteException;

    void x() throws RemoteException;
}
