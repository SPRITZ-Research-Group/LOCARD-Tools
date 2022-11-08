package com.applovin.impl.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class bu {
    public static float a(org.json.JSONObject r2, java.lang.String r3, float r4, com.applovin.sdk.AppLovinSdk r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, float, com.applovin.sdk.AppLovinSdk):float. bs: []
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
        if (r2 == 0) goto L_0x0036;
    L_0x0002:
        r0 = r2.has(r3);
        if (r0 == 0) goto L_0x0036;
    L_0x0008:
        r0 = r2.getDouble(r3);	 Catch:{ JSONException -> 0x0021 }
        r2 = -4039728866288205824; // 0xc7efffffe0000000 float:-3.6893488E19 double:-3.4028234663852886E38;
        r5 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1));
        if (r5 >= 0) goto L_0x0036;
    L_0x0015:
        r2 = 5183643170566569984; // 0x47efffffe0000000 float:-3.6893488E19 double:3.4028234663852886E38;
        r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r5 >= 0) goto L_0x0036;
    L_0x001e:
        r2 = (float) r0;
        r4 = r2;
        goto L_0x0036;
    L_0x0021:
        if (r5 == 0) goto L_0x0036;
    L_0x0023:
        r2 = r5.getLogger();
        r5 = "JsonUtils";
        r0 = "Failed to retrieve float property for key = ";
        r3 = java.lang.String.valueOf(r3);
        r3 = r0.concat(r3);
        r2.e(r5, r3);
    L_0x0036:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, float, com.applovin.sdk.AppLovinSdk):float");
    }

    public static int a(org.json.JSONObject r1, java.lang.String r2, int r3, com.applovin.sdk.AppLovinSdk r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, int, com.applovin.sdk.AppLovinSdk):int. bs: []
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
        if (r1 == 0) goto L_0x0022;
    L_0x0002:
        r0 = r1.has(r2);
        if (r0 == 0) goto L_0x0022;
    L_0x0008:
        r1 = r1.getInt(r2);	 Catch:{ JSONException -> 0x000d }
        goto L_0x0023;
    L_0x000d:
        if (r4 == 0) goto L_0x0022;
    L_0x000f:
        r1 = r4.getLogger();
        r4 = "JsonUtils";
        r0 = "Failed to retrieve int property for key = ";
        r2 = java.lang.String.valueOf(r2);
        r2 = r0.concat(r2);
        r1.e(r4, r2);
    L_0x0022:
        r1 = r3;
    L_0x0023:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, int, com.applovin.sdk.AppLovinSdk):int");
    }

    public static long a(org.json.JSONObject r2, java.lang.String r3, long r4, com.applovin.sdk.AppLovinSdk r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, long, com.applovin.sdk.AppLovinSdk):long. bs: []
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
        if (r2 == 0) goto L_0x0023;
    L_0x0002:
        r0 = r2.has(r3);
        if (r0 == 0) goto L_0x0023;
    L_0x0008:
        r0 = r2.getLong(r3);	 Catch:{ JSONException -> 0x000e }
        r4 = r0;
        goto L_0x0023;
    L_0x000e:
        if (r6 == 0) goto L_0x0023;
    L_0x0010:
        r2 = r6.getLogger();
        r6 = "JsonUtils";
        r0 = "Failed to retrieve int property for key = ";
        r3 = java.lang.String.valueOf(r3);
        r3 = r0.concat(r3);
        r2.e(r6, r3);
    L_0x0023:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, long, com.applovin.sdk.AppLovinSdk):long");
    }

    public static java.lang.Boolean a(org.json.JSONObject r4, java.lang.String r5, java.lang.Boolean r6, com.applovin.sdk.AppLovinSdk r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, java.lang.Boolean, com.applovin.sdk.AppLovinSdk):java.lang.Boolean. bs: []
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
        if (r4 == 0) goto L_0x0042;
    L_0x0002:
        r0 = r4.has(r5);
        if (r0 == 0) goto L_0x0042;
    L_0x0008:
        r0 = r4.getBoolean(r5);	 Catch:{ JSONException -> 0x0012 }
        r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ JSONException -> 0x0012 }
        r6 = r0;
        goto L_0x0042;
    L_0x0012:
        if (r7 == 0) goto L_0x0030;
    L_0x0014:
        r0 = r7.getLogger();
        r1 = "JsonUtils";
        r2 = new java.lang.StringBuilder;
        r3 = "Unable to parse boolean for key = ";
        r2.<init>(r3);
        r2.append(r5);
        r3 = "... Attempting to parse it as an int";
        r2.append(r3);
        r2 = r2.toString();
        r0.w(r1, r2);
    L_0x0030:
        r6 = r6.booleanValue();
        r4 = a(r4, r5, r6, r7);
        if (r4 <= 0) goto L_0x003c;
    L_0x003a:
        r4 = 1;
        goto L_0x003d;
    L_0x003c:
        r4 = 0;
    L_0x003d:
        r4 = java.lang.Boolean.valueOf(r4);
        return r4;
    L_0x0042:
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, java.lang.Boolean, com.applovin.sdk.AppLovinSdk):java.lang.Boolean");
    }

    private static Object a(Object obj) throws JSONException {
        if (obj == JSONObject.NULL) {
            return null;
        }
        if (obj instanceof JSONObject) {
            return a((JSONObject) obj);
        }
        if (obj instanceof JSONArray) {
            obj = a((JSONArray) obj);
        }
        return obj;
    }

    public static java.lang.String a(org.json.JSONObject r1, java.lang.String r2, java.lang.String r3, com.applovin.sdk.AppLovinSdk r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, java.lang.String, com.applovin.sdk.AppLovinSdk):java.lang.String. bs: []
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
        if (r1 == 0) goto L_0x0022;
    L_0x0002:
        r0 = r1.has(r2);
        if (r0 == 0) goto L_0x0022;
    L_0x0008:
        r1 = r1.getString(r2);	 Catch:{ JSONException -> 0x000d }
        goto L_0x0023;
    L_0x000d:
        if (r4 == 0) goto L_0x0022;
    L_0x000f:
        r1 = r4.getLogger();
        r4 = "JsonUtils";
        r0 = "Failed to retrieve string property for key = ";
        r2 = java.lang.String.valueOf(r2);
        r2 = r0.concat(r2);
        r1.e(r4, r2);
    L_0x0022:
        r1 = r3;
    L_0x0023:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, java.lang.String, com.applovin.sdk.AppLovinSdk):java.lang.String");
    }

    public static List a(JSONArray jSONArray) throws JSONException {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(a(jSONArray.get(i)));
        }
        return arrayList;
    }

    public static Map<String, String> a(JSONObject jSONObject) throws JSONException {
        Map<String, String> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, a(jSONObject.get(str)).toString());
        }
        return hashMap;
    }

    public static org.json.JSONArray a(org.json.JSONObject r1, java.lang.String r2, org.json.JSONArray r3, com.applovin.sdk.AppLovinSdk r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, org.json.JSONArray, com.applovin.sdk.AppLovinSdk):org.json.JSONArray. bs: []
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
        if (r1 == 0) goto L_0x0022;
    L_0x0002:
        r0 = r1.has(r2);
        if (r0 == 0) goto L_0x0022;
    L_0x0008:
        r1 = r1.getJSONArray(r2);	 Catch:{ JSONException -> 0x000d }
        goto L_0x0023;
    L_0x000d:
        if (r4 == 0) goto L_0x0022;
    L_0x000f:
        r1 = r4.getLogger();
        r4 = "JsonUtils";
        r0 = "Failed to retrieve JSON array for key = ";
        r2 = java.lang.String.valueOf(r2);
        r2 = r0.concat(r2);
        r1.e(r4, r2);
    L_0x0022:
        r1 = r3;
    L_0x0023:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, org.json.JSONArray, com.applovin.sdk.AppLovinSdk):org.json.JSONArray");
    }

    static JSONObject a(Map<String, ?> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : map.entrySet()) {
            jSONObject.put((String) entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }

    public static org.json.JSONObject a(org.json.JSONArray r1, int r2, org.json.JSONObject r3, com.applovin.sdk.AppLovinSdk r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.bu.a(org.json.JSONArray, int, org.json.JSONObject, com.applovin.sdk.AppLovinSdk):org.json.JSONObject. bs: []
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
        if (r1 == 0) goto L_0x0022;
    L_0x0002:
        r0 = r1.length();
        if (r2 >= r0) goto L_0x0022;
    L_0x0008:
        r1 = r1.getJSONObject(r2);	 Catch:{ JSONException -> 0x000d }
        goto L_0x0023;
    L_0x000d:
        if (r4 == 0) goto L_0x0022;
    L_0x000f:
        r1 = r4.getLogger();
        r4 = "JsonUtils";
        r0 = "Failed to retrieve JSON object from array for index = ";
        r2 = java.lang.String.valueOf(r2);
        r2 = r0.concat(r2);
        r1.e(r4, r2);
    L_0x0022:
        r1 = r3;
    L_0x0023:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.bu.a(org.json.JSONArray, int, org.json.JSONObject, com.applovin.sdk.AppLovinSdk):org.json.JSONObject");
    }

    public static org.json.JSONObject a(org.json.JSONObject r1, java.lang.String r2, org.json.JSONObject r3, com.applovin.sdk.AppLovinSdk r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, org.json.JSONObject, com.applovin.sdk.AppLovinSdk):org.json.JSONObject. bs: []
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
        if (r1 == 0) goto L_0x0022;
    L_0x0002:
        r0 = r1.has(r2);
        if (r0 == 0) goto L_0x0022;
    L_0x0008:
        r1 = r1.getJSONObject(r2);	 Catch:{ JSONException -> 0x000d }
        goto L_0x0023;
    L_0x000d:
        if (r4 == 0) goto L_0x0022;
    L_0x000f:
        r1 = r4.getLogger();
        r4 = "JsonUtils";
        r0 = "Failed to retrieve JSON property for key = ";
        r2 = java.lang.String.valueOf(r2);
        r2 = r0.concat(r2);
        r1.e(r4, r2);
    L_0x0022:
        r1 = r3;
    L_0x0023:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.bu.a(org.json.JSONObject, java.lang.String, org.json.JSONObject, com.applovin.sdk.AppLovinSdk):org.json.JSONObject");
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    public static void b(org.json.JSONObject r0, java.lang.String r1, long r2, com.applovin.sdk.AppLovinSdk r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.bu.b(org.json.JSONObject, java.lang.String, long, com.applovin.sdk.AppLovinSdk):void. bs: []
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
        if (r0 == 0) goto L_0x001b;
    L_0x0002:
        r0.put(r1, r2);	 Catch:{ JSONException -> 0x0006 }
        return;
    L_0x0006:
        if (r4 == 0) goto L_0x001b;
    L_0x0008:
        r0 = r4.getLogger();
        r2 = "JsonUtils";
        r3 = "Failed to put long property for key = ";
        r1 = java.lang.String.valueOf(r1);
        r1 = r3.concat(r1);
        r0.e(r2, r1);
    L_0x001b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.bu.b(org.json.JSONObject, java.lang.String, long, com.applovin.sdk.AppLovinSdk):void");
    }
}
