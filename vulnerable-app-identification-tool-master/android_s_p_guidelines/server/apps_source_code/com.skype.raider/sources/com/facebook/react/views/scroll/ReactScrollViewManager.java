package com.facebook.react.views.scroll;

import com.facebook.react.bridge.al;
import com.facebook.react.common.e;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.p;
import com.facebook.react.views.scroll.c.b;
import com.facebook.react.views.scroll.c.c;
import com.facebook.react.views.scroll.c.d;
import com.facebook.yoga.a;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "RCTScrollView")
public class ReactScrollViewManager extends ViewGroupManager<ReactScrollView> implements b<ReactScrollView> {
    protected static final String REACT_CLASS = "RCTScrollView";
    private static final int[] SPACING_TYPES = new int[]{8, 0, 2, 1, 3};
    @Nullable
    private a mFpsListener;

    public ReactScrollViewManager() {
        this(null);
    }

    public ReactScrollViewManager(@Nullable a fpsListener) {
        this.mFpsListener = null;
        this.mFpsListener = fpsListener;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactScrollView createViewInstance(ae context) {
        return new ReactScrollView(context, this.mFpsListener);
    }

    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(ReactScrollView view, boolean value) {
        view.setScrollEnabled(value);
    }

    @ReactProp(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(ReactScrollView view, boolean value) {
        view.setVerticalScrollBarEnabled(value);
    }

    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(ReactScrollView view, boolean removeClippedSubviews) {
        view.setRemoveClippedSubviews(removeClippedSubviews);
    }

    @ReactProp(name = "sendMomentumEvents")
    public void setSendMomentumEvents(ReactScrollView view, boolean sendMomentumEvents) {
        view.setSendMomentumEvents(sendMomentumEvents);
    }

    @ReactProp(name = "scrollPerfTag")
    public void setScrollPerfTag(ReactScrollView view, String scrollPerfTag) {
        view.setScrollPerfTag(scrollPerfTag);
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "endFillColor")
    public void setBottomFillColor(ReactScrollView view, int color) {
        view.setEndFillColor(color);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(ReactScrollView view, String value) {
        view.setOverScrollMode(d.a(value));
    }

    public void receiveCommand(ReactScrollView scrollView, int commandId, @Nullable al args) {
        c.a(this, scrollView, commandId, args);
    }

    public void scrollTo(ReactScrollView scrollView, c data) {
        if (data.c) {
            scrollView.smoothScrollTo(data.a, data.b);
        } else {
            scrollView.scrollTo(data.a, data.b);
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactScrollView view, int index, float borderRadius) {
        if (!a.a(borderRadius)) {
            borderRadius = p.a(borderRadius);
        }
        if (index == 0) {
            view.setBorderRadius(borderRadius);
        } else {
            view.setBorderRadius(borderRadius, index - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactScrollView view, @Nullable String borderStyle) {
        view.setBorderStyle(borderStyle);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactScrollView view, int index, float width) {
        if (!a.a(width)) {
            width = p.a(width);
        }
        view.setBorderWidth(SPACING_TYPES[index], width);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactScrollView view, int index, Integer color) {
        float rgbComponent;
        float alphaComponent = Float.NaN;
        if (color == null) {
            rgbComponent = Float.NaN;
        } else {
            rgbComponent = (float) (color.intValue() & 16777215);
        }
        if (color != null) {
            alphaComponent = (float) (color.intValue() >>> 24);
        }
        view.setBorderColor(SPACING_TYPES[index], rgbComponent, alphaComponent);
    }

    public void scrollToEnd(ReactScrollView scrollView, d data) {
        int bottom = scrollView.getChildAt(0).getHeight() + scrollView.getPaddingBottom();
        if (data.a) {
            scrollView.smoothScrollTo(scrollView.getScrollX(), bottom);
        } else {
            scrollView.scrollTo(scrollView.getScrollX(), bottom);
        }
    }

    public void scrollBy(ReactScrollView scrollView, c.a data) {
        int x = scrollView.getScrollX() + data.a;
        int y = scrollView.getScrollY() + data.b;
        if (data.c) {
            scrollView.smoothScrollTo(x, y);
        } else {
            scrollView.scrollTo(x, y);
        }
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return createExportedCustomDirectEventTypeConstants();
    }

    public static Map createExportedCustomDirectEventTypeConstants() {
        return e.a().a(f.SCROLL.a(), e.a("registrationName", "onScroll")).a(f.BEGIN_DRAG.a(), e.a("registrationName", "onScrollBeginDrag")).a(f.END_DRAG.a(), e.a("registrationName", "onScrollEndDrag")).a(f.ANIMATION_END.a(), e.a("registrationName", "onScrollAnimationEnd")).a(f.MOMENTUM_BEGIN.a(), e.a("registrationName", "onMomentumScrollBegin")).a(f.MOMENTUM_END.a(), e.a("registrationName", "onMomentumScrollEnd")).a(f.SCROLL_POSITION_SET.a(), e.a("registrationName", "onScrollPositionSet")).a();
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return e.a("scrollTo", Integer.valueOf(1), "scrollToEnd", Integer.valueOf(2), "scrollBy", Integer.valueOf(3));
    }
}
