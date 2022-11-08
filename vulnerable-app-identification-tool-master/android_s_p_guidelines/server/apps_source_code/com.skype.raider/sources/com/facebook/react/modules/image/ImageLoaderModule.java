package com.facebook.react.modules.image;

import android.net.Uri;
import android.util.SparseArray;
import com.facebook.common.f.a;
import com.facebook.datasource.b;
import com.facebook.datasource.c;
import com.facebook.datasource.e;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.g;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.j;
import com.facebook.react.bridge.v;
import com.facebook.react.module.annotations.ReactModule;
import javax.annotation.Nullable;

@ReactModule(name = "ImageLoader")
public class ImageLoaderModule extends ReactContextBaseJavaModule implements v {
    private static final String ERROR_GET_SIZE_FAILURE = "E_GET_SIZE_FAILURE";
    private static final String ERROR_INVALID_URI = "E_INVALID_URI";
    private static final String ERROR_PREFETCH_FAILURE = "E_PREFETCH_FAILURE";
    private final Object mCallerContext;
    private final Object mEnqueuedRequestMonitor;
    private final SparseArray<c<Void>> mEnqueuedRequests;

    public ImageLoaderModule(ag reactContext) {
        super(reactContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = new SparseArray();
        this.mCallerContext = this;
    }

    public ImageLoaderModule(ag reactContext, Object callerContext) {
        super(reactContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = new SparseArray();
        this.mCallerContext = callerContext;
    }

    public String getName() {
        return "ImageLoader";
    }

    @ReactMethod
    public void getSize(String uriString, final ae promise) {
        if (uriString == null || uriString.isEmpty()) {
            promise.a(ERROR_INVALID_URI, "Cannot get the size of an image for an empty URI");
            return;
        }
        Fresco.b().a(com.facebook.imagepipeline.k.c.a(Uri.parse(uriString)).q(), this.mCallerContext).a(new b<a<com.facebook.imagepipeline.image.c>>(this) {
            final /* synthetic */ ImageLoaderModule b;

            protected final void e(c<a<com.facebook.imagepipeline.image.c>> dataSource) {
                if (dataSource.b()) {
                    a<com.facebook.imagepipeline.image.c> ref = (a) dataSource.d();
                    if (ref != null) {
                        try {
                            com.facebook.imagepipeline.image.c image = (com.facebook.imagepipeline.image.c) ref.a();
                            Object sizes = new WritableNativeMap();
                            sizes.putInt("width", image.a());
                            sizes.putInt("height", image.b());
                            promise.a(sizes);
                        } catch (Throwable e) {
                            promise.a(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, e);
                        } finally {
                            a.c(ref);
                        }
                    } else {
                        promise.a(ImageLoaderModule.ERROR_GET_SIZE_FAILURE);
                    }
                }
            }

            protected final void f(c<a<com.facebook.imagepipeline.image.c>> dataSource) {
                promise.a(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, dataSource.f());
            }
        }, com.facebook.common.b.a.a());
    }

    @ReactMethod
    public void prefetchImage(String uriString, final int requestId, final ae promise) {
        if (uriString == null || uriString.isEmpty()) {
            promise.a(ERROR_INVALID_URI, "Cannot prefetch an image for an empty URI");
            return;
        }
        c<Void> prefetchSource = Fresco.b().b(com.facebook.imagepipeline.k.c.a(Uri.parse(uriString)).q(), this.mCallerContext);
        e<Void> prefetchSubscriber = new b<Void>(this) {
            final /* synthetic */ ImageLoaderModule c;

            protected final void e(c<Void> dataSource) {
                if (dataSource.b()) {
                    try {
                        this.c.removeRequest(requestId);
                        promise.a(Boolean.valueOf(true));
                    } finally {
                        dataSource.h();
                    }
                }
            }

            protected final void f(c<Void> dataSource) {
                try {
                    this.c.removeRequest(requestId);
                    promise.a(ImageLoaderModule.ERROR_PREFETCH_FAILURE, dataSource.f());
                } finally {
                    dataSource.h();
                }
            }
        };
        registerRequest(requestId, prefetchSource);
        prefetchSource.a(prefetchSubscriber, com.facebook.common.b.a.a());
    }

    @ReactMethod
    public void abortRequest(int requestId) {
        c<Void> request = removeRequest(requestId);
        if (request != null) {
            request.h();
        }
    }

    @ReactMethod
    public void queryCache(final al uris, final ae promise) {
        new j<Void, Void>(this, getReactApplicationContext()) {
            final /* synthetic */ ImageLoaderModule c;

            protected final /* synthetic */ void a() {
                Object writableNativeMap = new WritableNativeMap();
                g b = Fresco.b();
                for (int i = 0; i < uris.size(); i++) {
                    String string = uris.getString(i);
                    Uri parse = Uri.parse(string);
                    if (b.b(parse)) {
                        writableNativeMap.putString(string, "memory");
                    } else if (b.c(parse)) {
                        writableNativeMap.putString(string, "disk");
                    }
                }
                promise.a(writableNativeMap);
            }
        }.executeOnExecutor(j.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void registerRequest(int requestId, c<Void> request) {
        synchronized (this.mEnqueuedRequestMonitor) {
            this.mEnqueuedRequests.put(requestId, request);
        }
    }

    @Nullable
    private c<Void> removeRequest(int requestId) {
        c<Void> request;
        synchronized (this.mEnqueuedRequestMonitor) {
            request = (c) this.mEnqueuedRequests.get(requestId);
            this.mEnqueuedRequests.remove(requestId);
        }
        return request;
    }

    public void onHostResume() {
    }

    public void onHostPause() {
    }

    public void onHostDestroy() {
        synchronized (this.mEnqueuedRequestMonitor) {
            int size = this.mEnqueuedRequests.size();
            for (int i = 0; i < size; i++) {
                c<Void> enqueuedRequest = (c) this.mEnqueuedRequests.valueAt(i);
                if (enqueuedRequest != null) {
                    enqueuedRequest.h();
                }
            }
            this.mEnqueuedRequests.clear();
        }
    }
}
