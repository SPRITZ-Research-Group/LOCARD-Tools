package com.skype.smsmanager.nativesms.models;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;

public interface MessageListener {
    void a(Context context, MmsMessageItem mmsMessageItem);

    void a(Context context, SmsMessageItem smsMessageItem);

    void a(DeletedCellularMessagesItem deletedCellularMessagesItem);

    void a(OutgoingMessageStatusImpl outgoingMessageStatusImpl);

    void a(ArrayList<HashMap<String, Object>> arrayList);
}
