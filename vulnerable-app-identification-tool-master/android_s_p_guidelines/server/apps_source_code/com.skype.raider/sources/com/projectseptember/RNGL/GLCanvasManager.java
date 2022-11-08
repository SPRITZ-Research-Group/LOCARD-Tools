package com.projectseptember.RNGL;

import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.core.a;
import com.facebook.imagepipeline.core.e;
import com.facebook.imagepipeline.memory.ab;
import com.facebook.imagepipeline.memory.ac;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.q;
import com.microsoft.react.videofxp.VideoFXPModule;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class GLCanvasManager extends SimpleViewManager<GLCanvas> {
    public static final int COMMAND_CAPTURE_FRAME = 1;
    public static final String REACT_CLASS = "GLCanvas";
    private static final String TAG = "RNGLCanvasManager";
    private static final Random random = new Random();
    private e executorSupplier;

    public static int causeId() {
        return random.nextInt();
    }

    @ReactProp(name = "pixelRatio")
    public void setPixelRatio(GLCanvas view, float pixelRatio) {
        view.setPixelRatio(pixelRatio);
    }

    @ReactProp(name = "nbContentTextures")
    public void setNbContentTextures(GLCanvas view, int nbContentTextures) {
        view.setNbContentTextures(nbContentTextures);
    }

    @ReactProp(name = "renderId")
    public void setRenderId(GLCanvas view, int renderId) {
        view.setRenderId(renderId);
    }

    @ReactProp(name = "autoRedraw")
    public void setAutoRedraw(GLCanvas view, boolean autoRedraw) {
        view.setAutoRedraw(autoRedraw);
    }

    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(GLCanvas view, @Nullable String pointerEventsStr) {
        if (pointerEventsStr != null) {
            view.a(q.valueOf(pointerEventsStr.toUpperCase(Locale.US).replace("-", "_")));
        }
    }

    @ReactProp(name = "data")
    public void setData(GLCanvas view, @Nullable am data) {
        int causeId = causeId();
        FLog.i(TAG, "setData with causeId %x", Integer.valueOf(causeId));
        view.setData(data == null ? null : e.a(data), causeId);
    }

    @ReactProp(name = "imagesToPreload")
    public void setImagesToPreload(GLCanvas view, @Nullable al imageToPreload) {
        int causeId = causeId();
        FLog.i(TAG, "setImagesToPreload with causeId %x", Integer.valueOf(causeId));
        view.setImagesToPreload(imageToPreload, causeId);
    }

    public String getName() {
        return REACT_CLASS;
    }

    public GLCanvas createViewInstance(ae context) {
        if (this.executorSupplier == null) {
            this.executorSupplier = new a(new ac(ab.l().a()).c());
        }
        return new GLCanvas(context, this.executorSupplier);
    }

    public void receiveCommand(GLCanvas canvas, int commandType, @Nullable al args) {
        com.facebook.infer.annotation.a.a((Object) canvas);
        com.facebook.infer.annotation.a.a((Object) args);
        switch (commandType) {
            case 1:
                am map = args.getMap(0);
                canvas.a(new a(map.getString("format"), map.getString("type"), map.getString("filePath"), Double.valueOf(map.getDouble("quality")), map.getString("filePathBackup")));
                return;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", new Object[]{Integer.valueOf(commandType), getClass().getSimpleName()}));
        }
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return com.facebook.react.common.e.a("captureFrame", com.facebook.react.common.e.a("registrationName", "onGLCaptureFrame"), "load", com.facebook.react.common.e.a("registrationName", "onGLLoad"), VideoFXPModule.REENCODING_EVENT_PROGRESS_KEY, com.facebook.react.common.e.a("registrationName", "onGLProgress"));
    }

    public Map<String, Integer> getCommandsMap() {
        return com.facebook.react.common.e.a("captureFrame", Integer.valueOf(1));
    }
}
