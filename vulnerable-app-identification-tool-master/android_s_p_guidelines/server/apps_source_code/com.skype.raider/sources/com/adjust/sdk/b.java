package com.adjust.sdk;

import android.content.ContentResolver;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.skype.camera.imagefilter.ImageFilterManager;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

final class b {
    private static ILogger o = AdjustFactory.getLogger();
    Map<String, String> a;
    AdjustAttribution b;
    String c;
    String d;
    String e;
    String f;
    long g = -1;
    long h = -1;
    long i = -1;
    private AdjustConfig j;
    private a k;
    private a l;
    private SessionParameters m;
    private long n;

    private class a {
        long a = -1;
        int b = -1;
        String c = null;
        int d = -1;
        int e = -1;
        long f = -1;
        long g = -1;
        String h = null;
        final /* synthetic */ b i;

        a(b bVar, ActivityState activityState) {
            this.i = bVar;
            if (activityState != null) {
                this.a = activityState.lastInterval;
                this.b = activityState.eventCount;
                this.c = activityState.uuid;
                this.d = activityState.sessionCount;
                this.e = activityState.subsessionCount;
                this.f = activityState.sessionLength;
                this.g = activityState.timeSpent;
                this.h = activityState.pushToken;
            }
        }
    }

    public b(AdjustConfig adjustConfig, a deviceInfo, ActivityState activityState, SessionParameters sessionParameters, long createdAt) {
        this.j = adjustConfig;
        this.k = deviceInfo;
        this.l = new a(this, activityState);
        this.m = sessionParameters;
        this.n = createdAt;
    }

    public final ActivityPackage a(boolean isInDelay) {
        Map<String, String> parameters = b(isInDelay);
        ActivityPackage sessionPackage = a(ActivityKind.SESSION);
        sessionPackage.setPath("/session");
        sessionPackage.setSuffix("");
        sessionPackage.setParameters(parameters);
        return sessionPackage;
    }

    public final ActivityPackage a(AdjustEvent event, boolean isInDelay) {
        Map parameters = b();
        a(parameters, "event_count", (long) this.l.b);
        a(parameters, "event_token", event.eventToken);
        String str = "revenue";
        if (event.revenue != null) {
            a(parameters, str, Util.formatString("%.5f", event.revenue));
        }
        a(parameters, "currency", event.currency);
        if (!isInDelay) {
            a(parameters, Constants.CALLBACK_PARAMETERS, Util.mergeParameters(this.m.callbackParameters, event.callbackParameters, "Callback"));
            a(parameters, Constants.PARTNER_PARAMETERS, Util.mergeParameters(this.m.partnerParameters, event.partnerParameters, "Partner"));
        }
        ActivityPackage eventPackage = a(ActivityKind.EVENT);
        eventPackage.setPath("/event");
        if (event.revenue == null) {
            str = Util.formatString("'%s'", event.eventToken);
        } else {
            str = Util.formatString("(%.5f %s, '%s')", event.revenue, event.currency, event.eventToken);
        }
        eventPackage.setSuffix(str);
        eventPackage.setParameters(parameters);
        if (isInDelay) {
            eventPackage.setCallbackParameters(event.callbackParameters);
            eventPackage.setPartnerParameters(event.partnerParameters);
        }
        return eventPackage;
    }

    public final ActivityPackage a(String source) {
        Map parameters = b(false);
        a(parameters, ImageFilterManager.PROP_SOURCE, source);
        b(parameters, "click_time", this.g);
        a(parameters, Constants.REFTAG, this.c);
        a(parameters, "params", this.a);
        a(parameters, Constants.REFERRER, this.d);
        a(parameters, "raw_referrer", this.e);
        a(parameters, Constants.DEEPLINK, this.f);
        c(parameters, "click_time", this.h);
        c(parameters, "install_begin_time", this.i);
        if (this.b != null) {
            a(parameters, "tracker", this.b.trackerName);
            a(parameters, "campaign", this.b.campaign);
            a(parameters, "adgroup", this.b.adgroup);
            a(parameters, "creative", this.b.creative);
        }
        ActivityPackage clickPackage = a(ActivityKind.CLICK);
        clickPackage.setPath("/sdk_click");
        clickPackage.setSuffix("");
        clickPackage.setClickTimeInMilliseconds(this.g);
        clickPackage.setClickTimeInSeconds(this.h);
        clickPackage.setInstallBeginTimeInSeconds(this.i);
        clickPackage.setParameters(parameters);
        return clickPackage;
    }

