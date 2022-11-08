package com.skype.android.video.hw.extension.encoder;

import android.os.Build.VERSION;
import com.adjust.sdk.Constants;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.HWFeatureSelectiveFields;
import com.skype.android.video.hw.codec.encoder.AbstractVideoEncoder;
import com.skype.android.video.hw.codec.encoder.AbstractVideoEncoder.APINotImplementedException;
import com.skype.android.video.hw.codec.encoder.camera.SurfaceObserver;
import com.skype.android.video.hw.format.ColorFormat;
import com.skype.android.video.hw.format.H264Level;
import com.skype.android.video.hw.format.H264Profile;
import com.skype.android.video.hw.format.RateControlMode;
import com.skype.android.video.hw.format.Resolution;
import com.skype.android.video.hw.format.VideoFormat;
import com.skype.android.video.hw.frame.OutputFrame;
import com.skype.android.video.hw.frame.OutputFrame.FrameBuffer;
import com.skype.android.video.hw.utils.CodecUtils;
import com.skype.android.video.hw.utils.DebugUtils;
import com.skype.android.video.hw.utils.EncoderAttributes;
import com.skype.android.video.hw.utils.EncoderAttributes.AttributeException;
import com.skype.android.video.hw.utils.Log;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

public abstract class AbstractVideoEncoderExtension<Encoder extends AbstractVideoEncoder> implements VideoEncoderExtension {
    private static final int COMPRESSION_RATIO_ESTIMATION = 100;
    private static final int DEFAULT_FRAMERATE = 30;
    private static final H264Level DEFAULT_H264_LEVEL = H264Level.L31;
    private static final H264Profile DEFAULT_H264_PROFILE = H264Profile.BASELINE;
    private static final int DEFAULT_I_FRAME_INTERVAL = 420;
    private static final int OUTPUT_FRAME_POOL_SIZE = 2;
    private int consecutiveTimeOut;
    private Encoder encoder;
    private final String encoderName;
    private long initialTimestamp;
    private boolean isFastAsyncMode = false;
    private boolean isFirstFrame = true;
    private int rawFrameSize;
    private WeakReference<SurfaceObserver> weakRefSurfaceObserver;

    private static native void bitstreamReady(long j, boolean z, ByteBuffer byteBuffer, int i, int i2, long j2, boolean z2, boolean z3, boolean z4);

    protected abstract Encoder doCreateEncoder(String str);

    protected abstract OutputFrame doEncodeFrame(long j, long j2, long j3, boolean z);

