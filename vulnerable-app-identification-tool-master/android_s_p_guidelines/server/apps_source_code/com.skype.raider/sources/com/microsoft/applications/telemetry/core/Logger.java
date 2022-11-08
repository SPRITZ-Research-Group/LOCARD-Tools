package com.microsoft.applications.telemetry.core;

import android.os.Build.VERSION;
import com.microsoft.applications.telemetry.ActionType;
import com.microsoft.applications.telemetry.AggregatedMetricData;
import com.microsoft.applications.telemetry.AppLifecycleState;
import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.EventProperties;
import com.microsoft.applications.telemetry.ILogger;
import com.microsoft.applications.telemetry.ISemanticContext;
import com.microsoft.applications.telemetry.LogManager;
import com.microsoft.applications.telemetry.PageActionData;
import com.microsoft.applications.telemetry.PiiKind;
import com.microsoft.applications.telemetry.SessionState;
import com.microsoft.applications.telemetry.TraceLevel;
import com.microsoft.applications.telemetry.UserState;
import com.microsoft.applications.telemetry.datamodels.EventBase;
import com.microsoft.applications.telemetry.datamodels.PII;
import com.microsoft.applications.telemetry.datamodels.PIIScrubber;
import com.microsoft.applications.telemetry.pal.hardware.NetworkInformation;
import com.microsoft.applications.telemetry.pal.hardware.SystemInformation;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.UUID;

class Logger implements ILogger {
    private static final String LOG_TAG = ("[ACT]:" + Logger.class.getSimpleName().toUpperCase());
    private static final long MILLISECS_TILL_UNIX_EPOCH = 62135596800000L;
    private static final int MS_TO_TICKS_MULTIPLIER = 10000;
    private static final String TYPE_PREFIX = "custom.";
    private String appToken = "";
    private EventProperties contextProperties = new EventProperties("");
    private DateFormat dateFormat;
    private final String exceptionMessageForEmptyEventName = "Event name cannot be null or empty";
    private boolean isLoggingEnabled = false;
    private IEventMessenger messenger;
    private String sdkVersion;
    private final ISemanticContext semanticContext = new SemanticContext(false);
    private String sessionID = null;
    private long sessionStartTime;
    private String source = "";

    Logger(String source, String appToken) {
        setUpCommonContructorFields(source, appToken);
    }

    Logger(IEventMessenger messenger, String source, String appToken) {
        setUpCommonContructorFields(source, appToken);
        this.messenger = (IEventMessenger) Preconditions.isNotNull(messenger, "messenger cannot be null.");
        this.isLoggingEnabled = true;
    }

    public ISemanticContext getSemanticContext() {
        return this.semanticContext;
    }

    public void setContext(String key, String value) {
        this.contextProperties.setProperty(key, value);
    }

    public void setContext(String key, String value, PiiKind piiKind) {
        this.contextProperties.setProperty(key, value, piiKind);
    }

    public void setContext(String key, double value) {
        this.contextProperties.setProperty(key, value);
    }

    public void setContext(String key, double value, PiiKind piiKind) {
        this.contextProperties.setProperty(key, value, piiKind);
    }

    public void setContext(String key, long value) {
        this.contextProperties.setProperty(key, value);
    }

    public void setContext(String key, long value, PiiKind piiKind) {
        this.contextProperties.setProperty(key, value, piiKind);
    }

    public void setContext(String key, boolean value) {
        this.contextProperties.setProperty(key, value);
    }

    public void setContext(String key, boolean value, PiiKind piiKind) {
        this.contextProperties.setProperty(key, value, piiKind);
    }

    public void setContext(String key, Date value) {
        this.contextProperties.setProperty(key, value);
    }

    public void setContext(String key, Date value, PiiKind piiKind) {
        this.contextProperties.setProperty(key, value, piiKind);
    }

    public void setContext(String key, UUID value) {
        this.contextProperties.setProperty(key, value);
    }

    public void setContext(String key, UUID value, PiiKind piiKind) {
        this.contextProperties.setProperty(key, value, piiKind);
    }

