package com.facebook.react.views.text;

import android.text.TextUtils.TruncateAt;
import com.facebook.react.bridge.n;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.e;
import com.facebook.react.uimanager.p;
import com.facebook.yoga.a;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "RCTText")
public class ReactTextViewManager extends BaseViewManager<ReactTextView, g> implements e {
    @VisibleForTesting
    public static final String REACT_CLASS = "RCTText";
    private static final int[] SPACING_TYPES = new int[]{8, 0, 2, 1, 3};

    public String getName() {
        return REACT_CLASS;
    }

    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return com.facebook.react.common.e.a().a("topInlineViewLayout", com.facebook.react.common.e.a("registrationName", "onInlineViewLayout")).a();
    }

    public ReactTextView createViewInstance(ae context) {
        return new ReactTextView(context);
    }

    @ReactProp(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public void setNumberOfLines(ReactTextView view, int numberOfLines) {
        view.setNumberOfLines(numberOfLines);
    }

    @ReactProp(name = "ellipsizeMode")
    public void setEllipsizeMode(ReactTextView view, @Nullable String ellipsizeMode) {
        if (ellipsizeMode == null || ellipsizeMode.equals("tail")) {
            view.setEllipsizeLocation(TruncateAt.END);
        } else if (ellipsizeMode.equals("head")) {
            view.setEllipsizeLocation(TruncateAt.START);
        } else if (ellipsizeMode.equals("middle")) {
            view.setEllipsizeLocation(TruncateAt.MIDDLE);
        } else {
            throw new n("Invalid ellipsizeMode: " + ellipsizeMode);
        }
    }

    @ReactProp(name = "textAlignVertical")
    public void setTextAlignVertical(ReactTextView view, @Nullable String textAlignVertical) {
        if (textAlignVertical == null || "auto".equals(textAlignVertical)) {
            view.a(0);
        } else if ("top".equals(textAlignVertical)) {
            view.a(48);
        } else if ("bottom".equals(textAlignVertical)) {
            view.a(80);
        } else if ("center".equals(textAlignVertical)) {
            view.a(16);
        } else {
            throw new n("Invalid textAlignVertical: " + textAlignVertical);
        }
    }

    @ReactProp(name = "selectable")
    public void setSelectable(ReactTextView view, boolean isSelectable) {
        view.setTextIsSelectable(isSelectable);
    }

    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactTextView view, @Nullable Integer color) {
        if (color == null) {
            view.setHighlightColor(c.c(view.getContext()));
        } else {
            view.setHighlightColor(color.intValue());
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactTextView view, int index, float borderRadius) {
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
    public void setBorderStyle(ReactTextView view, @Nullable String borderStyle) {
        view.setBorderStyle(borderStyle);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactTextView view, int index, float width) {
        if (!a.a(width)) {
            width = p.a(width);
        }
        view.setBorderWidth(SPACING_TYPES[index], width);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactTextView view, int index, Integer color) {
        float alphaComponent = Float.NaN;
        float rgbComponent = color == null ? Float.NaN : (float) (color.intValue() & 16777215);
        if (color != null) {
            alphaComponent = (float) (color.intValue() >>> 24);
        }
        view.setBorderColor(SPACING_TYPES[index], rgbComponent, alphaComponent);
    }

    @ReactProp(defaultBoolean = true, name = "includeFontPadding")
    public void setIncludeFontPadding(ReactTextView view, boolean includepad) {
        view.setIncludeFontPadding(includepad);
    }

    @ReactProp(defaultBoolean = false, name = "disabled")
    public void setDisabled(ReactTextView view, boolean disabled) {
        view.setEnabled(!disabled);
    }

    @ReactProp(name = "onInlineViewLayout")
    public void setNotifyOnInlineViewLayout(ReactTextView view, boolean notifyOnInlineViewLayout) {
        view.setNotifyOnInlineViewLayout(notifyOnInlineViewLayout);
    }

    public void updateExtraData(ReactTextView view, Object extraData) {
        h update = (h) extraData;
        if (update.c()) {
            k.a(update.a(), view);
        }
        view.setText(update);
    }

    public g createShadowNodeInstance() {
        return new g();
    }

    public Class<g> getShadowNodeClass() {
        return g.class;
    }

    protected void onAfterUpdateTransaction(ReactTextView view) {
        super.onAfterUpdateTransaction(view);
        view.a();
    }

    public boolean needsCustomLayoutForChildren() {
        return true;
    }
}
