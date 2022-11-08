package com.microsoft.applications.telemetry.core;

class StatsConstants {
    static final String BEST_EFFORT = "b_e";
    static final String CORRUPT_EVENT_EVENTTYPE = "EventType";
    static final String CORRUPT_EVENT_EXTENSIONS = "Extensions";
    static final String CORRUPT_EVENT_NAME = "corrupt_event";
    static final String CORRUPT_EVENT_TENANTID = "TenantId";
    static final String CORRUPT_EVENT_TIMESTAMP = "Timestamp";
    static final String CORRUPT_EVENT_TYPE = "Type";
    static final String DROP_BAD_TENANT = "d_bad_tenant";
    static final String DROP_BAD_TENANT_HIGH = "h_d_bad_tenant";
    static final String DROP_BAD_TENANT_IMMEDIATE = "i_d_bad_tenant";
    static final String DROP_BAD_TENANT_LOW = "l_d_bad_tenant";
    static final String DROP_BAD_TENANT_NORMAL = "n_d_bad_tenant";
    static final String DROP_EVENT_CORRUPT = "d_corrupt";
    static final String DROP_EVENT_CORRUPT_HIGH = "h_d_crc";
    static final String DROP_EVENT_CORRUPT_IMMEDIATE = "i_d_crc";
    static final String DROP_EVENT_CORRUPT_LOW = "l_d_crc";
    static final String DROP_EVENT_CORRUPT_NORMAL = "n_crc";
    static final String DROP_OFFLINE_FAIL = "d_io_fail";
    static final String DROP_OFFLINE_FAIL_HIGH = "h_d_io_fail";
    static final String DROP_OFFLINE_FAIL_IMMEDIATE = "i_d_io_fail";
    static final String DROP_OFFLINE_FAIL_LOW = "l_d_io_fail";
    static final String DROP_OFFLINE_FAIL_NORMAL = "n_d_io_fail";
    static final String DROP_OFFLINE_FULL = "d_disk_full";
    static final String DROP_OFFLINE_FULL_HIGH = "h_d_disk_full";
    static final String DROP_OFFLINE_FULL_IMMEDIATE = "i_d_disk_full";
    static final String DROP_OFFLINE_FULL_LOW = "l_d_disk_full";
    static final String DROP_OFFLINE_FULL_NORMAL = "n_d_disk_full";
    static final String DROP_SERIALIZE_FAIL = "d_bond_fail";
    static final String DROP_SERIALIZE_FAIL_HIGH = "h_d_bond_fail";
    static final String DROP_SERIALIZE_FAIL_IMMEDIATE = "i_d_bond_fail";
    static final String DROP_SERIALIZE_FAIL_LOW = "l_d_bond_fail";
    static final String DROP_SERIALIZE_FAIL_NORMAL = "n_d_bond_fail";
    static final String EXCEPTION_EVENT_NAME = "exception";
    static final String EXCEPTION_MESSAGE = "message";
    static final String EXCEPTION_TYPE = "type";
    static final String HTTP_EXCEPTION = "ex";
    static final String HTTP_STATUS = "h_";
    static final String HTTP_STATUS_HIGH = "h_h_";
    static final String HTTP_STATUS_IMMEDIATE = "i_h_";
    static final String HTTP_STATUS_LOW = "l_h_";
    static final String HTTP_STATUS_NORMAL = "n_h_";
    static final String INVALID_EVENT_NAME_MISSING = "r_no_name";
    static final String INVALID_EVENT_NAME_MISSING_HIGH = "h_r_no_name";
    static final String INVALID_EVENT_NAME_MISSING_IMMEDIATE = "i_r_no_name";
    static final String INVALID_EVENT_NAME_MISSING_LOW = "l_r_no_name";
    static final String INVALID_EVENT_NAME_MISSING_NORMAL = "n_r_no_name";
    static final String INVALID_EVENT_SIZE_LIMIT = "r_size";
    static final String INVALID_EVENT_SIZE_LIMIT_HIGH = "h_r_size";
    static final String INVALID_EVENT_SIZE_LIMIT_IMMEDIATE = "i_r_size";
    static final String INVALID_EVENT_SIZE_LIMIT_LOW = "l_r_size";
    static final String INVALID_EVENT_SIZE_LIMIT_NORMAL = "n_r_size";
    static final String INVALID_UNKNOWN_REASON = "r_unk";
    static final String INVALID_UNKNOWN_REASON_HIGH = "h_r_unk";
    static final String INVALID_UNKNOWN_REASON_IMMEDIATE = "i_r_unk";
    static final String INVALID_UNKNOWN_REASON_LOW = "l_r_unk";
    static final String INVALID_UNKNOWN_REASON_NORMAL = "n_r_unk";
    static final String INVALID_VALIDATION_FAIL = "r_inv";
    static final String INVALID_VALIDATION_FAIL_HIGH = "h_r_inv";
    static final String INVALID_VALIDATION_FAIL_IMMEDIATE = "i_r_inv";
    static final String INVALID_VALIDATION_FAIL_LOW = "l_r_inv";
    static final String INVALID_VALIDATION_FAIL_NORMAL = "n_r_inv";
    static final String IN_FLIGHT = "infl";
    static final String IN_FLIGHT_HIGH = "h_infl";
    static final String IN_FLIGHT_IMMEDIATE = "i_infl";
    static final String IN_FLIGHT_LOW = "l_infl";
    static final String IN_FLIGHT_NORMAL = "n_infl";
    static final String IN_OFFLINE = "inol";
    static final String IN_OFFLINE_HIGH = "h_inol";
    static final String IN_OFFLINE_IMMEDIATE = "i_inol";
    static final String IN_OFFLINE_LOW = "l_inol";
    static final String IN_OFFLINE_NORMAL = "n_inol";
    static final String NEAR_REAL_TIME = "n_r_t";
    static final String POWER_SOURCE = "t_p";
    static final String REAL_TIME = "r_t";
    static final String RECORDS_ADDED_HIGH = "high_priority_records_received_count";
    static final String RECORDS_ADDED_IMMEDIATE = "immediate_priority_records_received_count";
    static final String RECORDS_ADDED_LOW = "low_priority_records_received_count";
    static final String RECORDS_ADDED_NORMAL = "normal_priority_records_received_count";
    static final String RECORDS_ADDED_TOTAL = "records_received_count";
    static final String RECORDS_DROPPED_HIGH = "high_priority_records_dropped_count";
    static final String RECORDS_DROPPED_IMMEDIATE = "immediate_priority_records_dropped_count";
    static final String RECORDS_DROPPED_LOW = "low_priority_records_dropped_count";
    static final String RECORDS_DROPPED_NORMAL = "normal_priority_records_dropped_count";
    static final String RECORDS_DROPPED_TOTAL = "records_dropped_count";
    static final String RECORDS_REJECTED_COUNT = "r_count";
    static final String RECORDS_REJECTED_COUNT_HIGH = "h_r_count";
    static final String RECORDS_REJECTED_COUNT_IMMEDIATE = "i_r_count";
    static final String RECORDS_REJECTED_COUNT_LOW = "l_r_count";
    static final String RECORDS_REJECTED_COUNT_NORMAL = "n_r_count";
    static final String RECORDS_SENT_HIGH = "high_priority_records_sent_count";
    static final String RECORDS_SENT_IMMEDIATE = "immediate_priority_records_sent_count";
    static final String RECORDS_SENT_LOW = "low_priority_records_sent_count";
    static final String RECORDS_SENT_NORMAL = "normal_priority_records_sent_count";
    static final String RECORDS_SENT_TOTAL = "records_sent_count";
    static final String RECORDS_TRIED_SEND_HIGH = "high_priority_records_tried_to_send_count";
    static final String RECORDS_TRIED_SEND_IMMEDIATE = "immediate_priority_records_tried_to_send_count";
    static final String RECORDS_TRIED_SEND_LOW = "low_priority_records_tried_to_send_count";
    static final String RECORDS_TRIED_SEND_NORMAL = "normal_priority_records_tried_to_send_count";
    static final String RECORDS_TRIED_SEND_TOTAL = "records_tried_to_send_count";
    static final String RETRY = "retry";
    static final String RETRY_HIGH = "h_retry";
    static final String RETRY_IMMEDIATE = "i_retry";
    static final String RETRY_LOW = "l_retry";
    static final String RETRY_NORMAL = "n_retry";
    static final int STATS_CADENCE = 60000;
    static final String STATS_EVENT_NAME = "act_stats";
    static final String STATS_TENANT_TOKEN_DEBUG = "ff4c325c9413441694c3290e97291533-d1bc1297-cb94-400f-9d68-b53ff97f06a5-6705";
    static final String STATS_TENANT_TOKEN_RELEASE = "7434683b182f4b49bc52295c8152518d-e1d93d3d-7e05-4dd3-94d4-eb44bc27b601-7301";
    static final String TENANT_ID = "TenantId";
    static final String TRANSMIT_PROFILE = "tr_p";
    static final String TRANSMIT_PROFILE_HIGH = "t_h";
    static final String TRANSMIT_PROFILE_LOW = "t_l";
    static final String TRANSMIT_PROFILE_NORMAL = "t_n";

    StatsConstants() {
    }
}