package com.appboy;

import a.a.aa;
import a.a.ab;
import a.a.ad;
import a.a.ag;
import a.a.ak;
import a.a.al;
import a.a.aq;
import a.a.ar;
import a.a.as;
import a.a.av;
import a.a.aw;
import a.a.az;
import a.a.b;
import a.a.be;
import a.a.bz;
import a.a.cf;
import a.a.cj;
import a.a.ck;
import a.a.co;
import a.a.cz;
import a.a.db;
import a.a.ec;
import a.a.eh;
import a.a.eq;
import a.a.er;
import a.a.fh;
import a.a.ft;
import a.a.fy;
import a.a.s;
import a.a.t;
import a.a.u;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import com.appboy.f.c;
import com.appboy.f.h;
import com.appboy.f.i;
import com.appboy.f.j;
import java.io.File;
import java.io.FilenameFilter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;

public class a {
    private static volatile f A;
    private static volatile i B;
    private static volatile boolean C = false;
    private static volatile boolean D = false;
    private static volatile boolean E = false;
    private static volatile cj F;
    private static final String k = c.a(a.class);
    private static final Set<String> l = new HashSet(Arrays.asList(new String[]{"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", "BTC", "BTN", "BWP", "BYR", "BZD", "CAD", "CDF", "CHF", "CLF", "CLP", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EEK", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LTL", "LVL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRO", "MTL", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "STD", "SVC", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VEF", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XCD", "XDR", "XOF", "XPD", "XPF", "XPT", "YER", "ZAR", "ZMK", "ZMW", "ZWL"}));
    private static final Set<String> m = new HashSet(Collections.singletonList("calypso appcrawler"));
    private static final Set<String> n = new HashSet(Arrays.asList(new String[]{"android.permission.ACCESS_NETWORK_STATE", "android.permission.INTERNET"}));
    @SuppressLint({"StaticFieldLeak"})
    private static volatile a o = null;
    private static final Object z = new Object();
    @VisibleForTesting
    volatile b a;
    @VisibleForTesting
    volatile db b;
    volatile cf c;
    volatile er d;
    volatile ad e;
    volatile ck f;
    volatile ag g;
    @VisibleForTesting
    final com.appboy.a.a h;
    @VisibleForTesting
    final aq i;
    @VisibleForTesting
    final u j;
    private final Context p;
    private final fy q;
    private final as r;
    private volatile d s;
    private volatile ThreadPoolExecutor t;
    private final fh u;
    private final ab v;
    private final t w;
    private g x;
    private volatile boolean y = false;

    static /* synthetic */ void f(a aVar) {
        Object obj;
        Object obj2 = 1;
        Iterator it = n.iterator();
        while (true) {
            obj = obj2;
            if (!it.hasNext()) {
                break;
            }
            String str = (String) it.next();
            if (h.a(aVar.p, str)) {
                obj2 = obj;
            } else {
                c.g(k, "The Braze SDK requires the permission " + str + ". Check your app manifest.");
                obj2 = null;
            }
        }
        if (aVar.h.b().toString().equals("")) {
            c.g(k, "The Braze SDK requires a non-empty API key. Check your appboy.xml or AppboyConfig.");
            obj = null;
        }
        if (aVar.h.A() && aVar.h.c()) {
            c.g(k, "Both Firebase Cloud Messaging and Google Cloud Messaging automatic push registration are enabled. It is recommended to only have one automatic push registration active.");
            obj = null;
        }
        if (obj == null) {
            c.g(k, "The Braze SDK is not integrated correctly. Please visit https://www.braze.com/documentation/Android");
        }
    }

    public static a a(Context context) {
        if (o == null || o.y) {
            synchronized (a.class) {
                if (o == null || o.y) {
                    b(d(context).a());
                    a aVar = new a(context);
                    o = aVar;
                    return aVar;
                }
            }
        }
        return o;
    }

