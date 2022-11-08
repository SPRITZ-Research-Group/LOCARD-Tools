package com.facebook;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.internal.bj;
import com.facebook.internal.bn;
import defpackage.lj;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public final class d {
    private static volatile d a;
    private final lj b;
    private final b c;
    private AccessToken d;
    private AtomicBoolean e = new AtomicBoolean(false);
    private Date f = new Date(0);

    private d(lj ljVar, b bVar) {
        bn.a((Object) ljVar, "localBroadcastManager");
        bn.a((Object) bVar, "accessTokenCache");
        this.b = ljVar;
        this.c = bVar;
    }

    static d a() {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d(lj.a(s.f()), new b());
                }
            }
        }
        return a;
    }

    final AccessToken b() {
        return this.d;
    }

    final boolean c() {
        AccessToken a = this.c.a();
        if (a == null) {
            return false;
        }
        a(a, false);
        return true;
    }

    final void a(AccessToken accessToken) {
        a(accessToken, true);
    }

    private void a(AccessToken accessToken, boolean z) {
        AccessToken accessToken2 = this.d;
        this.d = accessToken;
        this.e.set(false);
        this.f = new Date(0);
        if (z) {
            if (accessToken != null) {
                this.c.a(accessToken);
            } else {
                this.c.b();
                bj.b(s.f());
            }
        }
        if (!bj.a((Object) accessToken2, (Object) accessToken)) {
            a(accessToken2, accessToken);
            f();
        }
    }

    final void d() {
        a(this.d, this.d);
    }

    private void a(AccessToken accessToken, AccessToken accessToken2) {
        Intent intent = new Intent(s.f(), CurrentAccessTokenExpirationBroadcastReceiver.class);
        intent.setAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN", accessToken);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN", accessToken2);
        this.b.a(intent);
    }

    private static void f() {
        Context f = s.f();
        AccessToken a = AccessToken.a();
        AlarmManager alarmManager = (AlarmManager) f.getSystemService("alarm");
        if (AccessToken.b() && a.e() != null && alarmManager != null) {
            Intent intent = new Intent(f, CurrentAccessTokenExpirationBroadcastReceiver.class);
            intent.setAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
            alarmManager.set(1, a.e().getTime(), PendingIntent.getBroadcast(f, 0, intent, 0));
        }
    }

    private static GraphRequest a(AccessToken accessToken, x xVar) {
        return new GraphRequest(accessToken, "me/permissions", new Bundle(), ag.GET, xVar);
    }

    private static GraphRequest b(AccessToken accessToken, x xVar) {
        Bundle bundle = new Bundle();
        bundle.putString("grant_type", "fb_extend_sso_token");
        return new GraphRequest(accessToken, "oauth/access_token", bundle, ag.GET, xVar);
    }

    private void a(a aVar) {
        final AccessToken accessToken = this.d;
        n nVar;
        if (accessToken == null) {
            if (aVar != null) {
                nVar = new n("No current access token to refresh");
            }
        } else if (this.e.compareAndSet(false, true)) {
            this.f = new Date();
            final Set hashSet = new HashSet();
            final Set hashSet2 = new HashSet();
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final e eVar = new e();
            ac acVar = new ac(a(accessToken, new x(this) {
                final /* synthetic */ d d;

                public final void a(af afVar) {
                    JSONObject b = afVar.b();
                    if (b != null) {
                        JSONArray optJSONArray = b.optJSONArray("data");
                        if (optJSONArray != null) {
                            atomicBoolean.set(true);
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                if (optJSONObject != null) {
                                    String optString = optJSONObject.optString("permission");
                                    String optString2 = optJSONObject.optString("status");
                                    if (!(bj.a(optString) || bj.a(optString2))) {
                                        optString2 = optString2.toLowerCase(Locale.US);
                                        if (optString2.equals("granted")) {
                                            hashSet.add(optString);
                                        } else if (optString2.equals("declined")) {
                                            hashSet2.add(optString);
                                        } else {
                                            Log.w("AccessTokenManager", "Unexpected status: ".concat(String.valueOf(optString2)));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }), b(accessToken, new x(this) {
                final /* synthetic */ d b;

                public final void a(af afVar) {
                    JSONObject b = afVar.b();
                    if (b != null) {
                        eVar.a = b.optString("access_token");
                        eVar.b = b.optInt("expires_at");
                    }
                }
            }));
            final a aVar2 = aVar;
            acVar.a(new ad(this) {
                final /* synthetic */ d g;

                public final void a() {
                    try {
                        n nVar;
                        if (d.a().b() == null || d.a().b().k() != accessToken.k()) {
                            if (aVar2 != null) {
                                nVar = new n("No current access token to refresh");
                            }
                            this.g.e.set(false);
                        } else if (!atomicBoolean.get() && eVar.a == null && eVar.b == 0) {
                            if (aVar2 != null) {
                                nVar = new n("Failed to refresh access token");
                            }
                            this.g.e.set(false);
                        } else {
                            String str;
                            Set set;
                            Date date;
                            if (eVar.a != null) {
                                str = eVar.a;
                            } else {
                                str = accessToken.d();
                            }
                            String str2 = str;
                            String j = accessToken.j();
                            String k = accessToken.k();
                            if (atomicBoolean.get()) {
                                set = hashSet;
                            } else {
                                set = accessToken.f();
                            }
                            Set set2 = set;
                            if (atomicBoolean.get()) {
                                set = hashSet2;
                            } else {
                                set = accessToken.g();
                            }
                            Set set3 = set;
                            f h = accessToken.h();
                            if (eVar.b != 0) {
                                date = new Date(((long) eVar.b) * 1000);
                            } else {
                                date = accessToken.e();
                            }
                            d.a().a(new AccessToken(str2, j, k, set2, set3, h, date, new Date()));
                            this.g.e.set(false);
                            if (aVar2 == null) {
                            }
                        }
                    } catch (Throwable th) {
                        this.g.e.set(false);
                    }
                }
            });
            GraphRequest.b(acVar);
        } else {
            if (aVar != null) {
                nVar = new n("Refresh already in progress");
            }
        }
    }

    final void e() {
        Object obj;
        if (this.d != null) {
            Long valueOf = Long.valueOf(new Date().getTime());
            if (this.d.h().a() && valueOf.longValue() - this.f.getTime() > 3600000 && valueOf.longValue() - this.d.i().getTime() > 86400000) {
                obj = 1;
                if (obj != null) {
                    if (Looper.getMainLooper().equals(Looper.myLooper())) {
                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                            final /* synthetic */ a a = null;
                            final /* synthetic */ d b;

                            {
                                this.b = r1;
                            }

                            public final void run() {
                                this.b.a(this.a);
                            }
                        });
                    } else {
                        a(null);
                    }
                }
                return;
            }
        }
        obj = null;
        if (obj != null) {
            return;
        }
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            new Handler(Looper.getMainLooper()).post(/* anonymous class already generated */);
        } else {
            a(null);
        }
    }
}
