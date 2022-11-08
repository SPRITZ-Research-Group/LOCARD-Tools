package com.microsoft.react.incomingcallinteractionmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import java.util.ArrayList;
import java.util.List;

public class IncomingCallInteractionManagerModule extends ReactContextBaseJavaModule {
    public static String ACTION_INCOMING_RING_RECEIVED = "IncomingCallInteractionManager_IncomingRingReceived";
    public static String INCOMING_RING_CALL_ID = "IncomingCallInteractionManager_IncomingRingCallId";
    private static final String MODULE_NAME = "IncomingCallInteractionManager";
    public static String SHOW_INCOMING_RING = "IncomingCallInteractionManager_ShowIncomingRing";
    private boolean backgroundActivityOnCallEnd;
    private a deviceUtilitiesProvider;
    private List<String> incomingCallIds = new ArrayList();
    private boolean keyguardDisabled = false;
    private boolean screenTurnedOn = false;
    private boolean showWhenLockedEnabled;

    public IncomingCallInteractionManagerModule(ag reactContext) {
        super(reactContext);
    }

    public a getDeviceUtilitiesProvider() {
        return this.deviceUtilitiesProvider;
    }

    public void setDeviceUtilitiesProvider(a provider) {
        FLog.i(MODULE_NAME, "set deviceUtilities provider called");
        if (provider == null) {
            throw new IllegalArgumentException("Must provide device utilities provider");
        }
        this.deviceUtilitiesProvider = provider;
    }

    public String getName() {
        return MODULE_NAME;
    }

    public void processLaunchIntent(Intent intent) {
        boolean isIncomingCallIntent = intent.getBooleanExtra(SHOW_INCOMING_RING, false);
        String incomingCallId = intent.getStringExtra(INCOMING_RING_CALL_ID);
        if (isIncomingCallIntent && this.incomingCallIds.contains(incomingCallId)) {
            FLog.i(MODULE_NAME, "Processing launch intent for incoming call");
            if (isDeviceUtilitiesProviderInitialized()) {
                this.deviceUtilitiesProvider.a();
                this.deviceUtilitiesProvider.c();
                this.deviceUtilitiesProvider.e();
                this.screenTurnedOn = true;
                this.keyguardDisabled = true;
                this.showWhenLockedEnabled = true;
                this.backgroundActivityOnCallEnd = true;
                return;
            }
            FLog.w(MODULE_NAME, "Cannot disable screen guard/turnScreenOn/enableShowWhenLocked");
            return;
        }
        FLog.w(MODULE_NAME, "Did not process call intent for callID: " + incomingCallId + " isIncomingCallIntent: " + isIncomingCallIntent);
    }

    @ReactMethod
    public void showIncomingRing(String callId) {
        if (this.incomingCallIds.contains(callId)) {
            FLog.e(MODULE_NAME, "Not starting activity for call Id as it's already created, this should not happen, callId: " + callId);
            return;
        }
        this.incomingCallIds.add(callId);
        FLog.i(MODULE_NAME, "showIncoming ring called for callId: " + callId);
        Context context = getReactApplicationContext();
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntent.setFlags(268468224);
        launchIntent.setAction(ACTION_INCOMING_RING_RECEIVED);
        Bundle arguments = new Bundle();
        arguments.putBoolean(SHOW_INCOMING_RING, true);
        arguments.putString(INCOMING_RING_CALL_ID, callId);
        launchIntent.putExtras(arguments);
        context.startActivity(launchIntent);
    }

    @ReactMethod
    public void onCallStarted(String callId) {
        FLog.i(MODULE_NAME, "onCallStarted called callId: " + callId);
    }

    @ReactMethod
    public void onCallEnded(String callId) {
        FLog.i(MODULE_NAME, "onCallEnded called backgroundActivityOnCallEnd: " + this.backgroundActivityOnCallEnd + " keyguardDisabled: " + this.keyguardDisabled + " screenTurnedOn: " + this.screenTurnedOn + " showWhenLocked: " + this.showWhenLockedEnabled + "hasCurrentActivity: s4l" + getReactApplicationContext().i() + "callId: " + callId);
        this.incomingCallIds.remove(callId);
        if (isDeviceUtilitiesProviderInitialized()) {
            if (this.keyguardDisabled) {
                this.deviceUtilitiesProvider.b();
                this.keyguardDisabled = false;
            }
            if (this.screenTurnedOn) {
                this.deviceUtilitiesProvider.d();
                this.screenTurnedOn = false;
            }
            if (this.showWhenLockedEnabled) {
                this.deviceUtilitiesProvider.f();
                this.showWhenLockedEnabled = false;
            }
            if (this.backgroundActivityOnCallEnd) {
                if (getReactApplicationContext().i()) {
                    getCurrentActivity().moveTaskToBack(false);
                }
                this.backgroundActivityOnCallEnd = false;
            }
        }
    }

    @ReactMethod
    public void onCallScreenOpen() {
    }

    @ReactMethod
    public void onCallScreenClose() {
        FLog.i(MODULE_NAME, "onCallScreenClose called, keyguardDisabled: " + this.keyguardDisabled + " showWhenLockedEnabled: " + this.showWhenLockedEnabled);
        if (isDeviceUtilitiesProviderInitialized()) {
            if (this.keyguardDisabled) {
                this.deviceUtilitiesProvider.b();
                this.keyguardDisabled = false;
            }
            if (this.showWhenLockedEnabled) {
                this.deviceUtilitiesProvider.f();
                this.showWhenLockedEnabled = false;
            }
        }
        this.backgroundActivityOnCallEnd = false;
    }

    private boolean isDeviceUtilitiesProviderInitialized() {
        boolean isReactContextReady = true;
        if (this.deviceUtilitiesProvider != null) {
            return true;
        }
        if (getReactApplicationContext() == null || !getReactApplicationContext().b()) {
            isReactContextReady = false;
        }
        FLog.w(MODULE_NAME, "Device Utilities provider not added isReactContextReady: " + isReactContextReady);
        return false;
    }
}
