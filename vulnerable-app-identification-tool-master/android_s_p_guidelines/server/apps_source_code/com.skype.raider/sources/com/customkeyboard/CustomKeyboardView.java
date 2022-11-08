package com.customkeyboard;

import android.annotation.SuppressLint;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.p;
import com.facebook.react.views.view.ReactViewGroup;

@SuppressLint({"ViewConstructor"})
public class CustomKeyboardView extends ReactViewGroup {
    public String a;
    public boolean b;
    public boolean c;
    private float d;
    private boolean e;

    public CustomKeyboardView(ai context) {
        super(context);
    }

    public void setDefaultHeight(int height) {
        if (this.c) {
            e();
        } else if (this.d != ((float) height)) {
            this.d = (float) height;
            e();
        }
    }

    public void setVisible(boolean visible) {
        if (this.e != visible) {
            this.e = visible;
            e();
        }
    }

    private void e() {
        ar event = new WritableNativeMap();
        event.putDouble("height", (double) p.b(this.d));
        event.putBoolean("visible", this.e);
        ((RCTEventEmitter) ((ai) getContext()).a(RCTEventEmitter.class)).receiveEvent(getId(), "topChange", event);
    }
}
