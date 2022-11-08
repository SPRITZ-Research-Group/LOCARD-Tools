package kotlin.reflect.jvm.internal.impl.util;

import defpackage.acru;

public abstract class CheckResult {
    private final boolean isSuccess;

    public final class IllegalFunctionName extends CheckResult {
        public static final IllegalFunctionName INSTANCE = new IllegalFunctionName();

        private IllegalFunctionName() {
            super(false, null);
        }
    }

    public final class IllegalSignature extends CheckResult {
        private final String error;

        public IllegalSignature(String str) {
            super(false, null);
            this.error = str;
        }
    }

    public final class SuccessCheck extends CheckResult {
        public static final SuccessCheck INSTANCE = new SuccessCheck();

        private SuccessCheck() {
            super(true, null);
        }
    }

    private CheckResult(boolean z) {
        this.isSuccess = z;
    }

    public /* synthetic */ CheckResult(boolean z, acru acru) {
        this(z);
    }

    public final boolean isSuccess() {
        return this.isSuccess;
    }
}
