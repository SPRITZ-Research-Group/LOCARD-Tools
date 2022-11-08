package com.skype.commandinvoker;

import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.common.e;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.microsoft.skype.a.a.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RNCommandInvokerModule extends ReactContextBaseJavaModule {
    private static final String RN_CLASS = "RNCommandInvoker";
    private static final com.microsoft.skype.a.a commandInvokerQueue = com.microsoft.skype.a.a.a(RN_CLASS, b.DEFAULT);
    private List<a> pendingEvents = new ArrayList();
    private boolean readyToReceiveEvents = false;

    private class a {
        public final String a;
        public final ar b;
        final /* synthetic */ RNCommandInvokerModule c;

        public a(RNCommandInvokerModule rNCommandInvokerModule, String name, ar params) {
            this.c = rNCommandInvokerModule;
            this.a = name;
            this.b = params;
        }
    }

    public RNCommandInvokerModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return RN_CLASS;
    }

    public Map<String, Object> getConstants() {
        com.facebook.react.common.e.a<String, Object> builder = e.a();
        builder.a("LoginEventName", "CommandInvoker-LoginEvent");
        builder.a("LogoutEventName", "CommandInvoker-LogoutEvent");
        builder.a("AnswerCallEventName", "CommandInvoker-AnswerCallEvent");
        builder.a("EndCallEventName", "CommandInvoker-EndCallEvent");
        builder.a("StartCallEventName", "CommandInvoker-StartCallEvent");
        builder.a("MuteUnmuteCallEventName", "CommandInvoker-MuteUnmuteCallEvent");
        return builder.a();
    }

    @ReactMethod
    public void beginReceivingEvents() {
        commandInvokerQueue.b(new Runnable(this) {
            final /* synthetic */ RNCommandInvokerModule a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.readyToReceiveEvents = true;
                for (a event : this.a.pendingEvents) {
                    this.a.intSendEvent(event);
                }
                this.a.pendingEvents.clear();
            }
        });
    }

    public void sendEvent(final String eventName, @Nullable final ar params) {
        commandInvokerQueue.b(new Runnable(this) {
            final /* synthetic */ RNCommandInvokerModule c;

            public final void run() {
                a event = new a(this.c, eventName, params);
                if (this.c.readyToReceiveEvents) {
                    this.c.intSendEvent(event);
                    return;
                }
                FLog.i(RNCommandInvokerModule.RN_CLASS, "Enqueueing %s", eventName);
                this.c.pendingEvents.add(event);
            }
        });
    }

    private void intSendEvent(a commandEvent) {
        com.facebook.infer.annotation.a.a(com.microsoft.skype.a.a.a(commandInvokerQueue));
        FLog.i(RN_CLASS, "Sending %s", commandEvent.a);
        ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit(commandEvent.a, commandEvent.b);
    }
}
