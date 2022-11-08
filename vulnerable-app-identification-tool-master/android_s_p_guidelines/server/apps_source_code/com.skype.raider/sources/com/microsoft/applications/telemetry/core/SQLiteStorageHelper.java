package com.microsoft.applications.telemetry.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.TenantTokenEntry;
import com.microsoft.applications.telemetry.datamodels.DataPackage;
import com.microsoft.applications.telemetry.datamodels.Record;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.zip.CRC32;

class SQLiteStorageHelper extends SQLiteOpenHelper {
    private static final String BLOB_TYPE = " BLOB";
    private static final String COMMA_SEP = ",";
    private static final String DATABASE_NAME = "AriaStorage.db";
    private static final int DATABASE_VERSION = 2;
    private static final String INTEGER_PRIMARY_KEY = " INTEGER PRIMARY KEY";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final long INVALID_ROW_ID = -1;
    private static final String LOG_TAG = ("[ACT]:" + PersistentStorageManager.class.getSimpleName().toUpperCase());
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String RECORD_DROP_LIMIT = "20";
    private static final int RECORD_READ_LIMIT_PER_PRIORITY = 100;
    private static final String SQL_ADD_INFLIGHT_COLUMN = "ALTER TABLE events ADD COLUMN inflight INTEGER DEFAULT 0";
    private static final String SQL_CREATE_EVENTS = "CREATE TABLE events (_id INTEGER PRIMARY KEY,tenanttoken INTEGER,priority INTEGER,eventtimestamp INTEGER,eventretrycount INTEGER,clockcorrectionenabled INTEGER,eventcrc INTEGER,event BLOB,inflight INTEGER )";
    private static final String SQL_CREATE_PROPERTIES = "CREATE TABLE properties (key TEXT PRIMARY KEY,value TEXT )";
    private static final String SQL_CREATE_TENANTTOKENS = "CREATE TABLE tenanttokens (_id INTEGER PRIMARY KEY,tenanttoken BLOB )";
    private static final String SQL_DELETE_EVENTS = "DROP TABLE IF EXISTS events";
    private static final String SQL_DELETE_PROPERTIES = "DROP TABLE IF EXISTS properties";
    private static final String SQL_DELETE_TENANTTOKENS = "DROP TABLE IF EXISTS tenanttokens";
    private static final int TENANT_TOKEN_SIZE = 74;
    private static final String TEXT_TYPE = " TEXT";
    private Context context = null;
    private final CRC32 crc = new CRC32();
    private int databaseSize;
    private EventsHandler eventsHandler;
    private final long maxRecordSizeInBytes = 2000000;
    private HashMap<Long, String> tenantTokenMap = new HashMap();

    SQLiteStorageHelper(Context context, int databaseSize, EventsHandler eventsHandler) {
        super(context, DATABASE_NAME, null, 2);
        this.context = context;
        initializeHelper(databaseSize, eventsHandler);
    }

    SQLiteStorageHelper(Context context, int databaseSize, EventsHandler eventsHandler, String databaseName) {
        super(context, databaseName, null, 2);
        this.context = context;
        initializeHelper(databaseSize, eventsHandler);
    }

    private void initializeHelper(int databaseSize, EventsHandler eventsHandler) {
        getWritableDatabase().setMaximumSize((long) databaseSize);
        this.databaseSize = databaseSize;
        this.eventsHandler = (EventsHandler) Preconditions.isNotNull(eventsHandler, "eventsHandler can not be null.");
    }