    public void logAppLifecycle(AppLifecycleState state, EventProperties properties) {
        EventProperties eventProp;
        if (properties == null) {
            eventProp = new EventProperties("");
        } else {
            eventProp = properties;
        }
        eventProp.setPriority(EventPriority.HIGH);
        EventBase event = createEventBaseForEvent("applifecycle");
        String str = LOG_TAG;
        String str2 = "Stage Receive: event name=%s, event priority=%s, id=%s, tenantId=%s";
        Object[] objArr = new Object[4];
        String eventType = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
        objArr[0] = eventType;
        objArr[1] = eventProp.getPriority();
        objArr[2] = event.getId();
        objArr[3] = DataModelHelper.getTenantId(this.appToken);
        TraceHelper.TraceInformation(str, String.format(str2, objArr));
        try {
            Preconditions.isNullOrEmpty(eventProp.getType(), "type cannot be set for this api.");
            Preconditions.isNotNull(state, "state cannot be null");
            if (eventProp != null) {
                addEventPropertiesToRecord(event, eventProp, false);
            }
            event.getExtension().put("AppLifeCycle.State", state.toString());
            logEvent(event, eventProp.getPriority());
        } catch (Exception e) {
            str = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
            eventFailed(str, eventProp.getPriority(), event.getId(), DataModelHelper.getTenantId(this.appToken), event, e);
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
        }
    }

    public void logFailure(String signature, String detail, EventProperties properties) {
        logFailure(signature, detail, null, null, properties);
    }

    public void logFailure(String signature, String detail, String category, String id, EventProperties properties) {
        EventProperties eventProp;
        if (properties == null) {
            eventProp = new EventProperties("");
        } else {
            eventProp = properties;
        }
        EventBase event = createEventBaseForEvent("failure");
        String str = LOG_TAG;
        String str2 = "Stage Receive: event name=%s, event priority=%s, id=%s, tenantId=%s";
        Object[] objArr = new Object[4];
        String eventType = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
        objArr[0] = eventType;
        objArr[1] = eventProp.getPriority();
        objArr[2] = event.getId();
        objArr[3] = DataModelHelper.getTenantId(this.appToken);
        TraceHelper.TraceInformation(str, String.format(str2, objArr));
        try {
            Preconditions.isNullOrEmpty(eventProp.getType(), "type cannot be set for this api.");
            Preconditions.isNotNullOrEmpty(signature, "signature cannot be null or empty");
            Preconditions.isNotNullOrEmpty(detail, "detail cannot be null or empty");
            if (eventProp != null) {
                addEventPropertiesToRecord(event, eventProp, false);
            }
            event.getExtension().put("Failure.Signature", signature);
            event.getExtension().put("Failure.Detail", detail);
            if (!(category == null || category.isEmpty())) {
                event.getExtension().put("Failure.Category", category);
            }
            if (!(id == null || id.isEmpty())) {
                event.getExtension().put("Failure.Id", id);
            }
            logEvent(event, eventProp.getPriority());
        } catch (Exception e) {
            str = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
            eventFailed(str, eventProp.getPriority(), event.getId(), DataModelHelper.getTenantId(this.appToken), event, e);
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
        }
    }

    public void logPageView(String id, String pageName, EventProperties properties) {
        logPageView(id, pageName, null, null, null, properties);
    }

    public void logPageView(String id, String pageName, String category, String uri, String referrerUri, EventProperties properties) {
        EventProperties eventProp;
        if (properties == null) {
            eventProp = new EventProperties("");
        } else {
            eventProp = properties;
        }
        EventBase event = createEventBaseForEvent("pageview");
        String str = LOG_TAG;
        String str2 = "Stage Receive: event name=%s, event priority=%s, id=%s, tenantId=%s";
        Object[] objArr = new Object[4];
        String eventType = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
        objArr[0] = eventType;
        objArr[1] = eventProp.getPriority();
        objArr[2] = event.getId();
        objArr[3] = DataModelHelper.getTenantId(this.appToken);
        TraceHelper.TraceInformation(str, String.format(str2, objArr));
        try {
            Preconditions.isNullOrEmpty(eventProp.getType(), "type cannot be set for this api.");
            Preconditions.isNotNullOrEmpty(id, "id cannot be null or empty");
            Preconditions.isNotNullOrEmpty(pageName, "pageName cannot be null or empty");
            if (eventProp != null) {
                addEventPropertiesToRecord(event, eventProp, false);
            }
            event.getExtension().put("PageView.Id", id);
            event.getExtension().put("PageView.Name", pageName);
            if (!(category == null || category.isEmpty())) {
                event.getExtension().put("PageView.Category", category);
            }
            if (!(uri == null || uri.isEmpty())) {
                event.getExtension().put("PageView.Uri", uri);
            }
            if (!(referrerUri == null || referrerUri.isEmpty())) {
                event.getExtension().put("PageView.ReferrerUri", referrerUri);
            }
            logEvent(event, eventProp.getPriority());
        } catch (Exception e) {
            str = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
            eventFailed(str, eventProp.getPriority(), event.getId(), DataModelHelper.getTenantId(this.appToken), event, e);
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
        }
    }

    public void logPageAction(String pageViewId, ActionType actionType, EventProperties properties) {
        logPageAction(new PageActionData(pageViewId, actionType), properties);
    }

