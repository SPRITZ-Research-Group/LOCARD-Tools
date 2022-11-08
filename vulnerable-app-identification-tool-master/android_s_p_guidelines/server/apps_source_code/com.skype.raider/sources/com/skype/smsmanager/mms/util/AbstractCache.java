package com.skype.smsmanager.mms.util;

import java.util.HashMap;

public abstract class AbstractCache<K, V> {
    private final HashMap<K, Object<V>> a = new HashMap();

    protected AbstractCache() {
    }
}
