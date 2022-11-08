package com.skype.android.video.hw.format;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class VideoFormat implements Serializable {
    private static final long serialVersionUID = 3950051940485529793L;
    private int baseLayerPriorityId;
    private int bitrate;
    private ColorFormat colorFormat;
    private int frameRate;
    private int iFrameInterval;
    private H264Level level;
    private Resolution maxResolution;
    private int minNumSlices;
    private int numLayers;
    private H264Profile profile;
    private RateControlMode rcMode;
    private int refCount;
    private Resolution resolution;
    private ByteBuffer spsPps;
    private boolean svcAlways;

    public ByteBuffer getSpsPps() {
        return this.spsPps;
    }

    public void setSpsPps(ByteBuffer spsPps) {
        this.spsPps = spsPps;
    }

    public Resolution getResolution() {
        return this.resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public Resolution getMaxResolution() {
        return this.maxResolution;
    }

    public void setMaxResolution(Resolution resolution) {
        this.maxResolution = resolution;
    }

    public ColorFormat getColorFormat() {
        return this.colorFormat;
    }

    public void setColorFormat(ColorFormat colorFormat) {
        this.colorFormat = colorFormat;
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getFrameRate() {
        return this.frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public int getiFrameInterval() {
        return this.iFrameInterval;
    }

    public void setiFrameInterval(int iFrameInterval) {
        this.iFrameInterval = iFrameInterval;
    }

    public H264Profile getProfile() {
        return this.profile;
    }

    public void setProfile(H264Profile profile) {
        this.profile = profile;
    }

    public H264Level getLevel() {
        return this.level;
    }

    public void setLevel(H264Level level) {
        this.level = level;
    }

    public RateControlMode getRCMode() {
        return this.rcMode;
    }

    public void setRCMode(RateControlMode rcMode) {
        this.rcMode = rcMode;
    }

    public int getRefCount() {
        return this.refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }

    public int getNumLayers() {
        return this.numLayers;
    }

    public void setNumLayers(int numLayers) {
        this.numLayers = numLayers;
    }

    public int getBaseLayerPriorityId() {
        return this.baseLayerPriorityId;
    }

    public void setBaseLayerPriorityId(int baseLayerPriorityId) {
        this.baseLayerPriorityId = baseLayerPriorityId;
    }

    public int getMinNumSlices() {
        return this.minNumSlices;
    }

    public void setMinNumSlices(int minNumSlices) {
        this.minNumSlices = minNumSlices;
    }

    public boolean getSvcAlways() {
        return this.svcAlways;
    }

    public void setSvcAlways(boolean svcAlways) {
        this.svcAlways = svcAlways;
    }

    public String toString() {
        return getClass().getSimpleName() + " [resolution=" + this.resolution + ", maxResolution=" + this.maxResolution + ", colorFormat=" + this.colorFormat + ", bitrate=" + this.bitrate + ", frameRate=" + this.frameRate + ", iFrameInterval=" + this.iFrameInterval + ", profile=" + this.profile + ", level=" + this.level + ", rcMode=" + this.rcMode + ", refCount" + this.refCount + ", numLayers" + this.numLayers + ", baseLayerPriorityId" + this.baseLayerPriorityId + ", minNumSlices" + this.minNumSlices + ", svcAlways" + this.svcAlways + "]";
    }
}
