package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

interface IPersistentStorageManager {
    boolean checkStorageForPriority(EventPriority eventPriority);

    void closeSQLiteDatabase();

    HashMap<EventPriority, Queue<RecordWithMetadata>> getRecordsFromStorageForPriority(EventPriority eventPriority);

    void markRecordsReturned(DataPackageCollection dataPackageCollection);

    void removeRecords(ArrayList<Long> arrayList);

    void storeRecord(RecordWithMetadata recordWithMetadata, int i);
}
