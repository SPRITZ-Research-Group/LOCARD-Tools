package kotlin.reflect.jvm.internal.impl.descriptors;

import defpackage.acru;

public enum Modality {
    ;
    
    public static final Companion Companion = null;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }

        public final Modality convertFromFlags(boolean z, boolean z2) {
            if (z) {
                return Modality.ABSTRACT;
            }
            if (z2) {
                return Modality.OPEN;
            }
            return Modality.FINAL;
        }
    }

    static {
        Companion = new Companion();
    }
}
