package com.skype.android.video.hw.extension.decoder;

import android.media.MediaFormat;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.codec.IpcOmxWrapper;
import com.skype.android.video.hw.codec.OmxWrapper;
import com.skype.android.video.hw.codec.decoder.VideoSampleDecoder;
import com.skype.android.video.hw.extension.JniCodecUtils;
import com.skype.android.video.hw.format.Capabilities;
import com.skype.android.video.hw.format.ColorFormat;
import com.skype.android.video.hw.format.H264Level;
import com.skype.android.video.hw.format.H264Profile;
import com.skype.android.video.hw.format.Resolution;
import com.skype.android.video.hw.format.VideoFormat;
import com.skype.android.video.hw.frame.InputFrame;
import com.skype.android.video.hw.frame.OutputFrame;
import com.skype.android.video.hw.frame.OutputFrame.FrameBuffer;
import com.skype.android.video.hw.utils.CodecUtils;
import com.skype.android.video.hw.utils.DebugUtils;
import com.skype.android.video.hw.utils.EncoderAttributes;
import com.skype.android.video.hw.utils.EncoderAttributes.AttributeException;
import com.skype.android.video.hw.utils.Log;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class VideoSampleDecoderExtension implements VideoDecoderExtension {
    private static final int COMPRESSION_RATIO_ESTIMATION = 10;
    private static final int DEFAULT_FRAMERATE = 30;
    private static final H264Level DEFAULT_H264_LEVEL = H264Level.L31;
    private static final H264Profile DEFAULT_H264_PROFILE = H264Profile.BASELINE;
    private static final int DEFAULT_I_FRAME_INTERVAL = 420;
    private static final int INPUT_FRAME_POOL_SIZE = 10;
    private static final int MAX_CONSECUTIVE_TIMEOUT_COUNT = 4;
    private static final int MAX_TOTAL_TIMEOUT_COUNT = 3;
    private static final int OUTPUT_FRAME_POOL_SIZE = 10;
    private int consecutiveTimeoutCount;
    private VideoSampleDecoder decoder;
    private final String decoderName;
    boolean firstFrame;
    private long initialTimestamp;
    boolean isRunning;
    private long lastTs;
    private final Object lock;
    private int rawFrameSize;
    private int totaltimeoutCount;
    VideoFormat videoFormat;

    public static class Factory implements VideoDecoderExtensionFactory {
        public VideoSampleDecoderExtension create(int decoderIndex) {
            return new VideoSampleDecoderExtension(decoderIndex);
        }
    }

    private static native void frameReady(long j, long j2, int i, ByteBuffer byteBuffer, int i2, int i3, long j3, boolean z, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11);

    private VideoSampleDecoderExtension(int decoderIndex) {
        this.lock = new Object();
        this.firstFrame = false;
        this.isRunning = false;
        this.consecutiveTimeoutCount = 0;
        this.totaltimeoutCount = 0;
        this.lastTs = 0;
        this.decoderName = CodecUtils.enumDecoders()[decoderIndex];
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getCanonicalName() + " with h/w decoder #" + decoderIndex + " (" + this.decoderName + ") created");
        }
    }

    public static VideoSampleDecoderExtension createStatic(int decoderIndex) {
        return new VideoSampleDecoderExtension(decoderIndex);
    }

    public static boolean getCapabilities(String decoderName, long capabilitiesNativeObj) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, VideoSampleDecoderExtension.class.getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        try {
            passCapabilitiesToNative(capabilitiesNativeObj, CodecUtils.getDecoderCapabilities(decoderName));
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, VideoSampleDecoderExtension.class.getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
            return true;
        } catch (RuntimeException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Unexpected exception caught", e);
            }
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, VideoSampleDecoderExtension.class.getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed");
            }
            return false;
        }
    }

    private static void passCapabilitiesToNative(long capabilitiesNativeObj, Capabilities capabilities) {
        int i;
        int[] profiles = new int[capabilities.getProfiles().size()];
        int i2 = 0;
        for (H264Profile p : capabilities.getProfiles()) {
            i = i2 + 1;
            profiles[i2] = p.getSliqValue().intValue();
            i2 = i;
        }
        int maxLevel = -1;
        for (H264Level l : capabilities.getLevels()) {
            maxLevel = Math.max(maxLevel, l.getSliqValue().intValue());
        }
        int[] colorFormats = new int[capabilities.getColorFormats().size()];
        i2 = 0;
        for (ColorFormat cf : capabilities.getColorFormats()) {
            i = i2 + 1;
            colorFormats[i2] = cf.getSliqValue().intValue();
            i2 = i;
        }
        Buffer nativeOMXCapability = null;
        MediaFormat extCaps;
        if (capabilities.isQCExtensionSupported()) {
            Object obj;
            extCaps = capabilities.getExtCapabilities();
            String str = Commons.TAG;
            StringBuilder stringBuilder = new StringBuilder("getExtCapabilities: ");
            if (extCaps == null) {
                obj = "null";
            } else {
                MediaFormat obj2 = extCaps;
            }
            Log.d(str, stringBuilder.append(obj2).toString());
            if (extCaps != null) {
                Log.d(Commons.TAG, "!!!!!!!!!!!!!!");
                Log.d(Commons.TAG, "getVersion() =>                     " + extCaps.getString("vt-version"));
                Log.d(Commons.TAG, "isLowLatencySupported() =>          " + extCaps.getString("vt-low-latency"));
                Log.d(Commons.TAG, "getMaxInstances() =>                " + extCaps.getString("vt-max-instances"));
                Log.d(Commons.TAG, "getMaxLevel() =>                    " + extCaps.getString("vt-max-level"));
                Log.d(Commons.TAG, "getMaxMacroblockProcessingRate() => " + extCaps.getString("vt-max-macroblock-processing-rate"));
            }
        } else if (capabilities.isQCAfterNougatExtensionsSupported()) {
            extCaps = capabilities.getExtCapabilities();
            String str2 = Commons.TAG;
            StringBuilder stringBuilder2 = new StringBuilder("getExtCapabilities: ");
            if (extCaps == null) {
                extCaps = "null";
            }
            Log.d(str2, stringBuilder2.append(extCaps).toString());
        } else {
            OmxWrapper ipcOmxWrapper = new IpcOmxWrapper(capabilities.getCodecName(), capabilities.getCodecName());
            ipcOmxWrapper.connectForQueriesOnly();
            if (ipcOmxWrapper.isNodeIdKnown()) {
                Log.i(Commons.TAG, "!!! Got nodeId: " + ipcOmxWrapper.getNodeId());
                nativeOMXCapability = ipcOmxWrapper.getDecCapabilityBuffer();
            }
            try {
                ipcOmxWrapper.close();
            } catch (Throwable e) {
                if (Log.isLoggable(Commons.TAG, 6)) {
                    Log.e(Commons.TAG, "Unexpected IOexception caught", e);
                }
            }
        }
        JniCodecUtils.returnCapabilitiesBuffer(capabilitiesNativeObj, capabilities.getCodecName(), profiles, maxLevel, colorFormats, nativeOMXCapability, 0, false, 0, 0, 0, 0, 0, 0, 0, false, 0, false);
    }

    public int configure(long spsAddr, int spsLen, long ppsAddr, int ppsLen) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        ByteBuffer sps = ByteBuffer.allocateDirect(spsLen);
        ByteBuffer pps = ByteBuffer.allocateDirect(ppsLen);
        ByteBuffer spspps = ByteBuffer.allocateDirect(spsLen + ppsLen);
        this.consecutiveTimeoutCount = 0;
        this.totaltimeoutCount = 0;
        JniCodecUtils.fillInputFrameBuffer(spsAddr, sps, spsLen, false);
        JniCodecUtils.fillInputFrameBuffer(ppsAddr, pps, ppsLen, false);
        sps.position(0);
        spspps.put(sps);
        pps.position(0);
        spspps.put(pps);
        spspps.position(0);
        try {
            if (this.isRunning) {
                this.decoder.setReconfigFlag(true);
                this.decoder.stop();
            }
            this.videoFormat.setSpsPps(spspps);
            this.decoder.configure(null, this.videoFormat, false);
            this.decoder.start();
            this.isRunning = true;
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
            this.firstFrame = true;
            return 0;
        } catch (Exception e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Exception caught!", e);
            }
            this.isRunning = false;
            return -1;
        }
    }

    public int init(String attr) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        try {
            this.videoFormat = createDecoderVideoFormat(new EncoderAttributes(attr));
            this.rawFrameSize = ((this.videoFormat.getMaxResolution().getWidth() * this.videoFormat.getMaxResolution().getHeight()) * this.videoFormat.getColorFormat().getBitsPerPixel()) / 8;
            this.initialTimestamp = 132;
            this.lastTs = 0;
            this.isRunning = false;
            this.decoder = new VideoSampleDecoder(this.decoderName);
            if (!Log.isLoggable(Commons.TAG, 3)) {
                return 0;
            }
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            return 0;
        } catch (AttributeException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Illegal attribute value", e);
            }
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_INCORRECT_PARAM");
            }
            return -9;
        } catch (RuntimeException e2) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Unexpected exception caught", e2);
            }
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_UNKNOWN");
            }
            return -1;
        }
    }

    public int uninit() {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        try {
            if (this.decoder != null) {
                this.decoder.stop();
                this.isRunning = false;
                this.decoder.close();
                this.decoder = null;
            }
            if (!Log.isLoggable(Commons.TAG, 3)) {
                return 0;
            }
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            return 0;
        } catch (RuntimeException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Unexpected exception caught", e);
            }
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_UNKNOWN");
            }
            this.isRunning = false;
            return -1;
        }
    }

    public int processFrame(long timeoutUs, long nativeCallbackObj, long encodedData, int dataLen, long ts) {
        int ret;
        synchronized (this.lock) {
            ret = 0;
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
            }
            if (this.isRunning) {
                try {
                    InputFrame inputFrame = this.decoder.getInputFrame(timeoutUs);
                    if (Log.isLoggable(Commons.TAG, 3)) {
                        Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() deocder.getInputFrame() done");
                    }
                    inputFrame.setTimestamp(this.initialTimestamp);
                    this.initialTimestamp += 33333;
                    inputFrame.setSize(dataLen);
                    JniCodecUtils.fillInputFrameBuffer(encodedData, inputFrame.getData(), dataLen, false);
                    if (Log.isLoggable(Commons.TAG, 3)) {
                        Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() JniCodecUtils.fillInputFrameBuffer done");
                    }
                    if (this.firstFrame) {
                        timeoutUs *= 10;
                    }
                    OutputFrame outputFrame = this.decoder.decode(inputFrame, timeoutUs);
                    if (outputFrame.isTimedOut()) {
                        this.consecutiveTimeoutCount++;
                        this.totaltimeoutCount++;
                    } else {
                        this.consecutiveTimeoutCount = 0;
                    }
                    if (Log.isLoggable(Commons.TAG, 3)) {
                        Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() decoder.decode() done !totalTimeoutCount " + this.totaltimeoutCount);
                    }
                    this.firstFrame = false;
                    this.lastTs = ts;
                    frameReady(nativeCallbackObj, ts, outputFrame);
                    outputFrame.close();
                    if (this.consecutiveTimeoutCount >= 4) {
                        return -23;
                    }
                    if (this.totaltimeoutCount >= 3) {
                        Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() start to flush frames");
                        int maxNumOfFlushframe = this.totaltimeoutCount;
                        for (int i = 0; i < maxNumOfFlushframe; i++) {
                            OutputFrame abandonOutputFrame = this.decoder.flushOneFrame(timeoutUs);
                            if (abandonOutputFrame.isTimedOut()) {
                                this.consecutiveTimeoutCount++;
                            }
                            if (!(abandonOutputFrame == null || abandonOutputFrame.isTimedOut() || abandonOutputFrame.isEndOfStream())) {
                                frameReady(nativeCallbackObj, ts, abandonOutputFrame);
                                this.totaltimeoutCount--;
                                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() flush succeeded, now " + this.totaltimeoutCount + " frames remain");
                            }
                            abandonOutputFrame.close();
                        }
                    }
                    if (Log.isLoggable(Commons.TAG, 3)) {
                        Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
                    }
                } catch (RuntimeException e) {
                    if (Log.isLoggable(Commons.TAG, 6)) {
                        Log.e(Commons.TAG, "Unexpected exception caught", e);
                    }
                    if (Log.isLoggable(Commons.TAG, 3)) {
                        Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_ANDROID_FAILED");
                    }
                    this.isRunning = false;
                    ret = -23;
                }
            } else {
                return -1;
            }
        }
        return ret;
    }

    public int flushFrames(long nativeCallbackObj, long timeoutUs) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        if (this.isRunning) {
            this.decoder.signalEOS(this.initialTimestamp, timeoutUs);
            boolean stop = false;
            do {
                OutputFrame outputFrame = this.decoder.flushOneFrame(timeoutUs);
                if (outputFrame == null || outputFrame.isTimedOut() || outputFrame.isEndOfStream()) {
                    stop = true;
                    continue;
                } else {
                    outputFrame.close();
                    continue;
                }
            } while (!stop);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
        } else {
            Log.e(Commons.TAG, "can't flushFrame because decoder is not running!");
        }
        return 0;
    }

    private static VideoFormat createDecoderVideoFormat(EncoderAttributes attributes) throws AttributeException {
        VideoFormat format = new VideoFormat();
        format.setResolution(new Resolution(1920, 1080));
        format.setMaxResolution(new Resolution(1920, 1080));
        format.setColorFormat(ColorFormat.I420);
        return format;
    }

    private void frameReady(long nativeCallbackObj, long ts, OutputFrame outputFrame) {
        int cropL = 0;
        int cropT = 0;
        int cropR = 0;
        int cropB = 0;
        if (this.decoder.getMediaFormat().containsKey("crop-left")) {
            cropL = this.decoder.getMediaFormat().getInteger("crop-left");
            cropT = this.decoder.getMediaFormat().getInteger("crop-top");
            cropR = this.decoder.getMediaFormat().getInteger("crop-right");
            cropB = this.decoder.getMediaFormat().getInteger("crop-bottom");
        }
        while (outputFrame.hasBuffers()) {
            int i;
            FrameBuffer frameBuffer = outputFrame.popBuffer();
            int stride = this.decoder.getMediaFormat().getInteger("stride");
            if (stride % 16 == 0) {
                i = 0;
            } else {
                try {
                    i = 16 - (stride % 16);
                } catch (Throwable th) {
                    frameBuffer.close();
                }
            }
            stride += i;
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() outputFrame.popBuffer() done colorformat " + this.decoder.getMediaFormat().getInteger("color-format"));
            }
            frameReady(nativeCallbackObj, outputFrame.getNativeLockingObj(), frameBuffer.getId(), frameBuffer.getData(), frameBuffer.getOffset(), frameBuffer.getSize(), ts, outputFrame.isTimedOut(), this.decoder.getMediaFormat().getInteger("width"), this.decoder.getMediaFormat().getInteger("height"), ColorFormat.fromOmx(this.decoder.getMediaFormat().getInteger("color-format")).getSliqValue().intValue(), stride, cropL, cropT, cropR, cropB);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() frameReady() native call done");
            }
            frameBuffer.close();
        }
    }
}
