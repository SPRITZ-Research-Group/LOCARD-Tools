package com.facebook.react.modules.debug;

import com.facebook.infer.annotation.a;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ai;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "SourceCode")
public class SourceCodeModule extends BaseJavaModule {
    public static final String NAME = "SourceCode";
    private final ai mReactContext;

    public SourceCodeModule(ai reactContext) {
        this.mReactContext = reactContext;
    }

    public String getName() {
        return NAME;
    }

    @Nullable
    public Map<String, Object> getConstants() {
        HashMap<String, Object> constants = new HashMap();
        constants.put("scriptURL", (String) a.a(this.mReactContext.a().getSourceURL(), "No source URL loaded, have you initialised the instance?"));
        return constants;
    }
}
