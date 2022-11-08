package com.facebook;

import android.util.Log;
import com.facebook.internal.ar;
import com.facebook.internal.bj;
import java.io.Closeable;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class af {
    private static final String a = "af";
    private final HttpURLConnection b;
    private final JSONObject c;
    private final JSONArray d;
    private final FacebookRequestError e;
    private final String f;
    private final GraphRequest g;

    private af(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject) {
        this(graphRequest, httpURLConnection, str, jSONObject, null, null);
    }

    private af(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONArray jSONArray) {
        this(graphRequest, httpURLConnection, str, null, jSONArray, null);
    }

    private af(GraphRequest graphRequest, HttpURLConnection httpURLConnection, FacebookRequestError facebookRequestError) {
        this(graphRequest, httpURLConnection, null, null, null, facebookRequestError);
    }

    private af(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject, JSONArray jSONArray, FacebookRequestError facebookRequestError) {
        this.g = graphRequest;
        this.b = httpURLConnection;
        this.f = str;
        this.c = jSONObject;
        this.d = jSONArray;
        this.e = facebookRequestError;
    }

    public final FacebookRequestError a() {
        return this.e;
    }

    public final JSONObject b() {
        return this.c;
    }

    public java.lang.String toString() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.af.toString():java.lang.String. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r5 = this;
        r0 = java.util.Locale.US;	 Catch:{ IOException -> 0x0020 }
        r1 = "%d";	 Catch:{ IOException -> 0x0020 }
        r2 = 1;	 Catch:{ IOException -> 0x0020 }
        r2 = new java.lang.Object[r2];	 Catch:{ IOException -> 0x0020 }
        r3 = 0;	 Catch:{ IOException -> 0x0020 }
        r4 = r5.b;	 Catch:{ IOException -> 0x0020 }
        if (r4 == 0) goto L_0x0013;	 Catch:{ IOException -> 0x0020 }
    L_0x000c:
        r4 = r5.b;	 Catch:{ IOException -> 0x0020 }
        r4 = r4.getResponseCode();	 Catch:{ IOException -> 0x0020 }
        goto L_0x0015;	 Catch:{ IOException -> 0x0020 }
    L_0x0013:
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ IOException -> 0x0020 }
    L_0x0015:
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ IOException -> 0x0020 }
        r2[r3] = r4;	 Catch:{ IOException -> 0x0020 }
        r0 = java.lang.String.format(r0, r1, r2);	 Catch:{ IOException -> 0x0020 }
        goto L_0x0022;
    L_0x0020:
        r0 = "unknown";
    L_0x0022:
        r1 = new java.lang.StringBuilder;
        r2 = "{Response:  responseCode: ";
        r1.<init>(r2);
        r1.append(r0);
        r0 = ", graphObject: ";
        r1.append(r0);
        r0 = r5.c;
        r1.append(r0);
        r0 = ", error: ";
        r1.append(r0);
        r0 = r5.e;
        r1.append(r0);
        r0 = "}";
        r1.append(r0);
        r0 = r1.toString();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.af.toString():java.lang.String");
    }

    static List<af> a(HttpURLConnection httpURLConnection, ac acVar) {
        List<af> httpURLConnection2;
        Closeable closeable = null;
        List<af> a;
        try {
            InputStream errorStream;
            if (httpURLConnection2.getResponseCode() >= HttpStatus.SC_BAD_REQUEST) {
                errorStream = httpURLConnection2.getErrorStream();
            } else {
                errorStream = httpURLConnection2.getInputStream();
            }
            closeable = errorStream;
            ar.a(ai.INCLUDE_RAW_RESPONSES, "Response", "Response (raw)\n  Size: %d\n  Response:\n%s\n", Integer.valueOf(bj.a((InputStream) closeable).length()), r3);
            a = a(httpURLConnection2, (List) acVar, new JSONTokener(r3).nextValue());
            ar.a(ai.REQUESTS, "Response", "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", acVar.b(), Integer.valueOf(r3.length()), a);
            return a;
        } catch (n e) {
            a = ai.REQUESTS;
            ar.a((ai) a, "Response", "Response <Error>: %s", e);
            httpURLConnection2 = a((List) acVar, httpURLConnection2, e);
            return httpURLConnection2;
        } catch (Throwable e2) {
            a = ai.REQUESTS;
            ar.a((ai) a, "Response", "Response <Error>: %s", e2);
            httpURLConnection2 = a((List) acVar, httpURLConnection2, new n(e2));
            return httpURLConnection2;
        } finally {
            bj.a(closeable);
        }
    }

    private static List<af> a(HttpURLConnection httpURLConnection, List<GraphRequest> list, Object obj) throws n, JSONException {
        JSONArray jSONArray;
        GraphRequest graphRequest;
        Object obj2;
        JSONObject jSONObject;
        FacebookRequestError a;
        Object afVar;
        StringBuilder stringBuilder;
        int size = list.size();
        List<af> arrayList = new ArrayList(size);
        int i = 0;
        if (size == 1) {
            GraphRequest graphRequest2 = (GraphRequest) list.get(0);
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(TtmlNode.TAG_BODY, obj);
                jSONObject2.put("code", httpURLConnection != null ? httpURLConnection.getResponseCode() : 200);
                jSONArray = new JSONArray();
                jSONArray.put(jSONObject2);
            } catch (Exception e) {
                arrayList.add(new af(graphRequest2, httpURLConnection, new FacebookRequestError(httpURLConnection, e)));
            } catch (Exception e2) {
                arrayList.add(new af(graphRequest2, httpURLConnection, new FacebookRequestError(httpURLConnection, e2)));
            }
            if (jSONArray instanceof JSONArray) {
                jSONArray = jSONArray;
                if (jSONArray.length() == size) {
                    while (i < jSONArray.length()) {
                        graphRequest = (GraphRequest) list.get(i);
                        try {
                            obj2 = jSONArray.get(i);
                            if (obj2 instanceof JSONObject) {
                                jSONObject = (JSONObject) obj2;
                                a = FacebookRequestError.a(jSONObject, obj, httpURLConnection);
                                if (a == null) {
                                    Log.e(a, a.toString());
                                    if (a.b() == 190 && bj.a(graphRequest.d())) {
                                        if (a.c() != 493) {
                                            AccessToken.a(null);
                                        } else if (!AccessToken.a().l()) {
                                            AccessToken.c();
                                        }
                                    }
                                    afVar = new af(graphRequest, httpURLConnection, a);
                                } else {
                                    obj2 = bj.a(jSONObject, TtmlNode.TAG_BODY, "FACEBOOK_NON_JSON_RESULT");
                                    if (obj2 instanceof JSONObject) {
                                        afVar = new af(graphRequest, httpURLConnection, obj2.toString(), (JSONObject) obj2);
                                    } else if (obj2 instanceof JSONArray) {
                                        obj2 = JSONObject.NULL;
                                    } else {
                                        afVar = new af(graphRequest, httpURLConnection, obj2.toString(), (JSONArray) obj2);
                                    }
                                }
                                arrayList.add(afVar);
                                i++;
                            }
                            if (obj2 != JSONObject.NULL) {
                                afVar = new af(graphRequest, httpURLConnection, obj2.toString(), null);
                                arrayList.add(afVar);
                                i++;
                            } else {
                                stringBuilder = new StringBuilder("Got unexpected object type in response, class: ");
                                stringBuilder.append(obj2.getClass().getSimpleName());
                                throw new n(stringBuilder.toString());
                            }
                        } catch (Exception e3) {
                            arrayList.add(new af(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, e3)));
                        } catch (Exception e32) {
                            arrayList.add(new af(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, e32)));
                        }
                    }
                    return arrayList;
                }
            }
            throw new n("Unexpected number of results");
        }
        jSONArray = obj;
        if (jSONArray instanceof JSONArray) {
            jSONArray = jSONArray;
            if (jSONArray.length() == size) {
                while (i < jSONArray.length()) {
                    graphRequest = (GraphRequest) list.get(i);
                    obj2 = jSONArray.get(i);
                    if (obj2 instanceof JSONObject) {
                        jSONObject = (JSONObject) obj2;
                        a = FacebookRequestError.a(jSONObject, obj, httpURLConnection);
                        if (a == null) {
                            obj2 = bj.a(jSONObject, TtmlNode.TAG_BODY, "FACEBOOK_NON_JSON_RESULT");
                            if (obj2 instanceof JSONObject) {
                                afVar = new af(graphRequest, httpURLConnection, obj2.toString(), (JSONObject) obj2);
                            } else if (obj2 instanceof JSONArray) {
                                obj2 = JSONObject.NULL;
                            } else {
                                afVar = new af(graphRequest, httpURLConnection, obj2.toString(), (JSONArray) obj2);
                            }
                        } else {
                            Log.e(a, a.toString());
                            if (a.c() != 493) {
                                AccessToken.a(null);
                            } else if (AccessToken.a().l()) {
                                AccessToken.c();
                            }
                            afVar = new af(graphRequest, httpURLConnection, a);
                        }
                        arrayList.add(afVar);
                        i++;
                    }
                    if (obj2 != JSONObject.NULL) {
                        stringBuilder = new StringBuilder("Got unexpected object type in response, class: ");
                        stringBuilder.append(obj2.getClass().getSimpleName());
                        throw new n(stringBuilder.toString());
                    }
                    afVar = new af(graphRequest, httpURLConnection, obj2.toString(), null);
                    arrayList.add(afVar);
                    i++;
                }
                return arrayList;
            }
        }
        throw new n("Unexpected number of results");
    }

    static List<af> a(List<GraphRequest> list, HttpURLConnection httpURLConnection, n nVar) {
        int size = list.size();
        List<af> arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new af((GraphRequest) list.get(i), httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception) nVar)));
        }
        return arrayList;
    }
}
