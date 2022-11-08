package com.facebook.react.views.textinput;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.ao.d;
import com.facebook.react.uimanager.w;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactTextInputShadowNode$$PropsSetter implements d<j> {
    private final HashMap<String, d<j>> setters = new HashMap(62);

    public ReactTextInputShadowNode$$PropsSetter() {
        this.setters.put("alignContent", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setAlignContent(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("alignItems", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setAlignItems(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("alignSelf", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setAlignSelf(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("allowFontScaling", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setAllowFontScaling(xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("aspectRatio", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setAspectRatio(xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                Integer num;
                j jVar = (j) wVar;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                jVar.setBackgroundColor(num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomWidth", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setBorderWidths(4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftWidth", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setBorderWidths(1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightWidth", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setBorderWidths(2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopWidth", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setBorderWidths(3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderWidth", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setBorderWidths(0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("bottom", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setPositionValues(3, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("color", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                Integer num;
                j jVar = (j) wVar;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                jVar.setColor(num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("display", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setDisplay(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flex", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setFlex(xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexBasis", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setFlexBasis(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexDirection", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setFlexDirection(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexGrow", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setFlexGrow(xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexShrink", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setFlexShrink(xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexWrap", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setFlexWrap(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontFamily", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setFontFamily(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontSize", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setFontSize(xVar.a(str, -1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontStyle", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setFontStyle(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontWeight", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setFontWeight(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("height", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setHeight(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("justifyContent", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setJustifyContent(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("left", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setPositionValues(0, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("lineHeight", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setLineHeight(xVar.a(str, -1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("margin", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setMargins(0, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginBottom", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setMargins(6, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginHorizontal", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setMargins(2, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginLeft", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setMargins(3, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginRight", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setMargins(4, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginTop", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setMargins(5, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginVertical", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setMargins(1, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maxContentSizeMultiplier", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setMaxContentSizeMultiplier(xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maxHeight", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setMaxHeight(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maxWidth", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setMaxWidth(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("minHeight", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setMinHeight(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("minWidth", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setMinWidth(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("mostRecentEventCount", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setMostRecentEventCount(xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("numberOfLines", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setNumberOfLines(xVar.a(str, -1));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("onLayout", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setShouldNotifyOnLayout(xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("overflow", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setOverflow(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("padding", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setPaddings(0, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingBottom", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setPaddings(6, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingHorizontal", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setPaddings(2, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingLeft", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setPaddings(3, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingRight", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setPaddings(4, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingTop", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setPaddings(5, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingVertical", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setPaddings(1, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("position", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setPosition(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("right", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setPositionValues(1, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("text", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setText(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textAlign", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setTextAlign(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textBreakStrategy", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setTextBreakStrategy(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textDecorationLine", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setTextDecorationLine(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textShadowColor", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setTextShadowColor(xVar.a(str, 1426063360));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textShadowOffset", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setTextShadowOffset(xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textShadowRadius", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setTextShadowRadius(xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("top", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setPositionValues(2, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("width", new d<j>(this) {
            final /* synthetic */ ReactTextInputShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((j) wVar).setWidth(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(j node, String name, x props) {
        d<j> setter = (d) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(node, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("alignContent", "String");
        props.put("alignItems", "String");
        props.put("alignSelf", "String");
        props.put("allowFontScaling", "boolean");
        props.put("aspectRatio", "number");
        props.put("backgroundColor", "number");
        props.put("borderBottomWidth", "number");
        props.put("borderLeftWidth", "number");
        props.put("borderRightWidth", "number");
        props.put("borderTopWidth", "number");
        props.put("borderWidth", "number");
        props.put("bottom", "Dynamic");
        props.put("color", "number");
        props.put("display", "String");
        props.put("flex", "number");
        props.put("flexBasis", "Dynamic");
        props.put("flexDirection", "String");
        props.put("flexGrow", "number");
        props.put("flexShrink", "number");
        props.put("flexWrap", "String");
        props.put("fontFamily", "String");
        props.put("fontSize", "number");
        props.put("fontStyle", "String");
        props.put("fontWeight", "String");
        props.put("height", "Dynamic");
        props.put("justifyContent", "String");
        props.put("left", "Dynamic");
        props.put("lineHeight", "number");
        props.put("margin", "Dynamic");
        props.put("marginBottom", "Dynamic");
        props.put("marginHorizontal", "Dynamic");
        props.put("marginLeft", "Dynamic");
        props.put("marginRight", "Dynamic");
        props.put("marginTop", "Dynamic");
        props.put("marginVertical", "Dynamic");
        props.put("maxContentSizeMultiplier", "number");
        props.put("maxHeight", "Dynamic");
        props.put("maxWidth", "Dynamic");
        props.put("minHeight", "Dynamic");
        props.put("minWidth", "Dynamic");
        props.put("mostRecentEventCount", "number");
        props.put("numberOfLines", "number");
        props.put("onLayout", "boolean");
        props.put("overflow", "String");
        props.put("padding", "Dynamic");
        props.put("paddingBottom", "Dynamic");
        props.put("paddingHorizontal", "Dynamic");
        props.put("paddingLeft", "Dynamic");
        props.put("paddingRight", "Dynamic");
        props.put("paddingTop", "Dynamic");
        props.put("paddingVertical", "Dynamic");
        props.put("position", "String");
        props.put("right", "Dynamic");
        props.put("text", "String");
        props.put("textAlign", "String");
        props.put("textBreakStrategy", "String");
        props.put("textDecorationLine", "String");
        props.put("textShadowColor", "Color");
        props.put("textShadowOffset", "Map");
        props.put("textShadowRadius", "number");
        props.put("top", "Dynamic");
        props.put("width", "Dynamic");
    }
}
