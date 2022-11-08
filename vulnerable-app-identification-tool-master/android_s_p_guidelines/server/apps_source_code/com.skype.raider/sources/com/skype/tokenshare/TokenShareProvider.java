package com.skype.tokenshare;

import android.os.IBinder;
import android.os.RemoteException;
import com.facebook.common.logging.FLog;
import com.microsoft.tokenshare.AccountInfo;
import com.microsoft.tokenshare.RefreshToken;
import com.microsoft.tokenshare.g;
import java.util.LinkedList;
import java.util.List;

public class TokenShareProvider implements g {
    public final List<AccountInfo> a() throws RemoteException {
        LinkedList<AccountInfo> accountList = new LinkedList();
        AccountInfo accountInfo = a.a().b();
        if (accountInfo != null) {
            accountList.add(accountInfo);
        }
        FLog.i("TokenShareModule", "getAccounts() called, returned with list size " + accountList.size());
        return accountList;
    }

    public final RefreshToken a(AccountInfo accountInfo) throws RemoteException {
        FLog.i("TokenShareModule", "getToken()");
        AccountInfo storedAccount = a.a().b();
        if (storedAccount == null || !storedAccount.getAccountId().equals(accountInfo.getAccountId())) {
            return null;
        }
        FLog.i("TokenShareModule", "getToken() account stored matches the requested one");
        return a.a().c();
    }

    public final String b() throws RemoteException {
        return null;
    }

    public IBinder asBinder() {
        return null;
    }
}
