package com.facebook.login;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.facebook.AccessToken;
import com.facebook.common.g;
import com.facebook.internal.bj;
import com.facebook.internal.bn;
import com.facebook.internal.i;
import com.facebook.n;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class LoginClient implements Parcelable {
    public static final Creator<LoginClient> CREATOR = new Creator<LoginClient>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new LoginClient[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new LoginClient(parcel);
        }
    };
    LoginMethodHandler[] a;
    int b = -1;
    Fragment c;
    f d;
    e e;
    boolean f;
    Request g;
    Map<String, String> h;
    private h i;

    public class Request implements Parcelable {
        public static final Creator<Request> CREATOR = new Creator<Request>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new Request[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new Request(parcel, (byte) 0);
            }
        };
        private final d a;
        private Set<String> b;
        private final a c;
        private final String d;
        private final String e;
        private boolean f;
        private String g;
        private String h;

        public int describeContents() {
            return 0;
        }

        /* synthetic */ Request(Parcel parcel, byte b) {
            this(parcel);
        }

        Request(d dVar, Set<String> set, a aVar, String str, String str2, String str3) {
            Set set2;
            this.f = false;
            this.a = dVar;
            if (set2 == null) {
                set2 = new HashSet();
            }
            this.b = set2;
            this.c = aVar;
            this.h = str;
            this.d = str2;
            this.e = str3;
        }

        final Set<String> a() {
            return this.b;
        }

        final void a(Set<String> set) {
            bn.a((Object) set, "permissions");
            this.b = set;
        }

        final d b() {
            return this.a;
        }

        final a c() {
            return this.c;
        }

        final String d() {
            return this.d;
        }

        final String e() {
            return this.e;
        }

        final boolean f() {
            return this.f;
        }

        final void a(boolean z) {
            this.f = z;
        }

        final String g() {
            return this.g;
        }

        final void a(String str) {
            this.g = str;
        }

        final String h() {
            return this.h;
        }

        final boolean i() {
            for (String b : this.b) {
                if (i.b(b)) {
                    return true;
                }
            }
            return false;
        }

        private Request(Parcel parcel) {
            boolean z = false;
            this.f = false;
            String readString = parcel.readString();
            a aVar = null;
            this.a = readString != null ? d.valueOf(readString) : null;
            Collection arrayList = new ArrayList();
            parcel.readStringList(arrayList);
            this.b = new HashSet(arrayList);
            readString = parcel.readString();
            if (readString != null) {
                aVar = a.valueOf(readString);
            }
            this.c = aVar;
            this.d = parcel.readString();
            this.e = parcel.readString();
            if (parcel.readByte() != (byte) 0) {
                z = true;
            }
            this.f = z;
            this.g = parcel.readString();
            this.h = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            String str = null;
            parcel.writeString(this.a != null ? this.a.name() : null);
            parcel.writeStringList(new ArrayList(this.b));
            if (this.c != null) {
                str = this.c.name();
            }
            parcel.writeString(str);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
            parcel.writeByte((byte) this.f);
            parcel.writeString(this.g);
            parcel.writeString(this.h);
        }
    }

    public class Result implements Parcelable {
        public static final Creator<Result> CREATOR = new Creator<Result>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new Result[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new Result(parcel, (byte) 0);
            }
        };
        final g a;
        final AccessToken b;
        final String c;
        final String d;
        final Request e;
        public Map<String, String> f;

        public int describeContents() {
            return 0;
        }

        /* synthetic */ Result(Parcel parcel, byte b) {
            this(parcel);
        }

        private Result(Request request, g gVar, AccessToken accessToken, String str, String str2) {
            bn.a((Object) gVar, "code");
            this.e = request;
            this.b = accessToken;
            this.c = str;
            this.a = gVar;
            this.d = str2;
        }

        static Result a(Request request, AccessToken accessToken) {
            return new Result(request, g.SUCCESS, accessToken, null, null);
        }

        static Result a(Request request, String str) {
            return new Result(request, g.CANCEL, null, str, null);
        }

        static Result a(Request request, String str, String str2) {
            return a(request, str, str2, null);
        }

        static Result a(Request request, String str, String str2, String str3) {
            return new Result(request, g.ERROR, null, TextUtils.join(": ", bj.b(str, str2)), str3);
        }

        private Result(Parcel parcel) {
            this.a = g.valueOf(parcel.readString());
            this.b = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = (Request) parcel.readParcelable(Request.class.getClassLoader());
            this.f = bj.a(parcel);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a.name());
            parcel.writeParcelable(this.b, i);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeParcelable(this.e, i);
            bj.a(parcel, this.f);
        }
    }

    public int describeContents() {
        return 0;
    }

    public LoginClient(Fragment fragment) {
        this.c = fragment;
    }

    public static int a() {
        return i.Login.a();
    }

    final LoginMethodHandler b() {
        return this.b >= 0 ? this.a[this.b] : null;
    }

    private boolean f() {
        if (this.f) {
            return true;
        }
        if (a("android.permission.INTERNET") != 0) {
            Activity activity = this.c.getActivity();
            b(Result.a(this.g, activity.getString(g.com_facebook_internet_permission_error_title), activity.getString(g.com_facebook_internet_permission_error_message)));
            return false;
        }
        this.f = true;
        return true;
    }

    final void c() {
        if (this.b >= 0) {
            a(b().a(), "skipped", null, null, b().a);
        }
        while (this.a != null && this.b < this.a.length - 1) {
            this.b++;
            LoginMethodHandler b = b();
            boolean z = false;
            if (!b.d() || f()) {
                z = b.a(this.g);
                if (z) {
                    g().a(this.g.e(), b.a());
                    continue;
                } else {
                    g().b(this.g.e(), b.a());
                    a("not_tried", b.a(), true);
                    continue;
                }
            } else {
                a("no_internet_permission", com.linecorp.yuki.effect.android.g.a, false);
                continue;
            }
            if (z) {
                return;
            }
        }
        if (this.g != null) {
            b(Result.a(this.g, "Login attempt failed.", null));
        }
    }

    private void a(String str, String str2, boolean z) {
        Object str22;
        if (this.h == null) {
            this.h = new HashMap();
        }
        if (this.h.containsKey(str) && z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append((String) this.h.get(str));
            stringBuilder.append(",");
            stringBuilder.append(str22);
            str22 = stringBuilder.toString();
        }
        this.h.put(str, str22);
    }

    final void a(Result result) {
        if (result.b == null || !AccessToken.b()) {
            b(result);
        } else if (result.b != null) {
            AccessToken a = AccessToken.a();
            AccessToken accessToken = result.b;
            if (!(a == null || accessToken == null)) {
                try {
                    if (a.k().equals(accessToken.k())) {
                        result = Result.a(this.g, result.b);
                        b(result);
                    }
                } catch (Exception e) {
                    b(Result.a(this.g, "Caught exception", e.getMessage()));
                    return;
                }
            }
            result = Result.a(this.g, "User logged in as different Facebook user.", null);
            b(result);
        } else {
            throw new n("Can't validate without a token");
        }
    }

    final void b(Result result) {
        LoginMethodHandler b = b();
        if (b != null) {
            a(b.a(), result, b.a);
        }
        if (this.h != null) {
            result.f = this.h;
        }
        this.a = null;
        this.b = -1;
        this.g = null;
        this.h = null;
        c(result);
    }

    private h g() {
        if (this.i == null || !this.i.a().equals(this.g.d())) {
            this.i = new h(this.c.getActivity(), this.g.d());
        }
        return this.i;
    }

    private void c(Result result) {
        if (this.d != null) {
            this.d.a(result);
        }
    }

    final void d() {
        if (this.e != null) {
            this.e.a();
        }
    }

    private void a(String str, Result result, Map<String, String> map) {
        a(str, result.a.a(), result.c, result.d, map);
    }

    private void a(String str, String str2, String str3, String str4, Map<String, String> map) {
        if (this.g == null) {
            g().a("fb_mobile_login_method_complete", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.", str);
        } else {
            g().a(this.g.e(), str, str2, str3, str4, map);
        }
    }

    static java.lang.String e() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.login.LoginClient.e():java.lang.String. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = new org.json.JSONObject;
        r0.<init>();
        r1 = "init";	 Catch:{ JSONException -> 0x000e }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ JSONException -> 0x000e }
        r0.put(r1, r2);	 Catch:{ JSONException -> 0x000e }
    L_0x000e:
        r0 = r0.toString();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.login.LoginClient.e():java.lang.String");
    }

    public LoginClient(Parcel parcel) {
        Parcelable[] readParcelableArray = parcel.readParcelableArray(LoginMethodHandler.class.getClassLoader());
        this.a = new LoginMethodHandler[readParcelableArray.length];
        for (int i = 0; i < readParcelableArray.length; i++) {
            this.a[i] = (LoginMethodHandler) readParcelableArray[i];
            this.a[i].a(this);
        }
        this.b = parcel.readInt();
        this.g = (Request) parcel.readParcelable(Request.class.getClassLoader());
        this.h = bj.a(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelableArray(this.a, i);
        parcel.writeInt(this.b);
        parcel.writeParcelable(this.g, i);
        bj.a(parcel, this.h);
    }

    final void a(Request request) {
        Object obj = (this.g == null || this.b < 0) ? null : 1;
        if (obj == null && request != null) {
            if (this.g != null) {
                throw new n("Attempted to authorize while a request is pending.");
            } else if (!AccessToken.b() || f()) {
                this.g = request;
                ArrayList arrayList = new ArrayList();
                d b = request.b();
                if (b.a()) {
                    arrayList.add(new GetTokenLoginMethodHandler(this));
                }
                if (b.b()) {
                    arrayList.add(new KatanaProxyLoginMethodHandler(this));
                }
                if (b.f()) {
                    arrayList.add(new FacebookLiteLoginMethodHandler(this));
                }
                if (b.e()) {
                    arrayList.add(new CustomTabLoginMethodHandler(this));
                }
                if (b.c()) {
                    arrayList.add(new WebViewLoginMethodHandler(this));
                }
                if (b.d()) {
                    arrayList.add(new DeviceAuthMethodHandler(this));
                }
                LoginMethodHandler[] loginMethodHandlerArr = new LoginMethodHandler[arrayList.size()];
                arrayList.toArray(loginMethodHandlerArr);
                this.a = loginMethodHandlerArr;
                c();
            }
        }
    }

    private int a(String str) {
        return this.c.getActivity().checkCallingOrSelfPermission(str);
    }
}
