package com.facebook.react.devsupport;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.module.annotations.ReactModule;
import java.io.File;
import javax.annotation.Nullable;

@ReactModule(name = "JSCHeapCapture", needsEagerInit = true)
public class JSCHeapCapture extends ReactContextBaseJavaModule {
    @Nullable
    private a mCaptureInProgress = null;

    public interface HeapCapture extends JavaScriptModule {
        void captureHeap(String str);
    }

    public interface a {
    }

    public static class b extends Exception {
        b(String message) {
            super(message);
        }
    }

    public JSCHeapCapture(ag reactContext) {
        super(reactContext);
    }

    public synchronized void captureHeap(String path, a callback) {
        b bVar;
        if (this.mCaptureInProgress != null) {
            bVar = new b("Heap capture already in progress.");
        } else {
            File f = new File(path + "/capture.json");
            f.delete();
            HeapCapture heapCapture = (HeapCapture) getReactApplicationContext().a(HeapCapture.class);
            if (heapCapture == null) {
                bVar = new b("Heap capture js module not registered.");
            } else {
                this.mCaptureInProgress = callback;
                heapCapture.captureHeap(f.getPath());
            }
        }
    }

    @ReactMethod
    public synchronized void captureComplete(String path, String error) {
        if (this.mCaptureInProgress != null) {
            if (error == null) {
                File file = new File(path);
            } else {
                b bVar = new b(error);
            }
            this.mCaptureInProgress = null;
        }
    }

    public String getName() {
        return "JSCHeapCapture";
    }
}
