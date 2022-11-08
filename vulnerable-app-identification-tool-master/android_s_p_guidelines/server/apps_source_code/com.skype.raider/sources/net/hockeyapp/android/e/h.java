package net.hockeyapp.android.e;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.ArrayList;
import net.hockeyapp.android.FeedbackActivity;
import net.hockeyapp.android.d.c;
import net.hockeyapp.android.d.d;
import net.hockeyapp.android.j;

@SuppressLint({"StaticFieldLeak"})
public final class h extends AsyncTask<Void, Void, d> {
    private Context a;
    private String b;
    private Handler c;
    private String d;
    private String e = null;

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        Class cls = null;
        if (this.a == null || this.b == null) {
            return null;
        }
        net.hockeyapp.android.f.d.a();
        d a = net.hockeyapp.android.f.d.a(this.b);
        if (!(a == null || a.b() == null)) {
            ArrayList a2 = a.b().a();
            if (!(a2 == null || a2.isEmpty())) {
                int c = ((c) a2.get(a2.size() - 1)).c();
                SharedPreferences sharedPreferences = this.a.getSharedPreferences("net.hockeyapp.android.feedback", 0);
                if (this.d.equals("send")) {
                    sharedPreferences.edit().putInt("idLastMessageSend", c).putInt("idLastMessageProcessed", c).apply();
                } else if (this.d.equals("fetch")) {
                    int i = sharedPreferences.getInt("idLastMessageSend", -1);
                    int i2 = sharedPreferences.getInt("idLastMessageProcessed", -1);
                    if (!(c == i || c == i2)) {
                        boolean a3;
                        sharedPreferences.edit().putInt("idLastMessageProcessed", c).apply();
                        net.hockeyapp.android.d a4 = net.hockeyapp.android.c.a();
                        if (a4 != null) {
                            a3 = a4.a();
                        } else {
                            a3 = false;
                        }
                        if (!(a3 || this.e == null)) {
                            Context context = this.a;
                            String str = this.e;
                            if (net.hockeyapp.android.c.a() != null) {
                                net.hockeyapp.android.c.a();
                                cls = FeedbackActivity.class;
                            }
                            if (cls == null) {
                                cls = FeedbackActivity.class;
                            }
                            i = context.getResources().getIdentifier("ic_menu_refresh", "drawable", "android");
                            Intent intent = new Intent();
                            intent.setFlags(805306368);
                            intent.setClass(context, cls);
                            intent.putExtra(j.FRAGMENT_URL, str);
                            net.hockeyapp.android.f.j.a(context, net.hockeyapp.android.f.j.a(context, PendingIntent.getActivity(context, 0, intent, ErrorDialogData.SUPPRESSED), context.getString(net.hockeyapp.android.h.d.hockeyapp_feedback_notification_title), context.getString(net.hockeyapp.android.h.d.hockeyapp_feedback_new_answer_notification_message), i, "net.hockeyapp.android.NOTIFICATION"), "net.hockeyapp.android.NOTIFICATION", context.getString(net.hockeyapp.android.h.d.hockeyapp_feedback_notification_channel));
                        }
                    }
                }
            }
        }
        return a;
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        d dVar = (d) obj;
        if (dVar != null && this.c != null) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putSerializable("parse_feedback_response", dVar);
            message.setData(bundle);
            this.c.sendMessage(message);
        }
    }

    public h(Context context, String feedbackResponse, Handler handler, String requestType) {
        this.a = context;
        this.b = feedbackResponse;
        this.c = handler;
        this.d = requestType;
    }
}
