package net.hockeyapp.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import net.hockeyapp.android.e.g;
import net.hockeyapp.android.f.j;

public final class e {
    static Class<?> a;
    static f b;
    private static String c = null;
    private static String d = null;
    private static Handler e = null;
    private static String f = null;
    private static int g;

    private static class a extends Handler {
        private final WeakReference<Context> a;

        a(Context context) {
            this.a = new WeakReference(context);
        }

        public final void handleMessage(Message msg) {
            boolean success = msg.getData().getBoolean("success");
            Context context = (Context) this.a.get();
            if (context != null) {
                if (success) {
                    net.hockeyapp.android.f.e.a("HockeyAuth");
                } else {
                    e.a(context);
                }
            }
        }
    }

    public static void a(Context context, String appIdentifier, String appSecret, int mode, Class<?> activity) {
        String str = "https://sdk.hockeyapp.net/";
        if (context != null) {
            c = j.c(appIdentifier);
            d = appSecret;
            f = str;
            g = mode;
            a = activity;
            if (e == null) {
                e = new a(context);
            }
            a.a(context);
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void a(final Activity context) {
        if (context != null && g != 0) {
            net.hockeyapp.android.f.a.a(new AsyncTask<Void, Object, Object>() {
                private String b;
                private String c;

                protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("net.hockeyapp.android.login", 0);
                    if (sharedPreferences.getInt("mode", -1) != e.g) {
                        net.hockeyapp.android.f.e.a("HockeyAuth");
                        sharedPreferences.edit().remove("auid").remove("iuid").putInt("mode", e.g).apply();
                    }
                    this.b = sharedPreferences.getString("auid", null);
                    this.c = sharedPreferences.getString("iuid", null);
                    return null;
                }

                protected final void onPostExecute(Object o) {
                    boolean notAuthenticated;
                    if (this.b == null && this.c == null) {
                        notAuthenticated = true;
                    } else {
                        notAuthenticated = false;
                    }
                    boolean auidMissing;
                    if (this.b == null && (e.g == 2 || e.g == 3)) {
                        auidMissing = true;
                    } else {
                        auidMissing = false;
                    }
                    boolean iuidMissing;
                    if (this.c == null && e.g == 1) {
                        iuidMissing = true;
                    } else {
                        iuidMissing = false;
                    }
                    if (notAuthenticated || auidMissing || iuidMissing) {
                        net.hockeyapp.android.f.e.a("HockeyAuth");
                        e.a(context);
                    } else if (e.g == 3) {
                        net.hockeyapp.android.f.e.a("HockeyAuth");
                        Map<String, String> params = new HashMap();
                        if (this.b != null) {
                            params.put("type", "auid");
                            params.put("id", this.b);
                        } else if (this.c != null) {
                            params.put("type", "iuid");
                            params.put("id", this.c);
                        }
                        AsyncTask verifyTask = new g(context, e.e, e.a(3), 3, params);
                        verifyTask.a();
                        net.hockeyapp.android.f.a.a(verifyTask);
                    }
                }
            });
        }
    }

    private static String a(int mode) {
        String suffix = "";
        if (mode == 2) {
            suffix = "authorize";
        } else if (mode == 1) {
            suffix = "check";
        } else if (mode == 3) {
            suffix = "validate";
        }
        return f + "api/3/apps/" + c + "/identity/" + suffix;
    }

    static /* synthetic */ void a(Context x0) {
        Intent intent = new Intent();
        int i = Boolean.valueOf(g == 3).booleanValue() ? 2 : g;
        intent.setFlags(335544320);
        intent.setClass(x0, LoginActivity.class);
        intent.putExtra(j.FRAGMENT_URL, a(i));
        intent.putExtra("mode", i);
        intent.putExtra("secret", d);
        x0.startActivity(intent);
    }
}
