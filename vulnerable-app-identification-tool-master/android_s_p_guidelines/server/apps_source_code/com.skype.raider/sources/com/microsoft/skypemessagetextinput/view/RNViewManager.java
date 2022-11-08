package com.microsoft.skypemessagetextinput.view;

import android.graphics.Color;
import android.support.annotation.Nullable;
import com.adjust.sdk.Constants;
import com.facebook.infer.annotation.a;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.p;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RNViewManager extends BaseViewManager<RNView, c> {
    public static final String REACT_CLASS = "SKPSkypeMessageTextInput";
    private static Pattern rgbColorRegex = Pattern.compile("rgb\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");
    private static Pattern rgbaColorRegex = Pattern.compile("rgba\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*([\\d\\.]+)\\s*\\)");
    private c _shadowNode;

    private static int parseColor(String fromString) {
        if (fromString.startsWith("#")) {
            return Color.parseColor(fromString);
        }
        Matcher result = rgbColorRegex.matcher(fromString);
        if (result.matches()) {
            return Color.rgb(Integer.parseInt(result.group(1)), Integer.parseInt(result.group(2)), Integer.parseInt(result.group(3)));
        }
        result = rgbaColorRegex.matcher(fromString);
        if (result.matches()) {
            return Color.argb(Math.round(255.0f * Float.parseFloat(result.group(4))), Integer.parseInt(result.group(1)), Integer.parseInt(result.group(2)), Integer.parseInt(result.group(3)));
        }
        throw new IllegalArgumentException(String.format("Unrecognized color spec received! %s", new Object[]{fromString}));
    }

    public String getName() {
        return REACT_CLASS;
    }

    protected RNView createViewInstance(ae context) {
        return new RNView(context, this._shadowNode);
    }

    public void onDropViewInstance(RNView view) {
        super.onDropViewInstance(view);
        view.i();
    }

    public Class<? extends c> getShadowNodeClass() {
        return c.class;
    }

    public c createShadowNodeInstance() {
        this._shadowNode = new c();
        return this._shadowNode;
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return RNView.h();
    }

    public Map<String, Integer> getCommandsMap() {
        return RNView.g();
    }

    public void updateExtraData(RNView view, Object extraData) {
    }

    public void receiveCommand(RNView view, int commandType, @Nullable al args) {
        if (!view.j()) {
            a.a((Object) view);
            view.a(commandType, args);
        }
    }

    @ReactProp(name = "color")
    public void setColor(RNView view, Integer color) {
        if (!view.j()) {
            view.setTextColor(color.intValue());
        }
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(RNView view, String fontFamily) {
        if (!view.j()) {
            view.setFontFamily(fontFamily);
        }
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(RNView view, String weight) {
        if (!view.j()) {
            Object obj = -1;
            switch (weight.hashCode()) {
                case -1039745817:
                    if (weight.equals(Constants.NORMAL)) {
                        obj = 1;
                        break;
                    }
                    break;
                case 3029637:
                    if (weight.equals("bold")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    view.setFontWeight(700);
                    return;
                case 1:
                    view.setFontWeight(400);
                    return;
                default:
                    view.setFontWeight(Integer.parseInt(weight));
                    return;
            }
        }
    }

    @ReactProp(name = "fontSize")
    public void setFontSize(RNView view, Integer fontSize) {
        if (!view.j()) {
            view.setFontSize(fontSize.intValue());
        }
    }

    @ReactProp(name = "agnosticContentBackgroundColor")
    public void setAgnosticContentBackgroundColor(RNView view, String color) {
        if (!view.j()) {
            view.setAgnosticContentBackgroundColor(parseColor(color));
        }
    }

    @ReactProp(name = "atMentionBackgroundColor")
    public void setAtMentionBackgroundColor(RNView view, String color) {
        if (!view.j()) {
            view.setAtMentionBackgroundColor(parseColor(color));
        }
    }

    @ReactProp(name = "lineHeight")
    public void setLineHeight(RNView view, Integer height) {
        if (!view.j()) {
            view.setLineHeight((int) p.a((float) height.intValue()));
        }
    }

    @ReactProp(name = "maxHeight")
    public void setMaxHeight(RNView view, Integer maxHeight) {
        if (!view.j()) {
            view.setMaxHeight((int) p.a((float) maxHeight.intValue()));
        }
    }

    @ReactProp(name = "placeholder")
    public void setPlaceholder(RNView view, @Nullable String placeholder) {
        if (!view.j()) {
            if (placeholder == null) {
                placeholder = "";
            }
            view.setHint(placeholder);
        }
    }

    @ReactProp(name = "placeholderTextColor")
    public void setPlaceholderTextColor(RNView view, @Nullable String color) {
        if (!view.j()) {
            view.setHintTextColor(color != null ? parseColor(color) : -7829368);
        }
    }

    @ReactProp(name = "keyboardAppearance")
    public void setKeyboardAppearance(RNView view, @Nullable String keyboardAppearance) {
    }

    @ReactProp(name = "keyboardType")
    public void setKeyboardType(RNView view, @Nullable String keyboardType) {
    }

    @ReactProp(name = "maxLength")
    public void setMaxLength(RNView view, @Nullable Integer maxLength) {
        if (!view.j()) {
            view.setMaxLength(maxLength);
        }
    }

    @ReactProp(name = "enterKeyOnExtKeyboardSendsMessage")
    public void setEnterKeyOnExtKeyboardSendsMessage(RNView view, @Nullable Boolean enterKeyOnExtKeyboardSendsMessage) {
        if (!view.j()) {
            view.setEnterKeyOnExtKeyboardSendsMessage(Boolean.valueOf(enterKeyOnExtKeyboardSendsMessage != null ? enterKeyOnExtKeyboardSendsMessage.booleanValue() : true));
        }
    }

    @ReactProp(name = "editable")
    public void setEditable(RNView view, @Nullable Boolean editable) {
        if (!view.j()) {
            view.setEnabled(editable != null ? editable.booleanValue() : true);
        }
    }

    @ReactProp(name = "initialContent")
    public void setInitialContent(final RNView view, @Nullable final am initialContent) {
        if (!view.j() && view.c() == 0) {
            view.post(new Runnable(this) {
                final /* synthetic */ RNViewManager c;

                public final void run() {
                    if (!view.j()) {
                        view.setContent(initialContent);
                    }
                }
            });
        }
    }

    @ReactProp(name = "customContext")
    public void setCustomContext(RNView view, @Nullable String customContext) {
        if (!view.j()) {
            view.setCustomContext(customContext);
        }
    }

    @ReactProp(name = "disableCopyPasteMedia")
    public void setDisableCopyPasteMedia(RNView view, @Nullable Boolean disableCopyPasteMedia) {
    }

    @ReactProp(name = "autoCompletionTriggers")
    public void setAutoCompletionTriggers(RNView view, @Nullable am autoCompletionTriggers) {
        if (!view.j()) {
            view.setAutoCompletionTriggers(autoCompletionTriggers);
        }
    }

    @ReactProp(name = "intermediateContentUpdatedEventInterval")
    public void setIntermediateContentUpdatedEventInterval(RNView view, @Nullable Integer interval) {
        if (!view.j()) {
            view.setIntermediateContentUpdatedEventInterval(interval);
        }
    }

    @ReactProp(name = "delayedTriggersWaitTime")
    public void setDelayedTriggersWaitTime(RNView view, @Nullable Integer delay) {
        if (!view.j()) {
            view.setDelayedTriggersWaitTime(delay);
        }
    }

    @ReactProp(name = "convenienceSpaceReplacingChars")
    public void setConvenienceSpaceReplacingChars(RNView view, @Nullable String value) {
        if (!view.j()) {
            view.setConvenienceSpaceReplacingChars(value);
        }
    }

    public void onHostDestroy() {
        super.onHostDestroy();
        this._shadowNode = null;
    }
}
