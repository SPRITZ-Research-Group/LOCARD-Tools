package defpackage;

import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader.Kind;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* renamed from: acxn */
public final /* synthetic */ class acxn {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[Kind.values().length];
        a = iArr;
        iArr[Kind.FILE_FACADE.ordinal()] = 1;
        a[Kind.MULTIFILE_CLASS_PART.ordinal()] = 2;
        a[Kind.MULTIFILE_CLASS.ordinal()] = 3;
    }
}