    public final ActivityPackage b(String source) {
        Map parameters = c();
        a(parameters, ImageFilterManager.PROP_SOURCE, source);
        ActivityPackage clickPackage = a(ActivityKind.INFO);
        clickPackage.setPath("/sdk_info");
        clickPackage.setSuffix("");
        clickPackage.setParameters(parameters);
        return clickPackage;
    }

    public final ActivityPackage a() {
        Map<String, String> parameters = c();
        ActivityPackage attributionPackage = a(ActivityKind.ATTRIBUTION);
        attributionPackage.setPath("attribution");
        attributionPackage.setSuffix("");
        attributionPackage.setParameters(parameters);
        return attributionPackage;
    }

    private ActivityPackage a(ActivityKind activityKind) {
        ActivityPackage activityPackage = new ActivityPackage(activityKind);
        activityPackage.setClientSdk(this.k.g);
        return activityPackage;
    }

    private Map<String, String> b(boolean isInDelay) {
        Map parameters = b();
        d(parameters, "last_interval", this.l.a);
        a(parameters, "default_tracker", this.j.defaultTracker);
        a(parameters, "installed_at", this.k.A);
        a(parameters, "updated_at", this.k.B);
        if (!isInDelay) {
            a(parameters, Constants.CALLBACK_PARAMETERS, this.m.callbackParameters);
            a(parameters, Constants.PARTNER_PARAMETERS, this.m.partnerParameters);
        }
        return parameters;
    }

    private Map<String, String> b() {
        Map parameters = new HashMap();
        a(parameters);
        a(parameters, "fb_id", this.k.f);
        a(parameters, "package_name", this.k.h);
        a(parameters, "app_version", this.k.i);
        a(parameters, "device_type", this.k.j);
        a(parameters, "device_name", this.k.k);
        a(parameters, "device_manufacturer", this.k.l);
        a(parameters, "os_name", this.k.m);
        a(parameters, "os_version", this.k.n);
        a(parameters, "api_level", this.k.o);
        a(parameters, "language", this.k.p);
        a(parameters, "country", this.k.q);
        a(parameters, "screen_size", this.k.r);
        a(parameters, "screen_format", this.k.s);
        a(parameters, "screen_density", this.k.t);
        a(parameters, "display_width", this.k.u);
        a(parameters, "display_height", this.k.v);
        a(parameters, "hardware_name", this.k.w);
        a(parameters, "cpu_type", this.k.x);
        a(parameters, "os_build", this.k.y);
        a(parameters, "vm_isa", this.k.z);
        a(parameters, "mcc", Util.getMcc(this.j.context));
        a(parameters, "mnc", Util.getMnc(this.j.context));
        a(parameters, "connectivity_type", (long) Util.getConnectivityType(this.j.context));
        a(parameters, "network_type", (long) Util.getNetworkType(this.j.context));
        e(parameters);
        b(parameters);
        a(parameters, "android_uuid", this.l.c);
        a(parameters, "session_count", (long) this.l.d);
        a(parameters, "subsession_count", (long) this.l.e);
        d(parameters, "session_length", this.l.f);
        d(parameters, "time_spent", this.l.g);
        c(parameters);
        d(parameters);
        return parameters;
    }

    private Map<String, String> c() {
        Map parameters = new HashMap();
        a(parameters);
        b(parameters);
        c(parameters);
        d(parameters);
        return parameters;
    }

