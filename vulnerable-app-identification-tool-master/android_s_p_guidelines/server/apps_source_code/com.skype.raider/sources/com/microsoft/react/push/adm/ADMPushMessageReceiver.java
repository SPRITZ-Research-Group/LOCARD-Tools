package com.microsoft.react.push.adm;

import com.amazon.device.messaging.ADMMessageReceiver;

public class ADMPushMessageReceiver extends ADMMessageReceiver {
    public ADMPushMessageReceiver() {
        super(ADMMessageHandler.class);
    }
}
