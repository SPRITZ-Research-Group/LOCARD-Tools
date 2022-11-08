package net.hockeyapp.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import net.hockeyapp.android.d.e;
import net.hockeyapp.android.f.a;
import net.hockeyapp.android.f.h;
import net.hockeyapp.android.f.j;

public final class c {
    private static BroadcastReceiver a = null;
    private static boolean b = false;
    private static String c = null;
    private static String d = null;
    private static e e = e.REQUIRED;
    private static e f = e.REQUIRED;
    private static String g;
    private static String h;
    private static String i;
    private static d j = null;

    public static void a(Context context, String appIdentifier) {
        String str = "https://sdk.hockeyapp.net/";
        if (context != null) {
            c = j.c(appIdentifier);
            d = str;
            j = null;
            a.a(context);
        }
    }

    public static d a() {
        return j;
    }

    public static e b() {
        return e;
    }

    public static e c() {
        return f;
    }

    public static void a(final Context context, final Uri... attachments) {
        if (d == null || c == null) {
            net.hockeyapp.android.f.e.e();
        } else if (context != null) {
            Class cls;
            if (j != null) {
                cls = FeedbackActivity.class;
            } else {
                cls = null;
            }
            a.a(new AsyncTask<Void, Object, Intent>() {
                final /* synthetic */ Bundle a = null;
                final /* synthetic */ boolean d;

                protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                    String str;
                    String str2 = null;
                    Intent intent = new Intent();
                    if (!(this.a == null || this.a.isEmpty())) {
                        intent.putExtras(this.a);
                    }
                    intent.setFlags(ErrorDialogData.BINDER_CRASH);
                    intent.setClass(context, cls != null ? cls : FeedbackActivity.class);
                    intent.putExtra(j.FRAGMENT_URL, c.d());
                    if (this.d) {
                        str = null;
                    } else {
                        str = h.a().a(context);
                    }
                    intent.putExtra("token", str);
                    intent.putExtra("forceNewThread", this.d);
                    String e = c.g;
                    str = c.h;
                    String b = h.a().b(context);
                    if (b != null) {
                        String[] split = b.split("\\|");
                        if (split != null && split.length >= 2) {
                            e = split[0];
                            str = split[1];
                            if (!this.d && split.length >= 3) {
                                str2 = split[2];
                            }
                        }
                    }
                    intent.putExtra("initialUserName", e);
                    intent.putExtra("initialUserEmail", str);
                    intent.putExtra("initialUserSubject", str2);
                    intent.putExtra("initialAttachments", a());
                    intent.putExtra("userId", c.i);
                    return intent;
                }

                protected final /* synthetic */ void onPostExecute(Object obj) {
                    context.startActivity((Intent) obj);
                }

                private Uri[] a() {
                    ArrayList<Uri> initialAttachments = new ArrayList();
                    File[] screenshots = a.b(context).listFiles(new FilenameFilter(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = this$0;
                        }

                        public final boolean accept(File dir, String name) {
                            return name.endsWith(".jpg");
                        }
                    });
                    if (screenshots != null) {
                        for (File screenshot : screenshots) {
                            initialAttachments.add(Uri.fromFile(screenshot));
                        }
                    }
                    if (attachments != null && attachments.length > 0) {
                        initialAttachments.addAll(Arrays.asList(attachments));
                    }
                    if (initialAttachments.size() > 0) {
                        return (Uri[]) initialAttachments.toArray(new Uri[0]);
                    }
                    return null;
                }
            });
        }
    }

    static /* synthetic */ String d() {
        if (d != null && c != null) {
            return d + "api/2/apps/" + c + "/feedback/";
        }
        net.hockeyapp.android.f.e.e();
        return null;
    }
}
