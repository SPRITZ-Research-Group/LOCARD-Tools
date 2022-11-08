package com.skype4life.syncadapter;

import android.accounts.Account;
import android.annotation.TargetApi;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.telephony.PhoneNumberUtils;
import com.facebook.common.logging.FLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class d implements e {
    private static final String[] a = new String[]{"_id", "display_name_source", "display_name", "has_phone_number"};
    private static final String[] b = new String[]{"40"};
    private static final String[] c = new String[]{"_id", "sourceid", "contact_id"};
    private static final String[] d = new String[]{"_id", "data1", "data2", "data4", "mimetype"};
    private final Context e;
    private final Account f;
    private final boolean g;
    private final k h;

    public d(Context context) {
        this.e = context;
        this.h = new k(context);
        this.f = a.a(this.h.k(), this.h.j());
        this.g = false;
    }

    public d(Context context, Account account) {
        this.e = context;
        this.h = new k(context);
        this.f = account;
        this.g = true;
    }

    public final k a() {
        return this.h;
    }

    public final i a(Uri resourceUri) {
        Cursor cursor = this.e.getContentResolver().query(resourceUri, d, null, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    i nativeRawData;
                    if (this.h.d().equals(cursor.getString(cursor.getColumnIndex("mimetype")))) {
                        nativeRawData = i.a(cursor, g.PSTN);
                    } else {
                        nativeRawData = i.a(cursor, g.FREE);
                    }
                    a(cursor);
                    return nativeRawData;
                }
            } catch (Throwable e) {
                FLog.e("ContactsManager", "Cannot retrieve raw data of " + resourceUri.toString(), e);
                throw e;
            } catch (Throwable th) {
                a(cursor);
            }
        }
        a(cursor);
        return null;
    }

    public final boolean a(String mimeType) {
        return this.h.a().contains(mimeType);
    }

    @TargetApi(21)
    public final Set<String> a(b contact) {
        Set<String> phoneNumbers = new HashSet();
        Cursor cursor = this.e.getContentResolver().query(Phone.CONTENT_URI, null, "contact_id =?", new String[]{contact.a()}, null);
        while (cursor != null) {
            try {
                if (!cursor.moveToNext()) {
                    break;
                }
                phoneNumbers.add(PhoneNumberUtils.normalizeNumber(cursor.getString(cursor.getColumnIndex("data1"))));
            } catch (Throwable e) {
                FLog.e("ContactsManager", "Cannot retrieve phone number of given contact", e);
                throw e;
            } catch (Throwable th) {
                a(cursor);
            }
        }
        a(cursor);
        return phoneNumbers;
    }

    public final List<f> b() {
        List<f> result = new ArrayList();
        Cursor cursor = this.e.getContentResolver().query(b(Contacts.CONTENT_URI), a, "display_name_source IN(?)", b, null);
        while (cursor != null) {
            try {
                if (!cursor.moveToNext()) {
                    break;
                }
                result.add(f.a(cursor));
            } catch (Throwable e) {
                FLog.e("ContactsManager", "Cannot retrieve all native contacts ", e);
                throw e;
            } catch (Throwable th) {
                a(cursor);
            }
        }
        a(cursor);
        return result;
    }

    private void a(h rawContact) {
        Cursor cursor = this.e.getContentResolver().query(b(Data.CONTENT_URI), d, "raw_contact_id=? AND mimetype IN(?,?,?,?)", new String[]{rawContact.c(), this.h.d(), this.h.e(), this.h.g(), this.h.b()}, null);
        while (cursor != null) {
            i nativeRawData;
            if (!cursor.moveToNext()) {
                break;
            } else if (this.h.d().equals(cursor.getString(cursor.getColumnIndex("mimetype")))) {
                nativeRawData = i.a(cursor, g.PSTN);
                rawContact.b().put(nativeRawData.c(), nativeRawData);
            } else {
                try {
                    nativeRawData = i.a(cursor, g.FREE);
                    rawContact.a().put(nativeRawData.e(), nativeRawData);
                } catch (Throwable e) {
                    FLog.e("ContactsManager", "Cannot populate raw data of raw contact", e);
                    throw e;
                } catch (Throwable th) {
                    a(cursor);
                }
            }
        }
        a(cursor);
    }

    public final f a(String firstName, String lastName) {
        f fVar = null;
        if (!(firstName == null || lastName == null)) {
            Cursor cursor = this.e.getContentResolver().query(b(Data.CONTENT_URI), new String[]{"contact_id"}, "data2=? AND data3=?", new String[]{firstName, lastName}, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        fVar = b(cursor.getString(cursor.getColumnIndex("contact_id")));
                        a(cursor);
                    }
                } catch (Throwable e) {
                    FLog.e("ContactsManager", "Cannot find native contact by firstName and lastName", e);
                    throw e;
                } catch (Throwable th) {
                    a(cursor);
                }
            }
            a(cursor);
        }
        return fVar;
    }

    private f b(String id) {
        f fVar = null;
        Cursor cursor = this.e.getContentResolver().query(b(Contacts.CONTENT_URI).buildUpon().appendPath(id).build(), a, "display_name_source IN(?)", b, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    fVar = f.a(cursor);
                    a(cursor);
                    return fVar;
                }
            } catch (Throwable e) {
                FLog.e("ContactsManager", "Cannot find native contact by id", e);
                throw e;
            } catch (Throwable th) {
                a(cursor);
            }
        }
        a(cursor);
        return fVar;
    }

    public final h a(f contact) {
        h hVar = null;
        Cursor cursor = this.e.getContentResolver().query(b(RawContacts.CONTENT_URI).buildUpon().appendQueryParameter("account_name", this.f.name).appendQueryParameter("account_type", this.f.type).build(), c, "sourceid=? OR contact_id=?", new String[]{contact.b(), contact.a()}, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    hVar = new h(cursor.getString(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("sourceid")), cursor.getString(cursor.getColumnIndex("contact_id")));
                    a(cursor);
                    return hVar;
                }
            } catch (Throwable e) {
                FLog.e("ContactsManager", "Cannot find native raw contact by contact.", e);
                throw e;
            } catch (Throwable th) {
                a(cursor);
            }
        }
        a(cursor);
        return hVar;
    }

    public final void a(f nativeContacts, g... capabilities) {
        Throwable e;
        ArrayList<ContentProviderOperation> ops = new ArrayList();
        nativeContacts.a(a(nativeContacts));
        if (nativeContacts.f()) {
            FLog.i("ContactsManager", "Insert from contact");
            if (nativeContacts.f()) {
                ops.add(ContentProviderOperation.newInsert(b(RawContacts.CONTENT_URI)).withValue("account_name", this.f.name).withValue("account_type", this.f.type).withValue("sourceid", nativeContacts.b()).build());
                switch (nativeContacts.c()) {
                    case 40:
                        ops.add(a(nativeContacts, "vnd.android.cursor.item/name").withValue("data1", nativeContacts.g()).withYieldAllowed(true).build());
                        break;
                    default:
                        throw new IllegalArgumentException("Display source not supported. Current source: " + nativeContacts.c());
                }
            }
            throw new IllegalArgumentException("Do not create multiple raw contact from the same contact");
        }
        a(nativeContacts.e());
        if (b(nativeContacts, capabilities)) {
            FLog.i("ContactsManager", "Update from contact");
        } else {
            FLog.i("ContactsManager", "Contact is already up to date");
            return;
        }
        for (int i = 0; i <= 0; i++) {
            g capability = capabilities[0];
            FLog.i("ContactsManager", "Adding capability " + capability + " to contact ");
            switch (capability) {
                case FREE:
                    if (nativeContacts.i() != null) {
                        ops.add(b(nativeContacts, this.h.g(), this.h.h()).build());
                        ops.add(b(nativeContacts, this.h.b(), this.h.c()).build());
                        ops.add(b(nativeContacts, this.h.e(), this.h.f()).build());
                        break;
                    }
                    throw new IllegalArgumentException("SkypeId should be defined at this point");
                case PSTN:
                    ops.add(c(nativeContacts).build());
                    for (String phoneNumber : nativeContacts.h()) {
                        Builder withValueBackReference;
                        String d = this.h.d();
                        if (nativeContacts.f()) {
                            withValueBackReference = ContentProviderOperation.newInsert(b(Data.CONTENT_URI)).withValueBackReference("raw_contact_id", 0);
                        } else if (nativeContacts.e().c(phoneNumber)) {
                            withValueBackReference = ContentProviderOperation.newUpdate(c(nativeContacts.e().d(phoneNumber).a()));
                        } else {
                            withValueBackReference = ContentProviderOperation.newInsert(b(Data.CONTENT_URI)).withValue("raw_contact_id", nativeContacts.e().c());
                        }
                        ops.add(withValueBackReference.withValue("mimetype", d).withValue("data1", phoneNumber).withValue("data3", this.h.k()).withValue("data4", this.h.a(phoneNumber)).build());
                    }
                    break;
                default:
                    break;
            }
        }
        try {
            this.e.getContentResolver().applyBatch("com.android.contacts", ops);
            return;
        } catch (RemoteException e2) {
            e = e2;
        } catch (OperationApplicationException e3) {
            e = e3;
        }
        FLog.e("ContactsManager", "Could not add " + Arrays.toString(capabilities) + " to contact", e);
        throw new RuntimeException(e);
    }

    private boolean b(f nativeContacts, g... capabilities) {
        for (g capability : capabilities) {
            switch (capability) {
                case FREE:
                    if (nativeContacts.i() != null) {
                        if (!a(nativeContacts, this.h.g(), this.h.h()) && !a(nativeContacts, this.h.b(), this.h.c()) && !a(nativeContacts, this.h.e(), this.h.f())) {
                            break;
                        }
                        return true;
                    }
                    throw new IllegalArgumentException("SkypeId should be defined at this point");
                case PSTN:
                    if (!b(nativeContacts)) {
                        break;
                    }
                    return true;
                default:
                    break;
            }
        }
        return false;
    }

    private static boolean b(f nativeContacts) {
        if (nativeContacts.h().size() != nativeContacts.e().b().size()) {
            return true;
        }
        for (String phoneNumber : nativeContacts.h()) {
            if (!nativeContacts.e().c(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(f nativeContacts, String mimeType, String actionName) {
        if (!nativeContacts.e().a(mimeType)) {
            return true;
        }
        i nativeRawData = nativeContacts.e().b(mimeType);
        if (nativeContacts.i().equals(nativeRawData.b()) && actionName.equals(nativeRawData.d())) {
            return false;
        }
        return true;
    }

    private Builder b(f nativeContacts, String mimeType, String actionName) {
        return a(nativeContacts, mimeType).withValue("data1", nativeContacts.i()).withValue("data3", this.h.k()).withValue("data4", actionName);
    }

    private Builder c(f nativeContacts) {
        String str;
        StringBuilder selectionBuilder = new StringBuilder();
        selectionBuilder.append("raw_contact_id=? AND mimetype=?");
        String[] selectionArgs = new String[(nativeContacts.h().size() + 2)];
        if (nativeContacts.e() == null) {
            str = "-1";
        } else {
            str = nativeContacts.e().c();
        }
        selectionArgs[0] = str;
        selectionArgs[1] = this.h.d();
        if (!nativeContacts.h().isEmpty()) {
            selectionBuilder.append(" AND data1 NOT IN (");
            String separator = "";
            int i = 0;
            for (String phone : nativeContacts.h()) {
                selectionBuilder.append(separator);
                separator = ",";
                selectionBuilder.append('?');
                int i2 = i + 1;
                selectionArgs[i + 2] = phone;
                i = i2;
            }
            selectionBuilder.append(")");
        }
        return ContentProviderOperation.newDelete(b(Data.CONTENT_URI)).withSelection(selectionBuilder.toString(), selectionArgs);
    }

    private Builder a(f nativeContacts, String mimeType) {
        if (nativeContacts.f()) {
            return ContentProviderOperation.newInsert(b(Data.CONTENT_URI)).withValueBackReference("raw_contact_id", 0).withValue("mimetype", mimeType);
        }
        if (nativeContacts.e().a(mimeType)) {
            return ContentProviderOperation.newUpdate(c(nativeContacts.e().b(mimeType).a()));
        }
        return ContentProviderOperation.newInsert(b(Data.CONTENT_URI)).withValue("raw_contact_id", nativeContacts.e().c()).withValue("mimetype", mimeType);
    }

    private Uri c(String rawDataId) {
        return b(Data.CONTENT_URI.buildUpon().appendPath(rawDataId).build());
    }

    private Uri b(Uri uri) {
        if (this.g) {
            return uri.buildUpon().appendQueryParameter("caller_is_syncadapter", "true").build();
        }
        return uri;
    }

    private static void a(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }
}
