package com.facebook.react.views.swiperefresh;

import android.support.v4.widget.SwipeRefreshLayout.b;
import com.facebook.react.bridge.al;
import com.facebook.react.common.e;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "AndroidSwipeRefreshLayout")
public class SwipeRefreshLayoutManager extends ViewGroupManager<ReactSwipeRefreshLayout> {
    protected static final String REACT_CLASS = "AndroidSwipeRefreshLayout";

    protected ReactSwipeRefreshLayout createViewInstance(ae reactContext) {
        return new ReactSwipeRefreshLayout(reactContext);
    }

    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSwipeRefreshLayout view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @ReactProp(customType = "ColorArray", name = "colors")
    public void setColors(ReactSwipeRefreshLayout view, @Nullable al colors) {
        if (colors != null) {
            int[] colorValues = new int[colors.size()];
            for (int i = 0; i < colors.size(); i++) {
                colorValues[i] = colors.getInt(i);
            }
            view.setColorSchemeColors(colorValues);
            return;
        }
        view.setColorSchemeColors(new int[0]);
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "progressBackgroundColor")
    public void setProgressBackgroundColor(ReactSwipeRefreshLayout view, int color) {
        view.setProgressBackgroundColorSchemeColor(color);
    }

    @ReactProp(defaultInt = 1, name = "size")
    public void setSize(ReactSwipeRefreshLayout view, int size) {
        view.setSize(size);
    }

    @ReactProp(name = "refreshing")
    public void setRefreshing(ReactSwipeRefreshLayout view, boolean refreshing) {
        view.setRefreshing(refreshing);
    }

    @ReactProp(defaultFloat = 0.0f, name = "progressViewOffset")
    public void setProgressViewOffset(ReactSwipeRefreshLayout view, float offset) {
        view.setProgressViewOffset(offset);
    }

    protected void addEventEmitters(final ae reactContext, final ReactSwipeRefreshLayout view) {
        view.setOnRefreshListener(new b(this) {
            final /* synthetic */ SwipeRefreshLayoutManager c;

            public final void onRefresh() {
                ((UIManagerModule) reactContext.b(UIManagerModule.class)).getEventDispatcher().a(new a(view.getId()));
            }
        });
    }

    @Nullable
    public Map<String, Object> getExportedViewConstants() {
        return e.a("SIZE", e.a("DEFAULT", Integer.valueOf(1), "LARGE", Integer.valueOf(0)));
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return e.a().a("topRefresh", e.a("registrationName", "onRefresh")).a();
    }
}
