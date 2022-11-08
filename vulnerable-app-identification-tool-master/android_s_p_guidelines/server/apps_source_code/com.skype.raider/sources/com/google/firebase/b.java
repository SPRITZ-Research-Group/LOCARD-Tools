package com.google.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.ah;
import com.google.android.gms.common.internal.z;
import com.google.android.gms.common.util.o;
import java.util.Arrays;

public final class b {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;

    private b(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        ab.a(!o.a(str), (Object) "ApplicationId must be set.");
        this.b = str;
        this.a = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
    }

    public static b a(Context context) {
        ah ahVar = new ah(context);
        Object a = ahVar.a("google_app_id");
        return TextUtils.isEmpty(a) ? null : new b(a, ahVar.a("google_api_key"), ahVar.a("firebase_database_url"), ahVar.a("ga_trackingId"), ahVar.a("gcm_defaultSenderId"), ahVar.a("google_storage_bucket"), ahVar.a("project_id"));
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.e;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return z.a(this.b, bVar.b) && z.a(this.a, bVar.a) && z.a(this.c, bVar.c) && z.a(this.d, bVar.d) && z.a(this.e, bVar.e) && z.a(this.f, bVar.f) && z.a(this.g, bVar.g);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.b, this.a, this.c, this.d, this.e, this.f, this.g});
    }

    public final String toString() {
        return z.a(this).a("applicationId", this.b).a("apiKey", this.a).a("databaseUrl", this.c).a("gcmSenderId", this.e).a("storageBucket", this.f).a("projectId", this.g).toString();
    }
}
