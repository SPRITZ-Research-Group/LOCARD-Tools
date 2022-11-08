package com.facebook.ads.internal.o;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.f.e;
import com.facebook.ads.internal.p.a.n;
import com.facebook.ads.internal.p.a.p;
import com.facebook.ads.internal.q.a.i;
import com.facebook.ads.internal.q.a.m;
import com.facebook.ads.internal.q.a.q;
import com.facebook.ads.internal.q.d.b;
import com.facebook.ads.internal.r.d;
import com.facebook.ads.internal.r.h;
import com.facebook.ads.internal.r.k;
import java.security.MessageDigest;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONException;

public final class c {
    private static final m i;
    private static final ThreadPoolExecutor j;
    private final Context a;
    private final d b = d.a();
    private final com.facebook.ads.internal.l.a c = com.facebook.ads.internal.l.a.w(this.a);
    private Map<String, String> d;
    private a e;
    private b f;
    private com.facebook.ads.internal.p.a.a g;
    private final String h;

    public interface a {
        void a(f fVar);

        void a(com.facebook.ads.internal.r.c cVar);
    }

    static {
        ThreadFactory mVar = new m();
        i = mVar;
        j = (ThreadPoolExecutor) Executors.newCachedThreadPool(mVar);
    }

    public c(Context context) {
        String str;
        this.a = context.getApplicationContext();
        if (TextUtils.isEmpty(com.facebook.ads.internal.t.a.a())) {
            str = "https://graph.facebook.com/network_ads_common";
        } else {
            str = String.format("https://graph.%s.facebook.com/network_ads_common", new Object[]{com.facebook.ads.internal.t.a.a()});
        }
        this.h = str;
    }

    private void a() {
        if (this.g != null) {
            this.g.b(1);
            this.g.a(1);
            this.g = null;
        }
    }

    private void a(com.facebook.ads.internal.r.c cVar) {
        if (this.e != null) {
            this.e.a(cVar);
        }
        a();
    }

