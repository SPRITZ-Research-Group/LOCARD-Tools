package com.google.android.gms.maps;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.b.d;
import com.google.android.gms.b.e;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.maps.a.al;
import com.google.android.gms.maps.a.am;
import com.google.android.gms.maps.a.c;
import com.google.android.gms.maps.a.h;
import com.google.android.gms.maps.model.g;
import java.util.ArrayList;
import java.util.List;

public class MapView extends FrameLayout {
    private final b a;

    @VisibleForTesting
    static class a implements h {
        private final ViewGroup a;
        private final c b;
        private View c;

        public a(ViewGroup viewGroup, c cVar) {
            this.b = (c) ab.a((Object) cVar);
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

        public final void a(e eVar) {
            try {
                this.b.a(new l(eVar));
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
        private final GoogleMapOptions d;
        private final List<e> e = new ArrayList();

        @VisibleForTesting
        b(ViewGroup viewGroup, Context context, GoogleMapOptions googleMapOptions) {
            this.a = viewGroup;
            this.b = context;
            this.d = googleMapOptions;
        }

        protected final void a(e<a> eVar) {
            this.c = eVar;
            if (this.c != null && a() == null) {
                try {
                    d.a(this.b);
                    c a = am.a(this.b).a(d.a(this.b), this.d);
                    if (a != null) {
                        this.c.a(new a(this.a, a));
                        for (e a2 : this.e) {
                            ((a) a()).a(a2);
                        }
                        this.e.clear();
                    }
                } catch (RemoteException e) {
                    throw new g(e);
                } catch (com.google.android.gms.common.h e2) {
                }
            }
        }

        public final void a(e eVar) {
            if (a() != null) {
                ((a) a()).a(eVar);
            } else {
                this.e.add(eVar);
            }
        }
    }

    public MapView(Context context) {
        super(context);
        this.a = new b(this, context, null);
        setClickable(true);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new b(this, context, GoogleMapOptions.a(context, attributeSet));
        setClickable(true);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new b(this, context, GoogleMapOptions.a(context, attributeSet));
        setClickable(true);
    }

    public MapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context);
        this.a = new b(this, context, googleMapOptions);
        setClickable(true);
    }

    public final void a(e eVar) {
        ab.b("getMapAsync() must be called on the main thread");
        this.a.a(eVar);
    }

    public final void e() {
        ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new Builder(threadPolicy).permitAll().build());
        try {
            this.a.b();
            if (this.a.a() == null) {
                com.google.android.gms.b.a.a((FrameLayout) this);
            }
            StrictMode.setThreadPolicy(threadPolicy);
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public final void f() {
        this.a.c();
    }

    public final void g() {
        this.a.d();
    }

    public final void h() {
        this.a.e();
    }
}
