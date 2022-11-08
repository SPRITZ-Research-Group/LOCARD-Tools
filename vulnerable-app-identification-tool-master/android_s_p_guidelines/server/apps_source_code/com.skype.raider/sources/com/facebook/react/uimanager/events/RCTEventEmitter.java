package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import javax.annotation.Nullable;

public interface RCTEventEmitter extends JavaScriptModule {
    void receiveEvent(int i, String str, @Nullable ar arVar);

    void receiveTouches(String str, aq aqVar, aq aqVar2);
}
