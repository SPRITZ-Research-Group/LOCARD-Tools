package com.skype.android.video.hw.format;

import com.adjust.sdk.Constants;

public enum H264Profile implements SliqOmxMapping<Integer, Integer> {
    CONSTRAINEDBASELINE("constrainedbaseline", 65536, 0),
    BASELINE("baseline", 1, 1),
    EXTENDEND("extended", 4, 2),
    MAIN("main", 2, 3),
    CONSTRAINEDHIGH("constrainedhigh", 524288, 4),
    HIGH(Constants.HIGH, 8, 6),
    HIGH10("high10", 16, 8),
    HIGH422("high422", 32, 10),
    HIGH444("high444", 64, 13);
    
    private static FormatMapper<Integer, Integer, H264Profile> mapper;
    private final String name;
    private final int omxValue;
    private final int sliqValue;

    private H264Profile(String name, int omxValue, int sliqValue) {
        this.name = name;
        this.omxValue = omxValue;
        this.sliqValue = sliqValue;
    }

    public final String getName() {
        return this.name;
    }

    public final Integer getOmxValue() {
        return Integer.valueOf(this.omxValue);
    }

    public final Integer getSliqValue() {
        return Integer.valueOf(this.sliqValue);
    }

    public static H264Profile fromSliq(int value) {
        return (H264Profile) getMapper().fromSliq(Integer.valueOf(value));
    }

    public static H264Profile fromOmx(int value) {
        return (H264Profile) getMapper().fromOmx(Integer.valueOf(value));
    }

    public static H264Profile fromName(String name) {
        return (H264Profile) getMapper().fromName(name);
    }

    private static synchronized FormatMapper<Integer, Integer, H264Profile> getMapper() {
        FormatMapper<Integer, Integer, H264Profile> formatMapper;
        synchronized (H264Profile.class) {
            if (mapper == null) {
                mapper = new FormatMapper(values());
            }
            formatMapper = mapper;
        }
        return formatMapper;
    }
}
