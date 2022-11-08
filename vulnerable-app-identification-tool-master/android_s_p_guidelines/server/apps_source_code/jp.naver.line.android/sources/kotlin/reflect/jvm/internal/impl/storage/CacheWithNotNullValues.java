package kotlin.reflect.jvm.internal.impl.storage;

import defpackage.acqq;

public interface CacheWithNotNullValues<K, V> {
    V computeIfAbsent(K k, acqq<? extends V> acqq);
}
