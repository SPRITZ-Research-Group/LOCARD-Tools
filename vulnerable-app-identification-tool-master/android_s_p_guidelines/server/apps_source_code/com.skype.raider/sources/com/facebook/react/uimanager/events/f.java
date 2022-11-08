package com.facebook.react.uimanager.events;

import android.support.v4.util.j.c;
import android.view.MotionEvent;
import com.facebook.infer.annotation.a;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.an;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.p;
import javax.annotation.Nullable;

public final class f extends b<f> {
    private static final c<f> a = new c(3);
    @Nullable
    private MotionEvent b;
    @Nullable
    private h c;
    private short d;
    private float e;
    private float f;

    public static f a(int viewTag, h touchEventType, MotionEvent motionEventToCopy, long gestureStartTime, float viewX, float viewY, g touchEventCoalescingKeyHelper) {
        short s = (short) 0;
        f event = (f) a.a();
        if (event == null) {
            event = new f();
        }
        super.a(viewTag);
        an.a(gestureStartTime != Long.MIN_VALUE, "Gesture start time must be initialized");
        int action = motionEventToCopy.getAction() & 255;
        switch (action) {
            case 0:
                touchEventCoalescingKeyHelper.a(gestureStartTime);
                break;
            case 1:
                touchEventCoalescingKeyHelper.d(gestureStartTime);
                break;
            case 2:
                s = touchEventCoalescingKeyHelper.c(gestureStartTime);
                break;
            case 3:
                touchEventCoalescingKeyHelper.d(gestureStartTime);
                break;
            case 5:
            case 6:
                touchEventCoalescingKeyHelper.b(gestureStartTime);
                break;
            default:
                throw new RuntimeException("Unhandled MotionEvent action: " + action);
        }
        event.c = touchEventType;
        event.b = MotionEvent.obtain(motionEventToCopy);
        event.d = s;
        event.e = viewX;
        event.f = viewY;
        return event;
    }

    private f() {
    }

    public final void c() {
        ((MotionEvent) a.a(this.b)).recycle();
        this.b = null;
        a.a(this);
    }

    public final String a() {
        return ((h) a.a(this.c)).a();
    }

    public final boolean b() {
        switch ((h) a.a(this.c)) {
            case START:
            case END:
            case CANCEL:
                return false;
            case MOVE:
                return true;
            default:
                throw new RuntimeException("Unknown touch event type: " + this.c);
        }
    }

    public final short f() {
        return this.d;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        int i = 0;
        h hVar = (h) a.a(this.c);
        int d = d();
        aq writableNativeArray = new WritableNativeArray();
        MotionEvent i2 = i();
        float x = i2.getX() - this.e;
        float y = i2.getY() - this.f;
        for (int i3 = 0; i3 < i2.getPointerCount(); i3++) {
            ar writableNativeMap = new WritableNativeMap();
            writableNativeMap.putDouble("pageX", (double) p.b(i2.getX(i3)));
            writableNativeMap.putDouble("pageY", (double) p.b(i2.getY(i3)));
            float y2 = i2.getY(i3) - y;
            writableNativeMap.putDouble("locationX", (double) p.b(i2.getX(i3) - x));
            writableNativeMap.putDouble("locationY", (double) p.b(y2));
            writableNativeMap.putInt("target", d);
            writableNativeMap.putDouble("timestamp", (double) e());
            writableNativeMap.putDouble("identifier", (double) i2.getPointerId(i3));
            writableNativeArray.pushMap(writableNativeMap);
        }
        MotionEvent i4 = i();
        aq writableNativeArray2 = new WritableNativeArray();
        if (hVar == h.MOVE || hVar == h.CANCEL) {
            while (i < i4.getPointerCount()) {
                writableNativeArray2.pushInt(i);
                i++;
            }
        } else if (hVar == h.START || hVar == h.END) {
            writableNativeArray2.pushInt(i4.getActionIndex());
        } else {
            throw new RuntimeException("Unknown touch type: " + hVar);
        }
        rctEventEmitter.receiveTouches(hVar.a(), writableNativeArray, writableNativeArray2);
    }

    private MotionEvent i() {
        a.a(this.b);
        return this.b;
    }
}
