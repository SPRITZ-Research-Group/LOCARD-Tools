package com.google.android.gms.internal.clearcut;

import java.lang.reflect.Type;

public enum ad {
    DOUBLE(0, af.SCALAR, ap.DOUBLE),
    FLOAT(1, af.SCALAR, ap.FLOAT),
    INT64(2, af.SCALAR, ap.LONG),
    UINT64(3, af.SCALAR, ap.LONG),
    INT32(4, af.SCALAR, ap.INT),
    FIXED64(5, af.SCALAR, ap.LONG),
    FIXED32(6, af.SCALAR, ap.INT),
    BOOL(7, af.SCALAR, ap.BOOLEAN),
    STRING(8, af.SCALAR, ap.STRING),
    MESSAGE(9, af.SCALAR, ap.MESSAGE),
    BYTES(10, af.SCALAR, ap.BYTE_STRING),
    UINT32(11, af.SCALAR, ap.INT),
    ENUM(12, af.SCALAR, ap.ENUM),
    SFIXED32(13, af.SCALAR, ap.INT),
    SFIXED64(14, af.SCALAR, ap.LONG),
    SINT32(15, af.SCALAR, ap.INT),
    SINT64(16, af.SCALAR, ap.LONG),
    GROUP(17, af.SCALAR, ap.MESSAGE),
    DOUBLE_LIST(18, af.VECTOR, ap.DOUBLE),
    FLOAT_LIST(19, af.VECTOR, ap.FLOAT),
    INT64_LIST(20, af.VECTOR, ap.LONG),
    UINT64_LIST(21, af.VECTOR, ap.LONG),
    INT32_LIST(22, af.VECTOR, ap.INT),
    FIXED64_LIST(23, af.VECTOR, ap.LONG),
    FIXED32_LIST(24, af.VECTOR, ap.INT),
    BOOL_LIST(25, af.VECTOR, ap.BOOLEAN),
    STRING_LIST(26, af.VECTOR, ap.STRING),
    MESSAGE_LIST(27, af.VECTOR, ap.MESSAGE),
    BYTES_LIST(28, af.VECTOR, ap.BYTE_STRING),
    UINT32_LIST(29, af.VECTOR, ap.INT),
    ENUM_LIST(30, af.VECTOR, ap.ENUM),
    SFIXED32_LIST(31, af.VECTOR, ap.INT),
    SFIXED64_LIST(32, af.VECTOR, ap.LONG),
    SINT32_LIST(33, af.VECTOR, ap.INT),
    SINT64_LIST(34, af.VECTOR, ap.LONG),
    DOUBLE_LIST_PACKED(35, af.PACKED_VECTOR, ap.DOUBLE),
    FLOAT_LIST_PACKED(36, af.PACKED_VECTOR, ap.FLOAT),
    INT64_LIST_PACKED(37, af.PACKED_VECTOR, ap.LONG),
    UINT64_LIST_PACKED(38, af.PACKED_VECTOR, ap.LONG),
    INT32_LIST_PACKED(39, af.PACKED_VECTOR, ap.INT),
    FIXED64_LIST_PACKED(40, af.PACKED_VECTOR, ap.LONG),
    FIXED32_LIST_PACKED(41, af.PACKED_VECTOR, ap.INT),
    BOOL_LIST_PACKED(42, af.PACKED_VECTOR, ap.BOOLEAN),
    UINT32_LIST_PACKED(43, af.PACKED_VECTOR, ap.INT),
    ENUM_LIST_PACKED(44, af.PACKED_VECTOR, ap.ENUM),
    SFIXED32_LIST_PACKED(45, af.PACKED_VECTOR, ap.INT),
    SFIXED64_LIST_PACKED(46, af.PACKED_VECTOR, ap.LONG),
    SINT32_LIST_PACKED(47, af.PACKED_VECTOR, ap.INT),
    SINT64_LIST_PACKED(48, af.PACKED_VECTOR, ap.LONG),
    GROUP_LIST(49, af.VECTOR, ap.MESSAGE),
    MAP(50, af.MAP, ap.VOID);
    
    private static final ad[] ae = null;
    private static final Type[] af = null;
    private final ap Z;
    private final int aa;
    private final af ab;
    private final Class<?> ac;
    private final boolean ad;

    static {
        af = new Type[0];
        ad[] values = values();
        ae = new ad[values.length];
        int length = values.length;
        int i;
        while (i < length) {
            ad adVar = values[i];
            ae[adVar.aa] = adVar;
            i++;
        }
    }

    private ad(int i, af afVar, ap apVar) {
        this.aa = i;
        this.ab = afVar;
        this.Z = apVar;
        switch (ae.a[afVar.ordinal()]) {
            case 1:
                this.ac = apVar.a();
                break;
            case 2:
                this.ac = apVar.a();
                break;
            default:
                this.ac = null;
                break;
        }
        boolean z = false;
        if (afVar == af.SCALAR) {
            switch (ae.b[apVar.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    break;
                default:
                    z = true;
                    break;
            }
        }
        this.ad = z;
    }

    public final int a() {
        return this.aa;
    }
}
