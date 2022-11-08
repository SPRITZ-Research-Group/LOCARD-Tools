package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;

final class Sniffer {
    private static final int[] COMPATIBLE_BRANDS = new int[]{Util.getIntegerCodeForString("isom"), Util.getIntegerCodeForString("iso2"), Util.getIntegerCodeForString("avc1"), Util.getIntegerCodeForString("hvc1"), Util.getIntegerCodeForString("hev1"), Util.getIntegerCodeForString("mp41"), Util.getIntegerCodeForString("mp42"), Util.getIntegerCodeForString("3g2a"), Util.getIntegerCodeForString("3g2b"), Util.getIntegerCodeForString("3gr6"), Util.getIntegerCodeForString("3gs6"), Util.getIntegerCodeForString("3ge6"), Util.getIntegerCodeForString("3gg6"), Util.getIntegerCodeForString("M4V "), Util.getIntegerCodeForString("M4A "), Util.getIntegerCodeForString("f4v "), Util.getIntegerCodeForString("kddi"), Util.getIntegerCodeForString("M4VP"), Util.getIntegerCodeForString("qt  "), Util.getIntegerCodeForString("MSNV")};

    public static boolean sniffFragmented(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return sniffInternal(extractorInput, 4096, true);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return sniffInternal(extractorInput, 128, false);
    }

    private static boolean sniffInternal(ExtractorInput extractorInput, int i, boolean z) throws IOException, InterruptedException {
        boolean z2;
        ExtractorInput extractorInput2 = extractorInput;
        int i2 = i;
        long length = extractorInput.getLength();
        if (length == -1 || length > ((long) i2)) {
            length = (long) i2;
        }
        i2 = (int) length;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        int i3 = 0;
        Object obj = null;
        while (i3 < i2) {
            int i4;
            extractorInput2.peekFully(parsableByteArray.data, 0, 8);
            parsableByteArray.setPosition(0);
            long readUnsignedInt = parsableByteArray.readUnsignedInt();
            int readInt = parsableByteArray.readInt();
            if (readUnsignedInt == 1) {
                extractorInput2.peekFully(parsableByteArray.data, 8, 8);
                readUnsignedInt = parsableByteArray.readLong();
                i4 = 16;
            } else {
                i4 = 8;
            }
            if (readUnsignedInt >= ((long) i4) && readUnsignedInt <= 2147483647L) {
                long j = ((long) i3) + readUnsignedInt;
                if (j > ((long) i2)) {
                    break;
                }
                i3 = ((int) readUnsignedInt) - i4;
                if (readInt == Atom.TYPE_ftyp) {
                    if (i3 >= 8) {
                        i3 = ((i3 - 8) / 4) + 2;
                        extractorInput2.peekFully(parsableByteArray.data, 0, i3 * 4);
                        for (readInt = 0; readInt < i3; readInt++) {
                            if (readInt != 1 && isCompatibleBrand(parsableByteArray.readInt())) {
                                obj = 1;
                                break;
                            }
                        }
                    } else {
                        return false;
                    }
                } else if (readInt == Atom.TYPE_moof) {
                    z2 = true;
                    break;
                } else if (i3 != 0) {
                    extractorInput2.advancePeekPosition(i3);
                }
                i3 = (int) j;
            } else {
                return false;
            }
        }
        z2 = false;
        return obj != null && z == z2;
    }

    private static boolean isCompatibleBrand(int i) {
        if ((i >>> 8) == Util.getIntegerCodeForString("3gp")) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private Sniffer() {
    }
}
