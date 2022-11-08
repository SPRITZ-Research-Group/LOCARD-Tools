package com.facebook.react;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.ap;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.uimanager.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.aa;
import com.facebook.react.uimanager.g;
import com.facebook.react.uimanager.p;
import javax.annotation.Nullable;

public class ReactRootView extends SizeMonitoringFrameLayout implements aa {
    @Nullable
    private l a;
    @Nullable
    private String b;
    @Nullable
    private Bundle c;
    @Nullable
    private a d;
    @Nullable
    private b e;
    private int f;
    private boolean g;
    private final g h = new g(this);

    private class a implements OnGlobalLayoutListener {
        final /* synthetic */ ReactRootView a;
        private final Rect b;
        private final int c;
        private int d = 0;
        private int e = 0;
        private DisplayMetrics f = new DisplayMetrics();
        private DisplayMetrics g = new DisplayMetrics();

        a(ReactRootView reactRootView) {
            this.a = reactRootView;
            com.facebook.react.uimanager.b.a(reactRootView.getContext().getApplicationContext());
            this.b = new Rect();
            this.c = (int) p.a(60.0f);
        }

        public final void onGlobalLayout() {
            if (this.a.a != null && this.a.g && this.a.a.g() != null) {
                this.a.getRootView().getWindowVisibleDisplayFrame(this.b);
                int i = com.facebook.react.uimanager.b.a().heightPixels - this.b.bottom;
                if (this.d != i && i > this.c) {
                    this.d = i;
                    ar writableNativeMap = new WritableNativeMap();
                    ar writableNativeMap2 = new WritableNativeMap();
                    writableNativeMap2.putDouble("screenY", (double) p.b((float) this.b.bottom));
                    writableNativeMap2.putDouble("screenX", (double) p.b((float) this.b.left));
                    writableNativeMap2.putDouble("width", (double) p.b((float) this.b.width()));
                    writableNativeMap2.putDouble("height", (double) p.b((float) this.d));
                    writableNativeMap.putMap("endCoordinates", writableNativeMap2);
                    a("keyboardDidShow", writableNativeMap);
                } else if (this.d != 0 && i <= this.c) {
                    this.d = 0;
                    a("keyboardDidHide", null);
                }
                i = ((WindowManager) this.a.getContext().getSystemService("window")).getDefaultDisplay().getRotation();
                if (this.e != i) {
                    double d;
                    String str;
                    boolean z;
                    this.e = i;
                    switch (i) {
                        case 0:
                            d = 0.0d;
                            str = "portrait-primary";
                            z = false;
                            break;
                        case 1:
                            z = true;
                            str = "landscape-primary";
                            d = -90.0d;
                            break;
                        case 2:
                            d = 180.0d;
                            str = "portrait-secondary";
                            z = false;
                            break;
                        case 3:
                            z = true;
                            str = "landscape-secondary";
                            d = 90.0d;
                            break;
                    }
                    ar writableNativeMap3 = new WritableNativeMap();
                    writableNativeMap3.putString("name", str);
                    writableNativeMap3.putDouble("rotationDegrees", d);
                    writableNativeMap3.putBoolean("isLandscape", z);
                    a("namedOrientationDidChange", writableNativeMap3);
                }
                com.facebook.react.uimanager.b.b(this.a.getContext());
                if (!a(this.f, com.facebook.react.uimanager.b.a()) || !a(this.g, com.facebook.react.uimanager.b.b())) {
                    this.f.setTo(com.facebook.react.uimanager.b.a());
                    this.g.setTo(com.facebook.react.uimanager.b.b());
                    ((DeviceInfoModule) this.a.a.g().b(DeviceInfoModule.class)).emitUpdateDimensionsEvent();
                }
            }
        }

        private static boolean a(DisplayMetrics displayMetrics, DisplayMetrics otherMetrics) {
            if (VERSION.SDK_INT >= 17) {
                return displayMetrics.equals(otherMetrics);
            }
            return displayMetrics.widthPixels == otherMetrics.widthPixels && displayMetrics.heightPixels == otherMetrics.heightPixels && displayMetrics.density == otherMetrics.density && displayMetrics.densityDpi == otherMetrics.densityDpi && displayMetrics.scaledDensity == otherMetrics.scaledDensity && displayMetrics.xdpi == otherMetrics.xdpi && displayMetrics.ydpi == otherMetrics.ydpi;
        }

