package com.skype.android.video.hw.codec;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.HWFeatureSelectiveFields;
import com.skype.android.video.hw.extension.SliqConstants;
import com.skype.android.video.hw.format.VideoFormat;
import com.skype.android.video.hw.frame.OutputFrame;
import com.skype.android.video.hw.frame.OutputFramePool;
import com.skype.android.video.hw.utils.CodecUtils;
import com.skype.android.video.hw.utils.Log;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class AbstractMediaCodec implements Closeable {
    private static final int OUTPUT_FRAME_POOL_SIZE = 4;
    private String extPrefix;
    private MediaFormat format;
    private boolean isEncoder = true;
    private boolean isOpen = true;
    protected MediaCodec mediaCodec;
    protected OmxWrapper omxWrapper = null;
    protected final BufferInfo outputBufferInfo = new BufferInfo();
    private ByteBuffer[] outputBuffers;
    private final OutputFramePool outputFramePool;
    protected boolean useQCAfterNougatDecoderExtension = false;
    protected boolean useQCAfterNougatExtension = false;
    protected boolean useQCDecoderExtension = false;
    protected boolean useQCExtension = false;

    protected abstract void doConfigureDynamic(VideoFormat videoFormat);

    protected abstract void doConfigureMediaFormat(MediaFormat mediaFormat, VideoFormat videoFormat);

    protected abstract int doGetMediaCodecFlags();

    protected AbstractMediaCodec(String name, boolean isEncoder) {
        boolean z = true;
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Creating MediaCodec " + isEncoder + " by name " + name);
        }
        this.isEncoder = isEncoder;
        boolean z2 = isEncoder && CodecUtils.getHWMode().contains(HWFeatureSelectiveFields.QC_OMX_Extension) && CodecUtils.getEncoderCapabilities(name).isQCExtensionSupported();
        this.useQCExtension = z2;
        if (isEncoder && CodecUtils.getHWMode().contains(HWFeatureSelectiveFields.QC_OMX_Extension) && CodecUtils.getEncoderCapabilities(name).isQCAfterNougatExtensionsSupported()) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.useQCAfterNougatExtension = z2;
        if (isEncoder || !CodecUtils.getDecoderCapabilities(name).isQCExtensionSupported()) {
            z2 = false;
        } else {
            z2 = true;
        }
        this.useQCDecoderExtension = z2;
        if (isEncoder || !CodecUtils.getDecoderCapabilities(name).isQCAfterNougatExtensionsSupported()) {
            z = false;
        }
        this.useQCAfterNougatDecoderExtension = z;
        Log.i(Commons.TAG, getClass().getSimpleName() + "useQCExtension " + this.useQCExtension + " useQCAfterNougatExtension " + this.useQCAfterNougatExtension + " useQCDecoderExtension " + this.useQCDecoderExtension + " useQCAfterNougatDecoderExtension" + this.useQCAfterNougatDecoderExtension);
        if (VERSION.SDK_INT > 25 || (isEncoder && (this.useQCExtension || this.useQCAfterNougatExtension || !CodecUtils.getHWMode().contains(HWFeatureSelectiveFields.Android_OMX)))) {
            try {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": MediaCodec path " + name);
                this.mediaCodec = MediaCodec.createByCodecName(name);
                this.extPrefix = name.toLowerCase().contains("qcom") ? "vendor.qti" : "vendor.rtc";
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": OMXwrapper path " + name);
            this.omxWrapper = new IpcOmxWrapper(name, name);
            this.mediaCodec = this.omxWrapper.createTracked();
            if (this.omxWrapper.isNodeIdKnown()) {
                Log.i(Commons.TAG, "!!! Got nodeId: " + this.omxWrapper.getNodeId());
            }
        }
        this.outputFramePool = new OutputFramePool(4, this.mediaCodec);
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": " + this.mediaCodec + " by name " + name + " created");
        }
    }

    public OmxWrapper getOmxWrapper() {
        return this.omxWrapper;
    }

    public MediaFormat getMediaFormat() {
        return this.format;
    }

    public String getExtPrefix() {
        return this.extPrefix;
    }

    public void configure(Object userData, VideoFormat videoFormat, boolean testHwApiVersioning) {
        if (this.isOpen) {
            this.format = createMediaFormat(videoFormat);
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Configuring " + this.mediaCodec + " with " + this.format);
            }
            this.mediaCodec.configure(this.format, null, null, doGetMediaCodecFlags());
            if (!this.useQCExtension && !this.useQCAfterNougatExtension && this.omxWrapper != null && this.omxWrapper.isNodeIdKnown()) {
                if (testHwApiVersioning) {
                    this.omxWrapper.queryDriverVersionAndCheckVerSystem();
                }
                if (this.isEncoder) {
                    Log.i(Commons.TAG, "configureEncoder returned " + this.omxWrapper.configureEncoder(this.format, videoFormat.getRCMode().ordinal(), videoFormat.getRefCount(), videoFormat.getNumLayers(), videoFormat.getBaseLayerPriorityId(), videoFormat.getMinNumSlices(), videoFormat.getSvcAlways()));
                    return;
                } else if (!this.useQCDecoderExtension) {
                    Log.i(Commons.TAG, "configureDecoderLowLatency returned " + this.omxWrapper.configureDecoderLowLatency());
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        throw new IllegalStateException("closed");
    }

    public void configureDynamic(VideoFormat videoFormat) {
        doConfigureDynamic(videoFormat);
    }

    public void setReconfigFlag(boolean rec) {
        this.outputFramePool.setReconfigFlag(rec);
    }

    public void start() {
        if (this.isOpen) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Starting " + this.mediaCodec);
            }
            this.mediaCodec.start();
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
            return;
        }
        throw new IllegalStateException("closed");
    }

    public void stop() {
        if (this.isOpen) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Stopping " + this.mediaCodec);
            }
            try {
                this.mediaCodec.stop();
                return;
            } catch (Exception e) {
                if (Log.isLoggable(Commons.TAG, 6)) {
                    Log.e(Commons.TAG, "Exception caught!", e);
                    return;
                }
                return;
            }
        }
        throw new IllegalStateException("closed");
    }

    public void close() {
        if (this.isOpen) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Releasing " + this.mediaCodec);
            }
            this.mediaCodec.release();
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Released " + this.mediaCodec);
            }
            try {
                if (this.omxWrapper != null) {
                    Log.i(Commons.TAG, getClass().getSimpleName() + ": OMXWrapper Closing");
                    this.omxWrapper.close();
                }
                this.outputFramePool.close();
            } catch (IOException e) {
                if (Log.isLoggable(Commons.TAG, 6)) {
                    Log.e(Commons.TAG, "Exception caught!", e);
                }
            }
            this.isOpen = false;
            return;
        }
        throw new IllegalStateException("closed");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected OutputFrame drainCodec(long timeoutUs) {
        if (this.isOpen) {
            OutputFrame frame = this.outputFramePool.poll();
            if (frame == null) {
                throw new IllegalStateException("frame pool is empty");
            }
            while (true) {
                boolean frameCopied = false;
                int status = Integer.MIN_VALUE;
                try {
                    status = this.mediaCodec.dequeueOutputBuffer(this.outputBufferInfo, timeoutUs);
                    if ((this.outputBufferInfo.flags & 4) != 0) {
                        frame.setEndOfStream(true);
                        if (Log.isLoggable(Commons.TAG, 4)) {
                            Log.i(Commons.TAG, getClass().getSimpleName() + ": End of stream");
                        }
                    }
                    if ((this.outputBufferInfo.flags & 2) != 0) {
                        frame.setCodecConfig(true);
                        if (Log.isLoggable(Commons.TAG, 4)) {
                            Log.i(Commons.TAG, getClass().getSimpleName() + ": Codec config (e.g. SPS/PPS)");
                        }
                    }
                    switch (status) {
                        case SliqConstants.SLIQ_ERROR_INVALID_MEMORY /*-3*/:
                            this.outputBuffers = this.mediaCodec.getOutputBuffers();
                            if (Log.isLoggable(Commons.TAG, 4)) {
                                Log.i(Commons.TAG, getClass().getSimpleName() + ": Output buffers changed");
                                break;
                            }
                            break;
                        case SliqConstants.SLIQ_ERROR_NOT_ENOUGH_DATA /*-2*/:
                            this.format = this.mediaCodec.getOutputFormat();
                            frame.setOutputFormatChanged(true);
                            if (Log.isLoggable(Commons.TAG, 4)) {
                                Log.i(Commons.TAG, getClass().getSimpleName() + ": Output format changed: " + this.format);
                                break;
                            }
                            break;
                        case -1:
                            frame.setTimedOut(true);
                            if (Log.isLoggable(Commons.TAG, 4)) {
                                Log.i(Commons.TAG, getClass().getSimpleName() + ": dequeueOutputBuffer timed out " + (((float) timeoutUs) / 1000.0f) + " ms");
                            }
                            if (status >= 0) {
                                this.mediaCodec.releaseOutputBuffer(status, false);
                                break;
                            }
                            break;
                        default:
                            if (status < 0) {
                                if (Log.isLoggable(Commons.TAG, 5)) {
                                    Log.w(Commons.TAG, getClass().getSimpleName() + ": Unknown encoder status: " + status);
                                }
                                if (status >= 0) {
                                    this.mediaCodec.releaseOutputBuffer(status, false);
                                    break;
                                }
                            }
                            frameCopied = copyFrame(frame, status);
                            break;
                            break;
                    }
                    
/*
Method generation error in method: com.skype.android.video.hw.codec.AbstractMediaCodec.drainCodec(long):com.skype.android.video.hw.frame.OutputFrame, dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0124: IF  (wrap: boolean
  0x0120: INVOKE  (r3_25 boolean) = (r0_0 'frame' com.skype.android.video.hw.frame.OutputFrame) com.skype.android.video.hw.frame.OutputFrame.isEndOfStream():boolean type: VIRTUAL) == false  -> B:8:0x001d in method: com.skype.android.video.hw.codec.AbstractMediaCodec.drainCodec(long):com.skype.android.video.hw.frame.OutputFrame, dex: classes.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:203)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:100)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:50)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:278)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:173)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:118)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:186)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
Caused by: jadx.core.utils.exceptions.CodegenException: IF can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:444)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 37 more

*/

                    protected boolean copyFrame(OutputFrame frame, int outputBufferIndex) {
                        ByteBuffer data = this.outputBuffers[outputBufferIndex];
                        if (data == null || this.outputBufferInfo.size <= 0) {
                            return false;
                        }
                        frame.pushBuffer(outputBufferIndex, data, this.outputBufferInfo.offset, this.outputBufferInfo.size);
                        return true;
                    }

                    protected boolean isOpen() {
                        return this.isOpen;
                    }

                    private MediaFormat createMediaFormat(VideoFormat format) {
                        MediaFormat mediaFormat = MediaFormat.createVideoFormat(CodecUtils.MEDIA_TYPE, format.getResolution().getWidth(), format.getResolution().getHeight());
                        doConfigureMediaFormat(mediaFormat, format);
                        Log.i(Commons.TAG, "Created MediaFormat: " + mediaFormat.toString());
                        return mediaFormat;
                    }
                }
