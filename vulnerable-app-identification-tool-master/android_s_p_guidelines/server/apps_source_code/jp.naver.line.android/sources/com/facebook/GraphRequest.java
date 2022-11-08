package com.facebook;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.internal.ap;
import com.facebook.internal.ar;
import com.facebook.internal.bg;
import com.facebook.internal.bj;
import com.facebook.internal.bn;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import jp.naver.android.npush.common.NPushIntent;
import org.apache.http.HttpHeaders;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphRequest {
    public static final String a = "GraphRequest";
    private static String b;
    private static Pattern c = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
    private static volatile String q;
    private AccessToken d;
    private ag e;
    private String f;
    private JSONObject g;
    private String h;
    private String i;
    private boolean j;
    private Bundle k;
    private x l;
    private String m;
    private Object n;
    private String o;
    private boolean p;

    public class ParcelableResourceWithMimeType<RESOURCE extends Parcelable> implements Parcelable {
        public static final Creator<ParcelableResourceWithMimeType> CREATOR = new Creator<ParcelableResourceWithMimeType>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new ParcelableResourceWithMimeType[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new ParcelableResourceWithMimeType(parcel, (byte) 0);
            }
        };
        private final String a;
        private final RESOURCE b;

        public int describeContents() {
            return 1;
        }

        /* synthetic */ ParcelableResourceWithMimeType(Parcel parcel, byte b) {
            this(parcel);
        }

        public final String a() {
            return this.a;
        }

        public final RESOURCE b() {
            return this.b;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            parcel.writeParcelable(this.b, i);
        }

        public ParcelableResourceWithMimeType(RESOURCE resource, String str) {
            this.a = str;
            this.b = resource;
        }

        private ParcelableResourceWithMimeType(Parcel parcel) {
            this.a = parcel.readString();
            this.b = parcel.readParcelable(s.f().getClassLoader());
        }
    }

    public GraphRequest() {
        this(null, null, null, null, null);
    }

    public GraphRequest(AccessToken accessToken, String str, Bundle bundle, ag agVar) {
        this(accessToken, str, bundle, agVar, null);
    }

    public GraphRequest(AccessToken accessToken, String str, Bundle bundle, ag agVar, x xVar) {
        this(accessToken, str, bundle, agVar, xVar, (byte) 0);
    }

    private GraphRequest(AccessToken accessToken, String str, Bundle bundle, ag agVar, x xVar, byte b) {
        this.j = true;
        this.p = false;
        this.d = accessToken;
        this.f = str;
        this.o = null;
        a(xVar);
        if (this.m == null || agVar == ag.GET) {
            if (agVar == null) {
                agVar = ag.GET;
            }
            this.e = agVar;
            if (bundle != null) {
                this.k = new Bundle(bundle);
            } else {
                this.k = new Bundle();
            }
            if (this.o == null) {
                this.o = s.g();
                return;
            }
            return;
        }
        throw new n("Can't change HTTP method on request with overridden URL.");
    }

    public static GraphRequest a(AccessToken accessToken, String str, JSONObject jSONObject) {
        GraphRequest graphRequest = new GraphRequest(accessToken, str, null, ag.POST, null);
        graphRequest.g = jSONObject;
        return graphRequest;
    }

    public static GraphRequest a(String str) {
        return new GraphRequest(null, str, null, null, null);
    }

    public final JSONObject a() {
        return this.g;
    }

    public final void a(JSONObject jSONObject) {
        this.g = jSONObject;
    }

    public final void b(String str) {
        this.o = str;
    }

    public final void b() {
        this.p = true;
    }

    public final Bundle c() {
        return this.k;
    }

    public final void a(Bundle bundle) {
        this.k = bundle;
    }

    public final AccessToken d() {
        return this.d;
    }

    public final x e() {
        return this.l;
    }

    public final void a(final x xVar) {
        if (s.a(ai.GRAPH_API_DEBUG_INFO) || s.a(ai.GRAPH_API_DEBUG_WARNING)) {
            this.l = new x(this) {
                final /* synthetic */ GraphRequest b;

                public final void a(af afVar) {
                    JSONObject b = afVar.b();
                    b = b != null ? b.optJSONObject("__debug__") : null;
                    JSONArray optJSONArray = b != null ? b.optJSONArray("messages") : null;
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            String optString = optJSONObject != null ? optJSONObject.optString(NPushIntent.PARAM_MESSAGE) : null;
                            String optString2 = optJSONObject != null ? optJSONObject.optString("type") : null;
                            String optString3 = optJSONObject != null ? optJSONObject.optString("link") : null;
                            if (!(optString == null || optString2 == null)) {
                                ai aiVar = ai.GRAPH_API_DEBUG_INFO;
                                if (optString2.equals("warning")) {
                                    aiVar = ai.GRAPH_API_DEBUG_WARNING;
                                }
                                if (!bj.a(optString3)) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append(optString);
                                    stringBuilder.append(" Link: ");
                                    stringBuilder.append(optString3);
                                    optString = stringBuilder.toString();
                                }
                                ar.a(aiVar, GraphRequest.a, optString);
                            }
                        }
                    }
                    if (xVar != null) {
                        xVar.a(afVar);
                    }
                }
            };
        } else {
            this.l = xVar;
        }
    }

    public final void a(Object obj) {
        this.n = obj;
    }

    public final Object f() {
        return this.n;
    }

    public final ab g() {
        Object obj = new GraphRequest[]{this};
        bn.a(obj, "requests");
        return b(new ac(Arrays.asList(obj)));
    }

    public static af a(GraphRequest graphRequest) {
        Object obj = new GraphRequest[]{graphRequest};
        bn.a(obj, "requests");
        List a = a(new ac(Arrays.asList(obj)));
        if (a != null && a.size() == 1) {
            return (af) a.get(0);
        }
        throw new n("invalid state: expected a single response");
    }

    public static List<af> a(ac acVar) {
        Throwable th;
        bn.a((Collection) acVar, "requests");
        URLConnection uRLConnection = null;
        try {
            URLConnection c = c(acVar);
            try {
                List<af> a = a((HttpURLConnection) c, acVar);
                bj.a(c);
                return a;
            } catch (Throwable th2) {
                th = th2;
                uRLConnection = c;
                bj.a(uRLConnection);
                throw th;
            }
        } catch (Throwable e) {
            List a2 = af.a(acVar.d(), null, new n(e));
            a(acVar, a2);
            bj.a(null);
            return a2;
        } catch (Throwable th3) {
            th = th3;
            bj.a(uRLConnection);
            throw th;
        }
    }

    public static ab b(ac acVar) {
        bn.a((Collection) acVar, "requests");
        ab abVar = new ab(acVar);
        abVar.executeOnExecutor(s.d(), new Void[0]);
        return abVar;
    }

    public static List<af> a(HttpURLConnection httpURLConnection, ac acVar) {
        List a = af.a(httpURLConnection, acVar);
        bj.a((URLConnection) httpURLConnection);
        if (acVar.size() == a.size()) {
            a(acVar, a);
            d.a().e();
            return a;
        }
        throw new n(String.format(Locale.US, "Received %d responses while expecting %d", new Object[]{Integer.valueOf(a.size()), Integer.valueOf(r4)}));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{Request:  accessToken: ");
        stringBuilder.append(this.d == null ? "null" : this.d);
        stringBuilder.append(", graphPath: ");
        stringBuilder.append(this.f);
        stringBuilder.append(", graphObject: ");
        stringBuilder.append(this.g);
        stringBuilder.append(", httpMethod: ");
        stringBuilder.append(this.e);
        stringBuilder.append(", parameters: ");
        stringBuilder.append(this.k);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static void a(final ac acVar, List<af> list) {
        int size = acVar.size();
        final ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            GraphRequest a = acVar.a(i);
            if (a.l != null) {
                arrayList.add(new Pair(a.l, list.get(i)));
            }
        }
        if (arrayList.size() > 0) {
            Runnable anonymousClass2 = new Runnable() {
                public final void run() {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        Pair pair = (Pair) it.next();
                        ((x) pair.first).a((af) pair.second);
                    }
                    for (ad a : acVar.e()) {
                        a.a();
                    }
                }
            };
            Handler c = acVar.c();
            if (c == null) {
                anonymousClass2.run();
                return;
            }
            c.post(anonymousClass2);
        }
    }

    private void h() {
        String d;
        if (this.d != null) {
            if (!this.k.containsKey("access_token")) {
                d = this.d.d();
                ar.a(d);
                this.k.putString("access_token", d);
            }
        } else if (!(this.p || this.k.containsKey("access_token"))) {
            d = s.j();
            String l = s.l();
            if (!(bj.a(d) || bj.a(l))) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(d);
                stringBuilder.append("|");
                stringBuilder.append(l);
                this.k.putString("access_token", stringBuilder.toString());
            }
        }
        this.k.putString("sdk", "android");
        this.k.putString("format", "json");
        if (s.a(ai.GRAPH_API_DEBUG_INFO)) {
            this.k.putString("debug", "info");
            return;
        }
        if (s.a(ai.GRAPH_API_DEBUG_WARNING)) {
            this.k.putString("debug", "warning");
        }
    }

    private String a(String str, Boolean bool) {
        if (!bool.booleanValue() && this.e == ag.POST) {
            return str;
        }
        Builder buildUpon = Uri.parse(str).buildUpon();
        for (String str2 : this.k.keySet()) {
            Object obj = this.k.get(str2);
            if (obj == null) {
                obj = "";
            }
            if (e(obj)) {
                buildUpon.appendQueryParameter(str2, f(obj).toString());
            } else if (this.e == ag.GET) {
                throw new IllegalArgumentException(String.format(Locale.US, "Unsupported parameter type for GET request: %s", new Object[]{obj.getClass().getSimpleName()}));
            }
        }
        return buildUpon.toString();
    }

    private String i() {
        if (this.m == null) {
            String format = String.format("%s/%s", new Object[]{bg.b(), j()});
            h();
            Uri parse = Uri.parse(a(format, Boolean.TRUE));
            return String.format("%s?%s", new Object[]{parse.getPath(), parse.getQuery()});
        }
        throw new n("Can't override URL for a batch request");
    }

    private String j() {
        if (c.matcher(this.f).matches()) {
            return this.f;
        }
        return String.format("%s/%s", new Object[]{this.o, this.f});
    }

    private void a(JSONArray jSONArray, Map<String, w> map) throws JSONException, IOException {
        JSONObject jSONObject = new JSONObject();
        if (this.h != null) {
            jSONObject.put("name", this.h);
            jSONObject.put("omit_response_on_success", this.j);
        }
        if (this.i != null) {
            jSONObject.put("depends_on", this.i);
        }
        String i = i();
        jSONObject.put("relative_url", i);
        jSONObject.put("method", this.e);
        if (this.d != null) {
            ar.a(this.d.d());
        }
        Iterable arrayList = new ArrayList();
        for (String str : this.k.keySet()) {
            Object obj = this.k.get(str);
            if (d(obj)) {
                String format = String.format(Locale.ROOT, "%s%d", new Object[]{"file", Integer.valueOf(map.size())});
                arrayList.add(format);
                map.put(format, new w(this, obj));
            }
        }
        if (!arrayList.isEmpty()) {
            jSONObject.put("attached_files", TextUtils.join(",", arrayList));
        }
        if (this.g != null) {
            final Iterable arrayList2 = new ArrayList();
            a(this.g, i, new y(this) {
                final /* synthetic */ GraphRequest b;

                public final void a(String str, String str2) throws IOException {
                    arrayList2.add(String.format(Locale.US, "%s=%s", new Object[]{str, URLEncoder.encode(str2, "UTF-8")}));
                }
            });
            jSONObject.put(TtmlNode.TAG_BODY, TextUtils.join("&", arrayList2));
        }
        jSONArray.put(jSONObject);
    }

    private static boolean d(ac acVar) {
        for (ad adVar : acVar.e()) {
            if (adVar instanceof ae) {
                return true;
            }
        }
        Iterator it = acVar.iterator();
        while (it.hasNext()) {
            if (((GraphRequest) it.next()).l instanceof z) {
                return true;
            }
        }
        return false;
    }

    private static boolean e(ac acVar) {
        Iterator it = acVar.iterator();
        while (it.hasNext()) {
            GraphRequest graphRequest = (GraphRequest) it.next();
            for (String str : graphRequest.k.keySet()) {
                if (d(graphRequest.k.get(str))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void a(ac acVar, ar arVar, int i, URL url, OutputStream outputStream, boolean z) throws IOException, JSONException {
        aa aaVar = new aa(outputStream, arVar, z);
        Map hashMap;
        if (i == 1) {
            GraphRequest a = acVar.a(0);
            hashMap = new HashMap();
            for (String str : a.k.keySet()) {
                Object obj = a.k.get(str);
                if (d(obj)) {
                    hashMap.put(str, new w(a, obj));
                }
            }
            if (arVar != null) {
                arVar.b("  Parameters:\n");
            }
            a(a.k, aaVar, a);
            if (arVar != null) {
                arVar.b("  Attachments:\n");
            }
            a(hashMap, aaVar);
            if (a.g != null) {
                a(a.g, url.getPath(), (y) aaVar);
            }
            return;
        }
        String f = f(acVar);
        if (bj.a(f)) {
            throw new n("App ID was not specified at the request or Settings.");
        }
        aaVar.a("batch_app_id", f);
        hashMap = new HashMap();
        a(aaVar, (Collection) acVar, hashMap);
        if (arVar != null) {
            arVar.b("  Attachments:\n");
        }
        a(hashMap, aaVar);
    }

    private static void a(String str, Object obj, y yVar, boolean z) throws IOException {
        while (true) {
            Class cls = obj.getClass();
            if (JSONObject.class.isAssignableFrom(cls)) {
                JSONObject jSONObject = (JSONObject) obj;
                if (z) {
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        Object[] objArr = new Object[]{str, (String) keys.next()};
                        a(String.format("%s[%s]", objArr), jSONObject.opt((String) keys.next()), yVar, z);
                    }
                    return;
                } else if (jSONObject.has("id")) {
                    obj = jSONObject.optString("id");
                } else if (jSONObject.has(ImagesContract.URL)) {
                    obj = jSONObject.optString(ImagesContract.URL);
                } else if (jSONObject.has("fbsdk:create_object")) {
                    obj = jSONObject.toString();
                } else {
                    return;
                }
            } else if (JSONArray.class.isAssignableFrom(cls)) {
                JSONArray jSONArray = (JSONArray) obj;
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    a(String.format(Locale.ROOT, "%s[%d]", new Object[]{str, Integer.valueOf(i)}), jSONArray.opt(i), yVar, z);
                }
                return;
            } else if (String.class.isAssignableFrom(cls) || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls)) {
                yVar.a(str, obj.toString());
                return;
            } else {
                if (Date.class.isAssignableFrom(cls)) {
                    yVar.a(str, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format((Date) obj));
                }
                return;
            }
        }
    }

    private static void a(Bundle bundle, aa aaVar, GraphRequest graphRequest) throws IOException {
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (e(obj)) {
                aaVar.a(str, obj, graphRequest);
            }
        }
    }

    private static void a(Map<String, w> map, aa aaVar) throws IOException {
        for (String str : map.keySet()) {
            w wVar = (w) map.get(str);
            if (d(wVar.b())) {
                aaVar.a(str, wVar.b(), wVar.a());
            }
        }
    }

    private static void a(aa aaVar, Collection<GraphRequest> collection, Map<String, w> map) throws JSONException, IOException {
        JSONArray jSONArray = new JSONArray();
        for (GraphRequest a : collection) {
            a.a(jSONArray, (Map) map);
        }
        aaVar.a("batch", jSONArray, (Collection) collection);
    }

    private static String f(ac acVar) {
        if (!bj.a(acVar.f())) {
            return acVar.f();
        }
        Iterator it = acVar.iterator();
        while (it.hasNext()) {
            AccessToken accessToken = ((GraphRequest) it.next()).d;
            if (accessToken != null) {
                String j = accessToken.j();
                if (j != null) {
                    return j;
                }
            }
        }
        if (bj.a(b)) {
            return s.j();
        }
        return b;
    }

    private static boolean d(Object obj) {
        return (obj instanceof Bitmap) || (obj instanceof byte[]) || (obj instanceof Uri) || (obj instanceof ParcelFileDescriptor) || (obj instanceof ParcelableResourceWithMimeType);
    }

    private static boolean e(Object obj) {
        return (obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Number) || (obj instanceof Date);
    }

    private static String f(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if ((obj instanceof Boolean) || (obj instanceof Number)) {
            return obj.toString();
        }
        if (obj instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(obj);
        }
        throw new IllegalArgumentException("Unsupported parameter type.");
    }

    private static HttpURLConnection c(ac acVar) {
        int i;
        String str;
        Throwable e;
        URL url;
        Iterator it = acVar.iterator();
        while (true) {
            i = 0;
            if (it.hasNext()) {
                GraphRequest graphRequest = (GraphRequest) it.next();
                if (ag.GET.equals(graphRequest.e)) {
                    Object obj;
                    Bundle bundle;
                    str = graphRequest.o;
                    if (!bj.a(str)) {
                        if (str.startsWith("v")) {
                            str = str.substring(1);
                        }
                        String[] split = str.split("\\.");
                        if ((split.length < 2 || Integer.parseInt(split[0]) <= 2) && (Integer.parseInt(split[0]) < 2 || Integer.parseInt(split[1]) < 4)) {
                            obj = null;
                            if (obj != null) {
                                bundle = graphRequest.k;
                                if (bundle.containsKey("fields") || bj.a(bundle.getString("fields"))) {
                                    ar.b(ai.DEVELOPER_ERRORS, "Request", "starting with Graph API v2.4, GET requests for /%s should contain an explicit \"fields\" parameter.", graphRequest.f);
                                }
                            }
                        }
                    }
                    obj = 1;
                    if (obj != null) {
                        bundle = graphRequest.k;
                        if (bundle.containsKey("fields")) {
                        }
                        ar.b(ai.DEVELOPER_ERRORS, "Request", "starting with Graph API v2.4, GET requests for /%s should contain an explicit \"fields\" parameter.", graphRequest.f);
                    }
                }
            } else {
                try {
                    break;
                } catch (Throwable e2) {
                    throw new n("could not construct URL for request", e2);
                }
            }
        }
        if (acVar.size() == 1) {
            String str2;
            GraphRequest a = acVar.a(0);
            if (a.m != null) {
                str2 = a.m.toString();
            } else {
                if (a.e == ag.POST && a.f != null && a.f.endsWith("/videos")) {
                    str = bg.c();
                } else {
                    str = bg.b();
                }
                str = String.format("%s/%s", new Object[]{str, a.j()});
                a.h();
                str2 = a.a(str, Boolean.FALSE);
            }
            url = new URL(str2);
        } else {
            url = new URL(bg.b());
        }
        OutputStream outputStream = null;
        URLConnection uRLConnection;
        try {
            uRLConnection = (HttpURLConnection) url.openConnection();
            str = "User-Agent";
            if (q == null) {
                q = String.format("%s.%s", new Object[]{"FBAndroidSDK", "4.34.0"});
                if (!bj.a(ap.a())) {
                    q = String.format(Locale.ROOT, "%s/%s", new Object[]{q, ap.a()});
                }
            }
            uRLConnection.setRequestProperty(str, q);
            uRLConnection.setRequestProperty(HttpHeaders.ACCEPT_LANGUAGE, Locale.getDefault().toString());
            uRLConnection.setChunkedStreamingMode(0);
            try {
                ar arVar = new ar(ai.REQUESTS, "Request");
                int size = acVar.size();
                boolean e3 = e(acVar);
                ag agVar = size == 1 ? acVar.a(0).e : ag.POST;
                uRLConnection.setRequestMethod(agVar.name());
                if (e3) {
                    uRLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    uRLConnection.setRequestProperty("Content-Encoding", "gzip");
                } else {
                    uRLConnection.setRequestProperty("Content-Type", String.format("multipart/form-data; boundary=%s", new Object[]{"3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f"}));
                }
                URL url2 = uRLConnection.getURL();
                arVar.b("Request:\n");
                arVar.a("Id", acVar.b());
                arVar.a("URL", (Object) url2);
                arVar.a("Method", uRLConnection.getRequestMethod());
                arVar.a("User-Agent", uRLConnection.getRequestProperty("User-Agent"));
                arVar.a("Content-Type", uRLConnection.getRequestProperty("Content-Type"));
                uRLConnection.setConnectTimeout(acVar.a());
                uRLConnection.setReadTimeout(acVar.a());
                if (agVar == ag.POST) {
                    i = 1;
                }
                if (i == 0) {
                    arVar.a();
                } else {
                    uRLConnection.setDoOutput(true);
                    try {
                        OutputStream bufferedOutputStream = new BufferedOutputStream(uRLConnection.getOutputStream());
                        if (e3) {
                            try {
                                outputStream = new GZIPOutputStream(bufferedOutputStream);
                            } catch (Throwable th) {
                                e2 = th;
                                outputStream = bufferedOutputStream;
                            }
                        } else {
                            outputStream = bufferedOutputStream;
                        }
                        if (d(acVar)) {
                            bufferedOutputStream = new al(acVar.c());
                            a(acVar, null, size, url2, bufferedOutputStream, e3);
                            outputStream = new am(outputStream, acVar, bufferedOutputStream.b(), (long) bufferedOutputStream.a());
                        }
                        a(acVar, arVar, size, url2, outputStream, e3);
                        outputStream.close();
                        arVar.a();
                    } catch (Throwable th2) {
                        e2 = th2;
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        throw e2;
                    }
                }
                return uRLConnection;
            } catch (IOException e4) {
                e2 = e4;
                bj.a(uRLConnection);
                throw new n("could not construct request body", e2);
            }
        } catch (IOException e5) {
            e2 = e5;
            uRLConnection = null;
            bj.a(uRLConnection);
            throw new n("could not construct request body", e2);
        }
    }

    private static void a(JSONObject jSONObject, String str, y yVar) throws IOException {
        Object obj;
        Iterator keys;
        String str2;
        Object opt;
        boolean z;
        Matcher matcher = c.matcher(str);
        String group = matcher.matches() ? matcher.group(1) : str;
        Object obj2 = (group.startsWith("me/") || group.startsWith("/me/")) ? 1 : null;
        if (obj2 != null) {
            int indexOf = str.indexOf(":");
            int indexOf2 = str.indexOf("?");
            if (indexOf > 3 && (indexOf2 == -1 || indexOf < indexOf2)) {
                obj = 1;
                keys = jSONObject.keys();
                while (keys.hasNext()) {
                    str2 = (String) keys.next();
                    opt = jSONObject.opt(str2);
                    z = obj == null && str2.equalsIgnoreCase("image");
                    a(str2, opt, yVar, z);
                }
            }
        }
        obj = null;
        keys = jSONObject.keys();
        while (keys.hasNext()) {
            str2 = (String) keys.next();
            opt = jSONObject.opt(str2);
            if (obj == null) {
            }
            a(str2, opt, yVar, z);
        }
    }
}
