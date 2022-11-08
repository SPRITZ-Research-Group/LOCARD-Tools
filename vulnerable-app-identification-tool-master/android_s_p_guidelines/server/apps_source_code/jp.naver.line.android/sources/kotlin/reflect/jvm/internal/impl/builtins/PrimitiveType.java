package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

public enum PrimitiveType {
    BOOLEAN("Boolean"),
    CHAR("Char"),
    BYTE("Byte"),
    SHORT("Short"),
    INT("Int"),
    FLOAT("Float"),
    LONG("Long"),
    DOUBLE("Double");
    
    public static final Set<PrimitiveType> NUMBER_TYPES = null;
    private FqName arrayTypeFqName;
    private final Name arrayTypeName;
    private FqName typeFqName;
    private final Name typeName;

    static {
        NUMBER_TYPES = Collections.unmodifiableSet(EnumSet.of(CHAR, new PrimitiveType[]{BYTE, SHORT, INT, FLOAT, LONG, DOUBLE}));
    }

    private PrimitiveType(String str) {
        this.typeFqName = null;
        this.arrayTypeFqName = null;
        this.typeName = Name.identifier(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("Array");
        this.arrayTypeName = Name.identifier(stringBuilder.toString());
    }

    public final Name getTypeName() {
        return this.typeName;
    }

    public final FqName getTypeFqName() {
        if (this.typeFqName != null) {
            return this.typeFqName;
        }
        this.typeFqName = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME.child(this.typeName);
        return this.typeFqName;
    }

    public final Name getArrayTypeName() {
        return this.arrayTypeName;
    }

    public final FqName getArrayTypeFqName() {
        if (this.arrayTypeFqName != null) {
            return this.arrayTypeFqName;
        }
        this.arrayTypeFqName = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME.child(this.arrayTypeName);
        return this.arrayTypeFqName;
    }
}
