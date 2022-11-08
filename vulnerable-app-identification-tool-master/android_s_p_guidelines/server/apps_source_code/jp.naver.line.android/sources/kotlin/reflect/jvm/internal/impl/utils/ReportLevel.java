package kotlin.reflect.jvm.internal.impl.utils;

import defpackage.acru;

public enum ReportLevel {
    ;
    
    public static final Companion Companion = null;
    private final String description;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }
    }

    private ReportLevel(String str) {
        this.description = str;
    }

    public final String getDescription() {
        return this.description;
    }

    static {
        Companion = new Companion();
    }

    public final boolean isWarning() {
        return ((ReportLevel) this) == WARN;
    }

    public final boolean isIgnore() {
        return ((ReportLevel) this) == IGNORE;
    }
}
