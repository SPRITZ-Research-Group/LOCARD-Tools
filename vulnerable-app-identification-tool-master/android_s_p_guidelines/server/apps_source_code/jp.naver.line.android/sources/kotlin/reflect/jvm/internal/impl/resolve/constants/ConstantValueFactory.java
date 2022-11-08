package kotlin.reflect.jvm.internal.impl.resolve.constants;

import defpackage.acno;
import defpackage.acnz;
import defpackage.acob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class ConstantValueFactory {
    public static final ConstantValueFactory INSTANCE = new ConstantValueFactory();

    private ConstantValueFactory() {
    }

    public final ArrayValue createArrayValue(List<? extends ConstantValue<?>> list, KotlinType kotlinType) {
        return new ArrayValue(list, new ConstantValueFactory$createArrayValue$1(kotlinType));
    }

    public final ConstantValue<?> createConstantValue(Object obj) {
        if (obj instanceof Byte) {
            return new ByteValue(((Number) obj).byteValue());
        }
        if (obj instanceof Short) {
            return new ShortValue(((Number) obj).shortValue());
        }
        if (obj instanceof Integer) {
            return new IntValue(((Number) obj).intValue());
        }
        if (obj instanceof Long) {
            return new LongValue(((Number) obj).longValue());
        }
        if (obj instanceof Character) {
            return new CharValue(((Character) obj).charValue());
        }
        if (obj instanceof Float) {
            return new FloatValue(((Number) obj).floatValue());
        }
        if (obj instanceof Double) {
            return new DoubleValue(((Number) obj).doubleValue());
        }
        if (obj instanceof Boolean) {
            return new BooleanValue(((Boolean) obj).booleanValue());
        }
        if (obj instanceof String) {
            return new StringValue((String) obj);
        }
        if (obj instanceof byte[]) {
            List list;
            byte[] bArr = (byte[]) obj;
            int i = 0;
            switch (bArr.length) {
                case 0:
                    list = acob.a;
                    break;
                case 1:
                    list = Collections.singletonList(Byte.valueOf(bArr[0]));
                    break;
                default:
                    ArrayList arrayList = new ArrayList(bArr.length);
                    int length = bArr.length;
                    while (i < length) {
                        arrayList.add(Byte.valueOf(bArr[i]));
                        i++;
                    }
                    list = arrayList;
                    break;
            }
            return createArrayValue(list, PrimitiveType.BYTE);
        } else if (obj instanceof short[]) {
            return createArrayValue(acno.a((short[]) obj), PrimitiveType.SHORT);
        } else {
            if (obj instanceof int[]) {
                return createArrayValue(acno.b((int[]) obj), PrimitiveType.INT);
            }
            if (obj instanceof long[]) {
                return createArrayValue(acno.b((long[]) obj), PrimitiveType.LONG);
            }
            if (obj instanceof char[]) {
                return createArrayValue(acno.a((char[]) obj), PrimitiveType.CHAR);
            }
            if (obj instanceof float[]) {
                return createArrayValue(acno.a((float[]) obj), PrimitiveType.FLOAT);
            }
            if (obj instanceof double[]) {
                return createArrayValue(acno.a((double[]) obj), PrimitiveType.DOUBLE);
            }
            if (obj instanceof boolean[]) {
                return createArrayValue(acno.a((boolean[]) obj), PrimitiveType.BOOLEAN);
            }
            return obj == null ? new NullValue() : null;
        }
    }

    private final ArrayValue createArrayValue(List<?> list, PrimitiveType primitiveType) {
        Collection arrayList = new ArrayList();
        for (Object createConstantValue : acnz.k((Iterable) list)) {
            ConstantValue createConstantValue2 = createConstantValue(createConstantValue);
            if (createConstantValue2 != null) {
                arrayList.add(createConstantValue2);
            }
        }
        return new ArrayValue((List) arrayList, new ConstantValueFactory$createArrayValue$3(primitiveType));
    }
}
