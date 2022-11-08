package com.skype.smsmanager.models;

import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.skype.smsmanager.AndroidSmsManagerModule;
import com.skype.smsmanager.nativesms.models.MmsMediaItem;
import com.skype.smsmanager.nativesms.models.MmsMessageItem;
import java.util.Iterator;

public class RnMmsMessage implements EventSmsMessage, RnSmsMmsConstants {
    private final MmsMessageItem a;
    private final String b;

    public RnMmsMessage(MmsMessageItem mmsMessageItem, String wakeLockId) {
        this.a = mmsMessageItem;
        this.b = wakeLockId;
    }

    public final String a() {
        return AndroidSmsManagerModule.INCOMING_SMS;
    }

    public final ar b() {
        ar jsMessage = new WritableNativeMap();
        jsMessage.putString("timestampMs", new Long(this.a.a()).toString());
        jsMessage.putString("body", this.a.b());
        jsMessage.putInt("cuid", 0);
        if (!"".equals(this.a.c())) {
            jsMessage.putString("sender", this.a.c());
        }
        aq recipientList = new WritableNativeArray();
        Iterator it = this.a.d().iterator();
        while (it.hasNext()) {
            recipientList.pushString((String) it.next());
        }
        jsMessage.putArray("recipients", recipientList);
        aq mediaList = new WritableNativeArray();
        for (MmsMediaItem item : this.a.g()) {
            ar map = new WritableNativeMap();
            map.putString(ReactVideoViewManager.PROP_SRC_URI, item.a());
            map.putString("name", item.b());
            map.putString("type", item.c());
            map.putDouble("size", (double) item.d());
            mediaList.pushMap(map);
        }
        jsMessage.putArray("media", mediaList);
        jsMessage.putString("wakeLockId", this.b);
        jsMessage.putDouble("telemetryIncomingSmsIntentTime", this.a.e());
        jsMessage.putInt("telemetryGetMmsRecipientsIterations", this.a.f());
        jsMessage.putString("cellularMessageId", this.a.h());
        return jsMessage;
    }
}
