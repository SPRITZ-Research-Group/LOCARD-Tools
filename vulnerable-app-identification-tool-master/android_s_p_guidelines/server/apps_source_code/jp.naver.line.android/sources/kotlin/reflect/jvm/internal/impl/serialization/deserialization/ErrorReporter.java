package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;

public interface ErrorReporter {
    public static final ErrorReporter DO_NOTHING = new ErrorReporter() {
        public final void reportCannotInferVisibility(CallableMemberDescriptor callableMemberDescriptor) {
        }

        public final void reportIncompleteHierarchy(ClassDescriptor classDescriptor, List<String> list) {
        }
    };

    void reportCannotInferVisibility(CallableMemberDescriptor callableMemberDescriptor);

    void reportIncompleteHierarchy(ClassDescriptor classDescriptor, List<String> list);
}
