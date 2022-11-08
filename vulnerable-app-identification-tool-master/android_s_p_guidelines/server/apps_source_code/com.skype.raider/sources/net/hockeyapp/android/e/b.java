package net.hockeyapp.android.e;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.microsoft.tokenshare.AccountInfo;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;
import net.hockeyapp.android.a;
import net.hockeyapp.android.f.e;
import net.hockeyapp.android.f.j;
import net.hockeyapp.android.f.k;
import net.hockeyapp.android.i;
import net.hockeyapp.android.m;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends AsyncTask<Void, String, JSONArray> {
    protected String a = null;
    protected String b = null;
    protected String c = null;
    protected Boolean d = Boolean.valueOf(false);
    protected m e;
    private WeakReference<Context> f = null;
    private long g = 0;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return b();
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((JSONArray) obj);
    }

    public b(WeakReference<? extends Context> weakContext, String urlString, String appIdentifier) {
        this.c = appIdentifier;
        this.a = urlString;
        this.e = null;
        Context ctx = null;
        if (weakContext != null) {
            ctx = (Context) weakContext.get();
        }
        if (ctx != null) {
            this.f = new WeakReference(ctx.getApplicationContext());
            this.g = i.a(ctx);
            a.a(ctx);
        }
    }

    public final void a(WeakReference<? extends Context> weakContext) {
        Context ctx = (Context) weakContext.get();
        if (ctx != null) {
            this.f = new WeakReference(ctx.getApplicationContext());
            a.a(ctx);
        }
    }

    private JSONArray b() {
        Context context;
        if (this.f != null) {
            context = (Context) this.f.get();
        } else {
            context = null;
        }
        if (context == null) {
            return null;
        }
        this.b = a(context, "apk");
        try {
            int versionCode = Integer.parseInt(a.a);
            URLConnection connection = new URL(a(context, "json")).openConnection();
            connection.addRequestProperty("User-Agent", "HockeySDK/Android 5.1.0");
            connection.connect();
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            String jsonString = j.a(inputStream);
            inputStream.close();
            JSONArray json = new JSONArray(jsonString);
            if (a(context, json, versionCode)) {
                return b(json);
            }
        } catch (IOException e) {
            if (j.c(context)) {
                e.g("HockeyUpdate");
            }
            return null;
        } catch (JSONException e2) {
            if (j.c(context)) {
                e.g("HockeyUpdate");
            }
            return null;
        }
        return null;
    }

    private boolean a(Context context, JSONArray json, int versionCode) {
        boolean newerVersionFound = false;
        int index = 0;
        while (index < json.length()) {
            try {
                boolean largerVersionCode;
                JSONObject entry = json.getJSONObject(index);
                if (entry.getInt(AccountInfo.VERSION_KEY) > versionCode) {
                    largerVersionCode = true;
                } else {
                    largerVersionCode = false;
                }
                boolean newerApkFile;
                if (entry.getInt(AccountInfo.VERSION_KEY) == versionCode && k.a(context, entry.getLong("timestamp"))) {
                    newerApkFile = true;
                } else {
                    newerApkFile = false;
                }
                String string = entry.getString("minimum_os_version");
                String str = VERSION.RELEASE;
                if (str == null || str.equalsIgnoreCase("L")) {
                    str = "5.0";
                } else if (str.equalsIgnoreCase("M")) {
                    str = "6.0";
                } else if (str.equalsIgnoreCase("N")) {
                    str = "7.0";
                } else if (str.equalsIgnoreCase("O")) {
                    str = "8.0";
                } else if (Pattern.matches("^[a-zA-Z]+", str)) {
                    str = "99.0";
                }
                boolean minRequirementsMet = k.a(string, str) <= 0;
                if ((largerVersionCode || newerApkFile) && minRequirementsMet) {
                    if (entry.has("mandatory")) {
                        this.d = Boolean.valueOf(this.d.booleanValue() | entry.getBoolean("mandatory"));
                    }
                    newerVersionFound = true;
                }
                index++;
            } catch (JSONException e) {
                return false;
            }
        }
        return newerVersionFound;
    }

    private static JSONArray b(JSONArray json) {
        JSONArray result = new JSONArray();
        for (int index = 0; index < Math.min(json.length(), 25); index++) {
            try {
                result.put(json.get(index));
            } catch (JSONException e) {
            }
        }
        return result;
    }

    protected void a(org.json.JSONArray r2) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r1 = this;
        if (r2 == 0) goto L_0x000c;
    L_0x0002:
        r0 = "HockeyUpdate";
        net.hockeyapp.android.f.e.a(r0);
        r0 = r1.e;
        if (r0 == 0) goto L_0x000b;
    L_0x000b:
        return;
    L_0x000c:
        r0 = "HockeyUpdate";
        net.hockeyapp.android.f.e.a(r0);
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.e.b.a(org.json.JSONArray):void");
    }

    protected void a() {
        this.a = null;
        this.c = null;
    }

    private String a(Context context, String format) {
        SharedPreferences prefs;
        String auid;
        String iuid;
        StringBuilder builder = new StringBuilder();
        builder.append(this.a);
        builder.append("api/2/apps/");
        builder.append(this.c != null ? this.c : context.getPackageName());
        builder.append("?format=").append(format);
        String deviceIdentifier = null;
        try {
            deviceIdentifier = (String) a.a().get();
        } catch (InterruptedException e) {
            e.c();
            if (!TextUtils.isEmpty(deviceIdentifier)) {
                builder.append("&udid=").append(a(deviceIdentifier));
            }
            prefs = context.getSharedPreferences("net.hockeyapp.android.login", 0);
            auid = prefs.getString("auid", null);
            if (!TextUtils.isEmpty(auid)) {
                builder.append("&auid=").append(a(auid));
            }
            iuid = prefs.getString("iuid", null);
            if (!TextUtils.isEmpty(iuid)) {
                builder.append("&iuid=").append(a(iuid));
            }
            builder.append("&os=Android");
            builder.append("&os_version=").append(a(a.d));
            builder.append("&device=").append(a(a.f));
            builder.append("&oem=").append(a(a.g));
            builder.append("&app_version=").append(a(a.a));
            builder.append("&sdk=").append(a("HockeySDK"));
            builder.append("&sdk_version=").append(a("5.1.0"));
            builder.append("&lang=").append(a(Locale.getDefault().getLanguage()));
            builder.append("&usage_time=").append(this.g);
            return builder.toString();
        } catch (ExecutionException e2) {
            e.c();
            if (TextUtils.isEmpty(deviceIdentifier)) {
                builder.append("&udid=").append(a(deviceIdentifier));
            }
            prefs = context.getSharedPreferences("net.hockeyapp.android.login", 0);
            auid = prefs.getString("auid", null);
            if (TextUtils.isEmpty(auid)) {
                builder.append("&auid=").append(a(auid));
            }
            iuid = prefs.getString("iuid", null);
            if (TextUtils.isEmpty(iuid)) {
                builder.append("&iuid=").append(a(iuid));
            }
            builder.append("&os=Android");
            builder.append("&os_version=").append(a(a.d));
            builder.append("&device=").append(a(a.f));
            builder.append("&oem=").append(a(a.g));
            builder.append("&app_version=").append(a(a.a));
            builder.append("&sdk=").append(a("HockeySDK"));
            builder.append("&sdk_version=").append(a("5.1.0"));
            builder.append("&lang=").append(a(Locale.getDefault().getLanguage()));
            builder.append("&usage_time=").append(this.g);
            return builder.toString();
        }
        if (TextUtils.isEmpty(deviceIdentifier)) {
            builder.append("&udid=").append(a(deviceIdentifier));
        }
        prefs = context.getSharedPreferences("net.hockeyapp.android.login", 0);
        auid = prefs.getString("auid", null);
        if (TextUtils.isEmpty(auid)) {
            builder.append("&auid=").append(a(auid));
        }
        iuid = prefs.getString("iuid", null);
        if (TextUtils.isEmpty(iuid)) {
            builder.append("&iuid=").append(a(iuid));
        }
        builder.append("&os=Android");
        builder.append("&os_version=").append(a(a.d));
        builder.append("&device=").append(a(a.f));
        builder.append("&oem=").append(a(a.g));
        builder.append("&app_version=").append(a(a.a));
        builder.append("&sdk=").append(a("HockeySDK"));
        builder.append("&sdk_version=").append(a("5.1.0"));
        builder.append("&lang=").append(a(Locale.getDefault().getLanguage()));
        builder.append("&usage_time=").append(this.g);
        return builder.toString();
    }

    private static String a(String param) {
        try {
            return URLEncoder.encode(param, Constants.ENCODING);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
