package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import java.util.HashMap;
import java.util.Queue;

interface IInboundQueuesManager {
    boolean checkIfSomethingToSendForPriority(EventPriority eventPriority);

    HashMap<EventPriority, Queue<RecordWithMetadata>> getRecordsFromInboundQueueForPriorityAndAbove(EventPriority eventPriority);
}
