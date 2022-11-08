package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.EventProperties;
import com.microsoft.applications.telemetry.ILogger;
import com.microsoft.applications.telemetry.datamodels.DataPackage;
import com.microsoft.applications.telemetry.datamodels.Record;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

class StatsManager extends TimerTask implements IStatsEvents, ITransmissionEvents {
    private static final String THREAD_PREFIX = "Aria-Stats";
    private final Object STATS_LOCK = new Object();
    private int highTimer;
    private int lowTimer;
    private int normalTimer;
    private int powerSource = 0;
    private ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicLong>> stats = new ConcurrentHashMap();
    ILogger statsLogger;
    private String tenantToken = "7434683b182f4b49bc52295c8152518d-e1d93d3d-7e05-4dd3-94d4-eb44bc27b601-7301";
    private Timer timer = new Timer(THREAD_PREFIX);
    private String transmitProfile = "";

    StatsManager() {
        this.timer.schedule(this, 60000, 60000);
        if (BuildConfig.DEBUG) {
            this.tenantToken = "ff4c325c9413441694c3290e97291533-d1bc1297-cb94-400f-9d68-b53ff97f06a5-6705";
        }
        this.statsLogger = InternalMgrImpl.getLogger("", this.tenantToken);
        this.statsLogger.setContext("S_t", LibraryInfo.getLibraryType());
        this.statsLogger.setContext("S_p", LibraryInfo.getLibraryPlatform());
        this.statsLogger.setContext("S_k", LibraryInfo.getLibraryLanguage());
        this.statsLogger.setContext("S_j", LibraryInfo.getLibraryProjection());
        this.statsLogger.setContext("S_v", LibraryInfo.getLibraryVersion());
        this.statsLogger.setContext("S_e", LibraryInfo.getLibraryExperimentation());
    }

    public void run() {
        flush();
    }

    void stopAndFlushStats() {
        this.timer.cancel();
        flush();
    }

    void flush() {
        synchronized (this.STATS_LOCK) {
            for (Entry<String, ConcurrentHashMap<String, AtomicLong>> entry : this.stats.entrySet()) {
                boolean hasStats = false;
                EventProperties eventProperties = new EventProperties("act_stats");
                eventProperties.setProperty("TenantId", (String) entry.getKey());
                eventProperties.setPriority(EventPriority.HIGH);
                for (Entry<String, AtomicLong> stat : ((ConcurrentHashMap) entry.getValue()).entrySet()) {
                    long value = ((AtomicLong) stat.getValue()).getAndSet(Long.MIN_VALUE);
                    if (value != Long.MIN_VALUE) {
                        eventProperties.setProperty((String) stat.getKey(), value);
                        hasStats = true;
                    }
                }
                if (hasStats) {
                    eventProperties.setProperty("tr_p", this.transmitProfile);
                    eventProperties.setProperty("t_h", (long) this.highTimer);
                    eventProperties.setProperty("t_n", (long) this.normalTimer);
                    eventProperties.setProperty("t_l", (long) this.lowTimer);
                    eventProperties.setProperty("t_p", (long) this.powerSource);
                    InternalMgrImpl.getLogger("", this.tenantToken).logEvent(eventProperties);
                }
            }
        }
    }

    public void requestSent(HashMap<DataPackage, EventPriority> dataPackages, String tenantToken) {
        synchronized (this.STATS_LOCK) {
            for (Entry<DataPackage, EventPriority> kvp : dataPackages.entrySet()) {
                int recordsCount = ((DataPackage) kvp.getKey()).getRecords().size() + 0;
                inFlight(recordsCount * -1, (EventPriority) kvp.getValue(), tenantToken);
                requestSent(recordsCount, (EventPriority) kvp.getValue(), tenantToken);
            }
        }
    }

    private void requestSent(int recordsCount, EventPriority priority, String tenantToken) {
        String tenantId = DataModelHelper.getTenantId(tenantToken);
        if (!this.stats.containsKey(tenantId)) {
            this.stats.put(tenantId, new ConcurrentHashMap());
        }
        ConcurrentHashMap<String, AtomicLong> tenantSpecificStats = (ConcurrentHashMap) this.stats.get(tenantId);
        switch (priority) {
            case IMMEDIATE:
                addField(recordsCount, "immediate_priority_records_sent_count", tenantSpecificStats);
                break;
            case HIGH:
                addField(recordsCount, "high_priority_records_sent_count", tenantSpecificStats);
                break;
            case NORMAL:
                addField(recordsCount, "normal_priority_records_sent_count", tenantSpecificStats);
                break;
            case LOW:
                addField(recordsCount, "low_priority_records_sent_count", tenantSpecificStats);
                break;
        }
        addField(recordsCount, "records_sent_count", tenantSpecificStats);
    }

