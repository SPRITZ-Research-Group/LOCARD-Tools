package com.skypecam.camera2;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import com.skypecam.camera2.j.a;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u001b\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005\u001a\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001d\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0002\u0010\r\u001a\u001d\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0002\u0010\u000f\u001a\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\t¨\u0006\u0013"}, d2 = {"getCameraIds", "", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)[Ljava/lang/String;", "getCameraManager", "Landroid/hardware/camera2/CameraManager;", "getGlobalSupportedHardwareLevel", "Lcom/skypecam/camera2/HardwareLevel;", "getSupportedHardwareLevel", "", "cameraId", "(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/Integer;", "manager", "(Landroid/hardware/camera2/CameraManager;Ljava/lang/String;)Ljava/lang/Integer;", "isHardwareLevelSupported", "", "requiredLevel", "react-native-camera2lib_release"}, k = 2, mv = {1, 1, 10})
@JvmName(name = "CameraInfoHelper")
public final class c {
    @Nullable
    private static Integer a(@NotNull CameraManager manager, @NotNull String cameraId) {
        CameraManager it;
        kotlin.jvm.b.c.b(manager, "manager");
        kotlin.jvm.b.c.b(cameraId, "cameraId");
        try {
            it = manager.getCameraCharacteristics(cameraId);
        } catch (IllegalArgumentException e) {
            kotlin.jvm.b.c.b("getSupportedHardwardLevels failed: cameraId does not match any known camera device.", "message");
            it = null;
        } catch (CameraAccessException e2) {
            kotlin.jvm.b.c.b("getSupportedHardwareLevels failed: camera device has been disconnnected.", "message");
            it = null;
        }
        if (it == null) {
            return null;
        }
        try {
            return (Integer) it.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        } catch (IllegalArgumentException e3) {
            kotlin.jvm.b.c.b("getSupportedHardwareLevels failed: key to get cameracharacteristics was not valid.", "message");
            return null;
        }
    }

    @NotNull
    public static final j a(@NotNull Context context) {
        kotlin.jvm.b.c.b(context, "context");
        j globalHwLevelSupported = null;
        kotlin.jvm.b.c.b(context, "context");
        CameraManager manager = context.getSystemService("camera");
        if (!(manager instanceof CameraManager)) {
            manager = null;
        }
        manager = manager;
        if (manager != null) {
            String[] cameraIdList = manager.getCameraIdList();
            if (cameraIdList != null) {
                for (Object id : cameraIdList) {
                    kotlin.jvm.b.c.a(id, "id");
                    Integer a = a(manager, id);
                    if (a != null) {
                        int it = a.intValue();
                        a aVar = j.g;
                        for (j cameraLevel : j.values()) {
                            Object obj;
                            a = cameraLevel.b();
                            if (a != null && a.intValue() == it) {
                                obj = 1;
                            } else {
                                obj = null;
                            }
                            if (obj != null) {
                                break;
                            }
                        }
                        j cameraLevel2 = null;
                        if (cameraLevel2 == null) {
                            cameraLevel2 = j.f;
                        }
                        l.a("Hardware level for camera " + id + " is " + cameraLevel2);
                        if (globalHwLevelSupported != null) {
                            int a2 = cameraLevel2.a();
                            if (globalHwLevelSupported == null) {
                                kotlin.jvm.b.c.a();
                            }
                            if (a2 >= globalHwLevelSupported.a()) {
                            }
                        }
                        globalHwLevelSupported = cameraLevel2;
                    }
                }
            }
        }
        if (globalHwLevelSupported == null) {
            globalHwLevelSupported = j.a;
        }
        l.a("Global hardware level for this device: " + globalHwLevelSupported);
        if (globalHwLevelSupported == null) {
            kotlin.jvm.b.c.a();
        }
        return globalHwLevelSupported;
    }
}
