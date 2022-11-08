package com.skype.android.gen;

import com.skype.Call.CallIListener;
import com.skype.ObjectInterface;
import com.skype.ObjectInterface.ObjectInterfaceIListener;
import com.skype.PROPKEY;
import com.skype.android.event.EventBus;
import com.skype.android.event.EventBusInstance;
import com.skype.android.event.ListenerError;
import com.skype.android.event.ListenerErrorReporter;

public class CallListener implements CallIListener, ObjectInterfaceIListener {
    final EventBus eventBus = EventBusInstance.a();

    public static class OnPropertyChange {
        private PROPKEY _propKey;
        private ObjectInterface _sender;

        public OnPropertyChange(ObjectInterface sender, PROPKEY propKey) {
            this._sender = sender;
            this._propKey = propKey;
        }

        public ObjectInterface getSender() {
            return this._sender;
        }

        public PROPKEY getPropKey() {
            return this._propKey;
        }
    }

    public void onPropertyChange(ObjectInterface sender, PROPKEY propKey) {
        try {
            this.eventBus.sendEvent(new OnPropertyChange(sender, propKey));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(propKey, t));
        }
    }
}
