package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.b;
import com.google.android.gms.common.util.c;
import com.google.android.gms.common.util.k;
import com.google.android.gms.common.util.l;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Class(creator = "SafeParcelResponseCreator")
@VisibleForTesting
public class SafeParcelResponse extends FastSafeParcelableJsonResponse {
    public static final Creator<SafeParcelResponse> CREATOR = new e();
    @VersionField(getter = "getVersionCode", id = 1)
    private final int a;
    @Field(getter = "getParcel", id = 2)
    private final Parcel b;
    private final int c = 2;
    @Field(getter = "getFieldMappingDictionary", id = 3)
    private final FieldMappingDictionary d;
    private final String e;
    private int f;
    private int g;

    @Constructor
    SafeParcelResponse(@Param(id = 1) int i, @Param(id = 2) Parcel parcel, @Param(id = 3) FieldMappingDictionary fieldMappingDictionary) {
        this.a = i;
        this.b = (Parcel) ab.a((Object) parcel);
        this.d = fieldMappingDictionary;
        if (this.d == null) {
            this.e = null;
        } else {
            this.e = this.d.a();
        }
        this.f = 2;
    }

    private static HashMap<String, String> a(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    private static void a(StringBuilder stringBuilder, int i, Object obj) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                stringBuilder.append(obj);
                return;
            case 7:
                stringBuilder.append("\"").append(k.a(obj.toString())).append("\"");
                return;
            case 8:
                stringBuilder.append("\"").append(c.a((byte[]) obj)).append("\"");
                return;
            case 9:
                stringBuilder.append("\"").append(c.b((byte[]) obj));
                stringBuilder.append("\"");
                return;
            case 10:
                l.a(stringBuilder, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    private static void a(StringBuilder stringBuilder, FastJsonResponse.Field<?, ?> field, Object obj) {
        if (field.b()) {
            ArrayList arrayList = (ArrayList) obj;
            stringBuilder.append("[");
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    stringBuilder.append(",");
                }
                a(stringBuilder, field.a(), arrayList.get(i));
            }
            stringBuilder.append("]");
            return;
        }
        a(stringBuilder, field.a(), obj);
    }

