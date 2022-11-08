package com.facebook.react.views.textinput;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactTextInputManager$$PropsSetter implements e<ReactTextInputManager, ReactEditText> {
    private final HashMap<String, e<ReactTextInputManager, ReactEditText>> setters = new HashMap(66);

    public ReactTextInputManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setAccessibilityComponentType((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setAccessibilityLabel((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setAccessibilityLiveRegion((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("autoCapitalize", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setAutoCapitalize((ReactEditText) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("autoCorrect", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Boolean bool;
                ReactTextInputManager reactTextInputManager = (ReactTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    bool = null;
                } else {
                    bool = Boolean.valueOf(xVar.a(str, false));
                }
                reactTextInputManager.setAutoCorrect(reactEditText, bool);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setBackgroundColor((ReactEditText) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("blurOnSubmit", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setBlurOnSubmit((ReactEditText) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomColor", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactTextInputManager reactTextInputManager = (ReactTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactTextInputManager.setBorderColor(reactEditText, 4, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomLeftRadius", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setBorderRadius((ReactEditText) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomRightRadius", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setBorderRadius((ReactEditText) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomWidth", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setBorderWidth((ReactEditText) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderColor", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactTextInputManager reactTextInputManager = (ReactTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactTextInputManager.setBorderColor(reactEditText, 0, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftColor", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactTextInputManager reactTextInputManager = (ReactTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactTextInputManager.setBorderColor(reactEditText, 1, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftWidth", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setBorderWidth((ReactEditText) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRadius", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setBorderRadius((ReactEditText) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightColor", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactTextInputManager reactTextInputManager = (ReactTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactTextInputManager.setBorderColor(reactEditText, 2, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightWidth", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setBorderWidth((ReactEditText) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderStyle", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setBorderStyle((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopColor", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactTextInputManager reactTextInputManager = (ReactTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactTextInputManager.setBorderColor(reactEditText, 3, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopLeftRadius", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setBorderRadius((ReactEditText) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopRightRadius", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setBorderRadius((ReactEditText) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopWidth", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setBorderWidth((ReactEditText) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderWidth", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setBorderWidth((ReactEditText) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("caretHidden", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setCaretHidden((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("color", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactTextInputManager reactTextInputManager = (ReactTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactTextInputManager.setColor(reactEditText, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("disableFullscreenUI", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setDisableFullscreenUI((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("editable", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setEditable((ReactEditText) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setElevation((ReactEditText) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontFamily", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setFontFamily((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontSize", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setFontSize((ReactEditText) view, xVar.a(str, 14.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontStyle", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setFontStyle((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontWeight", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setFontWeight((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setImportantForAccessibility((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("inlineImageLeft", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setInlineImageLeft((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("inlineImagePadding", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setInlineImagePadding((ReactEditText) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("keyboardType", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setKeyboardType((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maxContentSizeMultiplier", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setMaxContentSizeMultiplier((ReactEditText) view, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maxLength", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactTextInputManager reactTextInputManager = (ReactTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactTextInputManager.setMaxLength(reactEditText, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("multiline", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setMultiline((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setNativeId((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("numberOfLines", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setNumLines((ReactEditText) view, xVar.a(str, 1));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("onContentSizeChange", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setOnContentSizeChange((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("onScroll", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setOnScroll((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("onSelectionChange", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setOnSelectionChange((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setOpacity((ReactEditText) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("placeholder", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setPlaceholder((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("placeholderTextColor", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactTextInputManager reactTextInputManager = (ReactTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactTextInputManager.setPlaceholderTextColor(reactEditText, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setRenderToHardwareTexture((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("returnKeyLabel", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setReturnKeyLabel((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("returnKeyType", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setReturnKeyType((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setRotation((ReactEditText) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setScaleX((ReactEditText) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setScaleY((ReactEditText) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("secureTextEntry", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setSecureTextEntry((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("selectTextOnFocus", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setSelectTextOnFocus((ReactEditText) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("selection", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setSelection((ReactEditText) view, xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("selectionColor", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactTextInputManager reactTextInputManager = (ReactTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactTextInputManager.setSelectionColor(reactEditText, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setTestId((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textAlign", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setTextAlign((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textAlignVertical", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setTextAlignVertical((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setTransform((ReactEditText) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setTranslateX((ReactEditText) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setTranslateY((ReactEditText) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("underlineColorAndroid", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactTextInputManager reactTextInputManager = (ReactTextInputManager) viewManager;
                ReactEditText reactEditText = (ReactEditText) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactTextInputManager.setUnderlineColor(reactEditText, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setViewLayerType((ReactEditText) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactTextInputManager, ReactEditText>(this) {
            final /* synthetic */ ReactTextInputManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactTextInputManager) viewManager).setZIndex((ReactEditText) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactTextInputManager manager, ReactEditText view, String name, x props) {
        e<ReactTextInputManager, ReactEditText> setter = (e) this.setters.get(name);
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
