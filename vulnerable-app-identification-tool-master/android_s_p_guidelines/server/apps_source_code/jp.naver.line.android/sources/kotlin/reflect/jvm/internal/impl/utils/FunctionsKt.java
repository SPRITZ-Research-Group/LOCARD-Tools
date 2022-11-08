package kotlin.reflect.jvm.internal.impl.utils;

import defpackage.acqr;
import defpackage.acrc;
import defpackage.acrg;
import kotlin.y;

public final class FunctionsKt {
    private static final acqr<Object, Object> ALWAYS_NULL = FunctionsKt$ALWAYS_NULL$1.INSTANCE;
    private static final acqr<Object, Boolean> ALWAYS_TRUE = FunctionsKt$ALWAYS_TRUE$1.INSTANCE;
    private static final acqr<Object, y> DO_NOTHING = FunctionsKt$DO_NOTHING$1.INSTANCE;
    private static final acrc<Object, Object, y> DO_NOTHING_2 = FunctionsKt$DO_NOTHING_2$1.INSTANCE;
    private static final acrg<Object, Object, Object, y> DO_NOTHING_3 = FunctionsKt$DO_NOTHING_3$1.INSTANCE;
    private static final acqr<Object, Object> IDENTITY = FunctionsKt$IDENTITY$1.INSTANCE;

    public static final <T> acqr<T, Boolean> alwaysTrue() {
        return ALWAYS_TRUE;
    }

    public static final acrc<Object, Object, y> getDO_NOTHING_2() {
        return DO_NOTHING_2;
    }

    public static final acrg<Object, Object, Object, y> getDO_NOTHING_3() {
        return DO_NOTHING_3;
    }
}
