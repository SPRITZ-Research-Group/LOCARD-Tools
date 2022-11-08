package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.extractor.g;
import java.io.IOException;

final class h {
    private static final int[] a = new int[]{s.e("isom"), s.e("iso2"), s.e("iso3"), s.e("iso4"), s.e("iso5"), s.e("iso6"), s.e("avc1"), s.e("hvc1"), s.e("hev1"), s.e("mp41"), s.e("mp42"), s.e("3g2a"), s.e("3g2b"), s.e("3gr6"), s.e("3gs6"), s.e("3ge6"), s.e("3gg6"), s.e("M4V "), s.e("M4A "), s.e("f4v "), s.e("kddi"), s.e("M4VP"), s.e("qt  "), s.e("MSNV")};

    public static boolean a(g input) throws IOException, InterruptedException {
        return a(input, true);
    }

    public static boolean b(g input) throws IOException, InterruptedException {
        return a(input, false);
    }

    private static boolean a(g input, boolean fragmented) throws IOException, InterruptedException {
        long inputLength = input.d();
        if (inputLength == -1 || inputLength > 4096) {
            inputLength = 4096;
        }
        int bytesToSearch = (int) inputLength;
        k buffer = new k(64);
        int bytesSearched = 0;
        boolean foundGoodFileType = false;
        boolean isFragmented = false;
        while (bytesSearched < bytesToSearch) {
            int headerSize = 8;
            buffer.a(8);
            input.c(buffer.a, 0, 8);
            long atomSize = buffer.l();
            int atomType = buffer.n();
            if (atomSize == 1) {
                headerSize = 16;
                input.c(buffer.a, 8, 8);
                buffer.b(16);
                atomSize = buffer.v();
            }
            if (atomSize >= ((long) headerSize)) {
                bytesSearched += headerSize;
                if (atomType != a.B) {
                    if (atomType != a.K && atomType != a.M) {
                        if ((((long) bytesSearched) + atomSize) - ((long) headerSize) >= ((long) bytesToSearch)) {
                            break;
                        }
                        int atomDataSize = (int) (atomSize - ((long) headerSize));
                        bytesSearched += atomDataSize;
                        if (atomType == a.a) {
                            if (atomDataSize < 8) {
                                return false;
                            }
                            buffer.a(atomDataSize);
                            input.c(buffer.a, 0, atomDataSize);
                            int brandsCount = atomDataSize / 4;
                            for (int i = 0; i < brandsCount; i++) {
                                if (i == 1) {
                                    buffer.d(4);
                                } else {
                                    Object obj;
                                    int n = buffer.n();
                                    if ((n >>> 8) == s.e("3gp")) {
                                        obj = 1;
                                    } else {
                                        for (int i2 : a) {
                                            if (i2 == n) {
                                                obj = 1;
                                                break;
                                            }
                                        }
                                        obj = null;
                                    }
                                    if (obj != null) {
                                        foundGoodFileType = true;
                                        break;
                                    }
                                }
                            }
                            if (!foundGoodFileType) {
                                return false;
                            }
                        } else if (atomDataSize != 0) {
                            input.c(atomDataSize);
                        }
                    } else {
                        isFragmented = true;
                        break;
                    }
                }
            } else {
                return false;
            }
        }
        if (foundGoodFileType && fragmented == isFragmented) {
            return true;
        }
        return false;
    }
}
