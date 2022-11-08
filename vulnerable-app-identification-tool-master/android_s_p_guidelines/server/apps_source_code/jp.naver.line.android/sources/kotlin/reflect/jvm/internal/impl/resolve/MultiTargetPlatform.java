package kotlin.reflect.jvm.internal.impl.resolve;

import defpackage.acru;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor.Capability;

public abstract class MultiTargetPlatform implements Comparable<MultiTargetPlatform> {
    public static final Capability<MultiTargetPlatform> CAPABILITY = new Capability("MULTI_TARGET_PLATFORM");
    public static final Companion Companion = new Companion();

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }
    }

    private MultiTargetPlatform() {
    }
}
