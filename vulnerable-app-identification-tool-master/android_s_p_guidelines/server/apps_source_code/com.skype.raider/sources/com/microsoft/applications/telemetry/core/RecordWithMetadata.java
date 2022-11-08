package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.datamodels.Record;

final class RecordWithMetadata {
    private EventPriority priority;
    private Record record;
    private long rowId;
    private int sizeBytes;
    private String tenantToken;

    public RecordWithMetadata(Record record, EventPriority priority) {
        this.sizeBytes = -1;
        this.rowId = -1;
        this.record = (Record) Preconditions.isNotNull(record, "record cannot be null");
        if (priority != EventPriority.UNSPECIFIED) {
            this.priority = priority;
        } else {
            this.priority = EventPriority.NORMAL;
        }
    }

    public RecordWithMetadata(Record record, EventPriority priority, String tenantToken) {
        this(record, priority);
        this.tenantToken = tenantToken;
    }

    final void setRecord(Record record) {
        this.record = record;
    }

    final Record getRecord() {
        return this.record;
    }

    final void setTenantToken(String tenantToken) {
        this.tenantToken = tenantToken;
    }

    final String getTenantToken() {
        return this.tenantToken;
    }

    final boolean hasTenantToken() {
        return (this.tenantToken == null || this.tenantToken.isEmpty()) ? false : true;
    }

    final void setSizeBytes(int sizeBytes) {
        this.sizeBytes = sizeBytes;
    }

    final int getSizeBytes() {
        return this.sizeBytes;
    }

    final void setPriority(EventPriority priority) {
        if (priority != EventPriority.UNSPECIFIED) {
            this.priority = priority;
        }
    }

    final EventPriority getPriority() {
        return this.priority;
    }

    final void setRowId(long rowId) {
        this.rowId = rowId;
    }

    final long getRowId() {
        return this.rowId;
    }
}