    @VisibleForTesting
    private a(final Context context) {
        long nanoTime = System.nanoTime();
        c.b(k, "Braze SDK Initializing");
        this.p = context.getApplicationContext();
        this.r = new as();
        c.a(this.r);
        String str = Build.MODEL;
        if (str != null && m.contains(str.toLowerCase(Locale.US))) {
            c.d(k, "Device build model matches a known crawler. Enabling mock network request mode. Device model: " + str);
            o();
        }
        this.x = new com.appboy.d.a(this.p);
        Executor threadPoolExecutor = new ThreadPoolExecutor(cz.a(), cz.b(), cz.c(), TimeUnit.SECONDS, cz.d(), new DiscardOldestPolicy());
        threadPoolExecutor.execute(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public final void run() {
                c.a();
            }
        });
        this.h = new com.appboy.a.a(this.p);
        if (!i.c(this.h.o())) {
            str = this.h.o();
            synchronized (z) {
                f anonymousClass15 = new f(this) {
                    final /* synthetic */ a b;

                    public final Uri a(Uri appboyEndpoint) {
                        return appboyEndpoint.buildUpon().encodedAuthority(str).build();
                    }
                };
                synchronized (z) {
                    A = anonymousClass15;
                }
            }
        }
        this.u = new fh(this.p);
        this.v = new ab(this.p);
        this.q = new fy(threadPoolExecutor, F);
        this.i = new ar(this.p, this.h);
        threadPoolExecutor.execute(new Runnable(this) {
            final /* synthetic */ a b;

            public final void run() {
                if (!this.b.h.A()) {
                    c.d(a.k, "Automatic Firebase Cloud Messaging registration not enabled in configuration. Braze will not register for Firebase Cloud Messaging.");
                } else if (ak.a(this.b.p, this.b.h)) {
                    c.d(a.k, "Firebase Cloud Messaging found. Setting up Firebase Cloud Messaging.");
                    new ak(context).a(this.b.h.B());
                } else {
                    c.g(a.k, "Firebase Cloud Messaging requirements not met. Braze will not register for Firebase Cloud Messaging.");
                }
                if (!this.b.h.c()) {
                    c.d(a.k, "Automatic GCM registration not enabled in configuration. Braze will not register for GCM.");
                } else if (al.a(this.b.p, this.b.h)) {
                    c.d(a.k, "Google Cloud Messaging found. Setting up Google Cloud Messaging");
                    al alVar = new al(this.b.p, this.b.i);
                    if (this.b.h.n() != null) {
                        alVar.a(this.b.h.n());
                    } else {
                        c.g(a.k, "GCM Sender Id not found, not registering with GCM Server");
                    }
                } else {
                    c.g(a.k, "GCM manifest requirements not met. Braze will not register for GCM.");
                }
                if (!this.b.h.d()) {
                    c.d(a.k, "Automatic ADM registration not enabled in configuration. Braze will not register for ADM.");
                } else if (aa.a(this.b.p)) {
                    c.d(a.k, "Amazon Device Messaging found. Setting up Amazon Device Messaging");
                    new aa(this.b.p, this.b.i).a();
                } else {
                    c.g(a.k, "ADM manifest requirements not met. Braze will not register for ADM.");
                }
            }
        });
        ThreadFactory sVar = new s("Appboy-User-Dependency-Thread");
        this.w = new t(this.q);
        sVar.a(this.w);
        this.j = new u(sVar);
        this.j.submit(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public final void run() {
                c.a(a.k, "Starting up a new user dependency manager");
                a.a(this.a, new db(this.a.p, this.a.u, this.a.h, this.a.q, this.a.v, this.a.i, a.C, a.D, this.a.r));
            }
        });
        threadPoolExecutor.execute(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public final void run() {
                a.f(this.a);
            }
        });
        c.b(k, "Appboy loaded in " + TimeUnit.MILLISECONDS.convert(System.nanoTime() - nanoTime, TimeUnit.NANOSECONDS) + " ms.");
    }

    public final void a(final Activity activity) {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a b;

                public final void run() {
                    try {
                        if (activity == null) {
                            c.f(a.k, "Cannot open session with null activity.");
                        } else {
                            this.b.g.a(activity);
                        }
                    } catch (Throwable e) {
                        c.d(a.k, "Failed to open session.", e);
                        this.b.a(e);
                    }
                }
            });
        }
    }

    public final void b(final Activity activity) {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a b;

                public final void run() {
                    try {
                        if (activity == null) {
                            c.f(a.k, "Cannot close session with null activity.");
                            return;
                        }
                        az b = this.b.g.b(activity);
                        if (b != null) {
                            c.d(a.k, "Closed session with ID: " + b.a());
                        }
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to close session.", e);
                        this.b.a(e);
                    }
                }
            });
        }
    }

    public final void a(final String replyToEmail, final String message, final boolean isReportingABug) {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a d;

                public final void run() {
                    try {
                        this.d.g.a(replyToEmail, message, isReportingABug);
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to submit feedback: " + message, e);
                        this.d.a(e);
                    }
                }
            });
        }
    }

    public final void a(final String eventNameInput, final com.appboy.e.b.a properties) {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a c;

                public final void run() {
                    String str = eventNameInput;
                    try {
                        if (j.a(str, this.c.f)) {
                            str = j.c(str);
                            av a = be.a(str, properties);
                            if (this.c.g.a(a)) {
                                this.c.d.a(new ec(str, properties, a));
                                return;
                            }
                            return;
                        }
                        c.f(a.k, "Log custom event input " + str + " was invalid. Not logging custom event to Appboy.");
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to log custom event: " + str, e);
                        this.c.a(e);
                    }
                }
            });
        }
    }

    public final void a(String productIdInput, String currencyCodeInput, BigDecimal price, int quantity, com.appboy.e.b.a properties) {
        if (!q()) {
            final String str = productIdInput;
            final String str2 = currencyCodeInput;
            final BigDecimal bigDecimal = price;
            final int i = quantity;
            final com.appboy.e.b.a aVar = properties;
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a f;

                public final void run() {
                    Throwable e;
                    String str = str;
                    String str2 = str2;
                    if (str2 == null) {
                        try {
                            c.f(a.k, "The currencyCode is null. Expected one of " + a.l + ". Not logging in-app purchase to Appboy.");
                        } catch (Throwable e2) {
                            Throwable th = e2;
                            str2 = str;
                            e = th;
                        }
                    } else {
                        str2 = str2.trim().toUpperCase(Locale.US);
                        if (j.a(str, str2, bigDecimal, i, this.f.f, a.l)) {
                            String c = j.c(str);
                            try {
                                av a = be.a(c, str2, bigDecimal, i, aVar);
                                if (this.f.g.a(a)) {
                                    this.f.d.a(new eh(c, aVar, a));
                                    return;
                                }
                                return;
                            } catch (Exception e3) {
                                e = e3;
                                str2 = c;
                                c.c(a.k, "Failed to log purchase event of " + str2, e);
                                this.f.a(e);
                                return;
                            }
                        }
                        c.f(a.k, "Log purchase input was invalid. Not logging in-app purchase to Appboy.");
                    }
                }
            });
        }
    }

    public final void a(final String campaignId) {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a b;

                public final void run() {
                    try {
                        if (i.c(campaignId)) {
                            c.f(a.k, "Campaign ID cannot be null or blank. Not logging push notification opened.");
                        } else {
                            this.b.g.a(be.b(campaignId));
                        }
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to log opened push.", e);
                        this.b.a(e);
                    }
                }
            });
        }
    }

    public final void a(final Intent intent) {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a b;

                public final void run() {
                    try {
                        String stringExtra = intent.getStringExtra("cid");
                        if (i.c(stringExtra)) {
                            c.d(a.k, "No campaign Id associated with this notification. Not logging push click to Appboy.");
                        } else {
                            c.d(a.k, "Logging push click to Appboy. Campaign Id: " + stringExtra);
                            this.b.a(stringExtra);
                        }
                        if (intent.hasExtra("ab_push_fetch_test_triggers_key") && intent.getStringExtra("ab_push_fetch_test_triggers_key").equals("true")) {
                            c.d(a.k, "Push contained key for fetching test triggers, fetching triggers.");
                            this.b.g.a(new a.a.bi.a().b());
                        }
                    } catch (Throwable e) {
                        c.c(a.k, "Error logging push notification", e);
                    }
                }
            });
        }
    }

    public final void b(final String campaignId) {
        this.j.submit(new Runnable(this) {
            final /* synthetic */ a b;

            public final void run() {
                try {
                    if (i.c(campaignId)) {
                        c.f(a.k, "Campaign ID cannot be null or blank for push delivery event.");
                    } else {
                        this.b.g.a(be.f(campaignId));
                    }
                } catch (Throwable e) {
                    c.c(a.k, "Failed to log push delivery event.", e);
                    this.b.a(e);
                }
            }
        });
    }

    public final void a(final String campaignId, final String actionId) {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a c;

                public final void run() {
                    try {
                        if (i.c(campaignId)) {
                            c.f(a.k, "Campaign ID cannot be null or blank. Not logging push notification action clicked.");
                        } else if (i.c(actionId)) {
                            c.f(a.k, "Action ID cannot be null or blank");
                        } else {
                            this.c.g.a(be.b(campaignId, actionId));
                        }
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to log push notification action clicked.", e);
                        this.c.a(e);
                    }
                }
            });
        }
    }

    public final void b(final String campaignId, final String pageId) {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a c;

                public final void run() {
                    try {
                        if (j.a(campaignId, pageId)) {
                            this.c.g.a(be.a(campaignId, pageId));
                        } else {
                            c.f(a.k, "Push story page click input was invalid. Not logging in-app purchase to Appboy.");
                        }
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to log push story page clicked for page id: " + pageId + " cid: " + campaignId, e);
                        this.c.a(e);
                    }
                }
            });
        }
    }

    public final void a() {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void run() {
                    try {
                        this.a.g.a(be.g());
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to log that the feed was displayed.", e);
                        this.a.a(e);
                    }
                }
            });
        }
    }

    public final void b() {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void run() {
                    try {
                        this.a.g.a(be.i());
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to log that feedback was displayed.", e);
                        this.a.a(e);
                    }
                }
            });
        }
    }

    public final void c() {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void run() {
                    try {
                        this.a.q.a(this.a.c.a(), com.appboy.c.a.class);
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to retrieve and publish feed from offline cache.", e);
                    }
                }
            });
        }
    }

    public final void d() {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void run() {
                    try {
                        this.a.g.a(new a.a.bi.a().a());
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to request refresh of feed.", e);
                        this.a.a(e);
                    }
                }
            });
        }
    }

    public final void e() {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void run() {
                    try {
                        this.a.g.c();
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to request data flush.", e);
                        this.a.a(e);
                    }
                }
            });
        }
    }

    public final void a(com.appboy.c.b<com.appboy.c.c> subscriber) {
        try {
            this.q.a((com.appboy.c.b) subscriber, com.appboy.c.c.class);
        } catch (Throwable e) {
            c.c(k, "Failed to add subscriber to new in-app messages.", e);
            a(e);
        }
    }

    public final void b(com.appboy.c.b<com.appboy.c.a> subscriber) {
        try {
            this.q.a((com.appboy.c.b) subscriber, com.appboy.c.a.class);
        } catch (Throwable e) {
            c.c(k, "Failed to add subscriber for feed updates.", e);
            a(e);
        }
    }

    public final <T> void a(com.appboy.c.b<T> subscriber, Class<T> eventClass) {
        try {
            this.q.b((com.appboy.c.b) subscriber, (Class) eventClass);
        } catch (Throwable e) {
            c.c(k, "Failed to remove " + eventClass.getName() + " subscriber.", e);
            a(e);
        }
    }

    public final void c(final String userId) {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a b;

                public final void run() {
                    try {
                        if (i.b(userId)) {
                            c.g(a.k, "ArgumentException: userId passed to changeUser was null or empty. The current user will remain the active user.");
                            return;
                        }
                        String a = this.b.s.a();
                        if (a.equals(userId)) {
                            c.d(a.k, "Received request to change current user " + userId + " to the same user id. Doing nothing.");
                            return;
                        }
                        if (a.equals("")) {
                            c.d(a.k, "Changing anonymous user to " + userId);
                            this.b.u.b(userId);
                            this.b.s.i(userId);
                        } else {
                            c.d(a.k, "Changing current user " + a + " to new user " + userId + ".");
                            this.b.q.a(new com.appboy.c.a(new ArrayList(), userId, false, co.a()), com.appboy.c.a.class);
                        }
                        this.b.g.b();
                        this.b.u.a(userId);
                        db dbVar = this.b.b;
                        a.a(this.b, new db(this.b.p, this.b.u, this.b.h, this.b.q, this.b.v, this.b.i, a.C, a.D, this.b.r));
                        this.b.b.g().d();
                        this.b.g.a();
                        this.b.g.a(new a.a.bi.a().a());
                        dbVar.n();
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to set external id to: " + userId, e);
                        this.b.a(e);
                    }
                }
            });
        }
    }

    public final d f() {
        try {
            return (d) this.j.submit(new Callable<d>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final /* synthetic */ Object call() {
                    return this.a.s;
                }
            }).get();
        } catch (Throwable e) {
            c.c(k, "Failed to retrieve the current user.", e);
            a(e);
            return null;
        }
    }

    public final void d(String registrationId) {
        if (!q()) {
            try {
                if (i.c(registrationId)) {
                    c.f(k, "Push registration ID must not be null or blank. Not registering for push messages from Appboy.");
                    return;
                }
                c.d(k, "Push token " + registrationId + " registered and immediately being flushed.");
                this.i.a(registrationId);
                e();
            } catch (Throwable e) {
                c.c(k, "Failed to set the registration ID.", e);
                a(e);
            }
        }
    }

    public final g g() {
        if (this.x == null) {
            c.b(k, "The Image Loader was null. Creating a new Image Loader and returning it.");
            this.x = new com.appboy.d.a(this.p);
        }
        return this.x;
    }

    private static void b(final boolean isOffline) {
        c.d(k, "Appboy outbound network requests are now " + (isOffline ? "disabled" : "enabled"));
        synchronized (a.class) {
            D = isOffline;
            if (o != null) {
                a aVar = o;
                aVar.j.submit(new Runnable(aVar) {
                    final /* synthetic */ a b;

                    public final void run() {
                        this.b.g.a(isOffline);
                        this.b.b.b().a(isOffline);
                        if (this.b.x != null) {
                            c.b(a.k, "Setting the image loader deny network downloads to " + isOffline);
                            this.b.x.a(isOffline);
                        }
                    }
                });
            }
        }
    }

    public static boolean h() {
        return D;
    }

    public static Uri a(Uri appboyEndpoint) {
        synchronized (z) {
            if (A != null) {
                try {
                    Uri a = A.a(appboyEndpoint);
                    if (a != null) {
                        return a;
                    }
                } catch (Exception e) {
                    c.g(k, "Caught exception trying to get a Braze API endpoint from the AppboyEndpointProvider. Using the original URI");
                }
            }
        }
        return appboyEndpoint;
    }

    public static i i() {
        return B;
    }

    private static boolean o() {
        if (o == null) {
            synchronized (a.class) {
                if (o == null) {
                    if (C) {
                        c.d(k, "Appboy network requests already being mocked. Note that events dispatched in this mode are dropped.");
                        return true;
                    }
                    c.d(k, "Appboy network requests will be mocked. Events dispatched in this mode will be dropped.");
                    C = true;
                    return true;
                }
            }
        }
        c.g(k, "Attempt to enable mocking Braze network requests had no effect since getInstance() has already been called.");
        return false;
    }

    public static void b(Context context) {
        d(context).b();
        p();
        c.f(k, "Disabling all network requests");
        b(true);
    }

    public static void c(Context context) {
        p();
        try {
            eq.a(context);
            com.appboy.d.a.a(context);
        } catch (Throwable e) {
            c.c(k, "Failed to delete data from the internal storage cache.", e);
        }
        try {
            bz.a(context);
        } catch (Throwable e2) {
            c.c(k, "Failed to delete Braze database files for the Braze SDK.", e2);
        }
        try {
            File file = new File(context.getApplicationInfo().dataDir, "shared_prefs");
            if (file.exists() && file.isDirectory()) {
                for (File file2 : file.listFiles(new FilenameFilter() {
                    public final boolean accept(File directory, String name) {
                        return name.startsWith("com.appboy");
                    }
                })) {
                    c.a(k, "Deleting shared prefs file at: " + file2.getAbsolutePath());
                    com.appboy.f.a.a(file2);
                }
            }
        } catch (Throwable e22) {
            c.c(k, "Failed to delete shared preference data for the Braze SDK.", e22);
        }
    }

    final void a(final aw awVar) {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a b;

                public final void run() {
                    try {
                        if (this.b.e != null) {
                            this.b.e.a(awVar);
                        } else {
                            c.b(a.k, "Geofence manager was null. Not requesting geofence refresh.");
                        }
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to request geofence refresh.", e);
                        this.b.a(e);
                    }
                }
            });
        }
    }

    final void a(final boolean z) {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a b;

                public final void run() {
                    try {
                        if (this.b.e != null) {
                            this.b.e.a(z);
                        } else {
                            c.b(a.k, "Geofence manager was null. Not requesting geofence refresh.");
                        }
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to request geofence refresh with rate limit ignore: " + z, e);
                        this.b.a(e);
                    }
                }
            });
        }
    }

    final void a(final String str, final ft ftVar) {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a c;

                public final void run() {
                    try {
                        if (this.c.e != null) {
                            this.c.e.a(str, ftVar);
                        } else {
                            c.b(a.k, "Geofence manager was null. Not posting geofence report");
                        }
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to post geofence report.", e);
                        this.c.a(e);
                    }
                }
            });
        }
    }

    final void j() {
        if (!q()) {
            this.j.submit(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void run() {
                    try {
                        if (this.a.e != null) {
                            this.a.e.a();
                        } else {
                            c.b(a.k, "Geofence manager was null. Not initializing geofences.");
                        }
                    } catch (Throwable e) {
                        c.c(a.k, "Failed to initialize geofences with the geofence manager.", e);
                        this.a.a(e);
                    }
                }
            });
        }
    }

    private void a(Throwable th) {
        try {
            this.a.a(th, Throwable.class);
        } catch (Throwable e) {
            c.d(k, "Failed to log throwable.", e);
        }
    }

    private static void p() {
        try {
            c.d(k, "Shutting down all queued work on the Braze SDK");
            synchronized (a.class) {
                if (o != null) {
                    if (o.j != null) {
                        c.b(k, "Shutting down the user dependency executor");
                        o.j.shutdownNow();
                    }
                    db dbVar = o.b;
                    if (dbVar != null) {
                        if (dbVar.b() != null) {
                            dbVar.b().a(true);
                        }
                        if (dbVar.k() != null) {
                            dbVar.k().a();
                        }
                        if (dbVar.m() != null) {
                            dbVar.m().b();
                        }
                        if (dbVar.j() != null) {
                            dbVar.j().a();
                        }
                    }
                    o.y = true;
                }
            }
        } catch (Throwable e) {
            c.c(k, "Failed to shutdown queued work on the Braze SDK.", e);
        }
    }

    private static boolean q() {
        if (F == null) {
            c.b(k, "SDK enablement provider was null. Returning SDK as enabled.");
            return false;
        }
        boolean a = F.a();
        if (!a) {
            return a;
        }
        c.f(k, "SDK is disabled. Not performing action on SDK.");
        return a;
    }

    private static cj d(Context context) {
        if (F == null) {
            F = new cj(context);
        }
        return F;
    }

    static /* synthetic */ void a(a aVar, db dbVar) {
        aVar.b = dbVar;
        aVar.g = dbVar.d();
        aVar.f = dbVar.a();
        aVar.d = dbVar.l();
        aVar.e = dbVar.m();
        aVar.s = new d(dbVar.g(), aVar.g, aVar.u.a(), dbVar.j(), aVar.f);
        dbVar.c().a(dbVar.f());
        dbVar.e().a();
        aVar.a = dbVar.f();
        aVar.w.a(aVar.a);
        aVar.t = dbVar.h();
        aVar.c = dbVar.i();
        aVar.d = dbVar.l();
        dbVar.k().a(aVar.t, dbVar.e());
        aVar.r.a(aVar.g);
        aVar.r.a(aVar.f.o());
    }
}
