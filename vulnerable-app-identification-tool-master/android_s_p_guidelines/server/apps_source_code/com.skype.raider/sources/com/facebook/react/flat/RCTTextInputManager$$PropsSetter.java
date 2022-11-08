package com.facebook.react.flat;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import com.facebook.react.views.textinput.ReactEditText;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class RCTTextInputManager$$PropsSetter implements e<RCTTextInputManager, ReactEditText> {
    private final HashMap<String, e<RCTTextInputManager, ReactEditText>> setters = new HashMap(66);

    public RCTTextInputManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setAccessibilityComponentType((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setAccessibilityLabel((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setAccessibilityLiveRegion((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("autoCapitalize", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setAutoCapitalize((ReactEditText) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("autoCorrect", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Boolean bool;
                RCTTextInputManager rCTTextInputManager = (RCTTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    bool = null;
                } else {
                    bool = Boolean.valueOf(xVar.a(str, false));
                }
                rCTTextInputManager.setAutoCorrect(reactEditText, bool);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setBackgroundColor((ReactEditText) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("blurOnSubmit", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setBlurOnSubmit((ReactEditText) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomColor", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                RCTTextInputManager rCTTextInputManager = (RCTTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                rCTTextInputManager.setBorderColor(reactEditText, 4, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomLeftRadius", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setBorderRadius((ReactEditText) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomRightRadius", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setBorderRadius((ReactEditText) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomWidth", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setBorderWidth((ReactEditText) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderColor", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                RCTTextInputManager rCTTextInputManager = (RCTTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                rCTTextInputManager.setBorderColor(reactEditText, 0, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftColor", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                RCTTextInputManager rCTTextInputManager = (RCTTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                rCTTextInputManager.setBorderColor(reactEditText, 1, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftWidth", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setBorderWidth((ReactEditText) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRadius", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setBorderRadius((ReactEditText) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightColor", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                RCTTextInputManager rCTTextInputManager = (RCTTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                rCTTextInputManager.setBorderColor(reactEditText, 2, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightWidth", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setBorderWidth((ReactEditText) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderStyle", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setBorderStyle((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopColor", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                RCTTextInputManager rCTTextInputManager = (RCTTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                rCTTextInputManager.setBorderColor(reactEditText, 3, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopLeftRadius", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setBorderRadius((ReactEditText) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopRightRadius", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setBorderRadius((ReactEditText) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopWidth", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setBorderWidth((ReactEditText) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderWidth", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setBorderWidth((ReactEditText) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("caretHidden", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setCaretHidden((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("color", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                RCTTextInputManager rCTTextInputManager = (RCTTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                rCTTextInputManager.setColor(reactEditText, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("disableFullscreenUI", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setDisableFullscreenUI((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("editable", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setEditable((ReactEditText) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setElevation((ReactEditText) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontFamily", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setFontFamily((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontSize", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setFontSize((ReactEditText) view, xVar.a(str, 14.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontStyle", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setFontStyle((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontWeight", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setFontWeight((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setImportantForAccessibility((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("inlineImageLeft", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setInlineImageLeft((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("inlineImagePadding", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setInlineImagePadding((ReactEditText) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("keyboardType", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setKeyboardType((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maxContentSizeMultiplier", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setMaxContentSizeMultiplier((ReactEditText) view, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maxLength", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                RCTTextInputManager rCTTextInputManager = (RCTTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                rCTTextInputManager.setMaxLength(reactEditText, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("multiline", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setMultiline((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setNativeId((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("numberOfLines", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setNumLines((ReactEditText) view, xVar.a(str, 1));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("onContentSizeChange", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setOnContentSizeChange((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("onScroll", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setOnScroll((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("onSelectionChange", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setOnSelectionChange((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setOpacity((ReactEditText) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("placeholder", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setPlaceholder((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("placeholderTextColor", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                RCTTextInputManager rCTTextInputManager = (RCTTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                rCTTextInputManager.setPlaceholderTextColor(reactEditText, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setRenderToHardwareTexture((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("returnKeyLabel", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setReturnKeyLabel((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("returnKeyType", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setReturnKeyType((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setRotation((ReactEditText) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setScaleX((ReactEditText) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setScaleY((ReactEditText) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("secureTextEntry", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setSecureTextEntry((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("selectTextOnFocus", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setSelectTextOnFocus((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("selection", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setSelection((ReactEditText) view, xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("selectionColor", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                RCTTextInputManager rCTTextInputManager = (RCTTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                rCTTextInputManager.setSelectionColor(reactEditText, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setTestId((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textAlign", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setTextAlign((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textAlignVertical", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setTextAlignVertical((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setTransform((ReactEditText) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setTranslateX((ReactEditText) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setTranslateY((ReactEditText) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("underlineColorAndroid", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                RCTTextInputManager rCTTextInputManager = (RCTTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                rCTTextInputManager.setUnderlineColor(reactEditText, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setViewLayerType((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<RCTTextInputManager, ReactEditText>(this) {
            final /* synthetic */ RCTTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTTextInputManager) viewManager).setZIndex((ReactEditText) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(RCTTextInputManager manager, ReactEditText view, String name, x props) {
        e<RCTTextInputManager, ReactEditText> setter = (e) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(manager, view, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("accessibilityComponentType", "String");
        props.put("accessibilityLabel", "String");
        props.put("accessibilityLiveRegion", "String");
        props.put("autoCapitalize", "number");
        props.put("autoCorrect", "boolean");
        props.put("backgroundColor", "Color");
        props.put("blurOnSubmit", "boolean");
        props.put("borderBottomColor", "Color");
        props.put("borderBottomLeftRadius", "number");
        props.put("borderBottomRightRadius", "number");
        props.put("borderBottomWidth", "number");
        props.put("borderColor", "Color");
        props.put("borderLeftColor", "Color");
        props.put("borderLeftWidth", "number");
        props.put("borderRadius", "number");
        props.put("borderRightColor", "Color");
        props.put("borderRightWidth", "number");
        props.put("borderStyle", "String");
        props.put("borderTopColor", "Color");
        props.put("borderTopLeftRadius", "number");
        props.put("borderTopRightRadius", "number");
        props.put("borderTopWidth", "number");
        props.put("borderWidth", "number");
        props.put("caretHidden", "boolean");
        props.put("color", "Color");
        props.put("disableFullscreenUI", "boolean");
        props.put("editable", "boolean");
        props.put("elevation", "number");
        props.put("fontFamily", "String");
        props.put("fontSize", "number");
        props.put("fontStyle", "String");
        props.put("fontWeight", "String");
        props.put("importantForAccessibility", "String");
        props.put("inlineImageLeft", "String");
        props.put("inlineImagePadding", "number");
        props.put("keyboardType", "String");
        props.put("maxContentSizeMultiplier", "number");
        props.put("maxLength", "number");
        props.put("multiline", "boolean");
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("numberOfLines", "number");
        props.put("onContentSizeChange", "boolean");
        props.put("onScroll", "boolean");
        props.put("onSelectionChange", "boolean");
        props.put("opacity", "number");
        props.put("placeholder", "String");
        props.put("placeholderTextColor", "Color");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("returnKeyLabel", "String");
        props.put("returnKeyType", "String");
        props.put("rotation", "number");
        props.put("scaleX", "number");
        props.put("scaleY", "number");
        props.put("secureTextEntry", "boolean");
        props.put("selectTextOnFocus", "boolean");
        props.put("selection", "Map");
        props.put("selectionColor", "Color");
        props.put(BaseViewManager.PROP_TEST_ID, "String");
        props.put("textAlign", "String");
        props.put("textAlignVertical", "String");
        props.put("transform", "Array");
        props.put("translateX", "number");
        props.put("translateY", "number");
        props.put("underlineColorAndroid", "Color");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
