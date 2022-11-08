package kotlin.reflect.jvm.internal.impl.util;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.util.CheckResult.IllegalFunctionName;

public abstract class AbstractModifierChecks {
    public abstract List<Checks> getChecks$descriptors();

    public final CheckResult check(FunctionDescriptor functionDescriptor) {
        for (Checks checks : getChecks$descriptors()) {
            if (checks.isApplicable(functionDescriptor)) {
                return checks.checkAll(functionDescriptor);
            }
        }
        return IllegalFunctionName.INSTANCE;
    }
}
