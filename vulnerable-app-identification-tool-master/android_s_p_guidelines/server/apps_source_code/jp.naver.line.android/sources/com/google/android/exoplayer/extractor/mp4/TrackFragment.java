package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.IOException;

final class TrackFragment {
    public boolean definesEncryptionData;
    public int length;
    public int[] sampleCompositionTimeOffsetTable;
    public long[] sampleDecodingTimeTable;
    public int sampleDescriptionIndex;
    public ParsableByteArray sampleEncryptionData;
    public int sampleEncryptionDataLength;
    public boolean sampleEncryptionDataNeedsFill;
    public boolean[] sampleHasSubsampleEncryptionTable;
    public boolean[] sampleIsSyncFrameTable;
    public int[] sampleSizeTable;

    TrackFragment() {
    }

    public final void reset() {
        this.length = 0;
        this.definesEncryptionData = false;
        this.sampleEncryptionDataNeedsFill = false;
    }

    public final void initTables(int i) {
        this.length = i;
        if (this.sampleSizeTable == null || this.sampleSizeTable.length < this.length) {
            i = (i * 125) / 100;
            this.sampleSizeTable = new int[i];
            this.sampleCompositionTimeOffsetTable = new int[i];
            this.sampleDecodingTimeTable = new long[i];
            this.sampleIsSyncFrameTable = new boolean[i];
            this.sampleHasSubsampleEncryptionTable = new boolean[i];
        }
    }

    public final void initEncryptionData(int i) {
        if (this.sampleEncryptionData == null || this.sampleEncryptionData.limit() < i) {
            this.sampleEncryptionData = new ParsableByteArray(i);
        }
        this.sampleEncryptionDataLength = i;
        this.definesEncryptionData = true;
        this.sampleEncryptionDataNeedsFill = true;
    }

    public final void fillEncryptionData(ExtractorInput extractorInput) throws IOException, InterruptedException {
        extractorInput.readFully(this.sampleEncryptionData.data, 0, this.sampleEncryptionDataLength);
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }

    public final void fillEncryptionData(ParsableByteArray parsableByteArray) {
        parsableByteArray.readBytes(this.sampleEncryptionData.data, 0, this.sampleEncryptionDataLength);
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }

    public final long getSamplePresentationTime(int i) {
        return this.sampleDecodingTimeTable[i] + ((long) this.sampleCompositionTimeOffsetTable[i]);
    }
}
