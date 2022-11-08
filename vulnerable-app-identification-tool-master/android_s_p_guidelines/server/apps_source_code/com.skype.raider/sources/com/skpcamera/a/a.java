package com.skpcamera.a;

import android.content.Context;
import android.net.Uri;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.skpcamera.SkypeCameraViewManager;
import com.skype.camera.imagefilter.ImageFilterManager;
import com.skypecam.camera2.CameraView;
import com.skypecam.camera2.b;
import com.skypecam.camera2.f;
import com.skypecam.camera2.k;
import com.skypecam.camera2.o;
import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a implements com.skpcamera.a, f {
    private ag a;

    public final void a(ag context) {
        this.a = context;
        b bVar = b.a;
        b.a((Context) context);
        bVar = b.a;
        b.a((f) this);
    }

    public final void a() {
        b bVar = b.a;
        b.b();
    }

    public final void b() {
        b bVar = b.a;
        b.c();
    }

    public final void c() {
        b bVar = b.a;
        b.d();
    }

    public final void a(final ae promise) {
        b bVar = b.a;
        b.a(new o(this) {
            final /* synthetic */ a b;

            public final void a(String message) {
                promise.a(new Throwable("Video recording failed. " + message));
            }

            public final void a(boolean recording, CameraView view) {
                ar event = new WritableNativeMap();
                event.putInt("isRecording", recording ? 1 : 0);
                SkypeCameraViewManager.sendEvent(SkypeCameraViewManager.RECORDING_CHANGE_EVENT_NAME, view, event);
            }

            public final void a(@Nullable File file, @Nullable File thumbnailFile, int width, int height) {
                Object map = new WritableNativeMap();
                map.putString(ReactVideoViewManager.PROP_SRC_URI, Uri.fromFile(file).toString());
                map.putString("thumbnailUri", Uri.fromFile(thumbnailFile).toString());
                map.putInt("width", width);
                map.putInt("height", height);
                map.putInt("fileSize", (int) file.length());
                promise.a(map);
            }
        });
    }

    public final void d() {
        b bVar = b.a;
        b.e();
    }

    public final void e() {
        b bVar = b.a;
        b.f();
    }

    public final void a(boolean freeze, final ae promise) {
        b bVar = b.a;
        b.a(new k(this) {
            final /* synthetic */ a b;

            public final void a(String message) {
                promise.a(new Throwable("Image capture failed. " + message));
            }

            public final void a(@NotNull File file, int width, int height) {
                Object map = new WritableNativeMap();
                map.putString(ReactVideoViewManager.PROP_SRC_URI, Uri.fromFile(file).toString());
                map.putInt("fileSize", (int) file.length());
                map.putInt("width", width);
                map.putInt("height", height);
                promise.a(map);
            }
        });
    }

    public final void a(int x, int y) {
        b bVar = b.a;
        b.a(x, y);
    }

    public final void a(boolean complete, int d, int d0) {
        b bVar = b.a;
        b.a(complete, d, d0);
    }

    public final void f() {
        b bVar = b.a;
        b.g();
    }

    public final void b(ae promise) {
        b bVar = b.a;
        promise.a(Integer.valueOf(b.h()));
    }

    public final void c(ae promise) {
        promise.a(Boolean.valueOf(true));
    }

    public final void g() {
        b bVar = b.a;
        b.i();
    }

    public final void h() {
        b bVar = b.a;
        b.a();
    }

    private void a(@NotNull String eventName, @NotNull ar data) {
        data.putString(ImageFilterManager.PROP_SOURCE, "Camera2");
        new StringBuilder("EventReporter: reporting event ").append(eventName).append(" -> ").append(data.toString());
        ((RCTNativeAppEventEmitter) this.a.a(RCTNativeAppEventEmitter.class)).emit(eventName, data);
    }

    public final void a(@NotNull String eventName, @NotNull Map<String, String> payload) {
        ar data = new WritableNativeMap();
        for (Entry<String, String> entry : payload.entrySet()) {
            data.putString((String) entry.getKey(), (String) entry.getValue());
        }
        a(eventName, data);
    }

    public final void a(@NotNull String payload) {
        ar data = new WritableNativeMap();
        data.putString("message", payload);
        a("CameraWarningEvent", data);
    }
}