        private void a(String eventName, @Nullable ar params) {
            if (this.a.a != null) {
                ((RCTDeviceEventEmitter) this.a.a.g().a(RCTDeviceEventEmitter.class)).emit(eventName, params);
            }
        }
    }

    public interface b {
    }

    public ReactRootView(Context context) {
        super(context);
    }

    public ReactRootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactRootView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        com.facebook.systrace.a.a("ReactRootView.onMeasure");
        try {
            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
            if (!(this.a == null || this.g)) {
                d();
            }
            com.facebook.systrace.a.a();
        } catch (Throwable th) {
            com.facebook.systrace.a.a();
        }
    }

    public final void a(MotionEvent androidEvent) {
        if (this.a == null || !this.g || this.a.g() == null) {
            FLog.w("React", "Unable to dispatch touch to JS as the catalyst instance has not been attached");
            return;
        }
        this.h.a(androidEvent, ((UIManagerModule) this.a.g().b(UIManagerModule.class)).getEventDispatcher());
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        b(ev);
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        b(ev);
        super.onTouchEvent(ev);
        return true;
    }

    private void b(MotionEvent event) {
        if (this.a == null || !this.g || this.a.g() == null) {
            FLog.w("React", "Unable to dispatch touch to JS as the catalyst instance has not been attached");
            return;
        }
        this.h.b(event, ((UIManagerModule) this.a.g().b(UIManagerModule.class)).getEventDispatcher());
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.g) {
            getViewTreeObserver().addOnGlobalLayoutListener(c());
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.g) {
            getViewTreeObserver().removeOnGlobalLayoutListener(c());
        }
    }

    public final void a(l reactInstanceManager, String moduleName) {
        com.facebook.systrace.a.a("startReactApplication");
        try {
            ap.b();
            com.facebook.infer.annotation.a.a(this.a == null, "This root view has already been attached to a catalyst instance manager");
            this.a = reactInstanceManager;
            this.b = moduleName;
            this.c = null;
            if (!this.a.d()) {
                this.a.c();
            }
            d();
        } finally {
            com.facebook.systrace.a.a();
        }
    }

    public final void a() {
        if (this.a != null && this.g) {
            this.a.b(this);
            this.g = false;
        }
    }

    public void setEventListener(b eventListener) {
        this.e = eventListener;
    }

    public void setAppProperties(@Nullable Bundle appProperties) {
        ap.b();
        this.c = appProperties;
        b();
    }

    final void b() {
        com.facebook.systrace.a.a("ReactRootView.runApplication");
        try {
            if (this.a == null || !this.g) {
                com.facebook.systrace.a.a();
                return;
            }
            ai reactContext = this.a.g();
            if (reactContext == null) {
                com.facebook.systrace.a.a();
                return;
            }
            CatalystInstance catalystInstance = reactContext.a();
            WritableNativeMap appParams = new WritableNativeMap();
            appParams.putDouble("rootTag", (double) this.f);
            Bundle appProperties = this.c;
            if (appProperties != null) {
                appParams.putMap("initialProps", com.facebook.react.bridge.b.a(appProperties));
            }
            ((AppRegistry) catalystInstance.getJSModule(AppRegistry.class)).runApplication((String) com.facebook.infer.annotation.a.a(this.b), appParams);
            com.facebook.systrace.a.a();
        } catch (Throwable th) {
            com.facebook.systrace.a.a();
        }
    }

    private a c() {
        if (this.d == null) {
            this.d = new a(this);
        }
        return this.d;
    }

    private void d() {
        com.facebook.systrace.a.a("attachToReactInstanceManager");
        try {
            if (!this.g) {
                this.g = true;
                ((l) com.facebook.infer.annotation.a.a(this.a)).a(this);
                getViewTreeObserver().addOnGlobalLayoutListener(c());
                com.facebook.systrace.a.a();
            }
        } finally {
            com.facebook.systrace.a.a();
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        com.facebook.infer.annotation.a.a(!this.g, "The application this ReactRootView was rendering was not unmounted before the ReactRootView was garbage collected. This usually means that your application is leaking large amounts of memory. To solve this, make sure to call ReactRootView#unmountReactApplication in the onDestroy() of your hosting Activity or in the onDestroyView() of your hosting Fragment.");
    }

    public void setRootViewTag(int rootViewTag) {
        this.f = rootViewTag;
    }
}
