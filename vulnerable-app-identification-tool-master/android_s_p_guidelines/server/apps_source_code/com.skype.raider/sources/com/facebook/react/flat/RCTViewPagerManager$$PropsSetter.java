package com.facebook.react.flat;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import com.facebook.react.views.viewpager.ReactViewPager;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class RCTViewPagerManager$$PropsSetter implements e<RCTViewPagerManager, ReactViewPager> {
    private final HashMap<String, e<RCTViewPagerManager, ReactViewPager>> setters = new HashMap(20);

    public RCTViewPagerManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setAccessibilityComponentType((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setAccessibilityLabel((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setAccessibilityLiveRegion((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setBackgroundColor((ReactViewPager) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setElevation((ReactViewPager) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setImportantForAccessibility((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setNativeId((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setOpacity((ReactViewPager) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("pageMargin", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setPageMargin((ReactViewPager) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setRenderToHardwareTexture((ReactViewPager) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setRotation((ReactViewPager) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setScaleX((ReactViewPager) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setScaleY((ReactViewPager) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scrollEnabled", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setScrollEnabled((ReactViewPager) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setTestId((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setTransform((ReactViewPager) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setTranslateX((ReactViewPager) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setTranslateY((ReactViewPager) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setViewLayerType((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<RCTViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ RCTViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewPagerManager) viewManager).setZIndex((ReactViewPager) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(RCTViewPagerManager manager, ReactViewPager view, String name, x props) {
        e<RCTViewPagerManager, ReactViewPager> setter = (e) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(manager, view, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("accessibilityComponentType", "String");
        props.put("accessibilityLabel", "String");
        props.put("accessibilityLiveRegion", "String");
        props.put("backgroundColor", "Color");
        props.put("elevation", "number");
        props.put("importantForAccessibility", "String");
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("opacity", "number");
        props.put("pageMargin", "number");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("rotation", "number");
        props.put("scaleX", "number");
        props.put("scaleY", "number");
        props.put("scrollEnabled", "boolean");
        props.put(BaseViewManager.PROP_TEST_ID, "String");
        props.put("transform", "Array");
        props.put("translateX", "number");
        props.put("translateY", "number");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
