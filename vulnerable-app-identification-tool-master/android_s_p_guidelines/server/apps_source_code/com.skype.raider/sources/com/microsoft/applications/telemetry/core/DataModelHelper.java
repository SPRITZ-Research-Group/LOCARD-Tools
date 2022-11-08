package com.microsoft.applications.telemetry.core;

import com.microsoft.a.b.e;
import com.microsoft.applications.telemetry.datamodels.DataPackage;
import com.microsoft.applications.telemetry.datamodels.Record;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

final class DataModelHelper {
    private static final String LOG_TAG = ("[ACT]:" + DataModelHelper.class.getSimpleName().toUpperCase());

    private DataModelHelper() {
        throw new AssertionError();
    }

    static DataPackage createDataPackage(ArrayList<Record> records, String source) {
        DataPackage dataPackage = new DataPackage();
        dataPackage.setSchemaVersion(1);
        dataPackage.setTimestamp(System.currentTimeMillis());
        dataPackage.setDataPackageId(UUID.randomUUID().toString());
        dataPackage.setRecords(records);
        dataPackage.setSource(source);
        return dataPackage;
    }

    static byte[] serializeRecord(Record record) throws IOException {
        e outputStream = new e();
        Serializer.serialize(record, outputStream);
        return outputStream.a();
    }

    static Record deserializeRecord(byte[] buffer) throws IOException {
        Record record = new Record();
        Serializer.deserialize(record, buffer);
        return record;
    }

    static long computeMaxRecordBatchSizeInBytes(String dataPackageSource) {
        DataPackage dataPackage = createDataPackage(new ArrayList(), dataPackageSource);
        e outputStream = new e();
        long serializedDpSize = 0;
        try {
            Serializer.serialize(dataPackage, outputStream);
            serializedDpSize = (long) outputStream.b();
        } catch (IOException e) {
            TraceHelper.TraceError(LOG_TAG, "Caught IOException while serializing empty DataPackage. Rely on safety margin to calculate maximal record batch size in bytes.", e);
        }
        return (3145728 - serializedDpSize) - 5120;
    }

    static boolean getRecordSizeAndCheckIfRecordSizeValid(RecordWithMetadata record, long maxRecordSizeBytes) throws IOException {
        int serializedRecordSize;
        if (record.getSizeBytes() < 0) {
            e outputStream = new e();
            Serializer.serialize(record.getRecord(), outputStream);
            serializedRecordSize = outputStream.b();
            record.setSizeBytes(serializedRecordSize);
        } else {
            serializedRecordSize = record.getSizeBytes();
        }
        return ((long) serializedRecordSize) <= maxRecordSizeBytes;
    }

    static String getTenantId(String tenantToken) {
        int indexOfTenantId = tenantToken.indexOf("-");
        String tenantId = "";
        if (indexOfTenantId > 0) {
            return tenantToken.substring(0, indexOfTenantId);
        }
        return tenantId;
    }
}
