package com.facebook.react.views.drawer;

import android.os.Build.VERSION;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.c;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.n;
import com.facebook.react.common.e;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.p;
import com.facebook.react.views.drawer.a.b;
import com.facebook.react.views.drawer.a.d;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "AndroidDrawerLayout")
public class ReactDrawerLayoutManager extends ViewGroupManager<a> {
    public static final int CLOSE_DRAWER = 2;
    public static final int OPEN_DRAWER = 1;
    protected static final String REACT_CLASS = "AndroidDrawerLayout";

    public static class a implements c {
        private final DrawerLayout a;
        private final com.facebook.react.uimanager.events.c b;

        public a(DrawerLayout drawerLayout, com.facebook.react.uimanager.events.c eventDispatcher) {
            this.a = drawerLayout;
            this.b = eventDispatcher;
        }

        public final void a(float v) {
            this.b.a(new com.facebook.react.views.drawer.a.c(this.a.getId(), v));
        }

        public final void a() {
            this.b.a(new b(this.a.getId()));
        }

        public final void b() {
            this.b.a(new com.facebook.react.views.drawer.a.a(this.a.getId()));
        }

        public final void a(int i) {
            this.b.a(new d(this.a.getId(), i));
        }
    }

    public String getName() {
        return REACT_CLASS;
    }

    protected void addEventEmitters(ae reactContext, a view) {
        view.setDrawerListener(new a(view, ((UIManagerModule) reactContext.b(UIManagerModule.class)).getEventDispatcher()));
    }

    protected a createViewInstance(ae context) {
        return new a(context);
    }

    @ReactProp(defaultInt = 8388611, name = "drawerPosition")
    public void setDrawerPosition(a view, int drawerPosition) {
        if (8388611 == drawerPosition || 8388613 == drawerPosition) {
            view.e(drawerPosition);
            return;
        }
        throw new n("Unknown drawerPosition " + drawerPosition);
    }

    @ReactProp(defaultFloat = Float.NaN, name = "drawerWidth")
    public void getDrawerWidth(a view, float width) {
        int widthInPx;
        if (Float.isNaN(width)) {
            widthInPx = -1;
        } else {
            widthInPx = Math.round(p.a(width));
        }
        view.f(widthInPx);
    }

    @ReactProp(name = "drawerLockMode")
    public void setDrawerLockMode(a view, @Nullable String drawerLockMode) {
        if (drawerLockMode == null || "unlocked".equals(drawerLockMode)) {
            view.setDrawerLockMode(0);
        } else if ("locked-closed".equals(drawerLockMode)) {
            view.setDrawerLockMode(1);
        } else if ("locked-open".equals(drawerLockMode)) {
            view.setDrawerLockMode(2);
        } else {
            throw new n("Unknown drawerLockMode " + drawerLockMode);
        }
    }

    public void setElevation(a view, float elevation) {
        if (VERSION.SDK_INT >= 21) {
            try {
                a.class.getMethod("setDrawerElevation", new Class[]{Float.TYPE}).invoke(view, new Object[]{Float.valueOf(p.a(elevation))});
            } catch (Throwable ex) {
                FLog.w("React", "setDrawerElevation is not available in this version of the support lib.", ex);
            }
        }
    }

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return e.a("openDrawer", Integer.valueOf(1), "closeDrawer", Integer.valueOf(2));
    }

    public void receiveCommand(a root, int commandId, @Nullable al args) {
        switch (commandId) {
            case 1:
                root.c();
                return;
            case 2:
                root.d();
                return;
            default:
                return;
        }
    }

    @Nullable
    public Map getExportedViewConstants() {
        return e.a("DrawerPosition", e.a("Left", Integer.valueOf(8388611), "Right", Integer.valueOf(8388613)));
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a("topDrawerSlide", e.a("registrationName", "onDrawerSlide"), "topDrawerOpened", e.a("registrationName", "onDrawerOpen"), "topDrawerClosed", e.a("registrationName", "onDrawerClose"), "topDrawerStateChanged", e.a("registrationName", "onDrawerStateChanged"));
    }

    public void addView(a parent, View child, int index) {
        if (getChildCount(parent) >= 2) {
            throw new n("The Drawer cannot have more than two children");
        } else if (index == 0 || index == 1) {
            parent.addView(child, index);
            parent.e();
        } else {
            throw new n("The only valid indices for drawer's child are 0 or 1. Got " + index + " instead.");
        }
    }
}
