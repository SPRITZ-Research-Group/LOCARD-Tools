package com.skype.tokenshare;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.microsoft.tokenshare.AccountInfo;
import com.microsoft.tokenshare.AccountInfo.AccountType;
import com.microsoft.tokenshare.RefreshToken;
import com.microsoft.tokenshare.TokenSharingService;
import com.microsoft.tokenshare.b;
import com.microsoft.tokenshare.f;
import com.microsoft.tokenshare.o;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokenShareModule extends ReactContextBaseJavaModule implements f {
    private static final String ACCOUNT_ID = "accountId";
    private static final String ACCOUNT_PHONE_NUMBER = "accountPhoneNumber";
    private static final String ACCOUNT_PRIMARY_EMAIL = "accountPrimaryEmail";
    private static final String ACCOUNT_PROVIDER_PACKAGE_ID = "accountProviderPackageId";
    private static final String ON_ACCOUNT_ADDED_EVENT = "onAccountAdded";
    static final String REACT_CLASS = "TokenShareModule";
    private static final String REFRESH_TOKEN = "refreshToken";
    private static final String SERVICE_ENABLED_INTENT = "com.microsoft.tokenshare.SERVICE_ENABLED";
    private static final String SHARED_DEVICE_ID = "sharedDeviceId";
    private static final String SSO_CONSUMER_ERROR_RETRIEVING_ACCOUNT_LIST = "SsoConsumerErrorRetrievingAccountList";
    private static final String SSO_CONSUMER_ERROR_RETRIEVING_REFRESH_TOKEN = "SsoConsumerErrorRetrievingRefreshToken";
    private static final String SSO_CONSUMER_NO_REFRESH_TOKEN_FOUND = "SsoConsumerNoRefreshTokenFound";
    private static final String SSO_CONSUMER_NO_REFRESH_TOKEN_FOUND_MSG = "No refresh token was found";
    private static final String SSO_PROVIDER_ACCOUNT_CACHED_SUCCESSFULLY = "SsoProviderAccountCachedSuccessfully";
    private static final String SSO_PROVIDER_ACCOUNT_CLEARED_SUCCESSFULLY = "SsoProviderAccountClearedSuccessfully";
    private static final String SSO_PROVIDER_ERROR_NO_ACCOUNT_ID = "SsoProviderErrorNoAccountId";
    private static final String SSO_PROVIDER_ERROR_NO_ACCOUNT_TO_CLEAR = "SsoProviderErrorNoAccountToClear";
    private static final String SSO_PROVIDER_ERROR_NO_EMAIL_OR_PHONE_NUMBER = "SsoProviderErrorNoEmailOrPhoneNumber";
    private static final String SSO_PROVIDER_ERROR_NO_REFRESH_TOKEN = "SsoProviderErrorNoRefreshToken";

    TokenShareModule(ag reactContext) {
        super(reactContext);
    }

    public void initialize() {
        super.initialize();
        o.a().b();
        o.a().a(getReactApplicationContext(), new TokenShareProvider(), (f) this);
    }

    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void getToken(final String accountUsername, final ae promise) {
        o.a().b(getReactApplicationContext(), new b<List<AccountInfo>>(this) {
            final /* synthetic */ TokenShareModule c;

            public final /* synthetic */ void a(Object obj) {
                TokenShareModule.findToken(this.c.getReactApplicationContext(), promise, accountUsername, (List) obj);
            }

            public final void a(Throwable throwable) {
                FLog.i(TokenShareModule.REACT_CLASS, "Unable to retrieve account list", throwable);
                promise.a(TokenShareModule.SSO_CONSUMER_ERROR_RETRIEVING_ACCOUNT_LIST, throwable.getMessage());
            }
        });
    }

    private static void findToken(ag context, ae promise, @Nullable String accountUsername, List<AccountInfo> accountInfoList) {
        findToken(context, promise, accountUsername, accountInfoList, 0, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void findToken(ag context, ae promise, @Nullable String accountUsername, List<AccountInfo> accountInfoList, int index, @Nullable String errorString) {
        final AccountInfo accountInfo;
        while (true) {
            int accountInfoListSize = accountInfoList.size();
            if (index < 0 || index > accountInfoListSize) {
                String errorMessage = "findToken() called with illegal index.";
                FLog.w(REACT_CLASS, errorMessage);
                promise.a(SSO_CONSUMER_ERROR_RETRIEVING_ACCOUNT_LIST, errorMessage);
            } else if (index != accountInfoListSize) {
                accountInfo = (AccountInfo) accountInfoList.get(index);
                if (!(accountInfo.getAccountType() == AccountType.MSA && (TextUtils.isEmpty(accountUsername) || accountUsername.equalsIgnoreCase(accountInfo.getPrimaryEmail()) || PhoneNumberUtils.compare(context, accountUsername, accountInfo.getPhoneNumber())))) {
                    FLog.i(REACT_CLASS, "This account doesn't match the criteria, either is not an MSA or it doesn't match the given account username.");
                    index++;
                }
            } else if (TextUtils.isEmpty(errorString)) {
                promise.a(SSO_CONSUMER_NO_REFRESH_TOKEN_FOUND, SSO_CONSUMER_NO_REFRESH_TOKEN_FOUND_MSG);
                return;
            } else {
                promise.a(SSO_CONSUMER_ERROR_RETRIEVING_REFRESH_TOKEN, errorString);
                return;
            }
        }
        final ag agVar = context;
        final ae aeVar = promise;
        final String str = accountUsername;
        final List<AccountInfo> list = accountInfoList;
        final int i = index;
        o.a().a((Context) context, accountInfo, new b<RefreshToken>() {
            public final /* synthetic */ void a(Object obj) {
                RefreshToken refreshToken = (RefreshToken) obj;
                final ar writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString(TokenShareModule.ACCOUNT_ID, accountInfo.getAccountId());
                writableNativeMap.putString(TokenShareModule.ACCOUNT_PRIMARY_EMAIL, accountInfo.getPrimaryEmail());
                writableNativeMap.putString(TokenShareModule.ACCOUNT_PHONE_NUMBER, accountInfo.getPhoneNumber());
                writableNativeMap.putString(TokenShareModule.ACCOUNT_PROVIDER_PACKAGE_ID, accountInfo.getProviderPackageId());
                writableNativeMap.putString(TokenShareModule.REFRESH_TOKEN, refreshToken.a());
                o.a().a(agVar, new b<String>(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public final /* synthetic */ void a(Object obj) {
                        writableNativeMap.putString(TokenShareModule.SHARED_DEVICE_ID, (String) obj);
                        aeVar.a(writableNativeMap);
                    }

                    public final void a(Throwable throwable) {
                        aeVar.a(writableNativeMap);
                    }
                });
            }

            public final void a(Throwable throwable) {
                FLog.i(TokenShareModule.REACT_CLASS, "Unable to retrieve refresh token.", throwable);
                TokenShareModule.findToken(agVar, aeVar, str, list, i + 1, throwable.getMessage());
            }
        });
    }

    @NonNull
    private Map<String, AccountInfo> getAccountMap(List<AccountInfo> accountList) {
        Map<String, AccountInfo> returnedList = new HashMap();
        for (AccountInfo accountInfo : accountList) {
            if (accountInfo.getAccountType() == AccountType.MSA) {
                Date refreshTokenAcquireTime = accountInfo.getRefreshTokenAcquireTime();
                String accountId = accountInfo.getAccountId();
                AccountInfo storedAccountInfo = (AccountInfo) returnedList.get(accountId);
                if (storedAccountInfo == null || (refreshTokenAcquireTime != null && refreshTokenAcquireTime.after(storedAccountInfo.getRefreshTokenAcquireTime()))) {
                    returnedList.put(accountId, accountInfo);
                }
            }
        }
        return returnedList;
    }

    @ReactMethod
    public void setToken(String accountId, String primaryEmail, String phoneNumber, String refreshToken, String appId, Double acquireTimeMs, ae promise) {
        if (TextUtils.isEmpty(primaryEmail) && TextUtils.isEmpty(phoneNumber)) {
            promise.a(SSO_PROVIDER_ERROR_NO_EMAIL_OR_PHONE_NUMBER, "Cannot provide an account with no primaryEmail and no phoneNumber.");
        } else if (TextUtils.isEmpty(refreshToken)) {
            promise.a(SSO_PROVIDER_ERROR_NO_REFRESH_TOKEN, "Cannot provide an account with no refreshToken.");
        } else if (TextUtils.isEmpty(accountId)) {
            promise.a(SSO_PROVIDER_ERROR_NO_ACCOUNT_ID, "Cannot provide an account with no accountId,");
        } else {
            setServiceEnable(true);
            a.a().a(new AccountInfo(accountId, primaryEmail, AccountType.MSA, false, phoneNumber, new Date(acquireTimeMs.longValue())), new RefreshToken(refreshToken, appId));
            promise.a(SSO_PROVIDER_ACCOUNT_CACHED_SUCCESSFULLY);
        }
    }

    @ReactMethod
    public void clearToken(ae promise) {
        if (a.a().d()) {
            setServiceEnable(false);
            promise.a(SSO_PROVIDER_ACCOUNT_CLEARED_SUCCESSFULLY);
            return;
        }
        promise.a(SSO_PROVIDER_ERROR_NO_ACCOUNT_TO_CLEAR, "No account found to be cleared.");
    }

    public void onAccountAdded(String s) {
        ar data = new WritableNativeMap();
        data.putString(ACCOUNT_PROVIDER_PACKAGE_ID, s);
        ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit(ON_ACCOUNT_ADDED_EVENT, data);
    }

    private void setServiceEnable(boolean isEnable) {
        ag context = getReactApplicationContext();
        ComponentName tokenShareService = new ComponentName(context, TokenSharingService.class);
        int oldState = context.getPackageManager().getComponentEnabledSetting(tokenShareService);
        int newState = isEnable ? 0 : 2;
        if (oldState != newState) {
            context.getPackageManager().setComponentEnabledSetting(tokenShareService, newState, 1);
            if (newState == 0) {
                context.getApplicationContext().sendBroadcast(new Intent(SERVICE_ENABLED_INTENT, Uri.parse("package:" + context.getPackageName())));
            }
        }
    }
}