    private final void a(StringBuilder stringBuilder, Map<String, FastJsonResponse.Field<?, ?>> map, Parcel parcel) {
        Entry entry;
        SparseArray sparseArray = new SparseArray();
        for (Entry entry2 : map.entrySet()) {
            sparseArray.put(((FastJsonResponse.Field) entry2.getValue()).f(), entry2);
        }
        stringBuilder.append('{');
        int a = a.a(parcel);
        int i = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            entry2 = (Entry) sparseArray.get(65535 & readInt);
            if (entry2 != null) {
                if (i != 0) {
                    stringBuilder.append(",");
                }
                String str = (String) entry2.getKey();
                FastJsonResponse.Field field = (FastJsonResponse.Field) entry2.getValue();
                stringBuilder.append("\"").append(str).append("\":");
                if (field.h()) {
                    switch (field.c()) {
                        case 0:
                            a(stringBuilder, field, FastJsonResponse.a(field, Integer.valueOf(a.f(parcel, readInt))));
                            break;
                        case 1:
                            a(stringBuilder, field, FastJsonResponse.a(field, a.j(parcel, readInt)));
                            break;
                        case 2:
                            a(stringBuilder, field, FastJsonResponse.a(field, Long.valueOf(a.h(parcel, readInt))));
                            break;
                        case 3:
                            a(stringBuilder, field, FastJsonResponse.a(field, Float.valueOf(a.k(parcel, readInt))));
                            break;
                        case 4:
                            a(stringBuilder, field, FastJsonResponse.a(field, Double.valueOf(a.m(parcel, readInt))));
                            break;
                        case 5:
                            a(stringBuilder, field, FastJsonResponse.a(field, a.o(parcel, readInt)));
                            break;
                        case 6:
                            a(stringBuilder, field, FastJsonResponse.a(field, Boolean.valueOf(a.c(parcel, readInt))));
                            break;
                        case 7:
                            a(stringBuilder, field, FastJsonResponse.a(field, a.p(parcel, readInt)));
                            break;
                        case 8:
                        case 9:
                            a(stringBuilder, field, FastJsonResponse.a(field, a.s(parcel, readInt)));
                            break;
                        case 10:
                            a(stringBuilder, field, FastJsonResponse.a(field, a(a.r(parcel, readInt))));
                            break;
                        case 11:
                            throw new IllegalArgumentException("Method does not accept concrete type.");
                        default:
                            throw new IllegalArgumentException("Unknown field out type = " + field.c());
                    }
                } else if (field.d()) {
                    stringBuilder.append("[");
                    switch (field.c()) {
                        case 0:
                            b.a(stringBuilder, a.u(parcel, readInt));
                            break;
                        case 1:
                            b.a(stringBuilder, a.v(parcel, readInt));
                            break;
                        case 2:
                            long[] jArr;
                            i = a.a(parcel, readInt);
                            readInt = parcel.dataPosition();
                            if (i == 0) {
                                jArr = null;
                            } else {
                                jArr = parcel.createLongArray();
                                parcel.setDataPosition(i + readInt);
                            }
                            b.a(stringBuilder, jArr);
                            break;
                        case 3:
                            float[] fArr;
                            i = a.a(parcel, readInt);
                            readInt = parcel.dataPosition();
                            if (i == 0) {
                                fArr = null;
                            } else {
                                fArr = parcel.createFloatArray();
                                parcel.setDataPosition(i + readInt);
                            }
                            b.a(stringBuilder, fArr);
                            break;
                        case 4:
                            double[] dArr;
                            i = a.a(parcel, readInt);
                            readInt = parcel.dataPosition();
                            if (i == 0) {
                                dArr = null;
                            } else {
                                dArr = parcel.createDoubleArray();
                                parcel.setDataPosition(i + readInt);
                            }
                            b.a(stringBuilder, dArr);
                            break;
                        case 5:
                            b.a(stringBuilder, a.w(parcel, readInt));
                            break;
                        case 6:
                            boolean[] zArr;
                            i = a.a(parcel, readInt);
                            readInt = parcel.dataPosition();
                            if (i == 0) {
                                zArr = null;
                            } else {
                                zArr = parcel.createBooleanArray();
                                parcel.setDataPosition(i + readInt);
                            }
                            b.a(stringBuilder, zArr);
                            break;
                        case 7:
                            b.a(stringBuilder, a.x(parcel, readInt));
                            break;
                        case 8:
                        case 9:
                        case 10:
                            throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                        case 11:
                            Parcel[] A = a.A(parcel, readInt);
                            int length = A.length;
                            for (i = 0; i < length; i++) {
                                if (i > 0) {
                                    stringBuilder.append(",");
                                }
                                A[i].setDataPosition(0);
                                a(stringBuilder, field.i(), A[i]);
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unknown field type out.");
                    }
                    stringBuilder.append("]");
                } else {
                    switch (field.c()) {
                        case 0:
                            stringBuilder.append(a.f(parcel, readInt));
                            break;
                        case 1:
                            stringBuilder.append(a.j(parcel, readInt));
                            break;
                        case 2:
                            stringBuilder.append(a.h(parcel, readInt));
                            break;
                        case 3:
                            stringBuilder.append(a.k(parcel, readInt));
                            break;
                        case 4:
                            stringBuilder.append(a.m(parcel, readInt));
                            break;
                        case 5:
                            stringBuilder.append(a.o(parcel, readInt));
                            break;
                        case 6:
                            stringBuilder.append(a.c(parcel, readInt));
                            break;
                        case 7:
                            stringBuilder.append("\"").append(k.a(a.p(parcel, readInt))).append("\"");
                            break;
                        case 8:
                            stringBuilder.append("\"").append(c.a(a.s(parcel, readInt))).append("\"");
                            break;
                        case 9:
                            stringBuilder.append("\"").append(c.b(a.s(parcel, readInt)));
                            stringBuilder.append("\"");
                            break;
                        case 10:
                            Bundle r = a.r(parcel, readInt);
                            Set<String> keySet = r.keySet();
                            keySet.size();
                            stringBuilder.append("{");
                            i = 1;
                            for (String str2 : keySet) {
                                if (i == 0) {
                                    stringBuilder.append(",");
                                }
                                stringBuilder.append("\"").append(str2).append("\"");
                                stringBuilder.append(":");
                                stringBuilder.append("\"").append(k.a(r.getString(str2))).append("\"");
                                i = 0;
                            }
                            stringBuilder.append("}");
                            break;
                        case 11:
                            Parcel z = a.z(parcel, readInt);
                            z.setDataPosition(0);
                            a(stringBuilder, field.i(), z);
                            break;
                        default:
                            throw new IllegalStateException("Unknown field type out");
                    }
                }
                i = 1;
            }
        }
        if (parcel.dataPosition() != a) {
            throw new a.a("Overread allowed size end=" + a, parcel);
        }
        stringBuilder.append('}');
    }

    private Parcel d() {
        switch (this.f) {
            case 0:
                this.g = com.google.android.gms.common.internal.safeparcel.b.a(this.b);
                break;
            case 1:
                break;
        }
        com.google.android.gms.common.internal.safeparcel.b.a(this.b, this.g);
        this.f = 2;
        return this.b;
    }

    public final Map<String, FastJsonResponse.Field<?, ?>> a() {
        return this.d == null ? null : this.d.a(this.e);
    }

    public final Object b() {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public final boolean c() {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public String toString() {
        ab.a(this.d, (Object) "Cannot convert to JSON on client side.");
        Parcel d = d();
        d.setDataPosition(0);
        StringBuilder stringBuilder = new StringBuilder(100);
        a(stringBuilder, this.d.a(this.e), d);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        Parcelable parcelable;
        int a = com.google.android.gms.common.internal.safeparcel.b.a(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, this.a);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, d());
        switch (this.c) {
            case 0:
                parcelable = null;
                break;
            case 1:
                parcelable = this.d;
                break;
            case 2:
                parcelable = this.d;
                break;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.c);
        }
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, parcelable, i);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, a);
    }
}
