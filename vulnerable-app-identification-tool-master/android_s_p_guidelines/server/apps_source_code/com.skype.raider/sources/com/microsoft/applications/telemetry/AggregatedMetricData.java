package com.microsoft.applications.telemetry;

import java.util.HashMap;
import java.util.Map;

public class AggregatedMetricData {
    public Map<AggregateType, Double> aggregates = new HashMap();
    public Map<Long, Long> buckets = new HashMap();
    public final long count;
    public final long duration;
    public String instanceName = "";
    public final String name;
    public String objectClass = "";
    public String objectId = "";
    public String units = "";

    public AggregatedMetricData(String aggrName, long aggrDuration, long aggrCount) {
        this.name = aggrName;
        this.duration = aggrDuration;
        this.count = aggrCount;
    }
}
