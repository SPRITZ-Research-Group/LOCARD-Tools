package com.skype;

import android.support.v4.util.m;
import com.skype.Video.MEDIATYPE;
import com.skype.Video.VIDEO_DEVICE_CAPABILITY;
import com.skype.msrtc.QualityEventType;
import com.skype.msrtc.QualityLevel;

public interface SkyLib extends GI {

    public enum COMPOSITE_AUDIO_DEVICE_TYPE {
        ADT_SPEAKERS(0),
        ADT_HEADPHONES(1),
        ADT_HEADSET(2),
        ADT_HANDSET(3),
        ADT_SPEAKERPHONE(4),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<COMPOSITE_AUDIO_DEVICE_TYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            COMPOSITE_AUDIO_DEVICE_TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                COMPOSITE_AUDIO_DEVICE_TYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private COMPOSITE_AUDIO_DEVICE_TYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static COMPOSITE_AUDIO_DEVICE_TYPE fromInt(int i) {
            COMPOSITE_AUDIO_DEVICE_TYPE tmpVar = (COMPOSITE_AUDIO_DEVICE_TYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public static class DecryptPayload_Result {
        public byte[] m_message;
        public boolean m_return;

        public void init(byte[] message, boolean funcRet) {
            this.m_message = message;
            this.m_return = funcRet;
        }
    }

    public enum ECS_CALLBACK_EVENT_TYPE {
        CONFIG_UPDATED(0),
        TOKEN_IS_INVALID(1),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<ECS_CALLBACK_EVENT_TYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            ECS_CALLBACK_EVENT_TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                ECS_CALLBACK_EVENT_TYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private ECS_CALLBACK_EVENT_TYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static ECS_CALLBACK_EVENT_TYPE fromInt(int i) {
            ECS_CALLBACK_EVENT_TYPE tmpVar = (ECS_CALLBACK_EVENT_TYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public static class FindObjects_Result {
        public int[] m_objectIDList;
        public boolean m_return;

        public void init(int[] objectIDList, boolean funcRet) {
            this.m_objectIDList = objectIDList;
            this.m_return = funcRet;
        }
    }

    public static class GetAudioDeviceCapabilities_Result {
        public String m_interfaceString;
        public int m_return;

        public void init(byte[] interfaceString, int funcRet) {
            this.m_interfaceString = NativeStringConvert.ConvertFromNativeBytes(interfaceString);
            this.m_return = funcRet;
        }
    }

    public static class GetAvailableCompositeAudioDevices_Result {
        public COMPOSITE_AUDIO_DEVICE_TYPE[] m_deviceTyeList;
        public boolean[] m_isPcInternalDeviceFlagList;
        public String[] m_micHandleList;
        public String[] m_micProductIdList;
        public String[] m_nameList;
        public boolean m_return;
        public String[] m_spkHandleList;
        public String[] m_spkProductIdList;

        public void init(byte[][] nameList, COMPOSITE_AUDIO_DEVICE_TYPE[] deviceTyeList, byte[][] micHandleList, byte[][] micProductIdList, byte[][] spkHandleList, byte[][] spkProductIdList, boolean[] isPcInternalDeviceFlagList, boolean funcRet) {
            this.m_nameList = NativeStringConvert.ConvertFromNativeStringArray(nameList);
            this.m_deviceTyeList = deviceTyeList;
            this.m_micHandleList = NativeStringConvert.ConvertFromNativeStringArray(micHandleList);
            this.m_micProductIdList = NativeStringConvert.ConvertFromNativeStringArray(micProductIdList);
            this.m_spkHandleList = NativeStringConvert.ConvertFromNativeStringArray(spkHandleList);
            this.m_spkProductIdList = NativeStringConvert.ConvertFromNativeStringArray(spkProductIdList);
            this.m_isPcInternalDeviceFlagList = isPcInternalDeviceFlagList;
            this.m_return = funcRet;
        }
    }

    public static class GetAvailableOutputDevices_Result {
        public String[] m_handleList;
        public boolean[] m_isDefaultDeviceFlagList;
        public String[] m_nameList;
        public String[] m_productIdList;
        public boolean m_return;
        public String[] m_vendorIdList;

        public void init(byte[][] handleList, byte[][] nameList, byte[][] productIdList, byte[][] vendorIdList, boolean[] isDefaultDeviceFlagList, boolean funcRet) {
            this.m_handleList = NativeStringConvert.ConvertFromNativeStringArray(handleList);
            this.m_nameList = NativeStringConvert.ConvertFromNativeStringArray(nameList);
            this.m_productIdList = NativeStringConvert.ConvertFromNativeStringArray(productIdList);
            this.m_vendorIdList = NativeStringConvert.ConvertFromNativeStringArray(vendorIdList);
            this.m_isDefaultDeviceFlagList = isDefaultDeviceFlagList;
            this.m_return = funcRet;
        }
    }

    public static class GetAvailableRecordingDevices_Result {
        public String[] m_handleList;
        public boolean[] m_isDefaultDeviceFlagList;
        public String[] m_nameList;
        public String[] m_productIdList;
        public boolean m_return;
        public String[] m_vendorIdList;

        public void init(byte[][] handleList, byte[][] nameList, byte[][] productIdList, byte[][] vendorIdList, boolean[] isDefaultDeviceFlagList, boolean funcRet) {
            this.m_handleList = NativeStringConvert.ConvertFromNativeStringArray(handleList);
            this.m_nameList = NativeStringConvert.ConvertFromNativeStringArray(nameList);
            this.m_productIdList = NativeStringConvert.ConvertFromNativeStringArray(productIdList);
            this.m_vendorIdList = NativeStringConvert.ConvertFromNativeStringArray(vendorIdList);
            this.m_isDefaultDeviceFlagList = isDefaultDeviceFlagList;
            this.m_return = funcRet;
        }
    }

    public static class GetAvailableVideoDevices_Result {
        public String[] m_deviceNames;
        public String[] m_devicePaths;
        public int m_return;

        public void init(byte[][] deviceNames, byte[][] devicePaths, int funcRet) {
            this.m_deviceNames = NativeStringConvert.ConvertFromNativeStringArray(deviceNames);
            this.m_devicePaths = NativeStringConvert.ConvertFromNativeStringArray(devicePaths);
            this.m_return = funcRet;
        }
    }

    public static class GetCoexistenceCredentials_Result {
        public String m_accountName;
        public long m_partnerId;
        public String m_passwordHash;
        public String m_refreshToken;
        public int m_refreshTokenExpiration;
        public boolean m_return;
        public String m_skypeToken;
        public int m_skypeTokenExpiration;

        public void init(byte[] accountName, byte[] passwordHash, byte[] refreshToken, int refreshTokenExpiration, byte[] skypeToken, int skypeTokenExpiration, long partnerId, boolean funcRet) {
            this.m_accountName = NativeStringConvert.ConvertFromNativeBytes(accountName);
            this.m_passwordHash = NativeStringConvert.ConvertFromNativeBytes(passwordHash);
            this.m_refreshToken = NativeStringConvert.ConvertFromNativeBytes(refreshToken);
            this.m_refreshTokenExpiration = refreshTokenExpiration;
            this.m_skypeToken = NativeStringConvert.ConvertFromNativeBytes(skypeToken);
            this.m_skypeTokenExpiration = skypeTokenExpiration;
            this.m_partnerId = partnerId;
            this.m_return = funcRet;
        }
    }

    public static class GetDefaultAccountOldCredentials_Result {
        public String m_accountName;
        public long m_partnerId;
        public String m_passwordHash;
        public String m_refreshToken;
        public int m_refreshTokenExpiration;
        public boolean m_return;
        public String m_skypeToken;
        public int m_skypeTokenExpiration;

        public void init(byte[] accountName, byte[] passwordHash, byte[] refreshToken, int refreshTokenExpiration, byte[] skypeToken, int skypeTokenExpiration, long partnerId, boolean funcRet) {
            this.m_accountName = NativeStringConvert.ConvertFromNativeBytes(accountName);
            this.m_passwordHash = NativeStringConvert.ConvertFromNativeBytes(passwordHash);
            this.m_refreshToken = NativeStringConvert.ConvertFromNativeBytes(refreshToken);
            this.m_refreshTokenExpiration = refreshTokenExpiration;
            this.m_skypeToken = NativeStringConvert.ConvertFromNativeBytes(skypeToken);
            this.m_skypeTokenExpiration = skypeTokenExpiration;
            this.m_partnerId = partnerId;
            this.m_return = funcRet;
        }
    }

    public static class GetVideoDeviceVendorProductIds_Result {
        public int m_productId;
        public int m_vendorId;

        public void init(int vendorId, int productId) {
            this.m_vendorId = vendorId;
            this.m_productId = productId;
        }
    }

    public enum IDENTITYTYPE {
        UNRECOGNIZED(0),
        SKYPE(1),
        SKYPE_MYSELF(2),
        SKYPE_UNDISCLOSED(3),
        PSTN(4),
        PSTN_EMERGENCY(5),
        PSTN_FREE(6),
        PSTN_UNDISCLOSED(7),
        CONFERENCE(8),
        EXTERNAL(9),
        XMPP(10),
        PASSPORT(11),
        LYNC(12),
        BOT(13),
        LOCAL_SMS(14),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<IDENTITYTYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            IDENTITYTYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                IDENTITYTYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private IDENTITYTYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static IDENTITYTYPE fromInt(int i) {
            IDENTITYTYPE tmpVar = (IDENTITYTYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum INTENT {
        I_REGULAR(0),
        I_CALL_PUSH(2),
        I_CALL_USER(3),
        I_UNKNOWN(4),
        I_CALL_PREHEAT(5),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<INTENT> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            INTENT[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                INTENT type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private INTENT(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static INTENT fromInt(int i) {
            INTENT tmpVar = (INTENT) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum IN_MEMORY_OBJECTTYPE {
        INVALID(0),
        EXAMPLEINMEMORYOBJECT(1),
        SESSIONPARAMETERS(2),
        ADDPARTICIPANTPARAMETERS(3),
        NROF_IN_MEMORY_OBJECTTYPES(4),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<IN_MEMORY_OBJECTTYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            IN_MEMORY_OBJECTTYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                IN_MEMORY_OBJECTTYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private IN_MEMORY_OBJECTTYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static IN_MEMORY_OBJECTTYPE fromInt(int i) {
            IN_MEMORY_OBJECTTYPE tmpVar = (IN_MEMORY_OBJECTTYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public static class IsMicrophoneMuted_Result {
        public boolean m_muted;
        public boolean m_return;

        public void init(boolean muted, boolean funcRet) {
            this.m_muted = muted;
            this.m_return = funcRet;
        }
    }

    public static class IsSpeakerMuted_Result {
        public boolean m_muted;
        public boolean m_return;

        public void init(boolean muted, boolean funcRet) {
            this.m_muted = muted;
            this.m_return = funcRet;
        }
    }

    public enum MEDIASTATUS {
        MEDIA_STATUS_NOT_AVAILABLE(0),
        MEDIA_STATUS_INITIALIZED(1),
        MEDIA_STATUS_FAILED(2),
        MEDIA_STATUS_UNINITIALIZED(3),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<MEDIASTATUS> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            MEDIASTATUS[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                MEDIASTATUS type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private MEDIASTATUS(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static MEDIASTATUS fromInt(int i) {
            MEDIASTATUS tmpVar = (MEDIASTATUS) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum OBJECTTYPE {
        VIDEO(11),
        CONTENTSHARING(29),
        DATACHANNEL(30),
        CALLMEMBER(13),
        CALL(4),
        ACCOUNT(5),
        CALLHANDLER(26),
        RESERVED(31),
        NROF_OBJECTTYPES(32),
        FORMERLY_SEARCH(1),
        FORMERLY_CONTACT(2),
        FORMERLY_LEGACYMESSAGE(3),
        FORMERLY_TRANSFER(6),
        FORMERLY_VOICEMAIL(7),
        FORMERLY_CHAT(8),
        FORMERLY_MESSAGE(9),
        FORMERLY_CONTACTGROUP(10),
        FORMERLY_SMS(12),
        FORMERLY_LIGHTWEIGHTMEETING(14),
        FORMERLY_CHATMEMBER(15),
        FORMERLY_ALERT(16),
        FORMERLY_PRICEQUOTE(17),
        FORMERLY_CONVERSATION(18),
        FORMERLY_PARTICIPANT(19),
        FORMERLY_ACCESS(20),
        FORMERLY_VIDEOMESSAGE(21),
        FORMERLY_MEDIADOCUMENT(22),
        FORMERLY_TRANSLATOR(23),
        FORMERLY_MESSAGEANNOTATION(24),
        FORMERLY_CONTENTSEARCHDOCUMENT(25),
        FORMERLY_CONTENTSEARCH(27),
        FORMERLY_CONVERSATIONVIEW(28),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<OBJECTTYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            OBJECTTYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                OBJECTTYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private OBJECTTYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static OBJECTTYPE fromInt(int i) {
            OBJECTTYPE tmpVar = (OBJECTTYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum PNM_REGISTER_CONTEXTS_RESULT {
        REGISTER_CONTEXTS_SUCCEEDED(0),
        REGISTER_CONTEXTS_FAILED(1),
        REGISTER_CONTEXTS_FAILED_REQUEST(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<PNM_REGISTER_CONTEXTS_RESULT> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            PNM_REGISTER_CONTEXTS_RESULT[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                PNM_REGISTER_CONTEXTS_RESULT type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private PNM_REGISTER_CONTEXTS_RESULT(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static PNM_REGISTER_CONTEXTS_RESULT fromInt(int i) {
            PNM_REGISTER_CONTEXTS_RESULT tmpVar = (PNM_REGISTER_CONTEXTS_RESULT) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum PNM_REGISTER_MODULE {
        PNM_REGISTER_EDF(0),
        PNM_REGISTER_INTERNAL(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<PNM_REGISTER_MODULE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            PNM_REGISTER_MODULE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                PNM_REGISTER_MODULE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private PNM_REGISTER_MODULE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static PNM_REGISTER_MODULE fromInt(int i) {
            PNM_REGISTER_MODULE tmpVar = (PNM_REGISTER_MODULE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum PUSHHANDLINGRESULT {
        BAD_NOTIFICATION_PAYLOAD(0),
        CALL_SETUP_SUCCEEDED(1),
        CALL_SETUP_FAILED(2),
        BAD_NOTIFICATION_EVENT_TYPE(5),
        CALL_SETUP_SUCCEEDED_CALL_ALREADY_EXISTS(6),
        CALL_SETUP_FAILED_CANNOT_CONNECT(7),
        CALL_SETUP_FAILED_NO_SIGNALING(8),
        CALL_SETUP_FAILED_NO_PERMISSION(9),
        CALL_SETUP_FAILED_NO_COMMON_CODEC(10),
        CALL_SETUP_FAILED_CONFLICT(11),
        CALL_SETUP_FAILED_PUSH_IGNORED(12),
        CALL_SETUP_FAILED_ANSWERED_ELSEWHERE(13),
        CALL_SETUP_FAILED_CALL_ALREADY_ENDED(14),
        CALL_SETUP_PROGRESS(17),
        LOG_UPLOAD_EVENT_HANDLED(24),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<PUSHHANDLINGRESULT> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            PUSHHANDLINGRESULT[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                PUSHHANDLINGRESULT type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private PUSHHANDLINGRESULT(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static PUSHHANDLINGRESULT fromInt(int i) {
            PUSHHANDLINGRESULT tmpVar = (PUSHHANDLINGRESULT) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum QUALITY_MEDIATYPE {
        AUDIO(0),
        CAMERA(1),
        PANORAMICCAMERA(2),
        APPSHARING(3),
        DATA(4),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<QUALITY_MEDIATYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            QUALITY_MEDIATYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                QUALITY_MEDIATYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private QUALITY_MEDIATYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static QUALITY_MEDIATYPE fromInt(int i) {
            QUALITY_MEDIATYPE tmpVar = (QUALITY_MEDIATYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum SERVICE_TYPE {
        TESTING(0),
        MICROSOFT_WNS(1),
        MICROSOFT_WP7NS(2),
        APPLE_APN(3),
        GOOGLE_AGCM(4),
        HOTMAIL(5),
        GOOGLE_AC2DM(6),
        TROUTER(7),
        GRIFFIN(8),
        ADM(9),
        NNA(10),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<SERVICE_TYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            SERVICE_TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                SERVICE_TYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private SERVICE_TYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static SERVICE_TYPE fromInt(int i) {
            SERVICE_TYPE tmpVar = (SERVICE_TYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public interface SkyLibIListener {
        void onAvailableDeviceListChange(SkyLib skyLib);

        void onAvailableVideoDeviceListChange(SkyLib skyLib);

        void onE911InfoChanged(SkyLib skyLib, String str);

        void onEcsEvent(SkyLib skyLib, ECS_CALLBACK_EVENT_TYPE ecs_callback_event_type);

        void onLoggingEvent(SkyLib skyLib, String str, String str2);

        void onMediaStatusChanged(SkyLib skyLib, MEDIASTATUS mediastatus);

        void onObjectDelete(SkyLib skyLib, OBJECTTYPE objecttype, int i);

        void onObjectPropertyChangeWithValue(SkyLib skyLib, int i, PROPKEY propkey, Metatag metatag);

        void onOperationModeChanged(SkyLib skyLib, int i);

        void onProxiedPushNotification(SkyLib skyLib, int i, String str);

        void onPushHandlingComplete(SkyLib skyLib, int i, PUSHHANDLINGRESULT pushhandlingresult);

        void onQualityChanged(SkyLib skyLib, int i, QualityEventType qualityEventType, QualityLevel qualityLevel, QUALITY_MEDIATYPE quality_mediatype);

        void onRegisterContextsComplete(SkyLib skyLib, PNM_REGISTER_CONTEXTS_RESULT pnm_register_contexts_result, int i);

        void onSkypeTokenRequired(SkyLib skyLib, String str);

        void onTrouterCheckConnectionComplete(SkyLib skyLib, boolean z);

        void onTrouterConnectionStateChanged(SkyLib skyLib, TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE trouter_connection_state_callback_event_type);

        void onTrouterSuspendComplete(SkyLib skyLib);

        void onTrouterSuspendReady(SkyLib skyLib);
    }

    public enum TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE {
        TROUTER_CONNECTED(0),
        TROUTER_DISCONNECTED(1),
        TROUTER_CONNECT_FAILED(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE fromInt(int i) {
            TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE tmpVar = (TROUTER_CONNECTION_STATE_CALLBACK_EVENT_TYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum VIDEO_DEVICE_FACING {
        VIDEO_DEVICE_FACING_UNKNOWN(0),
        VIDEO_DEVICE_FACING_EXTERNAL(1),
        VIDEO_DEVICE_FACING_FRONT(2),
        VIDEO_DEVICE_FACING_BACK(3),
        VIDEO_DEVICE_FACING_PANORAMIC(4),
        VIDEO_DEVICE_FACING_LEFT_FRONT(5),
        VIDEO_DEVICE_FACING_RIGHT_FRONT(6),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<VIDEO_DEVICE_FACING> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            VIDEO_DEVICE_FACING[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                VIDEO_DEVICE_FACING type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private VIDEO_DEVICE_FACING(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static VIDEO_DEVICE_FACING fromInt(int i) {
            VIDEO_DEVICE_FACING tmpVar = (VIDEO_DEVICE_FACING) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum VIDEO_DEVICE_TYPE {
        VIDEO_DEVICE_USB_CAMERA(0),
        VIDEO_DEVICE_CAPTURE_ADAPTER(1),
        VIDEO_DEVICE_VIRTUAL(2),
        VIDEO_DEVICE_SR_AUGMENTED(5),
        VIDEO_DEVICE_VIRTUAL_REALITY(6),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<VIDEO_DEVICE_TYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            VIDEO_DEVICE_TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                VIDEO_DEVICE_TYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private VIDEO_DEVICE_TYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static VIDEO_DEVICE_TYPE fromInt(int i) {
            VIDEO_DEVICE_TYPE tmpVar = (VIDEO_DEVICE_TYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    void addListener(SkyLibIListener skyLibIListener);

    void changeOperationMode(int i);

    boolean createExampleObject(ExampleInMemoryObject exampleInMemoryObject);

    long createHrtfAudioProcessor(int i);

    int createLocalVideo(MEDIATYPE mediatype);

    int createLocalVideo(MEDIATYPE mediatype, String str);

    int createLocalVideo(MEDIATYPE mediatype, String str, String str2);

    int createPreviewVideo(MEDIATYPE mediatype);

    int createPreviewVideo(MEDIATYPE mediatype, String str);

    int createPreviewVideo(MEDIATYPE mediatype, String str, String str2);

    DecryptPayload_Result decryptPayload(byte[] bArr);

    boolean deleteHrtfAudioProcessor(long j);

    void ecsAddQueryParameter(String str, String str2);

    String ecsGetETag();

    boolean ecsGetSettingsAsBool(String str, String str2, boolean z);

    int ecsGetSettingsAsInt(String str, String str2, int i);

    String ecsGetSettingsAsString(String str, String str2, String str3);

    void ecsRemoveQueryParameter(String str);

    void enableAGC(boolean z);

    boolean enableHrtfAudioProcessor(long j, boolean z);

    void enableMediaQoS(boolean z);

    void enableTtySupport(boolean z);

    FindObjects_Result findObjects(OBJECTTYPE objecttype);

    void fireIntent(INTENT intent);

    void fireIntent(INTENT intent, String str);

    void fireIntent(INTENT intent, String str, int i);

    boolean getAccount(int i, Account account);

    boolean getAccount(String str, Account account);

    long getAriaLogManager();

    GetAudioDeviceCapabilities_Result getAudioDeviceCapabilities(String str);

    GetAvailableCompositeAudioDevices_Result getAvailableCompositeAudioDevices();

    GetAvailableOutputDevices_Result getAvailableOutputDevices();

    GetAvailableRecordingDevices_Result getAvailableRecordingDevices();

    GetAvailableVideoDevices_Result getAvailableVideoDevices();

    String getBuildName();

    String getBuildVersion();

    boolean getCall(int i, Call call);

    boolean getCallHandler(int i, int i2, CallHandler callHandler);

    boolean getCallHandler(int i, CallHandler callHandler);

    GetCoexistenceCredentials_Result getCoexistenceCredentials();

    boolean getContentSharing(int i, ContentSharing contentSharing);

    boolean getDataChannel(int i, DataChannel dataChannel);

    GetDefaultAccountOldCredentials_Result getDefaultAccountOldCredentials();

    String getDefaultDBPath();

    String getDefaultDBPath(boolean z);

    String getEcsQueryParameters();

    boolean getExampleObject(int i, ExampleInMemoryObject exampleInMemoryObject);

    long getMediaExtension(int i);

    MEDIASTATUS getMediaStatus();

    int getMicVolume();

    int getNrgLevelsForDeviceTuner(String str);

    boolean getObjectInterface(int i, ObjectInterface objectInterface);

    OBJECTTYPE getObjectType(int i);

    String getRegistrationId();

    String getRegistrationId(String str);

    int getSpeakerSystemVolume();

    int getSpeakerVolume();

    Object getTrouterInstance();

    boolean getVideo(int i, Video video);

    VIDEO_DEVICE_FACING getVideoDeviceFacing(String str, String str2);

    VIDEO_DEVICE_TYPE getVideoDeviceType(String str, String str2);

    GetVideoDeviceVendorProductIds_Result getVideoDeviceVendorProductIds(String str, String str2);

    void handleLoggingEvent(String str, String str2);

    int handlePushNotification(int i, byte[] bArr, byte[] bArr2);

    int handlePushNotification(int i, byte[] bArr, byte[] bArr2, String str);

    int handlePushNotification(int i, byte[] bArr, byte[] bArr2, String str, String str2);

    int handlePushNotification(int i, byte[] bArr, byte[] bArr2, String str, String str2, String str3);

    int handlePushNotification(int i, byte[] bArr, byte[] bArr2, String str, String str2, String str3, int i2);

    int handlePushNotification(int i, byte[] bArr, byte[] bArr2, String str, String str2, String str3, int i2, String str4);

    boolean hasVideoDeviceCapability(String str, String str2, VIDEO_DEVICE_CAPABILITY video_device_capability);

    int insertRegistrationTransports(SERVICE_TYPE[] service_typeArr, String[] strArr, String[] strArr2, int[] iArr, String str, String str2);

    IsMicrophoneMuted_Result isMicrophoneMuted();

    IsSpeakerMuted_Result isSpeakerMuted();

    boolean muteMicrophone(boolean z);

    boolean muteSpeakers(boolean z);

    int registerContexts(SERVICE_TYPE service_type, String str, String str2, String[] strArr, String[] strArr2, int[] iArr);

    int registerContextsEx(SERVICE_TYPE[] service_typeArr, String str, String str2, String[] strArr, String[] strArr2, int[] iArr);

    int registerContextsEx(SERVICE_TYPE[] service_typeArr, String str, String str2, String[] strArr, String[] strArr2, int[] iArr, String str3);

    int registerContextsEx(SERVICE_TYPE[] service_typeArr, String str, String str2, String[] strArr, String[] strArr2, int[] iArr, String str3, String str4);

    int registerContextsEx2(SERVICE_TYPE[] service_typeArr, String str, String str2, String[] strArr, String[] strArr2, int[] iArr, String str3, String str4, String str5);

    int registerContextsEx2(SERVICE_TYPE[] service_typeArr, String str, String str2, String[] strArr, String[] strArr2, int[] iArr, String str3, String str4, String str5, String str6);

    void removeListener(SkyLibIListener skyLibIListener);

    int reregisterContexts();

    int reregisterContextsEx2(String str, String str2);

    void selectSoundDevices(String str, String str2, String str3);

    void setAndroidId(String str);

    int setClientDescription(String str, String str2, String str3, String str4, String str5);

    int setClientDescription(String str, String str2, String str3, String str4, String str5, String str6);

    void setDeviceOrientation(int i);

    void setEcsConfig(String str, String str2, String str3);

    void setEcsConfig(String str, String str2, String str3, boolean z);

    void setIMEI(String str);

    void setMediaConfig(String str);

    void setMediaPortRanges(int i, int i2, int i3, int i4, int i5, int i6);

    void setMicVolume(int i);

    void setOpenCameraInMaxResolution(boolean z);

    boolean setRegistrationId(String str);

    void setSpeakerSystemVolume(int i);

    void setSpeakerVolume(int i);

    boolean start();

    boolean start(boolean z);

    void stop();

    void stop(boolean z);

    boolean trouterCheckConnection();

    boolean trouterCheckConnection(String str);

    boolean trouterConnect();

    boolean trouterReceiveData();

    boolean trouterReceiveData(String str);

    boolean trouterSuspend();

    void unmuteMicrophone();

    void unmuteSpeaker();

    int unregisterAllContexts(String str, String str2);

    int unregisterContexts(String[] strArr);

    int unregisterContextsEx2(String[] strArr, String str, String str2);

    boolean updateHrtf3DSourcePosition(long j, int i, int i2, int i3, int i4);
}
