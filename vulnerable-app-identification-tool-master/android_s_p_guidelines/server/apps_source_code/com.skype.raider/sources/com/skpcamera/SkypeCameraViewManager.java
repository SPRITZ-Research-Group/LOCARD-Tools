package com.skpcamera;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.ar;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.Map;
import javax.annotation.Nullable;

public class SkypeCameraViewManager extends ViewGroupManager<ViewGroup> {
    public static final String CAMERA_MODE_EVENT_NAME = "evtCameraMode";
    public static final String FLASH_MODE_EVENT_NAME = "evtFlashMode";
    public static final String QR_CODE_DETECT_EVENT_NAME = "evtQRCodeDetect";
    public static final String REACT_CLASS = "SKPCameraView";
    public static final String RECORDING_CHANGE_EVENT_NAME = "evtRecordingChange";
    private static final String TAG = "SkypeCameraViewManager";

    SkypeCameraViewManager() {
        FLog.i(TAG, "init");
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ViewGroup createViewInstance(ae context) {
        FLog.i(TAG, "createViewInstance");
        return b.a().b().a(context);
    }

    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return e.a(CAMERA_MODE_EVENT_NAME, e.a("registrationName", "onCameraModeChange"), RECORDING_CHANGE_EVENT_NAME, e.a("registrationName", "onRecordingChange"), QR_CODE_DETECT_EVENT_NAME, e.a("registrationName", "onQRCodeDetect"), FLASH_MODE_EVENT_NAME, e.a("registrationName", "onFlashModeChange"));
    }

    @ReactProp(name = "cameraMode")
    public void setCameraMode(ViewGroup view, Integer mode) {
        b.a().b().a(view, mode);
    }

    @ReactProp(name = "flashMode")
    public void setFlashMode(ViewGroup view, Integer mode) {
        b.a().b().a(mode);
        ar event = new WritableNativeMap();
        event.putInt("flashMode", mode.intValue());
        sendEvent(FLASH_MODE_EVENT_NAME, view, event);
    }

    @ReactProp(name = "sessionActive")
    public void setSessionActive(ViewGroup view, boolean sessionActive) {
        FLog.i(TAG, "setSessionActive " + sessionActive);
        b.a().b().a(view, sessionActive);
    }

    @ReactProp(name = "useShutterAnimation")
    public void setUseShutterAnimation(ViewGroup view, boolean useShutterAnimation) {
    }

    @ReactProp(name = "maxRecordingMs")
    public void setMaxRecordingMs(ViewGroup view, float mode) {
        b.a().b().a(mode);
    }

    @ReactProp(name = "releaseOnBackground")
    public void setReleaseOnBackground(ViewGroup view, boolean shouldReleaseOnBackground) {
    }

    @ReactProp(name = "videoThumbnailMaxDimension")
    public void setVideoThumbnailMaxDimension(ViewGroup view, float dimension) {
        b.a().b().b(dimension);
    }

    @ReactProp(name = "videoThumbnailCompressionRate")
    public void setVideoThumbnailCompressionRate(ViewGroup view, int rate) {
        b.a().b().a(rate);
    }

    @ReactProp(name = "videoWidth")
    public void setVideoWidth(ViewGroup view, float width) {
        b.a().b().c(width);
    }

    @ReactProp(name = "videoHeight")
    public void setVideoHeight(ViewGroup view, float height) {
        b.a().b().d(height);
    }

    @ReactProp(name = "videoBitrate")
    public void setVideoBitrate(ViewGroup view, float bitrate) {
        b.a().b().e(bitrate);
    }

    @ReactProp(name = "videoProfile")
    public void setVideoProfile(ViewGroup view, float profile) {
        b.a().b().f(profile);
    }

    @ReactProp(name = "enableVideoBFrame")
    public void enableVideoBFrame(ViewGroup view, boolean state) {
        b.a().b().a(state);
    }

    @ReactProp(name = "qrCodeDetect")
    public void setQRCodeDetect(ViewGroup view, boolean detect) {
        b.a().b().b(view, detect);
    }

    public static void sendEvent(String eventName, View view, ar event) {
        if (view == null) {
            FLog.e(TAG, "Attempted to send event " + eventName + " with null view");
            return;
        }
        if (event == null) {
            FLog.w(TAG, "Attempted to send null event " + eventName);
        }
        int id = view.getId();
        ai context = (ai) view.getContext();
        if (context instanceof ai) {
            FLog.i(TAG, "Sending event " + eventName + " to view " + id + " with " + event.toString());
            ((RCTEventEmitter) context.a(RCTEventEmitter.class)).receiveEvent(id, eventName, event);
            return;
        }
        throw new IllegalStateException("React view context should be react context");
    }

    @ReactProp(name = "photoMode")
    public void setPhotoMode(ViewGroup view, boolean photo) {
    }

    @ReactProp(name = "adaptiveContentScale")
    public void setAdaptiveContentScale(ViewGroup view, boolean adaptiveContentScale) {
    }

    @ReactProp(name = "fillContentMode")
    public void setFillContentMode(ViewGroup view, boolean fillContentMode) {
    }

    @ReactProp(name = "useSensorOrientation")
    public void setUseSensorOrientation(ViewGroup view, boolean useSensorOrientation) {
        b.a().b().b(useSensorOrientation);
    }
}
