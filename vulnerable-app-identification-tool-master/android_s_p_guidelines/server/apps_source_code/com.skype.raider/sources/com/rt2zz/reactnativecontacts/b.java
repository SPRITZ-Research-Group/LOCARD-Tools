package com.rt2zz.reactnativecontacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.Profile;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class b {
    private static final List<String> a = new ArrayList<String>() {
        {
            add("contact_id");
            add("lookup");
            add("mimetype");
            add("display_name");
            add("photo_uri");
            add("data1");
            add("data2");
            add("data5");
            add("data3");
            add("data4");
            add("data6");
            add("data1");
            add("data2");
            add("data3");
            add("data1");
            add("data1");
            add("data2");
            add("data3");
            add("data1");
            add("data4");
            add("data5");
            add("data1");
            add("data2");
            add("data3");
            add("data4");
            add("data5");
            add("data6");
            add("data7");
            add("data8");
            add("data9");
            add("data10");
        }
    };
    private static final List<String> b = new ArrayList<String>() {
        {
            addAll(b.a);
        }
    };
    private static final List<String> c = new ArrayList<String>() {
        {
            add("photo_uri");
        }
    };
    private final ContentResolver d;

    private static class a {
        private String a;
        private String b;
        private String c = "";
        private String d = "";
        private String e = "";
        private String f = "";
        private String g = "";
        private String h = "";
        private String i = "";
        private String j = "";
        private boolean k = false;
        private String l;
        private List<a> m = new ArrayList();
        private List<a> n = new ArrayList();
        private List<b> o = new ArrayList();

        public static class a {
            public String a;
            public String b;

            public a(String label, String value) {
                this.a = label;
                this.b = value;
            }
        }

        public static class b {
            public final ar a = new WritableNativeMap();

            public b(Cursor cursor) {
                String string;
                ar arVar = this.a;
                String str = "label";
                switch (cursor.getInt(cursor.getColumnIndex("data2"))) {
                    case 0:
                        string = cursor.getString(cursor.getColumnIndex("data3"));
                        if (string == null) {
                            string = "";
                            break;
                        }
                        break;
                    case 1:
                        string = "home";
                        break;
                    case 2:
                        string = "work";
                        break;
                    default:
                        string = "other";
                        break;
                }
                arVar.putString(str, string);
                a(cursor, "formattedAddress", "data1");
                a(cursor, "street", "data4");
                a(cursor, "pobox", "data5");
                a(cursor, "neighborhood", "data6");
                a(cursor, "city", "data7");
                a(cursor, "region", "data8");
                a(cursor, "state", "data8");
                a(cursor, "postCode", "data9");
                a(cursor, "country", "data10");
            }

            private void a(Cursor cursor, String key, String androidKey) {
                String value = cursor.getString(cursor.getColumnIndex(androidKey));
                if (!TextUtils.isEmpty(value)) {
                    this.a.putString(key, value);
                }
            }
        }

        public a(String contactId) {
            this.a = contactId;
        }

        public final ar a() {
            String str;
            ar map;
            ar contact = new WritableNativeMap();
            contact.putString("recordId", this.a);
            contact.putString("givenName", TextUtils.isEmpty(this.c) ? this.b : this.c);
            contact.putString("middleName", this.d);
            contact.putString("familyName", this.e);
            contact.putString("prefix", this.f);
            contact.putString("suffix", this.g);
            contact.putString("company", this.h);
            contact.putString("jobTitle", this.i);
            contact.putString("department", this.j);
            contact.putBoolean("hasThumbnail", this.k);
            String str2 = "thumbnailPath";
            if (this.l == null) {
                str = "";
            } else {
                str = this.l;
            }
            contact.putString(str2, str);
            aq phoneNumbers = new WritableNativeArray();
            for (a item : this.n) {
                map = new WritableNativeMap();
                map.putString("number", item.b);
                map.putString("label", item.a);
                phoneNumbers.pushMap(map);
            }
            contact.putArray("phoneNumbers", phoneNumbers);
            aq emailAddresses = new WritableNativeArray();
            for (a item2 : this.m) {
                map = new WritableNativeMap();
                map.putString("email", item2.b);
                map.putString("label", item2.a);
                emailAddresses.pushMap(map);
            }
            contact.putArray("emailAddresses", emailAddresses);
            aq postalAddresses = new WritableNativeArray();
            for (b item3 : this.o) {
                postalAddresses.pushMap(item3.a);
            }
            contact.putArray("postalAddresses", postalAddresses);
            return contact;
        }
    }

    public b(ContentResolver contentResolver) {
        this.d = contentResolver;
    }

    public final aq a(String searchString) {
        Cursor cursor = this.d.query(Data.CONTENT_URI, (String[]) b.toArray(new String[b.size()]), "display_name LIKE ?", new String[]{"%" + searchString + "%"}, null);
        if (cursor == null) {
            return null;
        }
        try {
            Map<String, a> matchingContacts = a(cursor);
            aq contacts = new WritableNativeArray();
            for (a contact : matchingContacts.values()) {
                contacts.pushMap(contact.a());
            }
            return contacts;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public final int a() {
        Cursor cursor = this.d.query(Data.CONTENT_URI, (String[]) b.toArray(new String[b.size()]), null, null, null);
        if (cursor == null) {
            return 0;
        }
        Map<String, Integer> map = new LinkedHashMap();
        while (cursor != null && cursor.moveToNext()) {
            try {
                int columnIndex = cursor.getColumnIndex("contact_id");
                if (!(columnIndex == -1 || cursor.isNull(columnIndex))) {
                    String contactId = cursor.getString(columnIndex);
                    if (!map.containsKey(contactId)) {
                        map.put(contactId, Integer.valueOf(0));
                    }
                }
            } catch (Throwable e) {
                FLog.e("ContactsProvider", e, "Failed to get contact", new Object[0]);
            }
        }
        cursor.close();
        return map.size();
    }

    public final aq b() {
        Cursor cursor = this.d.query(Uri.withAppendedPath(Profile.CONTENT_URI, "data"), (String[]) a.toArray(new String[a.size()]), null, null, null);
        Map<String, a> justMe;
        Map<String, a> everyoneElse;
        aq contacts;
        if (cursor != null) {
            try {
                justMe = a(cursor);
                cursor = this.d.query(Data.CONTENT_URI, (String[]) b.toArray(new String[b.size()]), "mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR mimetype=?", new String[]{"vnd.android.cursor.item/email_v2", "vnd.android.cursor.item/phone_v2", "vnd.android.cursor.item/name", "vnd.android.cursor.item/organization", "vnd.android.cursor.item/postal-address_v2"}, null);
                if (cursor == null) {
                    try {
                        everyoneElse = a(cursor);
                        contacts = new WritableNativeArray();
                        if (justMe != null) {
                            for (a contact : justMe.values()) {
                                contacts.pushMap(contact.a());
                            }
                        }
                        if (everyoneElse != null) {
                            for (a contact2 : everyoneElse.values()) {
                                contacts.pushMap(contact2.a());
                            }
                        }
                        return contacts;
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                } else {
                    everyoneElse = null;
                    contacts = new WritableNativeArray();
                    if (justMe != null) {
                        while (r0.hasNext()) {
                            contacts.pushMap(contact2.a());
                        }
                    }
                    if (everyoneElse != null) {
                        while (r0.hasNext()) {
                            contacts.pushMap(contact2.a());
                        }
                    }
                    return contacts;
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        } else {
            justMe = null;
            cursor = this.d.query(Data.CONTENT_URI, (String[]) b.toArray(new String[b.size()]), "mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR mimetype=?", new String[]{"vnd.android.cursor.item/email_v2", "vnd.android.cursor.item/phone_v2", "vnd.android.cursor.item/name", "vnd.android.cursor.item/organization", "vnd.android.cursor.item/postal-address_v2"}, null);
            if (cursor == null) {
                everyoneElse = null;
                contacts = new WritableNativeArray();
                if (justMe != null) {
                    while (r0.hasNext()) {
                        contacts.pushMap(contact2.a());
                    }
                }
                if (everyoneElse != null) {
                    while (r0.hasNext()) {
                        contacts.pushMap(contact2.a());
                    }
                }
                return contacts;
            }
            everyoneElse = a(cursor);
            contacts = new WritableNativeArray();
            if (justMe != null) {
                while (r0.hasNext()) {
                    contacts.pushMap(contact2.a());
                }
            }
            if (everyoneElse != null) {
                while (r0.hasNext()) {
                    contacts.pushMap(contact2.a());
                }
            }
            return contacts;
        }
    }

    @NonNull
    private static Map<String, a> a(Cursor cursor) {
        Map<String, a> map = new LinkedHashMap();
        while (cursor != null && cursor.moveToNext()) {
            try {
                String contactId;
                int columnIndex = cursor.getColumnIndex("contact_id");
                if (columnIndex == -1) {
                    contactId = "-1";
                } else if (!cursor.isNull(columnIndex)) {
                    contactId = cursor.getString(columnIndex);
                }
                if (!map.containsKey(contactId)) {
                    map.put(contactId, new a(contactId));
                }
                a contact = (a) map.get(contactId);
                contact.a = contactId;
                String mimeType = cursor.getString(cursor.getColumnIndex("mimetype"));
                String name = cursor.getString(cursor.getColumnIndex("display_name"));
                if (!TextUtils.isEmpty(name) && TextUtils.isEmpty(contact.b)) {
                    contact.b = name;
                }
                if (TextUtils.isEmpty(contact.l)) {
                    String rawPhotoURI = cursor.getString(cursor.getColumnIndex("photo_uri"));
                    if (!TextUtils.isEmpty(rawPhotoURI)) {
                        contact.l = rawPhotoURI;
                        contact.k = true;
                    }
                }
                int type;
                String label;
                if (mimeType.equals("vnd.android.cursor.item/name")) {
                    contact.c = cursor.getString(cursor.getColumnIndex("data2"));
                    contact.d = cursor.getString(cursor.getColumnIndex("data5"));
                    contact.e = cursor.getString(cursor.getColumnIndex("data3"));
                    contact.f = cursor.getString(cursor.getColumnIndex("data4"));
                    contact.g = cursor.getString(cursor.getColumnIndex("data6"));
                } else if (mimeType.equals("vnd.android.cursor.item/phone_v2")) {
                    String phoneNumber = cursor.getString(cursor.getColumnIndex("data1"));
                    type = cursor.getInt(cursor.getColumnIndex("data2"));
                    if (!TextUtils.isEmpty(phoneNumber)) {
                        switch (type) {
                            case 1:
                                label = "home";
                                break;
                            case 2:
                                label = "mobile";
                                break;
                            case 3:
                                label = "work";
                                break;
                            default:
                                label = "other";
                                break;
                        }
                        contact.n.add(new a(label, phoneNumber));
                    }
                } else if (mimeType.equals("vnd.android.cursor.item/email_v2")) {
                    String email = cursor.getString(cursor.getColumnIndex("data1"));
                    type = cursor.getInt(cursor.getColumnIndex("data2"));
                    if (!TextUtils.isEmpty(email)) {
                        switch (type) {
                            case 0:
                                if (cursor.getString(cursor.getColumnIndex("data3")) == null) {
                                    label = "";
                                    break;
                                }
                                label = cursor.getString(cursor.getColumnIndex("data3")).toLowerCase();
                                break;
                            case 1:
                                label = "home";
                                break;
                            case 2:
                                label = "work";
                                break;
                            case 4:
                                label = "mobile";
                                break;
                            default:
                                label = "other";
                                break;
                        }
                        contact.m.add(new a(label, email));
                    }
                } else if (mimeType.equals("vnd.android.cursor.item/organization")) {
                    contact.h = cursor.getString(cursor.getColumnIndex("data1"));
                    contact.i = cursor.getString(cursor.getColumnIndex("data4"));
                    contact.j = cursor.getString(cursor.getColumnIndex("data5"));
                } else if (mimeType.equals("vnd.android.cursor.item/postal-address_v2")) {
                    contact.o.add(new b(cursor));
                }
            } catch (Exception e) {
            }
        }
        return map;
    }

    public final String b(String contactId) {
        Cursor cursor = this.d.query(Data.CONTENT_URI, (String[]) c.toArray(new String[c.size()]), "contact_id = ?", new String[]{contactId}, null);
        if (cursor == null) {
            return null;
        }
        if (cursor != null) {
            try {
                if (cursor.moveToNext()) {
                    String rawPhotoURI = cursor.getString(cursor.getColumnIndex("photo_uri"));
                    if (!TextUtils.isEmpty(rawPhotoURI)) {
                        return rawPhotoURI;
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (cursor == null) {
            return null;
        }
        cursor.close();
        return null;
    }
}
