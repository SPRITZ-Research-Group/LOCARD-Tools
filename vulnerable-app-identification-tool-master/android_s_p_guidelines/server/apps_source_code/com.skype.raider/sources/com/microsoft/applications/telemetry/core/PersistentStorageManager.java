package com.microsoft.applications.telemetry.core;

import android.content.Context;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteFullException;
import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.LogConfiguration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Queue;

class PersistentStorageManager implements IPersistentStorageManager {
    private static final String LOG_TAG = ("[ACT]:" + PersistentStorageManager.class.getSimpleName().toUpperCase());
    private LogConfiguration configuration;
    private EventsHandler eventsHandler;
    private final Object eventsLock = new Object();
    private boolean isDbClosed;
    private final Object kvpLock = new Object();
    File offlineHighEventsFile;
    File offlineImmediateEventsFile;
    File offlineKVPFile;
    File offlineLowEventsFile;
    File offlineNormalEventsFile;
    SQLiteStorageHelper storageHelper;

    PersistentStorageManager(EventsHandler eventsHandler, LogConfiguration logConfiguration, Context context) {
        this.eventsHandler = (EventsHandler) Preconditions.isNotNull(eventsHandler, "eventsHandler can not be null.");
        this.configuration = (LogConfiguration) Preconditions.isNotNull(logConfiguration, "logConfiguration should not be null.");
        this.offlineKVPFile = new File(this.configuration.getOfflineKVPStoragePath());
        this.offlineImmediateEventsFile = new File(this.configuration.getCacheFilePath() + "immediate.db");
        this.offlineHighEventsFile = new File(this.configuration.getCacheFilePath() + "high.db");
        this.offlineNormalEventsFile = new File(this.configuration.getCacheFilePath() + "normal.db");
        this.offlineLowEventsFile = new File(this.configuration.getCacheFilePath() + "low.db");
        checkAndCreateDatabase(context);
        deleteOldStorageFiles();
        this.isDbClosed = false;
    }

    private void checkAndCreateDatabase(Context context) {
        int databaseSize = this.configuration.getCacheFileSizeLimitInBytes();
        try {
            this.storageHelper = new SQLiteStorageHelper(context, databaseSize, this.eventsHandler);
        } catch (SQLiteCantOpenDatabaseException e) {
            this.storageHelper = new SQLiteStorageHelper(context, databaseSize, this.eventsHandler, null);
        }
        this.storageHelper.resetInflight();
    }

    private void deleteOldStorageFiles() {
        if (this.offlineKVPFile.exists()) {
            byte[] flt = getKVPValue("FirstLaunchTime");
            if (flt.length > 0) {
                this.storageHelper.addKeyValueToPropertiesTable("FirstLaunchTime", new String(flt));
            }
            byte[] sdkUUID = getKVPValue("SDKUid");
            if (sdkUUID.length > 0) {
                this.storageHelper.addKeyValueToPropertiesTable("SDKUid", new String(sdkUUID));
            }
            this.offlineKVPFile.delete();
        }
        if (this.offlineImmediateEventsFile.exists()) {
            this.offlineImmediateEventsFile.delete();
        }
        if (this.offlineHighEventsFile.exists()) {
            this.offlineHighEventsFile.delete();
        }
        if (this.offlineNormalEventsFile.exists()) {
            this.offlineNormalEventsFile.delete();
        }
        if (this.offlineLowEventsFile.exists()) {
            this.offlineLowEventsFile.delete();
        }
    }

    public void markRecordsReturned(DataPackageCollection request) {
        synchronized (this.eventsLock) {
            if (!this.isDbClosed) {
                this.storageHelper.markRecordsReturned(request);
            }
        }
    }

    public void storeRecord(RecordWithMetadata recordWithMetadata, int inflight) {
        synchronized (this.eventsLock) {
            if (!this.isDbClosed) {
                try {
                    this.storageHelper.writeRecordOffline(recordWithMetadata, inflight);
                } catch (SQLiteFullException e) {
                    handleDiskFullAndNoMoreSpaceToDoAnything();
                }
            }
        }
    }

    public HashMap<EventPriority, Queue<RecordWithMetadata>> getRecordsFromStorageForPriority(EventPriority priority) {
        HashMap<EventPriority, Queue<RecordWithMetadata>> queues = new HashMap();
        synchronized (this.eventsLock) {
            if (!this.isDbClosed) {
                try {
                    if (checkStorageForPriority(priority)) {
                        queues = this.storageHelper.getRecordsForPriorityAndAbove(priority);
                    }
                } catch (SQLiteFullException e) {
                    handleDiskFullAndNoMoreSpaceToDoAnything();
                }
            }
        }
        for (Entry<EventPriority, Queue<RecordWithMetadata>> entry : queues.entrySet()) {
            statsUpdateForQueue((EventPriority) entry.getKey(), (Queue) entry.getValue());
        }
        return queues;
    }

