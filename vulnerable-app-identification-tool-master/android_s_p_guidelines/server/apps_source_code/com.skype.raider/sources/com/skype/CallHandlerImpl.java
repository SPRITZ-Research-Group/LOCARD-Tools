package com.skype;

import com.skype.CallHandler.ANSWER_MEDIA_TYPE;
import com.skype.CallHandler.AUDIO_USAGE_MODE;
import com.skype.CallHandler.CallGetParticipantVideos_Result;
import com.skype.CallHandler.CallGetParticipants_Result;
import com.skype.CallHandler.CallGetSendVideos_Result;
import com.skype.CallHandler.CallHandlerIListener;
import com.skype.CallHandler.DTMF;
import com.skype.CallHandler.GetActiveCalls_Result;
import com.skype.CallHandler.GetContentSharingSessions_Result;
import com.skype.CallHandler.MEDIA_DIRECTION;
import com.skype.CallHandler.MEDIA_NEGOTIATION_STATUS_CODE;
import com.skype.CallHandler.MEDIA_PEER_TYPE;
import com.skype.CallHandler.MEDIA_STREAM_STATE;
import com.skype.CallHandler.MODALITY_TYPE;
import com.skype.CallHandler.MUTE_SCOPE;
import com.skype.CallHandler.OPERATIONRESULTCODE;
import com.skype.CallHandler.PARK_CONTEXT;
import com.skype.CallHandler.QUALITYRATING;
import com.skype.CallHandler.REMOVE_ENDPOINT_SCOPE;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

