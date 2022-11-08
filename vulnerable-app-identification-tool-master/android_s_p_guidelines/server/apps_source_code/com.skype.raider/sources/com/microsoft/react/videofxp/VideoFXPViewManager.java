package com.microsoft.react.videofxp;

import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.am;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.microsoft.react.videofxp.VideoFXPModule.a;
import java.util.Map;

public class VideoFXPViewManager extends SimpleViewManager<VideoFXPView> implements a {
    public static final String REACT_CLASS = "VideoFXPView";
    public static final String REENCODE_PROGRESS_CHANGED_EVENT_NAME = "VideoReencoderProgress";
    public static final String TAG = "VideoFXPViewManager";
    private VideoFXPView mView;

    public String getName() {
        return REACT_CLASS;
    }

    public VideoFXPView createViewInstance(ae context) {
        this.mView = new VideoFXPView(context);
        return this.mView;
    }

    @ReactProp(name = "shaderLeft")
    public void setShaderLeft(VideoFXPView view, am shaderLeft) {
        FLog.i(TAG, "setShaderLeft : " + shaderLeft.toString());
        view.setLensModeLeft(shaderLeft.getString("shader"), (float) shaderLeft.getDouble("intensity"));
    }

    @ReactProp(name = "shaderRight")
    public void setShaderRight(VideoFXPView view, am shaderRight) {
        FLog.i(TAG, "setShaderRight : " + shaderRight.getString("shader"));
        view.setLensModeRight(shaderRight.getString("shader"), (float) shaderRight.getDouble("intensity"));
    }

    @ReactProp(name = "shaderX")
    public void setShaderX(VideoFXPView view, float shaderX) {
        FLog.i(TAG, "setShaderX " + shaderX);
        view.setLensModeOffsetX(shaderX);
    }

    @ReactProp(name = "source")
    public void setSource(VideoFXPView view, String source) {
        FLog.i(TAG, "setSource " + source);
        view.setSource(source);
    }

    public VideoFXPView getView() {
        return this.mView;
    }

    @Nullable
    public Map getExportedViewConstants() {
        return e.a(REENCODE_PROGRESS_CHANGED_EVENT_NAME, REENCODE_PROGRESS_CHANGED_EVENT_NAME);
    }
}
