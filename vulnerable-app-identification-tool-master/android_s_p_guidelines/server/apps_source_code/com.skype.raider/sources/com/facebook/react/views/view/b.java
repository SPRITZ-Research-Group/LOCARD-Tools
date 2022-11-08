package com.facebook.react.views.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import com.facebook.react.ReactRootView;

public final class b {
    private static b d;
    private static boolean e = false;
    private a a;
    private boolean b;
    private Context c;

    class a extends ReactViewGroup {
        final /* synthetic */ b a;

        public a(b this$0, Context context) {
            this.a = this$0;
            super(context);
        }

        public final boolean onTouchEvent(MotionEvent ev) {
            return false;
        }
    }

    public static void a(boolean keyboardInput) {
        e = keyboardInput;
    }

    public static void a() {
        e = false;
    }

    public final void a(View vg) {
        boolean z = true;
        if (!vg.isFocusable()) {
            z = false;
        } else if (this.a == null) {
            LayoutParams layoutParams;
            boolean z2;
            this.c = vg.getContext();
            this.a = new a(this, this.c);
            this.a.setClickable(false);
            this.a.setAlpha(0.9f);
            this.a.setEnabled(false);
            LayoutParams layoutParams2 = this.a.getLayoutParams();
            if (layoutParams2 == null) {
                layoutParams = new LayoutParams(-1, -1);
            } else {
                layoutParams = layoutParams2;
            }
            if (!(vg instanceof ViewGroup) || (vg instanceof ScrollView) || (vg instanceof HorizontalScrollView)) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.b = z2;
            if (!this.b) {
                ((ViewGroup) vg).addView(this.a, -1, layoutParams);
            } else if (vg.getParent() instanceof ViewGroup) {
                ((ViewGroup) vg.getParent()).addView(this.a, -1, layoutParams);
            } else {
                z = false;
            }
        }
        if (z) {
            this.a.setTop(0);
            this.a.setLeft(0);
            this.a.setRight(vg.getRight() - vg.getLeft());
            this.a.setBottom(vg.getBottom() - vg.getTop());
        }
    }

    public final void a(ViewGroup vg) {
        if (this.a != null) {
            if (!this.b) {
                vg.bringChildToFront(this.a);
            } else if (vg.getParent() != null) {
                vg.getParent().bringChildToFront(this.a);
            }
        }
    }

    public final void b(boolean didTakeFocus) {
        if (e && this.a != null) {
            c();
            if (didTakeFocus) {
                this.a.setBorderWidth(8, 3.0f);
                this.a.setBorderStyle("SOLID");
                this.a.setBorderColor(8, -65536.0f, 255.0f);
                d = this;
                return;
            }
            b();
        }
    }

    public final void b() {
        if (this.a != null) {
            this.a.setBorderWidth(8, 0.0f);
            d = null;
        }
    }

    public static void c() {
        if (d != null) {
            d.b();
        }
    }

    public final boolean a(View startingView, int keyCode, KeyEvent event) {
        boolean eventWasHandled = false;
        if (keyCode == 62 || keyCode == 66) {
            if ((event.getModifiers() & 1) == 1) {
                eventWasHandled = a(startingView, startingView.getParent(), 1050);
            } else {
                eventWasHandled = a(startingView, startingView.getParent(), 0);
            }
            b();
        }
        return eventWasHandled;
    }

    private boolean a(View startingView, ViewParent vp, int delayMs) {
        while (vp != null) {
            if (vp instanceof ReactRootView) {
                int[] tapWindowLocation = new int[2];
                startingView.getLocationInWindow(tapWindowLocation);
                ((ReactRootView) vp).offsetDescendantRectToMyCoords(startingView, new Rect());
                float tapX = (float) tapWindowLocation[0];
                float tapY = (float) tapWindowLocation[1];
                float offsetX = (float) (startingView.getWidth() / 2);
                float offsetY = -14.0f * this.c.getResources().getDisplayMetrics().density;
                ReactRootView reactRootView = (ReactRootView) vp;
                reactRootView.onInterceptTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, tapX + offsetX, tapY + offsetY, 0));
                final float f = tapX;
                final float f2 = offsetX;
                final float f3 = tapY;
                final float f4 = offsetY;
                final ReactRootView reactRootView2 = reactRootView;
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                    final /* synthetic */ b f;

                    public final void run() {
                        reactRootView2.onInterceptTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, f + f2, f3 + f4, 0));
                    }
                }, (long) delayMs);
                return true;
            }
            vp = vp.getParent();
        }
        return false;
    }
}
