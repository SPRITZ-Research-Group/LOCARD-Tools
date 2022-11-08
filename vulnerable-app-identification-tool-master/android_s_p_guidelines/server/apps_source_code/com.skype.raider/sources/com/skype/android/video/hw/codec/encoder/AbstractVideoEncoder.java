package com.skype.android.video.hw.codec.encoder;

import android.annotation.SuppressLint;
import android.media.MediaFormat;
import android.os.Bundle;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.codec.AbstractMediaCodec;
import com.skype.android.video.hw.codec.encoder.control.AbstractVideoEncoderControl;
import com.skype.android.video.hw.codec.encoder.control.VideoEncoderControlOmx;
import com.skype.android.video.hw.codec.encoder.control.VideoEncoderControlQcAfterNExtension;
import com.skype.android.video.hw.codec.encoder.control.VideoEncoderControlQcExtension;
import com.skype.android.video.hw.format.VideoFormat;
import com.skype.android.video.hw.utils.DebugUtils;
import com.skype.android.video.hw.utils.Log;

public abstract class AbstractVideoEncoder extends AbstractMediaCodec {
    private AbstractVideoEncoderControl encControl = null;
    private final Bundle setBitrateRequest = new Bundle();
    private final Bundle suspendRequest = new Bundle();
    private final Bundle syncFrameRequest = new Bundle();

    public static class APINotImplementedException extends Exception {
        private static final long serialVersionUID = 1;
        private String failedApiName;

        public APINotImplementedException(String failedApiName, String detailMessage) {
            super(detailMessage);
            this.failedApiName = failedApiName;
        }

        public String getFailedApiName() {
            return this.failedApiName;
        }
    }

    protected AbstractVideoEncoder(String codecName) {
        super(codecName, true);
        if (this.useQCExtension) {
            this.encControl = new VideoEncoderControlQcExtension(this.mediaCodec);
        } else if (this.useQCAfterNougatExtension) {
            this.encControl = new VideoEncoderControlQcAfterNExtension(this.mediaCodec);
        } else if (this.omxWrapper != null) {
            this.encControl = new VideoEncoderControlOmx(this.mediaCodec, this.omxWrapper);
        } else {
            this.encControl = new AbstractVideoEncoderControl(this.mediaCodec, null) {
                public void setRcFrameRate(float fps) throws APINotImplementedException {
                    LogAndThrowNotImplemented(getClass().getSimpleName() + '#' + DebugUtils.getMethodName());
                }

                public void useLTRFrame(int useLTRFrameIdxBitMap) throws APINotImplementedException {
                    LogAndThrowNotImplemented(getClass().getSimpleName() + '#' + DebugUtils.getMethodName());
                }

                public void setQp(int qp) throws APINotImplementedException {
                    LogAndThrowNotImplemented(getClass().getSimpleName() + '#' + DebugUtils.getMethodName());
                }

                public void setNumTempLayers(int numTempLayers) throws APINotImplementedException {
                    LogAndThrowNotImplemented(getClass().getSimpleName() + '#' + DebugUtils.getMethodName());
                }

                public void setBaseLayerPID(int baseLayerPID) throws APINotImplementedException {
                    LogAndThrowNotImplemented(getClass().getSimpleName() + '#' + DebugUtils.getMethodName());
                }

                public void markLtrFrame(int longTermFrameIdx) throws APINotImplementedException {
                    LogAndThrowNotImplemented(getClass().getSimpleName() + '#' + DebugUtils.getMethodName());
                }

                public void cleanFrameParams() throws APINotImplementedException {
                    LogAndThrowNotImplemented(getClass().getSimpleName() + '#' + DebugUtils.getMethodName());
                }

                public void markParamsTimestamp(long timestamp) throws APINotImplementedException {
                    LogAndThrowNotImplemented(getClass().getSimpleName() + '#' + DebugUtils.getMethodName());
                }

                public void configureFrameParams() throws APINotImplementedException {
                    LogAndThrowNotImplemented(getClass().getSimpleName() + '#' + DebugUtils.getMethodName());
                }

                private void LogAndThrowNotImplemented(String methodName) throws APINotImplementedException {
                    if (Log.isLoggable(Commons.TAG, 4)) {
                        Log.i(Commons.TAG, methodName + " is not implemented (neither QC nor OMX extensions are available)");
                    }
                    throw new APINotImplementedException(methodName, "neither QC nor OMX extensions are available");
                }
            };
        }
    }

    public void setRcFrameRate(float fps) throws APINotImplementedException {
        if (isOpen()) {
            this.encControl.setRcFrameRate(fps);
            return;
        }
        throw new IllegalStateException("closed");
    }

