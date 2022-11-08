package com.skype.smsmanager.models;

import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.skype.smsmanager.AndroidSmsManagerModule;
import com.skype.smsmanager.nativesms.models.DeletedCellularMessagesItem;
import java.util.ArrayList;
import java.util.Iterator;

public class RnDeletedCellularMessages implements EventSmsMessage, RnSmsMmsConstants {
    private final ArrayList<String> a;

    public RnDeletedCellularMessages(DeletedCellularMessagesItem deletedCellularMessagesItem) {
        this.a = deletedCellularMessagesItem.a();
    }

    public final String a() {
        return AndroidSmsManagerModule.DELETED_CELLULAR_MESSAGES;
    }

    public final ar b() {
        ar jsMessage = new WritableNativeMap();
        aq deletedCellularMessageIds = new WritableNativeArray();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            deletedCellularMessageIds.pushString((String) it.next());
        }
        jsMessage.putArray("deletedCellularMessageIds", deletedCellularMessageIds);
        return jsMessage;
    }
}
