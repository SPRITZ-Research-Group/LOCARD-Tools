package com.skype.smsmanager.nativesms.services;

import android.content.Context;
import android.content.Intent;
import com.skype.smsmanager.nativesms.SmsManagerConstants.IntentType;
import com.skype.smsmanager.nativesms.SmsMmsLogger;
import com.skype.smsmanager.nativesms.models.CellularMessageType;
import com.skype.smsmanager.nativesms.models.CellularMessagesMap;
import com.skype.smsmanager.nativesms.receivers.SmsRelayServiceIntentReceiver;
import java.util.ArrayList;

public final class DeleteCellularMessages {
    private final Context a;
    private final String b = "DeleteCellularMessages";

    public DeleteCellularMessages(Context context) {
        this.a = context;
    }

    public final ArrayList<String> a(CellularMessagesMap cellularMessagesMap, ArrayList<String> currentSmsMessages) {
        ArrayList deletedSmsMessageIds = a((ArrayList) currentSmsMessages, cellularMessagesMap.a());
        cellularMessagesMap.a(deletedSmsMessageIds, CellularMessageType.SmsMessage);
        a(deletedSmsMessageIds);
        SmsMmsLogger.b("DeleteCellularMessages", "deleteSmsMessages() - deleted SMS messages: " + deletedSmsMessageIds.size());
        return deletedSmsMessageIds;
    }

    public final ArrayList<String> b(CellularMessagesMap cellularMessagesMap, ArrayList<String> currentMmsMessages) {
        ArrayList deletedMmsMessageIds = a((ArrayList) currentMmsMessages, cellularMessagesMap.b());
        cellularMessagesMap.a(deletedMmsMessageIds, CellularMessageType.MmsMessage);
        a(deletedMmsMessageIds);
        SmsMmsLogger.b("DeleteCellularMessages", "deleteMmsMessages() - deleted MMS messages: " + deletedMmsMessageIds.size());
        return deletedMmsMessageIds;
    }

    private static ArrayList<String> a(ArrayList<String> currentCellularMessages, ArrayList<String> originalCellularMessages) {
        ArrayList<String> deletedCellularMessages = new ArrayList(originalCellularMessages);
        deletedCellularMessages.removeAll(currentCellularMessages);
        SmsMmsLogger.a("DeleteCellularMessages", "getDeletedCellularMessages() - Count of messages to delete: " + deletedCellularMessages.size());
        return deletedCellularMessages;
    }

    private void a(ArrayList<String> deletedCellularMessageIds) {
        if (deletedCellularMessageIds.size() == 0) {
            SmsMmsLogger.a("DeleteCellularMessages", "sendDeleteCellularMessageBroadcast() - no messages to delete");
            return;
        }
        SmsMmsLogger.a("DeleteCellularMessages", "sendDeleteCellularMessageBroadcast() - sending delete broadcast. Count of messages deleted: " + deletedCellularMessageIds.size());
        Intent intent = new Intent(this.a, SmsRelayServiceIntentReceiver.class);
        intent.setAction("ACTION_SMSMMS_SERVICE");
        intent.putExtra("IntentType", IntentType.INTENT_DELETED_CELLULAR_MESSAGE.a());
        intent.putStringArrayListExtra("DeletedMessageIds", deletedCellularMessageIds);
        this.a.sendBroadcast(intent);
    }
}
