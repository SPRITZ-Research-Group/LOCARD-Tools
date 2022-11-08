package com.google.android.exoplayer.extractor;

import java.io.IOException;

public interface ExtractorInput {
    void advancePeekPosition(int i) throws IOException, InterruptedException;

    long getLength();

    long getPosition();

    void peekFully(byte[] bArr, int i, int i2) throws IOException, InterruptedException;

    int read(byte[] bArr, int i, int i2) throws IOException, InterruptedException;

    void readFully(byte[] bArr, int i, int i2) throws IOException, InterruptedException;

    boolean readFully(byte[] bArr, int i, int i2, boolean z) throws IOException, InterruptedException;

    void resetPeekPosition();

    void skipFully(int i) throws IOException, InterruptedException;
}
