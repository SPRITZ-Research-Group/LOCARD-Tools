package com.google.android.exoplayer;

public final class CodecCounters {
    public int codecInitCount;
    public int codecReleaseCount;
    public int droppedOutputBufferCount;
    public int outputBuffersChangedCount;
    public int outputFormatChangedCount;
    public int renderedOutputBufferCount;
    public int skippedOutputBufferCount;

    public final synchronized void ensureUpdated() {
    }

    public final String getDebugString() {
        ensureUpdated();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("cic:");
        stringBuilder.append(this.codecInitCount);
        stringBuilder.append(" crc:");
        stringBuilder.append(this.codecReleaseCount);
        stringBuilder.append(" ofc:");
        stringBuilder.append(this.outputFormatChangedCount);
        stringBuilder.append(" obc:");
        stringBuilder.append(this.outputBuffersChangedCount);
        stringBuilder.append(" ren:");
        stringBuilder.append(this.renderedOutputBufferCount);
        stringBuilder.append(" sob:");
        stringBuilder.append(this.skippedOutputBufferCount);
        stringBuilder.append(" dob:");
        stringBuilder.append(this.droppedOutputBufferCount);
        return stringBuilder.toString();
    }
}
