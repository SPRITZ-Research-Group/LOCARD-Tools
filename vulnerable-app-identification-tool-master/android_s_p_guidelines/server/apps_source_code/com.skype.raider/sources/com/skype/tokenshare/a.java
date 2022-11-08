package com.skype.tokenshare;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.microsoft.tokenshare.AccountInfo;
import com.microsoft.tokenshare.RefreshToken;

final class a {
    private static a a = new a();
    private AccountInfo b = null;
    private RefreshToken c = null;

    a() {
    }

    static a a() {
        return a;
    }

    @Nullable
    final AccountInfo b() {
        return this.b;
    }

    @Nullable
    final RefreshToken c() {
        return this.c;
    }

    final void a(@NonNull AccountInfo account, @NonNull RefreshToken refreshToken) {
        FLog.i("TokenShareModule", "setAccountWithToken()");
        this.b = account;
        this.c = refreshToken;
    }

    final boolean d() {
        FLog.i("TokenShareModule", "clearAccountWithToken()");
        if (this.b == null && this.c == null) {
            return false;
        }
        FLog.i("TokenShareModule", "clearAccountWithToken() clearing the account and the token");
        this.b = null;
        this.c = null;
        return true;
    }
}
