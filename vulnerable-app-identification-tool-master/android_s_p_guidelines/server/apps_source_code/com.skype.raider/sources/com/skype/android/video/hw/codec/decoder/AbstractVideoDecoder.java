package com.skype.android.video.hw.codec.decoder;

import android.media.MediaFormat;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.codec.AbstractMediaCodec;
import com.skype.android.video.hw.format.VideoFormat;
import com.skype.android.video.hw.utils.CodecUtils;
import com.skype.android.video.hw.utils.Log;

public abstract class AbstractVideoDecoder extends AbstractMediaCodec {
    protected AbstractVideoDecoder(String codecName) {
        super(codecName, false);
    }

    protected void doConfigureMediaFormat(MediaFormat mediaFormat, VideoFormat format) {
        if (format.getSpsPps().capacity() != 0) {
            mediaFormat.setByteBuffer("csd-0", format.getSpsPps());
        }
        if (this.useQCDecoderExtension) {
            mediaFormat.setInteger("vt-low-latency", 1);
        } else if (this.useQCAfterNougatDecoderExtension) {
            mediaFormat.setInteger(getExtPrefix() + "-ext-dec-low-latency.enable", 1);
            mediaFormat.setInteger(getExtPrefix() + "-ext-dec-picture-order.enable", 1);
        }
        if (this.useQCDecoderExtension || this.useQCAfterNougatDecoderExtension) {
            int operatingfps = CodecUtils.getDecoderOperatingFpsFromNative();
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + " operatingfps " + operatingfps);
            }
            if (operatingfps != 0) {
                if (Log.isLoggable(Commons.TAG, 3)) {
                    Log.d(Commons.TAG, getClass().getSimpleName() + " Setting KEY_OPERATING_RATE to " + operatingfps);
                }
                mediaFormat.setInteger("operating-rate", operatingfps);
            }
            mediaFormat.setInteger("color-format", 2135033992);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": Setting " + mediaFormat);
            }
        }
    }

    protected void doConfigureDynamic(VideoFormat format) {
    }

    protected int doGetMediaCodecFlags() {
        return 0;
    }
}
