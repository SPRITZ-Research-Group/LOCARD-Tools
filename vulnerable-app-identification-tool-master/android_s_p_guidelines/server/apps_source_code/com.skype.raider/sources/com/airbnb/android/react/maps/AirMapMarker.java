package com.airbnb.android.react.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.facebook.common.f.a;
import com.facebook.datasource.c;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.c.q;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.b;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.d;
import com.facebook.react.bridge.am;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import javax.annotation.Nullable;

public class AirMapMarker extends AirMapFeature {
    private final b<?> A;
    private c<a<com.facebook.imagepipeline.image.c>> B;
    private final ControllerListener<ImageInfo> C = new BaseControllerListener<ImageInfo>(this) {
        final /* synthetic */ AirMapMarker a;

        {
            this.a = this$0;
        }

        public final /* synthetic */ void onFinalImageSet(String str, @Nullable Object obj, @Nullable Animatable animatable) {
            Throwable th;
            a aVar = null;
            try {
                a aVar2 = (a) this.a.B.d();
                if (aVar2 != null) {
                    try {
                        com.facebook.imagepipeline.image.c cVar = (com.facebook.imagepipeline.image.c) aVar2.a();
                        if (cVar != null && (cVar instanceof d)) {
                            Bitmap f = ((d) cVar).f();
                            if (f != null) {
                                f = f.copy(Config.ARGB_8888, true);
                                this.a.q = f;
                                this.a.p = com.google.android.gms.maps.model.b.a(f);
                            }
                        }
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        aVar = aVar2;
                        th = th3;
                        this.a.B.h();
                        if (aVar != null) {
                            a.c(aVar);
                        }
                        throw th;
                    }
                }
                this.a.B.h();
                if (aVar2 != null) {
                    a.c(aVar2);
                }
                this.a.d_();
            } catch (Throwable th4) {
                th = th4;
                this.a.B.h();
                if (aVar != null) {
                    a.c(aVar);
                }
                throw th;
            }
        }
    };
    private MarkerOptions a;
    private com.google.android.gms.maps.model.d b;
    private int c;
    private int d;
    private String e;
    private LatLng f;
    private String g;
    private String h;
    private boolean i;
    private float j;
    private float k;
    private AirMapCallout l;
    private View m;
    private final Context n;
    private float o = 0.0f;
    private com.google.android.gms.maps.model.a p;
    private Bitmap q;
    private float r = 0.0f;
    private boolean s = false;
    private boolean t = false;
    private int u = 0;
    private float v = 1.0f;
    private float w;
    private float x;
    private boolean y;
    private boolean z = false;

    public AirMapMarker(Context context) {
        super(context);
        this.n = context;
        this.A = b.a(new com.facebook.drawee.d.b(getResources()).e(q.b.c).a(0).s());
        this.A.b();
    }

    public void setCoordinate(am coordinate) {
        this.f = new LatLng(coordinate.getDouble("latitude"), coordinate.getDouble("longitude"));
        if (this.b != null) {
            this.b.a(this.f);
        }
        d_();
    }

    public void setIdentifier(String identifier) {
        this.e = identifier;
        d_();
    }

    public final String c() {
        return this.e;
    }

    public void setTitle(String title) {
        this.g = title;
        if (this.b != null) {
            this.b.a(title);
        }
        d_();
    }

    public void setSnippet(String snippet) {
        this.h = snippet;
        if (this.b != null) {
            this.b.b(snippet);
        }
        d_();
    }

    public void setRotation(float rotation) {
        this.r = rotation;
        if (this.b != null) {
            this.b.b(rotation);
        }
        d_();
    }

    public void setFlat(boolean flat) {
        this.s = flat;
        if (this.b != null) {
            this.b.b(flat);
        }
        d_();
    }

    public void setDraggable(boolean draggable) {
        this.t = draggable;
        if (this.b != null) {
            this.b.a(draggable);
        }
        d_();
    }

    public void setZIndex(int zIndex) {
        this.u = zIndex;
        if (this.b != null) {
            this.b.a((float) zIndex);
        }
        d_();
    }

    public void setOpacity(float opacity) {
        this.v = opacity;
        if (this.b != null) {
            this.b.c(opacity);
        }
        d_();
    }

    public void setMarkerHue(float markerHue) {
        this.o = markerHue;
        d_();
    }

    public void setAnchor(double x, double y) {
        this.i = true;
        this.j = (float) x;
        this.k = (float) y;
        if (this.b != null) {
            this.b.a(this.j, this.k);
        }
        d_();
    }

