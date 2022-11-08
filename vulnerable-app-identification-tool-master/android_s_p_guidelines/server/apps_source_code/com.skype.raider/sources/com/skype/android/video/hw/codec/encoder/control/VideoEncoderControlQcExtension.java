package com.skype.android.video.hw.codec.encoder.control;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.codec.encoder.AbstractVideoEncoder.APINotImplementedException;
import com.skype.android.video.hw.format.VideoFormat;
import com.skype.android.video.hw.utils.CodecUtils;
import com.skype.android.video.hw.utils.Log;

public final class VideoEncoderControlQcExtension extends AbstractVideoEncoderControl {
    private int curNumTemporalLayers = 1;
    private final Bundle frameParams = new Bundle();
    private final MediaCodec mediaCodec;
    private int prevNumTemporalLayers = 1;

    public VideoEncoderControlQcExtension(MediaCodec mediaCodec) {
        super(mediaCodec, null);
        this.mediaCodec = mediaCodec;
    }

    public final void setRcFrameRate(float fps) throws APINotImplementedException {
    }

    public final void setQp(int qp) throws APINotImplementedException {
        this.frameParams.putInt("vt-config-frame-qp", qp);
    }

    public final void setNumTempLayers(int numTempLayers) throws APINotImplementedException {
        this.frameParams.putInt("vt-config-temporal-layer-count", numTempLayers);
        this.curNumTemporalLayers = numTempLayers;
    }

    public final void setBaseLayerPID(int baseLayerPID) throws APINotImplementedException {
        this.frameParams.putInt("vt-config-base-layer-pid", baseLayerPID);
    }

    public final void markLtrFrame(int longTermFrameIdx) throws APINotImplementedException {
        this.frameParams.putInt("vt-config-mark-ltr", longTermFrameIdx);
    }

    public final void useLTRFrame(int useLTRFrameIdxBitMap) throws APINotImplementedException {
        this.frameParams.putInt("vt-config-use-ltr", useLTRFrameIdxBitMap);
    }

    public final void doConfigureMediaFormat(MediaFormat mediaFormat, VideoFormat format) {
        int maxNumTemporalLayers = 0;
        super.doConfigureMediaFormat(mediaFormat, format);
        mediaFormat.setInteger("vt-low-latency", 1);
        mediaFormat.setInteger("vt-avc-level", format.getLevel().getOmxValue().intValue());
        mediaFormat.setInteger("vt-use-extended-profile", 1);
        mediaFormat.setInteger("vt-extension-profile", format.getProfile().getOmxValue().intValue());
        mediaFormat.setInteger("vt-sequence-hdr-with-idr", 1);
        mediaFormat.setInteger("vt-ratecontrol", 0);
        int numSlices = Math.min(Math.max(format.getMinNumSlices(), 1), 15);
        int sliceSize = (((((format.getResolution().getWidth() + 15) >> 4) * ((format.getResolution().getHeight() + 15) >> 4)) + numSlices) - 1) / numSlices;
        if (sliceSize > 1) {
            mediaFormat.setInteger("vt-slice-hdr-spacing", sliceSize);
            mediaFormat.setInteger("vt-slice-control-mode", 1);
        } else {
            mediaFormat.setInteger("vt-slice-control-mode", 0);
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
            mediaFormat.setInteger("vt-video-hierar-type", 1);
            mediaFormat.setInteger("vt-max-temporal-layer-count", maxNumTemporalLayers);
        }
        mediaFormat.setInteger("vt-num-ltr-frames", Math.max(format.getRefCount(), 1) - 1);
    }

    public final void cleanFrameParams() {
        this.frameParams.clear();
        if (this.prevNumTemporalLayers != this.curNumTemporalLayers) {
            this.frameParams.putInt("vt-config-temporal-layer-count", this.curNumTemporalLayers);
            this.prevNumTemporalLayers = this.curNumTemporalLayers;
        }
    }

    public final void markParamsTimestamp(long timestampMs) {
        Log.d(Commons.TAG, getClass().getSimpleName() + " set vt-config-timestamp " + (timestampMs * 1000) + " us");
        this.frameParams.putLong("vt-config-timestamp", timestampMs * 1000);
    }

    public final void configureFrameParams() {
        this.prevNumTemporalLayers = this.curNumTemporalLayers;
        this.mediaCodec.setParameters(this.frameParams);
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": Setting " + this.frameParams + " on " + this.mediaCodec);
        }
    }
}
