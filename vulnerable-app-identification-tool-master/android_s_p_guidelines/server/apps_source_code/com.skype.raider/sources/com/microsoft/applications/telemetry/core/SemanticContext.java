package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.ISemanticContext;
import com.microsoft.applications.telemetry.PiiKind;
import java.util.concurrent.ConcurrentHashMap;

public class SemanticContext implements ISemanticContext {
    private static final String LOG_TAG = ("[ACT]:" + SemanticContext.class.getSimpleName().toUpperCase());
    private boolean allowDeviceInfoFields = false;
    private ConcurrentHashMap<String, String> commonContextFields = new ConcurrentHashMap();
    private ConcurrentHashMap<String, PiiData> commonContextFieldsPii = new ConcurrentHashMap();
    private ConcurrentHashMap<String, String> experimentIdsMap = new ConcurrentHashMap();

    protected SemanticContext(boolean allowDeviceInfoFields) {
        this.allowDeviceInfoFields = allowDeviceInfoFields;
    }

    public void setAppId(String appId) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setAppId|appId: %s", new Object[]{appId}));
        setContextField("AppInfo.Id", appId, null);
    }

    public void setAppVersion(String appVersion) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setAppVersion|appVersion: %s", new Object[]{appVersion}));
        setContextField("AppInfo.Version", appVersion, null);
    }

    public void setAppLanguage(String appLanguage) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setAppLanguage|appLanguage: %s", new Object[]{appLanguage}));
        setContextField("AppInfo.Language", appLanguage, null);
    }

    public void setAppExperimentIds(String experimentIds) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setAppExperimentIds|experimentIds: %s", new Object[]{experimentIds}));
        setContextField("AppInfo.ExperimentIds", experimentIds, null);
    }

    public void setEventExperimentIds(String eventName, String experimentIds) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setEventExperimentIds|eventName: %s|experimentIds: %s", new Object[]{eventName, experimentIds}));
        if (eventName != null && !eventName.isEmpty()) {
            this.experimentIdsMap.put(eventName.toLowerCase(), experimentIds);
        }
    }

    public void setAppExperimentETag(String eTag) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setAppExperimentETag|eTag: %s", new Object[]{eTag}));
        setContextField("AppInfo.ETag", eTag, null);
        removeContextField("AppInfo.ExperimentIds");
        this.experimentIdsMap.clear();
    }

    public void setAppExperimentImpressionId(String appExperimentImpressionId) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("SetAppExperimentImpressionId|appExperimentImpressionId: %s", new Object[]{appExperimentImpressionId}));
        setContextField("Session.ImpressionId", appExperimentImpressionId, null);
        removeContextField("AppInfo.ExperimentIds");
        this.experimentIdsMap.clear();
    }

    public void setDeviceId(String deviceId) {
        if (this.allowDeviceInfoFields) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("setDeviceId|deviceId: %s", new Object[]{deviceId}));
            setContextField("DeviceInfo.Id", deviceId, null);
        }
    }

    public void setDeviceMake(String deviceMake) {
        if (this.allowDeviceInfoFields) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("setDeviceMake|deviceMake: %s", new Object[]{deviceMake}));
            setContextField("DeviceInfo.Make", deviceMake, null);
        }
    }

    public void setDeviceModel(String deviceModel) {
        if (this.allowDeviceInfoFields) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("setDeviceModel|deviceModel: %s", new Object[]{deviceModel}));
            setContextField("DeviceInfo.Model", deviceModel, null);
        }
    }

    public void setNetworkProvider(String networkProvider) {
        if (this.allowDeviceInfoFields) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("setNetworkProvider|networkProvider: %s", new Object[]{networkProvider}));
            setContextField("DeviceInfo.NetworkProvider", networkProvider, null);
        }
    }

    protected void setOsBuild(String osBuild) {
        if (this.allowDeviceInfoFields) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("setOsBuild|deviceModel: %s", new Object[]{osBuild}));
            setContextField("DeviceInfo.OsBuild", osBuild, null);
        }
    }

    public void setUserId(String userId) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setUserId|userId: %s", new Object[]{userId}));
        setUserId(userId, PiiKind.IDENTITY);
    }

    public void setUserId(String userId, PiiKind piiKind) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setUserId|userId: %s|piiKind: %s", new Object[]{userId, piiKind}));
        setContextField("UserInfo.Id", userId, piiKind);
    }

    public void setUserMsaId(String userMsaId) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setUserMsaId|userMsaId: %s", new Object[]{userMsaId}));
        setContextField("UserInfo.MsaId", userMsaId, null);
    }

    public void setUserANID(String userANID) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setUserANID|userANID: %s", new Object[]{userANID}));
        setContextField("UserInfo.ANID", userANID, null);
    }

    public void setUserAdvertisingId(String userAdvertisingId) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setUserAdvertisingId|userAdvertisingId: %s", new Object[]{userAdvertisingId}));
        setContextField("UserInfo.AdvertisingId", userAdvertisingId, null);
    }

    public void setUserLanguage(String language) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setUserLanguage|language: %s", new Object[]{language}));
        setContextField("UserInfo.Language", language, null);
    }

    public void setUserTimeZone(String timeZone) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setUserTimeZone|timeZone: %s", new Object[]{timeZone}));
        setContextField("UserInfo.TimeZone", timeZone, null);
    }

    protected boolean containsKey(String key) {
        return this.commonContextFields.containsKey(key);
    }

    protected ConcurrentHashMap<String, String> getExperimentIdsMap() {
        return this.experimentIdsMap;
    }

    protected ConcurrentHashMap<String, String> getContextFields() {
        return this.commonContextFields;
    }

    protected ConcurrentHashMap<String, PiiData> getContextFieldsPii() {
        return this.commonContextFieldsPii;
    }

    private synchronized void setContextField(String name, String value, PiiKind pii) {
        if (pii != null) {
            if (pii != PiiKind.NONE) {
                this.commonContextFieldsPii.put(name, new PiiData(value, pii));
            }
        }
        this.commonContextFields.put(name, value);
    }

    private synchronized void removeContextField(String name) {
        if (this.commonContextFields.containsKey(name)) {
            this.commonContextFields.remove(name);
        }
    }
}
