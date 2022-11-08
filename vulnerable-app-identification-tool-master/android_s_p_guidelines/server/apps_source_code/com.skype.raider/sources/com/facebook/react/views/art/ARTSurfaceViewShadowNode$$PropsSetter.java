package com.facebook.react.views.art;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.ao.d;
import com.facebook.react.uimanager.w;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ARTSurfaceViewShadowNode$$PropsSetter implements d<c> {
    private final HashMap<String, d<c>> setters = new HashMap(45);

    public ARTSurfaceViewShadowNode$$PropsSetter() {
        this.setters.put("alignContent", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setAlignContent(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("alignItems", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setAlignItems(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("alignSelf", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setAlignSelf(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("aspectRatio", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setAspectRatio(xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                Integer num;
                c cVar = (c) wVar;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                cVar.setBackgroundColor(num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomWidth", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setBorderWidths(4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftWidth", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setBorderWidths(1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightWidth", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setBorderWidths(2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopWidth", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setBorderWidths(3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderWidth", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setBorderWidths(0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("bottom", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setPositionValues(3, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("display", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setDisplay(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flex", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setFlex(xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexBasis", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setFlexBasis(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexDirection", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setFlexDirection(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexGrow", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setFlexGrow(xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexShrink", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setFlexShrink(xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("flexWrap", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setFlexWrap(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("height", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setHeight(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("justifyContent", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setJustifyContent(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("left", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setPositionValues(0, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("margin", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setMargins(0, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginBottom", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setMargins(6, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginHorizontal", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setMargins(2, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginLeft", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setMargins(3, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginRight", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setMargins(4, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginTop", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setMargins(5, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("marginVertical", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setMargins(1, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maxHeight", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setMaxHeight(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maxWidth", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setMaxWidth(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("minHeight", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setMinHeight(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("minWidth", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setMinWidth(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("onLayout", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setShouldNotifyOnLayout(xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("overflow", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setOverflow(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("padding", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setPaddings(0, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingBottom", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setPaddings(6, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingHorizontal", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setPaddings(2, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingLeft", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setPaddings(3, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingRight", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setPaddings(4, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingTop", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setPaddings(5, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("paddingVertical", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setPaddings(1, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("position", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setPosition(xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("right", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setPositionValues(1, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("top", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setPositionValues(2, xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("width", new d<c>(this) {
            final /* synthetic */ ARTSurfaceViewShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((c) wVar).setWidth(xVar.f(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(c node, String name, x props) {
        d<c> setter = (d) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(node, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("alignContent", "String");
        props.put("alignItems", "String");
        props.put("alignSelf", "String");
        props.put("aspectRatio", "number");
        props.put("backgroundColor", "Color");
        props.put("borderBottomWidth", "number");
        props.put("borderLeftWidth", "number");
        props.put("borderRightWidth", "number");
        props.put("borderTopWidth", "number");
        props.put("borderWidth", "number");
        props.put("bottom", "Dynamic");
        props.put("display", "String");
        props.put("flex", "number");
        props.put("flexBasis", "Dynamic");
        props.put("flexDirection", "String");
        props.put("flexGrow", "number");
        props.put("flexShrink", "number");
        props.put("flexWrap", "String");
        props.put("height", "Dynamic");
        props.put("justifyContent", "String");
        props.put("left", "Dynamic");
        props.put("margin", "Dynamic");
        props.put("marginBottom", "Dynamic");
        props.put("marginHorizontal", "Dynamic");
        props.put("marginLeft", "Dynamic");
        props.put("marginRight", "Dynamic");
        props.put("marginTop", "Dynamic");
        props.put("marginVertical", "Dynamic");
        props.put("maxHeight", "Dynamic");
        props.put("maxWidth", "Dynamic");
        props.put("minHeight", "Dynamic");
        props.put("minWidth", "Dynamic");
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
        props.put("top", "Dynamic");
        props.put("width", "Dynamic");
    }
}
