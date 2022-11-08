package com.facebook.react.modules.appregistry;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ar;

public interface AppRegistry extends JavaScriptModule {
    void runApplication(String str, ar arVar);

    void startHeadlessTask(int i, String str, ar arVar);

    void unmountApplicationComponentAtRootTag(int i);
}
