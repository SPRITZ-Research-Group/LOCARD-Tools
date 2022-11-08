package com.skype.android.video.hw.extension.decoder;

public interface VideoDecoderExtension {
    int configure(long j, int i, long j2, int i2);

    int flushFrames(long j, long j2);

    int init(String str);

    int processFrame(long j, long j2, long j3, int i, long j4);

    int uninit();
}
