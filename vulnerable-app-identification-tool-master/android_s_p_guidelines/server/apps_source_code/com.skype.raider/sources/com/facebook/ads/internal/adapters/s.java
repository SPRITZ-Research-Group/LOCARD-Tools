package com.facebook.ads.internal.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.WindowManager;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.d;
import com.facebook.ads.internal.a.e;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.d.b;
import com.facebook.ads.internal.m.c;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONObject;

public final class s extends d {
    private static final ConcurrentMap<String, com.facebook.ads.internal.view.a> a = new ConcurrentHashMap();
    private final String b = UUID.randomUUID().toString();
    private String c;
    private long d;
    private Context e;
    private t f;
    private e g;
    private boolean h = false;
    private y i;
    private a j = a.UNSPECIFIED;
    private g k;
    private com.facebook.ads.internal.t.b.a l;
    private boolean m;

    public enum a {
        UNSPECIFIED,
        VERTICAL,
        HORIZONTAL;

        public static a a(int i) {
            return i == 0 ? UNSPECIFIED : i == 2 ? HORIZONTAL : VERTICAL;
        }
    }

    public static com.facebook.ads.internal.view.a a(String str) {
        return (com.facebook.ads.internal.view.a) a.get(str);
    }

    public static void a(com.facebook.ads.internal.view.a aVar) {
        for (Entry entry : a.entrySet()) {
            if (entry.getValue() == aVar) {
                a.remove(entry.getKey());
            }
        }
    }

    public final void a(Context context, e eVar, Map<String, Object> map, c cVar, final EnumSet<d> enumSet) {
        this.e = context;
        this.g = eVar;
        this.c = (String) map.get("placementId");
        this.d = ((Long) map.get("requestTime")).longValue();
        JSONObject jSONObject = (JSONObject) map.get("data");
        com.facebook.ads.internal.h.d dVar = (com.facebook.ads.internal.h.d) map.get("definition");
        if (jSONObject.has("markup")) {
            this.l = com.facebook.ads.internal.t.b.a.INTERSTITIAL_WEB_VIEW;
            this.i = y.a(jSONObject);
            if (e.a(context, this.i, cVar)) {
                eVar.a(this, com.facebook.ads.a.b);
                return;
            }
            this.f = new t(context, this.b, this, this.g);
            this.f.a();
            Map f = this.i.f();
            if (f.containsKey("orientation")) {
                this.j = a.a(Integer.parseInt((String) f.get("orientation")));
            }
            this.h = true;
            if (this.g != null) {
                this.g.a((d) this);
            }
        } else if (jSONObject.has("video")) {
            this.l = com.facebook.ads.internal.t.b.a.INTERSTITIAL_OLD_NATIVE_VIDEO;
            this.f = new t(context, this.b, this, this.g);
            this.f.a();
            final u uVar = new u();
            uVar.a(context, new com.facebook.ads.a.a(this) {
                final /* synthetic */ s b;

                public final void a() {
                    this.b.j = uVar.j();
                    s.a.put(this.b.b, uVar);
                }

                public final void a(com.facebook.ads.a aVar) {
                    uVar.k();
                    this.b.g.a(this.b, aVar);
                }

                public final void a(ab abVar) {
                    this.b.h = true;
                    if (this.b.g != null) {
                        this.b.g.a(this.b);
                    }
                }

                public final void b() {
                    this.b.g.a("");
                }

                public final void c() {
                    this.b.g.a();
                }

                public final void d() {
                }
            }, map, cVar, enumSet);
        } else {
            this.k = g.a(jSONObject, context);
            if (dVar != null) {
                this.k.a(dVar.k());
            }
            if (this.k.d().size() == 0) {
                this.g.a(this, com.facebook.ads.a.b);
            }
            this.f = new t(context, this.b, this, this.g);
            this.f.a();
            b bVar;
            com.facebook.ads.internal.adapters.a.b c;
            if (jSONObject.has("carousel")) {
                this.l = com.facebook.ads.internal.t.b.a.INTERSTITIAL_NATIVE_CAROUSEL;
                bVar = new b(context);
                bVar.a(this.k.a().b(), -1, -1);
                List<h> d = this.k.d();
                boolean contains = enumSet.contains(d.VIDEO);
                for (h hVar : d) {
                    bVar.a(hVar.c().f(), hVar.c().h(), hVar.c().g());
                    if (contains && !TextUtils.isEmpty(hVar.c().a())) {
                        bVar.a(hVar.c().f());
                    }
                }
                bVar.a(new com.facebook.ads.internal.d.a(this) {
                    final /* synthetic */ s b;

                    private void a(boolean z) {
                        boolean z2 = true;
                        boolean z3 = !enumSet.contains(d.NONE);
                        s sVar = this.b;
                        if (!(z && z3)) {
                            z2 = false;
                        }
                        sVar.m = z2;
                        this.b.h = true;
                        this.b.g.a(this.b);
                    }

                    public final void a() {
                        a(true);
                    }

                    public final void b() {
                        a(false);
                    }
                });
            } else if (jSONObject.has("video_url")) {
                this.l = com.facebook.ads.internal.t.b.a.INTERSTITIAL_NATIVE_VIDEO;
                bVar = new b(context);
                c = ((h) this.k.d().get(0)).c();
                bVar.a(c.f(), c.h(), c.g());
                bVar.a(this.k.a().b(), -1, -1);
                if (enumSet.contains(d.VIDEO)) {
                    bVar.a(c.a());
                }
                bVar.a(new com.facebook.ads.internal.d.a(this) {
                    final /* synthetic */ s b;

                    private void a(boolean z) {
                        this.b.m = z;
                        this.b.h = true;
                        this.b.g.a(this.b);
                    }

                    public final void a() {
                        a(enumSet.contains(d.VIDEO));
                    }

                    public final void b() {
                        a(false);
                    }
                });
            } else {
                this.l = com.facebook.ads.internal.t.b.a.INTERSTITIAL_NATIVE_IMAGE;
                bVar = new b(context);
                c = ((h) this.k.d().get(0)).c();
                bVar.a(c.f(), c.h(), c.g());
                bVar.a(this.k.a().b(), -1, -1);
                bVar.a(new com.facebook.ads.internal.d.a(this) {
                    final /* synthetic */ s a;

                    {
                        this.a = r1;
                    }

                    private void c() {
                        this.a.h = true;
                        this.a.g.a(this.a);
                    }

                    public final void a() {
                        c();
                    }

                    public final void b() {
                        c();
                    }
                });
            }
        }
    }

