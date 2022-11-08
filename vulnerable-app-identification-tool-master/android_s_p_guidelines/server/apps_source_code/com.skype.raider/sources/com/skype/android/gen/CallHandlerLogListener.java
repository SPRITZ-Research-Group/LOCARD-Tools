package com.skype.android.gen;

import com.skype.CallHandler;
import com.skype.CallHandler.CallHandlerIListener;
import com.skype.CallHandler.MEDIA_DIRECTION;
import com.skype.CallHandler.MEDIA_NEGOTIATION_STATUS_CODE;
import com.skype.CallHandler.MEDIA_STREAM_STATE;
import com.skype.CallHandler.MODALITY_TYPE;
import com.skype.CallHandler.OPERATIONRESULTCODE;
import com.skype.ObjectInterface;
import com.skype.ObjectInterface.ObjectInterfaceIListener;
import com.skype.PROPKEY;

public class CallHandlerLogListener implements CallHandlerIListener, ObjectInterfaceIListener {
    public void onActiveSpeakerListChanged(CallHandler sender, int callObjectId, String[] activeSpeakers) {
    }

    public void onAudioStreamStateChanged(CallHandler sender, int callObjectId, MEDIA_DIRECTION direction, MEDIA_STREAM_STATE streamState) {
    }

    public void onCallMeBackOperationStatusChange(CallHandler sender, int callObjectId, String participantMri, int failureReason) {
    }

    public void onCallTransferCallReceived(CallHandler sender, int callObjectId, int targetCallObjectId, String transferorMri, String transferTargetMri) {
    }

    public void onDominantSpeakerListChanged(CallHandler sender, int callObjectId, String[] dominantSpeakers) {
    }

    public void onMediaNegotiationStatusChange(CallHandler sender, int callObjectId, MODALITY_TYPE modalityType, MEDIA_NEGOTIATION_STATUS_CODE mediaNegotiationStatusCode) {
    }

    public void onNudgeParticipantsOperationStatusChanged(CallHandler sender, int callObjectId, String context, int failureReason) {
    }

    public void onProxiedPushNotification(CallHandler sender, int eventId, String payload) {
    }

    public void onRemoteVideosCountChanged(CallHandler sender, int participantObjectId) {
    }

    public void onUnmuteSelfOperationStatusChange(CallHandler sender, int callObjectId, OPERATIONRESULTCODE operationResult, int failureReason) {
    }

    public void onPropertyChange(ObjectInterface sender, PROPKEY propKey) {
    }
}
