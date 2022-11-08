package com.skype.facebookaudiencenetwork;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;

public class MediaViewManager extends SimpleViewManager<MediaView> {
    private static final String RN_CLASS = "RNMediaView";

    public String getName() {
        return RN_CLASS;
    }

    protected MediaView createViewInstance(ae reactContext) {
        MediaView mediaView = new MediaView(reactContext);
        mediaView.setFocusable(false);
        return mediaView;
    }
}
