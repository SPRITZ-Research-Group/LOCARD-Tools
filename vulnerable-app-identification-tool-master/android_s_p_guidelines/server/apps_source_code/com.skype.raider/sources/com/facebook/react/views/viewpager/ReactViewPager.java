package com.facebook.react.views.viewpager;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.e;
import android.support.v4.view.n;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ai;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.c;
import java.util.ArrayList;
import java.util.List;

public class ReactViewPager extends ViewPager {
    private final c d;
    private boolean e;
    private boolean f = true;

    private class a extends n {
        final /* synthetic */ ReactViewPager a;
        private final List<View> b;
        private boolean c;

        private a(ReactViewPager reactViewPager) {
            this.a = reactViewPager;
            this.b = new ArrayList();
            this.c = false;
        }

        /* synthetic */ a(ReactViewPager x0, byte b) {
            this(x0);
        }

        final void a(View child, int index) {
            this.b.add(index, child);
            b();
            this.a.setOffscreenPageLimit(this.b.size());
        }

        final void a(int index) {
            this.b.remove(index);
            b();
            this.a.setOffscreenPageLimit(this.b.size());
        }

        final void a(List<View> views) {
            this.b.clear();
            this.b.addAll(views);
            b();
            this.c = false;
        }

        final void a(ViewPager pager) {
            this.b.clear();
            pager.removeAllViews();
            this.c = true;
        }

        final View b(int index) {
            return (View) this.b.get(index);
        }

        public final int a() {
            return this.b.size();
        }

        public final int a(Object object) {
            if (this.c || !this.b.contains(object)) {
                return -2;
            }
            return this.b.indexOf(object);
        }

        public final Object a(ViewGroup container, int position) {
            View view = (View) this.b.get(position);
            container.addView(view, 0, this.a.generateDefaultLayoutParams());
            return view;
        }

        public final void a(ViewGroup container, Object object) {
            container.removeView((View) object);
        }

        public final boolean a(View view, Object object) {
            return view == object;
        }
    }

    private class b implements e {
        final /* synthetic */ ReactViewPager a;

        private b(ReactViewPager reactViewPager) {
            this.a = reactViewPager;
        }

        /* synthetic */ b(ReactViewPager x0, byte b) {
            this(x0);
        }

        public final void a(int position, float positionOffset) {
            this.a.d.a(new a(this.a.getId(), position, positionOffset));
        }

        public final void b(int position) {
            if (!this.a.e) {
                this.a.d.a(new c(this.a.getId(), position));
            }
        }

        public final void a(int state) {
            String pageScrollState;
            switch (state) {
                case 0:
                    pageScrollState = "idle";
                    break;
                case 1:
                    pageScrollState = "dragging";
                    break;
                case 2:
                    pageScrollState = "settling";
                    break;
                default:
                    throw new IllegalStateException("Unsupported pageScrollState");
            }
            this.a.d.a(new b(this.a.getId(), pageScrollState));
        }
    }

    public ReactViewPager(ai reactContext) {
        super(reactContext);
        this.d = ((UIManagerModule) reactContext.b(UIManagerModule.class)).getEventDispatcher();
        this.e = false;
        setOnPageChangeListener(new b());
        setAdapter(new a());
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!this.f || !super.onInterceptTouchEvent(ev)) {
            return false;
        }
        com.facebook.react.uimanager.events.e.a(this, ev);
        return true;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (this.f) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    public void setCurrentItemFromJs(int item, boolean animated) {
        this.e = true;
        setCurrentItem(item, animated);
        this.e = false;
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        this.f = scrollEnabled;
    }

    final void a(View child, int index) {
        ((a) super.a()).a(child, index);
    }

    final void b(int index) {
        ((a) super.a()).a(index);
    }

    final int e() {
        return ((a) super.a()).a();
    }

    final View c(int index) {
        return ((a) super.a()).b(index);
    }

    public void setViews(List<View> views) {
        ((a) super.a()).a((List) views);
    }

    public final void f() {
        ((a) super.a()).a((ViewPager) this);
    }

    public final /* bridge */ /* synthetic */ n a() {
        return (a) super.a();
    }
}
