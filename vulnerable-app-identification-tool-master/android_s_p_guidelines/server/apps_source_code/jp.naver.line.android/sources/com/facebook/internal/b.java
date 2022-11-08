package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import com.facebook.n;
import java.lang.reflect.Method;

public class b {
    private static final String a = b.class.getCanonicalName();
    private static b g;
    private String b;
    private String c;
    private String d;
    private boolean e;
    private long f;

    private static b b(Context context) {
        try {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                Method a = bj.a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
                if (a == null) {
                    return null;
                }
                Object a2 = bj.a(null, a, context);
                if (!(a2 instanceof Integer) || ((Integer) a2).intValue() != 0) {
                    return null;
                }
                a = bj.a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
                if (a == null) {
                    return null;
                }
                Object a3 = bj.a(null, a, context);
                if (a3 == null) {
                    return null;
                }
                a = bj.a(a3.getClass(), "getId", new Class[0]);
                Method a4 = bj.a(a3.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
                if (a == null || a4 == null) {
                    return null;
                }
                b bVar = new b();
                bVar.c = (String) bj.a(a3, a, new Object[0]);
                bVar.e = ((Boolean) bj.a(a3, a4, new Object[0])).booleanValue();
                return bVar;
            }
            throw new n("getAndroidId cannot be called on the main thread.");
        } catch (Exception e) {
            bj.a("android_id", e);
            return null;
        }
    }

    private static b c(Context context) {
        ServiceConnection dVar = new d();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        b bVar = true;
        if (context.bindService(intent, dVar, 1)) {
            try {
                c cVar = new c(dVar.a());
                bVar = new b();
                bVar.c = cVar.a();
                bVar.e = cVar.b();
                return bVar;
            } catch (Exception e) {
                bVar = "android_id";
                bj.a((String) bVar, e);
            } finally {
                context.unbindService(dVar);
            }
        }
        return null;
    }

    public static b a(Context context) {
        Cursor cursor;
        Exception e;
        Throwable th;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.e(a, "getAttributionIdentifiers should not be called from the main thread");
        }
        if (g != null && System.currentTimeMillis() - g.f < 3600000) {
            return g;
        }
        b b = b(context);
        if (b == null) {
            b = c(context);
            if (b == null) {
                b = new b();
            }
        }
        Cursor cursor2 = null;
        try {
            Uri parse;
            Uri uri;
            PackageManager packageManager;
            String installerPackageName;
            Cursor query;
            int columnIndex;
            int columnIndex2;
            int columnIndex3;
            String[] strArr = new String[]{"aid", "androidid", "limit_tracking"};
            if (context.getPackageManager().resolveContentProvider("com.facebook.katana.provider.AttributionIdProvider", 0) != null) {
                parse = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
            } else if (context.getPackageManager().resolveContentProvider("com.facebook.wakizashi.provider.AttributionIdProvider", 0) != null) {
                parse = Uri.parse("content://com.facebook.wakizashi.provider.AttributionIdProvider");
            } else {
                uri = null;
                packageManager = context.getPackageManager();
                installerPackageName = packageManager == null ? packageManager.getInstallerPackageName(context.getPackageName()) : null;
                if (installerPackageName != null) {
                    b.d = installerPackageName;
                }
                if (uri == null) {
                    return a(b);
                }
                query = context.getContentResolver().query(uri, strArr, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            columnIndex = query.getColumnIndex("aid");
                            columnIndex2 = query.getColumnIndex("androidid");
                            columnIndex3 = query.getColumnIndex("limit_tracking");
                            b.b = query.getString(columnIndex);
                            if (columnIndex2 > 0 && columnIndex3 > 0 && b.c == null) {
                                b.c = query.getString(columnIndex2);
                                b.e = Boolean.parseBoolean(query.getString(columnIndex3));
                            }
                            if (query != null) {
                                query.close();
                            }
                            return a(b);
                        }
                    } catch (Exception e2) {
                        Exception exception = e2;
                        cursor = query;
                        e = exception;
                        try {
                            new StringBuilder("Caught unexpected exception in getAttributionId(): ").append(e.toString());
                            if (cursor != null) {
                                cursor.close();
                            }
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            cursor2 = cursor;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        cursor2 = query;
                        th = th3;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                }
                b = a(b);
                if (query != null) {
                    query.close();
                }
                return b;
            }
            uri = parse;
            packageManager = context.getPackageManager();
            if (packageManager == null) {
            }
            if (installerPackageName != null) {
                b.d = installerPackageName;
            }
            if (uri == null) {
                return a(b);
            }
            query = context.getContentResolver().query(uri, strArr, null, null, null);
            if (query != null) {
                if (query.moveToFirst()) {
                    columnIndex = query.getColumnIndex("aid");
                    columnIndex2 = query.getColumnIndex("androidid");
                    columnIndex3 = query.getColumnIndex("limit_tracking");
                    b.b = query.getString(columnIndex);
                    b.c = query.getString(columnIndex2);
                    b.e = Boolean.parseBoolean(query.getString(columnIndex3));
                    if (query != null) {
                        query.close();
                    }
                    return a(b);
                }
            }
            b = a(b);
            if (query != null) {
                query.close();
            }
            return b;
        } catch (Exception e3) {
            e = e3;
            cursor = null;
            new StringBuilder("Caught unexpected exception in getAttributionId(): ").append(e.toString());
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    private static b a(b bVar) {
        bVar.f = System.currentTimeMillis();
        g = bVar;
        return bVar;
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.c;
    }

    public final String c() {
        return this.d;
    }

    public final boolean d() {
        return this.e;
    }
}
