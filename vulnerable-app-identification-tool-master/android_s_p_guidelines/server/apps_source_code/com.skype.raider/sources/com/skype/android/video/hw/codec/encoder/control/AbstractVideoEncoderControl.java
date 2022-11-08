package com.skype.android.video.hw.codec.encoder.control;

import android.annotation.SuppressLint;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.codec.OmxWrapper;
import com.skype.android.video.hw.format.H264Profile;
import com.skype.android.video.hw.format.VideoFormat;
import com.skype.android.video.hw.utils.Log;

public abstract class AbstractVideoEncoderControl implements VideoEncoderControl {
    private final MediaCodec mediaCodec;
    private final Bundle setBitrateRequest = new Bundle();
    private final Bundle suspendRequest = new Bundle();
    private final Bundle syncFrameRequest = new Bundle();

    public AbstractVideoEncoderControl(MediaCodec mediaCodec, OmxWrapper omxWrapper) {
        this.mediaCodec = mediaCodec;
    }

    @SuppressLint({"NewApi"})
    public void setBitrate(int bitrate) {
        this.setBitrateRequest.putInt("video-bitrate", bitrate);
        this.mediaCodec.setParameters(this.setBitrateRequest);
    }

    @SuppressLint({"NewApi"})
    public void requestSyncFrame() {
        this.syncFrameRequest.putInt("request-sync", 0);
        this.mediaCodec.setParameters(this.syncFrameRequest);
    }

    @SuppressLint({"NewApi"})
    public void suspend() {
        this.suspendRequest.putInt("drop-input-frames", 1);
        this.mediaCodec.setParameters(this.suspendRequest);
    }

    public void doConfigureMediaFormat(MediaFormat mediaFormat, VideoFormat format) {
        mediaFormat.setInteger("color-format", format.getColorFormat().getOmxValue().intValue());
        if (format.getBitrate() > 0) {
            mediaFormat.setInteger("bitrate", format.getBitrate());
        } else {
            if (Log.isLoggable(Commons.TAG, 5)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Bad init bitrate " + format.getBitrate() + "bps. Setting default bitrate 500000bps on " + this.mediaCodec + " to prevent crash");
            }
            mediaFormat.setInteger("bitrate", 500000);
        }
        if (format.getFrameRate() >= 0) {
            mediaFormat.setInteger("frame-rate", format.getFrameRate());
        }
        if (format.getiFrameInterval() >= 0) {
            mediaFormat.setInteger("i-frame-interval", format.getiFrameInterval());
        }
        if (format.getProfile() == H264Profile.HIGH) {
            mediaFormat.setInteger("profile", H264Profile.HIGH.getOmxValue().intValue());
        } else {
            mediaFormat.setInteger("profile", H264Profile.BASELINE.getOmxValue().intValue());
        }
        mediaFormat.setInteger("level", format.getLevel().getOmxValue().intValue());
    }
}
