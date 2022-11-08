package com.skpcamera;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.skpcamera.a.a;
import com.skpcamera.antediluvian.d;
import com.skypecam.camera2.c;
import com.skypecam.camera2.j;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class b {
    private static final b a = new b();
    private AtomicBoolean b = new AtomicBoolean(true);
    private AtomicInteger c = new AtomicInteger(1);
    private c d;
    private a e;
    private ag f;

    public static b a() {
        return a;
    }

    public final void a(int desired) {
        if (this.b.get()) {
            FLog.i("CameraVersionSwitcher", "setVersion " + desired);
            this.c.set(desired);
        } else if (this.c.get() != desired) {
            FLog.w("CameraVersionSwitcher", "setVersion " + desired + " not allowed");
        }
    }

    public final void a(ag context) {
        synchronized (this) {
            if (this.b.get()) {
                this.f = context;
            } else {
                this.e.a(context);
            }
        }
    }

    public final c b() {
        d();
        return this.d;
    }

    public final a c() {
        d();
        return this.e;
    }

    private synchronized void d() {
        synchronized (this) {
            if (this.b.getAndSet(false)) {
                String moduleName;
                j deviceHwLevel = c.a(this.f);
                boolean fallbackFromCamera2 = false;
                switch (this.c.get()) {
                    case 1:
                        break;
                    case 2:
                        if (!deviceHwLevel.a(j.c)) {
                            FLog.w("CameraVersionSwitcher", "Camera 2 not supported on this device!");
                            fallbackFromCamera2 = true;
                            break;
                        }
                        moduleName = "Camera2";
                        FLog.i("CameraVersionSwitcher", "selectVersion 2: Camera2");
                        this.d = new com.skpcamera.a.b();
                        this.e = new a();
                        break;
                    default:
                        moduleName = "AnteDiluvian";
                        FLog.i("CameraVersionSwitcher", "selectVersion 0: Default");
                        this.d = new d();
                        this.e = new com.skpcamera.antediluvian.c();
                        break;
                }
                moduleName = "FSM";
                FLog.i("CameraVersionSwitcher", "selectVersion 1: FSM");
                this.d = new com.skpcamera.fsm.d();
                this.e = new com.skpcamera.fsm.c();
                this.e.a(this.f);
                ar writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString("moduleName", moduleName);
                writableNativeMap.putString("hardwareLevel", deviceHwLevel.toString());
                writableNativeMap.putBoolean("fallback", fallbackFromCamera2);
                ((RCTNativeAppEventEmitter) this.f.a(RCTNativeAppEventEmitter.class)).emit("CameraInitEvent", writableNativeMap);
                new StringBuilder("MODULE INIT ").append(moduleName).append("/").append(deviceHwLevel.toString()).append("/").append(fallbackFromCamera2);
                this.f = null;
            }
        }
    }
}
