package com.facebook.react.views.image;

import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;
import com.skype.camera.imagefilter.ImageFilterManager;
import javax.annotation.Nullable;
import net.hockeyapp.android.j;

public final class a extends b<a> {
    private final int a;
    @Nullable
    private final String b;
    private final int c;
    private final int d;

    public a(int viewId, int eventType) {
        this(viewId, eventType, (byte) 0);
    }

    private a(int viewId, int eventType, byte b) {
        this(viewId, eventType, null, 0, 0);
    }

    public a(int viewId, int eventType, @Nullable String imageUri, int width, int height) {
        super(viewId);
        this.a = eventType;
        this.b = imageUri;
        this.c = width;
        this.d = height;
    }

    public static String b(int eventType) {
        switch (eventType) {
            case 1:
                return "topError";
            case 2:
                return "topLoad";
            case 3:
                return "topLoadEnd";
            case 4:
                return "topLoadStart";
            case 5:
                return "topProgress";
            default:
                throw new IllegalStateException("Invalid image event: " + Integer.toString(eventType));
        }
    }

    public final String a() {
        return b(this.a);
    }

    public final short f() {
        return (short) this.a;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar eventData = null;
        if (this.b != null || this.a == 2) {
            eventData = new WritableNativeMap();
            if (this.b != null) {
                eventData.putString(ReactVideoViewManager.PROP_SRC_URI, this.b);
            }
            if (this.a == 2) {
                ar source = new WritableNativeMap();
                source.putDouble("width", (double) this.c);
                source.putDouble("height", (double) this.d);
                if (this.b != null) {
                    source.putString(j.FRAGMENT_URL, this.b);
                }
                eventData.putMap(ImageFilterManager.PROP_SOURCE, source);
            }
        }
        rctEventEmitter.receiveEvent(d(), b(this.a), eventData);
    }
}
