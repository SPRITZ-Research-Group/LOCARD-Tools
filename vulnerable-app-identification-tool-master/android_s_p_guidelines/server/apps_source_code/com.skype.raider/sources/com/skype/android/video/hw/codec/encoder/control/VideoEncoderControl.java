package com.skype.android.video.hw.codec.encoder.control;

import android.media.MediaFormat;
import com.skype.android.video.hw.codec.encoder.AbstractVideoEncoder.APINotImplementedException;
import com.skype.android.video.hw.format.VideoFormat;

interface VideoEncoderControl {
    void cleanFrameParams() throws APINotImplementedException;

    void configureFrameParams() throws APINotImplementedException;

    void doConfigureMediaFormat(MediaFormat mediaFormat, VideoFormat videoFormat);

    void markLtrFrame(int i) throws APINotImplementedException;

    void markParamsTimestamp(long j) throws APINotImplementedException;

    void setBaseLayerPID(int i) throws APINotImplementedException;

    void setBitrate(int i);

    void setNumTempLayers(int i) throws APINotImplementedException;

    void setQp(int i) throws APINotImplementedException;

    void setRcFrameRate(float f) throws APINotImplementedException;

    void suspend();

    void useLTRFrame(int i) throws APINotImplementedException;
}
