package com.skype.smsmanager.nativesms;

import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobInfo.TriggerContentUri;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Telephony.MmsSms;
import android.provider.Telephony.Sms;
import android.support.v4.content.a;
import android.telephony.SmsManager;
import com.brentvatne.react.ReactVideoViewManager;
import com.skype.smsmanager.nativesms.SmsManagerConstants.IntentType;
import com.skype.smsmanager.nativesms.models.DeletedCellularMessagesItem;
import com.skype.smsmanager.nativesms.models.LoggerStrategy;
import com.skype.smsmanager.nativesms.models.MessageListener;
import com.skype.smsmanager.nativesms.models.MmsMessageItem;
import com.skype.smsmanager.nativesms.models.OutgoingMessageStatusImpl;
import com.skype.smsmanager.nativesms.models.OutgoingSmsMessageImpl;
import com.skype.smsmanager.nativesms.models.SmsMessageItem;
import com.skype.smsmanager.nativesms.receivers.SmsRelayBroadcastReceiver;
import com.skype.smsmanager.nativesms.receivers.SmsRelayServiceIntentReceiver;
import com.skype.smsmanager.nativesms.services.SmsMmsObserverService;
import com.skype.smsmanager.nativesms.services.SmsObserverService;
import com.skype.smsmanager.nativesms.services.SmsRelayCoordinator;
import com.skype.smsmanager.nativesms.services.SmsRelayService;
import com.skype.smsmanager.nativesms.utils.MmsUtils;
import com.skype.smsmanager.nativesms.utils.PhoneUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SmsMmsManager implements SmsManagerConstants {
    private static CopyOnWriteArrayList<MessageListener> a = new CopyOnWriteArrayList();
    private static boolean f = false;
    private final SmsManager b = SmsManager.getDefault();
    private final Context c;
    private final MmsUtils d;
    private final AtomicInteger e;

    public SmsMmsManager(Context context, LoggerStrategy loggerStrategy) {
        SmsMmsLogger.a(loggerStrategy);
        SmsMmsLogger.a("SmsMmsManager", "SmsMmsManager Initialized.");
        this.c = context;
        this.d = new MmsUtils(context);
        SharedPreferences pref = context.getSharedPreferences("NATIVE_SMS_PREFS", 0);
        f = true;
        a(pref.getBoolean("NATIVE_SMS_ENABLED_RECEIVERS", false));
        this.e = new AtomicInteger();
    }

    public final void a(OutgoingSmsMessageImpl outgoingSmsMessage) {
        ArrayList<String> smsMessageItemParts = this.b.divideMessage(outgoingSmsMessage.c());
        if (smsMessageItemParts.size() == 1) {
            try {
                this.b.sendTextMessage(outgoingSmsMessage.a(), null, outgoingSmsMessage.c(), b(outgoingSmsMessage), null);
                return;
            } catch (Throwable e) {
                SmsMmsLogger.a("SmsMmsManager", "Could not send SMS message: ", e);
                return;
            }
        }
        ArrayList arrayList = new ArrayList(smsMessageItemParts.size());
        for (int i = 0; i < smsMessageItemParts.size(); i++) {
            arrayList.add(b(outgoingSmsMessage));
        }
        try {
            this.b.sendMultipartTextMessage(outgoingSmsMessage.a(), null, smsMessageItemParts, arrayList, null);
        } catch (Throwable e2) {
            SmsMmsLogger.a("SmsMmsManager", "Could not send multi-part SMS message: ", e2);
        }
    }

    public final void a(String[] recipients, String body, String cuid) {
        this.d.a(body, PhoneUtils.a(this.c, "SmsMmsManager", "sendTextMessage"), recipients, cuid);
    }

    public final void a(String selfPhone) {
        this.c.getSharedPreferences("NATIVE_SMS_PREFS", 0).edit().putString("NATIVE_SMS_SELF_NUMBER", selfPhone).apply();
        Intent intent = new Intent(this.c, SmsRelayService.class);
        intent.putExtra("SELF_PHONE_NUMBER", selfPhone);
        b(intent);
    }

    public final void a() {
        this.c.getSharedPreferences("NATIVE_SMS_PREFS", 0).edit().remove("NATIVE_SMS_SELF_NUMBER").apply();
    }

    public final void b() {
        String[] strArr = new String[]{"NATIVE_SMS_LAST_SMS_MSG_COUNT", "NATIVE_SMS_LAST_PROCESSED_SMS_DBKEY", "NATIVE_SMS_LAST_PROCESSED_SMS_TS", "NATIVE_SMS_LAST_MMS_MSG_COUNT", "NATIVE_SMS_LAST_PROCESSED_MMS_DBKEY", "NATIVE_SMS_LAST_PROCESSED_MMS_TS", "NATIVE_SMS_IGNORE_BRICKED_MSG_TIMEOUT"};
        for (int i = 0; i < 7; i++) {
            this.c.getSharedPreferences("NATIVE_SMS_PREFS", 0).edit().remove(strArr[i]).apply();
        }
    }

    public final void a(boolean enable) {
        if (a.a(this.c, "android.permission.READ_SMS") != 0) {
            SmsMmsLogger.a("SmsMmsManager", "Permissions are not granted");
            c(new Intent(this.c, SmsRelayService.class));
            SmsRelayCoordinator.a();
            return;
        }
        SmsMmsLogger.a("SmsMmsManager", "enableBroadcastReceivers enable:" + enable);
        PackageManager pm = this.c.getPackageManager();
        int state = enable ? 1 : 2;
        pm.setComponentEnabledSetting(new ComponentName(this.c, SmsRelayServiceIntentReceiver.class), state, 1);
        pm.setComponentEnabledSetting(new ComponentName(this.c, SmsRelayBroadcastReceiver.class), state, 1);
        Intent intent = new Intent(this.c, SmsRelayService.class);
        if (enable) {
            b(intent);
        } else {
            c(intent);
        }
        this.c.getSharedPreferences("NATIVE_SMS_PREFS", 0).edit().putBoolean("NATIVE_SMS_ENABLED_RECEIVERS", enable).apply();
    }

    private void b(Intent intent) {
        a(true, intent, false);
        a(false, intent, true);
    }

    private void a(boolean isSms, Intent intent, boolean alreadyInitialized) {
        if (VERSION.SDK_INT >= 26) {
            SmsMmsLogger.a("SmsMmsManager", String.format("Running Oreo+ path-isSms:%b", new Object[]{Boolean.valueOf(isSms)}));
            if (!alreadyInitialized) {
                SmsMmsLogger.b("SmsMmsManager", "Using Application");
                SmsRelayCoordinator.a(this.c);
            }
            Uri contentUri = isSms ? Sms.CONTENT_URI : MmsSms.CONTENT_URI;
            Class classToUse = isSms ? SmsObserverService.class : SmsMmsObserverService.class;
            JobScheduler jobScheduler = (JobScheduler) this.c.getSystemService("jobscheduler");
            SmsMmsLogger.b("SmsMmsManager", String.format("Scheduling job isSms:%b jobId:%d", new Object[]{Boolean.valueOf(isSms), Integer.valueOf(b(isSms))}));
            Builder jobInfoBuilder = new Builder(jobId, new ComponentName(this.c, classToUse));
            jobInfoBuilder.addTriggerContentUri(new TriggerContentUri(contentUri, 1));
            jobInfoBuilder.setTriggerContentMaxDelay(0);
            int result = jobScheduler.schedule(jobInfoBuilder.build());
            for (JobInfo job : jobScheduler.getAllPendingJobs()) {
                SmsMmsLogger.a("SmsMmsManager", String.format("JobId:%d ClassName:%s", new Object[]{Integer.valueOf(job.getId()), job.getService().getClassName()}));
            }
            SmsMmsLogger.a("SmsMmsManager", String.format("Scheduled Sms/Mms Observer jobs - isSms:%b, result:%d", new Object[]{Boolean.valueOf(isSms), Integer.valueOf(result)}));
        } else if (!alreadyInitialized) {
            this.c.startService(intent);
        }
    }

    private static int b(boolean isSms) {
        return (isSms ? "SmsObserverService" : "SmsMmsObserverService").hashCode();
    }

    private void c(Intent intent) {
        if (VERSION.SDK_INT >= 26) {
            SmsMmsLogger.a("SmsMmsManager", "Cancelling Sms/Mms Observer schedules");
            JobScheduler scheduler = (JobScheduler) this.c.getSystemService("jobscheduler");
            scheduler.cancel(b(true));
            SmsMmsLogger.a("SmsMmsManager", String.format("Cancelled JobId:%d", new Object[]{Integer.valueOf(jobIdToCancel)}));
            scheduler.cancel(b(false));
            SmsMmsLogger.a("SmsMmsManager", String.format("Cancelled JobId:%d", new Object[]{Integer.valueOf(jobIdToCancel)}));
            return;
        }
        this.c.stopService(intent);
    }

    private PendingIntent b(OutgoingSmsMessageImpl smsMessageItem) {
        Intent intent = new Intent(this.c, SmsRelayServiceIntentReceiver.class);
        intent.setAction("ACTION_SMSMMS_STATUS");
        Bundle bundle = new Bundle();
        bundle.putInt("IntentType", IntentType.INTENT_OUTGOING_SMS_STATUS.a());
        bundle.putString("cuid", smsMessageItem.b());
        intent.putExtras(bundle);
        return PendingIntent.getBroadcast(this.c, this.e.incrementAndGet(), intent, 134217728);
    }

    public static void a(MessageListener listener) {
        a.add(listener);
    }

    public static void c() {
        a.clear();
    }

    public static void a(Context context, Intent intent) {
        int type = intent.getIntExtra("IntentType", IntentType.UNKNOWN.a());
        SmsMmsLogger.a("SmsMmsManager", "Intent type: " + type);
        switch (IntentType.a(type)) {
            case INTENT_SMS:
                c(context, intent);
                return;
            case INTENT_MMS:
                b(context, intent);
                return;
            case INTENT_OUTGOING_SMS_STATUS:
                d(intent);
                return;
            case INTENT_OUTGOING_MMS_STATUS:
                d(context, intent);
                return;
            case INTENT_DELETED_CELLULAR_MESSAGE:
                e(intent);
                return;
            default:
                return;
        }
    }

    private static void b(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            SmsMmsLogger.a("SmsMmsManager", "processMms - null bundle");
            return;
        }
        MmsMessageItem mmsMessageItem = new MmsMessageItem(bundle);
        SmsMmsLogger.a("SmsMmsManager", "processMms()");
        Iterator it = a.iterator();
        while (it.hasNext()) {
            MessageListener listener = (MessageListener) it.next();
            SmsMmsLogger.a("SmsMmsManager", "Listener invoked for processMms");
            listener.a(context, mmsMessageItem);
        }
    }

    private static void c(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            SmsMmsLogger.a("SmsMmsManager", "processSms - null bundle");
            return;
        }
        SmsMessageItem incomingSmsMessage = new SmsMessageItem(bundle);
        SmsMmsLogger.a("SmsMmsManager", "incomingSms()");
        Iterator it = a.iterator();
        while (it.hasNext()) {
            MessageListener listener = (MessageListener) it.next();
            SmsMmsLogger.a("SmsMmsManager", "Listener invoked for incomingSms");
            listener.a(context, incomingSmsMessage);
        }
    }

    private static void d(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            SmsMmsLogger.a("SmsMmsManager", "outgoingMmsStatus - null bundle");
            return;
        }
        boolean successful = MmsUtils.a(context, bundle);
        OutgoingMessageStatusImpl outgoingMmsStatusEvent = new OutgoingMessageStatusImpl(bundle.getString("cuid", ""), a(bundle, false), successful, true);
        SmsMmsLogger.a("SmsMmsManager", "outgoingMmsStatus() cuid: " + outgoingMmsStatusEvent.c() + " cellularMessageId: " + outgoingMmsStatusEvent.d() + " successful: " + outgoingMmsStatusEvent.b());
        Iterator it = a.iterator();
        while (it.hasNext()) {
            MessageListener listener = (MessageListener) it.next();
            SmsMmsLogger.a("SmsMmsManager", "Listener invoked for outgoingMmsStatus");
            listener.a(outgoingMmsStatusEvent);
        }
    }

    private static void d(Intent intent) {
        boolean z = true;
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            SmsMmsLogger.a("SmsMmsManager", "outgoingSmsStatus - null bundle");
            return;
        }
        String cellularMessageId = a(bundle, true);
        String string = bundle.getString("cuid", "");
        if (bundle.getInt("outgoingSmsStatus") != -1) {
            z = false;
        }
        OutgoingMessageStatusImpl outgoingSmsStatus = new OutgoingMessageStatusImpl(string, cellularMessageId, z, false);
        SmsMmsLogger.a("SmsMmsManager", "outgoingSmsStatus() cuid: " + outgoingSmsStatus.c() + " cellulrMessageId: " + outgoingSmsStatus.d() + " successful: " + outgoingSmsStatus.b());
        Iterator it = a.iterator();
        while (it.hasNext()) {
            MessageListener listener = (MessageListener) it.next();
            SmsMmsLogger.a("SmsMmsManager", "Listener invoked for outgoingSmsStatus");
            listener.a(outgoingSmsStatus);
        }
    }

    private static void e(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            SmsMmsLogger.a("SmsMmsManager", "processDeletedCellularMessage - null bundle");
            return;
        }
        DeletedCellularMessagesItem deletedCellularMessagesItem = new DeletedCellularMessagesItem(bundle);
        SmsMmsLogger.a("SmsMmsManager", "processDeletedCellularMessage() Count of deleted messages: " + deletedCellularMessagesItem.a().size());
        Iterator it = a.iterator();
        while (it.hasNext()) {
            MessageListener listener = (MessageListener) it.next();
            SmsMmsLogger.a("SmsMmsManager", "Listener invoked for processDeletedCellularMessage");
            listener.a(deletedCellularMessagesItem);
        }
    }

    public static boolean d() {
        return f;
    }

    public final void a(int timeout) {
        Long newTimeout = Long.valueOf((long) timeout);
        Long origTimeout = Long.valueOf(this.c.getSharedPreferences("NATIVE_SMS_PREFS", 0).getLong("NATIVE_SMS_IGNORE_BRICKED_MSG_TIMEOUT", -1));
        if ((origTimeout.longValue() != -1 ? 1 : 0) == 0 || !origTimeout.equals(newTimeout)) {
            this.c.getSharedPreferences("NATIVE_SMS_PREFS", 0).edit().putLong("NATIVE_SMS_IGNORE_BRICKED_MSG_TIMEOUT", newTimeout.longValue()).apply();
            SmsMmsLogger.a("SmsMmsManager", "setIgnoreBrickedSmsMessagesTimeout() - set timeout value from: " + origTimeout + " to " + newTimeout);
        }
    }

    public static void a(Intent intent) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            MessageListener listener = (MessageListener) it.next();
            ArrayList telemetryInfos = (ArrayList) intent.getSerializableExtra("TelemetryInfos");
            if (telemetryInfos.size() > 0) {
                SmsMmsLogger.a("SmsMmsManager", "Listener invoked for sendNativeTelemetryInfo");
                listener.a(telemetryInfos);
            }
        }
    }

    private static String a(Bundle bundle, boolean isSms) {
        String uri = bundle.getString(ReactVideoViewManager.PROP_SRC_URI, "");
        if (uri.isEmpty()) {
            SmsMmsLogger.a("SmsMmsManager", "getCellularMessageIdFromUri(): No URI in bundle");
            return uri;
        }
        List<String> paths = Uri.parse(uri).getPathSegments();
        return (isSms ? "sms-" : "mms-") + ((String) paths.get(paths.size() - 1));
    }
}
