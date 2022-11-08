package com.skype.smsmanager;

import android.content.Context;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ag;
import com.microsoft.backgroundexecution.a;
import com.skype.smsmanager.models.EventSmsMessage;
import com.skype.smsmanager.models.RnDeletedCellularMessages;
import com.skype.smsmanager.models.RnMmsMessage;
import com.skype.smsmanager.models.RnOutgoingMessageStatus;
import com.skype.smsmanager.models.RnSmsMessage;
import com.skype.smsmanager.nativesms.SmsMmsManager;
import com.skype.smsmanager.nativesms.models.DeletedCellularMessagesItem;
import com.skype.smsmanager.nativesms.models.LoggerStrategy;
import com.skype.smsmanager.nativesms.models.MessageListener;
import com.skype.smsmanager.nativesms.models.MmsMessageItem;
import com.skype.smsmanager.nativesms.models.OutgoingMessageStatusImpl;
import com.skype.smsmanager.nativesms.models.SmsMessageItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SmsHandlingContext {
    private static AndroidSmsManagerModule a;
    private static SmsMmsManager b;
    private static final Queue<EventSmsMessage> c = new ConcurrentLinkedQueue();
    private static boolean d = false;

    public static synchronized AndroidSmsManagerModule a(ag reactContext) {
        AndroidSmsManagerModule androidSmsManagerModule;
        synchronized (SmsHandlingContext.class) {
            LoggerStrategy loggerStrategy = new LoggerStrategy() {
                public final void a(String tag, String msg) {
                    FLog.i(tag, msg);
                }

                public final void b(String tag, String msg) {
                    FLog.d(tag, msg);
                }

                public final void a(String tag, String msg, Throwable tr) {
                    FLog.e(tag, msg, tr);
                }

                public final void c(String tag, String msg) {
                    FLog.e(tag, msg);
                }
            };
            MessageListener messageListener = new MessageListener() {
                public final void a(Context context, SmsMessageItem smsMessageItem) {
                    String wakeLockId = a.a.a(context, 30.0d, "SmsReceived");
                    FLog.i("SmsHandlingContext", "smsReceived() WakeLockId: " + wakeLockId);
                    SmsHandlingContext.a(new RnSmsMessage(smsMessageItem, wakeLockId));
                }

                public final void a(OutgoingMessageStatusImpl outgoingMessageStatusImpl) {
                    FLog.i("SmsHandlingContext", "outgoingMessageStatus()");
                    SmsHandlingContext.a(new RnOutgoingMessageStatus(outgoingMessageStatusImpl));
                }

                public final void a(Context context, MmsMessageItem mmsMessageItem) {
                    String wakeLockId = a.a.a(context, 30.0d, "MmsReceived");
                    FLog.i("SmsHandlingContext", "mmsReceived() WakeLockId: " + wakeLockId);
                    SmsHandlingContext.a(new RnMmsMessage(mmsMessageItem, wakeLockId));
                }

                public final void a(DeletedCellularMessagesItem deletedCellularMessagesItem) {
                    FLog.i("SmsHandlingContext", "cellularMessageDeleted()");
                    SmsHandlingContext.a(new RnDeletedCellularMessages(deletedCellularMessagesItem));
                }

                public final void a(ArrayList<HashMap<String, Object>> telemetryInfos) {
                    boolean smsModuleInitialized;
                    if (SmsHandlingContext.a != null) {
                        smsModuleInitialized = true;
                    } else {
                        smsModuleInitialized = false;
                    }
                    if (SmsHandlingContext.d && smsModuleInitialized) {
                        FLog.i("SmsHandlingContext", "sendNativeTelemetryInfo() - sending telemetry info event");
                        SmsHandlingContext.a.sendTelemetryInfoEvent(telemetryInfos);
                        return;
                    }
                    FLog.i("SmsHandlingContext", String.format("sendNativeTelemetryInfo() - didn't send telemetry info event. InstanceManagerInitialized:%b, smsModule:%b", new Object[]{Boolean.valueOf(SmsHandlingContext.d), Boolean.valueOf(smsModuleInitialized)}));
                }
            };
            SmsMmsManager.c();
            SmsMmsManager.a(messageListener);
            b = new SmsMmsManager(reactContext.getApplicationContext(), loggerStrategy);
            androidSmsManagerModule = new AndroidSmsManagerModule(reactContext, c, b);
            a = androidSmsManagerModule;
        }
        return androidSmsManagerModule;
    }

    public static synchronized void a() {
        synchronized (SmsHandlingContext.class) {
            FLog.i("SmsHandlingContext", "init()");
            d = true;
            if (a == null) {
                throw new IllegalStateException("smsModule is not created while js engine is initialized");
            }
        }
    }

    public static synchronized void b() {
        synchronized (SmsHandlingContext.class) {
            FLog.i("SmsHandlingContext", "destroy()");
            SmsMmsManager.c();
            d = false;
        }
    }

    public static synchronized void a(EventSmsMessage eventSmsMessage) {
        synchronized (SmsHandlingContext.class) {
            FLog.i("SmsHandlingContext", "onReceive() instanceManagerInitialized:" + d + ", smsModule:" + (a != null));
            if (!d || a == null) {
                FLog.i("SmsHandlingContext", "onReceive() delaying message until AndroidSmsManager is initialized");
                c.add(eventSmsMessage);
            } else {
                a.sendEvent(eventSmsMessage);
                FLog.i("SmsHandlingContext", "onReceive() sent event");
            }
        }
    }
}
