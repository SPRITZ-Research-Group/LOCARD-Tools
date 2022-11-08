package com.skype.android.video.hw;

public enum HWFeatureSelectiveFields {
    Mediacodec_Only("Mediacodec_Only"),
    Android_OMX("Android_OMX"),
    Skype_OMX_Extension("Skype_OMX_Extension"),
    QC_OMX_Extension("QC_OMX_Extension"),
    Surface_Mode("Surface_Mode");
    
    private final String name;
    private final int value;

    private HWFeatureSelectiveFields(String name) {
        this.name = name;
        this.value = 1 << ordinal();
    }

    public final String getName() {
        return this.name;
    }

    public final int getValue() {
        return this.value;
    }

    public final boolean isSet(int bitSet) {
        return (this.value & bitSet) != 0;
    }
}
