package com.facebook.react.animated;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ad;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.v;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.modules.core.e;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.d;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.util.ArrayList;
import javax.annotation.Nullable;

@ReactModule(name = "NativeAnimatedModule")
public class NativeAnimatedModule extends ReactContextBaseJavaModule implements ad, v {
    protected static final String NAME = "NativeAnimatedModule";
    private final d mAnimatedFrameCallback;
    @Nullable
    private l mNodesManager;
    private ArrayList<a> mOperations = new ArrayList();
    private final Object mOperationsCopyLock = new Object();
    private final e mReactChoreographer = e.b();
    @Nullable
    private volatile ArrayList<a> mReadyOperations = null;

    private interface a {
        void a(l lVar);
    }

    public NativeAnimatedModule(ag reactContext) {
        super(reactContext);
        this.mAnimatedFrameCallback = new d(this, reactContext) {
            final /* synthetic */ NativeAnimatedModule a;

            protected final void a(long frameTimeNanos) {
                ArrayList<a> operations;
                if (this.a.mNodesManager == null) {
                    this.a.mNodesManager = new l((UIManagerModule) this.a.getReactApplicationContext().b(UIManagerModule.class));
                }
                synchronized (this.a.mOperationsCopyLock) {
                    operations = this.a.mReadyOperations;
                    this.a.mReadyOperations = null;
                }
                if (operations != null) {
                    int size = operations.size();
                    for (int i = 0; i < size; i++) {
                        ((a) operations.get(i)).a(this.a.mNodesManager);
                    }
                }
                if (this.a.mNodesManager.a()) {
                    this.a.mNodesManager.a(frameTimeNanos);
                }
                ((e) com.facebook.infer.annotation.a.a(this.a.mReactChoreographer)).a(com.facebook.react.modules.core.e.a.NATIVE_ANIMATED_MODULE, this.a.mAnimatedFrameCallback);
            }
        };
    }

    public void initialize() {
        getReactApplicationContext().a((v) this);
    }

    public void onHostResume() {
        enqueueFrameCallback();
    }

    public void onBatchComplete() {
        ArrayList<a> operations = this.mOperations.isEmpty() ? null : this.mOperations;
        if (operations != null) {
            this.mOperations = new ArrayList();
            synchronized (this.mOperationsCopyLock) {
                if (this.mReadyOperations == null) {
                    this.mReadyOperations = operations;
                } else {
                    this.mReadyOperations.addAll(operations);
                }
            }
        }
    }

    public void onHostPause() {
        clearFrameCallback();
    }

    public void onHostDestroy() {
    }

    public String getName() {
        return NAME;
    }

    private void clearFrameCallback() {
        ((e) com.facebook.infer.annotation.a.a(this.mReactChoreographer)).b(com.facebook.react.modules.core.e.a.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    private void enqueueFrameCallback() {
        ((e) com.facebook.infer.annotation.a.a(this.mReactChoreographer)).a(com.facebook.react.modules.core.e.a.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    @ReactMethod
    public void createAnimatedNode(final int tag, final am config) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule c;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.a(tag, config);
            }
        });
    }

    @ReactMethod
    public void startListeningToAnimatedNodeValue(final int tag) {
        final c listener = new c(this) {
            final /* synthetic */ NativeAnimatedModule b;

            public final void a(double value) {
                ar onAnimatedValueData = new WritableNativeMap();
                onAnimatedValueData.putInt("tag", tag);
                onAnimatedValueData.putDouble(PropertiesEntry.COLUMN_NAME_VALUE, value);
                ((RCTDeviceEventEmitter) this.b.getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit("onAnimatedValueUpdate", onAnimatedValueData);
            }
        };
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule c;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.a(tag, listener);
            }
        });
    }

    @ReactMethod
    public void stopListeningToAnimatedNodeValue(final int tag) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule b;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.c(tag);
            }
        });
    }

    @ReactMethod
    public void dropAnimatedNode(final int tag) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule b;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.b(tag);
            }
        });
    }

    @ReactMethod
    public void setAnimatedNodeValue(final int tag, final double value) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule c;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.a(tag, value);
            }
        });
    }

    @ReactMethod
    public void setAnimatedNodeOffset(final int tag, final double value) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule c;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.b(tag, value);
            }
        });
    }

    @ReactMethod
    public void flattenAnimatedNodeOffset(final int tag) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule b;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.d(tag);
            }
        });
    }

    @ReactMethod
    public void extractAnimatedNodeOffset(final int tag) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule b;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.e(tag);
            }
        });
    }

    @ReactMethod
    public void startAnimatingNode(int animationId, int animatedNodeTag, am animationConfig, com.facebook.react.bridge.d endCallback) {
        final int i = animationId;
        final int i2 = animatedNodeTag;
        final am amVar = animationConfig;
        final com.facebook.react.bridge.d dVar = endCallback;
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule e;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.a(i, i2, amVar, dVar);
            }
        });
    }

    @ReactMethod
    public void stopAnimation(final int animationId) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule b;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.f(animationId);
            }
        });
    }

    @ReactMethod
    public void connectAnimatedNodes(final int parentNodeTag, final int childNodeTag) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule c;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.a(parentNodeTag, childNodeTag);
            }
        });
    }

    @ReactMethod
    public void disconnectAnimatedNodes(final int parentNodeTag, final int childNodeTag) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule c;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.b(parentNodeTag, childNodeTag);
            }
        });
    }

    @ReactMethod
    public void connectAnimatedNodeToView(final int animatedNodeTag, final int viewTag) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule c;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.c(animatedNodeTag, viewTag);
            }
        });
    }

    @ReactMethod
    public void disconnectAnimatedNodeFromView(final int animatedNodeTag, final int viewTag) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule c;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.d(animatedNodeTag, viewTag);
            }
        });
    }

    @ReactMethod
    public void addAnimatedEventToView(final int viewTag, final String eventName, final am eventMapping) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule d;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.a(viewTag, eventName, eventMapping);
            }
        });
    }

    @ReactMethod
    public void removeAnimatedEventFromView(final int viewTag, final String eventName, final int animatedValueTag) {
        this.mOperations.add(new a(this) {
            final /* synthetic */ NativeAnimatedModule d;

            public final void a(l animatedNodesManager) {
                animatedNodesManager.a(viewTag, eventName, animatedValueTag);
            }
        });
    }
}
