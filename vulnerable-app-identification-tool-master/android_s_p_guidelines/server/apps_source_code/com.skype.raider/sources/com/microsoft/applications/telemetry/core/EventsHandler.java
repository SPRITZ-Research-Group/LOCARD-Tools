package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.datamodels.DataPackage;
import com.microsoft.applications.telemetry.datamodels.Record;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

class EventsHandler implements IStatsEvents, ITransmissionEvents {
    private static final String LOG_TAG = EventsHandler.class.getSimpleName();
    private final String defaultTenantToken;
    private final Vector<ITransmissionEvents> eventListeners = new Vector();
    private IStatsEvents statsManager = null;

    EventsHandler(String tenantToken) {
        this.defaultTenantToken = tenantToken;
    }

    public void requestSent(HashMap<DataPackage, EventPriority> dataPackages, String tenantToken) {
        if (!tenantToken.equals("ff4c325c9413441694c3290e97291533-d1bc1297-cb94-400f-9d68-b53ff97f06a5-6705") && !tenantToken.equals("7434683b182f4b49bc52295c8152518d-e1d93d3d-7e05-4dd3-94d4-eb44bc27b601-7301")) {
            Iterator it = this.eventListeners.iterator();
            while (it.hasNext()) {
                ITransmissionEvents listener = (ITransmissionEvents) it.next();
                try {
                    String str;
                    if (tenantToken.isEmpty()) {
                        str = this.defaultTenantToken;
                    } else {
                        str = tenantToken;
                    }
                    listener.requestSent(dataPackages, str);
                } catch (Exception e) {
                    TraceHelper.TraceError(LOG_TAG, "Caught Exception while triggering RequestSent event.", e);
                }
            }
        }
    }

    public void eventDropped(IEvent event, EventPriority priority, String tenantToken, EventDropReason reason) {
        if (!tenantToken.equals("ff4c325c9413441694c3290e97291533-d1bc1297-cb94-400f-9d68-b53ff97f06a5-6705") && !tenantToken.equals("7434683b182f4b49bc52295c8152518d-e1d93d3d-7e05-4dd3-94d4-eb44bc27b601-7301")) {
            Iterator it = this.eventListeners.iterator();
            while (it.hasNext()) {
                ITransmissionEvents listener = (ITransmissionEvents) it.next();
                try {
                    String str;
                    if (tenantToken.isEmpty()) {
                        str = this.defaultTenantToken;
                    } else {
                        str = tenantToken;
                    }
                    listener.eventDropped(event, priority, str, reason);
                } catch (Exception e) {
                    TraceHelper.TraceError(LOG_TAG, "Caught Exception while triggering EventDropped event.", e);
                }
            }
        }
    }

    public void eventRejected(IEvent event, EventPriority priority, String tenantToken, EventRejectedReason reason) {
        if (!tenantToken.equals("ff4c325c9413441694c3290e97291533-d1bc1297-cb94-400f-9d68-b53ff97f06a5-6705") && !tenantToken.equals("7434683b182f4b49bc52295c8152518d-e1d93d3d-7e05-4dd3-94d4-eb44bc27b601-7301")) {
            Iterator it = this.eventListeners.iterator();
            while (it.hasNext()) {
                ITransmissionEvents listener = (ITransmissionEvents) it.next();
                try {
                    String str;
                    if (tenantToken.isEmpty()) {
                        str = this.defaultTenantToken;
                    } else {
                        str = tenantToken;
                    }
                    listener.eventRejected(event, priority, str, reason);
                } catch (Exception e) {
                    TraceHelper.TraceError(LOG_TAG, "Caught Exception while triggering EventRejected event.", e);
                }
            }
        }
    }

    public void requestSendAttempted(HashMap<DataPackage, EventPriority> dataPackages, String tenantToken) {
        if (!tenantToken.equals("ff4c325c9413441694c3290e97291533-d1bc1297-cb94-400f-9d68-b53ff97f06a5-6705") && !tenantToken.equals("7434683b182f4b49bc52295c8152518d-e1d93d3d-7e05-4dd3-94d4-eb44bc27b601-7301")) {
            Iterator it = this.eventListeners.iterator();
            while (it.hasNext()) {
                ITransmissionEvents listener = (ITransmissionEvents) it.next();
                try {
                    String str;
                    if (tenantToken.isEmpty()) {
                        str = this.defaultTenantToken;
                    } else {
                        str = tenantToken;
                    }
                    listener.requestSendAttempted(dataPackages, str);
                } catch (Exception e) {
                    TraceHelper.TraceError(LOG_TAG, "Caught Exception while triggering RequestSendAttempted event.", e);
                }
            }
        }
    }

