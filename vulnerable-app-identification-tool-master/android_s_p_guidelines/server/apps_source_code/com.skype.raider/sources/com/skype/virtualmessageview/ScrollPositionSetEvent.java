package com.skype.virtualmessageview;

import android.support.v4.util.j.c;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public class ScrollPositionSetEvent extends b<ScrollPositionSetEvent> {
    private static final c<ScrollPositionSetEvent> a = new c(3);

    private ScrollPositionSetEvent() {
    }

    public static ScrollPositionSetEvent b(int viewTag) {
        ScrollPositionSetEvent event = (ScrollPositionSetEvent) a.a();
        if (event == null) {
            event = new ScrollPositionSetEvent();
        }
        super.a(viewTag);
        return event;
    }

    public final void c() {
        a.a(this);
    }

    protected final void a(int viewTag) {
        super.a(viewTag);
    }

    public final String a() {
        return VirtualMessageViewControllerManager.SCROLL_SET_EVENT;
    }

    public final boolean b() {
        return false;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(d(), VirtualMessageViewControllerManager.SCROLL_SET_EVENT, new WritableNativeMap());
    }
}
