package kotlin.reflect.jvm.internal.impl.storage;

import defpackage.acuo;

public final class StorageKt {
    public static final <T> T getValue(NotNullLazyValue<? extends T> notNullLazyValue, Object obj, acuo<?> acuo) {
        return notNullLazyValue.invoke();
    }

    public static final <T> T getValue(NullableLazyValue<? extends T> nullableLazyValue, Object obj, acuo<?> acuo) {
        return nullableLazyValue.invoke();
    }
}
