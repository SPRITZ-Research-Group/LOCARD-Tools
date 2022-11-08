package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.b.e;
import com.facebook.ads.internal.view.f.a.c;
import com.facebook.ads.internal.view.f.b.b;
import com.facebook.ads.internal.view.f.b.j;

public class g extends c {
    private final ImageView a;
    private final f<j> b = new f<j>(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public final Class<j> a() {
            return j.class;
        }

        public final /* synthetic */ void a(d dVar) {
            this.a.setVisibility(8);
        }
    };
    private final f<b> c = new f<b>(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public final Class<b> a() {
            return b.class;
        }

        public final /* synthetic */ void a(d dVar) {
            this.a.setVisibility(0);
        }
    };

    public g(Context context) {
        super(context);
        this.a = new ImageView(context);
        this.a.setScaleType(ScaleType.FIT_CENTER);
        u.a(this.a, -16777216);
        this.a.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.a);
    }

    public final void a(@Nullable String str, @Nullable e eVar) {
        if (str == null) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        com.facebook.ads.internal.view.b.d a = new com.facebook.ads.internal.view.b.d(this.a).a();
        if (eVar != null) {
            a.a(eVar);
        }
        a.a(str);
    }

    protected final void b() {
        super.b();
        if (a() != null) {
            a().a().a(this.b, this.c);
        }
    }

    protected final void c() {
        if (a() != null) {
            a().a().b(this.c, this.b);
        }
        super.c();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.a.layout(0, 0, i3 - i, i4 - i2);
    }

    public void setImage(@Nullable String str) {
        a(str, null);
    }
}
