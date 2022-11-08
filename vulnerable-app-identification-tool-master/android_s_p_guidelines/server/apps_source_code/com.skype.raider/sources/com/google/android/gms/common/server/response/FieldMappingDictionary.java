package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Class(creator = "FieldMappingDictionaryCreator")
public class FieldMappingDictionary extends AbstractSafeParcelable {
    public static final Creator<FieldMappingDictionary> CREATOR = new c();
    @VersionField(id = 1)
    private final int a;
    private final HashMap<String, Map<String, Field<?, ?>>> b;
    @SafeParcelable.Field(getter = "getSerializedDictionary", id = 2)
    private final ArrayList<Entry> c = null;
    @SafeParcelable.Field(getter = "getRootClassName", id = 3)
    private final String d;

    @Class(creator = "FieldMappingDictionaryEntryCreator")
    public static class Entry extends AbstractSafeParcelable {
        public static final Creator<Entry> CREATOR = new d();
        @SafeParcelable.Field(id = 2)
        final String a;
        @SafeParcelable.Field(id = 3)
        final ArrayList<FieldMapPair> b;
        @VersionField(id = 1)
        private final int c;

        @Constructor
        Entry(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) ArrayList<FieldMapPair> arrayList) {
            this.c = i;
            this.a = str;
            this.b = arrayList;
        }

        Entry(String str, Map<String, Field<?, ?>> map) {
            ArrayList arrayList;
            this.c = 1;
            this.a = str;
            if (map == null) {
                arrayList = null;
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (String str2 : map.keySet()) {
                    arrayList2.add(new FieldMapPair(str2, (Field) map.get(str2)));
                }
                arrayList = arrayList2;
            }
            this.b = arrayList;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 1, this.c);
            b.a(parcel, 2, this.a);
            b.b(parcel, 3, this.b);
            b.a(parcel, a);
        }
    }

    @Class(creator = "FieldMapPairCreator")
    public static class FieldMapPair extends AbstractSafeParcelable {
        public static final Creator<FieldMapPair> CREATOR = new b();
        @SafeParcelable.Field(id = 2)
        final String a;
        @SafeParcelable.Field(id = 3)
        final Field<?, ?> b;
        @VersionField(id = 1)
        private final int c;

        @Constructor
        FieldMapPair(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) Field<?, ?> field) {
            this.c = i;
            this.a = str;
            this.b = field;
        }

        FieldMapPair(String str, Field<?, ?> field) {
            this.c = 1;
            this.a = str;
            this.b = field;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 1, this.c);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b, i);
            b.a(parcel, a);
        }
    }

    @Constructor
    FieldMappingDictionary(@Param(id = 1) int i, @Param(id = 2) ArrayList<Entry> arrayList, @Param(id = 3) String str) {
        this.a = i;
        HashMap hashMap = new HashMap();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            Entry entry = (Entry) arrayList.get(i2);
            String str2 = entry.a;
            HashMap hashMap2 = new HashMap();
            int size2 = entry.b.size();
            for (int i3 = 0; i3 < size2; i3++) {
                FieldMapPair fieldMapPair = (FieldMapPair) entry.b.get(i3);
                hashMap2.put(fieldMapPair.a, fieldMapPair.b);
            }
            hashMap.put(str2, hashMap2);
        }
        this.b = hashMap;
        this.d = (String) ab.a((Object) str);
        b();
    }

    private void b() {
        for (String str : this.b.keySet()) {
            Map map = (Map) this.b.get(str);
            for (String str2 : map.keySet()) {
                ((Field) map.get(str2)).a(this);
            }
        }
    }

    public final String a() {
        return this.d;
    }

    public final Map<String, Field<?, ?>> a(String str) {
        return (Map) this.b.get(str);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.b.keySet()) {
            stringBuilder.append(str).append(":\n");
            Map map = (Map) this.b.get(str);
            for (String str2 : map.keySet()) {
                stringBuilder.append("  ").append(str2).append(": ");
                stringBuilder.append(map.get(str2));
            }
        }
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        List arrayList = new ArrayList();
        for (String str : this.b.keySet()) {
            arrayList.add(new Entry(str, (Map) this.b.get(str)));
        }
        b.b(parcel, 2, arrayList);
        b.a(parcel, 3, this.d);
        b.a(parcel, a);
    }
}