    private void a(String str) {
        try {
            e a = d.a(str);
            com.facebook.ads.internal.h.c a2 = a.a();
            if (a2 != null) {
                this.c.a(a2.b());
                a.a(a2.a().d(), this.f);
            }
            switch (a.b()) {
                case ADS:
                    f fVar = (f) a;
                    if (a2 != null) {
                        if (a2.a().e()) {
                            a.a(str, this.f);
                        }
                        Object obj = this.d != null ? (String) this.d.get("CLIENT_REQUEST_ID") : null;
                        String c = a.c();
                        if (!(TextUtils.isEmpty(c) || TextUtils.isEmpty(obj))) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 0; i < 32; i++) {
                                char charAt = "82042s3304s547sso6r044qoq3sn5199".charAt(i);
                                if ((charAt >= 'a' && charAt <= 'm') || (charAt >= 'A' && charAt <= 'M')) {
                                    charAt = (char) (charAt + 13);
                                } else if ((charAt >= 'n' && charAt <= 'z') || (charAt >= 'N' && charAt <= 'Z')) {
                                    charAt = (char) (charAt - 13);
                                }
                                stringBuilder.append(charAt);
                            }
                            byte[] bytes = (obj + c + stringBuilder.toString()).getBytes("iso-8859-1");
                            MessageDigest instance = MessageDigest.getInstance(Constants.SHA1);
                            instance.update(bytes, 0, bytes.length);
                            if (!a.d().equals(i.a(instance.digest()))) {
                                com.facebook.ads.internal.q.d.a.a(this.a, "network", b.h, new k());
                            }
                            bytes = (c + obj + stringBuilder.toString()).getBytes("iso-8859-1");
                            instance = MessageDigest.getInstance(Constants.SHA1);
                            instance.update(bytes, 0, bytes.length);
                            e.a(new com.facebook.ads.internal.f.a(c, i.a(instance.digest())), this.a);
                        }
                        if (!(TextUtils.isEmpty(a.e()) || TextUtils.isEmpty(obj))) {
                            new com.facebook.ads.internal.k.a(this.a, obj, a.e()).a();
                        }
                    }
                    if (this.e != null) {
                        this.e.a(fVar);
                    }
                    a();
                    return;
                case ERROR:
                    g gVar = (g) a;
                    String f = gVar.f();
                    com.facebook.ads.internal.r.a a3 = com.facebook.ads.internal.r.a.a(gVar.g(), com.facebook.ads.internal.r.a.ERROR_MESSAGE);
                    if (f != null) {
                        str = f;
                    }
                    a(com.facebook.ads.internal.r.c.a(a3, str));
                    return;
                default:
                    a(com.facebook.ads.internal.r.c.a(com.facebook.ads.internal.r.a.UNKNOWN_RESPONSE, str));
                    return;
            }
        } catch (Exception e) {
            a(com.facebook.ads.internal.r.c.a(com.facebook.ads.internal.r.a.PARSER_FAILURE, e.getMessage()));
        }
        a(com.facebook.ads.internal.r.c.a(com.facebook.ads.internal.r.a.PARSER_FAILURE, e.getMessage()));
    }

    public final void a(final b bVar) {
        a();
        if (q.a(this.a) == com.facebook.ads.internal.q.a.q.a.NONE) {
            a(new com.facebook.ads.internal.r.c(com.facebook.ads.internal.r.a.NETWORK_ERROR, "No network connection"));
            return;
        }
        this.f = bVar;
        com.facebook.ads.internal.g.a.a(this.a);
        if (a.a(bVar)) {
            String c = a.c(bVar);
            if (c != null) {
                a(c);
                return;
            } else {
                a(com.facebook.ads.internal.r.c.a(com.facebook.ads.internal.r.a.LOAD_TOO_FREQUENTLY, null));
                return;
            }
        }
        j.submit(new Runnable(this) {
            final /* synthetic */ c b;

            public final void run() {
                com.facebook.ads.internal.c.b.a(this.b.a);
                if (bVar.e().a()) {
                    try {
                        bVar.e().a(com.facebook.ads.internal.c.b.b);
                    } catch (d e) {
                        this.b.a(com.facebook.ads.internal.r.c.a(e));
                    }
                    this.b.a(bVar.e().b());
                    return;
                }
                this.b.d = bVar.f();
                try {
                    this.b.d.put("M_BANNER_KEY", new String(Base64.encode((this.b.a.getPackageName() + " " + this.b.a.getPackageManager().getInstallerPackageName(this.b.a.getPackageName())).getBytes(), 2)));
                } catch (Exception e2) {
                }
                try {
                    boolean z = bVar.c == h.NATIVE_250 || bVar.c == h.NATIVE_UNKNOWN || bVar.c == h.NATIVE_BANNER || bVar.c == null;
                    this.b.g = com.facebook.ads.internal.q.c.d.a(this.b.a, z);
                    com.facebook.ads.internal.p.a.a d = this.b.g;
                    String c = this.b.h;
                    this.b.g;
                    p a = com.facebook.ads.internal.p.a.a.a();
                    a.putAll(this.b.d);
                    d.a(c, a, new com.facebook.ads.internal.p.a.b(this.b) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public final void a(n nVar) {
                            if (nVar != null) {
                                String e = nVar.e();
                                a.b(this.a.f);
                                this.a.g = null;
                                this.a.a(e);
                            }
                        }

                        public final void a(Exception exception) {
                            if (com.facebook.ads.internal.p.a.m.class.equals(exception.getClass())) {
                                com.facebook.ads.internal.p.a.m mVar = (com.facebook.ads.internal.p.a.m) exception;
                                a.b(this.a.f);
                                this.a.g = null;
                                try {
                                    n a = mVar.a();
                                    if (a != null) {
                                        String e = a.e();
                                        this.a.b;
                                        e a2 = d.a(e);
                                        if (a2.b() == a.ERROR) {
                                            g gVar = (g) a2;
                                            String f = gVar.f();
                                            this.a.a(com.facebook.ads.internal.r.c.a(com.facebook.ads.internal.r.a.a(gVar.g(), com.facebook.ads.internal.r.a.ERROR_MESSAGE), f == null ? e : f));
                                            return;
                                        }
                                    }
                                } catch (JSONException e2) {
                                }
                                this.a.a(com.facebook.ads.internal.r.c.a(com.facebook.ads.internal.r.a.NETWORK_ERROR, mVar.getMessage()));
                                return;
                            }
                            this.a.a(com.facebook.ads.internal.r.c.a(com.facebook.ads.internal.r.a.NETWORK_ERROR, exception.getMessage()));
                        }
                    });
                } catch (Exception e3) {
                    this.b.a(com.facebook.ads.internal.r.c.a(com.facebook.ads.internal.r.a.AD_REQUEST_FAILED, e3.getMessage()));
                }
            }
        });
    }

    public final void a(a aVar) {
        this.e = aVar;
    }
}