    protected AbstractVideoEncoderExtension(int encoderIndex) {
        this.encoderName = CodecUtils.enumEncoders()[encoderIndex];
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getCanonicalName() + " with h/w encoder #" + encoderIndex + " (" + this.encoderName + ") created");
        }
    }

    public static boolean getCapabilities(String encoderName, long capabilitiesNativeObj) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, AbstractVideoEncoderExtension.class.getSimpleName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        if (VERSION.SDK_INT >= 19) {
            try {
                if (VERSION.SDK_INT >= 24) {
                    boolean z = CodecUtils.getHWMode().contains(HWFeatureSelectiveFields.Mediacodec_Only) && CodecUtils.getHWMode().size() == 1;
                    if (z) {
                        CodecUtils.getEncoderCapabilities(encoderName).getProfiles().clear();
                        CodecUtils.getEncoderCapabilities(encoderName).getProfiles().add(H264Profile.BASELINE);
                    }
                }
                passCapabilitiesToNative(capabilitiesNativeObj, CodecUtils.getEncoderCapabilities(encoderName));
                if (Log.isLoggable(Commons.TAG, 3)) {
                    Log.d(Commons.TAG, AbstractVideoEncoderExtension.class.getSimpleName() + '#' + DebugUtils.getMethodName() + "() succeeded");
                }
                return true;
            } catch (RuntimeException e) {
                if (Log.isLoggable(Commons.TAG, 6)) {
                    Log.e(Commons.TAG, "Unexpected exception caught", e);
                }
                if (!Log.isLoggable(Commons.TAG, 3)) {
                    return false;
                }
                Log.d(Commons.TAG, AbstractVideoEncoderExtension.class.getSimpleName() + '#' + DebugUtils.getMethodName() + "() failed");
                return false;
            }
        } else if (!Log.isLoggable(Commons.TAG, 3)) {
            return false;
        } else {
            Log.d(Commons.TAG, AbstractVideoEncoderExtension.class.getSimpleName() + '#' + DebugUtils.getMethodName() + "() don't query capability on API level " + VERSION.SDK_INT);
            return false;
        }
    }

    protected int initInternal(EncoderAttributes attributes, boolean isReinitializing, boolean isFastAsyncMode, Object userData) throws AttributeException {
        WeakReference weakReference;
        boolean checkDriverVersion = true;
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        this.initialTimestamp = (long) attributes.getInteger("ts").intValue();
        if (userData == null) {
            weakReference = null;
        } else {
            weakReference = new WeakReference((SurfaceObserver) userData);
        }
        this.weakRefSurfaceObserver = weakReference;
        VideoFormat videoFormat = getVideoFormatFromAttributes(attributes);
        Log.i(Commons.TAG, "video format " + videoFormat.toString());
        if (CodecUtils.getEncoderCapabilities(this.encoderName).getProfiles().contains(videoFormat.getProfile())) {
            this.rawFrameSize = ((videoFormat.getResolution().getWidth() * videoFormat.getResolution().getHeight()) * videoFormat.getColorFormat().getBitsPerPixel()) / 8;
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Creating encoder " + this.encoderName + " with " + videoFormat + " and " + userData);
            }
            this.isFirstFrame = true;
            if (!isReinitializing) {
                this.encoder = doCreateEncoder(this.encoderName);
            }
            if (isReinitializing || 1 != attributes.getInteger("check-android-hw-api-version", 0)) {
                checkDriverVersion = false;
            }
            this.encoder.configure(userData, videoFormat, checkDriverVersion);
            this.encoder.start();
            this.encoder.configureDynamic(videoFormat);
            try {
                Thread.sleep(200);
                if (!Log.isLoggable(Commons.TAG, 3)) {
                    return 0;
                }
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
                return 0;
            } catch (InterruptedException e) {
                if (Log.isLoggable(Commons.TAG, 5)) {
                    Log.w(Commons.TAG, getClass().getSimpleName() + ": Interrupted", e);
                }
                return -1;
            }
        }
        throw new AttributeException("encoder " + this.encoderName + " does not support " + videoFormat.getProfile());
    }

    public int uninit(boolean isReinitializing) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        try {
            if (this.encoder != null) {
                this.encoder.stop();
                if (!isReinitializing) {
                    this.encoder.close();
                    this.encoder = null;
                }
            }
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
            return 0;
        } catch (RuntimeException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Unexpected exception caught", e);
            }
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_UNKNOWN");
            }
            return -1;
        }
    }

    public int processFrame(long timeoutUs, long nativeCallbackObj, long nativeFrameContext, long ts, boolean isReinitializing) {
        FrameBuffer frameBuffer;
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        SurfaceObserver surfaceObserver = this.weakRefSurfaceObserver != null ? (SurfaceObserver) this.weakRefSurfaceObserver.get() : null;
        if (!(surfaceObserver == null || isReinitializing)) {
            try {
                if (surfaceObserver.pushFrame()) {
                    if (Log.isLoggable(Commons.TAG, 4)) {
                        Log.i(Commons.TAG, getClass().getCanonicalName() + ": Drop frame from Capturer");
                    }
                    bitstreamReady(nativeCallbackObj, false, null, 0, 0, ts, false, false, false);
                    return 0;
                } else if (this.isFirstFrame && this.isFastAsyncMode) {
                    surfaceObserver.pushFrame();
                }
            } catch (Throwable e) {
                if (Log.isLoggable(Commons.TAG, 6)) {
                    Log.e(Commons.TAG, "Unexpected exception caught", e);
                }
                if (Log.isLoggable(Commons.TAG, 3)) {
                    Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_UNKNOWN");
                }
                return -1;
            } catch (Throwable e2) {
                if (Log.isLoggable(Commons.TAG, 6)) {
                    Log.e(Commons.TAG, getClass().getCanonicalName() + '#' + "Push frame has failed", e2);
                }
                return -1;
            } catch (Throwable th) {
                frameBuffer.releaseFrameBuffer();
                frameBuffer.close();
            }
        }
        if (isReinitializing) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getCanonicalName() + ": Skipping the frame due to reinitialization");
            }
            bitstreamReady(nativeCallbackObj, false, null, 0, 0, ts, false, false, false);
        } else {
            boolean isCodecConfig = false;
            if (this.isFirstFrame) {
                timeoutUs = 200000;
                this.isFirstFrame = false;
            }
            do {
                OutputFrame outputFrame = doEncodeFrame(timeoutUs, nativeFrameContext, ts, isCodecConfig);
                isCodecConfig = outputFrame.isCodecConfig();
                while (outputFrame.hasBuffers()) {
                    frameBuffer = outputFrame.popBuffer();
                    boolean isIncomplete = outputFrame.hasBuffers() || isCodecConfig;
                    bitstreamReady(nativeCallbackObj, isIncomplete, frameBuffer.getData(), frameBuffer.getOffset(), frameBuffer.getSize(), ts, outputFrame.isEndOfStream(), outputFrame.isTimedOut(), outputFrame.isOutputFormatChanged());
                    frameBuffer.releaseFrameBuffer();
                    frameBuffer.close();
                }
                if (outputFrame.isTimedOut()) {
                    this.consecutiveTimeOut++;
                    if (this.consecutiveTimeOut < 151) {
                        bitstreamReady(nativeCallbackObj, false, null, 0, 0, ts, outputFrame.isEndOfStream(), outputFrame.isTimedOut(), outputFrame.isOutputFormatChanged());
                    } else {
                        if (Log.isLoggable(Commons.TAG, 3)) {
                            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() HW encoder should not drop continuouse for 5 seconds, it is a timeout");
                        }
                        return 0;
                    }
                }
                this.consecutiveTimeOut = 0;
                outputFrame.close();
            } while (isCodecConfig);
        }
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
        }
        return 0;
    }

    public int flushFrames() {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
        }
        return 0;
    }

    public int h264SetQp(int qp) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        try {
            this.encoder.setQp(qp);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
            return 0;
        } catch (APINotImplementedException e) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, e.getFailedApiName() + "(" + e.toString() + ")");
            }
            return -10;
        }
    }

    public int setNumTempLayers(int numTempLayers) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        try {
            this.encoder.setNumTempLayers(numTempLayers);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
            return 0;
        } catch (APINotImplementedException e) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, e.getFailedApiName() + "(" + e.toString() + ")");
            }
            return -10;
        }
    }

    public int cleanFrameParams() {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        try {
            this.encoder.cleanFrameParams();
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
            return 0;
        } catch (APINotImplementedException e) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, e.getFailedApiName() + "(" + e.toString() + ")");
            }
            return -10;
        }
    }

    public int markParamsTimestamp(long timestamp) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called with timestamp " + timestamp);
        }
        try {
            this.encoder.markParamsTimestamp(timestamp);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
            return 0;
        } catch (APINotImplementedException e) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, e.getFailedApiName() + "(" + e.toString() + ")");
            }
            return -10;
        }
    }

    public int configureFrameParams() {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        try {
            this.encoder.configureFrameParams();
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
            return 0;
        } catch (APINotImplementedException e) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, e.getFailedApiName() + "(" + e.toString() + ")");
            }
            return -10;
        }
    }

    public int setBaseLayerPID(int baseLayerPID) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        try {
            this.encoder.setBaseLayerPID(baseLayerPID);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
            return 0;
        } catch (APINotImplementedException e) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, e.getFailedApiName() + "(" + e.toString() + ")");
            }
            return -10;
        }
    }

    public int setBitrate(int bitratekbps) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called bitrate:" + bitratekbps + "kbps");
        }
        if (VERSION.SDK_INT < 19) {
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_NOT_IMPLEMENTED in API " + VERSION.SDK_INT);
            }
            return -10;
        }
        try {
            this.encoder.setBitrate(bitratekbps * Constants.ONE_SECOND);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
            return 0;
        } catch (IllegalArgumentException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Illegal argument value", e);
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

    public int forceIdr() {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        if (VERSION.SDK_INT < 19) {
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_NOT_IMPLEMENTED in API " + VERSION.SDK_INT);
            }
            return -10;
        }
        try {
            this.encoder.requestSyncFrame();
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
            return 0;
        } catch (RuntimeException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Unexpected exception caught", e);
            }
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_UNKNOWN");
            }
            return -1;
        }
    }

    public int dropNextNFrames(int n) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_NOT_IMPLEMENTED");
        }
        return -10;
    }

    public int setFPS(float fps) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        try {
            SurfaceObserver surfaceObserver = this.weakRefSurfaceObserver != null ? (SurfaceObserver) this.weakRefSurfaceObserver.get() : null;
            if (surfaceObserver != null) {
                if (Log.isLoggable(Commons.TAG, 3)) {
                    Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() set targetFps to " + fps);
                }
                surfaceObserver.setTargetFrameRate(fps);
                if (Log.isLoggable(Commons.TAG, 3)) {
                    Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
                }
            }
            return 0;
        } catch (RuntimeException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, "Unexpected exception caught", e);
            }
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_UNKNOWN");
            }
            return -1;
        }
    }

    public int restartEncoder(int bitratekbps, float fps) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_NOT_IMPLEMENTED");
        }
        return -10;
    }

    public int setSliceSize(int size, int mode) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_NOT_IMPLEMENTED");
        }
        return -10;
    }

    public int setMaxNumRefFrames(int maxNumRefFrames) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_NOT_IMPLEMENTED");
        }
        return -10;
    }

    public int setProvideMADMetric(boolean provideMAD) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_NOT_IMPLEMENTED");
        }
        return -10;
    }

    public int getMaxQp() {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_NOT_IMPLEMENTED");
        }
        return -10;
    }

    public int setLTRBufferControl(int numLTR, boolean doNotTrust) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_NOT_IMPLEMENTED");
        }
        return -10;
    }

    public int markLTRFrame(int longTermFrameIdx) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        try {
            this.encoder.markLtrFrame(longTermFrameIdx);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
            return 0;
        } catch (APINotImplementedException e) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, e.getFailedApiName() + "(" + e.toString() + ")");
            }
            return -10;
        }
    }

    public int useLTRFrame(int useLTRFrameIdxBitMap, boolean constrainedMode) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        try {
            this.encoder.useLTRFrame(useLTRFrameIdxBitMap);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
            }
            return 0;
        } catch (APINotImplementedException e) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, e.getFailedApiName() + "(" + e.toString() + ")");
            }
            return -10;
        }
    }

    public int enhanceROI(int left, int top, int right, int bottom, int deltaQP) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() failed: SLIQ_ERROR_NOT_IMPLEMENTED");
        }
        return -10;
    }

    public int hintRcFrameRate(float inputFps, float targetFps) {
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() called");
        }
        try {
            this.encoder.setRcFrameRate(inputFps);
            SurfaceObserver surfaceObserver = this.weakRefSurfaceObserver != null ? (SurfaceObserver) this.weakRefSurfaceObserver.get() : null;
            if (surfaceObserver != null) {
                if (Log.isLoggable(Commons.TAG, 3)) {
                    Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() set targetFps to " + targetFps);
                }
                surfaceObserver.setTargetFrameRate(targetFps);
                if (Log.isLoggable(Commons.TAG, 3)) {
                    Log.d(Commons.TAG, getClass().getCanonicalName() + '#' + DebugUtils.getMethodName() + "() succeeded");
                }
            }
            return 0;
        } catch (APINotImplementedException e) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, e.getFailedApiName() + "(" + e.toString() + ")");
            }
            return -10;
        }
    }

    protected int getRawFrameSize() {
        return this.rawFrameSize;
    }

    protected long getInitialTimestamp() {
        return this.initialTimestamp;
    }

    protected Encoder getEncoder() {
        return this.encoder;
    }

    private static VideoFormat getVideoFormatFromAttributes(EncoderAttributes attributes) throws AttributeException {
        boolean z = true;
        int width = attributes.getInteger("width").intValue();
        if (width <= 0) {
            throw new AttributeException("width is illegal: " + width);
        }
        int height = attributes.getInteger("height").intValue();
        if (height <= 0) {
            throw new AttributeException("height is illegal: " + width);
        }
        int bitrate;
        width = (width + 15) & 65520;
        height = (height + 15) & 65520;
        int hrd_max_rate = attributes.getInteger("hrd-max-rate", 0);
        if (hrd_max_rate > 0) {
            bitrate = hrd_max_rate;
        } else {
            bitrate = attributes.getInteger("bitrate").intValue();
        }
        if (bitrate > 2147483 || bitrate < 0) {
            throw new AttributeException("bitrate out of range: " + bitrate);
        }
        int rcMode = attributes.getInteger("android-rc-mode", 2);
        VideoFormat format = new VideoFormat();
        format.setResolution(new Resolution(width, height));
        format.setBitrate(bitrate * Constants.ONE_SECOND);
        format.setColorFormat(ColorFormat.fromSliq(attributes.getInteger("color-space-format").intValue()));
        format.setProfile(H264Profile.valueOf(attributes.getString("h264-profile", DEFAULT_H264_PROFILE.getName()).toUpperCase()));
        format.setLevel(H264Level.fromName(attributes.getString("h264-level-idc", DEFAULT_H264_LEVEL.getName())));
        format.setFrameRate(attributes.getInteger("fps", 30));
        format.setiFrameInterval(DEFAULT_I_FRAME_INTERVAL);
        format.setRCMode(RateControlMode.values()[rcMode]);
        format.setRefCount(attributes.getInteger("ref-count", 1));
        format.setNumLayers(attributes.getInteger("num-channels", 1));
        format.setBaseLayerPriorityId(attributes.getInteger("base-priority-id", 0));
        format.setMinNumSlices(attributes.getInteger("min-num-slices", 1));
        if (attributes.getInteger("mlvec-svc", 0) == 0) {
            z = false;
        }
        format.setSvcAlways(z);
        return format;
    }

    private static void passCapabilitiesToNative(long r30, com.skype.android.video.hw.format.Capabilities r32) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r27_2 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper) in PHI: PHI: (r27_1 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper) = (r27_0 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper), (r27_0 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper), (r27_0 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper), (r27_0 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper), (r27_2 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper), (r27_2 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper) binds: {(r27_0 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper)=B:50:0x029e, (r27_0 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper)=B:55:0x02bc, (r27_0 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper)=B:59:0x032a, (r27_0 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper)=B:79:0x0374, (r27_2 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper)=B:81:0x038c, (r27_2 'omxWrapper' com.skype.android.video.hw.codec.OmxWrapper)=B:94:0x03f3}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r1 = r32.getProfiles();
        r1 = r1.size();
        r4 = new int[r1];
        r24 = 0;
        r1 = r32.getProfiles();
        r1 = r1.iterator();
    L_0x0014:
        r2 = r1.hasNext();
        if (r2 == 0) goto L_0x002f;
    L_0x001a:
        r28 = r1.next();
        r28 = (com.skype.android.video.hw.format.H264Profile) r28;
        r25 = r24 + 1;
        r2 = r28.getSliqValue();
        r2 = r2.intValue();
        r4[r24] = r2;
        r24 = r25;
        goto L_0x0014;
    L_0x002f:
        r5 = -1;
        r1 = r32.getLevels();
        r1 = r1.iterator();
    L_0x0038:
        r2 = r1.hasNext();
        if (r2 == 0) goto L_0x0051;
    L_0x003e:
        r26 = r1.next();
        r26 = (com.skype.android.video.hw.format.H264Level) r26;
        r2 = r26.getSliqValue();
        r2 = r2.intValue();
        r5 = java.lang.Math.max(r5, r2);
        goto L_0x0038;
    L_0x0051:
        r1 = r32.getColorFormats();
        r1 = r1.size();
        r6 = new int[r1];
        r24 = 0;
        r1 = r32.getColorFormats();
        r1 = r1.iterator();
    L_0x0065:
        r2 = r1.hasNext();
        if (r2 == 0) goto L_0x0080;
    L_0x006b:
        r21 = r1.next();
        r21 = (com.skype.android.video.hw.format.ColorFormat) r21;
        r25 = r24 + 1;
        r2 = r21.getSliqValue();
        r2 = r2.intValue();
        r6[r24] = r2;
        r24 = r25;
        goto L_0x0065;
    L_0x0080:
        r7 = 0;
        r8 = 0;
        r10 = 0;
        r1 = r32.getBitrateRange();
        if (r1 == 0) goto L_0x0342;
    L_0x008a:
        r1 = r32.getBitrateRange();
        r1 = r1.getLower();
        r1 = (java.lang.Integer) r1;
        r16 = r1.intValue();
    L_0x0098:
        r1 = r32.getBitrateRange();
        if (r1 == 0) goto L_0x0346;
    L_0x009e:
        r1 = r32.getBitrateRange();
        r1 = r1.getUpper();
        r1 = (java.lang.Integer) r1;
        r17 = r1.intValue();
    L_0x00ac:
        r1 = r32.getWidthRange();
        if (r1 == 0) goto L_0x034a;
    L_0x00b2:
        r1 = r32.getWidthRange();
        r1 = r1.getLower();
        r1 = (java.lang.Integer) r1;
        r12 = r1.intValue();
    L_0x00c0:
        r1 = r32.getWidthRange();
        if (r1 == 0) goto L_0x034d;
    L_0x00c6:
        r1 = r32.getWidthRange();
        r1 = r1.getUpper();
        r1 = (java.lang.Integer) r1;
        r13 = r1.intValue();
    L_0x00d4:
        r1 = r32.getHeightRange();
        if (r1 == 0) goto L_0x0350;
    L_0x00da:
        r1 = r32.getHeightRange();
        r1 = r1.getLower();
        r1 = (java.lang.Integer) r1;
        r14 = r1.intValue();
    L_0x00e8:
        r1 = r32.getHeightRange();
        if (r1 == 0) goto L_0x0353;
    L_0x00ee:
        r1 = r32.getHeightRange();
        r1 = r1.getUpper();
        r1 = (java.lang.Integer) r1;
        r15 = r1.intValue();
    L_0x00fc:
        r27 = 0;
        r18 = 0;
        r19 = 0;
        r20 = 0;
        r1 = com.skype.android.video.hw.utils.CodecUtils.getHWMode();
        r2 = com.skype.android.video.hw.HWFeatureSelectiveFields.QC_OMX_Extension;
        r1 = r1.contains(r2);
        if (r1 == 0) goto L_0x036a;
    L_0x0110:
        r1 = r32.isQCExtensionSupported();
        if (r1 == 0) goto L_0x029a;
    L_0x0116:
        r23 = r32.getExtCapabilities();
        r1 = "vt-version";
        r0 = r23;
        r1 = r0.getString(r1);
        if (r1 != 0) goto L_0x0356;
    L_0x0124:
        r1 = 0;
    L_0x0125:
        r29 = java.lang.Integer.valueOf(r1);
        r2 = "SLIQ";
        r3 = new java.lang.StringBuilder;
        r1 = "getExtCapabilities: ";
        r3.<init>(r1);
        if (r23 != 0) goto L_0x0359;
    L_0x0134:
        r1 = "null";
    L_0x0136:
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.skype.android.video.hw.utils.Log.d(r2, r1);
        if (r23 == 0) goto L_0x027e;
    L_0x0143:
        r1 = "SLIQ";
        r2 = "!!!!!!!!!!!!!!";
        com.skype.android.video.hw.utils.Log.d(r1, r2);
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "getVersion() =>                     ";
        r2.<init>(r3);
        r3 = "vt-version";
        r0 = r23;
        r3 = r0.getString(r3);
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.d(r1, r2);
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "isLowLatencySupported() =>          ";
        r2.<init>(r3);
        r3 = "vt-low-latency";
        r0 = r23;
        r3 = r0.getString(r3);
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.d(r1, r2);
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "getMaxInstances() =>                ";
        r2.<init>(r3);
        r3 = "vt-max-instances";
        r0 = r23;
        r3 = r0.getString(r3);
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.d(r1, r2);
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "getMaxTemporalLayerCount() =>       ";
        r2.<init>(r3);
        r3 = "vt-max-temporal-layer-count";
        r0 = r23;
        r3 = r0.getString(r3);
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.d(r1, r2);
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "getMaxRefFrames() =>                ";
        r2.<init>(r3);
        r3 = "vt-max-ref-frames";
        r0 = r23;
        r3 = r0.getString(r3);
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.d(r1, r2);
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "getMaxLTRFrames() =>                ";
        r2.<init>(r3);
        r3 = "vt-max-ltr-frames";
        r0 = r23;
        r3 = r0.getString(r3);
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.d(r1, r2);
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "getMaxLevel() =>                    ";
        r2.<init>(r3);
        r3 = "vt-max-level";
        r0 = r23;
        r3 = r0.getString(r3);
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.d(r1, r2);
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "getSliceControlModesBM() =>         ";
        r2.<init>(r3);
        r3 = "vt-slice-control-modes-bitmask";
        r0 = r23;
        r3 = r0.getString(r3);
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.d(r1, r2);
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "getMaxMacroblockProcessingRate() => ";
        r2.<init>(r3);
        r3 = "vt-max-macroblock-processing-rate";
        r0 = r23;
        r3 = r0.getString(r3);
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.d(r1, r2);
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "getSupportedDownScaleFactor() =>    ";
        r2.<init>(r3);
        r3 = "vt-down-scale-factor";
        r0 = r23;
        r3 = r0.getString(r3);
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.d(r1, r2);
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "getMinScaleFactor() =>              ";
        r2.<init>(r3);
        r3 = "vt-min-scale-factor";
        r0 = r23;
        r3 = r0.getString(r3);
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.d(r1, r2);
    L_0x027e:
        r1 = "vt-max-ltr-frames";
        r0 = r23;
        r1 = r0.getString(r1);
        r1 = java.lang.Integer.valueOf(r1);
        r19 = r1.intValue();
        if (r29 == 0) goto L_0x035d;
    L_0x0290:
        r1 = r29.intValue();
        r8 = (long) r1;
    L_0x0295:
        if (r29 == 0) goto L_0x0361;
    L_0x0297:
        r10 = 1;
    L_0x0298:
        r18 = 1;
    L_0x029a:
        r1 = r32.isQCAfterNougatExtensionsSupported();
        if (r1 == 0) goto L_0x032c;
    L_0x02a0:
        r23 = r32.getExtCapabilities();
        r2 = "SLIQ";
        r3 = new java.lang.StringBuilder;
        r1 = "getExtCapabilities: ";
        r3.<init>(r1);
        if (r23 != 0) goto L_0x0364;
    L_0x02af:
        r1 = "null";
    L_0x02b1:
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.skype.android.video.hw.utils.Log.d(r2, r1);
        if (r23 == 0) goto L_0x032c;
    L_0x02be:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r32.getExtPrefix();
        r1 = r1.append(r2);
        r2 = "-ext-enc-caps-vt-driver-version.number";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0 = r23;
        r1 = r0.getInteger(r1);
        r8 = (long) r1;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r32.getExtPrefix();
        r1 = r1.append(r2);
        r2 = "-ext-enc-caps-ltr.max-count";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0 = r23;
        r19 = r0.getInteger(r1);
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "[Ext] driver version: ";
        r2.<init>(r3);
        r2 = r2.append(r8);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.i(r1, r2);
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "[Ext] maxnums of ltr frame: ";
        r2.<init>(r3);
        r0 = r19;
        r2 = r2.append(r0);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.i(r1, r2);
        r1 = 0;
        r1 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1));
        if (r1 == 0) goto L_0x0368;
    L_0x0329:
        r10 = 1;
    L_0x032a:
        r18 = 1;
    L_0x032c:
        r3 = r32.getCodecName();
        r11 = 1;
        r1 = r30;
        com.skype.android.video.hw.extension.JniCodecUtils.returnCapabilitiesBuffer(r1, r3, r4, r5, r6, r7, r8, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);
        if (r27 == 0) goto L_0x0341;
    L_0x0338:
        r1 = r27.isNodeIdKnown();
        if (r1 == 0) goto L_0x0341;
    L_0x033e:
        r27.close();	 Catch:{ IOException -> 0x0405 }
    L_0x0341:
        return;
    L_0x0342:
        r16 = 0;
        goto L_0x0098;
    L_0x0346:
        r17 = 0;
        goto L_0x00ac;
    L_0x034a:
        r12 = 0;
        goto L_0x00c0;
    L_0x034d:
        r13 = 0;
        goto L_0x00d4;
    L_0x0350:
        r14 = 0;
        goto L_0x00e8;
    L_0x0353:
        r15 = 0;
        goto L_0x00fc;
    L_0x0356:
        r1 = 1;
        goto L_0x0125;
    L_0x0359:
        r1 = r23;
        goto L_0x0136;
    L_0x035d:
        r8 = 0;
        goto L_0x0295;
    L_0x0361:
        r10 = 0;
        goto L_0x0298;
    L_0x0364:
        r1 = r23;
        goto L_0x02b1;
    L_0x0368:
        r10 = 0;
        goto L_0x032a;
    L_0x036a:
        r1 = com.skype.android.video.hw.utils.CodecUtils.getHWMode();
        r2 = com.skype.android.video.hw.HWFeatureSelectiveFields.Android_OMX;
        r1 = r1.contains(r2);
        if (r1 == 0) goto L_0x032c;
    L_0x0376:
        r27 = new com.skype.android.video.hw.codec.IpcOmxWrapper;
        r1 = r32.getCodecName();
        r2 = r32.getCodecName();
        r0 = r27;
        r0.<init>(r1, r2);
        r27.connectForQueriesOnly();
        r1 = r27.isNodeIdKnown();
        if (r1 == 0) goto L_0x032c;
    L_0x038e:
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "!!! Got nodeId: ";
        r2.<init>(r3);
        r3 = r27.getNodeId();
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.i(r1, r2);
        r1 = r27.queryDriverVersion();
        if (r1 == 0) goto L_0x0400;
    L_0x03ac:
        r10 = 1;
    L_0x03ad:
        if (r10 == 0) goto L_0x03ce;
    L_0x03af:
        r8 = r27.getDriverVersion();
        r7 = r27.getEncCapabilityBuffer();
        if (r7 != 0) goto L_0x03ce;
    L_0x03b9:
        r1 = com.skype.android.video.hw.utils.CodecUtils.omxCapableHW;
        r1 = java.util.Arrays.asList(r1);
        r2 = r32.getCodecName();
        r1 = r1.contains(r2);
        if (r1 == 0) goto L_0x03ce;
    L_0x03c9:
        r1 = com.skype.android.video.hw.HWFeatureSelectiveFields.Android_OMX;
        com.skype.android.video.hw.utils.CodecUtils.overrideHWMode(r1);
    L_0x03ce:
        r1 = "SLIQ";
        r2 = new java.lang.StringBuilder;
        r3 = "Driver version: ";
        r2.<init>(r3);
        r2 = r2.append(r8);
        r2 = r2.toString();
        com.skype.android.video.hw.utils.Log.i(r1, r2);
        r20 = r27.isQpSupported();
        r2 = "SLIQ";
        r3 = new java.lang.StringBuilder;
        r1 = "QP control ";
        r3.<init>(r1);
        if (r20 == 0) goto L_0x0402;
    L_0x03f1:
        r1 = "supported";
    L_0x03f3:
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.skype.android.video.hw.utils.Log.i(r2, r1);
        goto L_0x032c;
    L_0x0400:
        r10 = 0;
        goto L_0x03ad;
    L_0x0402:
        r1 = "un-supported";
        goto L_0x03f3;
    L_0x0405:
        r22 = move-exception;
        r1 = "SLIQ";
        r2 = 6;
        r1 = com.skype.android.video.hw.utils.Log.isLoggable(r1, r2);
        if (r1 == 0) goto L_0x0341;
    L_0x040f:
        r1 = "SLIQ";
        r2 = "Unexpected IOexception caught";
        r0 = r22;
        com.skype.android.video.hw.utils.Log.e(r1, r2, r0);
        goto L_0x0341;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.skype.android.video.hw.extension.encoder.AbstractVideoEncoderExtension.passCapabilitiesToNative(long, com.skype.android.video.hw.format.Capabilities):void");
    }
}
