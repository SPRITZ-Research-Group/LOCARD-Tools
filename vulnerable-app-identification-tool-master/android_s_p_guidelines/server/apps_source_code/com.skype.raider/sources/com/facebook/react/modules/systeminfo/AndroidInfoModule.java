package com.facebook.react.modules.systeminfo;

import android.os.Build;
import android.os.Build.VERSION;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "PlatformConstants")
public class AndroidInfoModule extends BaseJavaModule {
    private static final String IS_TESTING = "IS_TESTING";

    public String getName() {
        return "PlatformConstants";
    }

    @Nullable
    public Map<String, Object> getConstants() {
        Object obj;
        HashMap<String, Object> constants = new HashMap();
        constants.put("Version", Integer.valueOf(VERSION.SDK_INT));
        String str = "ServerHost";
        if (Build.FINGERPRINT.contains("vbox")) {
            obj = "10.0.3.2:8081";
        } else if (Build.FINGERPRINT.contains("generic")) {
            obj = "10.0.2.2:8081";
        } else {
            obj = "localhost:8081";
        }
        constants.put(str, obj);
        constants.put("isTesting", Boolean.valueOf("true".equals(System.getProperty(IS_TESTING))));
        return constants;
    }
}
