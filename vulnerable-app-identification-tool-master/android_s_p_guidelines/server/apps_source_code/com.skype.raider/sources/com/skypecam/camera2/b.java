package com.skypecam.camera2;

import android.content.Context;
import com.adjust.sdk.Constants;
import com.skypecam.camera2.h.a;
import kotlin.Metadata;
import kotlin.jvm.b.c;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0006J\u0006\u0010\u0012\u001a\u00020\u0006J\u0016\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\rJ\u000e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0006J\u0006\u0010\u001a\u001a\u00020\u0006J\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\rJ\u000e\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"J\u0010\u0010#\u001a\u00020\u00062\b\u0010$\u001a\u0004\u0018\u00010%J\u000e\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u0010J\u000e\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0010J\u000e\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u0010J\u000e\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0010J\u000e\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\"J\u000e\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u00020\"J\u000e\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\"J\u000e\u00104\u001a\u00020\u00062\u0006\u00105\u001a\u00020\rJ\u000e\u00106\u001a\u00020\u00062\u0006\u00107\u001a\u00020\"J\u000e\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\"J\u000e\u0010:\u001a\u00020\u00062\u0006\u0010;\u001a\u00020<J\u000e\u0010=\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020>J\u0006\u0010?\u001a\u00020\u0006J\u0006\u0010@\u001a\u00020\u0006J\u0006\u0010A\u001a\u00020\rJ\u001e\u0010B\u001a\u00020\u00062\u0006\u0010C\u001a\u00020\u00102\u0006\u0010D\u001a\u00020\r2\u0006\u0010E\u001a\u00020\rR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/skypecam/camera2/Camera2Controller;", "", "()V", "camera2", "Lcom/skypecam/camera2/Camera2;", "cancel", "", "cancelRecording", "capture", "callback", "Lcom/skypecam/camera2/ImageCapturedCallback;", "chooseCamera", "cameraType", "", "enableVideoBFrame", "state", "", "enterPreview", "finishRecording", "focus", "x", "y", "initialize", "context", "Landroid/content/Context;", "pauseSession", "release", "setEventReporter", "eventReporter", "Lcom/skypecam/camera2/EventReporter;", "setFlashMode", "mode", "setMaxRecordingMs", "ms", "", "setQRCodeListener", "listener", "Lcom/skypecam/camera2/QRCodeListener;", "setReleaseOnBackground", "shouldReleaseOnBackground", "setSessionActive", "sessionActive", "setUseSensorOrientation", "useSensorOrientation", "setUseShutterAnimation", "useShutterAnimation", "setVideoBitrate", "bitrate", "setVideoHeight", "height", "setVideoProfile", "profile", "setVideoThumbnailCompressionRate", "rate", "setVideoThumbnailMaxDimension", "dimension", "setVideoWidth", "width", "setView", "cameraView", "Lcom/skypecam/camera2/CameraView;", "startRecording", "Lcom/skypecam/camera2/VideoCapturedCallback;", "startSession", "stopSession", "supportedCameras", "zoom", "gestureCompleted", "distance", "initialDistance", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
public final class b {
    public static final b a = new b();
    private static a b;

    private b() {
    }

    public static void a(@NotNull Context context) {
        c.b(context, "context");
        l.a("Camera2Controller initialize");
        a it = b;
        if (it != null) {
            l.a("Destroying existing camera2 instance");
            it.g();
        }
        b = new a(context);
    }

    public static void a() {
        l.a("Camera2Controller released");
        a aVar = b;
        if (aVar != null) {
            aVar.g();
        }
        b = null;
    }

    public static void a(@NotNull CameraView cameraView) {
        c.b(cameraView, "cameraView");
        l.a("setCameraView " + cameraView);
        a aVar = b;
        if (aVar != null) {
            aVar.a(cameraView);
        }
    }

    public static void b() {
        l.a("NOOP: stopSession");
    }

