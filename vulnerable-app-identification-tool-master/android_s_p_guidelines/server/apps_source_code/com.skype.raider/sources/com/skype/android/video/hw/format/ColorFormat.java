package com.skype.android.video.hw.format;

public enum ColorFormat implements SliqOmxMapping<Integer, Integer> {
    I420("I420", 19, (String) 12),
    NV12("NV12", 21, (String) 12),
    YUY2("YUY2", 25, (String) 16),
    UYVY("UYVY", 27, (String) 16),
    YVYU("YVYU", 26, (String) 16),
    OMX_QCOM_COLOR_FormatYUV420PackedSemiPlanar32m((String) 2141391876, 2141391876, 0),
    Android_COLOR_FormatYUV420Flexible((String) 2135033992, 2135033992, 0),
    SURFACE((String) 2130708361, 2130708361, 32);
    
    private static FormatMapper<Integer, Integer, ColorFormat> mapper;
    private final int bitsPerPixel;
    private final String name;
    private final int omxValue;
    private final int sliqValue;

    private ColorFormat(int fourcc, int omxValue, int bitsPerPixel) {
        this.name = "0x" + Integer.toHexString(fourcc);
        this.bitsPerPixel = bitsPerPixel;
        this.sliqValue = fourcc;
        this.omxValue = omxValue;
    }

    private ColorFormat(String name, int omxValue, int bitsPerPixel) {
        this.name = name;
        this.bitsPerPixel = bitsPerPixel;
        this.sliqValue = fourcc(name);
        this.omxValue = omxValue;
    }

    public final int getBitsPerPixel() {
        return this.bitsPerPixel;
    }

    public final String getName() {
        return this.name;
    }

    public final Integer getSliqValue() {
        return Integer.valueOf(this.sliqValue);
    }

    public final Integer getOmxValue() {
        return Integer.valueOf(this.omxValue);
    }

    public static ColorFormat fromSliq(int value) {
        return (ColorFormat) getMapper().fromSliq(Integer.valueOf(value));
    }

    public static ColorFormat fromOmx(int value) {
        return (ColorFormat) getMapper().fromOmx(Integer.valueOf(value));
    }

    public static ColorFormat fromName(String name) {
        return (ColorFormat) getMapper().fromName(name);
    }

    private static synchronized FormatMapper<Integer, Integer, ColorFormat> getMapper() {
        FormatMapper<Integer, Integer, ColorFormat> formatMapper;
        synchronized (ColorFormat.class) {
            if (mapper == null) {
                mapper = new FormatMapper(values());
            }
            formatMapper = mapper;
        }
        return formatMapper;
    }

    private static int fourcc(CharSequence s) {
        if (s.length() != 4) {
            throw new IllegalArgumentException("four chars expected");
        }
        int n = 0;
        for (int i = 3; i >= 0; i--) {
            n = (n << 8) | (s.charAt(i) & 255);
        }
        return n;
    }
}
