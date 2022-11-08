package com.facebook.ads.internal.p.a;

import android.os.Build.VERSION;
import com.adjust.sdk.Constants;
import com.skype.Defines;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.net.ssl.HttpsURLConnection;

public class a {
    private static int[] f = new int[20];
    private static final String g = a.class.getSimpleName();
    protected final q a = new f(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }
    };
    protected final d b = new e();
    protected r c = new g();
    protected int d = 2000;
    protected int e = Defines.SKYLIB_MESSAGE_MAX_BODY_SIZE;
    private int h = 3;
    private Map<String, String> i = new TreeMap();
    private boolean j;
    private Set<String> k;
    private Set<String> l;

    static {
        if (VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
        if (VERSION.SDK_INT > 8 && CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
    }

    private int a(HttpURLConnection httpURLConnection, byte[] bArr) {
        OutputStream outputStream = null;
        try {
            outputStream = this.a.a(httpURLConnection);
            if (outputStream != null) {
                this.a.a(outputStream, bArr);
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                }
            }
            return responseCode;
        } catch (Throwable th) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e2) {
                }
            }
        }
    }

    private n a(String str, j jVar, String str2, byte[] bArr) {
        HttpURLConnection b;
        n a;
        Exception exception;
        Throwable th;
        n nVar = null;
        n nVar2 = null;
        try {
            this.j = false;
            b = b(str);
            try {
                b.setConnectTimeout(this.d);
                b.setReadTimeout(this.e);
                this.a.a(b, jVar, str2);
                for (String str3 : this.i.keySet()) {
                    b.setRequestProperty(str3, (String) this.i.get(str3));
                }
                if (this.c.a()) {
                    this.c.a(b);
                }
                b.connect();
                this.j = true;
                Object obj = (this.l == null || this.l.isEmpty()) ? null : 1;
                Object obj2 = (this.k == null || this.k.isEmpty()) ? null : 1;
                if ((b instanceof HttpsURLConnection) && !(obj == null && obj2 == null)) {
                    try {
                        o.a((HttpsURLConnection) b, this.l, this.k);
                    } catch (Exception e) {
                    }
                }
                if (b.getDoOutput() && bArr != null) {
                    a(b, bArr);
                }
                a = b.getDoInput() ? a(b) : new n(b, null);
                if (this.c.a()) {
                    this.c.a(a);
                }
                if (b == null) {
                    return a;
                }
                b.disconnect();
                return a;
            } catch (Exception e2) {
                exception = e2;
            }
        } catch (Exception e22) {
            exception = e22;
            b = null;
        } catch (Throwable th2) {
            th = th2;
            b = null;
            if (this.c.a()) {
                this.c.a(nVar);
            }
            if (b != null) {
                b.disconnect();
            }
            throw th;
        }
        try {
            a = b(b);
            try {
                if (a.a() > 0) {
                    if (this.c.a()) {
                        this.c.a(a);
                    }
                    if (b == null) {
                        return a;
                    }
                    b.disconnect();
                    return a;
                }
                throw new m(exception, a);
            } catch (Throwable th3) {
                nVar = a;
                th = th3;
            }
        } catch (Exception e3) {
            exception.printStackTrace();
            if (null != null) {
                if (nVar2.a() > 0) {
                    if (this.c.a()) {
                        this.c.a(null);
                    }
                    if (b != null) {
                        b.disconnect();
                    }
                    return null;
                }
            }
            throw new m(exception, nVar2);
        } catch (Throwable th4) {
            th = th4;
        }
    }

    private n a(HttpURLConnection httpURLConnection) {
        Throwable th;
        byte[] bArr = null;
        InputStream b;
        try {
            b = this.a.b(httpURLConnection);
            if (b != null) {
                try {
                    bArr = this.a.a(b);
                } catch (Throwable th2) {
                    th = th2;
                    if (b != null) {
                        try {
                            b.close();
                        } catch (Exception e) {
                        }
                    }
                    throw th;
                }
            }
            n nVar = new n(httpURLConnection, bArr);
            if (b != null) {
                try {
                    b.close();
                } catch (Exception e2) {
                }
            }
            return nVar;
        } catch (Throwable th3) {
            th = th3;
            b = null;
        }
    }

    public static p a() {
        return new p();
    }

    private n b(l lVar) {
        n nVar = null;
        try {
            return a(lVar.a(), lVar.b(), lVar.c(), lVar.d());
        } catch (m e) {
            this.a.a(e);
            return nVar;
        } catch (Exception e2) {
            this.a.a(new m(e2, nVar));
            return nVar;
        }
    }

    private n b(HttpURLConnection httpURLConnection) {
        Throwable th;
        byte[] bArr = null;
        InputStream errorStream;
        try {
            errorStream = httpURLConnection.getErrorStream();
            if (errorStream != null) {
                try {
                    bArr = this.a.a(errorStream);
                } catch (Throwable th2) {
                    th = th2;
                    if (errorStream != null) {
                        try {
                            errorStream.close();
                        } catch (Exception e) {
                        }
                    }
                    throw th;
                }
            }
            n nVar = new n(httpURLConnection, bArr);
            if (errorStream != null) {
                try {
                    errorStream.close();
                } catch (Exception e2) {
                }
            }
            return nVar;
        } catch (Throwable th3) {
            th = th3;
            errorStream = null;
        }
    }

    private HttpURLConnection b(String str) {
        try {
            URL url = new URL(str);
            return this.a.a(str);
        } catch (Throwable e) {
            throw new IllegalArgumentException(str + " is not a valid URL", e);
        }
    }

    public final a a(String str, String str2) {
        this.i.put(str, str2);
        return this;
    }

    public final n a(l lVar) {
        Object obj;
        long currentTimeMillis = System.currentTimeMillis();
        int i = 0;
        while (i < this.h) {
            try {
                this.d = f[i + 2] * Constants.ONE_SECOND;
                if (this.c.a()) {
                    this.c.a((i + 1) + "of" + this.h + ", trying " + lVar.a());
                }
                currentTimeMillis = System.currentTimeMillis();
                n a = a(lVar.a(), lVar.b(), lVar.c(), lVar.d());
                if (a != null) {
                    return a;
                }
                i++;
            } catch (m e) {
                long currentTimeMillis2 = (System.currentTimeMillis() - currentTimeMillis) + 10;
                if (this.c.a()) {
                    this.c.a("ELAPSED TIME = " + currentTimeMillis2 + ", CT = " + this.d + ", RT = " + this.e);
                }
                if (this.j) {
                    obj = currentTimeMillis2 >= ((long) this.e) ? 1 : null;
                } else if (currentTimeMillis2 >= ((long) this.d)) {
                    int obj2 = 1;
                } else {
                    obj2 = null;
                }
                if (obj2 != null && i < this.h - 1) {
                    continue;
                } else if (!this.a.a(e) || i >= this.h - 1) {
                    throw e;
                } else {
                    try {
                        Thread.sleep((long) this.d);
                    } catch (InterruptedException e2) {
                        throw e;
                    }
                }
            }
        }
        return null;
    }

    public final n a(String str) {
        return b(new i(str));
    }

    public final n a(String str, p pVar) {
        return b(new k(str, pVar));
    }

    public final void a(int i) {
        if (i <= 0 || i > 18) {
            throw new IllegalArgumentException("Maximum retries must be between 1 and 18");
        }
        this.h = i;
    }

    public final void a(String str, p pVar, b bVar) {
        this.b.a(this, bVar).a(new k(str, pVar));
    }

    public final void a(Set<String> set) {
        this.l = set;
    }

    public final void b(int i) {
        this.d = i;
    }

    public final void b(Set<String> set) {
        this.k = set;
    }
}
