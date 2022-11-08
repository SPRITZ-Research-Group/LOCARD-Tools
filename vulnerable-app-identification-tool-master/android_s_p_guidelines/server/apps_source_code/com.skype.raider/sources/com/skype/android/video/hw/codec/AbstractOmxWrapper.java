package com.skype.android.video.hw.codec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.HWFeatureSelectiveFields;
import com.skype.android.video.hw.format.H264Level;
import com.skype.android.video.hw.format.H264Profile;
import com.skype.android.video.hw.utils.CodecUtils;
import com.skype.android.video.hw.utils.Log;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractOmxWrapper implements OmxWrapper {
    public static final int MAGIC_FAILURE_CONSTANT = -1;
    protected static final Lock lock = new ReentrantLock(true);
    private long driverVersion = 0;
    final String dummyName;
    final String hwCodecName;
    private int newNodeId;
    private int oldNodeId;

    protected abstract int configureEncoder(int i, int i2, int i3, int i4, int i5, boolean z, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14);

    protected abstract int doMarkLtrFrame(int i, int i2);

    protected abstract int doQueryDriverVersion(int i);

    protected abstract int doQueryDriverVersionAndCheckVerSystem(int i);

    protected abstract int doSetBaseLayerPID(int i, int i2);

    protected abstract int doSetNumTempLayers(int i, int i2);

    protected abstract int doSetQp(int i, int i2);

    protected abstract int doUseLTRFrame(int i, int i2);

    protected abstract int incrementAndGetNodeId();

    public AbstractOmxWrapper(String codecName, String dummyComponentName) {
        this.hwCodecName = codecName;
        this.dummyName = dummyComponentName;
    }

    public int setDriverVersion(long drvVer) {
        this.driverVersion = drvVer;
        return 0;
    }

    public long getDriverVersion() {
        return this.driverVersion;
    }

    private int preAllocate() {
        lock.lock();
        try {
            int oldNodeId = incrementAndGetNodeId();
            Log.i(Commons.TAG, "old node ID: " + oldNodeId);
            return oldNodeId;
        } catch (Throwable th) {
            Log.i(Commons.TAG, "old node ID: -1");
        }
    }

    private int postAllocate() {
        try {
            int newNodeId = incrementAndGetNodeId();
            Log.i(Commons.TAG, "new node ID: " + newNodeId);
            lock.unlock();
            return newNodeId;
        } catch (Throwable th) {
            Log.i(Commons.TAG, "new node ID: -1");
            lock.unlock();
        }
    }

    public final MediaCodec createTracked() {
        MediaCodec codec;
        try {
            this.oldNodeId = preAllocate();
            codec = MediaCodec.createByCodecName(this.hwCodecName);
        } catch (IOException e) {
            codec = null;
            e.printStackTrace();
        } finally {
            this.newNodeId = postAllocate();
        }
        return codec;
    }

    public boolean isNodeIdKnown() {
        return (this.oldNodeId == -1 || this.newNodeId == -1 || this.newNodeId - 1 != this.oldNodeId + 1) ? false : true;
    }

    public int getNodeId() {
        if (isNodeIdKnown()) {
            return this.oldNodeId + 1;
        }
        throw new IllegalStateException("Node ID not determined");
    }

    public int configureEncoder(MediaFormat format, int rcMode, int refCount, int numChannels, int baseLayerPriorityId, int minNumSlices, boolean svcAlways) {
        if (CodecUtils.getHWMode().contains(HWFeatureSelectiveFields.Android_OMX)) {
            int iFrameInt;
            int profile;
            int level;
            int colorFormat = format.getInteger("color-format");
            int width = format.getInteger("width");
            int height = format.getInteger("height");
            int bitrate = format.getInteger("bitrate");
            int framerate = format.getInteger("frame-rate");
            if (format.containsKey("i-frame-interval")) {
                iFrameInt = format.getInteger("i-frame-interval");
            } else {
                iFrameInt = 0;
            }
            if (format.containsKey("profile")) {
                profile = format.getInteger("profile");
            } else {
                profile = H264Profile.BASELINE.getOmxValue().intValue();
            }
            if (format.containsKey("level")) {
                level = format.getInteger("level");
            } else {
                level = H264Level.L3.getOmxValue().intValue();
            }
            return configureEncoder(rcMode, refCount, numChannels, baseLayerPriorityId, minNumSlices, svcAlways, colorFormat, width, height, bitrate, framerate, iFrameInt, profile, level, CodecUtils.hwMode_java);
        }
        Log.i(Commons.TAG, "EnableAndroidOMXFeature is false, thus will not configureEncoder via OMX");
        return 0;
    }

    public int setQp(int qp) {
        return hasSkypeOmxExtension() ? doSetQp(getNodeId(), qp) : 0;
    }

    public int setNumTempLayers(int numTempLayers) {
        return hasSkypeOmxExtension() ? doSetNumTempLayers(getNodeId(), numTempLayers) : 0;
    }

    public int setBaseLayerPID(int baseLayerPID) {
        return hasSkypeOmxExtension() ? doSetBaseLayerPID(getNodeId(), baseLayerPID) : 0;
    }

    public int markLtrFrame(int longTermFrameIdx) {
        return hasSkypeOmxExtension() ? doMarkLtrFrame(getNodeId(), longTermFrameIdx) : 0;
    }

    public int useLTRFrame(int useLTRFrameIdxBitMap) {
        return hasSkypeOmxExtension() ? doUseLTRFrame(getNodeId(), useLTRFrameIdxBitMap) : 0;
    }

    protected boolean hasSkypeOmxExtension() {
        return CodecUtils.getHWMode().contains(HWFeatureSelectiveFields.Skype_OMX_Extension);
    }

    public int queryDriverVersion() {
        return hasSkypeOmxExtension() ? doQueryDriverVersion(getNodeId()) : 0;
    }

    public int queryDriverVersionAndCheckVerSystem() {
        return hasSkypeOmxExtension() ? doQueryDriverVersionAndCheckVerSystem(getNodeId()) : 0;
    }
}
