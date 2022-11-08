package com.skype;

import com.skype.Listener.ListenerIIListener;
import java.util.HashSet;
import java.util.Set;

public class ListenerImpl implements Listener, NativeListenable {
    private final Set<ListenerIIListener> m_listeners;
    protected int m_nativeObject;

    public native void initializeListener();

    protected ListenerImpl(int nativeObject) {
        this(SkypeFactory.getInstance(), nativeObject);
    }

    protected ListenerImpl(ObjectInterfaceFactory factory, int nativeObject) {
        this.m_listeners = new HashSet();
        this.m_nativeObject = nativeObject;
    }

    public void addIListener(ListenerIIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeIListener(ListenerIIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }

    public void onChange(byte[] key) {
        synchronized (this.m_listeners) {
            for (ListenerIIListener onChange : this.m_listeners) {
                onChange.onChange(this, NativeStringConvert.ConvertFromNativeBytes(key));
            }
        }
    }
}
