package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public enum JvmPrimitiveType {
    BOOLEAN(PrimitiveType.BOOLEAN, "boolean", "Z", "java.lang.Boolean"),
    CHAR(PrimitiveType.CHAR, "char", "C", "java.lang.Character"),
    BYTE(PrimitiveType.BYTE, "byte", "B", "java.lang.Byte"),
    SHORT(PrimitiveType.SHORT, "short", "S", "java.lang.Short"),
    INT(PrimitiveType.INT, "int", "I", "java.lang.Integer"),
    FLOAT(PrimitiveType.FLOAT, "float", "F", "java.lang.Float"),
    LONG(PrimitiveType.LONG, "long", "J", "java.lang.Long"),
    DOUBLE(PrimitiveType.DOUBLE, "double", "D", "java.lang.Double");
    
    private static final Map<String, JvmPrimitiveType> TYPE_BY_DESC = null;
    private static final Map<String, JvmPrimitiveType> TYPE_BY_NAME = null;
    private static final Map<PrimitiveType, JvmPrimitiveType> TYPE_BY_PRIMITIVE_TYPE = null;
    private static final Set<FqName> WRAPPERS_CLASS_NAMES = null;
    private final String desc;
    private final String name;
    private final PrimitiveType primitiveType;
    private final FqName wrapperFqName;

    static {
        WRAPPERS_CLASS_NAMES = new HashSet();
        TYPE_BY_NAME = new HashMap();
        TYPE_BY_PRIMITIVE_TYPE = new EnumMap(PrimitiveType.class);
        TYPE_BY_DESC = new HashMap();
        JvmPrimitiveType[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            JvmPrimitiveType jvmPrimitiveType = values[i];
            WRAPPERS_CLASS_NAMES.add(jvmPrimitiveType.getWrapperFqName());
            TYPE_BY_NAME.put(jvmPrimitiveType.getJavaKeywordName(), jvmPrimitiveType);
            TYPE_BY_PRIMITIVE_TYPE.put(jvmPrimitiveType.getPrimitiveType(), jvmPrimitiveType);
            TYPE_BY_DESC.put(jvmPrimitiveType.getDesc(), jvmPrimitiveType);
            i++;
        }
    }

    public static JvmPrimitiveType get(String str) {
        JvmPrimitiveType jvmPrimitiveType = (JvmPrimitiveType) TYPE_BY_NAME.get(str);
        if (jvmPrimitiveType != null) {
            return jvmPrimitiveType;
        }
        throw new AssertionError("Non-primitive type name passed: ".concat(String.valueOf(str)));
    }

    public static JvmPrimitiveType get(PrimitiveType primitiveType) {
        return (JvmPrimitiveType) TYPE_BY_PRIMITIVE_TYPE.get(primitiveType);
    }

    private JvmPrimitiveType(PrimitiveType primitiveType, String str, String str2, String str3) {
        this.primitiveType = primitiveType;
        this.name = str;
        this.desc = str2;
        this.wrapperFqName = new FqName(str3);
    }

    public final PrimitiveType getPrimitiveType() {
        return this.primitiveType;
    }

    public final String getJavaKeywordName() {
        return this.name;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final FqName getWrapperFqName() {
        return this.wrapperFqName;
    }
}
