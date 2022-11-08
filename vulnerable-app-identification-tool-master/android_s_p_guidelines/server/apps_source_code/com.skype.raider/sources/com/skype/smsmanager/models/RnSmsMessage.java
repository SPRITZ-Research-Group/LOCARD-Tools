package com.skype.smsmanager.models;

import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.skype.smsmanager.AndroidSmsManagerModule;
import com.skype.smsmanager.nativesms.models.SmsMessageItem;

public class RnSmsMessage implements EventSmsMessage, RnSmsMmsConstants {
    final SmsMessageItem a;
    final String b;

    public RnSmsMessage(SmsMessageItem smsMessageItem, String wakeLockId) {
        this.a = smsMessageItem;
        this.b = wakeLockId;
    }

    public final String a() {
        return AndroidSmsManagerModule.INCOMING_SMS;
    }

    public final ar b() {
        ar jsMessage = new WritableNativeMap();
        jsMessage.putString("body", this.a.c());
        jsMessage.putString("timestampMs", new Long(this.a.d()).toString());
        jsMessage.putInt("cuid", 0);
        aq recipientList = new WritableNativeArray();
        recipientList.pushString(this.a.a());
        jsMessage.putArray("recipients", recipientList);
        jsMessage.putString("sender", this.a.b());
        jsMessage.putString("wakeLockId", this.b);
        jsMessage.putDouble("telemetryIncomingSmsIntentTime", this.a.e());
        jsMessage.putString("cellularMessageId", this.a.f());
        return jsMessage;
    }
}
