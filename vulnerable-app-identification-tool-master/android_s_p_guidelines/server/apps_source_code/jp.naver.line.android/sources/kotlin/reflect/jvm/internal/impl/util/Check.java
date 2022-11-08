package kotlin.reflect.jvm.internal.impl.util;

import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;

public interface Check {

    public final class DefaultImpls {
        public static String invoke(Check check, FunctionDescriptor functionDescriptor) {
            return !check.check(functionDescriptor) ? check.getDescription() : null;
        }
    }

    boolean check(FunctionDescriptor functionDescriptor);

    String getDescription();

    String invoke(FunctionDescriptor functionDescriptor);
}
