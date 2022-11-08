package com.skype;

import com.skype.SkyLib.IN_MEMORY_OBJECTTYPE;

public interface AddParticipantParameters {

    public interface AddParticipantParametersIListener {
    }

    void addListener(AddParticipantParametersIListener addParticipantParametersIListener);

    IN_MEMORY_OBJECTTYPE getInMemObjectType();

    int getObjectID();

    void removeListener(AddParticipantParametersIListener addParticipantParametersIListener);

    void setAdditionalData(String str);

    void setMessageId(String str);

    void setThreadId(String str);
}
