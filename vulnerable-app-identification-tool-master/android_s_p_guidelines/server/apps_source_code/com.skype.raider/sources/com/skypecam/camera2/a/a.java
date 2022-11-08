package com.skypecam.camera2.a;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.MediaRecorder;
import android.util.Range;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.WindowManager;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import com.skype.Defines;
import com.skypecam.camera2.g;
import com.skypecam.camera2.h;
import com.skypecam.camera2.l;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 h2\u00020\u0001:\u0002hiB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010a\u001a\u00020=J&\u0010b\u001a\u00020=2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020,2\u0006\u0010f\u001a\u00020\r2\u0006\u0010g\u001a\u00020\rR&\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R&\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r8F@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R$\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0019@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\"\u0010%\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R&\u00101\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r8F@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u000f\"\u0004\b3\u0010\u0011R\u000e\u00104\u001a\u00020 X\u000e¢\u0006\u0002\n\u0000R\u001a\u00105\u001a\u000206X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R \u0010;\u001a\b\u0012\u0004\u0012\u00020=0<X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR \u0010B\u001a\b\u0012\u0004\u0012\u00020=0<X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010?\"\u0004\bD\u0010AR\u000e\u0010E\u001a\u00020FX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0004¢\u0006\u0002\n\u0000R$\u0010I\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\t\"\u0004\bK\u0010\u000bR$\u0010L\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\t\"\u0004\bN\u0010\u000bR\u001c\u0010O\u001a\u0004\u0018\u00010PX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u000e\u0010U\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010V\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010\u000f\"\u0004\bX\u0010\u0011R\u001a\u0010Y\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010\"\"\u0004\b[\u0010$R\u001a\u0010\\\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010\t\"\u0004\b^\u0010\u000bR\u000e\u0010_\u001a\u00020`X\u0004¢\u0006\u0002\n\u0000¨\u0006j"}, d2 = {"Lcom/skypecam/camera2/modules/CameraInfo;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "<set-?>", "Landroid/util/Size;", "captureSize", "getCaptureSize", "()Landroid/util/Size;", "setCaptureSize", "(Landroid/util/Size;)V", "desideredVideoHeight", "", "getDesideredVideoHeight", "()I", "setDesideredVideoHeight", "(I)V", "desideredVideoWidth", "getDesideredVideoWidth", "setDesideredVideoWidth", "displayRotation", "getDisplayRotation", "setDisplayRotation", "value", "Lcom/skypecam/camera2/FlashMode;", "flashMode", "getFlashMode", "()Lcom/skypecam/camera2/FlashMode;", "setFlashMode", "(Lcom/skypecam/camera2/FlashMode;)V", "flashSupported", "", "getFlashSupported", "()Z", "setFlashSupported", "(Z)V", "fpsRange", "Landroid/util/Range;", "getFpsRange", "()Landroid/util/Range;", "setFpsRange", "(Landroid/util/Range;)V", "id", "", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "imageRotation", "getImageRotation", "setImageRotation", "isFrontFacing", "maximumZoomLevel", "", "getMaximumZoomLevel", "()F", "setMaximumZoomLevel", "(F)V", "onCaptureSizeChanged", "Lkotlin/Function0;", "", "getOnCaptureSizeChanged", "()Lkotlin/jvm/functions/Function0;", "setOnCaptureSizeChanged", "(Lkotlin/jvm/functions/Function0;)V", "onPreviewSizeChanged", "getOnPreviewSizeChanged", "setOnPreviewSizeChanged", "orientationTracker", "Lcom/skype4life/shared/orientationtracker/OrientationTracker;", "orientations", "Landroid/util/SparseIntArray;", "previewSize", "getPreviewSize", "setPreviewSize", "rawCaptureSize", "getRawCaptureSize", "setRawCaptureSize", "sensorArraySize", "Landroid/graphics/Rect;", "getSensorArraySize", "()Landroid/graphics/Rect;", "setSensorArraySize", "(Landroid/graphics/Rect;)V", "sensorOrientation", "sensorRotation", "getSensorRotation", "setSensorRotation", "supportsMeteringAreas", "getSupportsMeteringAreas", "setSupportsMeteringAreas", "videoCaptureSize", "getVideoCaptureSize", "setVideoCaptureSize", "windowManager", "Landroid/view/WindowManager;", "release", "setupCamera", "manager", "Landroid/hardware/camera2/CameraManager;", "cameraId", "surfaceWidth", "surfaceHeight", "Companion", "CompareSizesByArea", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
public final class a {
    public static final b a = new b();
    private static boolean w = true;
    private final com.skype4life.c.a.a b;
    private final WindowManager c;
    @Nullable
    private String d;
    private int e;
    private boolean f;
    private int g;
    private final SparseIntArray h;
    private boolean i;
    @NotNull
    private h j;
    @NotNull
    private kotlin.jvm.a.a<kotlin.f> k;
    @NotNull
    private Size l;
    @NotNull
    private kotlin.jvm.a.a<kotlin.f> m;
    @NotNull
    private Size n;
    @NotNull
    private Size o;
    private int p;
    private int q;
    @NotNull
    private Size r;
    private boolean s;
    @Nullable
    private Rect t;
    private float u;
    @Nullable
    private Range<Integer> v;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/skype4life/shared/orientationtracker/OrientationTracker$DeviceOrientation;", "kotlin.jvm.PlatformType", "deviceOrientationInSpace", "Lcom/skype4life/shared/orientationtracker/OrientationTracker$DeviceOrientationInSpace;", "onOrientationChanged", "com/skypecam/camera2/modules/CameraInfo$1$1"}, k = 3, mv = {1, 1, 10})
    static final class a implements com.skype4life.c.a.a.a {
        final /* synthetic */ a a;

        a(a aVar) {
            this.a = aVar;
        }

        public final void a(com.skype4life.c.a.a.b $noName_0, com.skype4life.c.a.a.c deviceOrientationInSpace) {
            int i = 0;
            a aVar = this.a;
            if (deviceOrientationInSpace != null) {
                switch (c.a[deviceOrientationInSpace.ordinal()]) {
                    case 2:
                        i = 3;
                        break;
                    case 3:
                        i = 2;
                        break;
                    case 4:
                        i = 1;
                        break;
                }
            }
            aVar.a(i);
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJA\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000e¢\u0006\u0002\u0010\u0016J)\u0010\u0017\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u000e¢\u0006\u0002\u0010\u001aJJ\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u001c2\u0006\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\u000bJ\u0018\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020)R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006*"}, d2 = {"Lcom/skypecam/camera2/modules/CameraInfo$Companion;", "", "()V", "USE_SENSOR_ROTATION", "", "getUSE_SENSOR_ROTATION", "()Z", "setUSE_SENSOR_ROTATION", "(Z)V", "areDimensionsSwapped", "sensorOrientation", "", "displayRotation", "chooseOptimalPreviewSize", "Landroid/util/Size;", "choices", "", "textureViewWidth", "textureViewHeight", "maxWidth", "maxHeight", "aspectRatio", "([Landroid/util/Size;IIIILandroid/util/Size;)Landroid/util/Size;", "chooseOptimalVideoSize", "previewTextureSize", "desiredVideoSize", "([Landroid/util/Size;Landroid/util/Size;Landroid/util/Size;)Landroid/util/Size;", "getNormalizedSensorPointFromScreenCoordinates", "Lkotlin/Pair;", "screenX", "screenY", "screenWidth", "screenHeight", "sensorWidth", "sensorHeight", "imageRotation", "setupFlashModeRequest", "", "builder", "Landroid/hardware/camera2/CaptureRequest$Builder;", "flashMode", "Lcom/skypecam/camera2/FlashMode;", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
    public static final class b {

        @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}, k = 3, mv = {1, 1, 10})
        public static final class a<T> implements Comparator<T> {
            final /* synthetic */ float a;

            public a(float f) {
                this.a = f;
            }

            public final int compare(T a, T b) {
                Size it = (Size) a;
                Size size = (Size) b;
                return b.a(Float.valueOf(Math.abs(this.a - (((float) it.getWidth()) / ((float) it.getHeight())))), Float.valueOf(Math.abs(this.a - (((float) size.getWidth()) / ((float) size.getHeight())))));
            }
        }

        private b() {
        }

        public /* synthetic */ b(byte b) {
            this();
        }

        @NotNull
        public static Size a(@NotNull Size[] choices, int textureViewWidth, int textureViewHeight, int maxWidth, int maxHeight, @NotNull Size aspectRatio) {
            kotlin.jvm.b.c.b(choices, "choices");
            kotlin.jvm.b.c.b(aspectRatio, "aspectRatio");
            ArrayList bigEnough = new ArrayList();
            ArrayList notBigEnough = new ArrayList();
            float ratio = ((float) aspectRatio.getHeight()) / ((float) aspectRatio.getWidth());
            for (Size option : choices) {
                if (option.getWidth() <= maxWidth && option.getHeight() <= maxHeight && option.getHeight() == ((int) (((float) option.getWidth()) * ratio))) {
                    if (option.getWidth() < textureViewWidth || option.getHeight() < textureViewHeight) {
                        notBigEnough.add(option);
                    } else {
                        bigEnough.add(option);
                    }
                }
            }
            Object min;
            if (bigEnough.size() > 0) {
                min = Collections.min(bigEnough, new c());
                kotlin.jvm.b.c.a(min, "Collections.min(bigEnough, CompareSizesByArea())");
                return (Size) min;
            } else if (notBigEnough.size() > 0) {
                min = Collections.max(notBigEnough, new c());
                kotlin.jvm.b.c.a(min, "Collections.max(notBigEn…gh, CompareSizesByArea())");
                return (Size) min;
            } else {
                kotlin.jvm.b.c.b("Couldn't find any optimal preview size", "message");
                g.d("No optimal preview size found");
                return choices[0];
            }
        }

        @NotNull
        public static Size a(@NotNull Size[] choices, @NotNull Size previewTextureSize, @NotNull Size desiredVideoSize) {
            Size it;
            Object obj;
            Size output;
            kotlin.jvm.b.c.b(choices, "choices");
            kotlin.jvm.b.c.b(previewTextureSize, "previewTextureSize");
            kotlin.jvm.b.c.b(desiredVideoSize, "desiredVideoSize");
            int maxVideoHeight = Math.max(desiredVideoSize.getWidth(), desiredVideoSize.getHeight());
            if (maxVideoHeight > 1080) {
                maxVideoHeight = 1080;
                kotlin.jvm.b.c.b("Max video resolution supported is 1080", "message");
            }
            int screenWidth = Math.max(previewTextureSize.getWidth(), previewTextureSize.getHeight());
            int screenHeight = Math.min(previewTextureSize.getWidth(), previewTextureSize.getHeight());
            l.a("Looking for optimal video resolution for screen size " + screenWidth + 'x' + screenHeight + " (" + (((float) screenWidth) / ((float) screenHeight)) + ')');
            float previewRatio = ((float) screenWidth) / ((float) screenHeight);
            Collection destination$iv$iv = new ArrayList();
            for (Size element$iv$iv : choices) {
                if ((element$iv$iv.getHeight() <= maxVideoHeight ? 1 : null) != null) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            for (Size element$iv : (List) destination$iv$iv) {
                it = element$iv;
                if (it.getWidth() == ((int) (((float) it.getHeight()) * previewRatio))) {
                    obj = 1;
                    continue;
                } else {
                    obj = null;
                    continue;
                }
                if (obj != null) {
                    output = element$iv;
                    break;
                }
            }
            output = null;
            output = output;
            if (output == null) {
                destination$iv$iv = new ArrayList();
                for (Size element$iv$iv2 : choices) {
                    if (element$iv$iv2.getHeight() <= 1080) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        destination$iv$iv.add(element$iv$iv2);
                    }
                }
                for (Size element$iv2 : (List) destination$iv$iv) {
                    it = element$iv2;
                    if (it.getWidth() == ((int) (((float) it.getHeight()) * previewRatio))) {
                        obj = 1;
                        continue;
                    } else {
                        obj = null;
                        continue;
                    }
                    if (obj != null) {
                        output = element$iv2;
                        break;
                    }
                }
                output = null;
                output = output;
            }
            if (output == null) {
                l.a("Found no video recording size that matches exactly the surface size");
                destination$iv$iv = new ArrayList();
                for (Size element$iv$iv22 : choices) {
                    if (element$iv$iv22.getHeight() <= maxVideoHeight) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        destination$iv$iv.add(element$iv$iv22);
                    }
                }
                output = (Size) k.a((Iterable) (List) destination$iv$iv, (Comparator) new a(previewRatio)).get(0);
            }
            l.a("Optimal video resolution " + output);
            return output;
        }

        public static void a(@Nullable Builder builder, @NotNull h flashMode) {
            kotlin.jvm.b.c.b(flashMode, "flashMode");
            switch (b.a[flashMode.ordinal()]) {
                case 1:
                    if (builder != null) {
                        builder.set(CaptureRequest.FLASH_MODE, Integer.valueOf(0));
                    }
                    if (builder != null) {
                        builder.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(1));
                        return;
                    }
                    return;
                case 2:
                    if (builder != null) {
                        builder.set(CaptureRequest.FLASH_MODE, Integer.valueOf(2));
                    }
                    if (builder != null) {
                        builder.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(1));
                        return;
                    }
                    return;
                case 3:
                    if (builder != null) {
                        builder.set(CaptureRequest.FLASH_MODE, Integer.valueOf(0));
                    }
                    if (builder != null) {
                        builder.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(2));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"Lcom/skypecam/camera2/modules/CameraInfo$CompareSizesByArea;", "Ljava/util/Comparator;", "Landroid/util/Size;", "()V", "compare", "", "lhs", "rhs", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
    public static final class c implements Comparator<Size> {
        public final /* synthetic */ int compare(Object obj, Object obj2) {
            Size size = (Size) obj;
            Size size2 = (Size) obj2;
            kotlin.jvm.b.c.b(size, "lhs");
            kotlin.jvm.b.c.b(size2, "rhs");
            return kotlin.d.a.a((((long) size.getWidth()) * ((long) size.getHeight())) - (((long) size2.getWidth()) * ((long) size2.getHeight())));
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 10})
    static final class d extends kotlin.jvm.b.d implements kotlin.jvm.a.a<kotlin.f> {
        public static final d a = new d();

        d() {
            super(0);
        }

        public final /* bridge */ /* synthetic */ Object a() {
            return kotlin.f.a;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 10})
    static final class e extends kotlin.jvm.b.d implements kotlin.jvm.a.a<kotlin.f> {
        public static final e a = new e();

        e() {
            super(0);
        }

        public final /* bridge */ /* synthetic */ Object a() {
            return kotlin.f.a;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareByDescending$1"}, k = 3, mv = {1, 1, 10})
    public static final class f<T> implements Comparator<T> {
        public final int compare(T a, T b) {
            Object it = (Range) b;
            kotlin.jvm.b.c.a(it, "it");
            int intValue = ((Number) it.getUpper()).intValue();
            Object lower = it.getLower();
            kotlin.jvm.b.c.a(lower, "it.lower");
            Comparable valueOf = Integer.valueOf(intValue - ((Number) lower).intValue());
            Object obj = (Range) a;
            kotlin.jvm.b.c.a(obj, "it");
            int intValue2 = ((Number) obj.getUpper()).intValue();
            Object lower2 = obj.getLower();
            kotlin.jvm.b.c.a(lower2, "it.lower");
            return b.a(valueOf, Integer.valueOf(intValue2 - ((Number) lower2).intValue()));
        }
    }

    public a(@NotNull Context context) {
        kotlin.jvm.b.c.b(context, "context");
        com.skypecam.camera2.e.a(this);
        Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new kotlin.e("null cannot be cast to non-null type android.view.WindowManager");
        }
        this.c = (WindowManager) systemService;
        com.skype4life.c.a.a aVar = new com.skype4life.c.a.a(context);
        aVar.a(new a(this));
        aVar.enable();
        this.b = aVar;
        SparseIntArray $receiver = new SparseIntArray();
        $receiver.append(0, 90);
        $receiver.append(1, 0);
        $receiver.append(2, 270);
        $receiver.append(3, 180);
        this.h = $receiver;
        this.j = h.a;
        this.k = e.a;
        this.l = new Size(0, 0);
        this.m = d.a;
        this.n = new Size(0, 0);
        this.o = new Size(0, 0);
        this.p = 640;
        this.q = 360;
        this.r = new Size(this.p, this.q);
        this.u = 1.0f;
    }

    @Nullable
    public final String a() {
        return this.d;
    }

    public final int b() {
        if (w) {
            return this.e;
        }
        Object defaultDisplay = this.c.getDefaultDisplay();
        kotlin.jvm.b.c.a(defaultDisplay, "windowManager.defaultDisplay");
        return defaultDisplay.getRotation();
    }

    public final void a(int <set-?>) {
        this.e = <set-?>;
    }

    public final int c() {
        return this.e;
    }

    public final int d() {
        int adjustedDisplayRotation;
        if (this.f) {
            switch (b()) {
                case 1:
                    adjustedDisplayRotation = 3;
                    break;
                case 3:
                    adjustedDisplayRotation = 1;
                    break;
                default:
                    adjustedDisplayRotation = b();
                    break;
            }
        }
        adjustedDisplayRotation = b();
        return ((this.h.get(adjustedDisplayRotation) + this.g) + 270) % 360;
    }

    @NotNull
    public final h e() {
        return this.j;
    }

    public final void a(@NotNull h value) {
        kotlin.jvm.b.c.b(value, PropertiesEntry.COLUMN_NAME_VALUE);
        if (!this.i) {
            value = h.a;
        }
        this.j = value;
        l.a("New flash mode: " + this.j.name());
    }

    @NotNull
    public final Size f() {
        return this.l;
    }

    public final void a(@NotNull kotlin.jvm.a.a<kotlin.f> <set-?>) {
        kotlin.jvm.b.c.b(<set-?>, "<set-?>");
        this.m = <set-?>;
    }

    @NotNull
    public final Size g() {
        return this.n;
    }

    @NotNull
    public final Size h() {
        switch (d()) {
            case 0:
            case 180:
                return this.n;
            default:
                return new Size(this.n.getHeight(), this.n.getWidth());
        }
    }

    public final void b(int <set-?>) {
        this.p = <set-?>;
    }

    public final void c(int <set-?>) {
        this.q = <set-?>;
    }

    @NotNull
    public final Size i() {
        return this.r;
    }

    public final boolean j() {
        return this.s;
    }

    @Nullable
    public final Rect k() {
        return this.t;
    }

    public final float l() {
        return this.u;
    }

    @Nullable
    public final Range<Integer> m() {
        return this.v;
    }

    public final void a(@NotNull CameraManager manager, @NotNull String cameraId, int surfaceWidth, int surfaceHeight) {
        kotlin.jvm.b.c.b(manager, "manager");
        kotlin.jvm.b.c.b(cameraId, "cameraId");
        this.d = cameraId;
        CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
        Integer num = (Integer) characteristics.get(CameraCharacteristics.LENS_FACING);
        boolean z = num != null && num.intValue() == 0;
        this.f = z;
        StreamConfigurationMap streamsMap = (StreamConfigurationMap) characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamsMap != null) {
            int rotatedPreviewWidth;
            int rotatedPreviewHeight;
            Size[] outputSizes = streamsMap.getOutputSizes(Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE);
            Object max = Collections.max(Arrays.asList((Size[]) Arrays.copyOf(outputSizes, outputSizes.length)), new c());
            kotlin.jvm.b.c.a(max, "Collections.max(\n       …    CompareSizesByArea())");
            Size size = (Size) max;
            kotlin.jvm.b.c.b(size, PropertiesEntry.COLUMN_NAME_VALUE);
            this.n = size;
            l.a("New capture size: " + size);
            this.m.a();
            max = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
            kotlin.jvm.b.c.a(max, "characteristics.get(Came…stics.SENSOR_ORIENTATION)");
            this.g = ((Number) max).intValue();
            int i = this.g;
            int b = b();
            boolean swappedDimensions = false;
            switch (b) {
                case 0:
                case 2:
                    if (i == 90 || i == 270) {
                        swappedDimensions = true;
                        break;
                    }
                case 1:
                case 3:
                    if (i == 0 || i == 180) {
                        swappedDimensions = true;
                        break;
                    }
                default:
                    kotlin.jvm.b.c.b("Display rotation is invalid: " + b, "message");
                    break;
            }
            Point displaySize = new Point();
            this.c.getDefaultDisplay().getSize(displaySize);
            if (swappedDimensions) {
                rotatedPreviewWidth = surfaceHeight;
            } else {
                rotatedPreviewWidth = surfaceWidth;
            }
            if (swappedDimensions) {
                rotatedPreviewHeight = surfaceWidth;
            } else {
                rotatedPreviewHeight = surfaceHeight;
            }
            int maxPreviewWidth = swappedDimensions ? displaySize.y : displaySize.x;
            int maxPreviewHeight = swappedDimensions ? displaySize.x : displaySize.y;
            maxPreviewWidth = Math.min(maxPreviewWidth, 1920);
            maxPreviewHeight = Math.min(maxPreviewHeight, 1080);
            max = streamsMap.getOutputSizes(SurfaceTexture.class);
            kotlin.jvm.b.c.a(max, "streamsMap.getOutputSize…rfaceTexture::class.java)");
            size = b.a(max, rotatedPreviewWidth, rotatedPreviewHeight, maxPreviewWidth, maxPreviewHeight, this.n);
            kotlin.jvm.b.c.b(size, PropertiesEntry.COLUMN_NAME_VALUE);
            this.l = size;
            l.a("New preview size: " + size);
            this.k.a();
            max = streamsMap.getOutputSizes(MediaRecorder.class);
            kotlin.jvm.b.c.a(max, "streamsMap.getOutputSize…ediaRecorder::class.java)");
            this.r = b.a(max, new Size(surfaceWidth, surfaceHeight), new Size(this.p, this.q));
            max = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
            kotlin.jvm.b.c.a(max, "characteristics.get(Came…ics.FLASH_INFO_AVAILABLE)");
            this.i = ((Boolean) max).booleanValue();
            this.t = (Rect) characteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
            this.s = kotlin.jvm.b.c.a(((Number) characteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue()) >= 0;
            max = characteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM);
            kotlin.jvm.b.c.a(max, "characteristics.get(Came…AILABLE_MAX_DIGITAL_ZOOM)");
            this.u = ((Number) max).floatValue();
            max = characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
            kotlin.jvm.b.c.a(max, "characteristics.get(Came…ILABLE_TARGET_FPS_RANGES)");
            this.v = (Range) d.a((Object[]) max, new f()).get(0);
        }
    }

    public final void n() {
        this.b.disable();
    }
}
