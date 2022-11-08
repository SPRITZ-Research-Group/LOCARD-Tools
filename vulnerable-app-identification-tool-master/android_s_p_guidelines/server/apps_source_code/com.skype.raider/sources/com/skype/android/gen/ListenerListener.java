package com.skype.android.gen;

import com.skype.Listener;
import com.skype.Listener.ListenerIIListener;
import com.skype.android.event.EventBus;
import com.skype.android.event.EventBusInstance;
import com.skype.android.event.ListenerError;
import com.skype.android.event.ListenerErrorReporter;

public class ListenerListener implements ListenerIIListener {
    final EventBus eventBus = EventBusInstance.a();

    public static class OnChange {
        private String _key;
        private Listener _sender;

        public OnChange(Listener sender, String key) {
            this._sender = sender;
            this._key = key;
        }

        public Listener getSender() {
            return this._sender;
        }

        public String getKey() {
            return this._key;
        }
    }

    public void onChange(Listener sender, String key) {
        try {
            this.eventBus.sendEvent(new OnChange(sender, key));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }
}
