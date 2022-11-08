package com.skype.android.video.hw.extension.encoder;

import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.codec.encoder.sample.VideoSampleEncoder;
import com.skype.android.video.hw.extension.JniCodecUtils;
import com.skype.android.video.hw.frame.InputFrame;
import com.skype.android.video.hw.frame.OutputFrame;
import com.skype.android.video.hw.utils.DebugUtils;
import com.skype.android.video.hw.utils.EncoderAttributes;
import com.skype.android.video.hw.utils.EncoderAttributes.AttributeException;
import com.skype.android.video.hw.utils.Log;

public class VideoSampleEncoderExtension extends AbstractVideoEncoderExtension<VideoSampleEncoder> {

    public static class Factory implements VideoEncoderExtensionFactory {
        public VideoSampleEncoderExtension create(int encoderIndex) {
            return new VideoSampleEncoderExtension(encoderIndex);
        }
    }

    public VideoSampleEncoderExtension(int encoderIndex) {
        super(encoderIndex);
    }

    protected VideoSampleEncoder doCreateEncoder(String encoderName) {
        return new VideoSampleEncoder(encoderName);
    }

    protected OutputFrame doEncodeFrame(long timeoutUs, long nativeFrameContext, long ts, boolean drainOnly) {
        if (drainOnly) {
            return ((VideoSampleEncoder) getEncoder()).encode(null, timeoutUs, Boolean.valueOf(true));
        }
        InputFrame inputFrame = ((VideoSampleEncoder) getEncoder()).getInputFrame(timeoutUs);
        inputFrame.setTimestamp((ts - getInitialTimestamp()) * 1000);
        inputFrame.setSize(getRawFrameSize());
        try {
            JniCodecUtils.fillInputFrameBuffer(nativeFrameContext, inputFrame.getData(), getRawFrameSize(), true);
            return ((VideoSampleEncoder) getEncoder()).encode(inputFrame, timeoutUs, Boolean.valueOf(false));
        } catch (RuntimeException e) {
            if (!Log.isLoggable(Commons.TAG, 6)) {
                return null;
            }
            Log.e(Commons.TAG, "fillInputFrameBuffer throw exception ", e);
            return null;
        }
    }

    public static VideoSampleEncoderExtension createStatic(int encoderIndex) {
        return new VideoSampleEncoderExtension(encoderIndex);
    }

    public int init(String attr, boolean isReinitializing) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
            Log.d(Commons.TAG, attr);
        }
        try {
            return super.initInternal(new EncoderAttributes(attr), isReinitializing, false, null);
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
