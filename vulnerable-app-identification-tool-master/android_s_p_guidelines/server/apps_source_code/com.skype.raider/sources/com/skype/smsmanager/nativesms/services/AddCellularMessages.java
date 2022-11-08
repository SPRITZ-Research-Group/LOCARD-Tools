package com.skype.smsmanager.nativesms.services;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;
import android.util.Pair;
import com.skype.smsmanager.nativesms.SmsManagerConstants.IntentType;
import com.skype.smsmanager.nativesms.SmsMmsLogger;
import com.skype.smsmanager.nativesms.models.CellularMessageType;
import com.skype.smsmanager.nativesms.models.CellularMessagesMap;
import com.skype.smsmanager.nativesms.models.MmsGetRecipientsInfo;
import com.skype.smsmanager.nativesms.models.MmsMediaItem;
import com.skype.smsmanager.nativesms.receivers.SmsRelayServiceIntentReceiver;
import com.skype.smsmanager.nativesms.utils.NativeTelemetryUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public final class AddCellularMessages {
    private static final String[] g = new String[]{"_id", "address", "body", "date", "type", "creator"};
    private static final String[] h = new String[]{"_id", "date", "text_only", "m_type", "creator", "m_id"};
    private final String a = "AddCellularMessages";
    private final Context b;
    private AtomicLong c;
    private AtomicLong d;
    private AtomicLong e;
    private AtomicLong f;

    public AddCellularMessages(Context context) {
        this.b = context;
        long currentTimeMillis = System.currentTimeMillis();
        this.c = new AtomicLong(currentTimeMillis);
        this.d = new AtomicLong(currentTimeMillis);
        this.f = new AtomicLong(-1);
        this.e = new AtomicLong(-1);
    }

    public final void a(CellularMessagesMap cellularMessagesMap) {
        a(cellularMessagesMap, this.c, this.f);
    }

    public final ArrayList<Pair<Long, Long>> a(CellularMessagesMap cellularMessagesMap, AtomicLong lastProcessedSms, AtomicLong lastProcessedSmsDbKey) {
        ArrayList<Pair<Long, Long>> processedDbKeys = new ArrayList();
        Cursor smsCur = null;
        try {
            smsCur = this.b.getContentResolver().query(Sms.CONTENT_URI, g, "date > ?", new String[]{Long.toString(Long.valueOf(lastProcessedSms.get()).longValue())}, "_id");
        } catch (Throwable e) {
            SmsMmsLogger.a("AddCellularMessages", "AddSmsMessage() Unable to run content resolver query", e);
        }
        if (smsCur == null) {
            SmsMmsLogger.a("AddCellularMessages", "AddSmsMessage() smsCur == null");
        } else {
            try {
                SmsMmsLogger.a("AddCellularMessages", "AddSmsMessage() Current Sms count: " + smsCur.getCount() + " stored lastSmsTs: " + lastProcessedSms.get());
                while (smsCur.moveToNext()) {
                    int type = Integer.parseInt(smsCur.getString(smsCur.getColumnIndex("type")));
                    long dbKey = (long) smsCur.getInt(smsCur.getColumnIndex("_id"));
                    Long lastSmsTs = Long.valueOf(Long.parseLong(smsCur.getString(smsCur.getColumnIndex("date"))));
                    lastProcessedSms.set(lastSmsTs.longValue());
                    String creator = smsCur.getString(smsCur.getColumnIndex("creator"));
                    boolean isIncoming = type == 1;
                    if (a(isIncoming, creator, dbKey, lastProcessedSmsDbKey, true)) {
                        SmsMmsLogger.a("AddCellularMessages", "AddSmsMessage() type: " + type + " dbKey: " + dbKey + " creator: " + creator + " lastProcessedSmsDbKey:" + lastProcessedSmsDbKey + " packageName:" + this.b.getPackageName() + " SKIPPED");
                        cellularMessagesMap.a("sms-" + String.valueOf(dbKey), CellularMessageType.SmsMessage);
                        processedDbKeys.add(new Pair(Long.valueOf(dbKey), lastSmsTs));
                    } else {
                        SmsMmsLogger.a("AddCellularMessages", "AddSmsMessage() _id  = " + dbKey + " lastMmsTsFromDb = " + lastSmsTs + " creator: " + creator + " package: " + this.b.getPackageName());
                        String messageText = smsCur.getString(smsCur.getColumnIndex("body"));
                        String otherPartyAddress = smsCur.getString(smsCur.getColumnIndex("address"));
                        Intent intent = new Intent(this.b, SmsRelayServiceIntentReceiver.class);
                        intent.setAction("ACTION_SMSMMS_SERVICE");
                        intent.putExtra("IntentType", IntentType.INTENT_SMS.a());
                        intent.putExtra("Body", messageText);
                        intent.putExtra(isIncoming ? "Sender" : "Recipient", otherPartyAddress);
                        intent.putExtra("TimestampMs", lastSmsTs);
                        String cellularMessageId = "sms-" + String.valueOf(dbKey);
                        intent.putExtra("dbId", cellularMessageId);
                        intent.putExtra("SmsIntentTime", (double) System.currentTimeMillis());
                        SmsMmsLogger.a("AddCellularMessages", "Adding SMS message " + cellularMessageId);
                        cellularMessagesMap.a(cellularMessageId, CellularMessageType.SmsMessage);
                        this.b.sendBroadcast(intent);
                        processedDbKeys.add(new Pair(Long.valueOf(dbKey), lastSmsTs));
                    }
                }
                if (smsCur != null) {
                    smsCur.close();
                }
            } catch (Throwable th) {
                if (smsCur != null) {
                    smsCur.close();
                }
            }
        }
        return processedDbKeys;
    }

    public final void a(CellularMessagesMap cellularMessagesMap, String selfPhoneNumber) {
        a(cellularMessagesMap, selfPhoneNumber, this.d, this.e);
    }

    public final ArrayList<Pair<Long, Long>> a(CellularMessagesMap cellularMessagesMap, String selfPhoneNumber, AtomicLong lastProcessedMms, AtomicLong lastProcessedMmsDbKey) {
        ArrayList<Pair<Long, Long>> processedDbKeysTsPairs = new ArrayList();
        ArrayList<HashMap<String, Object>> brickedMsgTelemetry = new ArrayList();
        Cursor mmsCur = null;
        try {
            mmsCur = this.b.getContentResolver().query(Mms.CONTENT_URI, h, "date > ?", new String[]{Long.toString(Long.valueOf(lastProcessedMms.get()).longValue() / 1000)}, "_id");
        } catch (Throwable e) {
            SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() Unable to run content resolver query", e);
        }
        if (mmsCur == null) {
            SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() Empty cursor, no rows found");
        } else {
            SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() Current Mms count:" + mmsCur.getCount() + " stored lastMmsTs:" + lastProcessedMms.get());
            while (mmsCur.moveToNext()) {
                String messageId = mmsCur.getString(mmsCur.getColumnIndex("m_id"));
                Long lastMmsTs = Long.valueOf(mmsCur.getLong(mmsCur.getColumnIndex("date")) * 1000);
                Long ignoreBrickedSmsMsgsTimeout = Long.valueOf(this.b.getSharedPreferences("NATIVE_SMS_PREFS", 0).getLong("NATIVE_SMS_IGNORE_BRICKED_MSG_TIMEOUT", -1));
                Long ignoreBrickedSmsMsgsTs = Long.valueOf(System.currentTimeMillis() - ignoreBrickedSmsMsgsTimeout.longValue());
                SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() ignoreBrickedSmsMsgsTs:" + ignoreBrickedSmsMsgsTs + " lastMmsTs:" + lastMmsTs);
                boolean isBrickedMessage = ignoreBrickedSmsMsgsTimeout.longValue() != -1 && ignoreBrickedSmsMsgsTs.longValue() > lastMmsTs.longValue();
                if (messageId != null) {
                    try {
                        long dbKey = mmsCur.getLong(mmsCur.getColumnIndex("_id"));
                        boolean textOnly = mmsCur.getInt(mmsCur.getColumnIndex("text_only")) == 1;
                        String creator = mmsCur.getString(mmsCur.getColumnIndex("creator"));
                        int type = Integer.parseInt(mmsCur.getString(mmsCur.getColumnIndex("m_type")));
                        boolean isIncoming = type != 128;
                        lastProcessedMms.set(lastMmsTs.longValue());
                        if (a(isIncoming, creator, dbKey, lastProcessedMmsDbKey, false)) {
                            SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() _id  = " + dbKey + " creator:" + creator + " lastProcessedMmsDbKey" + lastProcessedMmsDbKey + " SKIPPED");
                            cellularMessagesMap.a("mms-" + String.valueOf(dbKey), CellularMessageType.MmsMessage);
                            processedDbKeysTsPairs.add(new Pair(Long.valueOf(dbKey), lastMmsTs));
                        } else {
                            SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() _id  = " + dbKey + " lastMmsTsFromDb = " + lastMmsTs + " textOnly = " + textOnly + " type: " + type + " isIncoming: " + isIncoming + " creator: " + creator + " package: " + this.b.getPackageName());
                            MessagePartExtractor messagePartExtractor = new MessagePartExtractor(this.b.getContentResolver(), this.b.getCacheDir());
                            String mmsText = messagePartExtractor.a(dbKey);
                            if (mmsText != null || !textOnly) {
                                String sender = a(dbKey, isIncoming);
                                if (sender != null) {
                                    MmsGetRecipientsInfo recipientsInfo = a(dbKey, selfPhoneNumber);
                                    ArrayList<MmsMediaItem> mediaItems = messagePartExtractor.b(dbKey);
                                    SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() message #" + dbKey + " has " + mediaItems.size() + " media items.");
                                    SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() dbKey: " + dbKey);
                                    Intent intent = new Intent(this.b, SmsRelayServiceIntentReceiver.class);
                                    intent.setAction("ACTION_SMSMMS_SERVICE");
                                    intent.putExtra("IntentType", IntentType.INTENT_MMS.a());
                                    intent.putExtra("Body", mmsText);
                                    intent.putExtra("Sender", sender);
                                    intent.putStringArrayListExtra("Recipients", recipientsInfo.a());
                                    intent.putExtra("TimestampMs", lastMmsTs);
                                    intent.putParcelableArrayListExtra("Media", mediaItems);
                                    String cellularMessageId = "mms-" + dbKey;
                                    intent.putExtra("dbId", cellularMessageId);
                                    intent.putExtra("SmsIntentTime", (double) System.currentTimeMillis());
                                    intent.putExtra("GetMmsRecipientsIterations", recipientsInfo.b());
                                    SmsMmsLogger.a("AddCellularMessages", "Adding MMS message " + cellularMessageId);
                                    cellularMessagesMap.a(cellularMessageId, CellularMessageType.MmsMessage);
                                    this.b.sendBroadcast(intent);
                                    processedDbKeysTsPairs.add(new Pair(Long.valueOf(dbKey), lastMmsTs));
                                } else if (isBrickedMessage) {
                                    SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() Bricked Message - sender == null");
                                    brickedMsgTelemetry.add(NativeTelemetryUtils.a(messageId, mmsText, sender, textOnly, isIncoming));
                                } else {
                                    SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() sender is empty. Wait for Db write to complete");
                                    if (mmsCur != null) {
                                        mmsCur.close();
                                    }
                                }
                            } else if (isBrickedMessage) {
                                SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() Bricked Message - mmsText == null");
                                brickedMsgTelemetry.add(NativeTelemetryUtils.a(messageId, mmsText, a(dbKey, isIncoming), textOnly, isIncoming));
                            } else {
                                SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() mmsText == null");
                                if (mmsCur != null) {
                                    mmsCur.close();
                                }
                            }
                        }
                    } finally {
                        if (mmsCur != null) {
                            mmsCur.close();
                        }
                    }
                } else if (isBrickedMessage) {
                    SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() Bricked Message - messageId is not set");
                    Long valueOf = Long.valueOf(mmsCur.getLong(mmsCur.getColumnIndex("_id")));
                    boolean z = mmsCur.getInt(mmsCur.getColumnIndex("text_only")) == 1;
                    String a = new MessagePartExtractor(this.b.getContentResolver(), this.b.getCacheDir()).a(valueOf.longValue());
                    boolean z2 = Integer.parseInt(mmsCur.getString(mmsCur.getColumnIndex("m_type"))) != 128;
                    brickedMsgTelemetry.add(NativeTelemetryUtils.a(messageId, a, a(valueOf.longValue(), z2), z, z2));
                } else {
                    SmsMmsLogger.a("AddCellularMessages", "AddMmsMessage() messageId is not set. Skipping since participants might not be set");
                    if (mmsCur != null) {
                        mmsCur.close();
                    }
                }
            }
            NativeTelemetryUtils.a(this.b, brickedMsgTelemetry);
        }
        return processedDbKeysTsPairs;
    }

    private MmsGetRecipientsInfo a(long _id, String selfPhoneNumber) {
        ArrayList<String> recipients;
        ArrayList arrayList = new ArrayList();
        int iterations = 0;
        int initialRecipientsCount = b(_id, selfPhoneNumber).size();
        SmsMmsLogger.a("AddCellularMessages", "getMmsRecipientsAddresses() initial query to get recipients:" + initialRecipientsCount);
        boolean recipientCountChanged;
        do {
            iterations++;
            recipients = b(_id, selfPhoneNumber);
            recipientCountChanged = recipients.size() != initialRecipientsCount;
            initialRecipientsCount = recipients.size();
            SmsMmsLogger.a("AddCellularMessages", "getMmsRecipientsAddresses() recipientCountChanged: " + recipientCountChanged + " recipientCount: " + recipients.size());
        } while (recipientCountChanged);
        return new MmsGetRecipientsInfo(recipients, iterations);
    }

    private ArrayList<String> b(long _id, String selfPhoneNumber) {
        ArrayList<String> recipients = new ArrayList();
        Cursor addrCur = null;
        try {
            addrCur = this.b.getContentResolver().query(Uri.parse("content://mms/" + _id + "/addr"), null, "type=151", null, "_id");
            if (addrCur == null || !addrCur.moveToLast()) {
                if (addrCur != null) {
                    addrCur.close();
                }
                return recipients;
            }
            do {
                String recipient = addrCur.getString(addrCur.getColumnIndex("address"));
                SmsMmsLogger.a("AddCellularMessages", "getMmsRecipientsAddressesInternal() phone length: " + recipient.length());
                SmsMmsLogger.b("AddCellularMessages", "getMmsRecipientsAddressesInternal() phone: " + recipient);
                if (selfPhoneNumber.endsWith(recipient)) {
                    SmsMmsLogger.a("AddCellularMessages", "getMmsRecipientsAddressesInternal() skip self phone");
                } else {
                    SmsMmsLogger.a("AddCellularMessages", "getMmsRecipientsAddressesInternal() adding recipient:" + recipient);
                    recipients.add(recipient);
                }
            } while (addrCur.moveToPrevious());
            if (addrCur != null) {
                addrCur.close();
            }
            return recipients;
        } catch (Throwable th) {
            if (addrCur != null) {
                addrCur.close();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private String a(long _id, boolean isIncoming) {
        if (!isIncoming) {
            return "";
        }
        Cursor addrCur = null;
        try {
            addrCur = this.b.getContentResolver().query(Uri.parse("content://mms/" + _id + "/addr"), null, "type=137", null, "_id");
            if (addrCur != null && addrCur.moveToLast()) {
                String senderNumber = addrCur.getString(addrCur.getColumnIndex("address"));
                SmsMmsLogger.b("AddCellularMessages", "getMmsSenderAddresses() addr: " + senderNumber);
                String sender = senderNumber;
            }
            if (addrCur == null) {
                return null;
            }
            addrCur.close();
        } catch (Throwable th) {
            if (addrCur != null) {
                addrCur.close();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(boolean isIncoming, String creator, long dbKey, AtomicLong lastProcessedDbKey, boolean isSms) {
        boolean isCreatorSkype;
        if (isSms) {
            if (creator != null) {
                if (creator.equalsIgnoreCase(this.b.getPackageName())) {
                    isCreatorSkype = true;
                }
            }
            isCreatorSkype = false;
        } else {
            if (creator != null) {
                if (!creator.equalsIgnoreCase(this.b.getPackageName())) {
                }
                isCreatorSkype = true;
            }
            isCreatorSkype = false;
        }
        boolean isRelayOrReverseRelayFromSkype = !isIncoming && isCreatorSkype;
        SmsMmsLogger.b("AddCellularMessages", String.format("skipProcessingMessage() - isSms:%b, creator:%s, isIncoming:%b, isCreatorSkype:%b, packagename:%s", new Object[]{Boolean.valueOf(isSms), creator, Boolean.valueOf(isIncoming), Boolean.valueOf(isCreatorSkype), this.b.getPackageName()}));
        if (isRelayOrReverseRelayFromSkype) {
            return true;
        }
        boolean alreadyProcessedDbKey = lastProcessedDbKey.getAndSet(dbKey) == dbKey;
        boolean lastDeletedDbKeyMatchesDbKey = false;
        String lastDeletedMsgDbKey = this.b.getSharedPreferences("NATIVE_SMS_PREFS", 0).getString("NATIVE_SMS_LAST_DELETED_SMS_DBKEY", null);
        if (lastDeletedMsgDbKey != null) {
            boolean equalsIgnoreCase;
            int index = lastDeletedMsgDbKey.indexOf(45);
            String msgType = lastDeletedMsgDbKey.substring(0, index);
            long lastDeletedDbKey = Long.parseLong(lastDeletedMsgDbKey.substring(index + 1, lastDeletedMsgDbKey.length()));
            if (isSms) {
                equalsIgnoreCase = msgType.equalsIgnoreCase("sms");
            } else {
                equalsIgnoreCase = msgType.equalsIgnoreCase("mms");
            }
            lastDeletedDbKeyMatchesDbKey = equalsIgnoreCase && lastDeletedDbKey == dbKey;
        }
        if (!alreadyProcessedDbKey || lastDeletedDbKeyMatchesDbKey) {
            return false;
        }
        return true;
    }
}
