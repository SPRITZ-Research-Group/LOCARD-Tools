package com.skype.android.gen;

import com.skype.ExampleInMemoryObject;
import com.skype.ExampleInMemoryObject.ExampleInMemoryObjectIListener;
import com.skype.android.event.EventBus;
import com.skype.android.event.EventBusInstance;
import com.skype.android.event.ListenerError;
import com.skype.android.event.ListenerErrorReporter;

public class ExampleInMemoryObjectListener implements ExampleInMemoryObjectIListener {
    final EventBus eventBus = EventBusInstance.a();

    public static class OnChildrenChanged {
        private int[] _newChildren;
        private ExampleInMemoryObject _sender;

        public OnChildrenChanged(ExampleInMemoryObject sender, int[] newChildren) {
            this._sender = sender;
            this._newChildren = newChildren;
        }

        public ExampleInMemoryObject getSender() {
            return this._sender;
        }

        public int[] getNewChildren() {
            return this._newChildren;
        }
    }

    public static class OnNameChanged {
        private String _newName;
        private String _oldName;
        private ExampleInMemoryObject _sender;

        public OnNameChanged(ExampleInMemoryObject sender, String newName, String oldName) {
            this._sender = sender;
            this._newName = newName;
            this._oldName = oldName;
        }

        public ExampleInMemoryObject getSender() {
            return this._sender;
        }

        public String getNewName() {
            return this._newName;
        }

        public String getOldName() {
            return this._oldName;
        }
    }

    public void onChildrenChanged(ExampleInMemoryObject sender, int[] newChildren) {
        try {
            this.eventBus.sendEvent(new OnChildrenChanged(sender, newChildren));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }

    public void onNameChanged(ExampleInMemoryObject sender, String newName, String oldName) {
        try {
            this.eventBus.sendEvent(new OnNameChanged(sender, newName, oldName));
        } catch (Throwable t) {
            ListenerErrorReporter.a(new ListenerError(t));
        }
    }
}
