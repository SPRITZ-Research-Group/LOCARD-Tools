package com.skype.smsmanager.models;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.skype.smsmanager.AndroidSmsManagerModule;
import com.skype.smsmanager.nativesms.models.OutgoingMessageStatusImpl;

public final class RnOutgoingMessageStatus implements EventSmsMessage, RnSmsMmsConstants {
    private final OutgoingMessageStatusImpl a;

    public RnOutgoingMessageStatus(OutgoingMessageStatusImpl outgoingMessageStatus) {
        this.a = outgoingMessageStatus;
    }

    public final String a() {
        return AndroidSmsManagerModule.OUTGOING_SMS_STATUS;
    }

    public final ar b() {
        ar jsMessage = new WritableNativeMap();
        jsMessage.putString("cuid", this.a.c());
        jsMessage.putBoolean("wasSent", this.a.b());
        jsMessage.putBoolean("isMms", this.a.a());
        jsMessage.putString("cellularMessageId", this.a.d());
        return jsMessage;
    }
}
