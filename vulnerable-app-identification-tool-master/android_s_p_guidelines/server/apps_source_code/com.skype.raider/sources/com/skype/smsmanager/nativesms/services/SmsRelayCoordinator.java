package com.skype.smsmanager.nativesms.services;

import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobInfo.TriggerContentUri;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Telephony.MmsSms;
import android.provider.Telephony.Sms;
import android.util.Pair;
import com.microsoft.skype.a.a;
import com.microsoft.skype.a.a.b;
import com.skype.smsmanager.nativesms.SmsManagerConstants;
import com.skype.smsmanager.nativesms.SmsMmsLogger;
import com.skype.smsmanager.nativesms.SmsMmsManager;
import com.skype.smsmanager.nativesms.models.CellularMessagesMap;
import com.skype.smsmanager.nativesms.utils.PhoneUtils;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public final class SmsRelayCoordinator implements SmsManagerConstants {
    private static CellularMessagesMap a;
    private static final a b = a.a("SmsRelayCoordinator", b.DEFAULT);
    private static boolean c = false;
    private static String d;

    static /* synthetic */ void c(Context x0) {
        if (SmsMmsManager.d()) {
            ArrayList a = new GetCellularMessages(x0).a();
            int a2 = a(x0, true);
            AtomicLong b = b(x0, true);
            AtomicLong c = c(x0, true);
            SmsMmsLogger.a("SmsRelayCoordinator", "currentSmsMessages size:" + a.size() + ", lastStoredSmsMessageCount:" + a2 + ", lastProcessedSmsMsgTs:" + b + ", lastProcessedSmsDbKey:" + c);
            if (b.get() == 0) {
                b = new AtomicLong(System.currentTimeMillis() - 5000);
                SmsMmsLogger.a("SmsRelayCoordinator", "lastProcessedSmsMsgTs is zero. Setting it to current ts");
                x0.getSharedPreferences("NATIVE_SMS_PREFS", 0).edit().putLong("NATIVE_SMS_LAST_PROCESSED_SMS_TS", b.get()).apply();
            }
            if (a.size() > a2) {
                ArrayList a3 = new AddCellularMessages(x0).a(a, b, c);
                int size = a3.size();
                if (size > 0) {
                    SmsMmsLogger.a("SmsRelayCoordinator", "Setting last SMS message info in the SharedPrefs after add SMS. Count:" + size);
                    a(x0, true, a2 == 0 ? a.size() : a2 + size, ((Long) ((Pair) a3.get(size - 1)).second).longValue(), ((Long) ((Pair) a3.get(size - 1)).first).longValue(), null);
                    return;
                }
                return;
            } else if (a.size() < a2) {
                Long valueOf = Long.valueOf(System.currentTimeMillis());
                ArrayList a4 = new DeleteCellularMessages(x0).a(a, a);
                if (a4.size() > 0) {
                    SmsMmsLogger.a("SmsRelayCoordinator", "Setting last SMS message info in the SharedPrefs after delete SMS");
                    a(x0, true, a.size(), valueOf.longValue(), -1, (String) a4.get(a4.size() - 1));
                    return;
                }
                return;
            } else {
                return;
            }
        }
        SmsMmsLogger.a("SmsRelayCoordinator", "SmsMmsManager is not initialized - not processing SMS message");
    }

    public static void a(final Context context) {
        SmsMmsLogger.a("SmsRelayCoordinator", "Initializing SmsRelayCoordinator");
        a = new GetCellularMessages(context).c();
        d = PhoneUtils.a(context, "SmsRelayCoordinator", "initialize");
        SmsMmsLogger.b("SmsRelayCoordinator", "selfPhoneNumber:" + d);
        SmsMmsLogger.a("SmsRelayCoordinator", "Processing Missed Messages");
        b.b(new Runnable() {
            public final void run() {
                SmsRelayCoordinator.c(context);
                SmsRelayCoordinator.b(context);
            }
        });
        c = true;
    }

    public static void a(final boolean isSms, final Context context) {
        SmsMmsLogger.a("SmsRelayCoordinator", "Received new Cellular Message - isSms:" + isSms + ",isInitialized :" + c);
        if (!c) {
            a(context);
        }
        b.a(new Runnable() {
            public final void run() {
                if (isSms) {
                    SmsRelayCoordinator.c(context);
                } else {
                    SmsRelayCoordinator.b(context);
                }
            }
        });
    }

    public static void b(android.content.Context r25) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r21_1 'lastProcessedMmsMsgTs' java.util.concurrent.atomic.AtomicLong) in PHI: PHI: (r21_2 'lastProcessedMmsMsgTs' java.util.concurrent.atomic.AtomicLong) = (r21_0 'lastProcessedMmsMsgTs' java.util.concurrent.atomic.AtomicLong), (r21_1 'lastProcessedMmsMsgTs' java.util.concurrent.atomic.AtomicLong) binds: {(r21_0 'lastProcessedMmsMsgTs' java.util.concurrent.atomic.AtomicLong)=B:9:0x0086, (r21_1 'lastProcessedMmsMsgTs' java.util.concurrent.atomic.AtomicLong)=B:10:0x0088}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r3 = com.skype.smsmanager.nativesms.SmsMmsManager.d();
        if (r3 != 0) goto L_0x000e;
    L_0x0006:
        r3 = "SmsRelayCoordinator";
        r4 = "SmsMmsManager is not initialized - not processing MMS message";
        com.skype.smsmanager.nativesms.SmsMmsLogger.a(r3, r4);
    L_0x000d:
        return;
    L_0x000e:
        r3 = d;
        if (r3 == 0) goto L_0x001a;
    L_0x0012:
        r3 = d;
        r3 = r3.isEmpty();
        if (r3 == 0) goto L_0x0022;
    L_0x001a:
        r3 = "SmsRelayCoordinator";
        r4 = "Skipping MMS message processing, selfPhoneNumber is empty or null";
        com.skype.smsmanager.nativesms.SmsMmsLogger.a(r3, r4);
        goto L_0x000d;
    L_0x0022:
        r3 = new com.skype.smsmanager.nativesms.services.GetCellularMessages;
        r0 = r25;
        r3.<init>(r0);
        r2 = r3.b();
        r3 = 0;
        r0 = r25;
        r24 = a(r0, r3);
        r3 = 0;
        r0 = r25;
        r21 = b(r0, r3);
        r3 = 0;
        r0 = r25;
        r20 = c(r0, r3);
        r3 = "SmsRelayCoordinator";
        r4 = new java.lang.StringBuilder;
        r10 = "currentMmsMessages size:";
        r4.<init>(r10);
        r10 = r2.size();
        r4 = r4.append(r10);
        r10 = ", lastStoredMmsMessageCount:";
        r4 = r4.append(r10);
        r0 = r24;
        r4 = r4.append(r0);
        r10 = ", lastProcessedMmsMsgTs:";
        r4 = r4.append(r10);
        r0 = r21;
        r4 = r4.append(r0);
        r10 = ", lastProcessedMmsDbKey:";
        r4 = r4.append(r10);
        r0 = r20;
        r4 = r4.append(r0);
        r4 = r4.toString();
        com.skype.smsmanager.nativesms.SmsMmsLogger.a(r3, r4);
        r10 = r21.get();
        r12 = 0;
        r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r3 != 0) goto L_0x00b7;
    L_0x0088:
        r21 = new java.util.concurrent.atomic.AtomicLong;
        r10 = java.lang.System.currentTimeMillis();
        r12 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r10 = r10 - r12;
        r0 = r21;
        r0.<init>(r10);
        r3 = "SmsRelayCoordinator";
        r4 = "lastProcessedMmsMsgTs is zero. Setting it to current ts";
        com.skype.smsmanager.nativesms.SmsMmsLogger.a(r3, r4);
        r3 = "NATIVE_SMS_PREFS";
        r4 = 0;
        r0 = r25;
        r3 = r0.getSharedPreferences(r3, r4);
        r3 = r3.edit();
        r4 = "NATIVE_SMS_LAST_PROCESSED_MMS_TS";
        r10 = r21.get();
        r3 = r3.putLong(r4, r10);
        r3.apply();
    L_0x00b7:
        r3 = r2.size();
        r0 = r24;
        if (r3 <= r0) goto L_0x0124;
    L_0x00bf:
        r3 = new com.skype.smsmanager.nativesms.services.AddCellularMessages;
        r0 = r25;
        r3.<init>(r0);
        r4 = a;
        r10 = d;
        r0 = r21;
        r1 = r20;
        r22 = r3.a(r4, r10, r0, r1);
        r23 = r22.size();
        if (r23 <= 0) goto L_0x000d;
    L_0x00d8:
        r3 = "SmsRelayCoordinator";
        r4 = new java.lang.StringBuilder;
        r10 = "Setting last MMS message info in the SharedPrefs after add MMS. Count:";
        r4.<init>(r10);
        r0 = r23;
        r4 = r4.append(r0);
        r4 = r4.toString();
        com.skype.smsmanager.nativesms.SmsMmsLogger.a(r3, r4);
        if (r24 != 0) goto L_0x0121;
    L_0x00f0:
        r5 = r2.size();
    L_0x00f4:
        r3 = r23 + -1;
        r0 = r22;
        r3 = r0.get(r3);
        r3 = (android.util.Pair) r3;
        r3 = r3.second;
        r3 = (java.lang.Long) r3;
        r6 = r3.longValue();
        r3 = r23 + -1;
        r0 = r22;
        r3 = r0.get(r3);
        r3 = (android.util.Pair) r3;
        r3 = r3.first;
        r3 = (java.lang.Long) r3;
        r8 = r3.longValue();
        r4 = 0;
        r10 = 0;
        r3 = r25;
        a(r3, r4, r5, r6, r8, r10);
        goto L_0x000d;
    L_0x0121:
        r5 = r24 + r23;
        goto L_0x00f4;
    L_0x0124:
        r3 = r2.size();
        r0 = r24;
        if (r3 >= r0) goto L_0x000d;
    L_0x012c:
        r10 = java.lang.System.currentTimeMillis();
        r6 = java.lang.Long.valueOf(r10);
        r3 = new com.skype.smsmanager.nativesms.services.DeleteCellularMessages;
        r0 = r25;
        r3.<init>(r0);
        r4 = a;
        r19 = r3.b(r4, r2);
        r3 = r19.size();
        if (r3 <= 0) goto L_0x000d;
    L_0x0147:
        r3 = "SmsRelayCoordinator";
        r4 = "Setting last MMS message info in the SharedPrefs after delete MMS";
        com.skype.smsmanager.nativesms.SmsMmsLogger.a(r3, r4);
        r3 = r19.size();
        r3 = r3 + -1;
        r0 = r19;
        r18 = r0.get(r3);
        r18 = (java.lang.String) r18;
        r12 = 0;
        r13 = r2.size();
        r14 = r6.longValue();
        r16 = -1;
        r11 = r25;
        a(r11, r12, r13, r14, r16, r18);
        goto L_0x000d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.skype.smsmanager.nativesms.services.SmsRelayCoordinator.b(android.content.Context):void");
    }

    public static void b(boolean isSms, Context context) {
        if (VERSION.SDK_INT < 26) {
            SmsMmsLogger.a("SmsRelayCoordinator", "Skipping rescheduleJobInfo.");
            return;
        }
        Uri contentUri = isSms ? Sms.CONTENT_URI : MmsSms.CONTENT_URI;
        Class classToUse = isSms ? SmsObserverService.class : SmsMmsObserverService.class;
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        int jobId = (isSms ? "SmsObserverService" : "SmsMmsObserverService").hashCode();
        SmsMmsLogger.b("SmsRelayCoordinator", "Scheduling job isSms:" + isSms + " jobId:" + jobId);
        Builder jobInfoBuilder = new Builder(jobId, new ComponentName(context, classToUse));
        jobInfoBuilder.addTriggerContentUri(new TriggerContentUri(contentUri, 1));
        jobInfoBuilder.setTriggerContentMaxDelay(0);
        int result = jobScheduler.schedule(jobInfoBuilder.build());
        for (JobInfo job : jobScheduler.getAllPendingJobs()) {
            SmsMmsLogger.a("SmsRelayCoordinator", "Job:" + job.getId() + " " + job.getService().getClassName());
        }
        SmsMmsLogger.a("SmsRelayCoordinator", "Re-Scheduled Sms/Mms Observer jobs - isSms:" + isSms + " result:" + result);
    }

    public static void a() {
        SmsMmsLogger.a("SmsRelayCoordinator", "Resetting isInitialized to false");
        c = false;
    }

    private static int a(Context context, boolean isSms) {
        return context.getSharedPreferences("NATIVE_SMS_PREFS", 0).getInt(isSms ? "NATIVE_SMS_LAST_SMS_MSG_COUNT" : "NATIVE_SMS_LAST_MMS_MSG_COUNT", 0);
    }

    private static AtomicLong b(Context context, boolean isSms) {
        return new AtomicLong(Long.valueOf(context.getSharedPreferences("NATIVE_SMS_PREFS", 0).getLong(isSms ? "NATIVE_SMS_LAST_PROCESSED_SMS_TS" : "NATIVE_SMS_LAST_PROCESSED_MMS_TS", 0)).longValue());
    }

    private static AtomicLong c(Context context, boolean isSms) {
        return new AtomicLong(Long.valueOf(context.getSharedPreferences("NATIVE_SMS_PREFS", 0).getLong(isSms ? "NATIVE_SMS_LAST_PROCESSED_SMS_DBKEY" : "NATIVE_SMS_LAST_PROCESSED_MMS_DBKEY", 0)).longValue());
    }

    private static void a(Context context, boolean isSms, int countOfSmsMsgs, long lastProcessedMsgTs, long lastProcessedMsgDbKey, String lastDeletedMsgDbKey) {
        SmsMmsLogger.a("SmsRelayCoordinator", "Set last message shared prefs - isSms:" + isSms + " count:" + countOfSmsMsgs + " message TS:" + lastProcessedMsgTs + " dbKey:" + lastProcessedMsgDbKey + " lastDeletedMsgDbKey:" + lastDeletedMsgDbKey);
        context.getSharedPreferences("NATIVE_SMS_PREFS", 0).edit().putInt(isSms ? "NATIVE_SMS_LAST_SMS_MSG_COUNT" : "NATIVE_SMS_LAST_MMS_MSG_COUNT", countOfSmsMsgs).apply();
        context.getSharedPreferences("NATIVE_SMS_PREFS", 0).edit().putLong(isSms ? "NATIVE_SMS_LAST_PROCESSED_SMS_TS" : "NATIVE_SMS_LAST_PROCESSED_MMS_TS", lastProcessedMsgTs).apply();
        if (lastProcessedMsgDbKey != -1) {
            context.getSharedPreferences("NATIVE_SMS_PREFS", 0).edit().putLong(isSms ? "NATIVE_SMS_LAST_PROCESSED_SMS_DBKEY" : "NATIVE_SMS_LAST_PROCESSED_MMS_DBKEY", lastProcessedMsgDbKey).apply();
        }
        if (lastDeletedMsgDbKey != null) {
            context.getSharedPreferences("NATIVE_SMS_PREFS", 0).edit().putString(isSms ? "NATIVE_SMS_LAST_DELETED_SMS_DBKEY" : "NATIVE_SMS_LAST_DELETED_MMS_DBKEY", lastDeletedMsgDbKey).apply();
        }
    }
}
