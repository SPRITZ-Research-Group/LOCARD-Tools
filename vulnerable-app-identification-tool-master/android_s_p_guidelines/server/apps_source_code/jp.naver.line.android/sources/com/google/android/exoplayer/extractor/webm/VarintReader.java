package com.google.android.exoplayer.extractor.webm;

import com.google.android.exoplayer.extractor.ExtractorInput;
import java.io.IOException;

final class VarintReader {
    private static final int STATE_BEGIN_READING = 0;
    private static final int STATE_READ_CONTENTS = 1;
    private static final int[] VARINT_LENGTH_MASKS = new int[]{128, 64, 32, 16, 8, 4, 2, 1};
    private int length;
    private final byte[] scratch = new byte[8];
    private int state;

    public final void reset() {
        this.state = 0;
        this.length = 0;
    }

    public final long readUnsignedVarint(ExtractorInput extractorInput, boolean z, boolean z2) throws IOException, InterruptedException {
        if (this.state == 0) {
            if (!extractorInput.readFully(this.scratch, 0, 1, z)) {
                return -1;
            }
            int i = this.scratch[0] & 255;
            this.length = -1;
            for (int i2 = 0; i2 < VARINT_LENGTH_MASKS.length; i2++) {
                if ((VARINT_LENGTH_MASKS[i2] & i) != 0) {
                    this.length = i2 + 1;
                    break;
                }
            }
            if (this.length != -1) {
                this.state = 1;
            } else {
                throw new IllegalStateException("No valid varint length mask found");
            }
        }
        extractorInput.readFully(this.scratch, 1, this.length - 1);
        if (z2) {
            byte[] bArr = this.scratch;
            bArr[0] = (byte) (bArr[0] & (VARINT_LENGTH_MASKS[this.length - 1] ^ -1));
        }
        long j = 0;
        for (int i3 = 0; i3 < this.length; i3++) {
            j = (j << 8) | ((long) (this.scratch[i3] & 255));
        }
        this.state = 0;
        return j;
    }

    public final int getLastLength() {
        return this.length;
    }
}