    private void addField(int recordsCount, String fieldName, ConcurrentHashMap<String, AtomicLong> tenantSpecificStats) {
        if (!tenantSpecificStats.containsKey(fieldName)) {
            tenantSpecificStats.put(fieldName, new AtomicLong(0));
        }
        ((AtomicLong) tenantSpecificStats.get(fieldName)).compareAndSet(Long.MIN_VALUE, 0);
        ((AtomicLong) tenantSpecificStats.get(fieldName)).addAndGet((long) recordsCount);
    }

    private void incrementField(String fieldName, ConcurrentHashMap<String, AtomicLong> tenantSpecificStats) {
        if (!tenantSpecificStats.containsKey(fieldName)) {
            tenantSpecificStats.put(fieldName, new AtomicLong(0));
        }
        ((AtomicLong) tenantSpecificStats.get(fieldName)).compareAndSet(Long.MIN_VALUE, 0);
        ((AtomicLong) tenantSpecificStats.get(fieldName)).incrementAndGet();
    }

    public void eventRejected(IEvent event, EventPriority priority, String tenantToken, EventRejectedReason reason) {
        switch (reason) {
            case EVENT_SIZE_LIMIT_EXCEEDED_WHEN_STORING_OFFLINE:
                synchronized (this.STATS_LOCK) {
                    toOffline(-1, priority, tenantToken);
                    eventRejected(priority, tenantToken, reason);
                }
                return;
            default:
                eventRejected(priority, tenantToken, reason);
                return;
        }
    }

    private void eventRejected(EventPriority priority, String tenantToken, EventRejectedReason reason) {
        String tenantId = DataModelHelper.getTenantId(tenantToken);
        if (!this.stats.containsKey(tenantId)) {
            this.stats.put(tenantId, new ConcurrentHashMap());
        }
        ConcurrentHashMap<String, AtomicLong> tenantSpecificStats = (ConcurrentHashMap) this.stats.get(tenantId);
        switch (priority) {
            case IMMEDIATE:
                incrementField("i_r_count", tenantSpecificStats);
                switch (reason) {
                    case EVENT_SIZE_LIMIT_EXCEEDED_WHEN_STORING_OFFLINE:
                        incrementField("i_r_size", tenantSpecificStats);
                        break;
                    case EVENT_NAME_MISSING:
                        incrementField("i_r_no_name", tenantSpecificStats);
                        break;
                    case VALIDATION_FAIL:
                        incrementField("i_r_inv", tenantSpecificStats);
                        break;
                    case UNKNOWN:
                        incrementField("i_r_unk", tenantSpecificStats);
                        break;
                }
                break;
            case HIGH:
                incrementField("h_r_count", tenantSpecificStats);
                switch (reason) {
                    case EVENT_SIZE_LIMIT_EXCEEDED_WHEN_STORING_OFFLINE:
                        incrementField("h_r_size", tenantSpecificStats);
                        break;
                    case EVENT_NAME_MISSING:
                        incrementField("h_r_no_name", tenantSpecificStats);
                        break;
                    case VALIDATION_FAIL:
                        incrementField("h_r_inv", tenantSpecificStats);
                        break;
                    case UNKNOWN:
                        incrementField("h_r_unk", tenantSpecificStats);
                        break;
                }
                break;
            case NORMAL:
                incrementField("n_r_count", tenantSpecificStats);
                switch (reason) {
                    case EVENT_SIZE_LIMIT_EXCEEDED_WHEN_STORING_OFFLINE:
                        incrementField("n_r_size", tenantSpecificStats);
                        break;
                    case EVENT_NAME_MISSING:
                        incrementField("n_r_no_name", tenantSpecificStats);
                        break;
                    case VALIDATION_FAIL:
                        incrementField("n_r_inv", tenantSpecificStats);
                        break;
                    case UNKNOWN:
                        incrementField("n_r_unk", tenantSpecificStats);
                        break;
                }
                break;
            case LOW:
                incrementField("l_r_count", tenantSpecificStats);
                switch (reason) {
                    case EVENT_SIZE_LIMIT_EXCEEDED_WHEN_STORING_OFFLINE:
                        incrementField("l_r_size", tenantSpecificStats);
                        break;
                    case EVENT_NAME_MISSING:
                        incrementField("l_r_no_name", tenantSpecificStats);
                        break;
                    case VALIDATION_FAIL:
                        incrementField("l_r_inv", tenantSpecificStats);
                        break;
                    case UNKNOWN:
                        incrementField("l_r_unk", tenantSpecificStats);
                        break;
                }
                break;
        }
        incrementField("r_count", tenantSpecificStats);
        switch (reason) {
            case EVENT_SIZE_LIMIT_EXCEEDED_WHEN_STORING_OFFLINE:
                incrementField("r_size", tenantSpecificStats);
                return;
            case EVENT_NAME_MISSING:
                incrementField("r_no_name", tenantSpecificStats);
                return;
            case VALIDATION_FAIL:
                incrementField("r_inv", tenantSpecificStats);
                return;
            case UNKNOWN:
                incrementField("r_unk", tenantSpecificStats);
                return;
            default:
                return;
        }
    }

