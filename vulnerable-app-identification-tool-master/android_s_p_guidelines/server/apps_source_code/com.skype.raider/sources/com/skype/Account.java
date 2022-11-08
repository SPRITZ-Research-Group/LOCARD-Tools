package com.skype;

import android.support.v4.util.m;

public interface Account extends ObjectInterface {

    public interface AccountIListener {
        void onSkypeTokenRequired(Account account, String str);
    }

    public static class GetStatusWithProgress_Result {
        public int m_progress;
        public STATUS m_return;

        public void init(int progress, STATUS funcRet) {
            this.m_progress = progress;
            this.m_return = funcRet;
        }
    }

    public enum LOGINREQUESTRESULT {
        LOGIN_REQUEST_REJECTED(0),
        LOGIN_REQUEST_ACCEPTED(1),
        LOGIN_REQUEST_IGNORED(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<LOGINREQUESTRESULT> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            LOGINREQUESTRESULT[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                LOGINREQUESTRESULT type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private LOGINREQUESTRESULT(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static LOGINREQUESTRESULT fromInt(int i) {
            LOGINREQUESTRESULT tmpVar = (LOGINREQUESTRESULT) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum LOGOUTREASON {
        LOGOUT_CALLED(1),
        HTTPS_PROXY_AUTH_FAILED(2),
        SOCKS_PROXY_AUTH_FAILED(3),
        P2P_CONNECT_FAILED(4),
        SERVER_CONNECT_FAILED(5),
        SERVER_OVERLOADED(6),
        DB_IN_USE(7),
        INVALID_SKYPENAME(8),
        INVALID_EMAIL(9),
        UNACCEPTABLE_PASSWORD(10),
        SKYPENAME_TAKEN(11),
        REJECTED_AS_UNDERAGE(12),
        NO_SUCH_IDENTITY(13),
        INCORRECT_PASSWORD(14),
        TOO_MANY_LOGIN_ATTEMPTS(15),
        PASSWORD_HAS_CHANGED(16),
        PERIODIC_UIC_UPDATE_FAILED(17),
        DB_DISK_FULL(18),
        DB_IO_ERROR(19),
        DB_CORRUPT(20),
        DB_FAILURE(21),
        INVALID_APP_ID(22),
        APP_ID_FAILURE(23),
        UNSUPPORTED_VERSION(24),
        ATO_BLOCKED(25),
        REMOTE_LOGOUT(26),
        ACCESS_TOKEN_RENEWAL_FAILED(27),
        DB_CANTOPEN(28),
        DB_ACCESS_DENIED(29),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<LOGOUTREASON> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            LOGOUTREASON[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                LOGOUTREASON type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private LOGOUTREASON(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static LOGOUTREASON fromInt(int i) {
            LOGOUTREASON tmpVar = (LOGOUTREASON) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum STATUS {
        LOGGED_OUT(1),
        LOGGING_IN(5),
        LOGGED_IN(7),
        LOGGING_OUT(8),
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

    void addListener(AccountIListener accountIListener);

    int getCobrandIdProp();

    int getFederatedPresencePolicyProp();

    int getFlamingoXmppStatusProp();

    int getForwardStarttimeProp();

    String getHiddenExpressionTabsProp();

    String getLiveidMembernameProp();

    LOGOUTREASON getLogoutReasonProp();

    int getNrOfOtherInstancesProp();

    String getOfflineCallforwardProp();

    String getOptionUiColorProp();

    String getPartnerChannelStatusProp();

    String getPartnerOptedoutProp();

    int getRegistrationTimestampProp();

    boolean getRoamingHistoryEnabledProp();

    String getServiceProviderInfoProp();

    String getSigninNameProp();

    String getSkypeinNumbersProp();

    String getSkypenameProp();

    String getSkypeoutBalanceCurrencyProp();

    int getSkypeoutBalanceProp();

    int getSkypeoutPrecisionProp();

    STATUS getStatusProp();

    GetStatusWithProgress_Result getStatusWithProgress();

    String getSubscriptionsProp();

    String getSuggestedSkypenameProp();

    int getUserSetAvailabilityProp();

    LOGINREQUESTRESULT loginWithSkypeToken(String str);

    LOGINREQUESTRESULT loginWithSkypeToken(String str, int i);

    LOGINREQUESTRESULT loginWithSkypeToken(String str, int i, String str2);

    void logout();

    void logout(boolean z);

    void removeListener(AccountIListener accountIListener);

    boolean setAdditionalIdentities(String str);

    boolean setRing(String str);

    boolean setTenantId(String str);

    void setUIVersion(String str);

    void updateSkypeToken(String str);

    void updateSkypeToken(String str, int i);
}
