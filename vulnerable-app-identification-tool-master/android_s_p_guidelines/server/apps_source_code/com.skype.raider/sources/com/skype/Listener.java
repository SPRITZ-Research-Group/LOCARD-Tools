package com.skype;

public interface Listener {

    public interface ListenerIIListener {
        void onChange(Listener listener, String str);
    }

    void addIListener(ListenerIIListener listenerIIListener);

    void removeIListener(ListenerIIListener listenerIIListener);
}
