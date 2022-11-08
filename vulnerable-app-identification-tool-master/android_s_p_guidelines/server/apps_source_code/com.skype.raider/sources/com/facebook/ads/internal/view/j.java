package com.facebook.ads.internal.view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.g;
import com.facebook.ads.internal.adapters.ae;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.k;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.UUID;

public class j extends a {
    private final String b = UUID.randomUUID().toString();
    private final k c = new k(this) {
        final /* synthetic */ j a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(d dVar) {
            if (this.a.n != null) {
                this.a.n;
            }
        }
    };
    private final i d = new i(this) {
        final /* synthetic */ j a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(d dVar) {
            if (this.a.n != null) {
                this.a.n;
            }
        }
    };
    private final c e = new c(this) {
        final /* synthetic */ j a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(d dVar) {
            if (this.a.n != null) {
                this.a.n;
            }
        }
    };
    private final ae f;
    private com.facebook.ads.internal.m.c g;
    @Nullable
    private b h;
    @Nullable
    private String i;
    @Nullable
    private Uri j;
    @Nullable
    private String k;
    @Nullable
    private String l;
    @Nullable
    private String m;
    @Nullable
    private k n;
    @Nullable
    private g o;

    public j(Context context) {
        super(context);
        this.f = new ae(this, context);
        F();
    }

    public j(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new ae(this, context);
        F();
    }

    public j(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = new ae(this, context);
        F();
    }

    private void F() {
        a().a(this.c, this.d, this.e);
    }

    public final void B() {
        Context context = getContext();
        Intent intent = new Intent(context, AudienceNetworkActivity.class);
        if (this.i == null || this.h == null) {
            throw new IllegalStateException("Must setVideoReportUri first.");
        } else if (this.j == null && this.l == null) {
            throw new IllegalStateException("Must setVideoURI or setVideoMPD first.");
        } else {
            intent.putExtra("useNativeCtaButton", this.m);
            intent.putExtra("viewType", com.facebook.ads.internal.t.b.a.FULL_SCREEN_VIDEO);
            intent.putExtra("videoURL", this.j.toString());
            intent.putExtra("clientToken", this.k == null ? "" : this.k);
            intent.putExtra("videoMPD", this.l);
            intent.putExtra("predefinedOrientationKey", 13);
            intent.putExtra("videoSeekTime", d());
            intent.putExtra("uniqueId", this.b);
            intent.putExtra("videoLogger", this.h.h());
            intent.putExtra("video_time_polling_interval", i());
            intent.addFlags(ErrorDialogData.BINDER_CRASH);
            try {
                a(false);
                setVisibility(8);
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                try {
                    intent.setClass(context, InterstitialAdActivity.class);
                    context.startActivity(intent);
                } catch (Throwable e2) {
                    com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(e2, "Error occurred while loading fullscreen video activity."));
                }
            } catch (Throwable e22) {
                com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(e22, "Error occurred while loading fullscreen video activity."));
            }
        }
    }

    public final void C() {
        if (this.o != null) {
            this.o.q();
        }
    }

    @Nullable
    public final k D() {
        return this.n;
    }

    public final String E() {
        return this.b;
    }

    public final void a(@Nullable String str, @Nullable String str2) {
        if (this.h != null) {
            this.h.a();
        }
        this.k = str2;
        this.i = str;
        b bVar = (str == null || str2 == null) ? null : new b(getContext(), this.g, this, str2);
        this.h = bVar;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f.a();
    }

    protected void onDetachedFromWindow() {
        this.f.b();
        super.onDetachedFromWindow();
    }

    public void setAdEventManager(com.facebook.ads.internal.m.c cVar) {
        this.g = cVar;
    }

    public void setEnableBackgroundVideo(boolean z) {
        this.a.setBackgroundPlaybackEnabled(z);
    }

    public void setListener(@Nullable k kVar) {
        this.n = kVar;
    }

    public void setNativeAd(@Nullable g gVar) {
        this.o = gVar;
    }

    public void setVideoCTA(@Nullable String str) {
        this.m = str;
    }

    public void setVideoMPD(@Nullable String str) {
        if (str == null || this.h != null) {
            this.l = str;
            super.setVideoMPD(str);
            return;
        }
        throw new IllegalStateException("Must setVideoReportUri first.");
    }

    public void setVideoURI(@Nullable Uri uri) {
        if (uri == null || this.h != null) {
            this.j = uri;
            super.setVideoURI(uri);
            return;
        }
        throw new IllegalStateException("Must setVideoReportUri first.");
    }
}
