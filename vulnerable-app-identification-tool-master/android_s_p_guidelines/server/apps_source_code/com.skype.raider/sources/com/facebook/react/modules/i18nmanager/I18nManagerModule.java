package com.facebook.react.modules.i18nmanager;

import android.content.Context;
import com.facebook.react.bridge.ContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ReactModule(name = "I18nManager")
public class I18nManagerModule extends ContextBaseJavaModule {
    private final a sharedI18nUtilInstance = a.a();

    public I18nManagerModule(Context context) {
        super(context);
    }

    public String getName() {
        return "I18nManager";
    }

    public Map<String, Object> getConstants() {
        Context context = getContext();
        Locale locale = context.getResources().getConfiguration().locale;
        Map<String, Object> constants = new HashMap();
        constants.put("isRTL", Boolean.valueOf(a.a(context)));
        constants.put("localeIdentifier", locale.toString());
        return constants;
    }

    @ReactMethod
    public void allowRTL(boolean value) {
        a.a(getContext(), value);
    }

    @ReactMethod
    public void forceRTL(boolean value) {
        a.b(getContext(), value);
    }
}
