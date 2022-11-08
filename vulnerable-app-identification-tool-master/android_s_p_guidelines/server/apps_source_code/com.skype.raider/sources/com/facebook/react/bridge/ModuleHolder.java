package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.systrace.b;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.inject.a;

@DoNotStrip
public class ModuleHolder {
    private static final AtomicInteger sInstanceKeyCounter = new AtomicInteger(1);
    private final boolean mCanOverrideExistingModule;
    private final boolean mHasConstants;
    @GuardedBy("this")
    private boolean mInitializable;
    @GuardedBy("this")
    private boolean mIsCreating;
    @GuardedBy("this")
    private boolean mIsInitializing;
    @GuardedBy("this")
    @Nullable
    private NativeModule mModule;
    private final String mName;
    @Nullable
    private a<? extends NativeModule> mProvider;

    public ModuleHolder(com.facebook.react.module.a.a moduleInfo, a<? extends NativeModule> provider) {
        this.mName = moduleInfo.a();
        this.mCanOverrideExistingModule = moduleInfo.b();
        this.mHasConstants = moduleInfo.d();
        this.mProvider = provider;
        if (moduleInfo.c()) {
            this.mModule = create();
        }
    }

    public ModuleHolder(NativeModule nativeModule) {
        this.mName = nativeModule.getName();
        this.mCanOverrideExistingModule = nativeModule.canOverrideExistingModule();
        this.mHasConstants = true;
        this.mModule = nativeModule;
    }

    void markInitializable() {
        boolean z = true;
        boolean shouldInitializeNow = false;
        NativeModule module = null;
        synchronized (this) {
            this.mInitializable = true;
            if (this.mModule != null) {
                if (this.mIsInitializing) {
                    z = false;
                }
                com.facebook.infer.annotation.a.a(z);
                shouldInitializeNow = true;
                module = this.mModule;
            }
        }
        if (shouldInitializeNow) {
            doInitialize(module);
        }
    }

    synchronized boolean hasInstance() {
        return this.mModule != null;
    }

    public synchronized void destroy() {
        if (this.mModule != null) {
            this.mModule.onCatalystInstanceDestroy();
        }
    }

    @DoNotStrip
    public String getName() {
        return this.mName;
    }

    public boolean getCanOverrideExistingModule() {
        return this.mCanOverrideExistingModule;
    }

    public boolean getHasConstants() {
        return this.mHasConstants;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @DoNotStrip
    public NativeModule getModule() {
        boolean shouldCreate = false;
        synchronized (this) {
            if (this.mModule != null) {
                NativeModule nativeModule = this.mModule;
                return nativeModule;
            } else if (!this.mIsCreating) {
                shouldCreate = true;
                this.mIsCreating = true;
            }
        }
    }

    private NativeModule create() {
        an.a(this.mModule == null, "Creating an already created module.");
        int instanceKey = sInstanceKeyCounter.getAndIncrement();
        ReactMarker.logMarker(aj.CREATE_MODULE_START, this.mName, instanceKey);
        b.a();
        try {
            NativeModule module = (NativeModule) ((a) com.facebook.infer.annotation.a.a(this.mProvider)).a();
            this.mProvider = null;
            boolean shouldInitializeNow = false;
            synchronized (this) {
                this.mModule = module;
                if (this.mInitializable && !this.mIsInitializing) {
                    shouldInitializeNow = true;
                }
            }
            if (shouldInitializeNow) {
                doInitialize(module);
            }
            ReactMarker.logMarker(aj.CREATE_MODULE_END, instanceKey);
            b.b();
            return module;
        } catch (Throwable th) {
            ReactMarker.logMarker(aj.CREATE_MODULE_END, instanceKey);
            b.b();
        }
    }

    private void doInitialize(NativeModule module) {
        b.a();
        ReactMarker.logMarker(aj.INITIALIZE_MODULE_START, this.mName);
        boolean shouldInitialize = false;
        try {
            synchronized (this) {
                if (this.mInitializable && !this.mIsInitializing) {
                    shouldInitialize = true;
                    this.mIsInitializing = true;
                }
            }
            if (shouldInitialize) {
                module.initialize();
                synchronized (this) {
                    this.mIsInitializing = false;
                }
            }
            ReactMarker.logMarker(aj.INITIALIZE_MODULE_END);
            b.b();
        } catch (Throwable th) {
            ReactMarker.logMarker(aj.INITIALIZE_MODULE_END);
            b.b();
        }
    }
}
