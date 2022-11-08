package com.skype;

import com.skype.Account.AccountIListener;
import com.skype.Account.GetStatusWithProgress_Result;
import com.skype.Account.LOGINREQUESTRESULT;
import com.skype.Account.LOGOUTREASON;
import com.skype.Account.STATUS;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

public class AccountImpl extends ObjectInterfaceImpl implements Account, NativeListenable, ObjectInterface {
    private final Set<AccountIListener> m_listeners;
    private String skypeName;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroyAccount(this.nativeObject);
        }
    }

    private native LOGINREQUESTRESULT loginWithSkypeToken(byte[] bArr, int i, byte[] bArr2);

    private native boolean setAdditionalIdentities(byte[] bArr);

    private native boolean setRing(byte[] bArr);

    private native boolean setTenantId(byte[] bArr);

    private native void setUIVersion(byte[] bArr);

    private native void updateSkypeToken(byte[] bArr, int i);

    public native GetStatusWithProgress_Result getStatusWithProgress();

    public native void initializeListener();

    public native void logout(boolean z);

    public AccountImpl() {
        this(SkypeFactory.getInstance());
    }

    public AccountImpl(ObjectInterfaceFactory factory) {
        super(factory, factory.createAccount());
        this.skypeName = "";
        this.m_listeners = new HashSet();
        factory.initializeListener(this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public STATUS getStatusProp() {
        return STATUS.fromInt(getIntProperty(PROPKEY.ACCOUNT_STATUS));
    }

    public LOGOUTREASON getLogoutReasonProp() {
        return LOGOUTREASON.fromInt(getIntProperty(PROPKEY.ACCOUNT_LOGOUTREASON));
    }

    public String getSuggestedSkypenameProp() {
        return getStrProperty(PROPKEY.ACCOUNT_SUGGESTED_SKYPENAME);
    }

    public String getSkypeoutBalanceCurrencyProp() {
        return getStrProperty(PROPKEY.ACCOUNT_SKYPEOUT_BALANCE_CURRENCY);
    }

    public int getSkypeoutBalanceProp() {
        return getIntProperty(PROPKEY.ACCOUNT_SKYPEOUT_BALANCE);
    }

    public int getSkypeoutPrecisionProp() {
        return getIntProperty(PROPKEY.ACCOUNT_SKYPEOUT_PRECISION);
    }

    public String getSkypeinNumbersProp() {
        return getStrProperty(PROPKEY.ACCOUNT_SKYPEIN_NUMBERS);
    }

    public String getSubscriptionsProp() {
        return getStrProperty(PROPKEY.ACCOUNT_SUBSCRIPTIONS);
    }

    public String getOfflineCallforwardProp() {
        return getStrProperty(PROPKEY.ACCOUNT_OFFLINE_CALLFORWARD);
    }

    public String getPartnerOptedoutProp() {
        return getStrProperty(PROPKEY.ACCOUNT_PARTNER_OPTEDOUT);
    }

    public String getServiceProviderInfoProp() {
        return getStrProperty(PROPKEY.ACCOUNT_SERVICE_PROVIDER_INFO);
    }

    public int getRegistrationTimestampProp() {
        return getIntProperty(PROPKEY.ACCOUNT_REGISTRATION_TIMESTAMP);
    }

    public int getNrOfOtherInstancesProp() {
        return getIntProperty(PROPKEY.ACCOUNT_NR_OF_OTHER_INSTANCES);
    }

    public String getPartnerChannelStatusProp() {
        return getStrProperty(PROPKEY.ACCOUNT_PARTNER_CHANNEL_STATUS);
    }

    public int getFlamingoXmppStatusProp() {
        return getIntProperty(PROPKEY.ACCOUNT_FLAMINGO_XMPP_STATUS);
    }

    public int getFederatedPresencePolicyProp() {
        return getIntProperty(PROPKEY.ACCOUNT_FEDERATED_PRESENCE_POLICY);
    }

    public String getLiveidMembernameProp() {
        return getStrProperty(PROPKEY.ACCOUNT_LIVEID_MEMBERNAME);
    }

    public boolean getRoamingHistoryEnabledProp() {
        return getIntProperty(PROPKEY.ACCOUNT_ROAMING_HISTORY_ENABLED) != 0;
    }

    public int getCobrandIdProp() {
        return getIntProperty(PROPKEY.ACCOUNT_COBRAND_ID);
    }

    public String getSigninNameProp() {
        return getStrProperty(PROPKEY.ACCOUNT_SIGNIN_NAME);
    }

    public String getHiddenExpressionTabsProp() {
        return getStrProperty(PROPKEY.ACCOUNT_HIDDEN_EXPRESSION_TABS);
    }

    public int getForwardStarttimeProp() {
        return getIntProperty(PROPKEY.ACCOUNT_FORWARD_STARTTIME);
    }

    public String getSkypenameProp() {
        if (this.skypeName == null || this.skypeName.equals("")) {
            this.skypeName = getStrProperty(PROPKEY.ACCOUNT_SKYPENAME);
        }
        return this.skypeName;
    }

    public int getUserSetAvailabilityProp() {
        return getIntProperty(PROPKEY.ACCOUNT_USER_SET_AVAILABILITY);
    }

    public String getOptionUiColorProp() {
        return getStrProperty(PROPKEY.ACCOUNT_OPTION_UI_COLOR);
    }

    public void addListener(AccountIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(AccountIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }

    public void onSkypeTokenRequired(byte[] invalidToken) {
        synchronized (this.m_listeners) {
            for (AccountIListener onSkypeTokenRequired : this.m_listeners) {
                onSkypeTokenRequired.onSkypeTokenRequired(this, NativeStringConvert.ConvertFromNativeBytes(invalidToken));
            }
        }
    }

    public LOGINREQUESTRESULT loginWithSkypeToken(String skypeToken, int expirationTime, String displayName) {
        return loginWithSkypeToken(NativeStringConvert.ConvertToNativeBytes(skypeToken), expirationTime, NativeStringConvert.ConvertToNativeBytes(displayName));
    }

    public LOGINREQUESTRESULT loginWithSkypeToken(String skypeToken) {
        return loginWithSkypeToken(skypeToken, 0, "");
    }

    public LOGINREQUESTRESULT loginWithSkypeToken(String skypeToken, int expirationTime) {
        return loginWithSkypeToken(skypeToken, expirationTime, "");
    }

    public void logout() {
        logout(false);
    }

    public boolean setAdditionalIdentities(String identities) {
        return setAdditionalIdentities(NativeStringConvert.ConvertToNativeBytes(identities));
    }

    public boolean setRing(String ring) {
        return setRing(NativeStringConvert.ConvertToNativeBytes(ring));
    }

    public boolean setTenantId(String tenantId) {
        return setTenantId(NativeStringConvert.ConvertToNativeBytes(tenantId));
    }

    public void setUIVersion(String uiVersionString) {
        setUIVersion(NativeStringConvert.ConvertToNativeBytes(uiVersionString));
    }

    public void updateSkypeToken(String skypeToken, int expirationTime) {
        updateSkypeToken(NativeStringConvert.ConvertToNativeBytes(skypeToken), expirationTime);
    }

    public void updateSkypeToken(String skypeToken) {
        updateSkypeToken(skypeToken, 0);
    }
}
