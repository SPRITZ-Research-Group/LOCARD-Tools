package com.google.android.gms.maps;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.b.d;
import com.google.android.gms.b.e;
import com.google.android.gms.common.h;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.maps.a.al;
import com.google.android.gms.maps.a.am;
import com.google.android.gms.maps.a.f;
import com.google.android.gms.maps.a.i;
import com.google.android.gms.maps.model.g;
import java.util.ArrayList;
import java.util.List;

public class StreetViewPanoramaView extends FrameLayout {
    private final b a;

    @VisibleForTesting
    static class a implements i {
        private final ViewGroup a;
        private final f b;
        private View c;

        public a(ViewGroup viewGroup, f fVar) {
            this.b = (f) ab.a((Object) fVar);
            this.a = (ViewGroup) ab.a((Object) viewGroup);
        }

        public final void a() {
            try {
                this.b.a();
            } catch (RemoteException e) {
                throw new g(e);
            }
        }

        public final void a(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                al.a(bundle, bundle2);
                this.b.a(bundle2);
                al.a(bundle2, bundle);
                this.c = (View) d.a(this.b.d());
                this.a.removeAllViews();
                this.a.addView(this.c);
            } catch (RemoteException e) {
                throw new g(e);
            }
        }

        public final void a(f fVar) {
            try {
                this.b.a(new n(fVar));
            } catch (RemoteException e) {
                throw new g(e);
            }
        }

        public final void b() {
            try {
                this.b.b();
            } catch (RemoteException e) {
                throw new g(e);
            }
        }

        public final void c() {
            try {
                this.b.c();
            } catch (RemoteException e) {
                throw new g(e);
            }
        }
    }

    @VisibleForTesting
    static class b extends com.google.android.gms.b.a<a> {
        private final ViewGroup a;
        private final Context b;
        private e<a> c;
        private final StreetViewPanoramaOptions d;
        private final List<f> e = new ArrayList();

        @VisibleForTesting
        b(ViewGroup viewGroup, Context context) {
            this.a = viewGroup;
            this.b = context;
            this.d = null;
        }

        protected final void a(e<a> eVar) {
            this.c = eVar;
            if (this.c != null && a() == null) {
                try {
                    d.a(this.b);
                    this.c.a(new a(this.a, am.a(this.b).a(d.a(this.b), this.d)));
                    for (f a : this.e) {
                        ((a) a()).a(a);
                    }
                    this.e.clear();
                } catch (RemoteException e) {
                    throw new g(e);
                } catch (h e2) {
                }
            }
        }
    }

    public StreetViewPanoramaView(Context context) {
        super(context);
        this.a = new b(this, context);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new b(this, context);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new b(this, context);
    }
}
