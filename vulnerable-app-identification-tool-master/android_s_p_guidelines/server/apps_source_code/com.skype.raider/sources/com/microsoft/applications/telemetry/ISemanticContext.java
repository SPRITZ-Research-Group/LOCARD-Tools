package com.microsoft.applications.telemetry;

public interface ISemanticContext {
    void setAppExperimentETag(String str);

    void setAppExperimentIds(String str);

    void setAppExperimentImpressionId(String str);

    void setAppId(String str);

    void setAppLanguage(String str);

    void setAppVersion(String str);

    void setDeviceId(String str);

    void setDeviceMake(String str);

    void setDeviceModel(String str);

    void setEventExperimentIds(String str, String str2);

    void setNetworkProvider(String str);

    void setUserANID(String str);

    void setUserAdvertisingId(String str);

    void setUserId(String str);

    void setUserId(String str, PiiKind piiKind);

    void setUserLanguage(String str);

    void setUserMsaId(String str);

    void setUserTimeZone(String str);
}
