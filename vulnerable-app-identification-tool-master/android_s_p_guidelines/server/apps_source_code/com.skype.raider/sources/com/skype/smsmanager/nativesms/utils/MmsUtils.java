package com.skype.smsmanager.nativesms.utils;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import com.skype.smsmanager.mms.InvalidHeaderValueException;
import com.skype.smsmanager.mms.pdu.EncodedStringValue;
import com.skype.smsmanager.mms.pdu.GenericPdu;
import com.skype.smsmanager.mms.pdu.PduBody;
import com.skype.smsmanager.mms.pdu.PduComposer;
import com.skype.smsmanager.mms.pdu.PduParser;
import com.skype.smsmanager.mms.pdu.PduPart;
import com.skype.smsmanager.mms.pdu.SendConf;
import com.skype.smsmanager.mms.pdu.SendReq;
import com.skype.smsmanager.nativesms.SmsManagerConstants;
import com.skype.smsmanager.nativesms.SmsManagerConstants.IntentType;
import com.skype.smsmanager.nativesms.SmsMmsLogger;
import com.skype.smsmanager.nativesms.receivers.SmsRelayServiceIntentReceiver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public final class MmsUtils implements SmsManagerConstants {
    public static String a = "send";
    public static String b = "dat";
    private final Context c;
    private final Random d = new Random();
    private final AtomicInteger e = new AtomicInteger();

    public MmsUtils(Context context) {
        this.c = context;
    }

    public final void a(String body, String selfPhone, String[] recipients, String cuid) {
        IOException e;
        Throwable th;
        byte[] pdu = a(body, selfPhone, recipients);
        long randomLong = Math.abs(this.d.nextLong());
        String fileName = String.format("%s.%019d.%s", new Object[]{a, Long.valueOf(randomLong), b});
        File file = new File(this.c.getCacheDir(), fileName);
        SmsMmsLogger.a("MmsUtils", "sendTextMmsMessage() create: " + file);
        Uri writerUri = new Builder().authority(this.c.getPackageName() + ".SmsRelayFileProvider").path(fileName).scheme("content").build();
        Intent intent = new Intent(this.c, SmsRelayServiceIntentReceiver.class);
        intent.setAction("ACTION_SMSMMS_STATUS");
        Bundle bundle = new Bundle();
        bundle.putInt("IntentType", IntentType.INTENT_OUTGOING_MMS_STATUS.a());
        bundle.putString("MmsTextContentFile", fileName);
        bundle.putString("cuid", cuid);
        intent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.c, this.e.incrementAndGet(), intent, 134217728);
        FileOutputStream writer = null;
        Uri contentUri = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(pdu);
                contentUri = writerUri;
                try {
                    fileOutputStream.close();
                    writer = fileOutputStream;
                } catch (IOException e2) {
                    writer = fileOutputStream;
                }
            } catch (IOException e3) {
                e = e3;
                writer = fileOutputStream;
                try {
                    SmsMmsLogger.a("MmsUtils", "sendTextMmsMessage() Error writing send file: ", e);
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (IOException e4) {
                        }
                    }
                    if (contentUri != null) {
                        SmsManager.getDefault().sendMultimediaMessage(this.c, contentUri, null, null, pendingIntent);
                        return;
                    }
                    SmsMmsLogger.c("MmsUtils", "sendTextMmsMessage() Error writing sending Mms");
                    try {
                        pendingIntent.send(5);
                    } catch (CanceledException ex) {
                        SmsMmsLogger.a("MmsUtils", "sendTextMmsMessage() Mms pending intent cancelled?", ex);
                        return;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (IOException e5) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                writer = fileOutputStream;
                if (writer != null) {
                    writer.close();
                }
                throw th;
            }
        } catch (IOException e6) {
            e = e6;
            SmsMmsLogger.a("MmsUtils", "sendTextMmsMessage() Error writing send file: ", e);
            if (writer != null) {
                writer.close();
            }
            if (contentUri != null) {
                SmsMmsLogger.c("MmsUtils", "sendTextMmsMessage() Error writing sending Mms");
                pendingIntent.send(5);
            }
            SmsManager.getDefault().sendMultimediaMessage(this.c, contentUri, null, null, pendingIntent);
            return;
        }
        if (contentUri != null) {
            SmsManager.getDefault().sendMultimediaMessage(this.c, contentUri, null, null, pendingIntent);
            return;
        }
        SmsMmsLogger.c("MmsUtils", "sendTextMmsMessage() Error writing sending Mms");
        pendingIntent.send(5);
    }

    public static boolean a(Context context, Bundle bundle) {
        File sendFile = new File(context.getCacheDir(), bundle.getString("MmsTextContentFile", ""));
        try {
            if (sendFile.exists()) {
                SmsMmsLogger.a("MmsUtils", "handleMmsSentResult() delete: " + sendFile);
                sendFile.delete();
            }
        } catch (Exception e) {
        }
        boolean sent = bundle.getInt("outgoingSmsStatus") == -1;
        SmsMmsLogger.a("MmsUtils", "handleMmsSentResult() sent: " + sent);
        if (sent) {
            byte[] response = bundle.getByteArray("android.telephony.extra.MMS_DATA");
            if (response != null) {
                GenericPdu pdu = new PduParser(response, SmsManager.getDefault().getCarrierConfigValues().getBoolean("supportMmsContentDisposition", true)).a();
                if (pdu instanceof SendConf) {
                    SendConf sendConf = (SendConf) pdu;
                    if (sendConf.c() == 128) {
                        SmsMmsLogger.a("MmsUtils", "handleMmsSentResult() Sent");
                        return true;
                    }
                    SmsMmsLogger.c("MmsUtils", "handleMmsSentResult() MMS sent, error=" + sendConf.c());
                    return false;
                }
                SmsMmsLogger.c("MmsUtils", "handleMmsSentResult() MMS sent, invalid response");
                return false;
            }
            SmsMmsLogger.c("MmsUtils", "handleMmsSentResult() MMS sent, empty response");
            return false;
        }
        SmsMmsLogger.c("MmsUtils", "handleMmsSentResult() MMS not sent, error: " + bundle.getInt("outgoingSmsStatus"));
        return sent;
    }

    private byte[] a(String textBody, String selfPhone, String[] recipients) {
        String str;
        SendReq req = new SendReq();
        if (!TextUtils.isEmpty(selfPhone)) {
            req.a(new EncodedStringValue(selfPhone));
        }
        EncodedStringValue[] encodedNumbers = EncodedStringValue.a(recipients);
        if (encodedNumbers != null) {
            req.a(encodedNumbers);
        }
        PduBody body = new PduBody();
        PduPart pduPart = new PduPart();
        pduPart.a(106);
        pduPart.e("text/plain".getBytes());
        pduPart.c("text_0.txt".getBytes());
        int lastIndexOf = "text_0.txt".lastIndexOf(".");
        if (lastIndexOf == -1) {
            str = "text_0.txt";
        } else {
            str = "text_0.txt".substring(0, lastIndexOf);
        }
        pduPart.b(str.getBytes());
        pduPart.a(textBody.getBytes());
        body.a(pduPart);
        str = String.format("<smil><head><layout><root-layout/><region height=\"100%%\" id=\"Text\" left=\"0%%\" top=\"0%%\" width=\"100%%\"/></layout></head><body><par dur=\"8000ms\"><text src=\"%s\" region=\"Text\"/></par></body></smil>", new Object[]{"text_0.txt"});
        PduPart pduPart2 = new PduPart();
        pduPart2.b("smil".getBytes());
        pduPart2.c("smil.xml".getBytes());
        pduPart2.e("application/smil".getBytes());
        pduPart2.a(str.getBytes());
        body.b(pduPart2);
        int size = pduPart.a().length;
        req.a(body);
        req.a((long) size);
        req.a("personal".getBytes());
        req.f();
        try {
            req.d();
            req.e();
            req.g();
        } catch (InvalidHeaderValueException e) {
        }
        return new PduComposer(this.c, req).a();
    }
}
