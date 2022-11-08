package com.skype.android.gen;

import com.skype.InMemoryObject;
import com.skype.InMemoryObject.InMemoryObjectIListener;
import com.skype.android.event.EventBus;
import com.skype.android.event.EventBusInstance;
import com.skype.android.event.ListenerError;
import com.skype.android.event.ListenerErrorReporter;

public class InMemoryObjectListener implements InMemoryObjectIListener {
    final EventBus eventBus = EventBusInstance.a();

    public static class OnDummy {
        private InMemoryObject _sender;

        public OnDummy(InMemoryObject sender) {
            this._sender = sender;
        }

        public InMemoryObject getSender() {
            return this._sender;
        }
    }

    public void onDummy(InMemoryObject sender) {
        try {
            this.eventBus.sendEvent(new OnDummy(sender));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }
}
