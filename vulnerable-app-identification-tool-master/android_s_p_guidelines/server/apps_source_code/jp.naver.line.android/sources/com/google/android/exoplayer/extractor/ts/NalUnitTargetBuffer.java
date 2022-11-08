package com.google.android.exoplayer.extractor.ts;

import com.google.android.exoplayer.util.Assertions;
import java.util.Arrays;

final class NalUnitTargetBuffer {
    private boolean isCompleted;
    private boolean isFilling;
    public byte[] nalData;
    public int nalLength;
    private final int targetType;

    public NalUnitTargetBuffer(int i, int i2) {
        this.targetType = i;
        this.nalData = new byte[(i2 + 3)];
        this.nalData[2] = (byte) 1;
    }

    public final void reset() {
        this.isFilling = false;
        this.isCompleted = false;
    }

    public final boolean isCompleted() {
        return this.isCompleted;
    }

    public final void startNalUnit(int i) {
        boolean z = true;
        Assertions.checkState(this.isFilling ^ true);
        if (i != this.targetType) {
            z = false;
        }
        this.isFilling = z;
        if (this.isFilling) {
            this.nalLength = 3;
            this.isCompleted = false;
        }
    }

    public final void appendToNalUnit(byte[] bArr, int i, int i2) {
        if (this.isFilling) {
            i2 -= i;
            if (this.nalData.length < this.nalLength + i2) {
                this.nalData = Arrays.copyOf(this.nalData, (this.nalLength + i2) * 2);
            }
            System.arraycopy(bArr, i, this.nalData, this.nalLength, i2);
            this.nalLength += i2;
        }
    }

    public final boolean endNalUnit(int i) {
        if (!this.isFilling) {
            return false;
        }
        this.nalLength -= i;
        this.isFilling = false;
        this.isCompleted = true;
        return true;
    }
}
