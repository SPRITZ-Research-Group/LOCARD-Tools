package com.microsoft.react.sharesheet;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.d;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.File;
import java.util.Locale;
import java.util.logging.Logger;

public class ShareSheet extends ReactContextBaseJavaModule {
    private static final String ACTION = "action";
    private static final String ACTION_SEND = "send";
    private static final String ACTION_VIEW = "view";
    private static final String MESSAGE = "message";
    private static final String MODULE_NAME = "ShareSheet";
    private static final String SUBJECT = "subject";
    private static final String TAG = "ShareSheet";
    private static final String URL = "url";
    private static final Logger logger = Logger.getLogger("ShareSheet");
    private Context context;

    public ShareSheet(ag reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    public String getName() {
        return "ShareSheet";
    }

    @ReactMethod
    public void show(am options, d errorCallback, d successCallback) {
        if (options == null) {
            errorCallback.invoke("ShareSheet invoked without options");
        } else if (!options.hasKey(ACTION)) {
            errorCallback.invoke("Action not provided for ShareSheet");
        } else if (this.context == null) {
            errorCallback.invoke("Unable to show ShareSheet as no context present");
        } else {
            String action = options.getString(ACTION);
            Intent intent = null;
            int i = -1;
            try {
                switch (action.hashCode()) {
                    case 3526536:
                        if (action.equals(ACTION_SEND)) {
                            i = 0;
                            break;
                        }
                        break;
                    case 3619493:
                        if (action.equals(ACTION_VIEW)) {
                            i = 1;
                            break;
                        }
                        break;
                }
                switch (i) {
                    case 0:
                        intent = getSendActionIntent(options, errorCallback);
                        break;
                    case 1:
                        intent = getViewActionIntent(options, errorCallback);
                        break;
                    default:
                        errorCallback.invoke("Unknown action provided for ShareSheet");
                        break;
                }
                if (intent == null) {
                    return;
                }
                if (intent.resolveActivity(this.context.getPackageManager()) != null) {
                    this.context.startActivity(intent);
                    successCallback.invoke(Boolean.valueOf(true), action);
                    return;
                }
                errorCallback.invoke("Unable to open ShareSheet as no activity could handle the intent");
            } catch (Throwable ex) {
                FLog.e("ShareSheet", "ShareSheet.show failed", ex);
                errorCallback.invoke("Unhandled java exception: " + ex.getMessage());
            }
        }
    }

    private Intent getSendActionIntent(am options, d errorCallback) {
        Intent sendIntent = getBaseIntent(options, errorCallback);
        if (sendIntent == null) {
            return null;
        }
        sendIntent.setAction("android.intent.action.SEND");
        sendIntent = Intent.createChooser(sendIntent, null);
        sendIntent.addFlags(ErrorDialogData.BINDER_CRASH);
        return sendIntent;
    }

    private Intent getViewActionIntent(am options, d errorCallback) {
        if (options.hasKey("url")) {
            Intent viewIntent = getBaseIntent(options, errorCallback);
            if (viewIntent == null) {
                return null;
            }
            viewIntent.setAction("android.intent.action.VIEW");
            return viewIntent;
        }
        errorCallback.invoke("Could not create view intent, url not present");
        return null;
    }

    private Uri getFileProviderUri(String fileUri) {
        File newFile = new File(fileUri);
        return FileProvider.a(this.context, this.context.getPackageName() + ".fileprovider", newFile);
    }

    private Intent getBaseIntent(am options, d errorCallback) {
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        Intent intent = new Intent();
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        if (options.hasKey(SUBJECT)) {
            intent.putExtra("android.intent.extra.SUBJECT", options.getString(SUBJECT));
        }
        if (options.hasKey(MESSAGE)) {
            intent.putExtra("android.intent.extra.TEXT", options.getString(MESSAGE));
        }
        if (options.hasKey("url")) {
            intent.addFlags(1);
            String fileUri = options.getString("url");
            if (fileUri == null || fileUri.isEmpty()) {
                errorCallback.invoke("Invalid URL of file to be opened");
                return null;
            }
            String mimeType;
            Uri uri = getFileProviderUri(fileUri);
            String extension = MimeTypeMap.getFileExtensionFromUrl(fileUri);
            if (extension != null) {
                mimeType = mimeTypeMap.getMimeTypeFromExtension(extension.toLowerCase(Locale.US));
            } else {
                mimeType = null;
            }
            intent.putExtra("android.intent.extra.STREAM", uri);
            intent.setClipData(ClipData.newRawUri(null, uri));
            if (mimeType == null || mimeType.isEmpty()) {
                intent.setData(uri);
                logger.info("Unable to find mimeType for uri: " + fileUri);
            } else {
                intent.setDataAndType(uri, mimeType);
            }
        } else {
            intent.setType("text/plain");
        }
        return intent;
    }
}
