package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.server.response.FastJsonResponse.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Class(creator = "StringToIntConverterCreator")
public final class StringToIntConverter extends AbstractSafeParcelable implements a<String, Integer> {
    public static final Creator<StringToIntConverter> CREATOR = new b();
    @VersionField(id = 1)
    private final int a;
    private final HashMap<String, Integer> b;
    private final SparseArray<String> c;
    @Field(getter = "getSerializedMap", id = 2)
    private final ArrayList<Entry> d;

    @Class(creator = "StringToIntConverterEntryCreator")
    public static final class Entry extends AbstractSafeParcelable {
        public static final Creator<Entry> CREATOR = new c();
        @Field(id = 2)
        final String a;
        @Field(id = 3)
        final int b;
        @VersionField(id = 1)
        private final int c;

        @Constructor
        Entry(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) int i2) {
            this.c = i;
            this.a = str;
            this.b = i2;
        }

        Entry(String str, int i) {
            this.c = 1;
            this.a = str;
            this.b = i;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 1, this.c);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b);
            b.a(parcel, a);
        }
    }

    public StringToIntConverter() {
        this.a = 1;
        this.b = new HashMap();
        this.c = new SparseArray();
        this.d = null;
    }

    @Constructor
    StringToIntConverter(@Param(id = 1) int i, @Param(id = 2) ArrayList<Entry> arrayList) {
        this.a = i;
        this.b = new HashMap();
        this.c = new SparseArray();
        this.d = null;
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList2.get(i2);
            i2++;
            Entry entry = (Entry) obj;
            String str = entry.a;
            int i3 = entry.b;
            this.b.put(str, Integer.valueOf(i3));
            this.c.put(i3, str);
        }
    }

    public final /* synthetic */ Object a(Object obj) {
        String str = (String) this.c.get(((Integer) obj).intValue());
        return (str == null && this.b.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        List arrayList = new ArrayList();
        for (String str : this.b.keySet()) {
            arrayList.add(new Entry(str, ((Integer) this.b.get(str)).intValue()));
        }
        b.b(parcel, 2, arrayList);
        b.a(parcel, a);
    }
}
