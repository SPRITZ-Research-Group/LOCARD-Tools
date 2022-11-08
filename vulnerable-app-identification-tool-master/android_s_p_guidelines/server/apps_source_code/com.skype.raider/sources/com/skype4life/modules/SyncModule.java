package com.skype4life.modules;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.PermissionChecker;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.module.annotations.ReactModule;
import com.microsoft.skype.a.a;
import com.skype.permissions.PermissionsModule.PermissionType;
import com.skype.raider.R;
import com.skype4life.syncadapter.d;
import com.skype4life.syncadapter.f;
import com.skype4life.syncadapter.g;
import com.skype4life.syncadapter.k;

@ReactModule(name = "SyncModule")
public class SyncModule extends ReactContextBaseJavaModule {
    static final String RN_CLASS = "SyncModule";
    private static final long SUGGESTED_SYNC_FREQUENCY = 86400;
    private static final String TAG = "SyncModule";
    private ag applicationContext;
    private k constants;
    private d contactsManager;

    public SyncModule(ag reactContext) {
        super(reactContext);
        this.applicationContext = reactContext;
        this.constants = new k(reactContext);
        this.contactsManager = new d(reactContext);
    }

    public String getName() {
        return "SyncModule";
    }

    @ReactMethod
    public void createSyncAccount(final ae promise) {
        FLog.i("SyncModule", "Creating sync account ");
        a.c.b(new Runnable(this) {
            final /* synthetic */ SyncModule b;

            public final void run() {
                try {
                    Account account = this.b.getAccount();
                    if (((AccountManager) this.b.applicationContext.getSystemService("account")).addAccountExplicitly(account, null, null)) {
                        String authority = this.b.constants.i();
                        ContentResolver.setIsSyncable(account, authority, 1);
                        ContentResolver.setSyncAutomatically(account, authority, true);
                        ContentResolver.addPeriodicSync(account, authority, new Bundle(), SyncModule.SUGGESTED_SYNC_FREQUENCY);
                    }
                    promise.a(null);
                } catch (Throwable e) {
                    FLog.e("SyncModule", "Failed to create account", e);
                    promise.a(null, "Failed to create account", e);
                }
            }
        });
    }

    @ReactMethod
    public void deleteSyncAccount(final ae promise) {
        FLog.i("SyncModule", "Deleting sync account ");
        a.c.b(new Runnable(this) {
            final /* synthetic */ SyncModule b;

            public final void run() {
                try {
                    ((AccountManager) this.b.applicationContext.getSystemService("account")).removeAccount(this.b.getAccount(), null, null);
                    promise.a(null);
                } catch (Throwable e) {
                    FLog.e("SyncModule", "Failed to delete account", e);
                    promise.a(null, "Failed to delete account", e);
                }
            }
        });
    }

    @ReactMethod
    public void refresh(final boolean force, final ae promise) {
        FLog.i("SyncModule", "Requesting sync: forced= " + force);
        a.c.b(new Runnable(this) {
            final /* synthetic */ SyncModule c;

            public final void run() {
                try {
                    Bundle b = new Bundle();
                    b.putBoolean("force", force);
                    b.putBoolean("expedited", true);
                    ContentResolver.requestSync(this.c.getAccount(), this.c.applicationContext.getString(R.string.sync_adapter_authority), b);
                    promise.a(null);
                } catch (Throwable e) {
                    FLog.e("SyncModule", "Failed to refresh", e);
                    promise.a(null, "Failed to refresh", e);
                }
            }
        });
    }

    @ReactMethod
    public void autoGrantContactsWritePermissions(ae promise) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            promise.a(Boolean.valueOf(false));
        } else if (PermissionChecker.a(activity, "android.permission.READ_CONTACTS") == 0 && PermissionChecker.a(activity, "android.permission.WRITE_CONTACTS") != 0) {
            if (android.support.v4.app.a.a(activity, "android.permission.READ_CONTACTS")) {
                promise.a(Boolean.valueOf(false));
                return;
            }
            android.support.v4.app.a.a(activity, new String[]{"android.permission.WRITE_CONTACTS"}, PermissionType.CONTACTS.k);
            promise.a(Boolean.valueOf(true));
        }
    }

    @ReactMethod
    public void addSkypeCapabilitiesToNativeContacts(final am syncContacts, final ae promise) {
        a.b.b(new Runnable(this) {
            final /* synthetic */ SyncModule c;

            public final void run() {
                try {
                    FLog.i("SyncModule", "Adding skype capabilities to new synchronized contacts");
                    ReadableMapKeySetIterator iterator = syncContacts.keySetIterator();
                    while (iterator.hasNextKey()) {
                        String skypeId = iterator.nextKey();
                        am contact = syncContacts.getMap(skypeId);
                        f nativeContact = this.c.contactsManager.a(contact.getString("firstName"), contact.getString("lastName"));
                        if (nativeContact != null) {
                            nativeContact.a(this.c.contactsManager.a(nativeContact));
                            nativeContact.a(skypeId);
                            this.c.contactsManager.a(nativeContact, g.FREE);
                        }
                    }
                    promise.a(null);
                } catch (Throwable e) {
                    FLog.e("SyncModule", "Failed to update contact with free skype capabilities", e);
                    promise.a(null, "Failed to update contact with free skype capabilities", e);
                }
            }
        });
    }

    @NonNull
    private Account getAccount() {
        return com.skype4life.syncadapter.a.a(this.constants.k(), this.constants.j());
    }
}