    public void eventDropped(IEvent event, EventPriority priority, String tenantToken, EventDropReason reason) {
        switch (reason) {
            case BAD_TENANT:
                eventDropped(priority, tenantToken, reason);
                return;
            case BAD_TENANT_OFFLINE:
                synchronized (this.STATS_LOCK) {
                    toOffline(-1, priority, tenantToken);
                    eventDropped(priority, tenantToken, EventDropReason.BAD_TENANT);
                }
                return;
            case OFFLINE_FAIL:
                synchronized (this.STATS_LOCK) {
                    toOffline(-1, priority, tenantToken);
                    eventDropped(priority, tenantToken, reason);
                }
                return;
            case OFFLINE_FULL:
                synchronized (this.STATS_LOCK) {
                    toOffline(-1, priority, tenantToken);
                    eventDropped(priority, tenantToken, reason);
                }
                return;
            case SERIALIZATION_FAIL_OFFLINE:
                synchronized (this.STATS_LOCK) {
                    toOffline(-1, priority, tenantToken);
                    eventDropped(priority, tenantToken, reason);
                }
                return;
            case EVENT_CORRUPT:
                synchronized (this.STATS_LOCK) {
                    toOffline(-1, priority, tenantToken);
                    eventDropped(priority, tenantToken, reason);
                }
                return;
            default:
                return;
        }
    }

