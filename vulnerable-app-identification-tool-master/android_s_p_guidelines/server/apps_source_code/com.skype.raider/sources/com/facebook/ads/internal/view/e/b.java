package com.facebook.ads.internal.view.e;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.c.f;
import com.facebook.ads.internal.view.component.e;
import com.facebook.ads.internal.view.component.h;
import com.facebook.ads.internal.view.f.b.z;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class b {
    private static final String a = b.class.getSimpleName();
    private static final int b = ((int) (4.0f * u.b));
    private static final int c = ((int) (72.0f * u.b));
    private static final int d = ((int) (8.0f * u.b));
    private final Context e;
    private final c f;
    private final k g;
    private final String h;
    private final d i;
    private final com.facebook.ads.internal.s.a j;
    private final s k;
    private Executor l = AsyncTask.THREAD_POOL_EXECUTOR;
    @Nullable
    private com.facebook.ads.internal.view.a.a m;
    @Nullable
    private com.facebook.ads.internal.view.b.a n;
    @Nullable
    private com.facebook.ads.internal.view.b.a.b o;

    public enum a {
        SCREENSHOTS,
        MARKUP,
        INFO
    }

    public b(Context context, c cVar, k kVar, com.facebook.ads.internal.view.a.a aVar, com.facebook.ads.internal.s.a aVar2, s sVar) {
        this.e = context;
        this.f = cVar;
        this.g = kVar;
        this.m = aVar;
        this.h = com.facebook.ads.internal.j.c.a(this.g.f().b());
        this.i = this.g.d().a();
        this.j = aVar2;
        this.k = sVar;
    }

    public final boolean a() {
        return b() == a.MARKUP;
    }

    public final a b() {
        return !this.g.f().d().isEmpty() ? a.SCREENSHOTS : !TextUtils.isEmpty(this.h) ? a.MARKUP : a.INFO;
    }

    public final Pair<a, View> c() {
        a b = b();
        switch (b) {
            case MARKUP:
                this.o = new com.facebook.ads.internal.view.b.a.c(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public final void a() {
                        if (this.a.n != null && !TextUtils.isEmpty(this.a.g.f().c())) {
                            this.a.n.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public final void run() {
                                    if (this.a.a.n == null || this.a.a.n.c()) {
                                        b.a;
                                    } else {
                                        this.a.a.n.loadUrl("javascript:" + this.a.a.g.f().c());
                                    }
                                }
                            });
                        }
                    }

                    public final void a(String str, Map<String, String> map) {
                        Uri parse = Uri.parse(str);
                        if ("fbad".equals(parse.getScheme()) && parse.getAuthority().equals("close")) {
                            b.a(this.a);
                            return;
                        }
                        if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority()) && this.a.m != null) {
                            this.a.m.a(z.REWARDED_VIDEO_AD_CLICK.a());
                        }
                        com.facebook.ads.internal.a.b a = com.facebook.ads.internal.a.c.a(this.a.e, this.a.f, this.a.g.g(), parse, map);
                        if (a != null) {
                            try {
                                a.b();
                            } catch (Exception e) {
                                b.a;
                            }
                        }
                    }
                };
                this.n = new com.facebook.ads.internal.view.b.a(this.e, new WeakReference(this.o), 1);
                this.n.loadDataWithBaseURL(com.facebook.ads.internal.q.c.b.a(), this.h, "text/html", "utf-8", null);
                return new Pair(b, this.n);
            case SCREENSHOTS:
                RecyclerView recyclerView = new RecyclerView(this.e);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.e, 0, false));
                recyclerView.setAdapter(new c(this.g.f().d(), b));
                return new Pair(b, recyclerView);
            default:
                View hVar = new h(this.e, this.i, true, false, false);
                hVar.a(this.g.b().a(), this.g.b().c(), false, true);
                hVar.setAlignment(17);
                View aVar = new com.facebook.ads.internal.view.component.a(this.e, true, false, z.REWARDED_VIDEO_AD_CLICK.a(), this.i, this.f, this.m, this.j, this.k);
                aVar.a(this.g.c(), this.g.g(), new HashMap());
                ImageView eVar = new e(this.e);
                u.a((View) eVar, 0);
                eVar.setRadius(50);
                new com.facebook.ads.internal.view.b.d(eVar).a().a(this.g.a().b());
                LinearLayout linearLayout = new LinearLayout(this.e);
                linearLayout.setOrientation(1);
                linearLayout.setGravity(17);
                linearLayout.addView(eVar, new LayoutParams(c, c));
                ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
                layoutParams.setMargins(0, d, 0, d);
                linearLayout.addView(hVar, layoutParams);
                linearLayout.addView(aVar, layoutParams);
                return new Pair(b, linearLayout);
        }
    }

    public final void d() {
        if (!TextUtils.isEmpty(this.g.f().a())) {
            com.facebook.ads.internal.q.c.e eVar = new com.facebook.ads.internal.q.c.e(this.e, new HashMap());
            eVar.a(new com.facebook.ads.internal.q.c.e.a(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public final void a() {
                    if (this.a.m != null) {
                        this.a.m.a(z.REWARD_SERVER_FAILED.a());
                    }
                }

                public final void a(f fVar) {
                    if (this.a.m != null) {
                        if (fVar == null || !fVar.a()) {
                            this.a.m.a(z.REWARD_SERVER_FAILED.a());
                        } else {
                            this.a.m.a(z.REWARD_SERVER_SUCCESS.a());
                        }
                    }
                }
            });
            eVar.executeOnExecutor(this.l, new String[]{r0});
        }
    }

    public final void e() {
        if (this.n != null) {
            this.n.destroy();
            this.n = null;
            this.o = null;
        }
    }

    static /* synthetic */ void a(b bVar) {
        if (bVar.m != null) {
            bVar.m.a(z.REWARDED_VIDEO_END_ACTIVITY.a());
        }
    }
}
