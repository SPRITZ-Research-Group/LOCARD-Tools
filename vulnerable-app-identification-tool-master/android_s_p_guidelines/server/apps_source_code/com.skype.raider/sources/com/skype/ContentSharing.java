package com.skype;

import android.support.v4.util.m;

public interface ContentSharing extends ObjectInterface {

    public enum CONTENTROLE {
        NONE(0),
        ATTENDEE(1),
        PRESENTER(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<CONTENTROLE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            CONTENTROLE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                CONTENTROLE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private CONTENTROLE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static CONTENTROLE fromInt(int i) {
            CONTENTROLE tmpVar = (CONTENTROLE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public interface ContentSharingIListener {
        void onJoinContentSharingResult(ContentSharing contentSharing, FAILUREREASON failurereason, int i, int i2);

        void onTakeContentSharingControlResult(ContentSharing contentSharing, FAILUREREASON failurereason, int i, int i2);

        void onUpdateContentSharingParticipantStateResult(ContentSharing contentSharing, FAILUREREASON failurereason, int i, int i2);

        void onUpdateContentSharingSessionStateResult(ContentSharing contentSharing, String str, FAILUREREASON failurereason, int i, int i2);
    }

    public enum FAILUREREASON {
        NOFAILURE(0),
        FAILURE(1),
        SESSION_NOT_FOUND(2),
        SESSION_TIMED_OUT(3),
        NETWORK_ERROR(4),
        NETWORK_CANNOT_CONNECT_ERROR(5),
        AUTH_FAILURE(6),
        SERVICE_FAILURE(7),
        REQUEST_TIMED_OUT(8),
        ACTION_NOT_ALLOWED(9),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<FAILUREREASON> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            FAILUREREASON[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                FAILUREREASON type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private FAILUREREASON(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static FAILUREREASON fromInt(int i) {
            FAILUREREASON tmpVar = (FAILUREREASON) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum STATUS {
        INITIAL(0),
        ROUTING(1),
        RINGING(2),
        PRESENTING(3),
        CONNECTED(4),
        VIEWING(5),
        FINISHING(6),
        DONE(7),
        SHARING_FAILED(8),
        TIMED_OUT(9),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<STATUS> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            STATUS[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                STATUS type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private STATUS(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static STATUS fromInt(int i) {
            STATUS tmpVar = (STATUS) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    void addListener(ContentSharingIListener contentSharingIListener);

    int getCallIdProp();

    int getFailureReasonProp();

    int getFailurecodeProp();

    int getFailuresubcodeProp();

    String getIdentityProp();

    String getSharingIdProp();

    String getStateProp();

    int getStatusProp();

    void removeListener(ContentSharingIListener contentSharingIListener);

    boolean startContentSharing();

    void stopContentSharing();

    boolean takeContentSharingControl();

    boolean updateContentSharingParticipantState();

    boolean updateContentSharingSessionState(String str, String str2);
}
