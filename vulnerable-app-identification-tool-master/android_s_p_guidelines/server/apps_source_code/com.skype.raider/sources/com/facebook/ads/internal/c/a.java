package com.facebook.ads.internal.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class a {
    public static final String a = a.class.getSimpleName();
    private final String b;
    private final boolean c;
    private final c d;

    private static final class a implements IInterface {
        private IBinder a;

        a(IBinder iBinder) {
            this.a = iBinder;
        }

        public final String a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                return readString;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public final IBinder asBinder() {
            return this.a;
        }

        public final boolean b() {
            boolean z = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    private static final class b implements ServiceConnection {
        private AtomicBoolean a;
        private final BlockingQueue<IBinder> b;

        private b() {
            this.a = new AtomicBoolean(false);
            this.b = new LinkedBlockingDeque();
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public final IBinder a() {
            if (!this.a.compareAndSet(true, true)) {
                return (IBinder) this.b.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public final void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public enum c {
        SHARED_PREFS,
        FB4A,
        DIRECT,
        REFLECTION,
        SERVICE
    }

    private a(String str, boolean z, c cVar) {
        this.b = str;
        this.c = z;
        this.d = cVar;
    }

    private static a a(Context context) {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo != null) {
                return new a(advertisingIdInfo.getId(), advertisingIdInfo.isLimitAdTrackingEnabled(), c.DIRECT);
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static a a(Context context, com.facebook.ads.internal.c.c.a aVar) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot get advertising info on main thread.");
        } else if (com.facebook.ads.internal.q.a.b.a() && com.facebook.ads.internal.q.a.b.b("idfa_override")) {
            return new a(com.facebook.ads.internal.q.a.b.a("idfa_override"), false, c.DIRECT);
        } else {
            if (aVar != null && !TextUtils.isEmpty(aVar.b)) {
                return new a(aVar.b, aVar.c, c.FB4A);
            }
            a a = a(context);
            if (a == null || TextUtils.isEmpty(a.b)) {
                Method a2 = d.a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
                if (a2 == null) {
                    a = null;
                } else {
                    Object a3 = d.a(null, a2, context);
                    if (a3 == null || ((Integer) a3).intValue() != 0) {
                        a = null;
                    } else {
                        a2 = d.a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
                        if (a2 == null) {
                            a = null;
                        } else {
                            Object a4 = d.a(null, a2, context);
                            if (a4 == null) {
                                a = null;
                            } else {
                                a2 = d.a(a4.getClass(), "getId", new Class[0]);
                                Method a5 = d.a(a4.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
                                a = (a2 == null || a5 == null) ? null : new a((String) d.a(a4, a2, new Object[0]), ((Boolean) d.a(a4, a5, new Object[0])).booleanValue(), c.REFLECTION);
                            }
                        }
                    }
                }
            }
            if (a == null || TextUtils.isEmpty(a.b)) {
                return b(context);
            }
            return a;
        }
    }

    private static a b(Context context) {
        ServiceConnection bVar = new b();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, bVar, 1)) {
            a aVar;
            try {
                a aVar2 = new a(bVar.a());
                aVar = new a(aVar2.a(), aVar2.b(), c.SERVICE);
                return aVar;
            } catch (Exception e) {
                aVar = e;
            } finally {
                context.unbindService(bVar);
            }
        }
        return null;
    }

    public final String a() {
        return this.b;
    }

    public final boolean b() {
        return this.c;
    }

    public final c c() {
        return this.d;
    }
}
