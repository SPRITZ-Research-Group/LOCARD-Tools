package com.skype.android.gen;

import com.skype.GI;
import com.skype.GI.CONNSTATUS;
import com.skype.GI.FILEERROR;
import com.skype.GI.GIIListener;
import com.skype.GI.LIBSTATUS;
import com.skype.GI.PROXYTYPE;
import com.skype.android.event.EventBus;
import com.skype.android.event.EventBusInstance;
import com.skype.android.event.ListenerError;
import com.skype.android.event.ListenerErrorReporter;

public class GIListener implements GIIListener {
    final EventBus eventBus = EventBusInstance.a();

    public static class OnConnStatusChange {
        private CONNSTATUS _connStatus;
        private GI _sender;

        public OnConnStatusChange(GI sender, CONNSTATUS connStatus) {
            this._sender = sender;
            this._connStatus = connStatus;
        }

        public GI getSender() {
            return this._sender;
        }

        public CONNSTATUS getConnStatus() {
            return this._connStatus;
        }
    }

    public static class OnFileError {
        private FILEERROR _error;
        private GI _sender;

        public OnFileError(GI sender, FILEERROR error) {
            this._sender = sender;
            this._error = error;
        }

        public GI getSender() {
            return this._sender;
        }

        public FILEERROR getError() {
            return this._error;
        }
    }

    public static class OnLibStatusChange {
        private LIBSTATUS _newStatus;
        private GI _sender;

        public OnLibStatusChange(GI sender, LIBSTATUS newStatus) {
            this._sender = sender;
            this._newStatus = newStatus;
        }

        public GI getSender() {
            return this._sender;
        }

        public LIBSTATUS getNewStatus() {
            return this._newStatus;
        }
    }

    public static class OnNodeinfoChange {
        private byte[] _nodeinfo;
        private GI _sender;

        public OnNodeinfoChange(GI sender, byte[] nodeinfo) {
            this._sender = sender;
            this._nodeinfo = nodeinfo;
        }

        public GI getSender() {
            return this._sender;
        }

        public byte[] getNodeinfo() {
            return this._nodeinfo;
        }
    }

    public static class OnProxyAuthenticationFailure {
        private PROXYTYPE _proxyType;
        private GI _sender;

        public OnProxyAuthenticationFailure(GI sender, PROXYTYPE proxyType) {
            this._sender = sender;
            this._proxyType = proxyType;
        }

        public GI getSender() {
            return this._sender;
        }

        public PROXYTYPE getProxyType() {
            return this._proxyType;
        }
    }

    public void onConnStatusChange(GI sender, CONNSTATUS connStatus) {
        try {
            this.eventBus.sendEvent(new OnConnStatusChange(sender, connStatus));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onFileError(GI sender, FILEERROR error) {
        try {
            this.eventBus.sendEvent(new OnFileError(sender, error));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onLibStatusChange(GI sender, LIBSTATUS newStatus) {
        try {
            this.eventBus.sendEvent(new OnLibStatusChange(sender, newStatus));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onNodeinfoChange(GI sender, byte[] nodeinfo) {
        try {
            this.eventBus.sendEvent(new OnNodeinfoChange(sender, nodeinfo));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onProxyAuthenticationFailure(GI sender, PROXYTYPE proxyType) {
        try {
            this.eventBus.sendEvent(new OnProxyAuthenticationFailure(sender, proxyType));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }
}
