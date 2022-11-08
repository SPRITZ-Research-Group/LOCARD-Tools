package defpackage;

import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.types.Variance;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* renamed from: acwz */
public final /* synthetic */ class acwz {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[Variance.values().length];
        a = iArr;
        iArr[Variance.INVARIANT.ordinal()] = 1;
        a[Variance.IN_VARIANCE.ordinal()] = 2;
        a[Variance.OUT_VARIANCE.ordinal()] = 3;
    }
}