    private void eventDropped(EventPriority priority, String tenantToken, EventDropReason reason) {
        String tenantId = DataModelHelper.getTenantId(tenantToken);
        if (!this.stats.containsKey(tenantId)) {
            this.stats.put(tenantId, new ConcurrentHashMap());
        }
        ConcurrentHashMap<String, AtomicLong> tenantSpecificStats = (ConcurrentHashMap) this.stats.get(tenantId);
        switch (priority) {
            case IMMEDIATE:
                incrementField("immediate_priority_records_dropped_count", tenantSpecificStats);
                switch (reason) {
                    case BAD_TENANT:
                        incrementField("i_d_bad_tenant", tenantSpecificStats);
                        break;
                    case OFFLINE_FAIL:
                        incrementField("i_d_io_fail", tenantSpecificStats);
                        break;
                    case OFFLINE_FULL:
                        incrementField("i_d_disk_full", tenantSpecificStats);
                        break;
                    case SERIALIZATION_FAIL_OFFLINE:
                        incrementField("i_d_bond_fail", tenantSpecificStats);
                        break;
                    case EVENT_CORRUPT:
                        incrementField("i_d_crc", tenantSpecificStats);
                        break;
                }
                break;
            case HIGH:
                incrementField("high_priority_records_dropped_count", tenantSpecificStats);
                switch (reason) {
                    case BAD_TENANT:
                        incrementField("h_d_bad_tenant", tenantSpecificStats);
                        break;
                    case OFFLINE_FAIL:
                        incrementField("h_d_io_fail", tenantSpecificStats);
                        break;
                    case OFFLINE_FULL:
                        incrementField("h_d_disk_full", tenantSpecificStats);
                        break;
                    case SERIALIZATION_FAIL_OFFLINE:
                        incrementField("h_d_bond_fail", tenantSpecificStats);
                        break;
                    case EVENT_CORRUPT:
                        incrementField("h_d_crc", tenantSpecificStats);
                        break;
                }
                break;
            case NORMAL:
                incrementField("normal_priority_records_dropped_count", tenantSpecificStats);
                switch (reason) {
                    case BAD_TENANT:
                        incrementField("n_d_bad_tenant", tenantSpecificStats);
                        break;
                    case OFFLINE_FAIL:
                        incrementField("n_d_io_fail", tenantSpecificStats);
                        break;
                    case OFFLINE_FULL:
                        incrementField("n_d_disk_full", tenantSpecificStats);
                        break;
                    case SERIALIZATION_FAIL_OFFLINE:
                        incrementField("n_d_bond_fail", tenantSpecificStats);
                        break;
                    case EVENT_CORRUPT:
                        incrementField("n_crc", tenantSpecificStats);
                        break;
                }
                break;
            case LOW:
                incrementField("low_priority_records_dropped_count", tenantSpecificStats);
                switch (reason) {
                    case BAD_TENANT:
                        incrementField("l_d_bad_tenant", tenantSpecificStats);
                        break;
                    case OFFLINE_FAIL:
                        incrementField("l_d_io_fail", tenantSpecificStats);
                        break;
                    case OFFLINE_FULL:
                        incrementField("l_d_disk_full", tenantSpecificStats);
                        break;
                    case SERIALIZATION_FAIL_OFFLINE:
                        incrementField("l_d_bond_fail", tenantSpecificStats);
                        break;
                    case EVENT_CORRUPT:
                        incrementField("l_d_crc", tenantSpecificStats);
                        break;
                }
                break;
        }
        incrementField("records_dropped_count", tenantSpecificStats);
        switch (reason) {
            case BAD_TENANT:
                incrementField("d_bad_tenant", tenantSpecificStats);
                return;
            case OFFLINE_FAIL:
                incrementField("d_io_fail", tenantSpecificStats);
                return;
            case OFFLINE_FULL:
                incrementField("d_disk_full", tenantSpecificStats);
                return;
            case SERIALIZATION_FAIL_OFFLINE:
                incrementField("d_bond_fail", tenantSpecificStats);
                return;
            case EVENT_CORRUPT:
                incrementField("d_corrupt", tenantSpecificStats);
                return;
            default:
                return;
        }
    }

    public void requestSendAttempted(HashMap<DataPackage, EventPriority> dataPackages, String tenantToken) {
        String tenantId = DataModelHelper.getTenantId(tenantToken);
        if (!this.stats.containsKey(tenantId)) {
            this.stats.put(tenantId, new ConcurrentHashMap());
        }
        ConcurrentHashMap<String, AtomicLong> tenantSpecificStats = (ConcurrentHashMap) this.stats.get(tenantId);
        for (Entry<DataPackage, EventPriority> kvp : dataPackages.entrySet()) {
            int recordsSentAttemptedCount = ((DataPackage) kvp.getKey()).getRecords().size() + 0;
            switch ((EventPriority) kvp.getValue()) {
                case IMMEDIATE:
                    addField(recordsSentAttemptedCount, "immediate_priority_records_tried_to_send_count", tenantSpecificStats);
                    break;
                case HIGH:
                    addField(recordsSentAttemptedCount, "high_priority_records_tried_to_send_count", tenantSpecificStats);
                    break;
                case NORMAL:
                    addField(recordsSentAttemptedCount, "normal_priority_records_tried_to_send_count", tenantSpecificStats);
                    break;
                case LOW:
                    addField(recordsSentAttemptedCount, "low_priority_records_tried_to_send_count", tenantSpecificStats);
                    break;
                default:
                    break;
            }
            addField(recordsSentAttemptedCount, "records_tried_to_send_count", tenantSpecificStats);
        }
    }

    public void requestSendFailed(HashMap<DataPackage, EventPriority> dataPackages, String tenantToken, int statusCode) {
        synchronized (this.STATS_LOCK) {
            for (Entry<DataPackage, EventPriority> kvp : dataPackages.entrySet()) {
                int recordsCount = ((DataPackage) kvp.getKey()).getRecords().size() + 0;
                inFlight(recordsCount * -1, (EventPriority) kvp.getValue(), tenantToken);
                requestSendFailed(recordsCount, (EventPriority) kvp.getValue(), tenantToken, statusCode);
            }
        }
    }

