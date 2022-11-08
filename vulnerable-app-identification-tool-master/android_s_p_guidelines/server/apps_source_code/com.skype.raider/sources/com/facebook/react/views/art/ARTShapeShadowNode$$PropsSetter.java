package com.facebook.react.views.art;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.ao.d;
import com.facebook.react.uimanager.w;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ARTShapeShadowNode$$PropsSetter implements d<b> {
    private final HashMap<String, d<b>> setters = new HashMap(9);

    public ARTShapeShadowNode$$PropsSetter() {
        this.setters.put("d", new d<b>(this) {
            final /* synthetic */ ARTShapeShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((b) wVar).setShapePath(xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fill", new d<b>(this) {
            final /* synthetic */ ARTShapeShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((b) wVar).setFill(xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new d<b>(this) {
            final /* synthetic */ ARTShapeShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((b) wVar).setOpacity(xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("stroke", new d<b>(this) {
            final /* synthetic */ ARTShapeShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((b) wVar).setStroke(xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("strokeCap", new d<b>(this) {
            final /* synthetic */ ARTShapeShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((b) wVar).setStrokeCap(xVar.a(str, 1));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("strokeDash", new d<b>(this) {
            final /* synthetic */ ARTShapeShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((b) wVar).setStrokeDash(xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("strokeJoin", new d<b>(this) {
            final /* synthetic */ ARTShapeShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((b) wVar).setStrokeJoin(xVar.a(str, 1));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("strokeWidth", new d<b>(this) {
            final /* synthetic */ ARTShapeShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((b) wVar).setStrokeWidth(xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new d<b>(this) {
            final /* synthetic */ ARTShapeShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((b) wVar).setTransform(xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(b node, String name, x props) {
        d<b> setter = (d) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(node, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("d", "Array");
        props.put("fill", "Array");
        props.put("opacity", "number");
        props.put("stroke", "Array");
        props.put("strokeCap", "number");
        props.put("strokeDash", "Array");
        props.put("strokeJoin", "number");
        props.put("strokeWidth", "number");
        props.put("transform", "Array");
    }
}
