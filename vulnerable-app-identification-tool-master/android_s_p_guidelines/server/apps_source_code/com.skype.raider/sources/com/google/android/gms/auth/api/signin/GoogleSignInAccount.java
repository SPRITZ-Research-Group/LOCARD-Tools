package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.e;
import com.google.android.gms.common.util.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "GoogleSignInAccountCreator")
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<GoogleSignInAccount> CREATOR = new a();
    @VisibleForTesting
    public static e a = f.d();
    @VersionField(id = 1)
    private final int b;
    @Field(getter = "getId", id = 2)
    private String c;
    @Field(getter = "getIdToken", id = 3)
    private String d;
    @Field(getter = "getEmail", id = 4)
    private String e;
    @Field(getter = "getDisplayName", id = 5)
    private String f;
    @Field(getter = "getPhotoUrl", id = 6)
    private Uri g;
    @Field(getter = "getServerAuthCode", id = 7)
    private String h;
    @Field(getter = "getExpirationTimeSecs", id = 8)
    private long i;
    @Field(getter = "getObfuscatedIdentifier", id = 9)
    private String j;
    @Field(id = 10)
    private List<Scope> k;
    @Field(getter = "getGivenName", id = 11)
    private String l;
    @Field(getter = "getFamilyName", id = 12)
    private String m;
    private Set<Scope> n = new HashSet();

    @Constructor
    GoogleSignInAccount(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) Uri uri, @Param(id = 7) String str5, @Param(id = 8) long j, @Param(id = 9) String str6, @Param(id = 10) List<Scope> list, @Param(id = 11) String str7, @Param(id = 12) String str8) {
        this.b = i;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = uri;
        this.h = str5;
        this.i = j;
        this.j = str6;
        this.k = list;
        this.l = str7;
        this.m = str8;
    }

    @Nullable
    public static GoogleSignInAccount a(@Nullable String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Uri uri = null;
        Object optString = jSONObject.optString("photoUrl", null);
        if (!TextUtils.isEmpty(optString)) {
            uri = Uri.parse(optString);
        }
        long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
        Object hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString2 = jSONObject.optString("id");
        String optString3 = jSONObject.optString("tokenId", null);
        String optString4 = jSONObject.optString("email", null);
        String optString5 = jSONObject.optString("displayName", null);
        String optString6 = jSONObject.optString("givenName", null);
        String optString7 = jSONObject.optString("familyName", null);
        Long valueOf = Long.valueOf(parseLong);
        GoogleSignInAccount googleSignInAccount = new GoogleSignInAccount(3, optString2, optString3, optString4, optString5, uri, null, (valueOf == null ? Long.valueOf(a.a() / 1000) : valueOf).longValue(), ab.a(jSONObject.getString("obfuscatedIdentifier")), new ArrayList((Collection) ab.a(hashSet)), optString6, optString7);
        googleSignInAccount.h = jSONObject.optString("serverAuthCode", null);
        return googleSignInAccount;
    }

    @Nullable
    public final Account a() {
        return this.e == null ? null : new Account(this.e, "com.google");
    }

    @NonNull
    public final Set<Scope> b() {
        Set<Scope> hashSet = new HashSet(this.k);
        hashSet.addAll(this.n);
        return hashSet;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        return googleSignInAccount.j.equals(this.j) && googleSignInAccount.b().equals(b());
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.b);
        b.a(parcel, 2, this.c);
        b.a(parcel, 3, this.d);
        b.a(parcel, 4, this.e);
        b.a(parcel, 5, this.f);
        b.a(parcel, 6, this.g, i);
        b.a(parcel, 7, this.h);
        b.a(parcel, 8, this.i);
        b.a(parcel, 9, this.j);
        b.b(parcel, 10, this.k);
        b.a(parcel, 11, this.l);
        b.a(parcel, 12, this.m);
        b.a(parcel, a);
    }

    public int hashCode() {
        return ((this.j.hashCode() + 527) * 31) + b().hashCode();
    }
}
