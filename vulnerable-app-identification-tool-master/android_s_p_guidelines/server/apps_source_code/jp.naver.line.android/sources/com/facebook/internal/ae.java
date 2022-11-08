package com.facebook.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.facebook.n;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;

public final class ae {
    private static Handler a;
    private static bt b = new bt(8);
    private static bt c = new bt(2);
    private static final Map<ai, ah> d = new HashMap();

    public static void a(aj ajVar) {
        if (ajVar != null) {
            ai aiVar = new ai(ajVar.b(), ajVar.e());
            synchronized (d) {
                ah ahVar = (ah) d.get(aiVar);
                if (ahVar != null) {
                    ahVar.b = ajVar;
                    ahVar.c = false;
                    ahVar.a.b();
                } else {
                    a(ajVar, aiVar, ajVar.d());
                }
            }
        }
    }

    public static boolean b(aj ajVar) {
        boolean z;
        ai aiVar = new ai(ajVar.b(), ajVar.e());
        synchronized (d) {
            ah ahVar = (ah) d.get(aiVar);
            z = true;
            if (ahVar == null) {
                z = false;
            } else if (ahVar.a.a()) {
                d.remove(aiVar);
            } else {
                ahVar.c = true;
            }
        }
        return z;
    }

    private static void a(aj ajVar, ai aiVar, boolean z) {
        a(ajVar, aiVar, c, new af(ajVar.a(), aiVar, z));
    }

    private static void a(aj ajVar, ai aiVar, bt btVar, Runnable runnable) {
        synchronized (d) {
            ah ahVar = new ah();
            ahVar.b = ajVar;
            d.put(aiVar, ahVar);
            ahVar.a = btVar.a(runnable);
        }
    }

    private static void a(ai aiVar, Exception exception, Bitmap bitmap, boolean z) {
        ah b = b(aiVar);
        if (b != null && !b.c) {
            final aj ajVar = b.b;
            final al c = ajVar.c();
            if (c != null) {
                final Exception exception2 = exception;
                final boolean z2 = z;
                final Bitmap bitmap2 = bitmap;
                a().post(new Runnable() {
                    public final void run() {
                        c.a(new am(ajVar, exception2, z2, bitmap2));
                    }
                });
            }
        }
    }

    private static synchronized Handler a() {
        Handler handler;
        synchronized (ae.class) {
            if (a == null) {
                a = new Handler(Looper.getMainLooper());
            }
            handler = a;
        }
        return handler;
    }

    private static ah b(ai aiVar) {
        ah ahVar;
        synchronized (d) {
            ahVar = (ah) d.remove(aiVar);
        }
        return ahVar;
    }

    static /* synthetic */ void a(ai aiVar, boolean z) {
        Closeable a;
        Bitmap decodeStream;
        ah b;
        aj ajVar;
        boolean z2 = false;
        if (z) {
            Uri a2 = bi.a(aiVar.a);
            if (a2 != null) {
                a = an.a(a2);
                if (a != null) {
                    z2 = true;
                }
                if (!z2) {
                    a = an.a(aiVar.a);
                }
                if (a == null) {
                    decodeStream = BitmapFactory.decodeStream(a);
                    bj.a(a);
                    a(aiVar, null, decodeStream, z2);
                }
                b = b(aiVar);
                if (!(b == null || b.c)) {
                    ajVar = b.b;
                    a(ajVar, aiVar, b, new ag(ajVar.a(), aiVar));
                }
                return;
            }
        }
        a = null;
        if (z2) {
            a = an.a(aiVar.a);
        }
        if (a == null) {
            b = b(aiVar);
            ajVar = b.b;
            a(ajVar, aiVar, b, new ag(ajVar.a(), aiVar));
            return;
        }
        decodeStream = BitmapFactory.decodeStream(a);
        bj.a(a);
        a(aiVar, null, decodeStream, z2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(ai aiVar) {
        Bitmap bitmap;
        Exception exception;
        Exception e;
        Exception exception2;
        Throwable th;
        Closeable closeable = null;
        Object obj = 1;
        URLConnection uRLConnection;
        Closeable closeable2;
        try {
            uRLConnection = (HttpURLConnection) new URL(aiVar.a.toString()).openConnection();
            try {
                uRLConnection.setInstanceFollowRedirects(false);
                int responseCode = uRLConnection.getResponseCode();
                if (responseCode != 200) {
                    switch (responseCode) {
                        case HttpStatus.SC_MOVED_PERMANENTLY /*301*/:
                        case HttpStatus.SC_MOVED_TEMPORARILY /*302*/:
                            String headerField = uRLConnection.getHeaderField("location");
                            if (!bj.a(headerField)) {
                                Uri parse = Uri.parse(headerField);
                                bi.a(aiVar.a, parse);
                                ah b = b(aiVar);
                                if (!(b == null || b.c)) {
                                    a(b.b, new ai(parse, aiVar.b), false);
                                }
                            }
                            closeable2 = null;
                            bitmap = closeable2;
                            obj = null;
                            break;
                        default:
                            closeable2 = uRLConnection.getErrorStream();
                            try {
                                StringBuilder stringBuilder = new StringBuilder();
                                if (closeable2 != null) {
                                    Closeable inputStreamReader = new InputStreamReader(closeable2);
                                    char[] cArr = new char[128];
                                    while (true) {
                                        int read = inputStreamReader.read(cArr, 0, 128);
                                        if (read > 0) {
                                            stringBuilder.append(cArr, 0, read);
                                        } else {
                                            bj.a(inputStreamReader);
                                        }
                                    }
                                } else {
                                    stringBuilder.append("Unexpected error while downloading an image.");
                                }
                                Exception nVar = new n(stringBuilder.toString());
                                bitmap = null;
                                exception = nVar;
                                break;
                            } catch (IOException e2) {
                                e = e2;
                                bj.a(closeable2);
                                bj.a(uRLConnection);
                                exception2 = e;
                                bitmap = null;
                                exception = exception2;
                                if (obj != null) {
                                    a(aiVar, exception, bitmap, false);
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                closeable = closeable2;
                                bj.a(closeable);
                                bj.a(uRLConnection);
                                throw th;
                            }
                    }
                }
                closeable2 = an.a((HttpURLConnection) uRLConnection);
                bitmap = BitmapFactory.decodeStream(closeable2);
                bj.a(closeable2);
                bj.a(uRLConnection);
            } catch (IOException e3) {
                e = e3;
                closeable2 = null;
                bj.a(closeable2);
                bj.a(uRLConnection);
                exception2 = e;
                bitmap = null;
                exception = exception2;
                if (obj != null) {
                    a(aiVar, exception, bitmap, false);
                }
            } catch (Throwable th3) {
                th = th3;
                bj.a(closeable);
                bj.a(uRLConnection);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            uRLConnection = null;
            closeable2 = uRLConnection;
            bj.a(closeable2);
            bj.a(uRLConnection);
            exception2 = e;
            bitmap = null;
            exception = exception2;
            if (obj != null) {
                a(aiVar, exception, bitmap, false);
            }
        } catch (Throwable th4) {
            th = th4;
            uRLConnection = null;
            bj.a(closeable);
            bj.a(uRLConnection);
            throw th;
        }
        if (obj != null) {
            a(aiVar, exception, bitmap, false);
        }
    }
}
