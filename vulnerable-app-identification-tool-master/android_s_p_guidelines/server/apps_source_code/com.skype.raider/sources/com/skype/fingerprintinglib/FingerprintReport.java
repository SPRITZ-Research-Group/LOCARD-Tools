package com.skype.fingerprintinglib;

import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import java.util.List;

public class FingerprintReport {
    public String A;
    public String B;
    public String C;
    public String D;
    public List<String> E;
    public String F;
    public long G;
    public String H;
    public String I;
    public List<String> J;
    public String K;
    public boolean L;
    public String M;
    public String N;
    public long O;
    public long P;
    public String Q;
    public GeoLocation R;
    public String S;
    public List<String> T;
    public String a;
    public String b;
    public long c;
    public long d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public long k;
    public String l;
    public boolean m;
    public boolean n;
    public boolean o;
    public String p = "android";
    public String q;
    public String r;
    public String s;
    public long t;
    public String u;
    public long v;
    public long w;
    public String x;
    public String y;
    public boolean z;

    public final ar a() {
        WritableNativeMap map = new WritableNativeMap();
        map.putString("applicationGuid", this.a);
        map.putString("applicationId", this.b);
        map.putDouble("applicationFirstInstallationTime", (double) this.c);
        map.putDouble("applicationLastUpdateTime", (double) this.d);
        map.putString("applicationVersion", this.e);
        map.putString("advertisingId", this.f);
        map.putString("androidId", this.g);
        map.putString("deviceId", this.h);
        map.putString("deviceModel", this.i);
        map.putString("deviceName", this.j);
        map.putDouble("deviceUptime", (double) this.k);
        map.putString("gsfId", this.l);
        map.putBoolean("isSmsEnabled", this.m);
        map.putBoolean("isEmulator", this.n);
        map.putBoolean("isRooted", this.o);
        map.putString("osType", this.p);
        map.putString("osVersion", this.q);
        map.putString("phoneType", this.r);
        map.putString("serialNumber", this.s);
        map.putDouble("totalStorageSpace", (double) this.t);
        map.putString("baseStationId", this.u);
        map.putDouble("cdmaNetworkId", (double) this.v);
        map.putDouble("cdmaSystemId", (double) this.w);
        map.putString("cellId", this.x);
        map.putString("connectionType", this.y);
        map.putBoolean("isInRoaming", this.z);
        map.putString("networkOperator", this.A);
        map.putString("simOperatorName", this.B);
        map.putString("simSerialNumber", this.C);
        map.putString("subscriberId", this.D);
        map.putArray("ipAddresses", a(this.E));
        map.putString("proxyHost", this.F);
        map.putDouble("proxyPort", (double) this.G);
        map.putString("vpnInterface", this.H);
        map.putString("bssid", this.I);
        map.putArray("bssidArray", a(this.J));
        map.putString("ssid", this.K);
        map.putBoolean("isDaylightSaving", this.L);
        map.putString("localeCountry", this.M);
        map.putString("localeLanguage", this.N);
        map.putDouble("timestamp", (double) this.O);
        map.putDouble("timezoneOffset", (double) this.P);
        map.putString("timezoneName", this.Q);
        if (this.R != null) {
            GeoLocation geoLocation = this.R;
            ar writableNativeMap = new WritableNativeMap();
            writableNativeMap.putDouble("latitude", geoLocation.a);
            writableNativeMap.putDouble("longitude", geoLocation.b);
            writableNativeMap.putDouble("accuracy", (double) geoLocation.c);
            writableNativeMap.putDouble("timestamp", (double) geoLocation.d);
            map.putMap("geoLocation", writableNativeMap);
        }
        map.putString("locationAreaCode", this.S);
        map.putArray("knownApplications", a(this.T));
        return map;
    }

    private static aq a(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        aq ipAddressesArray = new WritableNativeArray();
        for (String str : stringList) {
            ipAddressesArray.pushString(str);
        }
        return ipAddressesArray;
    }
}
