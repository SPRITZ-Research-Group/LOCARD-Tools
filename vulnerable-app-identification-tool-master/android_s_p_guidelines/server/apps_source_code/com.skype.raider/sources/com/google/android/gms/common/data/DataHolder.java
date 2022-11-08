package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;

@KeepName
@Class(creator = "DataHolderCreator", validate = true)
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    public static final Creator<DataHolder> CREATOR = new b();
    private static final a k = new c(new String[0]);
    @VersionField(id = 1000)
    private final int a;
    @Field(getter = "getColumns", id = 1)
    private final String[] b;
    private Bundle c;
    @Field(getter = "getWindows", id = 2)
    private final CursorWindow[] d;
    @Field(getter = "getStatusCode", id = 3)
    private final int e;
    @Field(getter = "getMetadata", id = 4)
    private final Bundle f;
    private int[] g;
    private int h;
    private boolean i = false;
    private boolean j = true;

    public static class a {
        private final String[] a;
        private final ArrayList<HashMap<String, Object>> b;
        private final String c;
        private final HashMap<Object, Integer> d;
        private boolean e;
        private String f;

        private a(String[] strArr) {
            this.a = (String[]) ab.a((Object) strArr);
            this.b = new ArrayList();
            this.c = null;
            this.d = new HashMap();
            this.e = false;
            this.f = null;
        }

        /* synthetic */ a(String[] strArr, byte b) {
            this(strArr);
        }
    }

    @Constructor
    DataHolder(@Param(id = 1000) int i, @Param(id = 1) String[] strArr, @Param(id = 2) CursorWindow[] cursorWindowArr, @Param(id = 3) int i2, @Param(id = 4) Bundle bundle) {
        this.a = i;
        this.b = strArr;
        this.d = cursorWindowArr;
        this.e = i2;
        this.f = bundle;
    }

    private boolean b() {
        boolean z;
        synchronized (this) {
            z = this.i;
        }
        return z;
    }

    public final void a() {
        int i;
        int i2 = 0;
        this.c = new Bundle();
        for (i = 0; i < this.b.length; i++) {
            this.c.putInt(this.b[i], i);
        }
        this.g = new int[this.d.length];
        i = 0;
        while (i2 < this.d.length) {
            this.g[i2] = i;
            i += this.d[i2].getNumRows() - (i - this.d[i2].getStartPosition());
            i2++;
        }
        this.h = i;
    }

    public final void close() {
        synchronized (this) {
            if (!this.i) {
                this.i = true;
                for (CursorWindow close : this.d) {
                    close.close();
                }
            }
        }
    }

    protected final void finalize() throws Throwable {
        try {
            if (this.j && this.d.length > 0 && !b()) {
                close();
                String obj = toString();
                new StringBuilder(String.valueOf(obj).length() + 178).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ").append(obj).append(")");
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.b);
        b.a(parcel, 2, this.d, i);
        b.a(parcel, 3, this.e);
        b.a(parcel, 4, this.f);
        b.a(parcel, (int) Constants.ONE_SECOND, this.a);
        b.a(parcel, a);
        if ((i & 1) != 0) {
            close();
        }
    }
}
