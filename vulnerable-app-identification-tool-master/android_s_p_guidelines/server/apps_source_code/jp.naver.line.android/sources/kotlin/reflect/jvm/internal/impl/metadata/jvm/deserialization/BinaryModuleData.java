package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.List;

public final class BinaryModuleData {
    private final List<String> annotations;

    public BinaryModuleData(List<String> list) {
        this.annotations = list;
    }
}
