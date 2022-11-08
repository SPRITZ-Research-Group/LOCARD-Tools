package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

public final class VersionSpecificBehaviorKt {
    public static final boolean isVersionRequirementTableWrittenCorrectly(BinaryVersion binaryVersion) {
        return isKotlin1Dot4OrLater(binaryVersion);
    }

    public static final boolean isKotlin1Dot4OrLater(BinaryVersion binaryVersion) {
        return binaryVersion.getMajor() == 1 && binaryVersion.getMinor() >= 4;
    }
}