    private void requestSendFailed(int recordsCount, EventPriority priority, String tenantToken, int statusCode) {
        String tenantId = DataModelHelper.getTenantId(tenantToken);
        if (!this.stats.containsKey(tenantId)) {
            this.stats.put(tenantId, new ConcurrentHashMap());
        }
        ConcurrentHashMap<String, AtomicLong> tenantSpecificStats = (ConcurrentHashMap) this.stats.get(tenantId);
        String httpStatusString = statusCode == Integer.MIN_VALUE ? "ex" : String.valueOf(statusCode);
        switch (priority) {
            case IMMEDIATE:
                if (statusCode == 403) {
                    addField(recordsCount, "i_r_count", tenantSpecificStats);
                } else {
                    addField(recordsCount, "immediate_priority_records_dropped_count", tenantSpecificStats);
                }
                addField(recordsCount, "i_h_" + httpStatusString, tenantSpecificStats);
                break;
            case HIGH:
                if (statusCode == 403) {
                    addField(recordsCount, "h_r_count", tenantSpecificStats);
                } else {
                    addField(recordsCount, "high_priority_records_dropped_count", tenantSpecificStats);
                }
                addField(recordsCount, "h_h_" + httpStatusString, tenantSpecificStats);
                break;
            case NORMAL:
                if (statusCode == 403) {
                    addField(recordsCount, "n_r_count", tenantSpecificStats);
                } else {
                    addField(recordsCount, "normal_priority_records_dropped_count", tenantSpecificStats);
                }
                addField(recordsCount, "n_h_" + httpStatusString, tenantSpecificStats);
                break;
            case LOW:
                if (statusCode == 403) {
                    addField(recordsCount, "l_r_count", tenantSpecificStats);
                } else {
                    addField(recordsCount, "low_priority_records_dropped_count", tenantSpecificStats);
                }
                addField(recordsCount, "l_h_" + httpStatusString, tenantSpecificStats);
                break;
        }
        if (statusCode == 403) {
            addField(recordsCount, "r_count", tenantSpecificStats);
        } else {
            addField(recordsCount, "records_dropped_count", tenantSpecificStats);
        }
        addField(recordsCount, "h_" + httpStatusString, tenantSpecificStats);
    }

    public void requestSendRetrying(HashMap<DataPackage, EventPriority> dataPackages, String tenantToken) {
        String tenantId = DataModelHelper.getTenantId(tenantToken);
        if (!this.stats.containsKey(tenantId)) {
            this.stats.put(tenantId, new ConcurrentHashMap());
        }
        ConcurrentHashMap<String, AtomicLong> tenantSpecificStats = (ConcurrentHashMap) this.stats.get(tenantId);
        for (Entry<DataPackage, EventPriority> kvp : dataPackages.entrySet()) {
            int recordsCount = ((DataPackage) kvp.getKey()).getRecords().size() + 0;
            switch ((EventPriority) kvp.getValue()) {
                case IMMEDIATE:
                    addField(recordsCount, "i_retry", tenantSpecificStats);
                    break;
                case HIGH:
                    addField(recordsCount, "h_retry", tenantSpecificStats);
                    break;
                case NORMAL:
                    addField(recordsCount, "n_retry", tenantSpecificStats);
                    break;
                case LOW:
                    addField(recordsCount, "l_retry", tenantSpecificStats);
                    break;
                default:
                    break;
            }
            addField(recordsCount, "retry", tenantSpecificStats);
        }
    }

    public void eventAdded(IEvent event, EventPriority priority, String tenantToken) {
        String tenantId = DataModelHelper.getTenantId(tenantToken);
        if (!this.stats.containsKey(tenantId)) {
            this.stats.put(tenantId, new ConcurrentHashMap());
        }
        ConcurrentHashMap<String, AtomicLong> tenantSpecificStats = (ConcurrentHashMap) this.stats.get(tenantId);
        switch (priority) {
            case IMMEDIATE:
                incrementField("immediate_priority_records_received_count", tenantSpecificStats);
                break;
            case HIGH:
                incrementField("high_priority_records_received_count", tenantSpecificStats);
                break;
            case NORMAL:
                incrementField("normal_priority_records_received_count", tenantSpecificStats);
                break;
            case LOW:
                incrementField("low_priority_records_received_count", tenantSpecificStats);
                break;
        }
        incrementField("records_received_count", tenantSpecificStats);
    }

