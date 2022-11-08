package com.facebook.react.uimanager.debug;

import android.util.SparseArray;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.m;
import com.facebook.react.module.annotations.ReactModule;
import javax.annotation.Nullable;

@ReactModule(name = "DebugComponentOwnershipModule")
public class DebugComponentOwnershipModule extends ReactContextBaseJavaModule {
    private int mNextRequestId = 0;
    @Nullable
    private RCTDebugComponentOwnership mRCTDebugComponentOwnership;
    private final SparseArray<a> mRequestIdToCallback = new SparseArray();

    public interface RCTDebugComponentOwnership extends JavaScriptModule {
        void getOwnerHierarchy(int i, int i2);
    }

    public interface a {
    }

    public DebugComponentOwnershipModule(ag reactContext) {
        super(reactContext);
    }

    public void initialize() {
        super.initialize();
        this.mRCTDebugComponentOwnership = (RCTDebugComponentOwnership) getReactApplicationContext().a(RCTDebugComponentOwnership.class);
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        this.mRCTDebugComponentOwnership = null;
    }

    @ReactMethod
    public synchronized void receiveOwnershipHierarchy(int requestId, int tag, @Nullable al owners) {
        if (((a) this.mRequestIdToCallback.get(requestId)) == null) {
            throw new m("Got receiveOwnershipHierarchy for invalid request id: " + requestId);
        }
        this.mRequestIdToCallback.delete(requestId);
    }

    public synchronized void loadComponentOwnerHierarchy(int tag, a callback) {
        int requestId = this.mNextRequestId;
        this.mNextRequestId++;
        this.mRequestIdToCallback.put(requestId, callback);
        ((RCTDebugComponentOwnership) com.facebook.infer.annotation.a.a(this.mRCTDebugComponentOwnership)).getOwnerHierarchy(requestId, tag);
    }

    public String getName() {
        return "DebugComponentOwnershipModule";
    }
}
