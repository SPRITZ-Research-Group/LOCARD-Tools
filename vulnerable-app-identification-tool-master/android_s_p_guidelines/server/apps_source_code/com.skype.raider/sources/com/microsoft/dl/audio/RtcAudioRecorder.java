package com.microsoft.dl.audio;

import com.microsoft.dl.utils.Log;
import java.nio.ByteBuffer;

public class RtcAudioRecorder {
    public static final long ERROR_INVALID_PARAM = -1;
    public static final long ERROR_NO_DATA = -2;
    private static long a = 0;
    private static long b = 0;

    private static native long Create(long j);

    private static native long ReadFrame(long j);

    private static native long ReadFrameByteBuffer(long j, ByteBuffer byteBuffer);

    private static native int Release(long j, long j2);

    private static native int SetOutputFormat(long j, int i, int i2, int i3, int i4, int i5);

    private static native int SetStreamPosition(long j, int i);

    private static native int Start(long j);

    private static native int Stop(long j);

    public synchronized long create(long nativeExtension) {
        b = nativeExtension;
        if (nativeExtension != 0) {
            a = Create(b);
        }
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "create RtcAudioRecorder: (nativeInstance=" + a + ", nativeAudioManagerExtension=" + b + ")");
        }
        return a;
    }

    public synchronized void release() {
        int ret = 0;
        if (!(b == 0 || a == 0)) {
            ret = Release(b, a);
        }
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "release RtcAudioRecorder: (nativeInstance=" + a + ", nativeAudioManagerExtension=" + b + ", return=" + ret + ")");
        }
        b = 0;
        a = 0;
    }

    public synchronized int setStreamPosition(int streamPosition) {
        int SetStreamPosition;
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "setStreamPosition: (nativeInstance=" + a + ", streamPosition=" + streamPosition + ")");
        }
        if (a != 0) {
            SetStreamPosition = SetStreamPosition(a, streamPosition);
        } else {
            SetStreamPosition = -1;
        }
        return SetStreamPosition;
    }

    public synchronized int setOutputFormat(int formatTag, int samplingRate, int channels, int bitsPerSample, int samplesPerFrame) {
        int SetOutputFormat;
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "setOutputFormat: (nativeInstance=" + a + ", samplingRate=" + samplingRate + ", channels=" + channels + ", bitsPerSample=" + bitsPerSample + ", samplesPerFrame=" + samplesPerFrame + ")");
        }
        if (a != 0) {
            SetOutputFormat = SetOutputFormat(a, formatTag, samplingRate, channels, bitsPerSample, samplesPerFrame);
        } else {
            SetOutputFormat = -1;
        }
        return SetOutputFormat;
    }

    public synchronized int start() {
        int Start;
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "audio recorder start");
        }
        if (a != 0) {
            Start = Start(a);
        } else {
            Start = -1;
        }
        return Start;
    }

    public synchronized int stop() {
        int Stop;
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "audio recorder stop");
        }
        if (a != 0) {
            Stop = Stop(a);
        } else {
            Stop = -1;
        }
        return Stop;
    }

    public synchronized long readFrame() {
        long j = 0;
        synchronized (this) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.d(PackageInfo.TAG, "audio recorder readFrame");
            }
            if (a != 0) {
                j = ReadFrame(a);
            }
        }
        return j;
    }

    public synchronized long readFrame(ByteBuffer audioBuffer) {
        long retVal;
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.d(PackageInfo.TAG, "audio recorder readFrame(ByteBuffer, int)");
        }
        if (a == 0 || audioBuffer == null) {
            Log.e(PackageInfo.TAG, "audio recorder readFrame(ByteBuffer, int), invalid parameters");
            retVal = -1;
        } else {
            retVal = ReadFrameByteBuffer(a, audioBuffer);
            if (retVal < 0) {
                Log.d(PackageInfo.TAG, "audio recorder readFrame(ByteBuffer, int), error in frame reading");
                retVal = -2;
            }
        }
        return retVal;
    }
}
