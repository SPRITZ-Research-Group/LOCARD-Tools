package com.facebook.react.views.scroll;

import com.facebook.react.bridge.al;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.p;
import com.facebook.react.views.scroll.c.a;
import com.facebook.react.views.scroll.c.b;
import com.facebook.react.views.scroll.c.c;
import com.facebook.react.views.scroll.c.d;
import javax.annotation.Nullable;

@ReactModule(name = "AndroidHorizontalScrollView")
public class ReactHorizontalScrollViewManager extends ViewGroupManager<ReactHorizontalScrollView> implements b<ReactHorizontalScrollView> {
    protected static final String REACT_CLASS = "AndroidHorizontalScrollView";
    private static final int[] SPACING_TYPES = new int[]{8, 0, 2, 1, 3};
    @Nullable
    private a mFpsListener;

    public ReactHorizontalScrollViewManager() {
        this(null);
    }

    public ReactHorizontalScrollViewManager(@Nullable a fpsListener) {
        this.mFpsListener = null;
        this.mFpsListener = fpsListener;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactHorizontalScrollView createViewInstance(ae context) {
        return new ReactHorizontalScrollView(context, this.mFpsListener);
    }

    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(ReactHorizontalScrollView view, boolean value) {
        view.setScrollEnabled(value);
    }

    @ReactProp(name = "showsHorizontalScrollIndicator")
    public void setShowsHorizontalScrollIndicator(ReactHorizontalScrollView view, boolean value) {
        view.setHorizontalScrollBarEnabled(value);
    }

    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(ReactHorizontalScrollView view, boolean removeClippedSubviews) {
        view.setRemoveClippedSubviews(removeClippedSubviews);
    }

    @ReactProp(name = "sendMomentumEvents")
    public void setSendMomentumEvents(ReactHorizontalScrollView view, boolean sendMomentumEvents) {
        view.setSendMomentumEvents(sendMomentumEvents);
    }

    @ReactProp(name = "scrollPerfTag")
    public void setScrollPerfTag(ReactHorizontalScrollView view, String scrollPerfTag) {
        view.setScrollPerfTag(scrollPerfTag);
    }

    @ReactProp(name = "pagingEnabled")
    public void setPagingEnabled(ReactHorizontalScrollView view, boolean pagingEnabled) {
        view.setPagingEnabled(pagingEnabled);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(ReactHorizontalScrollView view, String value) {
        view.setOverScrollMode(d.a(value));
    }

    public void receiveCommand(ReactHorizontalScrollView scrollView, int commandId, @Nullable al args) {
        c.a(this, scrollView, commandId, args);
    }

    public void scrollTo(ReactHorizontalScrollView scrollView, c data) {
        if (data.c) {
            scrollView.smoothScrollTo(data.a, data.b);
        } else {
            scrollView.scrollTo(data.a, data.b);
        }
    }

    public void scrollToEnd(ReactHorizontalScrollView scrollView, d data) {
        int right = scrollView.getChildAt(0).getWidth() + scrollView.getPaddingRight();
        if (data.a) {
            scrollView.smoothScrollTo(right, scrollView.getScrollY());
        } else {
            scrollView.scrollTo(right, scrollView.getScrollY());
        }
    }

    public void scrollBy(ReactHorizontalScrollView scrollView, a data) {
        int x = scrollView.getScrollX() + data.a;
        int y = scrollView.getScrollY() + data.b;
        if (data.c) {
            scrollView.smoothScrollTo(x, y);
        } else {
            scrollView.scrollTo(x, y);
        }
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "endFillColor")
    public void setBottomFillColor(ReactHorizontalScrollView view, int color) {
        view.setEndFillColor(color);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactHorizontalScrollView view, int index, float borderRadius) {
        if (!com.facebook.yoga.a.a(borderRadius)) {
            borderRadius = p.a(borderRadius);
        }
        if (index == 0) {
            view.setBorderRadius(borderRadius);
        } else {
            view.setBorderRadius(borderRadius, index - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactHorizontalScrollView view, @Nullable String borderStyle) {
        view.setBorderStyle(borderStyle);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactHorizontalScrollView view, int index, float width) {
        if (!com.facebook.yoga.a.a(width)) {
            width = p.a(width);
        }
        view.setBorderWidth(SPACING_TYPES[index], width);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactHorizontalScrollView view, int index, Integer color) {
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
}
