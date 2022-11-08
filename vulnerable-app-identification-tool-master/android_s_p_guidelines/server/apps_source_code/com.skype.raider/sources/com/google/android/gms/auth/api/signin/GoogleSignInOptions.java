package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.b;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.a.d.e;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Class(creator = "GoogleSignInOptionsCreator")
public class GoogleSignInOptions extends AbstractSafeParcelable implements e, ReflectedParcelable {
    public static final Creator<GoogleSignInOptions> CREATOR = new b();
    @VisibleForTesting
    public static final Scope a = new Scope("profile");
    @VisibleForTesting
    public static final Scope b = new Scope("email");
    @VisibleForTesting
    public static final Scope c = new Scope("openid");
    @VisibleForTesting
    public static final Scope d = new Scope("https://www.googleapis.com/auth/games_lite");
    @VisibleForTesting
    public static final Scope e = new Scope("https://www.googleapis.com/auth/games");
    public static final GoogleSignInOptions f = new a().a().b().c();
    public static final GoogleSignInOptions g = new a().a(d, new Scope[0]).c();
    private static Comparator<Scope> r = new c();
    @VersionField(id = 1)
    private final int h;
    @Field(getter = "getScopes", id = 2)
    private final ArrayList<Scope> i;
    @Field(getter = "getAccount", id = 3)
    private Account j;
    @Field(getter = "isIdTokenRequested", id = 4)
    private boolean k;
    @Field(getter = "isServerAuthCodeRequested", id = 5)
    private final boolean l;
    @Field(getter = "isForceCodeForRefreshToken", id = 6)
    private final boolean m;
    @Field(getter = "getServerClientId", id = 7)
    private String n;
    @Field(getter = "getHostedDomain", id = 8)
    private String o;
    @Field(getter = "getExtensions", id = 9)
    private ArrayList<GoogleSignInOptionsExtensionParcelable> p;
    private Map<Integer, GoogleSignInOptionsExtensionParcelable> q;

    public static final class a {
        private Set<Scope> a = new HashSet();
        private boolean b;
        private boolean c;
        private boolean d;
        private String e;
        private Account f;
        private String g;
        private Map<Integer, GoogleSignInOptionsExtensionParcelable> h = new HashMap();

        public final a a() {
            this.a.add(GoogleSignInOptions.c);
            return this;
        }

        public final a a(Scope scope, Scope... scopeArr) {
            this.a.add(scope);
            this.a.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public final a b() {
            this.a.add(GoogleSignInOptions.a);
            return this;
        }

        public final GoogleSignInOptions c() {
            if (this.a.contains(GoogleSignInOptions.e) && this.a.contains(GoogleSignInOptions.d)) {
                this.a.remove(GoogleSignInOptions.d);
            }
            if (this.d && (this.f == null || !this.a.isEmpty())) {
                a();
            }
            return new GoogleSignInOptions(new ArrayList(this.a), this.f, this.d, this.b, this.c, this.e, this.g, this.h);
        }
    }

    @Constructor
    GoogleSignInOptions(@Param(id = 1) int i, @Param(id = 2) ArrayList<Scope> arrayList, @Param(id = 3) Account account, @Param(id = 4) boolean z, @Param(id = 5) boolean z2, @Param(id = 6) boolean z3, @Param(id = 7) String str, @Param(id = 8) String str2, @Param(id = 9) ArrayList<GoogleSignInOptionsExtensionParcelable> arrayList2) {
        this(i, (ArrayList) arrayList, account, z, z2, z3, str, str2, a(arrayList2));
    }

    private GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map<Integer, GoogleSignInOptionsExtensionParcelable> map) {
        this.h = i;
        this.i = arrayList;
        this.j = account;
        this.k = z;
        this.l = z2;
        this.m = z3;
        this.n = str;
        this.o = str2;
        this.p = new ArrayList(map.values());
        this.q = map;
    }

    /* synthetic */ GoogleSignInOptions(ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map) {
        this(3, arrayList, account, z, z2, z3, str, str2, map);
    }

    private ArrayList<Scope> a() {
        return new ArrayList(this.i);
    }

    private static Map<Integer, GoogleSignInOptionsExtensionParcelable> a(@Nullable List<GoogleSignInOptionsExtensionParcelable> list) {
        Map<Integer, GoogleSignInOptionsExtensionParcelable> hashMap = new HashMap();
        if (list == null) {
            return hashMap;
        }
        for (GoogleSignInOptionsExtensionParcelable googleSignInOptionsExtensionParcelable : list) {
            hashMap.put(Integer.valueOf(googleSignInOptionsExtensionParcelable.a()), googleSignInOptionsExtensionParcelable);
        }
        return hashMap;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.p.size() > 0 || googleSignInOptions.p.size() > 0 || this.i.size() != googleSignInOptions.a().size() || !this.i.containsAll(googleSignInOptions.a())) {
                return false;
            }
            if (this.j == null) {
                if (googleSignInOptions.j != null) {
                    return false;
                }
            } else if (!this.j.equals(googleSignInOptions.j)) {
                return false;
            }
            if (TextUtils.isEmpty(this.n)) {
                if (!TextUtils.isEmpty(googleSignInOptions.n)) {
                    return false;
                }
            } else if (!this.n.equals(googleSignInOptions.n)) {
                return false;
            }
            return this.m == googleSignInOptions.m && this.k == googleSignInOptions.k && this.l == googleSignInOptions.l;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        Object arrayList = new ArrayList();
        ArrayList arrayList2 = this.i;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            arrayList.add(((Scope) obj).a());
        }
        Collections.sort(arrayList);
        return new b().a(arrayList).a(this.j).a(this.n).a(this.m).a(this.k).a(this.l).a();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = com.google.android.gms.common.internal.safeparcel.b.a(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, this.h);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 2, a());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, this.j, i);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, this.k);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, this.l);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, this.m);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, this.n);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, this.o);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 9, this.p);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, a);
    }
}
