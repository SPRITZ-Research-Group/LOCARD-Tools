package com.facebook.react.views.toolbar;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.common.e;
import com.facebook.react.h;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.c;
import com.facebook.react.uimanager.p;
import com.facebook.react.views.toolbar.a.a;
import java.util.Map;
import javax.annotation.Nullable;

public class ReactToolbarManager extends ViewGroupManager<ReactToolbar> {
    private static final String REACT_CLASS = "ToolbarAndroid";

    public String getName() {
        return REACT_CLASS;
    }

    protected ReactToolbar createViewInstance(ae reactContext) {
        return new ReactToolbar(reactContext);
    }

    @ReactProp(name = "logo")
    public void setLogo(ReactToolbar view, @Nullable am logo) {
        view.a(logo);
    }

    @ReactProp(name = "navIcon")
    public void setNavIcon(ReactToolbar view, @Nullable am navIcon) {
        view.b(navIcon);
    }

    @ReactProp(name = "overflowIcon")
    public void setOverflowIcon(ReactToolbar view, @Nullable am overflowIcon) {
        view.c(overflowIcon);
    }

    @ReactProp(name = "rtl")
    public void setRtl(ReactToolbar view, boolean rtl) {
        view.setLayoutDirection(rtl ? 1 : 0);
    }

    @ReactProp(name = "subtitle")
    public void setSubtitle(ReactToolbar view, @Nullable String subtitle) {
        view.setSubtitle((CharSequence) subtitle);
    }

    @ReactProp(customType = "Color", name = "subtitleColor")
    public void setSubtitleColor(ReactToolbar view, @Nullable Integer subtitleColor) {
        int[] defaultColors = getDefaultColors(view.getContext());
        if (subtitleColor != null) {
            view.setSubtitleTextColor(subtitleColor.intValue());
        } else {
            view.setSubtitleTextColor(defaultColors[1]);
        }
    }

    @ReactProp(name = "title")
    public void setTitle(ReactToolbar view, @Nullable String title) {
        view.setTitle((CharSequence) title);
    }

    @ReactProp(customType = "Color", name = "titleColor")
    public void setTitleColor(ReactToolbar view, @Nullable Integer titleColor) {
        int[] defaultColors = getDefaultColors(view.getContext());
        if (titleColor != null) {
            view.setTitleTextColor(titleColor.intValue());
        } else {
            view.setTitleTextColor(defaultColors[0]);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "contentInsetStart")
    public void setContentInsetStart(ReactToolbar view, float insetStart) {
        int inset;
        if (Float.isNaN(insetStart)) {
            inset = getDefaultContentInsets(view.getContext())[0];
        } else {
            inset = Math.round(p.a(insetStart));
        }
        view.setContentInsetsRelative(inset, view.o());
    }

    @ReactProp(defaultFloat = Float.NaN, name = "contentInsetEnd")
    public void setContentInsetEnd(ReactToolbar view, float insetEnd) {
        int inset;
        if (Float.isNaN(insetEnd)) {
            inset = getDefaultContentInsets(view.getContext())[1];
        } else {
            inset = Math.round(p.a(insetEnd));
        }
        view.setContentInsetsRelative(view.n(), inset);
    }

    @ReactProp(name = "nativeActions")
    public void setActions(ReactToolbar view, @Nullable al actions) {
        view.a(actions);
    }

    protected void addEventEmitters(ae reactContext, final ReactToolbar view) {
        final c mEventDispatcher = ((UIManagerModule) reactContext.b(UIManagerModule.class)).getEventDispatcher();
        view.setNavigationOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReactToolbarManager c;

            public final void onClick(View v) {
                mEventDispatcher.a(new a(view.getId(), -1));
            }
        });
        view.setOnMenuItemClickListener(new Toolbar.c(this) {
            final /* synthetic */ ReactToolbarManager c;

            public final boolean a(MenuItem menuItem) {
                mEventDispatcher.a(new a(view.getId(), menuItem.getOrder()));
                return true;
            }
        });
    }

    @Nullable
    public Map<String, Object> getExportedViewConstants() {
        return e.a("ShowAsAction", e.a("never", Integer.valueOf(0), "always", Integer.valueOf(2), "ifRoom", Integer.valueOf(1)));
    }

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    private int[] getDefaultContentInsets(Context context) {
        Theme theme = context.getTheme();
        TypedArray toolbarStyle = null;
        TypedArray contentInsets = null;
        try {
            toolbarStyle = theme.obtainStyledAttributes(new int[]{h.a.toolbarStyle});
            contentInsets = theme.obtainStyledAttributes(toolbarStyle.getResourceId(0, 0), new int[]{h.a.contentInsetStart, h.a.contentInsetEnd});
            int contentInsetStart = contentInsets.getDimensionPixelSize(0, 0);
            int contentInsetEnd = contentInsets.getDimensionPixelSize(1, 0);
            int[] iArr = new int[]{contentInsetStart, contentInsetEnd};
            return iArr;
        } finally {
            recycleQuietly(toolbarStyle);
            recycleQuietly(contentInsets);
        }
    }

    private static int[] getDefaultColors(Context context) {
        Theme theme = context.getTheme();
        TypedArray toolbarStyle = null;
        TypedArray textAppearances = null;
        TypedArray titleTextAppearance = null;
        TypedArray subtitleTextAppearance = null;
        try {
            toolbarStyle = theme.obtainStyledAttributes(new int[]{h.a.toolbarStyle});
            textAppearances = theme.obtainStyledAttributes(toolbarStyle.getResourceId(0, 0), new int[]{h.a.titleTextAppearance, h.a.subtitleTextAppearance});
            int titleTextAppearanceResId = textAppearances.getResourceId(0, 0);
            int subtitleTextAppearanceResId = textAppearances.getResourceId(1, 0);
            titleTextAppearance = theme.obtainStyledAttributes(titleTextAppearanceResId, new int[]{16842904});
            subtitleTextAppearance = theme.obtainStyledAttributes(subtitleTextAppearanceResId, new int[]{16842904});
            int titleTextColor = titleTextAppearance.getColor(0, -16777216);
            int subtitleTextColor = subtitleTextAppearance.getColor(0, -16777216);
            int[] iArr = new int[]{titleTextColor, subtitleTextColor};
            return iArr;
        } finally {
            recycleQuietly(toolbarStyle);
            recycleQuietly(textAppearances);
            recycleQuietly(titleTextAppearance);
            recycleQuietly(subtitleTextAppearance);
        }
    }

    private static void recycleQuietly(@Nullable TypedArray style) {
        if (style != null) {
            style.recycle();
        }
    }
}
