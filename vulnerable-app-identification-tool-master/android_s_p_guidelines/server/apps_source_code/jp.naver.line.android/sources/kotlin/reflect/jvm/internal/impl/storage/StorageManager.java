package kotlin.reflect.jvm.internal.impl.storage;

import defpackage.acqq;
import defpackage.acqr;
import kotlin.y;

public interface StorageManager {
    <K, V> CacheWithNotNullValues<K, V> createCacheWithNotNullValues();

    <T> NotNullLazyValue<T> createLazyValue(acqq<? extends T> acqq);

    <T> NotNullLazyValue<T> createLazyValueWithPostCompute(acqq<? extends T> acqq, acqr<? super Boolean, ? extends T> acqr, acqr<? super T, y> acqr2);

    <K, V> MemoizedFunctionToNotNull<K, V> createMemoizedFunction(acqr<? super K, ? extends V> acqr);

    <K, V> MemoizedFunctionToNullable<K, V> createMemoizedFunctionWithNullableValues(acqr<? super K, ? extends V> acqr);

    <T> NullableLazyValue<T> createNullableLazyValue(acqq<? extends T> acqq);

    <T> NotNullLazyValue<T> createRecursionTolerantLazyValue(acqq<? extends T> acqq, T t);
}
