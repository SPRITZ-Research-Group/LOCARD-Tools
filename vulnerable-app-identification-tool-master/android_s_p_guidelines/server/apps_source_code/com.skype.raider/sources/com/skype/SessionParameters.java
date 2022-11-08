package com.skype;

import com.skype.SkyLib.IN_MEMORY_OBJECTTYPE;

public interface SessionParameters {

    public interface SessionParametersIListener {
    }

    void addListener(SessionParametersIListener sessionParametersIListener);

    IN_MEMORY_OBJECTTYPE getInMemObjectType();

    int getObjectID();

    void removeListener(SessionParametersIListener sessionParametersIListener);

    void setAllowHostless(boolean z);

    void setBroadcastContext(String str);

    void setCallKey(String str);

    void setConnectionType(int i);

    void setConversationType(String str);

    void setDebugInfo(String str);

    void setEmergencyContent(String str);

    void setEnableGroupCallMeetupGeneration(boolean z);

    void setEnableLightWeightMeeting(boolean z);

    void setEncryptedKey(String str);

    void setEndpointMetadata(String str);

    void setInvitationType(int i);

    void setIsGoLive(boolean z);

    void setIsVideoEnabled(boolean z);

    void setMaxVideoChannels(int i);

    void setMediaPeerType(int i);

    void setMeetingInfo(String str);

    void setMessageId(String str);

    void setModalityDirection(int i, int i2);

    void setMuteFlags(int i);

    void setNegotiationTag(String str);

    void setOnBehalfOf(String str);

    void setParticipantLegId(String str);

    void setRoutingFlags(String[] strArr);

    void setScenario(String str);

    void setSubject(String str);

    void setThreadId(String str);
}
