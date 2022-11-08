package com.skype4life.syncadapter;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.content.Context;
import android.os.Bundle;
import com.facebook.common.logging.FLog;

public final class a extends AbstractAccountAuthenticator {
    private final Context a;

    public static Account a(String accountName, String accountType) {
        return new Account(accountName, accountType);
    }

    public a(Context context) {
        super(context);
        this.a = context;
    }

    public final Bundle editProperties(AccountAuthenticatorResponse r, String s) {
        FLog.i("Authenticator", "editProperties");
        throw new UnsupportedOperationException();
    }

    public final Bundle addAccount(AccountAuthenticatorResponse r, String s, String s2, String[] strings, Bundle bundle) {
        FLog.i("Authenticator", "addAccount");
        Bundle result = new Bundle();
        result.putInt("errorCode", 1);
        result.putString("errorMessage", "Not supported");
        return result;
    }

    public final Bundle confirmCredentials(AccountAuthenticatorResponse r, Account account, Bundle bundle) {
        FLog.i("Authenticator", "confirmCredentials");
        return null;
    }

    public final Bundle getAuthToken(AccountAuthenticatorResponse r, Account account, String s, Bundle bundle) {
        FLog.i("Authenticator", "getAuthToken");
        throw new UnsupportedOperationException();
    }

    public final String getAuthTokenLabel(String s) {
        FLog.i("Authenticator", "getAuthTokenLabel");
        throw new UnsupportedOperationException();
    }

    public final Bundle updateCredentials(AccountAuthenticatorResponse r, Account account, String s, Bundle bundle) {
        FLog.i("Authenticator", "updateCredentials");
        throw new UnsupportedOperationException();
    }

    public final Bundle hasFeatures(AccountAuthenticatorResponse r, Account account, String[] strings) {
        FLog.i("Authenticator", "hasFeatures");
        Bundle result = new Bundle();
        result.putBoolean("booleanResult", false);
        return result;
    }
}
