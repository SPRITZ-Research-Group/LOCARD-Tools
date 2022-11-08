package com.facebook.react.views.viewpager;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactViewPagerManager$$PropsSetter implements e<ReactViewPagerManager, ReactViewPager> {
    private final HashMap<String, e<ReactViewPagerManager, ReactViewPager>> setters = new HashMap(20);

    public ReactViewPagerManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setAccessibilityComponentType((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setAccessibilityLabel((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setAccessibilityLiveRegion((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setBackgroundColor((ReactViewPager) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setElevation((ReactViewPager) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setImportantForAccessibility((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setNativeId((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setOpacity((ReactViewPager) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("pageMargin", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setPageMargin((ReactViewPager) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setRenderToHardwareTexture((ReactViewPager) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setRotation((ReactViewPager) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setScaleX((ReactViewPager) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setScaleY((ReactViewPager) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scrollEnabled", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setScrollEnabled((ReactViewPager) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setTestId((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setTransform((ReactViewPager) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setTranslateX((ReactViewPager) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setTranslateY((ReactViewPager) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setViewLayerType((ReactViewPager) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactViewPagerManager, ReactViewPager>(this) {
            final /* synthetic */ ReactViewPagerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewPagerManager) viewManager).setZIndex((ReactViewPager) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactViewPagerManager manager, ReactViewPager view, String name, x props) {
        e<ReactViewPagerManager, ReactViewPager> setter = (e) this.setters.get(name);
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
