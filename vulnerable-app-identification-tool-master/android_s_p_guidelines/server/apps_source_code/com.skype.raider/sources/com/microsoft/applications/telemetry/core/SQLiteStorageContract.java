package com.microsoft.applications.telemetry.core;

import android.provider.BaseColumns;

final class SQLiteStorageContract {

    public static abstract class EventsEntry implements BaseColumns {
        public static final String COLUMN_NAME_CLOCK_CORRECTION_ENABLED = "clockcorrectionenabled";
        public static final String COLUMN_NAME_EVENT = "event";
        public static final String COLUMN_NAME_EVENT_CRC = "eventcrc";
        public static final String COLUMN_NAME_EVENT_RETRY_COUNT = "eventretrycount";
        public static final String COLUMN_NAME_EVENT_TIMESTAMP = "eventtimestamp";
        public static final String COLUMN_NAME_INFLIGHT = "inflight";
        public static final String COLUMN_NAME_PRIORITY = "priority";
        public static final String COLUMN_NAME_TENANT_TOKEN = "tenanttoken";
        public static final String TABLE_NAME = "events";
    }

    public static abstract class PropertiesEntry implements BaseColumns {
        public static final String COLUMN_NAME_KEY = "key";
        public static final String COLUMN_NAME_VALUE = "value";
        public static final String TABLE_NAME = "properties";
    }

    public static abstract class TenantTokenEntry implements BaseColumns {
        public static final String COLUMN_NAME_TENANT_TOKEN = "tenanttoken";
        public static final String TABLE_NAME = "tenanttokens";
    }

    public SQLiteStorageContract() {
        throw new AssertionError();
    }
}
