package com.skype.timezone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class TimeZoneModule extends ReactContextBaseJavaModule {
    private static final String INITIAL_TIME_ZONE_ID_KEY = "InitialTimeZoneId";
    ag context;
    BroadcastReceiver timeZoneChangedReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ TimeZoneModule a;

        {
            this.a = this$0;
        }

        public final void onReceive(Context context, Intent intent) {
            this.a.sendTimeZoneEvent();
        }
    };

    public TimeZoneModule(ag reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        constants.put(INITIAL_TIME_ZONE_ID_KEY, TimeZone.getDefault().getID());
        return constants;
    }

    public void initialize() {
        super.initialize();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        this.context.registerReceiver(this.timeZoneChangedReceiver, intentFilter);
    }

    public void onCatalystInstanceDestroy() {
        this.context.unregisterReceiver(this.timeZoneChangedReceiver);
        super.onCatalystInstanceDestroy();
    }

    public String getName() {
        return "TimeZoneManager";
    }

    @ReactMethod
    public void getTimeZone(ae promise) {
        promise.a(TimeZone.getDefault().getID());
    }

    private void sendTimeZoneEvent() {
        ar params = new WritableNativeMap();
        params.putString("name", TimeZone.getDefault().getID());
        ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit("TimeZoneChange", params);
    }
}
