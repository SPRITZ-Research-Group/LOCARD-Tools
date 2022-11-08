package com.brentvatne.react;

import com.facebook.react.bridge.am;
import com.facebook.react.common.e;
import com.facebook.react.common.e.a;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.yqritc.scalablevideoview.c;
import java.util.Map;
import javax.annotation.Nullable;

public class ReactVideoViewManager extends SimpleViewManager<ReactVideoView> {
    public static final String PROP_MUTED = "muted";
    public static final String PROP_PAUSED = "paused";
    public static final String PROP_PLAY_IN_BACKGROUND = "playInBackground";
    public static final String PROP_RATE = "rate";
    public static final String PROP_REPEAT = "repeat";
    public static final String PROP_RESIZE_MODE = "resizeMode";
    public static final String PROP_RESUME_AFTER_FOREGROUND = "resumeAfterForeground";
    public static final String PROP_SEEK = "seek";
    public static final String PROP_SRC = "src";
    public static final String PROP_SRC_AUTH_TOKEN = "authToken";
    public static final String PROP_SRC_CLIENT_VERSION = "clientVersion";
    public static final String PROP_SRC_IS_ASSET = "isAsset";
    public static final String PROP_SRC_IS_NETWORK = "isNetwork";
    public static final String PROP_SRC_MEDIA_PROVIDER = "mediaProvider";
    public static final String PROP_SRC_REDIRECT_HLS = "shouldRedirectForAndroidHLS";
    public static final String PROP_SRC_TYPE = "type";
    public static final String PROP_SRC_URI = "uri";
    public static final String PROP_VOLUME = "volume";
    public static final String REACT_CLASS = "RCTVideo";

    public String getName() {
        return REACT_CLASS;
    }

    protected ReactVideoView createViewInstance(ae themedReactContext) {
        return new ReactVideoView(themedReactContext);
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        a builder = e.a();
        for (ReactVideoView.a event : ReactVideoView.a.values()) {
            builder.a(event.toString(), e.a("registrationName", event.toString()));
        }
        return builder.a();
    }

    @Nullable
    public Map getExportedViewConstants() {
        return e.a("ScaleNone", Integer.toString(c.LEFT_TOP.ordinal()), "ScaleToFill", Integer.toString(c.FIT_XY.ordinal()), "ScaleAspectFit", Integer.toString(c.FIT_CENTER.ordinal()), "ScaleAspectFill", Integer.toString(c.CENTER_CROP.ordinal()));
    }

    @ReactProp(name = "src")
    public void setSrc(ReactVideoView videoView, @Nullable am src) {
        String string;
        String string2;
        String string3 = src.getString(PROP_SRC_URI);
        String string4 = src.getString("type");
        boolean z = src.getBoolean(PROP_SRC_IS_NETWORK);
        boolean z2 = src.getBoolean(PROP_SRC_IS_ASSET);
        String string5 = src.hasKey(PROP_SRC_AUTH_TOKEN) ? src.getString(PROP_SRC_AUTH_TOKEN) : null;
        boolean z3 = src.hasKey(PROP_SRC_REDIRECT_HLS) ? src.getBoolean(PROP_SRC_REDIRECT_HLS) : false;
        if (src.hasKey(PROP_SRC_CLIENT_VERSION)) {
            string = src.getString(PROP_SRC_CLIENT_VERSION);
        } else {
            string = null;
        }
        if (src.hasKey(PROP_SRC_MEDIA_PROVIDER)) {
            string2 = src.getString(PROP_SRC_MEDIA_PROVIDER);
        } else {
            string2 = null;
        }
        videoView.setSrc(string3, string4, z, z2, string5, z3, string, string2);
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(ReactVideoView videoView, String resizeModeOrdinalString) {
        videoView.setResizeModeModifier(c.values()[Integer.parseInt(resizeModeOrdinalString)]);
    }

    @ReactProp(defaultBoolean = false, name = "repeat")
    public void setRepeat(ReactVideoView videoView, boolean repeat) {
        videoView.setRepeatModifier(repeat);
    }

    @ReactProp(defaultBoolean = false, name = "paused")
    public void setPaused(ReactVideoView videoView, boolean paused) {
        videoView.setPausedModifier(paused);
    }

    @ReactProp(defaultBoolean = false, name = "muted")
    public void setMuted(ReactVideoView videoView, boolean muted) {
        videoView.setMutedModifier(muted);
    }

    @ReactProp(defaultFloat = 1.0f, name = "volume")
    public void setVolume(ReactVideoView videoView, float volume) {
        videoView.setVolumeModifier(volume);
    }

    @ReactProp(name = "seek")
    public void setSeek(ReactVideoView videoView, float seek) {
        videoView.a(Math.round(1000.0f * seek));
    }

    @ReactProp(name = "rate")
    public void setRate(ReactVideoView videoView, float rate) {
        videoView.setRateModifier(rate);
    }

    @ReactProp(defaultBoolean = false, name = "playInBackground")
    public void setPlayInBackground(ReactVideoView videoView, boolean playInBackground) {
        videoView.setPlayInBackground(playInBackground);
    }

    @ReactProp(defaultBoolean = false, name = "resumeAfterForeground")
    public void setResumeAfterForeground(ReactVideoView videoView, boolean resume) {
        videoView.setResumeAfterForeground(resume);
    }
}
