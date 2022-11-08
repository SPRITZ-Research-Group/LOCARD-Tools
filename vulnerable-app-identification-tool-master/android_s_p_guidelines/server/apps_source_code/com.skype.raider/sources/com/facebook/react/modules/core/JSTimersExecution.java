package com.facebook.react.modules.core;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.aq;

public interface JSTimersExecution extends JavaScriptModule {
    void callIdleCallbacks(double d);

    void callTimers(aq aqVar);

    void emitTimeDriftWarning(String str);
}
