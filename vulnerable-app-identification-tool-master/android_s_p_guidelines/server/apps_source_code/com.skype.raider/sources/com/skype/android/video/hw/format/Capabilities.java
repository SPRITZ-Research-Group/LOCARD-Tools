package com.skype.android.video.hw.format;

import android.media.MediaFormat;
import android.util.Range;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.utils.Log;
import java.io.Serializable;
import java.util.Set;

public class Capabilities implements Serializable {
    private static final long serialVersionUID = 6275606762677056785L;
    private Range<Integer> bitrateRange;
    private final String codecName;
    private final Set<ColorFormat> colorFormats;
    private MediaFormat extCaps;
    private String extPrefix;
    private Range<Integer> heightRange;
    private final Set<H264Level> levels;
    private final Set<H264Profile> profiles;
    private boolean supportQCAfterNougatExtensions = false;
    private boolean supportQCExtensions = false;
    private final Range<Integer> widthRange;

    public Capabilities(String codecName, Set<H264Profile> profiles, Set<H264Level> levels, Set<ColorFormat> colorFormats, Range<Integer> widthRange, Range<Integer> heightRange, Range<Integer> bitrateRange, MediaFormat extCaps) {
        this.codecName = codecName;
        this.profiles = profiles;
        this.levels = levels;
        this.colorFormats = colorFormats;
        this.bitrateRange = bitrateRange;
        this.widthRange = widthRange;
        this.heightRange = heightRange;
        this.extCaps = extCaps;
        if (extCaps != null) {
            this.supportQCExtensions = true;
            this.extPrefix = codecName.toLowerCase().contains("qcom") ? "vendor.qti" : "vendor.rtc";
            if (extCaps.containsKey(this.extPrefix + "-ext-enc-caps-vt-driver-version.number") || extCaps.containsKey(this.extPrefix + "-ext-dec-caps-vt-driver-version.number")) {
                if (extCaps.containsKey(this.extPrefix + "-ext-enc-caps-vt-driver-version.number")) {
                    Log.i(Commons.TAG, this.extPrefix + "-ext-enc-caps-vt-driver-version.number: " + extCaps.getInteger(this.extPrefix + "-ext-enc-caps-vt-driver-version.number"));
                }
                if (extCaps.containsKey(this.extPrefix + "-ext-dec-caps-vt-driver-version.number")) {
                    Log.i(Commons.TAG, this.extPrefix + "-ext-enc-caps-vt-driver-version.number: " + extCaps.getInteger(this.extPrefix + "-ext-dec-caps-vt-driver-version.number"));
                }
                this.supportQCAfterNougatExtensions = true;
                this.supportQCExtensions = false;
                Log.i(Commons.TAG, " supportQCAfterNougatExtensions: " + this.supportQCAfterNougatExtensions + " supportQCExtensions: " + this.supportQCExtensions);
            }
        }
    }

    public String getCodecName() {
        return this.codecName;
    }

    public String getExtPrefix() {
        return this.extPrefix;
    }

    public Set<H264Profile> getProfiles() {
        return this.profiles;
    }

    public Set<H264Level> getLevels() {
        return this.levels;
    }

    public Set<ColorFormat> getColorFormats() {
        return this.colorFormats;
    }

    public Range<Integer> getWidthRange() {
        return this.widthRange;
    }

    public Range<Integer> getHeightRange() {
        return this.heightRange;
    }

    public Range<Integer> getBitrateRange() {
        return this.bitrateRange;
    }

    public boolean isQCExtensionSupported() {
        return this.supportQCExtensions;
    }

    public boolean isQCAfterNougatExtensionsSupported() {
        return this.supportQCAfterNougatExtensions;
    }

    public MediaFormat getExtCapabilities() {
        return this.extCaps;
    }

    public String toString() {
        return getClass().getSimpleName() + " [codecName=" + this.codecName + ", profiles=" + this.profiles + ", levels=" + this.levels + ", colorFormats=" + this.colorFormats + ", widthRange=" + this.widthRange + ", heightRange=" + this.heightRange + ", bitrateRange=" + this.bitrateRange + ", supportQCExtensions=" + this.supportQCExtensions + ", supportQCAfterNougatExtension=" + this.supportQCAfterNougatExtensions + "]";
    }
}
