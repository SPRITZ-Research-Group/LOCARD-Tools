package com.facebook.react.views.modal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import com.facebook.react.common.e;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.c;
import com.facebook.react.uimanager.h;
import com.facebook.react.views.modal.ReactModalHostView.b;
import java.util.Map;

@ReactModule(name = "RCTModalHostView")
public class ReactModalHostManager extends ViewGroupManager<ReactModalHostView> {
    protected static final String REACT_CLASS = "RCTModalHostView";

    public String getName() {
        return REACT_CLASS;
    }

    protected ReactModalHostView createViewInstance(ae reactContext) {
        return new ReactModalHostView(reactContext);
    }

    public h createShadowNodeInstance() {
        return new b();
    }

    public Class<? extends h> getShadowNodeClass() {
        return b.class;
    }

    public void onDropViewInstance(ReactModalHostView view) {
        super.onDropViewInstance(view);
        view.a();
    }

    @ReactProp(name = "animationType")
    public void setAnimationType(ReactModalHostView view, String animationType) {
        view.a(animationType);
    }

    @ReactProp(name = "transparent")
    public void setTransparent(ReactModalHostView view, boolean transparent) {
        view.a(transparent);
    }

    @ReactProp(name = "hardwareAccelerated")
    public void setHardwareAccelerated(ReactModalHostView view, boolean hardwareAccelerated) {
        view.b(hardwareAccelerated);
    }

    protected void addEventEmitters(ae reactContext, final ReactModalHostView view) {
        final c dispatcher = ((UIManagerModule) reactContext.b(UIManagerModule.class)).getEventDispatcher();
        view.a(new b(this) {
            final /* synthetic */ ReactModalHostManager c;

            public final void a() {
                dispatcher.a(new c(view.getId()));
            }
        });
        view.a(new OnShowListener(this) {
            final /* synthetic */ ReactModalHostManager c;

            public final void onShow(DialogInterface dialog) {
                dispatcher.a(new d(view.getId()));
            }
        });
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return e.a().a("topRequestClose", e.a("registrationName", "onRequestClose")).a("topShow", e.a("registrationName", "onShow")).a();
    }

    protected void onAfterUpdateTransaction(ReactModalHostView view) {
        super.onAfterUpdateTransaction(view);
        view.b();
    }
}