    public final boolean a() {
        if (this.h) {
            Intent intent = new Intent(this.e, AudienceNetworkActivity.class);
            String str = "predefinedOrientationKey";
            int rotation = ((WindowManager) this.e.getSystemService("window")).getDefaultDisplay().getRotation();
            if (this.j != a.UNSPECIFIED) {
                if (this.j != a.HORIZONTAL) {
                    switch (rotation) {
                        case 2:
                            rotation = 9;
                            break;
                        default:
                            rotation = 1;
                            break;
                    }
                }
                switch (rotation) {
                    case 2:
                    case 3:
                        rotation = 8;
                        break;
                    default:
                        rotation = 0;
                        break;
                }
            }
            rotation = -1;
            intent.putExtra(str, rotation);
            intent.putExtra("uniqueId", this.b);
            intent.putExtra("placementId", this.c);
            intent.putExtra("requestTime", this.d);
            intent.putExtra("viewType", this.l);
            intent.putExtra("useCache", this.m);
            if (this.k != null) {
                intent.putExtra("ad_data_bundle", this.k);
            } else if (this.i != null) {
                this.i.a(intent);
            }
            intent.addFlags(ErrorDialogData.BINDER_CRASH);
            try {
                this.e.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                intent.setClass(this.e, InterstitialAdActivity.class);
                this.e.startActivity(intent);
            }
            return true;
        } else if (this.g == null) {
            return false;
        } else {
            this.g.a(this, com.facebook.ads.a.e);
            return false;
        }
    }

    public final void e() {
        if (this.f != null) {
            this.f.b();
        }
    }
}
