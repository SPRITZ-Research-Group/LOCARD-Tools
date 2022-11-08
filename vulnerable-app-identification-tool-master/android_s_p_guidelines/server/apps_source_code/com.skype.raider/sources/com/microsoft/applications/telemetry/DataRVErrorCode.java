package com.microsoft.applications.telemetry;

public enum DataRVErrorCode {
    DATARV_ERROR_OK("Success", 0),
    DATARV_ERROR_FAIL("Failure", 1),
    DATARV_ERROR_NOT_IMPLEMENTED("Not used", 2),
    DATARV_ERROR_INVALID_ARG("", 3),
    DATARV_ERROR_INVALID_CONFIG("Perhaps UI version is empty or version in config is empty.", 4),
    DATARV_ERROR_INVALID_DEPENDENCIES("Perhaps the version in dependency is incorrect", 5),
    DATARV_ERROR_INVALID_HTTPSTACK("Create HttpStack failed.", 6),
    DATARV_ERROR_INVALID_STATUS("Invalid status in TelemetryClient", 7),
    DATARV_ERROR_INVALID_EVENT("The event is invalid, perhaps there are some fields in event lost.", 8),
    DATARV_ERROR_INVALID_EVENT_VERSION("Non-supported event version", 9),
    DATARV_ERROR_DISABLED("clienttelemetry is disabled", 10),
    DATARV_ERROR_OUTOFMEMORY("Insufficient memory", 11),
    DATARV_ERROR_UNEXPECTED("Fatal error", 12),
    DATARV_ERROR_EVENT_BANNED("The events are banned and not allowed to be sent just now.", 13),
    DATARV_ERROR_CREATE_TIMER_FAILED("Create Timer Failed", 14),
    DATARV_ERROR_INIT_OFFLINESTORAGE_FAILED("Init IOfflineStorage failed", 15),
    DATARV_ERROR_START_OFFLINESTORAGE_FAILED("Start offline storage failed", 16),
    DATARV_ERROR_SMALL_OFFLINESTORAGE_SIZE("Offline storage size too small", 17),
    DATARV_ERROR_FILE_DATA_TOOLARGE("Data too large", 18),
    DATARV_ERROR_FILE_NOTOPEN("File not open", 19),
    DATARV_ERROR_FILE_DOESNOTEXIST("File does not exist", 20),
    DATARV_ERROR_FILE_EMPTY("File empty", 21),
    DATARV_ERROR_FILE_NOMOREITEMS("No more items", 22),
    DATARV_ERROR_DATA_SERIALIZATION_FAILED("Data serialization failed", 23);
    
    private String errorCodeStr;
    private int errorCodeValue;

    private DataRVErrorCode(String name, int idx) {
        this.errorCodeStr = name;
        this.errorCodeValue = idx;
    }

    public final String getErrorCodeStr(int errorCodeValue) {
        for (DataRVErrorCode err : values()) {
            if (err.errorCodeValue == errorCodeValue) {
                return err.errorCodeStr;
            }
        }
        return null;
    }

    public final String getErrorCodeStr() {
        return this.errorCodeStr;
    }

    public final int getErrorCodeValue() {
        return this.errorCodeValue;
    }
}
