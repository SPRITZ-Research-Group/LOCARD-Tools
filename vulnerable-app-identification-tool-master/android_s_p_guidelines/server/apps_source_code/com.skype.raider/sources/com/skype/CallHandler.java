package com.skype;

import android.support.v4.util.m;

public interface CallHandler extends ObjectInterface {

    public enum ANSWER_MEDIA_TYPE {
        ANSWER_WITH_AUDIO_ONLY(0),
        ANSWER_WITH_AUDIO_VIDEO(1),
        ANSWER_WITH_SCREENSHARE_ONLY(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<ANSWER_MEDIA_TYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            ANSWER_MEDIA_TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                ANSWER_MEDIA_TYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private ANSWER_MEDIA_TYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static ANSWER_MEDIA_TYPE fromInt(int i) {
            ANSWER_MEDIA_TYPE tmpVar = (ANSWER_MEDIA_TYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum AUDIO_USAGE_MODE {
        AUM_DEFAULT(0),
        AUM_LONGRANGE_SPEAKER(1),
        AUM_AUDITORIUM(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<AUDIO_USAGE_MODE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            AUDIO_USAGE_MODE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                AUDIO_USAGE_MODE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private AUDIO_USAGE_MODE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static AUDIO_USAGE_MODE fromInt(int i) {
            AUDIO_USAGE_MODE tmpVar = (AUDIO_USAGE_MODE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum CAPABILITY {
        CAPABILITY_NONE(0),
        CAN_CONFERENCE(1),
        CAN_SHARE_SCREEN(2),
        CAN_MERGE(4),
        CAPABILITY_ALL(-1),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<CAPABILITY> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            CAPABILITY[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                CAPABILITY type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private CAPABILITY(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static CAPABILITY fromInt(int i) {
            CAPABILITY tmpVar = (CAPABILITY) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum CONNECTION_TYPE {
        ALL_SUPPORTED(0),
        NO_DIRECT_CONNECTION(1),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<CONNECTION_TYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            CONNECTION_TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                CONNECTION_TYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private CONNECTION_TYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static CONNECTION_TYPE fromInt(int i) {
            CONNECTION_TYPE tmpVar = (CONNECTION_TYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public static class CallGetParticipantVideos_Result {
        public int[] m_participantVideosObjectId;

        public void init(int[] participantVideosObjectId) {
            this.m_participantVideosObjectId = participantVideosObjectId;
        }
    }

    public static class CallGetParticipants_Result {
        public int[] m_callParticipantObjectIds;

        public void init(int[] callParticipantObjectIds) {
            this.m_callParticipantObjectIds = callParticipantObjectIds;
        }
    }

    public static class CallGetSendVideos_Result {
        public int[] m_sendVideoObjectIds;

        public void init(int[] sendVideoObjectIds) {
            this.m_sendVideoObjectIds = sendVideoObjectIds;
        }
    }

    public interface CallHandlerIListener {
        void onActiveSpeakerListChanged(CallHandler callHandler, int i, String[] strArr);

        void onAudioStreamStateChanged(CallHandler callHandler, int i, MEDIA_DIRECTION media_direction, MEDIA_STREAM_STATE media_stream_state);

        void onCallMeBackOperationStatusChange(CallHandler callHandler, int i, String str, int i2);

        void onCallTransferCallReceived(CallHandler callHandler, int i, int i2, String str, String str2);

        void onDominantSpeakerListChanged(CallHandler callHandler, int i, String[] strArr);

        void onMediaNegotiationStatusChange(CallHandler callHandler, int i, MODALITY_TYPE modality_type, MEDIA_NEGOTIATION_STATUS_CODE media_negotiation_status_code);

        void onNudgeParticipantsOperationStatusChanged(CallHandler callHandler, int i, String str, int i2);

        void onProxiedPushNotification(CallHandler callHandler, int i, String str);

        void onRemoteVideosCountChanged(CallHandler callHandler, int i);

        void onUnmuteSelfOperationStatusChange(CallHandler callHandler, int i, OPERATIONRESULTCODE operationresultcode, int i2);
    }

    public enum DTMF {
        DTMF_0(0),
        DTMF_1(1),
        DTMF_2(2),
        DTMF_3(3),
        DTMF_4(4),
        DTMF_5(5),
        DTMF_6(6),
        DTMF_7(7),
        DTMF_8(8),
        DTMF_9(9),
        DTMF_STAR(10),
        DTMF_POUND(11),
        DTMF_A(12),
        DTMF_B(13),
        DTMF_C(14),
        DTMF_D(15),
        DTMF_FLASH(16),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<DTMF> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            DTMF[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                DTMF type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private DTMF(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static DTMF fromInt(int i) {
            DTMF tmpVar = (DTMF) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum ENDPOINT_TYPE {
        DEFAULT(0),
        LYNC(1),
        VOICEMAIL(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<ENDPOINT_TYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            ENDPOINT_TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                ENDPOINT_TYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private ENDPOINT_TYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static ENDPOINT_TYPE fromInt(int i) {
            ENDPOINT_TYPE tmpVar = (ENDPOINT_TYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public static class GetActiveCalls_Result {
        public int[] m_callObjectIds;

        public void init(int[] callObjectIds) {
            this.m_callObjectIds = callObjectIds;
        }
    }

    public static class GetContentSharingSessions_Result {
        public int[] m_contentSharingObjectIds;

        public void init(int[] contentSharingObjectIds) {
            this.m_contentSharingObjectIds = contentSharingObjectIds;
        }
    }

    public enum INVITATION_TYPE {
        NONE(0),
        NUDGE(1),
        DIALOUT(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<INVITATION_TYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            INVITATION_TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                INVITATION_TYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private INVITATION_TYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static INVITATION_TYPE fromInt(int i) {
            INVITATION_TYPE tmpVar = (INVITATION_TYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum MEDIA_DIRECTION {
        DISABLED(0),
        INACTIVE(1),
        SENDTOPEER(2),
        RECEIVEFROMPEER(3),
        BIDIRECTIONAL(4),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<MEDIA_DIRECTION> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            MEDIA_DIRECTION[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                MEDIA_DIRECTION type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private MEDIA_DIRECTION(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static MEDIA_DIRECTION fromInt(int i) {
            MEDIA_DIRECTION tmpVar = (MEDIA_DIRECTION) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum MEDIA_NEGOTIATION_STATUS_CODE {
        SUCCEEDED(0),
        ERRORLOCALINTERNAL(1),
        ERRORREMOTEINTERNAL(2),
        OFFERNOTACCEPTABLE(3),
        OFFERDECLINED(4),
        LOCALCANCEL(5),
        REMOTECANCEL(6),
        FAILEDNORETRY(7),
        TERMINATED(8),
        GENERALFAILURE(9),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<MEDIA_NEGOTIATION_STATUS_CODE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            MEDIA_NEGOTIATION_STATUS_CODE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                MEDIA_NEGOTIATION_STATUS_CODE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private MEDIA_NEGOTIATION_STATUS_CODE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static MEDIA_NEGOTIATION_STATUS_CODE fromInt(int i) {
            MEDIA_NEGOTIATION_STATUS_CODE tmpVar = (MEDIA_NEGOTIATION_STATUS_CODE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum MEDIA_PEER_TYPE {
        CONSUMER_TWOPARTY(1),
        CONSUMER_PSTN(2),
        CONSUMER_MULTIPARTY(3),
        ENTERPRISE_TWOPARTY_ONLINE_ONLY(4),
        ENTERPRISE_TWOPARTY_HYBRID(5),
        ENTERPRISE_MULTIPARTY(6),
        ENTERPRISE_PSTN_ONLINE_ONLY(7),
        ENTERPRISE_PSTN_HYBRID(8),
        ENTERPRISE_TWOPARTY_FEDERATED(9),
        ENTERPRISE_UNKNOWN(10),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<MEDIA_PEER_TYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            MEDIA_PEER_TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                MEDIA_PEER_TYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private MEDIA_PEER_TYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static MEDIA_PEER_TYPE fromInt(int i) {
            MEDIA_PEER_TYPE tmpVar = (MEDIA_PEER_TYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum MEDIA_STREAM_STATE {
        STREAM_CREATED(0),
        STREAM_REMOVED(1),
        STREAM_STARTED(2),
        STREAM_INACTIVE(3),
        STREAM_ACTIVE(4),
        STREAM_STOPPED(5),
        STREAM_FAIL(6),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<MEDIA_STREAM_STATE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            MEDIA_STREAM_STATE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                MEDIA_STREAM_STATE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private MEDIA_STREAM_STATE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static MEDIA_STREAM_STATE fromInt(int i) {
            MEDIA_STREAM_STATE tmpVar = (MEDIA_STREAM_STATE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum MODALITY_TYPE {
        AUDIO(0),
        VIDEO(1),
        SCREENSHARE(2),
        DATACHANNEL(3),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<MODALITY_TYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            MODALITY_TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                MODALITY_TYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private MODALITY_TYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static MODALITY_TYPE fromInt(int i) {
            MODALITY_TYPE tmpVar = (MODALITY_TYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum MUTE_FLAGS {
        MUTE_MICROPHONE(1),
        MUTE_SPEAKER(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<MUTE_FLAGS> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            MUTE_FLAGS[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                MUTE_FLAGS type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private MUTE_FLAGS(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static MUTE_FLAGS fromInt(int i) {
            MUTE_FLAGS tmpVar = (MUTE_FLAGS) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum MUTE_SCOPE {
        ALL(1),
        SPECIFIED(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<MUTE_SCOPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            MUTE_SCOPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                MUTE_SCOPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private MUTE_SCOPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static MUTE_SCOPE fromInt(int i) {
            MUTE_SCOPE tmpVar = (MUTE_SCOPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum OPERATIONRESULTCODE {
        OPERATIONRESULTNONE(0),
        OPERATIONRESULTFAILURE(1),
        OPERATIONRESULTSUCCESS(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<OPERATIONRESULTCODE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            OPERATIONRESULTCODE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                OPERATIONRESULTCODE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private OPERATIONRESULTCODE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static OPERATIONRESULTCODE fromInt(int i) {
            OPERATIONRESULTCODE tmpVar = (OPERATIONRESULTCODE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum PARK_CONTEXT {
        INVALID(0),
        TEAM(1),
        SHAREDLINE(2),
        SERVERHOLD(3),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<PARK_CONTEXT> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            PARK_CONTEXT[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                PARK_CONTEXT type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private PARK_CONTEXT(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static PARK_CONTEXT fromInt(int i) {
            PARK_CONTEXT tmpVar = (PARK_CONTEXT) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum QUALITYRATING {
        VERY_BAD(0),
        BAD(1),
        AVERAGE(2),
        GOOD(3),
        VERY_GOOD(4),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<QUALITYRATING> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            QUALITYRATING[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                QUALITYRATING type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private QUALITYRATING(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static QUALITYRATING fromInt(int i) {
            QUALITYRATING tmpVar = (QUALITYRATING) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum REMOVE_ENDPOINT_SCOPE {
        REMOVE_ENDPOINT_SCOPE_NONE(0),
        REMOVE_ENDPOINT_SCOPE_OTHER(1),
        REMOVE_ENDPOINT_SCOPE_SPECIFIED(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<REMOVE_ENDPOINT_SCOPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            REMOVE_ENDPOINT_SCOPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                REMOVE_ENDPOINT_SCOPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private REMOVE_ENDPOINT_SCOPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static REMOVE_ENDPOINT_SCOPE fromInt(int i) {
            REMOVE_ENDPOINT_SCOPE tmpVar = (REMOVE_ENDPOINT_SCOPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    void addListener(CallHandlerIListener callHandlerIListener);

    int addParticipant(int i, String str);

    int addParticipant(int i, String str, String str2);

    int addParticipant(int i, String str, String str2, String str3);

    int addParticipant(int i, String str, String str2, String str3, String str4);

    int addParticipantToCall(int i, String str, int i2);

    void admitParticipants(int i, String[] strArr);

    boolean answerCall(int i);

    boolean answerCall(int i, boolean z);

    boolean callAnswer(int i, ANSWER_MEDIA_TYPE answer_media_type);

    boolean callAssimilate(int i, int i2);

    boolean callAssimilate(int i, int i2, String str);

    boolean callAssimilate(int i, int i2, String str, String str2);

    boolean callAttachSendVideo(int i, int i2);

    CallGetParticipantVideos_Result callGetParticipantVideos(int i);

    CallGetParticipants_Result callGetParticipants(int i);

    CallGetSendVideos_Result callGetSendVideos(int i);

    String callGetTechnicalInformationJson(int i);

    boolean callHold(int i, boolean z);

    boolean callHold(int i, boolean z, String str);

    boolean callMeBack(int i, String str, String str2);

    boolean callMute(int i, boolean z);

    void callMuteParticipants(int i, MUTE_SCOPE mute_scope, String[] strArr);

    boolean callMuteSpeaker(int i, boolean z);

    int callParticipantGetCallObject(int i);

    boolean callSendDtmf(int i, DTMF dtmf);

    boolean callSetAudioUsageMode(int i, AUDIO_USAGE_MODE audio_usage_mode);

    boolean callSetMaxVideoChannels(int i, int i2);

    boolean callShareSystemSound(int i, boolean z);

    boolean callStartAudio(int i);

    boolean callStartAudio(int i, String str);

    boolean callStopAudio(int i);

    boolean callStopAudio(int i, String str);

    boolean callUpdateEndpointMetaData(int i);

    boolean callUpdateEndpointMetaData(int i, String str);

    boolean createAddParticipantParameters(AddParticipantParameters addParticipantParameters);

    int createContentSharing(int i, String str, String str2);

    int createContentSharing(int i, String str, String str2, String str3);

    int createContentSharing(int i, String str, String str2, String str3, String str4);

    boolean createSessionParameters(SessionParameters sessionParameters);

    boolean endCallForAll(int i);

    GetActiveCalls_Result getActiveCalls();

    boolean getAddParticipantParameters(int i, AddParticipantParameters addParticipantParameters);

    String getCallEndDiagnosticCode(int i);

    GetContentSharingSessions_Result getContentSharingSessions(int i);

    String getDebugInformation(String str);

    int getIntegerProperty(int i, PROPKEY propkey);

    boolean getSessionParameters(int i, SessionParameters sessionParameters);

    String getStringProperty(int i, PROPKEY propkey);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type, boolean z);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type, boolean z, boolean z2);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type, boolean z, boolean z2, boolean z3);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type, boolean z, boolean z2, boolean z3, boolean z4);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type, boolean z, boolean z2, boolean z3, boolean z4, String str2);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6, String str7);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6, String str7, String str8);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z5);

    int joinCall(String str, MEDIA_PEER_TYPE media_peer_type, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z5, String str9);

    int joinSignalingSession(String str, MEDIA_PEER_TYPE media_peer_type, int i);

    void leaveCall(int i);

    boolean nudgeParticipants(int i, int i2);

    boolean nudgeParticipants(int i, int i2, String[] strArr);

    boolean nudgeParticipants(int i, int i2, String[] strArr, String str);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z, boolean z2);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z, boolean z2, boolean z3);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z, boolean z2, boolean z3, boolean z4);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z, boolean z2, boolean z3, boolean z4, String str2);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6, String str7);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6, String str7, String str8);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z5);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z5, String str9);

    int placeCall(String str, MEDIA_PEER_TYPE media_peer_type, String[] strArr, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z5, String str9, String str10);

    int placeCallToVoicemail(String str, MEDIA_PEER_TYPE media_peer_type);

    int placeCallToVoicemail(String str, MEDIA_PEER_TYPE media_peer_type, String str2);

    int placeCallToVoicemail(String str, MEDIA_PEER_TYPE media_peer_type, String str2, String str3);

    int placeCallToVoicemail(String str, MEDIA_PEER_TYPE media_peer_type, String str2, String str3, String str4);

    int placeCallToVoicemail(String str, MEDIA_PEER_TYPE media_peer_type, String str2, String str3, String str4, String str5);

    void provideCallQualityFeedback(String str, String str2, String str3, String str4, QUALITYRATING qualityrating, String str5);

    void rejectLocally(int i);

    void removeListener(CallHandlerIListener callHandlerIListener);

    void removeParticipant(int i);

    void removeParticipant(int i, REMOVE_ENDPOINT_SCOPE remove_endpoint_scope);

    void removeParticipantByMri(int i, String str);

    void removeParticipantByMri(int i, String str, String str2);

    void removeParticipantByMri(int i, String str, String str2, REMOVE_ENDPOINT_SCOPE remove_endpoint_scope);

    boolean startCallPark(int i, PARK_CONTEXT park_context);

    boolean startCallTransfer(int i, String str);

    int startCallUnpark(String str, int i, PARK_CONTEXT park_context, String str2);

    boolean startConsultativeCallTransfer(int i, int i2);

    int startSignalingSession(String str, MEDIA_PEER_TYPE media_peer_type, int i);

    int startSignalingSession(String str, MEDIA_PEER_TYPE media_peer_type, int i, String[] strArr);

    boolean startTransferTargetCall(int i);

    boolean startTransferTargetCall(int i, boolean z);

    boolean startTransferTargetCall(int i, boolean z, String str);

    boolean startTransferTargetCall(int i, boolean z, String str, String str2);

    int subscribe(String str);

    int subscribe(String str, boolean z);

    int subscribe(String str, boolean z, boolean z2);

    int subscribe(String str, boolean z, boolean z2, boolean z3);

    int subscribe(String str, boolean z, boolean z2, boolean z3, boolean z4);

    int subscribe(String str, boolean z, boolean z2, boolean z3, boolean z4, String str2);

    int subscribe(String str, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3);

    int subscribe(String str, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4);

    int subscribe(String str, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5);

    int subscribe(String str, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6);

    int subscribe(String str, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6, String str7);

    int subscribe(String str, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6, String str7, String str8);

    int subscribe(String str, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z5);

    int subscribeToSignalingSession(String str, int i);

    void unsubscribe(int i);
}
