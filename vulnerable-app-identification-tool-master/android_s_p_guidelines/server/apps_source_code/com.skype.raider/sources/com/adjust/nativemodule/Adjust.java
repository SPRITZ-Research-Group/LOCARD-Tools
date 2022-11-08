package com.adjust.nativemodule;

import android.net.Uri;
import com.adjust.sdk.AdjustAttribution;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEvent;
import com.adjust.sdk.AdjustEventFailure;
import com.adjust.sdk.AdjustEventSuccess;
import com.adjust.sdk.AdjustSessionFailure;
import com.adjust.sdk.AdjustSessionSuccess;
import com.adjust.sdk.Constants;
import com.adjust.sdk.LogLevel;
import com.adjust.sdk.OnAttributionChangedListener;
import com.adjust.sdk.OnDeeplinkResponseListener;
import com.adjust.sdk.OnDeviceIdsRead;
import com.adjust.sdk.OnEventTrackingFailedListener;
import com.adjust.sdk.OnEventTrackingSucceededListener;
import com.adjust.sdk.OnSessionTrackingFailedListener;
import com.adjust.sdk.OnSessionTrackingSucceededListener;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.d;
import com.facebook.react.bridge.v;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public class Adjust extends ReactContextBaseJavaModule implements OnAttributionChangedListener, OnDeeplinkResponseListener, OnEventTrackingFailedListener, OnEventTrackingSucceededListener, OnSessionTrackingFailedListener, OnSessionTrackingSucceededListener, v {
    private boolean attributionCallback;
    private boolean deferredDeeplinkCallback;
    private boolean eventTrackingFailedCallback;
    private boolean eventTrackingSucceededCallback;
    private boolean sessionTrackingFailedCallback;
    private boolean sessionTrackingSucceededCallback;
    private boolean shouldLaunchDeeplink = true;

    public Adjust(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return Constants.LOGTAG;
    }

    public void initialize() {
        getReactApplicationContext().a((v) this);
    }

    public void onHostPause() {
        com.adjust.sdk.Adjust.onPause();
    }

    public void onHostResume() {
        com.adjust.sdk.Adjust.onResume();
    }

    public void onHostDestroy() {
    }

    public void onAttributionChanged(AdjustAttribution attribution) {
        sendEvent(getReactApplicationContext(), "adjust_attribution", b.a(attribution));
    }

    public void onFinishedEventTrackingSucceeded(AdjustEventSuccess event) {
        ar arVar;
        ai reactApplicationContext = getReactApplicationContext();
        String str = "adjust_eventTrackingSucceeded";
        ar writableNativeMap = new WritableNativeMap();
        if (event == null) {
            arVar = writableNativeMap;
        } else {
            writableNativeMap.putString("message", event.message != null ? event.message : "");
            writableNativeMap.putString("timestamp", event.timestamp != null ? event.timestamp : "");
            writableNativeMap.putString("adid", event.adid != null ? event.adid : "");
            writableNativeMap.putString("eventToken", event.eventToken != null ? event.eventToken : "");
            writableNativeMap.putString("jsonResponse", event.jsonResponse != null ? event.jsonResponse.toString() : "");
            arVar = writableNativeMap;
        }
        sendEvent(reactApplicationContext, str, arVar);
    }

    public void onFinishedEventTrackingFailed(AdjustEventFailure event) {
        ar arVar;
        ai reactApplicationContext = getReactApplicationContext();
        String str = "adjust_eventTrackingFailed";
        ar writableNativeMap = new WritableNativeMap();
        if (event == null) {
            arVar = writableNativeMap;
        } else {
            writableNativeMap.putString("message", event.message != null ? event.message : "");
            writableNativeMap.putString("timestamp", event.timestamp != null ? event.timestamp : "");
            writableNativeMap.putString("adid", event.adid != null ? event.adid : "");
            writableNativeMap.putString("eventToken", event.eventToken != null ? event.eventToken : "");
            writableNativeMap.putString("willRetry", event.willRetry ? "true" : "false");
            writableNativeMap.putString("jsonResponse", event.jsonResponse != null ? event.jsonResponse.toString() : "");
            arVar = writableNativeMap;
        }
        sendEvent(reactApplicationContext, str, arVar);
    }

    public void onFinishedSessionTrackingSucceeded(AdjustSessionSuccess session) {
        ar arVar;
        ai reactApplicationContext = getReactApplicationContext();
        String str = "adjust_sessionTrackingSucceeded";
        ar writableNativeMap = new WritableNativeMap();
        if (session == null) {
            arVar = writableNativeMap;
        } else {
            writableNativeMap.putString("message", session.message != null ? session.message : "");
            writableNativeMap.putString("timestamp", session.timestamp != null ? session.timestamp : "");
            writableNativeMap.putString("adid", session.adid != null ? session.adid : "");
            writableNativeMap.putString("jsonResponse", session.jsonResponse != null ? session.jsonResponse.toString() : "");
            arVar = writableNativeMap;
        }
        sendEvent(reactApplicationContext, str, arVar);
    }

    public void onFinishedSessionTrackingFailed(AdjustSessionFailure session) {
        ar arVar;
        ai reactApplicationContext = getReactApplicationContext();
        String str = "adjust_sessionTrackingFailed";
        ar writableNativeMap = new WritableNativeMap();
        if (session == null) {
            arVar = writableNativeMap;
        } else {
            writableNativeMap.putString("message", session.message != null ? session.message : "");
            writableNativeMap.putString("timestamp", session.timestamp != null ? session.timestamp : "");
            writableNativeMap.putString("adid", session.adid != null ? session.adid : "");
            writableNativeMap.putString("willRetry", session.willRetry ? "true" : "false");
            writableNativeMap.putString("jsonResponse", session.jsonResponse != null ? session.jsonResponse.toString() : "");
            arVar = writableNativeMap;
        }
        sendEvent(reactApplicationContext, str, arVar);
    }

    public boolean launchReceivedDeeplink(Uri uri) {
        ai reactApplicationContext = getReactApplicationContext();
        String str = "adjust_deferredDeeplink";
        ar writableNativeMap = new WritableNativeMap();
        if (uri != null) {
            writableNativeMap.putString(ReactVideoViewManager.PROP_SRC_URI, uri.toString());
        }
        sendEvent(reactApplicationContext, str, writableNativeMap);
        return this.shouldLaunchDeeplink;
    }

    @ReactMethod
    public void create(am mapConfig) {
        boolean isLogLevelSuppress = false;
        if (!mapConfig.isNull("logLevel") && mapConfig.getString("logLevel").equals("SUPPRESS")) {
            isLogLevelSuppress = true;
        }
        AdjustConfig adjustConfig = new AdjustConfig(getReactApplicationContext(), mapConfig.getString("appToken"), mapConfig.getString("environment"), isLogLevelSuppress);
        if (adjustConfig.isValid()) {
            if (!mapConfig.isNull("logLevel")) {
                String logLevel = mapConfig.getString("logLevel");
                if (logLevel.equals("VERBOSE")) {
                    adjustConfig.setLogLevel(LogLevel.VERBOSE);
                } else if (logLevel.equals("DEBUG")) {
                    adjustConfig.setLogLevel(LogLevel.DEBUG);
                } else {
                    if (!logLevel.equals("INFO")) {
                        if (logLevel.equals("WARN")) {
                            adjustConfig.setLogLevel(LogLevel.WARN);
                        } else if (logLevel.equals("ERROR")) {
                            adjustConfig.setLogLevel(LogLevel.ERROR);
                        } else if (logLevel.equals("ASSERT")) {
                            adjustConfig.setLogLevel(LogLevel.ASSERT);
                        } else if (logLevel.equals("SUPPRESS")) {
                            adjustConfig.setLogLevel(LogLevel.SUPRESS);
                        }
                    }
                    adjustConfig.setLogLevel(LogLevel.INFO);
                }
            }
            if (!mapConfig.isNull("eventBufferingEnabled")) {
                adjustConfig.setEventBufferingEnabled(Boolean.valueOf(mapConfig.getBoolean("eventBufferingEnabled")));
            }
            if (!mapConfig.isNull("sdkPrefix")) {
                adjustConfig.setSdkPrefix(mapConfig.getString("sdkPrefix"));
            }
            if (!mapConfig.isNull("processName")) {
                adjustConfig.setProcessName(mapConfig.getString("processName"));
            }
            if (!mapConfig.isNull("defaultTracker")) {
                adjustConfig.setDefaultTracker(mapConfig.getString("defaultTracker"));
            }
            if (!mapConfig.isNull("userAgent")) {
                adjustConfig.setUserAgent(mapConfig.getString("userAgent"));
            }
            if (!(mapConfig.isNull("secretId") || mapConfig.isNull("info1") || mapConfig.isNull("info2") || mapConfig.isNull("info3") || mapConfig.isNull("info4"))) {
                try {
                    adjustConfig.setAppSecret(Long.parseLong(mapConfig.getString("secretId"), 10), Long.parseLong(mapConfig.getString("info1"), 10), Long.parseLong(mapConfig.getString("info2"), 10), Long.parseLong(mapConfig.getString("info3"), 10), Long.parseLong(mapConfig.getString("info4"), 10));
                } catch (NumberFormatException e) {
                }
            }
            if (!mapConfig.isNull("sendInBackground")) {
                adjustConfig.setSendInBackground(mapConfig.getBoolean("sendInBackground"));
            }
            if (!mapConfig.isNull("isDeviceKnown")) {
                adjustConfig.setDeviceKnown(mapConfig.getBoolean("isDeviceKnown"));
            }
            if (!mapConfig.isNull("readMobileEquipmentIdentity")) {
                adjustConfig.setReadMobileEquipmentIdentity(mapConfig.getBoolean("readMobileEquipmentIdentity"));
            }
            if (!mapConfig.isNull("shouldLaunchDeeplink")) {
                this.shouldLaunchDeeplink = mapConfig.getBoolean("shouldLaunchDeeplink");
            }
            if (!mapConfig.isNull("delayStart")) {
                adjustConfig.setDelayStart(mapConfig.getDouble("delayStart"));
            }
            if (this.attributionCallback) {
                adjustConfig.setOnAttributionChangedListener(this);
            }
            if (this.eventTrackingSucceededCallback) {
                adjustConfig.setOnEventTrackingSucceededListener(this);
            }
            if (this.eventTrackingFailedCallback) {
                adjustConfig.setOnEventTrackingFailedListener(this);
            }
            if (this.sessionTrackingSucceededCallback) {
                adjustConfig.setOnSessionTrackingSucceededListener(this);
            }
            if (this.sessionTrackingFailedCallback) {
                adjustConfig.setOnSessionTrackingFailedListener(this);
            }
            if (this.deferredDeeplinkCallback) {
                adjustConfig.setOnDeeplinkResponseListener(this);
            }
            com.adjust.sdk.Adjust.onCreate(adjustConfig);
            com.adjust.sdk.Adjust.onResume();
        }
    }

    @ReactMethod
    public void trackEvent(am mapEvent) {
        String eventToken = mapEvent.getString("eventToken");
        String currency = mapEvent.getString("currency");
        String transactionId = mapEvent.getString("transactionId");
        Map<String, Object> callbackParameters = b.a(mapEvent.getMap("callbackParameters"));
        Map<String, Object> partnerParameters = b.a(mapEvent.getMap("partnerParameters"));
        AdjustEvent event = new AdjustEvent(eventToken);
        if (event.isValid()) {
            if (!mapEvent.isNull("revenue")) {
                event.setRevenue(mapEvent.getDouble("revenue"), currency);
            }
            if (callbackParameters != null) {
                for (Entry<String, Object> entry : callbackParameters.entrySet()) {
                    event.addCallbackParameter((String) entry.getKey(), entry.getValue().toString());
                }
            }
            if (partnerParameters != null) {
                for (Entry<String, Object> entry2 : partnerParameters.entrySet()) {
                    event.addPartnerParameter((String) entry2.getKey(), entry2.getValue().toString());
                }
            }
            if (transactionId != null) {
                event.setOrderId(transactionId);
            }
            com.adjust.sdk.Adjust.trackEvent(event);
        }
    }

    @ReactMethod
    public void setEnabled(Boolean enabled) {
        com.adjust.sdk.Adjust.setEnabled(enabled.booleanValue());
    }

    @ReactMethod
    public void isEnabled(d callback) {
        callback.invoke(Boolean.valueOf(com.adjust.sdk.Adjust.isEnabled()));
    }

    @ReactMethod
    public void setReferrer(String referrer) {
        com.adjust.sdk.Adjust.setReferrer(referrer, getReactApplicationContext());
    }

    @ReactMethod
    public void setOfflineMode(Boolean enabled) {
        com.adjust.sdk.Adjust.setOfflineMode(enabled.booleanValue());
    }

    @ReactMethod
    public void setPushToken(String token) {
        com.adjust.sdk.Adjust.setPushToken(token);
    }

    @ReactMethod
    public void appWillOpenUrl(String strUri) {
        com.adjust.sdk.Adjust.appWillOpenUrl(Uri.parse(strUri));
    }

    @ReactMethod
    public void sendFirstPackages() {
        com.adjust.sdk.Adjust.sendFirstPackages();
    }

    @ReactMethod
    public void addSessionCallbackParameter(String key, String value) {
        com.adjust.sdk.Adjust.addSessionCallbackParameter(key, value);
    }

    @ReactMethod
    public void addSessionPartnerParameter(String key, String value) {
        com.adjust.sdk.Adjust.addSessionPartnerParameter(key, value);
    }

    @ReactMethod
    public void removeSessionCallbackParameter(String key) {
        com.adjust.sdk.Adjust.removeSessionCallbackParameter(key);
    }

    @ReactMethod
    public void removeSessionPartnerParameter(String key) {
        com.adjust.sdk.Adjust.removeSessionPartnerParameter(key);
    }

    @ReactMethod
    public void resetSessionCallbackParameters() {
        com.adjust.sdk.Adjust.resetSessionCallbackParameters();
    }

    @ReactMethod
    public void resetSessionPartnerParameters() {
        com.adjust.sdk.Adjust.resetSessionPartnerParameters();
    }

    @ReactMethod
    public void getIdfa(d callback) {
        callback.invoke("");
    }

    @ReactMethod
    public void getGoogleAdId(final d callback) {
        com.adjust.sdk.Adjust.getGoogleAdId(getReactApplicationContext(), new OnDeviceIdsRead(this) {
            final /* synthetic */ Adjust b;

            public final void onGoogleAdIdRead(String googleAdId) {
                callback.invoke(googleAdId);
            }
        });
    }

    @ReactMethod
    public void getAdid(d callback) {
        callback.invoke(com.adjust.sdk.Adjust.getAdid());
    }

    @ReactMethod
    public void getAmazonAdId(d callback) {
        callback.invoke(com.adjust.sdk.Adjust.getAmazonAdId(getReactApplicationContext()));
    }

    @ReactMethod
    public void getAttribution(d callback) {
        callback.invoke(b.a(com.adjust.sdk.Adjust.getAttribution()));
    }

    @ReactMethod
    public void setAttributionCallbackListener() {
        this.attributionCallback = true;
    }

    @ReactMethod
    public void setEventTrackingSucceededCallbackListener() {
        this.eventTrackingSucceededCallback = true;
    }

    @ReactMethod
    public void setEventTrackingFailedCallbackListener() {
        this.eventTrackingFailedCallback = true;
    }

    @ReactMethod
    public void setSessionTrackingSucceededCallbackListener() {
        this.sessionTrackingSucceededCallback = true;
    }

    @ReactMethod
    public void setSessionTrackingFailedCallbackListener() {
        this.sessionTrackingFailedCallback = true;
    }

    @ReactMethod
    public void setDeferredDeeplinkCallbackListener() {
        this.deferredDeeplinkCallback = true;
    }

    private void sendEvent(ai reactContext, String eventName, @Nullable ar params) {
        ((RCTDeviceEventEmitter) reactContext.a(RCTDeviceEventEmitter.class)).emit(eventName, params);
    }
}
