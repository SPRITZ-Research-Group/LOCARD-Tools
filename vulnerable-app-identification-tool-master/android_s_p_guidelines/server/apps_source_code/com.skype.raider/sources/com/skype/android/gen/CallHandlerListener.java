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
import com.skype.android.event.EventBus;
import com.skype.android.event.EventBusInstance;
import com.skype.android.event.ListenerError;
import com.skype.android.event.ListenerErrorReporter;

public class CallHandlerListener implements CallHandlerIListener, ObjectInterfaceIListener {
    final EventBus eventBus = EventBusInstance.a();

    public static class OnActiveSpeakerListChanged {
        private String[] _activeSpeakers;
        private int _callObjectId;
        private CallHandler _sender;

        public OnActiveSpeakerListChanged(CallHandler sender, int callObjectId, String[] activeSpeakers) {
            this._sender = sender;
            this._callObjectId = callObjectId;
            this._activeSpeakers = activeSpeakers;
        }

        public CallHandler getSender() {
            return this._sender;
        }

        public int getCallObjectId() {
            return this._callObjectId;
        }

        public String[] getActiveSpeakers() {
            return this._activeSpeakers;
        }
    }

    public static class OnAudioStreamStateChanged {
        private int _callObjectId;
        private MEDIA_DIRECTION _direction;
        private CallHandler _sender;
        private MEDIA_STREAM_STATE _streamState;

        public OnAudioStreamStateChanged(CallHandler sender, int callObjectId, MEDIA_DIRECTION direction, MEDIA_STREAM_STATE streamState) {
            this._sender = sender;
            this._callObjectId = callObjectId;
            this._direction = direction;
            this._streamState = streamState;
        }

        public CallHandler getSender() {
            return this._sender;
        }

        public int getCallObjectId() {
            return this._callObjectId;
        }

        public MEDIA_DIRECTION getDirection() {
            return this._direction;
        }

        public MEDIA_STREAM_STATE getStreamState() {
            return this._streamState;
        }
    }

    public static class OnCallMeBackOperationStatusChange {
        private int _callObjectId;
        private int _failureReason;
        private String _participantMri;
        private CallHandler _sender;

        public OnCallMeBackOperationStatusChange(CallHandler sender, int callObjectId, String participantMri, int failureReason) {
            this._sender = sender;
            this._callObjectId = callObjectId;
            this._participantMri = participantMri;
            this._failureReason = failureReason;
        }

        public CallHandler getSender() {
            return this._sender;
        }

        public int getCallObjectId() {
            return this._callObjectId;
        }

        public String getParticipantMri() {
            return this._participantMri;
        }

        public int getFailureReason() {
            return this._failureReason;
        }
    }

    public static class OnCallTransferCallReceived {
        private int _callObjectId;
        private CallHandler _sender;
        private int _targetCallObjectId;
        private String _transferTargetMri;
        private String _transferorMri;

        public OnCallTransferCallReceived(CallHandler sender, int callObjectId, int targetCallObjectId, String transferorMri, String transferTargetMri) {
            this._sender = sender;
            this._callObjectId = callObjectId;
            this._targetCallObjectId = targetCallObjectId;
            this._transferorMri = transferorMri;
            this._transferTargetMri = transferTargetMri;
        }

        public CallHandler getSender() {
            return this._sender;
        }

        public int getCallObjectId() {
            return this._callObjectId;
        }

        public int getTargetCallObjectId() {
            return this._targetCallObjectId;
        }

        public String getTransferorMri() {
            return this._transferorMri;
        }

        public String getTransferTargetMri() {
            return this._transferTargetMri;
        }
    }

    public static class OnDominantSpeakerListChanged {
        private int _callObjectId;
        private String[] _dominantSpeakers;
        private CallHandler _sender;

        public OnDominantSpeakerListChanged(CallHandler sender, int callObjectId, String[] dominantSpeakers) {
            this._sender = sender;
            this._callObjectId = callObjectId;
            this._dominantSpeakers = dominantSpeakers;
        }

        public CallHandler getSender() {
            return this._sender;
        }

        public int getCallObjectId() {
            return this._callObjectId;
        }

        public String[] getDominantSpeakers() {
            return this._dominantSpeakers;
        }
    }

    public static class OnMediaNegotiationStatusChange {
        private int _callObjectId;
        private MEDIA_NEGOTIATION_STATUS_CODE _mediaNegotiationStatusCode;
        private MODALITY_TYPE _modalityType;
        private CallHandler _sender;

        public OnMediaNegotiationStatusChange(CallHandler sender, int callObjectId, MODALITY_TYPE modalityType, MEDIA_NEGOTIATION_STATUS_CODE mediaNegotiationStatusCode) {
            this._sender = sender;
            this._callObjectId = callObjectId;
            this._modalityType = modalityType;
            this._mediaNegotiationStatusCode = mediaNegotiationStatusCode;
        }

        public CallHandler getSender() {
            return this._sender;
        }

        public int getCallObjectId() {
            return this._callObjectId;
        }

        public MODALITY_TYPE getModalityType() {
            return this._modalityType;
        }

        public MEDIA_NEGOTIATION_STATUS_CODE getMediaNegotiationStatusCode() {
            return this._mediaNegotiationStatusCode;
        }
    }

    public static class OnNudgeParticipantsOperationStatusChanged {
        private int _callObjectId;
        private String _context;
        private int _failureReason;
        private CallHandler _sender;