    public void setCalloutAnchor(double x, double y) {
        this.y = true;
        this.w = (float) x;
        this.x = (float) y;
        if (this.b != null) {
            this.b.b(this.w, this.x);
        }
        d_();
    }

    public void setImage(String uri) {
        if (uri == null) {
            this.p = null;
            d_();
        } else if (uri.startsWith("http://") || uri.startsWith("https://") || uri.startsWith("file://")) {
            com.facebook.imagepipeline.k.b imageRequest = com.facebook.imagepipeline.k.c.a(Uri.parse(uri)).q();
            this.B = Fresco.b().a(imageRequest, (Object) this);
            this.A.a(((com.facebook.drawee.backends.pipeline.c) ((com.facebook.drawee.backends.pipeline.c) ((com.facebook.drawee.backends.pipeline.c) Fresco.a().b((Object) imageRequest)).a(this.C)).a(this.A.d())).h());
        } else {
            this.p = com.google.android.gms.maps.model.b.a(getResources().getIdentifier(uri, "drawable", getContext().getPackageName()));
            d_();
        }
    }

    public void addView(View child, int index) {
        super.addView(child, index);
        if (!(child instanceof AirMapCallout)) {
            this.z = true;
        }
        d_();
    }

    public final Object a() {
        return this.b;
    }

    public final void b() {
        this.b.a();
        this.b = null;
    }

    private com.google.android.gms.maps.model.a k() {
        if (this.z) {
            if (this.p == null) {
                return com.google.android.gms.maps.model.b.a(l());
            }
            Bitmap viewBitmap = l();
            Bitmap combinedBitmap = Bitmap.createBitmap(Math.max(this.q.getWidth(), viewBitmap.getWidth()), Math.max(this.q.getHeight(), viewBitmap.getHeight()), this.q.getConfig());
            Canvas canvas = new Canvas(combinedBitmap);
            canvas.drawBitmap(this.q, 0.0f, 0.0f, null);
            canvas.drawBitmap(viewBitmap, 0.0f, 0.0f, null);
            return com.google.android.gms.maps.model.b.a(combinedBitmap);
        } else if (this.p != null) {
            return this.p;
        } else {
            return com.google.android.gms.maps.model.b.a(this.o);
        }
    }

    public final void d_() {
        if (this.b != null) {
            this.b.a(k());
            if (this.i) {
                this.b.a(this.j, this.k);
            } else {
                this.b.a(0.5f, 1.0f);
            }
            if (this.y) {
                this.b.b(this.w, this.x);
            } else {
                this.b.b(0.5f, 0.0f);
            }
        }
    }

    public final void a(int width, int height) {
        this.c = width;
        this.d = height;
        d_();
    }

    private Bitmap l() {
        int width = this.c <= 0 ? 100 : this.c;
        int height = this.d <= 0 ? 100 : this.d;
        buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        draw(new Canvas(bitmap));
        return bitmap;
    }

    public void setCalloutView(AirMapCallout view) {
        this.l = view;
    }

    public final AirMapCallout e() {
        return this.l;
    }

    public final View f() {
        if (this.l == null) {
            return null;
        }
        if (this.m == null) {
            m();
        }
        if (this.l.a_()) {
            return this.m;
        }
        return null;
    }

    public final View g() {
        if (this.l == null) {
            return null;
        }
        if (this.m == null) {
            m();
        }
        if (this.l.a_()) {
            return null;
        }
        return this.m;
    }

    private void m() {
        if (this.l != null && this.l.getChildCount() != 0) {
            LinearLayout LL = new LinearLayout(this.n);
            LL.setOrientation(1);
            LL.setLayoutParams(new LayoutParams(this.l.a, this.l.b, 0.0f));
            LinearLayout LL2 = new LinearLayout(this.n);
            LL2.setOrientation(0);
            LL2.setLayoutParams(new LayoutParams(this.l.a, this.l.b, 0.0f));
            LL.addView(LL2);
            LL2.addView(this.l);
            this.m = LL;
        }
    }

    public final void a(com.google.android.gms.maps.c map) {
        if (this.a == null) {
            MarkerOptions a = new MarkerOptions().a(this.f);
            if (this.i) {
                a.a(this.j, this.k);
            }
            if (this.y) {
                a.b(this.w, this.x);
            }
            a.a(this.g);
            a.b(this.h);
            a.b(this.r);
            a.b(this.s);
            a.a(this.t);
            a.a((float) this.u);
            a.c(this.v);
            a.a(k());
            this.a = a;
        }
        this.b = map.a(this.a);
    }
}
