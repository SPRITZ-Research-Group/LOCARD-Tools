package kotlin.reflect.jvm.internal.impl.resolve;

import defpackage.acry;
import java.util.Collection;
import kotlin.aa;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;

public final class VisibilityUtilKt {
    public static final CallableMemberDescriptor findMemberWithMaxVisibility(Collection<? extends CallableMemberDescriptor> collection) {
        int isEmpty = collection.isEmpty() ^ 1;
        if (aa.a && isEmpty == 0) {
            throw new AssertionError("Assertion failed");
        }
        CallableMemberDescriptor callableMemberDescriptor = null;
        for (CallableMemberDescriptor callableMemberDescriptor2 : collection) {
            if (callableMemberDescriptor != null) {
                Integer compare = Visibilities.compare(callableMemberDescriptor.getVisibility(), callableMemberDescriptor2.getVisibility());
                if (compare != null) {
                    if (compare.intValue() >= 0) {
                    }
                }
            }
            callableMemberDescriptor = callableMemberDescriptor2;
        }
        if (callableMemberDescriptor == null) {
            acry.a();
        }
        return callableMemberDescriptor;
    }
}
