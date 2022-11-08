package com.skype.android.video.hw.extension.encoder;

public interface VideoEncoderExtension {
    int cleanFrameParams();

    int configureFrameParams();

    int dropNextNFrames(int i);

    int enhanceROI(int i, int i2, int i3, int i4, int i5);

    int flushFrames();

    int forceIdr();

    int getMaxQp();

    int h264SetQp(int i);

    int hintRcFrameRate(float f, float f2);

    int init(String str, boolean z);

    int markLTRFrame(int i);

    int markParamsTimestamp(long j);

    int processFrame(long j, long j2, long j3, long j4, boolean z);

    int restartEncoder(int i, float f);

    int setBaseLayerPID(int i);

    int setBitrate(int i);

    int setFPS(float f);

    int setLTRBufferControl(int i, boolean z);

    int setMaxNumRefFrames(int i);

    int setNumTempLayers(int i);

    int setProvideMADMetric(boolean z);

    int setSliceSize(int i, int i2);

    int uninit(boolean z);

    int useLTRFrame(int i, boolean z);
}