    public void requestSendFailed(HashMap<DataPackage, EventPriority> dataPackages, String tenantToken, int statusCode) {
        if (!tenantToken.equals("ff4c325c9413441694c3290e97291533-d1bc1297-cb94-400f-9d68-b53ff97f06a5-6705") && !tenantToken.equals("7434683b182f4b49bc52295c8152518d-e1d93d3d-7e05-4dd3-94d4-eb44bc27b601-7301")) {
            Iterator it = this.eventListeners.iterator();
            while (it.hasNext()) {
                ITransmissionEvents listener = (ITransmissionEvents) it.next();
                try {
                    String str;
                    if (tenantToken.isEmpty()) {
                        str = this.defaultTenantToken;
                    } else {
                        str = tenantToken;
                    }
                    listener.requestSendFailed(dataPackages, str, statusCode);
                } catch (Exception e) {
                    TraceHelper.TraceError(LOG_TAG, "Caught Exception while triggering RequestSendFailed event.", e);
                }
            }
        }
    }

    public void requestSendRetrying(HashMap<DataPackage, EventPriority> dataPackages, String tenantToken) {
        if (!tenantToken.equals("ff4c325c9413441694c3290e97291533-d1bc1297-cb94-400f-9d68-b53ff97f06a5-6705") && !tenantToken.equals("7434683b182f4b49bc52295c8152518d-e1d93d3d-7e05-4dd3-94d4-eb44bc27b601-7301")) {
            Iterator it = this.eventListeners.iterator();
            while (it.hasNext()) {
                ITransmissionEvents listener = (ITransmissionEvents) it.next();
                try {
                    String str;
                    if (tenantToken.isEmpty()) {
                        str = this.defaultTenantToken;
                    } else {
                        str = tenantToken;
                    }
                    listener.requestSendRetrying(dataPackages, str);
                } catch (Exception e) {
                    TraceHelper.TraceError(LOG_TAG, "Caught Exception while triggering RequestSendRetrying event.", e);
                }
            }
        }
    }

    public void eventAdded(IEvent event, EventPriority priority, String tenantToken) {
        if (!tenantToken.equals("ff4c325c9413441694c3290e97291533-d1bc1297-cb94-400f-9d68-b53ff97f06a5-6705") && !tenantToken.equals("7434683b182f4b49bc52295c8152518d-e1d93d3d-7e05-4dd3-94d4-eb44bc27b601-7301")) {
            Iterator it = this.eventListeners.iterator();
            while (it.hasNext()) {
                ITransmissionEvents listener = (ITransmissionEvents) it.next();
                try {
                    String str;
                    if (tenantToken.isEmpty()) {
                        str = this.defaultTenantToken;
                    } else {
                        str = tenantToken;
                    }
                    listener.eventAdded(event, priority, str);
                } catch (Exception e) {
                    TraceHelper.TraceError(LOG_TAG, "Caught Exception while triggering recordAdded event.", e);
                }
            }
        }
    }

    public void transition(EventTransition transition, int recordsTransitioned, EventPriority priority, String tenantToken) {
        if (this.statsManager != null && !tenantToken.equals("ff4c325c9413441694c3290e97291533-d1bc1297-cb94-400f-9d68-b53ff97f06a5-6705") && !tenantToken.equals("7434683b182f4b49bc52295c8152518d-e1d93d3d-7e05-4dd3-94d4-eb44bc27b601-7301")) {
            this.statsManager.transition(transition, recordsTransitioned, priority, tenantToken);
        }
    }

    public void logException(Throwable ex) {
        if (this.statsManager != null) {
            this.statsManager.logException(ex);
        }
    }

    public void logTransmitProfile(String profile, int highTimer, int normalTimer, int lowTimer, int powerSource) {
        if (this.statsManager != null) {
            this.statsManager.logTransmitProfile(profile, highTimer, normalTimer, lowTimer, powerSource);
        }
    }

    public void logCorruptEvent(Record corruptEvent, String tenantToken) {
        if (this.statsManager != null) {
            this.statsManager.logCorruptEvent(corruptEvent, tenantToken);
        }
    }

    void addEventListener(ITransmissionEvents listener) {
        this.eventListeners.addElement(listener);
    }

    void removeEventListener(ITransmissionEvents listener) {
        this.eventListeners.removeElement(listener);
    }

    void addStatsManager(StatsManager statsManager) {
        if (statsManager != null) {
            addEventListener(statsManager);
            this.statsManager = statsManager;
        }
    }

    void removeStatsManager(StatsManager statsManager) {
        if (statsManager != null) {
            removeEventListener(statsManager);
            this.statsManager = null;
        }
    }

    List<ITransmissionEvents> getEventListeners() {
        return Collections.unmodifiableList(this.eventListeners);
    }
}
