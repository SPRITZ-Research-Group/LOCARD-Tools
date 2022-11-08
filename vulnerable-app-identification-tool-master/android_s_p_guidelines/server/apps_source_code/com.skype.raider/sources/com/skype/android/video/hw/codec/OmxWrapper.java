package com.skype.android.video.hw.codec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import java.io.Closeable;
import java.nio.Buffer;

public interface OmxWrapper extends Closeable {
    int configureDecoderLowLatency();

    int configureEncoder(MediaFormat mediaFormat, int i, int i2, int i3, int i4, int i5, boolean z);

    int connectForQueriesOnly();

    MediaCodec createTracked();

    long getDecCapability();

    Buffer getDecCapabilityBuffer();

    long getDriverVersion();

    long getEncCapability();

    Buffer getEncCapabilityBuffer();

    int getNodeId();

    boolean isNodeIdKnown();

    boolean isQpSupported();

    int markLtrFrame(int i);

    int queryDriverVersion();

    int queryDriverVersionAndCheckVerSystem();

    int setBaseLayerPID(int i);

    int setDriverVersion(long j);

    int setNumTempLayers(int i);

    int setQp(int i);

    int setRcFrameRate(float f);

    int useLTRFrame(int i);
}
