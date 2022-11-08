package com.skype.campaignreceiver;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CampaignReceiverPackage implements q {
    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }

    public final List<NativeModule> a(ag reactContext) {
        List<NativeModule> modules = new ArrayList();
        modules.add(new CampaignReceiverModule(reactContext));
        return modules;
    }
}
