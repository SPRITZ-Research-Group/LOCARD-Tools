package kotlin.reflect.jvm.internal.impl.name;

import defpackage.acry;
import kotlin.v;

public final class FqNamesUtilKt {

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[State.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[State.BEGINNING.ordinal()] = 1;
            $EnumSwitchMapping$0[State.AFTER_DOT.ordinal()] = 2;
            $EnumSwitchMapping$0[State.MIDDLE.ordinal()] = 3;
        }
    }

    public static final boolean isSubpackageOf(FqName fqName, FqName fqName2) {
        if (acry.a((Object) fqName, (Object) fqName2) || fqName2.isRoot()) {
            return true;
        }
        return isSubpackageOf(fqName.asString(), fqName2.asString());
    }

    public static final FqName tail(FqName fqName, FqName fqName2) {
        if (!isSubpackageOf(fqName, fqName2) || fqName2.isRoot()) {
            return fqName;
        }
        if (acry.a((Object) fqName, (Object) fqName2)) {
            return FqName.ROOT;
        }
        String asString = fqName.asString();
        int length = fqName2.asString().length() + 1;
        if (asString != null) {
            return new FqName(asString.substring(length));
        }
        throw new v("null cannot be cast to non-null type java.lang.String");
    }

    public static final boolean isValidJavaFqName(String str) {
        if (str == null) {
            return false;
        }
        State state = State.BEGINNING;
        int length = str.length();
        State state2 = state;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (WhenMappings.$EnumSwitchMapping$0[state2.ordinal()]) {
                case 1:
                case 2:
                    if (Character.isJavaIdentifierPart(charAt)) {
                        state2 = State.MIDDLE;
                        break;
                    }
                    return false;
                case 3:
                    if (charAt != '.') {
                        if (Character.isJavaIdentifierPart(charAt)) {
                            break;
                        }
                        return false;
                    }
                    state2 = State.AFTER_DOT;
                    break;
                default:
                    break;
            }
        }
        if (state2 != State.AFTER_DOT) {
            return true;
        }
        return false;
    }

    private static final boolean isSubpackageOf(String str, String str2) {
        return str.startsWith(str2) && str.charAt(str2.length()) == '.';
    }
}
