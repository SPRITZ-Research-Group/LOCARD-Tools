package com.microsoft.urlrequest;

import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.content.c;
import com.facebook.common.logging.FLog;
import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.b;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.facebook.react.modules.core.d;
import com.microsoft.urlrequest.RNUrlRequestService.a;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import net.hockeyapp.android.j;

public class RNUrlRequestNativeModule extends ReactContextBaseJavaModule {
    private static final String TAG = "RNUrlRequest";
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ RNUrlRequestNativeModule a;

        {
            this.a = this$0;
        }

        public final void onReceive(Context context, Intent intent) {
            FLog.i(RNUrlRequestNativeModule.TAG, "onReceive()");
            this.a.sendEvent(this.a.convertBundle(intent.getBundleExtra("com.microsoft.s4l.UrlRequest.EXTRA_RESULT")));
        }
    };
    private AtomicBoolean receiverRegistered = new AtomicBoolean(false);

    RNUrlRequestNativeModule(ag reactContext) {
        super(reactContext);
    }

    private void registerReceiver() {
        if (this.receiverRegistered.compareAndSet(false, true)) {
            Context context = getReactApplicationContext().getApplicationContext();
            c.a(context).a(this.mMessageReceiver, new IntentFilter("com.microsoft.s4l.UrlRequest.REQUEST_RESULT_INTENT_LOCAL_BROADCASE"));
            sendEvent(convertBundle(a.a()));
        }
    }

    private void unregisterReceiver() {
        if (this.receiverRegistered.compareAndSet(true, false)) {
            c.a(getReactApplicationContext().getApplicationContext()).a(this.mMessageReceiver);
        }
    }

    public void onCatalystInstanceDestroy() {
        unregisterReceiver();
        super.onCatalystInstanceDestroy();
    }

    public String getName() {
        return TAG;
    }

    private void sendEvent(ar params) {
        FLog.i(TAG, "sendEvent()");
        if (this.receiverRegistered.get()) {
            ((RCTNativeAppEventEmitter) getReactApplicationContext().a(RCTNativeAppEventEmitter.class)).emit("UrlRequest", params);
            return;
        }
        String message = "Cannot send event from RNUrlRequest because startSendingEvents has not been called";
        FLog.e(TAG, message);
        throw new com.facebook.react.bridge.c(message);
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        constants.put("eventName", "UrlRequest");
        constants.put("eventKind", a.a);
        return constants;
    }

    @ReactMethod
    public void startSendingEvents(ae promise) {
        FLog.i(TAG, "startSendingEvents");
        registerReceiver();
        promise.a(null);
    }

    @ReactMethod
    public void finishedProcessingBackgroundResults() {
        FLog.i(TAG, "finishedProcessingBackgroundResults");
    }

    @ReactMethod
    public void send(String id, String url, String method, am rawHeaders, am options) {
        if (!hasRequiredPermissions("android.permission.WRITE_EXTERNAL_STORAGE")) {
            FLog.i(TAG, "WRITE_EXTERNAL_STORAGE permission not granted, requesting now.");
            requestPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        String sendFileUri = getStringOption(options, "postDataPathOrFileUri");
        String sendData = getStringOption(options, "postData");
        String downloadToFile = getStringOption(options, "getDownloadToFile");
        String downloadToPublicFolderWithName = getStringOption(options, "publicFolderFileName");
        boolean downloadPublicFolderUseSkypeFolder = getBooleanOption(options, "downloadPublicFolderUseSkypeFolder");
        boolean withCredentials = getBooleanOption(options, "withCredentials");
        Integer completeTimeout = getIntegerOption(options, "completeTimeout");
        Integer progressTimeout = getIntegerOption(options, "progressTimeout");
        boolean resumable = getBooleanOption(options, "resumable");
        Integer startOffset = getIntegerOption(options, "resumableStartOffset");
        String uploadStatusUrl = getStringOption(options, "resumableUploadStatusUrl");
        Integer attemptsSoFar = getIntegerOption(options, "resumableAttemptsSoFar");
        FLog.i(TAG, "send: " + id + ", url: " + url + ", method: " + method + ", dataPath: " + sendFileUri + ", hasSendData: " + (sendData != null) + ", downloadToFile: " + downloadToFile + ", withCredentials: " + withCredentials + ", completeTimeout: " + completeTimeout + ", progressTimeout: " + progressTimeout + ", toPublicFolderWithName: " + downloadToPublicFolderWithName + ", toSkypeFolder: " + downloadPublicFolderUseSkypeFolder + ", resumable: " + resumable + ", startOffset: " + startOffset + ", attemptsSoFar: " + attemptsSoFar);
        PersistableBundle headersBundle = decodeHeaders(rawHeaders);
        PersistableBundle paramsBundle = new PersistableBundle();
        paramsBundle.putString("kind", "request");
        paramsBundle.putString("id", id);
        paramsBundle.putString(j.FRAGMENT_URL, url);
        paramsBundle.putString("method", method);
        paramsBundle.putPersistableBundle("headers", headersBundle);
        paramsBundle.putString("sendFileUri", sendFileUri);
        paramsBundle.putString("sendData", sendData);
        paramsBundle.putString("downloadToFile", downloadToFile);
        paramsBundle.putString("downloadToPublicFolderWithName", downloadToPublicFolderWithName);
        paramsBundle.putBoolean("downloadPublicFolderUseSkypeFolder", downloadPublicFolderUseSkypeFolder);
        paramsBundle.putBoolean("withCredentials", withCredentials);
        if (completeTimeout != null) {
            paramsBundle.putInt("completeTimeout", completeTimeout.intValue());
        }
        if (progressTimeout != null) {
            paramsBundle.putInt("progressTimeout", progressTimeout.intValue());
        }
        paramsBundle.putBoolean("resumable", resumable);
        if (startOffset != null) {
            paramsBundle.putInt("startOffset", startOffset.intValue());
        }
        paramsBundle.putString("uploadStatusUrl", uploadStatusUrl);
        if (attemptsSoFar != null) {
            paramsBundle.putInt("attemptsSoFar", attemptsSoFar.intValue());
        }
        sendToService(paramsBundle);
    }

    @ReactMethod
    public void cancel(String id) {
        FLog.i(TAG, "cancel: " + id);
        PersistableBundle paramsBundle = new PersistableBundle();
        paramsBundle.putString("kind", "cancel");
        paramsBundle.putString("id", id);
        sendToService(paramsBundle);
    }

    private void sendToService(PersistableBundle bundle) {
        Context context = getReactApplicationContext().getApplicationContext();
        int jobId = bundle.getString("id").hashCode();
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        RNUrlRequestService.a(new a(this) {
            final /* synthetic */ RNUrlRequestNativeModule a;

            {
                this.a = this$0;
            }

            public final void a(Bundle result) {
                this.a.sendEvent(this.a.convertBundle(result));
            }
        });
        jobScheduler.schedule(new Builder(jobId, new ComponentName(context, RNUrlRequestService.class)).setExtras(bundle).setOverrideDeadline(100).build());
    }

    private PersistableBundle decodeHeaders(am headers) {
        PersistableBundle map = new PersistableBundle();
        ReadableMapKeySetIterator it = headers.keySetIterator();
        while (it.hasNextKey()) {
            String key = it.nextKey();
            map.putString(key, headers.getString(key));
        }
        return map;
    }

    private String getStringOption(am options, String key) {
        return options.hasKey(key) ? options.getString(key) : null;
    }

    private boolean getBooleanOption(am options, String key) {
        return options.hasKey(key) && options.getBoolean(key);
    }

    private Integer getIntegerOption(am options, String key) {
        return options.hasKey(key) ? Integer.valueOf(options.getInt(key)) : null;
    }

    private Bundle encodeHeaders(HashMap<String, String> headers) {
        Bundle bundle = new Bundle();
        for (Entry<String, String> entry : headers.entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        return bundle;
    }

    private ar convertBundle(Bundle bundle) {
        for (String key : bundle.keySet()) {
            Object value = bundle.get(key);
            if (value instanceof HashMap) {
                bundle.remove(key);
                bundle.putBundle(key, encodeHeaders((HashMap) value));
            }
        }
        return b.a(bundle);
    }

    private boolean hasRequiredPermissions(String permissionName) {
        return android.support.v4.content.a.a(getReactApplicationContext(), permissionName) == 0;
    }

    private void requestPermission(final String permissionName) {
        ReactActivity activity = (ReactActivity) getCurrentActivity();
        if (activity != null) {
            String[] strArr = new String[]{permissionName};
            activity.a(strArr, TAG.hashCode(), new d(this) {
                final /* synthetic */ RNUrlRequestNativeModule b;

                public final boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
                    int index = Arrays.asList(permissions).indexOf(permissionName);
                    if (index >= 0) {
                        if (grantResults[index] == 0) {
                            FLog.i(RNUrlRequestNativeModule.TAG, "Permission " + permissionName + "granted.");
                        } else {
                            FLog.w(RNUrlRequestNativeModule.TAG, "Permission " + permissionName + "prompt denied.");
                        }
                    }
                    return true;
                }
            });
        }
    }

    @ReactMethod
    public void getKnownRequestIds(ae promise) {
        promise.a(new WritableNativeArray());
    }
}
