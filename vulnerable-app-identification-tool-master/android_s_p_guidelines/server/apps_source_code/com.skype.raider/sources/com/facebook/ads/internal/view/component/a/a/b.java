package com.facebook.ads.internal.view.component.a.a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.k;
import com.facebook.ads.internal.view.f.b.m;
import com.facebook.ads.internal.view.f.b.w;
import com.facebook.ads.internal.view.s;
import java.lang.ref.WeakReference;
import java.util.Map;

public abstract class b extends com.facebook.ads.internal.view.component.a.b {
    private static final int c = ((int) (1.0f * u.b));
    private static final int d = ((int) (4.0f * u.b));
    private static final int e = ((int) (6.0f * u.b));
    private s f;
    private com.facebook.ads.internal.view.c.a.e g;
    private RelativeLayout h;
    private final String i;
    private final Paint j;
    private boolean k;
    private com.facebook.ads.internal.view.c.a.a l;
    private final Path m = new Path();
    private final RectF n = new RectF();
    private boolean o;
    private boolean p;
    private a q;
    private final w r = new w(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
            this.a.l.a().a(this.a.d().b());
        }
    };
    private final com.facebook.ads.internal.view.f.b.c s = new com.facebook.ads.internal.view.f.b.c(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
            this.a.l.b().a(((Integer) this.a.getTag(-1593835536)).intValue());
        }
    };
    private final k t = new k(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
            this.a.l.c().a(this.a);
        }
    };
    private final i u = new i(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
            this.a.l.c().a();
        }
    };
    private final m v = new m(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
            this.a.p = true;
            b.c(this.a);
        }
    };

    public interface e {
        float a();

        void a(float f);
    }

    public interface c {
        void a(int i);
    }

    public interface d {
        void a();

        void a(View view);
    }

    public interface a {
        void a();
    }

    private static class b implements com.facebook.ads.internal.view.b.e {
        final WeakReference<b> a;

        private b(b bVar) {
            this.a = new WeakReference(bVar);
        }

        /* synthetic */ b(b bVar, byte b) {
            this(bVar);
        }

        public final void a(boolean z) {
            b bVar = (b) this.a.get();
            if (bVar != null) {
                bVar.o = z;
                b.c(bVar);
            }
        }
    }

    b(com.facebook.ads.internal.view.component.a.d dVar, com.facebook.ads.internal.adapters.a.d dVar2, boolean z, String str, com.facebook.ads.internal.view.c.a.a aVar) {
        super(dVar, dVar2, z);
        this.l = aVar;
        this.i = str;
        setGravity(17);
        setPadding(c, 0, c, c);
        u.a((View) this, 0);
        Context context = getContext();
        this.f = new s(context);
        a(this.f);
        this.g = new com.facebook.ads.internal.view.c.a.e(context, l());
        a(this.g);
        this.h = new RelativeLayout(context);
        a(this.h);
        this.h.addView(this.f);
        this.h.addView(this.g);
        a(context);
        this.j = new Paint();
        this.j.setColor(-16777216);
        this.j.setStyle(Style.FILL);
        this.j.setAlpha(16);
        this.j.setAntiAlias(true);
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    private static void a(View view) {
        view.setLayoutParams(new LayoutParams(-1, -2));
        u.a(view);
    }

    protected abstract void a(Context context);

    public final void a(String str, String str2) {
        m().a(str, str2, true, false);
    }

    public final void a(String str, String str2, Map<String, String> map) {
        n().a(str, str2, this.i, map);
    }

    public final void a(Map<String, String> map) {
        this.g.d();
        if (this.k) {
            this.g.a(l(), this.i, map);
        }
    }

    public final boolean a() {
        return false;
    }

    protected boolean c() {
        return false;
    }

    protected final com.facebook.ads.internal.view.c.a.e d() {
        return this.g;
    }

    protected final RelativeLayout e() {
        return this.h;
    }

    public final boolean f() {
        return this.k;
    }

    public final void j() {
        float a = this.l.a().a();
        if (this.k && a != this.g.b()) {
            this.g.setVolume(a);
        }
    }

    protected void onDraw(Canvas canvas) {
        this.m.reset();
        this.n.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.m.addRoundRect(this.n, (float) e, (float) e, Direction.CW);
        canvas.drawPath(this.m, this.j);
        this.n.set((float) c, 0.0f, (float) (getWidth() - c), (float) (getHeight() - c));
        this.m.addRoundRect(this.n, (float) d, (float) d, Direction.CW);
        canvas.clipPath(this.m);
        super.onDraw(canvas);
    }

    public void setImageUrl(String str) {
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        new com.facebook.ads.internal.view.b.d(this.f).a().a(new b()).a(str);
    }

    public void setIsVideo(boolean z) {
        this.k = z;
    }

    public void setOnAssetsLoadedListener(a aVar) {
        this.q = aVar;
    }

    public void setVideoPlaceholderUrl(String str) {
        this.g.setPlaceholderUrl(str);
    }

    public void setVideoUrl(String str) {
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        this.g.setVideoURI(str);
        this.g.a(this.r);
        this.g.a(this.s);
        this.g.a(this.t);
        this.g.a(this.u);
        this.g.a(this.v);
    }

    public final boolean g() {
        return this.k && this.g.c();
    }

    public final void h() {
        if (this.k) {
            j();
            this.g.a(com.facebook.ads.internal.view.f.a.a.AUTO_STARTED);
        }
    }

    public final void i() {
        if (this.k) {
            this.g.a();
        }
    }

    static /* synthetic */ void c(b bVar) {
        if (bVar.q == null) {
            return;
        }
        if ((bVar.k && bVar.p) || (!bVar.k && bVar.o)) {
            bVar.q.a();
        }
    }
}
