package com.skypecam.camera2;

import android.util.Size;
import kotlin.Metadata;
import kotlin.d;
import kotlin.jvm.b.c;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0000\u001a\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0001H\u0000\u001a\b\u0010\u0011\u001a\u00020\rH\u0000\u001a\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u001a\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0002\u001a\b\u0010\u0017\u001a\u00020\rH\u0000\u001a\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u0015H\u0000\u001a\u0010\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u0015H\u0000\u001a\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"EVENT_NAME_CAMERA_EVENT", "", "EVENT_NAME_FIRST_FRAME", "PARAM_NAME_CAPTURE_RESOLUTION", "PARAM_NAME_FAILURE_REASON", "PARAM_NAME_MESSAGE", "PARAM_NAME_SUCCESS", "PARAM_VALUE_CAMERA_STARTED", "PARAM_VALUE_STILL_CAPTURED", "PARAM_VALUE_VIDEO_CAPTURED", "globalEventReporter", "Lcom/skypecam/camera2/EventReporter;", "registerGlobalEventReporter", "", "eventReporter", "reportCameraStartFailure", "reason", "reportCameraStartSuccess", "reportCaptureSuccess", "eventMessage", "captureResolution", "Landroid/util/Size;", "reportFailure", "reportFirstFrame", "reportGenericFailure", "reportStillCaptureFailure", "reportStillCaptureSuccess", "resolution", "reportVideoCaptureFailure", "reportVideoCaptureSuccess", "reportWarning", "payload", "react-native-camera2lib_release"}, k = 2, mv = {1, 1, 10})
public final class g {
    private static f a;

    public static final void a(@NotNull f eventReporter) {
        c.b(eventReporter, "eventReporter");
        a = eventReporter;
    }

    public static final void a() {
        f fVar = a;
        if (fVar != null) {
            fVar.a("CameraEvent", t.a(d.a("message", "CameraStarted"), d.a("success", "true")));
        }
    }

    public static final void a(@NotNull String reason) {
        c.b(reason, "reason");
        a("CameraStarted", reason);
    }

    public static final void a(@NotNull Size resolution) {
        c.b(resolution, "resolution");
        a("StillCaptured", resolution);
    }

    public static final void b(@NotNull String reason) {
        c.b(reason, "reason");
        a("StillCaptured", reason);
    }

    public static final void b(@NotNull Size resolution) {
        c.b(resolution, "resolution");
        a("VideoCaptured", resolution);
    }

    public static final void c(@NotNull String reason) {
        c.b(reason, "reason");
        a("VideoCaptured", reason);
    }

    public static final void b() {
        f fVar = a;
        if (fVar != null) {
            fVar.a("FirstFrameEvent", t.a());
        }
    }

    public static final void d(@NotNull String payload) {
        c.b(payload, "payload");
        f fVar = a;
        if (fVar != null) {
            fVar.a(payload);
        }
    }

    private static final void a(String eventMessage, Size captureResolution) {
        f fVar = a;
        if (fVar != null) {
            fVar.a("CameraEvent", t.a(d.a("message", eventMessage), d.a("success", "true"), d.a("captureResolution", captureResolution.getWidth() + 'x' + captureResolution.getHeight())));
        }
    }

    private static final void a(String eventMessage, String reason) {
        f fVar = a;
        if (fVar != null) {
            fVar.a("CameraEvent", t.a(d.a("message", eventMessage), d.a("success", "false"), d.a("failureReason", reason)));
        }
    }
}
