package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.e;
import com.google.android.gms.common.internal.q.a;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.Collection;

@Class(creator = "GetServiceRequestCreator")
@Reserved({9})
public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Creator<GetServiceRequest> CREATOR = new k();
    @VersionField(id = 1)
    private final int a;
    @Field(id = 2)
    private final int b;
    @Field(id = 3)
    private int c;
    @Field(id = 4)
    private String d;
    @Field(id = 5)
    private IBinder e;
    @Field(id = 6)
    private Scope[] f;
    @Field(id = 7)
    private Bundle g;
    @Field(id = 8)
    private Account h;
    @Field(id = 10)
    private Feature[] i;
    @Field(id = 11)
    private Feature[] j;
    @Field(id = 12)
    private boolean k;

    public GetServiceRequest(int i) {
        this.a = 4;
        this.c = e.b;
        this.b = i;
        this.k = true;
    }

    @Constructor
    GetServiceRequest(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) int i3, @Param(id = 4) String str, @Param(id = 5) IBinder iBinder, @Param(id = 6) Scope[] scopeArr, @Param(id = 7) Bundle bundle, @Param(id = 8) Account account, @Param(id = 10) Feature[] featureArr, @Param(id = 11) Feature[] featureArr2, @Param(id = 12) boolean z) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        if ("com.google.android.gms".equals(str)) {
            this.d = "com.google.android.gms";
        } else {
            this.d = str;
        }
        if (i < 2) {
            Account account2 = null;
            if (iBinder != null) {
                account2 = a.a(a.a(iBinder));
            }
            this.h = account2;
        } else {
            this.e = iBinder;
            this.h = account;
        }
        this.f = scopeArr;
        this.g = bundle;
        this.i = featureArr;
        this.j = featureArr2;
        this.k = z;
    }

    public final GetServiceRequest a(Account account) {
        this.h = account;
        return this;
    }

    public final GetServiceRequest a(Bundle bundle) {
        this.g = bundle;
        return this;
    }

    public final GetServiceRequest a(q qVar) {
        if (qVar != null) {
            this.e = qVar.asBinder();
        }
        return this;
    }

    public final GetServiceRequest a(String str) {
        this.d = str;
        return this;
    }

    public final GetServiceRequest a(Collection<Scope> collection) {
        this.f = (Scope[]) collection.toArray(new Scope[collection.size()]);
        return this;
    }

    public final GetServiceRequest a(Feature[] featureArr) {
        this.i = featureArr;
        return this;
    }

    public final GetServiceRequest b(Feature[] featureArr) {
        this.j = featureArr;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b);
        b.a(parcel, 3, this.c);
        b.a(parcel, 4, this.d);
        b.a(parcel, 5, this.e);
        b.a(parcel, 6, this.f, i);
        b.a(parcel, 7, this.g);
        b.a(parcel, 8, this.h, i);
        b.a(parcel, 10, this.i, i);
        b.a(parcel, 11, this.j, i);
        b.a(parcel, 12, this.k);
        b.a(parcel, a);
    }
}
