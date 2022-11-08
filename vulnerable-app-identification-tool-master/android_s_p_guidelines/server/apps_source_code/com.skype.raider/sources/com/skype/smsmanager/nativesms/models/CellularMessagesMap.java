package com.skype.smsmanager.nativesms.models;

import com.skype.smsmanager.nativesms.SmsMmsLogger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public final class CellularMessagesMap {
    private HashSet<String> a;
    private HashSet<String> b;

    public CellularMessagesMap(ArrayList<String> smsMessages, ArrayList<String> mmsMessages) {
        this.a = new HashSet(smsMessages);
        this.b = new HashSet(mmsMessages);
    }

    public final ArrayList<String> a() {
        return new ArrayList(this.a);
    }

    public final ArrayList<String> b() {
        return new ArrayList(this.b);
    }

    public final void a(String messageId, CellularMessageType messageType) {
        switch (messageType) {
            case SmsMessage:
                a(messageId, this.a);
                return;
            case MmsMessage:
                a(messageId, this.b);
                return;
            default:
                return;
        }
    }

    public final void a(ArrayList<String> messageIds, CellularMessageType messageType) {
        Iterator it = messageIds.iterator();
        while (it.hasNext()) {
            String deletedMessageId = (String) it.next();
            switch (messageType) {
                case SmsMessage:
                    b(deletedMessageId, this.a);
                    break;
                case MmsMessage:
                    b(deletedMessageId, this.b);
                    break;
                default:
                    break;
            }
        }
    }

    private static void a(String messageId, HashSet<String> cellularMessageArray) {
        SmsMmsLogger.a("CellularMessagesMap", "Adding cellular message from cellularMessagesMap: " + messageId);
        cellularMessageArray.add(messageId);
    }

    private static void b(String messageId, HashSet<String> cellularMessageArray) {
        SmsMmsLogger.a("CellularMessagesMap", "Removing cellular message from cellularMessagesMap: " + messageId);
        cellularMessageArray.remove(messageId);
    }
}
