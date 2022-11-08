package com.microsoft.applications.telemetry.core;

import java.util.HashMap;
import java.util.Map.Entry;

class RecordBatcherHandler {
    private HashMap<String, RecordBatcher> batcherHashMap;
    private long maxRecordBatchSizeInBytes;

    public RecordBatcherHandler(long maxRecordBatchSizeInBytes) {
        Preconditions.isTrue(maxRecordBatchSizeInBytes > 0, "maxRecordBatchSizeInBytes should be greater than 0.");
        this.maxRecordBatchSizeInBytes = maxRecordBatchSizeInBytes;
        this.batcherHashMap = new HashMap();
    }

    void flushAllBatchers() {
        for (Entry value : this.batcherHashMap.entrySet()) {
            ((RecordBatcher) value.getValue()).flushBatch();
        }
    }

    HashMap<String, RecordBatcher> getBatcherHashMap() {
        return this.batcherHashMap;
    }

    void addRecordToAppropriateBatch(RecordWithMetadata record) {
        if (record.hasTenantToken()) {
            RecordBatcher batcher;
            if (this.batcherHashMap.containsKey(record.getTenantToken())) {
                batcher = (RecordBatcher) this.batcherHashMap.get(record.getTenantToken());
            } else {
                batcher = new RecordBatcher(this.maxRecordBatchSizeInBytes);
                this.batcherHashMap.put(record.getTenantToken(), batcher);
            }
            batcher.addRecord(record);
        }
    }
}