    public static void c() {
        l.a("NOOP: startSession");
    }

    public static void d() {
        l.a("NOOP: pauseSession");
    }

    public static void a(@NotNull o callback) {
        c.b(callback, "callback");
        l.a("startRecording");
        a aVar = b;
        if (aVar != null) {
            aVar.a(callback);
        }
    }

    public static void e() {
        l.a("finishRecording");
        a aVar = b;
        if (aVar != null) {
            aVar.e();
        }
    }

    public static void f() {
        l.a("cancelRecording");
        a aVar = b;
        if (aVar != null) {
            aVar.f();
        }
    }

    public static void a(@NotNull k callback) {
        c.b(callback, "callback");
        l.a("capture " + callback);
        a aVar = b;
        if (aVar != null) {
            aVar.a(callback);
        }
    }

    public static void a(int x, int y) {
        l.a("focus on " + x + 'x' + y);
        a aVar = b;
        if (aVar != null) {
            aVar.a(x, y);
        }
    }

    public static void a(boolean gestureCompleted, int distance, int initialDistance) {
        if (gestureCompleted) {
            l.a("Zoomed start: " + initialDistance + " end: " + distance);
        }
        a aVar = b;
        if (aVar != null) {
            aVar.a(gestureCompleted, distance, initialDistance);
        }
    }

    public static void g() {
        l.a("NOOP: cancel");
    }

    public static int h() {
        a aVar = b;
        return aVar != null ? aVar.d() : 0;
    }

    public static void a(@NotNull f eventReporter) {
        c.b(eventReporter, "eventReporter");
        l.a("setEventReporter");
        g.a(eventReporter);
    }

    public static void i() {
        l.a("NOOP: enterPreview");
    }

    public static void a(int cameraType) {
        l.a("choose camera type " + cameraType);
        a aVar = b;
        if (aVar != null) {
            aVar.f(cameraType);
        }
    }

    public static void b(int mode) {
        l.a("setFlashMode " + mode);
        a aVar = b;
        if (aVar != null) {
            a aVar2 = h.d;
            aVar.a(a.a(mode));
        }
    }

    public static void a(boolean sessionActive) {
        l.a("NOOP: setSessionActive " + sessionActive);
    }

    public static void a(float ms) {
        l.a("setMaxRecordingMs " + ms);
        a aVar = b;
        if (aVar != null) {
            aVar.a(ms);
        }
    }

    public static void b(float dimension) {
        l.a("setVideoThumbnailMaxDimension " + dimension);
        a aVar = b;
        if (aVar != null) {
            aVar.e((int) dimension);
        }
    }

    public static void c(int rate) {
        l.a("setVideoThumbnailCompressionRate " + rate);
        a aVar = b;
        if (aVar != null) {
            aVar.d(rate);
        }
    }

    public static void c(float width) {
        l.a("setVideoWidth " + width);
        a aVar = b;
        if (aVar != null) {
            aVar.a((int) width);
        }
    }

    public static void d(float height) {
        l.a("setVideoHeight " + height);
        a aVar = b;
        if (aVar != null) {
            aVar.b((int) height);
        }
    }

    public static void e(float bitrate) {
        l.a("setVideoBitrate " + bitrate);
        a aVar = b;
        if (aVar != null) {
            aVar.c(((int) bitrate) * Constants.ONE_SECOND);
        }
    }

    public static void f(float profile) {
        l.a("NOOP: setVideoProfile " + profile);
    }

    public static void b(boolean state) {
        l.a("NOOP: enableVideoBFrame " + state);
    }

    public static void a(@Nullable m listener) {
        l.a("setQRCodeListener " + listener);
        a aVar = b;
        if (aVar != null) {
            aVar.a(listener);
        }
    }

    public static void c(boolean useSensorOrientation) {
        l.a("setUseSensorOrientation " + useSensorOrientation);
        if (b != null) {
            a.a(useSensorOrientation);
        }
    }
}
