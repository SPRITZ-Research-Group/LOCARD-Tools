package com.microsoft.skype.documentpicker;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.a;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.ar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DocumentPickerModule extends ReactContextBaseJavaModule implements a {
    private static final String NAME = "name";
    private static final String SIZE = "size";
    private static final String TAG = "RNDocumentPicker";
    private static final String TYPE = "type";
    private static final String URI = "uri";
    private ag reactContext;
    private AtomicInteger requestId = new AtomicInteger();
    private Map<Integer, ae> requestMap = Collections.synchronizedMap(new HashMap());

    public DocumentPickerModule(ag reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        reactContext.a((a) this);
    }

    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void show(ae promise) {
        int currentRequestValue = this.requestId.incrementAndGet();
        this.requestMap.put(Integer.valueOf(currentRequestValue), promise);
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        FLog.i(TAG, "show: showing activity for request:" + currentRequestValue);
        ai aiVar = this.reactContext;
        Bundle bundle = Bundle.EMPTY;
        Object j = aiVar.j();
        com.facebook.infer.annotation.a.a(j);
        j.startActivityForResult(intent, currentRequestValue, bundle);
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        FLog.i(TAG, "onActivityResult: redirecting to the other method.");
        onActivityResult(requestCode, resultCode, data);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        FLog.i(TAG, "onActivityResult: starting to process selected file with request:" + requestCode + " and result:" + resultCode);
        Object results = new WritableNativeArray();
        if (resultCode == -1) {
            FLog.i(TAG, "onActivityResult: result is ok.");
            if (resultData != null) {
                FLog.i(TAG, "onActivityResult: processing result data.");
                Uri uri = resultData.getData();
                if (uri != null) {
                    ar fileDetails = extractData(uri);
                    if (fileDetails != null) {
                        results.pushMap(fileDetails);
                        FLog.i(TAG, "onActivityResult: adding result data.");
                    }
                }
            }
        }
        ae promise = (ae) this.requestMap.get(Integer.valueOf(requestCode));
        if (promise != null) {
            FLog.i(TAG, "onActivityResult: resolving promise.");
            promise.a(results);
            this.requestMap.remove(Integer.valueOf(requestCode));
        }
    }

    public void onNewIntent(Intent intent) {
    }

    @Nullable
    private ar extractData(Uri uri) {
        ar result = new WritableNativeMap();
        ContentResolver resolver = this.reactContext.getContentResolver();
        try {
            resolver.takePersistableUriPermission(uri, 1);
            Cursor cursor = resolver.query(uri, null, null, null, null, null);
            if (cursor == null) {
                return null;
            }
            try {
                int displayNameIndex = cursor.getColumnIndex("_display_name");
                int sizeIndex = cursor.getColumnIndex("_size");
                if (!cursor.moveToFirst()) {
                    return null;
                }
                result.putString("uri", uri.toString());
                if (!cursor.isNull(sizeIndex)) {
                    result.putInt(SIZE, cursor.getInt(sizeIndex));
                }
                result.putString(NAME, cursor.getString(displayNameIndex));
                result.putString("type", resolver.getType(uri));
                cursor.close();
                return result;
            } finally {
                cursor.close();
            }
        } catch (Throwable e) {
            FLog.e(TAG, "extractData: Exception calling resolver.takePersistableUriPermission.", e);
            return null;
        }
    }
}
