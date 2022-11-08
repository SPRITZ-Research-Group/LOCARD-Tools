package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.LogConfiguration;
import com.microsoft.applications.telemetry.datamodels.DataPackage;
import com.microsoft.applications.telemetry.datamodels.Record;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Queue;

class RecordClassifier implements IRecordClassifier {
    private static final String LOG_TAG = ("[ACT]:" + RecordClassifier.class.getSimpleName().toUpperCase());
    private final LogConfiguration configuration;
    private ITransmissionEvents eventsHandler;
    private final IHttpClientManager httpClientManager;
    private final IInboundQueuesManager inboundQueuesManager;
    private final long maxRecordBatchSizeInBytes;
    private final HashMap<EventPriority, RecordBatcherHandler> recordBatcherHandlers = new HashMap();

    RecordClassifier(IInboundQueuesManager inboundQueuesManager, IHttpClientManager httpClientManager, LogConfiguration configuration, ITransmissionEvents eventsHandler, long maxRecordBatchSizeInBytes) {
        this.inboundQueuesManager = (IInboundQueuesManager) Preconditions.isNotNull(inboundQueuesManager, "inboundQueuesManager can not not be null.");
        this.httpClientManager = (IHttpClientManager) Preconditions.isNotNull(httpClientManager, "httpClientManager cannot be null.");
        this.configuration = (LogConfiguration) Preconditions.isNotNull(configuration, "log configuration cannot be null.");
        this.eventsHandler = (ITransmissionEvents) Preconditions.isNotNull(eventsHandler, "eventsHandler cannot be null.");
        this.maxRecordBatchSizeInBytes = maxRecordBatchSizeInBytes;
        this.recordBatcherHandlers.put(EventPriority.HIGH, new RecordBatcherHandler(this.maxRecordBatchSizeInBytes));
        this.recordBatcherHandlers.put(EventPriority.NORMAL, new RecordBatcherHandler(this.maxRecordBatchSizeInBytes));
        this.recordBatcherHandlers.put(EventPriority.LOW, new RecordBatcherHandler(this.maxRecordBatchSizeInBytes));
    }

    public boolean processForPriorityAndAbove(EventPriority eventPriority) {
        if (classifyRecordsFromInboundQueueForPriorityAndAbove(eventPriority)) {
            processBatchesForPriorityAndAbove(eventPriority);
        }
        if (this.inboundQueuesManager.checkIfSomethingToSendForPriority(EventPriority.LOW)) {
            return true;
        }
        return false;
    }

    private void processBatchesForPriorityAndAbove(EventPriority eventPriority) {
        TraceHelper.TraceDebug(LOG_TAG, String.format("classify min priority = %s ", new Object[]{eventPriority}));
        processOutboundBatchesWithPriority(EventPriority.HIGH);
        switch (eventPriority) {
            case NORMAL:
                processOutboundBatchesWithPriority(EventPriority.NORMAL);
                return;
            case LOW:
                processOutboundBatchesWithPriority(EventPriority.LOW);
                return;
            default:
                return;
        }
    }

    private boolean classifyRecordsFromInboundQueueForPriorityAndAbove(EventPriority eventPriority) {
        if (!this.inboundQueuesManager.checkIfSomethingToSendForPriority(eventPriority)) {
            return false;
        }
        for (Entry<EventPriority, Queue<RecordWithMetadata>> entry : this.inboundQueuesManager.getRecordsFromInboundQueueForPriorityAndAbove(eventPriority).entrySet()) {
            EventPriority batcherPriority = (EventPriority) entry.getKey();
            if (batcherPriority == EventPriority.IMMEDIATE) {
                batcherPriority = EventPriority.HIGH;
            }
            if (eventPriority == EventPriority.LOW && batcherPriority == EventPriority.NORMAL) {
                batcherPriority = EventPriority.LOW;
            }
            processQueue((Queue) entry.getValue(), (RecordBatcherHandler) this.recordBatcherHandlers.get(batcherPriority));
        }
        return true;
    }

    void processQueue(Queue<RecordWithMetadata> queue, RecordBatcherHandler batcherHandler) {
        while (!queue.isEmpty()) {
            batcherHandler.addRecordToAppropriateBatch((RecordWithMetadata) queue.remove());
        }
        batcherHandler.flushAllBatchers();
    }

    private void processOutboundBatchesWithPriority(EventPriority priority) {
        DataPackageCollection packagesToSend = new DataPackageCollection(false);
        ArrayList<Long> rowIds = new ArrayList();
        for (Entry<String, RecordBatcher> entry : ((RecordBatcherHandler) this.recordBatcherHandlers.get(priority)).getBatcherHashMap().entrySet()) {
            RecordBatcher batcher = (RecordBatcher) entry.getValue();
            String batchTenantToken = (String) entry.getKey();
            Iterator it = batcher.getBatches().iterator();
            while (it.hasNext()) {
                ArrayList<RecordWithMetadata> recordsWithMetaData = (ArrayList) it.next();
                ArrayList<Record> records = new ArrayList();
                long currentBatchSize = 0;
                EventPriority priorityOfPacakage = ((RecordWithMetadata) recordsWithMetaData.get(0)).getPriority();
                Iterator it2 = recordsWithMetaData.iterator();
                while (it2.hasNext()) {
                    RecordWithMetadata recordWithMetadata = (RecordWithMetadata) it2.next();
                    records.add(recordWithMetadata.getRecord());
                    currentBatchSize += (long) recordWithMetadata.getSizeBytes();
                    if (recordWithMetadata.getRowId() != -1) {
                        rowIds.add(Long.valueOf(recordWithMetadata.getRowId()));
                    }
                }
                DataPackage dataPackage = DataModelHelper.createDataPackage(records, this.configuration.getSource());
                while (!addToPackagesToSend(batchTenantToken, currentBatchSize, packagesToSend, dataPackage, rowIds, priorityOfPacakage)) {
                    this.httpClientManager.sendRequest(packagesToSend);
                    packagesToSend = new DataPackageCollection(false);
                }
                rowIds.clear();
            }
            batcher.resetBatcher();
        }
        if (packagesToSend.getSize() > 0) {
            this.httpClientManager.sendRequest(packagesToSend);
        }
    }

    private boolean addToPackagesToSend(String token, long size, DataPackageCollection packagesToSend, DataPackage dataPackage, ArrayList<Long> rowIds, EventPriority priority) {
        if (packagesToSend.getSize() + size > this.maxRecordBatchSizeInBytes) {
            return false;
        }
        packagesToSend.add(dataPackage, rowIds, size, priority, token);
        return true;
    }
}
