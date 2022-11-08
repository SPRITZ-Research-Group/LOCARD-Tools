package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.internal.z;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.c;
import com.google.android.gms.common.util.k;
import com.google.android.gms.common.util.l;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class FastJsonResponse {

    public interface a<I, O> {
        I a(O o);
    }

    @Class(creator = "FieldCreator")
    @VisibleForTesting
    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final a CREATOR = new a();
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getTypeIn", id = 2)
        protected final int a;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "isTypeInArray", id = 3)
        protected final boolean b;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getTypeOut", id = 4)
        protected final int c;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "isTypeOutArray", id = 5)
        protected final boolean d;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getOutputFieldName", id = 6)
        protected final String e;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getSafeParcelableFieldId", id = 7)
        protected final int f;
        protected final Class<? extends FastJsonResponse> g;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getConcreteTypeName", id = 8)
        protected final String h;
        @VersionField(getter = "getVersionCode", id = 1)
        private final int i;
        private FieldMappingDictionary j;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getWrappedConverter", id = 9, type = "com.google.android.gms.common.server.converter.ConverterWrapper")
        private a<I, O> k;

        @Constructor
        Field(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) boolean z, @Param(id = 4) int i3, @Param(id = 5) boolean z2, @Param(id = 6) String str, @Param(id = 7) int i4, @Param(id = 8) String str2, @Param(id = 9) ConverterWrapper converterWrapper) {
            this.i = i;
            this.a = i2;
            this.b = z;
            this.c = i3;
            this.d = z2;
            this.e = str;
            this.f = i4;
            if (str2 == null) {
                this.g = null;
                this.h = null;
            } else {
                this.g = SafeParcelResponse.class;
                this.h = str2;
            }
            if (converterWrapper == null) {
                this.k = null;
            } else {
                this.k = converterWrapper.a();
            }
        }

        private final String j() {
            return this.h == null ? null : this.h;
        }

        public final int a() {
            return this.a;
        }

        public final I a(O o) {
            return this.k.a(o);
        }

        public final void a(FieldMappingDictionary fieldMappingDictionary) {
            this.j = fieldMappingDictionary;
        }

        public final boolean b() {
            return this.b;
        }

        public final int c() {
            return this.c;
        }

        public final boolean d() {
            return this.d;
        }

        public final String e() {
            return this.e;
        }

        public final int f() {
            return this.f;
        }

        public final Class<? extends FastJsonResponse> g() {
            return this.g;
        }

        public final boolean h() {
            return this.k != null;
        }

        public final Map<String, Field<?, ?>> i() {
            ab.a(this.h);
            ab.a(this.j);
            return this.j.a(this.h);
        }

        public String toString() {
            com.google.android.gms.common.internal.z.a a = z.a(this).a("versionCode", Integer.valueOf(this.i)).a("typeIn", Integer.valueOf(this.a)).a("typeInArray", Boolean.valueOf(this.b)).a("typeOut", Integer.valueOf(this.c)).a("typeOutArray", Boolean.valueOf(this.d)).a("outputFieldName", this.e).a("safeParcelFieldId", Integer.valueOf(this.f)).a("concreteTypeName", j());
            Class cls = this.g;
            if (cls != null) {
                a.a("concreteType.class", cls.getCanonicalName());
            }
            if (this.k != null) {
                a.a("converterName", this.k.getClass().getCanonicalName());
            }
            return a.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 1, this.i);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b);
            b.a(parcel, 4, this.c);
            b.a(parcel, 5, this.d);
            b.a(parcel, 6, this.e);
            b.a(parcel, 7, this.f);
            b.a(parcel, 8, j());
            b.a(parcel, 9, this.k == null ? null : ConverterWrapper.a(this.k), i);
            b.a(parcel, a);
        }
    }

    protected static <O, I> I a(Field<I, O> field, Object obj) {
        return field.k != null ? field.a(obj) : obj;
    }

    private static void a(StringBuilder stringBuilder, Field field, Object obj) {
        if (field.a() == 11) {
            stringBuilder.append(((FastJsonResponse) field.g().cast(obj)).toString());
        } else if (field.a() == 7) {
            stringBuilder.append("\"");
            stringBuilder.append(k.a((String) obj));
            stringBuilder.append("\"");
        } else {
            stringBuilder.append(obj);
        }
    }

    public abstract Map<String, Field<?, ?>> a();

    protected final boolean a(Field field) {
        if (field.c() != 11) {
            field.e();
            return c();
        } else if (field.d()) {
            field.e();
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        } else {
            field.e();
            throw new UnsupportedOperationException("Concrete types not supported");
        }
    }

    protected abstract Object b();

    protected final Object b(Field field) {
        String e = field.e();
        if (field.g() != null) {
            field.e();
            b();
            new Object[1][0] = field.e();
            field.d();
            try {
                char toUpperCase = Character.toUpperCase(e.charAt(0));
                e = e.substring(1);
                return getClass().getMethod(new StringBuilder(String.valueOf(e).length() + 4).append("get").append(toUpperCase).append(e).toString(), new Class[0]).invoke(this, new Object[0]);
            } catch (Throwable e2) {
                throw new RuntimeException(e2);
            }
        }
        field.e();
        return b();
    }

    protected abstract boolean c();

    public String toString() {
        Map a = a();
        StringBuilder stringBuilder = new StringBuilder(100);
        for (String str : a.keySet()) {
            Field field = (Field) a.get(str);
            if (a(field)) {
                Object a2 = a(field, b(field));
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("{");
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("\"").append(str).append("\":");
                if (a2 != null) {
                    switch (field.c()) {
                        case 8:
                            stringBuilder.append("\"").append(c.a((byte[]) a2)).append("\"");
                            break;
                        case 9:
                            stringBuilder.append("\"").append(c.b((byte[]) a2)).append("\"");
                            break;
                        case 10:
                            l.a(stringBuilder, (HashMap) a2);
                            break;
                        default:
                            if (!field.b()) {
                                a(stringBuilder, field, a2);
                                break;
                            }
                            ArrayList arrayList = (ArrayList) a2;
                            stringBuilder.append("[");
                            int size = arrayList.size();
                            for (int i = 0; i < size; i++) {
                                if (i > 0) {
                                    stringBuilder.append(",");
                                }
                                Object obj = arrayList.get(i);
                                if (obj != null) {
                                    a(stringBuilder, field, obj);
                                }
                            }
                            stringBuilder.append("]");
                            break;
                    }
                }
                stringBuilder.append("null");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append("}");
        } else {
            stringBuilder.append("{}");
        }
        return stringBuilder.toString();
    }
}