    public void logPageAction(PageActionData pageAction, EventProperties properties) {
        EventProperties eventProp;
        if (properties == null) {
            eventProp = new EventProperties("");
        } else {
            eventProp = properties;
        }
        EventBase event = createEventBaseForEvent("pageaction");
        String str = LOG_TAG;
        String str2 = "Stage Receive: event name=%s, event priority=%s, id=%s, tenantId=%s";
        Object[] objArr = new Object[4];
        String eventType = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
        objArr[0] = eventType;
        objArr[1] = eventProp.getPriority();
        objArr[2] = event.getId();
        objArr[3] = DataModelHelper.getTenantId(this.appToken);
        TraceHelper.TraceInformation(str, String.format(str2, objArr));
        try {
            Preconditions.isNullOrEmpty(eventProp.getType(), "type cannot be set for this api.");
            Preconditions.isNotNull(pageAction, "pageAction cannot be null");
            Preconditions.isNotNullOrEmpty(pageAction.pageViewId, "pageAction.pageViewId cannot be null or empty");
            Preconditions.isNotNull(pageAction.actionType, "pageAction.actionType cannot be null");
            if (eventProp != null) {
                addEventPropertiesToRecord(event, eventProp, false);
            }
            event.getExtension().put("PageAction.PageViewId", pageAction.pageViewId);
            event.getExtension().put("PageAction.ActionType", pageAction.actionType.toString());
            if (pageAction.rawActionType != null) {
                event.getExtension().put("PageAction.RawActionType", pageAction.rawActionType.toString());
            }
            if (pageAction.inputDeviceType != null) {
                event.getExtension().put("PageAction.InputDeviceType", pageAction.inputDeviceType.toString());
            }
            if (!(pageAction.destinationUri == null || pageAction.destinationUri.isEmpty())) {
                event.getExtension().put("PageAction.DestinationUri", pageAction.destinationUri);
            }
            if (!(pageAction.targetItemId == null || pageAction.targetItemId.isEmpty())) {
                event.getExtension().put("PageAction.TargetItemId", pageAction.targetItemId);
            }
            if (!(pageAction.targetItemCollection == null || pageAction.targetItemCollection.isEmpty())) {
                event.getExtension().put("PageAction.TargetItemDataSource.Collection", pageAction.targetItemCollection);
            }
            if (!(pageAction.targetItemLayoutContainer == null || pageAction.targetItemLayoutContainer.isEmpty())) {
                event.getExtension().put("PageAction.TargetItemLayout.Container", pageAction.targetItemLayoutContainer);
            }
            event.getExtension().put("PageAction.TargetItemLayout.Rank", String.valueOf(pageAction.targetItemRank));
            if (!(pageAction.targetItemName == null || pageAction.targetItemName.isEmpty())) {
                event.getExtension().put("PageAction.TargetItemDataSource.Name", pageAction.targetItemName);
            }
            if (!(pageAction.targetItemCategory == null || pageAction.targetItemCategory.isEmpty())) {
                event.getExtension().put("PageAction.TargetItemDataSource.Category", pageAction.targetItemCategory);
            }
            logEvent(event, eventProp.getPriority());
        } catch (Exception e) {
            str = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
            eventFailed(str, eventProp.getPriority(), event.getId(), DataModelHelper.getTenantId(this.appToken), event, e);
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
        }
    }

    public void logSampledMetric(String name, double value, String units, EventProperties properties) {
        logSampledMetric(name, value, units, null, null, null, properties);
    }

    public void logSampledMetric(String name, double value, String units, String instanceName, String objClass, String objId, EventProperties properties) {
        EventProperties eventProp;
        if (properties == null) {
            eventProp = new EventProperties("");
        } else {
            eventProp = properties;
        }
        EventBase event = createEventBaseForEvent("sampledmetric");
        String str = LOG_TAG;
        String str2 = "Stage Receive: event name=%s, event priority=%s, id=%s, tenantId=%s";
        Object[] objArr = new Object[4];
        String eventType = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
        objArr[0] = eventType;
        objArr[1] = eventProp.getPriority();
        objArr[2] = event.getId();
        objArr[3] = DataModelHelper.getTenantId(this.appToken);
        TraceHelper.TraceInformation(str, String.format(str2, objArr));
        try {
            Preconditions.isNullOrEmpty(eventProp.getType(), "type cannot be set for this api.");
            Preconditions.isNotNullOrEmpty(name, "name cannot be null or empty");
            if (eventProp != null) {
                addEventPropertiesToRecord(event, eventProp, false);
            }
            event.getExtension().put("SampledMetric.Name", name);
            event.getExtension().put("SampledMetric.Value", String.valueOf(value));
            if (!(units == null && units.isEmpty())) {
                event.getExtension().put("SampledMetric.Units", units);
            }
            if (!(instanceName == null || instanceName.isEmpty())) {
                event.getExtension().put("SampledMetric.InstanceName", instanceName);
            }
            if (!(objClass == null || objClass.isEmpty())) {
                event.getExtension().put("SampledMetric.ObjectClass", objClass);
            }
            if (!(objId == null || objId.isEmpty())) {
                event.getExtension().put("SampledMetric.ObjectId", objId);
            }
            logEvent(event, eventProp.getPriority());
        } catch (Exception e) {
            str = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
            eventFailed(str, eventProp.getPriority(), event.getId(), DataModelHelper.getTenantId(this.appToken), event, e);
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
        }
    }