    @SuppressLint({"NewApi"})
    public void setBitrate(int bitrate) {
        if (!isOpen()) {
            throw new IllegalStateException("closed");
        } else if (bitrate > Integer.MAX_VALUE || bitrate < 0) {
            throw new IllegalArgumentException("bitrate out of range");
        } else {
            this.setBitrateRequest.putInt("video-bitrate", bitrate);
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Setting " + this.setBitrateRequest + " on " + this.mediaCodec);
            }
            this.mediaCodec.setParameters(this.setBitrateRequest);
        }
    }

    @SuppressLint({"NewApi"})
    public void requestSyncFrame() {
        if (isOpen()) {
            this.syncFrameRequest.putInt("request-sync", 0);
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Setting " + this.syncFrameRequest + " on " + this.mediaCodec);
            }
            this.mediaCodec.setParameters(this.syncFrameRequest);
            return;
        }
        throw new IllegalStateException("closed");
    }

    @SuppressLint({"NewApi"})
    public void suspend() {
        if (isOpen()) {
            this.suspendRequest.putInt("drop-input-frames", 1);
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Setting " + this.suspendRequest + " on " + this.mediaCodec);
            }
            this.mediaCodec.setParameters(this.suspendRequest);
            return;
        }
        throw new IllegalStateException("closed");
    }

    public void setQp(int qp) throws APINotImplementedException {
        if (!isOpen()) {
            throw new IllegalStateException("closed");
        } else if (qp < 0 || qp > 51) {
            throw new IllegalArgumentException("QP out of range");
        } else {
            this.encControl.setQp(qp);
        }
    }

    public void setNumTempLayers(int numTempLayers) throws APINotImplementedException {
        if (!isOpen()) {
            throw new IllegalStateException("closed");
        } else if (numTempLayers > 6 || numTempLayers <= 0) {
            throw new IllegalArgumentException("numTempLayers out of range [1;6]");
        } else {
            this.encControl.setNumTempLayers(numTempLayers);
        }
    }

    public void setBaseLayerPID(int baseLayerPID) throws APINotImplementedException {
        if (!isOpen()) {
            throw new IllegalStateException("closed");
        } else if (baseLayerPID < 0 || baseLayerPID > 63) {
            throw new IllegalArgumentException("baseLayerPID out of range [1;6]");
        } else {
            this.encControl.setBaseLayerPID(baseLayerPID);
        }
    }

    public void markLtrFrame(int longTermFrameIdx) throws APINotImplementedException {
        if (!isOpen()) {
            throw new IllegalStateException("closed");
        } else if (longTermFrameIdx < 0 || longTermFrameIdx > 4) {
            throw new IllegalArgumentException("longTermFrameIdx out of range [0;4]");
        } else {
            this.encControl.markLtrFrame(longTermFrameIdx);
        }
    }

    public void useLTRFrame(int useLTRFrameIdxBitMap) throws APINotImplementedException {
        if (!isOpen()) {
            throw new IllegalStateException("closed");
        } else if (useLTRFrameIdxBitMap < 0 || useLTRFrameIdxBitMap > 63) {
            throw new IllegalArgumentException("useLTRFrameIdxBitMap out of range [0;63]");
        } else {
            this.encControl.useLTRFrame(useLTRFrameIdxBitMap);
        }
    }

    public void cleanFrameParams() throws APINotImplementedException {
        if (this.useQCExtension || this.useQCAfterNougatExtension) {
            this.encControl.cleanFrameParams();
        }
    }

    public void markParamsTimestamp(long timestamp) throws APINotImplementedException {
        if (this.useQCExtension || this.useQCAfterNougatExtension) {
            this.encControl.markParamsTimestamp(timestamp);
        }
    }

    public void configureFrameParams() throws APINotImplementedException {
        if (this.useQCExtension || this.useQCAfterNougatExtension) {
            this.encControl.configureFrameParams();
        }
    }

    protected void doConfigureMediaFormat(MediaFormat mediaFormat, VideoFormat format) {
        this.encControl.doConfigureMediaFormat(mediaFormat, format);
    }

    protected void doConfigureDynamic(VideoFormat format) {
        int numTemporalLayers;
        if (format.getSvcAlways()) {
            numTemporalLayers = Math.min(Math.max(format.getNumLayers(), 1), 2);
        } else {
            numTemporalLayers = 0;
        }
        int baseLayerPID = Math.min(Math.max(format.getBaseLayerPriorityId(), 0), 63);
        try {
            this.encControl.setNumTempLayers(numTemporalLayers);
            this.encControl.setBaseLayerPID(baseLayerPID);
        } catch (APINotImplementedException e) {
            e.printStackTrace();
        }
    }

    protected int doGetMediaCodecFlags() {
        return 1;
    }
}
