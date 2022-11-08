package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.MemberKind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite;

public class Flags {
    public static final FlagField<Kind> CLASS_KIND;
    public static final BooleanFlagField DECLARES_DEFAULT_VALUE;
    public static final BooleanFlagField HAS_ANNOTATIONS;
    public static final BooleanFlagField HAS_CONSTANT;
    public static final BooleanFlagField HAS_GETTER;
    public static final BooleanFlagField HAS_SETTER;
    public static final BooleanFlagField IS_CONST;
    public static final BooleanFlagField IS_CROSSINLINE;
    public static final BooleanFlagField IS_DATA;
    public static final BooleanFlagField IS_DELEGATED;
    public static final BooleanFlagField IS_EXPECT_CLASS;
    public static final BooleanFlagField IS_EXPECT_FUNCTION;
    public static final BooleanFlagField IS_EXPECT_PROPERTY;
    public static final BooleanFlagField IS_EXTERNAL_ACCESSOR;
    public static final BooleanFlagField IS_EXTERNAL_CLASS;
    public static final BooleanFlagField IS_EXTERNAL_FUNCTION;
    public static final BooleanFlagField IS_EXTERNAL_PROPERTY;
    public static final BooleanFlagField IS_INFIX;
    public static final BooleanFlagField IS_INLINE;
    public static final BooleanFlagField IS_INLINE_ACCESSOR;
    public static final BooleanFlagField IS_INLINE_CLASS;
    public static final BooleanFlagField IS_INNER;
    public static final BooleanFlagField IS_LATEINIT;
    public static final BooleanFlagField IS_NEGATED;
    public static final BooleanFlagField IS_NOINLINE;
    public static final BooleanFlagField IS_NOT_DEFAULT;
    public static final BooleanFlagField IS_NULL_CHECK_PREDICATE;
    public static final BooleanFlagField IS_OPERATOR;
    public static final BooleanFlagField IS_SECONDARY = FlagField.booleanAfter(VISIBILITY);
    public static final BooleanFlagField IS_SUSPEND;
    public static final BooleanFlagField IS_TAILREC;
    public static final BooleanFlagField IS_UNSIGNED = FlagField.booleanFirst();
    public static final BooleanFlagField IS_VAR;
    public static final FlagField<MemberKind> MEMBER_KIND;
    public static final FlagField<Modality> MODALITY;
    public static final BooleanFlagField SUSPEND_TYPE = FlagField.booleanFirst();
    public static final FlagField<Visibility> VISIBILITY;

    public abstract class FlagField<E> {
        public final int bitWidth;
        public final int offset;

        public abstract E get(int i);

        public abstract int toFlags(E e);

        public static <E extends EnumLite> FlagField<E> after(FlagField<?> flagField, E[] eArr) {
            return new EnumLiteFlagField(flagField.offset + flagField.bitWidth, eArr);
        }

        public static BooleanFlagField booleanFirst() {
            return new BooleanFlagField(0);
        }

        public static BooleanFlagField booleanAfter(FlagField<?> flagField) {
            return new BooleanFlagField(flagField.offset + flagField.bitWidth);
        }

        private FlagField(int i, int i2) {
            this.offset = i;
            this.bitWidth = i2;
        }
    }

    public class BooleanFlagField extends FlagField<Boolean> {
        public BooleanFlagField(int i) {
            super(i, 1);
        }

        public Boolean get(int i) {
            boolean z = true;
            if ((i & (1 << this.offset)) == 0) {
                z = false;
            }
            return Boolean.valueOf(z);
        }

        public int toFlags(Boolean bool) {
            return bool.booleanValue() ? 1 << this.offset : 0;
        }
    }

    class EnumLiteFlagField<E extends EnumLite> extends FlagField<E> {
        private final E[] values;

        public EnumLiteFlagField(int i, E[] eArr) {
            super(i, bitWidth(eArr));
            this.values = eArr;
        }

        private static <E> int bitWidth(E[] eArr) {
            int length = eArr.length - 1;
            if (length == 0) {
                return 1;
            }
            for (int i = 31; i >= 0; i--) {
                if (((1 << i) & length) != 0) {
                    return i + 1;
                }
            }
            StringBuilder stringBuilder = new StringBuilder("Empty enum: ");
            stringBuilder.append(eArr.getClass());
            throw new IllegalStateException(stringBuilder.toString());
        }

        public E get(int i) {
            i = (i & (((1 << this.bitWidth) - 1) << this.offset)) >> this.offset;
            for (E e : this.values) {
                if (e.getNumber() == i) {
                    return e;
                }
            }
            return null;
        }

        public int toFlags(E e) {
            return e.getNumber() << this.offset;
        }
    }

    static {
        FlagField booleanFirst = FlagField.booleanFirst();
        HAS_ANNOTATIONS = booleanFirst;
        booleanFirst = FlagField.after(booleanFirst, Visibility.values());
        VISIBILITY = booleanFirst;
        booleanFirst = FlagField.after(booleanFirst, Modality.values());
        MODALITY = booleanFirst;
        booleanFirst = FlagField.after(booleanFirst, Kind.values());
        CLASS_KIND = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_INNER = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_DATA = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_EXTERNAL_CLASS = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_EXPECT_CLASS = booleanFirst;
        IS_INLINE_CLASS = FlagField.booleanAfter(booleanFirst);
        booleanFirst = FlagField.after(MODALITY, MemberKind.values());
        MEMBER_KIND = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_OPERATOR = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_INFIX = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_INLINE = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_TAILREC = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_EXTERNAL_FUNCTION = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_SUSPEND = booleanFirst;
        IS_EXPECT_FUNCTION = FlagField.booleanAfter(booleanFirst);
        booleanFirst = FlagField.booleanAfter(MEMBER_KIND);
        IS_VAR = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        HAS_GETTER = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        HAS_SETTER = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_CONST = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_LATEINIT = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        HAS_CONSTANT = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_EXTERNAL_PROPERTY = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_DELEGATED = booleanFirst;
        IS_EXPECT_PROPERTY = FlagField.booleanAfter(booleanFirst);
        booleanFirst = FlagField.booleanAfter(HAS_ANNOTATIONS);
        DECLARES_DEFAULT_VALUE = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_CROSSINLINE = booleanFirst;
        IS_NOINLINE = FlagField.booleanAfter(booleanFirst);
        booleanFirst = FlagField.booleanAfter(MODALITY);
        IS_NOT_DEFAULT = booleanFirst;
        booleanFirst = FlagField.booleanAfter(booleanFirst);
        IS_EXTERNAL_ACCESSOR = booleanFirst;
        IS_INLINE_ACCESSOR = FlagField.booleanAfter(booleanFirst);
        booleanFirst = FlagField.booleanFirst();
        IS_NEGATED = booleanFirst;
        IS_NULL_CHECK_PREDICATE = FlagField.booleanAfter(booleanFirst);
    }

    public static int getAccessorFlags(boolean z, Visibility visibility, Modality modality, boolean z2, boolean z3, boolean z4) {
        return ((((HAS_ANNOTATIONS.toFlags(Boolean.valueOf(z)) | MODALITY.toFlags(modality)) | VISIBILITY.toFlags(visibility)) | IS_NOT_DEFAULT.toFlags(Boolean.valueOf(z2))) | IS_EXTERNAL_ACCESSOR.toFlags(Boolean.valueOf(z3))) | IS_INLINE_ACCESSOR.toFlags(Boolean.valueOf(z4));
    }
}
