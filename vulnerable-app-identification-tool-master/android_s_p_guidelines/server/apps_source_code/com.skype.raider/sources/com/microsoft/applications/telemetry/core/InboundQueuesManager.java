package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.datamodels.Record;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

class InboundQueuesManager implements IInboundQueuesManager {
    private static final String LOG_TAG = ("[ACT]:" + InboundQueuesManager.class.getSimpleName().toUpperCase());
    private final String dataPackageSource;
    private EventsHandler eventsHandler;
    private final IHttpClientManager httpClientManager;
    private final IPersistentStorageManager persistentStorageManager;

    InboundQueuesManager(EventsHandler eventsHandler, IPersistentStorageManager persistentStorageManager, IHttpClientManager httpClientManager, String dataPackageSource) {
        this.eventsHandler = (EventsHandler) Preconditions.isNotNull(eventsHandler, "eventsHandler can not be null.");
        this.persistentStorageManager = (IPersistentStorageManager) Preconditions.isNotNull(persistentStorageManager, "persistentStorageManager can not be null");
        this.httpClientManager = (IHttpClientManager) Preconditions.isNotNull(httpClientManager, "httpClientManager cannot be null.");
        this.dataPackageSource = Preconditions.isNotNullOrEmpty(dataPackageSource, "log configuration cannot be null or empty.");
    }

    void sendRecord(RecordWithMetadata recordWithMetadata) {
        int i = 0;
        TraceHelper.TraceInformation(LOG_TAG, String.format("Stage Queue: event name=%s, event priority=%s, id=%s, tenantId=%s", new Object[]{recordWithMetadata.getRecord().getEventType(), recordWithMetadata.getPriority(), recordWithMetadata.getRecord().getId(), DataModelHelper.getTenantId(recordWithMetadata.getTenantToken())}));
        this.eventsHandler.transition(EventTransition.TO_OFFLINE, 1, recordWithMetadata.getPriority(), recordWithMetadata.getTenantToken());
        IPersistentStorageManager iPersistentStorageManager = this.persistentStorageManager;
        if (recordWithMetadata.getPriority() == EventPriority.IMMEDIATE) {
            i = 1;
        }
        iPersistentStorageManager.storeRecord(recordWithMetadata, i);
        if (recordWithMetadata.getPriority() == EventPriority.IMMEDIATE) {
            this.eventsHandler.transition(EventTransition.OFFLINE_TO_FLIGHT, 1, recordWithMetadata.getPriority(), recordWithMetadata.getTenantToken());
            sendRecordImmediate(recordWithMetadata);
        }
    }

    void returnRecordsToStorage(DataPackageCollection request) {
        this.persistentStorageManager.markRecordsReturned(request);
    }

    void removeRecords(ArrayList<Long> rowIds) {
        this.persistentStorageManager.removeRecords(rowIds);
    }

    private void sendRecordImmediate(RecordWithMetadata recordWithMetadata) {
        ArrayList<Record> records = new ArrayList();
        ArrayList<Long> rowId = new ArrayList();
        if (recordWithMetadata.getRowId() != -1) {
            rowId.add(Long.valueOf(recordWithMetadata.getRowId()));
        }
        records.add(recordWithMetadata.getRecord());
        DataPackageCollection dpCollection = new DataPackageCollection(true);
        dpCollection.add(DataModelHelper.createDataPackage(records, this.dataPackageSource), rowId, (long) recordWithMetadata.getSizeBytes(), EventPriority.IMMEDIATE, recordWithMetadata.getTenantToken());
        this.httpClientManager.sendRequest(dpCollection);
    }

    public HashMap<EventPriority, Queue<RecordWithMetadata>> getRecordsFromInboundQueueForPriorityAndAbove(EventPriority eventPriority) {
        TraceHelper.TraceDebug(LOG_TAG, String.format("Processing inbound queues with minimum priority: " + eventPriority, new Object[0]));
        return this.persistentStorageManager.getRecordsFromStorageForPriority(eventPriority);
    }

    public boolean checkIfSomethingToSendForPriority(EventPriority minPriority) {
        return this.persistentStorageManager.checkStorageForPriority(minPriority);
    }
}
