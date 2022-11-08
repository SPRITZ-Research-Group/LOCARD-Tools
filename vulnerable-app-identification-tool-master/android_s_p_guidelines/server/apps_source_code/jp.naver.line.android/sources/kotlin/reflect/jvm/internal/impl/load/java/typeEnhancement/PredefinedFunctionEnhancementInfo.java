package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import defpackage.acob;
import defpackage.acru;
import java.util.List;

public final class PredefinedFunctionEnhancementInfo {
    private final List<TypeEnhancementInfo> parametersInfo;
    private final TypeEnhancementInfo returnTypeInfo;

    public PredefinedFunctionEnhancementInfo() {
        this(null, null, 3, null);
    }

    public PredefinedFunctionEnhancementInfo(TypeEnhancementInfo typeEnhancementInfo, List<TypeEnhancementInfo> list) {
        this.returnTypeInfo = typeEnhancementInfo;
        this.parametersInfo = list;
    }

    public final TypeEnhancementInfo getReturnTypeInfo() {
        return this.returnTypeInfo;
    }

    public final List<TypeEnhancementInfo> getParametersInfo() {
        return this.parametersInfo;
    }

    public /* synthetic */ PredefinedFunctionEnhancementInfo(TypeEnhancementInfo typeEnhancementInfo, List list, int i, acru acru) {
        if ((i & 1) != 0) {
            typeEnhancementInfo = null;
        }
        if ((i & 2) != 0) {
            list = acob.a;
        }
        this(typeEnhancementInfo, list);
    }
}
