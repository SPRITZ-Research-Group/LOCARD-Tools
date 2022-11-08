package com.skypecam.camera2;

import android.graphics.Color;
import android.graphics.PointF;
import kotlin.Metadata;
import kotlin.jvm.b.c;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/skypecam/camera2/FocusTarget;", "", "screenTouchPoint", "Landroid/graphics/PointF;", "(Landroid/graphics/PointF;)V", "getScreenTouchPoint", "()Landroid/graphics/PointF;", "setScreenTouchPoint", "state", "Lcom/skypecam/camera2/FocusTarget$Companion$FocusRectState;", "getState", "()Lcom/skypecam/camera2/FocusTarget$Companion$FocusRectState;", "setState", "(Lcom/skypecam/camera2/FocusTarget$Companion$FocusRectState;)V", "Companion", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
public final class i {
    public static final a a = new a();
    private static int d = Color.argb(255, 249, 224, 75);
    @NotNull
    private a b = a.a;
    @NotNull
    private PointF c;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001:\u0001\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fXT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fXT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/skypecam/camera2/FocusTarget$Companion;", "", "()V", "FOCUS_TARGET_AREA_SIZE", "", "FOCUS_TARGET_COLOR", "getFOCUS_TARGET_COLOR", "()I", "setFOCUS_TARGET_COLOR", "(I)V", "FOCUS_TARGET_LINES_LENGHT", "FOCUS_TARGET_ROUND_CORNER_RADIUS", "", "FOCUS_TARGET_STROKE_WIDTH", "FocusRectState", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
    public static final class a {

        @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/skypecam/camera2/FocusTarget$Companion$FocusRectState;", "", "(Ljava/lang/String;I)V", "IN_PROGRESS", "COMPLETED", "FAILED", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
        public enum a {
        }

        private a() {
        }

        public /* synthetic */ a(byte b) {
            this();
        }
    }

    public i(@NotNull PointF screenTouchPoint) {
        c.b(screenTouchPoint, "screenTouchPoint");
        this.c = screenTouchPoint;
    }

    @NotNull
    public final PointF b() {
        return this.c;
    }

    @NotNull
    public final a a() {
        return this.b;
    }

    public final void a(@NotNull a <set-?>) {
        c.b(<set-?>, "<set-?>");
        this.b = <set-?>;
    }
}
