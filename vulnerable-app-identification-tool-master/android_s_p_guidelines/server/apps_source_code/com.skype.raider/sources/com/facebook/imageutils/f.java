package com.facebook.imageutils;

import android.util.Pair;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

public final class f {
    @Nullable
    public static Pair<Integer, Integer> a(InputStream is) {
        Pair<Integer, Integer> pair = null;
        byte[] headerBuffer = new byte[4];
        try {
            is.read(headerBuffer);
            if (a(headerBuffer, "RIFF")) {
                b(is);
                is.read(headerBuffer);
                if (a(headerBuffer, "WEBP")) {
                    is.read(headerBuffer);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < 4; i++) {
                        stringBuilder.append((char) headerBuffer[i]);
                    }
                    String headerAsString = stringBuilder.toString();
                    Pair<Integer, Integer> pair2;
                    if ("VP8 ".equals(headerAsString)) {
                        is.skip(7);
                        short e = e(is);
                        short e2 = e(is);
                        short e3 = e(is);
                        if (e == (short) 157 && e2 == (short) 1 && e3 == (short) 42) {
                            pair2 = new Pair(Integer.valueOf(c(is)), Integer.valueOf(c(is)));
                        } else {
                            pair2 = null;
                        }
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        return pair2;
                    } else if ("VP8L".equals(headerAsString)) {
                        b(is);
                        if (f(is) == (byte) 47) {
                            int read = ((byte) is.read()) & 255;
                            pair = new Pair(Integer.valueOf(((((byte) is.read()) & 255) | ((read & 63) << 8)) + 1), Integer.valueOf((((((((byte) is.read()) & 255) & 15) << 10) | ((((byte) is.read()) & 255) << 2)) | ((read & 192) >> 6)) + 1));
                        }
                        if (is == null) {
                            return pair;
                        }
                        try {
                            is.close();
                            return pair;
                        } catch (IOException e5) {
                            e5.printStackTrace();
                            return pair;
                        }
                    } else if ("VP8X".equals(headerAsString)) {
                        is.skip(8);
                        pair2 = new Pair(Integer.valueOf(d(is) + 1), Integer.valueOf(d(is) + 1));
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e42) {
                                e42.printStackTrace();
                            }
                        }
                        return pair2;
                    } else if (is == null) {
                        return null;
                    } else {
                        try {
                            is.close();
                            return null;
                        } catch (IOException e52) {
                            e52.printStackTrace();
                            return null;
                        }
                    }
                } else if (is == null) {
                    return null;
                } else {
                    try {
                        is.close();
                        return null;
                    } catch (IOException e522) {
                        e522.printStackTrace();
                        return null;
                    }
                }
            } else if (is == null) {
                return null;
            } else {
                try {
                    is.close();
                    return null;
                } catch (IOException e5222) {
                    e5222.printStackTrace();
                    return null;
                }
            }
        } catch (IOException e52222) {
            e52222.printStackTrace();
            if (is == null) {
                return null;
            }
            try {
                is.close();
                return null;
            } catch (IOException e522222) {
                e522222.printStackTrace();
                return null;
            }
        } catch (Throwable th) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e422) {
                    e422.printStackTrace();
                }
            }
        }
    }

    private static boolean a(byte[] what, String with) {
        if (4 != with.length()) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (with.charAt(i) != what[i]) {
                return false;
            }
        }
        return true;
    }

    private static int b(InputStream is) throws IOException {
        return ((((((byte) is.read()) << 24) & -16777216) | ((((byte) is.read()) << 16) & 16711680)) | ((((byte) is.read()) << 8) & 65280)) | (((byte) is.read()) & 255);
    }

    private static int c(InputStream is) throws IOException {
        return ((((byte) is.read()) << 8) & 65280) | (((byte) is.read()) & 255);
    }

    private static int d(InputStream is) throws IOException {
        byte byte1 = f(is);
        return (((f(is) << 16) & 16711680) | ((f(is) << 8) & 65280)) | (byte1 & 255);
    }

    private static short e(InputStream is) throws IOException {
        return (short) (is.read() & 255);
    }

    private static byte f(InputStream is) throws IOException {
        return (byte) (is.read() & 255);
    }
}