public class CallHandlerImpl extends ObjectInterfaceImpl implements CallHandler, NativeListenable, ObjectInterface {
    private final Set<CallHandlerIListener> m_listeners;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroyCallHandler(this.nativeObject);
        }
    }

    private native int addParticipant(int i, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4);

    private native int addParticipantToCall(int i, byte[] bArr, int i2);

    private native void admitParticipants(int i, byte[][] bArr);

    private native boolean callAssimilate(int i, int i2, byte[] bArr, byte[] bArr2);

    private native byte[] callGetTechnicalInformationJsonNativeString(int i);

    private native boolean callHold(int i, boolean z, byte[] bArr);

    private native boolean callMeBack(int i, byte[] bArr, byte[] bArr2);

    private native void callMuteParticipants(int i, MUTE_SCOPE mute_scope, byte[][] bArr);

    private native boolean callStartAudio(int i, byte[] bArr);

    private native boolean callStopAudio(int i, byte[] bArr);

    private native boolean callUpdateEndpointMetaData(int i, byte[] bArr);

    private native int createContentSharing(int i, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4);

    private native byte[] getCallEndDiagnosticCodeNativeString(int i);

    private native byte[] getDebugInformationNativeString(byte[] bArr);

    private native byte[] getStringPropertyNativeString(int i, PROPKEY propkey);

    private native int joinCall(byte[] bArr, MEDIA_PEER_TYPE media_peer_type, boolean z, boolean z2, boolean z3, boolean z4, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, boolean z5, byte[] bArr9);

    private native int joinSignalingSession(byte[] bArr, MEDIA_PEER_TYPE media_peer_type, int i);

    private native boolean nudgeParticipants(int i, int i2, byte[][] bArr, byte[] bArr2);

    private native int placeCall(byte[] bArr, MEDIA_PEER_TYPE media_peer_type, byte[][] bArr2, boolean z, boolean z2, boolean z3, boolean z4, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9, boolean z5, byte[] bArr10, byte[] bArr11);

    private native int placeCallToVoicemail(byte[] bArr, MEDIA_PEER_TYPE media_peer_type, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5);

    private native void provideCallQualityFeedback(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, QUALITYRATING qualityrating, byte[] bArr5);

    private native void removeParticipantByMri(int i, byte[] bArr, byte[] bArr2, REMOVE_ENDPOINT_SCOPE remove_endpoint_scope);

    private native boolean startCallTransfer(int i, byte[] bArr);

    private native int startCallUnpark(byte[] bArr, int i, PARK_CONTEXT park_context, byte[] bArr2);

    private native int startSignalingSession(byte[] bArr, MEDIA_PEER_TYPE media_peer_type, int i, byte[][] bArr2);

    private native boolean startTransferTargetCall(int i, boolean z, byte[] bArr, byte[] bArr2);

    private native int subscribe(byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, boolean z5);

    private native int subscribeToSignalingSession(byte[] bArr, int i);

    public native boolean answerCall(int i, boolean z);

    public native boolean callAnswer(int i, ANSWER_MEDIA_TYPE answer_media_type);

    public native boolean callAttachSendVideo(int i, int i2);

    public native CallGetParticipantVideos_Result callGetParticipantVideos(int i);

    public native CallGetParticipants_Result callGetParticipants(int i);

    public native CallGetSendVideos_Result callGetSendVideos(int i);

    public native boolean callMute(int i, boolean z);

    public native boolean callMuteSpeaker(int i, boolean z);

    public native int callParticipantGetCallObject(int i);

    public native boolean callSendDtmf(int i, DTMF dtmf);

    public native boolean callSetAudioUsageMode(int i, AUDIO_USAGE_MODE audio_usage_mode);

    public native boolean callSetMaxVideoChannels(int i, int i2);

    public native boolean callShareSystemSound(int i, boolean z);

    public native boolean createAddParticipantParameters(AddParticipantParameters addParticipantParameters);

    public native boolean createSessionParameters(SessionParameters sessionParameters);

    public native boolean endCallForAll(int i);

    public native GetActiveCalls_Result getActiveCalls();

    public native boolean getAddParticipantParameters(int i, AddParticipantParameters addParticipantParameters);

    public native GetContentSharingSessions_Result getContentSharingSessions(int i);

    public native int getIntegerProperty(int i, PROPKEY propkey);

    public native boolean getSessionParameters(int i, SessionParameters sessionParameters);

    public native void initializeListener();

    public native void leaveCall(int i);

    public native void rejectLocally(int i);

    public native void removeParticipant(int i, REMOVE_ENDPOINT_SCOPE remove_endpoint_scope);

    public native boolean startCallPark(int i, PARK_CONTEXT park_context);

    public native boolean startConsultativeCallTransfer(int i, int i2);

    public native void unsubscribe(int i);

    public CallHandlerImpl() {
        this(SkypeFactory.getInstance());
    }

    public CallHandlerImpl(ObjectInterfaceFactory factory) {
        super(factory, factory.createCallHandler());
        this.m_listeners = new HashSet();
        factory.initializeListener(this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public void addListener(CallHandlerIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(CallHandlerIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }

    public void onActiveSpeakerListChanged(int callObjectId, byte[][] activeSpeakers) {
        synchronized (this.m_listeners) {
            for (CallHandlerIListener onActiveSpeakerListChanged : this.m_listeners) {
                onActiveSpeakerListChanged.onActiveSpeakerListChanged(this, callObjectId, NativeStringConvert.ConvertFromNativeStringArray(activeSpeakers));
            }
        }
    }

    public void onAudioStreamStateChanged(int callObjectId, MEDIA_DIRECTION direction, MEDIA_STREAM_STATE streamState) {
        synchronized (this.m_listeners) {
            for (CallHandlerIListener onAudioStreamStateChanged : this.m_listeners) {
                onAudioStreamStateChanged.onAudioStreamStateChanged(this, callObjectId, direction, streamState);
            }
        }
    }

    public void onCallMeBackOperationStatusChange(int callObjectId, byte[] participantMri, int failureReason) {
        synchronized (this.m_listeners) {
            for (CallHandlerIListener onCallMeBackOperationStatusChange : this.m_listeners) {
                onCallMeBackOperationStatusChange.onCallMeBackOperationStatusChange(this, callObjectId, NativeStringConvert.ConvertFromNativeBytes(participantMri), failureReason);
            }
        }
    }

    public void onCallTransferCallReceived(int callObjectId, int targetCallObjectId, byte[] transferorMri, byte[] transferTargetMri) {
        synchronized (this.m_listeners) {
            for (CallHandlerIListener onCallTransferCallReceived : this.m_listeners) {
                onCallTransferCallReceived.onCallTransferCallReceived(this, callObjectId, targetCallObjectId, NativeStringConvert.ConvertFromNativeBytes(transferorMri), NativeStringConvert.ConvertFromNativeBytes(transferTargetMri));
            }
        }
    }

    public void onDominantSpeakerListChanged(int callObjectId, byte[][] dominantSpeakers) {
        synchronized (this.m_listeners) {
            for (CallHandlerIListener onDominantSpeakerListChanged : this.m_listeners) {
                onDominantSpeakerListChanged.onDominantSpeakerListChanged(this, callObjectId, NativeStringConvert.ConvertFromNativeStringArray(dominantSpeakers));
            }
        }
    }

    public void onMediaNegotiationStatusChange(int callObjectId, MODALITY_TYPE modalityType, MEDIA_NEGOTIATION_STATUS_CODE mediaNegotiationStatusCode) {
        synchronized (this.m_listeners) {
            for (CallHandlerIListener onMediaNegotiationStatusChange : this.m_listeners) {
                onMediaNegotiationStatusChange.onMediaNegotiationStatusChange(this, callObjectId, modalityType, mediaNegotiationStatusCode);
            }
        }
    }

    public void onNudgeParticipantsOperationStatusChanged(int callObjectId, byte[] context, int failureReason) {
        synchronized (this.m_listeners) {
            for (CallHandlerIListener onNudgeParticipantsOperationStatusChanged : this.m_listeners) {
                onNudgeParticipantsOperationStatusChanged.onNudgeParticipantsOperationStatusChanged(this, callObjectId, NativeStringConvert.ConvertFromNativeBytes(context), failureReason);
            }
        }
    }

    public void onProxiedPushNotification(int eventId, byte[] payload) {
        synchronized (this.m_listeners) {
            for (CallHandlerIListener onProxiedPushNotification : this.m_listeners) {
                onProxiedPushNotification.onProxiedPushNotification(this, eventId, NativeStringConvert.ConvertFromNativeBytes(payload));
            }
        }
    }

    public void onRemoteVideosCountChanged(int participantObjectId) {
        synchronized (this.m_listeners) {
            for (CallHandlerIListener onRemoteVideosCountChanged : this.m_listeners) {
                onRemoteVideosCountChanged.onRemoteVideosCountChanged(this, participantObjectId);
            }
        }
    }

    public void onUnmuteSelfOperationStatusChange(int callObjectId, OPERATIONRESULTCODE operationResult, int failureReason) {
        synchronized (this.m_listeners) {
            for (CallHandlerIListener onUnmuteSelfOperationStatusChange : this.m_listeners) {
                onUnmuteSelfOperationStatusChange.onUnmuteSelfOperationStatusChange(this, callObjectId, operationResult, failureReason);
            }
        }
    }

    public int addParticipant(int callObjectId, String participant, String threadId, String messageId, String additionalData) {
        return addParticipant(callObjectId, NativeStringConvert.ConvertToNativeBytes(participant), NativeStringConvert.ConvertToNativeBytes(threadId), NativeStringConvert.ConvertToNativeBytes(messageId), NativeStringConvert.ConvertToNativeBytes(additionalData));
    }

    public int addParticipant(int callObjectId, String participant) {
        return addParticipant(callObjectId, participant, "", "", "");
    }

    public int addParticipant(int callObjectId, String participant, String threadId) {
        return addParticipant(callObjectId, participant, threadId, "", "");
    }

    public int addParticipant(int callObjectId, String participant, String threadId, String messageId) {
        return addParticipant(callObjectId, participant, threadId, messageId, "");
    }

    public int addParticipantToCall(int callObjectId, String participant, int additionalParametersObjectId) {
        return addParticipantToCall(callObjectId, NativeStringConvert.ConvertToNativeBytes(participant), additionalParametersObjectId);
    }

    public void admitParticipants(int callObjectId, String[] participantList) {
        admitParticipants(callObjectId, NativeStringConvert.ConvertArrToNativeByteArr(participantList));
    }

    public boolean answerCall(int callObjectId) {
        return answerCall(callObjectId, false);
    }

    public boolean callAssimilate(int callObjectId1, int callObjectId2, String threadId, String messageId) {
        return callAssimilate(callObjectId1, callObjectId2, NativeStringConvert.ConvertToNativeBytes(threadId), NativeStringConvert.ConvertToNativeBytes(messageId));
    }

    public boolean callAssimilate(int callObjectId1, int callObjectId2) {
        return callAssimilate(callObjectId1, callObjectId2, "", "");
    }

    public boolean callAssimilate(int callObjectId1, int callObjectId2, String threadId) {
        return callAssimilate(callObjectId1, callObjectId2, threadId, "");
    }

    public String callGetTechnicalInformationJson(int callObjectId) {
        return NativeStringConvert.ConvertFromNativeBytes(callGetTechnicalInformationJsonNativeString(callObjectId));
    }

    public boolean callHold(int callObjectId, boolean hold, String negotiationTag) {
        return callHold(callObjectId, hold, NativeStringConvert.ConvertToNativeBytes(negotiationTag));
    }

    public boolean callHold(int callObjectId, boolean hold) {
        return callHold(callObjectId, hold, "");
    }

    public boolean callMeBack(int callObjectId, String participantMri, String assertedId) {
        return callMeBack(callObjectId, NativeStringConvert.ConvertToNativeBytes(participantMri), NativeStringConvert.ConvertToNativeBytes(assertedId));
    }

    public void callMuteParticipants(int callObjectId, MUTE_SCOPE muteScope, String[] participantList) {
        callMuteParticipants(callObjectId, muteScope, NativeStringConvert.ConvertArrToNativeByteArr(participantList));
    }

    public boolean callStartAudio(int callObjectId, String negotiationTag) {
        return callStartAudio(callObjectId, NativeStringConvert.ConvertToNativeBytes(negotiationTag));
    }

    public boolean callStartAudio(int callObjectId) {
        return callStartAudio(callObjectId, "");
    }

    public boolean callStopAudio(int callObjectId, String negotiationTag) {
        return callStopAudio(callObjectId, NativeStringConvert.ConvertToNativeBytes(negotiationTag));
    }

    public boolean callStopAudio(int callObjectId) {
        return callStopAudio(callObjectId, "");
    }

    public boolean callUpdateEndpointMetaData(int callObjectId, String endpointMetaData) {
        return callUpdateEndpointMetaData(callObjectId, NativeStringConvert.ConvertToNativeBytes(endpointMetaData));
    }

    public boolean callUpdateEndpointMetaData(int callObjectId) {
        return callUpdateEndpointMetaData(callObjectId, "");
    }

    public int createContentSharing(int callObjectId, String contentSharingGuid, String contentSharingIdentity, String subject, String initialContentSharingSessionState) {
        return createContentSharing(callObjectId, NativeStringConvert.ConvertToNativeBytes(contentSharingGuid), NativeStringConvert.ConvertToNativeBytes(contentSharingIdentity), NativeStringConvert.ConvertToNativeBytes(subject), NativeStringConvert.ConvertToNativeBytes(initialContentSharingSessionState));
    }

    public int createContentSharing(int callObjectId, String contentSharingGuid, String contentSharingIdentity) {
        return createContentSharing(callObjectId, contentSharingGuid, contentSharingIdentity, "", "");
    }

    public int createContentSharing(int callObjectId, String contentSharingGuid, String contentSharingIdentity, String subject) {
        return createContentSharing(callObjectId, contentSharingGuid, contentSharingIdentity, subject, "");
    }

    public String getCallEndDiagnosticCode(int callObjectId) {
        return NativeStringConvert.ConvertFromNativeBytes(getCallEndDiagnosticCodeNativeString(callObjectId));
    }

    public String getDebugInformation(String command) {
        return NativeStringConvert.ConvertFromNativeBytes(getDebugInformationNativeString(NativeStringConvert.ConvertToNativeBytes(command)));
    }

    public String getStringProperty(int objectId, PROPKEY propKey) {
        return NativeStringConvert.ConvertFromNativeBytes(getStringPropertyNativeString(objectId, propKey));
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetaData, String onBehalfOf, boolean enableLightWeightMeeting, String broadcastContext) {
        return joinCall(NativeStringConvert.ConvertToNativeBytes(joinContext), mediaPeerType, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, NativeStringConvert.ConvertToNativeBytes(threadId), NativeStringConvert.ConvertToNativeBytes(messageId), NativeStringConvert.ConvertToNativeBytes(subject), NativeStringConvert.ConvertToNativeBytes(conversationType), NativeStringConvert.ConvertToNativeBytes(meetingInfo), NativeStringConvert.ConvertToNativeBytes(endpointMetaData), NativeStringConvert.ConvertToNativeBytes(onBehalfOf), enableLightWeightMeeting, NativeStringConvert.ConvertToNativeBytes(broadcastContext));
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType) {
        return joinCall(joinContext, mediaPeerType, false, false, true, false, "", "", "", "", "", "", "", false, "");
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType, boolean isVideoEnabled) {
        return joinCall(joinContext, mediaPeerType, isVideoEnabled, false, true, false, "", "", "", "", "", "", "", false, "");
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType, boolean isVideoEnabled, boolean isGoLive) {
        return joinCall(joinContext, mediaPeerType, isVideoEnabled, isGoLive, true, false, "", "", "", "", "", "", "", false, "");
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless) {
        return joinCall(joinContext, mediaPeerType, isVideoEnabled, isGoLive, allowHostless, false, "", "", "", "", "", "", "", false, "");
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration) {
        return joinCall(joinContext, mediaPeerType, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, "", "", "", "", "", "", "", false, "");
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId) {
        return joinCall(joinContext, mediaPeerType, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, "", "", "", "", "", "", false, "");
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId) {
        return joinCall(joinContext, mediaPeerType, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, "", "", "", "", "", false, "");
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject) {
        return joinCall(joinContext, mediaPeerType, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, "", "", "", "", false, "");
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType) {
        return joinCall(joinContext, mediaPeerType, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, "", "", "", false, "");
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo) {
        return joinCall(joinContext, mediaPeerType, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, meetingInfo, "", "", false, "");
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetaData) {
        return joinCall(joinContext, mediaPeerType, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, meetingInfo, endpointMetaData, "", false, "");
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetaData, String onBehalfOf) {
        return joinCall(joinContext, mediaPeerType, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, meetingInfo, endpointMetaData, onBehalfOf, false, "");
    }

    public int joinCall(String joinContext, MEDIA_PEER_TYPE mediaPeerType, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetaData, String onBehalfOf, boolean enableLightWeightMeeting) {
        return joinCall(joinContext, mediaPeerType, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, meetingInfo, endpointMetaData, onBehalfOf, enableLightWeightMeeting, "");
    }

    public int joinSignalingSession(String joinContext, MEDIA_PEER_TYPE mediaPeerType, int sessionParametersObjectId) {
        return joinSignalingSession(NativeStringConvert.ConvertToNativeBytes(joinContext), mediaPeerType, sessionParametersObjectId);
    }

    public boolean nudgeParticipants(int callObjectId, int additionalParametersObjectId, String[] participantList, String context) {
        return nudgeParticipants(callObjectId, additionalParametersObjectId, NativeStringConvert.ConvertArrToNativeByteArr(participantList), NativeStringConvert.ConvertToNativeBytes(context));
    }

    public boolean nudgeParticipants(int callObjectId, int additionalParametersObjectId) {
        return nudgeParticipants(callObjectId, additionalParametersObjectId, null, "");
    }

    public boolean nudgeParticipants(int callObjectId, int additionalParametersObjectId, String[] participantList) {
        return nudgeParticipants(callObjectId, additionalParametersObjectId, participantList, "");
    }

    public int placeCallToVoicemail(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String participant, String threadId, String voicemailResourcePath, String voicemailItemId) {
        return placeCallToVoicemail(NativeStringConvert.ConvertToNativeBytes(callGuid), mediaPeerType, NativeStringConvert.ConvertToNativeBytes(participant), NativeStringConvert.ConvertToNativeBytes(threadId), NativeStringConvert.ConvertToNativeBytes(voicemailResourcePath), NativeStringConvert.ConvertToNativeBytes(voicemailItemId));
    }

    public int placeCallToVoicemail(String callGuid, MEDIA_PEER_TYPE mediaPeerType) {
        return placeCallToVoicemail(callGuid, mediaPeerType, "", "", "", "");
    }

    public int placeCallToVoicemail(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String participant) {
        return placeCallToVoicemail(callGuid, mediaPeerType, participant, "", "", "");
    }

    public int placeCallToVoicemail(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String participant, String threadId) {
        return placeCallToVoicemail(callGuid, mediaPeerType, participant, threadId, "", "");
    }

    public int placeCallToVoicemail(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String participant, String threadId, String voicemailResourcePath) {
        return placeCallToVoicemail(callGuid, mediaPeerType, participant, threadId, voicemailResourcePath, "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetaData, String onBehalfOf, boolean enableLightWeightMeeting, String emergencyContent, String broadcastContext) {
        return placeCall(NativeStringConvert.ConvertToNativeBytes(callGuid), mediaPeerType, NativeStringConvert.ConvertArrToNativeByteArr(participantList), isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, NativeStringConvert.ConvertToNativeBytes(threadId), NativeStringConvert.ConvertToNativeBytes(messageId), NativeStringConvert.ConvertToNativeBytes(subject), NativeStringConvert.ConvertToNativeBytes(conversationType), NativeStringConvert.ConvertToNativeBytes(meetingInfo), NativeStringConvert.ConvertToNativeBytes(endpointMetaData), NativeStringConvert.ConvertToNativeBytes(onBehalfOf), enableLightWeightMeeting, NativeStringConvert.ConvertToNativeBytes(emergencyContent), NativeStringConvert.ConvertToNativeBytes(broadcastContext));
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType) {
        return placeCall(callGuid, mediaPeerType, null, false, false, true, false, "", "", "", "", "", "", "", false, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList) {
        return placeCall(callGuid, mediaPeerType, participantList, false, false, true, false, "", "", "", "", "", "", "", false, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled) {
        return placeCall(callGuid, mediaPeerType, participantList, isVideoEnabled, false, true, false, "", "", "", "", "", "", "", false, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled, boolean isGoLive) {
        return placeCall(callGuid, mediaPeerType, participantList, isVideoEnabled, isGoLive, true, false, "", "", "", "", "", "", "", false, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless) {
        return placeCall(callGuid, mediaPeerType, participantList, isVideoEnabled, isGoLive, allowHostless, false, "", "", "", "", "", "", "", false, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration) {
        return placeCall(callGuid, mediaPeerType, participantList, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, "", "", "", "", "", "", "", false, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId) {
        return placeCall(callGuid, mediaPeerType, participantList, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, "", "", "", "", "", "", false, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId) {
        return placeCall(callGuid, mediaPeerType, participantList, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, "", "", "", "", "", false, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject) {
        return placeCall(callGuid, mediaPeerType, participantList, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, "", "", "", "", false, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType) {
        return placeCall(callGuid, mediaPeerType, participantList, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, "", "", "", false, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo) {
        return placeCall(callGuid, mediaPeerType, participantList, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, meetingInfo, "", "", false, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetaData) {
        return placeCall(callGuid, mediaPeerType, participantList, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, meetingInfo, endpointMetaData, "", false, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetaData, String onBehalfOf) {
        return placeCall(callGuid, mediaPeerType, participantList, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, meetingInfo, endpointMetaData, onBehalfOf, false, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetaData, String onBehalfOf, boolean enableLightWeightMeeting) {
        return placeCall(callGuid, mediaPeerType, participantList, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, meetingInfo, endpointMetaData, onBehalfOf, enableLightWeightMeeting, "", "");
    }

    public int placeCall(String callGuid, MEDIA_PEER_TYPE mediaPeerType, String[] participantList, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetaData, String onBehalfOf, boolean enableLightWeightMeeting, String emergencyContent) {
        return placeCall(callGuid, mediaPeerType, participantList, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, meetingInfo, endpointMetaData, onBehalfOf, enableLightWeightMeeting, emergencyContent, "");
    }

    public void provideCallQualityFeedback(String callId, String participantId, String questionaryId, String trackingReason, QUALITYRATING rating, String problemTokens) {
        provideCallQualityFeedback(NativeStringConvert.ConvertToNativeBytes(callId), NativeStringConvert.ConvertToNativeBytes(participantId), NativeStringConvert.ConvertToNativeBytes(questionaryId), NativeStringConvert.ConvertToNativeBytes(trackingReason), rating, NativeStringConvert.ConvertToNativeBytes(problemTokens));
    }

    public void removeParticipant(int participantObjectId) {
        removeParticipant(participantObjectId, REMOVE_ENDPOINT_SCOPE.REMOVE_ENDPOINT_SCOPE_NONE);
    }

    public void removeParticipantByMri(int callObjectId, String participantMri, String endpoint, REMOVE_ENDPOINT_SCOPE removeEndpointScope) {
        removeParticipantByMri(callObjectId, NativeStringConvert.ConvertToNativeBytes(participantMri), NativeStringConvert.ConvertToNativeBytes(endpoint), removeEndpointScope);
    }

    public void removeParticipantByMri(int callObjectId, String participantMri) {
        removeParticipantByMri(callObjectId, participantMri, "", REMOVE_ENDPOINT_SCOPE.REMOVE_ENDPOINT_SCOPE_NONE);
    }

    public void removeParticipantByMri(int callObjectId, String participantMri, String endpoint) {
        removeParticipantByMri(callObjectId, participantMri, endpoint, REMOVE_ENDPOINT_SCOPE.REMOVE_ENDPOINT_SCOPE_NONE);
    }

    public boolean startCallTransfer(int callObjectId, String participantId) {
        return startCallTransfer(callObjectId, NativeStringConvert.ConvertToNativeBytes(participantId));
    }

    public int startCallUnpark(String callGuid, int sessionParametersObjectId, PARK_CONTEXT parkContext, String pickupCode) {
        return startCallUnpark(NativeStringConvert.ConvertToNativeBytes(callGuid), sessionParametersObjectId, parkContext, NativeStringConvert.ConvertToNativeBytes(pickupCode));
    }

    public int startSignalingSession(String callGuid, MEDIA_PEER_TYPE mediaPeerType, int sessionParametersObjectId, String[] participantList) {
        return startSignalingSession(NativeStringConvert.ConvertToNativeBytes(callGuid), mediaPeerType, sessionParametersObjectId, NativeStringConvert.ConvertArrToNativeByteArr(participantList));
    }

    public int startSignalingSession(String callGuid, MEDIA_PEER_TYPE mediaPeerType, int sessionParametersObjectId) {
        return startSignalingSession(callGuid, mediaPeerType, sessionParametersObjectId, null);
    }

    public boolean startTransferTargetCall(int callObjectId, boolean isVideoEnabled, String threadId, String messageId) {
        return startTransferTargetCall(callObjectId, isVideoEnabled, NativeStringConvert.ConvertToNativeBytes(threadId), NativeStringConvert.ConvertToNativeBytes(messageId));
    }

    public boolean startTransferTargetCall(int callObjectId) {
        return startTransferTargetCall(callObjectId, false, "", "");
    }

    public boolean startTransferTargetCall(int callObjectId, boolean isVideoEnabled) {
        return startTransferTargetCall(callObjectId, isVideoEnabled, "", "");
    }

    public boolean startTransferTargetCall(int callObjectId, boolean isVideoEnabled, String threadId) {
        return startTransferTargetCall(callObjectId, isVideoEnabled, threadId, "");
    }

    public int subscribeToSignalingSession(String joinContext, int sessionParametersObjectId) {
        return subscribeToSignalingSession(NativeStringConvert.ConvertToNativeBytes(joinContext), sessionParametersObjectId);
    }

    public int subscribe(String joinContext, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetaData, String onBehalfOf, boolean enableLightWeightMeeting) {
        return subscribe(NativeStringConvert.ConvertToNativeBytes(joinContext), isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, NativeStringConvert.ConvertToNativeBytes(threadId), NativeStringConvert.ConvertToNativeBytes(messageId), NativeStringConvert.ConvertToNativeBytes(subject), NativeStringConvert.ConvertToNativeBytes(conversationType), NativeStringConvert.ConvertToNativeBytes(meetingInfo), NativeStringConvert.ConvertToNativeBytes(endpointMetaData), NativeStringConvert.ConvertToNativeBytes(onBehalfOf), enableLightWeightMeeting);
    }

    public int subscribe(String joinContext) {
        return subscribe(joinContext, false, false, true, false, "", "", "", "", "", "", "", false);
    }

    public int subscribe(String joinContext, boolean isVideoEnabled) {
        return subscribe(joinContext, isVideoEnabled, false, true, false, "", "", "", "", "", "", "", false);
    }

    public int subscribe(String joinContext, boolean isVideoEnabled, boolean isGoLive) {
        return subscribe(joinContext, isVideoEnabled, isGoLive, true, false, "", "", "", "", "", "", "", false);
    }

    public int subscribe(String joinContext, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless) {
        return subscribe(joinContext, isVideoEnabled, isGoLive, allowHostless, false, "", "", "", "", "", "", "", false);
    }

    public int subscribe(String joinContext, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration) {
        return subscribe(joinContext, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, "", "", "", "", "", "", "", false);
    }

    public int subscribe(String joinContext, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId) {
        return subscribe(joinContext, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, "", "", "", "", "", "", false);
    }

    public int subscribe(String joinContext, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId) {
        return subscribe(joinContext, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, "", "", "", "", "", false);
    }

    public int subscribe(String joinContext, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject) {
        return subscribe(joinContext, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, "", "", "", "", false);
    }

    public int subscribe(String joinContext, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType) {
        return subscribe(joinContext, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, "", "", "", false);
    }

    public int subscribe(String joinContext, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo) {
        return subscribe(joinContext, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, meetingInfo, "", "", false);
    }

    public int subscribe(String joinContext, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetaData) {
        return subscribe(joinContext, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, meetingInfo, endpointMetaData, "", false);
    }

    public int subscribe(String joinContext, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetaData, String onBehalfOf) {
        return subscribe(joinContext, isVideoEnabled, isGoLive, allowHostless, enableGroupCallMeetupGeneration, threadId, messageId, subject, conversationType, meetingInfo, endpointMetaData, onBehalfOf, false);
    }
}