    void resetInflight() {
        SQLiteDatabase db = getWritableDatabase();
        while (true) {
            try {
                ContentValues cv = new ContentValues();
                cv.put(EventsEntry.COLUMN_NAME_INFLIGHT, Integer.valueOf(0));
                db.update(EventsEntry.TABLE_NAME, cv, "inflight = 1", null);
                break;
            } catch (SQLiteFullException e) {
                dropRecordsToEmptySpace(db, true);
            }
        }
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PROPERTIES);
        db.execSQL(SQL_CREATE_EVENTS);
        db.execSQL(SQL_CREATE_TENANTTOKENS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            switch (newVersion) {
                case 2:
                    db.execSQL(SQL_ADD_INFLIGHT_COLUMN);
                    return;
                default:
                    return;
            }
        }
    }

    synchronized void addKeyValueToPropertiesTable(String key, String value) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PropertiesEntry.COLUMN_NAME_KEY, key);
        values.put(PropertiesEntry.COLUMN_NAME_VALUE, value);
        while (true) {
            try {
                db.replaceOrThrow(PropertiesEntry.TABLE_NAME, null, values);
            } catch (SQLiteFullException e) {
                dropRecordsToEmptySpace(db, false);
            }
        }
    }

    String readValueFromPropertiesTable(String key) {
        String value = null;
        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().query(PropertiesEntry.TABLE_NAME, new String[]{PropertiesEntry.COLUMN_NAME_VALUE}, "key LIKE ?", new String[]{key}, null, null, null);
            if (cursor.moveToFirst()) {
                value = cursor.getString(0);
            }
            if (cursor != null) {
                cursor.close();
            }
            return value;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    void markRecordsReturned(DataPackageCollection request) {
        ArrayList<Long> rowIds = new ArrayList();
        for (String token : request.getTokenToDataPackages().keySet()) {
            rowIds.addAll((Collection) request.getTokenToRowIds().get(token));
        }
        Iterator it;
        if (rowIds.size() > 0) {
            boolean isSuccess = markRowIdsReturned(rowIds);
            for (Entry<String, HashMap<DataPackage, EventPriority>> tokenToDataPackages : request.getTokenToDataPackages().entrySet()) {
                for (Entry<DataPackage, EventPriority> kvp : ((HashMap) tokenToDataPackages.getValue()).entrySet()) {
                    it = ((DataPackage) kvp.getKey()).getRecords().iterator();
                    while (it.hasNext()) {
                        Record record = (Record) it.next();
                        if (isSuccess) {
                            TraceHelper.TraceInformation(LOG_TAG, String.format("Stage Save: event name=%s, event priority=%s, id=%s, tenantId=%s", new Object[]{record.getEventType(), kvp.getValue(), record.getId(), DataModelHelper.getTenantId((String) tokenToDataPackages.getKey())}));
                        } else {
                            TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, tenantId=%s, request id=%s, reason = Could not be returned to db", new Object[]{record.getEventType(), kvp.getValue(), record.getId(), DataModelHelper.getTenantId((String) tokenToDataPackages.getKey()), request.getId()}));
                            this.eventsHandler.eventDropped(record, (EventPriority) kvp.getValue(), (String) tokenToDataPackages.getKey(), EventDropReason.OFFLINE_FULL);
                        }
                    }
                }
            }
            return;
        }
        for (Entry<String, HashMap<DataPackage, EventPriority>> tokenToDataPackages2 : request.getTokenToDataPackages().entrySet()) {
            for (Entry<DataPackage, EventPriority> kvp2 : ((HashMap) tokenToDataPackages2.getValue()).entrySet()) {
                it = ((DataPackage) kvp2.getKey()).getRecords().iterator();
                while (it.hasNext()) {
                    writeRecordOffline(new RecordWithMetadata((Record) it.next(), (EventPriority) kvp2.getValue(), (String) tokenToDataPackages2.getKey()), 0);
                }
            }
        }
    }

    private boolean markRowIdsReturned(ArrayList<Long> rowIds) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            ContentValues cv = new ContentValues();
            cv.put(EventsEntry.COLUMN_NAME_INFLIGHT, Integer.valueOf(0));
            db.update(EventsEntry.TABLE_NAME, cv, "inflight = 1", null);
            db.setTransactionSuccessful();
            db.endTransaction();
            return true;
        } catch (SQLiteFullException e) {
            removeRecords(rowIds, true);
            return false;
        }
    }

    void writeRecordOffline(RecordWithMetadata recordWithMetadata, int inflight) throws SQLiteFullException {
        try {
            if (recordWithMetadata.getTenantToken().length() != 74) {
                TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, tenantId=%s, reason=Invalid tenant token.", new Object[]{recordWithMetadata.getRecord().getEventType(), recordWithMetadata.getPriority(), recordWithMetadata.getRecord().getId(), DataModelHelper.getTenantId(recordWithMetadata.getTenantToken())}));
                this.eventsHandler.eventDropped(recordWithMetadata.getRecord(), recordWithMetadata.getPriority(), recordWithMetadata.getTenantToken(), EventDropReason.BAD_TENANT_OFFLINE);
                return;
            }
            try {
                byte[] serializedRecord = DataModelHelper.serializeRecord(recordWithMetadata.getRecord());
                int sizeBytes = serializedRecord.length;
                if (((long) sizeBytes) > 2000000) {
                    TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, tenantId=%s, size=%s, reason=Record was too large.", new Object[]{recordWithMetadata.getRecord().getEventType(), recordWithMetadata.getPriority(), recordWithMetadata.getRecord().getId(), DataModelHelper.getTenantId(recordWithMetadata.getTenantToken()), Integer.valueOf(sizeBytes)}));
                    this.eventsHandler.eventRejected(recordWithMetadata.getRecord(), recordWithMetadata.getPriority(), recordWithMetadata.getTenantToken(), EventRejectedReason.EVENT_SIZE_LIMIT_EXCEEDED_WHEN_STORING_OFFLINE);
                    return;
                }
                recordWithMetadata.setSizeBytes(sizeBytes);
                if (sizeBytes > this.databaseSize) {
                    TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, tenantId=%s, reason=Record to large to store offline", new Object[]{recordWithMetadata.getRecord().getEventType(), recordWithMetadata.getPriority(), recordWithMetadata.getRecord().getId(), DataModelHelper.getTenantId(recordWithMetadata.getTenantToken())}));
                    this.eventsHandler.eventDropped(recordWithMetadata.getRecord(), recordWithMetadata.getPriority(), recordWithMetadata.getTenantToken(), EventDropReason.OFFLINE_FAIL);
                    return;
                }
                TraceHelper.TraceInformation(LOG_TAG, String.format("Stage Save: event name=%s, event priority=%s, id=%s, tenantId=%s", new Object[]{recordWithMetadata.getRecord().getEventType(), recordWithMetadata.getPriority(), recordWithMetadata.getRecord().getId(), DataModelHelper.getTenantId(recordWithMetadata.getTenantToken())}));
                this.crc.reset();
                this.crc.update(serializedRecord, 0, sizeBytes);
                long tenantId = addAndGetTenantTokenId(recordWithMetadata.getTenantToken());
                if (tenantId != -1) {
                    long rowId = addRecord(recordWithMetadata.getPriority().getValue(), recordWithMetadata.getRecord().getTimestamp(), this.crc.getValue(), tenantId, serializedRecord, inflight);
                    recordWithMetadata.setRowId(rowId);
                    if (rowId != -1) {
                        return;
                    }
                }
                TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, tenantId=%s, reason=Db is full", new Object[]{recordWithMetadata.getRecord().getEventType(), recordWithMetadata.getPriority(), recordWithMetadata.getRecord().getId(), DataModelHelper.getTenantId(recordWithMetadata.getTenantToken())}));
                this.eventsHandler.eventDropped(recordWithMetadata.getRecord(), recordWithMetadata.getPriority(), recordWithMetadata.getTenantToken(), EventDropReason.OFFLINE_FULL);
            } catch (IOException e) {
                TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, tenantId=%s, reason=Record failed to be serialized", new Object[]{recordWithMetadata.getRecord().getEventType(), recordWithMetadata.getPriority(), recordWithMetadata.getRecord().getId(), DataModelHelper.getTenantId(recordWithMetadata.getTenantToken())}));
                this.eventsHandler.eventDropped(recordWithMetadata.getRecord(), recordWithMetadata.getPriority(), recordWithMetadata.getTenantToken(), EventDropReason.SERIALIZATION_FAIL_OFFLINE);
            }
        } catch (Exception e2) {
            TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, tenantId=%s reason=Record dropped from offline due to exception, ex=%s", new Object[]{recordWithMetadata.getRecord().getEventType(), recordWithMetadata.getPriority(), recordWithMetadata.getRecord().getId(), DataModelHelper.getTenantId(recordWithMetadata.getTenantToken()), e2.toString()}));
            this.eventsHandler.eventDropped(recordWithMetadata.getRecord(), recordWithMetadata.getPriority(), recordWithMetadata.getTenantToken(), EventDropReason.OFFLINE_FAIL);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long addAndGetTenantTokenId(String tenantToken) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = null;
        long rowId = 0;
        try {
            cursor = db.query(TenantTokenEntry.TABLE_NAME, new String[]{"_id"}, "tenanttoken LIKE ?", new String[]{tenantToken}, null, null, null);
            if (cursor.moveToFirst()) {
                rowId = (long) cursor.getInt(0);
            }
            if (cursor != null) {
                cursor.close();
            }
            return rowId;
        } catch (SQLiteFullException e) {
            if (!dropRecordsToEmptySpace(db, false)) {
                return -1;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
        if (rowId == 0) {
            ContentValues values = new ContentValues();
            values.put("tenanttoken", tenantToken);
            do {
                rowId = db.insertOrThrow(TenantTokenEntry.TABLE_NAME, null, values);
            } while (dropRecordsToEmptySpace(db, false));
            return -1;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private String getTenantTokenFromId(long id, SQLiteDatabase db) {
        if (this.tenantTokenMap.containsKey(Long.valueOf(id))) {
            return (String) this.tenantTokenMap.get(Long.valueOf(id));
        }
        Cursor cursor = null;
        try {
            cursor = db.query(TenantTokenEntry.TABLE_NAME, new String[]{"tenanttoken"}, "_id LIKE ?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor.moveToFirst()) {
                this.tenantTokenMap.put(Long.valueOf(id), cursor.getString(0));
            }
            if (cursor == null) {
                return null;
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private long addRecord(int priority, long timestamp, long crc, long tenantTokenId, byte[] record, int inflight) throws SQLiteFullException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EventsEntry.COLUMN_NAME_PRIORITY, Integer.valueOf(priority));
        values.put(EventsEntry.COLUMN_NAME_EVENT_TIMESTAMP, Long.valueOf(timestamp));
        values.put(EventsEntry.COLUMN_NAME_EVENT_RETRY_COUNT, Integer.valueOf(0));
        values.put(EventsEntry.COLUMN_NAME_CLOCK_CORRECTION_ENABLED, Integer.valueOf(0));
        values.put(EventsEntry.COLUMN_NAME_EVENT_CRC, Long.valueOf(crc));
        values.put("tenanttoken", Long.valueOf(tenantTokenId));
        values.put(EventsEntry.COLUMN_NAME_EVENT, record);
        values.put(EventsEntry.COLUMN_NAME_INFLIGHT, Integer.valueOf(inflight));
        do {
            try {
                return db.insertOrThrow(EventsEntry.TABLE_NAME, null, values);
            } catch (SQLiteFullException e) {
                if (!dropRecordsToEmptySpace(db, false)) {
                    return -1;
                }
            }
        } while (dropRecordsToEmptySpace(db, false));
        return -1;
    }

    HashMap<EventPriority, Queue<RecordWithMetadata>> getRecordsForPriorityAndAbove(EventPriority priority) {
        Cursor cursor;
        boolean priorityProcessed;
        String limit;
        int countPriorityProcessed;
        int countPriorityProcessed2;
        Throwable e;
        Throwable th;
        HashMap<EventPriority, Queue<RecordWithMetadata>> recordsQueues = new HashMap();
        int maxPriority = EventPriority.IMMEDIATE.getValue();
        SQLiteDatabase db = getWritableDatabase();
        String[] projection = new String[]{"_id", "tenanttoken", EventsEntry.COLUMN_NAME_EVENT, EventsEntry.COLUMN_NAME_EVENT_CRC};
        String selection = "priority LIKE ? AND inflight LIKE ?";
        ArrayList<Long> rowIdsRead = new ArrayList();
        ArrayList<Long> rowIdsDropped = new ArrayList();
        int i = priority.getValue();
        while (i <= maxPriority) {
            String[] selectionArgs = new String[]{String.valueOf(i), "0"};
            cursor = null;
            priorityProcessed = false;
            limit = "100";
            countPriorityProcessed = 1;
            while (!priorityProcessed) {
                ArrayList<Long> rowIdsReadForThisAttempt = new ArrayList();
                ArrayList<Long> rowIdsDroppedForThisAttempt = new ArrayList();
                try {
                    cursor = db.query(EventsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null, limit);
                    if (cursor.moveToFirst()) {
                        if (!recordsQueues.containsKey(EventPriority.fromValue(i))) {
                            recordsQueues.put(EventPriority.fromValue(i), new LinkedList());
                        }
                        do {
                            byte[] serialziedRecord = cursor.getBlob(cursor.getColumnIndexOrThrow(EventsEntry.COLUMN_NAME_EVENT));
                            long checksum = cursor.getLong(cursor.getColumnIndexOrThrow(EventsEntry.COLUMN_NAME_EVENT_CRC));
                            String tenantToken = getTenantTokenFromId(cursor.getLong(cursor.getColumnIndexOrThrow("tenanttoken")), db);
                            Record record = null;
                            long rowId = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                            try {
                                record = DataModelHelper.deserializeRecord(serialziedRecord);
                            } catch (OutOfMemoryError e2) {
                                TraceHelper.TraceError(LOG_TAG, "Out of memory exception thrown by Bond. " + e2.getMessage());
                            } catch (Exception e3) {
                                TraceHelper.TraceError(LOG_TAG, "Stored record was corrupted. " + e3.getMessage());
                            }
                            if (record != null) {
                                if (tenantToken != null) {
                                    if (tenantToken.length() == 74) {
                                        this.crc.reset();
                                        this.crc.update(serialziedRecord);
                                        if (this.crc.getValue() == checksum) {
                                            RecordWithMetadata recordWithMetadata = new RecordWithMetadata(record, EventPriority.fromValue(i), tenantToken);
                                            recordWithMetadata.setSizeBytes(serialziedRecord.length);
                                            recordWithMetadata.setRowId(rowId);
                                            ((Queue) recordsQueues.get(EventPriority.fromValue(i))).add(recordWithMetadata);
                                            TraceHelper.TraceInformation(LOG_TAG, String.format("Stage Load: event name=%s, event priority=%s, id=%s, tenantId=%s", new Object[]{recordWithMetadata.getRecord().getEventType(), recordWithMetadata.getPriority(), recordWithMetadata.getRecord().getId(), DataModelHelper.getTenantId(recordWithMetadata.getTenantToken())}));
                                            rowIdsReadForThisAttempt.add(Long.valueOf(rowId));
                                        } else {
                                            TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, tenantId=%s reason=Record corrupted from offline", new Object[]{record.getEventType(), EventPriority.fromValue(i), record.getId(), DataModelHelper.getTenantId(tenantToken)}));
                                            this.eventsHandler.eventDropped(record, EventPriority.fromValue(i), tenantToken, EventDropReason.EVENT_CORRUPT);
                                            this.eventsHandler.logCorruptEvent(record, tenantToken);
                                            rowIdsDroppedForThisAttempt.add(Long.valueOf(rowId));
                                        }
                                    }
                                }
                                TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, reason=Invalid tenant token.", new Object[]{record.getEventType(), EventPriority.fromValue(i), record.getId()}));
                                this.eventsHandler.eventDropped(record, EventPriority.fromValue(i), "", EventDropReason.BAD_TENANT_OFFLINE);
                                rowIdsDroppedForThisAttempt.add(Long.valueOf(rowId));
                            } else {
                                TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event priority=%s, tenantId=%s, reason=Record corrupted from offline", new Object[]{EventPriority.fromValue(i), DataModelHelper.getTenantId(tenantToken)}));
                                this.eventsHandler.eventDropped(null, EventPriority.fromValue(i), tenantToken, EventDropReason.EVENT_CORRUPT);
                                rowIdsDroppedForThisAttempt.add(Long.valueOf(rowId));
                            }
                        } while (cursor.moveToNext());
                        rowIdsRead.addAll(rowIdsReadForThisAttempt);
                        rowIdsDropped.addAll(rowIdsDroppedForThisAttempt);
                        if (rowIdsReadForThisAttempt.size() + rowIdsDroppedForThisAttempt.size() < 100) {
                            priorityProcessed = true;
                            countPriorityProcessed2 = countPriorityProcessed;
                        } else {
                            countPriorityProcessed2 = countPriorityProcessed + 1;
                            try {
                                limit = String.valueOf(countPriorityProcessed * 100) + ", 100";
                            } catch (Exception e4) {
                                e = e4;
                            }
                        }
                    } else {
                        priorityProcessed = true;
                        countPriorityProcessed2 = countPriorityProcessed;
                    }
                    if (cursor != null) {
                        cursor.close();
                        countPriorityProcessed = countPriorityProcessed2;
                    }
                } catch (Exception e5) {
                    e = e5;
                    countPriorityProcessed2 = countPriorityProcessed;
                } catch (Throwable th2) {
                    th = th2;
                    countPriorityProcessed2 = countPriorityProcessed;
                }
                countPriorityProcessed = countPriorityProcessed2;
            }
            i++;
        }
        db.beginTransaction();
        if (rowIdsDropped.size() > 0) {
            removeRecords(rowIdsDropped, false);
        }
        if (rowIdsRead.size() > 0) {
            try {
                ContentValues cv = new ContentValues();
                cv.put(EventsEntry.COLUMN_NAME_INFLIGHT, Integer.valueOf(1));
                db.update(EventsEntry.TABLE_NAME, cv, "_id IN (" + TextUtils.join(COMMA_SEP, rowIdsRead) + ")", null);
            } catch (SQLiteFullException e6) {
                removeRecords(rowIdsRead, true);
                for (Queue<RecordWithMetadata> it : recordsQueues.values()) {
                    for (RecordWithMetadata rowId2 : it) {
                        rowId2.setRowId(-1);
                    }
                }
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        return recordsQueues;
        try {
            if (e.getMessage().contains("android.database.CursorWindowAllocationException")) {
                dropRecordsForPriorityWithoutReadingBlobs(db, i, limit);
                this.eventsHandler.logException(e);
                priorityProcessed = true;
                if (cursor != null) {
                    cursor.close();
                    countPriorityProcessed = countPriorityProcessed2;
                }
                countPriorityProcessed = countPriorityProcessed2;
            } else {
                throw e;
            }
        } catch (Throwable th3) {
            th = th3;
        }
        if (cursor != null) {
            cursor.close();
        }
        throw th;
    }

    void removeRecords(ArrayList<Long> rowIds, boolean beginTransaction) {
        SQLiteDatabase db = getWritableDatabase();
        if (beginTransaction) {
            db.beginTransaction();
        }
        db.delete(EventsEntry.TABLE_NAME, "_id IN (" + TextUtils.join(COMMA_SEP, rowIds) + ")", null);
        if (beginTransaction) {
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }

    private boolean dropRecordsToEmptySpace(SQLiteDatabase db, boolean dropInflightAsWell) {
        TraceHelper.TraceDebug(LOG_TAG, "SQLite database full. Dropping records.");
        String selection = "inflight LIKE 0";
        if (dropInflightAsWell) {
            selection = null;
        }
        String[] projection = BuildConfig.DEBUG ? new String[]{"_id", EventsEntry.COLUMN_NAME_PRIORITY, "tenanttoken", EventsEntry.COLUMN_NAME_EVENT} : new String[]{"_id", EventsEntry.COLUMN_NAME_PRIORITY, "tenanttoken"};
        Cursor cursor = null;
        ArrayList<Long> rowIdsToDelete = new ArrayList();
        cursor = db.query(EventsEntry.TABLE_NAME, projection, selection, null, null, null, "priority ASC, eventtimestamp ASC", RECORD_DROP_LIMIT);
        if (cursor.moveToFirst()) {
            do {
                rowIdsToDelete.add(Long.valueOf(cursor.getLong(cursor.getColumnIndex("_id"))));
                String tenantToken = getTenantTokenFromId(cursor.getLong(cursor.getColumnIndex("tenanttoken")), db);
                EventPriority priority = EventPriority.fromValue(cursor.getInt(cursor.getColumnIndex(EventsEntry.COLUMN_NAME_PRIORITY)));
                Record record = null;
                if (BuildConfig.DEBUG) {
                    try {
                        record = DataModelHelper.deserializeRecord(cursor.getBlob(cursor.getColumnIndex(EventsEntry.COLUMN_NAME_EVENT)));
                        TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, tenantId=%s reason=Record dropped from offline to empty some space.", new Object[]{record.getEventType(), priority, record.getId(), DataModelHelper.getTenantId(tenantToken)}));
                    } catch (Exception e) {
                        TraceHelper.TraceError(LOG_TAG, "Error deserializing record.");
                    } catch (Throwable th) {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                }
                this.eventsHandler.eventDropped(record, priority, tenantToken, EventDropReason.OFFLINE_FULL);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        if (rowIdsToDelete.size() <= 0) {
            return false;
        }
        db.delete(EventsEntry.TABLE_NAME, "_id IN (" + TextUtils.join(COMMA_SEP, rowIdsToDelete) + ")", null);
        return true;
    }

    private void dropRecordsForPriorityWithoutReadingBlobs(SQLiteDatabase db, int priority, String limit) {
        TraceHelper.TraceDebug(LOG_TAG, "SQLite database full. Dropping records.");
        String[] projection = new String[]{"_id", "tenanttoken"};
        String selection = "priority LIKE ? AND inflight LIKE 0";
        String[] selectionArgs = new String[]{String.valueOf(priority)};
        Cursor cursor = null;
        ArrayList<Long> rowIdsToDelete = new ArrayList();
        try {
            cursor = db.query(EventsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null, limit);
            if (cursor.moveToFirst()) {
                do {
                    rowIdsToDelete.add(Long.valueOf(cursor.getLong(cursor.getColumnIndex("_id"))));
                    String tenantToken = getTenantTokenFromId(cursor.getLong(cursor.getColumnIndex("tenanttoken")), db);
                    TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event name=N/A, event priority=%s, id=N/A, tenantId=%s reason=Record dropped from offline because it could not be read.", new Object[]{EventPriority.fromValue(priority), DataModelHelper.getTenantId(tenantToken)}));
                    this.eventsHandler.eventDropped(null, EventPriority.fromValue(priority), tenantToken, EventDropReason.OFFLINE_FULL);
                } while (cursor.moveToNext());
            }
            if (cursor != null) {
                cursor.close();
            }
            if (rowIdsToDelete.size() > 0) {
                db.delete(EventsEntry.TABLE_NAME, "_id IN (" + TextUtils.join(COMMA_SEP, rowIdsToDelete) + ")", null);
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    boolean checkStorageForPriority(EventPriority priority) {
        Cursor cursor = null;
        boolean checkStorage = false;
        try {
            cursor = getReadableDatabase().rawQuery("SELECT count(*) FROM events WHERE priority>=? AND inflight = 0", new String[]{String.valueOf(priority.getValue())});
            if (cursor.moveToFirst()) {
                checkStorage = cursor.getInt(0) > 0;
            }
            if (cursor != null) {
                cursor.close();
            }
            return checkStorage;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    void deleteDbFile() {
        this.context.getDatabasePath(DATABASE_NAME).delete();
    }

    void deleteAndCreateTables() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_DELETE_PROPERTIES);
        db.execSQL(SQL_DELETE_EVENTS);
        db.execSQL(SQL_DELETE_TENANTTOKENS);
        db.execSQL(SQL_CREATE_PROPERTIES);
        db.execSQL(SQL_CREATE_EVENTS);
        db.execSQL(SQL_CREATE_TENANTTOKENS);
    }

    Cursor executeStatement(String statement, String[] selectionArgs) {
        return getWritableDatabase().rawQuery(statement, selectionArgs);
    }
}
