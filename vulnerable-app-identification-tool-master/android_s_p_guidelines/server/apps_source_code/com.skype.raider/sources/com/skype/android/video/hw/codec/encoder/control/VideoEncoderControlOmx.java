package com.skype.android.video.hw.codec.encoder.control;

import android.media.MediaCodec;
import android.media.MediaFormat;
import com.skype.android.video.hw.codec.OmxWrapper;
import com.skype.android.video.hw.codec.encoder.AbstractVideoEncoder.APINotImplementedException;
import com.skype.android.video.hw.format.VideoFormat;
import com.skype.android.video.hw.utils.DebugUtils;

public final class VideoEncoderControlOmx extends AbstractVideoEncoderControl {
    private final OmxWrapper omxWrapper;

    public VideoEncoderControlOmx(MediaCodec mediaCodec, OmxWrapper omxWrapper) {
        super(mediaCodec, omxWrapper);
        this.omxWrapper = omxWrapper;
    }

    public final void setRcFrameRate(float fps) throws APINotImplementedException {
        if (this.omxWrapper.isNodeIdKnown()) {
            this.omxWrapper.setRcFrameRate(fps);
            return;
        }
        throw new APINotImplementedException(getClass().getSimpleName() + '#' + DebugUtils.getMethodName(), "(node ID is knot known)");
    }

    public final void setQp(int qp) throws APINotImplementedException {
        if (this.omxWrapper.isNodeIdKnown()) {
            this.omxWrapper.setQp(qp);
            return;
        }
        throw new APINotImplementedException(getClass().getSimpleName() + '#' + DebugUtils.getMethodName(), "(node ID is knot known)");
    }

    public final void setNumTempLayers(int numTempLayers) throws APINotImplementedException {
        if (this.omxWrapper.isNodeIdKnown()) {
            this.omxWrapper.setNumTempLayers(numTempLayers);
            return;
        }
        throw new APINotImplementedException(getClass().getSimpleName() + '#' + DebugUtils.getMethodName(), "(node ID is knot known)");
    }

    public final void setBaseLayerPID(int baseLayerPID) throws APINotImplementedException {
        if (this.omxWrapper.isNodeIdKnown()) {
            this.omxWrapper.setBaseLayerPID(baseLayerPID);
            return;
        }
        throw new APINotImplementedException(getClass().getSimpleName() + '#' + DebugUtils.getMethodName(), "(node ID is knot known)");
    }

    public final void markLtrFrame(int longTermFrameIdx) throws APINotImplementedException {
        if (this.omxWrapper.isNodeIdKnown()) {
            this.omxWrapper.markLtrFrame(longTermFrameIdx);
            return;
        }
        throw new APINotImplementedException(getClass().getSimpleName() + '#' + DebugUtils.getMethodName(), "(node ID is knot known)");
    }

    public final void useLTRFrame(int useLTRFrameIdxBitMap) throws APINotImplementedException {
        if (this.omxWrapper.isNodeIdKnown()) {
            this.omxWrapper.useLTRFrame(useLTRFrameIdxBitMap);
            return;
        }
        throw new APINotImplementedException(getClass().getSimpleName() + '#' + DebugUtils.getMethodName(), "(node ID is knot known)");
    }

    public final void doConfigureMediaFormat(MediaFormat mediaFormat, VideoFormat format) {
        super.doConfigureMediaFormat(mediaFormat, format);
    }

    public final void cleanFrameParams() throws APINotImplementedException {
    }

    public final void markParamsTimestamp(long timestamp) throws APINotImplementedException {
    }

    public final void configureFrameParams() throws APINotImplementedException {
    }
}
