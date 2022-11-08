package com.microsoft.applications.telemetry.core;

import java.util.ArrayList;

final class RecordBatcher {
    private ArrayList<ArrayList<RecordWithMetadata>> batches;
    private ArrayList<RecordWithMetadata> currentBatch;
    private long currentBatchSizeInBytes;
    private long maxRecordBatchSizeInBytes;

    public RecordBatcher(long maxRecordBatchSizeInBytes) {
        Preconditions.isTrue(maxRecordBatchSizeInBytes > 0, "maxRecordBatchSizeInBytes should be greater than 0.");
        this.maxRecordBatchSizeInBytes = maxRecordBatchSizeInBytes;
        resetBatcher();
    }

    final void addRecord(RecordWithMetadata record) {
        if (wouldAddingExceedSizeLimit((long) record.getSizeBytes())) {
            flushBatch();
        }
        addToCurrentBatch(record);
        checkConditionsAndFlushBatchIfNeeded();
    }

    final void flushBatch() {
        if (this.currentBatch.size() > 0) {
            this.batches.add(this.currentBatch);
            resetBatch();
        }
    }

    private void resetBatch() {
        this.currentBatch = new ArrayList();
        this.currentBatchSizeInBytes = 0;
    }

    final ArrayList<ArrayList<RecordWithMetadata>> getBatches() {
        if (this.currentBatch.size() > 0) {
            flushBatch();
        }
        return this.batches;
    }

    final void resetBatcher() {
        this.batches = new ArrayList();
        resetBatch();
    }

    private boolean wouldAddingExceedSizeLimit(long recordToAddSizeBytes) {
        return this.maxRecordBatchSizeInBytes < this.currentBatchSizeInBytes + recordToAddSizeBytes;
    }

    private boolean sizeLimitReached() {
        return this.maxRecordBatchSizeInBytes <= this.currentBatchSizeInBytes;
    }

    private void checkConditionsAndFlushBatchIfNeeded() {
        if (isHardLimitReached()) {
            flushBatch();
        }
    }

    private boolean isHardLimitReached() {
        if (sizeLimitReached()) {
            return true;
        }
        return false;
    }

    private void addToCurrentBatch(RecordWithMetadata record) {
        long expectedBatchSize = this.currentBatchSizeInBytes + ((long) record.getSizeBytes());
        Preconditions.isTrue(expectedBatchSize <= this.maxRecordBatchSizeInBytes, String.format("_currentBatchSizeBytes [%d] would exceed limit [%d], #records in batch: {%d}, record size was: {%d}", new Object[]{Long.valueOf(expectedBatchSize), Long.valueOf(this.maxRecordBatchSizeInBytes), Integer.valueOf(this.currentBatch.size()), Integer.valueOf(record.getSizeBytes())}));
        this.currentBatch.add(record);
        this.currentBatchSizeInBytes = expectedBatchSize;
    }
}