    public void logAggregatedMetric(String name, long duration, long count, EventProperties properties) {
        logAggregatedMetric(new AggregatedMetricData(name, duration, count), properties);
    }

    public void logAggregatedMetric(AggregatedMetricData metricData, EventProperties properties) {
        EventProperties eventProp;
        if (properties == null) {
            eventProp = new EventProperties("");
        } else {
            eventProp = properties;
        }
        EventBase event = createEventBaseForEvent("aggregatedmetric");
        String str = LOG_TAG;
        String str2 = "Stage Receive: event name=%s, event priority=%s, id=%s, tenantId=%s";
        Object[] objArr = new Object[4];
        String eventType = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
        objArr[0] = eventType;
        objArr[1] = eventProp.getPriority();
        objArr[2] = event.getId();
        objArr[3] = DataModelHelper.getTenantId(this.appToken);
        TraceHelper.TraceInformation(str, String.format(str2, objArr));
        try {
            boolean z;
            Preconditions.isNullOrEmpty(eventProp.getType(), "type cannot be set for this api.");
            Preconditions.isNotNull(metricData, "metricData cannot be null.");
            Preconditions.isNotNullOrEmpty(metricData.name, "metric name cannot be null or empty");
            if (metricData.duration >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.isTrue(z, "metric duration cannot be less than 0");
            if (metricData.count >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.isTrue(z, "metric count cannot be less than 0");
            if (eventProp != null) {
                addEventPropertiesToRecord(event, eventProp, false);
            }
            event.getExtension().put("AggregatedMetric.Name", metricData.name);
            event.getExtension().put("AggregatedMetric.Duration", String.valueOf(metricData.duration));
            event.getExtension().put("AggregatedMetric.Count", String.valueOf(metricData.count));
            if (!(metricData.objectClass == null || metricData.objectClass.isEmpty())) {
                event.getExtension().put("AggregatedMetric.ObjectClass", metricData.objectClass);
            }
            if (!(metricData.objectId == null || metricData.objectId.isEmpty())) {
                event.getExtension().put("AggregatedMetric.ObjectId", metricData.objectId);
            }
            if (!(metricData.instanceName == null || metricData.instanceName.isEmpty())) {
                event.getExtension().put("AggregatedMetric.InstanceName", metricData.instanceName);
            }
            if (!(metricData.units == null || metricData.units.isEmpty())) {
                event.getExtension().put("AggregatedMetric.Units", metricData.units);
            }
            logEvent(event, eventProp.getPriority());
        } catch (Exception e) {
            String eventType2 = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
            eventFailed(eventType2, eventProp.getPriority(), event.getId(), DataModelHelper.getTenantId(this.appToken), event, e);
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
        }
    }

    public void logTrace(TraceLevel level, String message, EventProperties properties) {
        EventProperties eventProp;
        if (properties == null) {
            eventProp = new EventProperties("");
        } else {
            eventProp = properties;
        }
        EventBase event = createEventBaseForEvent("trace");
        String str = LOG_TAG;
        String str2 = "Stage Receive: event name=%s, event priority=%s, id=%s, tenantId=%s";
        Object[] objArr = new Object[4];
        String eventType = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
        objArr[0] = eventType;
        objArr[1] = eventProp.getPriority();
        objArr[2] = event.getId();
        objArr[3] = DataModelHelper.getTenantId(this.appToken);
        TraceHelper.TraceInformation(str, String.format(str2, objArr));
        try {
            Preconditions.isNullOrEmpty(eventProp.getType(), "type cannot be set for this api.");
            Preconditions.isNotNull(level, "level cannot be null");
            Preconditions.isNotNullOrEmpty(message, "message cannot be null or empty.");
            if (eventProp != null) {
                addEventPropertiesToRecord(event, eventProp, false);
            }
            event.getExtension().put("Trace.Level", level.toString());
            event.getExtension().put("Trace.Message", message);
            logEvent(event, eventProp.getPriority());
        } catch (Exception e) {
            str = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
            eventFailed(str, eventProp.getPriority(), event.getId(), DataModelHelper.getTenantId(this.appToken), event, e);
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
        }
    }

    public void logUserState(UserState state, long timeToLiveInMillis, EventProperties properties) {
        EventProperties eventProp;
        if (properties == null) {
            eventProp = new EventProperties("");
        } else {
            eventProp = properties;
        }
        EventBase event = createEventBaseForEvent("userinfo_userstate");
        String str = LOG_TAG;
        String str2 = "Stage Receive: event name=%s, event priority=%s, id=%s, tenantId=%s";
        Object[] objArr = new Object[4];
        String eventType = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
        objArr[0] = eventType;
        objArr[1] = eventProp.getPriority();
        objArr[2] = event.getId();
        objArr[3] = DataModelHelper.getTenantId(this.appToken);
        TraceHelper.TraceInformation(str, String.format(str2, objArr));
        try {
            boolean z;
            Preconditions.isNullOrEmpty(eventProp.getType(), "type cannot be set for this api.");
            Preconditions.isNotNull(state, "state cannot be null");
            if (timeToLiveInMillis >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.isTrue(z, "timeToLiveInMillis cannot be less than 0");
            if (eventProp != null) {
                addEventPropertiesToRecord(event, eventProp, false);
            }
            event.getExtension().put("State.Name", "UserState");
            event.getExtension().put("State.Value", state.toString());
            event.getExtension().put("State.TimeToLive", String.valueOf(timeToLiveInMillis));
            event.getExtension().put("State.IsTransition", "true");
            logEvent(event, eventProp.getPriority());
        } catch (Exception e) {
            String eventType2 = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
            eventFailed(eventType2, eventProp.getPriority(), event.getId(), DataModelHelper.getTenantId(this.appToken), event, e);
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
        }
    }

    public void logSession(SessionState state, EventProperties properties) {
        EventProperties eventProp;
        if (properties == null) {
            eventProp = new EventProperties("");
        } else {
            eventProp = properties;
        }
        eventProp.setPriority(EventPriority.HIGH);
        EventBase event = createEventBaseForEvent("session");
        String str = LOG_TAG;
        String str2 = "Stage Receive: event name=%s, event priority=%s, id=%s, tenantId=%s";
        Object[] objArr = new Object[4];
        String eventType = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
        objArr[0] = eventType;
        objArr[1] = eventProp.getPriority();
        objArr[2] = event.getId();
        objArr[3] = DataModelHelper.getTenantId(this.appToken);
        TraceHelper.TraceInformation(str, String.format(str2, objArr));
        try {
            Preconditions.isNullOrEmpty(eventProp.getType(), "type cannot be set for this api.");
            Preconditions.isNotNull(state, "state cannot be null");
            if (eventProp != null) {
                addEventPropertiesToRecord(event, eventProp, false);
            }
            if (state == SessionState.STARTED) {
                if (this.sessionStartTime > 0) {
                    TraceHelper.TraceError(LOG_TAG, "Session start called when a session already existed.");
                    return;
                }
                this.sessionStartTime = System.currentTimeMillis();
                this.sessionID = UUID.randomUUID().toString();
                event.getExtension().put("Session.Id", this.sessionID);
            } else if (state == SessionState.ENDED) {
                if (this.sessionStartTime == 0) {
                    TraceHelper.TraceError(LOG_TAG, "Session end called when a session did not exist.");
                    return;
                }
                long sessionDurationTime = (System.currentTimeMillis() - this.sessionStartTime) / 1000;
                this.sessionStartTime = 0;
                event.getExtension().put("Session.Duration", String.valueOf(sessionDurationTime));
                event.getExtension().put("Session.DurationBucket", getSessionDurationFromTime(sessionDurationTime).toString());
                event.getExtension().put("Session.Id", this.sessionID);
                this.sessionID = null;
            }
            event.getExtension().put("Session.State", state.toString());
            event.getExtension().put("Session.FirstLaunchTime", getISO8601(this.messenger.getFirstLaunchTimeInMillis()));
            logEvent(event, eventProp.getPriority());
        } catch (Exception e) {
            str = (eventProp == null || eventProp.getName() == null) ? event.getEventType() : eventProp.getName();
            eventFailed(str, eventProp.getPriority(), event.getId(), DataModelHelper.getTenantId(this.appToken), event, e);
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
        }
    }

    public String getSessionId() {
        return this.sessionID;
    }

    protected boolean getLoggingEnabled() {
        return this.isLoggingEnabled;
    }

    public void logEvent(EventProperties properties) {
        EventBase event = createEventBaseForEvent("custom");
        String str = LOG_TAG;
        String str2 = "Stage Receive: event name=%s, event priority=%s, id=%s, tenantId=%s";
        Object[] objArr = new Object[4];
        String eventType = (properties == null || properties.getName() == null) ? event.getEventType() : properties.getName();
        objArr[0] = eventType;
        objArr[1] = properties != null ? properties.getPriority() : "null";
        objArr[2] = event.getId();
        objArr[3] = DataModelHelper.getTenantId(this.appToken);
        TraceHelper.TraceInformation(str, String.format(str2, objArr));
        try {
            Preconditions.isNotNull(properties, "properties can not be null");
            if (properties != null) {
                addEventPropertiesToRecord(event, properties, true);
            }
            logEvent(event, properties.getPriority());
        } catch (Exception e) {
            str = (properties == null || properties.getName() == null) ? event.getEventType() : properties.getName();
            eventFailed(str, properties != null ? properties.getPriority() : EventPriority.NORMAL, event.getId(), DataModelHelper.getTenantId(this.appToken), event, e);
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
        }
    }

    private void eventFailed(String name, EventPriority priority, String id, String tenantId, EventBase event, Exception e) {
        TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, tenantId=%s", new Object[]{name, priority.toString(), id, tenantId}));
        if (!this.isLoggingEnabled) {
            return;
        }
        if (e.getMessage() == null || !e.getMessage().equals("Event name cannot be null or empty")) {
            updateStatsForEventRejected(event, priority, EventRejectedReason.UNKNOWN);
        } else {
            updateStatsForEventRejected(event, priority, EventRejectedReason.EVENT_NAME_MISSING);
        }
    }

    private void updateStatsForEventRejected(EventBase event, EventPriority priority, EventRejectedReason reason) {
        if (this.isLoggingEnabled) {
            this.messenger.getEventsHandler().eventAdded(event, priority, this.appToken);
            this.messenger.getEventsHandler().eventRejected(event, priority, this.appToken, reason);
        }
    }

    private void logEvent(EventBase event, EventPriority priority) {
        if (this.isLoggingEnabled) {
            this.messenger.getEventsHandler().eventAdded(event, priority, this.appToken);
            sendRecord(event, priority);
        }
    }

    protected void sendRecord(EventBase event, EventPriority priority) {
        this.messenger.sendRecord(event, priority, this.appToken);
    }

    private void setUpCommonContructorFields(String source, String appToken) {
        this.source = (String) Preconditions.isNotNull(source, "source cannot be null.");
        this.appToken = (String) Preconditions.isNotNull(appToken, "appToken cannot be null.");
        this.sdkVersion = LibraryInfo.getLibraryName() + "-" + LibraryInfo.getLibraryVersion() + "-" + LibraryInfo.getLibraryExperimentation();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        this.dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private EventBase createEventBaseForEvent(String recordType) {
        EventBase event = new EventBase();
        event.setType(recordType);
        event.setEventType(recordType);
        event.setId(UUID.randomUUID().toString());
        long time = System.currentTimeMillis();
        event.setTimestamp(time);
        addCommonFieldsToRecord(event);
        addEventInfoToRecord(event, recordType, time);
        return event;
    }

    private void addEventPropertiesToRecord(EventBase event, EventProperties properties, boolean nameRequired) {
        if (properties != null) {
            if (properties.hasCustomProperties() || nameRequired) {
                Preconditions.isNotNullOrEmpty(properties.getName(), "Event name cannot be null or empty");
            }
            properties.mergeProperties(this.contextProperties);
            properties.mergeProperties(InternalMgrImpl.getContextProperties());
            if (properties.hasCustomProperties()) {
                addPropertiesExtensionsToRecord(event, properties);
            }
            if (!(properties.getName() == null || properties.getName().isEmpty())) {
                sanitizeEventName(properties.getName());
                event.setEventType(properties.getName().toLowerCase());
                event.getExtension().put("EventInfo.Name", properties.getName().toLowerCase());
                SemanticContext semanticContext = (SemanticContext) LogManager.getSemanticContext();
                if (semanticContext.getExperimentIdsMap().containsKey(event.getEventType())) {
                    event.getExtension().put("AppInfo.ExperimentIds", semanticContext.getExperimentIdsMap().get(event.getEventType()));
                }
                semanticContext = this.semanticContext;
                if (semanticContext.getExperimentIdsMap().containsKey(event.getEventType())) {
                    event.getExtension().put("AppInfo.ExperimentIds", semanticContext.getExperimentIdsMap().get(event.getEventType()));
                }
            }
            if (!(properties.getType() == null || properties.getType().isEmpty())) {
                String type = properties.getType().toLowerCase();
                Preconditions.isValidNameAndType(type);
                event.setType(new StringBuilder(TYPE_PREFIX).append(type).toString());
            }
            if (properties.getTimestamp() != null) {
                event.getExtension().put("EventInfo.Time", getISO8601(properties.getTimestamp().getTime()));
                event.setTimestamp(properties.getTimestamp().getTime());
            }
            if (properties.getPriority() == null || properties.getPriority() == EventPriority.UNSPECIFIED) {
                properties.setPriority(EventPriority.NORMAL);
            }
            event.getExtension().put("eventpriority", properties.getPriority().toString());
        }
    }

    private String sanitizeEventName(String name) {
        return name.replace(".", "_");
    }

    private void addEventInfoToRecord(EventBase event, String eventName, long time) {
        event.getExtension().put("EventInfo.Name", eventName);
        event.getExtension().put("EventInfo.Source", this.source);
        String initId = "";
        String sequence = "";
        if (InternalMgrImpl.getIsInitialized().get()) {
            initId = InternalMgrImpl.getInitIdForTenantToken(this.appToken);
            sequence = InternalMgrImpl.getSequenceForTenantToken(this.appToken);
        }
        event.getExtension().put("EventInfo.InitId", initId);
        event.getExtension().put("EventInfo.Sequence", sequence);
        event.getExtension().put("EventInfo.Time", getISO8601(time));
        event.getExtension().put("EventInfo.SdkVersion", this.sdkVersion);
    }

    private void addContextToRecord(EventBase event) {
        addPropertiesExtensionsToRecord(event, InternalMgrImpl.getContextProperties());
        addPropertiesExtensionsToRecord(event, this.contextProperties);
    }

    private void addPropertiesExtensionsToRecord(EventBase event, EventProperties properties) {
        String key;
        EventBase eventBase;
        for (Entry<String, String> entry : properties.getProperties().entrySet()) {
            key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (properties.getPII().containsKey(key)) {
                addPIIToRecord(event, key, value, (PiiKind) properties.getPII().get(key));
            } else {
                event.getExtension().put(key, value);
            }
        }
        for (Entry<String, Double> entry2 : properties.getPropertiesDouble().entrySet()) {
            key = (String) entry2.getKey();
            Double value2 = (Double) entry2.getValue();
            if (properties.getPII().containsKey(key)) {
                eventBase = event;
                addPIIToRecord(eventBase, key, String.format(Locale.US, "%s", new Object[]{value2}), (PiiKind) properties.getPII().get(key));
            } else {
                event.getTypedExtensionDouble().put(key, value2);
            }
        }
        for (Entry<String, Long> entry3 : properties.getPropertiesLong().entrySet()) {
            key = (String) entry3.getKey();
            Long value3 = (Long) entry3.getValue();
            if (properties.getPII().containsKey(key)) {
                eventBase = event;
                addPIIToRecord(eventBase, key, String.format(Locale.US, "%d", new Object[]{value3}), (PiiKind) properties.getPII().get(key));
            } else {
                event.getTypedExtensionInt64().put(key, value3);
            }
        }
        for (Entry<String, Boolean> entry4 : properties.getPropertiesBoolean().entrySet()) {
            key = (String) entry4.getKey();
            Boolean value4 = (Boolean) entry4.getValue();
            if (properties.getPII().containsKey(key)) {
                addPIIToRecord(event, key, value4.toString(), (PiiKind) properties.getPII().get(key));
            } else {
                event.getTypedExtensionBoolean().put(key, value4);
            }
        }
        for (Entry<String, Date> entry5 : properties.getPropertiesDate().entrySet()) {
            key = (String) entry5.getKey();
            Date value5 = (Date) entry5.getValue();
            if (properties.getPII().containsKey(key)) {
                eventBase = event;
                addPIIToRecord(eventBase, key, String.format(Locale.US, "%d", new Object[]{Long.valueOf(convertDateToUTCTicks(value5))}), (PiiKind) properties.getPII().get(key));
            } else {
                event.getTypedExtensionDateTime().put(key, Long.valueOf(convertDateToUTCTicks(value5)));
            }
        }
        for (Entry<String, UUID> entry6 : properties.getPropertiesUUID().entrySet()) {
            key = (String) entry6.getKey();
            UUID value6 = (UUID) entry6.getValue();
            if (properties.getPII().containsKey(key)) {
                addPIIToRecord(event, key, value6.toString(), (PiiKind) properties.getPII().get(key));
            } else {
                event.getTypedExtensionGuid().put(key, convertUUIDToByteArray(value6));
            }
        }
    }

    private void addCommonFieldsToRecord(EventBase event) {
        addSemanticContextFields(event, (SemanticContext) LogManager.getSemanticContext());
        addSemanticContextFields(event, this.semanticContext);
        event.getExtension().put("DeviceInfo.OsName", SystemInformation.getOsName());
        event.getExtension().put("DeviceInfo.OsVersion", SystemInformation.getOsMajorVersion());
        event.getExtension().put("DeviceInfo.OsBuild", VERSION.INCREMENTAL);
        if (this.messenger != null) {
            event.getExtension().put("DeviceInfo.SDKUid", this.messenger.getSdkUID());
        }
        String networkCost = NetworkInformation.getNetworkCost().toString();
        String networkType = NetworkInformation.getNetworkType().toString();
        if (networkCost != null) {
            event.getExtension().put("DeviceInfo.NetworkCost", networkCost);
        }
        if (networkType != null) {
            event.getExtension().put("DeviceInfo.NetworkType", networkType);
        }
        if (this.sessionID != null) {
            event.getExtension().put("Session.Id", this.sessionID);
        }
    }

    private void addSemanticContextFields(EventBase event, SemanticContext semanticContext) {
        for (Entry<String, PiiData> e : semanticContext.getContextFieldsPii().entrySet()) {
            if (!(((PiiData) e.getValue()).value == null || ((PiiData) e.getValue()).value.isEmpty())) {
                addPIIToRecord(event, (String) e.getKey(), ((PiiData) e.getValue()).value, ((PiiData) e.getValue()).piiKind);
            }
        }
        for (Entry<String, String> e2 : semanticContext.getContextFields().entrySet()) {
            if (!(e2.getValue() == null || ((String) e2.getValue()).isEmpty())) {
                event.getExtension().put(e2.getKey(), e2.getValue());
            }
        }
        if (semanticContext.getExperimentIdsMap().containsKey(event.getEventType())) {
            event.getExtension().put("AppInfo.ExperimentIds", semanticContext.getExperimentIdsMap().get(event.getEventType()));
        }
    }

    private void addPIIToRecord(EventBase event, String key, String value, PiiKind piiKind) {
        PII pii = new PII();
        pii.setRawContent(value);
        pii.setKind(piiKind);
        pii.setScrubType(PIIScrubber.O365);
        event.getPIIExtensions().put(key, pii);
    }

    private ArrayList<Byte> convertUUIDToByteArray(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        ArrayList<Byte> uuidBoxed = new ArrayList();
        byte[] uuidUnboxed = bb.array();
        byte temp = uuidUnboxed[3];
        uuidUnboxed[3] = uuidUnboxed[0];
        uuidUnboxed[0] = temp;
        temp = uuidUnboxed[2];
        uuidUnboxed[2] = uuidUnboxed[1];
        uuidUnboxed[1] = temp;
        temp = uuidUnboxed[5];
        uuidUnboxed[5] = uuidUnboxed[4];
        uuidUnboxed[4] = temp;
        temp = uuidUnboxed[7];
        uuidUnboxed[7] = uuidUnboxed[6];
        uuidUnboxed[6] = temp;
        for (byte b : uuidUnboxed) {
            uuidBoxed.add(new Byte(b));
        }
        return uuidBoxed;
    }

    private long convertDateToUTCTicks(Date date) {
        return (date.getTime() + MILLISECS_TILL_UNIX_EPOCH) * 10000;
    }

    public void markLoggingEnabled(IEventMessenger messenger, String source, String appToken) {
        this.messenger = (IEventMessenger) Preconditions.isNotNull(messenger, "EventMessenger cannot be null.");
        if (this.source.isEmpty()) {
            this.source = (String) Preconditions.isNotNull(source, "source cannot be null.");
        }
        if (this.appToken.isEmpty()) {
            this.appToken = (String) Preconditions.isNotNull(appToken, "appToken cannot be null.");
        }
        this.isLoggingEnabled = true;
    }

    String getISO8601(long timeInMillis) {
        return this.dateFormat.format(new Date(timeInMillis));
    }

    SessionDuration getSessionDurationFromTime(long timeInSec) {
        if (timeInSec < 0) {
            return SessionDuration.UNDEFINED;
        }
        if (timeInSec <= 3) {
            return SessionDuration.UP_TO_3_SEC;
        }
        if (timeInSec <= 10) {
            return SessionDuration.UP_TO_10_SEC;
        }
        if (timeInSec <= 30) {
            return SessionDuration.UP_TO_30_SEC;
        }
        if (timeInSec <= 60) {
            return SessionDuration.UP_TO_60_SEC;
        }
        if (timeInSec <= 180) {
            return SessionDuration.UP_TO_3_MIN;
        }
        if (timeInSec <= 600) {
            return SessionDuration.UP_TO_10_MIN;
        }
        if (timeInSec <= 1800) {
            return SessionDuration.UP_TO_30_MIN;
        }
        return SessionDuration.ABOVE_30_MIN;
    }
}
