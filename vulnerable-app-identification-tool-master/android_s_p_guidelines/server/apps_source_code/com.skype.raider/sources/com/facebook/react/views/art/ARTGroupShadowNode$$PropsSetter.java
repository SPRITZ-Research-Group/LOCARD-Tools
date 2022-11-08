package com.facebook.react.views.art;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.ao.d;
import com.facebook.react.uimanager.w;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ARTGroupShadowNode$$PropsSetter implements d<a> {
    private final HashMap<String, d<a>> setters = new HashMap(3);

    public ARTGroupShadowNode$$PropsSetter() {
        this.setters.put("clipping", new d<a>(this) {
            final /* synthetic */ ARTGroupShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((a) wVar).setClipping(xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new d<a>(this) {
            final /* synthetic */ ARTGroupShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((a) wVar).setOpacity(xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new d<a>(this) {
            final /* synthetic */ ARTGroupShadowNode$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(w wVar, String str, x xVar) {
                ((a) wVar).setTransform(xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(a node, String name, x props) {
        d<a> setter = (d) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(node, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("clipping", "Array");
        props.put("opacity", "number");
        props.put("transform", "Array");
    }
}
