package com.facebook.react.modules.network;

import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.aq;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.net.SocketTimeoutException;

public final class l {
    public static void a(RCTDeviceEventEmitter eventEmitter, int requestId, String data, long progress, long total) {
        aq args = new WritableNativeArray();
        args.pushInt(requestId);
        args.pushString(data);
        args.pushInt((int) progress);
        args.pushInt((int) total);
        eventEmitter.emit("didReceiveNetworkIncrementalData", args);
    }

    public static void a(RCTDeviceEventEmitter eventEmitter, int requestId, String error, Throwable e) {
        aq args = new WritableNativeArray();
        args.pushInt(requestId);
        args.pushString(error);
        if (e != null && e.getClass() == SocketTimeoutException.class) {
            args.pushBoolean(true);
        }
        eventEmitter.emit("didCompleteNetworkResponse", args);
    }

    public static void a(RCTDeviceEventEmitter eventEmitter, int requestId) {
        aq args = new WritableNativeArray();
        args.pushInt(requestId);
        args.pushNull();
        eventEmitter.emit("didCompleteNetworkResponse", args);
    }
}
