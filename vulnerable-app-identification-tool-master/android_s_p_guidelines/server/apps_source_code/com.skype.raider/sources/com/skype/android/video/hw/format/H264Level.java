package com.skype.android.video.hw.format;

import com.skype.Defines;

public enum H264Level implements SliqOmxMapping<Integer, Integer> {
    L1("10", 1, 0),
    L1B("10b", 2, 1),
    L11("11", 4, 2),
    L12("12", 8, 3),
    L13("13", 16, 4),
    L2("20", 32, 5),
    L21("21", 64, 6),
    L22("22", 128, 7),
    L3("30", Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, 8),
    L31("31", 512, 9),
    L32("32", 1024, 10),
    L4("40", 2048, 11),
    L41("41", 4096, 12),
    L42("42", 8192, 13),
    L5("50", 16384, 14),
    L51("51", 32768, 15);
    
    private static FormatMapper<Integer, Integer, H264Level> mapper;
    private final String name;
    private final int omxValue;
    private final int sliqValue;

    private H264Level(String name, int omxValue, int sliqValue) {
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

    public static H264Level fromSliq(int value) {
        return (H264Level) getMapper().fromSliq(Integer.valueOf(value));
    }

    public static H264Level fromOmx(int value) {
        if (value > L51.getOmxValue().intValue()) {
            return L51;
        }
        return (H264Level) getMapper().fromOmx(Integer.valueOf(value));
    }

    public static H264Level fromName(String name) {
        return (H264Level) getMapper().fromName(name);
    }

    private static synchronized FormatMapper<Integer, Integer, H264Level> getMapper() {
        FormatMapper<Integer, Integer, H264Level> formatMapper;
        synchronized (H264Level.class) {
            if (mapper == null) {
                mapper = new FormatMapper(values());
            }
            formatMapper = mapper;
        }
        return formatMapper;
    }
}
