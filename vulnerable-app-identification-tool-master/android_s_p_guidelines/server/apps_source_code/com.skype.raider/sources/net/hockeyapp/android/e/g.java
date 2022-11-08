package net.hockeyapp.android.e;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import net.hockeyapp.android.a;
import net.hockeyapp.android.f.b;
import net.hockeyapp.android.f.e;
import net.hockeyapp.android.f.f;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"StaticFieldLeak"})
public final class g extends d<Void, Void, Boolean> {
    private final int a;
    private final String b;
    private final Map<String, String> c;
    private Context d;
    private Handler e;
    private ProgressDialog f;
    private boolean g = true;

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return c();
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        Boolean bool = (Boolean) obj;
        if (this.f != null) {
            try {
                this.f.dismiss();
            } catch (Exception e) {
            }
        }
        if (this.e != null) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putBoolean("success", bool.booleanValue());
            message.setData(bundle);
            this.e.sendMessage(message);
        }
    }

    public g(Context context, Handler handler, String urlString, int mode, Map<String, String> params) {
        this.d = context;
        this.e = handler;
        this.b = urlString;
        this.a = mode;
        this.c = params;
        if (context != null) {
            a.a(context);
        }
    }

    public final void a() {
        this.g = false;
    }

    public final void a(Context context, Handler handler) {
        this.d = context;
        this.e = handler;
    }

    public final void b() {
        this.d = null;
        this.e = null;
        this.f = null;
    }

    protected final void onPreExecute() {
        if ((this.f == null || !this.f.isShowing()) && this.g) {
            this.f = ProgressDialog.show(this.d, "", "Please wait...", true, false);
        }
    }

    private Boolean c() {
        HttpURLConnection connection = null;
        try {
            int i = this.a;
            Map map = this.c;
            String str;
            if (i == 1) {
                connection = new f(this.b).a("POST").a(map).a();
            } else if (i == 2) {
                f a = new f(this.b).a("POST");
                str = (String) map.get("email");
                String str2 = (String) map.get("password");
                a.a("Authorization", "Basic " + b.a((str + ":" + str2).getBytes()));
                connection = a.a();
            } else if (i == 3) {
                str = (String) map.get("type");
                connection = new f(this.b + "?" + str + "=" + ((String) map.get("id"))).a();
            } else {
                throw new IllegalArgumentException("Login mode " + i + " not supported.");
            }
            connection.connect();
            if (connection.getResponseCode() == 200) {
                String responseStr = d.a(connection);
                if (!TextUtils.isEmpty(responseStr)) {
                    Boolean valueOf = Boolean.valueOf(a(responseStr));
                    if (connection == null) {
                        return valueOf;
                    }
                    connection.disconnect();
                    return valueOf;
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        } catch (IOException e) {
            e.f();
            if (connection != null) {
                connection.disconnect();
            }
        } catch (Throwable th) {
            if (connection != null) {
                connection.disconnect();
            }
            throw th;
        }
        return Boolean.valueOf(false);
    }

    private boolean a(String responseStr) {
        SharedPreferences prefs = this.d.getSharedPreferences("net.hockeyapp.android.login", 0);
        try {
            JSONObject response = new JSONObject(responseStr);
            String status = response.getString("status");
            if (TextUtils.isEmpty(status)) {
                return false;
            }
            if (this.a == 1) {
                if (status.equals("identified")) {
                    String iuid = response.getString("iuid");
                    if (!TextUtils.isEmpty(iuid)) {
                        prefs.edit().putString("iuid", iuid).putString("email", (String) this.c.get("email")).apply();
                        return true;
                    }
                }
            } else if (this.a == 2) {
                if (status.equals("authorized")) {
                    String auid = response.getString("auid");
                    if (!TextUtils.isEmpty(auid)) {
                        prefs.edit().putString("auid", auid).putString("email", (String) this.c.get("email")).apply();
                        return true;
                    }
                }
            } else if (this.a != 3) {
                throw new IllegalArgumentException("Login mode " + this.a + " not supported.");
            } else if (status.equals("validated")) {
                return true;
            } else {
                prefs.edit().remove("iuid").remove("auid").remove("email").apply();
            }
            return false;
        } catch (JSONException e) {
            e.f();
            return false;
        }
    }
}
