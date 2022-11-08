package com.facebook.react.views.art;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.ao.d;
import com.facebook.react.uimanager.w;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ARTTextShadowNode$$PropsSetter implements d<d> {
    private final HashMap<String, d<d>> setters = new HashMap(11);

    public ARTTextShadowNode$$PropsSetter() {
        this.setters.put("alignment", new d<d>(this) {
            final /* synthetic */ ARTTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((d) wVar).setAlignment(xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("d", new d<d>(this) {
            final /* synthetic */ ARTTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((d) wVar).setShapePath(xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fill", new d<d>(this) {
            final /* synthetic */ ARTTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((d) wVar).setFill(xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("frame", new d<d>(this) {
            final /* synthetic */ ARTTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((d) wVar).setFrame(xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new d<d>(this) {
            final /* synthetic */ ARTTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((d) wVar).setOpacity(xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("stroke", new d<d>(this) {
            final /* synthetic */ ARTTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((d) wVar).setStroke(xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("strokeCap", new d<d>(this) {
            final /* synthetic */ ARTTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((d) wVar).setStrokeCap(xVar.a(str, 1));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("strokeDash", new d<d>(this) {
            final /* synthetic */ ARTTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((d) wVar).setStrokeDash(xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("strokeJoin", new d<d>(this) {
            final /* synthetic */ ARTTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((d) wVar).setStrokeJoin(xVar.a(str, 1));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("strokeWidth", new d<d>(this) {
            final /* synthetic */ ARTTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((d) wVar).setStrokeWidth(xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new d<d>(this) {
            final /* synthetic */ ARTTextShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((d) wVar).setTransform(xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(d node, String name, x props) {
        d<d> setter = (d) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(node, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("alignment", "number");
        props.put("d", "Array");
        props.put("fill", "Array");
        props.put("frame", "Map");
        props.put("opacity", "number");
        props.put("stroke", "Array");
        props.put("strokeCap", "number");
        props.put("strokeDash", "Array");
        props.put("strokeJoin", "number");
        props.put("strokeWidth", "number");
        props.put("transform", "Array");
    }
}