    public void toOffline(int recordsAdded, EventPriority priority, String tenantToken) {
        String tenantId = DataModelHelper.getTenantId(tenantToken);
        if (!this.stats.containsKey(tenantId)) {
            this.stats.put(tenantId, new ConcurrentHashMap());
        }
        ConcurrentHashMap<String, AtomicLong> tenantSpecificStats = (ConcurrentHashMap) this.stats.get(tenantId);
        switch (priority) {
            case IMMEDIATE:
                addField(recordsAdded, "i_inol", tenantSpecificStats);
                break;
            case HIGH:
                addField(recordsAdded, "h_inol", tenantSpecificStats);
                break;
            case NORMAL:
                addField(recordsAdded, "n_inol", tenantSpecificStats);
                break;
            case LOW:
                addField(recordsAdded, "l_inol", tenantSpecificStats);
                break;
        }
        addField(recordsAdded, "inol", tenantSpecificStats);
    }

    public void inFlight(int recordsAdded, EventPriority priority, String tenantToken) {
        String tenantId = DataModelHelper.getTenantId(tenantToken);
        if (!this.stats.containsKey(tenantId)) {
            this.stats.put(tenantId, new ConcurrentHashMap());
        }
        ConcurrentHashMap<String, AtomicLong> tenantSpecificStats = (ConcurrentHashMap) this.stats.get(tenantId);
        switch (priority) {
            case IMMEDIATE:
                addField(recordsAdded, "i_infl", tenantSpecificStats);
                break;
            case HIGH:
                addField(recordsAdded, "h_infl", tenantSpecificStats);
                break;
            case NORMAL:
                addField(recordsAdded, "n_infl", tenantSpecificStats);
                break;
            case LOW:
                addField(recordsAdded, "l_infl", tenantSpecificStats);
                break;
        }
        addField(recordsAdded, "infl", tenantSpecificStats);
    }

    public void transition(EventTransition transition, int recordsTransitioned, EventPriority priority, String tenantToken) {
        synchronized (this.STATS_LOCK) {
            switch (transition) {
                case TO_OFFLINE:
                    toOffline(recordsTransitioned, priority, tenantToken);
                    break;
                case OFFLINE_TO_FLIGHT:
                    toOffline(recordsTransitioned * -1, priority, tenantToken);
                    break;
                case FLIGHT_TO_OFFLINE:
                    inFlight(recordsTransitioned * -1, priority, tenantToken);
                    toOffline(recordsTransitioned, priority, tenantToken);
                    break;
                case TO_FLIGHT:
                    break;
            }
            inFlight(recordsTransitioned, priority, tenantToken);
        }
    }

    public void logException(Throwable ex) {
        EventProperties properties = new EventProperties("exception");
        properties.setProperty("type", ex.getClass().getName());
        properties.setProperty("message", ex.getMessage());
        this.statsLogger.logEvent(properties);
    }

    public void logTransmitProfile(String profile, int highTimer, int normalTimer, int lowTimer, int powerSource) {
        Object obj = -1;
        switch (profile.hashCode()) {
            case -1745566704:
                if (profile.equals("BestEffort")) {
                    obj = 2;
                    break;
                }
                break;
            case -795507285:
                if (profile.equals("RealTime")) {
                    obj = null;
                    break;
                }
                break;
            case 2101197299:
                if (profile.equals("NearRealTime")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                this.transmitProfile = "r_t";
                break;
            case 1:
                this.transmitProfile = "n_r_t";
                break;
            case 2:
                this.transmitProfile = "b_e";
                break;
            default:
                this.transmitProfile = profile;
                break;
        }
        this.powerSource = powerSource;
        this.highTimer = highTimer;
        this.normalTimer = normalTimer;
        this.lowTimer = lowTimer;
    }

    public void logCorruptEvent(Record corruptEvent, String tenantToken) {
        EventProperties properties = new EventProperties("corrupt_event");
        properties.setProperty("Timestamp", corruptEvent.getTimestamp());
        properties.setProperty("EventType", corruptEvent.getEventType());
        properties.setProperty("Type", corruptEvent.getType());
        properties.setProperty("TenantId", DataModelHelper.getTenantId(tenantToken));
        try {
            JSONObject object = new JSONObject();
            for (Entry<String, String> entry : corruptEvent.getExtension().entrySet()) {
                object.put((String) entry.getKey(), entry.getValue());
            }
            properties.setProperty("Extensions", object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.statsLogger.logEvent(properties);
    }
}
