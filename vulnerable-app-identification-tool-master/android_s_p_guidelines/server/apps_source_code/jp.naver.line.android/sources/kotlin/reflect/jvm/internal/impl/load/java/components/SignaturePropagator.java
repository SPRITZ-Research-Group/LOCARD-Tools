package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public interface SignaturePropagator {
    public static final SignaturePropagator DO_NOTHING = new SignaturePropagator() {
        public final PropagatedSignature resolvePropagatedSignature(JavaMethod javaMethod, ClassDescriptor classDescriptor, KotlinType kotlinType, KotlinType kotlinType2, List<ValueParameterDescriptor> list, List<TypeParameterDescriptor> list2) {
            return new PropagatedSignature(kotlinType, kotlinType2, list, list2, Collections.emptyList(), false);
        }

        public final void reportSignatureErrors(CallableMemberDescriptor callableMemberDescriptor, List<String> list) {
            throw new UnsupportedOperationException("Should not be called");
        }
    };

    public class PropagatedSignature {
        private final boolean hasStableParameterNames;
        private final KotlinType receiverType;
        private final KotlinType returnType;
        private final List<String> signatureErrors;
        private final List<TypeParameterDescriptor> typeParameters;
        private final List<ValueParameterDescriptor> valueParameters;

        public PropagatedSignature(KotlinType kotlinType, KotlinType kotlinType2, List<ValueParameterDescriptor> list, List<TypeParameterDescriptor> list2, List<String> list3, boolean z) {
            this.returnType = kotlinType;
            this.receiverType = kotlinType2;
            this.valueParameters = list;
            this.typeParameters = list2;
            this.signatureErrors = list3;
            this.hasStableParameterNames = z;
        }

        public KotlinType getReturnType() {
            return this.returnType;
        }

        public KotlinType getReceiverType() {
            return this.receiverType;
        }

        public List<ValueParameterDescriptor> getValueParameters() {
            return this.valueParameters;
        }

        public List<TypeParameterDescriptor> getTypeParameters() {
            return this.typeParameters;
        }

        public boolean hasStableParameterNames() {
            return this.hasStableParameterNames;
        }

        public List<String> getErrors() {
            return this.signatureErrors;
        }
    }

    void reportSignatureErrors(CallableMemberDescriptor callableMemberDescriptor, List<String> list);

    PropagatedSignature resolvePropagatedSignature(JavaMethod javaMethod, ClassDescriptor classDescriptor, KotlinType kotlinType, KotlinType kotlinType2, List<ValueParameterDescriptor> list, List<TypeParameterDescriptor> list2);
}
