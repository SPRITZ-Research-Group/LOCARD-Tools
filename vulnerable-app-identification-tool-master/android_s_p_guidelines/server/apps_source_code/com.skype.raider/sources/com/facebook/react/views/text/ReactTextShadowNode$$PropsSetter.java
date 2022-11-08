package com.facebook.react.views.text;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.ao.d;
import com.facebook.react.uimanager.w;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactTextShadowNode$$PropsSetter implements d<g> {
    private final HashMap<String, d<g>> setters = new HashMap(61);

    public ReactTextShadowNode$$PropsSetter() {
        this.setters.put("alignContent", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setAlignContent(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("alignItems", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setAlignItems(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("alignSelf", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setAlignSelf(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("allowFontScaling", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setAllowFontScaling(xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("aspectRatio", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setAspectRatio(xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                Integer num;
                g gVar = (g) wVar;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                gVar.setBackgroundColor(num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomWidth", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setBorderWidths(4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftWidth", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setBorderWidths(1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightWidth", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setBorderWidths(2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopWidth", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setBorderWidths(3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderWidth", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setBorderWidths(0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("bottom", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setPositionValues(3, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("color", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                Integer num;
                g gVar = (g) wVar;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                gVar.setColor(num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("display", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setDisplay(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flex", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setFlex(xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexBasis", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setFlexBasis(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexDirection", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setFlexDirection(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexGrow", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setFlexGrow(xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexShrink", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setFlexShrink(xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexWrap", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setFlexWrap(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontFamily", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setFontFamily(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontSize", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setFontSize(xVar.a(str, -1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontStyle", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setFontStyle(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fontWeight", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setFontWeight(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("height", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setHeight(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("justifyContent", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setJustifyContent(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("left", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setPositionValues(0, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("lineHeight", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setLineHeight(xVar.a(str, -1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("margin", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setMargins(0, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginBottom", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setMargins(6, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginHorizontal", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setMargins(2, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginLeft", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setMargins(3, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginRight", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setMargins(4, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginTop", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setMargins(5, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginVertical", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setMargins(1, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maxContentSizeMultiplier", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setMaxContentSizeMultiplier(xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maxHeight", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setMaxHeight(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maxWidth", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setMaxWidth(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("minHeight", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setMinHeight(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("minWidth", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setMinWidth(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("numberOfLines", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setNumberOfLines(xVar.a(str, -1));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("onLayout", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setShouldNotifyOnLayout(xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("overflow", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setOverflow(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("padding", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setPaddings(0, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingBottom", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setPaddings(6, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingHorizontal", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setPaddings(2, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingLeft", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setPaddings(3, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingRight", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setPaddings(4, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingTop", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setPaddings(5, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingVertical", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setPaddings(1, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("position", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setPosition(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("right", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setPositionValues(1, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("text", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setText(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textAlign", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setTextAlign(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textBreakStrategy", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setTextBreakStrategy(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textDecorationLine", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setTextDecorationLine(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textShadowColor", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setTextShadowColor(xVar.a(str, 1426063360));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textShadowOffset", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setTextShadowOffset(xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textShadowRadius", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setTextShadowRadius(xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("top", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setPositionValues(2, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("width", new d<g>(this) {
            final /* synthetic */ ReactTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((g) wVar).setWidth(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(g node, String name, x props) {
        d<g> setter = (d) this.setters.get(name);
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
