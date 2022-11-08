package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

public interface DeserializationConfiguration {

    public final class DefaultImpls {
        public static boolean getReleaseCoroutines(DeserializationConfiguration deserializationConfiguration) {
            return false;
        }

        public static boolean getReportErrorsOnPreReleaseDependencies(DeserializationConfiguration deserializationConfiguration) {
            return false;
        }

        public static boolean getSkipMetadataVersionCheck(DeserializationConfiguration deserializationConfiguration) {
            return false;
        }

        public static boolean getTypeAliasesAllowed(DeserializationConfiguration deserializationConfiguration) {
            return true;
        }

        public static boolean isJvmPackageNameSupported(DeserializationConfiguration deserializationConfiguration) {
            return true;
        }
    }

    public final class Default implements DeserializationConfiguration {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        public final boolean getReleaseCoroutines() {
            return DefaultImpls.getReleaseCoroutines(this);
        }

        public final boolean getReportErrorsOnPreReleaseDependencies() {
            return DefaultImpls.getReportErrorsOnPreReleaseDependencies(this);
        }

        public final boolean getSkipMetadataVersionCheck() {
            return DefaultImpls.getSkipMetadataVersionCheck(this);
        }

        public final boolean getTypeAliasesAllowed() {
            return DefaultImpls.getTypeAliasesAllowed(this);
        }

        public final boolean isJvmPackageNameSupported() {
            return DefaultImpls.isJvmPackageNameSupported(this);
        }
    }

    boolean getReleaseCoroutines();

    boolean getReportErrorsOnPreReleaseDependencies();

    boolean getSkipMetadataVersionCheck();

    boolean getTypeAliasesAllowed();

    boolean isJvmPackageNameSupported();
}
