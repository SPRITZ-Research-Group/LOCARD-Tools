package com.facebook.ads.internal.view.f;

import android.content.Context;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class c {
    private final String a;
    private boolean b;
    private final Context c;
    private final com.facebook.ads.internal.m.c d;
    private final a e;
    private final com.facebook.ads.internal.b.a f;
    private int g;
    private int h;
    private final e i;
    @Nullable
    private final Map<String, String> j;

    public interface a {
        int d();

        long e();

        boolean getGlobalVisibleRect(Rect rect);

        int getMeasuredHeight();

        int getMeasuredWidth();

        com.facebook.ads.internal.view.f.a.a h();

        boolean k();

        float l();

        boolean m();
    }

    protected enum b {
        PLAY(0),
        SKIP(1),
        TIME(2),
        MRC(3),
        PAUSE(4),
        RESUME(5),
        MUTE(6),
        UNMUTE(7),
        VIEWABLE_IMPRESSION(10);
        
        public final int j;

        private b(int i) {
            this.j = i;
        }
    }

    public c(Context context, com.facebook.ads.internal.m.c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str) {
        this(context, cVar, aVar, list, str, (byte) 0);
    }

    private c(Context context, com.facebook.ads.internal.m.c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str, byte b) {
        this(context, cVar, aVar, list, str, null, null);
    }

    public c(Context context, com.facebook.ads.internal.m.c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str, @Nullable Bundle bundle, @Nullable Map<String, String> map) {
        this.b = true;
        this.g = 0;
        this.h = 0;
        this.c = context;
        this.d = cVar;
        this.e = aVar;
        this.a = str;
        this.j = map;
        list.add(new com.facebook.ads.internal.b.b(this) {
            final /* synthetic */ c a;

            {
                this.a = r8;
                com.facebook.ads.internal.b.b bVar = this;
            }

            protected final void a(boolean z) {
                if (z) {
                    this.a.d.d(this.a.a, this.a.a(b.MRC));
                }
            }
        });
        list.add(new com.facebook.ads.internal.b.b(this) {
            final /* synthetic */ c a;

            {
                this.a = r8;
                com.facebook.ads.internal.b.b bVar = this;
            }

            protected final void a(boolean z) {
                if (z) {
                    this.a.d.d(this.a.a, this.a.a(b.VIEWABLE_IMPRESSION));
                }
            }
        });
        if (bundle != null) {
            this.f = new com.facebook.ads.internal.b.a((View) aVar, list, bundle.getBundle("adQualityManager"));
            this.g = bundle.getInt("lastProgressTimeMS");
            this.h = bundle.getInt("lastBoundaryTimeMS");
        } else {
            this.f = new com.facebook.ads.internal.b.a((View) aVar, list);
        }
        this.i = new e(new Handler(), this);
    }

    private float a() {
        float f;
        AudioManager audioManager = (AudioManager) this.c.getSystemService("audio");
        if (audioManager != null) {
            int streamVolume = audioManager.getStreamVolume(3);
            int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            if (streamMaxVolume > 0) {
                f = (((float) streamVolume) * 1.0f) / ((float) streamMaxVolume);
                return f * this.e.l();
            }
        }
        f = 0.0f;
        return f * this.e.l();
    }

    private Map<String, String> a(b bVar) {
        return a(bVar, this.e.d());
    }

    private Map<String, String> a(b bVar, int i) {
        Object obj;
        Object obj2 = 1;
        Map<String, String> hashMap = new HashMap();
        if (this.e.h() == com.facebook.ads.internal.view.f.a.a.AUTO_STARTED) {
            obj = 1;
        } else {
            obj = null;
        }
        if (this.e.m()) {
            obj2 = null;
        }
        String str = "autoplay";
        if (obj != null) {
            obj = "1";
        } else {
            obj = "0";
        }
        hashMap.put(str, obj);
        str = "inline";
        if (obj2 != null) {
            obj = "1";
        } else {
            obj = "0";
        }
        hashMap.put(str, obj);
        hashMap.put("exoplayer", String.valueOf(this.e.k()));
        hashMap.put("prep", Long.toString(this.e.e()));
        com.facebook.ads.internal.b.c c = this.f.c();
        com.facebook.ads.internal.b.c.a c2 = c.c();
        hashMap.put("vwa", String.valueOf(c2.d()));
        hashMap.put("vwm", String.valueOf(c2.c()));
        hashMap.put("vwmax", String.valueOf(c2.e()));
        hashMap.put("vtime_ms", String.valueOf(c2.g() * 1000.0d));
        hashMap.put("mcvt_ms", String.valueOf(c2.h() * 1000.0d));
        com.facebook.ads.internal.b.c.a d = c.d();
        hashMap.put("vla", String.valueOf(d.d()));
        hashMap.put("vlm", String.valueOf(d.c()));
        hashMap.put("vlmax", String.valueOf(d.e()));
        hashMap.put("atime_ms", String.valueOf(d.g() * 1000.0d));
        hashMap.put("mcat_ms", String.valueOf(d.h() * 1000.0d));
        hashMap.put("ptime", String.valueOf(((float) this.h) / 1000.0f));
        hashMap.put("time", String.valueOf(((float) i) / 1000.0f));
        Rect rect = new Rect();
        this.e.getGlobalVisibleRect(rect);
        hashMap.put("pt", String.valueOf(rect.top));
        hashMap.put("pl", String.valueOf(rect.left));
        hashMap.put("ph", String.valueOf(this.e.getMeasuredHeight()));
        hashMap.put("pw", String.valueOf(this.e.getMeasuredWidth()));
        WindowManager windowManager = (WindowManager) this.c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        hashMap.put("vph", String.valueOf(displayMetrics.heightPixels));
        hashMap.put("vpw", String.valueOf(displayMetrics.widthPixels));
        if (this.j != null) {
            hashMap.putAll(this.j);
        }
        hashMap.put("action", String.valueOf(bVar.j));
        return hashMap;
    }

    private void a(int i, boolean z) {
        if (((double) i) > 0.0d && i >= this.g) {
            if (i > this.g) {
                this.f.a((double) (((float) (i - this.g)) / 1000.0f), (double) a());
                this.g = i;
                if (i - this.h >= 5000) {
                    this.d.d(this.a, a(b.TIME, i));
                    this.h = this.g;
                    this.f.a();
                    return;
                }
            }
            if (z) {
                this.d.d(this.a, a(b.TIME, i));
            }
        }
    }

    final void a(int i) {
        a(i, false);
    }

    final void a(int i, int i2) {
        a(i, true);
        this.h = i2;
        this.g = i2;
        this.f.a();
        this.f.b();
    }

    public final void b() {
        this.c.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.i);
    }

    public final void b(int i) {
        a(i, true);
        this.h = 0;
        this.g = 0;
        this.f.a();
        this.f.b();
    }

    public final void c() {
        this.c.getContentResolver().unregisterContentObserver(this.i);
    }

    final void d() {
        if (((double) a()) < 0.05d) {
            if (this.b) {
                this.d.d(this.a, a(b.MUTE));
                this.b = false;
            }
        } else if (!this.b) {
            this.d.d(this.a, a(b.UNMUTE));
            this.b = true;
        }
    }

    final void e() {
        this.d.d(this.a, a(b.SKIP));
    }

    final void f() {
        this.d.d(this.a, a(b.PAUSE));
    }

    final void g() {
        this.d.d(this.a, a(b.RESUME));
    }

    public final int i() {
        return this.g;
    }

    public final Bundle h() {
        a(this.g, this.g);
        Bundle bundle = new Bundle();
        bundle.putInt("lastProgressTimeMS", this.g);
        bundle.putInt("lastBoundaryTimeMS", this.h);
        bundle.putBundle("adQualityManager", this.f.d());
        return bundle;
    }
}
