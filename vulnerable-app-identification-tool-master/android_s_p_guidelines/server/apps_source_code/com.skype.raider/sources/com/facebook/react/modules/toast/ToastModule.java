package com.facebook.react.modules.toast;

import android.widget.Toast;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ap;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name = "ToastAndroid")
public class ToastModule extends ReactContextBaseJavaModule {
    private static final String DURATION_LONG_KEY = "LONG";
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String GRAVITY_BOTTOM_KEY = "BOTTOM";
    private static final String GRAVITY_CENTER = "CENTER";
    private static final String GRAVITY_TOP_KEY = "TOP";

    public ToastModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "ToastAndroid";
    }

    @ReactMethod
    public void show(final String message, final int duration) {
        ap.a(new Runnable(this) {
            final /* synthetic */ ToastModule c;

            public final void run() {
                Toast.makeText(this.c.getReactApplicationContext(), message, duration).show();
            }
        });
    }

    @ReactMethod
    public void showWithGravity(final String message, final int duration, final int gravity) {
        ap.a(new Runnable(this) {
            final /* synthetic */ ToastModule d;

            public final void run() {
                Toast toast = Toast.makeText(this.d.getReactApplicationContext(), message, duration);
                toast.setGravity(gravity, 0, 0);
                toast.show();
            }
        });
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        constants.put(DURATION_SHORT_KEY, Integer.valueOf(0));
        constants.put(DURATION_LONG_KEY, Integer.valueOf(1));
        constants.put(GRAVITY_TOP_KEY, Integer.valueOf(49));
        constants.put(GRAVITY_BOTTOM_KEY, Integer.valueOf(81));
        constants.put(GRAVITY_CENTER, Integer.valueOf(17));
        return constants;
    }
}
