package com.skype.android.video.hw.codec.encoder.control;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.adjust.sdk.Constants;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.codec.encoder.AbstractVideoEncoder.APINotImplementedException;
import com.skype.android.video.hw.format.VideoFormat;
import com.skype.android.video.hw.utils.CodecUtils;
import com.skype.android.video.hw.utils.Log;

public final class VideoEncoderControlQcAfterNExtension extends AbstractVideoEncoderControl {
    private int curNumTemporalLayers = 1;
    private String extPrefix;
    private final Bundle frameParams = new Bundle();
    private final MediaCodec mediaCodec;
    private int prevNumTemporalLayers = 1;

    public VideoEncoderControlQcAfterNExtension(MediaCodec mediaCodec) {
        super(mediaCodec, null);
        this.mediaCodec = mediaCodec;
        this.extPrefix = mediaCodec.getCodecInfo().getName().toLowerCase().contains("qcom") ? "vendor.qti" : "vendor.rtc";
    }

    public final void setRcFrameRate(float fps) throws APINotImplementedException {
    }

    public final void setQp(int qp) throws APINotImplementedException {
        this.frameParams.putInt(this.extPrefix + "-ext-enc-frame-qp.value", qp);
    }

    public final void setNumTempLayers(int numTempLayers) throws APINotImplementedException {
        Log.d(Commons.TAG, "setNumTempLayers " + numTempLayers);
        this.frameParams.putString("ts-schema", "android.generic." + numTempLayers);
        this.curNumTemporalLayers = numTempLayers;
    }

    public final void setBaseLayerPID(int baseLayerPID) throws APINotImplementedException {
        this.frameParams.putInt(this.extPrefix + "-ext-enc-base-layer-pid.value", baseLayerPID);
    }

    public final void markLtrFrame(int longTermFrameIdx) throws APINotImplementedException {
        this.frameParams.putInt(this.extPrefix + "-ext-enc-ltr.mark-frame", longTermFrameIdx);
    }

    public final void useLTRFrame(int useLTRFrameIdxBitMap) throws APINotImplementedException {
        this.frameParams.putInt(this.extPrefix + "-ext-enc-ltr.use-frame", useLTRFrameIdxBitMap);
    }

    public final void doConfigureMediaFormat(MediaFormat mediaFormat, VideoFormat format) {
        int maxNumTemporalLayers = 0;
        super.doConfigureMediaFormat(mediaFormat, format);
        Log.i(Commons.TAG, "doConfigureMediaFormat  " + format.toString());
        mediaFormat.setInteger(this.extPrefix + "-ext-enc-low-latency.enable", 1);
        mediaFormat.setInteger("prepend-sps-pps-to-idr-frames", 1);
        mediaFormat.setInteger(this.extPrefix + "-ext-enc-app-input-control.enable", 1);
        mediaFormat.setInteger(this.extPrefix + "-ext-enc-custom-profile-level.level", format.getLevel().getOmxValue().intValue());
        int profile = format.getProfile().getOmxValue().intValue();
        if (VERSION.SDK_INT < 28) {
            mediaFormat.setInteger("bitrate-mode", 0);
        } else {
            mediaFormat.setInteger(this.extPrefix + "-ext-enc-bitrate-mode.value", 0);
            if (this.extPrefix.contains("qti")) {
                if (format.getProfile().getName().toLowerCase().contains("baseline")) {
                    profile = 65536;
                } else if (format.getProfile().getName().toLowerCase().contains(Constants.HIGH)) {
                    profile = 524288;
                }
            }
        }
        mediaFormat.setInteger("profile", profile);
        mediaFormat.setInteger(this.extPrefix + "-ext-enc-custom-profile-level.profile", profile);
        int numSlices = Math.min(Math.max(format.getMinNumSlices(), 1), 15);
        int sliceSize = (((((format.getResolution().getWidth() + 15) >> 4) * ((format.getResolution().getHeight() + 15) >> 4)) + numSlices) - 1) / numSlices;
        if (sliceSize > 1) {
            mediaFormat.setInteger(this.extPrefix + "-ext-enc-slice.spacing", sliceSize);
        } else {
            mediaFormat.setInteger(this.extPrefix + "-ext-enc-slice.spacing", 0);
        }
        int operatingfps = CodecUtils.getEncoderOperatingFpsFromNative();
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + " operatingfps " + operatingfps);
        }
        if (operatingfps != 0) {
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + " Setting KEY_OPERATING_RATE to " + operatingfps);
            }
            mediaFormat.setInteger("operating-rate", operatingfps);
        }
        this.curNumTemporalLayers = format.getNumLayers();
        if (format.getNumLayers() > 1) {
            maxNumTemporalLayers = format.getNumLayers();
        } else if (format.getSvcAlways()) {
            maxNumTemporalLayers = 2;
        }
        this.prevNumTemporalLayers = maxNumTemporalLayers;
        if (this.curNumTemporalLayers > 1 || format.getSvcAlways()) {
            mediaFormat.setString("ts-schema", "android.generic." + maxNumTemporalLayers);
        }
        mediaFormat.setInteger(this.extPrefix + "-ext-enc-ltr-count.num-ltr-frames", Math.max(format.getRefCount(), 1) - 1);
    }

    public final void cleanFrameParams() {
        this.frameParams.clear();
        if (this.prevNumTemporalLayers != this.curNumTemporalLayers) {
            this.frameParams.putString("ts-schema", "android.generic." + this.curNumTemporalLayers);
            this.prevNumTemporalLayers = this.curNumTemporalLayers;
        }
    }

    public final void markParamsTimestamp(long timestampMs) {
        Log.d(Commons.TAG, getClass().getSimpleName() + "set " + this.extPrefix + "-ext-enc-input-trigger.timestamp " + (timestampMs * 1000) + " us");
        this.frameParams.putLong(this.extPrefix + "-ext-enc-input-trigger.timestamp", timestampMs * 1000);
    }

    public final void configureFrameParams() {
        this.prevNumTemporalLayers = this.curNumTemporalLayers;
        this.mediaCodec.setParameters(this.frameParams);
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": Setting " + this.frameParams + " on " + this.mediaCodec);
        }
    }
}
