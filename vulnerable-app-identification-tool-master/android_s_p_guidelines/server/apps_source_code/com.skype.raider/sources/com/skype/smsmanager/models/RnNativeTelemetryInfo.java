package com.skype.smsmanager.models;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.skype.smsmanager.AndroidSmsManagerModule;
import java.util.HashMap;
import java.util.Map.Entry;

public class RnNativeTelemetryInfo implements EventSmsMessage {
    private final String a = AndroidSmsManagerModule.NATIVE_TELEMETRY_INFO;
    private final HashMap<String, Object> b;
    private final String c;
    private final String d = "messaging_sms_relay_native_";

    public RnNativeTelemetryInfo(HashMap<String, Object> telemetryInfos) {
        this.b = telemetryInfos;
        this.c = String.valueOf(this.b.get("TelemetryEventName"));
    }

    public final String a() {
        return AndroidSmsManagerModule.NATIVE_TELEMETRY_INFO;
    }

    public final ar b() {
        ar jsMessage = new WritableNativeMap();
        if (String.valueOf(this.b.get("TelemetryEventName")) != null) {
            for (Entry<String, Object> telemetryKeyValue : this.b.entrySet()) {
                if (telemetryKeyValue.getValue() instanceof Boolean) {
                    jsMessage.putBoolean((String) telemetryKeyValue.getKey(), ((Boolean) telemetryKeyValue.getValue()).booleanValue());
                } else if (telemetryKeyValue.getValue() instanceof Integer) {
                    jsMessage.putInt((String) telemetryKeyValue.getKey(), ((Integer) telemetryKeyValue.getValue()).intValue());
                } else if (telemetryKeyValue.getValue() instanceof Double) {
                    jsMessage.putDouble((String) telemetryKeyValue.getKey(), ((Double) telemetryKeyValue.getValue()).doubleValue());
                } else if (telemetryKeyValue.getValue() instanceof Float) {
                    jsMessage.putDouble((String) telemetryKeyValue.getKey(), (double) ((Float) telemetryKeyValue.getValue()).floatValue());
                } else if (telemetryKeyValue.getValue() instanceof String) {
                    jsMessage.putString((String) telemetryKeyValue.getKey(), (String) telemetryKeyValue.getValue());
                } else {
                    FLog.i(AndroidSmsManagerModule.NATIVE_TELEMETRY_INFO, String.format("toWritableMap() - couldn't cast value in telemetry map entry. Key:%s", new Object[]{telemetryKeyValue.getKey()}));
                }
            }
        }
        return jsMessage;
    }

    public final String c() {
        if (this.c.isEmpty() || this.c == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        getClass();
        return stringBuilder.append("messaging_sms_relay_native_").append(this.c).toString();
    }
}
