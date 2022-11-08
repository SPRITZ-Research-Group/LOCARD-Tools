package com.google.android.exoplayer.util;

public final class ParsableBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    public byte[] data;

    public ParsableBitArray(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public ParsableBitArray(byte[] bArr, int i) {
        this.data = bArr;
        this.byteLimit = i;
    }

    public final void reset(byte[] bArr) {
        reset(bArr, bArr.length);
    }

    public final void reset(byte[] bArr, int i) {
        this.data = bArr;
        this.byteOffset = 0;
        this.bitOffset = 0;
        this.byteLimit = i;
    }

    public final int bitsLeft() {
        return ((this.byteLimit - this.byteOffset) * 8) - this.bitOffset;
    }

    public final int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public final void setPosition(int i) {
        this.byteOffset = i / 8;
        this.bitOffset = i - (this.byteOffset * 8);
        assertValidOffset();
    }

    public final void skipBits(int i) {
        this.byteOffset += i / 8;
        this.bitOffset += i % 8;
        if (this.bitOffset > 7) {
            this.byteOffset++;
            this.bitOffset -= 8;
        }
        assertValidOffset();
    }

    public final boolean readBit() {
        return readBits(1) == 1;
    }

    public final int readBits(int i) {
        int i2 = 0;
        if (i == 0) {
            return 0;
        }
        while (i >= 8) {
            int i3;
            if (this.bitOffset != 0) {
                i3 = ((this.data[this.byteOffset + 1] & 255) >>> (8 - this.bitOffset)) | ((this.data[this.byteOffset] & 255) << this.bitOffset);
            } else {
                i3 = this.data[this.byteOffset];
            }
            i -= 8;
            i2 |= (255 & i3) << i;
            this.byteOffset++;
        }
        if (i > 0) {
            int i4 = this.bitOffset + i;
            byte b = (byte) (255 >> (8 - i));
            if (i4 > 8) {
                i = (b & (((255 & this.data[this.byteOffset + 1]) >> (16 - i4)) | ((this.data[this.byteOffset] & 255) << (i4 - 8)))) | i2;
                this.byteOffset++;
            } else {
                i = (b & ((255 & this.data[this.byteOffset]) >> (8 - i4))) | i2;
                if (i4 == 8) {
                    this.byteOffset++;
                }
            }
            i2 = i;
            this.bitOffset = i4 % 8;
        }
        assertValidOffset();
        return i2;
    }

    public final int peekExpGolombCodedNumLength() {
        int i = this.byteOffset;
        int i2 = this.bitOffset;
        Object obj = null;
        int i3 = 0;
        while (this.byteOffset < this.byteLimit && !readBit()) {
            i3++;
        }
        if (this.byteOffset == this.byteLimit) {
            obj = 1;
        }
        this.byteOffset = i;
        this.bitOffset = i2;
        return obj != null ? -1 : (i3 * 2) + 1;
    }

    public final int readUnsignedExpGolombCodedInt() {
        return readExpGolombCodeNum();
    }

    public final int readSignedExpGolombCodedInt() {
        int readExpGolombCodeNum = readExpGolombCodeNum();
        return (readExpGolombCodeNum % 2 == 0 ? -1 : 1) * ((readExpGolombCodeNum + 1) / 2);
    }

    private int readExpGolombCodeNum() {
        int i = 0;
        int i2 = 0;
        while (!readBit()) {
            i2++;
        }
        int i3 = (1 << i2) - 1;
        if (i2 > 0) {
            i = readBits(i2);
        }
        return i3 + i;
    }

    private void assertValidOffset() {
        boolean z = this.byteOffset >= 0 && this.bitOffset >= 0 && this.bitOffset < 8 && (this.byteOffset < this.byteLimit || (this.byteOffset == this.byteLimit && this.bitOffset == 0));
        Assertions.checkState(z);
    }
}
