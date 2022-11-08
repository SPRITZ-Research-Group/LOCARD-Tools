package net.hockeyapp.android.e;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.hockeyapp.android.a;
import net.hockeyapp.android.f.e;
import net.hockeyapp.android.f.f;
import net.hockeyapp.android.f.j;
import net.hockeyapp.android.h.d;

@SuppressLint({"StaticFieldLeak"})
public final class i extends d<Void, Void, HashMap<String, String>> {
    private Context a;
    private Handler b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private List<Uri> i;
    private String j;
    private boolean k;
    private ProgressDialog l;
    private boolean m = true;
    private int n = -1;

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        if (this.k && this.j != null) {
            return d();
        }
        if (this.k) {
            return null;
        }
        if (this.i.isEmpty()) {
            return b();
        }
        HashMap c = c();
        String str = (String) c.get("status");
        if (!(str == null || !str.startsWith("2") || this.a == null)) {
            File file = new File(this.a.getCacheDir(), "HockeyApp");
            if (file.exists()) {
                for (File file2 : file.listFiles()) {
                    if (!(file2 == null || Boolean.valueOf(file2.delete()).booleanValue())) {
                        e.b("SendFeedbackTask");
                    }
                }
            }
            for (File file3 : a.b(this.a).listFiles(new FilenameFilter(this) {
                final /* synthetic */ i a;

                {
                    this.a = this$0;
                }

                public final boolean accept(File dir, String name) {
                    return name.endsWith(".jpg");
                }
            })) {
                if (this.i.contains(Uri.fromFile(file3))) {
                    if (file3.delete()) {
                        new StringBuilder("Screenshot '").append(file3.getName()).append("' has been deleted");
                        e.b("SendFeedbackTask");
                    } else {
                        e.f("SendFeedbackTask");
                    }
                }
            }
        }
        return c;
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        HashMap hashMap = (HashMap) obj;
        if (this.l != null) {
            try {
                this.l.dismiss();
            } catch (Exception e) {
            }
        }
        if (this.b != null) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            if (hashMap != null) {
                bundle.putString("request_type", (String) hashMap.get("type"));
                bundle.putString("feedback_response", (String) hashMap.get("response"));
                bundle.putString("feedback_status", (String) hashMap.get("status"));
            } else {
                bundle.putString("request_type", "unknown");
            }
            message.setData(bundle);
            this.b.sendMessage(message);
        }
    }

    public i(Context context, String urlString, String name, String email, String subject, String text, String userId, List<Uri> attachmentUris, String token, Handler handler, boolean isFetchMessages) {
        this.a = context;
        this.c = urlString;
        this.d = name;
        this.e = email;
        this.f = subject;
        this.g = text;
        this.h = userId;
        this.i = attachmentUris;
        this.j = token;
        this.b = handler;
        this.k = isFetchMessages;
        if (context != null) {
            a.a(context);
        }
    }

    public final void a(Handler handler) {
        this.b = handler;
    }

    public final void a(Context context) {
        this.a = context;
        if (getStatus() != Status.RUNNING) {
            return;
        }
        if ((this.l == null || !this.l.isShowing()) && this.m) {
            this.l = ProgressDialog.show(this.a, "", e(), true, false);
        }
    }

    public final void a() {
        this.a = null;
        if (this.l != null) {
            this.l.dismiss();
            this.l = null;
        }
    }

    protected final void onPreExecute() {
        if ((this.l == null || !this.l.isShowing()) && this.m) {
            this.l = ProgressDialog.show(this.a, "", e(), true, false);
        }
    }

    private HashMap<String, String> b() {
        HashMap<String, String> result = new HashMap();
        result.put("type", "send");
        HttpURLConnection urlConnection = null;
        try {
            Map parameters = new HashMap();
            parameters.put("name", this.d);
            parameters.put("email", this.e);
            parameters.put("subject", this.f);
            parameters.put("text", this.g);
            parameters.put("bundle_identifier", a.c);
            parameters.put("bundle_short_version", a.b);
            parameters.put("bundle_version", a.a);
            parameters.put("os_version", a.d);
            parameters.put("oem", a.g);
            parameters.put("model", a.f);
            parameters.put("sdk_version", "5.1.0");
            if (this.h != null) {
                parameters.put("user_string", this.h);
            }
            if (this.j != null) {
                this.c += this.j + "/";
            }
            urlConnection = new f(this.c).a(this.j != null ? "PUT" : "POST").a(parameters).a();
            urlConnection.connect();
            result.put("status", String.valueOf(urlConnection.getResponseCode()));
            result.put("response", d.a(urlConnection));
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.f();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (Throwable th) {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    private HashMap<String, String> c() {
        HashMap<String, String> result = new HashMap();
        result.put("type", "send");
        HttpURLConnection urlConnection = null;
        try {
            Map<String, String> parameters = new HashMap();
            parameters.put("name", this.d);
            parameters.put("email", this.e);
            parameters.put("subject", this.f);
            parameters.put("text", this.g);
            parameters.put("bundle_identifier", a.c);
            parameters.put("bundle_short_version", a.b);
            parameters.put("bundle_version", a.a);
            parameters.put("os_version", a.d);
            parameters.put("oem", a.g);
            parameters.put("model", a.f);
            parameters.put("sdk_version", "5.1.0");
            if (this.h != null) {
                parameters.put("user_string", this.h);
            }
            if (this.j != null) {
                this.c += this.j + "/";
            }
            urlConnection = new f(this.c).a(this.j != null ? "PUT" : "POST").a(parameters, this.a, this.i).a();
            urlConnection.connect();
            result.put("status", String.valueOf(urlConnection.getResponseCode()));
            result.put("response", d.a(urlConnection));
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.f();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (Throwable th) {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    private HashMap<String, String> d() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.c).append(j.a(this.j));
        if (this.n != -1) {
            sb.append("?last_message_id=").append(this.n);
        }
        HashMap<String, String> result = new HashMap();
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = new f(sb.toString()).a();
            result.put("type", "fetch");
            urlConnection.connect();
            result.put("status", String.valueOf(urlConnection.getResponseCode()));
            result.put("response", d.a(urlConnection));
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.f();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (Throwable th) {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    private String e() {
        String loadingMessage = this.a.getString(d.hockeyapp_feedback_sending_feedback_text);
        if (this.k) {
            return this.a.getString(d.hockeyapp_feedback_fetching_feedback_text);
        }
        return loadingMessage;
    }
}
