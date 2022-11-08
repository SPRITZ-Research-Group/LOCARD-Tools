package com.slowpath.hockeyapp;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.v;
import com.skype.hockeyapp.SkypeCrashManager;
import java.util.ArrayDeque;
import java.util.Queue;

public class RNHockeyAppModule extends ReactContextBaseJavaModule implements v {
    public static final String TAG = "ReactHockeyApp";
    private Queue<Pair<d, a>> commandQueue = new ArrayDeque();
    @Nullable
    private a defaultCommandContext;
    @Nullable
    private b hockeyAppConfig;
    private boolean subscribedToLifecycleEvents;

    public RNHockeyAppModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "RNHockeyApp";
    }

    @ReactMethod
    public void configure(String token, boolean autoSend, boolean attachLogsToCrash, int apiAuthType, String secret, am crashAttachmentProviderConfig, boolean enableAppNotTerminatingCleanlyDetection) {
        if (this.hockeyAppConfig == null) {
            FLog.i(TAG, "HockeyApp - initializing configuration");
            this.hockeyAppConfig = new b(token, apiAuthType, secret);
            this.defaultCommandContext = a.a(this.hockeyAppConfig);
            SkypeCrashManager.d().a(autoSend);
            SkypeCrashManager.c().a(attachLogsToCrash);
            SkypeCrashManager.a();
        }
    }

    @ReactMethod
    public void start() {
        if (isNotInitialized()) {
            warnNotInitialized("start");
        } else {
            executeCommand(d.e);
        }
    }

    @ReactMethod
    public void checkForUpdate() {
        if (isNotInitialized()) {
            warnNotInitialized("checkForUpdate");
        } else {
            executeCommand(d.b);
        }
    }

    @ReactMethod
    public void feedback() {
        if (isNotInitialized()) {
            warnNotInitialized("feedback");
        } else {
            executeCommand(d.c);
        }
    }

    @ReactMethod
    public void addMetadata(String metadata) {
        if (isNotInitialized()) {
            warnNotInitialized("addMetadata");
        } else {
            executeCommand(d.a, a.a(this.hockeyAppConfig, metadata));
        }
    }

    @ReactMethod
    public void generateTestCrash() {
        if (isNotInitialized()) {
            warnNotInitialized("generateTestCrash");
        } else {
            executeCommand(d.d);
        }
    }

    @ReactMethod
    public void didCrashInLastSession(final ae promise) {
        try {
            new Thread(new Runnable(this) {
                final /* synthetic */ RNHockeyAppModule b;

                public final void run() {
                    promise.a(Boolean.valueOf(SkypeCrashManager.b()));
                }
            }, TAG).start();
        } catch (Throwable e) {
            promise.a(e);
        }
    }

    public void onHostResume() {
        getReactApplicationContext().c(new Runnable(this) {
            final /* synthetic */ RNHockeyAppModule a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.drainCommandQueue();
            }
        });
    }

    public void onHostPause() {
    }

    public void onHostDestroy() {
    }

    private boolean isNotInitialized() {
        return this.hockeyAppConfig == null;
    }

    private void warnNotInitialized(String action) {
        FLog.w(TAG, action + " is ignored hockeyapp module is not initialized");
    }

    private void executeCommand(d command) {
        executeCommand(command, this.defaultCommandContext);
    }

    private void executeCommand(d command, a commandContext) {
        if (!command.a(getCurrentActivity(), commandContext)) {
            this.commandQueue.add(new Pair(command, commandContext));
            if (!this.subscribedToLifecycleEvents) {
                getReactApplicationContext().a((v) this);
                this.subscribedToLifecycleEvents = true;
            }
        }
    }

    private void drainCommandQueue() {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            Queue<Pair<d, a>> notExecutedCommands = new ArrayDeque();
            while (!this.commandQueue.isEmpty()) {
                Pair<d, a> commandPair = (Pair) this.commandQueue.poll();
                if (!((d) commandPair.first).a(activity, (a) commandPair.second)) {
                    notExecutedCommands.add(commandPair);
                }
            }
            this.commandQueue = notExecutedCommands;
            getReactApplicationContext().b((v) this);
            this.subscribedToLifecycleEvents = false;
        }
    }
}
