package com.skype.android.video.hw.factory;

import com.skype.android.video.hw.extension.decoder.VideoDecoderExtension;
import com.skype.android.video.hw.extension.decoder.VideoDecoderExtensionFactory;
import com.skype.android.video.hw.extension.encoder.VideoEncoderExtension;
import com.skype.android.video.hw.extension.encoder.VideoEncoderExtensionFactory;

public class ExtensionFactory {
    private static VideoDecoderExtensionFactory videoDecoderExtensionFactory;
    private static VideoEncoderExtensionFactory videoEncoderExtensionFactory;

    public static native void initNative();

    public static synchronized void setVideoEncoderExtensionFactory(VideoEncoderExtensionFactory factory) {
        synchronized (ExtensionFactory.class) {
            videoEncoderExtensionFactory = factory;
        }
    }

    public static synchronized void setVideoDecoderExtensionFactory(VideoDecoderExtensionFactory factory) {
        synchronized (ExtensionFactory.class) {
            videoDecoderExtensionFactory = factory;
        }
    }

    public static synchronized VideoEncoderExtension createVideoEncoderExtension(int encoderIndex) {
        VideoEncoderExtension videoEncoderExtension;
        synchronized (ExtensionFactory.class) {
            if (videoEncoderExtensionFactory == null) {
                videoEncoderExtension = null;
            } else {
                videoEncoderExtension = videoEncoderExtensionFactory.create(encoderIndex);
            }
        }
        return videoEncoderExtension;
    }

    public static synchronized VideoDecoderExtension createVideoDecoderExtension(int decoderIndex) {
        VideoDecoderExtension videoDecoderExtension;
        synchronized (ExtensionFactory.class) {
            if (videoDecoderExtensionFactory == null) {
                videoDecoderExtension = null;
            } else {
                videoDecoderExtension = videoDecoderExtensionFactory.create(decoderIndex);
            }
        }
        return videoDecoderExtension;
    }
}
