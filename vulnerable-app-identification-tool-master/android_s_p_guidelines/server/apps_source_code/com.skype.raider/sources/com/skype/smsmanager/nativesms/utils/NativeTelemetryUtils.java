package com.skype.smsmanager.nativesms.utils;

import android.content.Context;
import android.content.Intent;
import com.skype.smsmanager.nativesms.SmsMmsLogger;
import com.skype.smsmanager.nativesms.receivers.SmsRelayServiceIntentReceiver;
import java.util.ArrayList;
import java.util.HashMap;

public final class NativeTelemetryUtils {
    public static HashMap<String, Object> a(String messageId, String mmsText, String sender, boolean isTextOnly, boolean isIncoming) {
        boolean isNullSender;
        boolean isNullMessageId;
        boolean isNullMessageText = false;
        SmsMmsLogger.a("NativeTelemetryUtils", "buildTelemetryInfoForBrickedMessage() - started");
        HashMap<String, Object> telemetryInfo = new HashMap();
        if (sender == null) {
            isNullSender = true;
        } else {
            isNullSender = false;
        }
        if (messageId == null) {
            isNullMessageId = true;
        } else {
            isNullMessageId = false;
        }
        if (mmsText == null) {
            isNullMessageText = true;
        }
        telemetryInfo.put("TelemetryEventName", "brickedMessage");
        telemetryInfo.put("TelemetryIsIncoming", Boolean.valueOf(isIncoming));
        telemetryInfo.put("TelemetryIsMmsMsg", Boolean.valueOf(true));
        telemetryInfo.put("TelemetryIsTextOnlyMmsMsg", Boolean.valueOf(isTextOnly));
        telemetryInfo.put("TelemetryIsNullSender", Boolean.valueOf(isNullSender));
        telemetryInfo.put("TelemetryIsNullMsgId", Boolean.valueOf(isNullMessageId));
        telemetryInfo.put("TelemetryIsNullMsgText", Boolean.valueOf(isNullMessageText));
        return telemetryInfo;
    }

    public static void a(Context context, ArrayList<HashMap<String, Object>> telemetryInfos) {
        if (telemetryInfos.size() > 0) {
            Intent intent = new Intent(context, SmsRelayServiceIntentReceiver.class);
            intent.setAction("ACTION_SMS_NATIVE_TELEMETRY");
            intent.putExtra("TelemetryInfos", telemetryInfos);
            context.sendBroadcast(intent);
            SmsMmsLogger.a("NativeTelemetryUtils", String.format("sendNativeTelemetryInfo() - Sending %d telemetry infos", new Object[]{Integer.valueOf(telemetryInfos.size())}));
        }
    }
}
