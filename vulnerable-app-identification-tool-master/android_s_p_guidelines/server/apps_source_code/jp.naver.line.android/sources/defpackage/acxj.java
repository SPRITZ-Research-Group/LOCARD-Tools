package defpackage;

import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.types.Variance;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* renamed from: acxj */
public final /* synthetic */ class acxj {
    public static final /* synthetic */ int[] a;
    public static final /* synthetic */ int[] b;

    static {
        int[] iArr = new int[acun.values().length];
        a = iArr;
        iArr[acun.EXTENSION_RECEIVER.ordinal()] = 1;
        a[acun.INSTANCE.ordinal()] = 2;
        a[acun.VALUE.ordinal()] = 3;
        iArr = new int[Variance.values().length];
        b = iArr;
        iArr[Variance.INVARIANT.ordinal()] = 1;
        b[Variance.IN_VARIANCE.ordinal()] = 2;
        b[Variance.OUT_VARIANCE.ordinal()] = 3;
    }
}
