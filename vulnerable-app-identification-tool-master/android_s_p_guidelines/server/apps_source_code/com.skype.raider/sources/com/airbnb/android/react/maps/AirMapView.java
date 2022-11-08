package com.airbnb.android.react.maps;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.content.PermissionChecker;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.d;
import com.facebook.react.bridge.v;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.events.c;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.c.a;
import com.google.android.gms.maps.c.b;
import com.google.android.gms.maps.c.g;
import com.google.android.gms.maps.c.h;
import com.google.android.gms.maps.c.i;
import com.google.android.gms.maps.c.j;
import com.google.android.gms.maps.c.k;
import com.google.android.gms.maps.c.l;
import com.google.android.gms.maps.e;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.VisibleRegion;
import com.google.android.gms.maps.model.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirMapView extends MapView implements b, i, e {
    private static final String[] r = new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private d A;
    private boolean B;
    private boolean C;
    private final ae D;
    private final c E;
    private LatLngBounds F;
    public com.google.android.gms.maps.c a;
    Handler b;
    Runnable c;
    private ProgressBar d;
    private RelativeLayout e;
    private ImageView f;
    private Boolean g;
    private Integer h;
    private Integer i;
    private final int j;
    private LatLngBounds k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private final List<AirMapFeature> s;
    private final Map<com.google.android.gms.maps.model.d, AirMapMarker> t;
    private final Map<f, AirMapPolyline> u;
    private final Map<com.google.android.gms.maps.model.e, AirMapPolygon> v;
    private final ScaleGestureDetector w;
    private final android.support.v4.view.c x;
    private final AirMapManager y;
    private v z;

    private static boolean a(Context context) {
        return context == null || context.getResources() == null || context.getResources().getConfiguration() == null;
    }

    public AirMapView(ae reactContext, ag appContext, AirMapManager manager, GoogleMapOptions googleMapOptions) {
        Context j;
        if (a(appContext.j())) {
            if (a((Context) reactContext)) {
                if (!a(reactContext.j())) {
                    j = reactContext.j();
                } else if (!a(reactContext.getApplicationContext())) {
                    j = reactContext.getApplicationContext();
                }
            }
            j = reactContext;
        } else {
            j = appContext.j();
        }
        super(j, googleMapOptions);
        this.g = Boolean.valueOf(false);
        this.h = null;
        this.i = null;
        this.j = 50;
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = true;
        this.q = false;
        this.s = new ArrayList();
        this.t = new HashMap();
        this.u = new HashMap();
        this.v = new HashMap();
        this.B = false;
        this.C = false;
        this.b = new Handler();
        this.c = new Runnable(this) {
            final /* synthetic */ AirMapView a;

            {
                this.a = this$0;
            }

            public final void run() {
                VisibleRegion region = this.a.a.c().a();
                LatLngBounds bounds = region != null ? region.e : null;
                if (bounds != null && (this.a.F == null || a.a(bounds, this.a.F))) {
                    LatLng center = this.a.a.a().a;
                    this.a.F = bounds;
                    this.a.E.a(new c(this.a.getId(), bounds, center, true));
                }
                this.a.b.postDelayed(this, 100);
            }
        };
        this.y = manager;
        this.D = reactContext;
        super.e();
        super.f();
        super.a(this);
        final AirMapView view = this;
        this.w = new ScaleGestureDetector(reactContext, new SimpleOnScaleGestureListener(this) {
            final /* synthetic */ AirMapView b;

            public final boolean onScaleBegin(ScaleGestureDetector detector) {
                view.c();
                return true;
            }
        });
        this.x = new android.support.v4.view.c(reactContext, new SimpleOnGestureListener(this) {
            final /* synthetic */ AirMapView b;

            public final boolean onDoubleTap(MotionEvent e) {
                view.c();
                return false;
            }

            public final boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (this.b.o) {
                    this.b.a(e2);
                }
                view.c();
                return false;
            }
        });
        addOnLayoutChangeListener(new OnLayoutChangeListener(this) {
            final /* synthetic */ AirMapView a;

            {
                this.a = this$0;
            }

            public final void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (!this.a.B) {
                    this.a.k();
                }
            }
        });
        this.E = ((UIManagerModule) reactContext.b(UIManagerModule.class)).getEventDispatcher();
    }

    public final void a(final com.google.android.gms.maps.c map) {
        if (!this.C) {
            this.a = map;
            this.a.a((b) this);
            this.a.a((i) this);
            this.y.pushEvent(this.D, this, "onMapReady", new WritableNativeMap());
            final AirMapView view = this;
            map.a(new h(this) {
                final /* synthetic */ AirMapView b;

                public final boolean a(com.google.android.gms.maps.model.d marker) {
                    AirMapMarker airMapMarker = (AirMapMarker) this.b.t.get(marker);
                    ar event = this.b.a(marker.b());
                    event.putString("action", "marker-press");
                    event.putString("id", airMapMarker.c());
                    this.b.y.pushEvent(this.b.D, view, "onMarkerPress", event);
                    event = this.b.a(marker.b());
                    event.putString("action", "marker-press");
                    event.putString("id", airMapMarker.c());
                    this.b.y.pushEvent(this.b.D, (View) this.b.t.get(marker), "onPress", event);
                    if (view.p) {
                        return false;
                    }
                    marker.c();
                    return true;
                }
            });
            map.a(new j(this) {
                final /* synthetic */ AirMapView a;

                {
                    this.a = this$0;
                }

                public final void a(com.google.android.gms.maps.model.e polygon) {
                    ar event = this.a.a((LatLng) polygon.b().get(0));
                    event.putString("action", "polygon-press");
                    this.a.y.pushEvent(this.a.D, (View) this.a.v.get(polygon), "onPress", event);
                }
            });
            map.a(new k(this) {
                final /* synthetic */ AirMapView a;

                {
                    this.a = this$0;
                }

                public final void a(f polyline) {
                    ar event = this.a.a((LatLng) polyline.b().get(0));
                    event.putString("action", "polyline-press");
                    this.a.y.pushEvent(this.a.D, (View) this.a.u.get(polyline), "onPress", event);
                }
            });
            map.a(new com.google.android.gms.maps.c.d(this) {
                final /* synthetic */ AirMapView b;

                public final void a(com.google.android.gms.maps.model.d marker) {
                    ar event = this.b.a(marker.b());
                    event.putString("action", "callout-press");
                    this.b.y.pushEvent(this.b.D, view, "onCalloutPress", event);
                    event = this.b.a(marker.b());
                    event.putString("action", "callout-press");
                    AirMapMarker markerView = (AirMapMarker) this.b.t.get(marker);
                    this.b.y.pushEvent(this.b.D, markerView, "onCalloutPress", event);
                    event = this.b.a(marker.b());
                    event.putString("action", "callout-press");
                    AirMapCallout infoWindow = markerView.e();
                    if (infoWindow != null) {
                        this.b.y.pushEvent(this.b.D, infoWindow, "onPress", event);
                    }
                }
            });
            map.a(new com.google.android.gms.maps.c.e(this) {
                final /* synthetic */ AirMapView b;

                public final void a(LatLng point) {
                    ar event = this.b.a(point);
                    event.putString("action", "press");
                    this.b.y.pushEvent(this.b.D, view, "onPress", event);
                }
            });
            map.a(new g(this) {
                final /* synthetic */ AirMapView b;

                public final void a(LatLng point) {
                    this.b.a(point).putString("action", "long-press");
                    this.b.y.pushEvent(this.b.D, view, "onLongPress", this.b.a(point));
                }
            });
            map.a(new com.google.android.gms.maps.c.c(this) {
                final /* synthetic */ AirMapView c;

                public final void a(CameraPosition position) {
                    LatLngBounds bounds = map.c().a().e;
                    LatLng center = position.a;
                    this.c.F = bounds;
                    this.c.E.a(new c(this.c.getId(), bounds, center, this.c.n));
                    view.d();
                }
            });
            map.a(new com.google.android.gms.maps.c.f(this) {
                final /* synthetic */ AirMapView a;

                {
                    this.a = this$0;
                }

                public final void a() {
                    this.a.g = Boolean.valueOf(true);
                    this.a.k();
                    if (this.a.A != null) {
                        this.a.A.invoke(new Object[0]);
                        this.a.A = null;
                    }
                }
            });
            this.z = new v(this) {
                final /* synthetic */ AirMapView b;

                public final void onHostResume() {
                    if (this.b.i()) {
                        map.d(this.b.l);
                    }
                    synchronized (this.b) {
                        if (!this.b.C) {
                            this.b.f();
                        }
                        this.b.B = false;
                    }
                }

                public final void onHostPause() {
                    if (this.b.i()) {
                        map.d(false);
                    }
                    synchronized (this.b) {
                        if (!this.b.C) {
                            this.b.g();
                        }
                        this.b.B = true;
                    }
                }

                public final void onHostDestroy() {
                    this.b.a();
                }
            };
            this.D.a(this.z);
        }
    }

    private boolean i() {
        if (PermissionChecker.a(getContext(), r[0]) == 0 || PermissionChecker.a(getContext(), r[1]) == 0) {
            return true;
        }
        return false;
    }

    public final synchronized void a() {
        if (!this.C) {
            this.C = true;
            if (!(this.z == null || this.D == null)) {
                this.D.b(this.z);
                this.z = null;
            }
            if (!this.B) {
                g();
                this.B = true;
            }
            h();
            this.A = null;
        }
    }

    public void setRegion(am region) {
        if (region != null) {
            Double lng = Double.valueOf(region.getDouble("longitude"));
            Double lat = Double.valueOf(region.getDouble("latitude"));
            Double lngDelta = Double.valueOf(region.getDouble("longitudeDelta"));
            Double latDelta = Double.valueOf(region.getDouble("latitudeDelta"));
            LatLngBounds bounds = new LatLngBounds(new LatLng(lat.doubleValue() - (latDelta.doubleValue() / 2.0d), lng.doubleValue() - (lngDelta.doubleValue() / 2.0d)), new LatLng(lat.doubleValue() + (latDelta.doubleValue() / 2.0d), lng.doubleValue() + (lngDelta.doubleValue() / 2.0d)));
            if (super.getHeight() <= 0 || super.getWidth() <= 0) {
                this.a.a(com.google.android.gms.maps.b.b(new LatLng(lat.doubleValue(), lng.doubleValue())));
                this.k = bounds;
                return;
            }
            this.a.a(com.google.android.gms.maps.b.a(bounds, 0));
            this.k = null;
        }
    }

    public void setShowsUserLocation(boolean showUserLocation) {
        this.l = showUserLocation;
        if (i()) {
            this.a.d(showUserLocation);
        }
    }

    public void setShowsMyLocationButton(boolean showMyLocationButton) {
        if (i()) {
            this.a.b().b(showMyLocationButton);
        }
    }

    public void setToolbarEnabled(boolean toolbarEnabled) {
        if (i()) {
            this.a.b().h(toolbarEnabled);
        }
    }

    public void setCacheEnabled(boolean cacheEnabled) {
        this.q = cacheEnabled;
        k();
    }

    public final void a(boolean loadingEnabled) {
        if (loadingEnabled && !this.g.booleanValue()) {
            j().setVisibility(0);
        }
    }

    public void setMoveOnMarkerPress(boolean moveOnPress) {
        this.p = moveOnPress;
    }

    public void setLoadingBackgroundColor(Integer loadingBackgroundColor) {
        this.h = loadingBackgroundColor;
        if (this.e == null) {
            return;
        }
        if (loadingBackgroundColor == null) {
            this.e.setBackgroundColor(-1);
        } else {
            this.e.setBackgroundColor(this.h.intValue());
        }
    }

    public void setLoadingIndicatorColor(Integer loadingIndicatorColor) {
        this.i = loadingIndicatorColor;
        if (this.d != null) {
            Integer color = loadingIndicatorColor;
            if (loadingIndicatorColor == null) {
                color = Integer.valueOf(Color.parseColor("#606060"));
            }
            if (VERSION.SDK_INT >= 21) {
                ColorStateList progressTintList = ColorStateList.valueOf(loadingIndicatorColor.intValue());
                ColorStateList secondaryProgressTintList = ColorStateList.valueOf(loadingIndicatorColor.intValue());
                ColorStateList indeterminateTintList = ColorStateList.valueOf(loadingIndicatorColor.intValue());
                this.d.setProgressTintList(progressTintList);
                this.d.setSecondaryProgressTintList(secondaryProgressTintList);
                this.d.setIndeterminateTintList(indeterminateTintList);
                return;
            }
            Mode mode = Mode.SRC_IN;
            if (VERSION.SDK_INT <= 10) {
                mode = Mode.MULTIPLY;
            }
            if (this.d.getIndeterminateDrawable() != null) {
                this.d.getIndeterminateDrawable().setColorFilter(color.intValue(), mode);
            }
            if (this.d.getProgressDrawable() != null) {
                this.d.getProgressDrawable().setColorFilter(color.intValue(), mode);
            }
        }
    }

    public void setHandlePanDrag(boolean handlePanDrag) {
        this.o = handlePanDrag;
    }

    public final void a(View child, int index) {
        if (child instanceof AirMapMarker) {
            AirMapMarker annotation = (AirMapMarker) child;
            annotation.a(this.a);
            this.s.add(index, annotation);
            this.t.put((com.google.android.gms.maps.model.d) annotation.a(), annotation);
        } else if (child instanceof AirMapPolyline) {
            AirMapPolyline polylineView = (AirMapPolyline) child;
            polylineView.a(this.a);
            this.s.add(index, polylineView);
            this.u.put((f) polylineView.a(), polylineView);
        } else if (child instanceof AirMapPolygon) {
            AirMapPolygon polygonView = (AirMapPolygon) child;
            polygonView.a(this.a);
            this.s.add(index, polygonView);
            this.v.put((com.google.android.gms.maps.model.e) polygonView.a(), polygonView);
        } else if (child instanceof AirMapCircle) {
            AirMapCircle circleView = (AirMapCircle) child;
            circleView.a(this.a);
            this.s.add(index, circleView);
        } else if (child instanceof AirMapUrlTile) {
            AirMapUrlTile urlTileView = (AirMapUrlTile) child;
            urlTileView.a(this.a);
            this.s.add(index, urlTileView);
        } else {
            ViewGroup children = (ViewGroup) child;
            for (int i = 0; i < children.getChildCount(); i++) {
                a(children.getChildAt(i), index);
            }
        }
    }

    public final int b() {
        return this.s.size();
    }

    public final View a(int index) {
        return (View) this.s.get(index);
    }

    public final void b(int index) {
        AirMapFeature feature = (AirMapFeature) this.s.remove(index);
        if (feature instanceof AirMapMarker) {
            this.t.remove(feature.a());
        }
        feature.b();
    }

    public final ar a(LatLng point) {
        ar event = new WritableNativeMap();
        ar coordinate = new WritableNativeMap();
        coordinate.putDouble("latitude", point.a);
        coordinate.putDouble("longitude", point.b);
        event.putMap("coordinate", coordinate);
        Point screenPoint = this.a.c().a(point);
        ar position = new WritableNativeMap();
        position.putDouble("x", (double) screenPoint.x);
        position.putDouble("y", (double) screenPoint.y);
        event.putMap("position", position);
        return event;
    }

    public final void a(Object extraData) {
        if (this.k != null) {
            HashMap<String, Float> data = (HashMap) extraData;
            this.a.a(com.google.android.gms.maps.b.a(this.k, (int) ((Float) data.get("width")).floatValue(), (int) ((Float) data.get("height")).floatValue()));
            this.k = null;
        }
    }

    public final void a(final LatLngBounds bounds, final int duration, final com.facebook.react.bridge.ae promise) {
        if (this.g.booleanValue()) {
            b(bounds, duration, promise);
        } else {
            this.A = new d(this) {
                final /* synthetic */ AirMapView d;

                public final void invoke(Object... args) {
                    this.d.b(bounds, duration, promise);
                }
            };
        }
    }

    private void b(LatLngBounds bounds, int duration, final com.facebook.react.bridge.ae promise) {
        c();
        this.a.a(com.google.android.gms.maps.b.a(bounds, 0), duration, new a(this) {
            final /* synthetic */ AirMapView b;

            public final void a() {
                promise.a(Boolean.valueOf(true));
            }

            public final void b() {
                promise.a("ANIMATE_COORDINATE_CANCELLED", "Animate to coordinate cancelled");
            }
        });
    }

    public final void a(final LatLng coordinate, final int duration, final com.facebook.react.bridge.ae promise) {
        if (this.g.booleanValue()) {
            b(coordinate, duration, promise);
        } else {
            this.A = new d(this) {
                final /* synthetic */ AirMapView d;

                public final void invoke(Object... args) {
                    this.d.b(coordinate, duration, promise);
                }
            };
        }
    }

    private void b(LatLng coordinate, int duration, final com.facebook.react.bridge.ae promise) {
        c();
        this.a.a(com.google.android.gms.maps.b.a(coordinate), duration, new a(this) {
            final /* synthetic */ AirMapView b;

            public final void a() {
                promise.a(Boolean.valueOf(true));
            }

            public final void b() {
                promise.a("ANIMATE_COORDINATE_CANCELLED", "Animate to coordinate cancelled");
            }
        });
    }

    public final void b(boolean animated) {
        LatLngBounds.a builder = new LatLngBounds.a();
        boolean addedPosition = false;
        for (AirMapFeature feature : this.s) {
            if (feature instanceof AirMapMarker) {
                builder.a(((com.google.android.gms.maps.model.d) feature.a()).b());
                addedPosition = true;
            }
        }
        if (addedPosition) {
            com.google.android.gms.maps.a cu = com.google.android.gms.maps.b.a(builder.a(), 50);
            if (animated) {
                c();
                this.a.b(cu);
                return;
            }
            this.a.a(cu);
        }
    }

    public final void a(al markerIDsArray, boolean animated) {
        LatLngBounds.a builder = new LatLngBounds.a();
        String[] markerIDs = new String[markerIDsArray.size()];
        for (int i = 0; i < markerIDsArray.size(); i++) {
            markerIDs[i] = markerIDsArray.getString(i);
        }
        boolean addedPosition = false;
        List<String> markerIDList = Arrays.asList(markerIDs);
        for (AirMapFeature feature : this.s) {
            if (feature instanceof AirMapMarker) {
                com.google.android.gms.maps.model.d marker = (com.google.android.gms.maps.model.d) feature.a();
                if (markerIDList.contains(((AirMapMarker) feature).c())) {
                    builder.a(marker.b());
                    addedPosition = true;
                }
            }
        }
        if (addedPosition) {
            com.google.android.gms.maps.a cu = com.google.android.gms.maps.b.a(builder.a(), 50);
            if (animated) {
                c();
                this.a.b(cu);
                return;
            }
            this.a.a(cu);
        }
    }

    public final View a(com.google.android.gms.maps.model.d marker) {
        return ((AirMapMarker) this.t.get(marker)).f();
    }

    public final View b(com.google.android.gms.maps.model.d marker) {
        return ((AirMapMarker) this.t.get(marker)).g();
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean z = false;
        this.w.onTouchEvent(ev);
        this.x.a(ev);
        switch (ev.getActionMasked()) {
            case 0:
                ViewParent parent = getParent();
                if (this.a != null && this.a.b().a()) {
                    z = true;
                }
                parent.requestDisallowInterceptTouchEvent(z);
                this.n = true;
                break;
            case 1:
                getParent().requestDisallowInterceptTouchEvent(false);
                this.n = false;
                break;
            case 2:
                c();
                break;
        }
        super.dispatchTouchEvent(ev);
        return true;
    }

    public final void c() {
        if (!this.m) {
            this.b.postDelayed(this.c, 100);
            this.m = true;
        }
    }

    public final void d() {
        if (this.m) {
            this.b.removeCallbacks(this.c);
            this.m = false;
        }
    }

    public final void c(com.google.android.gms.maps.model.d marker) {
        this.y.pushEvent(this.D, this, "onMarkerDragStart", a(marker.b()));
        this.y.pushEvent(this.D, (AirMapMarker) this.t.get(marker), "onDragStart", a(marker.b()));
    }

    public final void d(com.google.android.gms.maps.model.d marker) {
        this.y.pushEvent(this.D, this, "onMarkerDrag", a(marker.b()));
        this.y.pushEvent(this.D, (AirMapMarker) this.t.get(marker), "onDrag", a(marker.b()));
    }

    public final void e(com.google.android.gms.maps.model.d marker) {
        this.y.pushEvent(this.D, this, "onMarkerDragEnd", a(marker.b()));
        this.y.pushEvent(this.D, (AirMapMarker) this.t.get(marker), "onDragEnd", a(marker.b()));
    }

    private RelativeLayout j() {
        if (this.e == null) {
            this.e = new RelativeLayout(getContext());
            this.e.setBackgroundColor(-3355444);
            addView(this.e, new LayoutParams(-1, -1));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
            params.addRule(13);
            RelativeLayout relativeLayout = this.e;
            if (this.d == null) {
                this.d = new ProgressBar(getContext());
                this.d.setIndeterminate(true);
            }
            if (this.i != null) {
                setLoadingIndicatorColor(this.i);
            }
            relativeLayout.addView(this.d, params);
            this.e.setVisibility(4);
        }
        setLoadingBackgroundColor(this.h);
        return this.e;
    }

    private void k() {
        if (this.q) {
            if (this.f == null) {
                this.f = new ImageView(getContext());
                addView(this.f, new LayoutParams(-1, -1));
                this.f.setVisibility(4);
            }
            final ImageView cacheImageView = this.f;
            final RelativeLayout mapLoadingLayout = j();
            cacheImageView.setVisibility(4);
            mapLoadingLayout.setVisibility(0);
            if (this.g.booleanValue()) {
                this.a.a(new l(this) {
                    final /* synthetic */ AirMapView c;

                    public final void a(Bitmap bitmap) {
                        cacheImageView.setImageBitmap(bitmap);
                        cacheImageView.setVisibility(0);
                        mapLoadingLayout.setVisibility(4);
                    }
                });
                return;
            }
            return;
        }
        if (this.f != null) {
            ((ViewGroup) this.f.getParent()).removeView(this.f);
            this.f = null;
        }
        if (this.g.booleanValue()) {
            if (this.d != null) {
                ((ViewGroup) this.d.getParent()).removeView(this.d);
                this.d = null;
            }
            if (this.e != null) {
                ((ViewGroup) this.e.getParent()).removeView(this.e);
                this.e = null;
            }
        }
    }

    public final void a(MotionEvent ev) {
        this.y.pushEvent(this.D, this, "onPanDrag", a(this.a.c().a(new Point((int) ev.getX(), (int) ev.getY()))));
    }
}
