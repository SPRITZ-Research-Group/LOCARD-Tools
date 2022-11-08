package com.microsoft.a.a;

import java.io.IOException;

public final class a {
    public static int a(int src, byte[] dst, int offset) {
        if ((src & -128) != 0) {
            offset++;
            dst[0] = (byte) ((src & 127) | 128);
            src >>>= 7;
            if ((src & -128) != 0) {
                offset++;
                dst[1] = (byte) ((src & 127) | 128);
                src >>>= 7;
                if ((src & -128) != 0) {
                    offset++;
                    dst[2] = (byte) ((src & 127) | 128);
                    src >>>= 7;
                    if ((src & -128) != 0) {
                        offset++;
                        dst[3] = (byte) ((src & 127) | 128);
                        src >>>= 7;
                    }
                }
            }
        }
        int offset2 = offset + 1;
        dst[offset] = (byte) (src & 127);
        return offset2;
    }

    public static int a(long src, byte[] dst, int offset) {
        if ((-128 & src) != 0) {
            offset++;
            dst[0] = (byte) ((int) ((127 & src) | 128));
            src >>>= 7;
            if ((-128 & src) != 0) {
                offset++;
                dst[1] = (byte) ((int) ((127 & src) | 128));
                src >>>= 7;
                if ((-128 & src) != 0) {
                    offset++;
                    dst[2] = (byte) ((int) ((127 & src) | 128));
                    src >>>= 7;
                    if ((-128 & src) != 0) {
                        offset++;
                        dst[3] = (byte) ((int) ((127 & src) | 128));
                        src >>>= 7;
                        if ((-128 & src) != 0) {
                            offset++;
                            dst[4] = (byte) ((int) ((127 & src) | 128));
                            src >>>= 7;
                            if ((-128 & src) != 0) {
                                offset++;
                                dst[5] = (byte) ((int) ((127 & src) | 128));
                                src >>>= 7;
                                if ((-128 & src) != 0) {
                                    offset++;
                                    dst[6] = (byte) ((int) ((127 & src) | 128));
                                    src >>>= 7;
                                    if ((-128 & src) != 0) {
                                        offset++;
                                        dst[7] = (byte) ((int) ((127 & src) | 128));
                                        src >>>= 7;
                                        if ((-128 & src) != 0) {
                                            offset++;
                                            dst[8] = (byte) ((int) ((127 & src) | 128));
                                            src >>>= 7;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        int offset2 = offset + 1;
        dst[offset] = (byte) ((int) (127 & src));
        return offset2;
    }

    public static long a(com.microsoft.a.b.a stream) throws IOException {
        long result = 0;
        for (int shift = 0; shift < 64; shift += 7) {
            byte raw = stream.a();
            result |= ((long) (raw & 127)) << shift;
            if ((raw & 128) == 0) {
                break;
            }
        }
        return result;
    }
}
