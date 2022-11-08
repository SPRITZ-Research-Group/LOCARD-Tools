package com.google.android.exoplayer.extractor.mp3;

import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.EOFException;
import java.io.IOException;
import java.nio.BufferOverflowException;

final class BufferingInput {
    private final ParsableByteArray buffer;
    private final int capacity;
    private int markPosition;
    private int readPosition;
    private int writePosition;

    public BufferingInput(int i) {
        this.capacity = i;
        this.buffer = new ParsableByteArray(i * 2);
    }

    public final void reset() {
        this.readPosition = 0;
        this.writePosition = 0;
        this.markPosition = 0;
    }

    public final void mark() {
        if (this.readPosition > this.capacity) {
            System.arraycopy(this.buffer.data, this.readPosition, this.buffer.data, 0, this.writePosition - this.readPosition);
            this.writePosition -= this.readPosition;
            this.readPosition = 0;
        }
        this.markPosition = this.readPosition;
    }

    public final void returnToMark() {
        this.readPosition = this.markPosition;
    }

    public final int getAvailableByteCount() {
        return this.writePosition - this.readPosition;
    }

    public final ParsableByteArray getParsableByteArray(ExtractorInput extractorInput, int i) throws IOException, InterruptedException {
        if (ensureLoaded(extractorInput, i)) {
            ParsableByteArray parsableByteArray = new ParsableByteArray(this.buffer.data, this.writePosition);
            parsableByteArray.setPosition(this.readPosition);
            this.readPosition += i;
            return parsableByteArray;
        }
        throw new EOFException();
    }

    public final int drainToOutput(TrackOutput trackOutput, int i) {
        if (i == 0) {
            return 0;
        }
        this.buffer.setPosition(this.readPosition);
        i = Math.min(this.writePosition - this.readPosition, i);
        trackOutput.sampleData(this.buffer, i);
        this.readPosition += i;
        return i;
    }

    public final void skip(ExtractorInput extractorInput, int i) throws IOException, InterruptedException {
        if (!readInternal(extractorInput, null, 0, i)) {
            throw new EOFException();
        }
    }

    public final void read(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        if (!readInternal(extractorInput, bArr, i, i2)) {
            throw new EOFException();
        }
    }

    public final boolean readAllowingEndOfInput(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        return readInternal(extractorInput, bArr, i, i2);
    }

    private boolean readInternal(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        if (!ensureLoaded(extractorInput, i2)) {
            return false;
        }
        if (bArr != null) {
            System.arraycopy(this.buffer.data, this.readPosition, bArr, i, i2);
        }
        this.readPosition += i2;
        return true;
    }

    private boolean ensureLoaded(ExtractorInput extractorInput, int i) throws InterruptedException, IOException {
        if ((this.readPosition + i) - this.markPosition <= this.capacity) {
            i -= this.writePosition - this.readPosition;
            if (i > 0) {
                if (!extractorInput.readFully(this.buffer.data, this.writePosition, i, true)) {
                    return false;
                }
                this.writePosition += i;
            }
            return true;
        }
        throw new BufferOverflowException();
    }
}
