package kotlin.reflect.jvm.internal.impl.renderer;

import defpackage.acno;
import defpackage.acnz;
import defpackage.acru;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public enum DescriptorRendererModifier {
    ;
    
    public static final Set<DescriptorRendererModifier> ALL = null;
    public static final Companion Companion = null;
    public static final Set<DescriptorRendererModifier> DEFAULTS = null;
    private final boolean includeByDefault;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }
    }

    private DescriptorRendererModifier(boolean z) {
        this.includeByDefault = z;
    }

    static {
        Companion = new Companion();
        DescriptorRendererModifier[] values = values();
        Collection arrayList = new ArrayList();
        int length = values.length;
        int i;
        while (i < length) {
            DescriptorRendererModifier descriptorRendererModifier = values[i];
            if (descriptorRendererModifier.includeByDefault) {
                arrayList.add(descriptorRendererModifier);
            }
            i++;
        }
        DEFAULTS = acnz.m((List) arrayList);
        ALL = acno.h(values());
    }
}
