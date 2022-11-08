package kotlin.reflect.jvm.internal.impl.name;

public class SpecialNames {
    public static final Name ANONYMOUS_FUNCTION = Name.special("<anonymous>");
    public static final Name DEFAULT_NAME_FOR_COMPANION_OBJECT = Name.identifier("Companion");
    public static final Name NO_NAME_PROVIDED = Name.special("<no name provided>");
    public static final Name ROOT_PACKAGE = Name.special("<root package>");
    public static final Name SAFE_IDENTIFIER_FOR_NO_NAME = Name.identifier("no_name_in_PSI_3d19d79d_1ba9_4cd0_b7f5_b46aa3cd5d40");

    public static Name safeIdentifier(Name name) {
        return (name == null || name.isSpecial()) ? SAFE_IDENTIFIER_FOR_NO_NAME : name;
    }

    public static boolean isSafeIdentifier(Name name) {
        return (name.asString().isEmpty() || name.isSpecial()) ? false : true;
    }
}