    private void a(Map<String, String> parameters) {
        this.k.a(this.j.context);
        a((Map) parameters, "tracking_enabled", this.k.b);
        a((Map) parameters, "gps_adid", this.k.a);
        if (this.k.a == null) {
            a((Map) parameters, "mac_sha1", this.k.c);
            a((Map) parameters, "mac_md5", this.k.d);
            a((Map) parameters, "android_id", this.k.e);
        }
    }

    private void b(Map<String, String> parameters) {
        a((Map) parameters, "app_token", this.j.appToken);
        a((Map) parameters, "environment", this.j.environment);
        a((Map) parameters, "device_known", this.j.deviceKnown);
        a((Map) parameters, "event_buffering_enabled", Boolean.valueOf(this.j.eventBufferingEnabled));
        a((Map) parameters, "push_token", this.l.h);
        ContentResolver contentResolver = this.j.context.getContentResolver();
        a((Map) parameters, "fire_adid", Util.getFireAdvertisingId(contentResolver));
        a((Map) parameters, "fire_tracking_enabled", Util.getFireTrackingEnabled(contentResolver));
        a((Map) parameters, "secret_id", this.j.secretId);
        a((Map) parameters, "app_secret", this.j.appSecret);
        if (this.j.readMobileEquipmentIdentity) {
            TelephonyManager telephonyManager = (TelephonyManager) this.j.context.getSystemService("phone");
            a((Map) parameters, "device_ids", Util.getTelephonyIds(telephonyManager));
            a((Map) parameters, "imeis", Util.getIMEIs(telephonyManager));
            a((Map) parameters, "meids", Util.getMEIDs(telephonyManager));
        }
    }

    private void c(Map<String, String> parameters) {
        b(parameters, "created_at", this.n);
        a((Map) parameters, "attribution_deeplink", Boolean.valueOf(true));
        a((Map) parameters, "needs_response_details", Boolean.valueOf(true));
    }

    private static void d(Map<String, String> parameters) {
        if (!parameters.containsKey("mac_sha1") && !parameters.containsKey("mac_md5") && !parameters.containsKey("android_id") && !parameters.containsKey("gps_adid")) {
            o.error("Missing device id's. Please check if Proguard is correctly set with Adjust SDK", new Object[0]);
        }
    }

    private void e(Map<String, String> parameters) {
        if (this.k.C != null) {
            for (Entry<String, String> entry : this.k.C.entrySet()) {
                a((Map) parameters, (String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    private static void a(Map<String, String> parameters, String key, String value) {
        if (!TextUtils.isEmpty(value)) {
            parameters.put(key, value);
        }
    }

    private static void a(Map<String, String> parameters, String key, long value) {
        if (value >= 0) {
            a((Map) parameters, key, Long.toString(value));
        }
    }

    private static void b(Map<String, String> parameters, String key, long value) {
        if (value > 0) {
            a((Map) parameters, key, new Date(value));
        }
    }

    private static void c(Map<String, String> parameters, String key, long value) {
        if (value > 0) {
            a((Map) parameters, key, new Date(1000 * value));
        }
    }

    private static void a(Map<String, String> parameters, String key, Date value) {
        a((Map) parameters, key, Util.dateFormatter.format(value));
    }

    private static void d(Map<String, String> parameters, String key, long durationInMilliSeconds) {
        if (durationInMilliSeconds >= 0) {
            a((Map) parameters, key, (500 + durationInMilliSeconds) / 1000);
        }
    }

    public static void a(Map<String, String> parameters, String key, Map<String, String> map) {
        if (map != null && map.size() != 0) {
            a((Map) parameters, key, new JSONObject(map).toString());
        }
    }

    private static void a(Map<String, String> parameters, String key, Boolean value) {
        if (value != null) {
            a((Map) parameters, key, (long) (value.booleanValue() ? 1 : 0));
        }
    }
}
