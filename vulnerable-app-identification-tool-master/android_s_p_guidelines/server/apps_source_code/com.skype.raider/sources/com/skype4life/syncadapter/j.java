package com.skype4life.syncadapter;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import com.facebook.common.logging.FLog;

final class j extends AbstractThreadedSyncAdapter {
    public j(Context context) {
        super(context, true, false);
    }

    public final void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        FLog.i("SyncAdapter", "onPerformSync start: contacts synchronization " + account.type);
        try {
            d contactsManager = new d(getContext(), account);
            for (f contact : contactsManager.b()) {
                if (contact.d()) {
                    contact.a(contactsManager.a((b) contact));
                    contactsManager.a(contact, g.PSTN);
                }
            }
            FLog.i("SyncAdapter", "onPerformSync end: contacts synchronization " + account.type);
        } catch (Throwable e) {
            FLog.e("SyncAdapter", "onPerformSync failed ", e);
        }
    }
}
