package com.skype.android.gen;

import com.skype.Account;
import com.skype.Account.AccountIListener;
import com.skype.ObjectInterface;
import com.skype.ObjectInterface.ObjectInterfaceIListener;
import com.skype.PROPKEY;
import com.skype.android.event.EventBus;
import com.skype.android.event.EventBusInstance;
import com.skype.android.event.ListenerError;
import com.skype.android.event.ListenerErrorReporter;

public class AccountListener implements AccountIListener, ObjectInterfaceIListener {
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

    public static class OnSkypeTokenRequired {
        private String _invalidToken;
        private Account _sender;

        public OnSkypeTokenRequired(Account sender, String invalidToken) {
            this._sender = sender;
            this._invalidToken = invalidToken;
        }

        public Account getSender() {
            return this._sender;
        }

        public String getInvalidToken() {
            return this._invalidToken;
        }
    }

    public void onSkypeTokenRequired(Account sender, String invalidToken) {
        try {
            this.eventBus.sendEvent(new OnSkypeTokenRequired(sender, invalidToken));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
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
