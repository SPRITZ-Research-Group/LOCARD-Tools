package com.google.android.exoplayer.extractor.webm;

import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.IOException;

final class Sniffer {
    private static final int ID_EBML = 440786851;
    private static final int SEARCH_LENGTH = 1024;
    private int peekLength;
    private final ParsableByteArray scratch = new ParsableByteArray(8);

    public final boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        long readUnsignedInt;
        Sniffer sniffer = this;
        ExtractorInput extractorInput2 = extractorInput;
        long length = extractorInput.getLength();
        long j = 1024;
        if (length != -1 && length <= 1024) {
            j = length;
        }
        int i = (int) j;
        extractorInput2.peekFully(sniffer.scratch.data, 0, 4);
        sniffer.peekLength = 4;
        for (readUnsignedInt = sniffer.scratch.readUnsignedInt(); readUnsignedInt != 440786851; readUnsignedInt = ((readUnsignedInt << 8) & -256) | ((long) (sniffer.scratch.data[0] & 255))) {
            int i2 = sniffer.peekLength + 1;
            sniffer.peekLength = i2;
            if (i2 == i) {
                return false;
            }
            extractorInput2.peekFully(sniffer.scratch.data, 0, 1);
        }
        readUnsignedInt = readUint(extractorInput);
        long j2 = (long) sniffer.peekLength;
        if (readUnsignedInt == Long.MIN_VALUE || (length != -1 && j2 + readUnsignedInt >= length)) {
            return false;
        }
        while (true) {
            long j3 = j2 + readUnsignedInt;
            if (((long) sniffer.peekLength) < j3) {
                if (readUint(extractorInput) == Long.MIN_VALUE) {
                    return false;
                }
                length = readUint(extractorInput);
                if (length < 0 || length > 2147483647L) {
                    return false;
                }
                if (length != 0) {
                    extractorInput2.advancePeekPosition((int) length);
                    sniffer.peekLength = (int) (((long) sniffer.peekLength) + length);
                }
            } else if (((long) sniffer.peekLength) == j3) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private long readUint(ExtractorInput extractorInput) throws IOException, InterruptedException {
        int i = 0;
        extractorInput.peekFully(this.scratch.data, 0, 1);
        int i2 = this.scratch.data[0] & 255;
        if (i2 == 0) {
            return Long.MIN_VALUE;
        }
        int i3 = 128;
        int i4 = 0;
        while ((i2 & i3) == 0) {
            i3 >>= 1;
            i4++;
        }
        i2 &= i3 ^ -1;
        extractorInput.peekFully(this.scratch.data, 1, i4);
        while (i < i4) {
            i++;
            i2 = (this.scratch.data[i] & 255) + (i2 << 8);
        }
        this.peekLength += i4 + 1;
        return (long) i2;
    }
}