    private void handleDiskFullAndNoMoreSpaceToDoAnything() {
        try {
            closeSQLiteDatabase();
            this.storageHelper.deleteDbFile();
        } catch (Exception e) {
            this.isDbClosed = true;
        }
    }

    private void statsUpdateForQueue(EventPriority priority, Queue<RecordWithMetadata> eventsQueue) {
        for (RecordWithMetadata recordWithMetadata : eventsQueue) {
            this.eventsHandler.transition(EventTransition.OFFLINE_TO_FLIGHT, 1, priority, recordWithMetadata.getTenantToken());
        }
    }

    public boolean checkStorageForPriority(EventPriority priority) {
        return this.storageHelper.checkStorageForPriority(priority);
    }

    public void closeSQLiteDatabase() {
        synchronized (this.eventsLock) {
            if (this.storageHelper != null) {
                this.storageHelper.close();
            }
            this.isDbClosed = true;
        }
    }

    public void removeRecords(ArrayList<Long> rowIds) {
        synchronized (this.eventsLock) {
            if (!this.isDbClosed && rowIds.size() > 0) {
                try {
                    this.storageHelper.removeRecords(rowIds, true);
                } catch (SQLiteFullException e) {
                    handleDiskFullAndNoMoreSpaceToDoAnything();
                }
            }
        }
    }

    long getKVPLongValue(String key) {
        try {
            String value = this.storageHelper.readValueFromPropertiesTable(key);
            if (value != null) {
                return Long.valueOf(value).longValue();
            }
        } catch (Exception e) {
            TraceHelper.TraceError(LOG_TAG, "Tried to get a long value that did not exist for key: " + key);
        }
        return Long.MIN_VALUE;
    }

    void putKVPLongValue(String key, long value) {
        this.storageHelper.addKeyValueToPropertiesTable(key, String.valueOf(value));
    }

    String getKVPStringValue(String key) {
        try {
            String value = this.storageHelper.readValueFromPropertiesTable(key);
            if (value != null) {
                return value;
            }
        } catch (Exception e) {
            TraceHelper.TraceError(LOG_TAG, "Tried to get a string value that did not exist for key: " + key);
        }
        return null;
    }

    void putKVPStringValue(String key, String value) {
        try {
            this.storageHelper.addKeyValueToPropertiesTable(key, value);
        } catch (Exception e) {
            TraceHelper.TraceError(LOG_TAG, "Tried to store an invalid string value for key: " + key);
        }
    }

    private byte[] getKVPValue(String key) {
        byte[] bArr;
        Preconditions.isNotNullOrEmpty(key, "key to get from offline kvp can't be null or empty");
        synchronized (this.kvpLock) {
            OfflineKVPFile data = readKVPFile();
            if (data == null || !data.KVPMap.containsKey(key)) {
                bArr = new byte[0];
            } else {
                bArr = (byte[]) data.KVPMap.get(key);
            }
        }
        return bArr;
    }

    private OfflineKVPFile readKVPFile() {
        Exception e;
        Throwable th;
        OfflineKVPFile data = null;
        ObjectInputStream is = null;
        try {
            if (this.offlineKVPFile.exists()) {
                TraceHelper.TraceDebug(LOG_TAG, "Reading offline kvp file.");
                ObjectInputStream is2 = new ObjectInputStream(new FileInputStream(this.offlineKVPFile));
                try {
                    data = (OfflineKVPFile) is2.readObject();
                    is = is2;
                } catch (Exception e2) {
                    e = e2;
                    is = is2;
                    try {
                        TraceHelper.TraceError(LOG_TAG, "Error reading offline kvp file: " + e.getMessage());
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e3) {
                                TraceHelper.TraceError(LOG_TAG, "Error closing offline kvp file: " + e3.getMessage());
                            }
                        }
                        return data;
                    } catch (Throwable th2) {
                        th = th2;
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e32) {
                                TraceHelper.TraceError(LOG_TAG, "Error closing offline kvp file: " + e32.getMessage());
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    is = is2;
                    if (is != null) {
                        is.close();
                    }
                    throw th;
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e322) {
                    TraceHelper.TraceError(LOG_TAG, "Error closing offline kvp file: " + e322.getMessage());
                }
            }
        } catch (Exception e4) {
            e = e4;
            TraceHelper.TraceError(LOG_TAG, "Error reading offline kvp file: " + e.getMessage());
            if (is != null) {
                is.close();
            }
            return data;
        }
        return data;
    }

    void deleteAndCreateDatabase() {
        this.storageHelper.deleteAndCreateTables();
    }
}
