package com.skype.smsmanager.nativesms.services;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;
import com.skype.smsmanager.nativesms.SmsMmsLogger;
import com.skype.smsmanager.nativesms.models.CellularMessagesMap;
import java.util.ArrayList;

public final class GetCellularMessages {
    private final Context a;
    private final String b = "GetCellularMessages";

    public GetCellularMessages(Context context) {
        this.a = context;
    }

    public final ArrayList<String> a() {
        ArrayList<String> smsMessages = new ArrayList();
        Cursor cursor = this.a.getContentResolver().query(Uri.parse(Sms.CONTENT_URI.toString()), new String[]{"_id"}, null, null, null);
        if (cursor == null) {
            SmsMmsLogger.a("GetCellularMessages", "getAllSmsMessages() - cursor is null");
        } else {
            CursorHelper cursorHelper = new CursorHelper(cursor);
            while (cursor.moveToNext()) {
                try {
                    smsMessages.add("sms-" + cursorHelper.a("_id"));
                } finally {
                    cursor.close();
                }
            }
            SmsMmsLogger.a("GetCellularMessages", "getAllSmsMessages() - SMS Messages count:" + smsMessages.size());
        }
        return smsMessages;
    }

    public final ArrayList<String> b() {
        ArrayList<String> mmsMessages = new ArrayList();
        Cursor cursor = this.a.getContentResolver().query(Mms.CONTENT_URI, new String[]{"_id"}, null, null, null);
        if (cursor == null) {
            SmsMmsLogger.a("GetCellularMessages", "getAllMmsMessages() - cursor is null");
        } else {
            CursorHelper cursorHelper = new CursorHelper(cursor);
            while (cursor.moveToNext()) {
                try {
                    mmsMessages.add("mms-" + cursorHelper.a("_id"));
                } finally {
                    cursor.close();
                }
            }
            SmsMmsLogger.a("GetCellularMessages", "getAllMmsMessages() - MMS Messages count:" + mmsMessages.size());
        }
        return mmsMessages;
    }

    public final CellularMessagesMap c() {
        ArrayList<String> smsMessages = a();
        ArrayList<String> mmsMessages = b();
        CellularMessagesMap currentSmsMmsLocalStoreMapping = new CellularMessagesMap(smsMessages, mmsMessages);
        SmsMmsLogger.a("GetCellularMessages", "getAllSmsMms() - Total SMS & MMS Messages:" + (smsMessages.size() + mmsMessages.size()));
        return currentSmsMmsLocalStoreMapping;
    }
}
