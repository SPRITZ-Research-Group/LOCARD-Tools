package com.skype.campaignreceiver;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "CampaignReceiver")
public class CampaignReceiverModule extends ReactContextBaseJavaModule {
    public CampaignReceiverModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "CampaignReceiver";
    }

    @ReactMethod
    public void getReferrer(ae promise) {
        promise.a(CampaignReceiver.a());
    }
}
