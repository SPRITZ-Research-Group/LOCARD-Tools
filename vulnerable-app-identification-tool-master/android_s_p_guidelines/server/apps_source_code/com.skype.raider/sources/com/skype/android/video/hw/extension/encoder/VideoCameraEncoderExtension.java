package com.skype.android.video.hw.extension.encoder;

import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.codec.encoder.camera.VideoCameraEncoder;
import com.skype.android.video.hw.frame.OutputFrame;
import com.skype.android.video.hw.utils.DebugUtils;
import com.skype.android.video.hw.utils.EncoderAttributes;
import com.skype.android.video.hw.utils.EncoderAttributes.AttributeException;
import com.skype.android.video.hw.utils.Log;

public class VideoCameraEncoderExtension extends AbstractVideoEncoderExtension<VideoCameraEncoder> {

    public static class Factory implements VideoEncoderExtensionFactory {
        public VideoEncoderExtension create(int encoderIndex) {
            return new VideoCameraEncoderExtension(encoderIndex);
        }
    }

    protected VideoCameraEncoderExtension(int encoderIndex) {
        super(encoderIndex);
    }

    protected VideoCameraEncoder doCreateEncoder(String encoderName) {
        return new VideoCameraEncoder(encoderName);
    }

    protected OutputFrame doEncodeFrame(long timeoutUs, long nativeFrameContext, long ts, boolean drainOnly) {
        return ((VideoCameraEncoder) getEncoder()).encode(timeoutUs);
    }

    public static VideoCameraEncoderExtension createStatic(int encoderIndex) {
        return new VideoCameraEncoderExtension(encoderIndex);
    }

    public int init(String attr, boolean isReinitializing) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
            Log.d(Commons.TAG, attr);
        }
        try {
            EncoderAttributes attributes = new EncoderAttributes(attr);
            return super.initInternal(attributes, isReinitializing, attributes.getInteger("android-fast-async-mode").intValue() != 0, attributes.getPointer("java-object", null));
        } catch (AttributeException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Illegal attribute value", e);
            }
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_INCORRECT_PARAM");
            }
            return -9;
        } catch (RuntimeException e2) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Unexpected exception caught", e2);
            }
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_UNKNOWN");
            }
            return -1;
        }
    }
}
