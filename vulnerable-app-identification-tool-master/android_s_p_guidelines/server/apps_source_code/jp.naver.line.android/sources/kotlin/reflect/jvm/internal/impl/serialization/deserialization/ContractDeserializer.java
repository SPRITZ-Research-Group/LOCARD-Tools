package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.m;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;

public interface ContractDeserializer {
    public static final Companion Companion = Companion.$$INSTANCE;

    public final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final ContractDeserializer DEFAULT = new ContractDeserializer$Companion$DEFAULT$1();

        private Companion() {
        }

        public final ContractDeserializer getDEFAULT() {
            return DEFAULT;
        }
    }

    m<UserDataKey<?>, Object> deserializeContractFromFunction(Function function, FunctionDescriptor functionDescriptor, TypeTable typeTable, TypeDeserializer typeDeserializer);
}
