package kotlin.reflect.jvm.internal.impl.resolve;

import defpackage.acnz;
import defpackage.acqr;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

public final class OverridingUtilsKt {
    public static final <H> Collection<H> selectMostSpecificInEachOverridableGroup(Collection<? extends H> collection, acqr<? super H, ? extends CallableDescriptor> acqr) {
        if (collection.size() <= 1) {
            return collection;
        }
        LinkedList linkedList = new LinkedList(collection);
        SmartSet create = SmartSet.Companion.create();
        while (true) {
            Collection collection2 = linkedList;
            if ((collection2.isEmpty() ^ 1) == 0) {
                return create;
            }
            Object e = acnz.e((List) linkedList);
            SmartSet create2 = SmartSet.Companion.create();
            collection2 = OverridingUtil.extractMembersOverridableInBothWays(e, collection2, acqr, new OverridingUtilsKt$selectMostSpecificInEachOverridableGroup$overridableGroup$1(create2));
            if (collection2.size() == 1 && create2.isEmpty()) {
                create.add(acnz.e((Iterable) collection2));
            } else {
                e = OverridingUtil.selectMostSpecificMember(collection2, acqr);
                CallableDescriptor callableDescriptor = (CallableDescriptor) acqr.invoke(e);
                for (Object next : collection2) {
                    if (!OverridingUtil.isMoreSpecific(callableDescriptor, (CallableDescriptor) acqr.invoke(next))) {
                        create2.add(next);
                    }
                }
                Collection collection3 = create2;
                if ((collection3.isEmpty() ^ 1) != 0) {
                    create.addAll(collection3);
                }
                create.add(e);
            }
        }
    }

    public static final <D extends CallableDescriptor> void retainMostSpecificInEachOverridableGroup(Collection<D> collection) {
        Collection selectMostSpecificInEachOverridableGroup = selectMostSpecificInEachOverridableGroup(collection, OverridingUtilsKt$retainMostSpecificInEachOverridableGroup$newResult$1.INSTANCE);
        if (collection.size() != selectMostSpecificInEachOverridableGroup.size()) {
            collection.retainAll(selectMostSpecificInEachOverridableGroup);
        }
    }
}
