package com.rt2zz.reactnativecontacts;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.d;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ContactsManager extends ReactContextBaseJavaModule {
    int UpdateTimeoutMs = 15000;
    private ContentObserver contactObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) {
        final /* synthetic */ ContactsManager a;

        public final void onChange(boolean change) {
            super.onChange(change);
            this.a.sendContactsUpdateEventAfterTimeout();
        }
    };
    d testTimerCallback = null;
    private Timer updateTimer = null;

    private ContentResolver getContentResolver() {
        Context context = getReactApplicationContext();
        if (context != null) {
            return context.getContentResolver();
        }
        return null;
    }

    private b getContactsProvider() {
        ContentResolver cr = getContentResolver();
        if (cr != null) {
            return new b(cr);
        }
        return null;
    }

    public ContactsManager(ag reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void getAll(d callback) {
        getAllContacts(callback);
    }

    @ReactMethod
    public void getAllWithoutPhotos(d callback) {
        getAllContacts(callback);
    }

    @ReactMethod
    public void getContactsCount(final d callback) {
        AsyncTask.execute(new Runnable(this) {
            final /* synthetic */ ContactsManager b;

            public final void run() {
                int count = 0;
                b contactsProvider = this.b.getContactsProvider();
                if (contactsProvider != null) {
                    count = contactsProvider.a();
                }
                callback.invoke(null, Integer.valueOf(count));
            }
        });
    }

    private void getAllContacts(final d callback) {
        AsyncTask.execute(new Runnable(this) {
            final /* synthetic */ ContactsManager b;

            public final void run() {
                aq contacts = null;
                b contactsProvider = this.b.getContactsProvider();
                if (contactsProvider != null) {
                    contacts = contactsProvider.b();
                }
                callback.invoke(null, contacts);
            }
        });
    }

    @ReactMethod
    public void getContactsMatchingString(String searchString, d callback) {
        getAllContactsMatchingString(searchString, callback);
    }

    private void getAllContactsMatchingString(final String searchString, final d callback) {
        AsyncTask.execute(new Runnable(this) {
            final /* synthetic */ ContactsManager c;

            public final void run() {
                aq contacts = null;
                b contactsProvider = this.c.getContactsProvider();
                if (contactsProvider != null) {
                    contacts = contactsProvider.a(searchString);
                }
                callback.invoke(null, contacts);
            }
        });
    }

    @ReactMethod
    public void getPhotoForId(final String contactId, final d callback) {
        AsyncTask.execute(new Runnable(this) {
            final /* synthetic */ ContactsManager c;

            public final void run() {
                String photoUri = "";
                b contactsProvider = this.c.getContactsProvider();
                if (contactsProvider != null) {
                    photoUri = contactsProvider.b(contactId);
                }
                callback.invoke(null, photoUri);
            }
        });
    }

    @ReactMethod
    public void addContact(am contact, d callback) {
        int i;
        String givenName = contact.hasKey("givenName") ? contact.getString("givenName") : null;
        String middleName = contact.hasKey("middleName") ? contact.getString("middleName") : null;
        String familyName = contact.hasKey("familyName") ? contact.getString("familyName") : null;
        String prefix = contact.hasKey("prefix") ? contact.getString("prefix") : null;
        String suffix = contact.hasKey("suffix") ? contact.getString("suffix") : null;
        String company = contact.hasKey("company") ? contact.getString("company") : null;
        String jobTitle = contact.hasKey("jobTitle") ? contact.getString("jobTitle") : null;
        String department = contact.hasKey("department") ? contact.getString("department") : null;
        al phoneNumbers = contact.hasKey("phoneNumbers") ? contact.getArray("phoneNumbers") : null;
        int numOfPhones = 0;
        String[] phones = null;
        Integer[] phonesLabels = null;
        if (phoneNumbers != null) {
            numOfPhones = phoneNumbers.size();
            phones = new String[numOfPhones];
            phonesLabels = new Integer[numOfPhones];
            for (i = 0; i < numOfPhones; i++) {
                phones[i] = phoneNumbers.getMap(i).getString("number");
                phonesLabels[i] = Integer.valueOf(mapStringToPhoneType(phoneNumbers.getMap(i).getString("label")));
            }
        }
        al emailAddresses = contact.hasKey("emailAddresses") ? contact.getArray("emailAddresses") : null;
        int numOfEmails = 0;
        String[] emails = null;
        Integer[] emailsLabels = null;
        if (emailAddresses != null) {
            numOfEmails = emailAddresses.size();
            emails = new String[numOfEmails];
            emailsLabels = new Integer[numOfEmails];
            for (i = 0; i < numOfEmails; i++) {
                emails[i] = emailAddresses.getMap(i).getString("email");
                emailsLabels[i] = Integer.valueOf(mapStringToEmailType(emailAddresses.getMap(i).getString("label")));
            }
        }
        ArrayList<ContentProviderOperation> ops = new ArrayList();
        ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
        ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", givenName).withValue("data5", middleName).withValue("data3", familyName).withValue("data4", prefix).withValue("data6", suffix).build());
        Builder op = ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/organization").withValue("data1", company).withValue("data4", jobTitle).withValue("data5", department);
        ops.add(op.build());
        op.withYieldAllowed(true);
        for (i = 0; i < numOfPhones; i++) {
            ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", phones[i]).withValue("data2", phonesLabels[i]).build());
        }
        for (i = 0; i < numOfEmails; i++) {
            ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", emails[i]).withValue("data2", emailsLabels[i]).build());
        }
        try {
            getReactApplicationContext().getContentResolver().applyBatch("com.android.contacts", ops);
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(e.toString());
        }
    }

    @ReactMethod
    public void updateContact(am contact, d callback) {
        int i;
        String recordID = contact.hasKey("recordId") ? contact.getString("recordId") : null;
        String givenName = contact.hasKey("givenName") ? contact.getString("givenName") : null;
        String middleName = contact.hasKey("middleName") ? contact.getString("middleName") : null;
        String familyName = contact.hasKey("familyName") ? contact.getString("familyName") : null;
        String prefix = contact.hasKey("prefix") ? contact.getString("prefix") : null;
        String suffix = contact.hasKey("suffix") ? contact.getString("suffix") : null;
        String company = contact.hasKey("company") ? contact.getString("company") : null;
        String jobTitle = contact.hasKey("jobTitle") ? contact.getString("jobTitle") : null;
        String department = contact.hasKey("department") ? contact.getString("department") : null;
        al phoneNumbers = contact.hasKey("phoneNumbers") ? contact.getArray("phoneNumbers") : null;
        int numOfPhones = 0;
        String[] phones = null;
        Integer[] phonesLabels = null;
        if (phoneNumbers != null) {
            numOfPhones = phoneNumbers.size();
            phones = new String[numOfPhones];
            phonesLabels = new Integer[numOfPhones];
            for (i = 0; i < numOfPhones; i++) {
                am phoneMap = phoneNumbers.getMap(i);
                String phoneNumber = phoneMap.getString("number");
                String phoneLabel = phoneMap.getString("label");
                phones[i] = phoneNumber;
                phonesLabels[i] = Integer.valueOf(mapStringToPhoneType(phoneLabel));
            }
        }
        al emailAddresses = contact.hasKey("emailAddresses") ? contact.getArray("emailAddresses") : null;
        int numOfEmails = 0;
        String[] emails = null;
        Integer[] emailsLabels = null;
        if (emailAddresses != null) {
            numOfEmails = emailAddresses.size();
            emails = new String[numOfEmails];
            emailsLabels = new Integer[numOfEmails];
            for (i = 0; i < numOfEmails; i++) {
                am emailMap = emailAddresses.getMap(i);
                emails[i] = emailMap.getString("email");
                emailsLabels[i] = Integer.valueOf(mapStringToEmailType(emailMap.getString("label")));
            }
        }
        ArrayList<ContentProviderOperation> ops = new ArrayList();
        ops.add(ContentProviderOperation.newUpdate(RawContacts.CONTENT_URI).withSelection("contact_id=?", new String[]{String.valueOf(recordID)}).withValue("account_type", null).withValue("account_name", null).build());
        ops.add(ContentProviderOperation.newUpdate(Data.CONTENT_URI).withSelection("contact_id=?", new String[]{String.valueOf(recordID)}).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", givenName).withValue("data5", middleName).withValue("data3", familyName).withValue("data4", prefix).withValue("data6", suffix).build());
        Builder op = ContentProviderOperation.newUpdate(Data.CONTENT_URI).withSelection("contact_id=? AND mimetype = ?", new String[]{String.valueOf(recordID), "vnd.android.cursor.item/organization"}).withValue("data1", company).withValue("data4", jobTitle).withValue("data5", department);
        ops.add(op.build());
        op.withYieldAllowed(true);
        for (i = 0; i < numOfPhones; i++) {
            ops.add(ContentProviderOperation.newUpdate(Data.CONTENT_URI).withSelection("contact_id=? AND mimetype = ?", new String[]{String.valueOf(recordID), "vnd.android.cursor.item/phone_v2"}).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", phones[i]).withValue("data2", phonesLabels[i]).build());
        }
        for (i = 0; i < numOfEmails; i++) {
            ops.add(ContentProviderOperation.newUpdate(Data.CONTENT_URI).withSelection("raw_contact_id=? AND mimetype = ?", new String[]{String.valueOf(recordID), "vnd.android.cursor.item/email_v2"}).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", emails[i]).withValue("data2", emailsLabels[i]).build());
        }
        try {
            getReactApplicationContext().getContentResolver().applyBatch("com.android.contacts", ops);
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(e.toString());
        }
    }

    @ReactMethod
    public void deleteContact(am contact, d callback) {
        String recordID;
        if (contact.hasKey("recordId")) {
            recordID = contact.getString("recordId");
        } else {
            recordID = null;
        }
        try {
            Context ctx = getReactApplicationContext();
            if (ctx.getContentResolver().delete(Uri.withAppendedPath(Contacts.CONTENT_URI, recordID), null, null) > 0) {
                callback.invoke(null, recordID);
                return;
            }
            callback.invoke(null, null);
        } catch (Exception e) {
            callback.invoke(e.toString(), null);
        }
    }

    @ReactMethod
    public void checkPermission(d callback) {
        callback.invoke(null, isPermissionGranted());
    }

    @ReactMethod
    public void requestPermission(d callback) {
        callback.invoke(null, isPermissionGranted());
    }

    private String isPermissionGranted() {
        return getReactApplicationContext().checkCallingOrSelfPermission("android.permission.READ_CONTACTS") == 0 ? "authorized" : "denied";
    }

    private int mapStringToPhoneType(String label) {
        Object obj = -1;
        switch (label.hashCode()) {
            case -1068855134:
                if (label.equals("mobile")) {
                    obj = 2;
                    break;
                }
                break;
            case 3208415:
                if (label.equals("home")) {
                    obj = null;
                    break;
                }
                break;
            case 3655441:
                if (label.equals("work")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return 1;
            case 1:
                return 3;
            case 2:
                return 2;
            default:
                return 7;
        }
    }

    private int mapStringToEmailType(String label) {
        Object obj = -1;
        switch (label.hashCode()) {
            case -1068855134:
                if (label.equals("mobile")) {
                    obj = 2;
                    break;
                }
                break;
            case 3208415:
                if (label.equals("home")) {
                    obj = null;
                    break;
                }
                break;
            case 3655441:
                if (label.equals("work")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            default:
                return 3;
        }
    }

    public String getName() {
        return "Contacts";
    }

    protected void finalize() throws Throwable {
        try {
            unregisterContentObserver();
        } finally {
            super.finalize();
        }
    }

    private void killTimer() {
        if (this.updateTimer != null) {
            this.updateTimer.cancel();
            this.updateTimer.purge();
            this.updateTimer = null;
        }
    }

    private void sendContactsUpdateEventAfterTimeout() {
        killTimer();
        this.updateTimer = new Timer();
        this.updateTimer.schedule(new TimerTask(this) {
            final /* synthetic */ ContactsManager a;

            {
                this.a = this$0;
            }

            public final void run() {
                ag ctx = this.a.getReactApplicationContext();
                if (ctx != null) {
                    ((RCTDeviceEventEmitter) ctx.a(RCTDeviceEventEmitter.class)).emit("ContactStoreUpdateEvent", new WritableNativeMap());
                    if (this.a.testTimerCallback != null) {
                        this.a.testTimerCallback.invoke(null, null);
                    }
                }
            }
        }, (long) this.UpdateTimeoutMs);
    }

    @ReactMethod
    public void runAllTests(d callback) {
        new Timer().schedule(new com.rt2zz.reactnativecontacts.a.AnonymousClass1(new a(this), callback), 1000);
    }

    public void setTestContactsUpdateEventCallback(d callback) {
        this.testTimerCallback = callback;
    }

    @ReactMethod
    public void registerForContactChangeEvent() {
        registerContentObserver();
    }

    @ReactMethod
    public void unRegisterForContactChangeEvent() {
        unregisterContentObserver();
    }

    private void registerContentObserver() {
        ContentResolver cr = getContentResolver();
        if (cr != null) {
            cr.registerContentObserver(Contacts.CONTENT_URI, false, this.contactObserver);
        }
    }

    private void unregisterContentObserver() {
        ContentResolver cr = getContentResolver();
        if (cr != null) {
            cr.unregisterContentObserver(this.contactObserver);
        }
    }
}
