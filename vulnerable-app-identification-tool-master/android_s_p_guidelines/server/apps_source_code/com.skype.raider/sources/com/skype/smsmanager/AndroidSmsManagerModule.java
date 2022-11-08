package com.skype.smsmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.microsoft.backgroundexecution.a;
import com.microsoft.nativecodetelemetry.NativeCodeTelemetryModule;
import com.skype.smsmanager.models.EventSmsMessage;
import com.skype.smsmanager.models.RnNativeTelemetryInfo;
import com.skype.smsmanager.models.RnSmsMmsConstants;
import com.skype.smsmanager.nativesms.SmsMmsManager;
import com.skype.smsmanager.nativesms.models.OutgoingSmsMessageImpl;
import com.skype.smsmanager.nativesms.utils.PhoneUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;

public final class AndroidSmsManagerModule extends ReactContextBaseJavaModule implements RnSmsMmsConstants {
    public static final String DELETED_CELLULAR_MESSAGES = "RnDeletedCellularMessagesEvent";
    public static final String INCOMING_SMS = "RnIncomingSmsEvent";
    private static final String MODULE_NAME = "AndroidSmsManager";
    public static final String NATIVE_TELEMETRY_INFO = "RnNativeTelemetryInfo";
    public static final String OUTGOING_SMS_STATUS = "RnOutgoingSmsStatusEvent";
    public static final String TAG = "RNSmsManager";
    private final ag context;
    private Queue<EventSmsMessage> delayedMessagesQueue;
    private volatile boolean javascriptModuleInitialized;
    private final PowerManager powerManager = ((PowerManager) this.context.getSystemService("power"));
    private final SmsMmsManager smsMmsManager;

    public AndroidSmsManagerModule(ag reactContext, Queue<EventSmsMessage> delayedMessagesQueue, SmsMmsManager smsMmsManager) {
        super(reactContext);
        this.context = reactContext;
        this.delayedMessagesQueue = delayedMessagesQueue;
        this.smsMmsManager = smsMmsManager;
    }

    public final String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public final void startProcessingEvents() {
        FLog.i(TAG, "startProcessingEvents() javascriptModuleInitialized: " + this.javascriptModuleInitialized);
        if (!this.javascriptModuleInitialized) {
            this.javascriptModuleInitialized = true;
            processQueue();
        }
    }

    @ReactMethod
    public final void isSmsMmsCapable(ae promise) {
        if (PhoneUtils.a(this.context) != null) {
            FLog.i(TAG, "isSmsMmsCapable: true");
            promise.a(Boolean.valueOf(true));
            return;
        }
        promise.a(Boolean.valueOf(false));
    }

    @ReactMethod
    public final void setSelfPhoneNumber(String selfPhone) {
        FLog.i(TAG, "setSelfPhoneNumber: length:" + selfPhone.length());
        FLog.d(TAG, "setSelfPhoneNumber: " + selfPhone);
        this.smsMmsManager.a(selfPhone);
    }

    @ReactMethod
    private void getSelfPhoneNumber(ae promise) {
        Object selfPhone = PhoneUtils.a(this.context, TAG, "getSelfPhoneNumber");
        FLog.d(TAG, "getSelfPhoneNumber: " + selfPhone);
        promise.a(selfPhone);
    }

    @ReactMethod
    public final void sendTextMessage(am jsMessage) {
        boolean isMms = true;
        ArrayList recipients = jsMessage.getArray("recipients").toArrayList();
        String body = jsMessage.getString("body");
        String cuid = jsMessage.getString("cuid");
        if (recipients.size() <= 1) {
            isMms = false;
        }
        FLog.i(TAG, "sendTextMessage() " + (isMms ? "MMS" : "SMS"));
        if (isMms) {
            this.smsMmsManager.a((String[]) recipients.toArray(new String[0]), body, cuid);
        } else {
            this.smsMmsManager.a(new OutgoingSmsMessageImpl((String) recipients.get(0), body, cuid));
        }
    }

    @ReactMethod
    public final void finishIncomingSmsProcessing(String wakeLockId) {
        a.a.a(wakeLockId, "SmsFinish");
    }

    @ReactMethod
    public final void setEnableBroadcastReceivers(boolean enable, boolean removeSelfPhoneNumber) {
        FLog.i(TAG, "setEnableBroadcastReceivers() enable: " + enable);
        if (!enable && removeSelfPhoneNumber) {
            FLog.d(TAG, "Removing SelfPhoneNumber - enable:" + enable + " removeSelfPhoneNumber:" + removeSelfPhoneNumber);
            this.smsMmsManager.a();
            FLog.d(TAG, "Removing Last Processed SMS Message Prefs");
            this.smsMmsManager.b();
        }
        this.smsMmsManager.a(enable);
    }

    @ReactMethod
    public final void isBackgroundSupportEnabled(ae promise) {
        boolean isBatteryOptimizationIgnored = this.powerManager.isIgnoringBatteryOptimizations(this.context.getPackageName());
        FLog.i(TAG, "isBatteryOptimizationIgnored:" + isBatteryOptimizationIgnored);
        promise.a(Boolean.valueOf(isBatteryOptimizationIgnored));
    }

    @ReactMethod
    public final void requestEnableBackgroundSupport() {
        if (!this.powerManager.isIgnoringBatteryOptimizations(this.context.getPackageName())) {
            FLog.i(TAG, "Requesting ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
            this.context.startActivity(new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS").setData(Uri.parse("package:" + this.context.getPackageName())).addFlags(ErrorDialogData.BINDER_CRASH));
        }
    }

    @ReactMethod
    public final void setIgnoreBrickedSmsMessagesTimeout(int timeout) {
        FLog.i(TAG, "setIgnoreBrickedSmsMessageTimeout:" + timeout);
        this.smsMmsManager.a(timeout);
    }

    public final void sendEvent(EventSmsMessage cellularMessage) {
        if (this.javascriptModuleInitialized) {
            ((RCTDeviceEventEmitter) this.context.a(RCTDeviceEventEmitter.class)).emit(cellularMessage.a(), cellularMessage.b());
            FLog.i(TAG, "sendEvent() sent the message");
            return;
        }
        FLog.i(TAG, "javascript module is not initialized. Adding to delayedMessageQueue");
        this.delayedMessagesQueue.add(cellularMessage);
    }

    private void processQueue() {
        if (this.javascriptModuleInitialized) {
            FLog.i(TAG, "processing the queue of size: " + this.delayedMessagesQueue.size());
            while (!this.delayedMessagesQueue.isEmpty()) {
                sendEvent((EventSmsMessage) this.delayedMessagesQueue.poll());
            }
        }
    }

    public final void sendTelemetryInfoEvent(ArrayList<HashMap<String, Object>> telemetryInfos) {
        int index = 1;
        Iterator it = telemetryInfos.iterator();
        while (it.hasNext()) {
            HashMap<String, Object> telemetryInfo = (HashMap) it.next();
            FLog.i(TAG, String.format("sendTelemetryInfoEvent() - Sending telemetry info at index:%d", new Object[]{Integer.valueOf(index)}));
            RnNativeTelemetryInfo rnTelemetryInfo = new RnNativeTelemetryInfo(telemetryInfo);
            String telemetryEventName = rnTelemetryInfo.c();
            if (telemetryEventName == null) {
                FLog.i(TAG, "sendTelemetryInfoEvent() - Event name was null, cannot send event");
                return;
            } else {
                NativeCodeTelemetryModule.sendEvent(telemetryEventName, rnTelemetryInfo.b());
                index++;
            }
        }
    }
}