        public OnNudgeParticipantsOperationStatusChanged(CallHandler sender, int callObjectId, String context, int failureReason) {
            this._sender = sender;
            this._callObjectId = callObjectId;
            this._context = context;
            this._failureReason = failureReason;
        }

        public CallHandler getSender() {
            return this._sender;
        }

        public int getCallObjectId() {
            return this._callObjectId;
        }

        public String getContext() {
            return this._context;
        }

        public int getFailureReason() {
            return this._failureReason;
        }
    }

    public static class OnPropertyChange {
        private PROPKEY _propKey;
        private ObjectInterface _sender;

        public OnPropertyChange(ObjectInterface sender, PROPKEY propKey) {
            this._sender = sender;
            this._propKey = propKey;
        }

        public ObjectInterface getSender() {
            return this._sender;
        }

        public PROPKEY getPropKey() {
            return this._propKey;
        }
    }

    public static class OnProxiedPushNotification {
        private int _eventId;
        private String _payload;
        private CallHandler _sender;

        public OnProxiedPushNotification(CallHandler sender, int eventId, String payload) {
            this._sender = sender;
            this._eventId = eventId;
            this._payload = payload;
        }

        public CallHandler getSender() {
            return this._sender;
        }

        public int getEventId() {
            return this._eventId;
        }

        public String getPayload() {
            return this._payload;
        }
    }

    public static class OnRemoteVideosCountChanged {
        private int _participantObjectId;
        private CallHandler _sender;

        public OnRemoteVideosCountChanged(CallHandler sender, int participantObjectId) {
            this._sender = sender;
            this._participantObjectId = participantObjectId;
        }

        public CallHandler getSender() {
            return this._sender;
        }

        public int getParticipantObjectId() {
            return this._participantObjectId;
        }
    }

    public static class OnUnmuteSelfOperationStatusChange {
        private int _callObjectId;
        private int _failureReason;
        private OPERATIONRESULTCODE _operationResult;
        private CallHandler _sender;

        public OnUnmuteSelfOperationStatusChange(CallHandler sender, int callObjectId, OPERATIONRESULTCODE operationResult, int failureReason) {
            this._sender = sender;
            this._callObjectId = callObjectId;
            this._operationResult = operationResult;
            this._failureReason = failureReason;
        }

        public CallHandler getSender() {
            return this._sender;
        }

        public int getCallObjectId() {
            return this._callObjectId;
        }

        public OPERATIONRESULTCODE getOperationResult() {
            return this._operationResult;
        }

        public int getFailureReason() {
            return this._failureReason;
        }
    }

    public void onActiveSpeakerListChanged(CallHandler sender, int callObjectId, String[] activeSpeakers) {
        try {
            this.eventBus.sendEvent(new OnActiveSpeakerListChanged(sender, callObjectId, activeSpeakers));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onAudioStreamStateChanged(CallHandler sender, int callObjectId, MEDIA_DIRECTION direction, MEDIA_STREAM_STATE streamState) {
        try {
            this.eventBus.sendEvent(new OnAudioStreamStateChanged(sender, callObjectId, direction, streamState));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onCallMeBackOperationStatusChange(CallHandler sender, int callObjectId, String participantMri, int failureReason) {
        try {
            this.eventBus.sendEvent(new OnCallMeBackOperationStatusChange(sender, callObjectId, participantMri, failureReason));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onCallTransferCallReceived(CallHandler sender, int callObjectId, int targetCallObjectId, String transferorMri, String transferTargetMri) {
        try {
            this.eventBus.sendEvent(new OnCallTransferCallReceived(sender, callObjectId, targetCallObjectId, transferorMri, transferTargetMri));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onDominantSpeakerListChanged(CallHandler sender, int callObjectId, String[] dominantSpeakers) {
        try {
            this.eventBus.sendEvent(new OnDominantSpeakerListChanged(sender, callObjectId, dominantSpeakers));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onMediaNegotiationStatusChange(CallHandler sender, int callObjectId, MODALITY_TYPE modalityType, MEDIA_NEGOTIATION_STATUS_CODE mediaNegotiationStatusCode) {
        try {
            this.eventBus.sendEvent(new OnMediaNegotiationStatusChange(sender, callObjectId, modalityType, mediaNegotiationStatusCode));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onNudgeParticipantsOperationStatusChanged(CallHandler sender, int callObjectId, String context, int failureReason) {
        try {
            this.eventBus.sendEvent(new OnNudgeParticipantsOperationStatusChanged(sender, callObjectId, context, failureReason));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onProxiedPushNotification(CallHandler sender, int eventId, String payload) {
        try {
            this.eventBus.sendEvent(new OnProxiedPushNotification(sender, eventId, payload));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onRemoteVideosCountChanged(CallHandler sender, int participantObjectId) {
        try {
            this.eventBus.sendEvent(new OnRemoteVideosCountChanged(sender, participantObjectId));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onUnmuteSelfOperationStatusChange(CallHandler sender, int callObjectId, OPERATIONRESULTCODE operationResult, int failureReason) {
        try {
            this.eventBus.sendEvent(new OnUnmuteSelfOperationStatusChange(sender, callObjectId, operationResult, failureReason));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onPropertyChange(ObjectInterface sender, PROPKEY propKey) {
        try {
            this.eventBus.sendEvent(new OnPropertyChange(sender, propKey));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(propKey, t));
        }
    }
}
