package com.facebook.react.views.modal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnShowListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.l;
import com.facebook.react.bridge.v;
import com.facebook.react.h.d;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.aa;
import com.facebook.react.uimanager.events.c;
import com.facebook.react.uimanager.g;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class ReactModalHostView extends ViewGroup implements v {
    private a a;
    @Nullable
    private Dialog b;
    private boolean c;
    private String d;
    private boolean e;
    private boolean f;
    @Nullable
    private OnShowListener g;
    @Nullable
    private b h;

    public interface b {
        void a();
    }

    static class a extends ReactViewGroup implements aa {
        private final g a = new g(this);

        public a(Context context) {
            super(context);
        }

        protected final void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            if (getChildCount() > 0) {
                final int viewTag = getChildAt(0).getId();
                ai reactContext = (ai) getContext();
                final int i = w;
                final int i2 = h;
                reactContext.e(new l(this, reactContext) {
                    final /* synthetic */ a d;

                    public final void a() {
                        ((UIManagerModule) ((ai) this.d.getContext()).b(UIManagerModule.class)).updateNodeSize(viewTag, i, i2);
                    }
                });
            }
        }

        public final boolean onInterceptTouchEvent(MotionEvent event) {
            this.a.b(event, e());
            return super.onInterceptTouchEvent(event);
        }

        public final boolean onTouchEvent(MotionEvent event) {
            this.a.b(event, e());
            super.onTouchEvent(event);
            return true;
        }

        public final void a(MotionEvent androidEvent) {
            this.a.a(androidEvent, e());
        }

        public final void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }

        private c e() {
            return ((UIManagerModule) ((ai) getContext()).b(UIManagerModule.class)).getEventDispatcher();
        }
    }

    public ReactModalHostView(Context context) {
        super(context);
        ((ai) context).a((v) this);
        this.a = new a(context);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    public void addView(View child, int index) {
        this.a.addView(child, index);
    }

    public int getChildCount() {
        return this.a.getChildCount();
    }

    public View getChildAt(int index) {
        return this.a.getChildAt(index);
    }

    public void removeView(View child) {
        this.a.removeView(child);
    }

    public void removeViewAt(int index) {
        this.a.removeView(getChildAt(index));
    }

    public void addChildrenForAccessibility(ArrayList<View> arrayList) {
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return false;
    }

    public final void a() {
        ((ai) getContext()).b((v) this);
        c();
    }

    private void c() {
        if (this.b != null) {
            this.b.dismiss();
            this.b = null;
            ((ViewGroup) this.a.getParent()).removeViewAt(0);
        }
    }

    protected final void a(b listener) {
        this.h = listener;
    }

    protected final void a(OnShowListener listener) {
        this.g = listener;
    }

    protected final void a(boolean transparent) {
        this.c = transparent;
    }

    protected final void a(String animationType) {
        this.d = animationType;
        this.f = true;
    }

    protected final void b(boolean hardwareAccelerated) {
        this.e = hardwareAccelerated;
        this.f = true;
    }

    public void onHostResume() {
        b();
    }

    public void onHostPause() {
        c();
    }

    public void onHostDestroy() {
        a();
    }

    protected final void b() {
        if (this.b != null) {
            if (this.f) {
                c();
            } else {
                d();
                return;
            }
        }
        this.f = false;
        int theme = d.Theme_FullScreenDialog;
        if (this.d.equals("fade")) {
            theme = d.Theme_FullScreenDialogAnimatedFade;
        } else if (this.d.equals("slide")) {
            theme = d.Theme_FullScreenDialogAnimatedSlide;
        }
        this.b = new Dialog(getContext(), theme);
        Dialog dialog = this.b;
        View frameLayout = new FrameLayout(getContext());
        frameLayout.addView(this.a);
        frameLayout.setFitsSystemWindows(true);
        dialog.setContentView(frameLayout);
        d();
        this.b.setOnShowListener(this.g);
        this.b.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ ReactModalHostView a;

            {
                this.a = this$0;
            }

            public final boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (event.getAction() == 1) {
                    if (keyCode == 4) {
                        com.facebook.infer.annotation.a.a(this.a.h, "setOnRequestCloseListener must be called by the manager");
                        this.a.h.a();
                        return true;
                    }
                    Activity currentActivity = ((ai) this.a.getContext()).j();
                    if (currentActivity != null) {
                        return currentActivity.onKeyUp(keyCode, event);
                    }
                }
                return false;
            }
        });
        this.b.getWindow().setSoftInputMode(16);
        if (this.e) {
            this.b.getWindow().addFlags(16777216);
        }
        this.b.show();
    }

    private void d() {
        com.facebook.infer.annotation.a.a(this.b, "mDialog must exist when we call updateProperties");
        if (this.c) {
            this.b.getWindow().clearFlags(2);
            return;
        }
        this.b.getWindow().setDimAmount(0.5f);
        this.b.getWindow().setFlags(2, 2);
    }
}
